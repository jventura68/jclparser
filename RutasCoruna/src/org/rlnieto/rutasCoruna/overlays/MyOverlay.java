package org.rlnieto.rutasCoruna.overlays;

//import java.util.List;

import org.rlnieto.rutasCoruna.R;
//import org.rlnieto.rutasCoruna.R.drawable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
//import android.content.Context;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

import android.widget.Toast;

public class MyOverlay extends Overlay {
	GeoPoint point;
	
	public MyOverlay(GeoPoint point) {
		super();
		this.point = point;
	}
	
    @Override
    public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) {
        super.draw(canvas, mapView, shadow);                   

        Point scrnPoint = new Point();
        mapView.getProjection().toPixels(this.point, scrnPoint);

        Bitmap marker = BitmapFactory.decodeResource(mapView.getResources(), R.drawable.icon);
        canvas.drawBitmap(marker,
        		scrnPoint.x - marker.getWidth() / 2,
        		scrnPoint.y - marker.getHeight() / 2, null);
        return true;
    }


    @Override
    public boolean onTap(GeoPoint p, MapView map){

    	Context contexto = map.getContext();

    	//List<Overlay> mapOverlays = map.getOverlays();
    	Overlay overlay1 = map.getOverlays().get(1);
    	
    	Toast.makeText(contexto, overlay1.toString(), Toast.LENGTH_SHORT).show();

    	
    	
    	return(true);
    }

}	
