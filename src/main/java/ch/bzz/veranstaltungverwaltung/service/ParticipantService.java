package ch.bzz.veranstaltungverwaltung.service;

import ch.bzz.veranstaltungverwaltung.data.DataHandler;
import ch.bzz.veranstaltungverwaltung.model.Participant;

import javax.validation.Valid;
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
     * @param participantUUID the uuid of the participant
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
     * @param participant the participant
     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertParticipant(
            @Valid @BeanParam Participant participant
    ) {
        participant.setParticipantUUID(UUID.randomUUID().toString());
        DataHandler.insertParticipant(participant);

        return Response
                .status(200)
                .entity("")
                .build();
    }

    /**
     *
     * updates a participant
     * @param participant the participant
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateParticipant(
            @Valid @BeanParam Participant participant
    ) {
        int httpStatus = 200;
        Participant oldParticipant = DataHandler.readParticipantByUUID(participant.getParticipantUUID());
        if(oldParticipant != null) {
            oldParticipant.setName(participant.getName());
            oldParticipant.setLastName(participant.getLastName());
            oldParticipant.setTelNumber(participant.getTelNumber());

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
     * @param participantUUID the uuid of the participant
     * @return Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteParticipant(
            @QueryParam("uuid") String participantUUID
    ) {
        int httpStatus = 200;
        if (!DataHandler.deleteParticipant(participantUUID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
}
