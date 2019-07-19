package airportSimulator;
/**
 * Replace the secondsForWash instance variable with two instance variables,
 *  minutesForLanding and minutesForTakeoff.
 *  Change the constructor accordingly.
 *  
 * .
	The startService method  needs  to  take  an  input  parameter  to  specify 
	whether  the  service  is  landing  or  taking  off.  Assume  landing  is  type  1  and 
	takeoff is type 0. The method then sets the serviceTimeLeft
	according to the type of the service
 */
public class Runway {
	public static int TAKEOFF_TYPE = 0;
	public static int LANDING_TYPE = 1;
	private int minutesForLanding;
	private int minutesForTakeoff;
	private int startTime;
	private int serviceTimeLeft;
	
	/**
	 * Initializes a runway
	 * @param s number of minutes for takeoff or landing
	 */
	public Runway(int pMinutesForLanding,int pMinutesForTakeoff ){
		this.minutesForLanding = pMinutesForLanding;
		this.minutesForTakeoff = pMinutesForTakeoff;
		this.startTime = 0;
		this.serviceTimeLeft = 0;
	}
	
	public boolean startService(int pStartTime, int pServiceType) {
		if (this.serviceTimeLeft > 0)
			throw new IllegalStateException("Runway is already busy");

		if(pServiceType == TAKEOFF_TYPE) {
			this.serviceTimeLeft = this.minutesForTakeoff;
		} else if(pServiceType == LANDING_TYPE) {
			this.serviceTimeLeft = this.minutesForLanding;
		} else {
			return false;
		}
		this.startTime = pStartTime;
		return true;
	}
	
	/**
	 * determines whether this runway is currently busy 
	 * @return true if the runway is busy during a takeoff or landing and false otherwise
	 * will be is
	 */
	public boolean isBusy(){
		return this.serviceTimeLeft > 0;
	}
	
	/**
	 * if the current service is complete return its start time otherwise return 0
	 */
	public int isServiceComplete(){
		int tempStartTime = 0;
		if (this.serviceTimeLeft <= 0) {
			tempStartTime = this.startTime;
			this.startTime = 0;
		}
		return tempStartTime;
	}

	 // reduce the remaining time for the current service
	public void reduceServiceTime(){
		if (this.serviceTimeLeft > 0)
			this.serviceTimeLeft--;
	}
}
