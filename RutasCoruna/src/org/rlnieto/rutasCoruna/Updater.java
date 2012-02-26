package org.rlnieto.rutasCoruna;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;
import android.content.res.AssetManager;
import android.content.Context;

import android.widget.Toast;



//------------------------------------------------------------------------------------------------------
// Actualiza la base de datos y/o las páginas html con los datos de los pois
//
//------------------------------------------------------------------------------------------------------
public class Updater {
	
	private final String RUTA_DESTINO_HTML = "data/data/org.rlnieto.rutasCoruna/html";  // directorio con los ficheros html en la sd
	
	
	//------------------------------------------------------------------------------------------------------
	// Constructor
	//------------------------------------------------------------------------------------------------------
	public Updater(Context contexto){
		
		//--------------------------------------------------------------------------------------------------
		// Comprobamos el estado de la sd por si no existe o no está montada
		//--------------------------------------------------------------------------------------------------
		String estado = Environment.getExternalStorageState();
		
		if (estado.equals(Environment.MEDIA_MOUNTED))
		{
			Toast.makeText(contexto, "Memoria disponible y con acceso de escritura", Toast.LENGTH_LONG).show();
		}
		else if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY))
		{
			Toast.makeText(contexto, "Memoria disponible y con acceso de lectura", Toast.LENGTH_LONG).show();
		}
		else
		{
			Toast.makeText(contexto, "Memoria no disponible", Toast.LENGTH_LONG).show();
		}
	}
	
	
	//------------------------------------------------------------------------------------------------------
	// Copia los ficheros html desde el directorio de assets a la tarjeta sd
	//------------------------------------------------------------------------------------------------------
	public void copiarHtmlTarjetaSD(Context contexto, String directorioOrigen){
		
		//--------------------------------------------------------------------------------------------------
		// Copiamos los ficheros a la sd (data/data/org.rlnieto.rutasCoruna/html)
		// TODO: cómo sé si los ficheros ya existen en la sd y su version? => tabla de parámetros en la bd
		// 		 o utilizar las preferencias?
		//--------------------------------------------------------------------------------------------------
    	//Open your local db as the input stream
    	//InputStream myInput = miContexto.getAssets().open(DATABASE_NAME);
		AssetManager am = contexto.getAssets();
		
		try {
	        String list[] = am.list(directorioOrigen);
	        Log.w("Ficheros", "Encontrados " + list.length + " ficheros");
	        
	        if (list != null)
	            for (int i=0; i<list.length; ++i)
	                {
	                    Log.w("Assets:", directorioOrigen +"/"+ list[i]);
	                    //displayFiles(mgr, path + list[i]);  // por si hay directorios dentro?
	                }
	    } catch (IOException e) {
	        Log.w("List error:", "can't list" + directorioOrigen);
	    }

		
		
		try
		{
			File ruta_sd = Environment.getExternalStorageDirectory();
		 
		    File f = new File(ruta_sd.getAbsolutePath(), "prueba_sd.txt");
		 
		    OutputStreamWriter fout =
		        new OutputStreamWriter(
		            new FileOutputStream(f));
		 
		    fout.write("Texto de prueba.");
		    fout.close();
		}
		catch (Exception ex)
		{
		    Log.e("Ficheros", "Error al escribir fichero a tarjeta SD" + ex.getMessage());
		}
		
	}
	

}
