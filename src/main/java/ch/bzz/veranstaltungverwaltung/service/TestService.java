package ch.bzz.veranstaltungverwaltung.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Test Service
 *
 * @author : Obin Rokibul Hoque
 * @version : 1.0
 * @date : 2022-05-22
 */
@Path("test")
public class TestService {
    /**
     * confirms the applications runs correctly
     *
     * @return message
     */
    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    public Response test() {

        return Response
                .status(200)
                .entity("Test erfolgreich")
                .build();
    }
}

