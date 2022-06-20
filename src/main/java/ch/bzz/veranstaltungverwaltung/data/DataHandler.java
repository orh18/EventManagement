package ch.bzz.veranstaltungverwaltung.data;

import ch.bzz.veranstaltungverwaltung.model.Discipline;
import ch.bzz.veranstaltungverwaltung.model.Event;
import ch.bzz.veranstaltungverwaltung.model.Participant;
import ch.bzz.veranstaltungverwaltung.service.Config;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * reads and writes the data in the JSON-files
 * @author  : Obin Rokibul Hoque
 * @date    : 2022-05-17
 * @version : 1.0
 */
public class DataHandler {
    private static List<Participant> participantList;
    private static List<Discipline> disciplineList;
    private static List<Event> eventList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
    }

    /**
     * reads all participant
     * @return list of participant
     */
    public static List<Participant> readAllParticipants() {
        return getParticipantList();
    }

    /**
     * reads a participant by its uuid
     * @param participantUUID the uuid of the participant
     * @return the Participant (null=not found)
     */
    public static Participant readParticipantByUUID(String participantUUID) {
        Participant participant = null;
        for (Participant entry : getParticipantList()) {
            if (entry.getParticipantUUID().equals(participantUUID)) {
                participant = entry;
            }
        }
        return participant;
    }

    /**
     * inserts a new participant into the participantList
     *
     * @param participant the participant to be saved
     */
    public static void insertParticipant(Participant participant) {
        getParticipantList().add(participant);
        writeParticipantJSON();
    }

    /**
     * updates the participantList
     */
    public static void updateParticipant() {
        writeParticipantJSON();
    }

    /**
     * deletes a participant identified by the participantUUID
     * @param participantUUID  the key
     * @return  success=true/false
     */
    public static boolean deleteParticipant(String participantUUID) {
        Participant participant = readParticipantByUUID(participantUUID);
        if (participant != null) {
            getParticipantList().remove(participant);
            writeParticipantJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * reads all discipline
     * @return list of discipline
     */
    public static List<Discipline> readAllDisciplines() {
        return getDisciplineList();
    }

    /**
     * reads a discipline by its uuid
     * @param disciplineUUID the uuid of the discipline
     * @return the Discipline (null=not found)
     */
    public static Discipline readDisciplineByUUID(String disciplineUUID) {
        Discipline discipline = null;
        for (Discipline entry : getDisciplineList()) {
            if (entry.getDisciplineUUID().equals(disciplineUUID)) {
                discipline = entry;
            }
        }
        return discipline;
    }

    /**
     * inserts a new discipline into the disciplineList
     *
     * @param discipline the publisher to be saved
     */
    public static void insertDiscipline(Discipline discipline) {
        getDisciplineList().add(discipline);
        writeDisciplineJSON();
    }

    /**
     * updates the disciplineList
     */
    public static void updateDiscipline() {
        writeDisciplineJSON();
    }

    /**
     * deletes a discipline identified by the disciplineUUID
     * @param disciplineUUID  the key
     * @return  success=true/false
     */
    public static boolean deleteDiscipline(String disciplineUUID) {
        Discipline discipline = readDisciplineByUUID(disciplineUUID);
        if (discipline != null) {
            getDisciplineList().remove(discipline);
            writeDisciplineJSON();
            return true;
        } else {
            return false;
        }
    }


    /**
     * reads all events
     * @return list of events
     */
    public static List<Event> readAllEvents() {
        return getEventList();
    }

    /**
     * reads an event by its uuid
     * @param eventUUID the uuid of the event
     * @return the Event (null=not found)
     */
    public static Event readEventByUUID(String eventUUID) {
        Event event = null;
        for (Event entry : getEventList()) {
            if (entry.getEventUUID().equals(eventUUID)) {
                event = entry;
            }
        }
        return event;
    }

    /**
     * inserts a new event into the eventList
     *
     * @param event the event to be saved
     */
    public static void insertEvent(Event event) {
        getEventList().add(event);
        writeEventJSON();
    }

    /**
     * updates the eventList
     */
    public static void updateEvent() {
        writeEventJSON();
    }

    /**
     * deletes an event identified by the eventUUID
     * @param eventUUID  the key
     * @return  success=true/false
     */
    public static boolean deleteEvent(String eventUUID) {
        Event event = readEventByUUID(eventUUID);
        if (event != null) {
            getEventList().remove(event);
            writeEventJSON();
            return true;
        } else {
            return false;
        }
    }

    /**
     * reads the participants from the JSON-file
     */
    private static void readParticipantJSON() {
        try {
            String path = Config.getProperty("participantJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Participant[] participant = objectMapper.readValue(jsonData, Participant[].class);
            for (Participant t : participant) {
                getParticipantList().add(t);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * writes the participantList to the JSON-file
     */
    private static void writeParticipantJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream;
        Writer fileWriter;

        String bookPath = Config.getProperty("participantJSON");
        try {
            fileOutputStream = new FileOutputStream(bookPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getParticipantList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the disciplines from the JSON-File
     */
    private static void readDisciplineJSON() {
        try {
            String path = Config.getProperty("disciplineJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Discipline[] disciplines = objectMapper.readValue(jsonData, Discipline[].class);
            for (Discipline d : disciplines) {
                getDisciplineList().add(d);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * writes the disciplineList to the JSON-file
     */
    private static void writeDisciplineJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream;
        Writer fileWriter;

        String bookPath = Config.getProperty("disciplineJSON");
        try {
            fileOutputStream = new FileOutputStream(bookPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getDisciplineList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * reads the events from the JSON-File
     */
    private static void readEventJSON() {
        try {
            String path = Config.getProperty("eventJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Event[] events = objectMapper.readValue(jsonData, Event[].class);
            for (Event v : events) {
                getEventList().add(v);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * writes the eventList to the JSON-file
     */
    private static void writeEventJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
        FileOutputStream fileOutputStream;
        Writer fileWriter;

        String eventPath = Config.getProperty("eventJSON");
        try {
            fileOutputStream = new FileOutputStream(eventPath);
            fileWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8));
            objectWriter.writeValue(fileWriter, getEventList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * gets participantList
     *
     * @return value of participantList
     */
    private static List<Participant> getParticipantList() {
        if(participantList == null) {
            setParticipantList(new ArrayList<>());
            readParticipantJSON();
        }
        return participantList;
    }

    /**
     * sets participantList
     *
     * @param participantList the value to set
     */
    private static void setParticipantList(List<Participant> participantList) {
        DataHandler.participantList = participantList;
    }

    /**
     * gets disciplineList
     *
     * @return value of the disciplineList
     */
    public static List<Discipline> getDisciplineList() {
        if(disciplineList == null) {
            setDisciplineList(new ArrayList<>());
            readDisciplineJSON();
        }
        return disciplineList;
    }

    /**
     * sets disciplineList
     *
     * @param disciplineList the value to set
     */
    public static void setDisciplineList(List<Discipline> disciplineList) {
        DataHandler.disciplineList = disciplineList;
    }

    /**
     * gets eventList
     *
     * @return value of the eventList
     */
    public static List<Event> getEventList() {
        if(eventList == null) {
            setEventList(new ArrayList<>());
            readEventJSON();
        }
        return eventList;
    }

    /**
     * sets eventList
     *
     * @param eventList the value to set
     */
    public static void setEventList(List<Event> eventList) {
        DataHandler.eventList = eventList;
    }
}