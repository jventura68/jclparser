package org.rlnieto.rutasCoruna;

import java.io.IOException;
import java.io.InputStream;
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
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class PageVisualizer extends Activity{

	/**
	 * Punto de entrada a la aplicación
	 * 
	 */
	public void onCreate(Bundle savedInstanceState){

		String html = "";
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.visor_html);

		Bundle extras = getIntent().getExtras();
		if(extras !=null)
		{
			Integer clavePoi = (Integer)extras.get("clave_poi");

			if(clavePoi == 2)
			{
				html = "<html>" +
						"  <head>" +
						"    <title> Torre de Hércules </title>" +
						"    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />" +
						"  </head>" +
						"  <body>" +
						"    <h1>Torre de Hércules</h1>" +
    "<p>La <b>Torre de Hércules</b> es una torre y faro situado en la península de la ciudad de La Coruña, en Galicia (España). Su altura total es de 68 m y data del siglo I. Tiene el privilegio de ser el único faro romano y el más antiguo en funcionamiento del mundo. Es el segundo faro en altura de España, por detrás del Faro de Chipiona. El 27 de junio de 2009 fue declarado Patrimonio de la Humanidad1 por la UNESCO.</p>" +
  "<img align=\"center\" src=\"250px-A_coruna_torre_de_hercules_sunset_edit.jpg\"/>"+
    "<h1>Mitología y leyendas</h1>" +
  "<p>Hay varias leyendas relacionadas con su construcción. Una de ellas cuenta que Hércules llegó en barca a las costas que rodean actualmente la Torre, y que fue precisamente allí el lugar donde enterró la cabeza del gigante Gerión, después de vencerle en combate. Esta leyenda representa la continuidad del legado romano de Hércules sobre el legado tartésico-fenicio de Gerión.</p>" +
  "<p>Asimismo, historiadores identificaron la torre como el lugar donde pudo haber estado situada la Torre de Breogán,[cita requerida] una torre mitológica que aparece entre otros en el ciclo mitológico irlandés (más concretamente en el Leabhar Ghabhála Érenn), y desde la que Ith, hijo de Breogán, habría avistado las costas de Irlanda. Esta sospecha viene acrecentada por el topónimo Brigantium, que es altamente posible que derive de Breogán, y tiene sustento en la tradición de los romanos a la hora de identificar a sus propios dioses o héroes, en este caso Hércules, con los autóctonos.</p>" +
  "<p>La mitología dice que «hubo un gigante llamado Gerión, rey de Brigantium, que obligaba a sus súbditos a entregarle la mitad de sus bienes, incluyendo sus hijos. Un día los súbditos decidieron pedir ayuda a Hércules, que retó a Gerión en una gran pelea. Hércules derrotó a Gerión, lo enterró y levantó un túmulo que coronó con una gran antorcha. Cerca de este túmulo fundó una ciudad y, como la primera persona que llegó fue una mujer llamada Cruña, Hércules puso a la ciudad este nombre»</p>" +
  "<img src=\"220px-Torre_de_Hercules_-_DivesGallaecia2012-62.jpg\"/>"+
  "<h1> Historia </h1>" +
"<p>La Torre de Hércules fue construida por los romanos como faro de navegación en el siglo II d. C., comprendida la construcción entre los reinados de Nerón y Vespasiano en función de los hallazgos de fragmentos de terra sigillata y vasos de paredes finas datables entre los años 40 y 80 de nuestra era[cita requerida]. La inscripción al pie de la torre y las referencias documentales sobre la ciudad de Brigantium (La Coruña) revelan la existencia de un faro de la época de Trajano. En su base se encontró una piedra votiva con la inscripción en latín MARTI AVG.SACR C.SEVIVS LVPVS ARCHTECTVS ÆMINIENSIS LVSITANVS.EX.VO, lo que ha permitido identificar al arquitecto autor de la misma como Cayo Sevio Lupo, originario de Aeminium, hoy Coímbra, en Portugal.</p>" +
"<p>La mención más antigua a la Torre se encuentra en el Historiæ adversvm Paganos de Paulo Orosio, escrito hacia el 415–417 d. C., que dice: «Secvndvs angvlvs circivm intendit, ubi Brigantia Gallæciæ civitas sita altissimvm farvm et inter pavca memorandi operis ad specvlam Britanniæ erigit».</p>" +
"<p>La torre perdió, posiblemente, su uso marítimo durante la Edad Media al convertirse en fortificación. Fue en el siglo XVII (1682) cuando el Duque de Uceda encargó la restauración arquitectónica al arquitecto Amaro Antune, que construyó una escalera de madera que atravesaba las bóvedas hasta la parte superior, donde se sitúan dos pequeñas torres para soportar los fanales. En el reinado de Carlos III se realizó la reconstrucción completa. La obra neoclásica se terminó en 1791 bajo la dirección de Eustaquio Giannini.</p>" +
"<p>La torre era, antes de comenzar la reforma, un cuerpo prismático con base cuadrada; en el exterior presentaba un muro de piedra con dos puertas en la parte baja y ventanas asimétricas que la recorrían hasta el piso superior, y una mordiente helicoidal que llegaba hasta la parte superior. En su interior conservaba la vieja estructura romana, pero con escaleras de madera que pertenecían a la restauración de edificio, armonizándola en su decoración con marcos superiores de puertas y ventanas.</p>" +
"<p>La fachada actual de la torre es el fruto de la remodelación neoclásica efectuada en el siglo XVIII. En el año 2007, fue elegida candidata para engrosar la lista de bienes culturales Patrimonio de la Humanidad. El 9 de septiembre de 2008 se hermanó con la Estatua de la Libertad de Nueva York y el día 25 de ese mismo mes con el Faro del Morro de La Habana, el más antiguo de América y uno de los emblemas de Cuba.</p>" +
"<p>Desde el 15 de enero de 2012, la Torre de Hércules permanece cerrada de manera urgente, según la edil de Turismo Luisa Cid, para acometer unas obras. Lo cierto es que a día de hoy no hay obras de ninguna clase, y tampoco hay fecha prevista para su reapertura.</p>" +
"<img align=\"center\" src=\"220px-Torre_de_Hercules.003.jpg\"/>"+
"<h1> Parque escultórico </h1>" +
"<p>El Parque Escultórico de la Torre de Hércules es un museo al aire libre que, en su recorrido, se pueden contemplar más de 15 obras de importantes artistas del siglo XX como Francisco Leiro o Manolo Paz en un gran espacio natural.</p>" +
"<h1>Patrimonio de la humanidad</h1>" +
"<p>La Torre de Hércules fue declarada por la Unesco Patrimonio de la Humanidad el día 27 de junio de 2009 en la ciudad de Sevilla. La candidatura a obtener esta distinción, recibió un amplio apoyo popular e institucional.3 La noticia fue acogida con alegría entre todos los coruñeses concentrados en la plaza de María Pita y alrededor de la propia torre, quienes aguardaron durante horas la designación.</p>" +
 " </body>" +
"</html>";
				
			}else{
				if(clavePoi == 3){
					html = "<html>" +
					  "<head>" +
					    "<title> Castillo de San Antón </title>" +
					    "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />" +
					  "</head>" +
					  "<body>" +
					    "<h1>Castillo de San Antón</h1>" +
					    "<p>El <b>Castillo de San Antón</b> es un castillo del siglo XVI que formó parte, junto con el Castillo de Santa Cruz y el Castillo de San Diego, una red estratégica de castillos y baterías para defender la ciudad de La Coruña, en la comunidad autónoma de Galicia, España. Fue declarado Monumento Histórico Artístico en 1949 y desde 1994 ha pasado a ser considerado como un Bien de Interés Cultural con categoría de Monumento.1 Desde su inauguración en 1968, alberga el Museo Arqueológico e Histórico de La Coruña.</p>" +
					    "<img src=\"220px-Castillo_de_San_Anton.jpg\"/>" +
					    "<h1>Historia</h1>" +
					    "<p>Fue edificada esta antigua fortaleza en el que era entonces un pequeño islote en medio de la bahía coruñesa, en el que se encontraba una pequeña ermita dedicada a San Antón. Su propósito era defender la ciudad de los ataques desde el mar. Su construcción comenzó el año 1587, según indica una inscripción en la portada de la fortaleza." +
					"Durante el ataque inglés de 1589 por parte de la Armada Inglesa, el castillo contribuyó con eficacia a la defensa de la ciudad, a pesar de estar inacabado. Tras el ataque, se continuó su construcción hasta la finalización de las obras en 1590." +
					"A partir del siglo XVIII la fortaleza se convirtió en prisión, función que mantendría hasta su cesión al Ayuntamiento de A Coruña en 1960.</p>" +
					 "   <h1>Descripción</h1>" +
					    "<p>En la planta baja, antiguas dependencias de la guarnición del castillo, se exponen piezas de los diversos períodos de la Prehistoria e Historia Antigua de Galicia, procedentes en su mayor parte de excavaciones arqueológicas en yacimientos de la provincia. Mención especial merece la colección de orfebrería pre y protohistórica, con piezas tan notables como el casco de la Edad del Bronce de Leiro, los torques de Xanceda, el \"tesoro de Elviña\" o el conjunto de orfebrería calcolítica de Cícere (gargantilla de tiras, diademas y otras piezas)." +
					"Entrada al Castillo de San Antón." +
					"En la planta alta, que ocupa la llamada Casa del Gobernador, en donde residieron no solo gallegos ilustres, como Juana de Vega y su marido el General Mina, sino que también antecesores de otros como Francisco Vázquez que llegaría a ser alcalde de la ciudad, se recuerdan algunos hitos de la historia de la ciudad: expedición de la Armada Invencible y posterior ataque de Drake en 1589, con intervención heroica de María Pita, el capitán Juan Varela, el capitán Troncoso y otros muchos coruñeses y vecinos de los próximos lugares; en él sufrieron encarcelamiento presos ilustres, como Malaspina, Macanaz o Porlier. En La Coruña tuvo lugar el 16 de enero de 1809 la batalla de Elviña en la que fallecieron dos generales. Por parte de los británicos el teniente general jefe de aquel Ejército Sir John Moore, y por parte francesa el general de brigada Ives Manigaul-Gaulois. También se conservan la capilla de la Virgen del Rosario y su sacristía anexa." +
					"El 30 de mayo de 2009, en un documental de la TVG Europa, se habló de la posibilidad de que Manuel Blanco Romasanta hubiera muerto en este castillo.</p>" +
					    "<img src=\"250px-Entrada_del_Castillo_de_San_Anton.jpg\"/>" +
					 "</body>" +
					"</html>";
				}else{
					
					html = "<html><head></head><body background='white'><p>Cargando imágenes desde <b>assets/web</b>" +
							" para el poi con clave " +  String.valueOf(clavePoi) + "</p><br/><br/>" + 
							"<img src='Wikipedia-logo-v2-es.png' /><body></html>";
				}
			}	

			Spanned s = Html.fromHtml(html, getImageHTML_assets(), null);
			TextView txt = (TextView)findViewById(R.id.txtDocumento);
			txt.setText(s);

		}

	}

	
	/**
	 * Manejador para las imágenes html que aparecen en un texto (crea un drawable con la imagen)
	 * 
	 * @return
	 */

	public ImageGetter getImageHTML_assets(){
		ImageGetter ig = new ImageGetter(){
			public Drawable getDrawable(String source) {
				try{
					String rutaCompleta = "web/" + source;
					InputStream is = getAssets().open(rutaCompleta);
					Drawable d = Drawable.createFromStream(is, "src name");
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


	
	/**
	 * Manejador para imagenes por url
	 * 	
	 * @return
	 */
	public ImageGetter getImageHTML_link(){
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

	
	
	public ImageGetter getImageHTML_drawable(){
        ImageGetter ig = new ImageGetter(){
            public Drawable getDrawable(String source) {
               int resID = getResources().getIdentifier(source, "assets", "org.rlnieto.rutasCoruna"); //nombre del paquete al final
              Drawable d = new BitmapDrawable( BitmapFactory.decodeResource( PageVisualizer.this.getBaseContext().getResources(), resID));
              d.setBounds(0, 0, d.getIntrinsicWidth(),d.getIntrinsicHeight());
              return d;
            }
           };
       return ig;
   }
	
	
	
	
	
	
	


}