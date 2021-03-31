package cm2100.c2p1.model;

/**
 * This class models a seat in a seat row.
 * @author Kit-ying Hui
 *
 */
public class Seat extends Cell
{
/**
 * The seat number.
 * It starts from 1 in a seat row.
 */
protected int number;

/**
 * Create a seat in a seat row with a specific seat number.
 * @param row
 * @param number
 */
public Seat(SeatRow row,int number)
{
super(row);
this.number=number;
} //end method

/**
 * Return the seat as a String.
 * It is simply the row letter followed by the seat number.
 */
public String toString()
{
return ""+this.row.getLetter()+this.number;
} //end method
} //end class
