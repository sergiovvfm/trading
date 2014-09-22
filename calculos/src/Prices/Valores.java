package Prices;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;

public class Valores {

	private ArrayList<Valor> valores;
	private long fechaGuardada;
		
	public Valores() {
		
		valores = new ArrayList<Valor>();
	}
	
	public Valores(ArrayList<Valor> valores) {
		super();
		this.valores = valores;
	}

	public ArrayList<Valor> getValores() {
		return valores;
	}

	public void setValores(ArrayList<Valor> valores) {
		this.valores = valores;
	}


	public void addPrice(Valor valor)
	{
		valores.add(valor);
	}
	
	public void leerValores() {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			// Apertura del fichero y creacion de BufferedReader para poder
			// hacer una lectura comoda (disponer del metodo readLine()).
			archivo = new File ("C:/Users/VLB2831G/git/trading/calculos/src/Prices/tickers.txt");
			fr = new FileReader (archivo);
			br = new BufferedReader(fr);
		 
			// Lectura del fichero
			String linea;
		         
			int i = 1;
			while((linea=br.readLine())!=null){

				if(i>8){
					valores.add(leerLineaGoogle(linea));
					System.out.println(leerLineaGoogle(linea).toString());
					Thread.sleep(100);
				}
				i++;
			}
		}catch(Exception e){
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

	private Valor leerLineaGoogle(String linea) {
			   
		String[] arrayLinea = linea.split(",");
		long fechaUnix;
		Valor valor = new Valor();
		
		if(arrayLinea[0].substring(0, 1).equalsIgnoreCase("a")){
			fechaGuardada = Long.parseLong(arrayLinea[0].substring(1));
			fechaUnix = fechaGuardada;
		}else{
			fechaUnix = ((Long.parseLong(arrayLinea[0])*60) + (fechaGuardada));
		}
		
		
		valor = new Valor(Long.parseLong(arrayLinea[5]), Double.parseDouble(arrayLinea[1]), new Date(fechaUnix*1000));
			
		return valor;
	}

	
	@Override
	public String toString()
	{
		String strReturn = "";
		strReturn += "POS\tPrice\t\tBolUP\t\tBolDown\n";
		for(int i = 0; i < valores.size(); i++)
		{
			strReturn += "(" + (i + 1) + ")\t" + valores.get(i).toString() + "\n";
		}
		
		return strReturn;
	}
	
}
