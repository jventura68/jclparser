package org.rlnieto.rutasCoruna.core;

import org.rlnieto.rutasCoruna.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class Example extends Activity{
	
	@Override
	//-----------------------------------------------------------------------------------
	// Punto de entrada a la aplicación
	//-----------------------------------------------------------------------------------
	public void onCreate(Bundle savedInstanceState){
        
		super.onCreate(savedInstanceState);
        setContentView(R.layout.design);

	}

}
