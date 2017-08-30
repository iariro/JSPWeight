package kumagai.weight.test;

import java.sql.Date;

import kumagai.weight.BeforeAfterWeight;
import kumagai.weight.BeforeAfterWeightCollection;

public class BeforeAfterWeightCollectionTest
{
	public static void main(String[] args)
	{
		BeforeAfterWeightCollection weights = new BeforeAfterWeightCollection();
		weights.add(new BeforeAfterWeight(Date.valueOf("2017-08-20"), 61, 60));
		weights.add(new BeforeAfterWeight(Date.valueOf("2017-08-30"), 62, 61));
		System.out.println(weights.getChartPoints());
	}
}
