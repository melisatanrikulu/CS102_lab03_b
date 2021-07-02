/**
 * Allows a variable-sized collection of non-negative integer values to be stored. The last value is followed by a negative value.
 * @author Melisa Tanrýkulu
 * @version 2
 * 18.06.2021
 */

import java.util.Arrays;
import java.util.Iterator;

public class IntBag {
  // Instance Data Members
  private int[] bag;
  private int stepSize;
  
  // Constructor
  /**
   * Creates an empty collection with room for up to 4 values.
   */
  public IntBag() {
    bag = new int[4];
    stepSize = 1;
  }
  
  public IntBag(int stepSize) {
	  bag = new int[4];
	  this.stepSize = stepSize;
  }

  /**
   * Adds a value to the end of the collection.
   * @return true if added
   */  
  public boolean addValue( int value ) {
	  // Size of the IntBag
	int size = this.size();
	
	// Adds the value if it is nonnegative
	if ( value >= 0) {
		// Doubles the size of the array if needed
		if ( size + 1 == bag.length )
			bag = Arrays.copyOf( bag, bag.length * 2 );
    
		// Adds the number and adds -1 to the end of the IntBag
		bag[size] = value;
		bag[size + 1] = -1;
		
		// Informs the user that the value is added
		return true;
	}
	
	// If the value is negative informs the user that the value is not added
	else
		return false;
  }
  
  /**
   * Adds a value at a particular index location within it.
   * @return true if added
   */  
  public boolean addValue(int value, int index) {
	// Size of the IntBag
	  int size = this.size();
	  
	// Adds the value if it is nonnegative
	  if ( value >= 0 ) { 
		// Adds the value if index is valid
		  if ( 0 <= index && index <= size ) {			
			  // Doubles the size of the array if needed
			  if ( size + 1 == bag.length )
				  bag = Arrays.copyOf( bag, bag.length * 2 );
		    
			  // Moves the values up
			  for ( int i = size ; i >= index; i--)
				  this.bag[i + 1] = this.bag[i];

			  // Adds the value to the specific index
			  this.bag[index] = value;
			  
			  // If size was 0, makes sure that -1 is added
			  if ( size == 0 ) 
				  this.bag[1] = -1;
			  
			// Informs the user that the value is added
			  return true;
		  }
	  }
	  
	// If the value is negative or index is invalid informs the user that the value is not added
	  return false;
  }
  
  /**
   * Removes the value at a given index by placing the last element of the array into that index.
   * @return true if removed
   */
  public boolean removeValue( int index ) {
	// Size of the IntBag
	  int size = this.size();
	  
  	// Checks if index is within bounds
    if ( 0 <= index && index < size ) {
    	// Moves the last value to the given index
      this.bag[index] = this.bag[size - 1];
      // Last value is made -1
      this.bag[size - 1] = -1;
      // -1 is made 0
      this.bag[size] = 0;
      // Informs the user that the value is removed
      return true;
    }
    
    // If index is invalid informs the user that the value is not removed
    return false;
  }
  
  /**
   * Tests whether the collection contains a given value or not.
   * @return true if contains
   */
  public boolean contains( int value ) {
	// Size of the IntBag
	  int size = this.size();
	  
	  // If contains returns true
    for ( int i = 0; i < size; i++ ) {
      if ( this.bag[i] == value )
        return true;
    }
    
 // If does not contain returns false
    return false;
  }
  
  /**
   * Returns a String representation of the collection.
   * @return String representation
   */
  public String toString() {
    String repr;
    int size = this.size();
    
    repr = "[";
    
    if ( size > 0) {
    	for (int i = 0; i < size - 1; i++ )
    		repr += this.bag[i] + ", ";
      
    	repr += this.bag[ size - 1] + "]";
    }
    else
    	repr += "]";
    
    return repr;
  }
  
  /**
   * Returns the number of values currently in the collection.
   * @return the size
   */
  public int size() {
	  int size = 0;
	  
	  for ( int i = 0; i < this.bag.length; i++ ) {
		  if ( this.bag[i] < 0 )
			  return size;
		  // Increments the size if the value is positive
		  size++;
	  }
	  
	  // If the code made it until here, then the array is empty
	  return 0;
  }
  
  /**
   * Allows you to get the value at location 'index' within the collection.
   * @return index
   */
  public int getValue( int index ) {
    // Returns -1 if there is no value at location 'index'
    int value = -1;
    int size = this.size();
    
    //Checks if index is within bounds
    if ( 0 <= index && index < size ) 
      value = this.bag[ index ];
    
    return value;
  }
  
  /**
   * Removes all instances of a given value in the collection.
   * @return true if the value is contained
   */
  public boolean removeAll( int value ) {
	  boolean contains;
	  int size = this.size();
	   
	  contains = false;
	   // Searches the array for the value
	   for ( int i = 0; i < size; i++ ) {
		   // If the value is found, moves other values down
		   if ( this.bag[i] == value ) {
			   contains = true;
			   for ( int j = i; j < size - 1; j++) {
				   this.bag[j] = this.bag[j + 1];   
			   }
			   // Makes sures that -1 is the last value and updates the size
			   this.bag[size - 1] = -1;
			   size = this.size();
			   
			   // Reduces i so that no values are skipped
			   i--;
		   }
	   }
	   
	   return contains;
  }
  
  public Iterator iterator() { 
	  Iterator iterator;
	  if ( this.stepSize == 1 )	
		  iterator = new IntBagIterator(this);
	  else
		  iterator = new IntBagStepIterator(this, stepSize);
	  
	  return iterator;
  }
  
  public class IntBagIterator implements Iterator {
		
		private IntBag aBag;
		private int index;
		
		public IntBagIterator(IntBag aBag ) {
			index = 0;
			this.aBag = aBag;
		}
		
		public Object next() {
			int number;
			
			number = aBag.getValue(index);
			index++;
			
			Integer numberObj = new Integer(number);
			
			return numberObj;
		}
		
		public boolean hasNext() {
			int size;
			
			size = aBag.size();
			
			return (index < size);
		}
	}
  
  public class IntBagStepIterator implements Iterator {
	  
	  private IntBag aBag;
	  private int index;
	  private int stepValue;
	  private int size;
	  
	  public IntBagStepIterator( IntBag aBag, int m) {
		  index = 0;
		  this.aBag = aBag;
		  this.stepValue = m;
		  
	  }
	  
	  public Object next() {
			int number;
			
			number = aBag.getValue(index);
			index += stepValue;
			
			Integer numberObj = new Integer(number);
			
			return numberObj;
		}
		
	  public boolean hasNext() {		
		  size = aBag.size();
		  //System.out.println("Index: " + index + " Size: " + size + " Index + stepValue: " + (index+stepValue));
		  return (index < size);
	  }
  }
}