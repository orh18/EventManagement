package ch.bzz.veranstaltungverwaltung.model;


import ch.bzz.veranstaltungverwaltung.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Discipline of an event
 * @author  : Obin Rokibul Hoque
 * @date    : 2022-05-17
 * @version : 1.0
 */
public class Discipline {

    private String disciplineUUID;
    private String discipline;
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
        setEvent(DataHandler.readEventByUUID(eventUUID));
    }

    /**
     * sets participantList
     *
     * @param participant the value to set
     */
    @JsonProperty("participant")
    public void setParticipantByUUID(List<String> participant) {
        setParticipants(new ArrayList<>());
        for (String s : participant) {
            this.participants.add(DataHandler.readParticipantByUUID(s));
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
     * gets discipline
     *
     * @return value of discipline
     */
    public String getDiscipline() {
        return discipline;
    }

    /**
     * sets discipline
     *
     * @param discipline the value to set
     */
    public void setDiscipline(String discipline) {
        this.discipline = discipline;
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
