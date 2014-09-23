package Prices2;
import java.util.ArrayList;


public class Prices 
{
	private ArrayList<Price> _pPrices;
	
	public Prices ()
	{
		_pPrices = new ArrayList<Price>();
	}
	
	public ArrayList<Price> getPrices()
	{
		return _pPrices;
	}
	
	public void addPrice(Price price)
	{
		_pPrices.add(price);
	}
	public String toString()
	{
		String strReturn = "";
		strReturn += "POS\tPrice\t\tBolUP\t\tBolDown\n";
		for(int i = 0; i < _pPrices.size(); i++)
		{
			strReturn += "(" + (i + 1) + ")\t" + _pPrices.get(i).toString() + "\n";
		}
		return strReturn;
	}
}
