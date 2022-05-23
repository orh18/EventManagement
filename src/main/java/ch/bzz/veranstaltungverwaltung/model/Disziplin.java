package ch.bzz.veranstaltungverwaltung.model;


import ch.bzz.veranstaltungverwaltung.data.DataHandler;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Disziplin einer Veranstaltung
 * @author  : Obin Rokibul Hoque
 * @date    : 2022-05-17
 * @version : 1.0
 */
public class Disziplin {

    private String disziplinUUID;
    private String disziplin;
    private String beschreibung;
    private Veranstaltung veranstaltung;
    private ArrayList<Teilnehmer> teilnehmer;

    /**
     * setzt veranstaltung mit seiner Id
     *
     * @param veranstaltungUUID der Wert zu setzen
     */
    @JsonProperty("veranstaltung")
    public void setVeranstaltungByUUID(String veranstaltungUUID) {
        setVeranstaltung(DataHandler.getInstance().readVeranstaltungByUUID(veranstaltungUUID));
    }

    /**
     * setzt teilnehmerListe
     *
     * @param teilnehmer der Wert zu setzen
     */
    @JsonProperty("teilnehmer")
    public void setTeilnehmerByUUID(List<String> teilnehmer) {
        setTeilnehmer(new ArrayList<>());
        for (String s : teilnehmer) {
            this.teilnehmer.add(DataHandler.getInstance().readTeilnehmerByUUID(s));
        }
    }

    /**
     * zurückgibt disziplinUUID
     *
     * @return Wert von disziplinUUID
     */
    public String getDisziplinUUID() {
        return disziplinUUID;
    }

    /**
     * setzt disziplinUUID
     *
     * @param disziplinUUID der Wert zu setzen
     */
    public void setDisziplinUUID(String disziplinUUID) {
        this.disziplinUUID = disziplinUUID;
    }

    /**
     * zurückgibt disziplin
     *
     * @return Wert von disziplin
     */
    public String getDisziplin() {
        return disziplin;
    }

    /**
     * setzt disziplin
     *
     * @param disziplin der Wert zu setzen
     */
    public void setDisziplin(String disziplin) {
        this.disziplin = disziplin;
    }

    /**
     * zurückgibt beschreibung
     *
     * @return Wert von beschreibung
     */
    public String getBeschreibung() {
        return beschreibung;
    }

    /**
     * setzt beschreibung
     *
     * @param beschreibung der Wert zu setzen
     */
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    /**
     * zurückgibt veranstaltung
     *
     * @return Wert von veranstaltung
     */
    public Veranstaltung getVeranstaltung() {
        return veranstaltung;
    }

    /**
     * setzt veranstaltung
     *
     * @param veranstaltung der Wert zu setzen
     */
    public void setVeranstaltung(Veranstaltung veranstaltung) {
        this.veranstaltung = veranstaltung;
    }

    /**
     * zurückgibt teilnehmer
     *
     * @return Wert von teilnehmer
     */
    public ArrayList<Teilnehmer> getTeilnehmer() {
        return teilnehmer;
    }

    /**
     * setzt teilnehmer
     *
     * @param teilnehmer der Wert zu setzen
     */
    public void setTeilnehmer(ArrayList<Teilnehmer> teilnehmer) {
        this.teilnehmer = teilnehmer;
    }
}
