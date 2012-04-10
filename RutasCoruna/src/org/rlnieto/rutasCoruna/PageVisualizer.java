package org.rlnieto.rutasCoruna;

import java.io.IOException;
import java.net.URL;

import android.text.Html;
import android.text.Spanned;
import android.text.Html.ImageGetter;
import android.util.Log;
import android.widget.TextView;

import android.app.Activity;
//import android.net.Uri;
import android.os.Bundle;
//import android.content.Intent;
import android.graphics.drawable.Drawable;

public class PageVisualizer extends Activity{

	/**
	 * Punto de entrada a la aplicación
	 * 
	 */
	public void onCreate(Bundle savedInstanceState){

		super.onCreate(savedInstanceState);
		setContentView(R.layout.visor_html);

		Bundle extras = getIntent().getExtras();
		if(extras !=null)
		{
			String html = "Tus ejemplos en<br/>TutorialAndroid.com<br/><br/>" + 
					"<img src='http://www.tutorialandroid.com/tutorialandroidcom.jpg' /><br/>" +
					"<img src='http://www.sgoliver.net/blog/wp-content/uploads/2010/08/simulacro-200x300.png' />";

			Spanned s = Html.fromHtml(html,getImageHTML(),null);
			TextView txt = (TextView)findViewById(R.id.txtDocumento);
			txt.setText(s);

		}

	}

	
	/**
	 * 
	 * @return
	 */
	public ImageGetter getImageHTML(){
		ImageGetter ig = new ImageGetter(){
			public Drawable getDrawable(String source) {
				try{
					Drawable d = Drawable.createFromStream(new URL(source).openStream(), "src name");
					d.setBounds(0, 0, d.getIntrinsicWidth(),d.getIntrinsicHeight());
					return d;
				}catch(IOException e){
					Log.v("IOException",e.getMessage());
					return null;
				}
			}
		};
		return ig;	
	}



}