// Group Name :- JDK
// Member UCD ID :- 14208913
// Member Name :- Pemaththu Hewage Prasad Dhananjaya Gamlath
// Project Name :- DSA Project






package org.datastructure.com;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.JFrame;
import javax.swing.JOptionPane;




//import org.eclipse.swt.widgets.Decorations;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.graphics.Point;
//import org.omg.CORBA.Object;


public class RealEstate extends javax.swing.JFrame{
	
	/**
	 * Main Function of the Application .....
	 */
	public static void main(String[] args) {
		
		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RealEstate().setVisible(true);
            }
        });
		
	}


	
	private void btn_ResetActionPerformed(SelectionEvent e){
		
		textFirstName.setText("");
        textLastName.setText("");
        textLotNumber.setText("");
        textPrice.setText("");
        textSquareFeet.setText("");
        textNumberOfBedRooms.setText("");
        
        
		lblNextHouseDisplayed.setText("Next House Displayed : ");

        row = 0;

        checkAdd = true;
	}


	
	private void btn_nextActionPerformed(SelectionEvent evt) {

        checkAdd = false;
        
        houses = new House().readFile();
        if (!(houses.isEmpty())) {
            House house = houses.get(row);
            lblNextHouseDisplayed.setText("Next House Displayed : " + (row + 1));

            textFirstName.setText(house.getFirstName());
            textLastName.setText(house.getLastName());
            textLotNumber.setText(house.getLotNumber() + "");
            textNumberOfBedRooms.setText(house.getNumberOfBedRooms() + "");
            textPrice.setText(house.getPrice() + "");
            textSquareFeet.setText(house.getSquareFeet() + "");

            Integer i = houses.size();
            if (--i != row) {
                row++;
            }
        }
    }//event_btn_nextActionPerformed

    private void btn_addActionPerformed(SelectionEvent evt) {
    	
        if (checkAdd && !(textLotNumber.getText().equals(""))) {

            House house = new House();
            house.setFirstName(textFirstName.getText());
            house.setLastName(textLastName.getText());
            house.setLotNumber(textLotNumber.getText());
            house.setNumberOfBedRooms(textNumberOfBedRooms.getText());
            house.setPrice(textPrice.getText());
            house.setSquareFeet(textSquareFeet.getText());
            
            Boolean bol = false;
            houses = new House().readFile();
            for (House h : houses) {
            	if (Objects.equals(h.getLotNumber(), house.getLotNumber())) {
	                    bol = true;
	        	}
	        }

            if (bol) {
                JOptionPane.showMessageDialog(rootPane, "Error in Lot Number", "Error", JOptionPane.WARNING_MESSAGE);
            } else {
                House fm = new House();
                fm.saveHouseFile(house);

                House hf = new House();
                houses = hf.readFile();

                JOptionPane.showMessageDialog(rootPane, "Successfully Added", "Success", JOptionPane.INFORMATION_MESSAGE);

                clear();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "You need to rest befor add or Check Lot Number !", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }//event_btn_addActionPerformed
 
    private void btn_deleteActionPerformed(SelectionEvent evt) {
    	
    	JOptionPane.showMessageDialog(rootPane, "Error No Recode Selected !", "Error", JOptionPane.QUESTION_MESSAGE );
    	
    	if (textLotNumber.getText().equals("")){
    		 JOptionPane.showMessageDialog(rootPane, "Lot Number can't be empty !", "Error", JOptionPane.WARNING_MESSAGE);
    	}
    	else if (checkAdd) {
            JOptionPane.showMessageDialog(rootPane, "Error No Recode Selected !", "Error", JOptionPane.WARNING_MESSAGE);
        } else {
            List<House> newList = new ArrayList<>();

            for (House h : houses) {
                if (!(h.getLotNumber().toString().equals(textLotNumber.getText()))) {
                    newList.add(h);
                }
            }

            JOptionPane.showMessageDialog(rootPane, "Successfully Delete", "Success", JOptionPane.INFORMATION_MESSAGE);

            textFirstName.setText("");
            textLastName.setText("");
            textLotNumber.setText("");
            textNumberOfBedRooms.setText("");
            textPrice.setText("");
            textSquareFeet.setText("");

            lblNextHouseDisplayed.setText("Next House Displayed : ");

            row = 0;

            checkAdd = true;
            System.out.println(newList.size());
            House hf = new House();
            hf.saveHouseList(newList);

            houses = hf.readFile();
        }
    }//event_btn_deleteActionPerformed

    private void btn_sortedListActionPerformed(SelectionEvent evt) {
    	
    	House hf = new House();
        houses = hf.readFile();
        
        ListHouse lh = new ListHouse(this, rootPaneCheckingEnabled,houses);
        lh.setVisible(true);

        SortedList sl = new SortedList();
        sl.setHouses(houses);

        houses = sl.getShortHouses();

        hf.saveHouseList(houses);
    }//event_btn_sortedListActionPerformed

    private void btn_findActionPerformed(SelectionEvent evt) {

    	if (textLotNumber.getText().equals("")){
   		 JOptionPane.showMessageDialog(rootPane, "Lot Number can't be empty !", "Error", JOptionPane.WARNING_MESSAGE);
	   	}
	   	else{
	        textFirstName.setText("");
	        textLastName.setText("");
	        //        jTextField_lotNumber.setText("");
	        textNumberOfBedRooms.setText("");
	        textPrice.setText("");
	        textSquareFeet.setText("");
	
	        //lblNextHouseDisplayed.setText("Next House Displayed : ");
	
	        row = 0;
	
	        checkAdd = true;
	
	        try {
	            Long lotNumber = Long.parseLong(textLotNumber.getText());
	
	            House hf = new House();
	            List<House> houses = hf.readFile();
	            Boolean v = false;
	            //            System.out.println("l "+lotNumber);
	            for (House h : houses) {
	                    //System.out.println("s "+h.getLotNumber());
	            		//System.out.println(lotNumber + "-"+ h.getLotNumber());
	                if (Objects.equals(Long.parseLong(h.getLotNumber()),lotNumber)) {
	                    textFirstName.setText(h.getFirstName());
	                    textLastName.setText(h.getLastName());
	                    textNumberOfBedRooms.setText(h.getNumberOfBedRooms());
	                    textPrice.setText(h.getPrice());
	                    textSquareFeet.setText(h.getSquareFeet());
	                    lblNextHouseDisplayed.setText("Next House Displayed : " + h.getLotNumber());
	                    v = true;
	                }
	                
	            }
	
	            if (!v) {
	                JOptionPane.showMessageDialog(rootPane, "No Result to Show", "Information ", JOptionPane.INFORMATION_MESSAGE);
	            }
	        } catch (NumberFormatException e) {
	            JOptionPane.showMessageDialog(rootPane, "Lot number Error !", "Error ", JOptionPane.ERROR_MESSAGE);
	        }
   		}
    }//event_btn_findActionPerformed
    
    
    
    
    
    // Clear Controls in the form ..
    private void clear(){

    	textFirstName.setText("");
        textLastName.setText("");
        textLotNumber.setText("");
        textPrice.setText("");
        textSquareFeet.setText("");
        textNumberOfBedRooms.setText("");
        
        lblNextHouseDisplayed.setText("Next House Displayed : ");

        row = 0;

        checkAdd = true;
    }
