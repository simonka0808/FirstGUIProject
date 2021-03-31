package cm2100.c2p1.model;

/**
 * A cell models a position in a seat row.
 * A cell can be a seat or an aisle space.
 * @author Kit-ying Hui
 *
 */
public abstract class Cell
{
/**
 * The seat row to which the cell belongs.
 */
protected SeatRow row;

/**
 * Create a cell that belongs to a seat row.
 * Note that this constructor will never been invoked directly as {@link Cell} is an abstract class.
 * Instead, this method is expected to be called by constructors of concrete subclasses to fill in the {@link #row row} attribute.
 * @param row
 */
public Cell(SeatRow row)
{
this.row=row;
} //end method

/**
 * Get the seat row to which the cell belongs to.
 * @return	The seat row that holds the cell.
 */
public SeatRow getRow()
{
return this.row;
} //end method
} //end class
