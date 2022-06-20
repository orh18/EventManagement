package ch.bzz.veranstaltungverwaltung.service;

import ch.bzz.veranstaltungverwaltung.data.DataHandler;
import ch.bzz.veranstaltungverwaltung.model.Event;

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
     * @return  events as JSON
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listEvents() {
        List<Event> eventList = DataHandler.readAllEvents();
        return Response
                .status(200)
                .entity(eventList)
                .build();
    }

    /**
     * reads an event identified by the uuid
     * @param eventUUID the uuid of the event
     * @return event
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readEvent(
            @QueryParam("uuid") String eventUUID
    ) {
        Event event = DataHandler.readEventByUUID(eventUUID);
        if(event != null) {
            return Response
                    .status(200)
                    .entity(event)
                    .build();
        } else {
            return Response
                    .status(410)
                    .build();
        }
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
            @FormParam("date") String date
    ) {
        int httpStatus = 200;
        event.setEventUUID(UUID.randomUUID().toString());
        event.checkDate(LocalDate.parse(date));

        if (event.getDate() != null) {
            DataHandler.insertEvent(event);
        } else {
            httpStatus = 400;
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
            @FormParam("date") String date
    ) {
        int httpStatus = 200;
        Event oldEvent = DataHandler.readEventByUUID(event.getEventUUID());
        if(oldEvent != null && event.getDate() != null) {
            oldEvent.setName(event.getName());
            oldEvent.setDescription(event.getDescription());
            oldEvent.setAddress(event.getAddress());
            oldEvent.setPrice(event.getPrice());
            oldEvent.checkDate(LocalDate.parse(date));

            DataHandler.updateEvent();
        } else {
            httpStatus = 400;
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
            @QueryParam("uuid") String eventUUID
    ) {
        int httpStatus = 200;
        if (!DataHandler.deleteEvent(eventUUID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }
}
