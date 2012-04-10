package org.rlnieto.rutasCoruna.overlays;


import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
//import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.readystatesoftware.mapviewballoons.BalloonItemizedOverlay;

import android.database.Cursor;
import org.rlnieto.rutasCoruna.*;


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
	public void addItem(GeoPoint p, String title, String snippet, Drawable marker, int clavePoi){
		PoiOverlayItem newItem = new PoiOverlayItem(p, title, snippet, clavePoi);
		
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
		
		/*Toast.makeText(contexto, "onBalloonTap for overlay index " + index,
				Toast.LENGTH_LONG).show();
		*/
		
		/* ¿Devolvemos la clave del poi o abrimos la pantalla con los datos directamente? */
		/*Toast.makeText(contexto, "onBalloonTap clave en la tabla de pois: " + ((PoiOverlayItem)item).getClavePoi(),
				Toast.LENGTH_LONG).show();
		*/
		
    	Intent myIntent = new Intent(contexto, PageVisualizer.class);
		myIntent.putExtra("clave_poi", ((PoiOverlayItem)item).getClavePoi());
    	contexto.startActivity(myIntent);
		
		return true;
	}
	
	
	
	public void cargarPois(Cursor pois){
		
	}
	
	
	
	
	
	
}
