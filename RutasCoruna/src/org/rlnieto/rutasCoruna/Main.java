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



public class Main extends MapActivity implements LocationListener{

	private MapView mapa = null;
	private Button btnSatelite = null;
	private Button btnCentrar = null;
	private Button btnRuta1 = null;
	private Button btnRuta2 = null;
	private MapController controlMapa = null;

	private LocationManager locationManager = null;
	
	private static final int CODIGO_RUTA_SACRA = 1;
	private static final int CODIGO_RUTA_COMPLETA = 1;
	
	
	@Override
	//-----------------------------------------------------------------------------------
	// Punto de entrada a la aplicación
	//-----------------------------------------------------------------------------------
	public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Obtenemos una referencia a los controles desde el fichero de recursos
        mapa = (MapView)findViewById(R.id.mapa);
        btnSatelite = (Button)findViewById(R.id.BtnSatelite);
        btnCentrar = (Button)findViewById(R.id.BtnCentrar);
        btnRuta1 = (Button)findViewById(R.id.BtnRuta1);
        btnRuta2 = (Button)findViewById(R.id.BtnRuta2);
        
        // Cargamos una referencia al controlador del mapa
        controlMapa = mapa.getController();

        // Mostramos los controles de zoom sobre el mapa
        mapa.setBuiltInZoomControls(true);
        
        centrarMapa();        


        // Activamos el gps y solicitamos actualizaciones periódicas de la localización
//        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        
//        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//        	updateLocation(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 50, this);	
//			
//		} else {
//            Toast.makeText(getBaseContext(), "El GPS está desactivado", Toast.LENGTH_LONG).show();
//		}
        
        
        //        updateLocation(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));
        //        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 50, this);
        
        // Copiamos los ficheros html con las rutas a la sd
//        Updater uh = new Updater(this);
//        uh.copiarHtmlTarjetaSD(this, "web");
        
        
		//--------------------------------------------------------------------------
		//
		// listeners
		//
		//--------------------------------------------------------------------------
        
		//--------------------------------------------------------------------------
		// Pasa a modo satelite
		//--------------------------------------------------------------------------
		btnSatelite.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(mapa.isSatellite())
					mapa.setSatellite(false);
				else
					mapa.setSatellite(true);
			}
		});
        
		

		//--------------------------------------------------------------------------
		// Centra el mapa
		//--------------------------------------------------------------------------
        btnCentrar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				centrarMapa();
			}
		});

        
		//--------------------------------------------------------------------------
		// Centra el mapa con animación
        //--------------------------------------------------------------------------
        btnRuta1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
		        mostrarPuntosDeInteres(mapa, 1);
			}
		});
        

		//--------------------------------------------------------------------------
        // Mueve un poco el mapa
        //--------------------------------------------------------------------------
        btnRuta2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
		        mostrarPuntosDeInteres(mapa, 2);
			}
		});
    
	}   // onCreate
        
	
	
	//--------------------------------------------------------------------------
	// Centra el mapa con animación
    //--------------------------------------------------------------------------
    private void centrarMapa(){

		Double latitud = 43.371334*1E6;
		Double longitud = -8.396001*1E6;
		
		GeoPoint loc = 
			new GeoPoint(latitud.intValue(), longitud.intValue());
		
		controlMapa.animateTo(loc);
		
		int zoomActual = mapa.getZoomLevel();
		for(int i=zoomActual; i<14; i++)
		{
			controlMapa.zoomIn();
		}

    }
    

	
	//--------------------------------------------------------------------------
	// Al heredar de mapActivity hay que implementar el método isRouteDisplayed 
	//
	//--------------------------------------------------------------------------

	@Override
	//--------------------------------------------------------------------------
    // Se muestra o no la ruta (exige implementarlo)
    //--------------------------------------------------------------------------
    protected boolean isRouteDisplayed() {
    	return false;   // TODO: revisar la licencia, parece que no se puede usar la guía por voz
    }
        

	//--------------------------------------------------------------------------
	// Al implementar la interface locationListener hay que declarar los 
	// siguientes métodos:
	// 	- onProviderEnabled
	//	- onProviderDisabled
	//  - onStatusChanged
	//  - onLocationChanged
	//
	//--------------------------------------------------------------------------

	@Override
	//--------------------------------------------------------------------------------------------------
    // Cuando se activa el proveedor de localización
    //--------------------------------------------------------------------------------------------------
	public void onProviderEnabled(String provider) {
		Toast.makeText(this, "GPS activado", Toast.LENGTH_SHORT).show();
	}
	

	@Override
	//--------------------------------------------------------------------------------------------------
	// Si se pierde la señal de gps muestra un dialogo con las opciones de localizacion disponibles
	//--------------------------------------------------------------------------------------------------
	public void onProviderDisabled(String provider) { 
		Toast.makeText(this, "GPS desactivado", Toast.LENGTH_SHORT).show();

		//Intent intent = new Intent( android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		//startActivity(intent);
		
		locationManager.removeUpdates(this);
		
	}
	
	@Override
	//--------------------------------------------------------------------------------------------------
    // Actualiza la localización cuando nos movemos
    //--------------------------------------------------------------------------------------------------
	public void onLocationChanged(Location location) {
		updateLocation(location);
	}
	
	@Override
	//--------------------------------------------------------------------------------------------------
    // Cuando cambia el estado del proveedor de los servicios de localización
    //--------------------------------------------------------------------------------------------------
	public void onStatusChanged(String provider, int status, Bundle extras) {
		
	}	

	
	//--------------------------------------------------------------------------------------------------
    // Muestra los puntos turísticos
    //--------------------------------------------------------------------------------------------------
	protected void mostrarPuntosDeInteres(MapView mapa, int codigoRuta){
        
        Drawable marker = getResources().getDrawable(R.drawable.marcador_google_maps);
        int markerWidth = marker.getIntrinsicWidth();
        int markerHeight = marker.getIntrinsicHeight();

        MapController mapController = mapa.getController();
        GeoPoint point = null;
        
		// Copiamos la bd al dispositivo si no existe y la abrimos
		DatabaseHelper dbh = new DatabaseHelper(this);
		
		try{
			dbh.createDataBase();
		}catch (IOException ioe) {throw new Error("No se pudo crear la base de datos");}
		
		try {
	 		dbh.openDataBase();
	 
	 	}catch(SQLException sqle){throw sqle;}
		
		
		// Tenemos la bd disponible, recuperamos los pois de la ruta. Le pasamos el _id
		// de la tabla ruta
		Cursor c = dbh.recuperarRuta(codigoRuta);

		marker.setBounds(0, 0, markerHeight, markerWidth);
        MyItemizedOverlay myItemizedOverlay = new MyItemizedOverlay(marker, Main.this);
        mapa.getOverlays().clear();   // borramos los overlays para limpiar el mapa
        mapa.getOverlays().add(myItemizedOverlay);

        // Recorremos el cursor añadiendo marcadores al mapa
        while(c.moveToNext()){
        	Double latitud = c.getDouble(c.getColumnIndex("latitud"))*1E6;
        	Double longitud = c.getDouble(c.getColumnIndex("longitud"))*1E6;
        	String nombrePoi = c.getString(c.getColumnIndex("nombrePoi"));
        	String datosPoi = c.getString(c.getColumnIndex("descPoi"));
        	int iconoPoi = c.getInt(c.getColumnIndex("categoria"));

        	Log.v("Icono poi", String.valueOf(iconoPoi));
        	
        	
        	//TODO: asignar el icono al marker de una manera más limpia y a través de la tabla "categoria", 
        	// que es la que contiene el nombre del icono ¿lo hacemos en otra clase?
        	switch(iconoPoi){
        		case 1: marker = getResources().getDrawable(R.drawable.red_pushpin);   // general
        				break;
        		case 2: marker = getResources().getDrawable(R.drawable.hiker);		 	// senderismo
        				break;
        		case 3: marker = getResources().getDrawable(R.drawable.cycling);		// ruta en bicicleta
				 		break;
        		case 4: marker = getResources().getDrawable(R.drawable.camera);			// paisaje
				 		break;
        		case 5: marker = getResources().getDrawable(R.drawable.red_pushpin);	// pubs
				 		break;
        		case 6: marker = getResources().getDrawable(R.drawable.red_pushpin);  	// restaurantes
        				break;
        		case 7: marker = getResources().getDrawable(R.drawable.red_pushpin);	// shopping
				 		break;
        		case 8: marker = getResources().getDrawable(R.drawable.red_pushpin);	// monumento
				 		break;
				 default: marker = getResources().getDrawable(R.drawable.marcador_google_maps);	// no hay coincidencia
				 		break;
        	}

        	markerWidth = marker.getIntrinsicWidth();
            markerHeight = marker.getIntrinsicHeight();
        	marker.setBounds(0, 0, markerHeight, markerWidth);
        	
        	point = new GeoPoint(latitud.intValue(), longitud.intValue());
            myItemizedOverlay.addItem(point, nombrePoi, datosPoi, marker);


        }
		
        c.close();
        dbh.close();
                
        mapController.animateTo(point);        
        mapController.setZoom(15);
        
        mapa.invalidate();   // forzamos que el mapa se redibuje
        
        centrarMapa();
        
	}

	
	//--------------------------------------------------------------------------------------------------
	// Actualiza la localización actual
	//--------------------------------------------------------------------------------------------------
	protected void updateLocation(Location location){
		MapView mapView = (MapView) findViewById(R.id.mapa);
        MapController mapController = mapView.getController();
        GeoPoint point = new GeoPoint((int) (location.getLatitude() * 1E6), (int) (location.getLongitude() * 1E6));
        mapController.animateTo(point);        
        mapController.setZoom(15);
        
        Geocoder geoCoder = new Geocoder(this, Locale.getDefault());

        try {
            List<Address> addresses = geoCoder.getFromLocation(
                point.getLatitudeE6()  / 1E6, 
                point.getLongitudeE6() / 1E6, 1);

            String address = "";
            if (addresses.size() > 0) {
                for (int i = 0; i < addresses.get(0).getMaxAddressLineIndex(); i++)
                   address += addresses.get(0).getAddressLine(i) + "\n";
            }

            Toast.makeText(getBaseContext(), address, Toast.LENGTH_SHORT).show();
        }
        catch (IOException e) {                
            e.printStackTrace();
        }           

        
        List<Overlay> mapOverlays = mapView.getOverlays();
        MyOverlay marker = new MyOverlay(point);
        mapOverlays.add(marker);  
        mapView.invalidate();		
	}


	//--------------------------------------------------------------------------------------------------
	// Copia los fichero html con los datos de los poi a la tarjeta SD
	//--------------------------------------------------------------------------------------------------
	
	
}

