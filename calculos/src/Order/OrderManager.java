package Order;

import Algorithm.DecissionEvent;
import Algorithm.DecissionListener;

public class OrderManager implements DecissionListener 
{

	
	@Override
	public void DecissionReceived(DecissionEvent event) 
	{
		if (event.getBehavior() == DecissionEvent.Behavior.Buy)
		{
			System.out.println("\tCOMPRA\t" + event.getPrice());
		}
		else if (event.getBehavior() == DecissionEvent.Behavior.Sell)
		{
			System.out.println("\tVENDE\t" + event.getPrice());
		}
		else if (event.getBehavior() == DecissionEvent.Behavior.waitForSignal)
		{
			System.out.println(/*"\tESPERA a CALCULAR"*/);
		}
		else if (event.getBehavior() == DecissionEvent.Behavior.waitToBuy)
		{
			System.out.println(/*"\tESPERA a comprar"*/);
		}
		else if (event.getBehavior() == DecissionEvent.Behavior.waitToSell)
		{
			System.out.println(/*"\tESPERA a vender"*/);
		}
		
	}

}
