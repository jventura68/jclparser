package org.rlnieto.rutasCoruna.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.rlnieto.rutasCoruna.R;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;
import android.content.Context;

//------------------------------------------------------------------------------------------------------
// Actualiza la base de datos y/o las páginas html con los datos de los pois
//
//------------------------------------------------------------------------------------------------------
public class Updater {

	private Context contexto;

	//private final String RUTA_DESTINO_HTML = "data/data/org.rlnieto.rutasCoruna/html"; // directorio
																						// sd

	/*
	 * Constructores
	 */
	public Updater(Context contexto) {

		this.contexto = contexto;

		// Comprobamos el estado de la sd por si no existe o no está montada
		// String estado = Environment.getExternalStorageState();
		//
		// if (estado.equals(Environment.MEDIA_MOUNTED))
		// {
		// Toast.makeText(contexto,
		// "Memoria disponible y con acceso de escritura",
		// Toast.LENGTH_LONG).show();
		// }
		// else if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY))
		// {
		// Toast.makeText(contexto,
		// "Memoria disponible y con acceso de lectura",
		// Toast.LENGTH_LONG).show();
		// }
		// else
		// {
		// Toast.makeText(contexto, "Memoria no disponible",
		// Toast.LENGTH_LONG).show();
		// }

	}

	/**
	 * Actualiza la bd que hay en /data/data/paquete.aplicacion/bd.sqlite con la
	 * que hay en /assets si las versiones son distintas
	 */
	public void actualizarBd() {

		Preferences preferences = new Preferences(contexto);

		int versionBdDispositivo = preferences.getVersionBd();
		Resources res = contexto.getResources();
		int versionBdRecursos = res.getInteger(R.integer.versionBd);

		//Toast.makeText(contexto, "Versión bd en el dispositivo: " + versionBdDispositivo, Toast.LENGTH_SHORT).show();
		//Toast.makeText(contexto, "Versión bd en el paquete: " + versionBdRecursos, Toast.LENGTH_SHORT).show();

		if (versionBdDispositivo != versionBdRecursos) {
			DatabaseHelper dbh = new DatabaseHelper(contexto);

			try {
				dbh.createDataBase();
				Toast.makeText(contexto, "Actualizada BD", Toast.LENGTH_LONG).show(); 

			} catch (IOException ioe) {
				throw new Error("No se pudo crear la base de datos");
			}

			preferences.setVersionBd(versionBdRecursos);
		}

	}

	//private Updater getResources() {
		// TODO Auto-generated method stub
	//	return null;
	//}

	//private int getInteger(int versionbd) {
		// TODO Auto-generated method stub
	//	return 0;
	//}

	/**
	 * Copia los ficheros html desde el directorio de assets a la tarjeta sd
	 * 
	 * @param contexto
	 * @param directorioOrigen
	 */
	public void copiarHtmlTarjetaSD(Context contexto, String directorioOrigen) {

		// --------------------------------------------------------------------------------------------------
		// Copiamos los ficheros a la sd
		// (data/data/org.rlnieto.rutasCoruna/html)
		// TODO: cómo sé si los ficheros ya existen en la sd y su version? =>
		// tabla de parámetros en la bd
		// o utilizar las preferencias?
		// --------------------------------------------------------------------------------------------------
		// Open your local db as the input stream
		// InputStream myInput = miContexto.getAssets().open(DATABASE_NAME);
		AssetManager am = contexto.getAssets();

		try {
			String list[] = am.list(directorioOrigen);
			Log.w("Ficheros", "Encontrados " + list.length + " ficheros");

			if (list != null)
				for (int i = 0; i < list.length; ++i) {
					Log.w("Assets:", directorioOrigen + "/" + list[i]);
					// displayFiles(mgr, path + list[i]); // por si hay
					// directorios dentro?
				}
		} catch (IOException e) {
			Log.w("List error:", "can't list" + directorioOrigen);
		}

		try {
			File ruta_sd = Environment.getExternalStorageDirectory();

			File f = new File(ruta_sd.getAbsolutePath(), "prueba_sd.txt");

			OutputStreamWriter fout = new OutputStreamWriter(
					new FileOutputStream(f));

			fout.write("Texto de prueba.");
			fout.close();
		} catch (Exception ex) {
			Log.e("Ficheros",
					"Error al escribir fichero a tarjeta SD" + ex.getMessage());
		}

	}

}
