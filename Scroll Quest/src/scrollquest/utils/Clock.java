// Base code taken from "https://stackoverflow.com/questions/36494259/java-timer-class-timer-works-displays-errors" in a post by Thomas Milgrew
package scrollquest.utils;

import java.util.Timer;
import java.util.TimerTask;

public class Clock {

	int totalMins=0;
	int seconds=0;
	int hours=0;
	int minutes=0;

	TimerTask hourTask = new TimerTask(){
	    public void run() {
	        seconds++;
	        if(seconds == 60) totalMins++;
	        if(seconds==60 && minutes!=60 && hours!=60){
	            seconds=0;
	            minutes++;
	        }if(seconds==60 && minutes ==60 && hours!=60){
	            seconds=0;
	            minutes=0;
	            hours++;
	        }if(seconds==60 && minutes==60 && hours==60){
	            seconds=0;
	            minutes=0;
	            hours=0;
	        }
	    }
	};

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	//---------------------------------------------------------------------
	//constructor without parameters
	//---------------------------------------------------------------------
	public Clock (){
	    hours=0;
	    minutes=0;
	    seconds=0;
	    totalMins=0;
	}

	//---------------------------------------------------------------------
	//constructor with parameters
	//---------------------------------------------------------------------
	public Clock (int hrs, int min, int sec){
	    hours=hrs;
	    minutes=min;        
	    seconds=sec;
	    totalMins=min;
	}
	//---------------------------------------------------------------------
	//starts the clock
	//---------------------------------------------------------------------
	public void start(){

	    Timer hourTimer = new Timer(); 
	    hourTimer.scheduleAtFixedRate(hourTask, 1000, 1000);
	}
	//---------------------------------------------------------------------
	//sets the time
	//---------------------------------------------------------------------
	public void setTime(int hrs, int min, int sec){
	    hours=hrs;
	    minutes=min;
	    seconds=sec;        
	}

	//---------------------------------------------------------------------
	//gets the time
	//---------------------------------------------------------------------
	public String getTime(){
		String time = "";
		if (hours < 10) time = "0" + hours + ":";
		else time = hours + ":";
		if (minutes < 10) time = time + "0" + minutes + ":";
		else time = time + minutes + ":";
		if (seconds < 10) time = time + "0" + seconds;
		else time = time + seconds;
		return time;
		
	}
	
	public int getTotalMins() {
		return totalMins;
	}
}
