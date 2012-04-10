package org.rlnieto.rutasCoruna.overlays;

import java.util.ArrayList;

//import android.R.integer;
import android.content.Context;
//import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
//import android.util.Log;
//import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import android.app.AlertDialog;
import android.content.DialogInterface;



public class MyItemizedOverlay extends ItemizedOverlay<OverlayItem> {
 
	private ArrayList<OverlayItem> overlayItemList = new ArrayList<OverlayItem>();
	private Context context;

	public MyItemizedOverlay(Drawable marker, Context c) {
		super(boundCenterBottom(marker));
		populate();
		context = c;
	}
 
	@Override
	//--------------------------------------------------------------------------------------------------
	// Manejador para el evento "tap"
	//--------------------------------------------------------------------------------------------------
	protected boolean onTap(int index) {
		// TODO: se puede hacer que el toast desaparezca al tocar otro marcador del overlay?
		
		OverlayItem overlaySeleccionado = overlayItemList.get(index);
				
		// Código para mostrar el texto del snippet en un toast
//		Toast.makeText(context,
//				overlaySeleccionado.getTitle() + "\n" + overlaySeleccionado.getSnippet(),
//				Toast.LENGTH_LONG).show();
 
		
		// Código para mostrar el texto del snippet en un diálogo
		AlertDialog.Builder dialog = new AlertDialog.Builder(context);

		dialog.setTitle(overlaySeleccionado.getTitle());
		dialog.setMessage(overlaySeleccionado.getSnippet());
		dialog.setNegativeButton("Ok", 
				new DialogInterface.OnClickListener() {
	           			public void onClick(DialogInterface dialog, int id) {
	           				dialog.cancel();
	           			}
	           		});

		dialog.show();


		// Código para abrir un navegador cuando nos llega una url en el snippet
//		Intent myIntent = new Intent(context, PageVisualizer.class);
//		myIntent.putExtra("lugar", overlaySeleccionado.getSnippet());
//		context.startActivity(myIntent);

		return true;
		
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
}
