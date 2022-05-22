package ch.bzz.veranstaltungverwaltung.data;

import ch.bzz.veranstaltungverwaltung.model.Disziplin;
import ch.bzz.veranstaltungverwaltung.model.Teilnehmer;
import ch.bzz.veranstaltungverwaltung.model.Veranstaltung;
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
    private List<Disziplin> disziplinList;
    private List<Veranstaltung> veranstaltungList;

    /**
     * private constructor defeats instantiation
     */
    private DataHandler() {
        setTeilnehmerList(new ArrayList<>());
        readTeilnehmerJSON();
        setDisziplinList(new ArrayList<>());
        readDisziplinJSON();
        setVeranstaltungList(new ArrayList<>());
        readVeranstaltungJSON();
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
     * liest alle Teilnehmer
     * @return Liste von Teilnehmer
     */
    public List<Teilnehmer> readAllTeilnehmer() {
        return getTeilnehmerList();
    }

    /**
     * liest ein Buch mit seiner uuid
     * @param teilnehmerUUID
     * @return das Buch (null=not found)
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
     * liest alle Disziplinen
     * @return List von Disziplinen
     */
    public List<Disziplin> readAllDisziplinen() {
        return getDisziplinList();
    }

    /**
     * liest eine Disziplin mit seiner Id
     * @param disziplinUUID
     * @return die Disziplin (null=not found)
     */
    public Disziplin readDisziplinByUUID(String disziplinUUID) {
        Disziplin disziplin = null;
        for (Disziplin entry : getDisziplinList()) {
            if (entry.getDisziplinUUID().equals(disziplinUUID)) {
                disziplin = entry;
            }
        }
        return disziplin;
    }

    /**
     * liest alle Veranstaltungen
     * @return List von Veranstaltungen
     */
    public List<Veranstaltung> readAllVeranstaltungen() {
        return getVeranstaltungList();
    }

    /**
     * liest eine Disziplin mit seiner Id
     * @param veranstaltungUUID
     * @return die Veranstaltung (null=not found)
     */
    public Veranstaltung readVeranstaltungByUUID(String veranstaltungUUID) {
        Veranstaltung veranstaltung = null;
        for (Veranstaltung entry : getVeranstaltungList()) {
            if (entry.getVeranstaltungUUID().equals(veranstaltungUUID)) {
                veranstaltung = entry;
            }
        }
        return veranstaltung;
    }

    /**
     * liest die Teilnehmer vom JSON-File
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
     * liest die Disziplinen vom JSON-File
     */
    private void readDisziplinJSON() {
        try {
            String path = Config.getProperty("disziplinJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Disziplin[] disziplinen = objectMapper.readValue(jsonData, Disziplin[].class);
            for (Disziplin d : disziplinen) {
                getDisziplinList().add(d);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * liest die Veranstaltungen vom JSON-File
     */
    private void readVeranstaltungJSON() {
        try {
            String path = Config.getProperty("veranstaltungJSON");
            byte[] jsonData = Files.readAllBytes(
                    Paths.get(path)
            );
            ObjectMapper objectMapper = new ObjectMapper();
            Veranstaltung[] veranstaltungen = objectMapper.readValue(jsonData, Veranstaltung[].class);
            for (Veranstaltung v : veranstaltungen) {
                getVeranstaltungList().add(v);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * zurückgibt teilnehmerList
     *
     * @return Wert von teilnehmerList
     */
    private List<Teilnehmer> getTeilnehmerList() {
        return teilnehmerList;
    }

    /**
     * setzt disziplinList
     *
     * @param teilnehmerList der Wert zu setzen
     */
    private void setTeilnehmerList(List<Teilnehmer> teilnehmerList) {
        this.teilnehmerList = teilnehmerList;
    }

    /**
     * zurückgibt disziplinList
     *
     * @return Wert von disziplinList
     */
    public List<Disziplin> getDisziplinList() {
        return disziplinList;
    }

    /**
     * setzt disziplinList
     *
     * @param disziplinList der Wert zu setzen
     */
    public void setDisziplinList(List<Disziplin> disziplinList) {
        this.disziplinList = disziplinList;
    }

    /**
     * zurückgibt veranstaltungList
     *
     * @return Wert von veranstaltungList
     */
    public List<Veranstaltung> getVeranstaltungList() {
        return veranstaltungList;
    }

    /**
     * setzt veranstaltungList
     *
     * @param veranstaltungList der Wert zu setzen
     */
    public void setVeranstaltungList(List<Veranstaltung> veranstaltungList) {
        this.veranstaltungList = veranstaltungList;
    }
}