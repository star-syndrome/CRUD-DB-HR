package org.metrodataacademy.views;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.metrodataacademy.controllers.CountryController;
import org.metrodataacademy.models.Country;
import org.metrodataacademy.models.request.CreateCountryRequest;
import org.metrodataacademy.models.request.UpdateCountryRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
@NoArgsConstructor
public class CountryView {

    private final List<String> mainMenu = Arrays.asList(
            "1. Get All Countries", "2. Get Country By ID", "3. Searching Country By Name",
            "4. Searching Country By Region Name", "5. Add Country", "6. Update Country Name",
            "7. Delete Country By ID");

    private final List<String> secondMenu = Arrays.asList(
            "1. Back To Main Menu", "2. Re-run Program", "99. Exit Program");

    private CountryController countryController = new CountryController();

    public void mainView() {
        try {
            Scanner mainView = new Scanner(System.in);
            MainViews mainViews = new MainViews();

            System.out.println("-----------------------------------------------");
            System.out.println("\t\t\tWelcome To Table Country!");
            System.out.println("-----------------------------------------------");
            mainMenu.forEach(country -> System.out.println(country.intern()));
            System.out.println("-----------------------------------------------");
            System.out.println("98. Back To Main Views");
            System.out.println("99. Exit");
            System.out.println();
            System.out.print("==>> ");
            int input = mainView.nextInt();
            System.out.println();

            switch (input) {
                case 1:
                    getAllCountries();
                    break;
                case 2:
                    getCountryByID();
                    break;
                case 3:
                    searchingCountryByName();
                    break;
                case 4:
                    searchingCountryByRegionName();
                    break;
                case 5:
                    createCountry();
                    break;
                case 6:
                    updateCountry();
                    break;
                case 7:
                    deleteCountryByID();
                    break;
                case 98:
                    mainViews.play();
                    break;
                case 99:
                    System.out.println("Thank you!");
                    System.exit(0);
                    break;
                default: ;
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

    public void getAllCountries() {
        try {
            Scanner getAll = new Scanner(System.in);

            System.out.println("\t\t\t\t\tGet All Countries");
            System.out.println("---------------------------------------------------------");
            System.out.print("Limit: ");
            int limit = getAll.nextInt();
            System.out.print("Offset: ");
            int offset = getAll.nextInt();
            System.out.println("---------------------------------------------------------");
            countryController.getAll(limit, offset).forEach(
                    country -> System.out.println("ID: " + country.getId() + ",  Country: " +
                            country.getName() + ",  Region: " + country.getRegion().getName()));
            System.out.println("---------------------------------------------------------");
            System.out.println("Total size of countries: " + countryController.getSizeOfCountries());
            secondMenu();
            int input = getAll.nextInt();
            System.out.println();

            switch (input) {
                case 1:
                    this.mainView();
                    break;
                case 2:
                    this.getAllCountries();
                    System.out.println();
                    break;
                case 99:
                    System.out.println("Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please input an existing numbers!");
                    System.out.println();
                    this.getAllCountries();
                    break;
            }
        } catch (Exception e) {
            System.out.println();
            System.out.println("Error: " + e.getMessage());
            System.out.println();
            this.getAllCountries();
        }
    }

    public void getCountryByID() {
        try {
            Scanner getById = new Scanner(System.in);

            System.out.println("\t\t\t\t\tGet Country By ID");
            System.out.println("---------------------------------------------------------");
            System.out.print("Country ID: ");
            String id = getById.nextLine();
            System.out.println("---------------------------------------------------------");

            System.out.println();
            countryController.getByID(id);
            System.out.println();

            secondMenu();
            int input = getById.nextInt();
            System.out.println();

            switch (input) {
                case 1:
                    this.mainView();
                    break;
                case 2:
                    this.getCountryByID();
                    System.out.println();
                    break;
                case 99:
                    System.out.println("Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please input an existing number!");
                    System.out.println();
                    this.getCountryByID();
                    break;
            }
        } catch (Exception e) {
            System.out.println();
            System.out.println("Error: " + e.getMessage());
            System.out.println();
            this.getCountryByID();
        }
    }

    public void searchingCountryByRegionName() {
        try {
            Scanner searchingByName = new Scanner(System.in);

            System.out.println("\t\t\tSearching Country By Region Name");
            System.out.println("---------------------------------------------------------");
            System.out.print("Region Name: ");
            String name = searchingByName.nextLine();
            System.out.println("---------------------------------------------------------");

            System.out.println();
            List<Country> countries = countryController.searchingByRegionName(name);
            countries.forEach(
                    country -> System.out.println("ID: " + country.getId() + ",  Country: " +
                            country.getName() + ",  Region: " + country.getRegion().getName()
            ));
            System.out.println();

            secondMenu();
            int input = searchingByName.nextInt();
            System.out.println();

            switch (input) {
                case 1:
                    this.mainView();
                    break;
                case 2:
                    this.searchingCountryByRegionName();
                    System.out.println();
                    break;
                case 99:
                    System.out.println("Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please input an existing number!");
                    System.out.println();
                    this.searchingCountryByRegionName();
                    break;
            }
        } catch (Exception e) {
            System.out.println();
            System.out.println("Error: " + e.getMessage());
            System.out.println();
            this.searchingCountryByRegionName();
        }
    }

    public void searchingCountryByName() {
        try {
            Scanner searchingByName = new Scanner(System.in);

            System.out.println("\t\t\t\tSearching Country By Name");
            System.out.println("---------------------------------------------------------");
            System.out.print("Country Name: ");
            String name = searchingByName .nextLine();
            System.out.println("---------------------------------------------------------");

            System.out.println();
            List<Country> countries = countryController.searchingByName(name);
            countries.forEach(
                    country -> System.out.println("ID: " + country.getId() + ",  Country: " +
                            country.getName() + ",  Region: " + country.getRegion().getName()));
            System.out.println();

            secondMenu();
            int input = searchingByName.nextInt();
            System.out.println();

            switch (input) {
                case 1:
                    this.mainView();
                    break;
                case 2:
                    this.searchingCountryByName();
                    System.out.println();
                    break;
                case 99:
                    System.out.println("Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please input an existing number!");
                    System.out.println();
                    this.searchingCountryByName();
                    break;
            }
        } catch (Exception e) {
            System.out.println();
            System.out.println("Error: " + e.getMessage());
            System.out.println();
            this.searchingCountryByName();
        }
    }

    public void createCountry() {
        try {
            Scanner createRegion = new Scanner(System.in);

            System.out.println("\t\t\t\t\t\tAdd Country");
            System.out.println("---------------------------------------------------------");
            System.out.print("Country ID: ");
            String id = createRegion.nextLine();
            System.out.print("Country Name: ");
            String name = createRegion.nextLine();
            System.out.print("Region ID: ");
            int regionID = createRegion.nextInt();
            System.out.println("---------------------------------------------------------");

            System.out.println();
            CreateCountryRequest request = CreateCountryRequest.builder()
                    .id(id.toUpperCase())
                    .name(name)
                    .regionID(regionID)
                    .build();
            countryController.create(request);
            System.out.println();

            secondMenu();
            int input = createRegion.nextInt();
            System.out.println();

            switch (input) {
                case 1:
                    this.mainView();
                    break;
                case 2:
                    this.createCountry();
                    System.out.println();
                    break;
                case 99:
                    System.out.println("Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please input an existing number!");
                    System.out.println();
                    this.createCountry();
                    break;
            }
        } catch (Exception e) {
            System.out.println();
            System.out.println("Error: " + e.getMessage());
            System.out.println();
            this.createCountry();
        }
    }

    public void updateCountry() {
        try {
            Scanner updateCountryName = new Scanner(System.in);

            System.out.println("\t\t\t\t\tUpdate Country Name");
            System.out.println("---------------------------------------------------------");
            System.out.println("Country ID is the country ID whose country name you want to update.");
            System.out.print("Country ID: ");
            String id = updateCountryName.nextLine();
            System.out.print("Country Name: ");
            String name = updateCountryName.nextLine();
            System.out.println("---------------------------------------------------------");

            System.out.println();
            UpdateCountryRequest request = new UpdateCountryRequest(id.toUpperCase(), name);
            countryController.updateCountry(request);
            System.out.println();

            secondMenu();
            int input = updateCountryName.nextInt();
            System.out.println();

            switch (input) {
                case 1:
                    this.mainView();
                    break;
                case 2:
                    this.updateCountry();
                    System.out.println();
                    break;
                case 99:
                    System.out.println("Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please input an existing number!");
                    System.out.println();
                    this.updateCountry();
                    break;
            }
        } catch (Exception e) {
            System.out.println();
            System.out.println("Error: " + e.getMessage());
            System.out.println();
            this.updateCountry();
        }
    }

    public void deleteCountryByID() {
        try {
            Scanner deleteCountry = new Scanner(System.in);

            System.out.println("\t\t\t\t\tDelete Country By ID");
            System.out.println("---------------------------------------------------------");
            System.out.print("Country ID: ");
            String id = deleteCountry.nextLine();
            System.out.println("---------------------------------------------------------");

            System.out.println();
            countryController.deleteCountry(id);
            System.out.println();

            secondMenu();
            int input = deleteCountry.nextInt();
            System.out.println();

            switch (input) {
                case 1:
                    this.mainView();
                    break;
                case 2:
                    this.deleteCountryByID();
                    System.out.println();
                    break;
                case 99:
                    System.out.println("Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please input an existing number!");
                    System.out.println();
                    this.deleteCountryByID();
                    break;
            }
        } catch (Exception e) {
            System.out.println();
            System.out.println("Error: " + e.getMessage());
            System.out.println();
            this.deleteCountryByID();
        }
    }

    public void secondMenu() {
        System.out.println("---------------------------------------------------------");
        secondMenu.forEach(country -> System.out.println(country.intern()));
        System.out.println("---------------------------------------------------------");
        System.out.print("==>> ");
    }
}