import java.util.Iterator;

public class TestIterators {

	public static void main(String[] args) {
		// IntBagIterator
		
		IntBag bag = new IntBag();
		//  … insert some elements
		for (int index = 1; index <= 5; index++) 
			bag.addValue(index);	
		
		Iterator i, j;
		i = bag.iterator();
		while ( i.hasNext() )
		{
			System.out.println( i.next() );
			j = bag.iterator();
			while ( j.hasNext() )
			{
				System.out.println( "--" + j.next() );
			}
		}
		
		System.out.println();
		
		//IntBagStepIterator
		
		IntBag bag1 = new IntBag(3);
		//  … insert some elements
		for (int index = 1; index <= 20; index++) 
			bag1.addValue(index);
		
		Iterator k;
		
	    k = bag1.iterator();
        while ( k.hasNext() )
        {
                System.out.println( "--" + k.next() );
        }
	}

}
