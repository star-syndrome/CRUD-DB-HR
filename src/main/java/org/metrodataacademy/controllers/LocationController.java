package org.metrodataacademy.controllers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.metrodataacademy.daos.CountryDAO;
import org.metrodataacademy.daos.LocationDAO;
import org.metrodataacademy.models.Location;
import org.metrodataacademy.models.request.CreateLocationRequest;
import org.metrodataacademy.models.request.UpdateLocationRequest;
import org.metrodataacademy.tools.DBConnection;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class LocationController {

    private DBConnection dbConnection = new DBConnection();
    private CountryDAO countryDAO = new CountryDAO(dbConnection.getConnection());
    private LocationDAO locationDAO = new LocationDAO(dbConnection.getConnection(), countryDAO);

    public void create(CreateLocationRequest request) {
        if (request.getStreetAddress().length() > 40) {
            System.out.println("Street Address too long, maximum 40 characters!");
        } else if (request.getPostalCode().length() > 12) {
            System.out.println("Postal Code too long, maximum 12 characters!");
        } else if (request.getStateProvince().length() > 25) {
            System.out.println("State Province too long, maximum 25 characters!");
        } else if (request.getCity().length() > 30) {
            System.out.println("City too long, maximum 30 characters!");
        } else if (request.getStreetAddress().isEmpty()) {
            System.out.println("Street Address must NotBlank!");
        } else if (request.getPostalCode().isEmpty()) {
            System.out.println("Postal Code must NotBlank!");
        } else if (request.getStateProvince().isEmpty()) {
            System.out.println("State Province must NotBlank!");
        } else if (request.getCity().isEmpty()) {
            System.out.println("City must NotBlank!");
        } else if (request.getCountryID().isEmpty()) {
            System.out.println("Country ID must NotBlank!");
        } else {
            locationDAO.createLocation(request);
        }
    }

    public List<Location> getAll(int limit, int offset) {
        return locationDAO.getAllLocations(limit, offset);
    }

    public Integer getSizeOfLocations() {
        return locationDAO.sizeOfLocations();
    }

    public List<Location> searchingByCity(String city) {
        List<Location> locations = locationDAO.searchingByCity(city);

        if (locations.isEmpty()) {
            System.out.println("Location not found!");
        }
        return locations;
    }

    public List<Location> searchingByCountryName(String name) {

        List<Location> locations = locationDAO.searchingLocationByCountry(name);

        if (locations.isEmpty()) {
            System.out.println("Location not found!");
        }
        return locations;
    }

    public void getByID(Integer id) {
        locationDAO.getLocationByID(id);
    }

    public void update(UpdateLocationRequest request) {
        if (request.getStreetAddress().length() > 40) {
            System.out.println("Street Address too long, maximum 40 characters!");
        } else if (request.getPostalCode().length() > 12) {
            System.out.println("Postal Code too long, maximum 12 characters!");
        } else if (request.getStateProvince().length() > 25) {
            System.out.println("State Province too long, maximum 25 characters!");
        } else if (request.getCity().length() > 30) {
            System.out.println("City too long, maximum 30 characters!");
        } else if (request.getStreetAddress().isEmpty()) {
            System.out.println("Street Address must NotBlank!");
        } else if (request.getPostalCode().isEmpty()) {
            System.out.println("Postal Code must NotBlank!");
        } else if (request.getStateProvince().isEmpty()) {
            System.out.println("State Province must NotBlank!");
        } else if (request.getCity().isEmpty()) {
            System.out.println("City must NotBlank!");
        } else {
            locationDAO.updateLocation(request);
        }
    }

    public void delete(Integer id) {
        locationDAO.deleteLocationByID(id);
    }
}