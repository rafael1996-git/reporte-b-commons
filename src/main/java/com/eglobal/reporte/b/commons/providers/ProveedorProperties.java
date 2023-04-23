/*
 * Este código fuente es Confidencial y también puede contener información privilegiada, es para uso exclusivo de E-Global.
 * Tenga en cuenta que cualquier distribución, copia o uso de esta información sin autorización está estrictamente prohibida.
 * Si ha identificado algún mal uso de este código fuente por favor notifiquelo a la dirección de correo seginfo@eglobal.com.mx
 */
package com.eglobal.reporte.b.commons.providers;

import com.eglobal.reporte.b.commons.providers.ciberark.Credentials;
import com.eglobal.reporte.b.commons.providers.ciberark.CredentialsCiberArk;
import com.eglobal.reporte.b.commons.providers.ciberark.DataBaseCiberArk;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author _snak
 */
public class ProveedorProperties {

    private static final Logger LOG = LogManager.getLogger(ProveedorProperties.class);
    private static ProveedorProperties instance;
    private final DataBaseCiberArk arkCredentials = new DataBaseCiberArk();

    public static ProveedorProperties GETINSTANCE() {
        if (instance != null) {
            return instance;
        }
        instance = new ProveedorProperties();
        return instance;
    }

    public Properties getPropsI() throws IOException{
        FileInputStream conProp = null;
        Properties props = null;
        //EncriptadorUtil eu = new EncriptadorUtil();
        try {
            props = new Properties();
            String rutaProp = PathsProjectProvider.getConf().getHomeConfBatch() + "conexionReporteBInformix.properties";
            conProp = new FileInputStream(rutaProp);
            props.load(conProp);
            props.put("javax.persistence.jdbc.url", props.get("URLS").toString() + props.get("SERVERNAMES").toString() + ":" + props.get("PORTNUMBERS")
                    + "/" + props.get("DATABASENAME") + ":INFORMIXSERVER=" + props.get("PROPIETARIO"));
            props.put("javax.persistence.jdbc.user", props.get("USERS").toString());
            String pass = props.get("PASSWORDS").toString();
            props.put("javax.persistence.jdbc.password", pass);
        }catch(FileNotFoundException ex){
            LOG.info("No se encontró archivo de propiedades");
            System.exit(-1);
        }finally {
            if (conProp != null) {
                conProp.close();   
            }
        }
        return props;
    }

    public Properties getPropsP() throws IOException{
        FileInputStream conProp = null;
        Properties props = null;
        try {
            props = new Properties();
            String rutaProp = PathsProjectProvider.getConf().getHomeConfBatch() + "conexionReporteBPostgreSQL.properties";
            conProp = new FileInputStream(rutaProp);
            props.load(conProp);
            props.put("javax.persistence.jdbc.url", props.get("URLS").toString() + props.get("SERVERNAMES").toString() + ":" + props.get("PORTNUMBERS")
                    + "/" + props.get("DATABASENAME"));
            Credentials credentialsiberark = getCredentialCyberArk();
            if (credentialsiberark != null) {
                props.put("javax.persistence.jdbc.user", credentialsiberark.getUser());
                String pass = credentialsiberark.getPass();
                props.put("javax.persistence.jdbc.password", pass);
                System.out.println("credential SiberArk " + pass);
            } else {
                props.put("javax.persistence.jdbc.user", props.get("USERS").toString());
                String pass = props.get("PASSWORDS").toString();
                props.put("javax.persistence.jdbc.password", pass);
                System.out.println("credential LOCAL ");
            }

        } catch(FileNotFoundException ex){
            LOG.info("No se encontró archivo de propiedades");  
            System.exit(-1);
        }finally {
            if (conProp != null) {
                conProp.close();
            }
        }
        return props;
    }

    public Credentials getCredentialCyberArk() throws IOException{
        CredentialsCiberArk obj;
        Credentials credentials;
        /* En caso de que utilizar ciberark  */
        obj = new CredentialsCiberArk(ProveedorProperties.GETINSTANCE().getPropertiesCyberArk().getProperty("ciber.ark.appID"),
                ProveedorProperties.GETINSTANCE().getPropertiesCyberArk().getProperty("ciber.ark.safe"),
                ProveedorProperties.GETINSTANCE().getPropertiesCyberArk().getProperty("ciber.ark.folder"),
                ProveedorProperties.GETINSTANCE().getPropertiesCyberArk().getProperty("ciber.ark.object"),
                ProveedorProperties.GETINSTANCE().getPropertiesCyberArk().getProperty("ciber.ark.reason"));
        credentials = arkCredentials.getCredentials(obj);
        LOG.info("CREDENTIAL  CIBERARK: [" + credentials + "] ");
        return credentials;

    }

    public Properties getPropertiesCyberArk() throws FileNotFoundException, IOException {
        FileInputStream conProp = null;
        Properties props;
        try {
            props = new Properties();
            String rutaProp = PathsProjectProvider.getConf().getHomeConfBatch() + "ciberark.properties";
            conProp = new FileInputStream(rutaProp);
            props.load(conProp);
        } catch (IOException e) {
            LOG.error("ERROR: ", e);
            System.exit(-1);
            return null;
        } finally {
            if (conProp != null) {
                conProp.close();
            }

        }
        return props;
    }
}
