package Algorithm;

import java.util.EventObject;

public class DecissionEvent extends EventObject 
{

	private static final long serialVersionUID = 1L;

	public enum Behavior
	{
		waitForSignal,	// esperando a calcular una se�al
		Buy,			// se�al de compra
		Sell,			// se�al de venta
		waitToSell,		// esperando para vender
		waitToBuy		// esperando para comprar
	}
	
	private Behavior _pBehavior;
	private double _dbPrice;
    
    public DecissionEvent(Object pObject, Behavior pBehavior, double dbPrice) 
    {
        super(pObject);
        _pBehavior = pBehavior;
        _dbPrice = dbPrice;
    }
    
    public Behavior getBehavior() 
    {
        return _pBehavior;
    }
    
    public double getPrice() 
    {
        return _dbPrice;
    }

}
