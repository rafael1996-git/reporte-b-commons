/*
 * Este código fuente es Confidencial y también puede contener información privilegiada, es para uso exclusivo de E-Global.
 * Tenga en cuenta que cualquier distribución, copia o uso de esta información sin autorización está estrictamente prohibida.
 * Si ha identificado algún mal uso de este código fuente por favor notifiquelo a la dirección de correo seginfo@eglobal.com.mx
 */
package com.eglobal.reporte.b.commons.providers;

import java.io.File;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author _snak
 */
public class PathsProjectProvider {
  public static final int NUM_DIRECTORIOS = 1;
  
  public File dirClassPath;
  
  private String homeBatch;
  
  private String homeConfBatch;
  
  private String homeLogBatch;
  
  private String homeProductosBatch;
  
  private static PathsProjectProvider conf;
  
  private static final Logger LOG = LogManager.getLogger(PathsProjectProvider.class);
  
  public static PathsProjectProvider getConf() {
    PathsProjectProvider configuracion = null;
    if (conf != null) {
      configuracion = conf;
      return configuracion;
    } 
    try {
      configuracion = new PathsProjectProvider();
      conf = configuracion;
    } catch (IOException ex) {
      LOG.info("Error al abrir o cerrar archivo...");
    } 
    return configuracion;
  }
  
  private PathsProjectProvider() throws IOException {
    try {
      cargarCofiguracion();
    } catch (IOException ex) {
      LOG.info("No fue posible cargar la configuración");
      throw new IOException("Algo salió mal al intentar cargar la configuraciprincipal del proceso");
    } 
  }
  
  private void cargarCofiguracion() throws IOException {
    this.dirClassPath = new File(".");
    File dirHome = this.dirClassPath.getCanonicalFile();
    File dirParent = null;
    for (int i = 0; i < 1; i++) {
      dirParent = dirHome.getParentFile();
      dirHome = dirParent;
    } 
    LOG.info("directorio padre :----> " + dirParent.getCanonicalFile());
    this.homeBatch = dirParent.getAbsolutePath();
    cargarHomeConfBatch();
    cargarHomeLogBatch();
    cargarHomeProductosBatch();
  }
  
  private void cargarHomeConfBatch() {
    this.homeConfBatch = this.homeBatch + File.separator + "config" + File.separator;
  }
  
  private void cargarHomeLogBatch() {
    this.homeLogBatch = this.homeBatch + File.separator + "logs" + File.separator;
  }
  
  private void cargarHomeProductosBatch() {
    this.homeProductosBatch = this.homeBatch + File.separator + "productos";
  }
  
  public String getHomeBatch() {
    return this.homeBatch;
  }
  
  public String getHomeConfBatch() {
    return this.homeConfBatch;
  }
  
  public String getHomeLogBatch() {
    return this.homeLogBatch;
  }
  
  public String getHomeProductosBatch() {
    return this.homeProductosBatch;
  }
}