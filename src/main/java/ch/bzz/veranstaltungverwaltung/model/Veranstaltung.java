package ch.bzz.veranstaltungverwaltung.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Veranstaltung {
    private String veranstaltungUUID;
    private String name;
    private String beschreibung;
    private String adresse;
    private LocalDate datum;
    private BigDecimal preis;

    /**
     * erzeugt eine Instanz von Veranstaltung
     *
     * @param veranstaltungUUID
     * @param name
     * @param beschreibung
     * @param adresse
     * @param datum
     * @param preis
     */
    public Veranstaltung(String veranstaltungUUID, String name, String beschreibung, String adresse, LocalDate datum, BigDecimal preis) {
        this.veranstaltungUUID = veranstaltungUUID;
        this.name = name;
        this.beschreibung = beschreibung;
        this.adresse = adresse;
        this.datum = datum;
        this.preis = preis;
    }

    /**
     * zurückgibt veranstaltungUUID
     *
     * @return Wert von veranstaltungUUID
     */
    public String getVeranstaltungUUID() {
        return veranstaltungUUID;
    }

    /**
     * setzt veranstaltungUUID
     *
     * @param veranstaltungUUID der Wert zu setzen
     */
    public void setVeranstaltungUUID(String veranstaltungUUID) {
        this.veranstaltungUUID = veranstaltungUUID;
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
     * zurückgibt adresse
     *
     * @return Wert von adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * setzt adresse
     *
     * @param adresse der Wert zu setzen
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * zurückgibt datum
     *
     * @return Wert von datum
     */
    public LocalDate getDatum() {
        return datum;
    }

    /**
     * setzt datum
     *
     * @param datum der Wert zu setzen
     */
    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    /**
     * zurückgibt preis
     *
     * @return Wert von preis
     */
    public BigDecimal getPreis() {
        return preis;
    }

    /**
     * setzt preis
     *
     * @param preis der Wert zu setzen
     */
    public void setPreis(BigDecimal preis) {
        this.preis = preis;
    }
}
