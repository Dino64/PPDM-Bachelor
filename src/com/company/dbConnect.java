package com.company;

import java.sql.*;
import java.util.ArrayList;

public class dbConnect {
    private Statement statement = null;
    private ResultSet resultSet;
    private Connection connection = null;
    private static dbConnect single_instance;
    private PreparedStatement prep = null;

    public static dbConnect getInstance() {
        if (single_instance == null) {
            single_instance = new dbConnect();
        }
        return single_instance;
    }

    public void connection() throws SQLException {

        String url = "jdbc:mysql://127.0.0.1:3306/ppdmhtest?user=root&password=root";
        try {
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();

        }catch (Exception ex){
            System.out.println("ERROR not connect");
            ex.printStackTrace();
        }
        System.out.println("connected to database");


    }

    public ArrayList<String> getAll() throws SQLException {
        ArrayList<String> p = new ArrayList<>();
        statement = connection.createStatement();
        try {
            ResultSet rs = statement.executeQuery("select idPeople, Name,Age,Ailment,Job,Salary,Jobid from people , profession where Jobid = Profession_Jobid ;");
            while (rs.next()) {
                p.add("IdPeople: " + rs.getInt(1));
                p.add("Name: " + rs.getString(2));
                p.add("\nAge: " + rs.getInt(3));
                p.add("\nAilment: " + rs.getString(4));
                p.add("\nJob: " + rs.getString(5));
                p.add("\nSalary: " + rs.getInt(6));
                p.add("\nJobId: " + rs.getInt(7));
                p.add("\n------------\n");
//                System.out.println("DEBUG:" + p);

            }
        } catch (Exception ex) {
            System.out.println("get all failed");
            ex.printStackTrace();
        }
        return p;
    }

    public ArrayList<String> getAllH() throws SQLException {
        ArrayList<String> p = new ArrayList<>();
        statement = connection.createStatement();
        try {
            ResultSet rs = statement.executeQuery("select idPeople, Name,Age,Ailment,Job,Salary,Jobid from people , profession where Jobid = Profession_Jobid ;");
            while (rs.next()) {
                p.add("IdPeople: " + rs.getInt(1));
                p.add("Name: " + rs.getString(2));
                p.add("\nAge: " + rs.getString(3));
                p.add("\nAilment: " + rs.getString(4));
                p.add("\nJob: " + rs.getString(5));
                p.add("\nSalary: " + rs.getString(6));
                p.add("\nJobId: " + rs.getInt(7));
                p.add("\n------------\n");
//                System.out.println("DEBUG:" + p);

            }
        } catch (Exception ex) {
            System.out.println("get all failed");
            ex.printStackTrace();
        }
        return p;
    }

    public ArrayList<String> getValues() throws SQLException {
        ArrayList<String> val = new ArrayList<>();
        statement = connection.createStatement();
        try {
            resultSet = statement.executeQuery("select Age, Salary from people,profession where Jobid = Profession_Jobid;");
            while (resultSet.next()){
                val.add("\nAge: " + resultSet.getInt(1));
                val.add("Salary: " + resultSet.getInt(2));
            }
        }catch (Exception ex){
            System.out.println("Get values failed");
            ex.printStackTrace();
        }

        return val;
    }

    public void reconstructive(int num1, int num2) throws SQLException {
        statement.executeUpdate("update people set age = age +'" +num1 +"'");
        statement.executeUpdate("update profession set Salary = Salary +'" +num2 +"'");
    }

    public void heuristic(String age, String salary, String proffesion) throws SQLException {
        try {
            statement.executeUpdate("update people set age = '" +age +"'");
            statement.executeUpdate("update profession set Salary = '" + salary +"'");
            statement.executeUpdate("update profession set job = '" + proffesion +"'");
        }catch (Exception ex){
            System.out.println("caught");
           // System.out.println(dbConnect.getInstance().getAll());
        }


    }
//    public void heuristic2(String age) throws SQLException {
//        //String query = "INSERT INTO User (Firstname, Lastname, email, SSN, password,accesID) VALUES (?, ?, ?, ?, ?,?)";
//        String query2 = "UPDATE people (age)  VALUES (?)";
//        prep = connection.prepareStatement(query2);
//        prep.setString(1,age);
//
//    }

    public void reconstructiveMinus(int num1, int num2) throws SQLException {
        statement.executeUpdate("update people set age = age -'" +num1 +"'");
        statement.executeUpdate("update profession set Salary = Salary -'" +num2 +"'");
    }

    public ArrayList<String> getAge() throws SQLException {
        ArrayList<String> age = new ArrayList<>();
        statement = connection.createStatement();
        try {
            resultSet = statement.executeQuery("select Age from people,profession where Jobid = Profession_Jobid;");
            while (resultSet.next()){
                age.add(resultSet.getString(1));
            }
        }catch (Exception ex){
            System.out.println("Get age failed");
            ex.printStackTrace();
        }

        return age;

    }
    public ArrayList<String> getSalary() throws SQLException {
        ArrayList<String> salary = new ArrayList<>();
        statement = connection.createStatement();
        try {
            resultSet = statement.executeQuery("select Salary from people,profession where Jobid = Profession_Jobid;");
            while (resultSet.next()){
                salary.add(resultSet.getString(1));
            }
        }catch (Exception ex){
            System.out.println("Get salary failed");
            ex.printStackTrace();
        }

        return salary;

    }

    public ArrayList<String> getJobs() throws SQLException {
        ArrayList<String> jobs = new ArrayList<>();
        statement = connection.createStatement();
        try {
            resultSet = statement.executeQuery("select job from profession;");
            while (resultSet.next()){
                jobs.add(resultSet.getString(1));
            }
        }catch (Exception ex){
            System.out.println("Get salary failed");
            ex.printStackTrace();
        }

        return jobs;
    }




}
