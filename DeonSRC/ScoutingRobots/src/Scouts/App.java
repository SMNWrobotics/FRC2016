package Scouts;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class App extends JPanel implements ActionListener {
	Team[] Robot= new Team[7000];
	private
	//Initialize PANELS
	JPanel MainPanel = new JPanel(new GridLayout(4,4));
	JPanel Entries = new JPanel(new GridLayout(3,4));
	JPanel ListPanel = new JPanel();
	JPanel BottomPanel = new JPanel();
	JPanel BottomPanel2 = new JPanel();
	GridLayout EntryGrid = new GridLayout(0,2,20,30);
	int counter;
	JFrame Frame = new JFrame("Cougar Robotics Scouting 2016");
	
	//Entry Panels
	//LAbels
	JLabel TeamNumberL= new JLabel("TeamNumber");
	JLabel PortcullisL= new JLabel("Crossed Portcullis");
	JLabel ChevalL = new JLabel("Crossed Cheval");
	
	JTextField TeamNumberText = new JTextField();
	JTextField CatAText= new JTextField(1);
	JTextField CatBText = new JTextField();
	JTextField CatCText = new JTextField();
	JTextField CatDText = new JTextField();
	
	//Initialize Comboxes/ dropdown menus
	String[] CategoryALabel= {"-Select Type A Defense-","Portcullis","Cheval"};
	String[] CategoryBLabel= {"-Select Type B Defense-","Rampart","Moat"};
	String[] CategoryCLabel= {"-Select Type C Defense-","DrawBridge","SallyPort"};
	String[] CategoryDLabel= {"-Select Type D Defense-","RockWall","RoughTerrain"};

	JComboBox CatABox= new JComboBox(CategoryALabel);
	JComboBox CatBBox= new JComboBox(CategoryBLabel);
	JComboBox CatCBox= new JComboBox(CategoryCLabel);
	JComboBox CatDBox= new JComboBox(CategoryDLabel);
	
	//Bottom Panel
	JButton SubmitButton = new JButton("Submit Values");
	JButton ExportButton= new JButton("ExportFile");
	JButton ResizeButton = new JButton("Resize Window");
	
	//UserInputed values and fields
	boolean PortcullisChosen;
	boolean ChevalChosen;
	boolean RampartChosen;
	boolean MoatChosen;
	boolean DrawBridgeChosen;
	boolean SallyChosen;
	boolean RockChosen;
	boolean RoughChosen;
	
	boolean AnyErrors;
	boolean AlreadySaved;
	
	int PortcullisChosenValue;
	int ChevalChosenValue;
	int RampartChosenValue;
	int MoatChosenValue;
	int DrawBridgeChosenValue;
	int SallyChosenValue;
	int RockChosenValue;
	int RoughChosenValue;
	
	//Error Messages
	String ErrorCross = "ERROR: Can't cross defense more than twice or cross it negative times";
	String ErrorChooseD = "ERROR: Must choose a defense type";
	String ErrorTeam = "ERROR: Must have valid team number";
	String ErrorTextFields = "ERROR: Please check that inputed values are actual numbers";
	String SuccessMsg = "Game Submitted";
	
	int CompetitorNumber;
	int ValueInCatA;
	int ValueInCatB;
	int ValueInCatC;
	int ValueInCatD;
	//List Table
	
	String[] Columns= {"TeamNumber","Port","Cheval","Ramp","Moat","Draw","Sally","Rock","Rough"};
	Object[][] data;
	JTable Table = new JTable(new DefaultTableModel(new Object[]{"TeamNumber","Port","Cheval","Ramp","Moat","Draw","Sally","Rock","Rough"},0){
		@Override
		public boolean isCellEditable(int row, int column){
			return false;
		}
	});
	JTable Table2 = new JTable(new DefaultTableModel(new Object[]{"TeamNumber","Port","Cheval","Ramp","Moat","Draw","Sally","Rock","Rough"},0));
	DefaultTableModel model = (DefaultTableModel)Table.getModel();
	DefaultTableModel SavedModel=new DefaultTableModel(new Object[]{"TeamNumber","Port","Cheval","Ramp","Moat","Draw","Sally","Rock","Rough"},0); 
	
	
	JScrollPane ScrollPane = new JScrollPane(Table);
	JScrollPane ScrollPane2 = new JScrollPane(Table2);
	File importedFile;
	public App() {
		counter=0;
		//Initialize starting values, no defense is picked yet and no values are inputed in the fields
		PortcullisChosen = false;
		ChevalChosen = false;
		RampartChosen= false;
		MoatChosen= false;
		DrawBridgeChosen= false;
		SallyChosen= false;
		RockChosen= false;
		RoughChosen= false;
		
		AnyErrors = false;
		AlreadySaved = true;
		
		PortcullisChosenValue = 0;
		ChevalChosenValue = 0;
		RampartChosenValue= 0;
		MoatChosenValue= 0;
		DrawBridgeChosenValue= 0;
		SallyChosenValue= 0;
		RockChosenValue= 0;
		RoughChosenValue= 0;
		
		//Create the Frame
		Frame.setLayout(new GridLayout(0,2));
		Frame.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		Frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Frame.setSize(948, 500);
		Frame.setVisible(true);
		Frame.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e){
				int x = JOptionPane.showConfirmDialog(null, "Are you sure?","close", JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE); 
				if(x==JOptionPane.YES_OPTION){
					if(!AlreadySaved)
						exportData();
					e.getWindow().dispose();
				}else{
					
				}
					
					
			}
		});
		//Create Comboxes
		CatABox.addActionListener(this);
		CatBBox.addActionListener(this);
		CatCBox.addActionListener(this);
		CatDBox.addActionListener(this);
		//Create the Entry panel
		Entries.setBackground(Color.YELLOW);
		Entries.setLayout(EntryGrid);
		
		Entries.add(TeamNumberL);
		Entries.add(TeamNumberText);
		Entries.add(CatABox);
		Entries.add(CatAText);
		Entries.add(CatBBox);
		Entries.add(CatBText);
		Entries.add(CatCBox);
		Entries.add(CatCText);
		Entries.add(CatDBox);
		Entries.add(CatDText);
		
		BottomPanel.setBackground(Color.GREEN);
		BottomPanel.add(PortcullisL,BorderLayout.EAST);
		BottomPanel.add(SubmitButton,BorderLayout.EAST);
		BottomPanel.add(ExportButton);
		BottomPanel.add(ResizeButton);
		ResizeButton.addActionListener(this);
		ExportButton.addActionListener(this);
		SubmitButton.addActionListener(this);
		//Table
		importedFile= new File("C:\\Users\\1982\\Desktop\\ScoutingTable.txt");
		importData(importedFile);
		Table.setAutoCreateRowSorter(false);
		Table2.setModel(SavedModel);
		
		Table.setAutoCreateRowSorter(true);
		ScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		ListPanel.add(ScrollPane);
		Table.setFillsViewportHeight(true);
		//Table2
		BottomPanel2.setBackground(Color.YELLOW);
		BottomPanel2.add(ScrollPane2);
		
		SubmitButton.setText(Integer.toString(Table.getRowCount()));
		
	}
	public void initialize(){
	

		ListPanel.setBackground(Color.BLUE);
		
		//Entries.add(TeamNumberText);
	
		Frame.add(Entries);
		Frame.add(ListPanel);
		Frame.add(BottomPanel);
		Frame.add(BottomPanel2);
	}
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==ResizeButton)
		{
			resizeFields();
		}
		if(e.getSource()==ExportButton)
		{
			exportData();
		}
		if(e.getSource()==SubmitButton)
		{
			AnyErrors = false;
			testTeamNumber(); //make sure user inputed values are ok for team number then create robot for that team
			testTextFields();// make sure user inputed values are ok for rest of textfields then stores those to variables 
			testCategoryTextFields(PortcullisChosen,ChevalChosen,ValueInCatA,'A'); //  make sure user inputed values are with range and that a defense was actually chosen
			testCategoryTextFields(RampartChosen,MoatChosen,ValueInCatB,'B'); // do same test but with Category B values
			testCategoryTextFields(DrawBridgeChosen,SallyChosen,ValueInCatC,'C');
			testCategoryTextFields(RockChosen,RoughChosen,ValueInCatD,'D');
			addAllPoints();//   finally adds the user inputed values to the actually robot's values
		}
		if(e.getSource()== CatABox)
		{
			JComboBox selected= (JComboBox)e.getSource();
			String msg = (String)selected.getSelectedItem();
			switch(msg){
				case "Portcullis": PortcullisChosen = true; ChevalChosen = false; PortcullisL.setText("PORT");break;
				case "Cheval"	 : ChevalChosen = true; PortcullisChosen = false; PortcullisL.setText("CHEV");break;
				default			 : ChevalChosen = false; PortcullisChosen = false; PortcullisL.setText("UHOH");break;
			}
		
		}
		if(e.getSource()== CatBBox)
		{
			JComboBox selected= (JComboBox)e.getSource();
			String msg = (String)selected.getSelectedItem();
			switch(msg){
				case "Rampart": RampartChosen = true; MoatChosen = false; PortcullisL.setText("RAMP");break;
				case "Moat"	  : MoatChosen = true; RampartChosen = false; PortcullisL.setText("MOAT");break;
				default		  : MoatChosen = false; RampartChosen = false; PortcullisL.setText("UHOH");break;
			}
		
		}
		if(e.getSource()== CatCBox)
		{
			JComboBox selected= (JComboBox)e.getSource();
			String msg = (String)selected.getSelectedItem();
			switch(msg){
				case "DrawBridge": DrawBridgeChosen = true; SallyChosen = false; PortcullisL.setText("DRAW");break;
				case "SallyPort" : SallyChosen = true; DrawBridgeChosen = false; PortcullisL.setText("SALL");break;
				default			 : SallyChosen = false; DrawBridgeChosen = false; PortcullisL.setText("UHOH");break;
			}
		
		}
		if(e.getSource()== CatDBox)
		{
			JComboBox selected= (JComboBox)e.getSource();
			String msg = (String)selected.getSelectedItem();
			switch(msg){
				case "RockWall": RockChosen = true; RoughChosen = false; PortcullisL.setText("ROCK");break;
				case "RoughTerrain" : RoughChosen = true; RockChosen = false; PortcullisL.setText("ROUG");break;
				default			 : RoughChosen = false; RockChosen = false; PortcullisL.setText("UHOH");break;
			}
		
		}
	}
	public void entryPanelCorrector()
	{
		EntryGrid.setVgap((int)(Frame.getHeight()*.06));
	}
	public void clearFields(){
		TeamNumberText.setText(null);
		CatAText.setText(null);
		CatBText.setText(null);
		CatCText.setText(null);
		CatDText.setText(null);
	}
	private void testTeamNumber(){
		try {
			CompetitorNumber = Integer.valueOf(TeamNumberText.getText()); //Tries converting the Text in TeamNumber to an integer
		} catch (NumberFormatException e) { //if it fails then someone put something wrong in the text field
			JOptionPane.showMessageDialog(null, ErrorTeam);
			AnyErrors = true;
		}
		if(CompetitorNumber <7000 && CompetitorNumber >0){
			if(Robot[CompetitorNumber]== null) //Test to see if Team with this TeamNumber exists. If it doesn't create it
			{
				Robot[CompetitorNumber] = new Team(CompetitorNumber);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, ErrorTeam);
			AnyErrors = true;
		}
			
	}
	private void testTextFields(){
		try {
			ValueInCatA= Integer.valueOf(CatAText.getText()); //Tries converting the Text in all textfields to an integer
			ValueInCatB= Integer.valueOf(CatBText.getText());
			ValueInCatC= Integer.valueOf(CatCText.getText());
			ValueInCatD= Integer.valueOf(CatDText.getText());
		} catch (NumberFormatException e) { //if it fails then someone put something wrong in the text field
			JOptionPane.showMessageDialog(null, ErrorTextFields);;
			AnyErrors = true;
		}
	}
	
	private void testCategoryTextFields(boolean DefenseChosen1, boolean DefenseChosen2, int ValueInCatText, char CategoryType){
		if(DefenseChosen1 == true && DefenseChosen2 == false && AnyErrors == false)//if no errors so far and the portcullis is chosen
		{
			if(ValueInCatText < 3 && ValueInCatText >=0){ //if value in text fields are 0-2 inclusive then set to add crossing to portcullis
				switch(CategoryType){//Used to determine which values to modify 
				case 'A':PortcullisChosenValue = ValueInCatText;
						 ChevalChosenValue = 0; break;
				case 'B':RampartChosenValue = ValueInCatText;
				 		 MoatChosenValue = 0; break;
				case 'C':DrawBridgeChosenValue = ValueInCatText;
		 		 		 SallyChosenValue = 0; break;
				case 'D':RockChosenValue = ValueInCatText;
		 		 		 RoughChosenValue = 0; break;
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, ErrorCross);//if not in the range then display error message
				AnyErrors = true;
			}
			
		}
		else if(DefenseChosen2 == true && DefenseChosen1 == false && AnyErrors == false) // if the Cheval was chosen and there are no errors
		{
			if(ValueInCatText < 3 && ValueInCatText >=0){ //if points are within the range
				switch(CategoryType){
				case 'A':PortcullisChosenValue = 0;
						 ChevalChosenValue = ValueInCatText; break;
				case 'B':RampartChosenValue = 0;
				 		 MoatChosenValue = ValueInCatText; break;
				case 'C':DrawBridgeChosenValue = 0;
		 		 		 SallyChosenValue = ValueInCatText; break;
				case 'D':RockChosenValue = 0;
		 		 		 RoughChosenValue = ValueInCatText; break;
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, ErrorCross);//if not in the range display error message saying edit inputed value's range
				AnyErrors = true;
			}

		}
		else if(AnyErrors == false)//if neither the portcullis nor the cheval is chosen or something goes COMPLETELY wrong
		{
			JOptionPane.showMessageDialog(null, ErrorChooseD);//display error message choose defense
			AnyErrors = true;
		}
	}
	
	private void addAllPoints(){
		if(!AnyErrors) //if there aren't any errors then proceed, else don't add any values
		{
			Table.setAutoCreateRowSorter(false);
			for(int l =0;l< SavedModel.getRowCount(); l++)
			{
				for(int p = 0; p<SavedModel.getColumnCount(); p++)
				{
					model.setValueAt(SavedModel.getValueAt(l, p), l, p);
				}
			}
			Robot[CompetitorNumber].addToPort(PortcullisChosenValue);
			Robot[CompetitorNumber].addToCheval(ChevalChosenValue);
			Robot[CompetitorNumber].addToRamparts(RampartChosenValue);
			Robot[CompetitorNumber].addToMoat(MoatChosenValue);
			Robot[CompetitorNumber].addToDraw(DrawBridgeChosenValue);
			Robot[CompetitorNumber].addToSally(SallyChosenValue);
			Robot[CompetitorNumber].addToRock(RockChosenValue);
			Robot[CompetitorNumber].addToRough(RoughChosenValue);
			int P = Robot[CompetitorNumber].getPort();
			int C = Robot[CompetitorNumber].getCheval();
			int R = Robot[CompetitorNumber].getRamparts();
			int M = Robot[CompetitorNumber].getMoat();
			int D = Robot[CompetitorNumber].getDraw();
			int S = Robot[CompetitorNumber].getSally();
			int RW = Robot[CompetitorNumber].getRock();
			int RT = Robot[CompetitorNumber].getRough(); // ;)
			String team = Integer.toString(CompetitorNumber);
			String a = Integer.toString(P);
			String b = Integer.toString(C);
			String c = Integer.toString(R);
			String d = Integer.toString(M);
			String e = Integer.toString(D);
			String f = Integer.toString(S);
			String g = Integer.toString(RW);
			String h = Integer.toString(RT);
			int ji= Table.getRowCount()-1;
			if(Table.getRowCount()>0){
				for(int x = 0; x<=ji;x++){
					if(Table.getValueAt(x, 0).toString().equals(team)){
						model.removeRow(x);
						ji= ji-1;
					}
						
					
				}
			}
			model.addRow(new Object[]{team,a,b,c,d,e,f,g,h});
			clearFields();
			
			counter++;
			AlreadySaved = false;
			JOptionPane.showMessageDialog(null, SuccessMsg);
			//SavedModel = (DefaultTableModel)Table.getModel();
		}
		
	}
	public void resizeFields(){
		int x =Frame.getWidth();
		int y = Frame.getHeight();
		ScrollPane.setPreferredSize(new Dimension(x/2-8,y/2-24));
		Frame.setSize(x, y+1);
	}
	private void importData(File file){
		try{
		BufferedReader txtReader= new BufferedReader(new FileReader(file.getAbsoluteFile()));
		String eachLine;
		while((eachLine=txtReader.readLine()) != null)
		{
			model.addRow(eachLine.split("\\s+"));
			SavedModel.addRow(eachLine.split("\\s+"));
		}
		for(int x=0;x < Table.getRowCount();x++)
		{
			int Rteam = Integer.valueOf((String)Table.getModel().getValueAt(x, 0));
			Robot[Rteam] = new Team(Rteam);
			Robot[Rteam].addToPort(Integer.valueOf((String)Table.getModel().getValueAt(x, 1)));
			Robot[Rteam].addToCheval(Integer.valueOf((String)Table.getModel().getValueAt(x, 2)));
			Robot[Rteam].addToRamparts(Integer.valueOf((String)Table.getModel().getValueAt(x, 3)));
			Robot[Rteam].addToMoat(Integer.valueOf((String)Table.getModel().getValueAt(x, 4)));
			Robot[Rteam].addToDraw(Integer.valueOf((String)Table.getModel().getValueAt(x, 5)));
			Robot[Rteam].addToSally(Integer.valueOf((String)Table.getModel().getValueAt(x, 6)));
			Robot[Rteam].addToRock(Integer.valueOf((String)Table.getModel().getValueAt(x, 7)));
			Robot[Rteam].addToRough(Integer.valueOf((String)Table.getModel().getValueAt(x, 8)));
			
		}
		txtReader.close();
		}catch(IOException ex){
			try{file.createNewFile();}
			catch(IOException except){JOptionPane.showMessageDialog(null, "UnExpected Error Encountered when trying to create file");}
			JOptionPane.showMessageDialog(null, "No File to Import so file has been created");
			
		}
	}
	private void exportData(){

		try{
			File file = new File("C:\\Users\\1982\\Desktop\\ScoutingTable"+".txt");
			if(!file.exists()){
				file.createNewFile();}
			
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw =new BufferedWriter(fw);
			for(int x=0;x < Table.getRowCount();x++)
			{
				for(int y=0;y<Table.getColumnCount();y++)
				{
					bw.write((String)Table.getModel().getValueAt(x,y)+ " ");
				}
				bw.write("\n");
			}
			bw.close();
			fw.close();
			AlreadySaved = true;
			JOptionPane.showMessageDialog(null, "File Exported");;

			}
		catch(Exception ex)
			{ExportButton.setText("WOOOOPS");
			
			}
	}
}

