
package com.core;


import com.support.Order;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


/** It's the main class where all our restaurant management will happen. We also communicate Staff and Customer Class with help of its lists.
 * Because of using its methods with GUI, some methods were changed a bit. Instead of delete previous lines, I comment them. Working logic didn't change.
 * Also, now it is serializable to IOStream.
 */
public class RestManApp implements Serializable {

    private final ArrayList<Staff> staffs;
    private final ArrayList<Customer> customers;

    /** It's constructor for RestManApp class. I am only initializing the arraylists**/
    public RestManApp(){
        staffs = new ArrayList<Staff>();
        customers = new ArrayList<Customer>();
    }

    /** It's constructor for RestManApp class with variables. It gets variables as parameters and put into class' variables.
     *
     * @param vStaffs It's arraylist of Staff class to put in staffs.
     * @param vCustomers It's arraylist of Customer class to put in customers.
     */
    public RestManApp(ArrayList<Staff> vStaffs, ArrayList<Customer> vCustomers){
        staffs = new ArrayList<Staff>(vStaffs);
        customers = new ArrayList<Customer>(vCustomers);
    }

    /**It's simple getter for for staffs arraylist.
     * We will use them later when we will populate them.
     * @return It's returning basically arraylist of Staff class.
     */
    ArrayList<Staff> getStaffs(){
        return staffs;
    }
    /**It's simple getter for for customers arraylist.
     * We will use them later when we will populate them.
     * @return It's returning basically arraylist of Customer class.
     */
    ArrayList<Customer> getCustomers(){
        return customers;
    }

    /** Here, I create a method to create char with error checking in other methods. Especially, it's directly checking for gender if it is (M)ale or (F)emale.
     *
     * @return It's returning char.
     */
    public char createChar(){

        Scanner scanner = new Scanner(System.in);
        char vGender = 'a';
        vGender = scanner.next().charAt(0);
        while(vGender != 'm' && vGender != 'M' && vGender != 'f' && vGender != 'F' ) {
            System.out.print("Please select a gender category! (M / F): ");
            vGender = scanner.next().charAt(0);
        }
        return vGender;
    }
    /** Here, I create a method to create string in other methods. I used ".usedDelimiter("\n)". It helps me to get all line as input and with this way I can get name, order
     * details, description etc. easily to in one variable.
     * @return It's returning string.
     * **/
    public String createString(){

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        String string = scanner.next();
        return string;
    }
    /** Here, I create a method to create int with error checking in other methods. It directly control if the given input is integer or not. It continue till get integer.
     * @return It's returning integer.
     * */
    public int createInt(){

        int vSsn = 0;
        Scanner scanner = new Scanner(System.in);
        while(!scanner.hasNextInt()) {
            System.out.print("Invalid input! Enter an integer: ");
            scanner.next();
        }
        vSsn = scanner.nextInt();
        return vSsn;
    };
    /** Here, I create a method to create Date in other methods.
     * @return It's returning date.
     */
    public Date createDate(){

        Scanner scanner = new Scanner(System.in);
        String vDateOfBirth = scanner.next();
        Date myBirth = new Date();
        try {
            myBirth = new SimpleDateFormat("dd/MM/yyyy").parse(vDateOfBirth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return myBirth;
    }
    /** It's my menu method. I basically show options to user. **/
    public void menu(){

        System.out.println("\n1-Add Staff\n" + /*Completed*/
                "2-Delete Staff\n" + /*Completed*/
                "3-List Staff Details\n" + /*Completed*/
                "4-Add Customer\n" + /*Completed*/
                "5-Delete Customer\n" + /*Completed*/
                "6-Add Booking\n" + /*Completed*/
                "7-List Customer Details\n" + /*Completed*/
                "8-Display Customer Last Booking\n" + /*Completed*/
                "9-List Customer Orders\n" + /*Completed*/
                "10-List Staff\n" + /*Completed*/
                "11-List Customer\n" + /*Completed*/
                "12-Add Order\n" + /*Completed*/
                "13-List All Staff Salary\n" + /*Completed*/
                "14-List All Order\n" + /*Completed*/
                "15-Add Order Of Booking\n" + /*Completed*/
                "16-Exit");/*Completed*/

    }

    /** I create this method to adding staff. I am using my helper methods to get inputs to temp variables and once create the staff type to fill it.
     * I am also sending my RestManApp object to fill it here after a create the staff. For this method the important thing is ID. As we know, ID should be unique.
     * Therefore, I have to check it that ID is already registered to the system or not. For that, also I need to check both customers and staffs.
     * Because we are adding Staff, checking system working according to that. If we have already that ID number in staff list and the user's name input is the same with the
     * name of previous ID object, we say that the ssn has already added with same name. Otherwise, we are saying the ID is used by another staff.
     * Also, there is another possibility which is ID found in customer list. Here, I assume a customer also can be a staff. Therefore, If the name and ID of the object same
     * with the user's input ID, I am asking he or she is already a customer, do you want to add him/her as staff. If the name is different, I am just giving an error text to
     * user and ask to if user wants to enter new ID, if he doesn't want to enter new ID, then I am asking if he want to create the staff from the beginning, if still he doesn't
     * want to that I am closing program without creating any staff. Another important thing about the method is that after creating a junior,
     * I need to specify which senior will responsible. For maintain this, I am travelling all list end check senior's responsible from list for which one has the minimum number of
     * junior staff on their list. After I find which one has minimum, I put that junior to that senior list.
     *@param myApp It's for filling it and turn back to main again and also of course will help us to creating inputs.
     **/
    public int addStaff(RestManApp myApp,int type,String iName, char iGender, Date iDoB, Date iStartDate, int iID,int iYearlyGrossSalary, Date iExpectedEndDate, int iMonthlySalary,int iSelection) {


        int checker = 1, controller = 0, controller2 = 1, controller3 = 0, result = 1, erase = 0, selection = -1, myID, min = 0, k;
        String vName;
        char vGender;
        Date vDoB, vStartDate;
        while (controller2 == 1) {
            controller2 = 0;
            checker = 1;

            /*System.out.print("Do you want to add Senior Staff or Junior Staff? Press '1' for Senior Staff Press '2' for Junior Staff! ");
            while (selection < 1 || selection > 2) {
                selection = myApp.createInt();
                if (selection < 1 || selection > 2) {
                    System.out.print("Please select option 1 or 2! ");
                    selection = myApp.createInt();
                }
            }*/

            //System.out.print("\nEnter Staff Name: ");
            vName = iName;

            //System.out.print("Enter gender (M/F): ");
            vGender = iGender;

            //System.out.print("Enter Date of Birth (dd/mm/yyyy): ");
            vDoB = iDoB;

            //System.out.print("Enter Start Date (dd/mm/yyyy): ");
            vStartDate = iStartDate;


            while (checker == 1) { //This part is all about checking ID is unique or not.
                result = 1;
                erase = 0;
                checker = 0;
                //System.out.print("Enter ID: ");
                myID = iID;
                for (int i = 0; i < myApp.staffs.size(); i++) {
                    if (myApp.staffs.get(i).getID() == myID) {
                        result = 0;
                        erase = 1;
                        if (myApp.staffs.get(i).getName().equals(vName)) {
                            //System.out.println("You have already added this ID with same name!");
                            return 1;
                        } else {
                            //System.out.println("This ID number is used by another staff");
                            return 2;
                        }
                        /*System.out.print("Do you want to retry entering ID? For 'Yes' press 1! ");
                        controller3 = myApp.createInt();
                        if (controller3 == 1) {
                            checker = 1;
                        } else {
                            System.out.print("Do you want to retry entering Staff? For 'Yes' press 1! ");
                            controller2 = myApp.createInt();
                        }*/
                    }
                }
                if (erase != 1) {
                    for (int i = 0; i < myApp.customers.size(); i++) {
                        if (myApp.customers.get(i).getID() == myID) {
                            result = 0;
                            if (myApp.customers.get(i).getName().equals(vName)) {
                                //System.out.println("This ID is used by a customer!");
                                //System.out.print("Do you want to add same person as staff? For 'Yes' press 1!, for 'No' press 2!  ");
                                /*controller = myApp.createInt();*/
                                if (iSelection == 1) {
                                    if (type == 0) {
                                        int vYearlyGrossSalary;
                                        //System.out.print("Enter Yearly Gross Salary:  ");
                                        vYearlyGrossSalary = iYearlyGrossSalary;
                                        Senior mySenior = new Senior(myID, vName, vGender, vDoB, vStartDate, vYearlyGrossSalary);
                                        myApp.staffs.add(mySenior);
                                        return 5;
                                    } else {
                                        Date vExpectedEndDate;
                                        int vMonthlySalary;
                                        //System.out.print("Enter Monthly Salary:  ");
                                        vMonthlySalary = iMonthlySalary;
                                        //System.out.print("Enter Expected End Date:  ");
                                        vExpectedEndDate = iExpectedEndDate;
                                        Junior myJunior = new Junior(myID, vName, vGender, vDoB, vStartDate, vMonthlySalary, vExpectedEndDate);
                                        for(k = 0; k < myApp.staffs.size(); k++){ // This part for finding which senior member has the least number of junior staff in the responsible list.

                                                    try {
                                                        if (((Senior) myApp.staffs.get(k)).getResponsibleFrom().size() > ((Senior) myApp.staffs.get(min)).getResponsibleFrom().size()) {
                                                            min = k;
                                                        }
                                                    }catch(ClassCastException e){}
                                        }
                                        /*System.out.print("Min = " + min);*/
                                        ((Senior)myApp.staffs.get(min)).getResponsibleFrom().add(myJunior);

                                        myApp.staffs.add(myJunior);
                                        return 5;
                                    }
                                    /*System.out.println("Staff is added to the list!");*/
                                }
                                return 3;
                            } else {
                                //System.out.println("This ID number is used by a customer with different name!");
                                return 4;
                            }
                            /*if (controller != 1) {
                                System.out.print("Do you want to retry entering ID? For 'Yes' press 1!, for 'No' press 2!  ");
                                controller3 = myApp.createInt();

                                if (controller3 == 1) {
                                    checker = 1;
                                } else {
                                    System.out.print("Do you want to retry entering Staff? For 'Yes' press 1!, for 'No' press 2!  ");
                                    controller2 = myApp.createInt();
                                }
                            }*/
                        }
                    }
                }
                if (result == 1) {

                    if (type == 0) {
                        int vYearlyGrossSalary = iYearlyGrossSalary;
                        //System.out.print("Enter Yearly Gross Salary:  ");
                        Senior mySenior = new Senior(myID, vName, vGender, vDoB, vStartDate, vYearlyGrossSalary);
                        myApp.staffs.add(mySenior);
                    } else {
                        Date vExpectedEndDate = iExpectedEndDate;
                        int vMonthlySalary = iMonthlySalary;
                        /*System.out.print("Enter Monthly Salary:  ");
                        System.out.print("Enter Expected End Date:  ");*/
                        Junior myJunior = new Junior(myID, vName, vGender, vDoB, vStartDate, vMonthlySalary, vExpectedEndDate);
                        for (k = 0; k < myApp.staffs.size(); k++) { // This part for finding which senior member has the least number of junior staff in the responsible list.

                            try {
                                if (((Senior) myApp.staffs.get(k)).getResponsibleFrom().size() < ((Senior) myApp.staffs.get(min)).getResponsibleFrom().size()) {
                                    min = k;
                                }
                            } catch (ClassCastException e) {
                            }
                        }
                        ((Senior) myApp.staffs.get(min)).getResponsibleFrom().add(myJunior);
                        myApp.staffs.add(myJunior);
                    }
                    //System.out.println("Staff is added to the list!");
                    return 5;
                }

            }
            /*if (result == 0) {
                System.out.println("Staff adding process was finished as uncompleted!");
            }*/
        }
        return 0;
    }

    /** Finding staff in list and remove from list. If the given ID is not in the list, I am printing an error message about that.
     * @param myApp It's for filling it and turn back to main again and also of course will help us to creating inputs.
     * @param vID It's the integer ID which we will search in the staff list.
     */
    public void deleteStaff(int vID, RestManApp myApp){

        /*int checker = 0;*/
        for(int i = 0; i < myApp.staffs.size(); i++ ){
            if(vID == myApp.staffs.get(i).getID()) {
                myApp.staffs.remove(i);
                /*checker = 1;
                System.out.println("The staff is deleted from the list!");*/
            }
        }
        /*if(checker == 0){
            System.out.println("The given ID number couldn't found in the list! It doesn't belongs to any of staff!");
        }*/
    }

    /** This method for printing a particular staff details. I am searching according the ID and when I find it I, I call the printDetails method with that staff to print it.
     * If the given ID is not in the list, I am printing an error message about that.
     * @param vID - It's the integer ID which we will search in the staff list.
     * @param myApp - It's for deleting from it and turn back to main again.
     */
    public int listStaffDetails(int vID, RestManApp myApp){

        /*int checker = 0;*/
        for(int i = 0; i < myApp.staffs.size(); i++ ) {
            if(vID == myApp.staffs.get(i).getID())
            {
                /*myApp.staffs.get(i).printDetails();
                checker = 1;*/
                return i;
            }
        }
        /*if(checker == 0){
            System.out.println("The given ID number couldn't found in the list! It doesn't belogns to any of staff!");
        }*/
        return -1;
    }

    /** Here, I am creating a customer and fill its information with helper methods.It's important that to mention about ID again. As I explained in addStaff() method,
     * ID should be unique. Therefore, I am checking if it is unique or not. If I found the ID in staff list with same name, I am asking if user wants to add it also as customer.
     * If names are different, I am directly giving error about it. If the given ID and name are same with the customer list object, I am saying you are already added that customer.
     * If the names are different, I am giving error about that and try to prevent multiple ID with same number.  Also, instead of forcing user to adding booking,
     * I prefer give option to user add booking or not. If user wants to add booking, user can press 1 and continue to add bookings. If user doesn't want that, easily press anything
     * other than 1.
     * @param myApp It's for filling it and turn back to main again and also of course will help us to creating inputs. */
    public int addCustomer(RestManApp myApp,String iName,int iID, char iGender, Date iDoB, Date iRegistrationDate, String iCreditCardDetails,int iSelection){

        Customer myCustomer = new Customer();
        int selection = 0, checker = 1, controller = 0, controller2 = 1, controller3=0, result = 1, erase = 0;
        while (controller2 == 1) {
            checker = 1;
            controller2 = 0;
            //System.out.print("\nEnter Customer Name: ");
            myCustomer.setName(iName);

            //System.out.print("Enter Customer Gender (M/F): ");
            myCustomer.setGender(iGender);

            //System.out.print("Enter Customer Enter Date of Birth (dd/mm/yyyy): ");
            myCustomer.setDateOfBirth(iDoB);

            //System.out.print("Enter Customer Registration Date(dd/mm/yyyy): ");
            myCustomer.setRegistrationDate(iRegistrationDate);

            //System.out.print("Enter Customer Credit Card Details: ");
            myCustomer.setCreditCardDetails(iCreditCardDetails);

            //Here is all about creating ID as unique.
            while (checker == 1) {
                //System.out.print("Enter Customer ID: ");
                result = 1;
                erase = 0;
                checker = 0;
                myCustomer.setID(iID);
                for (int i = 0; i < myApp.customers.size(); i++) {
                    if (myApp.customers.get(i).getID() == myCustomer.getID()) {
                        result = 0;
                        erase = 1;
                        if (myApp.customers.get(i).getName().equals(myCustomer.getName())) {
                            //System.out.println("You have already added this ID with same name!");
                            return 1;
                        } else {
                            //System.out.println("This ID number is used by another customer");
                            return 2;
                        }
                        /*System.out.print("Do you want to retry entering ID? For 'Yes' press 1!, for 'No' press 2!  ");
                        controller3 = myApp.createInt();
                        if (controller3 == 1) {
                            checker = 1;
                        } else {
                            System.out.print("Do you want to retry entering Customer? For 'Yes' press 1!, for 'No' press 2!  ");
                            controller2 = myApp.createInt();
                        }*/
                    }
                }
                if(erase != 1) {
                    for (int i = 0; i < myApp.staffs.size(); i++) {
                        if (myApp.staffs.get(i).getID() == myCustomer.getID()) {
                            result = 0;
                            if (myApp.staffs.get(i).getName().equals(myCustomer.getName())) {
                                /*System.out.println("This ID is used by a staff!");
                                System.out.print("Do you want to add same person as customer? For 'Yes' press 1!, for 'No' press 2!  ");
                                controller = myApp.createInt();*/
                                if (iSelection == 1) {
                                    myApp.customers.add(myCustomer);
                                    //System.out.println("Customer is added to the list!");
                                    return 6;
                                }
                                return 3;
                            } else {
                                //System.out.println("This ID number is used by a staff with different name!");
                                return 4;
                            }
                            /*if (controller != 1) {
                                System.out.print("Do you want to retry entering ID? For 'Yes' press 1! ");
                                controller3 = myApp.createInt();

                                if (controller3 == 1) {
                                    checker = 1;
                                } else {
                                    System.out.print("Do you want to retry entering Customer? For 'Yes' press 1!, for 'No' press 2!  ");
                                    controller2 = myApp.createInt();
                                }
                            }*/
                        }
                    }
                }
                if (result == 1) {
                    myApp.customers.add(myCustomer);
                    //System.out.println("Customer is added to the list!");
                    return 5;
                }
            }
        }
        /*if(result == 0){
            System.out.println("Customer adding process was finished as uncompleted! ");
        }*/
        return 0;
    }

    /** Finding customer in list and remove from list. If the given ID is not in the list, I am printing an error message about that.
     * @param myApp It's for filling it and turn back to main again and also of course will help us to creating inputs.
     * @param vID It's the integer ID which we will search in the customer list.
     */
    public void deleteCustomer(int vID, RestManApp myApp){

        //int checker = 0;
        for(int i = 0; i < myApp.customers.size(); i++ ){
            if(vID == myApp.customers.get(i).getID()){
                myApp.customers.remove(i);
                /*checker = 1;
                System.out.println("The customer is deleted from the list!");*/
            }
        }
        /*if (checker == 0){
            System.out.println("The given ID couldn't found in the list! It doesn't belongs to any of customer!");
        }*/

    }

    /** Here, I am getting booking for specific customer. I am searching that ID in the list if it is, I'm getting inputs and add booking to that customer.
     * @param vID It's the integer ID which we will search in the customer list.
     * @param myApp It's for filling it and turn back to main again and also of course will help us to creating inputs.
     */
    public void addBooking(int vID, RestManApp myApp,Date iBookingDate){

        for(int i = 0; i < myApp.customers.size(); i++ ){
            if(vID == myApp.customers.get(i).getID()){
                Booking myBooking = new Booking();
                /*System.out.print("Enter the Booking Date: ");*/
                myBooking.setBookingDate(iBookingDate);
                myApp.customers.get(i).getBookings().add(myBooking);
            }
        }
    }

    /** Here, I am printing customer's details according to ID. I prefer give option to user to print customer's booking details or not. If given ID is not in the list
     * I'm printing appropriate message for that. For the printing customer orders, I prefer to call listCustomerOrders() function. For bookings, I will use printBookingDetails() from
     * Booking Class.
     * @param vID -  It's the integer ID which we will search in the customer list.
     * @param myApp -  It's for searching the given ID in the its list. */
    public Customer listCustomerDetails(int vID, RestManApp myApp){

        /*int selection = 0, checker = 0;
        System.out.print("Do you want to print Customer's Bookings and Order? Press '1' for 'Yes' ");
        selection = myApp.createInt();*/

        for(int i = 0; i < myApp.customers.size(); i++ ) {
            if(vID == myApp.customers.get(i).getID())
            {
                /*myApp.customers.get(i).printDetails();
                if(selection == 1){
                    System.out.println("\nHere is the Customer's Orders\n---------------------------------");
                    myApp.listCustomerOrders(myApp,vID);
                    System.out.println("\nHere is the Customer's Bookings\n---------------------------------");
                    for(j = 0; j < myApp.customers.get(i).getBookings().size(); j++){
                        myApp.customers.get(i).getBookings().get(j).printBookingDetails();
                    }
                }
                checker = 1;*/
                return myApp.getCustomers().get(i);
            }
        }
        /*if (checker == 0){
            System.out.println("The given ID couldn't found in the list! It doesn't belongs to any of customer!");
        }*/
        return null;
    }

    /** This method for displaying last booking of a particular customer. For getting last booking I get the size of bookings list and substract 1 and
     * try to do exception handling in case of (0-1).
     * @param myApp - It's for searching the given ID in the its list.
     * @param vCustomerID - It's the integer ID which we will search in the customer list.
     */
    public void displayCustomerLastBooking(RestManApp myApp, int vCustomerID){
        int i;
        for(i = 0; i < myApp.customers.size(); i++){
            if(vCustomerID == myApp.customers.get(i).getID()){
                try {
                    myApp.customers.get(i).getBookings().get(myApp.customers.get(i).getBookings().size() - 1).printBookingDetails();
                } catch(IndexOutOfBoundsException e){
                    System.out.println("This customer has no booking!");
                }
            }
        }

    }

    /** This methods for listing customer orders. It takes an ID for customer searching, If it find, it prints, otherwise printing and error massage about that.
     * @param myApp - It's for searching the given ID in the its list.
     * @param vCustomerID - It's the integer ID which we will search in the customer list.
     */
    public void listCustomerOrders(RestManApp myApp, int vCustomerID){
        int i,j,checker=0;
        for(i = 0; i < myApp.customers.size(); i++){
            if(vCustomerID == myApp.customers.get(i).getID()){
                for(j = 0; j < myApp.customers.get(i).getOrders().size(); j++){
                    myApp.customers.get(i).getOrders().get(j).printOrderDetails();
                    checker = 1;
                }
            }
        }
        if(checker == 0){
            System.out.println("The given ID couldn't found in the list! It doesn't belongs to any of customer!");
        }
    }



    /** Printing all staffs information.
     * @param myApp It is for searching in its staff list.*/
    public void listStaff(RestManApp myApp) {

        for (int i = 0; i < myApp.staffs.size(); i++) {
            System.out.println("\nThe " + (i + 1) + ". Staff\n--------------------------------");
            myApp.staffs.get(i).printDetails();
        }
    }

    /** Printing all customer information. Again, I am giving opportunity to print bookings and orders or not.
     * @param myApp - It is for searching in its customer list.*/
    public void listCustomer(RestManApp myApp){

        int selection = 0,i,j;
        System.out.print("\nDo you also want to print customer's bookings and orders? For 'Yes' press 1!, for 'No' press 2! ");
        selection = myApp.createInt();
        for(i = 0; i < myApp.customers.size(); i++){

            System.out.println("\nThe " + (i + 1) + ". Customer\n--------------------------------");
            myApp.customers.get(i).printDetails();
            if(selection == 1){
                System.out.println("\nHere is the Customer's Orders\n---------------------------------");
                myApp.listCustomerOrders(myApp,myApp.customers.get(i).getID());
                System.out.println("---------------------------------\nHere is the Customer's Bookings\n---------------------------------");
                for(j = 0; j < myApp.customers.get(i).getBookings().size(); j++){
                    myApp.customers.get(i).getBookings().get(j).printBookingDetails();
                }
            }

        }

    }

    /** This method for adding new order. Firstly, I am searching given ID in the customer list. If I find then I am keeping that in a variable to later use.
     * For the adding order part, I am asking if it is online order or in restaurant one. If it is online I am also want to junior staff ID to delivered order.
     * Here, I assume I must have a junior staff member and till I am getting it is correct ID, I keep continue to ID. For the in restaurant order I am asking if user want to add
     * booking for it, If yes, I am also getting it's inputs.
     * @param myApp - It is for searching and filling it.
     * @param vID - Customer ID to add order.
     */
    public void addOrder(RestManApp myApp, int vID, Order myOrder) {

        int selection = -1, staffID, cont = 0, i, index = -1;
        for (i = 0; i < myApp.customers.size(); i++) {
            if (vID == myApp.customers.get(i).getID()) {
                myApp.getCustomers().get(i).getOrders().add(myOrder);
            }
        }
        /*if (index == -1) {
            System.out.println("There is no such customer in the list! Add order operation terminated!");
        } else {
            Date vOrderDate;
            String vOrderDetails, vExtraNotes;
            System.out.print("Do you want to add Online Order or In Restaurant Order. Press 1 for 'Online' and Press 2 for 'In Restaurant'! ");
            while (selection < 1 || selection > 2) {
                selection = myApp.createInt();
                if (selection < 1 || selection > 2) {
                    System.out.print("Please select option 1 or 2! ");
                    selection = myApp.createInt();
                }
            }

            System.out.print("Enter Order Date: ");
            vOrderDate = myApp.createDate();

            System.out.print("Enter Order Details: ");
            vOrderDetails = myApp.createString();

            System.out.print("Enter Order Extra Notes: ");
            vExtraNotes = myApp.createString();

            if (selection == 1) {

                OnlineOrder myOrder = new OnlineOrder(vOrderDate, vOrderDetails, vExtraNotes);
                System.out.print("Enter Order Payment Type: ");
                myOrder.setPaymentType(myApp.createString());
                while (cont != 1) {
                    int control = 0;
                    System.out.print("Enter Staff ID: ");
                    staffID = myApp.createInt();
                    for (i = 0; i < myApp.staffs.size(); i++) {
                        if (myApp.staffs.get(i).getID() == staffID) {
                            if (myApp.staffs.get(i).getClass().getSimpleName().equals("Junior")) {
                                myOrder.setDeliveredBy(((Junior) myApp.staffs.get(i)));
                                cont = 1;
                            } else {
                                System.out.println("Orders only delivered by a junior staff. Please enter a junior staff ID!");
                            }
                            control = 1;
                        }
                    }
                    if(control == 0){
                        System.out.println("Please enter a valid staff ID!");
                    }
                }
                myApp.customers.get(index).getOrders().add(myOrder);
                System.out.println("Order is added to list!");
                myOrder.processPayment();
            } else {
                InRestrOrder myOrder = new InRestrOrder(vOrderDate, vOrderDetails, vExtraNotes);
                System.out.print("Enter Order Table Number: ");
                myOrder.setTableNumber(myApp.createInt());
                selection = 3;
                System.out.print("Do you want to add Booking Information for this order? Press 1 for 'Yes' and Press 2 for 'No'! ");
                while (selection < 1 || selection > 2) {
                    selection = myApp.createInt();
                    if (selection < 1 || selection > 2) {
                        System.out.print("Please select option 1 or 2! ");
                        selection = myApp.createInt();
                    }
                }
                if (selection == 1) {
                    Booking myBooking = new Booking();
                    System.out.print("Enter the Booking Date: ");
                    myBooking.setBookingDate(myApp.createDate());

                    myBooking.setBookingOrder(myOrder);
                    myOrder.setBookingOrder(myBooking);
                    myApp.customers.get(index).getBookings().add(myBooking);
                    myApp.customers.get(index).getOrders().add(myOrder);
                    System.out.println("Order is added to list!");
                    myOrder.processPayment();
                } else {
                    myApp.customers.get(index).getOrders().add(myOrder);
                    System.out.println("Order is added to list!");
                    myOrder.processPayment();
                }
            }

        }*/
    }


    /** It is printing all staffs salary with using getSalary() method.
     * @param myApp - It is for searching staffs.
     */
    public void listAllStaffSalary(RestManApp myApp){
        int i;
        for(i = 0; i < myApp.staffs.size(); i++){
            System.out.println((i+1)+".Staff\n----------------"+"\nStaff ID: " + myApp.staffs.get(i).getID() + "\nStaff Name: " + myApp.staffs.get(i).getName() );
            myApp.staffs.get(i).getSalary();
        }
    }

    /** It is for printing all order in the app.
     * @param myApp - It is for searching customer and printings their order.
     */
    public void listAllOrder(RestManApp myApp){
        int i,j;
        for(i = 0; i < myApp.customers.size(); i++){
            for(j = 0; j < myApp.customers.get(i).getOrders().size(); j++){
                myApp.customers.get(i).getOrders().get(j).printOrderDetails();
            }

        }
    }

    /** It is for adding order for a particular booking. I am getting booking ID and customer ID, searching in the list, If I find them, then I am getting inputs and add order.
     * @param myApp - It is for searching customer and booking lists.
     * @param vBookingID - It is particular booking ID.
     * @param vID - It is ID for searching customer.
     */
    public void addOrderOfBooking(RestManApp myApp, int vBookingID, int vID, InRestrOrder myOrder){

        int i,j,checker = 0;

        for(i = 0; i < myApp.customers.size(); i++){
            if(vID == myApp.customers.get(i).getID()){
                for(j = 0; j < myApp.customers.get(i).getBookings().size(); j++){
                    if(vBookingID == myApp.customers.get(i).getBookings().get(j).getBookingID()){

                        /*InRestrOrder myOrder = new InRestrOrder();
                        System.out.print("Enter Order Date: ");
                        myOrder.setOrderDate(myApp.createDate());

                        System.out.print("Enter Order Details: ");
                        myOrder.setOrderDetails(myApp.createString());

                        System.out.print("Enter Order Extra Notes: ");
                        myOrder.setExtraNotes(myApp.createString());

                        System.out.print("Enter Order Table Number: ");
                        myOrder.setTableNumber(myApp.createInt());*/

                        myOrder.setBookingOrder(myApp.customers.get(i).getBookings().get(j));
                        myApp.customers.get(i).getBookings().get(j).setBookingOrder(myOrder);
                        myApp.customers.get(i).getOrders().add(myOrder);
                        checker = 1;
                    }
                }
            }
        }
        /*if (checker == 0){
            System.out.println("The given Customer ID or Booking ID couldn't found in the list!");
        }*/
    }

    /** It's existing method.
     */
    public void exit(){
        System.exit(0);
    }
    /** It's main method where our program will run. It has simple selection loop.
     *
     * @param args
     */
    public static void main(String[]args) throws IOException, ClassNotFoundException {

        RestManApp myRestManApp = new RestManApp();
        int selection = 0, temp = 0, temp1 =0;
        //PopulateData myPopulateData = new PopulateData();
        //myPopulateData.fillCustomerAndStaff(myRestManApp);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Form form = new Form();
                form.myForm();
            }
        });

        /*

        while(selection != 16){

            myRestManApp.menu();
            System.out.print("Select your operation 1-16! ");
            selection = myRestManApp.createInt();
            switch (selection){
                case 1:
                    myRestManApp.addStaff(myRestManApp);
                    break;
                case 2:
                    System.out.print("\nEnter the Staff's ID: ");
                    temp = myRestManApp.createInt();
                    myRestManApp.deleteStaff(temp, myRestManApp);
                    break;
                case 3:
                    System.out.print("\nEnter the Staff's ID: ");
                    temp = myRestManApp.createInt();
                    myRestManApp.ListStaffDetails(temp, myRestManApp);
                    break;
                case 4:
                    myRestManApp.addCustomer(myRestManApp);
                    break;
                case 5:
                    System.out.print("\nEnter the Customer's ID: ");
                    temp = myRestManApp.createInt();
                    myRestManApp.deleteCustomer(temp, myRestManApp);
                    break;
                case 6:
                    System.out.print("\nEnter Customer's ID: ");
                    temp = myRestManApp.createInt();
                    myRestManApp.addBooking(temp, myRestManApp);
                    break;
                case 7:
                    System.out.print("\nEnter Customer's ID: ");
                    temp = myRestManApp.createInt();
                    myRestManApp.listCustomerDetails(temp,myRestManApp);
                    break;
                case 8:
                    System.out.print("\nEnter Customer's ID: ");
                    temp = myRestManApp.createInt();
                    myRestManApp.displayCustomerLastBooking(myRestManApp,temp);
                    break;
                case 9:
                    System.out.print("\nEnter Customer's ID: ");
                    temp = myRestManApp.createInt();
                    myRestManApp.listCustomerOrders(myRestManApp, temp);
                    break;
                case 10:
                    myRestManApp.listStaff(myRestManApp);
                    break;
                case 11:
                    myRestManApp.listCustomer(myRestManApp);
                    break;
                case 12:
                    System.out.print("\nEnter Customer's ID: ");
                    temp = myRestManApp.createInt();
                    myRestManApp.addOrder(myRestManApp,temp);
                    break;
                case 13:
                    myRestManApp.listAllStaffSalary(myRestManApp);
                    break;
                case 14:
                    myRestManApp.listAllOrder(myRestManApp);
                    break;
                case 15:
                    System.out.print("\nEnter Customer's ID: ");
                    temp = myRestManApp.createInt();
                    System.out.print("Enter Booking's ID: ");
                    temp1 = myRestManApp.createInt();
                    myRestManApp.addOrderOfBooking(myRestManApp,temp1,temp);
                    break;
                case 16:
                    myRestManApp.exit();
                    break;
                default:
                    System.out.print("Please enter a valid option 1-16!: ");
                    selection = myRestManApp.createInt();
            }
        }*/
    }
}

