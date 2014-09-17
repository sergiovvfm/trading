import java.util.ArrayList;
import java.util.List;

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
			dbValueSignal = MACD_Signal(alPrices, i, 9, 26);
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
		dbValueSignal = MACD_Signal(alPrices, nLastPos, 9, 26);
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
	
	private static double EMA(ArrayList<Price> alPrices, int nPos, int nPeriod)
	{
        double dbReturn;
        double dbFactor = 2.0/(nPeriod +1);
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
            for (Price sp : alPricesInterval) {
            	dbSum += sp.getPrice();
            }
            dbReturn = (float) (dbSum / nPeriod);
        } 
        else if ((nPos > (nPeriod-1)) && ((alPrices.get(nPos-1).getEMA(nPeriod)) != 0) )
        {
            dbReturn = (float) ( (dbFactor * (alPrices.get(nPos).getPrice() - alPrices.get(nPos-1).getEMA(nPeriod))) + alPrices.get(nPos-1).getEMA(nPeriod) );
        } 
        else 
        {
        	dbReturn = 0;
        }
        return dbReturn;
	}
	
	private static double MACD(double dbEMA12, double dbEMA26)
	{
		double dbReturn;
		if((dbEMA12 != 0) && (dbEMA26 != 0))
			dbReturn = dbEMA12 - dbEMA26;
		else
			dbReturn = 0;
		return dbReturn;
	}	
	
	private static double MACD_Signal(ArrayList<Price> alPrices, int nPos, int nFactor, int MACD_Offset)
	{
		double dbValue = 0;
		double dbWeigth;
		double dbMACD;
		double dbPrevValue;
		int nPosIni;
		int nPosEnd;
		int nCnt = 0;
		int nPosInitComplete;
		nPosInitComplete =  nFactor + MACD_Offset;
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
		else
		{
			dbWeigth = (2. / (1. + nFactor));
			dbMACD = alPrices.get(nPos).getMACD();
			dbPrevValue = alPrices.get(nPos-1).getMACD();
			dbValue = (dbWeigth * (dbMACD - dbPrevValue)) + dbPrevValue;
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
		pPrices.getPrices().add(new Price(null, 102.813, 0));
		pPrices.getPrices().add(new Price(null, 102.65, 0));	
		pPrices.getPrices().add(new Price(null, 102.92, 0));
		pPrices.getPrices().add(new Price(null, 102.96, 0));
		pPrices.getPrices().add(new Price(null, 102.93, 0));
		pPrices.getPrices().add(new Price(null, 102.68, 0));
		pPrices.getPrices().add(new Price(null, 102.54, 0));
		pPrices.getPrices().add(new Price(null, 102.43, 0));
		pPrices.getPrices().add(new Price(null, 102.4, 0));
		pPrices.getPrices().add(new Price(null, 102.44, 0));
		pPrices.getPrices().add(new Price(null, 102.4695, 0));
		pPrices.getPrices().add(new Price(null, 102.45, 0));
		pPrices.getPrices().add(new Price(null, 102.25, 0));
		pPrices.getPrices().add(new Price(null, 102.18, 0));
		pPrices.getPrices().add(new Price(null, 102.04, 0));
		pPrices.getPrices().add(new Price(null, 102.04, 0));
		pPrices.getPrices().add(new Price(null, 101.9899, 0));
		pPrices.getPrices().add(new Price(null, 102.04, 0));
		pPrices.getPrices().add(new Price(null, 101.81, 0));
		pPrices.getPrices().add(new Price(null, 101.96, 0));
		pPrices.getPrices().add(new Price(null, 101.82, 0));
		pPrices.getPrices().add(new Price(null, 101.99, 0));
		pPrices.getPrices().add(new Price(null, 102.21, 0));
		pPrices.getPrices().add(new Price(null, 102.09			, 0));
		pPrices.getPrices().add(new Price(null, 101.91          , 0));
		pPrices.getPrices().add(new Price(null, 101.96          , 0));
		pPrices.getPrices().add(new Price(null, 101.97          , 0));
		pPrices.getPrices().add(new Price(null, 101.94          , 0));
		pPrices.getPrices().add(new Price(null, 101.865         , 0));
		pPrices.getPrices().add(new Price(null, 101.66			, 0));
		pPrices.getPrices().add(new Price(null, 101.9           , 0));
		pPrices.getPrices().add(new Price(null, 101.7601        , 0));
		pPrices.getPrices().add(new Price(null, 101.93          , 0));
		pPrices.getPrices().add(new Price(null, 102.05          , 0));
		pPrices.getPrices().add(new Price(null, 102.08          , 0));
		pPrices.getPrices().add(new Price(null, 102.09			, 0));
		pPrices.getPrices().add(new Price(null, 101.98          , 0));
		pPrices.getPrices().add(new Price(null, 102.119         , 0));
		pPrices.getPrices().add(new Price(null, 102.15          , 0));
		pPrices.getPrices().add(new Price(null, 102.15          , 0));
		pPrices.getPrices().add(new Price(null, 102.15          , 0));
		pPrices.getPrices().add(new Price(null, 102.19          , 0));
		pPrices.getPrices().add(new Price(null, 102.1465        , 0));
		pPrices.getPrices().add(new Price(null, 102.05          , 0));
		pPrices.getPrices().add(new Price(null, 102.11          , 0));
		pPrices.getPrices().add(new Price(null, 102.15          , 0));
		pPrices.getPrices().add(new Price(null, 102.13          , 0));
		pPrices.getPrices().add(new Price(null, 102.179			, 0));
		pPrices.getPrices().add(new Price(null, 102.22          , 0));
		pPrices.getPrices().add(new Price(null, 102.34          , 0));
		pPrices.getPrices().add(new Price(null, 102.37          , 0));
		pPrices.getPrices().add(new Price(null, 102.33          , 0));
		pPrices.getPrices().add(new Price(null, 102.32          , 0));
		pPrices.getPrices().add(new Price(null, 102.2699        , 0));
		pPrices.getPrices().add(new Price(null, 102.215         , 0));
		pPrices.getPrices().add(new Price(null, 102.26          , 0));
		pPrices.getPrices().add(new Price(null, 102.141         , 0));
		pPrices.getPrices().add(new Price(null, 102.2092        , 0));
		pPrices.getPrices().add(new Price(null, 102.2001        , 0));
		pPrices.getPrices().add(new Price(null, 102.215			, 0));
		pPrices.getPrices().add(new Price(null, 102.24          , 0));
		pPrices.getPrices().add(new Price(null, 102.22          , 0));
		pPrices.getPrices().add(new Price(null, 102.34          , 0));
		pPrices.getPrices().add(new Price(null, 102.3           , 0));
		pPrices.getPrices().add(new Price(null, 102.2366        , 0));
		pPrices.getPrices().add(new Price(null, 102.11          , 0));
		pPrices.getPrices().add(new Price(null, 102.13          , 0));
		pPrices.getPrices().add(new Price(null, 102.1555        , 0));
		pPrices.getPrices().add(new Price(null, 102.23          , 0));
		pPrices.getPrices().add(new Price(null, 102.16          , 0));
		pPrices.getPrices().add(new Price(null, 102.194         , 0));
		pPrices.getPrices().add(new Price(null, 102.1545        , 0));
		pPrices.getPrices().add(new Price(null, 101.96          , 0));
		pPrices.getPrices().add(new Price(null, 102.1           , 0));
		pPrices.getPrices().add(new Price(null, 102.0501        , 0));
		pPrices.getPrices().add(new Price(null, 102.13          , 0));
		pPrices.getPrices().add(new Price(null, 102.04          , 0));
		pPrices.getPrices().add(new Price(null, 102.1           , 0));
		pPrices.getPrices().add(new Price(null, 102.0201        , 0));
		pPrices.getPrices().add(new Price(null, 102.12          , 0));
		pPrices.getPrices().add(new Price(null, 102.17          , 0));
		pPrices.getPrices().add(new Price(null, 102.15          , 0));
		pPrices.getPrices().add(new Price(null, 102.1952        , 0));
		pPrices.getPrices().add(new Price(null, 102.19          , 0));
		pPrices.getPrices().add(new Price(null, 102.15          , 0));
		pPrices.getPrices().add(new Price(null, 102.24          , 0));
		pPrices.getPrices().add(new Price(null, 102.23          , 0));
		pPrices.getPrices().add(new Price(null, 102.165         , 0));
		pPrices.getPrices().add(new Price(null, 102.21          , 0));
		pPrices.getPrices().add(new Price(null, 102.12          , 0));
		pPrices.getPrices().add(new Price(null, 102.175         , 0));
		pPrices.getPrices().add(new Price(null, 102.14          , 0));
		pPrices.getPrices().add(new Price(null, 102.23          , 0));
		pPrices.getPrices().add(new Price(null, 102.28          , 0));
		pPrices.getPrices().add(new Price(null, 102.35          , 0));
		pPrices.getPrices().add(new Price(null, 102.39			, 0));
		pPrices.getPrices().add(new Price(null, 102.52          , 0));
		pPrices.getPrices().add(new Price(null, 102.48          , 0));
		pPrices.getPrices().add(new Price(null, 102.61          , 0));
		pPrices.getPrices().add(new Price(null, 102.58          , 0));
		pPrices.getPrices().add(new Price(null, 102.6438        , 0));
		pPrices.getPrices().add(new Price(null, 102.59          , 0));
		pPrices.getPrices().add(new Price(null, 102.59          , 0));
		pPrices.getPrices().add(new Price(null, 102.5635        , 0));
		pPrices.getPrices().add(new Price(null, 102.567         , 0));
		pPrices.getPrices().add(new Price(null, 102.5416        , 0));
		pPrices.getPrices().add(new Price(null, 102.56          , 0));
		pPrices.getPrices().add(new Price(null, 102.5           , 0));
		pPrices.getPrices().add(new Price(null, 102.51          , 0));
		pPrices.getPrices().add(new Price(null, 102.49          , 0));
		pPrices.getPrices().add(new Price(null, 102.394         , 0));
		pPrices.getPrices().add(new Price(null, 102.3571        , 0));
		pPrices.getPrices().add(new Price(null, 102.415         , 0));
		pPrices.getPrices().add(new Price(null, 102.41          , 0));
		pPrices.getPrices().add(new Price(null, 102.4199        , 0));
		pPrices.getPrices().add(new Price(null, 102.4           , 0));
		pPrices.getPrices().add(new Price(null, 102.405         , 0));
		pPrices.getPrices().add(new Price(null, 102.398         , 0));
		pPrices.getPrices().add(new Price(null, 102.448         , 0));
		pPrices.getPrices().add(new Price(null, 102.41          , 0));
		pPrices.getPrices().add(new Price(null, 102.42          , 0));
		pPrices.getPrices().add(new Price(null, 102.42          , 0));
		pPrices.getPrices().add(new Price(null, 102.46          , 0));
		pPrices.getPrices().add(new Price(null, 102.4159        , 0));
		pPrices.getPrices().add(new Price(null, 102.3932        , 0));
		pPrices.getPrices().add(new Price(null, 102.36          , 0));
		pPrices.getPrices().add(new Price(null, 102.3499        , 0));
		pPrices.getPrices().add(new Price(null, 102.36          , 0));
		pPrices.getPrices().add(new Price(null, 102.3052        , 0));
		pPrices.getPrices().add(new Price(null, 102.31          , 0));
		pPrices.getPrices().add(new Price(null, 102.28          , 0));
		pPrices.getPrices().add(new Price(null, 102.27			, 0));
		pPrices.getPrices().add(new Price(null, 102.1965        , 0));
		pPrices.getPrices().add(new Price(null, 102.247         , 0));
		pPrices.getPrices().add(new Price(null, 102.23          , 0));
		pPrices.getPrices().add(new Price(null, 102.25          , 0));
		pPrices.getPrices().add(new Price(null, 102.2           , 0));
		pPrices.getPrices().add(new Price(null, 102.17          , 0));
		pPrices.getPrices().add(new Price(null, 102.1878        , 0));
		pPrices.getPrices().add(new Price(null, 102.209         , 0));
		pPrices.getPrices().add(new Price(null, 102.195         , 0));
		pPrices.getPrices().add(new Price(null, 102.1484        , 0));
		pPrices.getPrices().add(new Price(null, 102.2           , 0));
		pPrices.getPrices().add(new Price(null, 102.08          , 0));
		pPrices.getPrices().add(new Price(null, 102.1501        , 0));
		pPrices.getPrices().add(new Price(null, 102.13          , 0));
		pPrices.getPrices().add(new Price(null, 102.2           , 0));
		pPrices.getPrices().add(new Price(null, 102.11          , 0));
		pPrices.getPrices().add(new Price(null, 102.11          , 0));
		pPrices.getPrices().add(new Price(null, 101.9899        , 0));
		pPrices.getPrices().add(new Price(null, 102.04          , 0));
		pPrices.getPrices().add(new Price(null, 102.02          , 0));
		pPrices.getPrices().add(new Price(null, 101.94          , 0));
		pPrices.getPrices().add(new Price(null, 101.899         , 0));
		pPrices.getPrices().add(new Price(null, 101.83          , 0));
		pPrices.getPrices().add(new Price(null, 101.84          , 0));
		pPrices.getPrices().add(new Price(null, 101.93          , 0));
		pPrices.getPrices().add(new Price(null, 101.92          , 0));
		pPrices.getPrices().add(new Price(null, 101.925         , 0));
		pPrices.getPrices().add(new Price(null, 101.9601        , 0));
		pPrices.getPrices().add(new Price(null, 101.9768        , 0));
		pPrices.getPrices().add(new Price(null, 101.9899        , 0));
		pPrices.getPrices().add(new Price(null, 101.87          , 0));
		pPrices.getPrices().add(new Price(null, 101.7837        , 0));
		pPrices.getPrices().add(new Price(null, 101.86          , 0));
		pPrices.getPrices().add(new Price(null, 101.91          , 0));
		pPrices.getPrices().add(new Price(null, 101.94          , 0));
		pPrices.getPrices().add(new Price(null, 101.9599        , 0));
		pPrices.getPrices().add(new Price(null, 101.9648        , 0));
		pPrices.getPrices().add(new Price(null, 101.895         , 0));
		pPrices.getPrices().add(new Price(null, 101.964         , 0));
		pPrices.getPrices().add(new Price(null, 101.96          , 0));
		pPrices.getPrices().add(new Price(null, 101.98          , 0));
		pPrices.getPrices().add(new Price(null, 101.969         , 0));
		pPrices.getPrices().add(new Price(null, 101.95          , 0));
		pPrices.getPrices().add(new Price(null, 101.98          , 0));
		pPrices.getPrices().add(new Price(null, 102.09          , 0));
		pPrices.getPrices().add(new Price(null, 102.1768        , 0));
		pPrices.getPrices().add(new Price(null, 102.1299        , 0));
		pPrices.getPrices().add(new Price(null, 102.16          , 0));
		pPrices.getPrices().add(new Price(null, 102.144         , 0));
		pPrices.getPrices().add(new Price(null, 102.061         , 0));
		pPrices.getPrices().add(new Price(null, 102.035         , 0));
		pPrices.getPrices().add(new Price(null, 102.0635        , 0));
		pPrices.getPrices().add(new Price(null, 102.05          , 0));
		pPrices.getPrices().add(new Price(null, 102.0896        , 0));
		pPrices.getPrices().add(new Price(null, 102.07          , 0));
		pPrices.getPrices().add(new Price(null, 102.09          , 0));
		pPrices.getPrices().add(new Price(null, 102.059         , 0));
		pPrices.getPrices().add(new Price(null, 102.11          , 0));
		pPrices.getPrices().add(new Price(null, 102.09          , 0));
		pPrices.getPrices().add(new Price(null, 102.1632        , 0));
		pPrices.getPrices().add(new Price(null, 102.174         , 0));
		pPrices.getPrices().add(new Price(null, 102.19          , 0));
		pPrices.getPrices().add(new Price(null, 102.09          , 0));
		pPrices.getPrices().add(new Price(null, 102.165         , 0));
		pPrices.getPrices().add(new Price(null, 102.094         , 0));
		pPrices.getPrices().add(new Price(null, 102.15          , 0));
		pPrices.getPrices().add(new Price(null, 102.2           , 0));
		pPrices.getPrices().add(new Price(null, 102.3           , 0));
		pPrices.getPrices().add(new Price(null, 102.3432        , 0));
		pPrices.getPrices().add(new Price(null, 102.2627        , 0));
		pPrices.getPrices().add(new Price(null, 102.289         , 0));
		pPrices.getPrices().add(new Price(null, 102.3065        , 0));
		pPrices.getPrices().add(new Price(null, 102.295         , 0));
		pPrices.getPrices().add(new Price(null, 102.28          , 0));
		pPrices.getPrices().add(new Price(null, 102.27          , 0));
		pPrices.getPrices().add(new Price(null, 102.29          , 0));
		pPrices.getPrices().add(new Price(null, 102.2927        , 0));
		pPrices.getPrices().add(new Price(null, 102.3           , 0));
		pPrices.getPrices().add(new Price(null, 102.31          , 0));
		pPrices.getPrices().add(new Price(null, 102.305         , 0));
		pPrices.getPrices().add(new Price(null, 102.334         , 0));
		pPrices.getPrices().add(new Price(null, 102.31          , 0));
		pPrices.getPrices().add(new Price(null, 102.24          , 0));
		pPrices.getPrices().add(new Price(null, 102.26          , 0));
		pPrices.getPrices().add(new Price(null, 102.215         , 0));
		pPrices.getPrices().add(new Price(null, 102.2           , 0));
		pPrices.getPrices().add(new Price(null, 102.2           , 0));
		pPrices.getPrices().add(new Price(null, 102.2           , 0));
		pPrices.getPrices().add(new Price(null, 102.17          , 0));
		pPrices.getPrices().add(new Price(null, 102.07          , 0));
		pPrices.getPrices().add(new Price(null, 102.1           , 0));
		pPrices.getPrices().add(new Price(null, 101.94          , 0));
		pPrices.getPrices().add(new Price(null, 101.98          , 0));
		pPrices.getPrices().add(new Price(null, 101.98          , 0));
		pPrices.getPrices().add(new Price(null, 102.01          , 0));
		pPrices.getPrices().add(new Price(null, 102.07          , 0));
		pPrices.getPrices().add(new Price(null, 102.046         , 0));
		pPrices.getPrices().add(new Price(null, 102.0035        , 0));
		pPrices.getPrices().add(new Price(null, 101.994         , 0));
		pPrices.getPrices().add(new Price(null, 101.946         , 0));
		pPrices.getPrices().add(new Price(null, 101.8735        , 0));
		pPrices.getPrices().add(new Price(null, 101.8701        , 0));
		pPrices.getPrices().add(new Price(null, 101.975         , 0));
		pPrices.getPrices().add(new Price(null, 101.98          , 0));
		pPrices.getPrices().add(new Price(null, 101.96          , 0));
		pPrices.getPrices().add(new Price(null, 102.01          , 0));
		pPrices.getPrices().add(new Price(null, 101.85          , 0));
		pPrices.getPrices().add(new Price(null, 101.8851		, 0));
		pPrices.getPrices().add(new Price(null, 101.84          , 0));
		pPrices.getPrices().add(new Price(null, 101.8765        , 0));
		pPrices.getPrices().add(new Price(null, 101.85          , 0));
		pPrices.getPrices().add(new Price(null, 101.8745        , 0));
		pPrices.getPrices().add(new Price(null, 101.9101        , 0));
		pPrices.getPrices().add(new Price(null, 101.88          , 0));
		pPrices.getPrices().add(new Price(null, 101.87          , 0));
		pPrices.getPrices().add(new Price(null, 101.87          , 0));
		pPrices.getPrices().add(new Price(null, 101.92          , 0));
		pPrices.getPrices().add(new Price(null, 101.8849        , 0));
		pPrices.getPrices().add(new Price(null, 101.865         , 0));
		pPrices.getPrices().add(new Price(null, 101.86          , 0));
		pPrices.getPrices().add(new Price(null, 101.8903        , 0));
		pPrices.getPrices().add(new Price(null, 101.93          , 0));
		pPrices.getPrices().add(new Price(null, 101.91          , 0));
		pPrices.getPrices().add(new Price(null, 101.96          , 0));
		pPrices.getPrices().add(new Price(null, 101.97          , 0));
		pPrices.getPrices().add(new Price(null, 101.971         , 0));
		pPrices.getPrices().add(new Price(null, 101.92          , 0));
		pPrices.getPrices().add(new Price(null, 101.9199        , 0));
		pPrices.getPrices().add(new Price(null, 101.919         , 0));
		pPrices.getPrices().add(new Price(null, 101.82          , 0));
		pPrices.getPrices().add(new Price(null, 101.75          , 0));
		pPrices.getPrices().add(new Price(null, 101.7832        , 0));
		pPrices.getPrices().add(new Price(null, 101.7751        , 0));
		pPrices.getPrices().add(new Price(null, 101.76          , 0));
		pPrices.getPrices().add(new Price(null, 101.9           , 0));
		pPrices.getPrices().add(new Price(null, 101.811         , 0));
		pPrices.getPrices().add(new Price(null, 101.837         , 0));
		pPrices.getPrices().add(new Price(null, 101.64          , 0));
		pPrices.getPrices().add(new Price(null, 101.69          , 0));
		pPrices.getPrices().add(new Price(null, 101.785         , 0));
		pPrices.getPrices().add(new Price(null, 101.73          , 0));
		pPrices.getPrices().add(new Price(null, 101.76          , 0));
		pPrices.getPrices().add(new Price(null, 101.76          , 0));
		pPrices.getPrices().add(new Price(null, 101.7801        , 0));
		pPrices.getPrices().add(new Price(null, 101.665         , 0));
		pPrices.getPrices().add(new Price(null, 101.64          , 0));
		pPrices.getPrices().add(new Price(null, 101.64          , 0));
		pPrices.getPrices().add(new Price(null, 101.65          , 0));
		pPrices.getPrices().add(new Price(null, 101.67          , 0));
		pPrices.getPrices().add(new Price(null, 101.671         , 0));
		pPrices.getPrices().add(new Price(null, 101.7           , 0));
		pPrices.getPrices().add(new Price(null, 101.7101        , 0));
		pPrices.getPrices().add(new Price(null, 101.701         , 0));
		pPrices.getPrices().add(new Price(null, 101.7265        , 0));
		pPrices.getPrices().add(new Price(null, 101.734         , 0));
		pPrices.getPrices().add(new Price(null, 101.8123        , 0));
		pPrices.getPrices().add(new Price(null, 101.8268        , 0));
		pPrices.getPrices().add(new Price(null, 101.798         , 0));
		pPrices.getPrices().add(new Price(null, 101.848         , 0));
		pPrices.getPrices().add(new Price(null, 101.78          , 0));
		pPrices.getPrices().add(new Price(null, 101.79          , 0));
		pPrices.getPrices().add(new Price(null, 101.82          , 0));
		pPrices.getPrices().add(new Price(null, 101.775         , 0));
		pPrices.getPrices().add(new Price(null, 101.73          , 0));
		pPrices.getPrices().add(new Price(null, 101.6912        , 0));
		pPrices.getPrices().add(new Price(null, 101.75          , 0));
		pPrices.getPrices().add(new Price(null, 101.81          , 0));
		pPrices.getPrices().add(new Price(null, 101.87          , 0));
		pPrices.getPrices().add(new Price(null, 101.97          , 0));
		pPrices.getPrices().add(new Price(null, 101.9867        , 0));
		pPrices.getPrices().add(new Price(null, 101.9           , 0));
		pPrices.getPrices().add(new Price(null, 101.99          , 0));
		pPrices.getPrices().add(new Price(null, 102.04          , 0));
		pPrices.getPrices().add(new Price(null, 102.03          , 0));
		pPrices.getPrices().add(new Price(null, 102.12          , 0));
		pPrices.getPrices().add(new Price(null, 102.11          , 0));
		pPrices.getPrices().add(new Price(null, 102.0432        , 0));
		pPrices.getPrices().add(new Price(null, 102.0299        , 0));
		pPrices.getPrices().add(new Price(null, 102.01          , 0));
		pPrices.getPrices().add(new Price(null, 101.968         , 0));
		pPrices.getPrices().add(new Price(null, 102.005         , 0));
		pPrices.getPrices().add(new Price(null, 102.014         , 0));
		pPrices.getPrices().add(new Price(null, 102.01          , 0));
		pPrices.getPrices().add(new Price(null, 102.02          , 0));
		pPrices.getPrices().add(new Price(null, 102.0199        , 0));
		pPrices.getPrices().add(new Price(null, 101.99          , 0));
		pPrices.getPrices().add(new Price(null, 101.98          , 0));
		pPrices.getPrices().add(new Price(null, 101.9499        , 0));
		pPrices.getPrices().add(new Price(null, 101.869         , 0));
		pPrices.getPrices().add(new Price(null, 101.89          , 0));
		pPrices.getPrices().add(new Price(null, 101.8067        , 0));
		pPrices.getPrices().add(new Price(null, 101.8401        , 0));
		pPrices.getPrices().add(new Price(null, 101.88          , 0));
		pPrices.getPrices().add(new Price(null, 101.81          , 0));
		pPrices.getPrices().add(new Price(null, 101.8499        , 0));
		pPrices.getPrices().add(new Price(null, 101.83          , 0));
		pPrices.getPrices().add(new Price(null, 101.73          , 0));
		pPrices.getPrices().add(new Price(null, 101.77          , 0));
		pPrices.getPrices().add(new Price(null, 101.7899        , 0));
		pPrices.getPrices().add(new Price(null, 101.84          , 0));
		pPrices.getPrices().add(new Price(null, 101.91          , 0));
		pPrices.getPrices().add(new Price(null, 101.91          , 0));
		pPrices.getPrices().add(new Price(null, 101.97          , 0));
		pPrices.getPrices().add(new Price(null, 101.951         , 0));
		pPrices.getPrices().add(new Price(null, 101.97          , 0));
		pPrices.getPrices().add(new Price(null, 101.9678        , 0));
		pPrices.getPrices().add(new Price(null, 101.98          , 0));
		pPrices.getPrices().add(new Price(null, 101.94          , 0));
		pPrices.getPrices().add(new Price(null, 101.929         , 0));
		pPrices.getPrices().add(new Price(null, 101.9           , 0));
		pPrices.getPrices().add(new Price(null, 101.84          , 0));
		pPrices.getPrices().add(new Price(null, 101.86          , 0));
		pPrices.getPrices().add(new Price(null, 101.86          , 0));
		pPrices.getPrices().add(new Price(null, 101.855         , 0));
		pPrices.getPrices().add(new Price(null, 101.839         , 0));
		pPrices.getPrices().add(new Price(null, 101.83			, 0));
		pPrices.getPrices().add(new Price(null, 101.835         , 0));
		pPrices.getPrices().add(new Price(null, 101.84          , 0));
		pPrices.getPrices().add(new Price(null, 101.8401        , 0));
		pPrices.getPrices().add(new Price(null, 101.88          , 0));
		pPrices.getPrices().add(new Price(null, 101.9           , 0));
		pPrices.getPrices().add(new Price(null, 101.9           , 0));
		pPrices.getPrices().add(new Price(null, 101.89          , 0));
		pPrices.getPrices().add(new Price(null, 101.87          , 0));
		pPrices.getPrices().add(new Price(null, 101.9052        , 0));
		pPrices.getPrices().add(new Price(null, 101.891         , 0));
		pPrices.getPrices().add(new Price(null, 101.9553        , 0));
		pPrices.getPrices().add(new Price(null, 101.92          , 0));
		pPrices.getPrices().add(new Price(null, 101.97          , 0));
		pPrices.getPrices().add(new Price(null, 101.99          , 0));
		pPrices.getPrices().add(new Price(null, 101.9201        , 0));
		pPrices.getPrices().add(new Price(null, 101.96          , 0));
		pPrices.getPrices().add(new Price(null, 101.9601        , 0));
		pPrices.getPrices().add(new Price(null, 102             , 0));
		pPrices.getPrices().add(new Price(null, 101.9635        , 0));
		pPrices.getPrices().add(new Price(null, 101.921         , 0));
		pPrices.getPrices().add(new Price(null, 101.8774        , 0));
		pPrices.getPrices().add(new Price(null, 101.85          , 0));
		pPrices.getPrices().add(new Price(null, 101.835         , 0));
		pPrices.getPrices().add(new Price(null, 101.84          , 0));
		pPrices.getPrices().add(new Price(null, 101.785         , 0));
		pPrices.getPrices().add(new Price(null, 101.81          , 0));
		pPrices.getPrices().add(new Price(null, 101.8665        , 0));
		pPrices.getPrices().add(new Price(null, 101.8435        , 0));
		pPrices.getPrices().add(new Price(null, 101.82          , 0));
		pPrices.getPrices().add(new Price(null, 101.81          , 0));
		pPrices.getPrices().add(new Price(null, 101.821         , 0));
		pPrices.getPrices().add(new Price(null, 101.82          , 0));
		pPrices.getPrices().add(new Price(null, 101.8533        , 0));
		pPrices.getPrices().add(new Price(null, 101.715         , 0));
		pPrices.getPrices().add(new Price(null, 101.62          , 0));
		pPrices.getPrices().add(new Price(null, 101.635         , 0));
		pPrices.getPrices().add(new Price(null, 101.6           , 0));
		pPrices.getPrices().add(new Price(null, 101.62          , 0));
		pPrices.getPrices().add(new Price(null, 101.57          , 0));
		pPrices.getPrices().add(new Price(null, 101.46          , 0));
		pPrices.getPrices().add(new Price(null, 101.51          , 0));
		pPrices.getPrices().add(new Price(null, 101.53          , 0));
		pPrices.getPrices().add(new Price(null, 101.6138		, 0));
		Calculation.all(pPrices);
		System.out.println(pPrices);
	}
}