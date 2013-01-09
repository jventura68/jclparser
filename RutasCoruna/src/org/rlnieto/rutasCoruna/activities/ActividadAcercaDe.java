package org.rlnieto.rutasCoruna.activities;

import org.rlnieto.rutasCoruna.R;
import org.rlnieto.rutasCoruna.utils.Constantes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class ActividadAcercaDe extends Activity{
	
	private TextView txtAcercaDe = null;
	private ImageView imgFacebook = null;
	private ImageView imgTwitter = null;
	private Context contexto = null;
	
	@Override
	//-----------------------------------------------------------------------------------
	// Punto de entrada a la aplicación
	//-----------------------------------------------------------------------------------
	public void onCreate(Bundle savedInstanceState){
		
		contexto = this;
		
		super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_acerca_de);

        String textoAcercaDe = "  Coruña Avenue se presenta ante ustedes como una empresa de turismo cultural que nace con el objetivo de cubrir las necesidades culturales y de ocio de los turistas, nacionales y extranjeros, que llegan a la ciudad de A Coruña y que desean hacer de su breve visita una experiencia inolvidable.\n\n   Aprovechando los múltiples y variados recursos culturales existentes y uno de los entornos paisajísticos más bellos de este rincón de España, ofreceremos de una forma ágil, optimizada y entretenida, la posibilidad de encontrar en la ciudad de A Coruña y sus alrededores, todo aquello que pudiera satisfacer la curiosidad y deseos de los turistas.\n\n   A través de nuestras rutas guiadas en A Coruña y/o Santiago de Compostela, se podrán admirar bellos lugares históricos, fotografiar edificios modernistas, perderse por variadas rutas de compras en el centro y sus emblemáticas calles antiguas, disfrutar una y otra vez de la extensa y variada gastronomía gallega, deleitarse en sus museos y fundaciones, visitar monumentos que son patrimonio de la humanidad, como la torre de Hércules o la catedral de Santiago de Compostela, enamorarse de la belleza de la Costa da Morte o simplemente disfrutar de un relajante paseo a orillas del mar.";
        
        
        txtAcercaDe = (TextView)findViewById(R.id.txtPerfil);
        txtAcercaDe.setText(textoAcercaDe);
        
        ImageView imgFacebook = (ImageView)findViewById(R.id.imgFacebook);
        ImageView imgTwitter = (ImageView)findViewById(R.id.imgTwitter);
        
        
        
        
        /**
         * Listener para la imagen de facebook
         * 
         */
        imgFacebook.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				Intent myIntent = new Intent("android.intent.action.VIEW", Uri.parse(Constantes.URL_FACEBOOK_EMPRESA));
				contexto.startActivity(myIntent);
			}
		});
        
        /**
         * Listener para la imagen de twitter
         * 
         */
        imgTwitter.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {

				Intent myIntent = new Intent("android.intent.action.VIEW", Uri.parse(Constantes.URL_TWITTER_EMPRESA));
				contexto.startActivity(myIntent);
			}
		});
        
        
        
	}

}
