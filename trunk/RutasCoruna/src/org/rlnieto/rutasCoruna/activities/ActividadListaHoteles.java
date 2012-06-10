package org.rlnieto.rutasCoruna.activities;



import java.util.ArrayList;

import org.rlnieto.rutasCoruna.R;
import org.rlnieto.rutasCoruna.core.DatabaseHelper;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ActividadListaHoteles extends ListActivity {

	private Context contexto = null;
	ArrayList<Integer>claves = new ArrayList<Integer>();


	/**
	 * Clase privada para el mapeo de datos a la fila de rutas, que se compone de dos TextView
	 * con el nombre y la descripción de cada ruta
	 * 	
	 * @author guig
	 *
	 */
	private class MiAdaptador extends ArrayAdapter {

		private ArrayList<String> etiquetas;
		private ArrayList<String> descripciones;
		private ArrayList<Integer>claves;

		public MiAdaptador(Context context, int textViewResourceId, ArrayList<String>etiquetas, ArrayList<String>descripciones) {
			super(context, textViewResourceId, etiquetas);

			this.etiquetas = etiquetas;
			this.descripciones = descripciones;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			if (v == null) {
				LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.layout_fila_rutas, null);
			}


			TextView etiqueta = (TextView) v.findViewById(R.id.etiqueta);
			TextView descripcion = (TextView) v.findViewById(R.id.descripcion);

			etiqueta.setText(etiquetas.get(position));
			descripcion.setText(descripciones.get(position));

			return v;
		}
	}



	// -----------------------------------------------------------------------------------
	// Punto de entrada
	// -----------------------------------------------------------------------------------
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		// TODO: cargar etiquetas, descripciones y códigos de ruta desde la base de datos y 
		// eliminar hardcoding
		ArrayList<String>etiquetas = new ArrayList<String>();
		ArrayList<String>descripciones = new ArrayList<String>();

		contexto = this;


		// Recuperamos los hoteles de la bd
		DatabaseHelper dbh = new DatabaseHelper(this);

		try {
			dbh.openDataBase();

		}catch(SQLException sqle){throw sqle;}

		Cursor c = dbh.recuperarHoteles();

		while(c.moveToNext()){

			etiquetas.add(c.getString(c.getColumnIndex("nombrePoi")));
			descripciones.add(c.getString(c.getColumnIndex("direccion")) + "\n " + c.getString(c.getColumnIndex("telefono")).trim());
			claves.add(c.getInt(c.getColumnIndex("_id")));
		}

		for(int numero: claves){
			Log.w("elementos lista", String.valueOf(numero));
		}


		// Asignamos un adapter a la ListView
		setListAdapter(new MiAdaptador(this, R.layout.layout_fila_hoteles, etiquetas, descripciones));

	}


	protected void onListItemClick(ListView l, View v, int position, long id) {

		String url = "";

		switch(position){
			case 0: url = "http://www.booking.com/hotel/es/hesperiafinisterre.es.html";
					break;

			case 1: url = "http://www.booking.com/hotel/es/acacoruna.es.html";
					break;
					
			case 2: url = "http://www.booking.com/hotel/es/palace-a-coruna.es.html";
					break;

			case 3: url = "http://www.booking.com/hotel/es/carris-marineda.es.html";
					break;
					
			case 4: url = "http://http://www.booking.com/hotel/es/ciudadcoruna.es.html";
					break;
			
			case 5: url = "http://www.booking.com/hotel/es/husacenter.es.html";
			break;
	
			case 6: url = "http://www.booking.com/hotel/es/hesperia-coruna.es.html";
			break;
	
			case 7: url = "http://www.booking.com/hotel/es/meliamariapita.es.html";
			break;
		
			default: url = "http://www.booking.com/searchresults.es.html?sid=557473cd40256c4cdd68c62783802c07;dcid=1;checkin=;checkout=;city=-386792";
		}

		Intent myIntent = new Intent(contexto, ActividadNavegador.class);
		myIntent.putExtra("url", url);

		contexto.startActivity(myIntent);

	}
}
