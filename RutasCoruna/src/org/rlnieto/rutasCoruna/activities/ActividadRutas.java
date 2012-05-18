package org.rlnieto.rutasCoruna.activities;



import java.util.ArrayList;

import org.rlnieto.rutasCoruna.R;

import android.app.Activity;
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

public class ActividadRutas extends ListActivity {

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
		String[] etiquetas = new String[] { "La Coruña modernista", "El joven Picasso" };
		String[] descripciones = new String[] { "Un recorrido por los edificios emblemáticos de finales del siglo XIX", "Descubra los lugares que ayudaron a forjar el carácter de este genio durante su infancia" };

		 contexto = this;
		 
		// Asignamos un adapter a la ListView
		setListAdapter(new MiAdaptador(this, R.layout.layout_fila_rutas, etiquetas, descripciones));

	}
	
	
	protected void onListItemClick(ListView l, View v, int position, long id) {
		//String item = (String) getListAdapter().getItem(position);
		//Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();

		// Abrimos la pantalla del mapa cargando la ruta elegida
		Toast.makeText(contexto, "Cargando ruta ...", Toast.LENGTH_SHORT).show();
		
    	Intent myIntent = new Intent(contexto, PantallaMapa.class);
    	myIntent.putExtra("idRuta", position + 1);
    	
    	contexto.startActivity(myIntent);
		
	}
}
