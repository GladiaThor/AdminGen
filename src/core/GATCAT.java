/*
 * Guidance, Assitance, and Teaching, Casted Administration Tool
 * and open the template in the editor.
 */
/**
 * *(>^_^)> @author Claxxess<(^_^<)**
 */
package core;

import cso.AdvancedBox;
import panels.BidragSGFormPanel;
import panels.OperatorPanelOffline;
import panels.SelectionPanel;
import panels.ErrandFormPanel;
import panels.OperatorPanelOnline;
import panels.JAPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.apache.pdfbox.exceptions.COSVisitorException;

public class GATCAT {

    static panels.JAPanel formPanel;
    static JAPanel operatorPanel;
    static SelectionPanel selectorPanel;
    static AdvancedBox box = new AdvancedBox();
    static JFrame jf = new JFrame();
    static Person operator = new Person();
    static String boxValue = "Äskande mot Kvitto";
    static String[] availableForms = {"Studentärende", "Äskande mot Kvitto"};

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(Helpers.getDateYear());
        System.out.println(Helpers.getDateMonth());
        System.out.println(Helpers.getDateDay());
        initComponents();
    }

    /**
     * Log in and out button handler
     *
     */
    public static void logInOut() {

        operator = operatorPanel.logIn();
        
        System.out.println(operator.getFirstName());
        
        rebuildFrame();

    }
    static ActionListener log = new java.awt.event.ActionListener() {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            logInOut();
        }
    };
    static ActionListener rebuild = new java.awt.event.ActionListener() {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            rebuildFrame();
        }
    };
    static ActionListener changeForm = new java.awt.event.ActionListener() {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {

            boxValue = selectorPanel.getLeftBox();

            rebuildFrame();
        }
    };

    private static void initComponents() {

        //Preconfg frame
        jf.setLayout(new BorderLayout());
        jf.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Create all panels
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.PAGE_AXIS));
        JPanel midPanel = new JPanel();
        midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.PAGE_AXIS));
        JPanel botPanel = new JPanel();
        botPanel.setLayout(new BoxLayout(botPanel, BoxLayout.PAGE_AXIS));

        //Populating top panel
        operatorPanel = operatorSelector(); //Check if loged in or not
        topPanel.add(operatorPanel);
        topPanel.add(new javax.swing.JSeparator());

        //Populating center panel
        formPanel = formSelector();
        midPanel.add(formPanel);

        //Populating bottom panel
        JButton printButton = new JButton();
        printButton.setText("Print to PDF");

        //The SelectionPanels
        botPanel.add(printButton);
        selectorPanel = boxer(boxValue);
        topPanel.add(selectorPanel);

        //Finalise Frame by adding panels and packing it
        jf.add(topPanel, BorderLayout.NORTH);

        jf.add(midPanel, BorderLayout.CENTER);
        jf.add(botPanel, BorderLayout.SOUTH);
        jf.pack();
        jf.setVisible(true);

        printButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printPanel();
                rebuildFrame(); //You can add evt here and use it to target components <3

            }
        });
        box.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectApplicant();//You can add evt here and use it to target components <3
            }
        });

    }

    public static JAPanel operatorSelector() {

        if ( operator.getNumb().equals("%%&&%%&&##&&##&&")) {
            System.out.println("OFFLINE");
            operatorPanel = new OperatorPanelOffline();
            operatorPanel.epicAction(log); //Adds actionListeener log to OperatorPanel

        } else {
            System.out.println("ONLINE! YEY");
            operatorPanel = new OperatorPanelOnline(operator);

        }
        return operatorPanel;
    }

    /**
     * Fills out the form below using the info from the student database
     */
    public static void selectApplicant() {
        formPanel.autoFill(box.itemiser());
    }

    /**
     * It is used to rebuild the main window
     *
     */
    public static void rebuildFrame() {
        formPanel.clearForm();
        jf.getContentPane().removeAll();
        initComponents();

    }

    public static panels.JAPanel formSelector() {
        if (boxValue.equals(availableForms[0])) {
            return new ErrandFormPanel();
        }
        if (boxValue.equals(availableForms[1])) {
            return new BidragSGFormPanel();
        } else {
            JAPanel jp = new JAPanel();
            jp.add(new JLabel("Form not supported, Contact tech support at Support@AsgardTech.com"));
            return jp;
        }
    }

    /**
     * Prints form to PDF and resets all values to empty
     */
    public static void printPanel() {
        String target = formPanel.createPDF(selectorPanel.getLeftBox(), "Printtest");
        Populater pop = new Populater();
        ArrayList<PDFField> values = new ArrayList();
        values.addAll(formPanel.compileList());
        values.addAll(operatorPanel.compileList());
        for (int i = 0; i < values.size(); i++) { //Use try and catch to cleanup values that are null
            try {
               String x = "NAME: " + values.get(i).getName()+ "  --- VALUE: " + values.get(i).getValue().toString();

            } catch (Exception e) {
                values.remove(i);
            }

        }
        System.out.println(target);
        try {

            pop.write(target, values);
        } catch (IOException | COSVisitorException e) {
            // System.out.println("Writing failed due to: " + e.toString());
        }

        formPanel.clearForm();
        jf.getContentPane().removeAll();
        initComponents();

    }

    /**
     * Creates a dual selection box based on the choice of the left box.
     *
     * @param leftSelected
     * @return
     */
    public static SelectionPanel boxer(String leftSelected) {

        String rightSelected;
        switch (leftSelected) {
            case "Äskande mot Kvitto":
                rightSelected = "Applicants";
                break;
            case "Studentärende":
                rightSelected = "Students";
                break;
            default:
                rightSelected = "Helper";
                break;
        }
        SelectionPanel sp = new SelectionPanel("Templates", rightSelected, leftSelected);
        sp.addActionToLeft(changeForm);
        return sp;
    }
//INSERT INTO `PersonDB` (firstName,surName,numb,tel,mail, clrNr, accNr, bank, op, obs, password) 
//VALUES ('Thor','Salehi','880814-0571','+46700596440','Thor@asgard.pm','0000-0','767676776-9','Bank of Asgard', true, true,'kittypower')

    //SELECT `password` FROM `PersonDB` WHERE numb='880814-0571';
    //http://www.cheat-sheets.org/sites/sql.su/#data_manipulation
}
