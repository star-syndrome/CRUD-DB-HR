package org.metrodataacademy.daos;

import org.metrodataacademy.models.Region;
import org.metrodataacademy.models.request.CreateRegionRequest;
import org.metrodataacademy.models.request.UpdateRegionRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegionDAO {

    private final Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    public RegionDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Region> getAllRegions() {
        List<Region> regions = new ArrayList<>();
        String query = "SELECT * FROM region r";

        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Region region = Region.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .build();
                regions.add(region);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
        return regions;
    }

    public void createRegion(CreateRegionRequest request) {
        String query = "INSERT INTO region(name) VALUES (?)";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, request.getName());

            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                System.out.println("Inserting region successfully, new region: " + request.getName());
                System.out.println("Updated Rows: " + rows);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public Region getRegionById(Integer id) {
        Region region = new Region();
        String query = "SELECT * FROM region r WHERE r.id = ?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                region.setId(resultSet.getInt("id"));
                region.setName(resultSet.getString("name"));
            } else {
                System.out.println("Region with ID: " + id + " not found!");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
        return region;
    }

    public List<Region> searchingRegionByName(String name) {
        List<Region> regions = new ArrayList<>();
        String query = "SELECT * FROM region r WHERE r.name LIKE ?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + name + "%");

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Region region = Region.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .build();
                regions.add(region);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
        return regions;
    }

    public void updateRegion(UpdateRegionRequest request) {
        String query = "UPDATE region r SET r.name = ? WHERE r.id = ?";

        if (getRegionById(request.getId()) != null) {
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, request.getName());
                preparedStatement.setInt(2, request.getId());

                int rows = preparedStatement.executeUpdate();
                if (rows > 0) {
                    System.out.println("Updating successfully, new region name: " + request.getName());
                    System.out.println("Updated Rows: " + rows);
                }
            } catch (Exception e) {
                throw new RuntimeException("Error: " + e.getMessage());
            }
        }
    }

    public void deleteRegionById(Integer id) {
        String query = "DELETE FROM region r WHERE r.id = ?";

        if (getRegionById(id) != null) {
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, id);

                int rows = preparedStatement.executeUpdate();
                if (rows > 0) {
                    System.out.println("Deleting region with ID: " + id + " successful!");
                    System.out.println("Updated Rows: " + rows);
                }
            } catch (Exception e) {
                throw new RuntimeException("Error: " + e.getMessage());
            }
        }
    }
}