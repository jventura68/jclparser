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

import org.rlnieto.rutasCoruna.activities.ActividadNavegador;
import org.rlnieto.rutasCoruna.activities.PageVisualizer;
import org.rlnieto.rutasCoruna.core.*;


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
				
		if(categoria == 100){      // es un hotel => recupermos la url de booking
		  String url = recuperarUrlBooking();														 // y la abrimos en un webView
			
		  Intent myIntent = new Intent(contexto, ActividadNavegador.class);
		  myIntent.putExtra("url", url);

		  contexto.startActivity(myIntent);
			
		}else{   // los datos de los pois no comerciales van en un textView
			Intent myIntent = new Intent(contexto, PageVisualizer.class);
			myIntent.putExtra("clave_poi", idPoi);
			contexto.startActivity(myIntent);
		}
		return true;
	}
	
	
	
	
	/**
	 * Recupera la url de booking asociada a un hotel
	 * 
	 * @return
	 */
	private String recuperarUrlBooking(){
		return "http://www.booking.com/hotel/es/acacoruna.html?tab=&error_url=%2Fhotel%2Fes%2Facacoruna.es.html%3Fsid%3D74128f16a675c9d857792fa984497292%3Bdcid%3D1%3Bstid%3D325542%3B&do_availability_check=on&dcid=1&sid=74128f16a675c9d857792fa984497292&stid=325542&rows=10&error_url=http%3A%2F%2Fwww.booking.com%2Fhotel%2Fes%2Facacoruna.es.html%3Fsid%3D74128f16a675c9d857792fa984497292%3Bdcid%3D1%3Bstid%3D325542%3B&dcid=1&sid=74128f16a675c9d857792fa984497292&stid=325542&si=ai%2Cco%2Cci%2Cre%2Cdi&checkin_monthday=&checkin_year_month=&checkout_monthday=&checkout_year_month=&ci_date=&num_nights=1&co_date=";
	}
	
	
	
	
	
	
}
