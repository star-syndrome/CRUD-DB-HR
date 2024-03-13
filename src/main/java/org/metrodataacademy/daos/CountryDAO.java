package org.metrodataacademy.daos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.metrodataacademy.models.Country;
import org.metrodataacademy.models.Region;
import org.metrodataacademy.models.request.CreateCountryRequest;
import org.metrodataacademy.models.request.UpdateCountryRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class CountryDAO {

    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private RegionDAO regionDAO;

    public CountryDAO(Connection connection, RegionDAO regionDAO) {
        this.connection = connection;
        this.regionDAO = regionDAO;
    }

    public CountryDAO(Connection connection) {
        this.connection = connection;
    }

    public Integer sizeOfCountries() {
        String query = "SELECT COUNT(*) AS total_countries FROM country";
        int totalCountries = 0;

        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                totalCountries = resultSet.getInt("total_countries");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
        return totalCountries;
    }

    public List<Country> getAllCountries(int limit, int offset) {
        List<Country> countries = new ArrayList<>();
        String query =
                "SELECT c.id, c.name, r.name FROM country c " +
                "LEFT JOIN region r ON r.id = c.region_id LIMIT ? OFFSET ?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Country country = Country.builder()
                        .id(resultSet.getString("id"))
                        .name(resultSet.getString("name"))
                        .region(Region.builder()
                                .name(resultSet.getString("r.name"))
                                .build())
                        .build();
                countries.add(country);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
        return countries;
    }

    public void getCountryByID(String id) {
        String query =
                "SELECT c.id, c.name, r.name FROM country c " +
                "LEFT JOIN region r ON r.id = c.region_id WHERE c.id = ?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Country country = Country.builder()
                        .id(resultSet.getString("id"))
                        .name(resultSet.getString("name"))
                        .region(Region.builder()
                                .name(resultSet.getString("r.name")).build())
                        .build();

                System.out.println("ID: " + country.getId() + ",  Country: " +
                            country.getName() + ",  Region: " + country.getRegion().getName());
            } else {
                System.out.println("Country with ID: " + id + " not found!");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public List<Country> searchingCountryByRegion(String name) {
        List<Country> countries = new ArrayList<>();
        String query =
                "SELECT c.id, c.name, r.name FROM country c " +
                "LEFT JOIN region r ON r.id = c.region_id WHERE r.name LIKE ?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + name + "%");

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Country country = Country.builder()
                        .id(resultSet.getString("id"))
                        .name(resultSet.getString("name"))
                        .region(Region.builder()
                                .name(resultSet.getString("r.name"))
                                .build())
                        .build();
                countries.add(country);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
        return countries;
    }

    public List<Country> searchingCountryByName(String name) {
        List<Country> countries = new ArrayList<>();
        String query =
                "SELECT c.id, c.name, r.name FROM country c " +
                "LEFT JOIN region r ON r.id = c.region_id WHERE c.name LIKE ?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + name + "%");

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Country country = Country.builder()
                        .id(resultSet.getString("id"))
                        .name(resultSet.getString("name"))
                        .region(Region.builder()
                                .name(resultSet.getString("r.name"))
                                .build())
                        .build();
                countries.add(country);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
        return countries;
    }

    public boolean existsByCountryId(String id) {
        String query = "SELECT * FROM country c WHERE c.id = ?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);

            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public void createCountry(CreateCountryRequest request) {
        String query = "INSERT INTO country(id, name, region_id) VALUES (?, ?, ?)";

        if (regionDAO.existsByRegionId(request.getRegionID())) {
            if (!existsByCountryId(request.getId())) {
                try {
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, request.getId());
                    preparedStatement.setString(2, request.getName());
                    preparedStatement.setInt(3, request.getRegionID());

                    int rows = preparedStatement.executeUpdate();
                    if (rows > 0) {
                        System.out.println("Inserting country successfully, new country: " + request.getName());
                        System.out.println("Updated Rows: " + rows);
                    }
                } catch (Exception e) {
                    throw new RuntimeException("Error: " + e.getMessage());
                }
            } else {
                System.out.println("Country with ID: " + request.getId() + " already exist!");
            }
        } else {
            System.out.println("Region with ID: " + request.getRegionID() + " not found!");
        }
    }


    public void updateCountry(UpdateCountryRequest request) {
        String query = "UPDATE country c SET c.name = ? WHERE c.id = ?";

        if (existsByCountryId(request.getId())) {
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, request.getName());
                preparedStatement.setString(2, request.getId());

                int rows = preparedStatement.executeUpdate();
                if (rows > 0) {
                    System.out.println("Updating successful, new country name: " + request.getName());
                    System.out.println("Updated Rows: " + rows);
                }
            } catch (Exception e) {
                throw new RuntimeException("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Country with ID: " + request.getId() + " not found!");
        }
    }

    public void deleteCountryByID(String id) {
        String query = "DELETE FROM country c WHERE c.id = ?";

        if (existsByCountryId(id)) {
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, id);

                int rows = preparedStatement.executeUpdate();
                if (rows > 0) {
                    System.out.println("Deleting country with ID: " + id + " successful!");
                    System.out.println("Updated Rows: " + rows);
                }
            } catch (Exception e) {
                throw new RuntimeException("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Country with ID: " + id + " not found!");
        }
    }
}