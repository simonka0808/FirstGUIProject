package cm2100.c2p1.test;

import cm2100.c2p1.model.*;
import cm2100.c2p1.gui.MainPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.sound.sampled.Line;
import javax.swing.*;
import javax.swing.filechooser.*;

/**
 * This test class create a Screen object with a seat layout. You can use this
 * Screen to create your GUI panel showing the seats.
 * 
 * @author K. Hui
 */
public class TestScreenPanel extends JFrame {

	private JMenuBar menuBar = new JMenuBar(); // Window menu bar
	private JMenuItem openItem, saveItem, closeItem;
	private JLabel label = new JLabel();
	
	public static StringBuilder sb = new StringBuilder();
	
	public TestScreenPanel(JPanel screenPanel) {

//		this.setResizable(false);

		this.setSize(1040, 602);
		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());
		c.add(screenPanel);
		c.setBackground(Color.white);
		setJMenuBar(menuBar);
		JMenu fileMenu = new JMenu("File");
		saveItem = fileMenu.add("Save");
		saveItem.addActionListener(new SaveFile());
		openItem = fileMenu.add("Open");
		openItem.addActionListener(new OpenFile());
		closeItem = fileMenu.add("Quit");
		closeItem.addActionListener(new exitGUI());
		menuBar.add(fileMenu);
//		 

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private class OpenFile implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser = new JFileChooser(new File("c:\\"));
			fileChooser.setDialogTitle("Open a File");
			fileChooser.setFileFilter(new FileNameExtensionFilter(".txt", "txt"));
			int option = fileChooser.showOpenDialog(null);
			if (option == JFileChooser.APPROVE_OPTION) {
				File f = fileChooser.getSelectedFile();
				dataRead(f);
			}
		}
	}

	public void dataRead(File file) {
		
		String data = "";
		sb = new StringBuilder();
		try {
			FileReader filerReader = new FileReader(file);
			BufferedReader bfr = new BufferedReader(filerReader);
			while (true) {
				try {
					if (((data = bfr.readLine()) != null)) {
						sb.append(data);
						sb.append(System.lineSeparator());
					}else {
						break;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private class SaveFile implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			JFileChooser fileChooser = new JFileChooser(new File("c:\\"));
			fileChooser.setDialogTitle("Save a File");
			fileChooser.setFileFilter(new FileNameExtensionFilter(".txt", "txt"));
			int option = fileChooser.showSaveDialog(null);
			if (option == JFileChooser.APPROVE_OPTION) {
				//String content = "Hello";
				File file = fileChooser.getSelectedFile();
				label.setText("File Saved as: " + file.getName());
				try {
					FileWriter fw = new FileWriter(file.getPath());
					fw.write(sb.toString());
					fw.flush();
					fw.close();
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage());
				}
			}

		}
	}

	final class exitGUI implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	

	public static void main(String[] arg) {
		
		Screen screen = new Screen("Screen#1");
		char[] rowLetters = { 'A', 'B', 'C', '.', 'D', 'E', 'F', '.', 'G', 'H' };

		int[] aislePositions2 = { 0, 2, 7, 9 };
		Row rowA = new SeatRow(rowLetters[0], 10, aislePositions2);
		screen.addRow(rowA);

		int[] aislePositions = { 2, 7 };
		for (int i = 1; i < rowLetters.length; i++) {
			if (rowLetters[i] == '.') {
				Row row = new AisleRow();
				screen.addRow(row);
			} else {
				Row row = new SeatRow(rowLetters[i], 10, aislePositions);
				screen.addRow(row);
			}
		}

//
// At this point "screen" has the seats layout as specified in the coursework PDF.
// You can use "screen" to build a customised JPanel which shows your screen GUI.
// *** Replace "new JPanel()" below with a customised panel of your own to test it.
// Very likely the constructor of you panel will need to take "screen" as a parameter.
//
		JPanel screenPanel = new MainPanel(screen); // *** <-- Replace "new JPanel()" here.
		new TestScreenPanel(screenPanel).setVisible(true);
	} // end method
} // end class
