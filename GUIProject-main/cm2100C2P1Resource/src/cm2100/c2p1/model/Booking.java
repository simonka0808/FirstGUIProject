package cm2100.c2p1.model;

import java.util.*;

/**
 * This class models the booking of a customer.
 * @author kit
 *
 */
public class Booking 
{
/**
 * The name of the customer.
 */
protected String customer;
/**
 * The show booked in this booking.
 */
protected Show show;
/**
 * The seats booked in as a <code>Set</code> of <code>Seat</code> object.
 */
protected Set<Seat> seats;

/**
 * Create a booking.
 * Initially there is no seat.
 * Seats have to be added by subsequent calls to the {@link #addSeat(Seat) addSeat(Seat)} method.
 * @param customer	The name of the customer making the booking.
 * @param show		The show to booking.
 */
public Booking(String customer,Show show)
{
this.customer=customer;
this.show=show;
this.seats=new HashSet<Seat>();	//initially an empty set of seats
} //end method

/**
 * Get customer of booking.
 * @return The customer who made the booking.
 */
public String getCustomer() {
    return customer;
}

/**
 * Set customer of booking.
 * @param customer The name of the customer who made the booking.
 */
public void setCustomer(String customer) {
    this.customer = customer;
}

/**
 * Get the show of the booking.
 * @return The show.
 */
public Show getShow() {
    return show;
}

/**
 * Set the show of the booking.
 * @param show The show the booking is on.
 */
public void setShow(Show show) {
    this.show = show;
}

/**
 * Get all the seats reserved by the booking.
 * @return All seats booked as a set.
 */
public Set<Seat> getSeats() {
    return seats;
}

/**
 * Return the booking as a String.
 * Note that we only include the customer name and show details.
 * There is no seat included in the String as we expect to show the seats in a GUI.
 */
public String toString()
{
return this.customer+", "+show.toString();
} //end method

/**
 * Add a seat into a booking.
 * @param seat	The seat to add to this booking.
 * @return		<code>true</code> if the addition is successful. <code>false</code> otherwise.
 */
public boolean addSeat(Seat seat)
{
this.seats.add(seat);	//otherwise add it into booking
return true;
} //end method

/**
 * Compare a booking with another object.
 * @return	<code>true</code> if the other object is a {@link Booking} and has the same attribute values.
 */
public boolean equals(Object obj)
{
if (!(obj instanceof Booking))
	return false;
Booking booking=(Booking)obj;
return this.customer.equals(booking.customer) &&
		this.show.equals(booking.show) &&
		this.seats.equals(booking.seats);
} //end method

public int hashCode()
{
return this.customer.hashCode()+this.show.hashCode()+this.seats.hashCode();
} //end method
} //end class
