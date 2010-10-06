package a02;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;

public class SudokoFrame implements TableModel{

	JFrame frame = new JFrame();
	JTable table = new JTable(3,3);
	
	
public void buildFrame(){
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("1337 Sudoko (c) Bernie & Ert");
		frame.setBounds(0,0,800,600);

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.add(this.buildTable());	
		frame.add(this.table);
		
}

public JTable buildTable() {
		
		this.table.setRowHeight(100);
		this.table.setRowMargin(1);
		this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.table.setSize(200,200);
		this.table.setVisible(true);
		this.table.
		
		return this.table;
		
}



@Override
public void addTableModelListener(TableModelListener arg0) {
	// TODO Auto-generated method stub
	
}


@Override
public Class<?> getColumnClass(int arg0) {
	// TODO Auto-generated method stub
	return null;
}


@Override
public int getColumnCount() {
	// TODO Auto-generated method stub
	return 0;
}


@Override
public String getColumnName(int arg0) {
	// TODO Auto-generated method stub
	return null;
}


@Override
public int getRowCount() {
	// TODO Auto-generated method stub
	return 0;
}


@Override
public Object getValueAt(int arg0, int arg1) {
	// TODO Auto-generated method stub
	return null;
}


@Override
public boolean isCellEditable(int arg0, int arg1) {
	// TODO Auto-generated method stub
	return false;
}


@Override
public void removeTableModelListener(TableModelListener arg0) {
	// TODO Auto-generated method stub
	
}


@Override
public void setValueAt(Object arg0, int arg1, int arg2) {
	// TODO Auto-generated method stub
	
}	
    	
		
		
		
		
		
		
		
		
		
		
		
	
}	
	
	
	

