package Prices2;
import java.text.DecimalFormat;
import java.util.Date;


public class Price 
{

	private Date _dtDate;
	private double _dbPrice;
	private double _dbVolume;
	private double _dbSMA_Short;
	private double _dbSMA_Long;
	private double _dbEMA_Short;
	private double _dbEMA_Long;
	private double _dbMACD;
	private double _dbMACD_Signal;
	private double _dbMACD_Diff;
	private double _dbRSI_avg_Gain;
	private double _dbRSI_avg_Loss;
	private double _dbRSI;
	private double _dbBollinger_Up;
	private double _dbBollinger_Down;
	
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

	public void setSMA_Short(double dbValue) 
	{
		_dbSMA_Short = dbValue;
	}
	
	public void setSMA_Long(double dbValue) 
	{
		_dbSMA_Long = dbValue;
	}

	public void setEMA_Short(double dbValue) 
	{
		_dbEMA_Short = dbValue;
	}
	
	public void setEMA_Long(double dbValue) 
	{
		_dbEMA_Long = dbValue;
	}

	public void setMACD(double _dbMACD)
	{
		this._dbMACD = _dbMACD;
	}

	public void setMACD_Signal(double _dbMACD_Signal) 
	{
		this._dbMACD_Signal = _dbMACD_Signal;
	}

	public void setMACD_Diff(double _dbMACD_Diff) 
	{
		this._dbMACD_Diff = _dbMACD_Diff;
	}		

	public void setRSI_avg_Gain(double _dbRSI_avg_Gain) 
	{
		this._dbRSI_avg_Gain = _dbRSI_avg_Gain;
	}	

	public void setRSI_avg_Loss(double _dbRSI_avg_Loss) 
	{
		this._dbRSI_avg_Loss = _dbRSI_avg_Loss;
	}		

	public void setRSI(double _dbRSI) 
	{
		this._dbRSI = _dbRSI;
	}	

	public void setBollingerUp(double _dbBollinger_Up) 
	{
		this._dbBollinger_Up = _dbBollinger_Up;
	}

	public void setBollingerDown(double _dbBollinger_Down) 
	{
		this._dbBollinger_Down = _dbBollinger_Down;
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
	
	public double getSMA_Short()
	{
		return _dbSMA_Short;
	}
	
	public double getSMA_Long()
	{
		return _dbSMA_Long;
	}
	
	public double getEMA_Short()
	{
		return _dbEMA_Short;
	}
	
	public double getEMA_Long()
	{
		return _dbEMA_Long;
	}

	public double getMACD() 
	{
		return _dbMACD;
	}

	public double getMACD_Signal() 
	{
		return _dbMACD_Signal;
	}

	public double getMACD_Diff() 
	{
		return _dbMACD_Diff;
	}
	
	public double getRSI_avg_Gain() 
	{
		return _dbRSI_avg_Gain;
	}
	
	public double getRSI_avg_Loss() 
	{
		return _dbRSI_avg_Loss;
	}
	
	public double getRSI() 
	{
		return _dbRSI;
	}
	
	public double getBollingerUp() 
	{
		return _dbBollinger_Up;
	}
	
	public double getBollingerDown() 
	{
		return _dbBollinger_Down;
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
		DecimalFormat df = new DecimalFormat("000.0000"); 	
		String strReturn = "";
		strReturn += df.format(_dbPrice);
		strReturn += "\t";
		strReturn +=  df.format(_dbBollinger_Up);
		strReturn += "\t";
		strReturn +=  df.format(_dbBollinger_Down);
		return strReturn;
	}

}
