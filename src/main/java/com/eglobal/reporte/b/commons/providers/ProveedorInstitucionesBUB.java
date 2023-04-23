/*
 * Este código fuente es Confidencial y también puede contener información privilegiada, es para uso exclusivo de E-Global.
 * Tenga en cuenta que cualquier distribución, copia o uso de esta información sin autorización está estrictamente prohibida.
 * Si ha identificado algún mal uso de este código fuente por favor notifiquelo a la dirección de correo seginfo@eglobal.com.mx
 */
package com.eglobal.reporte.b.commons.providers;

import com.eglobal.beans.AuthenticationData;
import com.eglobal.beans.AuthorizedData;
import com.eglobal.beans.InstitucionesEnterData;
import com.eglobal.beans.InstitucionesResponseData;
import com.eglobal.exceptions.BUBClientServicesException;
import com.eglobal.services.ClientService;
import com.eglobal.services.impl.InstitucionesClientService;
import com.eglobal.services.impl.OAuthClientService;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author egldt1134
 */
public class ProveedorInstitucionesBUB {
    private static final Logger LOG = LogManager.getLogger(ProveedorInstitucionesBUB.class);
    private static ProveedorInstitucionesBUB instance;
    private Map<Integer, InstitucionesResponseData> mapaInstitucionesEmi;
    private Map<Integer, InstitucionesResponseData> mapaInstitucionesAdq;
    
    public static ProveedorInstitucionesBUB GETINSTANCE() {
        if (instance != null) {
            return instance;
        }
        instance = new ProveedorInstitucionesBUB();
        return instance;
    }
    
    public Map<Integer, InstitucionesResponseData> getMapaInstitucionesEmi(){
        if(this.mapaInstitucionesEmi != null){
            return this.mapaInstitucionesEmi;
        }
        
        Map<Integer, InstitucionesResponseData> mapaInstitucionesEmiBUB = null;
        FileInputStream conProp = null;
        Properties props;
        
        try {
            props = new Properties();
            String rutaProp = PathsProjectProvider.getConf().getHomeConfBatch() + "propEndPointsReporteB.properties";
            conProp = new FileInputStream(rutaProp);
            props.load(conProp);
            
            ClientService cs = new OAuthClientService();
            
            AuthenticationData authenticationData = new AuthenticationData();
            authenticationData.setUserName(props.get("USERNAME").toString());
            authenticationData.setCont(props.get("PASSOAUTH").toString());
            authenticationData.setUserNameBody(props.get("USERNAMEBODY").toString());
            authenticationData.setContBody(props.get("PASSBODY").toString());
            authenticationData.setUrl(props.get("ENDPOAUTH").toString());
            AuthorizedData ad = (AuthorizedData) cs.getData(authenticationData);
            
            ClientService ic = new InstitucionesClientService();
            InstitucionesEnterData ied = new InstitucionesEnterData();
            ied.setActoken(ad.getAccessToken());
            ied.setUrl(props.get("ENDPINST").toString());
            ied.setFlgEmisoras("true");
            ied.setFlgAdquirientes("false");
            mapaInstitucionesEmiBUB = (Map<Integer, InstitucionesResponseData>) ic.getData(ied);
        } catch (BUBClientServicesException ex) {
            LOG.info("Ocurrió un error al consultar las instituciones en la BUB",ex);
            mapaInstitucionesEmiBUB = new HashMap<>();
            //System.exit(-1);
        } catch (FileNotFoundException ex) {
            LOG.info("No se encuentró el archivo de propiedades",ex);
            System.exit(-1);
        } catch (IOException ex) {
            LOG.info("Ocurrió un error al leer el archivo de propiedades",ex);
            System.exit(-1);
        }
        this.mapaInstitucionesEmi = mapaInstitucionesEmiBUB;
        return this.mapaInstitucionesEmi;
    }
    
    public Map<Integer, InstitucionesResponseData> getMapaInstitucionesAdq(){
        if(this.mapaInstitucionesAdq != null){
            return this.mapaInstitucionesAdq;
        }
        
        Map<Integer, InstitucionesResponseData> mapaInstitucionesAdqBUB = null;
        FileInputStream conProp = null;
        Properties props;
        
        try {
            props = new Properties();
            String rutaProp = PathsProjectProvider.getConf().getHomeConfBatch() + "propEndPointsReporteB.properties";
            conProp = new FileInputStream(rutaProp);
            props.load(conProp);
            
            ClientService cs = new OAuthClientService();
            
            AuthenticationData authenticationData = new AuthenticationData();
            authenticationData.setUserName(props.get("USERNAME").toString());
            authenticationData.setCont(props.get("PASSOAUTH").toString());
            authenticationData.setUserNameBody(props.get("USERNAMEBODY").toString());
            authenticationData.setContBody(props.get("PASSBODY").toString());
            authenticationData.setUrl(props.get("ENDPOAUTH").toString());
            AuthorizedData ad = (AuthorizedData) cs.getData(authenticationData);
            
            ClientService ic = new InstitucionesClientService();
            InstitucionesEnterData ied = new InstitucionesEnterData();
            ied.setActoken(ad.getAccessToken());
            ied.setUrl(props.get("ENDPINST").toString());
            ied.setFlgEmisoras("false");
            ied.setFlgAdquirientes("true");
            mapaInstitucionesAdqBUB = (Map<Integer, InstitucionesResponseData>) ic.getData(ied);
        } catch (BUBClientServicesException ex) {
            LOG.info("Ocurrió un error al consultar las instituciones en la BUB",ex);
            mapaInstitucionesAdqBUB = new HashMap<>();
            //System.exit(-1);
        } catch (FileNotFoundException ex) {
            LOG.info("No se encuentró el archivo de propiedades",ex);
            System.exit(-1);
        } catch (IOException ex) {
            LOG.info("Ocurrió un error al leer el archivo de propiedades",ex);
            System.exit(-1);
        }
        this.mapaInstitucionesAdq = mapaInstitucionesAdqBUB;
        return this.mapaInstitucionesAdq;
    }
}
