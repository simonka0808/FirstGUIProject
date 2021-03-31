package cm2100.c2p1.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import cm2100.c2p1.model.Row;
import cm2100.c2p1.model.Screen;
import cm2100.c2p1.model.SeatRow;
import cm2100.c2p1.model.Show;
import cm2100.c2p1.model.Time;
import cm2100.c2p1.test.TestScreenPanel;

@SuppressWarnings("serial")
public class MainPanel extends JPanel {
	
	Map<String, BookSeats> bookingMap = new HashMap<>();
	List<JButton> allSeats = new ArrayList<JButton>();
	
	List<String> fileInfo = new ArrayList<String>();

	HashSet<Show> allShows = new HashSet<Show>();
	private JPanel leftJPanel = new JPanel();

	private JPanel rightJPanel = new JPanel();

	private JPanel rightJPanelTop = new JPanel();
	private JPanel rightJPanelTopButtons = new JPanel();
	private JPanel rightPanelBottom = new JPanel();
	private JPanel rightPanelBottomBtns = new JPanel();

	// right top panel components
	private JLabel customerLabel = new JLabel("Customer: ");
	private JLabel showLabel = new JLabel("Show: ");
	private JLabel searchLabel = new JLabel("Search Result:");
	private JTextField customerText = new JTextField();
	private JComboBox<Show> listOfShows = new JComboBox<>();
	private JComboBox<Object> searchResult = new JComboBox<>();
	private JButton book = new JButton("Book");
	private JButton delete = new JButton("Delete");
	private JButton reset = new JButton("Reset");
	private JButton search = new JButton("Search");

	// right bottom panel components

	private JLabel exsShows = new JLabel("Existing Shows: ");
	private JLabel title = new JLabel("Title: ");
	private JLabel startTime = new JLabel("Start Time: ");
	private JLabel duration = new JLabel("Duration (in min): ");
	private JComboBox<Show> listOfExsShows = new JComboBox<>();
	private JTextField titleText = new JTextField();
	private JTextField durationText = new JTextField();
	private JComboBox<Object> hours = new JComboBox<>();
	private JComboBox<Object> mins = new JComboBox<>();

	JButton button = new JButton();

	public MainPanel(Screen screen) {
		this.setName("Cinema Booking Applciation");
		this.setLayout(new GridLayout(1, 2));

		setScreen(screen);
		setRightTopPanel();
		setRigthBottomPanel();
		addShowsToScreen();

		leftJPanel.setLayout(new GridLayout(0, 10));
		this.add(leftJPanel, LEFT_ALIGNMENT);
		this.add(rightJPanel, RIGHT_ALIGNMENT);

		this.leftJPanel.setBackground(Color.white);
		this.rightJPanel.setBackground(Color.white);

		rightJPanel.add(rightJPanelTop, TOP_ALIGNMENT);
		rightJPanel.add(rightPanelBottom, BOTTOM_ALIGNMENT);

		rightJPanel.setLayout(new GridLayout(2, 1));
		rightJPanelTop.setLayout(new GridLayout(0, 1));

		rightJPanelTop.add(rightJPanelTopButtons, CENTER_ALIGNMENT);
		rightJPanelTopButtons.setLayout(new FlowLayout(FlowLayout.CENTER));

		rightPanelBottom.setLayout(new GridLayout(0, 1));
		rightPanelBottom.add(rightPanelBottomBtns, CENTER_ALIGNMENT);
		rightPanelBottomBtns.setLayout(new FlowLayout(FlowLayout.CENTER));

		rightJPanelTop.setBorder(BorderFactory.createTitledBorder("Booking Managment"));
		rightPanelBottom.setBorder(BorderFactory.createTitledBorder("Show Managment"));

		rightPanelBottom.add(rightPanelBottomBtns);

	}

	private void setRightTopPanel() {

		rightJPanelTop.add(customerLabel);
		rightJPanelTop.add(customerText);
		rightJPanelTop.add(showLabel);
		rightJPanelTop.add(listOfShows);
		rightJPanelTop.add(searchLabel);
		rightJPanelTop.add(searchResult);

		customerLabel.setPreferredSize(new Dimension(5, 5));
		rightJPanelTopButtons.add(book);
		rightJPanelTopButtons.add(delete);
		rightJPanelTopButtons.add(reset);
		rightJPanelTopButtons.add(search);

		book.addActionListener(new bookButtonListener());
		delete.addActionListener(new DeleteButtonListener());
		reset.addActionListener(new resetButtonListener());
		search.addActionListener(new searchButtonListener());

	}

	private void setRigthBottomPanel() {

		hours.setPreferredSize(new Dimension(40, 20));

		for (int i = 0; i <= 23; i++) {
			if (i >= 0 && i <= 9) {
				hours.addItem("0" + new Integer(i));
			} else {
				hours.addItem(new Integer(i));

			}
		}
		for (int i = 0; i <= 59; i++) {
			if (i >= 0 && i <= 9) {
				mins.addItem("0" + new Integer(i));
			} else {
				mins.addItem(new Integer(i));
			}
		}

		rightPanelBottom.add(exsShows);
		rightPanelBottom.add(listOfExsShows);
		rightPanelBottom.add(title);
		rightPanelBottom.add(titleText);
		rightPanelBottom.add(startTime);
		rightPanelBottom.add(hours);
		rightPanelBottom.add(mins);
		rightPanelBottom.add(duration);
		rightPanelBottom.add(durationText);

		JButton save = new JButton("Save");
		JButton edit = new JButton("Edit");
		JButton clear = new JButton("Clear");
		JButton delete = new JButton("Delete");
		rightPanelBottomBtns.add(save);
		rightPanelBottomBtns.add(edit);
		rightPanelBottomBtns.add(clear);
		rightPanelBottomBtns.add(delete);

		save.addActionListener(new saveButtonListener());
		save.addActionListener(new saveButtonListener1());
		clear.addActionListener(new clearButtonListener());
		edit.addActionListener(new editButtonListener());
		delete.addActionListener(new deleteShowManagment());

	}
	
	private String testData() {
		return "S,Tenet,19,30,150\r\n" + 
				"S,No Time to Die,18,0,163\r\n" + 
				"S,No Time to Die,21,0,163\r\n" + 
				"S,A Christmas Carol,17,15,90\r\n" + 
				"S,Skyfall,20,0,162\r\n" + 
				"S,The Hobbit,19,3,180\r\n" + 
				"B,Adam,Skyfall,20,0,G4,G5,G8,G3,G7,G6,G1,G2\r\n" + 
				"B,Betty,No Time to Die,18,0,D5,D6\r\n" + 
				"B,Chris,A Christmas Carol,17,15,H7,H8\r\n" + 
				"B,David,No Time to Die,21,0,F4,F3,F5,F6\r\n" + 
				"B,Edith,No Time to Die,21,0,A3,A4,A5\r\n" + 
				"B,Felix,Tenet,19,30,C3,C4";
	}

	private void addShowsToScreen() {
		String customerName = "";
		
		TestScreenPanel.sb.append(testData());
		
		String[] splitedDataByLine = TestScreenPanel.sb.toString().split("\r\n");
		for (String string : splitedDataByLine) {
			String[] lineData = string.split(",");
			fileInfo.add(string);
			Show show = null;
			if(lineData[0].equals("S")) {
				show = new Show(lineData[1], new Time(Integer.parseInt(lineData[2]), Integer.parseInt(lineData[3])), Integer.parseInt(lineData[4]));
				allShows.add(show);
				listOfShows.addItem(show);
				listOfExsShows.addItem(show);

			}else if(lineData[0].equals("B")) {
				for (Show string2 : allShows) {
					if(string2.getName().equals(lineData[2])) {
						show = string2;
						break;
					}
				}
				BookSeats bs = null;
				for (int i = 1; i < lineData.length; i++) {
					if(i == 1 ) {
						customerName = lineData[1];
					}else if(i == 2) {
						bs = new BookSeats(show);
					}else if(i > 4) {
						bs.addSeats(lineData[i]);
					}
				}
				bookingMap.put(customerName, bs);
				searchResult.addItem(customerName + "," + show.toString());
			}
		}		
	}

	private class seatListener implements ActionListener {
		private JButton currentButton;

		public seatListener(JButton b) {
			currentButton = b;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (currentButton.getBackground().equals(Color.white)) {
				currentButton.setBackground(new JButton().getBackground());
				currentButton.setForeground(Color.black);
			} else {
				currentButton.setForeground(Color.RED);
				currentButton.setBackground(Color.white);// Simona A1,A2,A3, Petar B1,B2,B3
			}

		}
	}
	
	private void colorButtons(List<String> customerButtons) {
		for (JButton customerButton : allSeats) {
			for (String buttonTest : customerButtons) {
				if(customerButton.getText().equals(buttonTest)) {
					customerButton.setForeground(Color.RED);
					customerButton.setBackground(Color.white);
				}
			}
		}
	}

	private class bookButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			
			List<String> bookedSeatsCustomer = new ArrayList<String>();
			StringBuilder sb = new StringBuilder();
			for (JButton jButton : allSeats) {
				List<String> bookedSeats = null;
				if (bookingMap.size() != 0) {
					for (Entry<String, BookSeats> value : bookingMap.entrySet()) {
						bookedSeats = value.getValue().getSeats();
						if (bookedSeats != null && bookedSeats.contains(jButton.getText())) {
							JOptionPane.showMessageDialog(rightJPanelTop,
									"These seats are already booked!");
							return;
						}
					}
				}
				if (!customerText.getText().trim().isEmpty() && jButton.getBackground().equals(Color.white)) {
					bookedSeatsCustomer.add(jButton.getText());
				} else if (customerText.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(rightJPanelTop, "Invalid1");
					return;
				}
			}
			if (bookedSeatsCustomer.isEmpty()) {
				JOptionPane.showMessageDialog(rightJPanelTop, "Invalid2");
				return;
			}

			sb.append(customerText.getText().trim() + ",");
			sb.append(listOfShows.getSelectedItem().toString());

			Show show = (Show) listOfShows.getSelectedItem();
			BookSeats bs = new BookSeats(show, bookedSeatsCustomer);
			bookingMap.put(customerText.getText().trim(), bs);
			searchResult.addItem(sb.toString());
			JOptionPane.showMessageDialog(rightJPanelTop, "Booking added");
			
			String booking = 
					"B,"+customerText.getText().trim()+","+show.getName()+","+show.getStartTime().getHour()+","+show.getStartTime().getMinute()+","+bs.getSeats();
			booking = booking.replace("[", "").replace("]", "");
			booking = booking.replace(" ", "");
			fileInfo.add(booking);
			changeFile();
			

			if (bookedSeatsCustomer.isEmpty()) {
				JOptionPane.showMessageDialog(rightJPanelTop, "Invalid2");
			}
			resetAllButtons();

		}
	}
	
	private void changeFile() {
		TestScreenPanel.sb = new StringBuilder();
		for (String string : fileInfo) {
			TestScreenPanel.sb.append(string);
			TestScreenPanel.sb.append(System.lineSeparator());
		}
	}
	private class DeleteButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String[] elements;
			for (int i = 0; i < searchResult.getItemCount(); i++) {
				elements = searchResult.getItemAt(i).toString().split(",");
				System.out.println(elements[0]);
				System.out.println(elements[1]);
				if (customerText.getText().trim().equals(elements[0])) {
					BookSeats bs = bookingMap.get(elements[0]);
					String booking = 
							"B,"+customerText.getText().trim()+","+bs.getShow().getName()+","+bs.getShow().getStartTime().getHour()+","+bs.getShow().getStartTime().getMinute()+","+bs.getSeats();
					booking = booking.replace("[", "").replace("]", "");
					booking = booking.replace(" ", "");
					fileInfo.remove(booking);
					changeFile();
					
					bookingMap.remove(elements[0]);
					searchResult.removeItemAt(i);
				}
			} 

		}
	}

	private class searchButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String name = customerText.getText();
			resetAllButtons();
			String[] elements = null;
			for (int i = 0; i < searchResult.getItemCount(); i++) {
				elements = searchResult.getItemAt(i).toString().split(",");
				if (name.trim().equals(elements[0])) {
					customerText.setText(name);
					searchResult.setSelectedItem(searchResult.getItemAt(i));
					break;
				}
			}

			if (elements != null) {
				for (int i = 0; i < listOfExsShows.getItemCount(); i++) {
					if (listOfExsShows.getItemAt(i).toString().equals(elements[1])) {
						listOfShows.setSelectedItem(listOfExsShows.getItemAt(i));
						break;
					}
				}
			}
			List<String> customerSeats = bookingMap.get(elements[0]).getSeats(); 
			colorButtons(customerSeats);
			// акхгн, мн лмнцн йнлокейямн

//			List<String> bookedSeats = null;

//			if (!bookingMap.isEmpty()) {
//				System.out.println(name);
//				for (Entry<String, BookSeats> value : bookingMap.entrySet()) {
////					bookedSeats = value.getValue().getSeats();
//					if (customerText.getText().equals(value.getKey())) {
//						listOfShows.setSelectedItem(value.getValue().getShow());
//						for (int i = 0; i < searchResult.getItemCount(); i++) {
//							if (value.getKey().equals(searchResult.getItemAt(i))) {
//								searchResult.setSelectedItem(searchResult.getItemAt(i));
//								System.out.println(searchResult.getItemAt(i));
//								}
//							}
//						}
//
//					}
//				}
		}

	}

	private class resetButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			resetAllButtons();
		}
	}

	private void resetAllButtons() {
		for (int i = 0; i < allSeats.size(); i++) {
			boolean isPressedButton = allSeats.get(i).getForeground().equals(Color.RED);

			if (isPressedButton || listOfShows.getSelectedIndex() > -1) {
				allSeats.get(i).setBackground(getBackground());
				allSeats.get(i).setForeground(Color.black);
				customerText.setText("");
			}

		}

	}

	private int ConvertIntoNumeric(String num) {
		try {
			return Integer.parseInt(num);
		} catch (Exception ex) {
			return 0;
		}
	}

	private class saveButtonListener implements ActionListener {

		Integer durationInt = ConvertIntoNumeric(durationText.getText());

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			String title = titleText.getText().trim();
			Integer h = Integer.parseInt((String) hours.getSelectedItem());

			Integer m = Integer.parseInt((String) mins.getSelectedItem());
			Integer durationInt = ConvertIntoNumeric(durationText.getText());
			Show sh = new Show(title, null, m);

			for (int i = 0; i < listOfExsShows.getItemCount(); i++) {
				if (!title.isEmpty() && h >= 0 && m >= 0 && durationInt > 0) {
					sh = new Show(title, new Time(h, m), durationInt);
					sh.setDuration(durationInt);
					listOfExsShows.addItem(sh);
					listOfShows.addItem(sh);
					String show = "S,"+sh.getName()+","+sh.getStartTime().getHour()+","+sh.getStartTime().getMinute()+","+sh.getDuration();
					fileInfo.add(show);
					changeFile();
					JOptionPane.showMessageDialog(rightPanelBottom, "Show added");
					return;

				} else if (titleText.getText().equals(listOfExsShows.getItemAt(i).getName())) {
					JOptionPane.showMessageDialog(rightPanelBottom, "The show is already added!");
				}
			}
		}
	}

	private class clearButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			titleText.setText("");
			hours.setSelectedIndex(0);
			mins.setSelectedIndex(0);
			durationText.setText("");
			resetAllButtons();

		}

	}

	private class editButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String show = String.valueOf(listOfExsShows.getSelectedItem());
			for (int i = 0; i < listOfExsShows.getItemCount(); i++) {
				if (show.equals(listOfExsShows.getItemAt(i).toString())) {
					String show1 = "S,"+listOfExsShows.getItemAt(i).getName()+","+listOfExsShows.getItemAt(i).getStartTime().getHour()+","+listOfExsShows.getItemAt(i).getStartTime().getMinute()+listOfExsShows.getItemAt(i).getDuration();
					fileInfo.remove(show1);
					titleText.setText(listOfExsShows.getItemAt(i).getName());
					hours.setSelectedIndex(listOfExsShows.getItemAt(i).getStartTime().getHour());
					mins.setSelectedIndex(listOfExsShows.getItemAt(i).getStartTime().getMinute());
					durationText.setText(String.valueOf(listOfExsShows.getItemAt(i).getDuration()));
					show1 = "S,"+listOfExsShows.getItemAt(i).getName()+","+listOfExsShows.getItemAt(i).getStartTime().getHour()+","+listOfExsShows.getItemAt(i).getStartTime().getMinute()+listOfExsShows.getItemAt(i).getDuration();
					fileInfo.add(show1);
					changeFile();
				}
			}

		}

	}

	private class deleteShowManagment implements ActionListener {

		String[] options = { "No", "Yes", "Cancel", };

		@Override

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int popUp = JOptionPane.showConfirmDialog(rightPanelBottom,
					"Are you sure you want to delete: " + listOfExsShows.getSelectedItem(), "Select an option",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null);
			for (int i = 0; i < listOfExsShows.getItemCount(); i++) {
				for (int j = 0; j < searchResult.getItemCount(); j++) {
					if (popUp == JOptionPane.YES_OPTION) {
						listOfExsShows.removeItemAt(listOfExsShows.getSelectedIndex());
						return;

					}
				}
			}
		}

	}

	private class saveButtonListener1 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String title = titleText.getText();
			int hour = hours.getSelectedIndex();
			int mins = hours.getSelectedIndex();
			int duration = Integer.parseInt(durationText.getText());

			Show updatedShow = new Show(title, new Time(hour, mins), duration);
			for (int i = 0; i < listOfExsShows.getItemCount(); i++) {
				for (int j = 0; j < listOfShows.getItemCount();) {
					if (!listOfExsShows.getSelectedItem().equals(updatedShow)) {
						listOfExsShows.removeItemAt(listOfExsShows.getSelectedIndex());
						listOfExsShows.addItem(updatedShow);
						resetValues();

						if (!updatedShow.equals(listOfShows.getItemAt(j))) {
							listOfShows.removeItemAt(listOfExsShows.getSelectedIndex());
							listOfShows.addItem(updatedShow);
						}
						return;

					}
				}

			}

		}
	}

	private void resetValues() {
		titleText.setText("");
		hours.setSelectedIndex(0);
		mins.setSelectedIndex(0);
		durationText.setText("");

	}

	private void setScreen(Screen screen) {
		for (int i = 0; i < screen.allRows().length; i++) {
			Row row = screen.allRows()[i];
			if (row.getClass().getSimpleName().equals("SeatRow")) {
				SeatRow seatRow = (SeatRow) row;
				for (int j = 0; j < seatRow.getNumberOfCells(); j++) {
					if (seatRow.getCell(j).getClass().getSimpleName().equals("Seat")) {
						button = new JButton(seatRow.getCell(j).toString());
						button.addActionListener(new seatListener(button));
						leftJPanel.add(button);
						allSeats.add(button);
					} else {
						JButton space = new JButton(" ");
						space.setVisible(false);
						leftJPanel.add(space);
					}
				}
			} else if (row.getClass().getSimpleName().equals("AisleRow")) {
				for (int j = 0; j < 10; j++) {
					JButton space = new JButton(" ");
					space.setVisible(false);
					leftJPanel.add(space);
				}
			}
		}

	}

}
