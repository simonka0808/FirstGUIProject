package cm2100.c2p1.gui;

import java.util.ArrayList;
import java.util.List;

import cm2100.c2p1.model.Show;

public class BookSeats {
	
	private Show show;
	private List<String> seats;
	
	public BookSeats(Show show, List<String> seats) {
		this.show = show;
		this.seats = seats;
	}
	public BookSeats(Show show) {
		this.show = show;
		this.seats = new ArrayList<String>();
	}
	
	public void addSeats(String s) {
		this.seats.add(s); 
	}

	public Show getShow() {
		return show;
	}

	public List<String> getSeats() {
		return seats;
	}
}
