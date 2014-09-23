package Prices2;

import java.util.ArrayList;

public class PricesManager {
	private static PricesManager pInstance;
	private ArrayList<Valores> valoresManager;
	
	private PricesManager() {
		valoresManager = new ArrayList<Valores>();
	}
		
	public PricesManager getInstance(){
		if(pInstance == null){
			pInstance = new PricesManager();
		}
		return pInstance;
	}
	
	public void llenarValores() {
		Valores valores = new Valores();
		valores.leerValores(TFN, fecha, tipo);
		valoresManager.add(valores);
	}
	

}
