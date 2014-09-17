package Indicator;
import java.util.ArrayList;

import Prices.Price;

/**
 * clase que se encarga de realizar los cálculos para la obtebción de los valores de MACD
 */
public class MACD
{
	
	/**
	 * Calcula el valor de MACD para todos los valores de un array de precios
	 * @param alPrices Array con los precios
	 * @param nShortAverge Periodo de la media corta
	 * @param nLongAverage Periodo de la media larga
	 * @param nMACDSmooth Suavizado de la curva MACD
	 */
	public static void all(ArrayList<Price> alPrices, int nShortAverge, int nLongAverage, int nMACDSmooth)
	{
		double dbValueMACD;
		double dbValueSignal;
		double dbValueDiff;
		for(int i = 0; i < alPrices.size(); i++)
		{
			dbValueMACD = MACD_Value(alPrices.get(i).getEMA_Short(), alPrices.get(i).getEMA_Long());
			alPrices.get(i).setMACD(dbValueMACD);
			dbValueSignal = MACD_Signal(alPrices, i, nMACDSmooth, nLongAverage);
			alPrices.get(i).setMACD_Signal(dbValueSignal);
			dbValueDiff = MACD_Diff(dbValueMACD, dbValueSignal);
			alPrices.get(i).setMACD_Diff(dbValueDiff);
		}
	}

	/**
	 * Calcula el valor de MACD para el último valor de un array de precios
	 * @param alPrices Array con los precios
	 * @param nShortAverge Periodo de la media corta
	 * @param nLongAverage Periodo de la media larga
	 * @param nMACDSmooth Suavizado de la curva MACD
	 */
	public static void last(ArrayList<Price> alPrices, int nShortAverge, int nLongAverage, int nMACDSmooth)
	{
		double dbValueMACD;
		double dbValueSignal;
		double dbValueDiff;
		int nLastPos = alPrices.size() - 1;
		dbValueMACD = MACD_Value(alPrices.get(nLastPos).getEMA_Short(), alPrices.get(nLastPos).getEMA_Long());
		alPrices.get(nLastPos).setMACD(dbValueMACD);
		dbValueSignal = MACD_Signal(alPrices, nLastPos, nMACDSmooth, nLongAverage);
		alPrices.get(nLastPos).setMACD_Signal(dbValueSignal);
		dbValueDiff = MACD_Diff(dbValueMACD, dbValueSignal);
		alPrices.get(nLastPos).setMACD_Diff(dbValueDiff);
	}
	

	/**
	 * Calcula el valor de MACD a partir de la medias
	 * @param dbEMA_Short Valor de la media exponencial corta
	 * @param dbEMA_Long Valor de la media exponencial larga
	 * @return Valor de MACD
	 */
	private static double MACD_Value(double dbEMA_Short, double dbEMA_Long)
	{
		double dbReturn;
		if((dbEMA_Short != 0) && (dbEMA_Long != 0))
			dbReturn = dbEMA_Short - dbEMA_Long;
		else
			dbReturn = 0;
		return dbReturn;
	}	
	
	/**
	 * calcula el valor de la linea de señal de compra/venta
	 * @param alPrices Array con los rpecios
	 * @param nPos Posición dentro del array a calcular
	 * @param nMACDSmooth Factor de suavizado de MACD
	 * @param nLongAverage periodo de la media exponencial larga
	 * @return Valor de la señal
	 */
	private static double MACD_Signal(ArrayList<Price> alPrices, int nPos, int nMACDSmooth, int nLongAverage)
	{
		double dbValue = 0;
		double dbWeigth;
		double dbMACD;
		double dbPrevValue;
		int nPosIni;
		int nPosEnd;
		int nCnt = 0;
		int nPosInitComplete;
		nPosInitComplete =  nMACDSmooth + nLongAverage;
		
		/* si la posición es menor al momento en que estan todos los datos se realiza la media simple */
		if(nPos < nPosInitComplete)
		{
			nPosIni = (nPos + 1) - nPosInitComplete;
			if(nPosIni < 0)
				nPosIni = 0;
			nPosEnd = nPos;
			while((nPosIni + nCnt) <= nPosEnd)	
			{
				dbValue += alPrices.get(nPosIni + nCnt).getMACD();
				nCnt++;	
			}
			dbValue = dbValue / nCnt;
			dbValue = 0;
		}
		/* si se realiza la media ponderada */
		else
		{
			dbWeigth = (2. / (1. + nMACDSmooth));
			dbMACD = alPrices.get(nPos).getMACD();
			dbPrevValue = alPrices.get(nPos-1).getMACD();
			dbValue = (dbWeigth * (dbMACD - dbPrevValue)) + dbPrevValue;
		}
		return dbValue;
	}
	
	/**
	 * Se calcula la diferecnia entre el MACD y la señal para conocer la amplitud
	 * @param dbMACD Valor de MACD
	 * @param dbMACDSignal Valor de la señal
	 * @return Valor de la diferencia
	 */
	private static double MACD_Diff(double dbMACD, double dbMACDSignal)
	{
		return dbMACD - dbMACDSignal;
	}
	
}
