package org.metrodataacademy.views;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.metrodataacademy.controllers.RegionController;
import org.metrodataacademy.models.Region;
import org.metrodataacademy.models.request.CreateRegionRequest;
import org.metrodataacademy.models.request.UpdateRegionRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
@NoArgsConstructor
public class RegionView {

    private final List<String> mainMenu = Arrays.asList(
            "1. Get All Regions", "2. Get Region By ID", "3. Searching Region By Name",
            "4. Add Region", "5. Update Region Name", "6. Delete Region By ID");

    private final List<String> secondMenu = Arrays.asList(
            "1. Back To Main Menu", "2. Re-run Program", "99. Exit Program");

    private RegionController regionController = new RegionController();

    public void mainView() {
        try {
            Scanner mainView = new Scanner(System.in);
            MainViews mainViews = new MainViews();

            System.out.println("-----------------------------------------------");
            System.out.println("\t\t\tWelcome To Table Region!");
            System.out.println("-----------------------------------------------");
            mainMenu.forEach(region -> System.out.println(region.intern()));
            System.out.println("-----------------------------------------------");
            System.out.println("98. Back To Main Views");
            System.out.println("99. Exit");
            System.out.println();
            System.out.print("==>> ");
            int input = mainView.nextInt();
            System.out.println();

            switch (input) {
                case 1:
                    getAllRegions();
                    break;
                case 2:
                    getRegionByID();
                    break;
                case 3:
                    searchingRegionByName();
                    break;
                case 4:
                    addRegion();
                    break;
                case 5:
                    updateRegionName();
                    break;
                case 6:
                    deleteRegionByID();
                    break;
                case 98:
                    mainViews.play();
                    break;
                case 99:
                    System.out.println("Thank you!");
                    System.exit(0);
                    break;
                default:
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
                    .forEach(region -> System.out.println("ID: " + region.getId() +
                            ",  Name: " + region.getName()));
            System.out.println("---------------------------------------------------------");
            System.out.println("1. Back To Main Menu");
            System.out.println("2. Exit");
            System.out.println("---------------------------------------------------------");
            System.out.print("==>> ");
            int input = getAll.nextInt();
            System.out.println();

            switch (input) {
                case 1:
                    this.mainView();
                    break;
                case 2:
                    System.out.println("Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please input an existing numbers!");
                    System.out.println();
                    this.getAllRegions();
                    break;
            }
        } catch (Exception e) {
            System.out.println();
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

            secondMenu();
            int input = getById.nextInt();
            System.out.println();

            switch (input) {
                case 1:
                    this.mainView();
                    break;
                case 2:
                    this.getRegionByID();
                    System.out.println();
                    break;
                case 99:
                    System.out.println("Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please input an existing number!");
                    System.out.println();
                    this.getRegionByID();
                    break;
            }
        } catch (Exception e) {
            System.out.println();
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
            String name = searchingByName .nextLine();
            System.out.println("---------------------------------------------------------");

            System.out.println();
            List<Region> regions = regionController.searchingByName(name);
            regions.forEach(
                    region -> System.out.println("ID: " + region.getId() + ",  Name: " + region.getName())
            );
            System.out.println();

            secondMenu();
            int input = searchingByName.nextInt();
            System.out.println();

            switch (input) {
                case 1:
                    this.mainView();
                    break;
                case 2:
                    this.searchingRegionByName();
                    System.out.println();
                    break;
                case 99:
                    System.out.println("Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please input an existing number!");
                    System.out.println();
                    this.searchingRegionByName();
                    break;
            }
        } catch (Exception e) {
            System.out.println();
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

            secondMenu();
            int input = addRegion.nextInt();
            System.out.println();

            switch (input) {
                case 1:
                    this.mainView();
                    break;
                case 2:
                    this.addRegion();
                    System.out.println();
                    break;
                case 99:
                    System.out.println("Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please input an existing number!");
                    System.out.println();
                    this.addRegion();
                    break;
            }
        } catch (Exception e) {
            System.out.println();
            System.out.println("Error: " + e.getMessage());
            System.out.println();
            this.addRegion();
        }
    }

    public void updateRegionName() {
        try {
            Scanner updateRegionName = new Scanner(System.in);

            System.out.println("\t\t\t\t\tUpdate Region Name");
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
                    .id(id)
                    .build();
            regionController.update(request);
            System.out.println();

            secondMenu();
            int input = updateRegionName.nextInt();
            System.out.println();

            switch (input) {
                case 1:
                    this.mainView();
                    break;
                case 2:
                    this.updateRegionName();
                    System.out.println();
                    break;
                case 99:
                    System.out.println("Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please input an existing number!");
                    System.out.println();
                    this.updateRegionName();
                    break;
            }
        } catch (Exception e) {
            System.out.println();
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

            secondMenu();
            int input = deleteRegion.nextInt();
            System.out.println();

            switch (input) {
                case 1:
                    this.mainView();
                    break;
                case 2:
                    this.deleteRegionByID();
                    System.out.println();
                    break;
                case 99:
                    System.out.println("Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please input an existing number!");
                    System.out.println();
                    this.deleteRegionByID();
                    break;
            }
        } catch (Exception e) {
            System.out.println();
            System.out.println("Error: " + e.getMessage());
            System.out.println();
            this.deleteRegionByID();
        }
    }

    public void secondMenu() {
        System.out.println("---------------------------------------------------------");
        secondMenu.forEach(region -> System.out.println(region.intern()));
        System.out.println("---------------------------------------------------------");
        System.out.print("==>> ");
    }
}