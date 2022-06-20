package ch.bzz.veranstaltungverwaltung.model;


import ch.bzz.veranstaltungverwaltung.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import java.util.ArrayList;
import java.util.List;

/**
 * Discipline of an event
 * @author  : Obin Rokibul Hoque
 * @date    : 2022-05-17
 * @version : 1.0
 */
public class Discipline {
    @FormParam("disciplineUUID")
    @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    private String disciplineUUID;

    @FormParam("disciplineName")
    @NotEmpty
    @Size(min = 3, max = 30)
    private String disciplineName;

    @FormParam("description")
    @NotEmpty
    @Size(min = 10, max = 100)
    private String description;

    private Event event;
    private ArrayList<Participant> participants;

    /**
     * sets event by eventUUID
     *
     * @param eventUUID the value to set
     */
    @JsonProperty("event")
    public void setEventByUUID(String eventUUID) {
        Event e = DataHandler.readEventByUUID(eventUUID);
        setEvent(e);
    }

    /**
     * sets participantList
     *
     * @param participantsUUIDs the value to set
     */
    @JsonProperty("participants")
    public void setParticipantsByUUID(List<String> participantsUUIDs) {
        setParticipants(new ArrayList<>());
        for (String s : participantsUUIDs) {
            Participant p = DataHandler.readParticipantByUUID(s);
            this.participants.add(p);
        }
    }

    /**
     * gets disciplineUUID
     *
     * @return value of disciplineUUID
     */
    public String getDisciplineUUID() {
        return disciplineUUID;
    }

    /**
     * sets disciplineUUID
     *
     * @param disciplineUUID the value to set
     */
    public void setDisciplineUUID(String disciplineUUID) {
        this.disciplineUUID = disciplineUUID;
    }

    /**
     * gets disciplineName
     *
     * @return value of disciplineName
     */
    public String getDisciplineName() {
        return disciplineName;
    }

    /**
     * sets disciplineName
     *
     * @param disciplineName the value to set
     */
    public void setDisciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
    }

    /**
     * gets description
     *
     * @return value of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * sets description
     *
     * @param description the value to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * gets event
     *
     * @return value of event
     */
    public Event getEvent() {
        return event;
    }

    /**
     * sets event
     *
     * @param event the value to set
     */
    public void setEvent(Event event) {
        this.event = event;
    }

    /**
     * gets participant
     *
     * @return value of participant
     */
    public ArrayList<Participant> getParticipants() {
        return participants;
    }

    /**
     * sets participant
     *
     * @param participants the value to set
     */
    public void setParticipants(ArrayList<Participant> participants) {
        this.participants = participants;
    }
}
