package be.solodoukhin.service.url;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author SOLODOUKHIN VIKTOR
 * <p>
 * date 16/06/18
 */
public class MySqlURL implements IDatabaseURL {

    private final static Logger logger = LoggerFactory.getLogger(MySqlURL.class);

    @Override
    public String getURL() {
        return "jdbc:mysql://";
    }

    @Override
    public String buildMemoryURL(String file) {
        throw new UnsupportedOperationException("MySQL database doesn't support in-memory mode.");
    }

    @Override
    public String buildEmbeddedURL(String file) {
        throw new UnsupportedOperationException("MySQL database doesn't support embedded mode.");
    }

    @Override
    public String buildServerURL(String file, String ip) {
        return this.buildServerURL(file, ip, this.getDefaultPort());
    }

    @Override
    public String buildServerURL(String file, String ip, int port) {
        logger.info("Build URL = " + this.getURL() + ip + "/" + port + ":" + file);
        return this.getURL() + ip + "/" + port + ":" + file;
    }

    @Override
    public int getDefaultPort() {
        return 3306;
    }

    @Override
    public boolean hasMemoryMode() {
        return false;
    }

    @Override
    public boolean hasEmbeddedMode() {
        return false;
    }

    @Override
    public boolean hasServerMode() {
        return true;
    }
}
