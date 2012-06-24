package org.rlnieto.entidades;

public class PoiComercial {

	private int idPoi = 0;
	private String nombrePoi = "";
	private String desPoi = "";
	private int categoriaPoi = 0;
	private String carpetaDocs = "";
	private String direccion = "";
	private String telefono = "";
	private int latitud = 0;
	private int longitud = 0;
	
	public int getIdPoi() {
		return idPoi;
	}
	public void setIdPoi(int idPoi) {
		this.idPoi = idPoi;
	}
	public String getNombrePoi() {
		return nombrePoi;
	}
	public void setNombrePoi(String nombrePoi) {
		this.nombrePoi = nombrePoi;
	}
	public String getDesPoi() {
		return desPoi;
	}
	public void setDesPoi(String desPoi) {
		this.desPoi = desPoi;
	}
	public int getCategoriaPoi() {
		return categoriaPoi;
	}
	public void setCategoriaPoi(int categoriaPoi) {
		this.categoriaPoi = categoriaPoi;
	}
	public String getCarpetaDocs() {
		return carpetaDocs;
	}
	public void setCarpetaDocs(String carpetaDocs) {
		this.carpetaDocs = carpetaDocs;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getLatitud() {
		return latitud;
	}
	public void setLatitud(int latitud) {
		this.latitud = latitud;
	}
	public int getLongitud() {
		return longitud;
	}
	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}
	
}
