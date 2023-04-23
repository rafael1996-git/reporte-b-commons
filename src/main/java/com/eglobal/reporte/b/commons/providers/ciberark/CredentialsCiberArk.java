/*Este código fuente es Confidencial y también puede contener información privilegiada, es para uso exclusivo de E-Global. Tenga en cuenta que cualquier distribución, copia o uso de esta información sin autorización está estrictamente prohibida. Si ha identificado algún mal uso de este código fuente por favor notifiquelo a la dirección de correo seginfo@eglobal.com.mx*/
package com.eglobal.reporte.b.commons.providers.ciberark;

/**
 *
 * @author xhls08
 */
public class CredentialsCiberArk {
    

    private String appID;
    private String safe;
    private String folder;
    private String object;
    private String reason;

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getSafe() {
        return safe;
    }

    public void setSafe(String safe) {
        this.safe = safe;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "CredentialsCiberArk{" + "appID=" + appID + ", safe=" + safe + ", folder=" + folder + ", object=" + object + ", reason=" + reason + '}';
    }

    
    
  

    public CredentialsCiberArk(String appID, String safe, String folder, String object, String reason) {
        this.appID = appID;
        this.safe = safe;
        this.folder = folder;
        this.object = object;
        this.reason = reason;
    }

    
    public CredentialsCiberArk() {
		super();
	}
}
