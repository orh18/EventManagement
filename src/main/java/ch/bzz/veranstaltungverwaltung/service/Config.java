package ch.bzz.veranstaltungverwaltung.service;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * konfiguriert die Web-Services und Properties
 * @author  : Obin Rokibul Hoque
 * @date    : 2022-05-17
 * @version : 1.0
 */
@ApplicationPath("/resource")
public class Config extends Application {
    private static final String PROPERTIES_PATH = "/home/bzz/data/veranstaltungList.properties";
    private static Properties properties = null;

    /**
     * definiert alle Provider Klassen
     *
     * @return Set von Klassen
     */
    @Override
    public Set<Class<?>> getClasses() {
        HashSet providers = new HashSet<Class<?>>();
        providers.add(TestService.class);
        providers.add(TeilnehmerService.class);
        providers.add(DisziplinService.class);
        providers.add(VeranstaltungService.class);
        return providers;
    }

    /**
     * Zurückgibt der Wert von einer Property
     *
     * @param property der Schlüssel von der Property, die gelesen werden soll
     * @return der Wert von der Property
     */
    public static String getProperty(String property) {
        if (Config.properties == null) {
            setProperties(new Properties());
            readProperties();
        }
        String value = Config.properties.getProperty(property);
        if (value == null) return "";
        return value;
    }

    /**
     * liest den Property File
     */
    private static void readProperties() {

        InputStream inputStream;
        try {
            inputStream = new FileInputStream(PROPERTIES_PATH);
            properties.load(inputStream);
            if (inputStream != null) inputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * Setzt die Properties
     *
     * @param properties der Wert zu setzen
     */
    private static void setProperties(Properties properties) {
        Config.properties = properties;
    }
}