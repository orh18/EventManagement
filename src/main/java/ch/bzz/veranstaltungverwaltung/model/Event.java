package ch.bzz.veranstaltungverwaltung.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Event that hosts one or many disciplines
 * @author  : Obin Rokibul Hoque
 * @date    : 2022-05-17
 * @version : 1.0
 */
public class Event {
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate date;

    private String eventUUID;
    private String name;
    private String description;
    private String address;
    private BigDecimal price;

    /**
     * gets date
     *
     * @return value of date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * sets date
     *
     * @param date the value to set
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * gets eventUUID
     *
     * @return value of eventUUID
     */
    public String getEventUUID() {
        return eventUUID;
    }

    /**
     * sets eventUUID
     *
     * @param eventUUID the value to set
     */
    public void setEventUUID(String eventUUID) {
        this.eventUUID = eventUUID;
    }

    /**
     * gets name
     *
     * @return value of name
     */
    public String getName() {
        return name;
    }

    /**
     * sets name
     *
     * @param name the value to set
     */
    public void setName(String name) {
        this.name = name;
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
     * gets address
     *
     * @return value of address
     */
    public String getAddress() {
        return address;
    }

    /**
     * sets address
     *
     * @param address the value to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * gets price
     *
     * @return value of price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * sets price
     *
     * @param price the value to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
