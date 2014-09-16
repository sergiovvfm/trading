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
	
	public String toString()
	{
		String strReturn = "";
		strReturn += "POS\tPrice\tSMA_12\tSMA_26\tEMA_12\tEMA_26\tMACD\tSignal\tDiff\n";
		for(int i = 0; i < _pPrices.size(); i++)
		{
			strReturn += "(" + (i + 1) + ")\t" + _pPrices.get(i).toString() + "\n";
		}
		return strReturn;
	}
}
