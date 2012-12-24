package org.rlnieto.rutasCoruna.activities;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

public class ActividadMapa extends MapActivity {

	private MapView mapa = null;
	private Context contexto = null;

	private MapController controlMapa = null;

	private LocationManager locationManager = null;

	private MyItemizedBalloonOverlay overlayRestaurantes = null;
	private MyItemizedBalloonOverlay overlayPubs = null;
	private Menu menuActivity = null;
	private boolean gpsActivo = false;
	private boolean modoSatelite = false;

	// Los valores por defecto para latitud y longitud son aproximadamente el
	// centro de Coruña
	private Double latitudCentrarRuta = 43.371334 * 1E6;
	private Double longitudCentrarRuta = -8.396001 * 1E6;
	private int nivelZoomRuta = 15;

	// private static final int CODIGO_RUTA_SACRA = 1;
	// private static final int CODIGO_RUTA_COMPLETA = 1;

	@Override
	// -----------------------------------------------------------------------------------
	// Punto de entrada a la aplicación
	// -----------------------------------------------------------------------------------
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.pantalla_mapa);
		contexto = this;

		// Obtenemos una referencia a los controles desde el fichero de recursos
		mapa = (MapView) findViewById(R.id.mapa);
		// btnSatelite = (ImageButton)findViewById(R.id.BtnSatelite);

		// Cargamos una referencia al controlador del mapa
		controlMapa = mapa.getController();
		mapa.setSatellite(false);

		// Mostramos los controles de zoom sobre el mapa
		mapa.setBuiltInZoomControls(true);

		// Cargamos la ruta elegida en la actividad anterior
		Bundle bundle = getIntent().getExtras();
		mostrarPuntosDeInteres(mapa, bundle.getInt("idRuta"));

		obtenerLatitudLongitudCentrarMapa(bundle.getInt("idRuta"));
		centrarMapa();

		
		// Añadimos el overlay que muestra nuestra posicion
		MyLocationOverlay locationOverlay = new MyLocationOverlay(this, mapa);
		mapa.getOverlays().add(locationOverlay);
		locationOverlay.enableCompass();
		locationOverlay.enableMyLocation();
		mapa.postInvalidate();

	} // onCreate

	/**
	 * Listener para el gps
	 * 
	 */
	private LocationListener providerListener = new LocationListener() {
		public void onLocationChanged(Location location) {
			Toast.makeText(contexto, "Location changed", Toast.LENGTH_SHORT)
					.show();
		}

		public void onProviderDisabled(String provider) {
			gpsActivo = false;
			// locationManager.removeUpdates(providerListener);
		}

		public void onProviderEnabled(String provider) {
			gpsActivo = true;
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
			Toast.makeText(contexto, "Status changed", Toast.LENGTH_SHORT)
					.show();
		}
	};

	// --------------------------------------------------------------------------
	// Centra el mapa con animación
	// --------------------------------------------------------------------------
	private void centrarMapa() {

		Double latitud = latitudCentrarRuta;
		Double longitud = longitudCentrarRuta;

		GeoPoint loc = new GeoPoint(latitud.intValue(), longitud.intValue());

		controlMapa.animateTo(loc);
		controlMapa.setZoom(nivelZoomRuta);

	}

	// --------------------------------------------------------------------------
	// Al heredar de mapActivity hay que implementar el método isRouteDisplayed
	//
	// --------------------------------------------------------------------------

	@Override
	// --------------------------------------------------------------------------
	// Se muestra o no la ruta (exige implementarlo)
	// --------------------------------------------------------------------------
	protected boolean isRouteDisplayed() {
		return false; // TODO: revisar la licencia, parece que no se puede usar
						// la guía por voz
	}

	/**
	 * mostrarPubs
	 * 
	 * Carga desde la base de datos los pois correspondientes a los pubs y los
	 * muestra en un nuevo overlay
	 * 
	 * @param mapa
	 *            => MapView que vamos a utilizar para mostrar los pois
	 * @param codigoRuta
	 *            => clave de la ruta en la bd
	 * 
	 *            TODO: fusionar este método y el de búsqueda de restaurantes
	 *            para tener sólo uno que haga las búsquedas de
	 *            "lugares comerciales" pasándole el código de lugar y ¿el
	 *            overlay en que mostrarlos?
	 * 
	 */
	protected void mostrarPubs(MapView mapa) {

		Drawable marker = getResources().getDrawable(R.drawable.ic_pin_bar);
		int markerWidth = marker.getIntrinsicWidth();
		int markerHeight = marker.getIntrinsicHeight();

		MapController mapController = mapa.getController();
		GeoPoint point = null;

		// Abrimos la bd
		DatabaseHelper dbh = new DatabaseHelper(this);

		try {
			dbh.openDataBase();

		} catch (SQLException sqle) {
			throw sqle;
		}

		// Recuperamos los restaurantes
		Cursor c = dbh.recuperarPubs();

		marker.setBounds(0, 0, markerHeight, markerWidth);
		// MyItemizedOverlay myItemizedOverlay = new MyItemizedOverlay(marker,
		// Main.this);

		// Si no existe el overlay con los pubs => lo creamos. Si ya existe lo
		// borramos.
		if (overlayPubs == null) {

			overlayPubs = new MyItemizedBalloonOverlay(marker, mapa);
			mapa.getOverlays().add(overlayPubs);

			// Recorremos el cursor añadiendo marcadores al mapa
			while (c.moveToNext()) {
				int clavePoi = c.getInt(c.getColumnIndex("_id"));
				Double latitud = c.getDouble(c.getColumnIndex("latitud")) * 1E6;
				Double longitud = c.getDouble(c.getColumnIndex("longitud")) * 1E6;
				String nombrePoi = c.getString(c.getColumnIndex("nombrePoi"));
				String datosPoi = c.getString(c.getColumnIndex("descPoi"));
				int categoria = c.getInt(c.getColumnIndex("categoria"));
				String direccion = c.getString(c.getColumnIndex("direccion"));
				marker = getResources().getDrawable(R.drawable.ic_bar);

				markerWidth = marker.getIntrinsicWidth();
				markerHeight = marker.getIntrinsicHeight();
				marker.setBounds(0, 0, markerHeight, markerWidth);

				point = new GeoPoint(latitud.intValue(), longitud.intValue());
				overlayPubs.addItem(point, nombrePoi, datosPoi + "\\n"
						+ direccion, marker, clavePoi, categoria);

			}

			c.close();
			dbh.close();

		} else { // el overlay ya existe => lo borramos
			overlayPubs.hideAllBalloons();
			mapa.getOverlays().remove(overlayPubs);
			overlayPubs = null;
		}

		mapa.invalidate(); // forzamos que el mapa se redibuje
	}

	/**
	 * mostrarRestaurantes
	 * 
	 * Carga desde la base de datos los pois correspondientes a los restaurantes
	 * y los muestra en un nuevo overlay
	 * 
	 * @param mapa
	 *            => MapView que vamos a utilizar para mostrar los pois
	 * @param codigoRuta
	 *            => clave de la ruta en la bd
	 * 
	 */
	protected void mostrarRestaurantes(MapView mapa) {

		Drawable marker = getResources().getDrawable(R.drawable.ic_pin_comer);
		int markerWidth = marker.getIntrinsicWidth();
		int markerHeight = marker.getIntrinsicHeight();

		MapController mapController = mapa.getController();
		GeoPoint point = null;

		// Abrimos la bd
		DatabaseHelper dbh = new DatabaseHelper(this);

		try {
			dbh.openDataBase();

		} catch (SQLException sqle) {
			throw sqle;
			// Toast.makeText(this, "Error al abrir la bd?¿?",
			// Toast.LENGTH_SHORT).show();
		}

		// Recuperamos los restaurantes
		Cursor c = dbh.recuperarRestaurantes();

		marker.setBounds(0, 0, markerHeight, markerWidth);
		// MyItemizedOverlay myItemizedOverlay = new MyItemizedOverlay(marker,
		// Main.this);
		if (overlayRestaurantes == null) {

			overlayRestaurantes = new MyItemizedBalloonOverlay(marker, mapa);
			mapa.getOverlays().add(overlayRestaurantes);

			// Recorremos el cursor añadiendo marcadores al mapa
			while (c.moveToNext()) {
				int clavePoi = c.getInt(c.getColumnIndex("_id"));
				Double latitud = c.getDouble(c.getColumnIndex("latitud")) * 1E6;
				Double longitud = c.getDouble(c.getColumnIndex("longitud")) * 1E6;
				String nombrePoi = c.getString(c.getColumnIndex("nombrePoi"));
				String datosPoi = c.getString(c.getColumnIndex("descPoi"));
				int categoria = c.getInt(c.getColumnIndex("categoria"));
				String direccion = c.getString(c.getColumnIndex("direccion"));

				marker = getResources().getDrawable(R.drawable.ic_pin_comer);

				markerWidth = marker.getIntrinsicWidth();
				markerHeight = marker.getIntrinsicHeight();
				marker.setBounds(0, 0, markerHeight, markerWidth);

				point = new GeoPoint(latitud.intValue(), longitud.intValue());
				overlayRestaurantes.addItem(point, nombrePoi + "\\n"
						+ direccion, datosPoi, marker, clavePoi, categoria);

			}

			c.close();
			dbh.close();

		} else { // el overlay ya existe => lo borramos
			overlayRestaurantes.hideAllBalloons();
			mapa.getOverlays().remove(overlayRestaurantes);
			overlayRestaurantes = null;
		}

		mapa.invalidate(); // forzamos que el mapa se redibuje
	}

	/**
	 * mostrarPuntosDeInteres
	 * 
	 * Carga desde la base de datos los pois correspondientes al código de ruta
	 * que se le pasa como parámetro y los muestra en el mapa. Se utiliza un
	 * ItemizedBalloonOverlay para que los textos aparezcan de manera similar a
	 * gmaps en web.
	 * 
	 * @param mapa
	 *            => MapView que vamos a utilizar para mostrar los pois
	 * @param codigoRuta
	 *            => clave de la ruta en la bd
	 * 
	 */
	protected void mostrarPuntosDeInteres(MapView mapa, int codigoRuta) {

		Drawable marker = getResources().getDrawable(
				R.drawable.ic_pin_historica);
		int markerWidth = marker.getIntrinsicWidth();
		int markerHeight = marker.getIntrinsicHeight();

		MapController mapController = mapa.getController();
		GeoPoint point = null;

		// Abrimos la bd
		DatabaseHelper dbh = new DatabaseHelper(this);

		try {
			dbh.openDataBase();

		} catch (SQLException sqle) {
			throw sqle;
		}

		// Tenemos la bd disponible, recuperamos los pois de la ruta. Le pasamos
		// el _id
		// de la tabla ruta
		Cursor c = dbh.recuperarPoisRuta(codigoRuta);

		marker.setBounds(0, 0, markerHeight, markerWidth);
		// MyItemizedOverlay myItemizedOverlay = new MyItemizedOverlay(marker,
		// Main.this);
		MyItemizedBalloonOverlay myItemizedBalloonOverlay = new MyItemizedBalloonOverlay(
				marker, mapa);
 
		mapa.getOverlays().clear(); // borramos los overlays para limpiar el
									// mapa
		mapa.getOverlays().add(myItemizedBalloonOverlay);

		// Recorremos el cursor añadiendo marcadores al mapa
		while (c.moveToNext()) {
			int clavePoi = c.getInt(c.getColumnIndex("_id"));
			Double latitud = c.getDouble(c.getColumnIndex("latitud")) * 1E6;
			Double longitud = c.getDouble(c.getColumnIndex("longitud")) * 1E6;
			String nombrePoi = c.getString(c.getColumnIndex("nombrePoi"));
			String datosPoi = c.getString(c.getColumnIndex("descPoi"));
			int categoria = c.getInt(c.getColumnIndex("categoria"));
			String direccion = c.getString(c.getColumnIndex("direccion"));

			// TODO: asignar el icono al marker de una manera más limpia y a
			// través de la tabla "categoria",
			// que es la que contiene el nombre del icono ¿lo hacemos en otra
			// clase?
			// Los códigos negativos son para rutas temáticas donde todos los
			// pois tienen el mismo icono
			switch (categoria) {
			case -2:
				marker = getResources().getDrawable(R.drawable.ic_picasso); // ruta
																			// picasso
				break;
			case -1:
				marker = getResources().getDrawable(R.drawable.ic_modernismo); // ruta
																				// modernista
				break;
			case 1:
				marker = getResources()
						.getDrawable(R.drawable.ic_pin_historica); // museo
				break;
			case 2:
				marker = getResources()
						.getDrawable(R.drawable.ic_pin_historica); // iglesia
				break;
			case 3:
				marker = getResources()
						.getDrawable(R.drawable.ic_pin_historica); // monumento
				break;
			case 4:
				marker = getResources()
						.getDrawable(R.drawable.ic_pin_historica); // paisaje
				break;
			case 100:
				marker = getResources().getDrawable(R.drawable.ic_pin_dormir); // hotel
				break;
			case 101:
				marker = getResources().getDrawable(R.drawable.ic_pin_comer); // restaurante
				break;
			case 102:
				marker = getResources().getDrawable(R.drawable.ic_pin_bar); // ocio
				// nocturno
				break;
			case 103:
				marker = getResources()
						.getDrawable(R.drawable.ic_llamada_verde); // centro
																	// comercial
				break;
			case 104:
				marker = getResources()
						.getDrawable(R.drawable.ic_llamada_verde); // espectáculo
				break;
			case 105:
				marker = getResources()
						.getDrawable(R.drawable.ic_llamada_verde); // cafetería
				break;
			case 106:
				marker = getResources().getDrawable(R.drawable.ic_bar); // cervecería
				break;
			default:
				marker = getResources()
						.getDrawable(R.drawable.ic_pin_historica); // no
																	// hay
																	// coincidencia
				break;
			}

			markerWidth = marker.getIntrinsicWidth();
			markerHeight = marker.getIntrinsicHeight();
			marker.setBounds(0, 0, markerHeight, markerWidth);

			point = new GeoPoint(latitud.intValue(), longitud.intValue());
			// myItemizedBalloonOverlay.addItem(point, nombrePoi + "\n" +
			// direccion, datosPoi,
			// marker, clavePoi, categoria);
			myItemizedBalloonOverlay.addItem(point, nombrePoi, direccion,
					marker, clavePoi, categoria);

		}

		c.close();
		dbh.close();

		mapController.animateTo(point);
		mapController.setZoom(15);

		mapa.invalidate(); // forzamos que el mapa se redibuje

		centrarMapa();

	}

	/*-------------------------------------------------------------------------------------------------------------*/
	/*-------------------------------------------------------------------------------------------------------------*/

	private Dialog crearDialogoAlerta(String mensajeAMostrar) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(mensajeAMostrar)
				.setCancelable(false)
				.setPositiveButton(android.R.string.yes,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								ActividadMapa.this.finish();
							}
						})
				.setNegativeButton(android.R.string.no,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});
		AlertDialog alert = builder.create();

		return alert;

	}

	/**
	 * Desactivamos las notificaciones del location manager cuando la actividad
	 * pasa a segundo plano
	 * 
	 */
	public void onStop() {
		super.onStop();
		locationManager.removeUpdates(providerListener);
	}

	/**
	 * Activamos las notificaciones del location manager cuando la actividad
	 * pasa a primer plano
	 * 
	 */
	public void onStart() {
		super.onStart();
		// Activamos el gps y solicitamos actualizaciones periódicas de la
		// localización
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				6000, 50, providerListener);

		if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			// mostramos el botón del gps como activado
			gpsActivo = true;

			locationManager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER, 6000, 50, providerListener);

		} else {
			// mostramos el botón del gps como desactivado
			gpsActivo = true;

		}

	}

	// ----------------------------------------------------------------------------------------
	//
	// Tratamiento del menú
	//
	//
	//
	// ----------------------------------------------------------------------------------------

	/**
	 * Cargamos el menú. Esto es suficiente para "engancharlo" a la tecla de
	 * menú
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Alternativa 1
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_actividad_mapa, menu);

		this.menuActivity = menu;

		return true;
	}

	/**
	 * Cambiamos los textos de los botones del menú: - Modo satélite on/off -
	 * Gps on/off
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// Modo satélite
		MenuItem menuItem = menu.findItem(R.id.mnuModoSatelite);
		if (modoSatelite)
			menuItem.setTitle("Modo mapa");
		else
			menuItem.setTitle("Modo satélite");

		// Gps
		menuItem = menu.findItem(R.id.mnuGps);
		if (gpsActivo)
			menuItem.setTitle("Desactivar GPS");
		else
			menuItem.setTitle("Activar GPS");

		return true;
	}

	/**
	 * Acciones asociadas a las opciones del menú
	 * 
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.mnuCentrarMapa:
			centrarMapa();
			return true;
		case R.id.mnuModoSatelite:
			if (modoSatelite) {
				mapa.setSatellite(false);
				modoSatelite = false;
			} else {
				mapa.setSatellite(true);
				modoSatelite = true;
			}

			return true;
		case R.id.mnuGps:
			// No podemos manipular directamente el gps => abrimos las
			// opciones de seguridad esté o
			// no habilitado para que modifiquen el estado desde ahí
			startActivity(new Intent(Settings.ACTION_SECURITY_SETTINGS));
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * Recuperamos de la bd la latitud, longitud y nivel de zoom de la ruta
	 * 
	 */
	private void obtenerLatitudLongitudCentrarMapa(int idRuta) {
		// Abrimos la bd
		DatabaseHelper dbh = new DatabaseHelper(this);

		try {
			dbh.openDataBase();

		} catch (SQLException sqle) {
			throw sqle;
		}

		// Recuperamos los datos de la ruta
		Cursor c = dbh.recuperarRuta(idRuta);
		c.moveToFirst();

		latitudCentrarRuta = c.getDouble(c.getColumnIndex("default_latitude")) * 1E6;
		longitudCentrarRuta = c.getDouble(c.getColumnIndex("default_longitude")) * 1E6;
		nivelZoomRuta = c.getInt(c.getColumnIndex("default_zoom_level"));

		c.close();
		dbh.close();

	}

}
