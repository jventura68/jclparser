package org.rlnieto.rutasCoruna.activities;

import org.rlnieto.rutasCoruna.R;
import org.rlnieto.rutasCoruna.R.id;
import org.rlnieto.rutasCoruna.R.layout;
import org.rlnieto.rutasCoruna.core.Example;
import org.rlnieto.rutasCoruna.core.Updater;

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
	private Button cmdAbout = null;
		
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
        cmdAbout = (Button)findViewById(R.id.cmdCreditos);
        contexto = this;
        
        
        
        /**-------------------------------------------------------------------------
         * 
         * Listeners
         * 
         * 
         *---------------------------------------------------------------------------
         **/
        
        /**
         * Abre la actividad del mapa
         * 
         */
        cmdRuta.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
		    	Toast.makeText(contexto, "Cargando ruta...", Toast.LENGTH_SHORT).show();

		    	Intent myIntent = new Intent(contexto, PantallaMapa.class);
				contexto.startActivity(myIntent);
			}
		});
        
        
        /**
         * De momento abre las pantallas de pruebas
         * 
         */
        cmdAbout.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View arg0) {
/*        		Intent myIntent = new Intent(contexto, PantallaPruebas.class);
        		contexto.startActivity(myIntent);
  */
        		Intent myIntent = new Intent(contexto, Example.class);
        		contexto.startActivity(myIntent);
  	
        	
        	
        	}
        	
        });
        
        
        
        
        
	}        
}