/*Este código fuente es Confidencial y también puede contener información privilegiada, es para uso exclusivo de E-Global. Tenga en cuenta que cualquier distribución, copia o uso de esta información sin autorización está estrictamente prohibida. Si ha identificado algún mal uso de este código fuente por favor notifiquelo a la dirección de correo seginfo@eglobal.com.mx*/
package com.eglobal.reporte.b.commons.providers.ciberark;

import java.util.Arrays;
import javapasswordsdk.PSDKPassword;
import javapasswordsdk.PSDKPasswordRequest;
import javapasswordsdk.exceptions.PSDKException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DataBaseCiberArk {

    private static final Logger log = LogManager.getLogger(DataBaseCiberArk.class);

    public Credentials getCredentials(CredentialsCiberArk credential) {
        log.info("Obteniendo credenciales de Ciber-Ark: " + credential);
        Credentials credentials = new Credentials();

        PSDKPassword password = null;
        String aux = "";
        char[] content = null;

        if (!credential.getAppID().isEmpty() && !credential.getFolder().isEmpty() && !credential.getObject().isEmpty() && !credential.getReason().isEmpty()) {

            try {
                PSDKPasswordRequest passRequest = new PSDKPasswordRequest();
                log.info("Obteniendo credenciales de Ciber-Ark");

                // Set request properties
                passRequest.setAppID(credential.getAppID());
                passRequest.setSafe(credential.getSafe());
                passRequest.setFolder(credential.getFolder());
                passRequest.setObject(credential.getObject());
                passRequest.setReason(credential.getReason());

                // Get password object
                password = javapasswordsdk.PasswordSDK.getPassword(passRequest);

                // Get password content
                content = password.getSecureContent();
                for (int i = 0; i < content.length; i++) {
                    aux += content[i];
                }
                credentials.setUser(password.getUserName());
                credentials.setPass(aux);
//				credentials.setUrl(password.getAddress());
            } catch (PSDKException e) {
                log.error("Existe un error al obtener credenciales de Ciber-Ark", e);
                credentials = null;
            } finally {
                if (content != null) {
                    // Clean the returned object
                    Arrays.fill(content, (char) 0);
                }

                if (password != null) {
                    try {
                        // Dispose of resources used by this PSDKPassword object
                        password.dispose();
                    } catch (PSDKException ex) {
                        log.error(DataBaseCiberArk.class.getName() + " ", ex);
                    }
                }
            }
        } else {
            credentials = null;
        }

        return credentials;
    }
}
