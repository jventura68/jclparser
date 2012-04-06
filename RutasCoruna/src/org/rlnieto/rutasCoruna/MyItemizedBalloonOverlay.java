package org.rlnieto.rutasCoruna;


import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.readystatesoftware.mapviewballoons.BalloonItemizedOverlay;

import android.database.Cursor;


public class MyItemizedBalloonOverlay  extends BalloonItemizedOverlay<OverlayItem>{

	
	private ArrayList<OverlayItem> overlayItemList = new ArrayList<OverlayItem>();
	private Context contexto;
	
	
	public MyItemizedBalloonOverlay(Drawable marker, MapView mapView) {
		super(boundCenterBottom(marker), mapView);
		populate();
		contexto = mapView.getContext();
	}
	
	
	//--------------------------------------------------------------------------------------------------
	// Se añade un nuevo marcador al overlay
	//--------------------------------------------------------------------------------------------------
	public void addItem(GeoPoint p, String title, String snippet, Drawable marker){
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
		Toast.makeText(contexto, "onBalloonTap for overlay index " + index,
				Toast.LENGTH_LONG).show();
		return true;
	}
	
	
	public void cargarPois(Cursor pois){
		
	}
	
	
	
	
	
	
}
