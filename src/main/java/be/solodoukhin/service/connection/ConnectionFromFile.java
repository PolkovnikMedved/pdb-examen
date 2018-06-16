package be.solodoukhin.service.connection;

import be.solodoukhin.service.url.Databases;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * @author SOLODOUKHIN VIKTOR
 * <p>
 * date 16/06/18
 */
public class ConnectionFromFile implements IConnectionInformation {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionFromFile.class);
    private Properties properties = new Properties();

    public ConnectionFromFile(String filename, Databases sgbd) {

        try (BufferedReader br = new BufferedReader(new FileReader(filename)))
        {
            this.properties.load(br);
            if (!this.properties.containsKey("url"))
                this.properties.setProperty("url",
                        sgbd.buildServerURL(
                                this.properties.getProperty("file", "parking"),
                                this.properties.getProperty("ip", "localhost"),
                                this.properties.containsKey("port")?
                                        Integer.parseInt(this.properties.getProperty("port")): sgbd.getDefaultPort()
                        )
                );
            logger.info("url: "+this.properties.getProperty("url"));
        } catch (IOException e) {
            logger.info("Could not load properties file.", e);
        }
    }

    @Override
    public Properties getProperties() {
        return this.properties;
    }
}
