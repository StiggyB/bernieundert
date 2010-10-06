package a02;

import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class Main{
   public static void main( String[] args ){
      // Die Daten für das Table
      String[][] data = new String[][]{
            {"1", "2", "3"},
            {"4", "5", "6"},
            {"7", "8", "9"}
      };
      
      // Die Column-Titles
      String[] title = new String[]{
            "A", "B", "C", 
      };
      
      // Das JTable initialisieren
      JTable table = new JTable( data, title );
      
      // Das automatische Neusetzen der Grösse würde das Vorhaben, die Grösse selbst
      // zu setzen, stören.
      table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF );
      
      // Über das TableColumnModel kommt man an die wichtigen Daten
      TableColumnModel columnModel = table.getColumnModel();
           	
      columnModel.getColumn( 0 ).setPreferredWidth( 40 );
      columnModel.getColumn( 1 ).setPreferredWidth( 40 );
      columnModel.getColumn( 2 ).setPreferredWidth( 40 );
      

      TableCellRenderer centerRenderer = new CenterRenderer();
      
      //  Use renderer on a specific column
      TableColumn column = table.getColumnModel().getColumn(1);
      column.setCellRenderer( centerRenderer );

      
           
      JFrame frame = new JFrame( "Demo" );
      frame.getContentPane().add( new JScrollPane( table ) );
      frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      frame.pack();
      frame.setVisible( true );
   }
   
   class CenterRenderer extends DefaultTableCellRenderer
   {
       public CenterRenderer()
       {
           setHorizontalAlignment( CENTER );
       }

       public Component getTableCellRendererComponent(
           JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
       {
           super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
           return this;
       }
   }   
   
}