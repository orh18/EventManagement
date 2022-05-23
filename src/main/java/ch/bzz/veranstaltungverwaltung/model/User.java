package ch.bzz.veranstaltungverwaltung.model;

/**
 * User Klasse
 * @author  : Obin Rokibul Hoque
 * @date    : 2022-05-17
 * @version : 1.0
 */
public class User {
    private String username;
    private String role;
    private String password;

    /**
     * zurückgibt username
     *
     * @return Wert von username
     */
    public String getUsername() {
        return username;
    }

    /**
     * setzt username
     *
     * @param username der Wert zu setzen
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * zurückgibt role
     *
     * @return Wert von role
     */
    public String getRole() {
        return role;
    }

    /**
     * setzt role
     *
     * @param role der Wert zu setzen
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * zurückgibt password
     *
     * @return Wert von password
     */
    public String getPassword() {
        return password;
    }

    /**
     * setzt password
     *
     * @param password der Wert zu setzen
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public void logOn() {}

    public void logOff() {}
}
