package ch.bzz.veranstaltungverwaltung.service;

import ch.bzz.veranstaltungverwaltung.data.DataHandler;
import ch.bzz.veranstaltungverwaltung.model.Disziplin;
import ch.bzz.veranstaltungverwaltung.model.Teilnehmer;
import ch.bzz.veranstaltungverwaltung.model.Veranstaltung;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Disziplin Service
 */
@Path("disziplin")
public class DisziplinService {
    /**
     * liest eine Liste von Disziplinen
     * @return Disziplinen als JSON
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listDisziplin() {
        List<Disziplin> disziplinList = DataHandler.getInstance().readAllDisziplinen();
        return Response
                .status(200)
                .entity(disziplinList)
                .build();
    }

    /**
     * liest eine Disziplin mit der gegebene id
     * @return Disziplin als JSON
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readDisziplin(
            @QueryParam("uuid") String disziplinUUID
    ) {
        Disziplin disziplin = DataHandler.getInstance().readDisziplinByUUID(disziplinUUID);
        return Response
                .status(200)
                .entity(disziplin)
                .build();
    }
}
