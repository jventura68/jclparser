package org.rlnieto.rutasCoruna.overlays;


import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
//import android.util.Log;
//import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.readystatesoftware.mapviewballoons.BalloonItemizedOverlay;

//import android.database.Cursor;

import org.rlnieto.rutasCoruna.activities.ActividadDescripcionPoi;
import org.rlnieto.rutasCoruna.activities.ActividadFormularioContacto;
//import org.rlnieto.rutasCoruna.activities.ActividadNavegador;
//import org.rlnieto.rutasCoruna.activities.PageVisualizer;
//import org.rlnieto.rutasCoruna.core.*;


public class MyItemizedBalloonOverlay  extends BalloonItemizedOverlay<OverlayItem>{

	
	private ArrayList<OverlayItem> overlayItemList = new ArrayList<OverlayItem>();
	private Context contexto;
	
	
	public MyItemizedBalloonOverlay(Drawable marker, MapView mapView) {
		super(boundCenterBottom(marker), mapView);
		populate();
		contexto = mapView.getContext();
	}
	
	
	/**
	 * Se añade un nuevo marcador al overlay
	 * 
	 * @param p: coordenadas
	 * @param title: título
	 * @param snippet: descripción
	 * @param marker: icono
	 * @param clavePoi: clave del poi en la tabla de pois
	 */
	public void addItem(GeoPoint p, String title, String snippet, Drawable marker, int clavePoi, int categoria){
		PoiOverlayItem newItem = new PoiOverlayItem(p, title, snippet, clavePoi, categoria);
		
		newItem.setMarker(marker);
		overlayItemList.add(newItem);
		populate();
	}
 
	public void addItemOriginal(GeoPoint p, String title, String snippet, Drawable marker, int clavePoi){
		OverlayItem newItem = new OverlayItem(p, title, snippet);
		
		newItem.setMarker(marker);
		overlayItemList.add(newItem);
		populate();
	}
 
	@Override
	//--------------------------------------------------------------------------------------------------
	// Creación de un nuevo overlay?
	//--------------------------------------------------------------------------------------------------
	protected OverlayItem createItem(int i) {
		return overlayItemList.get(i);
	}
 
	@Override
	//--------------------------------------------------------------------------------------------------
	// Devuelve el número de overlays creados
	//--------------------------------------------------------------------------------------------------
	public int size() {
		return overlayItemList.size();
	}
 
	@Override
	//--------------------------------------------------------------------------------------------------
	// Muestra el overlay?
	//--------------------------------------------------------------------------------------------------
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		super.draw(canvas, mapView, shadow);
	}
	

	
	@Override
	protected boolean onBalloonTap(int index, OverlayItem item) {

		int categoria = ((PoiOverlayItem)item).getCategoria(); 
		int idPoi = ((PoiOverlayItem)item).getClavePoi();

		// Si la categoría es >= 100 es un negocio => abrimos la pantalla para llamar o reservar por inet
		// Si la categoría es < 100 es un poi de una ruta => cargamos la descripción y las fotos
		if(categoria >= 100){     
		  Intent myIntent = new Intent(contexto, ActividadFormularioContacto.class);

		  myIntent.putExtra("clave_poi", idPoi);
		  contexto.startActivity(myIntent);
			
		}else{   // los datos de los pois no comerciales van en un textView
			Intent myIntent = new Intent(contexto, ActividadDescripcionPoi.class);
			myIntent.putExtra("clave_poi", idPoi);
			contexto.startActivity(myIntent);
		}
		return true;
	}
	
}
