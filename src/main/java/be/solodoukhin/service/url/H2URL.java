package be.solodoukhin.service.url;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author SOLODOUKHIN VIKTOR
 * <p>
 * date 16/06/18
 */
public class H2URL implements IDatabaseURL {

    private static final Logger logger = LoggerFactory.getLogger(H2URL.class);

    @Override
    public String getURL() {
        return "jdbc:h2:";
    }

    @Override
    public String buildMemoryURL(String file) {
        return this.getURL() + "mem:" + (file == null ? "" : file);
    }

    @Override
    public String buildEmbeddedURL(String file) {
        return this.getURL() + file;
    }

    @Override
    public String buildServerURL(String file, String ip) {
        return this.buildServerURL(file, ip, this.getDefaultPort());
    }

    @Override
    public String buildServerURL(String file, String ip, int port) {
        logger.info("Build URL = " + this.getURL() + "tcp://" + ip + ":" + port + "/" + file);
        return this.getURL() + "tcp://" + ip + ":" + port + "/" + file;
    }

    @Override
    public int getDefaultPort() {
        return 8082;
    }

    @Override
    public boolean hasMemoryMode() {
        return true;
    }

    @Override
    public boolean hasEmbeddedMode() {
        return true;
    }

    @Override
    public boolean hasServerMode() {
        return true;
    }
}
