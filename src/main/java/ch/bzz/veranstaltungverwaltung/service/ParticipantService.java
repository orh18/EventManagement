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
     * @param lastName
     * @param telNumber
     * @return Response
     */
    @PUT
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertParticipant(
            @FormParam("name") String name,
            @FormParam("lastName") String lastName,
            @FormParam("telNumber") String telNumber
    ) {
        Participant participant = new Participant();
        participant.setParticipantUUID(UUID.randomUUID().toString());
        participant.setName(name);
        participant.setLastName(lastName);
        participant.setTelNumber(telNumber);

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
     * @param lastName
     * @param telNumber
     * @return Response
     */
    @POST
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateParticipant(
            @FormParam("uuid") String participantUUID,
            @FormParam("name") String name,
            @FormParam("lastName") String lastName,
            @FormParam("telNumber") String telNumber
    ) {
        int httpStatus = 200;
        Participant participant = DataHandler.readParticipantByUUID(participantUUID);
        if(participant != null) {
            participant.setName(name);
            participant.setLastName(lastName);
            participant.setTelNumber(telNumber);

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
     * @return Response
     */
    @GET
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
