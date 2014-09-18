package Indicator;
import java.util.ArrayList;

import Prices.Price;

/**
 * clase que se encarga de realizar los cálculos para la obtebción de los balores de las banda de bolinger
 */
public class BollingerBands 
{
	/**
	 * Calcula el valor de las bandas de Bollinger para los valores de un array de precios
	 * @param alPrices Array con los precios
	 */
	public static void all(ArrayList<Price> alPrices)
	{
		double dbSMA20;
		double dbStDev20;
		double dbBollingerUp;
		double dbBollingerDown;
		for(int i = 0; i < alPrices.size(); i++)
		{
			dbSMA20 = SMA20(alPrices, i);
			dbStDev20 = desvEst20(alPrices, i, dbSMA20);
			dbBollingerUp = BollingerUp(dbSMA20, dbStDev20);
			alPrices.get(i).setBollingerUp(dbBollingerUp);
			dbBollingerDown = BollingerDown(dbSMA20, dbStDev20);
			alPrices.get(i).setBollingerDown(dbBollingerDown);
		}
	}

	/**
	 * Calcula el valor de las bandas de Bollinger para el ultimo valor de un array de precios
	 * @param alPrices Array con los precios
	 */
	public static void last(ArrayList<Price> alPrices)
	{
		double dbSMA20;
		double dbStDev20;
		double dbBollingerUp;
		double dbBollingerDown;
		int nLastPos = alPrices.size() - 1;
		dbSMA20 = SMA20(alPrices, nLastPos);
		dbStDev20 = desvEst20(alPrices, nLastPos, dbSMA20);
		dbBollingerUp = BollingerUp(dbSMA20, dbStDev20);
		alPrices.get(nLastPos).setBollingerUp(dbBollingerUp);
		dbBollingerDown = BollingerDown(dbSMA20, dbStDev20);
		alPrices.get(nLastPos).setBollingerDown(dbBollingerDown);
	}
			  
	/**
	 * Calcula el valor de media simple SMA de 20 posiciones para una posición del array de precios
	 * @param alPrices Array con los precios
	 * @param nPos Posición a calcular
	 * @return Valor calculado de la media
	 */
	private static double SMA20(ArrayList<Price> alPrices, int nPos)
	{
		double dbValue = 0;
		int nPosIni;
		int nPosEnd;
		int nCnt = 0;
		nPosIni = (nPos + 1) - 20;
		
		/* si la psoción incial calculada no existe, se escoge la primera */
		if(nPosIni < 0)
			nPosIni = 0;
		nPosEnd = nPos;
		while((nPosIni + nCnt) <= nPosEnd)	
		{
			dbValue += alPrices.get(nPosIni + nCnt).getPrice();
			nCnt++;	
		}
		dbValue = dbValue / nCnt;			
		return dbValue;
	}
	
	/**
	 * Calcula el valor de la desviación estandar (tipica) de 20 posiciones para una posición del array de precios
	 * @param alPrices Array con los precios
	 * @param nPos Posición a calcular
	 * @param dbSMA20 Valor de la media simple de 20 posiciones
	 * @return Valor calculado de la media
	 */
	private static double desvEst20(ArrayList<Price> alPrices, int nPos, double dbSMA20)
	{
		double dbValue = 0;
		double dbDiff = 0;
		int nPosIni;
		int nPosEnd;
		int nCnt = 0;
		nPosIni = (nPos + 1) - 20;
		
		/* si la psoción incial calculada no existe, se escoge la primera */
		if(nPosIni < 0)
			nPosIni = 0;
		nPosEnd = nPos;
		while((nPosIni + nCnt) <= nPosEnd)	
		{
			dbDiff = alPrices.get(nPosIni + nCnt).getPrice() - dbSMA20;
			dbValue += Math.pow(dbDiff, 2);
			nCnt++;	
		}
		/* se calcula la media de las diferencias al cuadrado*/
		dbValue = dbValue / nCnt;	
		
		/* se hace la raiz cuadrada del resultado */
		dbValue = Math.sqrt(dbValue);
		return dbValue;
	}
	
	/**
	 * Obtiene el balor superior de la banda
	 * @param dbSMA20 Valor de la media simple de 20 posiciones
	 * @param dbStDev20 Valor de la desviación estandar (tipica) de 20 posiciones
	 * @return valor de la banda superior
	 */
	private static double BollingerUp(double dbSMA20, double dbStDev20)
	{
		double dbReturn;
		dbReturn = dbSMA20 + (2 * dbStDev20); 
		return dbReturn;
	}	
	
	/**
	 * Obtiene el balor inferior de la banda
	 * @param dbSMA20 Valor de la media simple de 20 posiciones
	 * @param dbStDev20 Valor de la desviación estandar (tipica) de 20 posiciones
	 * @return valor de la banda inferior
	 */
	private static double BollingerDown(double dbSMA20, double dbStDev20)
	{
		double dbReturn;
		dbReturn = dbSMA20 - (2 * dbStDev20); 
		return dbReturn;
	}		
	
}
