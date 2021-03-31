package cm2100.c2p1.model;

/**
 * Model a simple time with hour and minute.
 * @author Kit-ying Hui
 *
 */
public class Time
{
/**
 * The hour of a time in the range 0-23.
 */
private int hour;

/*
 * The minute of a time in the range 0-59.
 */
private int minute;

/**
 * Create a Time object.
 * @param hour		The hour value.
 * @param minute	The minute value.
 */
public Time(int hour,int minute)
{
this.hour=hour;
this.minute=minute;
} //end method

/**
 * Compare the Time object with an <code>java.lang.Object</code> object.
 * @param obj	The <code>java.lang.Object</code> object to compare with.
 * @return	true if the 2 objects represent the same time. i.e. both hour and minute are the same.
 */
public boolean equals(Object obj)
{
if (!(obj instanceof Time))
	return false;
Time timeObject=(Time)obj;
if (this.hour!=timeObject.hour || this.minute!=timeObject.minute)
	return false;
return true;
} //end method

/**
 * Return a time as a human-readable String.
 */
public String toString()
{
String result="";

if (this.hour<10)
	result+="0"+this.hour+":";
else result+=this.hour+":";
if (this.minute<10)
	result+="0"+this.minute;
else result+=this.minute;
return result;
} //end method

/**
 * Get the hour of the time.
 * @return The hour in te range 0-23.
 */
public int getHour() {
    return hour;
}

/**
 * Set the hour of the time.
 * @param hour The new hour value in the range 0-23.
 */
public void setHour(int hour) {
    this.hour = hour;
}

/**
 * Get the minute of the time.
 * @return The minute in the range 0-59.
 */
public int getMinute() {
    return minute;
}

/**
 * Set the minute of the time.
 * @param minute The new minute value in the range 0-59.
 */
public void setMinute(int minute) {
    this.minute = minute;
}

/**
 * Calculate the hash code which is needed for any hash map operation.
 * We are using the simple formula of hour*100+minute.
 */
public int hashCode()
{
return this.hour*100+this.minute;	
} //end method
} //end class
