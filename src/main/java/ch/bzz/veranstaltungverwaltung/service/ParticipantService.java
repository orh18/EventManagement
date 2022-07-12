package ch.bzz.veranstaltungverwaltung.service;

import ch.bzz.veranstaltungverwaltung.data.DataHandler;
import ch.bzz.veranstaltungverwaltung.model.Participant;
import ch.bzz.veranstaltungverwaltung.util.AES256;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

/**
 * services for reading, adding, changing, and deleting participant
 *
 * @author : Obin Rokibul Hoque
 * @version : 1.0
 * @date : 2022-05-22
 */
@Path("participant")
public class ParticipantService {

    /**
     * reads a list of all participants
     *
     * @return participants as JSON
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listParticipants(
            @CookieParam("role") String role
    ) {
        List<Participant> participantList = DataHandler.readAllParticipants();
        int httpStatus = 200;
        if (role == null || AES256.decrypt(role).equals("guest")) {
            httpStatus = 403;
            participantList = null;
        }
        return Response
                .status(httpStatus)
                .entity(participantList)
                .build();
    }

    /**
     * reads a participant identified by the uuid
     *
     * @param participantUUID the uuid of the participant
     * @return participant
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readParticipant(
            @QueryParam("uuid") String participantUUID,
            @CookieParam("role") String role
    ) {
        int httpStatus = 200;
        Participant participant = DataHandler.readParticipantByUUID(participantUUID);
        if (role == null || AES256.decrypt(role).equals("guest")) {
            httpStatus = 403;
            participant = null;
        } else if (participant == null) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(participant)
                .build();
    }

    /**
     * inserts a new participant
     *
     * @param participant the participant
     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertParticipant(
            @Valid @BeanParam Participant participant,
            @CookieParam("role") String role
    ) {
        int httpStatus = 200;
        if (role == null || !AES256.decrypt(role).equals("admin")) {
            httpStatus = 403;
        } else {
            participant.setParticipantUUID(UUID.randomUUID().toString());
            DataHandler.insertParticipant(participant);
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * updates a participant
     *
     * @param participant the participant
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateParticipant(
            @Valid @BeanParam Participant participant,
            @CookieParam("role") String role
    ) {
        int httpStatus = 200;

        Participant oldParticipant = DataHandler.readParticipantByUUID(participant.getParticipantUUID());
        if (role == null || !AES256.decrypt(role).equals("admin")) {
            httpStatus = 403;
        } else if (oldParticipant != null) {
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
     *
     * @param participantUUID the uuid of the participant
     * @return Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteParticipant(
            @QueryParam("uuid") String participantUUID,
            @CookieParam("role") String role
    ) {
        int httpStatus = 200;
        if (role == null || !AES256.decrypt(role).equals("admin")) {
            httpStatus = 403;
        } else {
            if (!DataHandler.deleteParticipant(participantUUID)) {
                httpStatus = 410;
            }
        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
}
