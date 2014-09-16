import java.util.ArrayList;

class Calculation 
{
	public static void all(Prices pPrices)
	{
		Calculation.SMA_All(pPrices.getPrices());
		Calculation.EMA_All(pPrices.getPrices());
		Calculation.MACD_All(pPrices.getPrices());		
	}
	
	public static void last(Prices pPrices)
	{
		Calculation.SMA_Last(pPrices.getPrices());
		Calculation.EMA_Last(pPrices.getPrices());
		Calculation.MACD_Last(pPrices.getPrices());		
	}
	
	private static void SMA_All(ArrayList<Price> alPrices)
	{
		double dbValue;
		for(int i = 0; i < alPrices.size(); i++)
		{
			dbValue = SMA(alPrices, i, 12);
			alPrices.get(i).setSMA(dbValue, 12);
			dbValue = SMA(alPrices, i, 26);
			alPrices.get(i).setSMA(dbValue, 26);
		}
	}

	private static void SMA_Last(ArrayList<Price> alPrices)
	{
		double dbValue;
		int nLastPos = alPrices.size() - 1;
		dbValue = SMA(alPrices, nLastPos, 12);
		alPrices.get(nLastPos).setSMA(dbValue, 12);
		dbValue = SMA(alPrices, nLastPos, 26);
		alPrices.get(nLastPos).setSMA(dbValue, 26);
	}
	
	private static void EMA_All(ArrayList<Price> alPrices)
	{
		double dbValue;
		for(int i = 0; i < alPrices.size(); i++)
		{
			dbValue = EMA(alPrices, i, 12);
			alPrices.get(i).setEMA(dbValue, 12);
			dbValue = EMA(alPrices, i, 26);
			alPrices.get(i).setEMA(dbValue, 26);
		}
	}

	private static void EMA_Last(ArrayList<Price> alPrices)
	{
		double dbValue;
		int nLastPos = alPrices.size() - 1;
		dbValue = EMA(alPrices, nLastPos, 12);
		alPrices.get(nLastPos).setEMA(dbValue, 12);
		dbValue = EMA(alPrices, nLastPos, 26);
		alPrices.get(nLastPos).setEMA(dbValue, 26);
	}
	
	private static void MACD_All(ArrayList<Price> alPrices)
	{
		double dbValueMACD;
		double dbValueSignal;
		double dbValueDiff;
		for(int i = 0; i < alPrices.size(); i++)
		{
			dbValueMACD = MACD(alPrices.get(i).getEMA(12), alPrices.get(i).getEMA(26));
			alPrices.get(i).setMACD(dbValueMACD);
			dbValueSignal = MACD_Signal(alPrices, i, 9);
			alPrices.get(i).setMACD_Signal(dbValueSignal);
			dbValueDiff = MACD_Diff(dbValueMACD, dbValueSignal);
			alPrices.get(i).setMACD_Diff(dbValueDiff);
		}
	}

	private static void MACD_Last(ArrayList<Price> alPrices)
	{
		double dbValueMACD;
		double dbValueSignal;
		double dbValueDiff;
		int nLastPos = alPrices.size() - 1;
		dbValueMACD = MACD(alPrices.get(nLastPos).getEMA(12), alPrices.get(nLastPos).getEMA(26));
		alPrices.get(nLastPos).setMACD(dbValueMACD);
		dbValueSignal = MACD_Signal(alPrices, nLastPos, 9);
		alPrices.get(nLastPos).setMACD_Signal(dbValueSignal);
		dbValueDiff = MACD_Diff(dbValueMACD, dbValueSignal);
		alPrices.get(nLastPos).setMACD_Diff(dbValueDiff);
	}
	
	private static double SMA(ArrayList<Price> alPrices, int nPos, int nFactor)
	{
		double dbValue = 0;
		int nPosIni;
		int nPosEnd;
		int nCnt = 0;
		nPosIni = (nPos + 1) - nFactor;
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
	
	private static double EMA(ArrayList<Price> alPrices, int nPos, int nFactor)
	{
		double dbValue = 0;
		double dbWeigth;
		double dbPrice;
		double dbPrevValue;
		if(nPos < nFactor)
		{
			dbValue = SMA(alPrices, nPos, nFactor);
		}
		else
		{
			dbWeigth = (2. / (1. + nFactor));
			dbPrice = alPrices.get(nPos).getPrice();
			dbPrevValue = alPrices.get(nPos-1).getEMA(nFactor);
			dbValue = (dbWeigth * (dbPrice - dbPrevValue)) + dbPrevValue;
		}
		return dbValue;
	}
	
	private static double MACD(double dbEMA12, double dbEMA26)
	{
		return dbEMA12 - dbEMA26;
	}	
	
	private static double MACD_Signal(ArrayList<Price> alPrices, int nPos, int nFactor)
	{
		double dbValue = 0;
		double dbWeigth;
		double dbPrice;
		double dbPrevValue;
		int nPosIni;
		int nPosEnd;
		int nCnt = 0;
		if(nPos < nFactor)
		{
			nPosIni = (nPos + 1) - nFactor;
			if(nPosIni < 0)
				nPosIni = 0;
			nPosEnd = nPos;
			while((nPosIni + nCnt) <= nPosEnd)	
			{
				dbValue += alPrices.get(nPosIni + nCnt).getMACD();
				nCnt++;	
			}
			dbValue = dbValue / nCnt;			
		}
		else
		{
			dbWeigth = (2. / (1. + nFactor));
			dbPrice = alPrices.get(nPos).getMACD();
			dbPrevValue = alPrices.get(nPos-1).getEMA(nFactor);
			dbValue = (dbWeigth * (dbPrice - dbPrevValue)) + dbPrevValue;
		}
		return dbValue;
	}
	
	private static double MACD_Diff(double dbMACD, double dbMACDSignal)
	{
		return dbMACD - dbMACDSignal;
	}
	public static void main(String [ ] args)
	{
		Prices pPrices = new Prices();
		pPrices.getPrices().add(new Price(null, 22.2734, 0));
		pPrices.getPrices().add(new Price(null, 22.1940, 0));
		pPrices.getPrices().add(new Price(null, 22.0847, 0));
		pPrices.getPrices().add(new Price(null, 22.1741, 0));
		pPrices.getPrices().add(new Price(null, 22.1840, 0));
		pPrices.getPrices().add(new Price(null, 22.1344, 0));
		pPrices.getPrices().add(new Price(null, 22.2337, 0));
		pPrices.getPrices().add(new Price(null, 22.4323, 0));
		pPrices.getPrices().add(new Price(null, 22.2436, 0));
		pPrices.getPrices().add(new Price(null, 22.2933, 0));
		pPrices.getPrices().add(new Price(null, 22.1542, 0));
		pPrices.getPrices().add(new Price(null, 22.3926, 0));
		pPrices.getPrices().add(new Price(null, 22.3816, 0));
		pPrices.getPrices().add(new Price(null, 22.6109, 0));
		pPrices.getPrices().add(new Price(null, 23.3558, 0));
		pPrices.getPrices().add(new Price(null, 24.0519, 0));
		pPrices.getPrices().add(new Price(null, 23.7530, 0));
		pPrices.getPrices().add(new Price(null, 23.8324, 0));
		pPrices.getPrices().add(new Price(null, 23.9516, 0));
		pPrices.getPrices().add(new Price(null, 23.6338, 0));
		pPrices.getPrices().add(new Price(null, 23.8225, 0));
		pPrices.getPrices().add(new Price(null, 23.8722, 0));
		pPrices.getPrices().add(new Price(null, 23.6537, 0));
		pPrices.getPrices().add(new Price(null, 23.1870, 0));
		pPrices.getPrices().add(new Price(null, 23.0976, 0));
		pPrices.getPrices().add(new Price(null, 23.3260, 0));
		pPrices.getPrices().add(new Price(null, 22.6805, 0));
		pPrices.getPrices().add(new Price(null, 23.0976, 0));
		pPrices.getPrices().add(new Price(null, 23.1025, 0));
		pPrices.getPrices().add(new Price(null, 23.2725, 0));
		pPrices.getPrices().add(new Price(null, 23.3525, 0));
		pPrices.getPrices().add(new Price(null, 23.8252, 0));
		pPrices.getPrices().add(new Price(null, 23.7252, 0));
		pPrices.getPrices().add(new Price(null, 23.8725, 0));
		pPrices.getPrices().add(new Price(null, 23.1725, 0));
		pPrices.getPrices().add(new Price(null, 22.8725, 0));
		pPrices.getPrices().add(new Price(null, 22.0725, 0));
		pPrices.getPrices().add(new Price(null, 21.9725, 0));
		pPrices.getPrices().add(new Price(null, 21.8725, 0));
		pPrices.getPrices().add(new Price(null, 21.5725, 0));
		Calculation.all(pPrices);
		System.out.println(pPrices);
	}
}