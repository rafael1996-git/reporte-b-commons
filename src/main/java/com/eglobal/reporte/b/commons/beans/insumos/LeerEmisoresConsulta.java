/*
 * Este código fuente es Confidencial y también puede contener información privilegiada, es para uso exclusivo de E-Global.
 * Tenga en cuenta que cualquier distribución, copia o uso de esta información sin autorización está estrictamente prohibida.
 * Si ha identificado algún mal uso de este código fuente por favor notifiquelo a la dirección de correo seginfo@eglobal.com.mx
 */
package com.eglobal.reporte.b.commons.beans.insumos;

import com.eglobal.reporte.b.commons.beans.exceptions.GeneraResumenReporteBException;


/**
 *
 * @author egldt1134
 * @param <T>
 */
public interface LeerEmisoresConsulta <T>{
    public T obtenerEmisores(String nArchivo) throws GeneraResumenReporteBException;
}
