package com.core;

import java.io.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.*;
import java.security.MessageDigest;

/** It is our IOStream class where we will do import and export.
 *
 */
public class IOStream {


    /**It is our exportOut method. We are getting path, and export object to that path.
     * @param myApp - object to export.
     * @param path - path to export.
     * @throws IOException
     */
    public void exportOut(RestManApp myApp,String path) throws IOException {

        ObjectOutputStream objectOutputStream  = new ObjectOutputStream(new FileOutputStream(path));
        objectOutputStream.writeObject(myApp);
        objectOutputStream.close();

    }

    /** It is our importIn method. We are getting path, and import data from that path to object.
     * @param path - import from.
     * @return -  return RestManApp object.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public RestManApp importIn(String path) throws IOException, ClassNotFoundException {

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path));

        RestManApp myApp = (RestManApp) objectInputStream.readObject();
        objectInputStream.close();
        return myApp;
   }

    /** It is customerExport method. We are writing objects to external file and also creating MD5 for each of them and write to an external file.
     * @param myApp - It's for taking objects.
     * @param path - Path for customer file.
     * @throws IOException - Throwing exception.
     * @throws NoSuchAlgorithmException - Throwing exception.
     */
   public void customerExport(RestManApp myApp, String path) throws IOException, NoSuchAlgorithmException {

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path));
        /* First writing object to file.*/
        for(int i  = 0; i < myApp.getCustomers().size(); i++){
            objectOutputStream.writeObject(myApp.getCustomers().get(i));
        }
        /* Then, getting that file again and creating MD5 for every object. */
       OutputStream outputStream = new FileOutputStream("MD5.txt");
       for(int i  = 0; i < myApp.getCustomers().size(); i++) {
           ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path));
           MessageDigest securityDigest = MessageDigest.getInstance("MD5");
           ObjectOutputStream oos = null;
           BufferedInputStream bis = new BufferedInputStream(objectInputStream);
           ByteArrayOutputStream baos = new ByteArrayOutputStream();
           oos = new ObjectOutputStream(baos);
           oos.writeObject(myApp.getCustomers().get(i));


           byte[] digest = securityDigest.digest(baos.toByteArray());
           outputStream.write(digest);

           /*StringBuffer hexString = new StringBuffer();
           for(int j = 0; j < digest.length; j++){
              hexString.append(Integer.toHexString(0xFF & digest[j]));
              hexString.append(" ");
           }
           System.out.println(hexString);*/
       }
   }

    /** It is customerImport method. We are importing customers from given file.
     * @param myApp - For putting customer into it.
     * @param path - For getting path of file.
     * @return - Return object.
     * @throws IOException -Throws exception.
     * @throws ClassNotFoundException - Throws exception.
     */
   public RestManApp customerImport(RestManApp myApp, String path) throws IOException, ClassNotFoundException {


        try{
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path));
            while(true){
                Customer myCustomer = (Customer) objectInputStream.readObject();
                myApp.getCustomers().add(myCustomer);
            }
        }
        catch(EOFException eofException){

        }
       return myApp;
   }
}


