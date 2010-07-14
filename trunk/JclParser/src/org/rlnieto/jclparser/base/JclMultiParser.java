//------------------------------------------------------------------------------------------
// JclMultiParser
// ==============
// Analiza un conjunto de ficheros con jcls y extrae para cada uno los siguientes datos:
//   - Nombre de la cadena
//	 - Número de paso
//   - Nombre del programa
//   - Ficheros usados por el programa 
//------------------------------------------------------------------------------------------
package org.rlnieto.jclparser.base;

import java.io.File;

public class JclMultiParser {

	//--------------------------------------------------------------------------------------
	// Constructor
	//--------------------------------------------------------------------------------------
	public JclMultiParser(String vDirectorio){
		analizarDirectorio(vDirectorio);
	}

	//--------------------------------------------------------------------------------------
	// analizarDirectorio
	// ================
	// Recorre un directorio parseando todos sus ficheros
	//--------------------------------------------------------------------------------------
	private void analizarDirectorio(String vDirectorio)
	{
		File lDirectorio = new File(vDirectorio);
		Integer i = 0;

		String[] lFicheros = lDirectorio.list();

		if(lFicheros == null)
		{
			System.out.println("El directorio " + lDirectorio.toString() + " está vacío o no existe");
		}
		else
		{
			// parseamos todos los ficheros del directorio
			for(i=0;i<lFicheros.length;i++)
			{
				System.out.println("-------------------------------------------------------------------------");
				JclParser jclp = new JclParser(vDirectorio + lFicheros[i]);
				
			}
		}
	}
	
	
	//--------------------------------------------------------------------------------------
	// main
	//--------------------------------------------------------------------------------------
	public static void main(String[] argv){
		JclMultiParser mp = new JclMultiParser("/home/guig/Desktop/cadenas/");
		
	}
	
}
