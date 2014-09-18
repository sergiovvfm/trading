package Indicator;

import Prices.Prices;

/**
 * Clase que se encarga de unificar los calculos de los indicadores
 *
 */
public class Indicators 
{
	public static int AVG_PERIOD_SHORT = 6;
	public static int AVG_PERIOD_LONG = 70;
	public static int MACD_AVG_PERIOD_SHORT = 12;
	public static int MACD_AVG_PERIOD_LONG = 26;
	public static int MACD_PERIOD_SOFT = 9;
	public static int RSI_PERIOD = 14;
	
	public static void calculateAll(Prices pPrices)
	{
		SMA.all(pPrices.getPrices(), AVG_PERIOD_SHORT, AVG_PERIOD_LONG);
		EMA.all(pPrices.getPrices(), MACD_AVG_PERIOD_SHORT, MACD_AVG_PERIOD_LONG);
		MACD.all(pPrices.getPrices(), MACD_AVG_PERIOD_SHORT, MACD_AVG_PERIOD_LONG, MACD_PERIOD_SOFT);		
		RSI.all(pPrices.getPrices(), RSI_PERIOD);		
		BollingerBands.all(pPrices.getPrices());		
	}
	
	public static void calculateLast(Prices pPrices)
	{
		SMA.last(pPrices.getPrices(), AVG_PERIOD_SHORT, AVG_PERIOD_LONG);
		EMA.last(pPrices.getPrices(), MACD_AVG_PERIOD_SHORT, MACD_AVG_PERIOD_LONG);
		MACD.last(pPrices.getPrices(), MACD_AVG_PERIOD_SHORT, MACD_AVG_PERIOD_LONG, MACD_PERIOD_SOFT);			
		RSI.last(pPrices.getPrices(), RSI_PERIOD);		
		BollingerBands.last(pPrices.getPrices());		
	}
}