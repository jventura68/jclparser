package org.rlnieto.rutasCoruna;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
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

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

public class RutasCorunaActivity extends MapActivity implements LocationListener{

	private MapView mapa = null;
	private Button btnSatelite = null;
	private Button btnCentrar = null;
	private Button btnAnimar = null;
	private Button btnMover = null;
	private MapController controlMapa = null;
	
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
        btnAnimar = (Button)findViewById(R.id.BtnAnimar);
        btnMover = (Button)findViewById(R.id.BtnMover);
        
        // Cargamos una referencia al controlador del mapa
        controlMapa = mapa.getController();

        // Mostramos los controles de zoom sobre el mapa
        mapa.setBuiltInZoomControls(true);
        
		//Añadimos el overlay
		List<Overlay> capas = mapa.getOverlays();
		OverlayMapa om = new OverlayMapa();
		capas.add(om);
		mapa.postInvalidate();

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
				//Double latitud = 37.40*1E6;
				//Double longitud = -5.99*1E6;

				Double latitud = 43.371334*1E6;
				Double longitud = -8.396001*1E6;

				GeoPoint loc = 
					new GeoPoint(latitud.intValue(), longitud.intValue());
				
				controlMapa.setCenter(loc);
				controlMapa.setZoom(16);
			}
		});
        

		//--------------------------------------------------------------------------
		// Centra el mapa con animación
        //--------------------------------------------------------------------------
        btnAnimar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//Double latitud = 37.40*1E6;
				//Double longitud = -5.99*1E6;

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
		});
        

		//--------------------------------------------------------------------------
        // Mueve un poco el mapa
        //--------------------------------------------------------------------------
        btnMover.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				controlMapa.scrollBy(40, 40);
			}
		});
    
	}   // onCreate
        

	
	//--------------------------------------------------------------------------
	// Al heredar de mapActivity hay que implementar el método isRouteDisplayed 
	//
	//--------------------------------------------------------------------------

	@Override
	//--------------------------------------------------------------------------
    // Se muestra o no la ruta (exige implementarlo)
    //--------------------------------------------------------------------------
    protected boolean isRouteDisplayed() {
    	return true;
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
	public void onProviderEnabled(String provider) {}
	

	@Override
	//--------------------------------------------------------------------------------------------------
	// Si se pierde la señal de gps muestra un dialogo con las opciones de localizacion disponibles
	//--------------------------------------------------------------------------------------------------
	public void onProviderDisabled(String provider) { 
		Intent intent = new Intent( android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
		startActivity(intent);
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
	public void onStatusChanged(String provider, int status, Bundle extras) {}	

    
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
	
}