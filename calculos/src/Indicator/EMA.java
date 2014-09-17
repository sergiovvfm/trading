package Indicator;
import java.util.ArrayList;
import java.util.List;

import Prices.Price;

/**
 * clase que se encarga de realizar los cálculos para la obtebción de medias exponenciales EMA
 */
public class EMA 
{
	/**
	 * Calcula el valor de las media exponenciales para los valores de un array de precios
	 * @param alPrices Array con los precios
	 * @param nShortAverge Periodo de la media corta
	 * @param nLongAverage Periodo de la media larga
	 */	
	public static void all(ArrayList<Price> alPrices, int nShortAverge, int nLongAverage)
	{
		double dbValue;
		for(int i = 0; i < alPrices.size(); i++)
		{
			dbValue = EMA_Short(alPrices, i, nShortAverge);
			alPrices.get(i).setEMA_Short(dbValue);
			dbValue = EMA_Long(alPrices, i, nLongAverage);
			alPrices.get(i).setEMA_Long(dbValue);
		}
	}

	/**
	 * Calcula el valor de las media exponenciales para el ultimo valor de un array de precios
	 * @param alPrices Array con los precios
	 * @param nShortAverge Periodo de la media corta
	 * @param nLongAverage Periodo de la media larga
	 */
	public static void last(ArrayList<Price> alPrices, int nShortAverge, int nLongAverage)
	{
		double dbValue;
		int nLastPos = alPrices.size() - 1;
		dbValue = EMA_Short(alPrices, nLastPos, nShortAverge);
		alPrices.get(nLastPos).setEMA_Short(dbValue);
		dbValue = EMA_Long(alPrices, nLastPos, nLongAverage);
		alPrices.get(nLastPos).setEMA_Long(dbValue);
	}
	
	/**
	 * Calcula el valor de media exponencial EMA corta para una posición del array de precios
	 * @param alPrices Array con los precios
	 * @param nPos Posición a calcular
	 * @param nPeriod Periodo de la media
	 * @return Valor calculado de la media
	 */	
	private static double EMA_Short(ArrayList<Price> alPrices, int nPos, int nPeriod)
	{
        double dbReturn;
        double dbFactor = 2.0/(nPeriod +1);
      
        /* si la posición es la primera que contiene el rango de calculo, se realiza la media simple */
        if (nPos == (nPeriod - 1)) 
        {
            int nIntervalStart;
            int nIntervalEnd;
            double dbSum;
            List<Price> alPricesInterval;
        	nIntervalStart = nPos - (nPeriod -1);
        	nIntervalEnd = nIntervalStart + nPeriod;
        	alPricesInterval = alPrices.subList(nIntervalStart, nIntervalEnd);             
        	dbSum = 0;
            for (Price pPrice : alPricesInterval)
            {
            	dbSum += pPrice.getPrice();
            }
            dbReturn = (float) (dbSum / nPeriod);
        } 
        /* si la posición es posterior al primer cálculo se aplica la ponderación exponencial  */
        else if ((nPos > (nPeriod-1)) && ((alPrices.get(nPos-1).getEMA_Short()) != 0) )
        {
            dbReturn = (float) ( (dbFactor * (alPrices.get(nPos).getPrice() - alPrices.get(nPos-1).getEMA_Short())) + alPrices.get(nPos-1).getEMA_Short());
        } 
        /* si son las primeras posiciones el valor es 0 */
        else 
        {
        	dbReturn = 0;
        }
        return dbReturn;
	}
	
	/**
	 * Calcula el valor de media exponencial EMA larga para una posición del array de precios
	 * @param alPrices Array con los precios
	 * @param nPos Posición a calcular
	 * @param nPeriod Periodo de la media
	 * @return Valor calculado de la media
	 */	
	private static double EMA_Long(ArrayList<Price> alPrices, int nPos, int nPeriod)
	{
        double dbReturn;
        double dbFactor = 2.0/(nPeriod +1);
      
        /* si la posición es la primera que contiene el rango de calculo, se realiza la media simple */
        if (nPos == (nPeriod - 1)) 
        {
            int nIntervalStart;
            int nIntervalEnd;
            double dbSum;
            List<Price> alPricesInterval;
        	nIntervalStart = nPos - (nPeriod -1);
        	nIntervalEnd = nIntervalStart + nPeriod;
        	alPricesInterval = alPrices.subList(nIntervalStart, nIntervalEnd);             
        	dbSum = 0;
            for (Price pPrice : alPricesInterval)
            {
            	dbSum += pPrice.getPrice();
            }
            dbReturn = (float) (dbSum / nPeriod);
        } 
        /* si la posición es posterior al primer cálculo se aplica la ponderación exponencial  */
        else if ((nPos > (nPeriod-1)) && ((alPrices.get(nPos-1).getEMA_Long()) != 0) )
        {
            dbReturn = (float) ( (dbFactor * (alPrices.get(nPos).getPrice() - alPrices.get(nPos-1).getEMA_Long())) + alPrices.get(nPos-1).getEMA_Long());
        } 
        /* si son las primeras posiciones el valor es 0 */
        else 
        {
        	dbReturn = 0;
        }
        return dbReturn;
	}
}
