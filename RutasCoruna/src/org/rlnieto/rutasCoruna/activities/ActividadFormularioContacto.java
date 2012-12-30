package org.rlnieto.rutasCoruna.activities;

import java.io.IOException;
import java.io.InputStream;

import org.rlnieto.rutasCoruna.R;
import org.rlnieto.rutasCoruna.core.DatabaseHelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html.ImageGetter;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ActividadFormularioContacto extends Activity {

	private Context contexto = null;

	private TextView nombreEstablecimiento = null;
	private TextView descripcionEstablecimiento = null;
	private TextView direccionEstablecimiento = null;
	private TextView telefonoEstablecimiento = null;
	private ImageButton btnLlamarEstablecimiento = null;
	private Button btnReservarBooking = null;
	private Button btnReservarAtrapalo = null;
	private ImageView imgMapa = null;
	
	
	private int clavePoi = 0;

	@Override
	// -----------------------------------------------------------------------------------
	// Punto de entrada a la aplicación
	// -----------------------------------------------------------------------------------
	public void onCreate(Bundle savedInstanceState) {

		Cursor c = null;

		super.onCreate(savedInstanceState);
		setContentView(R.layout.pantalla_formulario_contacto);
		contexto = this;

		nombreEstablecimiento = (TextView) findViewById(R.id.txtNombreEstablecimiento);
		descripcionEstablecimiento = (TextView) findViewById(R.id.txtDescripcionEstablecimiento);
		direccionEstablecimiento = (TextView) findViewById(R.id.txtDireccionEstablecimiento);
		telefonoEstablecimiento = (TextView) findViewById(R.id.txtTelefonoEstablecimiento);
		btnLlamarEstablecimiento = (ImageButton) findViewById(R.id.btnLlamarEstablecimiento);
		btnReservarBooking = (Button) findViewById(R.id.btnReservarBooking);
		btnReservarAtrapalo = (Button) findViewById(R.id.btnReservarAtrapalo);
		imgMapa = (ImageView) findViewById(R.id.imgMapa);

		// Nos llega la clave de la tabla de pois
		Bundle bundle = getIntent().getExtras();
		clavePoi = Integer.valueOf(bundle.getInt("clave_poi"));

		rellenarDatosFormulario(clavePoi);

		// --------------------------------------------------------------------------
		//
		// listeners
		//
		// --------------------------------------------------------------------------

		/**
		 * Llama al establecimiento
		 * 
		 */
		btnLlamarEstablecimiento.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				llamarEstablecimiento(telefonoEstablecimiento.getText()
						.toString());

			}
		});

		/**
		 * Abre una pantalla con un webview para hacer la reserva vía booking
		 */
		btnReservarBooking.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				reservarViaBooking(recuperarUrlBooking(ActividadFormularioContacto.this.clavePoi));

			}
		});

		/**
		 * Abre una pantalla con un webview para hacer la reserva vía atrápalo
		 */
		btnReservarAtrapalo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				reservarViaAtrapalo(recuperarUrlAtrapalo(ActividadFormularioContacto.this.clavePoi));

			}
		});

	} // onCreate

	/**
	 * Recupera datos de la bd y rellena los campos del formulario
	 * 
	 * @param idPoi
	 *            : clave del poi en la tabla
	 * 
	 */
	private void rellenarDatosFormulario(int clavePoi) {

		// TODO: ver si se puede eliminar el cursor y utilizar un objeto de tipo
		// poi. Problema: ¿hacemos
		// uno genérico para hoteles y restaurantes o uno distinto para cada
		// categoría? Me inclino por la
		// segunda opción
		Cursor c = null;

		// Recuperamos los datos del poi
		DatabaseHelper dbh = new DatabaseHelper(this);

		try {
			dbh.openDataBase();

		} catch (SQLException sqle) {
			throw sqle;
		}

		c = dbh.recuperarPoi(clavePoi);
		c.moveToFirst();
		
		int idPoi = c.getInt(c.getColumnIndex("_id"));
		String nombrePoi = c.getString(c.getColumnIndex("nombrePoi"));
		String datosPoi = c.getString(c.getColumnIndex("descPoi"));
		int categoria = c.getInt(c.getColumnIndex("categoria"));
		String direccion = c.getString(c.getColumnIndex("direccion"));
		int telefono = c.getInt(c.getColumnIndex("telefono"));
		String urlBitmapMapa = c.getString(c.getColumnIndex("uriImagen"));
Log.v("Guarrin", urlBitmapMapa);

		nombreEstablecimiento.setText(nombrePoi);
		descripcionEstablecimiento.setText(datosPoi);
		direccionEstablecimiento.setText(direccion);
		telefonoEstablecimiento.setText(String.valueOf(telefono));

		try{
			InputStream is = this.contexto.getAssets().open(urlBitmapMapa);
			Drawable d = Drawable.createFromStream(is, null);
			imgMapa.setImageDrawable(d);
		} catch (IOException e) {
			Log.v("IOException", e.getMessage() + " Buscando: " + urlBitmapMapa);
		}		
		
		
		
		
		
/*		Cursor cursorRestaurante = dbh.detalleRestaurante(idPoi);

		cursorRestaurante.moveToFirst();
		txtOtrosDatos.setText(cursorRestaurante.getString(cursorRestaurante.getColumnIndex("especialidad")));
		txtPrecioMedio.setText(cursorRestaurante.getString(cursorRestaurante.getColumnIndex("precio_medio"))); 
		
		cursorRestaurante.close();
*/		
		
		// Si se trata de un hotel mostramos la opción de reservar vía Booking
		// Si se trata de un restaurante mostramos la especialidad y el precio medio
		switch (categoria) {
		case DatabaseHelper.CODIGO_HOTEL:
			//btnReservarBooking.setVisibility(android.view.View.VISIBLE);
			break;
		case DatabaseHelper.CODIGO_RESTAURANTE:  // mostramos la especialidad y el precio medio
			break;
		}

		c.close();
		dbh.close();

	}

	/**
	 * Inicia una llamada con el establecimiento para hacer la reserva
	 * 
	 * @param numTelefono
	 */
	private void llamarEstablecimiento(String numTelefono) {

		Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
				+ numTelefono));
		startActivity(intent);

	}

	/**
	 * Recupera la url de booking asociada a un hotel
	 * 
	 * @return
	 */
	private String recuperarUrlBooking(int clavePoi) {

		Cursor c = null;

		// Recuperamos los datos del hotel
		DatabaseHelper dbh = new DatabaseHelper(this);

		try {
			dbh.openDataBase();

		} catch (SQLException sqle) {
			throw sqle;
		}

		c = dbh.recuperarHotel(clavePoi);
		c.moveToFirst();

		String urlBooking = c.getString(c.getColumnIndex("url_booking"));

		c.close();
		dbh.close();

		return urlBooking;
	}


	/**
	 * Recupera la url de atrápalo asociada a un restaurante
	 * 
	 * @return
	 */
	private String recuperarUrlAtrapalo(int clavePoi) {
		return("http://www.atrapalo.com/restaurantes/la-marola_f2876.html");
	}


	
	
	/**
	 * Abre el navegador y muestra la url del hotel para que puedan hacer la
	 * reserva
	 * 
	 */
	private void reservarViaBooking(String url) {

		Intent myIntent = new Intent(contexto, ActividadNavegador.class);

		myIntent.putExtra("url", url);
		contexto.startActivity(myIntent);
		
	}

	/**
	 * Abre el navegador y muestra la url del restaurante para que puedan hacer la
	 * reserva
	 * 
	 */
	private void reservarViaAtrapalo(String url) {

		Intent myIntent = new Intent(contexto, ActividadNavegador.class);

		myIntent.putExtra("url", url);
		contexto.startActivity(myIntent);

	}

}
