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
import android.database.SQLException;
//import android.net.Uri;
import android.os.Bundle;
//import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class PageVisualizer extends Activity{

	private final String FICHERO_INDICE = "index.html";
	protected String carpetaDocs = "";
	
	
	/**
	 * Punto de entrada a la aplicación
	 * 
	 */
	public void onCreate(Bundle savedInstanceState){

		String html = "";

		super.onCreate(savedInstanceState);
		setContentView(R.layout.visor_html);

		Bundle extras = getIntent().getExtras();
		if(extras !=null)
		{
			Integer clavePoi = (Integer)extras.get("clave_poi");
			carpetaDocs = buscarCarpetaDocs(clavePoi);  // recuperamos el nombre de la carpeta con el texto
			// y las fotos

			// leemos el texto html a mostrar de la carpeta "assets" y lo volcamos en el textView
			html = leerDocumento(carpetaDocs, FICHERO_INDICE);

			Spanned s = Html.fromHtml(html, getImageHTML_assets(), null);
			TextView txt = (TextView)findViewById(R.id.txtDocumento);
			txt.setText(s);

		}

	}


	/**
	 * Manejador para las imágenes html que aparecen en un texto (crea un drawable con la imagen)
	 * 
	 * @return
	 */
	public ImageGetter getImageHTML_assets(){
		ImageGetter ig = new ImageGetter(){
			
			public Drawable getDrawable(String source) {
				
				try{
					String rutaCompleta = PageVisualizer.this.carpetaDocs + source;
					InputStream is = getAssets().open(rutaCompleta);
					Drawable d = Drawable.createFromStream(is, "src name");
					d.setBounds(0, 0, d.getIntrinsicWidth(),d.getIntrinsicHeight());
					return d;
				}catch(IOException e){
					Log.v("IOException",e.getMessage());
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
	public ImageGetter getImageHTML_link(){
		ImageGetter ig = new ImageGetter(){
			public Drawable getDrawable(String source) {
				try{
					Drawable d = Drawable.createFromStream(new URL(source).openStream(), "src name");
					d.setBounds(0, 0, d.getIntrinsicWidth(),d.getIntrinsicHeight());
					return d;
				}catch(IOException e){
					Log.v("IOException",e.getMessage());
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
	public ImageGetter getImageHTML_drawable(){
		ImageGetter ig = new ImageGetter(){
			public Drawable getDrawable(String source) {
				int resID = getResources().getIdentifier(source, "assets", "org.rlnieto.rutasCoruna"); //nombre del paquete al final
				Drawable d = new BitmapDrawable( BitmapFactory.decodeResource( PageVisualizer.this.getBaseContext().getResources(), resID));
				d.setBounds(0, 0, d.getIntrinsicWidth(),d.getIntrinsicHeight());
				return d;
			}
		};
		return ig;
	}



	/**
	 * Recupera la carpeta donde se guarda la documentación asociada a un poi
	 * 
	 * @return
	 */
	private String buscarCarpetaDocs(int clavePoi){

		DatabaseHelper dbh = new DatabaseHelper(this);

		try {
			dbh.openDataBase();
		}catch(SQLException sqle){throw sqle;}

		String nombreCarpeta = dbh.obtenerCarpetaDocsPoi(clavePoi);

		dbh.close();

		return nombreCarpeta;	

	}

	
	/**
	 * Lee el index.html de la carpeta que se le envía como parámetro. Esta carpeta está colgando
	 * de "assets"
	 * 
	 * @param carpetaDocs: carpeta colgando de assets donde está el fichero index.html a mostrar
	 * @return
	 */
	private String leerDocumento(String carpetaDocs, String ficheroHtml){

		String rutaFichero = carpetaDocs + ficheroHtml;
		String textoHtml = "";


		// Cargamos el texto desde el fichero
		try{

			InputStream is = getAssets().open(rutaFichero);
			InputStreamReader isr = new InputStreamReader(is);

			BufferedReader bf = new BufferedReader(isr); 

			StringBuffer textoFichero = new StringBuffer("");

			String linea = bf.readLine();
			while(linea != null){
				textoFichero.append(linea);
				linea = bf.readLine();
			}

			textoHtml = textoFichero.toString();

		}catch(Exception e){Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();}   // TODO: ¿qué hacemos si no encuentra el fichero? ¿sacar un mensaje y seguir?

		return(textoHtml);

	}




}