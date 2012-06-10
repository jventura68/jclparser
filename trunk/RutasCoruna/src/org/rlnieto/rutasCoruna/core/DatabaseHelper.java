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
//import android.widget.Toast;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;


public class DatabaseHelper extends SQLiteOpenHelper{

	
	// Constantes para las categorías de los pois
	// TODO: pensar cómo codificar y guardar los códigos de las categorías de los pois
	private static final int CODIGO_MUSEO = 1;
	private static final int CODIGO_IGLESIA = 2;
	private static final int CODIGO_MONUMENTO = 3;
	private static final int CODIGO_PAISAJE = 4;
	
	// Los pois comerciales tienen código >= 100
	private static final int CODIGO_HOTEL = 100;
	private static final int CODIGO_RESTAURANTE = 101;
	private static final int CODIGO_OCIO_NOCTURNO = 102;
	private static final int CODIGO_SHOPPING = 103;
	private static final int CODIGO_ESPECTACULO = 104;
	private static final int CODIGO_CAFETERIA = 105;
	private static final int CODIGO_CERVECERIA = 106;
	
	
	
	private static String DB_PATH = "/data/data/org.rlnieto.rutasCoruna/databases/";
	private static final String DATABASE_NAME="rutasCoruna.sqlite";
	//private static final int VERSION_BD = 1;
	
	private SQLiteDatabase myDb = null;
	private Context miContexto = null;
	
	
	public DatabaseHelper(Context contexto) {
		super(contexto, DATABASE_NAME, null, 1);
		
		miContexto = contexto;
		
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
	public Cursor recuperarRuta(int idRuta){

		// Query para recuperar los pois de lar ruta completa
		Cursor c = myDb.rawQuery("select a._id, a.latitud, a.longitud, a.nombrePoi, a.descPoi, a.categoria " +
								"from poi a, poiruta c " +
								"where c.idRuta = " + idRuta + " and " +
								"c.idPoi = a._id " +
								"order by descPoi", null); 

		return c;
		
	}


	/**
	 * Recupera los pois asociados a los pubs en la bd
	 * 
	 * @return
	 */
	public Cursor recuperarPubs(){

		Cursor c = myDb.rawQuery("select _id, latitud, longitud, nombrePoi, descPoi, categoria " +
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

		Cursor c = myDb.rawQuery("select _id, latitud, longitud, nombrePoi, descPoi, categoria " +
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

		Cursor c = myDb.rawQuery("select _id, latitud, longitud, nombrePoi, descPoi, categoria, direccion, telefono " +
								"from poi " +
								"where categoria = " + CODIGO_HOTEL, null); 

		return c;
		
	}
	
	
	
	
	/**
	 * Devuelve el nombre de la carpeta de documentación asociada a un poi
	 * 
	 * @param clavePoi
	 * @return
	 */
	public String obtenerCarpetaDocsPoi(int clavePoi){
		
		Cursor c = myDb.rawQuery("select carpetaDocs from poi where _id = " + clavePoi, null);
		
		c.moveToFirst();
		String ruta = c.getString(c.getColumnIndex("carpetaDocs"));
		return ruta;
		
	}
	
	
	
	//-----------------------------------------------------------------------------------
    // Crea una bd vacía en el dispositivo y copia la que tenemos en assets
	//
	//-----------------------------------------------------------------------------------
	public void createDataBase() throws IOException{
		 
    	boolean dbExist = checkDataBase();   // comprueba si existe la bd en el dispositivo
 
    	if(dbExist){
    		// la borramos
        	String outFileName = DB_PATH + DATABASE_NAME;
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
    		String myPath = DB_PATH + DATABASE_NAME;
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
    	InputStream myInput = miContexto.getAssets().open(DATABASE_NAME);
 
    	// Path to the just created empty db
    	String outFileName = DB_PATH + DATABASE_NAME;
 
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
        String myPath = DB_PATH + DATABASE_NAME;
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

		// Tabla de rutas
//		db.execSQL("CREATE TABLE ruta (_idRuta INTEGER PRIMARY KEY, nombreRuta TEXT, descripcionRuta TEXT);");
//		ContentValues cv=new ContentValues();
//		
//		cv.put(ID_RUTA, 1);
//		cv.put(NOMBRE_RUTA, "Ruta cultural");
//		cv.put(DESCRIPCION_RUTA, "Visita de los monumentos típicos de A Coruña");
//		db.insert("ruta", NOMBRE_RUTA, cv);
//		
//
//		// Tabla de pois
//		db.execSQL("CREATE TABLE poi (_idPoi INTEGER PRIMARY KEY, latitudPoi REAL, longitudPoi REAL, nombrePoi TEXT, descripcionPoi TEXT);");
//		cv.clear();
//		
//		cv.put("_idPoi", 1);
//		cv.put("latitudPoi", 43.367715);
//		cv.put("longitudPoi", -8.407604);
//		cv.put("nombrePoi", "Plaza de Pontevedra");
//		cv.put("descripcionPoi", "Descripción de la plaza de Pontevedra");
//		db.insert("poi", "nombrePoi", cv);
//		
//		
//		cv.put("_idPoi", 2);
//		cv.put("latitudPoi",  43.371334);
//		cv.put("longitudPoi", -8.396001);
//		cv.put("nombrePoi", "Plaza de María Pita");
//		cv.put("descripcionPoi", "Descripción plaza de María Pita");
//		db.insert("poi", "nombrePoi", cv);
//		
//		
//		cv.put("_idPoi", 3);
//		cv.put("latitudPoi", 43.385152);
//		cv.put("longitudPoi", -8.398533);
//		cv.put("nombrePoi", "Torre de Hércules");
//		cv.put("descripcionPoi", "Descripción torre de Hércules");
//		db.insert("poi", "nombrePoi", cv);
//		
//		
//		cv.put("_idPoi", 4);
//		cv.put("latitudPoi", 43.366062);
//		cv.put("longitudPoi", -8.387547);
//		cv.put("nombrePoi", "Castillo de San Antón");
//		cv.put("descripcionPoi", "Descripción castillo de san Antón");
//		db.insert("poi", "nombrePoi", cv);
//		
//
//		// Tabla de relación rutas/pois
//		db.execSQL("CREATE TABLE poiRuta (_idRuta INTEGER, _idPoi INTEGER);");
//
//		cv.clear();
//		
//		cv.put("_idRuta", 1);
//		cv.put("_idPoi", 1);
//		db.insert("poiRuta", "", cv);
//		
//		cv.put("_idRuta", 1);
//		cv.put("_idPoi", 2);
//		db.insert("poiRuta", "", cv);
//		
//		cv.put("_idRuta", 1);
//		cv.put("_idPoi", 3);
//		db.insert("poiRuta", "", cv);
//		
//		cv.put("_idRuta", 1);
//		cv.put("_idPoi", 4);
//		db.insert("poiRuta", "", cv);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//		android.util.Log.w("rutasCoruna", "Upgrading database, which will destroy all	old data");
//		db.execSQL("DROP TABLE IF EXISTS rutasCoruna");
//		onCreate(db);
	}
	
}











