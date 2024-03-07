package org.metrodataacademy.controllers;

import org.metrodataacademy.daos.RegionDAO;
import org.metrodataacademy.models.Region;
import org.metrodataacademy.models.request.CreateRegionRequest;
import org.metrodataacademy.models.request.UpdateRegionRequest;
import org.metrodataacademy.tools.DBConnection;

import java.util.InputMismatchException;
import java.util.List;

public class RegionController {

    private final RegionDAO regionDAO;

    public RegionController() {
        DBConnection dbConnection = new DBConnection();
        regionDAO = new RegionDAO(dbConnection.getConnection());
    }

    public void create(CreateRegionRequest request) {
        if (request.getName().length() > 25) {
            System.out.println("Region name too long, maximum 25 characters!");
        } else if (request.getName().isEmpty()) {
            System.out.println("Region name must NotBlank!");
        } else {
            regionDAO.createRegion(request);
        }
    }

    public List<Region> getAll() {
        return regionDAO.getAllRegions();
    }

    public List<Region> searchingByName(String name) {
        return regionDAO.searchingRegionByName(name);
    }

    public void getById(Integer id) throws InputMismatchException {
        Region region = regionDAO.getRegionById(id);

        if (region != null) {
            System.out.println("Region ID: " + region.getId() + "  |\t " + "Region Name: " + region.getName());
        }
    }

    public void update(UpdateRegionRequest request) {
        if (request.getName().isEmpty()) {
            System.out.println("Region name must NotBlank!");
        } else if (request.getName().length() > 25) {
            System.out.println("Region name too long, maximum 25 characters!");
        } else {
            regionDAO.updateRegion(request);
        }
    }

    public void delete(Integer id) {
        regionDAO.deleteRegionById(id);
    }
}