package Indicator;
import java.util.ArrayList;

import Prices.Price;

/**
 * clase que se encarga de realizar los cálculos para la obtebción de medias simples de valores SMA
 */
public class SMA 
{
	/**
	 * Calcula el valor de las media simples para los valores de un array de precios
	 * @param alPrices Array con los precios
	 * @param nShortAverge Periodo de la media corta
	 * @param nLongAverage Periodo de la media larga
	 */
	public static void all(ArrayList<Price> alPrices, int nShortAverge, int nLongAverage)
	{
		double dbValue;
		for(int i = 0; i < alPrices.size(); i++)
		{
			dbValue = SMA_Value(alPrices, i, nShortAverge);
			alPrices.get(i).setSMA_Short(dbValue);
			dbValue = SMA_Value(alPrices, i, nLongAverage);
			alPrices.get(i).setSMA_Long(dbValue);
		}
	}

	/**
	 * Calcula el valor de las media simples para el ultimo valor de un array de precios
	 * @param alPrices Array con los precios
	 * @param nShortAverge Periodo de la media corta
	 * @param nLongAverage Periodo de la media larga
	 */
	public static void last(ArrayList<Price> alPrices, int nShortAverge, int nLongAverage)
	{
		double dbValue;
		int nLastPos = alPrices.size() - 1;
		dbValue = SMA_Value(alPrices, nLastPos, nShortAverge);
		alPrices.get(nLastPos).setSMA_Short(dbValue);
		dbValue = SMA_Value(alPrices, nLastPos, nLongAverage);
		alPrices.get(nLastPos).setSMA_Long(dbValue);
	}
	
	/**
	 * Calcula el valor de media simple SMA para una posición del array de precios
	 * @param alPrices Array con los precios
	 * @param nPos Posición a calcular
	 * @param nPeriod Periodo de la media
	 * @return Valor calculado de la media
	 */
	private static double SMA_Value(ArrayList<Price> alPrices, int nPos, int nPeriod)
	{
		double dbValue = 0;
		int nPosIni;
		int nPosEnd;
		int nCnt = 0;
		nPosIni = (nPos + 1) - nPeriod;
		
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

}
