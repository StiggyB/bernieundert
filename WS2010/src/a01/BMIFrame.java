package a01;

/**
 * 
 * @author Bernie und Ert
 * 
 *         Klasse, die unseren Swing-Frame f�r den BMI Rechner erzeugt.
 *         Die einzige Logikmethode calcBmi() haben wir nicht extra in eine einzelne Klasse
 *         ausgelagert. Man h�tte jedoch alles in die drei Klassen BMIStart, BMICalc und BMIFrame 
 *         teilen k�nnen :)
 * 
 */

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BMIFrame {

	
	// Swing Komponenten
	JFrame frame = new JFrame();
	JTextField fieldWeight = new JTextField();
	JTextField fieldSize = new JTextField();
	JTextField fieldBMI = new JTextField();
	JLabel labelWeight = new JLabel("Gewicht in kg");
	JLabel labelSize = new JLabel("Groe�e in cm");
	JLabel labelTitle = new JLabel("<html><h1>BMI - Body Mass Index Rechner </h1></html>");
	JLabel labelBMI = new JLabel("<html><h1>BMI</h1></html>");
	JLabel labelWeightTopic = new JLabel("<html><h4>Gewicht in kg</h4></html>");
	JLabel labelSizeTopic = new JLabel("<html><h4>Groe�e in cm</h4></html>");
	JSlider sliderWeight = new JSlider(0, 250);
	JSlider sliderSize = new JSlider(0, 220);
	JButton buttonCalc = new JButton();
	
	//DecimalFormat f�r die Ausgabe im Field BMI
	DecimalFormat df = new DecimalFormat("0.00");

	
	//Listener f�r die Slider
	ChangeListener changeListenerWeight = new ChangeListener() {

		public void stateChanged(ChangeEvent e) {
			
			fieldWeight.setText(String.valueOf(sliderWeight.getValue()));
			fieldBMI.setText(df.format(calcBmi()));

		}
	};
	
	ChangeListener changeListenerSize = new ChangeListener() {

		public void stateChanged(ChangeEvent e) {
			
			fieldSize.setText(String.valueOf(sliderSize.getValue()));
			fieldBMI.setText(df.format(calcBmi()));

		}
	};
	
	//Listener f�r Button
	ActionListener listenerButtonCalc = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			sliderWeight.setValue(Integer.parseInt(fieldWeight.getText()));
			sliderSize.setValue(Integer.parseInt(fieldSize.getText()));
			fieldBMI.setText(df.format(calcBmi()));
			
			
		}
	};
	
	//Frame mit allem drum und dran zeichnen
	public void buildFrame(){
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("BMI Rechner - Body Mass Index");
		frame.setLayout(null);
		frame.setBounds(0,0,800,600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.add(sliderWeight);
		frame.add(sliderSize);
		frame.add(fieldSize);
		frame.add(fieldWeight);
		frame.add(labelWeight);
		frame.add(labelSize);
		frame.add(fieldBMI);
		frame.add(labelBMI);
		frame.add(labelTitle);
		frame.add(labelSizeTopic);
		frame.add(labelWeightTopic);
		frame.add(buttonCalc);
		
		sliderWeight.setMinorTickSpacing(5);
		sliderWeight.setMajorTickSpacing(25);
		sliderWeight.setBounds(0, 120, 775, 80);
		sliderWeight.setPaintTicks(true);
		sliderWeight.setPaintLabels(true);
		sliderWeight.addChangeListener(changeListenerWeight);
    	
		sliderSize.setMinorTickSpacing(5);
		sliderSize.setMajorTickSpacing(20);
		sliderSize.setBounds(0, 250, 775, 80);
		sliderSize.setPaintTicks(true);
		sliderSize.setPaintLabels(true);
		sliderSize.addChangeListener(changeListenerSize);
		
		fieldWeight.setBounds(100, 330, 70, 30);
		fieldWeight.setToolTipText("Gewicht in kg eingeben");
		fieldWeight.setText(String.valueOf(sliderWeight.getValue()));
		
		fieldSize.setBounds(300, 330, 70, 30);
		fieldSize.setToolTipText("Groe�e in cm eingeben");
		fieldSize.setText(String.valueOf(sliderSize.getValue()));
		
		fieldBMI.setBounds(320, 450, 160, 80);
		fieldBMI.setFont(new java.awt.Font("Dialog", 0, 36));
		fieldBMI.setEditable(false);
		fieldBMI.setHorizontalAlignment(JTextField.CENTER); 
		
		labelWeight.setBounds(20, 330, 80, 30);
		
		labelSize.setBounds(220, 330, 80, 30);
		
		labelBMI.setBounds(375, 400, 50, 30);
				
		labelTitle.setBounds(200, 10, 400, 80);
		labelTitle.setBorder(BorderFactory.createLineBorder(Color.black));
		labelTitle.setHorizontalAlignment(JLabel.CENTER);
		
		labelWeightTopic.setBounds(0, 90, 100, 30);
		
		labelSizeTopic.setBounds(0, 220, 80, 30);
		
		buttonCalc.addActionListener(listenerButtonCalc);
		buttonCalc.setText("BMI berechnen");
		buttonCalc.setBounds(450, 330, 120, 30);
		
		
	
	}
	
	//BMI berechnen, R�ckgabe als Float
	//die auskommentierten Konsolenausgaben dienen nur zu Debugzwecken
	public float calcBmi(){
//		System.out.println(sliderWeight.getValue());
		float gewicht = sliderWeight.getValue();
//		System.out.println("Gewicht: " + gewicht);
		float groesse = sliderSize.getValue();
//		System.out.println("Gr�sse: " + groesse);
		float bmi = gewicht / (groesse * groesse) * 10000;
//		System.out.println(bmi);
		return bmi;
		
	}

}

