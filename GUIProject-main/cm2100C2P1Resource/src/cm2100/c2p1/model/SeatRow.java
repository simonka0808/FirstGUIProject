package cm2100.c2p1.model;

/**
 * This class models a row in a cinema screen.
 * @author Kit-ying Hui
 *
 */
public class SeatRow extends Row
{
/**
 * The row letter of the seat row.
 */
protected char letter;
/**
 * The cells in a row stored as an array.
 * A {@link Cell} can be a {@link Seat} or {@link AisleSpace}.
 */
protected Cell[] cells;

/**
 * Create a row of seats with no aisle space.
 * @param letter	The row letter.
 * @param numOfSeats	The number of seats/cells required.
 * 						Seats will be numbered from 1 to numOfseats (not from 0).
 * 						As there is no aisle space in this row, the number of cells is the same as the number of seats.
 */
public SeatRow(char letter,int numOfSeats)
{
this.letter=letter;
this.cells=new Cell[numOfSeats];			//create an array of correct size
for (int i=0;i<numOfSeats;i++)						//creat seats of this row
	{
	this.cells[i]=new Seat(this,i+1);	//seat number starts from 1, not 0
	}
} //end method

/**
 * Create a row of seats with aisle space.
 * @param letter	The row letter.
 * @param numOfCells	The number of cells to create.
 * @param aislePositions	An <code>int</code> array specifying the aisle spaces cell positions.
 * 							Each <code>int</code> specifies the cell position number which contains an aisle space.
 * 							All other cells are seats.
 */
public SeatRow(char letter,int numOfCells,int[] aislePositions)
{
this.letter=letter;
this.cells=new Cell[numOfCells];

for (int i=0;i<numOfCells;i++)
	this.cells[i]=null;

for (int i=0;i<aislePositions.length;i++)
	this.cells[aislePositions[i]]=new AisleSpace(this);

int seatNumber=1;
for (int i=0;i<numOfCells;i++)
	if (this.cells[i]==null)
		{
		this.cells[i]=new Seat(this,seatNumber++);
		}
} //end method

/**
 * Get the letter of a seat row.
 * @return	The row letter of the row.
 */
public char getLetter()
{
return this.letter;
} //end method

/**
 * Find the number of seats in the row. Aisle spaces are excluded.
 * @return	The number of seats in the row.
 */
public int getNumberOfSeats()
{
int seatCount=0;

for (int i=0;i<this.cells.length;i++)
	{
	if (this.cells[i] instanceof Seat)
		seatCount++;
	}
return seatCount;
} //end method

/**
 * Find the number of cells in the row.
 * This should be the total number of seats plus aisle spaces.
 * This is need when we compose a GUI to show the seats and need to know how wide the screen is.
 * @return	The number of cells in the row.
 */
public int getNumberOfCells()
{
return this.cells.length;
} //end method

/**
 * Find a specific cell in a row by it cell position number.
 * It can be a seat or an aisle space.
 * @param number	The cell position number look for.
 * @return			The {@link Cell} if found. Or <code>null</code> if the number is outside the range of cells in the row.
 */
public Cell getCell(int number)
{
if (number>=this.cells.length)
	return null;
return this.cells[number];
} //end method

/**
 * Find a specific seat in a row by its seat number.
 * It must be a {@link Seat}.
 * We are looking for a seat based on the seat number, not its cell position number.
 * Remember that seat number is like the label on the seat.
 * It is not the same as the cell position number.
 * @param number	The seat number we are looking for.
 * @return			The {@link Seat} in the row with the given seat number. If no seat is found with the number, this method returns a <code>null</code>.
 */
public Seat getSeat(int number)
{
if (number>=this.cells.length)			//null if number is beyond width of row
	return null;

for (int i=0;i<this.cells.length;i++)
	{
	Cell cell=this.cells[i];
	if (cell instanceof Seat)
		{
		Seat seat=(Seat)cell;
		if (seat.number==number)
			return seat;
		}
	}
return null;
} //end method

/**
 * Convert a row into a String.
 * @return A {@link String} representation of the row.
 */
public String toString()
{
String result="";

for (int i=0;i<this.cells.length;i++)
	result+=this.cells[i].toString()+" ";
return result;
} //end method
} //end class
