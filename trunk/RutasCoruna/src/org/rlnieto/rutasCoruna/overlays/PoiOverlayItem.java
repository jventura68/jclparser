package org.rlnieto.rutasCoruna.overlays;

import com.google.android.maps.OverlayItem;
import com.google.android.maps.GeoPoint;

public class PoiOverlayItem extends OverlayItem {

	private int clavePoi;


	public PoiOverlayItem(GeoPoint point, String title, String snippet, int clavePoi){
		super(point, title, snippet);
		this.clavePoi = clavePoi;
		
	}


	public int getClavePoi() {
		return clavePoi;
	}

	
}
