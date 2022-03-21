package com.core;

import com.support.Order;

import java.io.*;
import java.nio.file.FileSystemNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;

/** It is our DataStorage class where we will make import and export operations.
 */
public class DataStorage {
    Connection connection = null;
    Statement statement = null;

    /** It is writeData method.
     * We will use this to export our data to database.
     * @param myApp - myApp to get info of objects.
     * @throws SQLException - Throws exception.
     */
    public void writeData(RestManApp myApp) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //System.out.println("Driver loaded");
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
        //System.out.println("Establishing connection");
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "cng443user", "1234");
           // System.out.println("Database connected");
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }

        statement = connection.createStatement();

        /* For all date we will convert util.date to mysql date.*/
        for (int i = 0; i < myApp.getCustomers().size(); i++) {
            String myPersonInfo = "insert into person (id, name, date_of_birth,gender) values (";

            java.sql.Date mySqlDoB = new java.sql.Date(myApp.getCustomers().get(i).getDateOfBirth().getTime());
            /*Insert to person table.*/
            myPersonInfo += myApp.getCustomers().get(i).getID();
            myPersonInfo += ", '";
            myPersonInfo += myApp.getCustomers().get(i).getName();
            myPersonInfo += "', '";
            myPersonInfo += mySqlDoB;
            myPersonInfo += "', '";
            myPersonInfo += myApp.getCustomers().get(i).getGender();
            myPersonInfo += "');";
            //System.out.println(myPersonInfo);
            /*Insert to customer table.*/
            String myCustomerInfo = "insert into customer (customer_id, registrationDate, creditCardDetails) values (";
            java.sql.Date mySqlRegistrationDate = new java.sql.Date(myApp.getCustomers().get(i).getRegistrationDate().getTime());
            myCustomerInfo += myApp.getCustomers().get(i).getID();
            myCustomerInfo += ", '";
            myCustomerInfo += mySqlRegistrationDate;
            myCustomerInfo += "', '";
            myCustomerInfo += myApp.getCustomers().get(i).getCreditCardDetails();
            myCustomerInfo += "');";
            //System.out.println(myCustomerInfo);
            statement.executeUpdate(myPersonInfo);
            statement.executeUpdate(myCustomerInfo);

            for (int j = 0; j < myApp.getCustomers().get(i).getBookings().size(); j++) {
                /*Insert to booking table.*/
                String myBookingInfo = "insert into booking (booking_id, customer_id, bookingDate) values (";
                java.sql.Date mySqlBookingDate = new java.sql.Date(myApp.getCustomers().get(i).getBookings().get(j).getBookingDate().getTime());
                myBookingInfo += myApp.getCustomers().get(i).getBookings().get(j).getBookingID();
                myBookingInfo += ",";
                myBookingInfo += myApp.getCustomers().get(i).getID();
                myBookingInfo += ", '";
                myBookingInfo += mySqlBookingDate;
                myBookingInfo += "');";
                //System.out.println(myBookingInfo);
                statement.executeUpdate(myBookingInfo);

                /*Insert to order table.*/
                String myOrderInfo = "insert into myorder (order_id, customer_id, orderDate, orderDetails, extraNotes) values (";
                java.sql.Date mySqlOrderDate = new java.sql.Date((myApp.getCustomers().get(i).getBookings().get(j).getBookingOrder().getOrderDate().getTime()));
                myOrderInfo += myApp.getCustomers().get(i).getBookings().get(j).getBookingOrder().getOrderID();
                myOrderInfo += ",";
                myOrderInfo += myApp.getCustomers().get(i).getID();
                myOrderInfo += ", '";
                myOrderInfo += mySqlOrderDate;
                myOrderInfo += "', '";
                myOrderInfo += myApp.getCustomers().get(i).getBookings().get(j).getBookingOrder().getOrderDetails();
                myOrderInfo += "', '";
                myOrderInfo += myApp.getCustomers().get(i).getBookings().get(j).getBookingOrder().getExtraNotes();
                myOrderInfo += "');";
                //System.out.println(myOrderInfo);
                statement.executeUpdate(myOrderInfo);
                /*Insert to myInRest table.*/
                String myInRestInfo = "insert into inRestOrder (inRest_order_id, inRest_booking_id, tableNumber) values (";
                myInRestInfo += myApp.getCustomers().get(i).getBookings().get(j).getBookingOrder().getOrderID();
                myInRestInfo += ",";
                myInRestInfo += myApp.getCustomers().get(i).getBookings().get(j).getBookingID();
                myInRestInfo += ",";
                myInRestInfo += ((InRestrOrder) myApp.getCustomers().get(i).getBookings().get(j).getBookingOrder()).getTableNumber();
                myInRestInfo += ");";
                //System.out.println(myInRestInfo);
                statement.executeUpdate(myInRestInfo);


            }
        }


    }

    /** It is our readData method. We will read customer data from database and create MD5 for them.
     * Also, we will get our MD5s from file and check if all of them same or not. If there is extra or missing customers etc.
     * @param myApp - Our app to fill it.
     * @return - Return integer to give result.
     * @throws SQLException - Throws exception.
     * @throws NoSuchAlgorithmException - Throws exception.
     * @throws IOException - Throws exception.
     */
    public int readData(RestManApp myApp) throws SQLException, NoSuchAlgorithmException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //System.out.println("Driver loaded");
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
        //System.out.println("Establishing connection");
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "cng443user", "1234");
            //System.out.println("Database connected");
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
        statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM person \n" +
                "INNER JOIN customer on customer.customer_id = person.id");
        int count = myApp.getCustomers().size();
        for (int i = 0; i < count; i++) {
            myApp.getCustomers().remove(0);
        }

        /* Getting customer info from resultSet. */
        while (resultSet.next()) {
            Customer myCustomer = new Customer();
            //System.out.println(resultSet.getString(1)+ "\t" + resultSet.getString(2)+"\t" + resultSet.getString(3)+"\t"+resultSet.getString(4)+"\t"
            //+    resultSet.getString(5)+"\t"+resultSet.getString(6)+"\t"+resultSet.getString(7));
            java.util.Date myCustomerDoB = new java.util.Date(resultSet.getDate(3).getTime());
            java.util.Date myCustomerRegistrationDate = new java.util.Date(resultSet.getDate(6).getTime());
            myCustomer.setID(resultSet.getInt(1));
            myCustomer.setName(resultSet.getString(2));
            myCustomer.setDateOfBirth(myCustomerDoB);
            myCustomer.setGender(resultSet.getString(4).charAt(0));
            myCustomer.setRegistrationDate(myCustomerRegistrationDate);
            myCustomer.setCreditCardDetails(resultSet.getString(7));
            myApp.getCustomers().add(myCustomer);

        }

        resultSet = statement.executeQuery("SELECT * FROM myorder\n" +
                "INNER JOIN inRestOrder on myorder.order_id = inRestOrder.inRest_order_id\n" +
                "INNER JOIN booking on booking.booking_id = inRestOrder.inRest_booking_id");
        /* Getting booking and order info from resultSet. */
        while (resultSet.next()) {
            Booking myBooking = new Booking();
            InRestrOrder myOrder = new InRestrOrder();
            java.util.Date myBookingDate = new java.util.Date(resultSet.getDate(11).getTime());
            java.util.Date myOrderDate = new java.util.Date(resultSet.getDate(3).getTime());

            myBooking.setBookingID(resultSet.getInt(7));
            myBooking.setBookingDate(myBookingDate);

            myOrder.setOrderID(resultSet.getInt(1));
            myOrder.setOrderDate(myOrderDate);
            myOrder.setOrderDetails(resultSet.getString(4));
            myOrder.setExtraNotes(resultSet.getString(5));
            myOrder.setTableNumber(resultSet.getInt(8));

            myOrder.setBookingOrder(myBooking);
            myBooking.setBookingOrder(myOrder);

            for (int i = 0; i < myApp.getCustomers().size(); i++) {
                if (myApp.getCustomers().get(i).getID() == resultSet.getInt(2)) {
                    myApp.getCustomers().get(i).getBookings().add(myBooking);
                    myApp.getCustomers().get(i).getOrders().add(myOrder);
                }
            }
        }
        /* Getting MD5.txt file and putting all md5 keys to an ArrayList.
           After getting customers information from database, we are creating hash key for each of them and checking if it is in our arraylist or not.
         */
        try {
            FileInputStream fileInputStream = new FileInputStream("MD5.txt");
            int count2 = 0;
            int myReturnIndex = 0;
            ArrayList<String> mySecurity = new ArrayList<String>();
            while (fileInputStream.available() != 0) {

                StringBuffer hexString2 = new StringBuffer();
                for (byte b : fileInputStream.readNBytes(16)) {
                    hexString2.append(Integer.toHexString(0xFF & b));
                    hexString2.append(" ");
                }
                mySecurity.add(String.valueOf(hexString2));
                //System.out.println(mySecurity.get(count2));
            }
            if(myApp.getCustomers().size() != mySecurity.size()){
                myReturnIndex = -1;
                return myReturnIndex;
            }
            for(int i = 0; i < myApp.getCustomers().size(); i++){

                MessageDigest securityDigest = MessageDigest.getInstance("MD5");
                ObjectOutputStream oos = null;
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                oos = new ObjectOutputStream(baos);
                oos.writeObject(myApp.getCustomers().get(count2));
                count2++;

                byte[] digest = securityDigest.digest(baos.toByteArray());

                StringBuffer hexString = new StringBuffer();
                for (int j = 0; j < digest.length; j++) {
                    hexString.append(Integer.toHexString(0xFF & digest[j]));
                    hexString.append(" ");
                }
                //System.out.println(hexString);
                for(int k = 0; k < mySecurity.size(); k++){
                    if(mySecurity.get(k).compareTo(String.valueOf(hexString)) == 0){
                        myReturnIndex++;
                    }
                    else{
                    }
                }

            }

            return myReturnIndex;
        }
        catch(FileSystemNotFoundException exception){

        }
        return 0;
    }
}
