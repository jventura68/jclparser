package org.rlnieto.rutasCoruna;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;

import android.graphics.drawable.Drawable;
import android.widget.Toast;

import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;
import android.database.Cursor;

import android.os.Environment;
import android.util.Log;


import java.io.File;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;



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