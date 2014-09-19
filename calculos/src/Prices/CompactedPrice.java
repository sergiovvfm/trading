package Prices;

import java.util.ArrayList;
import java.util.Date;

public class CompactedPrice extends Price
{
	private ArrayList<Price> pInnerPrices;
	
	public CompactedPrice(Date dtDate, double dbPrice, double dbVolume)
	{
		super(dtDate, dbPrice, dbVolume);
		pInnerPrices = new ArrayList<Price>();
	}
	
	public void addInnerPrice(Price pPrice)
	{
		double dbNewPriceValue = 0;
		pInnerPrices.add(pPrice);
		for(Price pInnerPrice : pInnerPrices)
		{
			dbNewPriceValue += pInnerPrice.getPrice();
		}
		dbNewPriceValue = dbNewPriceValue / pInnerPrices.size();
		this.setPrice(dbNewPriceValue);
		this.setVolume(this.getVolume() + pPrice.getVolume());
	}	
}
