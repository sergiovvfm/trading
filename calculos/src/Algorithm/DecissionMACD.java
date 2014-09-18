package Algorithm;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Algorithm.DecissionEvent.Behavior;
import Prices.Price;

public class DecissionMACD 
{
	private DecissionEvent pDecissionEvent;
	private Behavior pLastBehavior;
    private List<Object> _listeners = new ArrayList<Object>();
	
    public synchronized void addDecissionEventListener(DecissionListener pListener) 
    {
        _listeners.add(pListener);
    }
    
    public synchronized void removeDecissionEventListener(DecissionListener pListener) 
    {
        _listeners.remove(pListener);
    }
    
    private synchronized void raiseDecissionEvent(DecissionEvent.Behavior pBehavior, double dbPrice) 
	{
		pDecissionEvent = new DecissionEvent( this, pBehavior, dbPrice);
		Iterator<Object> listeners = _listeners.iterator();
		while(listeners.hasNext()) 
		{
			DecissionListener pListener = (DecissionListener)listeners.next();
			pListener.DecissionReceived(pDecissionEvent);
    	}
	}
	    	
	public DecissionMACD()
	{
		pLastBehavior = Behavior.waitForSignal;
	}
	
	public void proccess(ArrayList<Price> pPrices)
	{
		Price pPrice;
		Price pPrevPrice;
		double dbDiff;
		double dbDiffPrev;	
		if(pPrices.size() <= 35)
		{
			raiseDecissionEvent(Behavior.waitForSignal, -1);
			return;
		}
		pPrice = pPrices.get(pPrices.size()-1);
		pPrevPrice = pPrices.get(pPrices.size()-2);
		dbDiff 	= pPrice.getMACD_Diff();
		dbDiffPrev 	= pPrevPrice.getMACD_Diff();
		
		
		DecimalFormat df = new DecimalFormat("+0.000000;-0.000000"); 	
		System.out.print(df.format(pPrice.getPrice()) + " * " + 
				df.format(pPrevPrice.getPrice()) + " | " + 
				df.format(dbDiff) + " * " +  
				df.format(dbDiffPrev));
		
		
		/* si ha un corte hacia abajo */
		if((dbDiffPrev >= 0) && (dbDiff <= 0))
		{
			/* si el valor anterior fue comprar, toca esperar a vender */
			if(pLastBehavior == Behavior.Sell)
			{
				raiseDecissionEvent(Behavior.waitToSell, pPrice.getPrice());
				pLastBehavior = Behavior.waitToSell;
			}
			else
			{	
				raiseDecissionEvent(Behavior.Buy, pPrice.getPrice());
				pLastBehavior = Behavior.Buy;
			}
		}
		else if((dbDiffPrev <= 0) && (dbDiff >= 0))
		{
			/* si el valor anterior fue comprar, toca esperar a vender */
			if(pLastBehavior == Behavior.Sell)
			{
				raiseDecissionEvent(Behavior.waitToBuy, pPrice.getPrice());
				pLastBehavior = Behavior.waitToBuy;
			}
			else
			{	
				raiseDecissionEvent(Behavior.Sell, pPrice.getPrice());
				pLastBehavior = Behavior.Sell;
			}
		}
		else if(((dbDiffPrev >= 0) && (dbDiff >= 0)) || ((dbDiffPrev <= 0) && (dbDiff <= 0)))
		{
			/* si el valor anterior fue comprar, toca esperar a vender */
			if((pLastBehavior == Behavior.waitToBuy) || (pLastBehavior == Behavior.Sell))
			{
				raiseDecissionEvent(Behavior.waitToBuy, pPrice.getPrice());
				pLastBehavior = Behavior.waitToBuy;
			}
			else if((pLastBehavior == Behavior.waitToSell) || (pLastBehavior == Behavior.Buy))
			{	
				raiseDecissionEvent(Behavior.waitToSell, pPrice.getPrice());
				pLastBehavior = Behavior.waitToSell;
			}  
			else if(pLastBehavior == Behavior.waitForSignal)
			{
				raiseDecissionEvent(Behavior.waitForSignal, pPrice.getPrice());
				pLastBehavior = Behavior.waitForSignal;
			}  
		}		
	}
}
