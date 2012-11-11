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
	private ArrayList<Integer>clavesPoi = new ArrayList<Integer>(20);


	/**
	 * Clase privada para el mapeo de datos a la fila de rutas, que se compone de dos TextView
	 * con el nombre y la descripción de cada ruta
	 * 	
	 * @author guig
	 *
	 */
	private class MiAdaptador extends ArrayAdapter<String> {

		private ArrayList<String> etiquetas;
		private ArrayList<String> descripciones;

		/**
		 * Constructor 
		 * 
		 * @param context
		 * @param textViewResourceId
		 * @param etiquetas
		 * @param descripciones
		 */
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
				v = vi.inflate(R.layout.layout_fila_hoteles, null);
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

		ArrayList<String>etiquetas = new ArrayList<String>();
		ArrayList<String>descripciones = new ArrayList<String>();

		contexto = this;


		// Recuperamos los hoteles de la bd
		DatabaseHelper dbh = new DatabaseHelper(this);

		try {
			dbh.openDataBase();

		}catch(SQLException sqle){throw sqle;}

		Cursor c = dbh.recuperarHoteles();

		// Componemos tantos asteriscos como estrellas tiene el hotel
		// TODO: meter un gráfico en la lista en lugar de los asteriscos
		while(c.moveToNext()){
			
			StringBuilder estrellas = new StringBuilder("");
			
			int numEstrellas = Integer.parseInt(c.getString(c.getColumnIndex("estrellas")));
			for(int i=0;i<numEstrellas;i++){
				estrellas.append("*");
			}
			
			
			etiquetas.add(c.getString(c.getColumnIndex("nombrePoi")) + " " + estrellas);
			descripciones.add(c.getString(c.getColumnIndex("direccion")) + "\n " + c.getString(c.getColumnIndex("telefono")).trim());
			clavesPoi.add(c.getInt(c.getColumnIndex("_id")));
			
Log.w("Clave poi añadida al array: ", String.valueOf(c.getInt(c.getColumnIndex("_id"))));			
					
		}

		// Asignamos un adapter a la ListView (contexto, layout, datos etiquetas, datos descripciones)
		setListAdapter(new MiAdaptador(this, R.layout.layout_fila_hoteles, etiquetas, descripciones));

	}


	/**
	 * Evento click de la lista de hoteles: abrimos el formulario de contacto para que puedan
	 * reservar por teléfono
	 * 
	 * 
	 */
	protected void onListItemClick(ListView l, View v, int position, long id) {

		
//Toast.makeText(this.contexto, this.clavesPoi.get(position), Toast.LENGTH_SHORT).show();

		Intent myIntent = new Intent(contexto, ActividadFormularioContacto.class);
		myIntent.putExtra("clave_poi", clavesPoi.get(position));

		contexto.startActivity(myIntent);

	}
}
