package ch.bzz.veranstaltungverwaltung.service;

import ch.bzz.veranstaltungverwaltung.data.DataHandler;
import ch.bzz.veranstaltungverwaltung.model.Discipline;
;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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
    public Response listDisciplines() {
        List<Discipline> disciplineList = DataHandler.readAllDisciplines();
        return Response
                .status(200)
                .entity(disciplineList)
                .build();
    }

    /**
     * reads a list of all disciplines
     * @return  disciplines as JSON
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readDiscipline(
            @QueryParam("uuid") String disciplineUUID
    ) {
        Discipline discipline = DataHandler.readDisciplineByUUID(disciplineUUID);
        if(discipline != null) {
            return Response
                    .status(200)
                    .entity(discipline)
                    .build();
        } else {
            return Response
                    .status(404)
                    .build();
        }
    }
}
