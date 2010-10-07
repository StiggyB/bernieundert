package a02;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class SudokoFrame {

	JFrame frame = new JFrame();
	JTable[][] tables = new JTable[3][3];

	public void buildFrame() {

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("1337 Sudoko (c) Bernie & Ert");
		// frame.setBounds(0, 0, 800, 600);
		frame.setLayout(new GridLayout(3, 3));

		// frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		final SudokuLogik logik = new SudokuLogik(tables);

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				tables[i][j] = buildTable();
				frame.add(tables[i][j]);
			}
		}
		// frame.add(buildTable());

		logik.fillTables();

		frame.pack();
	}

	private JTable buildTable() {
		JTable table = new JTable(3, 3);
		// table.setRowHeight(100);
		// table.setRowMargin(1);
		// table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setSize(1, 20);
		table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		table.setVisible(true);
		// DefaultTableModel model = new DefaultTableModel(3, 3);
		// table.setModel(model);

		return table;
	}
}
