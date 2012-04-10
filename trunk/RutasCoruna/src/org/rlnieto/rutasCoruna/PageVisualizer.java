package org.rlnieto.rutasCoruna;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import android.text.Html;
import android.text.Spanned;
import android.text.Html.ImageGetter;
import android.util.Log;
import android.widget.TextView;

import android.app.Activity;
//import android.net.Uri;
import android.os.Bundle;
//import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class PageVisualizer extends Activity{

	/**
	 * Punto de entrada a la aplicación
	 * 
	 */
	public void onCreate(Bundle savedInstanceState){

		super.onCreate(savedInstanceState);
		setContentView(R.layout.visor_html);

		Bundle extras = getIntent().getExtras();
		if(extras !=null)
		{
			Integer clavePoi = (Integer)extras.get("clave_poi");
			
			String html = "<html><head></head><body background='white'><p>Cargando imágenes desde <b>assets/web</b>" +
			              " para el poi con clave " +  String.valueOf(clavePoi) + "</p><br/><br/>" + 
					"<img src='Wikipedia-logo-v2-es.png' /><body></html>";

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
					String rutaCompleta = "web/" + source;
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
	
	
	
	
	
	
	


}