package org.rlnieto.rutasCoruna.activities;

import java.io.IOException;
import java.io.InputStream;

import org.rlnieto.rutasCoruna.R;
import org.rlnieto.rutasCoruna.core.DatabaseHelper;

import android.text.Html;
import android.text.Spanned;
import android.text.Html.ImageGetter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.TextView;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.graphics.drawable.Drawable;


public class ActividadDescripcionPoi extends Activity {

	protected String uriImagenPoi = "";
	private Context contexto = null;
	private int windowHeight = 320;
	private int windowWidth = 240;
	
	
	/**
	 * Punto de entrada a la aplicación
	 * 
	 */
	public void onCreate(Bundle savedInstanceState) {

		String html = "";
		String[] nombreDescripcion = null;
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.visor_html);
		contexto = this;
		TextView txt = (TextView) findViewById(R.id.txtDocumento);


		// Consultamos la resolución de la pantalla para redimensionar las fotografías que 
		// acompañan a la documentación. Lo hacemos aquí y no en el método que carga la fotografía
		// para no repetir el cálculo para todas las fotografías
		DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        windowHeight = displaymetrics.heightPixels;
        windowWidth = displaymetrics.widthPixels;
        
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			Integer clavePoi = (Integer) extras.get("clave_poi");
			uriImagenPoi = uriImagenPoi(clavePoi); // recuperamos el nombre
														// de la carpeta con el
														// texto y las fotos

			// la imagen debe de ir con su tag html "img"
			uriImagenPoi = "<img src=\"" + uriImagenPoi + "\"/>";		
Log.w("uri imagen poi", uriImagenPoi);

			// Volcamos el texto en el textView
			nombreDescripcion =  recuperarDescripcionPoi(clavePoi);
			//html = uriImagenPoi + recuperarDescripcionPoi(clavePoi);
			String titulo = "<h1>" + nombreDescripcion[0] + "</h1>";
			html = uriImagenPoi + titulo + nombreDescripcion[1];
			
			
			Spanned s = Html.fromHtml(html, getImageHTML_assets(), null);
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

					Drawable d = Drawable.createFromStream(is, null);
					
					//d.setBounds(0, 0, d.getIntrinsicWidth(),
					//		d.getIntrinsicHeight());

					// Redimensionamos y centramos la imagen en función de la resolución de pantalla
					float anchoImagen = (float)d.getIntrinsicWidth();
					float largoImagen = (float)d.getIntrinsicHeight(); 
					
					float anchoPantalla = (float)ActividadDescripcionPoi.this.windowWidth;
					float largoPantalla = (float)ActividadDescripcionPoi.this.windowHeight;
					
					float relacionX = anchoImagen / anchoPantalla;
					float relacionY = largoImagen / largoPantalla;
					
					float factor = relacionX;
					if(factor < relacionY) factor = relacionY;

					int nuevoAnchoImagen = (int)((anchoImagen / factor) * 0.95); 
					int nuevoLargoImagen = (int)((largoImagen / factor) * 0.95);

					
					d.setBounds((int)((anchoPantalla - nuevoAnchoImagen) / 2), 2, nuevoAnchoImagen, nuevoLargoImagen);
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


	private String[] recuperarDescripcionPoi(int clavePoi) {

		// TODO: ver si se puede eliminar el cursor y utilizar un objeto de tipo
		// poi. Problema: ¿hacemos
		// uno genérico para hoteles y restaurantes o uno distinto para cada
		// categoría? Me inclino por la
		// segunda opción
		Cursor c = null;

		String[] nombreDescripcion = new String[2];
		
		// Recuperamos los datos del poi
		DatabaseHelper dbh = new DatabaseHelper(this);

		try {
			dbh.openDataBase();

		} catch (SQLException sqle) {
			throw sqle;
		}

		c = dbh.recuperarPoi(clavePoi);
		c.moveToFirst();

		nombreDescripcion[0] = c.getString(c.getColumnIndex("nombrePoi"));
		nombreDescripcion[1] = c.getString(c.getColumnIndex("descPoi"));

		c.close();
		dbh.close();

		return nombreDescripcion;

	}

}