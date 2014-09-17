package Indicator;
import java.util.ArrayList;

import Prices.Price;

/**
 * clase que se encarga de realizar los cálculos para la obtebción de los valores de RSI
 */
public class RSI 
{

	/**
	 * Calcula el valor de RSI para todos los valores de un array de precios
	 * @param alPrices Array con los precios
	 * @param nPeriod Perido del indicador RSI
	 */
	public static void all(ArrayList<Price> alPrices, int nPeriod)
	{
		double dbValueAvgGain;
		double dbValueAvgLoss;
		double dbValue;
		for(int i = 0; i < alPrices.size(); i++)
		{
			/* por cada precio se calcula las medias y el valor final */
			dbValueAvgGain = RSI_Avg_Gain(alPrices, i, nPeriod);
			alPrices.get(i).setRSI_avg_Gain(dbValueAvgGain);
			dbValueAvgLoss = RSI_Avg_Loss(alPrices, i, nPeriod);
			alPrices.get(i).setRSI_avg_Loss(dbValueAvgLoss);
			dbValue = RSI_Value(alPrices, i, nPeriod);
			alPrices.get(i).setRSI(dbValue);
		}
	}

	/**
	 * Calcula el valor de RSI para el último valor de un array de precios
	 * @param alPrices Array con los precios
	 * @param nPeriod Perido del indicador RSI
	 */
	public static void last(ArrayList<Price> alPrices, int nPeriod)
	{
		double dbValueAvgGain;
		double dbValueAvgLoss;
		double dbValue;
		int nLastPos = alPrices.size() - 1;
		
		/* se calcula las medias y el valor final de la ultima posición*/
		dbValueAvgGain = RSI_Avg_Gain(alPrices, nLastPos, nPeriod);
		alPrices.get(nLastPos).setRSI_avg_Gain(dbValueAvgGain);
		dbValueAvgLoss = RSI_Avg_Loss(alPrices, nLastPos, nPeriod);
		alPrices.get(nLastPos).setRSI_avg_Loss(dbValueAvgLoss);
		dbValue = RSI_Value(alPrices, nLastPos, nPeriod);
		alPrices.get(nLastPos).setRSI(dbValue);
	}
	
	/**
	 * Obtiene la media de ganancia para la posición dada en la lista de precios
	 * @param alPrices Array con los precios
	 * @param nPos Posición donde se inicia el cálculo 
	 * @param nPeriod Perido del indicador RSI
	 * @return Valor de la media de ganancia para esa posición
	 */
	private static double RSI_Avg_Gain(ArrayList<Price> alPrices, int nPos, int nPeriod)
	{
        double dbReturn;
        ArrayList<Double> alGains;
        double dbPrice = 0;
        double dbPricePrev = 0;
        double dbChange = 0;
        double dbAvgGain = 0;
        double dbAvgGainPrev = 0;
        
        /* si la posición coincide con el periodo se calcula la media simple */
        if (nPos == nPeriod)
        {
        	alGains = new ArrayList<Double>();
        	for(int i = 1; i < nPos; i++)
        	{
        		dbPricePrev = alPrices.get(i-1).getPrice();
        		dbPrice = alPrices.get(i).getPrice();
        		dbChange = dbPrice - dbPricePrev;
        		if(dbChange > 0 )
        			alGains.add(dbChange);
        	}
        	for(Double dbValue : alGains)
        	{
        		dbAvgGain += dbValue;
        	}   
        	dbReturn = dbAvgGain / nPeriod;
        } 
        /* si la posición es mayor que el periodo se calcula la media ponderada */
       else if (nPos > nPeriod)
        {
    		dbPricePrev = alPrices.get(nPos-1).getPrice();
    		dbPrice = alPrices.get(nPos).getPrice();
    		dbChange = dbPrice - dbPricePrev;
    		if(dbChange < 0)
    			dbChange = 0;
    		dbAvgGainPrev = alPrices.get(nPos-1).getRSI_avg_Gain();
    		dbAvgGain = ((dbAvgGainPrev * (nPeriod-1)) + dbChange) / nPeriod;
        	dbReturn = dbAvgGain;
        }         
        /* si la posición es menor que el periodo */
        else 
        {
        	dbReturn = 0;
        }
        return dbReturn;
	}	
	
	/**
	 * Obtiene la media de perdida para la posición dada en la lista de precios
	 * @param alPrices Array con los precios
	 * @param nPos Posición donde se inicia el cálculo 
	 * @param nPeriod Perido del indicador RSI
	 * @return Valor de la media de perdida para esa posición
	 */	
	private static double RSI_Avg_Loss(ArrayList<Price> alPrices, int nPos, int nPeriod)
	{
        double dbReturn;
        ArrayList<Double> alLoss;
        double dbPrice = 0;
        double dbPricePrev = 0;
        double dbChange = 0;
        double dbAvgLoss = 0;
        double dbAvgLossPrev = 0;
        /* si la posición coincide con el periodo se calcula la media simple */
       if (nPos == nPeriod)
        {
        	alLoss = new ArrayList<Double>();
        	for(int i = 1; i < nPos; i++)
        	{
        		dbPricePrev = alPrices.get(i-1).getPrice();
        		dbPrice = alPrices.get(i).getPrice();
        		dbChange = dbPrice - dbPricePrev;
        		if(dbChange < 0 )
        			alLoss.add(dbChange * (-1));
        	}
        	for(Double dbValue : alLoss)
        	{
        		dbAvgLoss += dbValue;
        	}   
        	dbReturn = dbAvgLoss /nPeriod;
        } 
        /* si la posición es mayor que el periodo se calcula la media ponderada */
        else if (nPos > nPeriod)
        {
    		dbPricePrev = alPrices.get(nPos-1).getPrice();
    		dbPrice = alPrices.get(nPos).getPrice();
    		dbChange = dbPrice - dbPricePrev;
    		if(dbChange > 0)
    			dbChange = 0;
    		dbChange = dbChange * (-1);
    		dbAvgLossPrev = alPrices.get(nPos-1).getRSI_avg_Loss();
    		dbAvgLoss = ((dbAvgLossPrev * (nPeriod-1)) + dbChange) / nPeriod;
        	dbReturn = dbAvgLoss;
        }       
        /* si la posición es menor que el periodo */
        else 
        {
        	dbReturn = 0;
        }
        return dbReturn;
	}			
	
	/**
	 * Obtiene la el valor de RSI para la posición dada en la lista de precios
	 * @param alPrices Array con los precios
	 * @param nPos Posición donde se inicia el cálculo 
	 * @param nPeriod Perido del indicador RSI
	 * @return Valor de la media de perdida para esa posición
	 */		
	private static double RSI_Value(ArrayList<Price> alPrices, int nPos, int nPeriod)
	{
        double dbReturn;
        double dbAvgGain;
        double dbAvgLoss;
        /* si la posición es mayor o igual al periodo se calcula el valor final */
        if (nPos >= nPeriod)
        {
            dbAvgGain = alPrices.get(nPos).getRSI_avg_Gain();
            dbAvgLoss = alPrices.get(nPos).getRSI_avg_Loss();
        	if(dbAvgLoss > 0)
        		dbReturn = 100-(100/(1+(dbAvgGain/dbAvgLoss)));
        	else
        		dbReturn = 100;        
        }
        /* si la posición es menor que el periodo */
      	else
        	dbReturn = 0;
        return dbReturn;
	}	
}
