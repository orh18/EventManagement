package ch.bzz.veranstaltungverwaltung.service;

import ch.bzz.veranstaltungverwaltung.data.UserData;
import ch.bzz.veranstaltungverwaltung.model.User;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

/**
 * services for authentication and authorization of users
 */
@PermitAll
@Path("user")
public class UserService {

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
            httpStatus = 404;
        } else {
            httpStatus = 200;
        }
        NewCookie cookie = new NewCookie(
                "role",
                user.getRole(),
                "/",
                "",
                "Login-Cookie",
                600,
                false
        );

        return Response
                .status(httpStatus)
                .entity("")
                .cookie(cookie)
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
                1,
                false
        );

        return Response
                .status(200)
                .entity("")
                .cookie(cookie)
                .build();
    }
}
