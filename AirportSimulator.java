	package airportSimulator;
	import java.util.LinkedList;
	import java.util.Queue;
	
	public class AirportSimulator {
		
		/**
		 * 
		 * @param args[0] = landingTime
		 * @param args[1] = takeoffTime 
		 * @param args[2] = landingProb
		 * @param args[3] = takeoffProb
		 * @param args[4] = totalTime
		 * 
		 */
		public static void main(String[] args){
			 
			int landingTime, takeoffTime;
			double landingProb, takeoffProb;
			int totalTime;
			
			if(args.length < 4) {
				System.out.println("not enough parameters");
				System.exit(-1);
			}
			
			landingTime = Integer.parseInt(args[0]);
			takeoffTime = Integer.parseInt(args[1]);
			landingProb = Double.parseDouble(args[2]);
			takeoffProb = Double.parseDouble(args[3]);
			totalTime   = Integer.parseInt(args[4]);
			
			System.out.println("landing time "+ landingTime);
			System.out.println(""
					+ "takeoff time "+ takeoffTime);
			System.out.println("landing probability "+ landingProb);
			System.out.println("takeoff probability "+ takeoffProb);
			System.out.println("Total time "+ totalTime);
			System.out.println("==============");
			
			airportSimulate( landingTime, takeoffTime, landingProb, takeoffProb , totalTime );
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
				double landingProb,double takeoffProb, int totalTime) {
			BooleanSource arrival = new BooleanSource(landingProb);
			Queue<Integer> arrivals = new LinkedList<Integer>();
			Averager landingAverager = new Averager();
			
			BooleanSource departure = new BooleanSource(takeoffProb);
			Queue<Integer> departures = new LinkedList<Integer>();
			Averager takeoffAverager = new Averager();
			
			Runway runway1 = new Runway(landingTime,takeoffTime );
		
			int currentClockMinute;
			int currentStartTime = 0;
			int currentType = -1;
			int tempStart, tempDuration;
			int arrivalCount = 0, departureCount = 0;
			for (currentClockMinute = 1; currentClockMinute <= totalTime; currentClockMinute++) {
				 //System.out.println( "current Minute = " + currentClockMinute );
				
				// if service finished, update averager
				// if runway is busy reduce service time.	
				if( runway1.isBusy() ) {
					runway1.reduceServiceTime();
					tempStart = runway1.isServiceComplete();
					if(tempStart > 0) {
						tempDuration = currentClockMinute - currentStartTime;
						if(currentType == Runway.TAKEOFF_TYPE) {
							takeoffAverager.addNumber(tempDuration);
						} else if(currentType == Runway.LANDING_TYPE) {
							landingAverager.addNumber(tempDuration);
						}
						currentStartTime = 0;
						currentType = -1;
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
	
				// if runway is empty
				// 		if arrival queue not empty
				//      	land flight from arrival queue
				//		else if departure queue not empty
				//			depart flight from departure queue	
				if(!runway1.isBusy()) {
					if(!arrivals.isEmpty()) {
						currentStartTime = arrivals.remove();
						currentType = Runway.LANDING_TYPE;
						runway1.startService(currentStartTime, currentType);
					} else if(!departures.isEmpty()) {
						currentStartTime = departures.remove();
						currentType = Runway.TAKEOFF_TYPE;
						runway1.startService(currentStartTime, currentType);
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
