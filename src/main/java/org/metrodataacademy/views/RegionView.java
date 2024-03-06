package org.metrodataacademy.views;

import org.metrodataacademy.controller.RegionController;
import org.metrodataacademy.models.Region;
import org.metrodataacademy.models.request.CreateRegionRequest;
import org.metrodataacademy.models.request.UpdateRegionRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class RegionView {

    private final List<String> mainMenu = Arrays.asList(
            "1. Get All Regions", "2. Get Region By ID", "3. Searching Region By Name",
            "4. Add Region", "5. Update Region Name", "6. Delete Region By ID");

    private final List<String> secondMenu = Arrays.asList(
            "1. Back To Main Menu", "2. Re-run Program", "99. Exit Program");
    private final RegionController regionController = new RegionController();

    public RegionView() {
        try {
            this.mainView();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void mainView() {
        try {
            Scanner mainView = new Scanner(System.in);

            System.out.println("-----------------------------------------------");
            System.out.println("\tWelcome To Database db_hr Table Region!");
            System.out.println("-----------------------------------------------");
            mainMenu.forEach(region -> System.out.println(region.intern()));
            System.out.println("-----------------------------------------------");
            System.out.println("99. Exit");
            System.out.println();
            System.out.print("==>> ");

            int input = mainView.nextInt();

            switch (input) {
                case 1:
                    System.out.println();
                    getAllRegions();
                    break;
                case 2:
                    System.out.println();
                    getRegionByID();
                    break;
                case 3:
                    System.out.println();
                    searchingRegionByName();
                    break;
                case 4:
                    System.out.println();
                    addRegion();
                    break;
                case 5:
                    System.out.println();
                    updateRegionName();
                    break;
                case 6:
                    System.out.println();
                    deleteRegionByID();
                    break;
                case 99:
                    System.out.println();
                    System.out.println("Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.out.println();
                    System.out.println("Please input an existing numbers!");
                    System.out.println();
                    this.mainView();
                    break;
            }
        } catch (Exception e) {
            System.out.println();
            System.out.println("Error: " + e.getMessage());
            System.out.println();
            this.mainView();
        }
    }

    public void getAllRegions() {
        try {
            Scanner getAll = new Scanner(System.in);

            System.out.println("\t\t\t\t\tGet All Regions");
            System.out.println("---------------------------------------------------------");
            regionController.getAll()
                    .forEach(region -> System.out.println("Region ID: " + region.getId() +
                            "\t| \t" + "Region Name: " + region.getName()));
            System.out.println("---------------------------------------------------------");
            System.out.println("1. Back To Main Menu");
            System.out.println("2. Exit");
            System.out.println("---------------------------------------------------------");
            System.out.println();
            System.out.print("==>> ");
            int input = getAll.nextInt();

            switch (input) {
                case 1:
                    this.mainView();
                    break;
                case 2:
                    System.out.println();
                    System.out.println("Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.out.println();
                    System.out.println("Please input an existing numbers!");
                    System.out.println();
                    this.getAllRegions();
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println();
            this.getAllRegions();
        }
    }

    public void getRegionByID() {
        try {
            Scanner getById = new Scanner(System.in);

            System.out.println("\t\t\t\t\tGet Region By ID");
            System.out.println("---------------------------------------------------------");
            System.out.print("Region ID: ");
            int id = getById.nextInt();
            System.out.println("---------------------------------------------------------");
            System.out.println();

            regionController.getById(id);

            System.out.println();
            System.out.println("---------------------------------------------------------");
            secondMenu.forEach(region -> System.out.println(region.intern()));
            System.out.println("---------------------------------------------------------");
            System.out.println();
            System.out.print("==>> ");
            int input = getById.nextInt();

            switch (input) {
                case 1:
                    this.mainView();
                    break;
                case 2:
                    System.out.println();
                    this.getRegionByID();
                    System.out.println();
                    break;
                case 99:
                    System.out.println();
                    System.out.println("Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.out.println();
                    System.out.println("Please input an existing number!");
                    System.out.println();
                    this.getRegionByID();
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println();
            this.getRegionByID();
        }
    }

    public void searchingRegionByName() {
        try {
            Scanner searchingByName = new Scanner(System.in);

            System.out.println("\t\t\t\tSearching Region By Name");
            System.out.println("---------------------------------------------------------");
            System.out.print("Region Name: ");
            String name = searchingByName.nextLine();
            System.out.println("---------------------------------------------------------");
            System.out.println();

            List<Region> regions = regionController.searchingByName(name);
            regions.forEach(
                    region -> System.out.println("Region ID: " + region.getId() +
                            "\t| \t" + "Region Name: " + region.getName())
            );

            System.out.println();
            System.out.println("---------------------------------------------------------");
            secondMenu.forEach(region -> System.out.println(region.intern()));
            System.out.println("---------------------------------------------------------");
            System.out.println();
            System.out.print("==>> ");
            int input = searchingByName.nextInt();

            switch (input) {
                case 1:
                    this.mainView();
                    break;
                case 2:
                    System.out.println();
                    this.searchingRegionByName();
                    System.out.println();
                    break;
                case 99:
                    System.out.println();
                    System.out.println("Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.out.println();
                    System.out.println("Please input an existing number!");
                    System.out.println();
                    this.searchingRegionByName();
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println();
            this.searchingRegionByName();
        }
    }

    public void addRegion() {
        try {
            Scanner addRegion = new Scanner(System.in);

            System.out.println("\t\t\t\t\t\tAdd Region");
            System.out.println("---------------------------------------------------------");
            System.out.print("Region Name: ");
            String name = addRegion.nextLine();
            System.out.println("---------------------------------------------------------");
            System.out.println();

            CreateRegionRequest request = new CreateRegionRequest(name);
            regionController.create(request);

            System.out.println();
            System.out.println("---------------------------------------------------------");
            secondMenu.forEach(region -> System.out.println(region.intern()));
            System.out.println("---------------------------------------------------------");
            System.out.println();
            System.out.print("==>> ");
            int input = addRegion.nextInt();

            switch (input) {
                case 1:
                    this.mainView();
                    break;
                case 2:
                    System.out.println();
                    this.addRegion();
                    System.out.println();
                    break;
                case 99:
                    System.out.println();
                    System.out.println("Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.out.println();
                    System.out.println("Please input an existing number!");
                    System.out.println();
                    this.addRegion();
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println();
            this.addRegion();
        }
    }

    public void updateRegionName() {
        try {
            Scanner updateRegionName = new Scanner(System.in);

            System.out.println("\t\t\t\tUpdate Region By Name");
            System.out.println("---------------------------------------------------------");
            System.out.println("Region ID is the region ID whose region name you want to update.");
            System.out.print("Region ID: ");
            int id = Integer.parseInt(updateRegionName.nextLine());
            System.out.print("Region Name: ");
            String name = updateRegionName.nextLine();
            System.out.println("---------------------------------------------------------");
            System.out.println();

            UpdateRegionRequest request = UpdateRegionRequest.builder()
                    .name(name)
                    .build();
            regionController.update(request, id);

            System.out.println();
            System.out.println("---------------------------------------------------------");
            secondMenu.forEach(region -> System.out.println(region.intern()));
            System.out.println("---------------------------------------------------------");
            System.out.println();
            System.out.print("==>> ");
            int input = updateRegionName.nextInt();

            switch (input) {
                case 1:
                    this.mainView();
                    break;
                case 2:
                    System.out.println();
                    this.updateRegionName();
                    System.out.println();
                    break;
                case 99:
                    System.out.println();
                    System.out.println("Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.out.println();
                    System.out.println("Please input an existing number!");
                    System.out.println();
                    this.updateRegionName();
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println();
            this.updateRegionName();
        }
    }

    private void deleteRegionByID() {
        try {
            Scanner deleteRegion = new Scanner(System.in);

            System.out.println("\t\t\t\t\tDelete Region By ID");
            System.out.println("---------------------------------------------------------");
            System.out.print("Region ID: ");
            int id = deleteRegion.nextInt();
            System.out.println("---------------------------------------------------------");
            System.out.println();

            regionController.delete(id);

            System.out.println();
            System.out.println("---------------------------------------------------------");
            secondMenu.forEach(region -> System.out.println(region.intern()));
            System.out.println("---------------------------------------------------------");
            System.out.println();
            System.out.print("==>> ");
            int input = deleteRegion.nextInt();

            switch (input) {
                case 1:
                    this.mainView();
                    break;
                case 2:
                    this.deleteRegionByID();
                    System.out.println();
                    break;
                case 99:
                    System.out.println();
                    System.out.println("Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.out.println();
                    System.out.println("Please input an existing number!");
                    System.out.println();
                    this.deleteRegionByID();
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println();
            this.deleteRegionByID();
        }
    }
}