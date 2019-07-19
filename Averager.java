package airportSimulator;

public class Averager {
	private int count; //how many numbers have been given to this avergaer
	private double sum; //sum of all numbers given to this averager
	
	public Averager(){
		this.count = 0;
		this.sum = 0;
	}
	/**
	 * 
	 * @param number the number to be added to this Averager
	 */
	public void addNumber(double value){
		this.count++;
		this.sum += value;
		
	}
	
	/**
	 * @return the average of all numbers given to this averager
	 */
	public double average(){
		if (count == 0)
			return Double.NaN;
		else
			return this.sum/this.count;
	}
	
	/**
	 * @return the count of how many numbers have been given to this Averager
	 */
	public int howManyNumbers(){
		return this.count;
	}
	
	public static void main(String[] args){
		
		Averager avg = new Averager();
		
		avg.addNumber(10);
		avg.addNumber(15);
		avg.addNumber(23);
		
		System.out.println("Count "+avg.howManyNumbers());
		System.out.println("Average "+avg.average());
	}

}


