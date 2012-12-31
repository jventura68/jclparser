package org.rlnieto.rutasCoruna.activities;

import org.rlnieto.rutasCoruna.R;

import android.app.Activity;
import android.os.Bundle;


public class ActividadAcercaDe extends Activity{
	
	@Override
	//-----------------------------------------------------------------------------------
	// Punto de entrada a la aplicación
	//-----------------------------------------------------------------------------------
	public void onCreate(Bundle savedInstanceState){
        
		super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_acerca_de);

	}

}
