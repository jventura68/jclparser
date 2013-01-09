package org.rlnieto.rutasCoruna.activities;

import org.rlnieto.rutasCoruna.R;
import org.rlnieto.rutasCoruna.core.DatabaseHelper;
import org.rlnieto.rutasCoruna.core.Updater;
import org.rlnieto.rutasCoruna.utils.Constantes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class ActividadPrincipal extends Activity{

	private Context contexto = null;
	private Button btnRutas = null;
	private Button btnRestaurantes = null;
	private Button btnHoteles = null;
	private Button btnAcercaDe = null;
	
	private Vibrator vibrador = null;
		
	
	@Override
	//-----------------------------------------------------------------------------------
	// Punto de entrada a la aplicación
	//-----------------------------------------------------------------------------------
	public void onCreate(Bundle savedInstanceState){
    
        contexto = this;
		vibrador = (Vibrator) contexto.getSystemService(Context.VIBRATOR_SERVICE) ;
		
		// Comprobamos si hay que actualizar la bd
		Updater updater = new Updater(this);
		updater.actualizarBd();
		
		super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_principal);

    	//Toast.makeText(getBaseContext(), "Thread activado", Toast.LENGTH_LONG).show();

        btnRutas = (Button)findViewById(R.id.BtnRutas);
        btnRestaurantes = (Button)findViewById(R.id.BtnRestaurantes);
        btnHoteles = (Button)findViewById(R.id.BtnHoteles);
        btnAcercaDe = (Button)findViewById(R.id.BtnAcercaDe);

        btnRutas.getBackground().setAlpha(185);
        btnRestaurantes.getBackground().setAlpha(185);
        btnHoteles.getBackground().setAlpha(185);
        btnAcercaDe.getBackground().setAlpha(185);
        
        // Asignamos una imagen de fondo a la actividad
    	Integer fondos[] = {R.drawable.rosa, R.drawable.cruz, R.drawable.surf, 
    			R.drawable.torre, R.drawable.torrecontrol2, R.drawable.mercado};
        
    	int fondoAleatorio = (int)Math.floor(Math.random() * fondos.length);
    	this.getWindow().setBackgroundDrawableResource(fondos[fondoAleatorio]);
        
        /**-------------------------------------------------------------------------
         * 
         * Listeners
         * 
         * 
         *---------------------------------------------------------------------------
         **/
        
        /**
         * Muestra la pantalla con la lista de las rutas disponiblesAbre la actividad del mapa
         * 
         */
        btnRutas.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				vibrador.vibrate(Constantes.TIEMPO_VIBRACION);
		    	Intent myIntent = new Intent(contexto, ActividadListaRutas.class);
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
        		vibrador.vibrate(Constantes.TIEMPO_VIBRACION);
//       	       	Intent myIntent = new Intent("android.intent.action.VIEW", Uri.parse(Constantes.URL_EMPRESA));
        		Intent myIntent = new Intent(contexto, ActividadAcercaDe.class);
       	       	contexto.startActivity(myIntent);
        	
        	}
        	
        });
        
        /**
         * Muestra la lista de los hoteles
         *         
         */
        btnHoteles.setOnClickListener(new OnClickListener(){
        	public void onClick(View arg0){
//        		Toast.makeText(getBaseContext(), "En construcción!!", Toast.LENGTH_SHORT).show();

				vibrador.vibrate(Constantes.TIEMPO_VIBRACION);
        		Intent myIntent = new Intent(contexto, ActividadListaHoteles.class);
        		myIntent.putExtra("idRuta", DatabaseHelper.LISTA_HOTELES);
        		contexto.startActivity(myIntent);
        		
        	}
        	
        });
        
        
        
        /**
         * Abre la lista de los restaurantes
         * 
         */
        btnRestaurantes.setOnClickListener(new OnClickListener(){
        	
        	public void onClick(View arg0){
        		
				vibrador.vibrate(Constantes.TIEMPO_VIBRACION);
        		Intent myIntent = new Intent(contexto, ActividadMapa.class);
        		myIntent.putExtra("idRuta", DatabaseHelper.LISTA_RESTAURANTES);
        		contexto.startActivity(myIntent);
        	}
        });
        
        
	}        
}