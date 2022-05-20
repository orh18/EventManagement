package ch.bzz.veranstaltungverwaltung.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Teilnehmer die an einer Disziplin teilnimmt
 */

public class Teilnehmer {
    private String teilnehmerUUID;
    private String name;
    private String vorname;
    private String handynummer;

    /**
     * erzeugt eine Instanz von Teilnehmer
     *
     * @param teilnehmerUUID
     * @param name
     * @param vorname
     * @param handynummer
     */
    public Teilnehmer(String teilnehmerUUID, String name, String vorname, String handynummer) {
        this.teilnehmerUUID = teilnehmerUUID;
        this.name = name;
        this.vorname = vorname;
        this.handynummer = handynummer;
    }

    /**
     * zurückgibt teilnehmerUUID
     *
     * @return Wert von teilnehmerUUID
     */
    public String getTeilnehmerUUID() {
        return teilnehmerUUID;
    }

    /**
     * setzt teilnehmerUUID
     *
     * @param teilnehmerUUID der Wert zu setzen
     */
    public void setTeilnehmerUUID(String teilnehmerUUID) {
        this.teilnehmerUUID = teilnehmerUUID;
    }

    /**
     * zurückgibt name
     *
     * @return Wert von name
     */
    public String getName() {
        return name;
    }

    /**
     * setzt name
     *
     * @param name der Wert zu setzen
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * zurückgibt vorname
     *
     * @return Wert von vorname
     */
    public String getVorname() {
        return vorname;
    }

    /**
     * setzt vorname
     *
     * @param vorname der Wert zu setzen
     */
    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    /**
     * zurückgibt handynummer
     *
     * @return Wert von handynummer
     */
    public String getHandynummer() {
        return handynummer;
    }

    /**
     * setzt handynummer
     *
     * @param handynummer der Wert zu setzen
     */
    public void setHandynummer(String handynummer) {
        this.handynummer = handynummer;
    }
}
