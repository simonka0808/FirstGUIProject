package cm2100.c2p1.model;

import java.util.*;

/**
 * This class models a mega-store that stores the screen, shows and bookings in a cinema.
 * In normal circumstances you do not need to access the {@link MegaStore} directly
 * but use use methods of the {@link Cinema} to access shows and bookings.
 * 
 * @author Kit-ying Hui
 *
 */
public class MegaStore
{
private Screen screen;			//the only screen in the cinema	
private Set<Show> showStore;		//a map of show name into show
private Set<Booking> bookingStore;	//a set of bookings

/**
 * Create an empty mega store.
 * Note that no screen is initialised in a new MegaStore.
 * You should create a {#link Screen} object which specifies the seats,
 * and assign to the MegaStore by a subsequent call to {@link #setScreen(Screen) setScreen(Screen)}.
 */
public MegaStore()
{
this.screen=null;
this.showStore=new HashSet<Show>();
this.bookingStore=new HashSet<Booking>();
} //end method

/**
 * Set the screen in the mega store.
 * The screen contains the seat arrangement.
 * 
 * @param screen	The screen which specifies the seat.
 */
public void setScreen(Screen screen)
{
this.screen=screen;
} //end method

/**
 * Get the screen in the mega store.
 * @return The screen in the mega store.
 */
public Screen getScreen()
{
return this.screen;
} //end method

/**
 * Add a show into the store.
 * This method is only called by the Cinema object.
 * @param show	The show to add.
 * @return      <code>true</code> if adding is ok. <code>false</code> otherwise.
 */
public boolean addShow(Show show)
{
if (this.showStore.contains(show))  //if show is already in mega store's set of shows
    return false;

this.showStore.add(show);   //otherwise add it into the set
return true;
} //end method

/**
 * Get all shows in the mega store as a <code>Set</code> of <code>Show</code> object.
 * @return	All shows in the mega store.
 */
public Set<Show> allShows()
{
return this.showStore;
} //end method

/**
 * Delete a show from the mega store.
 * Notice that deleting a show does not delete its bookings.
 * You need to explicitly find out all bookings on the show and delete them first.
 * 
 * @param show The show to delete.
 */
public void deleteShow(Show show)
{
this.showStore.remove(show);
} //end method

/**
 * This is a versatile method to find shows in the mega store based on different criteria.
 * 
 * @param titleKeyword	This is either a keyword in the movie title, or the full title.
 *                      If this is <code>null</code>, the keyword is ignored.
 * @param partialMatch  Indicate if this is a partial match on the keyword.
 *                      If this is <code>true</code>, the keyword will be used in a partial match.
 *                      If <code>false</code>, it will be an exact match on the show title.
 * @param startTime     The starting time of the show.
 *                      If this is <code>null</code>, it will be ignored.
 * @param duration      The duration of the show.
 *                      If this is 0 or negative, it will be ignored.
 * @return		All shows in the mega store matching the criteria set by the parameters.
 */
public Set<Show> findShows(String titleKeyword,boolean partialMatch,Time startTime,int duration)
{
Set<Show> allShows=this.allShows();     //get all the shows in the mega store
Set<Show> result=new HashSet<Show>();   //empty set to hold result

for (Show show:allShows)
    {
    //if keyword is specified, it is a partial match, and keyword is not found in show name, skip
    //otherwise, either keyword is null, or keyword is found in show name as a sub-string
    if (titleKeyword!=null && partialMatch && show.getName().toLowerCase().indexOf(titleKeyword.toLowerCase())<0)
        continue;

    //if keyword is specified, it is an exact match, and show name does not match keyword, skip
    //otherwise, either keyword is null, or keyword and show name are the same
    if (titleKeyword!=null && !partialMatch && !show.getName().equals(titleKeyword))
        continue;
    
    //if startTime is specified, and show times do not match, skip
    //otherwise, either startTime is null, or 2 the times are equal
    if (startTime!=null && !show.startTime.equals(startTime))
        continue;
    
    //if duration>0 and does not match with show time, skip
    //otherwise, either duration <=0, or 2 durations are equal
    if (duration>0 && show.duration!=duration)
        continue;
    
    //only then we reach here with no skipping and add it into the result set
    result.add(show);
    }
return result;
} //end method

/**
 * Add a booking into the mega store.
 * The adding will fail if this booking contains seats which are already booked on the same show.
 * @param booking	The booking to add.
 * @return		<code>true</code> if the addition is successful. <code>false</code> otherwise.
 */
public boolean addBooking(Booking booking)
{
if (booking.getCustomer()==null)	//if no customer name specified
    return false;			//fail

if (booking.getCustomer().length()==0)	//if empty customer name
    return false;			//fail

if (booking.getShow()==null)		//if no show specified
    return false;			//fail

if (booking.getSeats()==null)		//if no seat of seat specified
    return false;			//fail

if (booking.getSeats().size()==0)	//if 0 seat to book
	return false;			//fail

//check if this booking contains seats which are already booked
Set<Booking> bookingsOnShow=this.findBookings(null,true,booking.getShow());	//find all bookings on this show

for (Booking b: bookingsOnShow) //go through all bookings on the same show
    {
    Set<Seat> intersection=new HashSet<Seat>(b.seats);	//create a copy of seats
    intersection.retainAll(booking.seats);		//keep those seats in the new booking to add
    if (intersection.size()>0)				//if the set is not empty, there is an overlap in seats with the current booking
        return false;					//fail
    }

/*
Booking[] similarBookings=temp.toArray(new Booking[0]);		//convert it to an array for easier manipulation
for (Booking similarBooking:similarBookings)
    {
    if (similarBooking.show.equals(booking.show))		//same show
        {
        Set<Seat> intersection=new HashSet<Seat>(similarBooking.seats);	//create a copy of seats
        intersection.retainAll(booking.seats);				//keep those seats in the booking
        if (intersection.size()>0)					//some seats are already booked
            return false;						//fail
        }
    }
*/
this.bookingStore.add(booking);	//if everything is fine, add it into booking store set
return true;			//and done
} //end method

/**
 * Find all bookings that fit a criteria.
 * @param nameKeyword	Keyword in customer name.
 * 			If this is <code>null</code> or the empty string then it fits all names.
 * @param partialMatch  If this is <code>true</code>, keyword will be used for a partial match on the customer's name.
 *                      If <code>false</code>, it will be an exact match.
 * @param show		The show we are looking for.
 * 			If this is <code>null</code> then it fits all shows.
 * @return		All bookings fitting the criteria as a <code>Set</code> of <code>Booking</code> object.
 */
public Set<Booking> findBookings(String nameKeyword,boolean partialMatch, Show show)
{
Set<Booking> result=new HashSet<Booking>();

Iterator<Booking> i=this.bookingStore.iterator();
while (i.hasNext())
    {
    Booking booking=(Booking)i.next();

    //keyword is specified, exact match, and names are not equal, skip
    if (nameKeyword!=null && !partialMatch && !nameKeyword.equals(booking.getCustomer()))
        continue;
    
    //keyword is specified, partial match, and sub-string not found, skip
    if (nameKeyword!=null && partialMatch && booking.getCustomer().toLowerCase().indexOf(nameKeyword.toLowerCase())<0)
        continue;

    //if show is specified, and not a match, skip
    if (show!=null && !show.equals(booking.getShow()))
        continue;

    result.add(booking);    //if we reach here, it satisfies the criteria
    }
return result;
} //end method

/**
 * Delete a booking from the mega store.
 * @param booking   The booking to delete.
 * @return <code>true</code> if deletion is successful.
 */
public boolean deleteBooking(Booking booking)
{
return this.bookingStore.remove(booking);
} //end method
} //end class
