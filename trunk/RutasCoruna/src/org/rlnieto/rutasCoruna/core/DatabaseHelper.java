/**
 * Accesos a la bd
 * 
 */
package org.rlnieto.rutasCoruna.core;


import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;

import org.rlnieto.rutasCoruna.utils.Constantes;

public class DatabaseHelper extends SQLiteOpenHelper{

	// OJO OJITO: estos valores tienen que coincidir con la clave de la tabla de rutas
	public static final int RUTA_MODERNISTA = 1;
	public static final int RUTA_PICASSO = 2;
	public static final int LISTA_HOTELES = 3;
	public static final int RUTA_HISTORICA = 4;
	public static final int LISTA_RESTAURANTES = 5;
	public static final int RUTA_MUSEOS = 6;
	
	// Constantes para las categorías de los pois
	// TODO: pensar cómo codificar y guardar los códigos de las categorías de los pois
	public static final int CODIGO_MUSEO = 1;
	public static final int CODIGO_IGLESIA = 2;
	public static final int CODIGO_MONUMENTO = 3;
	public static final int CODIGO_PAISAJE = 4;
	
	// Los pois comerciales tienen código >= 100
	public static final int CODIGO_HOTEL = 100;
	public static final int CODIGO_RESTAURANTE = 101;
	public static final int CODIGO_OCIO_NOCTURNO = 102;
	public static final int CODIGO_SHOPPING = 103;
	public static final int CODIGO_ESPECTACULO = 104;
	public static final int CODIGO_CAFETERIA = 105;
	public static final int CODIGO_CERVECERIA = 106;
	
	
	private SQLiteDatabase myDb = null;
	private Context miContexto = null;
	
	
	public DatabaseHelper(Context contexto) {
		super(contexto, Constantes.DATABASE_NAME, null, 1);
		
		miContexto = contexto;
		
	}


	
	
	/**
	 * Recupera los datos específicos de un hotel
	 * 
	 * @param idPoi
	 * @return cursor con todos los campos de la tabla
	 * 
	 */
	public Cursor recuperarHotel(int idPoi){
		
		Cursor c = myDb.rawQuery("select _id, estrellas, url_booking, url_hotel " +
				"from hotel " +
				"where _id = " + idPoi, null); 

		return c;
		
	}
	
	
	
	/**
	 * Recupera los datos de un poi genérico y los devuelve en un objeto de tipo Cursor
	 * 
	 * @param idPoi
	 * @return Cursor
	 */
	public Cursor recuperarPoi(int idPoi){
		
		Cursor c = myDb.rawQuery("select _id, latitud, longitud, nombrePoi, descPoi, categoria, direccion, telefono, uriImagen " +
				"from poi " +
				"where _id = " + idPoi, null); 

		return c;
		
	}
	
	
	/**
	 * Recupera los pois de la ruta que se le envía como parámetro (le enviamos el
	 * código numérico que tenemos en el campo _id de la tabla ruta en la bd).
	 * 
	 * @param idRuta: clave de la ruta, entero
	 * @return Cursor con los pois que componen la ruta
	 */
	/* TODO: devolver una lista en lugar de un cursor? Así evitamos dependencias del sqlite
	   en la clase llamadora, pero quizá sea un trabajo inútil porque al final vamos a volcar
	   los datos en el mapa y no volveremos a usarlos 
	*/
	public Cursor recuperarPoisRuta(int idRuta){

		// Query para recuperar los pois de lar ruta completa
		Cursor c = myDb.rawQuery("select a._id, a.latitud, a.longitud, a.nombrePoi, a.descPoi, a.categoria, a.direccion " +
								"from poi a, poiruta c " +
								"where c.idRuta = " + idRuta + " and " +
								"c.idPoi = a._id " +
								"order by descPoi", null); 

		return c;
		
	}

	
	/**
	 * Recupera los datos de un idRuta de la tabla de rutas
	 * 
	 * @param idRuta
	 * @return
	 */
	public Cursor recuperarRuta(int idRuta){

		// Query para recuperar los pois de lar ruta completa
		Cursor c = myDb.rawQuery("select _id, nombreRuta, descRuta, default_latitude, default_longitude, default_zoom_level " +
								"from ruta " +
								"where _id = " + idRuta, null); 

		return c;
		
	}


	/**
	 * Recupera los pois asociados a los pubs en la bd
	 * 
	 * @return
	 */
	public Cursor recuperarPubs(){

		Cursor c = myDb.rawQuery("select _id, latitud, longitud, nombrePoi, descPoi, categoria, direccion " +
								"from poi " +
								"where categoria = " + CODIGO_CERVECERIA, null); 

		return c;
		
	}
	

	/**
	 * Recupera los pois asociados a los restaurantes en la bd
	 * 
	 * @return
	 */
	public Cursor recuperarRestaurantes(){

		Cursor c = myDb.rawQuery("select _id, latitud, longitud, nombrePoi, descPoi, categoria, direccion " +
								"from poi " +
								"where categoria = " + CODIGO_RESTAURANTE, null); 

		return c;
		
	}
	

	/**
	 * Recupera los pois asociados a los hoteles en la bd
	 * 
	 * @return
	 */
	public Cursor recuperarHoteles(){

		Cursor c = myDb.rawQuery("select poi._id, hotel._id, latitud, longitud, nombrePoi, descPoi, categoria, direccion, telefono, hotel.estrellas, hotel.url_booking " +
								"from poi, hotel " +
								"where categoria = " + CODIGO_HOTEL + " and " +
								"      hotel._id = poi._id " +
								"order by hotel.estrellas desc", null); 

		return c;
		
	}
	
	
	
	
	/**
	 * Devuelve el nombre de la carpeta de documentación asociada a un poi
	 * 
	 * @param clavePoi
	 * @return
	 */
	public String obtenerUriImagenPoi(int clavePoi){
		
		Cursor c = myDb.rawQuery("select uriImagen from poi where _id = " + clavePoi, null);
		
		c.moveToFirst();
		String ruta = c.getString(c.getColumnIndex("uriImagen"));
		return ruta;
		
	}
	
	
	
	/**
	 * Recupera los datos específicos de un restaurante desde la tabla "restaurante" a partir
	 * de la clave
	 * 
	 * @param idRestaurante
	 * @return
	 */
	public Cursor detalleRestaurante(int idRestaurante){
		
		Cursor c = myDb.rawQuery("select _id, tipo_cocina, precio_medio, url_reserva, especialidad " +
				"from restaurante " +
				"where _id = " + idRestaurante, null); 

		return c;
		
	}
	
	
	
	//-----------------------------------------------------------------------------------
    // Crea una bd vacía en el dispositivo y copia la que tenemos en assets
	//
	//-----------------------------------------------------------------------------------
	public void createDataBase() throws IOException{
		 
    	boolean dbExist = checkDataBase();   // comprueba si existe la bd en el dispositivo
 
    	if(dbExist){
    		// la borramos
        	String outFileName = Constantes.DB_PATH + Constantes.DATABASE_NAME;
        	File fichero = new File(outFileName);
        	fichero.delete();
    	}
 
    	// Crea una bd vacia en la ruta adecuada para que la encuentre la aplicación
        this.getReadableDatabase();

        try {
    		copyDataBase();
    	} catch (IOException e) {
    			throw new Error("Error copiando la base de datos");
    	  }
   	
    }
	
	//-----------------------------------------------------------------------------------
	// Comprueba si existe la bd en el dispositivo
	//
	//-----------------------------------------------------------------------------------
    private boolean checkDataBase(){
 
    	SQLiteDatabase checkDB = null;
 
    	try{
    		String myPath = Constantes.DB_PATH + Constantes.DATABASE_NAME;
    		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
 
    	}catch(SQLiteException e){
 
    		// La bd no existe
 
    	}
    	// La bd existe => la cerramos y no hacemos nada
    	if(checkDB != null){
    		checkDB.close();
    	}
 
    	return checkDB != null ? true : false;
    }
 
    
	//-----------------------------------------------------------------------------------
    // Copia la bd desde la carpeta de assets al dispositivo
    //
    //-----------------------------------------------------------------------------------
    private void copyDataBase() throws IOException{
 
    	//Open your local db as the input stream
    	InputStream myInput = miContexto.getAssets().open(Constantes.DATABASE_NAME);
 
    	// Path to the just created empty db
    	String outFileName = Constantes.DB_PATH + Constantes.DATABASE_NAME;
 
    	//Open the empty db as the output stream
    	OutputStream myOutput = new FileOutputStream(outFileName);
 
    	//transfer bytes from the inputfile to the outputfile
    	byte[] buffer = new byte[1024];
    	int length;
    	while ((length = myInput.read(buffer))>0){
    		myOutput.write(buffer, 0, length);
    	}
 
    	//Close the streams
    	myOutput.flush();
    	myOutput.close();
    	myInput.close();
 
    }
	
	//-----------------------------------------------------------------------------------
    // Abre la base de datos y guarda la referencia en una variable de instancia
    //
    //-----------------------------------------------------------------------------------
    public void openDataBase() throws SQLException{
    	 
    	//Open the database
        String myPath = Constantes.DB_PATH + Constantes.DATABASE_NAME;
    	myDb = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
 
    }    
    
    
	//-----------------------------------------------------------------------------------
    // Cierra la base de datos
    //
    //-----------------------------------------------------------------------------------
    @Override
	public synchronized void close() {
 
    	    if(myDb != null)
    		    myDb.close();
 
    	    super.close();
 
	}
    
    
    
    
    
	@Override
	public void onCreate(SQLiteDatabase db) {
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//		android.util.Log.w("rutasCoruna", "Upgrading database, which will destroy all	old data");
//		db.execSQL("DROP TABLE IF EXISTS rutasCoruna");
//		onCreate(db);
	}
	
}

