package org.rlnieto.rutasCoruna.overlays;

import com.google.android.maps.OverlayItem;
import com.google.android.maps.GeoPoint;

public class PoiOverlayItem extends OverlayItem {

	private int clavePoi;
	private int categoria;   // <100 para pois no comerciales, >=100 para pois comerciales

	public PoiOverlayItem(GeoPoint point, String title, String snippet, int clavePoi, int categoria){
		super(point, title, snippet);
		this.clavePoi = clavePoi;
		this.categoria = categoria;
		
	}


	public int getClavePoi() {
		return clavePoi;
	}

	public int getCategoria(){
		return categoria;
	}
	
}
