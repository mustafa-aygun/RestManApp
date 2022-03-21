package com.core;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/** It's the class where we initially populate our lists.
 *
 */
public class PopulateData {

    /**It's a simple constructor for PopulateData**/
    public PopulateData(){}

    /**It's the function where I initially fill the application with 3 customer and 3 staff. I am sending the object of RestManApp and fill it with hard method giving array.
     * I created all orders in restaurant because they need to booking according to assignment sheet.
     * @param myApp This for filling it.
     */
    public void fillCustomerAndStaff(RestManApp myApp) {

        String str = "Zaraki Kenpachi@Uzumaki Naruto@Jessica Patterson@Midoriya Izuku@Harvey Specter@Sherlock Holmes";
        String[] NameArray = str.split("@", -2);

        int[] id = new int[]{100, 101, 102, 103, 104, 105};

        String str1 = "10/11/2000@10/11/2001@10/11/1999@10/11/1998@10/11/1997@10/11/1996";
        String[] DateArray = str1.split("@", -2);
        Date[] myBirth = new Date[6];
        for (int i = 0; i < 6; i++)
            try {
                myBirth[i] = new SimpleDateFormat("dd/MM/yyyy").parse(DateArray[i]);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        char[] gender = new char[]{'M', 'M', 'F', 'M', 'M', 'M'};

        String str2 = "08/11/2020@10/09/2020@10/11/2020@";
        String[] registrationArray = str2.split("@", -2);
        Date[] myReg = new Date[3];
        for (int i = 0; i < 3; i++){
            try {
                myReg[i] = new SimpleDateFormat("dd/MM/yyyy").parse(registrationArray[i]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        String str3 = "07/12/2020@06/12/2020@05/12/2020@";
        String[] bookingDate = str3.split("@", -2);
        Date[] myBookingDate = new Date[3];
        for (int i = 0; i < 3; i++){
            try {
                myBookingDate[i] = new SimpleDateFormat("dd/MM/yyyy").parse(bookingDate[i]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        int[] bookingtable = new int[]{10, 11, 12};

        String str4 = "Pizza@Hamburger@Künefe";
        String[] details = str4.split("@", -2);

        String str5 = "Mix Italian Pizza@Chicken Royal with Big Mac souce@Vanilla Ice Cream";
        String[] descriptions = str5.split("@", -2);

        String str6 = "01/01/2018@01/01/2017@01/11/2020";
        String[] startDate = str6.split("@", -2);
        Date[] myStartDate = new Date[3];
        for (int i = 0; i < 3; i++){
            try {
                myStartDate[i] = new SimpleDateFormat("dd/MM/yyyy").parse(startDate[i]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        int[] salary = new int[]{12000, 15000, 900};
        String expectedEnd = "01/01/2021";
        Date myExpectedEnd = new Date();
        try {
           myExpectedEnd = new SimpleDateFormat("dd/MM/yyyy").parse(expectedEnd);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String str7 = "İş Bank Maximum Card@Paypal Mastercard@Garanti Card";
        String[] credicard = str7.split("@", -2);

        String str8 = "07/11/2020@6/11/2020@5/11/2020@";
        String[] orderDate = str8.split("@", -2);
        Date[] myOrderDate = new Date[3];
        for (int i = 0; i < 3; i++){
            try {
                myOrderDate[i] = new SimpleDateFormat("dd/MM/yyyy").parse(orderDate[i]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        for(int i = 0; i < 3; i++){
            InRestrOrder order = new InRestrOrder();
            order.setOrderDetails(details[i]);
            order.setExtraNotes(descriptions[i]);
            order.setTableNumber(bookingtable[i]);
            order.setOrderDate(myOrderDate[i]);


            Booking booking = new Booking();
            booking.setBookingDate(myBookingDate[i]);

            booking.setBookingOrder(order);
            order.setBookingOrder(booking);

            Customer customer = new Customer();
            customer.setID(id[i]);
            customer.setName(NameArray[i]);
            customer.setRegistrationDate(myReg[i]);
            customer.setDateOfBirth(myBirth[i]);
            customer.setGender(gender[i]);
            customer.setCreditCardDetails(credicard[i]);
            customer.getBookings().add(booking);
            customer.getOrders().add(order);
            myApp.getCustomers().add(customer);

            if(i<2){
                Senior senior = new Senior();
                senior.setID(id[i+3]);
                senior.setName(NameArray[i+3]);
                senior.setDateOfBirth(myBirth[i+3]);
                senior.setGender(gender[i+3]);
                senior.setStartDate(myStartDate[i]);
                senior.setGrossSalaryYearly(salary[i]);
                myApp.getStaffs().add(senior);
            }
            else{
                Junior junior = new Junior();
                junior.setID(id[i+3]);
                junior.setName(NameArray[i+3]);
                junior.setDateOfBirth(myBirth[i+3]);
                junior.setGender(gender[i+3]);
                junior.setStartDate(myStartDate[i]);
                junior.setMonthlySalary(salary[i]);
                junior.setExpectedEndDate(myExpectedEnd);
                ((Senior)myApp.getStaffs().get(0)).getResponsibleFrom().add(junior);
                myApp.getStaffs().add(junior);
            }
        }
    }
}
