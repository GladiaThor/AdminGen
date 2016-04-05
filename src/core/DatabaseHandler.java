/**
 * @author Claxxess <(^_^<)
 */
package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Handles the connection information for the database connection.
 * 
 * @author (>^_^)> Claxxess<(^_^<)
 * @version 1.1
 */
public class DatabaseHandler {

    String ip, port, user, pass, connectionAddress;
    File dBSettings;
    BufferedReader infoReader;


    public DatabaseHandler(String settingPath) throws FileNotFoundException, IOException {
        this.dBSettings = new File("Settings\\" + settingPath + ".txt");
        setUpConnection();
        connectionAddress = "jdbc:mysql://" + ip + ":" + port + "/mysql?"
                + "user=" + user + "&password=" + pass;
    }

    /**
     * Gets the IP for the current DB server from the DBSettings.txt file in the
     * Settings folder.
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void getIP() throws FileNotFoundException, IOException {
        infoReader = new BufferedReader(new FileReader(dBSettings));
        ip = infoReader.readLine();
        infoReader.close();
    }

    /**
     * Gets the port for the current DB server from the DBSettings.txt file in
     * the Settings folder.
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void getPort() throws FileNotFoundException, IOException {
        infoReader = new BufferedReader(new FileReader(dBSettings));
        port = infoReader.readLine();
        port = infoReader.readLine();
        infoReader.close();
    }

    /**
     * Gets the user name for the current DB server from the DBSettings.txt file
     * in the Settings folder.
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void getUser() throws FileNotFoundException, IOException {
        infoReader = new BufferedReader(new FileReader(dBSettings));
        user = infoReader.readLine();
        user = infoReader.readLine();
        user = infoReader.readLine();
        infoReader.close();
    }

    /**
     * Gets the password for the current DB server from the DBSettings.txt file
     * in the Settings folder.
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void getPass() throws FileNotFoundException, IOException {
        infoReader = new BufferedReader(new FileReader(dBSettings));
        pass = infoReader.readLine();
        pass = infoReader.readLine();
        pass = infoReader.readLine();
        pass = infoReader.readLine();
        infoReader.close();
    }

    /**
     * Gets the all info for the current DB server from the DBSettings.txt file
     * in the Settings folder.
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void setUpConnection() throws FileNotFoundException, IOException {
        infoReader = new BufferedReader(new FileReader(dBSettings));
        ip = infoReader.readLine();
        port = infoReader.readLine();
        user = infoReader.readLine();
        pass = infoReader.readLine();
        infoReader.close();
    }

    /**
     * Resolves the incoming update and returns an Array List reporting whether
     * or not the update was successful.
     *
     * @param incommingUpdate
     * @return an arrayList of the server responses.
     */
    public ArrayList<String> updater(String incommingUpdate) {
        ArrayList<String> status = new ArrayList();
        try {

            //setup connection
            Class.forName("com.mysql.jdbc.Driver");
            Connection serverConnection = DriverManager.getConnection(connectionAddress);
            //establish create query
            String update = incommingUpdate.replace("'null'", "null").replace("''", "null");

            //create statement
            Statement statement = serverConnection.createStatement();

            //execute statement
            statement.executeUpdate(update);

            //Add sucess of the string update to ArrayList
            status.add("Update Successful");

        } catch (ClassNotFoundException e) {
            //Add failiure of update and relevant reason to ArrayList
            status.add("Update Failed:" + e);
        } catch (java.sql.SQLException e) {
            //Add failiure of update and relevant reason to ArrayList
            status.add("Update Failed:" + e);
        }
        //Retun Update status
        return (status);
    }

    /**
     * Sends the incoming query to the database.
     *
     * @param incommingDelete incoming query
     * @return [delete success/fail]
     */
    public ArrayList<String> delete(String incommingDelete) {
        ArrayList<String> status = new ArrayList();
        try {

            //setup connection
            Class.forName("com.mysql.jdbc.Driver");
            Connection serverConnection = DriverManager.getConnection(connectionAddress);

            // create query
            String delete = incommingDelete;

            //create statement
            Statement statement = serverConnection.createStatement();

            //execute statement
            statement.execute(delete);
            status.add("Delete Successful");
        } catch (ClassNotFoundException e) {
            status.add("Delete Failed:" + e);
        } catch (SQLException e) {
            //Do nothing
            status.add("Delete Failed:" + e);
        }

        return (status);
    }

    /**
     * Resolves the incoming query and returns an Array List with the results.
     *
     * @param incommingQuery
     * @return Query Results
     */
    public ArrayList<String> query(String incommingQuery) {

        ArrayList<String> results = new ArrayList<String>();
        try {
            //setup connection
            Class.forName("com.mysql.jdbc.Driver");

            Connection serverConnection = DriverManager.getConnection(connectionAddress);
            //establish create query
            String query = incommingQuery;

            //create statement
            Statement statement = serverConnection.createStatement();

            //execute statement
            ResultSet statementResult = statement.executeQuery(query);

            int columnInt = 1;
            ResultSetMetaData resultsMeta = statementResult.getMetaData();
            int metaCountInt = resultsMeta.getColumnCount();
            //Build results into an ArrayList
            while (statementResult.next()) {
                while (columnInt <= metaCountInt) {
                    results.add(statementResult.getString(columnInt));
                    columnInt++;
                }
                columnInt = 1;
            }

            statementResult.close();
            statement.close();

        } catch (ClassNotFoundException e) {
            System.out.println("Query Failed:" + e);
        } catch (java.sql.SQLException e) {
            System.out.println("Query Failed:" + e);
        }

        //Return Query Results
        return (results);
    }

    /**
     * Inserts value to database
     *
     * @param incommingInsert the incoming query
     * @return [insert success/fail]
     */
    public ArrayList<String> insertHandler(String incommingInsert) {
        ArrayList<String> status = new ArrayList();
        try {
            System.out.println("EXECUTING QUERY: : "+incommingInsert );
            Class.forName("com.mysql.jdbc.Driver");
            Connection serverConnection = DriverManager.getConnection(connectionAddress);

            //establish create query
            String insert = incommingInsert.replace("'null'", "null").replace("''", "null");

            //creat statement
            Statement statement = serverConnection.createStatement();

            //execute statement
            statement.executeUpdate(insert);
            status.add("Insert Successful");
        } catch (ClassNotFoundException classExceptionString) {

            status.add("Insert Failed:" + classExceptionString);
        } catch (SQLException exp) {
            //Do nothing
            System.out.println(exp);
            status.add("Insert Failed:" + exp);
        }
        return (status);
    }

}
