package com.company;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        Reconstructive reconstructive = new Reconstructive();
        dbConnect.getInstance().connection();
        reconstructive.menu();

        //System.out.println(dbConnect.getInstance().getValues());
       // System.out.println(dbConnect.getInstance().getAll());

    }
}
