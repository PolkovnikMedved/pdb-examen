package be.solodoukhin.service.url;

/**
 * @author SOLODOUKHIN VIKTOR
 * <p>
 * date 16/06/18
 */
public interface IDatabaseURL {
    String getURL();

    String buildMemoryURL(String file);

    String buildEmbeddedURL(String file);

    /**
     *
     * @param file chemin et nom de fichier ou alias
     * @param ip l'adresse IP en utilisant le port par défaut
     * @return une URL serveur
     */
    String buildServerURL(String file, String ip);

    /**
     *
     * @param file chemin et nom de fichier ou alias
     * @param ip  l'adresse IP en utilisant le port par défaut
     * @param port le port à utiliser
     * @return une URL serveur
     */
    String buildServerURL(String file, String ip, int port);

    int getDefaultPort();

    boolean hasMemoryMode();

    boolean hasEmbeddedMode();

    boolean hasServerMode();

}
