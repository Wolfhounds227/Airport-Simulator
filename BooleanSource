* Provides a random sequence of boolean values
 *
 */
package airportSimulator;

public class BooleanSource {
	
	private double probability; //the probability of query() returning true
	
	public BooleanSource(double prob){
		this.probability = prob;
		
	}
	
	/**
	 * 
	 * @return the next value of this BooleanSource
	 */
	public boolean query(){
		double rand = Math.random();
		return (rand < this.probability);
	}
	
	
	public static void main(String[] args){
		
		BooleanSource arrival = new BooleanSource(0.4);
		int countTrue = 0;
		int countFalse = 0;
		
		for (int i=0; i < 10; i++){
			if (arrival.query())
				countTrue++;
			else
				countFalse++;
		}
		
		System.out.println("Number of true "+countTrue);
		System.out.println("Number of true "+countFalse);
	}

}
