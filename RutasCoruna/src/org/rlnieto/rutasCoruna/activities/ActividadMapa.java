package org.rlnieto.rutasCoruna.activities;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;

//import com.google.android.maps.OverlayItem;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
//import android.location.LocationProvider;

import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

//import android.database.sqlite.SQLiteDatabase;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.database.Cursor;

//import android.os.Environment;
import android.util.Log;


//import java.io.File;
//import java.io.InputStream;
//import java.io.OutputStreamWriter;
//import java.io.FileOutputStream;

import org.rlnieto.rutasCoruna.R;
import org.rlnieto.rutasCoruna.R.drawable;
import org.rlnieto.rutasCoruna.R.id;
import org.rlnieto.rutasCoruna.R.layout;
import org.rlnieto.rutasCoruna.core.DatabaseHelper;
import org.rlnieto.rutasCoruna.overlays.*;



public class ActividadMapa extends MapActivity implements LocationListener {

	private MapView mapa = null;
	private ImageButton btnSatelite = null;
	private ImageButton btnCentrar = null;
	private ImageButton btnRestaurantes = null;
	private ImageButton btnCopas = null;

	private MapController controlMapa = null;

	private LocationManager locationManager = null;

	private MyItemizedBalloonOverlay overlayRestaurantes = null;
	private MyItemizedBalloonOverlay overlayPubs = null;


	//	private static final int CODIGO_RUTA_SACRA = 1;
	//	private static final int CODIGO_RUTA_COMPLETA = 1;


	@Override
	//-----------------------------------------------------------------------------------
	// Punto de entrada a la aplicación
	//-----------------------------------------------------------------------------------
	public void onCreate(Bundle savedInstanceState){

		super.onCreate(savedInstanceState);
		setContentView(R.layout.pantalla_mapa);

		// Obtenemos una referencia a los controles desde el fichero de recursos
		mapa = (MapView)findViewById(R.id.mapa);
		//		btnSatelite = (ImageButton)findViewById(R.id.BtnSatelite);
		btnCentrar = (ImageButton)findViewById(R.id.BtnCentrar);
		btnRestaurantes = (ImageButton)findViewById(R.id.BtnRestaurantes);
		btnCopas = (ImageButton)findViewById(R.id.BtnCopas);

		// Cargamos una referencia al controlador del mapa
		controlMapa = mapa.getController();

		// Mostramos los controles de zoom sobre el mapa
		mapa.setBuiltInZoomControls(true);

		centrarMapa();        


		// Cargamos la ruta elegida en la actividad anterior
		Bundle bundle = getIntent().getExtras();
		mostrarPuntosDeInteres(mapa, bundle.getInt("idRuta"));


		// Añadimos el overlay que muestra nuestra posicion 
		MyLocationOverlay locationOverlay = new MyLocationOverlay(this, mapa);		
		mapa.getOverlays().add(locationOverlay);
		locationOverlay.enableCompass();
		locationOverlay.enableMyLocation();
		mapa.postInvalidate();





		// Activamos el gps y solicitamos actualizaciones periódicas de la localización
		locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

		if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			//        	updateLocation(locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 50, this);	

		} else {
			//Toast.makeText(getBaseContext(), "El GPS está desactivado", Toast.LENGTH_LONG).show();
			
			
			// preguntamos al usuario si quiere activar el gps y si quiere abrimos el diálogo de configuración
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("El GPS está desactivado").setMessage(
					"¿Desea activar el GPS ahora? Si decide no hacerlo podrá seguir utilizando la aplicación, pero no se mostrará su posición actual")
					.setCancelable(true)
					.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							startActivity(
									new Intent(Settings.ACTION_SECURITY_SETTINGS));
						}
					})
					.setNegativeButton("No",
							new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							dialog.cancel();
							//finish();
						}
					});
			AlertDialog alert = builder.create();
			alert.show();

			
			
			
			
		}


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

		/**
		 * Conmuta entre el modo mapa y el modo satélite
		 * 
		 */
		/*		btnSatelite.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(mapa.isSatellite())
					mapa.setSatellite(false);
				else
					mapa.setSatellite(true);
			}
		});
		 */


		/**
		 * Centra el mapa en el origen de coordenadas
		 * 
		 */
		btnCentrar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				centrarMapa();
			}
		});


		/**
		 * Manejador de evento click para el botón que muestra los restaurantes
		 * 
		 */
		btnRestaurantes.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0){
				mostrarRestaurantes(mapa);
			}

		});


		/**
		 * Manejador de evento click para el botón que muestra los sitios de copas
		 * 
		 */
		btnCopas.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0){
				mostrarPubs(mapa);
			}

		});



	}   // onCreate



	//--------------------------------------------------------------------------
	// Centra el mapa con animación
	//--------------------------------------------------------------------------
	private void centrarMapa(){

		Double latitud = 43.371334*1E6;
		Double longitud = -8.396001*1E6;

		GeoPoint loc = new GeoPoint(latitud.intValue(), longitud.intValue());

		controlMapa.animateTo(loc);
		controlMapa.setZoom(15);

		/*		int zoomActual = mapa.getZoomLevel();
		for(int i=zoomActual; i<14; i++)
		{
			controlMapa.zoomIn();
		}*/

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

	//--------------------------------------------------------------------------------------------------
	// Cuando se activa el proveedor de localización
	//--------------------------------------------------------------------------------------------------
	@Override
	public void onProviderEnabled(String provider) {
		Toast.makeText(this, "GPS activado", Toast.LENGTH_SHORT).show();

	}


	//--------------------------------------------------------------------------------------------------
	// Si se pierde la señal de gps muestra un dialogo con las opciones de localizacion disponibles
	//--------------------------------------------------------------------------------------------------
	@Override
	public void onProviderDisabled(String provider) { 
		Toast.makeText(this, "GPS desactivado", Toast.LENGTH_SHORT).show();

		locationManager.removeUpdates(this);

	}

	//--------------------------------------------------------------------------------------------------
	// Actualiza la localización cuando nos movemos
	//--------------------------------------------------------------------------------------------------
	@Override
	public void onLocationChanged(Location location) {
		//updateLocation(location);

		mapa.invalidate();		

	}

	@Override
	//--------------------------------------------------------------------------------------------------
	// Cuando cambia el estado del proveedor de los servicios de localización
	//--------------------------------------------------------------------------------------------------
	public void onStatusChanged(String provider, int status, Bundle extras) {

	}	




	/**
	 * mostrarPubs
	 * 
	 * Carga desde la base de datos los pois correspondientes a los pubs y los muestra
	 * en un nuevo overlay 
	 * 
	 * @param mapa => MapView que vamos a utilizar para mostrar los pois
	 * @param codigoRuta => clave de la ruta en la bd
	 * 
	 * TODO: fusionar este método y el de búsqueda de restaurantes para tener sólo uno que 
	 * haga las búsquedas de "lugares comerciales" pasándole el código de lugar y ¿el overlay 
	 * en que mostrarlos?
	 * 
	 */
	protected void mostrarPubs(MapView mapa){

		Drawable marker = getResources().getDrawable(R.drawable.marcador_google_maps);
		int markerWidth = marker.getIntrinsicWidth();
		int markerHeight = marker.getIntrinsicHeight();

		MapController mapController = mapa.getController();
		GeoPoint point = null;

		// Abrimos la bd
		DatabaseHelper dbh = new DatabaseHelper(this);

		try {
			dbh.openDataBase();

		}catch(SQLException sqle){throw sqle;}


		// Recuperamos los restaurantes
		Cursor c = dbh.recuperarPubs();

		marker.setBounds(0, 0, markerHeight, markerWidth);
		//MyItemizedOverlay myItemizedOverlay = new MyItemizedOverlay(marker, Main.this);
		
		// Si no existe el overlay con los pubs => lo creamos. Si ya existe lo borramos.
		if(overlayPubs == null){
			
			btnCopas.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_bar_seleccionado));

			overlayPubs = new MyItemizedBalloonOverlay(marker, mapa);
			mapa.getOverlays().add(overlayPubs);

			// Recorremos el cursor añadiendo marcadores al mapa
			while(c.moveToNext()){
				int clavePoi = c.getInt(c.getColumnIndex("_id"));
				Double latitud = c.getDouble(c.getColumnIndex("latitud"))*1E6;
				Double longitud = c.getDouble(c.getColumnIndex("longitud"))*1E6;
				String nombrePoi = c.getString(c.getColumnIndex("nombrePoi"));
				String datosPoi = c.getString(c.getColumnIndex("descPoi"));
				int categoria =  c.getInt(c.getColumnIndex("categoria"));
				
				marker = getResources().getDrawable(R.drawable.ic_bar);

				markerWidth = marker.getIntrinsicWidth();
				markerHeight = marker.getIntrinsicHeight();
				marker.setBounds(0, 0, markerHeight, markerWidth);

				point = new GeoPoint(latitud.intValue(), longitud.intValue());
				overlayPubs.addItem(point, nombrePoi, datosPoi, marker, clavePoi, categoria);

			}

			c.close();
			dbh.close();

		}else{   // el overlay ya existe => lo borramos
			btnCopas.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_bar));

			overlayPubs.hideAllBalloons();
			mapa.getOverlays().remove(overlayPubs);    
			overlayPubs = null;
		}

		mapa.invalidate();   // forzamos que el mapa se redibuje
	}


	/**
	 * mostrarRestaurantes
	 * 
	 * Carga desde la base de datos los pois correspondientes a los restaurantes y los muestra
	 * en un nuevo overlay 
	 * 
	 * @param mapa => MapView que vamos a utilizar para mostrar los pois
	 * @param codigoRuta => clave de la ruta en la bd
	 * 
	 */
	protected void mostrarRestaurantes(MapView mapa){

		Drawable marker = getResources().getDrawable(R.drawable.marcador_google_maps);
		int markerWidth = marker.getIntrinsicWidth();
		int markerHeight = marker.getIntrinsicHeight();

		MapController mapController = mapa.getController();
		GeoPoint point = null;

		// Abrimos la bd
		DatabaseHelper dbh = new DatabaseHelper(this);

		try {
			dbh.openDataBase();

		}catch(SQLException sqle){
			throw sqle;
//			Toast.makeText(this, "Error al abrir la bd?¿?", Toast.LENGTH_SHORT).show();
		}


		// Recuperamos los restaurantes
		Cursor c = dbh.recuperarRestaurantes();

		marker.setBounds(0, 0, markerHeight, markerWidth);
		//MyItemizedOverlay myItemizedOverlay = new MyItemizedOverlay(marker, Main.this);
		if(overlayRestaurantes == null){

			btnRestaurantes.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_restauracion_seleccionado));

			overlayRestaurantes = new MyItemizedBalloonOverlay(marker, mapa);
			mapa.getOverlays().add(overlayRestaurantes);

			// Recorremos el cursor añadiendo marcadores al mapa
			while(c.moveToNext()){
				int clavePoi = c.getInt(c.getColumnIndex("_id"));
				Double latitud = c.getDouble(c.getColumnIndex("latitud"))*1E6;
				Double longitud = c.getDouble(c.getColumnIndex("longitud"))*1E6;
				String nombrePoi = c.getString(c.getColumnIndex("nombrePoi"));
				String datosPoi = c.getString(c.getColumnIndex("descPoi"));
				int categoria =  c.getInt(c.getColumnIndex("categoria"));

				marker = getResources().getDrawable(R.drawable.ic_restauracion);

				markerWidth = marker.getIntrinsicWidth();
				markerHeight = marker.getIntrinsicHeight();
				marker.setBounds(0, 0, markerHeight, markerWidth);

				point = new GeoPoint(latitud.intValue(), longitud.intValue());
				overlayRestaurantes.addItem(point, nombrePoi, datosPoi, marker, clavePoi, categoria);

			}

			c.close();
			dbh.close();


		}else{   // el overlay ya existe => lo borramos
			btnRestaurantes.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_restauracion));

			overlayRestaurantes.hideAllBalloons();
			mapa.getOverlays().remove(overlayRestaurantes);    
			overlayRestaurantes = null;
		}

		mapa.invalidate();   // forzamos que el mapa se redibuje
	}



	/**
	 * mostrarPuntosDeInteres
	 * 
	 * Carga desde la base de datos los pois correspondientes al código de ruta que se le pasa
	 * como parámetro y los muestra en el mapa. Se utiliza un ItemizedBalloonOverlay para que 
	 * los textos aparezcan de manera similar a gmaps en web.
	 * 
	 * @param mapa => MapView que vamos a utilizar para mostrar los pois
	 * @param codigoRuta => clave de la ruta en la bd
	 * 
	 */
	protected void mostrarPuntosDeInteres(MapView mapa, int codigoRuta){

		Drawable marker = getResources().getDrawable(R.drawable.marcador_google_maps);
		int markerWidth = marker.getIntrinsicWidth();
		int markerHeight = marker.getIntrinsicHeight();

		MapController mapController = mapa.getController();
		GeoPoint point = null;

		// Abrimos la bd
		DatabaseHelper dbh = new DatabaseHelper(this);

		try {
			dbh.openDataBase();

		}catch(SQLException sqle){throw sqle;}


		// Tenemos la bd disponible, recuperamos los pois de la ruta. Le pasamos el _id
		// de la tabla ruta
		Cursor c = dbh.recuperarRuta(codigoRuta);

		marker.setBounds(0, 0, markerHeight, markerWidth);
		//MyItemizedOverlay myItemizedOverlay = new MyItemizedOverlay(marker, Main.this);
		MyItemizedBalloonOverlay myItemizedBalloonOverlay = new MyItemizedBalloonOverlay(marker, mapa);

		mapa.getOverlays().clear();   // borramos los overlays para limpiar el mapa
		mapa.getOverlays().add(myItemizedBalloonOverlay);

		// Recorremos el cursor añadiendo marcadores al mapa
		while(c.moveToNext()){
			int clavePoi = c.getInt(c.getColumnIndex("_id"));
			Double latitud = c.getDouble(c.getColumnIndex("latitud"))*1E6;
			Double longitud = c.getDouble(c.getColumnIndex("longitud"))*1E6;
			String nombrePoi = c.getString(c.getColumnIndex("nombrePoi"));
			String datosPoi = c.getString(c.getColumnIndex("descPoi"));
			int categoria = c.getInt(c.getColumnIndex("categoria"));

			//TODO: asignar el icono al marker de una manera más limpia y a través de la tabla "categoria", 
			// que es la que contiene el nombre del icono ¿lo hacemos en otra clase?
			switch(categoria){
			case 1: marker = getResources().getDrawable(R.drawable.ic_modernismo);   	// ruta modernista
			break;
			case 2: marker = getResources().getDrawable(R.drawable.ic_picasso);		// picasso
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
			case 100: marker = getResources().getDrawable(R.drawable.ic_dormir_mapa);	
			break;
			default: marker = getResources().getDrawable(R.drawable.ic_historica);	// no hay coincidencia
			break;
			}

			markerWidth = marker.getIntrinsicWidth();
			markerHeight = marker.getIntrinsicHeight();
			marker.setBounds(0, 0, markerHeight, markerWidth);

			point = new GeoPoint(latitud.intValue(), longitud.intValue());
			myItemizedBalloonOverlay.addItem(point, nombrePoi, datosPoi, marker, clavePoi, categoria);


		}

		c.close();
		dbh.close();


		mapController.animateTo(point);        
		mapController.setZoom(15);

		mapa.invalidate();   // forzamos que el mapa se redibuje

		centrarMapa();

	}


	/*-------------------------------------------------------------------------------------------------------------*/
	/*-------------------------------------------------------------------------------------------------------------*/


	private Dialog crearDialogoAlerta(String mensajeAMostrar)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(mensajeAMostrar)
		.setCancelable(false)
		.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				ActividadMapa.this.finish();
			}
		})
		.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});
		AlertDialog alert = builder.create();

		return alert;


	}

	/**
	 * Desactivamos las notificaciones del location manager cuando cierran la ventana
	 * 
	 */
	public void onPause(){
		super.onPause();
		locationManager.removeUpdates(this);			
	}


}
