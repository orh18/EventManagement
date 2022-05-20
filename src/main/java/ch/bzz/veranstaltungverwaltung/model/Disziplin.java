package ch.bzz.veranstaltungverwaltung.model;

import java.util.ArrayList;

public class Disziplin {
    private String disziplinUUID;
    private String disziplin;
    private String beschreibung;
    private Veranstaltung veranstaltung;
    private ArrayList<Teilnehmer> teilnehmer;

    /**
     * erzeugt eine Instanz von Disziplin
     *
     * @param disziplinUUID
     * @param disziplin
     * @param beschreibung
     * @param veranstaltung
     * @param teilnehmer
     */
    public Disziplin(String disziplinUUID, String disziplin, String beschreibung, Veranstaltung veranstaltung, ArrayList<Teilnehmer> teilnehmer) {
        this.disziplinUUID = disziplinUUID;
        this.disziplin = disziplin;
        this.beschreibung = beschreibung;
        this.veranstaltung = veranstaltung;
        this.teilnehmer = teilnehmer;
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
