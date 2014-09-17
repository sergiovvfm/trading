package Indicator;

import Prices.Price;
import Prices.Prices;

/**
 * Clase que se encarga de unificar los calculos de los indicadores
 *
 */
class Indicators 
{
	public static int AVG_PERIOD_SHORT = 12;
	public static int AVG_PERIOD_LONG = 26;
	public static int MACD_PERIOD_SOFT = 9;
	public static int RSI_PERIOD = 14;
	
	public static void calculateAll(Prices pPrices)
	{
		SMA.all(pPrices.getPrices(), AVG_PERIOD_SHORT, AVG_PERIOD_LONG);
		EMA.all(pPrices.getPrices(), AVG_PERIOD_SHORT, AVG_PERIOD_LONG);
		MACD.all(pPrices.getPrices(), AVG_PERIOD_SHORT, AVG_PERIOD_LONG, MACD_PERIOD_SOFT);		
		RSI.all(pPrices.getPrices(), RSI_PERIOD);		
	}
	
	public static void calculateLast(Prices pPrices)
	{
		SMA.last(pPrices.getPrices(), AVG_PERIOD_SHORT, AVG_PERIOD_LONG);
		EMA.last(pPrices.getPrices(), AVG_PERIOD_SHORT, AVG_PERIOD_LONG);
		MACD.last(pPrices.getPrices(), AVG_PERIOD_SHORT, AVG_PERIOD_LONG, MACD_PERIOD_SOFT);			
		RSI.last(pPrices.getPrices(), RSI_PERIOD);		
	}
	

	/* método main de prueba, despues s epuede borrar */
	public static void main(String [ ] args)
	{
		Prices pPrices = new Prices();
		pPrices.getPrices().add(new Price(null, 44.3389, 0));
		pPrices.getPrices().add(new Price(null, 44.0902, 0));
		pPrices.getPrices().add(new Price(null, 44.1497, 0));
		pPrices.getPrices().add(new Price(null, 43.6124, 0));
		pPrices.getPrices().add(new Price(null, 44.3278, 0));
		pPrices.getPrices().add(new Price(null, 44.8264, 0));
		pPrices.getPrices().add(new Price(null, 45.0955, 0));
		pPrices.getPrices().add(new Price(null, 45.4245, 0));
		pPrices.getPrices().add(new Price(null, 45.8433, 0));
		pPrices.getPrices().add(new Price(null, 46.0826, 0));
		pPrices.getPrices().add(new Price(null, 45.8931, 0));
		pPrices.getPrices().add(new Price(null, 46.0328, 0));
		pPrices.getPrices().add(new Price(null, 45.6140, 0));
		pPrices.getPrices().add(new Price(null, 46.2820, 0));
		pPrices.getPrices().add(new Price(null, 46.2820, 0));
		pPrices.getPrices().add(new Price(null, 46.0028, 0));
		pPrices.getPrices().add(new Price(null, 46.0328, 0));
		pPrices.getPrices().add(new Price(null, 46.4116, 0));
		pPrices.getPrices().add(new Price(null, 46.2222, 0));
		pPrices.getPrices().add(new Price(null, 45.6439, 0));
		pPrices.getPrices().add(new Price(null, 46.2122, 0));
		pPrices.getPrices().add(new Price(null, 46.2521, 0));
		pPrices.getPrices().add(new Price(null, 45.7137, 0));
		pPrices.getPrices().add(new Price(null, 46.4515, 0));
		pPrices.getPrices().add(new Price(null, 45.7835, 0));
		pPrices.getPrices().add(new Price(null, 45.3548, 0));
		pPrices.getPrices().add(new Price(null, 44.0288, 0));
		pPrices.getPrices().add(new Price(null, 44.1783, 0));
		pPrices.getPrices().add(new Price(null, 44.2181, 0));
		pPrices.getPrices().add(new Price(null, 44.5672, 0));
		pPrices.getPrices().add(new Price(null, 43.4205, 0));
		pPrices.getPrices().add(new Price(null, 42.6628, 0));
		pPrices.getPrices().add(new Price(null, 43.1314, 0));
		Indicators.calculateAll(pPrices);
		System.out.println(pPrices);
	}
}