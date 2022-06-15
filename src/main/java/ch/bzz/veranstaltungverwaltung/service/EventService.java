package ch.bzz.veranstaltungverwaltung.service;

import ch.bzz.veranstaltungverwaltung.data.DataHandler;
import ch.bzz.veranstaltungverwaltung.model.Event;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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
     * @param eventUUID
     * @return event
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readVeranstaltung(
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
                    .status(404)
                    .build();
        }
    }
}
