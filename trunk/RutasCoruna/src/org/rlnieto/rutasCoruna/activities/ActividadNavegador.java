package org.rlnieto.rutasCoruna.activities;

import org.rlnieto.rutasCoruna.R;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

public class ActividadNavegador extends Activity{

	private final String FICHERO_INDICE = "index.html";
	protected String carpetaDocs = "";
	
	
	/**
	 * Punto de entrada a la aplicacion
	 * 
	 */
	public void onCreate(Bundle savedInstanceState){

		String url = "http://www.corunavenue.com";

		super.onCreate(savedInstanceState);
		setContentView(R.layout.pantalla_navegador);

		WebView wv = (WebView)findViewById(R.id.webView);
		
		// Recuperamos la url a mostrar
		Bundle bundle = getIntent().getExtras();
		if(bundle.getString("url") != null || bundle.getString("url") != "")
			url = bundle.getString("url");
		

		Toast.makeText(this, "Cargando datos...", Toast.LENGTH_LONG).show();
		wv.loadUrl(url);
		
	}

}