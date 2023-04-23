/*
 * Este código fuente es Confidencial y también puede contener información privilegiada, es para uso exclusivo de E-Global.
 * Tenga en cuenta que cualquier distribución, copia o uso de esta información sin autorización está estrictamente prohibida.
 * Si ha identificado algún mal uso de este código fuente por favor notifiquelo a la dirección de correo seginfo@eglobal.com.mx
 */
package com.eglobal.reporte.b.commons.beans;

/**
 *
 * @author egldt1134
 */
public class EmisoresConsultaBean {
    private Integer idEmisor;
    private String query;
    private String idCaratula;
    private boolean subAfiliados;

    public Integer getIdEmisor() {
        return idEmisor;
    }

    public void setIdEmisor(Integer idEmisor) {
        this.idEmisor = idEmisor;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getIdCaratula() {
        return idCaratula;
    }

    public void setIdCaratula(String idCaratula) {
        this.idCaratula = idCaratula;
    }

    public boolean isSubAfiliados() {
        return subAfiliados;
    }

    public void setSubAfiliados(boolean subAfiliados) {
        this.subAfiliados = subAfiliados;
    }
}


