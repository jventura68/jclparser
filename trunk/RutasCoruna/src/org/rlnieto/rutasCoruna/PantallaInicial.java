package org.rlnieto.rutasCoruna;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class PantallaInicial extends Activity{

	private Context contexto = null;
	private Button cmdRuta = null;

		
	@Override
	//-----------------------------------------------------------------------------------
	// Punto de entrada a la aplicación
	//-----------------------------------------------------------------------------------
	public void onCreate(Bundle savedInstanceState){
        
		// Comprobamos si hay que actualizar la bd
		Updater updater = new Updater(this);
		updater.actualizarBd();
		
		super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_inicial);

    	//Toast.makeText(getBaseContext(), "Thread activado", Toast.LENGTH_LONG).show();

        cmdRuta = (Button)findViewById(R.id.cmdRuta);
        contexto = this;
        
        
        
		//--------------------------------------------------------------------------
		//
		// listeners
		//
		//--------------------------------------------------------------------------
        
		//--------------------------------------------------------------------------
		// Abrimos la actividad del mapa
		//--------------------------------------------------------------------------
        cmdRuta.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
		    	Toast.makeText(contexto, "Cargando ruta...", Toast.LENGTH_SHORT).show();

		    	Intent myIntent = new Intent(contexto, Main.class);
				contexto.startActivity(myIntent);
			}
		});
	}        
}