package ch.bzz.veranstaltungverwaltung.service;

import ch.bzz.veranstaltungverwaltung.data.DataHandler;
import ch.bzz.veranstaltungverwaltung.model.Teilnehmer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Teilnehmer Service
 */
@Path("teilnehmer")
public class TeilnehmerService {

    /**
     * liest eine Liste von Teilnehmern
     * @return Teilnehmer als JSON
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listTeilnehmer() {
        List<Teilnehmer> teilnehmerList = DataHandler.getInstance().readAllTeilnehmer();
        return Response
                .status(200)
                .entity(teilnehmerList)
                .build();
    }

    /**
     * liest ein Teilnehmer mit der gegebene id
     * @return Teilnehmer als JSON
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readTeilnehmer(
            @QueryParam("uuid") String teilnehmerUUID
    ) {
        Teilnehmer teilnehmer = DataHandler.getInstance().readTeilnehmerByUUID(teilnehmerUUID);
        return Response
                .status(200)
                .entity(teilnehmer)
                .build();
    }
}
