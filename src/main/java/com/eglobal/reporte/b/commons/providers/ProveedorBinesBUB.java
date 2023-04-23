/*
 * Este código fuente es Confidencial y también puede contener información privilegiada, es para uso exclusivo de E-Global.
 * Tenga en cuenta que cualquier distribución, copia o uso de esta información sin autorización está estrictamente prohibida.
 * Si ha identificado algún mal uso de este código fuente por favor notifiquelo a la dirección de correo seginfo@eglobal.com.mx
 */
package com.eglobal.reporte.b.commons.providers;

import com.eglobal.beans.AuthenticationData;
import com.eglobal.beans.AuthorizedData;
import com.eglobal.beans.ConsultaSimpleEnterData;
import com.eglobal.beans.ConsultaSimpleResponseData;
import com.eglobal.exceptions.BUBClientServicesException;
import com.eglobal.services.ClientService;
import com.eglobal.services.impl.ClientConsultaSimple;
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
public class ProveedorBinesBUB {
    private static final Logger LOG = LogManager.getLogger(ProveedorBinesBUB.class);
    private static ProveedorBinesBUB instance;
    private Map<String, ConsultaSimpleResponseData> mapaBinesEV;
    
    public static ProveedorBinesBUB GETINSTANCE() {
        if (instance != null) {
            return instance;
        }
        instance = new ProveedorBinesBUB();
        return instance;
    }
    
    public Map<String, ConsultaSimpleResponseData> getMapaBinesEv(){
        if(this.mapaBinesEV != null){
            return this.mapaBinesEV;
        }
        
        Map<String, ConsultaSimpleResponseData> mapaBinesEv = null;
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
        
            ClientService sc = new ClientConsultaSimple();
            ConsultaSimpleEnterData csed = new ConsultaSimpleEnterData();
            csed.setAtoken(ad.getAccessToken());
            csed.setUrl(props.get("ENDPBINES").toString());
            csed.setPagina(1);
            csed.setIdInstitucion(54);
            mapaBinesEv = (Map<String, ConsultaSimpleResponseData>) sc.getData(csed);
            
        } catch (BUBClientServicesException ex) {
            LOG.info("Ocurrió un error al consultar las instituciones en la BUB",ex);
            mapaBinesEv = new HashMap<>();
            //System.exit(-1);
        } catch (FileNotFoundException ex) {
            LOG.info("No se encuentró el archivo de propiedades",ex);
            System.exit(-1);
        } catch (IOException ex) {
            LOG.info("Ocurrió un error al leer el archivo de propiedades",ex);
            System.exit(-1);
        }
        this.mapaBinesEV = mapaBinesEv;
        return this.mapaBinesEV;
    }
}
