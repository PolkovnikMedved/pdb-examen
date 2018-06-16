package be.solodoukhin.service.url;

/**
 * @author SOLODOUKHIN VIKTOR
 * <p>
 * date 16/06/18
 */
public enum Databases implements IDatabaseURL {

    H2(new H2URL()),

    FIREBIRD(new FirebirdURL()),

    MYSQL(new MySqlURL());

    private IDatabaseURL databaseURL;

    Databases(IDatabaseURL databaseURL) {
        this.databaseURL = databaseURL;
    }

    @Override
    public String getURL() {
        return databaseURL.getURL();
    }

    @Override
    public String buildMemoryURL(String file) {
        return databaseURL.buildMemoryURL(file);
    }

    @Override
    public String buildEmbeddedURL(String file) {
        return databaseURL.buildEmbeddedURL(file);
    }

    @Override
    public String buildServerURL(String file, String ip) {
        return databaseURL.buildServerURL(file, ip);
    }

    @Override
    public String buildServerURL(String file, String ip, int port) {
        return databaseURL.buildServerURL(file, ip, port);
    }

    @Override
    public int getDefaultPort() {
        return databaseURL.getDefaultPort();
    }

    @Override
    public boolean hasMemoryMode() {
        return databaseURL.hasMemoryMode();
    }

    @Override
    public boolean hasEmbeddedMode() {
        return databaseURL.hasEmbeddedMode();
    }

    @Override
    public boolean hasServerMode() {
        return databaseURL.hasServerMode();
    }
}
