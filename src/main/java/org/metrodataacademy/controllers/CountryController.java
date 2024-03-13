package org.metrodataacademy.controllers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.metrodataacademy.daos.CountryDAO;
import org.metrodataacademy.daos.RegionDAO;
import org.metrodataacademy.models.Country;
import org.metrodataacademy.models.request.CreateCountryRequest;
import org.metrodataacademy.models.request.UpdateCountryRequest;
import org.metrodataacademy.tools.DBConnection;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class CountryController {

    private DBConnection dbConnection = new DBConnection();
    private RegionDAO regionDAO = new RegionDAO(dbConnection.getConnection());
    private CountryDAO countryDAO = new CountryDAO(dbConnection.getConnection(), regionDAO);

    public void create(CreateCountryRequest request) {
        if (request.getName().length() > 40) {
            System.out.println("Country name too long, maximum 40 characters!");
        } else if (request.getId().length() > 4) {
            System.out.println("Country ID too long, maximum 4 characters!");
        } else if (request.getName().isEmpty()) {
            System.out.println("Country name must NotBlank!");
        } else if (request.getId().isEmpty()) {
            System.out.println("Country ID must NotBlank!");
        } else {
            countryDAO.createCountry(request);
        }
    }

    public Integer getSizeOfCountries() {
        return countryDAO.sizeOfCountries();
    }

    public List<Country> getAll(int limit, int offset) {
        return countryDAO.getAllCountries(limit, offset);
    }

    public List<Country> searchingByName(String name) {
        List<Country> countries = countryDAO.searchingCountryByName(name);

        if (countries.isEmpty()) {
            System.out.println("Country not found!");
        }
        return countries;
    }

    public List<Country> searchingByRegionName(String name) {
        List<Country> countries = countryDAO.searchingCountryByRegion(name);

        if (countries.isEmpty()) {
            System.out.println("Country not found!");
        }
        return countries;
    }

    public void getByID(String id) {
        if (id.isEmpty()) {
            System.out.println("Country ID must NotBlank!");
        } else if (id.length() > 4) {
            System.out.println("Country ID too long, maximum 4 characters!");
        } else {
            countryDAO.getCountryByID(id);
        }
    }

    public void updateCountry(UpdateCountryRequest request) {
        if (request.getName().length() > 40) {
            System.out.println("Country name too long, maximum 40 characters!");
        } else if (request.getId().length() > 4) {
            System.out.println("Country ID too long, maximum 4 characters!");
        } else if (request.getName().isEmpty()) {
            System.out.println("Country name must NotBlank!");
        } else if (request.getId().isEmpty()) {
            System.out.println("Country ID must NotBlank!");
        } else {
            countryDAO.updateCountry(request);
        }
    }

    public void deleteCountry(String id) {
        if (id.isEmpty()) {
            System.out.println("Country ID must NotBlank!");
        } else if (id.length() > 4) {
            System.out.println("Country ID too long, maximum 4 characters!");
        } else {
            countryDAO.deleteCountryByID(id);
        }
    }
}