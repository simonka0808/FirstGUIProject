package cm2100.c2p1.model;

/**
 * This class models an aisle space in a seat row.
 * @author Kit-ying Hui
 *
 */
public class AisleSpace extends Cell
{
/**
 * Create an aisle space the belongs to a row.
 * @param row	The seat row to which the aisle space belongs.
 */
public AisleSpace(SeatRow row)
{
super(row);
} //end method

/**
 * Return an aisle space as a String.
 */
public String toString()
{
return "..";
} //end method
} //end class
