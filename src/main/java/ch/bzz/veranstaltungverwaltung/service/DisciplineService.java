package ch.bzz.veranstaltungverwaltung.service;

import ch.bzz.veranstaltungverwaltung.data.DataHandler;
import ch.bzz.veranstaltungverwaltung.model.Discipline;


import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

/**
 * services for reading, adding, changing, and deleting discipline
 * @author  : Obin Rokibul Hoque
 * @date    : 2022-05-22
 * @version : 1.0
 */
@Path("discipline")
public class DisciplineService {
    /**
     * reads a list of all disciplines
     * @return disciplines as JSON
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listDisciplines(
            @CookieParam("role") String role
    ) {
        List<Discipline> disciplineList = DataHandler.readAllDisciplines();
        int httpStatus = 200;
        if(role == null || role.equals("guest")) {
            httpStatus = 403;
            disciplineList = null;
        }
        return Response
                .status(httpStatus)
                .entity(disciplineList)
                .build();
    }

    /**
     * reads a discipline identified by the uuid
     * @param disciplineUUID the uuid of the discipline
     * @return  disciplines as JSON
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readDiscipline(
            @QueryParam("uuid") String disciplineUUID,
            @CookieParam("role") String role
    ) {
        int httpStatus = 200;
        Discipline discipline = DataHandler.readDisciplineByUUID(disciplineUUID);
        if (role == null || role.equals("guest")) {
            httpStatus = 403;
            discipline = null;
        } else if(discipline == null) {
            httpStatus = 410;
        }

            return Response
                    .status(httpStatus)
                    .entity(discipline)
                    .build();
    }

    /**
     * inserts a new discipline
     * @param discipline the discipline
     * @param participants the uuids of the participants
     * @param eventUUID the uuid of the event
     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertDiscipline(
            @Valid @BeanParam Discipline discipline,

            @FormParam("participants")
            List<@NotEmpty @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}") String>
            participants,

            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @FormParam("event") String eventUUID,

            @CookieParam("role") String role
    ) {
        int httpStatus = 200;
        if(role == null || !role.equals("admin")) {
            httpStatus = 403;
        } else {
            discipline.setDisciplineUUID(UUID.randomUUID().toString());
            discipline.setEventByUUID(eventUUID);
            discipline.setParticipantsByUUID(participants);
            DataHandler.insertDiscipline(discipline);
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     *
     * updates a discipline
     * @param discipline the discipline
     * @param participants the uuids of the participants
     * @param eventUUID the uuid of the event
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateDiscipline(
            @Valid @BeanParam Discipline discipline,
            @NotNull @FormParam("participants") List<@NotEmpty String> participants,
            @NotNull @FormParam("event") String eventUUID,
            @CookieParam("role") String role
    ) {
        int httpStatus = 200;
        Discipline oldDiscipline = DataHandler.readDisciplineByUUID(discipline.getDisciplineUUID());
        if(role == null || !role.equals("admin")) {
            httpStatus = 403;
        } else if(oldDiscipline != null) {
            oldDiscipline.setDisciplineName(discipline.getDisciplineName());
            oldDiscipline.setDescription(discipline.getDescription());
            oldDiscipline.setEventByUUID(eventUUID);
            oldDiscipline.setParticipantsByUUID(participants);

            DataHandler.updateDiscipline();
        } else {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * deletes a discipline identified by the uuid
     * @param disciplineUUID the uuid of the discipline
     * @return Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteDiscipline(
            @QueryParam("uuid") String disciplineUUID,
            @CookieParam("role") String role
    ) {
        int httpStatus = 200;
        if(role == null || !role.equals("admin")) {
            httpStatus = 403;
        } else {
            if (!DataHandler.deleteDiscipline(disciplineUUID)) {
                httpStatus = 410;
            }
        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
}
