package cm2100.c2p1.model;

/**
 * This class models a show in cinema.
 * A show is tied to a screen, a date and a time.
 * @author Kit-ying Hui
 *
 */
public class Show
{
/**
 * The name of the movie.
 */
protected String name;

/**
 * The starting time of the show.
 */
protected Time startTime;
/**
 * The duration of the show in minute.
 */
protected int duration;

/**
 * Create a show.
 * @param name		The movie title.
 * @param startTime	The starting time.
 * @param duration	The duration.
 */
public Show(String name,Time startTime,int duration)
{
this.name=name;
this.startTime=startTime;
this.duration=duration;
} //end method

/**
 * Get the movie title of the show.
 * @return	The movie title of the show.
 */
public String getName()
{
return this.name;
} //end method

/**
 * Set the show's name.
 * @param name The new show name.
 */
public void setName(String name)
{
this.name=name;
}

/**
 * Get the starting time of the show.
 * @return	The starting time of the show.
 */
public Time getStartTime()
{
return this.startTime;
} //end method

/**
 * Set the starting time of the show.
 * @param time The new time.
 */
public void setStartTime(Time time)
{
this.startTime=time;    
} //end method

/**
 * Get the duration of the show.
 * @return	The duration of the show in minute.
 */
public int getDuration()
{
return this.duration;
} //end method

/**
 * Set the duration of the show.
 * @param duration The new duration.
 */
public void setDuration(int duration)
{
this.duration=duration;    
} //end method

/**
 * Find the end time of the show based on the starting time and duration.
 * @return	The ending time of the show.
 */
public Time endTime()
{
int endMinute=this.startTime.getMinute()+duration;
int extraHour=endMinute/60;
return new Time((this.startTime.getHour()+extraHour)%24,endMinute%60);
} //end method

/**
 * Check if the current show equals to another <code>java.lang.Object</code> object.
 * @param obj	The <code>java.lang.Object</code> to compare with.
 * @return	<code>true</code> if the parameter is a {@link Show} object with the same, move title,
 * 			starting time and duration.
 * 			<code>false</code> otherwise.
 */
public boolean equals(Object obj)
{
if (!(obj instanceof Show))
    return false;
Show showObject=(Show)obj;
return  this.name.equals(showObject.getName()) &&
        this.startTime.equals(showObject.getStartTime()) &&
        this.duration==showObject.getDuration();
} //end method

/**
 * Get the hash code of the show object.
 * We use the simple formula of "movie title hash code" + "start time hash code" + "duration".
 * @return	The hash code of the show.
 */
public int hashCode()
{
return this.name.hashCode()+this.startTime.hashCode()+duration;
} //end method

/**
 * Return the show as a {@link String}, including the movie title, starting time and ending time.
 * @return The details of the show as a {@link String}.
 */
public String toString()
{
return this.name+" "+this.startTime+"-"+this.endTime();
} //end method
} //end class
