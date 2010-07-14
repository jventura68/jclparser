//------------------------------------------------------------------------------------------
// JclParser
// =========
// Extrae de un jcl:
//   - Nombre de la cadena
//	 - Número de paso
//   - Nombre del programa
//   - Ficheros usados por el programa 
//------------------------------------------------------------------------------------------
package org.rlnieto.jclparser.base;

import java.sql.ResultSet;
import java.util.*;
import java.io.*;
import java.util.regex.*;

import org.rlnieto.jclparser.utilidades.AccesoDatos;
import org.rlnieto.jclparser.entidades.*;


public class JclParser {
	
	//--------------------------------------------------------------------------------------
	// Constructor
	//--------------------------------------------------------------------------------------
	public JclParser(String vJcl){
		
		String nombreCadena = "";
		String nombrePrograma = "";
		String numeroPaso = "";
		ArrayList<FicheroJcl> listaFicheros = new ArrayList<FicheroJcl>();
		PasoJcl pasoJcl = null;
		
		String linea = "";
		
		Pattern patronNombreCadena = Pattern.compile("//([a-zA-Z0-9]+)[ ]*JOB");
		Pattern patronNumeroPaso = Pattern.compile("//[^\\*]*(PASO[0-9]+)[ ]+");
		Pattern patronPrograma = Pattern.compile("//[^\\*]*((PROGRAM|PGM)[ ]*=)([a-zA-Z0-9 ]+)");
		Pattern patronProgramaSistema = Pattern.compile("[^\\*]*(EXEC)([a-zA-Z0-9 ]+),");
		Pattern patronFicheroLogico = Pattern.compile("//([a-zA-Z0-9]{1,8})([ ]*DD[ ]*DSN=)([&a-zA-Z0-9.]+)(,DISP=[(]?)(([a-zA-Z]*)[,]*([a-zA-Z]*))");
		Pattern patronListado = Pattern.compile("//([a-zA-Z0-9\\.]{1,17})([ ]*DD[ ]*SYSOUT=)([&a-zA-Z0-9.]+)");
//		Pattern patronFinBloque = Pattern.compile("^/\\*[ ]*$");
		Pattern patronNuevoBloque = Pattern.compile("//[^\\*]*[ ]*EXEC[ ]*");
		
		
		
		
		//----------------------------------------------------------------------------------
		// leemos el fichero con el jcl linea a linea
		//----------------------------------------------------------------------------------
		try{
		  FileReader fr = new FileReader(vJcl);
		  BufferedReader br = new BufferedReader(fr);
		  
		  while((linea = br.readLine()) != null){
			  // Si es el inicio de un nuevo bloque grabamos los datos del bloque anterior. En la primera
			  // iteración no hay datos que mostrar, por eso añadimos la segunda condición, porque la 
			  // primera vez no tendremos el numero de paso (ni el nombre del programa)
			  Matcher mPatronNuevoBloque = patronNuevoBloque.matcher(linea);
			  if(mPatronNuevoBloque.find() && numeroPaso.length()>0){

				  pasoJcl = new PasoJcl(numeroPaso, nombrePrograma, listaFicheros);
				  
				  
				  System.out.println(nombreCadena + " - " + numeroPaso + " - " + nombrePrograma);
				  
				  Iterator<FicheroJcl> it = listaFicheros.iterator();
				  while(it.hasNext()){
					  FicheroJcl fjclAux = (FicheroJcl)it.next();
					  
					  System.out.println("  -->" + fjclAux.getNombreFisico() + ", " + fjclAux.getNombreLogico() + ", " + fjclAux.getDisp());
					  
					  fjclAux = null;
				  }

				  // guardamos el paso en la bd
				  insertarPaso(nombreCadena, pasoJcl);
				  
				  it = null;
				  listaFicheros.clear();
			  }				 
			  
			  // comprobamos si aparece el nombre de la cadena
			  Matcher mPatronNombreCadena = patronNombreCadena.matcher(linea);
			  if(mPatronNombreCadena.find()){
				  nombreCadena = mPatronNombreCadena.group(1);
				  System.out.println("Cadena: " + nombreCadena);
			  }
			  
			  // Buscamos el número de paso (el proceso es muy mejorable...)
			  Matcher mPatronNumeroPaso = patronNumeroPaso.matcher(linea);
			  if(mPatronNumeroPaso.find()){
				  numeroPaso = mPatronNumeroPaso.group(1);
			  }
			  
			  
			  // Buscamos el nombre del programa, que puede ser un programa "normal" (la línea incluye
			  // "EXEC y PROGRAM") o una utilidad del sistema (incluye "EXEC" pero no "PROGRAM")
			  Matcher mPatronPrograma = patronPrograma.matcher(linea);
			  if(mPatronPrograma.find()){
				  nombrePrograma = mPatronPrograma.group(3);
			  }else{
				  Matcher mPatronProgramaSistema = patronProgramaSistema.matcher(linea);
				  if(mPatronProgramaSistema.find()){
					  nombrePrograma = mPatronProgramaSistema.group(2);
				  }  
			  }

			  // comprobamos si aparece un nombre de fichero
			  Matcher mPatronFicheroLogico = patronFicheroLogico.matcher(linea);
			  if(mPatronFicheroLogico.find()){
				  
//				  listaFicheros.add(new FicheroJcl(mPatronFicheroLogico.group(1), mPatronFicheroLogico.group(3), mPatronFicheroLogico.group(5)));
				  String dispAux = mPatronFicheroLogico.group(6);
				  if(dispAux.length() == 0){
					  dispAux = mPatronFicheroLogico.group(7);
				  }
				  
				  listaFicheros.add(new FicheroJcl(mPatronFicheroLogico.group(3), mPatronFicheroLogico.group(1), dispAux));
			  }
		
			  // comprobamos si aparece algun listado y en lugar del nombre físico guardamos la clase con disp en blanco
			  Matcher mPatronListado = patronListado.matcher(linea);
			  if(mPatronListado.find()){
				  listaFicheros.add(new FicheroJcl(mPatronListado.group(3), mPatronListado.group(1), ""));
			  }
			  
			  
		  }   // while tratamos el fichero
		  
		  // imprimimos los datos del ultimo bloque
		  System.out.println(nombreCadena + " - " + numeroPaso + " - " + nombrePrograma);
		  
		  Iterator<FicheroJcl> it = listaFicheros.iterator();
		  while(it.hasNext()){
			  FicheroJcl fjclAux = (FicheroJcl)it.next();
			  
			  System.out.println("  -->" + fjclAux.getNombreFisico() + ", " + fjclAux.getNombreLogico() + ", " +fjclAux.getDisp());
			  
			  fjclAux = null;
		  }
		  
		  it = null;
		  listaFicheros.clear();

		  
		}catch(Exception e){System.out.println(e.getMessage());}
		
	}
	

	//--------------------------------------------------------------------------------------
	// insertarPaso
	// ============
	// Inserta en la bd los datos de un paso de una cadena
	//--------------------------------------------------------------------------------------
	private void insertarPaso(String vNombreCadena, PasoJcl vPasoJcl){
		AccesoDatos ad = null;
		String lszLaQuery = "";
		
		// insertamos los datos de la cadena
		lszLaQuery = "INSERT INTO jcl " +
					 "VALUES ('" + vNombreCadena + "', '')"; 

		ad = new AccesoDatos("localhost", "jcl", "root", "alamierda");
		ad.Insertar(lszLaQuery);
		
		// insertamos los datos del programa
		lszLaQuery = "INSERT INTO programa " +
		 "VALUES ('" + vPasoJcl.getNombrePrograma() + "', '')"; 

		ad.Insertar(lszLaQuery);
		
		// damos de alta las relaciones cadena-programa
		lszLaQuery = "INSERT INTO jcl_has_programa " +
		 "VALUES ('" + vNombreCadena + "', '" + vPasoJcl.getNombrePrograma() + "')"; 

		ad.Insertar(lszLaQuery);

		// insertamos los datos de los pasos
		lszLaQuery = "INSERT INTO paso " +
		 "VALUES ('" + vPasoJcl.getNombrePaso() + "' , '', '" + vNombreCadena + "' , '" + vPasoJcl.getNombrePrograma() + "')"; 

		ad.Insertar(lszLaQuery);

		// insertamos los nombres de los ficheros físicos y damos de alta su relación con el 
		// paso correspondiente
		Iterator<FicheroJcl> it = vPasoJcl.getListaFicheros().iterator();
		while(it.hasNext()){

			FicheroJcl fjcl_aux = (FicheroJcl)it.next();
			
			// alta del fichero físico
			lszLaQuery = "INSERT INTO fichero " +
			 "VALUES ('" + ((FicheroJcl)fjcl_aux).getNombreFisico() + "', '')"; 
			
			ad.Insertar(lszLaQuery);
			System.out.println("lalala");		

			// alta de la relación del fichero físco con el paso
			lszLaQuery = "INSERT INTO paso_has_fichero " +
			 "VALUES ('" + vPasoJcl.getNombrePaso() + "', '" +
			 			vNombreCadena + "', '" +
			 			vPasoJcl.getNombrePrograma() + "', '" +  
			 			((FicheroJcl)fjcl_aux).getNombreFisico() + "', '" +  
			 			((FicheroJcl)fjcl_aux).getNombreLogico() + "', '" +
			 			((FicheroJcl)fjcl_aux).getDisp() + "')"; 

			ad.Insertar(lszLaQuery);
		}
		
		// cerramos la conexión con la bd
		ad.cerrar();

	}

	
	 
	//--------------------------------------------------------------------------------------
	// Main
	//--------------------------------------------------------------------------------------
//	public static void main(String argv[]){
//		JclParser parser = new JclParser("/home/guig/Desktop/cadenas/VL01935E.txt");
//		System.out.print("Proceso finalizado");
//	}

}
