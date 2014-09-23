package Indicator;

import Prices.Price;
import Prices.Prices;

/**
 * Clase que se encarga de unificar los calculos de los indicadores
 *
 */
class Indicators 
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
	

	/* método main de prueba, despues s epuede borrar */
	public static void main(String [ ] args)
	{
		Prices pPrices = new Prices();
		pPrices.getPrices().add(new Price(null, 86.1557, 0));
		pPrices.getPrices().add(new Price(null, 89.0867, 0));
		pPrices.getPrices().add(new Price(null, 88.7829, 0));
		pPrices.getPrices().add(new Price(null, 90.3228, 0));
		pPrices.getPrices().add(new Price(null, 89.0671, 0));
		pPrices.getPrices().add(new Price(null, 91.1453, 0));
		pPrices.getPrices().add(new Price(null, 89.4397, 0));
		pPrices.getPrices().add(new Price(null, 89.1750, 0));
		pPrices.getPrices().add(new Price(null, 86.9302, 0));
		pPrices.getPrices().add(new Price(null, 87.6752, 0));
		pPrices.getPrices().add(new Price(null, 86.9596, 0));
		pPrices.getPrices().add(new Price(null, 89.4299, 0));
		pPrices.getPrices().add(new Price(null, 89.3221, 0));
		pPrices.getPrices().add(new Price(null, 88.7241, 0));
		pPrices.getPrices().add(new Price(null, 87.4497, 0));
		pPrices.getPrices().add(new Price(null, 87.2634, 0));
		pPrices.getPrices().add(new Price(null, 89.4985, 0));
		pPrices.getPrices().add(new Price(null, 87.9006, 0));
		pPrices.getPrices().add(new Price(null, 89.1260, 0));
		pPrices.getPrices().add(new Price(null, 90.7043, 0));
		pPrices.getPrices().add(new Price(null, 92.9001, 0));
		pPrices.getPrices().add(new Price(null, 92.9784, 0));
		pPrices.getPrices().add(new Price(null, 91.8021, 0));
		pPrices.getPrices().add(new Price(null, 92.6647, 0));
		pPrices.getPrices().add(new Price(null, 92.6843, 0));
		pPrices.getPrices().add(new Price(null, 92.3021, 0));
		pPrices.getPrices().add(new Price(null, 92.7725, 0));
		pPrices.getPrices().add(new Price(null, 92.5373, 0));
		pPrices.getPrices().add(new Price(null, 92.9490, 0));
		pPrices.getPrices().add(new Price(null, 93.2039, 0));
		pPrices.getPrices().add(new Price(null, 91.0669, 0));
		pPrices.getPrices().add(new Price(null, 89.8318, 0));
		pPrices.getPrices().add(new Price(null, 89.7435, 0));
		pPrices.getPrices().add(new Price(null, 90.3994, 0));
		pPrices.getPrices().add(new Price(null, 90.7387, 0));
		pPrices.getPrices().add(new Price(null, 88.0177, 0));
		pPrices.getPrices().add(new Price(null, 88.0867, 0));
		pPrices.getPrices().add(new Price(null, 88.8439, 0));
		pPrices.getPrices().add(new Price(null, 90.7781, 0));
		pPrices.getPrices().add(new Price(null, 90.5416, 0));
		pPrices.getPrices().add(new Price(null, 91.3894, 0));
		pPrices.getPrices().add(new Price(null, 90.6500, 0));
		Indicators.calculateAll(pPrices);
		System.out.println(pPrices);
	}
}