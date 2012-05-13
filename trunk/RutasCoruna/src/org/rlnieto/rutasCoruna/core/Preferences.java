/**
 *  Manejo de las preferencias del dispositivo.
 *  
 *  Las preferencias se almacenan en la ruta /data/data/paquete.aplicacion/shared_prefs/fichero.xml
 * 
 * 
 */
package org.rlnieto.rutasCoruna.core;

import android.content.Context;
import 	android.content.SharedPreferences;
//import android.widget.Toast;


public class Preferences {

	private int versionBd = 0;			// versión de la bd
	private Context contexto;
	
	private final String NOMBRE_PREFERENCIAS = "PreferenciasAplicacionRutas"; 
	private final String VERSION_BD = "versionBd";
	
	
	
	//-----------------------------------------------------------------------------
	// Constructores
	//
	//-----------------------------------------------------------------------------
	public Preferences(Context contexto){

		this.contexto = contexto;
		
		// Recupera los valores de las preferencias del dispositivo y los guarda
		// en las variables privadas
		SharedPreferences preferencias = contexto.getSharedPreferences(this.NOMBRE_PREFERENCIAS, Context.MODE_PRIVATE);
		
		// Cargamos los valores de las propiedades en las variables privadas
		versionBd = Integer.parseInt(preferencias.getString("versionBd", "0"));
		
	}
	
	
	
	//-----------------------------------------------------------------------------
	// Getters y setters para la versión de la bd
	//
	//-----------------------------------------------------------------------------
	/**
	 * Recupera la versión de la bd del dispositivo.
	 * 
	 * @return Versión de la bd del dispositivo como un entero
	 */
	
	public int getVersionBd() {
		return versionBd;
	}

	/**
	 * Establece la versión de la bd del dispositivo.
	 * 
	 * @param versionBd Nueva versión de la bd
	 * 
	 */
	public void setVersionBd(int nuevaVersionBd) {
		// actualizamos la versión en las preferencias del dispositivo
		SharedPreferences prefs = contexto.getSharedPreferences(this.NOMBRE_PREFERENCIAS, Context.MODE_PRIVATE);
			 
			SharedPreferences.Editor editor = prefs.edit();
			editor.putString(this.VERSION_BD, Integer.toString(nuevaVersionBd));
			editor.commit();
			
		this.versionBd = nuevaVersionBd;
	}

	/**
	 * Incrementa en 1 la versión de la bd del dispositivo.
	 * 
	 * @return Nueva versión de la bd
	 */
	public int avanzarVersion(){
		int version = this.getVersionBd();
		this.setVersionBd(version++);
		
		return version;
	}
	
	
	
	
}
