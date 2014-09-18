package datos;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

class LeerDatos {
		
   public static void main(String [] arg) {
      File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;
      ArrayList<Valor> valores = new ArrayList<Valor>();
      Valor valor = new Valor();
      long l = 10;
 
      //File f = new File("ruta.txt"); // Creamos un objeto file
	  //System.out.println(f.getAbsolutePath()); // Llamamos al método que devuelve la ruta absoluta
		
      try {
         // Apertura del fichero y creacion de BufferedReader para poder
         // hacer una lectura comoda (disponer del metodo readLine()).
         archivo = new File ("C:/Users/VLB2831G/git/trading/calculos/src/datos/archivo.txt");
         fr = new FileReader (archivo);
         br = new BufferedReader(fr);
 
         // Lectura del fichero
         String linea;
         
         int i = 1;
         while((linea=br.readLine())!=null){
        	//Thread.sleep(1000);
        	// LLenar valor

        	if(i>17){
        	 String[] arrayLinea = linea.split(",");
        	 long fechaUnix = Long.parseLong(arrayLinea[0]);
//        	 System.out.println("Fecha: " + new Date(fechaUnix*1000).toString() + " Valor: " + arrayLinea[1] + " Volumen: " + arrayLinea[5]);
        	 valor = new Valor(Long.parseLong(arrayLinea[5]), Double.parseDouble(arrayLinea[1]), new Date(fechaUnix*1000));
        	 valores.add(valor);
        	 System.out.println(valor.toString());
        	}
        	i++;
         }
      }
      
      catch(Exception e){
         e.printStackTrace();
      }finally{
         // En el finally cerramos el fichero, para asegurarnos
         // que se cierra tanto si todo va bien como si salta
         // una excepcion.
         try{                   
            if( null != fr ){  
               fr.close();    
            }                 
         }catch (Exception e2){
            e2.printStackTrace();
         }
      }
   }
}