package org.rlnieto.rutasCoruna.activities;

import org.rlnieto.rutasCoruna.R;
import org.rlnieto.rutasCoruna.R.id;
import org.rlnieto.rutasCoruna.R.layout;
import org.rlnieto.rutasCoruna.core.Updater;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class ActividadPrincipal extends Activity{

	private Context contexto = null;
	private Button btnRutas = null;
	private Button btnConfiguracion = null;
	private Button btnSalir = null;
	private Button btnAcercaDe = null;
		
	@Override
	//-----------------------------------------------------------------------------------
	// Punto de entrada a la aplicación
	//-----------------------------------------------------------------------------------
	public void onCreate(Bundle savedInstanceState){
        
		// Comprobamos si hay que actualizar la bd
		Updater updater = new Updater(this);
		updater.actualizarBd();
		
		super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_principal);

    	//Toast.makeText(getBaseContext(), "Thread activado", Toast.LENGTH_LONG).show();

        btnRutas = (Button)findViewById(R.id.BtnRutas);
        btnConfiguracion = (Button)findViewById(R.id.BtnConfiguracion);
        btnSalir = (Button)findViewById(R.id.BtnSalir);
        btnAcercaDe = (Button)findViewById(R.id.BtnAcercaDe);
        
        
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
        btnRutas.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

		    	Intent myIntent = new Intent(contexto, ActividadRutas.class);
//		    	Intent myIntent = new Intent(contexto, PantallaMapa.class);
				contexto.startActivity(myIntent);
			}
		});
        
        
        /**
         * Diálogo con datos de la empresa y accesos a facebook y twitter
         * 
         */
        btnAcercaDe.setOnClickListener(new OnClickListener() {
        	@Override
        	public void onClick(View arg0) {
        		Intent myIntent = new Intent(contexto, ActividadAcercaDe.class);
        		contexto.startActivity(myIntent);
        	
        	}
        	
        });
        
        /**
         * Cierra la aplicación
         *         
         */
        btnSalir.setOnClickListener(new OnClickListener(){
        	public void onClick(View arg0){
        		finish();
        	}
        	
        });
        
        
        
        /**
         * Abre la pantalla de configuración
         * 
         */
        btnConfiguracion.setOnClickListener(new OnClickListener(){
        	
        	public void onClick(View arg0){
        		Intent myIntent = new Intent(contexto, ActividadRutas.class);
        		contexto.startActivity(myIntent);
        	}
        });
        
        
	}        
}