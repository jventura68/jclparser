package org.rlnieto.rutasCoruna;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;

public class PageVisualizer extends Activity{
    
	//-----------------------------------------------------------------------------------
	// Punto de entrada a la aplicación
	//-----------------------------------------------------------------------------------
	public void onCreate(Bundle savedInstanceState){
		
		String url = "";
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.visor_html);

		Bundle extras = getIntent().getExtras();
		if(extras !=null)
		{
			url = extras.getString("lugar");
		}
		
		WebView mWebView;
		mWebView = (WebView)findViewById(R.id.webview);
		mWebView.getSettings().setJavaScriptEnabled(true);
		
		mWebView.loadUrl(url);

	}
	
}