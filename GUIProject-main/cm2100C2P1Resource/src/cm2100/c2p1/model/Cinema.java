package cm2100.c2p1.model;

import java.util.*;

/**
 * This class models a cinema with a single screen.
 * A cinema has a name and a mega store which contains its screen, shows, and bookings.
 * This is the main class/object for you to access shows and bookings.
 * 
 * @author Kit-ying Hui
 *
 */
public class Cinema
{
/**
 * The cinema name.
 */
protected String name;

/**
 * This is a <code>MegaStore</code> object that stores everything in the cinema.
 */
protected MegaStore store;

/**
 * Create a cinema with a given name.
 * @param name	The name of the cinema to create.
 */
public Cinema(String name)
{
this.name=name;
this.store=new MegaStore();
} //end method

/**
 * Get the name of the cinema.
 * @return	The cinema's name.
 */
public String getName()
{
return this.name;
} //end method

/**
 * Set the one and only one screen in the cinema.
 * @param screen	The screen to set.
 */
public void setScreen(Screen screen)
{
this.store.setScreen(screen);
} //end method

/**
 * Get the screen in the cinema.
 * @return	The screen in the cinema.
 */
public Screen getScreen()
{
return this.store.getScreen();
} //end method

/**
 * Add a show to the cinema.
 * @param show	The show to add.
 * @return	<code>true</code> if the addition is successful.
 */
public boolean addShow(Show show)
{
return this.store.addShow(show);
} //end method

public void deleteShow(Show show)
{
this.store.deleteShow(show);
} //end metho

/**
 * Get all shows in the cinema.
 * @return	All shows in the cinema as a <code>Set</code> of <code>Show</code> object.
 */
public Set<Show> allShows()
{
return this.store.allShows();	
} //end method

/**
 * Find all shows in the cinema with a keyword in their movie titles.
 * @param keyword	The keyword to look for.
 * @return		All shows with keyword in their titles as a <code>Set</code> of <code>Show</code> object.
 */
public Set<Show> findShows(String keyword,boolean partialMatch,Time startTime,int duration)
{
return this.store.findShows(keyword,partialMatch,startTime,duration);
} //end method

/**
 * Add a booking into the cinema.
 * @param booking	The booking to add.
 * @return		<code>true</code> if the addition is successful. <code>false</code> otherwise.
 */
public boolean addBooking(Booking booking)
{
return this.store.addBooking(booking);
} //end method



/**
 * Find all bookings that fit the search criteria.
 * @param nameKeyword	Keyword in customer name.
 * 			If this is <code>null</code> then it is ignored.
 * @param partialMatch  If this is <code>true</code>, keyword will be used for a partial match on the customer's name.
 *                      If <code>false</code>, it will be an exact match.
 * @param show		The show we are looking for.
 * 			If this is <code>null</code> then it is ignored.
 * @return		All bookings fitting the criteria as a <code>Set</code> of <code>Booking</code> object.
 */
public Set<Booking> findBookings(String nameKeyword,boolean partialMatch,Show show)
{
return this.store.findBookings(nameKeyword,partialMatch,show);
} //end method

/**
 * Delete a booking from the cinema.
 * @param booking	The booking to delete.
 * @return
 */
public boolean deleteBooking(Booking booking)
{
return this.store.deleteBooking(booking);
} //end method
} //end class
