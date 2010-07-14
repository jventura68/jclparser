//------------------------------------------------------------------------------------------
// FicheroJcl
// ==========
// Estructura para almacenar los ficheros de entrada/salida asociados a un programa en un
// paso de jcl
//------------------------------------------------------------------------------------------
package org.rlnieto.jclparser.entidades;

public class FicheroJcl {
 
	private String nombreLogico = "";
	private String nombreFisico = "";
	private String disp = "";
	
	// constructor
	public FicheroJcl(String vNombreFisico, String vNombreLogico, String vDisp){
		this.nombreLogico = vNombreLogico;
		this.nombreFisico = vNombreFisico;
		this.disp = vDisp;
	}
	
	
	public String getNombreLogico() {
		return nombreLogico;
	}
	public void setNombreLogico(String nombreLogico) {
		this.nombreLogico = nombreLogico;
	}
	public String getNombreFisico() {
		return nombreFisico;
	}
	public void setNombreFisico(String nombreFisico) {
		this.nombreFisico = nombreFisico;
	}
	public String getDisp() {
		return disp;
	}
	public void setDisp(String disp) {
		this.disp = disp;
	}
	
	
	
	
}
