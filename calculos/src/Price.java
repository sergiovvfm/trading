import java.text.DecimalFormat;
import java.util.Date;


public class Price 
{

	private Date _dtDate;
	private double _dbPrice;
	private double _dbVolume;
	private double _dbSMA_12;
	private double _dbSMA_26;
	private double _dbEMA_12;
	private double _dbEMA_26;
	private double _dbMACD;
	private double _dbMACD_Signal;
	private double _dbMACD_Diff;
	
	public Price (Date dtDate, double dbPrice, double dbVolume)
	{
		_dtDate = dtDate;
		_dbPrice = dbPrice;
		_dbVolume = dbVolume;	
	}
	
	public void  setDate(Date dtValue)
	{
		_dtDate = dtValue;
	}
	
	public void  setPrice(double dbValue)
	{
		_dbPrice = dbValue;
	}
	
	public void  setVolume(double dbValue)
	{
		_dbVolume = dbValue;
	}

	public void setSMA(double dbValue, int nType) 
	{
		switch(nType)
		{
			case 12:
				_dbSMA_12 = dbValue;
				break;
			case 26:
				_dbSMA_26 = dbValue;
				break;
		}
	}
	
	public void setEMA(double dbValue, int nType) 
	{
		switch(nType)
		{
			case 12:
				_dbEMA_12 = dbValue;
				break;
			case 26:
				_dbEMA_26 = dbValue;
				break;
		}
	}


	public void setMACD(double _dbMACD) {
		this._dbMACD = _dbMACD;
	}

	public void setMACD_Signal(double _dbMACD_Signal) {
		this._dbMACD_Signal = _dbMACD_Signal;
	}

	public void setMACD_Diff(double _dbMACD_Diff) {
		this._dbMACD_Diff = _dbMACD_Diff;
	}		
	
	public Date getDate()
	{
		return _dtDate;
	}

	public double getPrice()
	{
		return _dbPrice;
	}

	public double getVolume()
	{
		return _dbVolume;
	}
	
	public double getSMA(int nType)
	{
		double dbReturn = 0;
		switch(nType)
		{
			case 12:
				dbReturn = _dbSMA_12;
				break;
			case 26:
				dbReturn = _dbSMA_26;
				break;
		}
		return dbReturn;
	}
	
	public double getEMA(int nType)
	{
		double dbReturn = 0;
		switch(nType)
		{
			case 12:
				dbReturn = _dbEMA_12;
				break;
			case 26:
				dbReturn = _dbEMA_26;
				break;
		}
		return dbReturn;
	}

	public double getMACD() {
		return _dbMACD;
	}

	public double getMACD_Signal() {
		return _dbMACD_Signal;
	}

	public double getMACD_Diff() {
		return _dbMACD_Diff;
	}
	
//	public String toString()
//	{
//		String strReturn = "";
//		SimpleDateFormat pFormatDate = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
//		if(_dtDate != null)
//		{
//			strReturn += pFormatDate.format(_dtDate);
//			strReturn += " - ";
//		}
//		strReturn += _dbPrice;
//		strReturn += " - ";
//		strReturn += _dbVolume;
//		strReturn += " - ";
//		strReturn += _dbSMA_9;
//		strReturn += " - ";
//		strReturn += _dbSMA_12;
//		strReturn += " - ";
//		strReturn += _dbSMA_26;
//		strReturn += " - ";
//		strReturn += _dbEMA_9;
//		strReturn += " - ";
//		strReturn += _dbEMA_12;
//		strReturn += " - ";
//		strReturn += _dbEMA_26;
//		strReturn += " - ";
//		strReturn += _dbMACD;
//		strReturn += " - ";
//		strReturn += _dbMACD_Signal;
//		strReturn += " - ";
//		strReturn += _dbMACD_Diff;		
//		return strReturn;
//	}
	
	public String toString()
	{
		DecimalFormat df = new DecimalFormat("0.0000"); 	
		String strReturn = "";
		strReturn += df.format(_dbPrice);
		strReturn += "\t";
		strReturn +=  df.format(_dbSMA_12);
		strReturn += "\t";
		strReturn +=  df.format(_dbSMA_26);
		strReturn += "\t";
		strReturn +=  df.format(_dbEMA_12);
		strReturn += "\t";
		strReturn +=  df.format(_dbEMA_26);	
		strReturn += "\t";
		strReturn +=  df.format(_dbMACD);	
		strReturn += "\t";
		strReturn +=  df.format(_dbMACD_Signal);	
		strReturn += "\t";
		strReturn +=  df.format(_dbMACD_Diff);	
		return strReturn;
	}

}
