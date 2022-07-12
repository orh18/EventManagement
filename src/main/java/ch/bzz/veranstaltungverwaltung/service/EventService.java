package ch.bzz.veranstaltungverwaltung.service;

import ch.bzz.veranstaltungverwaltung.data.DataHandler;
import ch.bzz.veranstaltungverwaltung.model.Event;
import ch.bzz.veranstaltungverwaltung.util.AES256;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * services for reading, adding, changing and deleting events
 * @author  : Obin Rokibul Hoque
 * @date    : 2022-05-22
 * @version : 1.0
 */
@Path("event")
public class EventService {
    /**
     * reads a list of all events
     *
     * @return events as JSON
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listEvents(
            @CookieParam("role") String role
    ) {
        List<Event> eventList = DataHandler.readAllEvents();
        int httpStatus;
        if (role == null || AES256.decrypt(role).equals("guest")) {
            httpStatus = 403;
            eventList = null;
        } else {
            httpStatus = 200;
        }
        return Response
                .status(httpStatus)
                .entity(eventList)
                .build();
    }

    /**
     * reads an event identified by the uuid
     *
     * @param eventUUID the uuid of the event
     * @return event
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readEvent(
            @QueryParam("uuid") String eventUUID,
            @CookieParam("role") String role
    ) {
        int httpStatus = 200;
        Event event = DataHandler.readEventByUUID(eventUUID);
        if (role == null || AES256.decrypt(role).equals("guest")) {
            httpStatus = 403;
            event = null;
        } else if (event == null) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity(event)
                .build();
    }

    /**
     * inserts a new event
     * @param event the event
     * @param date the date of the event
     * @return Response
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response insertEvent(
            @Valid @BeanParam Event event,
            @FormParam("date") String date,
            @CookieParam("role") String role
    ) {
        int httpStatus = 200;
        if(role == null || !AES256.decrypt(role).equals("admin")) {
            httpStatus = 403;
        } else {
            event.setEventUUID(UUID.randomUUID().toString());
            DataHandler.insertEvent(event);
        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * updates an event
     * @param event the event
     * @param date the date of the event
     * @return Response
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateEvent(
            @Valid @BeanParam Event event,
            @FormParam("date") String date,
            @CookieParam("role") String role
    ) {
        int httpStatus = 200;
        Event oldEvent = DataHandler.readEventByUUID(event.getEventUUID());
        if(role == null || !AES256.decrypt(role).equals("admin")) {
            httpStatus = 403;
        } else if(oldEvent != null) {
            oldEvent.setName(event.getName());
            oldEvent.setDescription(event.getDescription());
            oldEvent.setAddress(event.getAddress());
            oldEvent.setPrice(event.getPrice());
            oldEvent.checkDate(LocalDate.parse(date));
            DataHandler.updateEvent();
        } else {
            httpStatus = 410;
        }

        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * deletes an event identified by the uuid
     * @param eventUUID the uuid of the event
     * @return Response
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteEvent(
            @QueryParam("uuid") String eventUUID,
            @CookieParam("role") String role
    ) {
        int httpStatus = 200;
        if(role == null || !AES256.decrypt(role).equals("admin")) {
            httpStatus = 403;
        } else {
            if (!DataHandler.deleteEvent(eventUUID)) {
                httpStatus = 410;
            }
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
}
