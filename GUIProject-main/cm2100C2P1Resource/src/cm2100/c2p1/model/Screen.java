package cm2100.c2p1.model;

import java.util.*;

/**
 * This class models a cinema screen.
 * A cinema screen consists of multiple rows.
 * Each row can be an aisle row or seat row.
 * @author Kit-ying Hui
 *
 */
public class Screen
{
/**
 * The name of the screen. e.g. "Screen#1", "Premier screen".
 */
private String name;

/**
 * The list of rows in the screen.
 */
private List<Row> rows;

/**
 * Create a screen with a given name.
 * In a multi-screen cinema, each screen has a name. e.g. screen#1, screen#2, etc.
 * Although we have only 1 screen in our cinema, we still give the screen a name.
 * @param name	The name of the screen.
 */
public Screen(String name)
{
this.name=name;
this.rows=new LinkedList<Row>();
} //end method

/**
 * Get the screen name.
 * @return	The name of the screen.
 */
public String getName()
{
return this.name;	
} //end method

/**
 * Given a row letter, find the seat row with that row letter.
 * The result must be a seat row as an aisle row has no letter.
 * @param letter	The row letter.
 * @return			The seat row with that row letter.
 * 					If no seat row is found with that letter, this method should return <code>null</code>.
 */
public SeatRow findRow(char letter)
{
Iterator<Row> i=this.rows.iterator();
while (i.hasNext())
	{
	Row row=(Row)i.next();
	if (row instanceof SeatRow)
		{
		SeatRow seatRow=(SeatRow)row;
		if (seatRow.letter==letter)
			return seatRow;
		}
	}
return null;
} //end method

/**
 * Return all rows as an array of {@link Row}.
 * @return All rows as an array, including seat rows and aisle rows.
 */
public Row[] allRows()
{
return this.rows.toArray(new Row[0]);
}

/**
 * Add a row to the end of the screen.
 * @param row	The row to add.
 * @return		<code>true</code> if the addition is ok. <code>false</code> otherwise.
 * 				Note that duplicate row letter is not allowed.
 */
public boolean addRow(Row row)
{
//
// aisle row is always fine as there is no letter
//
if (row instanceof AisleRow)
	{
	this.rows.add(row);
	return true;
	}
//
// for seat row, we need to check for potential clash of letter
//
SeatRow seatRow=(SeatRow)row;
if (this.findRow(seatRow.letter)!=null)	//letter already used
	return false;						//fail
this.rows.add(seatRow);					//otherwise add
return true;							//ok
} //end method

/**
 * Return the number of rows in this screen.
 * @return	The number of rows in the screen, including seat rows and aisle rows.
 */
public int getNumberOfRows()
{
return this.rows.size();
} //end method

/**
 * Get the width of the screen.
 * This should be the maximum number of seats + aisle space across all rows.
 * This is needed when we build the GUI to show the screen.
 * @return	The width of the screen in term of cells.
 */
public int getWidth()
{
int maxWidth=0;
Iterator<Row> i=this.rows.iterator();
while (i.hasNext())
	{
	Row row=(Row)i.next();
	if (row instanceof SeatRow)
		{
		SeatRow seatRow=(SeatRow)row;
		int width=seatRow.getNumberOfCells();
		if (width>maxWidth)
			maxWidth=width;
		}
	}
return maxWidth;
} //end method

/**
 * Get a {@link Cell} given a row letter and a cell position number.
 * Note that an aisle row does not have any row letter.
 * So we must be looking for a cell in a seat row.
 * @param row		The row letter.
 * @param number	The cell position number.
 * @return			The {@link Cell} found at the specified position.
 * 					If no cell is found, this method should return <code>null</code>.
 */
public Cell getCell(char row,int number)
{
SeatRow seatRow=this.findRow(row);			//find the seat row based on the letter
if (seatRow==null)							//if no row is found
	return null;

return seatRow.getCell(number);				//find the cell in that row
} //end method

public Seat getSeat(char row,int number)
{
SeatRow seatRow=this.findRow(row);			//find the seat row based on the letter
if (seatRow==null)							//if no row is found
	return null;

return seatRow.getSeat(number);				//find the seat in that row
} //end method

/**
 * Convert this row into a String.
 * @return	The String representation of the screen.
 */
public String toString()
{
String result="";
Iterator<Row> i=this.rows.iterator();
while (i.hasNext())
	{
	result+=i.next().toString()+"\n";
	}
return result;
} //end method
} //end class
