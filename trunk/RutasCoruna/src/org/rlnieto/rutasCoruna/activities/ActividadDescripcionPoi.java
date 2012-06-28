package org.rlnieto.rutasCoruna.activities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.rlnieto.rutasCoruna.R;
import org.rlnieto.rutasCoruna.R.id;
import org.rlnieto.rutasCoruna.R.layout;
import org.rlnieto.rutasCoruna.core.DatabaseHelper;

import android.text.Html;
import android.text.Spanned;
import android.text.Html.ImageGetter;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
//import android.net.Uri;
import android.net.Uri;
import android.os.Bundle;
//import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class ActividadDescripcionPoi extends Activity {

	protected String uriImagenPoi = "";
	private Context contexto = null;

	/**
	 * Punto de entrada a la aplicación
	 * 
	 */
	public void onCreate(Bundle savedInstanceState) {

		String html = "";

		super.onCreate(savedInstanceState);
		setContentView(R.layout.visor_html);
		contexto = this;

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			Integer clavePoi = (Integer) extras.get("clave_poi");
			uriImagenPoi = uriImagenPoi(clavePoi); // recuperamos el nombre
														// de la carpeta con el
														// texto y las fotos

//uriImagenPoi = "web/" + uriImagenPoi + " "; 	
uriImagenPoi = "<img src=\"" + uriImagenPoi + "\"/> ";

			
			
			// Volcamos el texto en el textView
			html = uriImagenPoi + recuperarDescripcionPoi(clavePoi);

			Spanned s = Html.fromHtml(html, getImageHTML_assets(), null);
			TextView txt = (TextView) findViewById(R.id.txtDocumento);
			txt.setText(s);

		}

	}

	/**
	 * Manejador para las imágenes html que aparecen en un texto (crea un
	 * drawable con la imagen)
	 * 
	 * @return
	 */
	public ImageGetter getImageHTML_assets() {

		ImageGetter ig = new ImageGetter() {

			public Drawable getDrawable(String source) {

				try {
					//String rutaCompleta = ActividadDescripcionPoi.this.uriImagenPoi + source;

					InputStream is = getAssets().open(source);
					Drawable d = Drawable.createFromStream(is, "src name");
					d.setBounds(0, 0, d.getIntrinsicWidth(),
							d.getIntrinsicHeight());
					return d;
				} catch (IOException e) {
					Log.v("IOException", e.getMessage() + " Buscando: " + source);
					return null;
				}
			}
		};
		return ig;
	}

	/**
	 * Manejador para imagenes por url
	 * 
	 * @return
	 */
	public ImageGetter getImageHTML_link() {
		ImageGetter ig = new ImageGetter() {
			public Drawable getDrawable(String source) {
				try {
					Drawable d = Drawable.createFromStream(
							new URL(source).openStream(), "src name");
					d.setBounds(0, 0, d.getIntrinsicWidth(),
							d.getIntrinsicHeight());
					return d;
				} catch (IOException e) {
					Log.v("IOException", e.getMessage());
					return null;
				}
			}
		};
		return ig;
	}

	/**
	 * Transforma las imagenes en objetos incrustables
	 * 
	 * @return
	 */
	public ImageGetter getImageHTML_drawable() {
		ImageGetter ig = new ImageGetter() {
			public Drawable getDrawable(String source) {
				int resID = getResources().getIdentifier(source, "assets",
						"org.rlnieto.rutasCoruna"); // nombre del paquete al
													// final
				Drawable d = new BitmapDrawable(BitmapFactory.decodeResource(
						ActividadDescripcionPoi.this.getBaseContext()
								.getResources(), resID));
				d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
				return d;
			}
		};
		return ig;
	}

	/**
	 * Recupera la uri de la imagen del poi
	 * 
	 * @return
	 */
	private String uriImagenPoi(int clavePoi) {

		DatabaseHelper dbh = new DatabaseHelper(this);

		try {
			dbh.openDataBase();
		} catch (SQLException sqle) {
			throw sqle;
		}

		String uriImagen = dbh.obtenerUriImagenPoi(clavePoi);

		dbh.close();

		return uriImagen;

	}

	/**
	 * Lee el index.html de la carpeta que se le envía como parámetro. Esta
	 * carpeta está colgando de "assets"
	 * 
	 * @param carpetaDocs
	 *            : carpeta colgando de assets donde está el fichero index.html
	 *            a mostrar
	 * @return
	 */
	private String leerDocumento(String carpetaDocs, String ficheroHtml) {

		String rutaFichero = carpetaDocs + ficheroHtml;
		String textoHtml = "";

		// Cargamos el texto desde el fichero
		try {

			InputStream is = getAssets().open(rutaFichero);
			InputStreamReader isr = new InputStreamReader(is);

			BufferedReader bf = new BufferedReader(isr);

			StringBuffer textoFichero = new StringBuffer("");

			String linea = bf.readLine();
			while (linea != null) {
				textoFichero.append(linea);
				linea = bf.readLine();
			}

			textoHtml = textoFichero.toString();

		} catch (Exception e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
		} // TODO: ¿qué hacemos si no encuentra el fichero? ¿sacar un mensaje y
			// seguir?

		return (textoHtml);

	}

	/**
	 * Recupera datos de la bd y rellena los campos del formulario
	 * 
	 * @param idPoi
	 *            : clave del poi en la tabla
	 * 
	 */
	private String recuperarDescripcionPoi(int clavePoi) {

		// TODO: ver si se puede eliminar el cursor y utilizar un objeto de tipo
		// poi. Problema: ¿hacemos
		// uno genérico para hoteles y restaurantes o uno distinto para cada
		// categoría? Me inclino por la
		// segunda opción
		Cursor c = null;

		
Toast.makeText(contexto, String.valueOf(clavePoi), Toast.LENGTH_LONG).show();			
		
		
		// Recuperamos los datos del poi
		DatabaseHelper dbh = new DatabaseHelper(this);

		try {
			dbh.openDataBase();

		} catch (SQLException sqle) {
			throw sqle;
		}

		c = dbh.recuperarPoi(clavePoi);
		c.moveToFirst();

		String datosPoi = c.getString(c.getColumnIndex("descPoi"));

		c.close();
		dbh.close();

		return datosPoi;

	}

}