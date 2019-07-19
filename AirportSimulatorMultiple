package airportSimulator;
import java.util.LinkedList;
import java.util.Queue;

public class AirportSimulatorMultiple {
	
	/**
	 * 
	 * @param args[0] = landingTime
	 * @param args[1] = takeoffTime 
	 * @param args[2] = landingProb
	 * @param args[3] = takeoffProb
	 * @param args[4] = totalTime
	 * @param args[5] = number of runways
	 */
	public static void main(String[] args){
		 
		int landingTime, takeoffTime;
		double landingProb, takeoffProb;
		int totalTime;
		int runwayCount;
		
		if(args.length < 4) {
			System.out.println("not enough parameters");
			System.exit(-1);
		}
		
		landingTime = Integer.parseInt(args[0]);
		takeoffTime = Integer.parseInt(args[1]);
		landingProb = Double.parseDouble(args[2]);
		takeoffProb = Double.parseDouble(args[3]);
		totalTime   = Integer.parseInt(args[4]);
	    runwayCount = Integer.parseInt(args[5]); 
		
		System.out.println("landing time "+ landingTime);
		System.out.println(""
				+ "takeoff time "+ takeoffTime);
		System.out.println("landing probability "+ landingProb);
		System.out.println("takeoff probability "+ takeoffProb);
		System.out.println("Total time "+ totalTime);
		System.out.println("Number of Runways " + runwayCount);
		System.out.println("==============");
		
		airportSimulate( landingTime, takeoffTime, landingProb, takeoffProb , totalTime, runwayCount );
	}
	
	/**
	 * 
	 * The simulate method is changed as follows:
a.Takes five input parameters to represent: 
landingTime, takeoffTime, landingProb, takeoffProb, and totalTime.

b.There  should  be  two  instances  of BooleanSource to  simulate  landing 
planes arrival and taking off planes arrival. Similarly, 
there should be two queues to landing and taking off planes. Also, there should be two instances 
of Averager to calculate the average wait time for landing and average wait time for taking off.
	 * @param washTime
	 * @param arrivalProb
	 * @param totalTime
	 */
	public static void airportSimulate(int landingTime, int takeoffTime,
			double landingProb,double takeoffProb, int totalTime, int runwayCount) {
		BooleanSource arrival = new BooleanSource(landingProb);
		Queue<Integer> arrivals = new LinkedList<Integer>();
		Averager landingAverager = new Averager();
		
		BooleanSource departure = new BooleanSource(takeoffProb);
		Queue<Integer> departures = new LinkedList<Integer>();
		Averager takeoffAverager = new Averager();
		int[] currentStartTime = new int[runwayCount];
		int[] currentType = new int[runwayCount];
		Runway[] runways = new Runway[runwayCount]; 
		for(int ridx=0;ridx<runwayCount;ridx++) {
			runways[ridx] = new Runway(landingTime,takeoffTime );
			currentType[ridx] = -1;
			currentStartTime[ridx] = 0;
		}
		int currentClockMinute;
		int tempStart, tempDuration;
		int arrivalCount = 0, departureCount = 0;
		for (currentClockMinute = 1; currentClockMinute <= totalTime; currentClockMinute++) {
			 //System.out.println( "current Minute = " + currentClockMinute );
			
			// if each runway is busy reduce service time.
			// if service complete update averages
			for(int ridx=0;ridx<runwayCount;ridx++) {
				if( runways[ridx].isBusy() ) {
					runways[ridx].reduceServiceTime();
					tempStart = runways[ridx].isServiceComplete();
					if(tempStart > 0) {
						tempDuration = currentClockMinute - currentStartTime[ridx];
						if(currentType[ridx] == Runway.TAKEOFF_TYPE) {
							takeoffAverager.addNumber(tempDuration);
						} else if(currentType[ridx] == Runway.LANDING_TYPE) {
							landingAverager.addNumber(tempDuration);
						}
						currentStartTime[ridx] = 0;
						currentType[ridx] = -1;
					}
				}
			}
			
			//check for flight arrival
			//if (arrival) add arrival to arrival queue
			if(isFlight(landingProb)) {
				arrivals.add(currentClockMinute);
				arrivalCount++;
			}
			
			// check for departure 
			// if (departure) add to departure queue
			if(isFlight(takeoffProb)) {
				departures.add(currentClockMinute);
				departureCount++;
			}

			// if a runway is empty
			// 		if arrival queue not empty
			//      	land flight from arrival queue
			//		else if departure queue not empty
			//depart flight from departure queue
			for(int ridx=0;ridx<runwayCount;ridx++) {
				if(!runways[ridx].isBusy()) {
					if(!arrivals.isEmpty()) {
						currentStartTime[ridx] = arrivals.remove();
						currentType[ridx] = Runway.LANDING_TYPE;
						runways[ridx].startService(currentStartTime[ridx], currentType[ridx]);
					} else if(!departures.isEmpty()) {
						currentStartTime[ridx] = departures.remove();
						currentType[ridx] = Runway.TAKEOFF_TYPE;
						runways[ridx].startService(currentStartTime[ridx], currentType[ridx]);
					}
				}
			}				
		}
		System.out.println("Arrival Count = " + arrivalCount + " Departure Count = " + departureCount);	
		//Write summary information about the simulation
		System.out.println("landing averager = " + landingAverager.average() + " " + "Takeoff averager = " + takeoffAverager.average());;
		System.out.println("Left in landing Queue = " + arrivals.size() + " " + "Left in Takeoff Queue = " + departures.size());
	
	}
	
	private static boolean isFlight(double pProb) {
		double tempNum = Math.random();
		return tempNum < pProb;

	}
	
}
