package org.rlnieto.jclparser.utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AccesoDatos {

	public String query = null;
	public Exception error = null;
	
	private String pDriver = "com.mysql.jdbc.Driver";
	private String pUrlServidor = null; 
    private Connection pConexion = null;
	private String pUsuario = null;
	private String pClave = null;
	
	// Establecemos la conexión con la BD al crear el objeto
	public AccesoDatos(String vServidor, String vBd, String vUsuario, String vClave){
    
		// Cargamos las variables de instancia con los valores apropiados
		pUrlServidor = "jdbc:mysql://" + vServidor + ":3306/" + vBd;
		pUsuario = vUsuario;
	    pClave = vClave;

	    try{
			Class.forName(pDriver);  // registramos el manejador
			pConexion = DriverManager.getConnection(pUrlServidor, pUsuario, pClave);  // conectamos
		}catch(Exception e){e.printStackTrace();}

	 
	}
	

	//---------------------------------------------------------------------------------------
	// Consulta
	// --------
	// Ejecuta una consulta y devuelve un resultset con los datos (todo ok) o vacío (error)
	//---------------------------------------------------------------------------------------
	public ResultSet Consultar(String vConsulta){
		ResultSet lRs = null;
		
		Statement lInstruccionSql = null;
		
		// Intentamos conectar
		try{
			// Ejecutamos la consulta
			lInstruccionSql = pConexion.createStatement();
			lRs = lInstruccionSql.executeQuery(vConsulta);			
			lInstruccionSql.close();
		}catch(Exception e){e.printStackTrace();}
		
		return lRs;
	}
	
	//---------------------------------------------------------------------------------------
	// Inserción
	// --------
	// Ejecuta una instrucción de inserción
	//---------------------------------------------------------------------------------------
	public void Insertar(String vConsulta){
		
		Statement lInstruccionSql = null;
		
		// Intentamos conectar
		try{
			// Ejecutamos la consulta
			lInstruccionSql = pConexion.createStatement();
			Boolean lEjecucionCorrecta = lInstruccionSql.execute(vConsulta);			
			lInstruccionSql.close();
		}catch(Exception e){System.out.println("Error insertando: " + e.getMessage());}
		
	}
	
	//---------------------------------------------------------------------------------------
	// Cerrar
	// ------
	// Cerramos la conexion liberando los recursos
	//---------------------------------------------------------------------------------------
	public void cerrar(){
		try{
			pConexion.close();

			query = null;
			error = null;
			pDriver = null;
			pUrlServidor = null; 
		    pConexion = null;
			pUsuario = null;
			pClave = null;
		
		}catch(Exception e){e.printStackTrace();}
	}
	
}
