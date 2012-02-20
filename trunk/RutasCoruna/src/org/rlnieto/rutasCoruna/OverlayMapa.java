package org.rlnieto.rutasCoruna;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class OverlayMapa extends Overlay {
	
	//private Double latitud = 37.40*1E6;
	//private Double longitud = -5.99*1E6;

	private Double latitud = 43.371334*1E6;
	private Double longitud = -8.396001*1E6;

	private Double latitudPlazaPontevedra = 43.367715*1E6;
	private Double longitudPlazaPontevedra = -8.407604*1E6;


	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow) 
	{
		Projection projection = mapView.getProjection();
		GeoPoint geoPoint = 
			new GeoPoint(latitud.intValue(), longitud.intValue());
		
		if (shadow == false) 
		{
			Point centro = new Point();
			projection.toPixels(geoPoint, centro);

			//Definimos el pincel de dibujo
			Paint p = new Paint();
			p.setColor(Color.BLUE);
			
			//Marca Ejemplo 1: C�rculo y Texto
			//canvas.drawCircle(centro.x, centro.y, 5, p);
			//canvas.drawText("Coruña", centro.x+10, centro.y+5, p);
			
			//Marca Ejemplo 2: Bitmap
			Bitmap bm = BitmapFactory.decodeResource(
					mapView.getResources(), 
					R.drawable.marcador_google_maps);

			// Punto central
			canvas.drawBitmap(bm, centro.x - bm.getWidth(), 
					              centro.y - bm.getHeight(), p);
			
			
			
			// Plaza de Pontevedra
			GeoPoint geoPointPlazaPontevedra = 
					new GeoPoint(latitudPlazaPontevedra.intValue(), longitudPlazaPontevedra.intValue());

			Point centroPlazaPontevedra = new Point();
			projection.toPixels(geoPointPlazaPontevedra, centroPlazaPontevedra);

			Bitmap bmPlazaPontevedra = BitmapFactory.decodeResource(
					mapView.getResources(), 
					R.drawable.marcador_google_maps);
			
			canvas.drawBitmap(bmPlazaPontevedra, centroPlazaPontevedra.x - bmPlazaPontevedra.getWidth(), 
		              centroPlazaPontevedra.y - bmPlazaPontevedra.getHeight(), p);

			canvas.drawBitmap(bmPlazaPontevedra, centroPlazaPontevedra.x - bmPlazaPontevedra.getWidth(), 
		              centroPlazaPontevedra.y - bmPlazaPontevedra.getHeight(), p);
			
			
			// Torre de Hercules
			Double latitudTorreHercules = 43.385152*1E6;
			Double longitudTorreHercules = -8.398533*1E6;

			GeoPoint geoPointTorreHercules = 
					new GeoPoint(latitudTorreHercules.intValue(), longitudTorreHercules.intValue());

			Point centroTorreHercules = new Point();
			projection.toPixels(geoPointTorreHercules, centroTorreHercules);

			Bitmap bmTorreHercules = BitmapFactory.decodeResource(
					mapView.getResources(), 
					R.drawable.marcador_google_maps);
			
			canvas.drawBitmap(bmTorreHercules, centroTorreHercules.x - bmTorreHercules.getWidth(), 
		              centroTorreHercules.y - bmTorreHercules.getHeight(), p);

			canvas.drawBitmap(bmTorreHercules, centroTorreHercules.x - bmTorreHercules.getWidth(), 
		              centroTorreHercules.y - bmTorreHercules.getHeight(), p);
			
			
			// Castillo de San Anton
			Double latitudSanAnton = 43.366062*1E6;
			Double longitudSanAnton = -8.387547*1E6;

			GeoPoint geoPointSanAnton = 
					new GeoPoint(latitudSanAnton.intValue(), longitudSanAnton.intValue());

			Point centroSanAnton = new Point();
			projection.toPixels(geoPointSanAnton, centroSanAnton);

			Bitmap bmSanAnton = BitmapFactory.decodeResource(
					mapView.getResources(), 
					R.drawable.marcador_google_maps);
			
			canvas.drawBitmap(bmSanAnton, centroSanAnton.x - bmSanAnton.getWidth(), 
		              centroSanAnton.y - bmSanAnton.getHeight(), p);

			canvas.drawBitmap(bmSanAnton, centroSanAnton.x - bmSanAnton.getWidth(), 
		              centroSanAnton.y - bmSanAnton.getHeight(), p);
			
			
			
			
		}
	}
	
	@Override
	public boolean onTap(GeoPoint point, MapView mapView) 
	{
		Context contexto = mapView.getContext();

		int lLatitud = 0;
		int lLongitud = 0;
		
		lLatitud = point.getLatitudeE6();
		lLongitud = point.getLongitudeE6();
		
		String msg = "Lat: " + point.getLatitudeE6()/1E6 + " - " + 
		             "Lon: " + point.getLongitudeE6()/1E6;
		
		System.out.println(lLatitud);
		System.out.println(latitudPlazaPontevedra);
		
		System.out.println(lLongitud);
		System.out.println(longitudPlazaPontevedra);
		
		
		if(lLatitud == latitudPlazaPontevedra.intValue() && lLongitud == longitudPlazaPontevedra.intValue()){
		  msg = msg + "Plaza de Pontevedra";
		}
		
		Toast toast = Toast.makeText(contexto, msg, Toast.LENGTH_SHORT);
		toast.show();
		
		return true;
	}
}
