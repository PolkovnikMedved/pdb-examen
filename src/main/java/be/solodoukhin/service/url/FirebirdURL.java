package be.solodoukhin.service.url;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author SOLODOUKHIN VIKTOR
 * <p>
 * date 16/06/18
 */
public class FirebirdURL implements IDatabaseURL {

    private static final Logger logger = LoggerFactory.getLogger(FirebirdURL.class);

    @Override
    public String getURL() {
        return "jdbc:firebirdsql:";
    }

    @Override
    public String buildMemoryURL(String file) {
        throw new UnsupportedOperationException("Firebird doesn't support in-memory mode.");
    }

    @Override
    public String buildEmbeddedURL(String file) {
        return this.getURL() + "embedded:" + file;
    }

    @Override
    public String buildServerURL(String file, String ip) {
        return this.buildServerURL(file, ip, getDefaultPort());
    }

    @Override
    public String buildServerURL(String file, String ip, int port) {
        logger.info("Build URL = " + this.getURL() + ip + "/" + port + ":" + file);
        return this.getURL() + ip + "/" + port + ":" + file;
    }

    @Override
    public int getDefaultPort() {
        return 3050;
    }

    @Override
    public boolean hasMemoryMode() {
        return false;
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
