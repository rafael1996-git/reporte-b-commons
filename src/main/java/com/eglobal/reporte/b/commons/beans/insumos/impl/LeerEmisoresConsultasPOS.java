/*
 * Este código fuente es Confidencial y también puede contener información privilegiada, es para uso exclusivo de E-Global.
 * Tenga en cuenta que cualquier distribución, copia o uso de esta información sin autorización está estrictamente prohibida.
 * Si ha identificado algún mal uso de este código fuente por favor notifiquelo a la dirección de correo seginfo@eglobal.com.mx
 */
package com.eglobal.reporte.b.commons.beans.insumos.impl;

import com.eglobal.reporte.b.commons.beans.EmisoresConsultaBean;
import com.eglobal.reporte.b.commons.beans.exceptions.GeneraResumenReporteBException;
import com.eglobal.reporte.b.commons.beans.insumos.LeerEmisoresConsulta;
import com.eglobal.reporte.b.commons.providers.PathsProjectProvider;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author egldt1134
 */
public class LeerEmisoresConsultasPOS implements LeerEmisoresConsulta<Map<Integer,List<EmisoresConsultaBean>>>{

    @Override
    public Map<Integer, List<EmisoresConsultaBean>> obtenerEmisores(String nArchivo) throws GeneraResumenReporteBException{
        Map<Integer, List<EmisoresConsultaBean>> mapaEmisores = new LinkedHashMap<>();
        String rutaXML = PathsProjectProvider.getConf().getHomeConfBatch() + nArchivo;
        File file = new File(rutaXML);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(file);
            document.getDocumentElement().normalize();
            NodeList processes = document.getElementsByTagName("process");
            for (int i = 0; i < processes.getLength(); i++) {
                Node nProcess = processes.item(i);
                Element eProcess = (Element) nProcess;
                String nombreProceso = eProcess.getAttribute("id"); 
                if (nombreProceso != null && nombreProceso.equals("POS")) {
                    NodeList emisores = eProcess.getElementsByTagName("emisor");
                    for (int j = 0; j < emisores.getLength(); j++) {
                        Node nEmisores= emisores.item(j);
                        Element eEmisores = (Element) nEmisores;
                        String idEmisor = eEmisores.getAttribute("id");
                        String subAfiliados = eEmisores.getAttribute("subafiliados");
                        NodeList queries = eEmisores.getElementsByTagName("queries");
                        for (int k = 0; k < queries.getLength(); k++) {
                            Node nQueries = queries.item(k);
                            Element eQueries = (Element) nQueries;
                            String idCarat = eQueries.getAttribute("carat");
                            NodeList nQuery = eQueries.getElementsByTagName("query");
                            List<EmisoresConsultaBean> lstEmisoresConsultaBean = new LinkedList<>();
                            for(int l=0; l<nQuery.getLength();l++){
                                Node nTable = nQuery.item(l);
                                String table = nTable.getTextContent();
                                EmisoresConsultaBean emisoresConsultaBean = new EmisoresConsultaBean();
                                emisoresConsultaBean.setIdCaratula(idCarat);
                                emisoresConsultaBean.setIdEmisor(new Integer(idEmisor));
                                emisoresConsultaBean.setSubAfiliados(subAfiliados.equals("1"));
                                emisoresConsultaBean.setQuery(table);
                                lstEmisoresConsultaBean.add(emisoresConsultaBean);
                            }
                            mapaEmisores.put(new Integer(idEmisor), lstEmisoresConsultaBean);
                        }

                    }
                }
            }
        } catch (ParserConfigurationException ex) {
            throw new GeneraResumenReporteBException("No fue posible parsear el archivo de propiedades de los DataSource.");
        } catch (IOException ex) {
            throw new GeneraResumenReporteBException("No fue posible abrir o cerrar el archivo de propiedades de los DataSource.");
        } catch (SAXException ex) {
            throw new GeneraResumenReporteBException("Ocurrió un error al validar formato del archivo de propiedades de los DataSource.");
        }
        return mapaEmisores;
    }
}
