package ch.bzz.veranstaltungverwaltung.data;

import ch.bzz.veranstaltungverwaltung.model.Teilnehmer;
import ch.bzz.veranstaltungverwaltung.service.Config;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * reads and writes the data in the JSON-files
 */
public class DataHandler {
    private static DataHandler instance = null;
    private List<Teilnehmer> teilnehmerList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
        setTeilnehmerList(new ArrayList<>());
        readTeilnehmerJSON();
    }

    /**
     * gets the only instance of this class
     * @return
     */
    public static DataHandler getInstance() {
        if (instance == null)
            instance = new DataHandler();
        return instance;
    }


    /**
     * reads all books
     * @return list of books
     */
    public List<Teilnehmer> readAllTeilnehmer() {
        return getTeilnehmerList();
    }

    /**
     * reads a book by its uuid
     * @param teilnehmerUUID
     * @return the Book (null=not found)
     */
    public Teilnehmer readTeilnehmerByUUID(String teilnehmerUUID) {
        Teilnehmer teilnehmer = null;
        for (Teilnehmer entry : getTeilnehmerList()) {
            if (entry.getTeilnehmerUUID().equals(teilnehmerUUID)) {
                teilnehmer = entry;
            }
        }
        return teilnehmer;
    }



    /**
     * reads the books from the JSON-file
     */
    private void readTeilnehmerJSON() {
        try {
            String path = Config.getProperty("teilnehmerJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Teilnehmer[] teilnehmer = objectMapper.readValue(jsonData, Teilnehmer[].class);
            for (Teilnehmer t : teilnehmer) {
                getTeilnehmerList().add(t);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * gets teilnehmerList
     *
     * @return value of teilnehmerList
     */
    private List<Teilnehmer> getTeilnehmerList() {
        return teilnehmerList;
    }

    /**
     * sets teilnehmerList
     *
     * @param teilnehmerList the value to set
     */
    private void setTeilnehmerList(List<Teilnehmer> teilnehmerList) {
        this.teilnehmerList = teilnehmerList;
    }


}