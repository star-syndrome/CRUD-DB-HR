package org.metrodataacademy.views;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.metrodataacademy.controllers.LocationController;
import org.metrodataacademy.models.Location;
import org.metrodataacademy.models.request.CreateLocationRequest;
import org.metrodataacademy.models.request.UpdateLocationRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
@NoArgsConstructor
public class LocationView {

    private final List<String> mainMenu = Arrays.asList(
            "1. Get All Locations", "2. Get Location By ID", "3. Searching Location By City",
            "4. Searching Location By Country Name", "5. Add Location", "6. Update Location",
            "7. Delete Location By ID");

    private final List<String> secondMenu = Arrays.asList(
            "1. Back To Main Menu", "2. Re-run Program", "99. Exit Program");

    private LocationController locationController = new LocationController();

    public void mainView() {
        try {
            Scanner mainView = new Scanner(System.in);
            MainViews mainViews = new MainViews();

            System.out.println("-----------------------------------------------");
            System.out.println("\t\t\tWelcome To Table Location!");
            System.out.println("-----------------------------------------------");
            mainMenu.forEach(location -> System.out.println(location.intern()));
            System.out.println("-----------------------------------------------");
            System.out.println("98. Back To Main Views");
            System.out.println("99. Exit");
            System.out.println();
            System.out.print("==>> ");
            int input = mainView.nextInt();
            System.out.println();

            switch (input) {
                case 1:
                    getAllLocations();
                    break;
                case 2:
                    getByID();
                    break;
                case 3:
                    searchingByCity();
                    break;
                case 4:
                    searchingByCountry();
                    break;
                case 5:
                    addLocation();
                    break;
                case 6:
                    updateLocation();
                    break;
                case 7:
                    deleteLocation();
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

    public void getAllLocations() {
        try {
            Scanner getAll = new Scanner(System.in);

            System.out.println("\t\t\t\t\tGet All Locations");
            System.out.println("---------------------------------------------------------");
            System.out.print("Limit: ");
            int limit = getAll.nextInt();
            System.out.print("Offset: ");
            int offset = getAll.nextInt();
            System.out.println("---------------------------------------------------------");
            locationController.getAll(limit, offset).forEach(
                    location -> System.out.println(
                            "ID: " + location.getId() + ",  Street Address: " + location.getStreetAddress() +
                            ",  \nPostal Code: " + location.getPostalCode() + ",  City: " + location.getCity() +
                            ",  State Province: " + location.getStateProvince() + ",  \nCountry: " + location.getCountry()
                            .getName() + ",  Region: " + location.getCountry().getRegion().getName() + "\n"));
            System.out.println("---------------------------------------------------------");
            System.out.println("Total size of locations: " + locationController.getSizeOfLocations());
            secondMenu();
            int input = getAll.nextInt();
            System.out.println();

            switch (input) {
                case 1:
                    this.mainView();
                    break;
                case 2:
                    this.getAllLocations();
                    System.out.println();
                    break;
                case 99:
                    System.out.println("Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please input an existing numbers!");
                    System.out.println();
                    this.getAllLocations();
                    break;
            }
        } catch (Exception e) {
            System.out.println();
            System.out.println("Error: " + e.getMessage());
            System.out.println();
            this.getAllLocations();
        }
    }

    public void getByID() {
        try {
            Scanner getById = new Scanner(System.in);

            System.out.println("\t\t\t\t\tGet Location By ID");
            System.out.println("---------------------------------------------------------");
            System.out.print("Location ID: ");
            int id = getById.nextInt();
            System.out.println("---------------------------------------------------------");

            System.out.println();
            locationController.getByID(id);
            System.out.println();

            secondMenu();
            int input = getById.nextInt();
            System.out.println();

            switch (input) {
                case 1:
                    this.mainView();
                    break;
                case 2:
                    this.getByID();
                    System.out.println();
                    break;
                case 99:
                    System.out.println("Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please input an existing number!");
                    System.out.println();
                    this.getByID();
                    break;
            }
        } catch (Exception e) {
            System.out.println();
            System.out.println("Error: " + e.getMessage());
            System.out.println();
            this.getByID();
        }
    }

    public void searchingByCity() {
        try {
            Scanner searchingByCity = new Scanner(System.in);

            System.out.println("\t\t\t\tSearching Location By City");
            System.out.println("---------------------------------------------------------");
            System.out.print("City: ");
            String city = searchingByCity.nextLine();
            System.out.println("---------------------------------------------------------");

            System.out.println();
            List<Location> locations = locationController.searchingByCity(city);
            locations.forEach(
                    location -> System.out.println(
                            "ID: " + location.getId() + ",  Street Address: " + location.getStreetAddress() +
                            ",  \nPostal Code: " + location.getPostalCode() + ",  City: " + location.getCity() +
                            ",  State Province: " + location.getStateProvince() + ",  \nCountry: " + location.getCountry()
                            .getName() + ",  Region: " + location.getCountry().getRegion().getName() + "\n"));
            System.out.println();

            secondMenu();
            int input = searchingByCity.nextInt();
            System.out.println();

            switch (input) {
                case 1:
                    this.mainView();
                    break;
                case 2:
                    this.searchingByCity();
                    System.out.println();
                    break;
                case 99:
                    System.out.println("Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please input an existing number!");
                    System.out.println();
                    this.searchingByCity();
                    break;
            }
        } catch (Exception e) {
            System.out.println();
            System.out.println("Error: " + e.getMessage());
            System.out.println();
            this.searchingByCity();
        }
    }

    public void searchingByCountry() {
        try {
            Scanner searchingByCountry = new Scanner(System.in);

            System.out.println("\t\t\tSearching Location By Country Name");
            System.out.println("---------------------------------------------------------");
            System.out.print("Country Name: ");
            String name = searchingByCountry.nextLine();
            System.out.println("---------------------------------------------------------");

            System.out.println();
            List<Location> locations = locationController.searchingByCountryName(name);
            locations.forEach(
                    location -> System.out.println(
                            "ID: " + location.getId() + ",  Street Address: " + location.getStreetAddress() +
                                    ",  \nPostal Code: " + location.getPostalCode() + ",  City: " + location.getCity() +
                                    ",  State Province: " + location.getStateProvince() + ",  \nCountry: " + location.getCountry()
                                    .getName() + ",  Region: " + location.getCountry().getRegion().getName() + "\n"));
            System.out.println();

            secondMenu();
            int input = searchingByCountry.nextInt();
            System.out.println();

            switch (input) {
                case 1:
                    this.mainView();
                    break;
                case 2:
                    this.searchingByCountry();
                    System.out.println();
                    break;
                case 99:
                    System.out.println("Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please input an existing number!");
                    System.out.println();
                    this.searchingByCountry();
                    break;
            }
        } catch (Exception e) {
            System.out.println();
            System.out.println("Error: " + e.getMessage());
            System.out.println();
            this.searchingByCountry();
        }
    }

    public void addLocation() {
        try {
            Scanner createLocation = new Scanner(System.in);

            System.out.println("\t\t\t\t\t\tAdd Location");
            System.out.println("---------------------------------------------------------");
            System.out.print("Location ID: ");
            int id = Integer.parseInt(createLocation.nextLine());
            System.out.print("Street Address: ");
            String street = createLocation.nextLine();
            System.out.print("Postal Code: ");
            String postal = createLocation.nextLine();
            System.out.print("City: ");
            String city = createLocation.nextLine();
            System.out.print("State Province: ");
            String province = createLocation.nextLine();
            System.out.print("Country ID: ");
            String countryID = createLocation.nextLine();
            System.out.println("---------------------------------------------------------");

            System.out.println();
            CreateLocationRequest createLocationRequest = CreateLocationRequest.builder()
                    .id(id)
                    .streetAddress(street)
                    .postalCode(postal)
                    .city(city)
                    .stateProvince(province)
                    .countryID(countryID.toUpperCase())
                    .build();
            locationController.create(createLocationRequest);
            System.out.println();

            secondMenu();
            int input = createLocation.nextInt();
            System.out.println();

            switch (input) {
                case 1:
                    this.mainView();
                    break;
                case 2:
                    this.addLocation();
                    System.out.println();
                    break;
                case 99:
                    System.out.println("Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please input an existing number!");
                    System.out.println();
                    this.addLocation();
                    break;
            }
        } catch (Exception e) {
            System.out.println();
            System.out.println("Error: " + e.getMessage());
            System.out.println();
            this.addLocation();
        }
    }

    public void updateLocation() {
        try {
            Scanner updateLoc = new Scanner(System.in);

            System.out.println("\t\t\t\t\tUpdate Location");
            System.out.println("---------------------------------------------------------");
            System.out.println("Location ID is the location ID whose location you want to update.");
            System.out.print("Location ID: ");
            int id = Integer.parseInt(updateLoc.nextLine());
            System.out.print("Street Address: ");
            String street = updateLoc.nextLine();
            System.out.print("Postal Code: ");
            String postal = updateLoc.nextLine();
            System.out.print("City: ");
            String city = updateLoc.nextLine();
            System.out.print("State Province: ");
            String province = updateLoc.nextLine();
            System.out.println("---------------------------------------------------------");

            System.out.println();
            UpdateLocationRequest updateLocationRequest = UpdateLocationRequest.builder()
                    .id(id)
                    .streetAddress(street)
                    .postalCode(postal)
                    .city(city)
                    .stateProvince(province)
                    .build();
            locationController.update(updateLocationRequest);
            System.out.println();

            secondMenu();
            int input = updateLoc.nextInt();
            System.out.println();

            switch (input) {
                case 1:
                    this.mainView();
                    break;
                case 2:
                    this.updateLocation();
                    System.out.println();
                    break;
                case 99:
                    System.out.println("Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please input an existing number!");
                    System.out.println();
                    this.updateLocation();
                    break;
            }
        } catch (Exception e) {
            System.out.println();
            System.out.println("Error: " + e.getMessage());
            System.out.println();
            this.updateLocation();
        }
    }

    public void deleteLocation() {
        try {
            Scanner deleteLoc = new Scanner(System.in);

            System.out.println("\t\t\t\t\tDelete Location By ID");
            System.out.println("---------------------------------------------------------");
            System.out.print("Location ID: ");
            int id = deleteLoc.nextInt();
            System.out.println("---------------------------------------------------------");

            System.out.println();
            locationController.delete(id);
            System.out.println();

            secondMenu();
            int input = deleteLoc.nextInt();
            System.out.println();

            switch (input) {
                case 1:
                    this.mainView();
                    break;
                case 2:
                    this.deleteLocation();
                    System.out.println();
                    break;
                case 99:
                    System.out.println("Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please input an existing number!");
                    System.out.println();
                    this.deleteLocation();
                    break;
            }
        } catch (Exception e) {
            System.out.println();
            System.out.println("Error: " + e.getMessage());
            System.out.println();
            this.deleteLocation();
        }
    }

    public void secondMenu() {
        System.out.println("---------------------------------------------------------");
        secondMenu.forEach(location -> System.out.println(location.intern()));
        System.out.println("---------------------------------------------------------");
        System.out.print("==>> ");
    }
}