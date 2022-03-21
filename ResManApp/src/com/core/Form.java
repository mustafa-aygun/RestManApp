package com.core;

import com.support.Order;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import static javax.swing.JOptionPane.*;

/** It's the class where all our GUI will work. As suggested I didn't use designer and tried to managed everything myself to better learning outcome.
 * We will call this in the main and then everything will be happen till the end.
 * I tried to use my previous functions for some cases and make a bit changes on them to use it.
 */
public class Form{
    protected JFrame myMenu;
    protected JOptionPane operationDone;
    protected JPanel deleteStaffPanel;
    protected JPanel listStaffPanel;
    protected JPanel searchStaffPanel;
    protected JPanel addStaffPanel;
    protected JPanel addCustomerPanel;
    protected JPanel listCustomerPanel;
    protected JPanel deleteCustomerPanel;
    protected JPanel searchCustomerPanel;
    protected JPanel addBookingPanel;
    protected JPanel customerLastBookingPanel;
    protected JPanel listCustomerOrdersPanel;
    protected JPanel addOrderPanel;
    protected JPanel getAllStaffSalary;
    protected JPanel addOrderOfBooking;
    protected JPanel listALlOrderPanel;
    protected JPanel mainMenuPanel;

    /**It is a constructor for the class. Panels are created as class instances.
     * Mainly, I will transfer between panels while application running on same frame.
     */
    public Form(){
        myMenu = new JFrame("Restaurant Application");
        addStaffPanel = new JPanel();
        deleteStaffPanel = new JPanel();
        listStaffPanel = new JPanel();
        searchStaffPanel = new JPanel();
        addCustomerPanel = new JPanel();
        deleteCustomerPanel = new JPanel();
        listCustomerPanel = new JPanel();
        searchCustomerPanel = new JPanel();
        addBookingPanel = new JPanel();
        customerLastBookingPanel = new JPanel();
        listCustomerOrdersPanel = new JPanel();
        addOrderPanel = new JPanel();
        getAllStaffSalary = new JPanel();
        addOrderOfBooking = new JPanel();
        listALlOrderPanel = new JPanel();
        operationDone = new JOptionPane();
        mainMenuPanel = new JPanel();
    }

    /** It is a void method to close all panels. After, a panel started to work if user directly wants to open another operation, we should close
     * that panel. Therefore, I am closing all of them here to be sure the necessary panel will be closed.
     */
    public void closeAllPanels(){
        addStaffPanel.setVisible(false);
        deleteStaffPanel.setVisible(false);
        listStaffPanel.setVisible(false);
        searchStaffPanel.setVisible(false);
        addCustomerPanel.setVisible(false);
        deleteCustomerPanel.setVisible(false);
        listCustomerPanel.setVisible(false);
        searchCustomerPanel.setVisible(false);
        addBookingPanel.setVisible(false);
        customerLastBookingPanel.setVisible(false);
        listCustomerOrdersPanel.setVisible(false);
        addOrderPanel.setVisible(false);
        getAllStaffSalary.setVisible(false);
        addOrderOfBooking.setVisible(false);
        listALlOrderPanel.setVisible(false);
        mainMenuPanel.setVisible(false);

    }
    /** It is the main GUI method where our frame created and arranged. All operations will call in this method.
     * Instead of getting a RestManApp variable from main, I am creating it here. I will import and export data in GUI anyways. That's why I don't want
     * pass object from main. In method, I will shortly comment parts.*/
    public void myForm() {

        myMenu = new JFrame("Restaurant Application");
        myMenu.setLayout(null);
        myMenu.setSize(500,500);
        myMenu.setVisible(true);
        myMenu.setResizable(false);
        final RestManApp[] myApp = new RestManApp[1]; /* Here, I created it as 1 variable array because I will send it to action listener to import file. */
        myApp[0] = new RestManApp();
        /*Here, I am initializing menubar and their menubar items. */
        JMenuBar menuBar = new JMenuBar();
        JMenu staffMenu = new JMenu("Staff Operations");
        menuBar.add(staffMenu);
        myMenu.setJMenuBar(menuBar);

        JMenuItem addStaff = new JMenuItem("Add Staff");
        JMenuItem deleteStaff = new JMenuItem("Delete Staff");
        JMenuItem listStaff = new JMenuItem("List Staffs");
        JMenuItem searchStaff = new JMenuItem("Search Staff");
        JMenuItem getSalary = new JMenuItem("List All Salary");

        staffMenu.add(addStaff);
        staffMenu.add(deleteStaff);
        staffMenu.add(listStaff);
        staffMenu.add(searchStaff);
        staffMenu.add(getSalary);

        JMenu customerMenu = new JMenu("Customer Operations");
        menuBar.add(customerMenu);

        JMenuItem addCustomer = new JMenuItem("Add Customer");
        JMenuItem deleteCustomer = new JMenuItem("Delete Customer");
        JMenuItem listCustomer = new JMenuItem("List Customers");
        JMenuItem searchCustomer = new JMenuItem("Search Customer");
        JMenuItem customerLastBooking = new JMenuItem("Customer Last Booking");
        JMenuItem customerOrders = new JMenuItem("List All Orders of A Customer");

        customerMenu.add(addCustomer);
        customerMenu.add(deleteCustomer);
        customerMenu.add(listCustomer);
        customerMenu.add(searchCustomer);
        customerMenu.add(customerLastBooking);
        customerMenu.add(customerOrders);

        JMenu bookingMenu = new JMenu("Booking & Order Operations");
        menuBar.add(bookingMenu);

        JMenuItem addBooking = new JMenuItem("Add Booking");
        JMenuItem addOrder = new JMenuItem("Add Order");
        JMenuItem addOrderBooking = new JMenuItem("Add Order of Booking");
        JMenuItem listALlOrder = new JMenuItem("List All Orders");

        bookingMenu.add(addBooking);
        bookingMenu.add(addOrder);
        bookingMenu.add(addOrderBooking);
        bookingMenu.add(listALlOrder);

        JMenu exit = new JMenu("Menu");

        JMenuItem exitOption = new JMenuItem("Close Application");
        JMenuItem exportOption = new JMenuItem("Export");
        JMenuItem importOption = new JMenuItem("Import");
        JMenuItem populateData = new JMenuItem("Populate Data with 3 Object");
        JMenuItem importData = new JMenuItem("Import from DataBase");
        JMenuItem exportData = new JMenuItem("Export to Database");
        JMenuItem exportCustomer = new JMenuItem("Export Customer");
        JMenuItem importCustomer = new JMenuItem("Import Customer");

        exit.add(exitOption);
        exit.add(exportOption);
        exit.add(importOption);
        exit.add(populateData);
        exit.add(exportData);
        exit.add(importData);
        exit.add(exportCustomer);
        exit.add(importCustomer);


        menuBar.add(exit);

        mainMenuPanel(); /* I am calling this panel for one time to show opening message to user.*/
        myMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); /* It is basic frame close option to stop project. */

        /*After that point, I am calling methods according to toolbar action.*/


        /** It is addStaff action listener which will call addStaff method.
         *  First, I am closing all panels then calling the appropriate method.
         */
        addStaff.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                closeAllPanels();
                addStaff(myApp[0]);
            }
        });

        /** It is deleteStaff action listener which will call deleteStaff method.
         *  First, I am closing all panels then calling the appropriate method.
         */
        deleteStaff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeAllPanels();
                deleteStaff(myApp[0]);
            }
        });

        /** It is listStaff action listener which will call listStaff method.
         *  First, I am closing all panels then calling the appropriate method.
         */
        listStaff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeAllPanels();
                listStaffs(myApp[0]);
            }
        });

        /** It is searchStaff action listener which will call searchStaff method.
         *  First, I am closing all panels then calling the appropriate method.
         */
        searchStaff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeAllPanels();
                searchStaff(myApp[0]);
            }
        });

        /** It is addCustomer action listener which will call addCustomer method.
         *  First, I am closing all panels then calling the appropriate method.
         */
        addCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeAllPanels();
                addCustomer(myApp[0]);
            }
        });

        /** It is deleteCustomer action listener which will call deleteCustomer method.
         *  First, I am closing all panels then calling the appropriate method.
         */
        deleteCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeAllPanels();
                deleteCustomer(myApp[0]);
            }
        });

        /** It is listCustomer action listener which will call listCustomer method.
         *  First, I am closing all panels then calling the appropriate method.
         */
        listCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeAllPanels();
                listCustomers(myApp[0]);
            }
        });

        /** It is searchCustomer action listener which will call searchCustomer method.
         *  First, I am closing all panels then calling the appropriate method.
         */
        searchCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeAllPanels();
                searchCustomer(myApp[0]);
            }
        });

        /** It is addBooking action listener which will call addBooking method.
         *  First, I am closing all panels then calling the appropriate method.
         */
        addBooking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeAllPanels();
                addBooking(myApp[0]);
            }
        });

        /** It is customerLastBooking action listener which will call customerLastBooking method.
         *  First, I am closing all panels then calling the appropriate method.
         */
        customerLastBooking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeAllPanels();
                customerLastBooking(myApp[0]);
            }
        });

        /** It is customerOrders action listener which will call customerOrders method.
         *  First, I am closing all panels then calling the appropriate method.
         */
        customerOrders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeAllPanels();
                customerOrders(myApp[0]);
            }
        });

        /** It is addOrder action listener which will call addOrder method.
         *  First, I am closing all panels then calling the appropriate method.
         */
        addOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeAllPanels();
                addOrder(myApp[0]);
            }
        });

        /** It is getSalary action listener which will call getSalary method.
         *  First, I am closing all panels then calling the appropriate method.
         */
        getSalary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeAllPanels();
                listAllSalary(myApp[0]);
            }
        });

        /** It is addOrderBooking action listener which will call addOrderBooking method.
         *  First, I am closing all panels then calling the appropriate method.
         */
        addOrderBooking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeAllPanels();
                addOrderOfBooking(myApp[0]);
            }
        });

        /** It is listALlOrderaction listener which will call listALlOrder method.
         *  First, I am closing all panels then calling the appropriate method.
         */
        listALlOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeAllPanels();
                listAllOrders(myApp[0]);
            }
        });
        int control[] = new int[1]; /*We will use this control later to control import and fill with predefined data option.*/
        control[0] = 0;

        /** It is exportOption listener which will do export operation.
         *  First, I am closing all panels then doing file exporting operations.
         *  Basically, with using JFileChooser save method I am getting the datapath where user wants to save file.
         *  Then, send that datapath and RestManApp object to IOStream's export method.
         */
        exportOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeAllPanels();
                JFileChooser myFileChooser = new JFileChooser();
                JButton save  = new JButton();
                IOStream ioStream = new IOStream();
                String myPath = new String();

                if(myFileChooser.showSaveDialog(save) == JFileChooser.APPROVE_OPTION ){
                    File selectedFile = myFileChooser.getSelectedFile();
                    try {
                        myPath = selectedFile.getCanonicalPath();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    try {
                        ioStream.exportOut(myApp[0],myPath);
                        operationDone = new JOptionPane();
                        operationDone.showMessageDialog(myMenu,"File is exported!");
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });

        /** It is importOption listener which will do import operation.
         *  First, I am closing all panels then doing file importing operations.
         *  Basically, with using JFileChooser open method I am getting the datapath where user wants to get file.
         *  Then, send that datapath and RestManApp object to IOStream's import method. We are also making control = 1 here.
         *  With this way, user won't be able to use fill with predefined objects option.
         */
        importOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeAllPanels();
                JFileChooser myFileChooser = new JFileChooser();
                JButton open  = new JButton();
                String myPath = new String();
                if(myFileChooser.showOpenDialog(open) == JFileChooser.APPROVE_OPTION ){
                    myPath = myFileChooser.getSelectedFile().getAbsolutePath();
                }
                IOStream ioStream = new IOStream();
                try {
                    myApp[0] = ioStream.importIn(myPath);
                    operationDone = new JOptionPane();
                    operationDone.showMessageDialog(myMenu,"File is imported!");
                    control[0] = 1;
                } catch (IOException a) {
                    a.printStackTrace();
                } catch (ClassNotFoundException a) {
                    a.printStackTrace();
                };
            }
        });

        /** It is populateData listener which will do populateData operation.
         *  Here, we are basically fill our app with 3 predefined object for once. After filling it,
         *  we will make control = 1 and user won't be able to use this again.
         */
        populateData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(control[0] == 0 ) {
                    PopulateData myPopulateData = new PopulateData();
                    myPopulateData.fillCustomerAndStaff(myApp[0]);
                    closeAllPanels();
                    control[0] = 1;
                }
                else{
                    operationDone = new JOptionPane();
                    operationDone.showMessageDialog(myMenu,"You can use this option only once or before import!","Error", ERROR_MESSAGE);
                    closeAllPanels();
                }
            }
        });

        /** It is export data to database.
         */
        exportData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DataStorage myConnection = new DataStorage();
                try {
                    myConnection.writeData(myApp[0]);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

        });

        /** It is import data from database. Also after importing customer information it is comparing MD5 of outfile.
         *  Method return an integer and according to that integer, we are giving the result if security is broken or not.
         */
        importData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeAllPanels();
                DataStorage myConnection = new DataStorage();

                try {
                    int returnValue;
                    returnValue = myConnection.readData(myApp[0]);
                    if(returnValue != myApp[0].getCustomers().size()){
                        operationDone = new JOptionPane();
                        operationDone.showMessageDialog(myMenu,"Your security was broken!");
                    }
                    else{
                        operationDone = new JOptionPane();
                        operationDone.showMessageDialog(myMenu,"Everything is Secure!");
                    }
                } catch (SQLException | NoSuchAlgorithmException | IOException throwables) {
                    throwables.printStackTrace();
                }
            }

        });

        /** It is export customer to file. While exporting customer to file, also we are serializing objects and creating MD5 for each of them and storing that MD5's
         * an external MD5.txt file.
         */
        exportCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeAllPanels();
                JFileChooser myFileChooser = new JFileChooser();
                JButton save  = new JButton();
                IOStream ioStream = new IOStream();
                String myPath = new String();

                if(myFileChooser.showSaveDialog(save) == JFileChooser.APPROVE_OPTION ){
                    File selectedFile = myFileChooser.getSelectedFile();
                    try {
                        myPath = selectedFile.getCanonicalPath();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    try {
                        ioStream.customerExport(myApp[0],myPath);
                        operationDone = new JOptionPane();
                        operationDone.showMessageDialog(myMenu,"File is exported!");
                    } catch (IOException | NoSuchAlgorithmException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });

        /** Import customer from external file.
         */
        importCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeAllPanels();
                JFileChooser myFileChooser = new JFileChooser();
                JButton open  = new JButton();
                String myPath = new String();
                if(myFileChooser.showOpenDialog(open) == JFileChooser.APPROVE_OPTION ){
                    myPath = myFileChooser.getSelectedFile().getAbsolutePath();
                }
                IOStream ioStream = new IOStream();
                try {
                    myApp[0] = ioStream.customerImport(myApp[0], myPath);
                    operationDone = new JOptionPane();
                    operationDone.showMessageDialog(myMenu,"File is imported!");
                    control[0] = 1;
                } catch (IOException a) {
                    a.printStackTrace();
                } catch (ClassNotFoundException a) {
                    a.printStackTrace();
                };
            }
        });

        /** It is very basic exit option. If user will select close application option from toolbar instead of closing JFrame,
         * We will finish terminate project here.
         */
        exitOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeAllPanels();
                operationDone = new JOptionPane();
                operationDone.showMessageDialog(myMenu,"Thanks for using our application!");
                myApp[0].exit();
            }
        });

    }

    /** It is main menu panel which will shown at the beginning for once with an information text.
     *
     */
    public void mainMenuPanel(){

        /*Initializing panel*/
        mainMenuPanel = new JPanel();
        mainMenuPanel.setSize(500,500);
        mainMenuPanel.setLayout(null);
        myMenu.add(mainMenuPanel);
        mainMenuPanel.setLocation(0,0);

        /*Creating information text*/
        String openingString = "Welcome to Restaurant Management Application!\nYou can use toolbars for selecting your operations!\nYou can export" +
                " your data before you are exiting and\nyou can import your data again!\nAlso, you can fill app with predefined data instead of import!";
        JTextArea openingText = new JTextArea(openingString);
        openingText.setEditable(false);
        openingText.setOpaque(false);
        openingText.setSize(350,150);
        openingText.setLocation(50,50);

        /*It's just for fun, I am showing a version.*/
        JLabel version = new JLabel("version 0.132");
        version.setSize(80,20);
        version.setLocation(380,400);

        mainMenuPanel.add(openingText);
        mainMenuPanel.add(version);
        mainMenuPanel.updateUI();
        mainMenuPanel.setVisible(true);
    }

    /** It is addStaff method where we will add a new staff.
     * @param myApp - taking RestManApp object to change it.
     */
    public void addStaff(RestManApp myApp){

        /*Reinitializing panel to reflesh it every time*/
        addStaffPanel = new JPanel();
        addStaffPanel.setSize(500,500);
        addStaffPanel.setLayout(null);
        myMenu.add(addStaffPanel);
        addStaffPanel.setLocation(0,0);

        /*Creating labels, radiobuttons, textfields etc.*/
        JLabel addName = new JLabel("Name:");
        addName.setSize(100,20);
        addName.setLocation(30,10);
        addStaffPanel.add(addName);

        JLabel addID = new JLabel("ID:");
        addID.setSize(100,20);
        addID.setLocation(30,40);
        addStaffPanel.add(addID);

        JLabel addGender = new JLabel("Gender:");
        addGender.setSize(100,20);
        addGender.setLocation(30,70);
        addStaffPanel.add(addGender);

        JLabel addDoB = new JLabel("Date of Birth:");
        addDoB.setSize(100,20);
        addDoB.setLocation(30,100);
        addStaffPanel.add(addDoB);

        JLabel addStartDate = new JLabel("Start Date:");
        addStartDate.setSize(100,20);
        addStartDate.setLocation(30,130);
        addStaffPanel.add(addStartDate);

        JLabel addExpectedEndDate = new JLabel("Expected End Date");
        addExpectedEndDate.setSize(120,20);
        addExpectedEndDate.setLocation(60,190);
        addStaffPanel.add(addExpectedEndDate);

        JLabel addMonthlySalary = new JLabel("Monthly Salary");
        addMonthlySalary.setSize(100,20);
        addMonthlySalary.setLocation(60,220);
        addStaffPanel.add(addMonthlySalary);

        JRadioButton juniorButton = new JRadioButton("Junior");
        juniorButton.setSize(100,20);
        juniorButton.setLocation(30,160);
        addStaffPanel.add(juniorButton);

        JLabel addGrossYearlySalary = new JLabel("Gross Yearly Salary");
        addGrossYearlySalary.setSize(120,20);
        addGrossYearlySalary.setLocation(60,280);
        addStaffPanel.add(addGrossYearlySalary);

        JRadioButton seniorButton = new JRadioButton("Senior");
        seniorButton.setSize(100,20);
        seniorButton.setLocation(30,250);
        addStaffPanel.add(seniorButton);

        JButton addStaffButton = new JButton("Add Staff");
        addStaffButton.setSize(100,30);
        addStaffButton.setLocation(30,350);
        addStaffPanel.add(addStaffButton);

        JTextField inputAddName = new JTextField();
        inputAddName.setSize(100,20);
        inputAddName.setLocation(200,10);
        addStaffPanel.add(inputAddName);

        JTextField inputAddID = new JTextField();
        inputAddID.setSize(100,20);
        inputAddID.setLocation(200,40);
        addStaffPanel.add(inputAddID);

        String inputStringAddGender[] = {"M","F"};
        JComboBox inputAddGender = new JComboBox(inputStringAddGender);
        inputAddGender.setSize(100,20);
        inputAddGender.setLocation(200,70);
        addStaffPanel.add(inputAddGender);
        /* I just add this years for visualize my aim. There can be more years.*/
        String day[] = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
        String year[] = {"1990","1991","1992","1993","1994","1995","1996","1997","1998","1999","2000","2001","2002","2003","2004","2005","2006","2007","2008",
                "2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020"};
        String month[] = {"1","2","3","4","5","6","7","8","9","10","11","12"};

        JComboBox inputAddDoBDay = new JComboBox(day);
        inputAddDoBDay.setSize(35,20);
        JComboBox inputAddDoBYear = new JComboBox(year);
        inputAddDoBYear.setSize(60,20);
        JComboBox inputAddDoBMonth = new JComboBox(month);
        inputAddDoBMonth.setSize(40,20);

        inputAddDoBDay.setLocation(200,100);
        inputAddDoBMonth.setLocation(240,100);
        inputAddDoBYear.setLocation(285,100);
        addStaffPanel.add(inputAddDoBDay);
        addStaffPanel.add(inputAddDoBMonth);
        addStaffPanel.add(inputAddDoBYear);

        JComboBox inputAddStartDateDay = new JComboBox(day);
        inputAddStartDateDay.setSize(35,20);
        JComboBox inputAddStartDateYear = new JComboBox(year);
        inputAddStartDateYear.setSize(60,20);
        JComboBox inputAddStartDateMonth = new JComboBox(month);
        inputAddStartDateMonth.setSize(40,20);

        inputAddStartDateDay.setLocation(200,130);
        inputAddStartDateMonth.setLocation(240,130);
        inputAddStartDateYear.setLocation(285,130);
        addStaffPanel.add(inputAddStartDateDay);
        addStaffPanel.add(inputAddStartDateMonth);
        addStaffPanel.add(inputAddStartDateYear);

        year = new String[]{"2020", "2021", "2022", "2023"};

        JComboBox inputAddExpectedEndDateDay = new JComboBox(day);
        inputAddExpectedEndDateDay.setSize(35,20);
        JComboBox inputAddExpectedEndDateYear = new JComboBox(year);
        inputAddExpectedEndDateYear.setSize(60,20);
        JComboBox inputAddExpectedEndDateMonth = new JComboBox(month);
        inputAddExpectedEndDateMonth.setSize(40,20);

        inputAddExpectedEndDateDay.setLocation(200,190);
        inputAddExpectedEndDateMonth.setLocation(240,190);
        inputAddExpectedEndDateYear.setLocation(285,190);
        addStaffPanel.add(inputAddExpectedEndDateDay);
        addStaffPanel.add(inputAddExpectedEndDateMonth);
        addStaffPanel.add(inputAddExpectedEndDateYear);

        JTextField inputAddMonthlySalary = new JTextField();
        inputAddMonthlySalary.setSize(100,20);
        inputAddMonthlySalary.setLocation(200,220);
        addStaffPanel.add(inputAddMonthlySalary);

        JTextField inputAddGrossYearlySalary = new JTextField();
        inputAddGrossYearlySalary.setSize(100,20);
        inputAddGrossYearlySalary.setLocation(200,280);
        addStaffPanel.add(inputAddGrossYearlySalary);


        /* Instead of using isSelected() and facing with updating panel problems. I use action listener for radioButtons and control them with
        brut force.
         */
        final boolean[] junior = new boolean[1];
        final boolean[] senior = new boolean[1];
        junior[0]=false;
        senior[0]=false;

        final int[] count = {0};

        /**Everytime user clicked the button, I am increasing count and according to its value mod 2 I am controlling if it is selected or not.
         */
        juniorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(count[0] %2 == 0){
                    seniorButton.setEnabled(false);
                    addGrossYearlySalary.setEnabled(false);
                    inputAddGrossYearlySalary.setEnabled(false);
                    count[0]++;
                    junior[0] = true;
                }
                else{
                    seniorButton.setEnabled(true);
                    addGrossYearlySalary.setEnabled(true);
                    inputAddGrossYearlySalary.setEnabled(true);
                    junior[0] = false;
                    count[0]++;
                }
            }
        });

        final int[] count2 = {0};
        /**Everytime user clicked the button, I am increasing count and according to its value mod 2 I am controlling if it is selected or not.
         */
        seniorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(count2[0] %2 == 0){
                    juniorButton.setEnabled(false);
                    addExpectedEndDate.setEnabled(false);
                    addMonthlySalary.setEnabled(false);
                    inputAddExpectedEndDateYear.setEnabled(false);
                    inputAddExpectedEndDateDay.setEnabled(false);
                    inputAddExpectedEndDateMonth.setEnabled(false);
                    inputAddMonthlySalary.setEnabled(false);
                    senior[0] = true;
                    count2[0]++;
                }
                else{
                    juniorButton.setEnabled(true);
                    addExpectedEndDate.setEnabled(true);
                    addMonthlySalary.setEnabled(true);
                    inputAddExpectedEndDateYear.setEnabled(true);
                    inputAddExpectedEndDateDay.setEnabled(true);
                    inputAddExpectedEndDateMonth.setEnabled(true);
                    inputAddMonthlySalary.setEnabled(true);
                    senior[0] = false;
                    count2[0]++;
                }
            }
        });

        /** It is addStaffButton action listener. I will collect inputs and add a staff.
         * After adding staff. I will close panel.
         */
        addStaffButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                /*Getting a Date variable using comboBox.*/
                String day = String.valueOf(inputAddDoBDay.getItemAt(inputAddDoBDay.getSelectedIndex()));
                String month =  String.valueOf(inputAddDoBMonth.getItemAt(inputAddDoBMonth.getSelectedIndex()));
                String year =  String.valueOf(inputAddDoBYear.getItemAt(inputAddDoBYear.getSelectedIndex()));
                Date doB = comboBoxtoDate(day,month,year);
                /*Getting a Date variable using comboBox.*/
                day = String.valueOf(inputAddStartDateDay.getItemAt(inputAddStartDateDay.getSelectedIndex()));
                month =  String.valueOf(inputAddStartDateMonth.getItemAt(inputAddStartDateMonth.getSelectedIndex()));
                year =  String.valueOf(inputAddStartDateYear.getItemAt(inputAddStartDateYear.getSelectedIndex()));
                Date startDate = comboBoxtoDate(day,month,year);
                /*Getting a Date variable using comboBox.*/
                day = String.valueOf(inputAddExpectedEndDateDay.getItemAt(inputAddExpectedEndDateDay.getSelectedIndex()));
                month =  String.valueOf(inputAddExpectedEndDateMonth.getItemAt(inputAddExpectedEndDateMonth.getSelectedIndex()));
                year =  String.valueOf(inputAddExpectedEndDateYear.getItemAt(inputAddExpectedEndDateYear.getSelectedIndex()));
                Date expectedEndDate = comboBoxtoDate(day,month,year);

                String iGender = String.valueOf(inputAddGender.getItemAt(inputAddGender.getSelectedIndex()));

                int returnValue=-1;

                /* If user select junior, I am calling my addStaff method according to that selection and telling it will be junior to method */
                if(junior[0] == true){
                    returnValue = myApp.addStaff(myApp,1,inputAddName.getText(),iGender.charAt(0),doB,startDate,
                            Integer.parseInt(inputAddID.getText()),0,expectedEndDate,Integer.parseInt(inputAddMonthlySalary.getText()),0);
                }
                /* If user select senior, I am calling my addStaff method according to that selection and telling it will be senior to method */
                if(senior[0] == true){
                    returnValue = myApp.addStaff(myApp,0,inputAddName.getText(),iGender.charAt(0),doB,startDate,
                            Integer.parseInt(inputAddID.getText()),Integer.parseInt(inputAddGrossYearlySalary.getText()),expectedEndDate,0,0);
                }
                /*As before, I am again checking if ID is unique or not. There will be 5 different option.
                For every option, I am returning an integer and according to that integer, I am giving error's or options to user.
                 */
                if(returnValue == 1){
                    operationDone = new JOptionPane();
                    operationDone.showMessageDialog(myMenu,"This ID is already added with same name!","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if(returnValue == 2){
                    operationDone = new JOptionPane();
                    operationDone.showMessageDialog(myMenu,"This added number is used by another staff!","Error", JOptionPane.ERROR_MESSAGE);
                }
                /* Here, same person can be in customer list and still can be add to staffs. I am checking that if it is the case,
                I am giving option to user if user still wants to add it or not.
                 */
                else if(returnValue == 3){
                    int n = JOptionPane.showConfirmDialog(null,"This ID already added to customer with same name! Do you want to add to staff?","Warning", YES_NO_OPTION);
                    if(n == JOptionPane.YES_OPTION){
                        if(junior[0] == true){
                            myApp.addStaff(myApp,1,inputAddName.getText(),iGender.charAt(0),doB,startDate,
                                    Integer.parseInt(inputAddID.getText()),0,expectedEndDate,Integer.parseInt(inputAddMonthlySalary.getText()),1);
                        }
                        if(senior[0] == true){
                            myApp.addStaff(myApp,0,inputAddName.getText(),iGender.charAt(0),doB,startDate,
                                    Integer.parseInt(inputAddID.getText()),Integer.parseInt(inputAddGrossYearlySalary.getText()),expectedEndDate,0,1);
                        }
                        operationDone = new JOptionPane();
                        operationDone.showMessageDialog(myMenu,"Staff is added!");
                        addStaffPanel.setVisible(false);
                    }
                    else{
                        operationDone = new JOptionPane();
                        operationDone.showMessageDialog(myMenu,"Staff is not added to list!");
                    }
                }
                else if(returnValue == 4){
                    operationDone = new JOptionPane();
                    operationDone.showMessageDialog(myMenu,"This ID number is used by a customer with different name!");
                }
                else if(returnValue == 5){
                    operationDone = new JOptionPane();
                    operationDone.showMessageDialog(myMenu,"Staff is added!");
                    addStaffPanel.setVisible(false);
                }
                else{}
                /*It is giving error message to user if user doesn't select any type.*/
                if(junior[0] == false && senior[0] == false){
                    operationDone = new JOptionPane();
                    operationDone.showMessageDialog(myMenu,"You should select staff type!","Error", ERROR_MESSAGE);
                }

            }
        });
        addStaffPanel.setVisible(true);
        addStaffPanel.updateUI();
    }

    /** It is deleteStaff method where we will delete a staff.
     * @param myApp - taking RestManApp object to change it.
     */
    public void deleteStaff(RestManApp myApp){

        /* Initializing panel.*/
        deleteStaffPanel = new JPanel();
        deleteStaffPanel.setSize(500,500);
        deleteStaffPanel.setLayout(null);
        myMenu.add(deleteStaffPanel);
        deleteStaffPanel.setLocation(0,0);

        /*ComboBox to show staffs with its name. I will use same way for all comboBox for objects*/
        JComboBox staffJComboBox = new JComboBox(new DefaultComboBoxModel(myApp.getStaffs().toArray()));
        staffJComboBox.setRenderer(new DefaultListCellRenderer() {
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus){
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if(value instanceof Staff){
                    Staff staff = (Staff) value;
                    setText(staff.getName());
                }
                return this;
            }
        });

        staffJComboBox.setSize(150,40);
        staffJComboBox.setLocation(175,50);
        deleteStaffPanel.add(staffJComboBox);


        JLabel selectStaffLabel = new JLabel("Select a Staff");
        selectStaffLabel.setSize(100,40);
        selectStaffLabel.setLocation(50,50);
        deleteStaffPanel.add(selectStaffLabel);

        JButton deleteStaffButton = new JButton("Delete Staff");
        deleteStaffButton.setSize(100,40);
        deleteStaffButton.setLocation(200,350);
        deleteStaffPanel.add(deleteStaffButton);

        JButton getStaffInfoButton = new JButton("Get Staff Information");
        getStaffInfoButton.setLocation(150,100);
        getStaffInfoButton.setSize(200,40);
        deleteStaffPanel.add(getStaffInfoButton);

        /** It is action listener to show staff details if user want to see them.
         */
        getStaffInfoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Staff myStaff = (Staff) staffJComboBox.getItemAt(staffJComboBox.getSelectedIndex());
                String[][] stringStaffInfo = new String[][]{};
                /*Adding information to JTable according to its type. I will get substring of util.java.date to show only month, day, year*/
                if(myStaff instanceof Junior){
                    stringStaffInfo = new String[][]{{"Staff ID", String.valueOf(myStaff.getID())},
                            {"Staff Gender", String.valueOf(myStaff.getGender())},
                            {"Staff DoB", String.valueOf(myStaff.getDateOfBirth()).substring(4,10)+String.valueOf(myStaff.getDateOfBirth()).substring(23,28)},
                            {"Staff Start Date", String.valueOf(myStaff.getStartDate()).substring(4,10)+String.valueOf(myStaff.getStartDate()).substring(23,28)},
                            {"Monthly Salary", String.valueOf(((Junior) myStaff).getMonthlySalary())},
                            {"Expected End Date", String.valueOf(((Junior) myStaff).getExpectedEndDate()).substring(4,10)+String.valueOf(((Junior) myStaff).getExpectedEndDate()).substring(23,28)}};


                }
                if(myStaff instanceof Senior){
                    stringStaffInfo = new String[][]{{"Staff ID", String.valueOf(myStaff.getID())},
                            {"Staff Gender", String.valueOf(myStaff.getGender())},
                            {"Staff DoB", String.valueOf(myStaff.getDateOfBirth())},
                            {"Staff Start Date", String.valueOf(myStaff.getStartDate()).substring(4,10)+String.valueOf(myStaff.getStartDate()).substring(23,28)},
                            {"Gross Yearly Salary", String.valueOf(((Senior) myStaff).getGrossSalaryYearly())}};


                }
                String Column[] = {"Category","Info"};
                JTable staffInfo = new JTable(stringStaffInfo,Column);

                staffInfo.setBounds(0,0,400,400);

                JScrollPane scrollPane = new JScrollPane(staffInfo);
                scrollPane.setPreferredSize(new Dimension(400,100));
                scrollPane.setSize(400,150);
                scrollPane.setLocation(50,160);


                deleteStaffPanel.add(scrollPane);
            }
        });
                /* It is action listener to deleteStaffButton. After deleting staff, I will close the panel.
                * To use my previous assignment method. I will get staff from comboBox and send its ID to method.
                */
                deleteStaffButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Staff myStaff = (Staff) staffJComboBox.getItemAt(staffJComboBox.getSelectedIndex());
                myApp.deleteStaff(myStaff.getID(),myApp);
                operationDone = new JOptionPane();
                operationDone.showMessageDialog(myMenu,"Staff is deleted!");
                deleteStaffPanel.setVisible(false);
            }
        });

        deleteStaffPanel.setVisible(true);
        deleteStaffPanel.updateUI();

    }

    /** It is listStaffs method where we will list staffs.
     * @param myApp - taking RestManApp object to change it.
     */
    public void listStaffs(RestManApp myApp){

        /*Initializing panel*/
        listStaffPanel = new JPanel();
        listStaffPanel.setSize(500,500);
        listStaffPanel.setLayout(null);
        myMenu.add(listStaffPanel);
        listStaffPanel.setLocation(0,0);

        /* I am counting how many junior I have to create JTable strings according to that.*/
        int juniorCount = 0;
        for(int i = 0; i < myApp.getStaffs().size(); i++){
            if(myApp.getStaffs().get(i) instanceof Junior){
                juniorCount++;
            }
        }
        /*Iniatlize strings according to my count.*/
        String[][] stringStaffInfo = new String[juniorCount][7];
        String[][] stringSeniorStaffInfo = new String[myApp.getStaffs().size()-juniorCount][7];
        String juniorColumn[] = {"ID","Name","Gender","DoB","Start Date","Expected End Date","Monthly Salary"};
        String seniorColumn[] = {"ID","Name","Gender","DoB","Start Date","Gross Salary Yearly","Responsible From"};
        int count1 = 0;
        int count2 = 0;
        for(int i = 0; i < myApp.getStaffs().size(); i++){
            /*Filling strings according to its type. I will get substring of util.java.date to show only month, day, year*/
            if(myApp.getStaffs().get(i) instanceof Junior){
                stringStaffInfo[count1][0] = String.valueOf(myApp.getStaffs().get(i).getID());
                stringStaffInfo[count1][1] = String.valueOf(myApp.getStaffs().get(i).getName());
                stringStaffInfo[count1][2] = String.valueOf(myApp.getStaffs().get(i).getGender());
                stringStaffInfo[count1][3] = String.valueOf(myApp.getStaffs().get(i).getDateOfBirth()).substring(4,10)+
                        String.valueOf(myApp.getStaffs().get(i).getDateOfBirth()).substring(23,28);
                stringStaffInfo[count1][4] = String.valueOf(myApp.getStaffs().get(i).getStartDate()).substring(4,10)+
                        String.valueOf(myApp.getStaffs().get(i).getStartDate()).substring(23,28);
                stringStaffInfo[count1][5] = String.valueOf(((Junior) myApp.getStaffs().get(i)).getExpectedEndDate()).substring(4,10)+
                        String.valueOf(((Junior) myApp.getStaffs().get(i)).getExpectedEndDate()).substring(23,28);
                stringStaffInfo[count1][6] = String.valueOf(((Junior) myApp.getStaffs().get(i)).getMonthlySalary());
                count1++;
            }
            if(myApp.getStaffs().get(i) instanceof Senior){
                String responsibleString = new String();
                for(int j = 0; j < ((Senior) myApp.getStaffs().get(i)).getResponsibleFrom().size(); j++){
                    responsibleString += " " + ((Senior) myApp.getStaffs().get(i)).getResponsibleFrom().get(j).getName();
                }
                stringSeniorStaffInfo[count2][0] = String.valueOf(myApp.getStaffs().get(i).getID());
                stringSeniorStaffInfo[count2][1] = String.valueOf(myApp.getStaffs().get(i).getName());
                stringSeniorStaffInfo[count2][2] = String.valueOf(myApp.getStaffs().get(i).getGender());
                stringSeniorStaffInfo[count2][3] = String.valueOf(myApp.getStaffs().get(i).getDateOfBirth()).substring(4,10)+
                        String.valueOf(myApp.getStaffs().get(i).getDateOfBirth()).substring(23,28);
                stringSeniorStaffInfo[count2][4] = String.valueOf(myApp.getStaffs().get(i).getStartDate()).substring(4,10)+
                        String.valueOf(myApp.getStaffs().get(i).getStartDate()).substring(23,28);
                stringSeniorStaffInfo[count2][5] = String.valueOf(((Senior) myApp.getStaffs().get(i)).getGrossSalaryYearly());
                stringSeniorStaffInfo[count2][6] = responsibleString;
                count2++;
            }

        }

        /*I am handling column size with my hand according to their string size to show efficently.*/
        JTable juniorStaffInfo = new JTable(stringStaffInfo,juniorColumn);
        juniorStaffInfo.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        juniorStaffInfo.getColumnModel().getColumn(0).setPreferredWidth(40);
        juniorStaffInfo.getColumnModel().getColumn(1).setPreferredWidth(80);
        juniorStaffInfo.getColumnModel().getColumn(2).setPreferredWidth(50);
        juniorStaffInfo.getColumnModel().getColumn(3).setPreferredWidth(77);
        juniorStaffInfo.getColumnModel().getColumn(4).setPreferredWidth(77);
        juniorStaffInfo.getColumnModel().getColumn(5).setPreferredWidth(77);
        juniorStaffInfo.getColumnModel().getColumn(6).setPreferredWidth(46);

        juniorStaffInfo.setBounds(300,200,200,200);
        JScrollPane scrollPane = new JScrollPane(juniorStaffInfo);
        scrollPane.setLocation(20,50);
        scrollPane.setSize(450,150);
        listStaffPanel.add(scrollPane);

        JTable seniorStaffInfo = new JTable(stringSeniorStaffInfo,seniorColumn);

        seniorStaffInfo.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        seniorStaffInfo.getColumnModel().getColumn(0).setPreferredWidth(40);
        seniorStaffInfo.getColumnModel().getColumn(1).setPreferredWidth(79);
        seniorStaffInfo.getColumnModel().getColumn(2).setPreferredWidth(50);
        seniorStaffInfo.getColumnModel().getColumn(3).setPreferredWidth(77);
        seniorStaffInfo.getColumnModel().getColumn(4).setPreferredWidth(77);
        seniorStaffInfo.getColumnModel().getColumn(5).setPreferredWidth(77);
        seniorStaffInfo.getColumnModel().getColumn(6).setPreferredWidth(47);

        seniorStaffInfo.setBounds(200,200,200,200);
        JScrollPane scrollPane1 = new JScrollPane(seniorStaffInfo);
        scrollPane1.setLocation(20,250);
        scrollPane1.setSize(450,150);
        listStaffPanel.add(scrollPane1);

        JLabel seniorStaffLabel = new JLabel("Senior Staff List");
        seniorStaffLabel.setSize(100,30);
        seniorStaffLabel.setLocation(200,200);
        listStaffPanel.add(seniorStaffLabel);

        JLabel juniorStaffLabel = new JLabel("Junior Staff List");
        juniorStaffLabel.setSize(100,30);
        juniorStaffLabel.setLocation(200,10);
        listStaffPanel.add(juniorStaffLabel);

        listStaffPanel.setVisible(true);

    }

    /** It is searchStaff method where we will search staff.
     * @param myApp - taking RestManApp object to change it.
     */
    public void searchStaff(RestManApp myApp){

        /*Initializing panel*/
        searchStaffPanel = new JPanel();
        searchStaffPanel.setSize(500,500);
        searchStaffPanel.setLayout(null);
        myMenu.add(searchStaffPanel);
        searchStaffPanel.setLocation(0,0);

        /*Adding components to panel.*/
        JLabel inputIDLabel = new JLabel("Enter ID");
        inputIDLabel.setSize(100,30);
        inputIDLabel.setLocation(80,50);
        searchStaffPanel.add(inputIDLabel);

        JTextField inputID = new JTextField();
        inputID.setSize(100,30);
        inputID.setLocation(180,50);
        searchStaffPanel.add(inputID);

        JButton searchButton = new JButton("Search");
        searchButton.setSize(100,30);
        searchButton.setLocation(180,100);
        searchStaffPanel.add(searchButton);

        /*After search button clicked, I will get ID user enter and search that ID in the list. Instead of showing all staff in a comboBox and selected it
        * from there, I prefer this method to both create a similar functionality with previous assignment and also assuming there may be a lot of staff.
        */
        searchButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int position = -1;
                position = myApp.listStaffDetails(Integer.parseInt(inputID.getText()),myApp);
                if(position != -1){
                    Staff myStaff = myApp.getStaffs().get(position);
                    String[][] stringStaffInfo = new String[][]{};
                    /*Adding information to JTable according to its type. I will get substring of util.java.date to show only month, day, year*/
                    if(myStaff instanceof Junior){
                        stringStaffInfo = new String[][]{{"Staff ID", String.valueOf(myStaff.getID())},
                                {"Staff Name", myStaff.getName()},
                                {"Staff Gender", String.valueOf(myStaff.getGender())},
                                {"Staff DoB", String.valueOf(myStaff.getDateOfBirth()).substring(4,10)+
                                        String.valueOf(myStaff.getDateOfBirth()).substring(23,28)},
                                {"Staff Start Date", String.valueOf(myStaff.getStartDate()).substring(4,10)+
                                        String.valueOf(myStaff.getStartDate()).substring(23,28)},
                                {"Monthly Salary", String.valueOf(((Junior) myStaff).getMonthlySalary())},
                                {"Expected End Date", String.valueOf(((Junior) myStaff).getExpectedEndDate()).substring(4,10)+
                                        String.valueOf(((Junior) myStaff).getExpectedEndDate()).substring(23,28)}};


                    }
                    if(myStaff instanceof Senior){
                        stringStaffInfo = new String[][]{{"Staff ID", String.valueOf(myStaff.getID())},
                                {"Staff Name", myStaff.getName()},
                                {"Staff Gender", String.valueOf(myStaff.getGender())},
                                {"Staff DoB", String.valueOf(myStaff.getDateOfBirth()).substring(4,10)+
                                        String.valueOf(myStaff.getDateOfBirth()).substring(23,28)},
                                {"Staff Start Date", String.valueOf(myStaff.getStartDate()).substring(4,10)+
                                        String.valueOf(myStaff.getStartDate()).substring(23,28)},
                                {"Gross Yearly Salary", String.valueOf(((Senior) myStaff).getGrossSalaryYearly())}};


                    }
                    String Column[] = {"Category","Info"};
                    JTable staffInfo = new JTable(stringStaffInfo,Column);

                    staffInfo.setBounds(200,200,200,200);
                    JScrollPane scrollPane = new JScrollPane(staffInfo);


                    scrollPane.setLocation(20,200);
                    scrollPane.setSize(300,150);
                    /*Checking type, if it is senior I am also filling a JTable to show its responsibleFrom list.*/
                    if(myStaff instanceof Senior){

                        String responsibleString[][] = new String[((Senior) myStaff).getResponsibleFrom().size()][1];
                        for (int i = 0; i < ((Senior) myStaff).getResponsibleFrom().size(); i++){
                            responsibleString[i][0] = ((Senior) myStaff).getResponsibleFrom().get(i).getName();
                        }
                        String Column2[] = {"Name"};
                        JTable responsibleTable = new JTable(responsibleString,Column2);
                        JScrollPane responsiblePane = new JScrollPane(responsibleTable);
                        responsiblePane.setSize(100,150);
                        responsiblePane.setLocation(350,200);
                        searchStaffPanel.add(responsiblePane);
                    }
                    else{
                        JTable emptyTable = new JTable();
                        JScrollPane responsiblePane = new JScrollPane(emptyTable);
                        responsiblePane.setSize(100,150);
                        responsiblePane.setLocation(350,200);
                        searchStaffPanel.add(responsiblePane);
                    }

                    searchStaffPanel.add(scrollPane);
                }
                else{
                    operationDone = new JOptionPane();
                    operationDone.showMessageDialog(myMenu,"Staff is couldn't found! You can use list all staff operation for checking IDs!","Error",ERROR_MESSAGE);
                }

            }
        });

        searchStaffPanel.updateUI();
        searchStaffPanel.setVisible(true);
    }

    /** It is addSCustomer method where we will add a new customer.
     * It's action listener mechanism is very similar to addCustomer.
     * @param myApp - taking RestManApp object to change it.
     */
    public void addCustomer(RestManApp myApp){

        /*Initializing JPanel*/
        addCustomerPanel = new JPanel();
        addCustomerPanel.setSize(500,500);
        addCustomerPanel.setLocation(0,0);
        addCustomerPanel.setLayout(null);
        myMenu.add(addCustomerPanel);

        /*Initializing components.*/
        JLabel addName = new JLabel("Name:");
        addName.setSize(100,20);
        addName.setLocation(30,10);
        JLabel addID = new JLabel("ID:");
        addID.setSize(100,20);
        addID.setLocation(30,40);
        JLabel addGender = new JLabel("Gender:");
        addGender.setSize(100,20);
        addGender.setLocation(30,70);
        JLabel addDoB = new JLabel("Date of Birth:");
        addDoB.setSize(100,20);
        addDoB.setLocation(30,100);
        JLabel addRegistrationDate = new JLabel("Registration Date:");
        addRegistrationDate.setSize(120,20);
        addRegistrationDate.setLocation(30,130);
        JLabel addCreditCardDetails = new JLabel("Credit Card Details:");
        addCreditCardDetails.setSize(130,20);
        addCreditCardDetails.setLocation(30,160);

        JTextField inputAddName = new JTextField();
        inputAddName.setSize(100,20);
        inputAddName.setLocation(200,10);

        JTextField inputAddID = new JTextField();
        inputAddID.setSize(100,20);
        inputAddID.setLocation(200,40);

        JTextField inputAddCreditCardDetails = new JTextField();
        inputAddCreditCardDetails.setSize(100,20);
        inputAddCreditCardDetails.setLocation(200,160);

        String inputStringAddGender[] = {"M","F"};
        JComboBox inputAddGender = new JComboBox(inputStringAddGender);
        inputAddGender.setSize(100,20);
        inputAddGender.setLocation(200,70);

        String day[] = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
        String year[] = {"1990","1991","1992","1993","1994","1995","1996","1997","1998","1999","2000","2001","2002","2003","2004","2005","2006","2007","2008",
                "2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020"};
        String month[] = {"1","2","3","4","5","6","7","8","9","10","11","12"};

        JComboBox inputAddDoBDay = new JComboBox(day);
        inputAddDoBDay.setSize(35,20);
        JComboBox inputAddDoBYear = new JComboBox(year);
        inputAddDoBYear.setSize(60,20);
        JComboBox inputAddDoBMonth = new JComboBox(month);
        inputAddDoBMonth.setSize(40,20);
        inputAddDoBDay.setLocation(200,100);
        inputAddDoBMonth.setLocation(240,100);
        inputAddDoBYear.setLocation(285,100);

        JComboBox inputAddRegistrationDateDay = new JComboBox(day);
        inputAddRegistrationDateDay.setSize(35,20);
        JComboBox inputAddRegistrationDateYear = new JComboBox(year);
        inputAddRegistrationDateYear.setSize(60,20);
        JComboBox inputAddRegistrationDateMonth = new JComboBox(month);
        inputAddRegistrationDateMonth.setSize(40,20);
        inputAddRegistrationDateDay.setLocation(200,130);
        inputAddRegistrationDateMonth.setLocation(240,130);
        inputAddRegistrationDateYear.setLocation(285,130);


        JButton addCustomerButton = new JButton("Add Customer");
        addCustomerButton.setSize(120,20);
        addCustomerButton.setLocation(150,200);


        addCustomerPanel.add(addName);
        addCustomerPanel.add(addID);
        addCustomerPanel.add(addGender);
        addCustomerPanel.add(addDoB);
        addCustomerPanel.add(addRegistrationDate);
        addCustomerPanel.add(addCreditCardDetails);
        addCustomerPanel.add(inputAddName);
        addCustomerPanel.add(inputAddID);
        addCustomerPanel.add(inputAddGender);
        addCustomerPanel.add(inputAddCreditCardDetails);
        addCustomerPanel.add(inputAddDoBDay);
        addCustomerPanel.add(inputAddDoBMonth);
        addCustomerPanel.add(inputAddDoBYear);
        addCustomerPanel.add(inputAddRegistrationDateDay);
        addCustomerPanel.add(inputAddRegistrationDateMonth);
        addCustomerPanel.add(inputAddRegistrationDateYear);
        addCustomerPanel.add(addCustomerButton);

        /**It is action listener of addCustomerButton. I will close panel after adding customer.*/
        addCustomerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                /*Getting a Date variable using comboBox.*/
                String day = String.valueOf(inputAddDoBDay.getItemAt(inputAddDoBDay.getSelectedIndex()));
                String month =  String.valueOf(inputAddDoBMonth.getItemAt(inputAddDoBMonth.getSelectedIndex()));
                String year =  String.valueOf(inputAddDoBYear.getItemAt(inputAddDoBYear.getSelectedIndex()));
                Date iDoB = comboBoxtoDate(day,month,year);
                /*Getting a Date variable using comboBox.*/
                day = String.valueOf(inputAddRegistrationDateDay.getItemAt(inputAddRegistrationDateDay.getSelectedIndex()));
                month =  String.valueOf(inputAddRegistrationDateMonth.getItemAt(inputAddRegistrationDateMonth.getSelectedIndex()));
                year =  String.valueOf(inputAddRegistrationDateYear.getItemAt(inputAddRegistrationDateYear.getSelectedIndex()));
                Date iRegistrationDate = comboBoxtoDate(day,month,year);


                String iGender = String.valueOf(inputAddGender.getItemAt(inputAddGender.getSelectedIndex()));
                int returnValue;
                returnValue = myApp.addCustomer(myApp,inputAddName.getText(),Integer.parseInt(inputAddID.getText().trim()),iGender.charAt(0),iDoB,iRegistrationDate,addCreditCardDetails.getText(),0);
                /* It's error giving mechanism same ass addStaff.*/
                if(returnValue == 1){
                    operationDone = new JOptionPane();
                    operationDone.showMessageDialog(myMenu,"This ID added with same name already!","Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (returnValue == 2){
                    operationDone = new JOptionPane();
                    operationDone.showMessageDialog(myMenu,"This ID added with different name already!","Error", JOptionPane.ERROR_MESSAGE);
                }
                else if (returnValue == 3){
                    int n = JOptionPane.showConfirmDialog(null,"This ID already added to staff with same name! Do you want to add to customers?","Warning", YES_NO_OPTION);
                    if(n == JOptionPane.YES_OPTION){
                        myApp.addCustomer(myApp,inputAddName.getText(),Integer.parseInt(inputAddID.getText().trim()),iGender.charAt(0),iDoB,iRegistrationDate,addCreditCardDetails.getText(),1);
                        operationDone = new JOptionPane();
                        operationDone.showMessageDialog(myMenu,"Customer is added!");
                        addCustomerPanel.setVisible(false);
                    }
                    else{
                        operationDone = new JOptionPane();
                        operationDone.showMessageDialog(myMenu,"Customer is not added to list!");
                    }
                }
                else if (returnValue == 4){
                    operationDone = new JOptionPane();
                    operationDone.showMessageDialog(myMenu,"This ID added Staff list with different name already!","Error", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    operationDone = new JOptionPane();
                    operationDone.showMessageDialog(myMenu,"Customer is added!");
                    addCustomerPanel.setVisible(false);
                }
            }
        });

        addCustomerPanel.updateUI();

    }

    /** It is deleteCustomer method where we will delete a customer.
     * @param myApp - taking RestManApp object to change it.
     */
    public void deleteCustomer(RestManApp myApp){

        /*Initializing panel*/
        deleteCustomerPanel = new JPanel();
        deleteCustomerPanel.setSize(500,500);
        deleteCustomerPanel.setLayout(null);
        myMenu.add(deleteCustomerPanel);
        deleteCustomerPanel.setLocation(0,0);

        /*Initializing components.*/
        JComboBox customerJComboBox = new JComboBox(new DefaultComboBoxModel(myApp.getCustomers().toArray()));
        customerJComboBox.setRenderer(new DefaultListCellRenderer() {
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus){
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if(value instanceof Customer){
                    Customer customer = (Customer) value;
                    setText(customer.getName());
                }
                return this;
            }
        });

        customerJComboBox.setSize(150,40);
        customerJComboBox.setLocation(175,50);
        deleteCustomerPanel.add(customerJComboBox);

        JLabel selectCustomer = new JLabel("Select Customer:");
        selectCustomer.setSize(100,40);
        selectCustomer.setLocation(50,50);
        deleteCustomerPanel.add(selectCustomer);

        JButton deleteCustomerButton = new JButton("Delete Customer");
        deleteCustomerButton.setSize(140,40);
        deleteCustomerButton.setLocation(180,320);
        deleteCustomerPanel.add(deleteCustomerButton);

        JButton getCustomerInfoButton = new JButton("Show Customer Information");
        getCustomerInfoButton.setLocation(150,100);
        getCustomerInfoButton.setSize(200,40);
        deleteCustomerPanel.add(getCustomerInfoButton);

        /*It is action listener of getCustomerInfoButton. If user click it, I will show details of customer.
         */
        getCustomerInfoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Customer myCustomer = (Customer) customerJComboBox.getItemAt(customerJComboBox.getSelectedIndex());
                String[][] stringCustomerInfo = new String[][]{};
                /*Initilaizing table with same way of previous tables. */
                stringCustomerInfo = new String[][]{{"Customer Name",myCustomer.getName()},
                        {"Customer ID", String.valueOf(myCustomer.getID())},
                        {"Customer Gender", String.valueOf(myCustomer.getGender())},
                        {"Customer DoB", String.valueOf(myCustomer.getDateOfBirth()).substring(4,10)+String.valueOf(myCustomer.getDateOfBirth()).substring(23,28)},
                        {"Customer Registration Date", String.valueOf(myCustomer.getRegistrationDate()).substring(4,10)+String.valueOf(myCustomer.getRegistrationDate()).substring(23,28)},
                        {"Customer Credit Card Details", myCustomer.getCreditCardDetails()}};

                String Column[] = {"Category","Info"};
                JTable customerInfo = new JTable(stringCustomerInfo,Column);


                customerInfo.setBounds(200,200,200,200);
                JScrollPane scrollPane = new JScrollPane(customerInfo);

                scrollPane.setLocation(50,150);
                scrollPane.setSize(400,119);

                deleteCustomerPanel.add(scrollPane);
            }
        });
        /*It is deleteCustomerButton action listener. I will close panel after delete customer. I will send id of customer to deleteCustomer method
        to use previous methods.
         */
        deleteCustomerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Customer myCustomer = (Customer) customerJComboBox.getItemAt(customerJComboBox.getSelectedIndex());
                myApp.deleteCustomer(myCustomer.getID(),myApp);
                operationDone = new JOptionPane();
                operationDone.showMessageDialog(myMenu,"Customer is deleted!");
                deleteCustomerPanel.setVisible(false);
            }
        });

        deleteCustomerPanel.updateUI();
        deleteCustomerPanel.setVisible(true);

    }

    /** It is searchCustomer method where we will search customer.
     * @param myApp - taking RestManApp object to change it.
     */
    public void searchCustomer(RestManApp myApp){

        /*Initializing panel*/
        searchCustomerPanel = new JPanel();
        searchCustomerPanel.setSize(500,500);
        searchCustomerPanel.setLayout(null);
        myMenu.add(searchCustomerPanel);
        searchCustomerPanel.setLocation(0,0);

        /*Initializing components*/
        JLabel inputIDLabel = new JLabel("Enter ID");
        inputIDLabel.setSize(100,30);
        inputIDLabel.setLocation(80,50);
        searchCustomerPanel.add(inputIDLabel);

        JTextField inputID = new JTextField();
        inputID.setSize(100,30);
        inputID.setLocation(180,50);
        searchCustomerPanel.add(inputID);

        JButton searchButton = new JButton("Search");
        searchButton.setSize(100,30);
        searchButton.setLocation(180,100);
        searchCustomerPanel.add(searchButton);

        /*After search button clicked, I will get ID user enter and search that ID in the list. Instead of showing all customers in a comboBox and selected it
         * from there, I prefer this method to both create a similar functionality with previous assignment and also assuming there may be a lot of customer.
         */
        searchButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Customer myCustomer = myApp.listCustomerDetails(Integer.parseInt(inputID.getText().trim()),myApp);
                if(myCustomer != null) {
                    String[][] stringCustomerInfo = new String[][]{};
                    stringCustomerInfo = new String[][]{{"Customer Name", myCustomer.getName()},
                            {"Customer ID", String.valueOf(myCustomer.getID())},
                            {"Customer Gender", String.valueOf(myCustomer.getGender())},
                            {"Customer DoB", String.valueOf(myCustomer.getDateOfBirth()).substring(4,10)+String.valueOf(myCustomer.getDateOfBirth()).substring(23,28)},
                            {"Customer Registration Date", String.valueOf(myCustomer.getRegistrationDate()).substring(4,10)+String.valueOf(myCustomer.getRegistrationDate()).substring(23,28)},
                            {"Customer Credit Card Details", myCustomer.getCreditCardDetails()}};

                    String Column[] = {"Category", "Info"};
                    JTable customerInfo = new JTable(stringCustomerInfo, Column);

                    customerInfo.setBounds(200, 200, 200, 200);
                    JScrollPane scrollPane = new JScrollPane(customerInfo);

                    scrollPane.setLocation(40,150);
                    scrollPane.setSize(400,119);

                    searchCustomerPanel.add(scrollPane);
                }
                else{
                    operationDone = new JOptionPane();
                    operationDone.showMessageDialog(myMenu,"Customer is couldn't found! You can use list all customer operation for checking IDs!","Error",ERROR_MESSAGE);
                }
            }
        });

        searchCustomerPanel.setVisible(true);
        searchCustomerPanel.updateUI();
    }

    /** It is listCustomer method where we will list customers.
     * @param myApp - taking RestManApp object to change it.
     */
    public void listCustomers(RestManApp myApp){

        /*Initializing panel.*/
        listCustomerPanel = new JPanel();
        listCustomerPanel.setSize(500,500);
        listCustomerPanel.setLayout(null);
        myMenu.add(listCustomerPanel);
        listCustomerPanel.setLocation(0,0);

        /*Putting customers to JTable.*/
        String Column[] = {"ID","Name","Gender","DoB","Registration Date","Card Details"};
        String[][] stringCustomerInfo = new String[myApp.getCustomers().size()][6];
        for(int i = 0; i < myApp.getCustomers().size(); i++){
            stringCustomerInfo[i][0] = String.valueOf(myApp.getCustomers().get(i).getID());
            stringCustomerInfo[i][1] = String.valueOf(myApp.getCustomers().get(i).getName());
            stringCustomerInfo[i][2] = String.valueOf(myApp.getCustomers().get(i).getGender());
            stringCustomerInfo[i][3] = String.valueOf(myApp.getCustomers().get(i).getDateOfBirth()).substring(4,10)+String.valueOf(myApp.getCustomers().get(i).getDateOfBirth()).substring(23,28);
            stringCustomerInfo[i][4] = String.valueOf(myApp.getCustomers().get(i).getRegistrationDate()).substring(4,10)+String.valueOf(myApp.getCustomers().get(i).getRegistrationDate()).substring(23,28);
            stringCustomerInfo[i][5] = String.valueOf(myApp.getCustomers().get(i).getCreditCardDetails());
        }

        /*Arranging column size with my hand according to string size.*/
        JTable customerInfo = new JTable(stringCustomerInfo,Column);
        customerInfo.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        customerInfo.getColumnModel().getColumn(0).setPreferredWidth(40);
        customerInfo.getColumnModel().getColumn(1).setPreferredWidth(90);
        customerInfo.getColumnModel().getColumn(2).setPreferredWidth(50);
        customerInfo.getColumnModel().getColumn(3).setPreferredWidth(80);
        customerInfo.getColumnModel().getColumn(4).setPreferredWidth(80);
        customerInfo.getColumnModel().getColumn(5).setPreferredWidth(82);

        customerInfo.setBounds(50,50,350,350);

        JScrollPane scrollPane = new JScrollPane(customerInfo);
        scrollPane.setSize(425,300);
        scrollPane.setLocation(30,50);

        JLabel customerLabel = new JLabel("Customer List");
        customerLabel.setSize(100,30);
        customerLabel.setLocation(200,10);

        listCustomerPanel.add(customerLabel);
        listCustomerPanel.add(scrollPane);

        listCustomerPanel.setVisible(true);
        listCustomerPanel.updateUI();
    }

    /** It is addBoking method where we will add booking.
     * @param myApp - taking RestManApp object to change it.
     */
    public void addBooking(RestManApp myApp){

        /*Initialize panel.*/
        addBookingPanel = new JPanel();
        addBookingPanel.setSize(500,500);
        addBookingPanel.setLocation(0,0);
        addBookingPanel.setLayout(null);
        myMenu.add(addBookingPanel);

        JComboBox customerJComboBox = new JComboBox(new DefaultComboBoxModel(myApp.getCustomers().toArray()));
        customerJComboBox.setRenderer(new DefaultListCellRenderer() {
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus){
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if(value instanceof Customer){
                    Customer customer = (Customer) value;
                    setText(customer.getName());
                }
                return this;
            }
        });

        customerJComboBox.setSize(150,40);
        customerJComboBox.setLocation(175,50);
        addBookingPanel.add(customerJComboBox);

        JLabel selectCustomer = new JLabel("Select Customer:");
        selectCustomer.setSize(100,40);
        selectCustomer.setLocation(50,50);

        JLabel selectDate = new JLabel("Booking Date:");
        selectDate.setSize(100,20);
        selectDate.setLocation(50,100);

        String day[] = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
        String year[] = {"2021","2022"};
        String month[] = {"1","2","3","4","5","6","7","8","9","10","11","12"};

        JComboBox inputBookingDateDay = new JComboBox(day);
        inputBookingDateDay.setSize(45,20);
        JComboBox inputBookingDateYear = new JComboBox(year);
        inputBookingDateYear.setSize(60,20);
        JComboBox inputBookingDateMonth = new JComboBox(month);
        inputBookingDateMonth.setSize(45,20);
        inputBookingDateDay.setLocation(175,100);
        inputBookingDateMonth.setLocation(225,100);
        inputBookingDateYear.setLocation(275,100);

        JButton addBookingButton = new JButton("Add Booking");
        addBookingButton.setSize(140,40);
        addBookingButton.setLocation(180,150);

        /*It is action listener of addBookingButton. After adding booking, I will close panel.*/
        addBookingButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Customer myCustomer =(Customer) customerJComboBox.getItemAt(customerJComboBox.getSelectedIndex());
                /*Create date object using comboBoxs.*/
                String day = String.valueOf(inputBookingDateDay.getItemAt(inputBookingDateDay.getSelectedIndex()));
                String month =  String.valueOf(inputBookingDateMonth.getItemAt(inputBookingDateMonth.getSelectedIndex()));
                String year =  String.valueOf(inputBookingDateYear.getItemAt(inputBookingDateYear.getSelectedIndex()));
                Date iBookingDate = comboBoxtoDate(day,month,year);
                myApp.addBooking(myCustomer.getID(),myApp,iBookingDate);

                operationDone = new JOptionPane();
                operationDone.showMessageDialog(myMenu,"Booking is added!");

                addBookingPanel.updateUI();
                addBookingPanel.setVisible(false);

            }
        });


        addBookingPanel.add(selectCustomer);
        addBookingPanel.add(inputBookingDateDay);
        addBookingPanel.add(inputBookingDateMonth);
        addBookingPanel.add(inputBookingDateYear);
        addBookingPanel.add(selectDate);
        addBookingPanel.add(addBookingButton);

        addBookingPanel.setVisible(true);
        addBookingPanel.updateUI();
    }

    /** It is customerLastBooking method where we will get selected customer last booking.
     * @param myApp - taking RestManApp object to change it.
     */
    public void customerLastBooking(RestManApp myApp){

        /*Initializing panel.*/
        customerLastBookingPanel = new JPanel();
        customerLastBookingPanel.setSize(500,500);
        customerLastBookingPanel.setLayout(null);
        myMenu.add(customerLastBookingPanel);
        customerLastBookingPanel.setLocation(0,0);
        /*Putting customers to comboBox.*/
        JComboBox customerJComboBox = new JComboBox(new DefaultComboBoxModel(myApp.getCustomers().toArray()));
        customerJComboBox.setRenderer(new DefaultListCellRenderer() {
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus){
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if(value instanceof Customer){
                    Customer customer = (Customer) value;
                    setText(customer.getName());
                }
                return this;
            }
        });

        customerJComboBox.setSize(150,40);
        customerJComboBox.setLocation(175,50);
        customerLastBookingPanel.add(customerJComboBox);

        JLabel selectCustomer = new JLabel("Select Customer:");
        selectCustomer.setSize(100,40);
        selectCustomer.setLocation(50,50);
        customerLastBookingPanel.add(selectCustomer);

        JButton getBookingButton = new JButton("Get Last Booking");
        getBookingButton.setSize(150,30);
        getBookingButton.setLocation(175,100);
        customerLastBookingPanel.add(getBookingButton);
        /* After get booking button clicked, I will put information of booking to JTable and show it.*/
        getBookingButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Customer myCustomer = (Customer) customerJComboBox.getItemAt(customerJComboBox.getSelectedIndex());
                /*If there is also an order of that booking, I am showing it too.*/
                String[][] stringBookingInfo;
                if(myCustomer.getBookings().size() > 0){
                    Booking myBooking = myCustomer.getBookings().get(myCustomer.getBookings().size()-1);
                    Order myOrder = myBooking.getBookingOrder();
                    if(myOrder != null) {
                        stringBookingInfo = new String[][]{{"Booking ID", String.valueOf(myBooking.getBookingID())},
                                {"Booking Date", String.valueOf(myBooking.getBookingDate()).substring(4,10)+String.valueOf(myBooking.getBookingDate()).substring(23,28)},
                                {"Booking Order ID", String.valueOf(myOrder.getOrderID())},
                                {"Order Date", String.valueOf(myOrder.getOrderDate()).substring(4,10)+String.valueOf(myOrder.getOrderDate()).substring(23,28)},
                                {"Order Extra Notes", String.valueOf(myOrder.getExtraNotes())},
                                {"Order Details", myOrder.getOrderDetails()}};
                    }
                    else{
                        stringBookingInfo = new String[][]{{"Booking ID", String.valueOf(myBooking.getBookingID())},
                                {"Booking Date", String.valueOf(myBooking.getBookingDate()).substring(4,10)+String.valueOf(myBooking.getBookingDate()).substring(23,28)}};
                    }

                    String Column[] = {"Category","Info"};
                    JTable bookingInfo = new JTable(stringBookingInfo,Column);

                    bookingInfo.setBounds(0,0,400,150);
                    JScrollPane scrollPane = new JScrollPane(bookingInfo);

                    scrollPane.setLocation(40,150);
                    scrollPane.setSize(400,150);

                    customerLastBookingPanel.add(scrollPane);
                }
                else{
                    operationDone = new JOptionPane();
                    operationDone.showMessageDialog(myMenu,"This customer has no booking!","Error", ERROR_MESSAGE);
                }
            }
        });

        customerLastBookingPanel.setVisible(true);
        customerLastBookingPanel.updateUI();
    }

    /** It is customerOrders method where we will show all orders of a selected customer.
     * @param myApp - taking RestManApp object to change it.
     */
    public void customerOrders(RestManApp myApp){

        /*Initializing panel.*/
        listCustomerOrdersPanel = new JPanel();
        listCustomerOrdersPanel.setSize(500,500);
        listCustomerOrdersPanel.setLayout(null);
        myMenu.add(listCustomerOrdersPanel);
        listCustomerOrdersPanel.setLocation(0,0);
        /*Putting customers to comboBox.*/
        JComboBox customerJComboBox = new JComboBox(new DefaultComboBoxModel(myApp.getCustomers().toArray()));
        customerJComboBox.setRenderer(new DefaultListCellRenderer() {
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus){
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if(value instanceof Customer){
                    Customer customer = (Customer) value;
                    setText(customer.getName());
                }
                return this;
            }
        });

        customerJComboBox.setSize(150,40);
        customerJComboBox.setLocation(175,20);

        JLabel selectCustomer = new JLabel("Select Customer:");
        selectCustomer.setSize(100,40);
        selectCustomer.setLocation(50,20);

        JButton getOrdersButton = new JButton("Get Orders");
        getOrdersButton.setSize(150,30);
        getOrdersButton.setLocation(175,70);

        /*It is action listener for getOrdersButton. After getOrdersButton clicked I will show its orders.*/
        getOrdersButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Customer myCustomer = (Customer) customerJComboBox.getItemAt(customerJComboBox.getSelectedIndex());
                ArrayList<Order> myOrders = myCustomer.getOrders();

                int onlineCount = 0;
                for(int i = 0; i < myOrders.size(); i++){
                    if(myOrders.get(i) instanceof OnlineOrder){
                        onlineCount++;
                    }
                }
                String[][] stringOnlineInfo = new String[onlineCount][6];
                String[][] stringInRestInfo = new String[myOrders.size()-onlineCount][5];
                String onlineColumn[] = {"ID","Date","Details","Extra Notes","Payment Type","Delivered By"};
                String inRestColumn[] = {"ID","Date","Details","Extra Notes","Table"};
                int count1 = 0;
                int count2 = 0;
                /*Putting information according to their types.*/
                for(int i = 0; i < myOrders.size(); i++){
                    if(myOrders.get(i) instanceof OnlineOrder){
                        stringOnlineInfo[count1][0] = String.valueOf(myOrders.get(i).getOrderID());
                        stringOnlineInfo[count1][1] = String.valueOf(myOrders.get(i).getOrderDate()).substring(4,10)+String.valueOf(myOrders.get(i).getOrderDate()).substring(23,28);
                        stringOnlineInfo[count1][2] = String.valueOf(myOrders.get(i).getOrderDetails());
                        stringOnlineInfo[count1][3] = String.valueOf(myOrders.get(i).getExtraNotes());
                        stringOnlineInfo[count1][4] = String.valueOf(((OnlineOrder) myOrders.get(i)).getPaymentType());
                        stringOnlineInfo[count1][5] = String.valueOf(((OnlineOrder) myOrders.get(i)).getDeliveredBy().getName());
                        count1++;
                    }
                    if(myOrders.get(i) instanceof InRestrOrder){

                        stringInRestInfo[count2][0] = String.valueOf(myOrders.get(i).getOrderID());
                        stringInRestInfo[count2][1] = String.valueOf(myOrders.get(i).getOrderDate()).substring(4,10)+String.valueOf(myOrders.get(i).getOrderDate()).substring(23,28);
                        stringInRestInfo[count2][2] = String.valueOf(myOrders.get(i).getOrderDetails());
                        stringInRestInfo[count2][3] = String.valueOf(myOrders.get(i).getExtraNotes());
                        stringInRestInfo[count2][4] = String.valueOf(((InRestrOrder) myOrders.get(i)).getTableNumber());
                        count2++;
                    }

                }
                /*Again, I am handling column sizes.*/
                JTable onlineOrders= new JTable(stringOnlineInfo,onlineColumn);
                onlineOrders.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                onlineOrders.getColumnModel().getColumn(0).setPreferredWidth(40);
                onlineOrders.getColumnModel().getColumn(1).setPreferredWidth(80);
                onlineOrders.getColumnModel().getColumn(2).setPreferredWidth(70);
                onlineOrders.getColumnModel().getColumn(3).setPreferredWidth(80);
                onlineOrders.getColumnModel().getColumn(4).setPreferredWidth(67);
                onlineOrders.getColumnModel().getColumn(5).setPreferredWidth(60);



                onlineOrders.setBounds(300,200,200,200);
                JScrollPane scrollPane = new JScrollPane(onlineOrders);
                scrollPane.setLocation(40,140);
                scrollPane.setSize(400,80);
                listCustomerOrdersPanel.add(scrollPane);

                JTable inRestOrders = new JTable(stringInRestInfo,inRestColumn);
                inRestOrders.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                inRestOrders.getColumnModel().getColumn(0).setPreferredWidth(40);
                inRestOrders.getColumnModel().getColumn(1).setPreferredWidth(80);
                inRestOrders.getColumnModel().getColumn(2).setPreferredWidth(110);
                inRestOrders.getColumnModel().getColumn(3).setPreferredWidth(107);
                inRestOrders.getColumnModel().getColumn(4).setPreferredWidth(60);

                inRestOrders.setBounds(200,200,200,200);
                JScrollPane scrollPane1 = new JScrollPane(inRestOrders);
                scrollPane1.setLocation(40,300);
                scrollPane1.setSize(400,80);
                listCustomerOrdersPanel.add(scrollPane1);
            }
        });

        JLabel onlineLabel = new JLabel("Online Orders");
        onlineLabel.setSize(100,20);
        onlineLabel.setLocation(200,110);
        listCustomerOrdersPanel.add(onlineLabel);

        JLabel inRestLabel = new JLabel("In Rest Orders");
        inRestLabel.setSize(100,20);
        inRestLabel.setLocation(200,260);
        listCustomerOrdersPanel.add(inRestLabel);

        listCustomerOrdersPanel.add(customerJComboBox);
        listCustomerOrdersPanel.add(selectCustomer);
        listCustomerOrdersPanel.add(getOrdersButton);

        listCustomerOrdersPanel.setVisible(true);
        listCustomerOrdersPanel.updateUI();
    }

    /** It is addOrder method where we will add order.
     * @param myApp - taking RestManApp object to change it.
     */
    public void addOrder(RestManApp myApp){

        /*Initializing panel.*/
        addOrderPanel = new JPanel();
        addOrderPanel.setSize(500,500);
        addOrderPanel.setLayout(null);
        myMenu.add(addOrderPanel);
        addOrderPanel.setLocation(0,0);
        /*Putting customers to comboBox.*/
        JComboBox customerJComboBox = new JComboBox(new DefaultComboBoxModel(myApp.getCustomers().toArray()));
        customerJComboBox.setRenderer(new DefaultListCellRenderer() {
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus){
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if(value instanceof Customer){
                    Customer customer = (Customer) value;
                    setText(customer.getName());
                }
                return this;
            }
        });
        ArrayList<Junior> juniorArrayList = new ArrayList<>();
        for(int i = 0; i < myApp.getStaffs().size(); i++){
            if(myApp.getStaffs().get(i) instanceof Junior)
            {
                juniorArrayList.add((Junior)myApp.getStaffs().get(i));
            }
        }
        JComboBox staffJComboBox = new JComboBox(new DefaultComboBoxModel(juniorArrayList.toArray()));
        staffJComboBox.setRenderer(new DefaultListCellRenderer() {
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus){
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if(value instanceof Junior){
                    Junior staff = (Junior) value;
                    setText(staff.getName());
                }
                return this;
            }
        });

        final JComboBox[] bookingJComboBox = new JComboBox[1];
        /*Getting bookings of selected customer.*/
        customerJComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Customer myCustomers = (Customer) customerJComboBox.getItemAt(customerJComboBox.getSelectedIndex());
                bookingJComboBox[0] = new JComboBox(new DefaultComboBoxModel(myCustomers.getBookings().toArray()));
                bookingJComboBox[0].setRenderer(new DefaultListCellRenderer() {
                    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus){
                        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                        if(value instanceof Booking){
                            Booking booking = (Booking) value;
                            setText(String.valueOf(booking.getBookingID()));
                        }
                        return this;
                    }
                });
                bookingJComboBox[0].setSize(100,20);
                bookingJComboBox[0].setLocation(175,280);
                addOrderPanel.add(bookingJComboBox[0]);
                customerJComboBox.setEnabled(false);
            }

        });

        customerJComboBox.setSize(150,20);
        customerJComboBox.setLocation(175,10);

        JLabel selectCustomer = new JLabel("Select Customer:");
        selectCustomer.setSize(100,20);
        selectCustomer.setLocation(30,10);

        JLabel addOrderDetails = new JLabel("Order Details:");
        addOrderDetails.setSize(100,20);
        addOrderDetails.setLocation(30,40);

        JTextField inputOrderDetails = new JTextField();
        inputOrderDetails.setSize(100,20);
        inputOrderDetails.setLocation(175,40);

        JLabel addOrderExtraNotes = new JLabel("Extra Notes:");
        addOrderExtraNotes.setSize(100,20);
        addOrderExtraNotes.setLocation(30,70);

        JTextField inputExtraNotes = new JTextField();
        inputExtraNotes.setSize(100,20);
        inputExtraNotes.setLocation(175,70);

        JLabel addOrderDate = new JLabel("Order Date:");
        addOrderDate.setSize(100,20);
        addOrderDate.setLocation(30,100);

        String day[] = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
        String year[] = {"2021","2022"};
        String month[] = {"1","2","3","4","5","6","7","8","9","10","11","12"};

        JComboBox inputOrderDateDay = new JComboBox(day);
        inputOrderDateDay.setSize(45,20);
        JComboBox inputOrderDateYear = new JComboBox(year);
        inputOrderDateYear.setSize(60,20);
        JComboBox inputOrderDateMonth = new JComboBox(month);
        inputOrderDateMonth.setSize(45,20);
        inputOrderDateDay.setLocation(175,100);
        inputOrderDateMonth.setLocation(225,100);
        inputOrderDateYear.setLocation(275,100);

        JRadioButton onlineButton = new JRadioButton("Online Order");
        onlineButton.setSize(100,20);
        onlineButton.setLocation(30,130);

        JLabel addOrderPaymentType = new JLabel("Payment Type:");
        addOrderPaymentType.setSize(120,20);
        addOrderPaymentType.setLocation(60,160);

        JTextField inputPaymentType = new JTextField();
        inputPaymentType.setSize(100,20);
        inputPaymentType.setLocation(175,160);

        JLabel selectStaff= new JLabel("Select Staff:");
        selectStaff.setSize(100,20);
        selectStaff.setLocation(60,190);

        staffJComboBox.setSize(150,20);
        staffJComboBox.setLocation(175,190);

        JRadioButton inRestButton = new JRadioButton("InRest");
        inRestButton.setSize(100,20);
        inRestButton.setLocation(30,220);

        JLabel addTableNumber = new JLabel("Table Number:");
        addTableNumber.setSize(120,20);
        addTableNumber.setLocation(60,250);

        JTextField inputTableNumber = new JTextField();
        inputTableNumber.setSize(100,20);
        inputTableNumber.setLocation(175,250);

        JLabel selectBooking = new JLabel("Select Booking:");
        selectBooking.setSize(100,20);
        selectBooking.setLocation(60,280);

        JButton addOrder = new JButton("Add Order");
        addOrder.setLocation(50,350);
        addOrder.setSize(100,20);

        JRadioButton inRestWithoutBookingButton = new JRadioButton("In Rest Without Booking");
        inRestWithoutBookingButton.setSize(200,20);
        inRestWithoutBookingButton.setLocation(30,310);

        final boolean[] online = new boolean[1];
        final boolean[] inRest = new boolean[1];
        final boolean[] inRestWithoutBooking = new boolean[1];
        online[0]=false;
        inRest[0]=false;
        inRestWithoutBooking[0]=false;

        /*Controlling JRadioButtons*/
        final int[] count = {0};
        onlineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(count[0] %2 == 0){
                    inRestButton.setEnabled(false);
                    addTableNumber.setEnabled(false);
                    inputTableNumber.setEnabled(false);
                    selectBooking.setEnabled(false);
                    inRestWithoutBookingButton.setEnabled(false);
                    if(bookingJComboBox[0] != null)
                        bookingJComboBox[0].setEnabled(false);
                    count[0]++;
                    online[0] = true;
                }
                else{
                    inRestButton.setEnabled(true);
                    addTableNumber.setEnabled(true);
                    inputTableNumber.setEnabled(true);
                    selectBooking.setEnabled(true);
                    inRestWithoutBookingButton.setEnabled(true);
                    if(bookingJComboBox[0] != null)
                        bookingJComboBox[0].setEnabled(true);
                    online[0] = false;
                    count[0]++;
                }
            }
        });

        final int[] count2 = {0};
        inRestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(count2[0] %2 == 0){
                    onlineButton.setEnabled(false);
                    addOrderPaymentType.setEnabled(false);
                    inputPaymentType.setEnabled(false);
                    selectStaff.setEnabled(false);
                    staffJComboBox.setEnabled(false);
                    inRestWithoutBookingButton.setEnabled(false);
                    inRest[0] = true;
                    count2[0]++;
                }
                else{
                    onlineButton.setEnabled(true);
                    addOrderPaymentType.setEnabled(true);
                    inputPaymentType.setEnabled(true);
                    selectStaff.setEnabled(true);
                    staffJComboBox.setEnabled(true);
                    inRestWithoutBookingButton.setEnabled(true);
                    inRest[0] = false;
                    count2[0]++;
                }
            }
        });

        final int[] count3 = {0};
        inRestWithoutBookingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(count3[0] %2 == 0){
                    inRestButton.setEnabled(false);
                    addTableNumber.setEnabled(false);
                    inputTableNumber.setEnabled(false);
                    selectBooking.setEnabled(false);
                    onlineButton.setEnabled(false);
                    addOrderPaymentType.setEnabled(false);
                    inputPaymentType.setEnabled(false);
                    selectStaff.setEnabled(false);
                    staffJComboBox.setEnabled(false);
                    if(bookingJComboBox[0] != null)
                        bookingJComboBox[0].setEnabled(false);
                    inRestWithoutBooking[0] = true;
                    count3[0]++;
                }
                else{
                    inRestButton.setEnabled(true);
                    addTableNumber.setEnabled(true);
                    inputTableNumber.setEnabled(true);
                    selectBooking.setEnabled(true);
                    if(bookingJComboBox[0] != null)
                        bookingJComboBox[0].setEnabled(true);
                    onlineButton.setEnabled(true);
                    addOrderPaymentType.setEnabled(true);
                    inputPaymentType.setEnabled(true);
                    selectStaff.setEnabled(true);
                    staffJComboBox.setEnabled(true);
                    inRestWithoutBooking[0] = false;
                    count3[0]++;
                }
            }
        });
        /*According to selected button, I am creating an object and put in to customer and necessary places. Here, I can also use my previous
        addOrder method but instead of that I prefer directly manage here this time.
         */
        addOrder.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                /*Creating Date object from comboBoxs*/
                String day = String.valueOf(inputOrderDateDay.getItemAt(inputOrderDateDay.getSelectedIndex()));
                String month =  String.valueOf(inputOrderDateMonth.getItemAt(inputOrderDateMonth.getSelectedIndex()));
                String year =  String.valueOf(inputOrderDateYear.getItemAt(inputOrderDateYear.getSelectedIndex()));
                Date orderDate = comboBoxtoDate(day,month,year);
                Customer myCustomer = ((Customer) customerJComboBox.getItemAt(customerJComboBox.getSelectedIndex()));
                if(online[0] == true){
                    OnlineOrder myOrder = new OnlineOrder();
                    myOrder.setOrderDetails(inputOrderDetails.getText());
                    myOrder.setExtraNotes(inputExtraNotes.getText());
                    myOrder.setOrderDate(orderDate);
                    myOrder.setDeliveredBy((Junior)staffJComboBox.getItemAt(staffJComboBox.getSelectedIndex()));
                    myOrder.setPaymentType(inputPaymentType.getText());
                    myCustomer.getOrders().add(myOrder);
                    operationDone = new JOptionPane();
                    operationDone.showMessageDialog(myMenu,"Order is added!");
                    addOrderPanel.setVisible(false);
                }
                if(inRest[0] == true){
                    InRestrOrder myOrder = new InRestrOrder();
                    myOrder.setOrderDetails(inputOrderDetails.getText());
                    myOrder.setExtraNotes(inputExtraNotes.getText());
                    myOrder.setOrderDate(orderDate);
                    myOrder.setTableNumber(Integer.parseInt(inputTableNumber.getText()));
                    myOrder.setBookingOrder((Booking) bookingJComboBox[0].getItemAt(bookingJComboBox[0].getSelectedIndex()));
                    myCustomer.getOrders().add(myOrder);
                    int vID = ((Booking) bookingJComboBox[0].getItemAt(bookingJComboBox[0].getSelectedIndex())).getBookingID();
                    for(int i = 0; i < myCustomer.getBookings().size(); i++){
                        if(myCustomer.getBookings().get(i).getBookingID() == vID){
                            myCustomer.getBookings().get(i).setBookingOrder(myOrder);
                        }
                    }
                    operationDone = new JOptionPane();
                    operationDone.showMessageDialog(myMenu,"Order is added!");
                    addOrderPanel.setVisible(false);
                }
                if(inRestWithoutBooking[0] == true){
                    InRestrOrder myOrder = new InRestrOrder();
                    myOrder.setOrderDetails(inputOrderDetails.getText());
                    myOrder.setExtraNotes(inputExtraNotes.getText());
                    myOrder.setOrderDate(orderDate);
                    myCustomer.getOrders().add(myOrder);
                    operationDone = new JOptionPane();
                    operationDone.showMessageDialog(myMenu,"Order is added!");
                    addOrderPanel.setVisible(false);
                }
                if(inRest[0] == false && online[0] == false && inRestWithoutBooking[0] == false){
                    operationDone = new JOptionPane();
                    operationDone.showMessageDialog(myMenu,"You should select order type!","Error", ERROR_MESSAGE);
                }
            }
        });

        addOrderPanel.add(customerJComboBox);
        addOrderPanel.add(selectCustomer);
        addOrderPanel.add(addOrderDetails);
        addOrderPanel.add(addOrderExtraNotes);
        addOrderPanel.add(addOrderDate);
        addOrderPanel.add(onlineButton);
        addOrderPanel.add(addOrderPaymentType);
        addOrderPanel.add(inRestButton);
        addOrderPanel.add(addTableNumber);
        addOrderPanel.add(inputOrderDateDay);
        addOrderPanel.add(inputOrderDateMonth);
        addOrderPanel.add(inputOrderDateYear);
        addOrderPanel.add(inputExtraNotes);
        addOrderPanel.add(inputOrderDetails);
        addOrderPanel.add(inputPaymentType);
        addOrderPanel.add(inputTableNumber);
        addOrderPanel.add(selectBooking);
        addOrderPanel.add(addOrder);
        addOrderPanel.add(staffJComboBox);
        addOrderPanel.add(selectStaff);
        addOrderPanel.add(inRestWithoutBookingButton);


        addOrderPanel.updateUI();
        addOrderPanel.setVisible(true);

    }

    /** It is listAllSalary method where we will list salary information of staffs..
     * @param myApp - taking RestManApp object to change it.
     */
    public void listAllSalary(RestManApp myApp){

        /*Initializing panel.*/
        getAllStaffSalary = new JPanel();
        getAllStaffSalary.setSize(500,500);
        getAllStaffSalary.setLayout(null);
        myMenu.add(getAllStaffSalary);
        getAllStaffSalary.setLocation(0,0);

        JLabel allSalary = new JLabel("All Staff Salary");
        allSalary.setSize(100,30);
        allSalary.setLocation(200,20);

        /*Putting staff name, staff id and its salary to JTable.*/
        String Column[] = {"Staff Name","Staff ID","Monthly Salary"};
        String[][] stringSalaryInfo = new String[myApp.getStaffs().size()][3];
        for(int i = 0; i < myApp.getStaffs().size(); i++){
            stringSalaryInfo[i][0] = String.valueOf(myApp.getStaffs().get(i).getName());
            stringSalaryInfo[i][1] = String.valueOf(myApp.getStaffs().get(i).getID());
            stringSalaryInfo[i][2] = String.valueOf(myApp.getStaffs().get(i).getSalary());
        }

        JTable salaryInfo = new JTable(stringSalaryInfo,Column);
        salaryInfo.setBounds(50,50,350,350);

        JScrollPane scrollPane = new JScrollPane(salaryInfo);
        scrollPane.setSize(400,300);
        scrollPane.setLocation(50,50);

        getAllStaffSalary.add(allSalary);
        getAllStaffSalary.add(scrollPane);

        getAllStaffSalary.setVisible(true);
        getAllStaffSalary.updateUI();

    }

    /** It is listAllOrders method where we will list all orders.
     * @param myApp - taking RestManApp object to change it.
     */
    public void listAllOrders(RestManApp myApp){

        /*Initializing panel.*/
        listALlOrderPanel = new JPanel();
        listALlOrderPanel.setSize(500,500);
        listALlOrderPanel.setLayout(null);
        myMenu.add(listALlOrderPanel);
        listALlOrderPanel.setLocation(0,0);

        /*This time I will get arraylist to local arraylist and fill form according to them. */
        ArrayList<OnlineOrder> myOnlineOrders = new ArrayList<OnlineOrder>();
        ArrayList<InRestrOrder> myInRestOrders = new ArrayList<InRestrOrder>();
        for(int i = 0; i < myApp.getCustomers().size(); i++){
            for(int j = 0; j < myApp.getCustomers().get(i).getOrders().size(); j++){
                if(myApp.getCustomers().get(i).getOrders().get(j) instanceof OnlineOrder){
                    myOnlineOrders.add((OnlineOrder)myApp.getCustomers().get(i).getOrders().get(j));
                }
                else{
                    myInRestOrders.add((InRestrOrder) myApp.getCustomers().get(i).getOrders().get(j));
                }
            }
        }
        /*Fill JTable strings as always.*/
        String[][] stringOnlineInfo = new String[myOnlineOrders.size()][6];
        String[][] stringInRestInfo = new String[myInRestOrders.size()][5];
        String onlineColumn[] = {"ID","Date","Details","Extra Notes","Payment Type","Delivered By"};
        String inRestColumn[] = {"ID","Date","Details","Extra Notes","Table"};
        for(int i = 0; i < myOnlineOrders.size(); i++) {
            stringOnlineInfo[i][0] = String.valueOf(myOnlineOrders.get(i).getOrderID());
            stringOnlineInfo[i][1] = String.valueOf(myOnlineOrders.get(i).getOrderDate()).substring(4,10)+String.valueOf(myOnlineOrders.get(i).getOrderDate()).substring(23,28);;
            stringOnlineInfo[i][2] = String.valueOf(myOnlineOrders.get(i).getOrderDetails());
            stringOnlineInfo[i][3] = String.valueOf(myOnlineOrders.get(i).getExtraNotes());
            stringOnlineInfo[i][4] = String.valueOf((myOnlineOrders.get(i)).getPaymentType());
            stringOnlineInfo[i][5] = String.valueOf((myOnlineOrders.get(i)).getDeliveredBy().getName());
        }
        for(int i = 0; i < myInRestOrders.size(); i++){
            stringInRestInfo[i][0] = String.valueOf(myInRestOrders.get(i).getOrderID());
            stringInRestInfo[i][1] = String.valueOf(myInRestOrders.get(i).getOrderDate()).substring(4,10)+String.valueOf(myInRestOrders.get(i).getOrderDate()).substring(23,28);
            stringInRestInfo[i][2] = String.valueOf(myInRestOrders.get(i).getOrderDetails());
            stringInRestInfo[i][3] = String.valueOf(myInRestOrders.get(i).getExtraNotes());
            stringInRestInfo[i][4] = String.valueOf((myInRestOrders.get(i)).getTableNumber());
        }

        /*Handling column sizes.*/
        JTable onlineOrders= new JTable(stringOnlineInfo,onlineColumn);
        onlineOrders.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        onlineOrders.getColumnModel().getColumn(0).setPreferredWidth(40);
        onlineOrders.getColumnModel().getColumn(1).setPreferredWidth(80);
        onlineOrders.getColumnModel().getColumn(2).setPreferredWidth(80);
        onlineOrders.getColumnModel().getColumn(3).setPreferredWidth(84);
        onlineOrders.getColumnModel().getColumn(4).setPreferredWidth(83);
        onlineOrders.getColumnModel().getColumn(5).setPreferredWidth(80);

        onlineOrders.setBounds(300,200,200,200);
        JScrollPane scrollPane = new JScrollPane(onlineOrders);
        scrollPane.setLocation(20,50);
        scrollPane.setSize(450,100);
        listALlOrderPanel.add(scrollPane);

        JTable inRestOrders = new JTable(stringInRestInfo,inRestColumn);
        inRestOrders.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        inRestOrders.getColumnModel().getColumn(0).setPreferredWidth(40);
        inRestOrders.getColumnModel().getColumn(1).setPreferredWidth(80);
        inRestOrders.getColumnModel().getColumn(2).setPreferredWidth(110);
        inRestOrders.getColumnModel().getColumn(3).setPreferredWidth(150);
        inRestOrders.getColumnModel().getColumn(4).setPreferredWidth(67);

        inRestOrders.setBounds(200,200,200,200);
        JScrollPane scrollPane1 = new JScrollPane(inRestOrders);
        scrollPane1.setLocation(20,250);
        scrollPane1.setSize(450,100);
        listALlOrderPanel.add(scrollPane1);

        JLabel onlineLabel = new JLabel("Online Orders");
        onlineLabel.setSize(100,20);
        onlineLabel.setLocation(200,20);
        listALlOrderPanel.add(onlineLabel);

        JLabel inRestLabel = new JLabel("In Rest Orders");
        inRestLabel.setSize(100,20);
        inRestLabel.setLocation(200,220);
        listALlOrderPanel.add(inRestLabel);
        listALlOrderPanel.updateUI();

        listALlOrderPanel.setVisible(true);
        listALlOrderPanel.updateUI();
    }

    /** It is addOrderOfBooking method where we will add order to spesific booking.
     * @param myApp - taking RestManApp object to change it.
     */
    public void addOrderOfBooking(RestManApp myApp){

        /*Initializing panel.*/
        addOrderOfBooking = new JPanel();
        addOrderOfBooking.setSize(500,500);
        addOrderOfBooking.setLayout(null);
        myMenu.add(addOrderOfBooking);
        addOrderOfBooking.setLocation(0,0);

        JComboBox customerJComboBox = new JComboBox(new DefaultComboBoxModel(myApp.getCustomers().toArray()));
        customerJComboBox.setRenderer(new DefaultListCellRenderer() {
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus){
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if(value instanceof Customer){
                    Customer customer = (Customer) value;
                    setText(customer.getName());
                }
                return this;
            }
        });
        customerJComboBox.setSize(150,20);
        customerJComboBox.setLocation(175,10);
        addOrderOfBooking.add(customerJComboBox);

        final JComboBox[] bookingJComboBox = new JComboBox[1];
        /*Filling comboBox according to customer.*/
        customerJComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addOrderPanel.revalidate();
                addOrderPanel.validate();
                addOrderPanel.updateUI();
                Customer myCustomers = (Customer) customerJComboBox.getItemAt(customerJComboBox.getSelectedIndex());
                bookingJComboBox[0] = new JComboBox(new DefaultComboBoxModel(myCustomers.getBookings().toArray()));
                bookingJComboBox[0].setRenderer(new DefaultListCellRenderer() {
                    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus){
                        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                        if(value instanceof Booking){
                            Booking booking = (Booking) value;
                            setText(String.valueOf(booking.getBookingID()));
                        }
                        return this;
                    }
                });
                bookingJComboBox[0].setSize(100,20);
                bookingJComboBox[0].setLocation(175,40);
                addOrderOfBooking.add(bookingJComboBox[0]);
                customerJComboBox.setEnabled(false);
            }
        });

        JLabel selectCustomer = new JLabel("Select Customer:");
        selectCustomer.setSize(100,20);
        selectCustomer.setLocation(30,10);

        JLabel addOrderDetails = new JLabel("Order Details:");
        addOrderDetails.setSize(100,20);
        addOrderDetails.setLocation(30,70);

        JTextField inputOrderDetails = new JTextField();
        inputOrderDetails.setSize(100,20);
        inputOrderDetails.setLocation(175,70);

        JLabel addOrderExtraNotes = new JLabel("Extra Notes:");
        addOrderExtraNotes.setSize(100,20);
        addOrderExtraNotes.setLocation(30,100);

        JTextField inputExtraNotes = new JTextField();
        inputExtraNotes.setSize(100,20);
        inputExtraNotes.setLocation(175,100);

        JLabel addOrderDate = new JLabel("Order Date:");
        addOrderDate.setSize(100,20);
        addOrderDate.setLocation(30,130);

        String day[] = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
        String year[] = {"2021","2022"};
        String month[] = {"1","2","3","4","5","6","7","8","9","10","11","12"};

        JComboBox inputOrderDateDay = new JComboBox(day);
        inputOrderDateDay.setSize(45,20);
        JComboBox inputOrderDateYear = new JComboBox(year);
        inputOrderDateYear.setSize(60,20);
        JComboBox inputOrderDateMonth = new JComboBox(month);
        inputOrderDateMonth.setSize(45,20);
        inputOrderDateDay.setLocation(175,130);
        inputOrderDateMonth.setLocation(225,130);
        inputOrderDateYear.setLocation(275,130);

        JLabel addTableNumber = new JLabel("Table Number:");
        addTableNumber.setSize(120,20);
        addTableNumber.setLocation(30,160);

        JTextField inputTableNumber = new JTextField();
        inputTableNumber.setSize(100,20);
        inputTableNumber.setLocation(175,160);

        JLabel selectBooking = new JLabel("Select Booking:");
        selectBooking.setSize(100,20);
        selectBooking.setLocation(30,40);

        JButton addOrder = new JButton("Add Order");
        addOrder.setLocation(175,200);
        addOrder.setSize(100,20);

        /*Here this time I prefer calling my previous method. I also comment how I can do that without calling it.
        * I will close panel after adding order.
        */
        addOrder.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                String day = String.valueOf(inputOrderDateDay.getItemAt(inputOrderDateDay.getSelectedIndex()));
                String month =  String.valueOf(inputOrderDateMonth.getItemAt(inputOrderDateMonth.getSelectedIndex()));
                String year =  String.valueOf(inputOrderDateYear.getItemAt(inputOrderDateYear.getSelectedIndex()));
                Date orderDate = comboBoxtoDate(day,month,year);

                Customer myCustomer = ((Customer) customerJComboBox.getItemAt(customerJComboBox.getSelectedIndex()));

                InRestrOrder myOrder = new InRestrOrder();
                myOrder.setOrderDetails(inputOrderDetails.getText());
                myOrder.setExtraNotes(inputExtraNotes.getText());
                myOrder.setOrderDate(orderDate);
                myOrder.setTableNumber(Integer.parseInt(inputTableNumber.getText()));
                myOrder.setBookingOrder((Booking) bookingJComboBox[0].getItemAt(bookingJComboBox[0].getSelectedIndex()));

                /*myCustomer.getOrders().add(myOrder);
                myCustomer.getBookings().get(myCustomer.getBookings().size()-1).setBookingOrder(myOrder); */

                myApp.addOrderOfBooking(myApp,myOrder.getBookingOrder().getBookingID(),myCustomer.getID(),myOrder);

                operationDone = new JOptionPane();
                operationDone.showMessageDialog(myMenu,"Order is added to booking!");
                addOrderOfBooking.setVisible(false);
            }
        });


        addOrderOfBooking.add(selectCustomer);
        addOrderOfBooking.add(addOrderDetails);
        addOrderOfBooking.add(addOrderExtraNotes);
        addOrderOfBooking.add(addOrderDate);
        addOrderOfBooking.add(addTableNumber);
        addOrderOfBooking.add(inputOrderDateDay);
        addOrderOfBooking.add(inputOrderDateMonth);
        addOrderOfBooking.add(inputOrderDateYear);
        addOrderOfBooking.add(inputExtraNotes);
        addOrderOfBooking.add(inputOrderDetails);
        addOrderOfBooking.add(inputTableNumber);
        addOrderOfBooking.add(selectBooking);
        addOrderOfBooking.add(addOrder);

        addOrderOfBooking.updateUI();
        addOrderOfBooking.setVisible(true);

    }

    /** It is comboBoxtoDate method. It will help us to make Date object.
     * @param day - string day
     * @param month - string month
     * @param year - string year
     * @return - we will return a date variable.
     */
    public Date comboBoxtoDate(String day, String month, String year){
        String date = day+"/"+month+"/"+year;
        Date myDate = new Date();
        try {
            myDate = new SimpleDateFormat("dd/mm/yyyy").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return myDate;
    }
}


