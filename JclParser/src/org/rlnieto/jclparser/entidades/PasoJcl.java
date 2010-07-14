//------------------------------------------------------------------------------------------
// PasoJcl
// =======
// Estructura para almacenar los datos de un paso de jcl:
//	- Paso
//	- Programa
// 	- Conjunto de ficheros de entrada/salida del programa
//------------------------------------------------------------------------------------------
package org.rlnieto.jclparser.entidades;

import java.util.ArrayList;

public class PasoJcl {
	private String nombrePaso = "";
	private String nombrePrograma = "";
	private ArrayList<FicheroJcl> listaFicheros = null;

	public PasoJcl(String vNombrePaso, String vNombrePrograma, ArrayList<FicheroJcl> vListaFicheros){
		this.nombrePaso = vNombrePaso;
		this.nombrePrograma = vNombrePrograma;
		this.listaFicheros = vListaFicheros;
	}
	
	
	public String getNombrePaso() {
		return nombrePaso;
	}
	public void setNombrePaso(String nombrePaso) {
		this.nombrePaso = nombrePaso;
	}
	public String getNombrePrograma() {
		return nombrePrograma;
	}
	public void setNombrePrograma(String nombrePrograma) {
		this.nombrePrograma = nombrePrograma;
	}
	public ArrayList<FicheroJcl> getListaFicheros() {
		return listaFicheros;
	}
	public void setListaFicheros(ArrayList<FicheroJcl> listaFicheros) {
		this.listaFicheros = listaFicheros;
	}
	
}
