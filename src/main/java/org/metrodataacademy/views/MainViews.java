package org.metrodataacademy.views;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MainViews {

    private final List<String> menuTables = Arrays.asList(
            "1. Table Region", "2. Table Country", "3. Table Location");

    public void play() {
        try {
            Scanner play = new Scanner(System.in);

            System.out.println("-----------------------------------------------");
            System.out.println("\t\t Welcome To HR Oracle Database");
            System.out.println("-----------------------------------------------");
            menuTables.forEach(mainView -> System.out.println(mainView.intern()));
            System.out.println("-----------------------------------------------");
            System.out.println("99. Exit");
            System.out.println();
            System.out.print("===>> ");
            int input = play.nextInt();
            System.out.println();

            switch (input) {
                case 1:
                    RegionView regionView = new RegionView();
                    regionView.mainView();
                    break;
                case 2:
                    CountryView countryView = new CountryView();
                    countryView.mainView();
                    break;
                case 3:
                    LocationView locationView = new LocationView();
                    locationView.mainView();
                    break;
                case 99:
                    System.out.println("Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please input an existing numbers!");
                    System.out.println();
                    this.play();
                    break;
            }
        } catch (Exception e) {
            System.out.println();
            System.out.println("Error: " + e.getMessage());
            System.out.println();
            this.play();
        }
    }
}