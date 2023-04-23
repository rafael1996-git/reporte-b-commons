/*Este código fuente es Confidencial y también puede contener información privilegiada, es para uso exclusivo de E-Global. Tenga en cuenta que cualquier distribución, copia o uso de esta información sin autorización está estrictamente prohibida. Si ha identificado algún mal uso de este código fuente por favor notifiquelo a la dirección de correo seginfo@eglobal.com.mx*/

package com.eglobal.reporte.b.commons.providers.ciberark;


public class Credentials {

	private String url;
	private String user;
	private String pass;
	private String driver;
	private String descripcion;
	public Credentials() {
		super();
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public String toString() {
		return "Credentials [url=" + url + ", user=" + user + ", pass=" + pass + ", driver=" + driver + ", descripcion=" + descripcion + "]";
	}
	public Credentials(String url, String user, String pass, String driver) {
		super();
		this.url = url;
		this.user = user;
		this.pass = pass;
		this.driver = driver;
	}
	
	


}

