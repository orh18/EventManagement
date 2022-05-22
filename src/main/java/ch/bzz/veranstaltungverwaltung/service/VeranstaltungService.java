package ch.bzz.veranstaltungverwaltung.service;

import ch.bzz.veranstaltungverwaltung.data.DataHandler;
import ch.bzz.veranstaltungverwaltung.model.Veranstaltung;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Veranstaltung Service
 */
@Path("veranstaltung")
public class VeranstaltungService {
    /**
     * liest eine Liste von Veranstaltungen
     * @return Veranstaltungen als JSON
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listVeranstaltung() {
        List<Veranstaltung> veranstaltungList = DataHandler.getInstance().readAllVeranstaltungen();
        return Response
                .status(200)
                .entity(veranstaltungList)
                .build();
    }

    /**
     * liest ein Teilnehmer mit der gegebene id
     * @return Teilnehmer als JSON
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readVeranstaltung(
            @QueryParam("uuid") String veranstaltungUUID
    ) {
        Veranstaltung veranstaltung = DataHandler.getInstance().readVeranstaltungByUUID(veranstaltungUUID);
        return Response
                .status(200)
                .entity(veranstaltung)
                .build();
    }
}
