package ch.bzz.veranstaltungverwaltung.model;

/**
 * Participant participating in a discipline
 * @author  : Obin Rokibul Hoque
 * @date    : 2022-05-17
 * @version : 1.0
 */
public class Participant {
    private String participantUUID;
    private String name;
    private String lastName;
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
