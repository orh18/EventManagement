package ch.bzz.veranstaltungverwaltung.service;

import ch.bzz.veranstaltungverwaltung.data.DataHandler;
import ch.bzz.veranstaltungverwaltung.model.Participant;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

/**
 * services for reading, adding, changing, and deleting participant
 * @author  : Obin Rokibul Hoque
 * @date    : 2022-05-22
 * @version : 1.0
 */
@Path("participant")
public class ParticipantService {

    /**
     * reads a list of all participants
     * @return  participants as JSON
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listParticipants() {
        List<Participant> participantList = DataHandler.readAllParticipants();
        return Response
                .status(200)
                .entity(participantList)
                .build();
    }

    /**
     * reads a participant identified by the uuid
     * @param participantUUID
     * @return participant
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readParticipant(
            @QueryParam("uuid") String participantUUID
    ) {
        int httpStatus = 200;
        Participant participant = DataHandler.readParticipantByUUID(participantUUID);
        if (participant == null) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(participant)
                .build();
    }

    /**
     * inserts a new participant
     * @param name
     * @param vorname
     * @param handynummer
     * @return Response
     */
    @PUT
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertParticipant(
            @FormParam("name") String name,
            @FormParam("vorname") String vorname,
            @FormParam("handynummer") String handynummer
    ) {
        Participant participant = new Participant();
        participant.setParticipantUUID(UUID.randomUUID().toString());
        participant.setName(name);
        participant.setLastname(vorname);
        participant.setTelNumber(handynummer);

        DataHandler.insertParticipant(participant);

        return Response
                .status(200)
                .entity("")
                .build();
    }

    /**
     *
     * updates a participant
     * @param name
     * @param vorname
     * @param handynummer
     * @return Response
     */
    @POST
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateParticipant(
            @FormParam("teilnehmerUUID") String teilnehmerUUID,
            @FormParam("name") String name,
            @FormParam("vorname") String vorname,
            @FormParam("handynummer") String handynummer
    ) {
        int httpStatus = 200;
        Participant participant = DataHandler.readParticipantByUUID(teilnehmerUUID);
        if(participant != null) {
            participant.setName(name);
            participant.setLastname(vorname);
            participant.setTelNumber(handynummer);

            DataHandler.updateParticipant();
        } else {
            httpStatus = 410;
        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * deletes a participant identified by the uuid
     * @param participantUUID
     * @return book
     */
    @GET
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteParticipant(
            @QueryParam("uuid") String participantUUID
    ) {
        int httpStatus = 200;
        if (!DataHandler.deleteDiscipline(participantUUID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
}
