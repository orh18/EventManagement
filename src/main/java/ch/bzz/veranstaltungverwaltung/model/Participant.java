package ch.bzz.veranstaltungverwaltung.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;

/**
 * Participant participating in a discipline
 * @author  : Obin Rokibul Hoque
 * @date    : 2022-05-17
 * @version : 1.0
 */
public class Participant {
    @FormParam("participantUUID")
    @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
    private String participantUUID;

    @FormParam("name")
    @NotEmpty
    @Size(min = 3, max = 20)
    private String name;

    @FormParam("lastName")
    @NotEmpty
    @Size(min = 3, max = 20)
    private String lastName;

    @FormParam("telNumber")
    @NotEmpty
    @Pattern(regexp = "0(2[1-246-7]|3[1-4]|4[13-4]|5[25-6]|6[1-2]|7[15-68-9]|8[17]|91)[0-9]{7}")
    private String telNumber;

    /**
     * gets participantUUID
     *
     * @return value of participantUUID
     */
    public String getParticipantUUID() {
        return participantUUID;
    }

    /**
     * sets participantUUID
     *
     * @param participantUUID the value to set
     */
    public void setParticipantUUID(String participantUUID) {
        this.participantUUID = participantUUID;
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
     * gets lastName
     *
     * @return value of lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * sets lastName
     *
     * @param lastName the value to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * gets telNumber
     *
     * @return value of telNumber
     */
    public String getTelNumber() {
        return telNumber;
    }

    /**
     * sets telNumber
     *
     * @param telNumber the value to set
     */
    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }
}
