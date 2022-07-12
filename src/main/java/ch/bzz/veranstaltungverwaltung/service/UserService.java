package ch.bzz.veranstaltungverwaltung.service;

import ch.bzz.veranstaltungverwaltung.data.DataHandler;
import ch.bzz.veranstaltungverwaltung.data.UserData;
import ch.bzz.veranstaltungverwaltung.model.User;
import ch.bzz.veranstaltungverwaltung.util.AES256;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.util.HashMap;

/**
 * services for authentication and authorization of users
 */
@PermitAll
@Path("user")
public class UserService {
    static HashMap<String, Integer> twoFAMap = new HashMap<>();

    @POST
    @Path("login")
    @Produces(MediaType.TEXT_PLAIN)
    public Response login (
            @FormParam("username") String username,
            @FormParam("password") String password
    )
    {
        int httpStatus;

        User user = UserData.readUser(username, password);
        if(user == null || user.getRole() == null || user.getRole().equals("guest")) {
            httpStatus = 401;
        } else {
            httpStatus = 200;
        }

        NewCookie cookieName = new NewCookie(
                "name",
                AES256.encrypt(user.getUsername()),
                "/",
                "",
                "Login-Cookie",
                600,
                false
        );

        return Response
                .status(httpStatus)
                .entity("")
                .cookie(cookieName)
                .build();

    }

    /**
     * logout current user
     *
     * @return Response object with guest-cookie
     */
    @DELETE
    @Path("logout")
    @Produces(MediaType.TEXT_PLAIN)
    public Response logout () {

        NewCookie cookie = new NewCookie(
                "role",
                "guest",
                "/",
                "",
                "Logout-Cookie",
                0,
                false
        );

        return Response
                .status(200)
                .entity("")
                .cookie(cookie)
                .build();
    }

    /**
     * get 2 factor authentication nr
     *
     * @return 2fa nr
     */
    @GET
    @Path("/get2fa")
    @Produces(MediaType.TEXT_PLAIN)
    public Response get2fa(
            @CookieParam("name") String name
    ) {
        int nr = (int) (100000 + Math.random() * 900000);
        String username = AES256.decrypt(name);
        twoFAMap.put(username, nr);


        return Response
                .ok()
                .entity(nr)
                .build();
    }

    /**
     * check 2 factor authentication nr
     *
     * @return cookie with role of user
     */
    @POST
    @Path("/post2fa")
    @Produces(MediaType.TEXT_PLAIN)
    public Response post2fa(
            @CookieParam("name") String name,
            @FormParam("nr") int nr
    ) {
        String loggedInName = AES256.decrypt(name);
        int expected = twoFAMap.get(loggedInName);
        if(nr == expected) {
            User user = UserData.readUser(loggedInName);
            NewCookie cookieRole = new NewCookie(
                    "role",
                    AES256.encrypt(user.getRole()),
                    "/",
                    "",
                    "Login-Cookie",
                    600,
                    false
            );

            return Response
                    .ok()
                    .cookie(cookieRole)
                    .entity(loggedInName)
                    .build();
        }
        return Response
                .status(401)
                .entity("")
                .build();
    }
}
