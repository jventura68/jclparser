package org.rlnieto.rutasCoruna.activities;

import org.rlnieto.rutasCoruna.R;
import org.rlnieto.rutasCoruna.core.DatabaseHelper;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ActividadListaRutas extends ListActivity {

	private Context contexto = null;
	
	
	/**
	 * Clase privada para el mapeo de datos a la fila de rutas, que se compone de dos TextView
	 * con el nombre y la descripción de cada ruta
	 * 	
	 * @author guig
	 *
	 */
	private class MiAdaptador extends ArrayAdapter {

		private String[] etiquetas;
		private String[] descripciones;

		public MiAdaptador(Context context, int textViewResourceId, String[] etiquetas, String[] descripciones) {
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
			
			etiqueta.setText(etiquetas[position]);
			descripcion.setText(descripciones[position]);
			
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
		String[] etiquetas = new String[] { "A Coruña histórica",
											"Edificios emblemáticos", 
											"Museos"};
		String[] descripciones = new String[] { "Descubra los principales edificios religiosos y militares de parte antigua la ciudad",
												"Un recorrido por los edificios emblemáticos de finales del siglo XIX", 
												"Recorra los museos de la ciudad, desde la Domus hasta el dedicado a la vida del joven Picasso"};

		 contexto = this;
		 
		// Asignamos un adapter a la ListView
		setListAdapter(new MiAdaptador(this, R.layout.layout_fila_rutas, etiquetas, descripciones));

	}
	

	protected void onListItemClick(ListView l, View v, int position, long id) {

		int idRuta = 0;

System.out.println("-------> Opción: " + position); 		

		// Abrimos la pantalla del mapa cargando la ruta elegida
		Toast.makeText(contexto, "Cargando datos ...", Toast.LENGTH_SHORT).show();

		switch(position){
			case 0: idRuta = DatabaseHelper.RUTA_HISTORICA;
					break;
			case 1: idRuta = DatabaseHelper.RUTA_MODERNISTA;
					break;
			case 2: idRuta = DatabaseHelper.RUTA_MUSEOS;
					break;
			default: idRuta = 0;
		}
		
    	Intent myIntent = new Intent(contexto, ActividadMapa.class);
    	myIntent.putExtra("idRuta", idRuta);
    	
    	contexto.startActivity(myIntent);
		
	}
}
