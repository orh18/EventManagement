package ch.bzz.veranstaltungverwaltung.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Veranstaltung, die verschiedene Disziplinen hostet
 * @author  : Obin Rokibul Hoque
 * @date    : 2022-05-17
 * @version : 1.0
 */
public class Veranstaltung {
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate datum;

    private String veranstaltungUUID;
    private String name;
    private String beschreibung;
    private String adresse;
    private BigDecimal preis;

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
