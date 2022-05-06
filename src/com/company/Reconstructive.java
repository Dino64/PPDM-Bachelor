package com.company;

import java.sql.SQLException;
import java.util.*;

public class Reconstructive {

    public void menu() throws SQLException {
        Scanner scan = new Scanner(System.in);
        int first;

        try {


            do {
                System.out.println();
                System.out.println("      Select which algorithm you want to use      ");

                System.out.println("--------------------");

                System.out.println("1) TDP  ");
                System.out.println("--------------------");
                //System.out.println("2) SDP  ");
                System.out.println("--------------------");
                System.out.println("3) Heuristic  ");
                System.out.println("--------------------");
                System.out.println("4)      Exit        ");
                System.out.println();
                first = scan.nextInt();
                if (first == 1){
                    //heuristic();
                    tdp();

                }else if (first == 2){

                }else if (first == 3){
                    heuristic();
                }
            } while (first != 4);
        }catch (InputMismatchException ex){
//            ex.printStackTrace();
            System.out.println("Please follow the instructions");
            menu();
        }

    }

    public void tdp() throws SQLException {

        Scanner scan = new Scanner(System.in);
//        System.out.println("Before you continue you must choose if you want");
//        System.out.println(" to deduce or add ");
//        System.out.println("1) Deduce 2) Add");
//        System.out.println("--------------------");
        System.out.println("Current database: ");
        System.out.println(dbConnect.getInstance().getValues());
        System.out.println("Please select your age noise vector: ");
        int age = scan.nextInt();
        System.out.println("Please select your Salary noise vector: ");
        int salary = scan.nextInt();
        long start = System.nanoTime();
        dbConnect.getInstance().reconstructiveMinus(age,salary);
        System.out.println(dbConnect.getInstance().getValues());
        long end = System.nanoTime();
        long elapsedTime = end - start;
        System.out.println(elapsedTime);
        System.out.println("proceed ? (y/n)");
        String fin = scan.nextLine();
        if (fin.equals("y")){
            menu();
        }else if (fin.equals("n")){
            System.exit(1);
        }
    }




    public void heuristic() throws SQLException {
        System.out.println("Only works once after that it is distorted forever");
        long start = System.nanoTime();
        //get random numbers
        Random rand = new Random();
        ArrayList<String> age = dbConnect.getInstance().getAge();
        ArrayList<Integer> ageInt = new ArrayList<Integer>();
        ArrayList<String> ageRebrand = new ArrayList<>();
        for(String stringValue : age) {
            try {
                ageInt.add(Integer.parseInt(stringValue));
            } catch(NumberFormatException nfe) {
                System.out.println("Could not parse " + nfe);

            }
        }

        Integer max = Collections.max(ageInt);
        Integer min = Collections.min(ageInt);
        int distortMaximum;
        int distortMinimum;
        ArrayList<Integer> minMax = new ArrayList<>();
        if (max >0 && min>0){
             distortMaximum = max + min;
             if (distortMaximum > 120){
                 int lower = 2;
                 int higher = 17;
                 int inter = rand.nextInt(higher-lower)+lower;
                 distortMaximum = distortMaximum - inter;
             }
            minMax.add(distortMaximum);
            System.out.println(distortMaximum);
        }else {
            System.out.println("Vectors to small");
        }
        if (min > 15){
            int higer2 = 8;
            int lower2 = 2;
            int inter2 = rand.nextInt(higer2-lower2) + lower2;
             distortMinimum = min - inter2;
            minMax.add(distortMinimum);
            System.out.println(distortMinimum);
        }else if (min >=5){
            distortMinimum = min -2;
            minMax.add(distortMinimum);
        }

        for(Integer intValue : minMax) {
            try {
                ageRebrand.add(String.valueOf(intValue));
            } catch(NumberFormatException nfe) {
                System.out.println("Could not parse " + nfe);

            }
        }
        //Send to database
        String maxS = ageRebrand.get(0);
        String minS = ageRebrand.get(1);
        //debug
        System.out.println(maxS);
        System.out.println(minS);

        String ageFin = "[" + minS + "-" + maxS + "]";
        System.out.println(ageFin);


        ArrayList<String> salaryS = dbConnect.getInstance().getSalary();
        ArrayList<Integer> salaryI = new ArrayList<>();
        for(String stringValue : salaryS) {
            try {
                salaryI.add(Integer.parseInt(stringValue));
            } catch(NumberFormatException nfe) {
                System.out.println("Could not parse " + nfe);

            }
        }
        Integer salaryMax = Collections.max(salaryI);
        Integer salaryMin = Collections.min(salaryI);
        System.out.println(salaryMax);
        System.out.println(salaryMin);
        int distortMaximumS;
        int distortMinimumS;
        ArrayList<Integer> minMaxS = new ArrayList<>();
        if (salaryMax >0 && salaryMin>0){
            distortMaximumS = salaryMax + salaryMin;
            if (distortMaximumS > 1200){
                //kan fixa så att 160 är random number :)
                distortMaximumS = distortMaximumS + 160 + salaryMin;
            }
            minMaxS.add(distortMaximumS);
            //debug
            //System.out.println(distortMaximumS);
        }else {
            System.out.println("Vectors to small");
        }
        if (salaryMin > 15000){
            distortMinimumS = salaryMin - 7000;
            minMaxS.add(distortMinimumS);
            //debug
            //System.out.println(distortMinimumS);
        }else if (salaryMin >=500){
            distortMinimumS = salaryMin -200;
            minMaxS.add(distortMinimumS);
        }

        ArrayList<String> salaryRebrand = new ArrayList<>();
        for(Integer intValue : minMaxS) {
            try {
                salaryRebrand.add(String.valueOf(intValue));
            } catch(NumberFormatException nfe) {
                System.out.println("Could not parse " + nfe);

            }
        }
        //Send to database
        String maxSal = salaryRebrand.get(0);
        String minSal = salaryRebrand.get(1);
        //debug
        System.out.println(maxSal);
        System.out.println(minSal);

        String salaryFin = "[" + minSal + "-" + maxSal + "]";
        System.out.println(salaryFin);

        ArrayList<String> professions = new ArrayList<>();
        String all = "[";
        professions = dbConnect.getInstance().getJobs();

        for (String job : professions) {
           all+= job+",";
        }
        all+="]";

        System.out.println(all);
        dbConnect.getInstance().heuristic(ageFin,salaryFin,all);
        System.out.println(dbConnect.getInstance().getAllH());
        long end = System.nanoTime();
        long elapsedTime = end - start;
        System.out.println(elapsedTime);

    }



}


