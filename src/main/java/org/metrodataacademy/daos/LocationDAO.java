package org.metrodataacademy.daos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.metrodataacademy.models.Country;
import org.metrodataacademy.models.Location;
import org.metrodataacademy.models.Region;
import org.metrodataacademy.models.request.CreateLocationRequest;
import org.metrodataacademy.models.request.UpdateLocationRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class LocationDAO {

    private Connection connection;
    private CountryDAO countryDAO;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public LocationDAO(Connection connection, CountryDAO countryDAO) {
        this.connection = connection;
        this.countryDAO = countryDAO;
    }

    public Integer sizeOfLocations() {
        String query = "SELECT COUNT(*) AS total_locations FROM location";
        int totalLocations = 0;

        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                totalLocations = resultSet.getInt("total_locations");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
        return totalLocations;
    }

    public List<Location> getAllLocations(Integer limit, Integer offset) {
        List<Location> locations = new ArrayList<>();
        String query =
                "SELECT l.id, l.street_address, l.postal_code, l.city, l.state_province, c.name, r.name " +
                "FROM location l " +
                "LEFT JOIN country c ON c.id = l.country_id " +
                "LEFT JOIN region r ON r.id = c.region_id LIMIT ? OFFSET ?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Location location = Location.builder()
                        .id(resultSet.getInt("id"))
                        .streetAddress(resultSet.getString("street_address"))
                        .postalCode(resultSet.getString("postal_code"))
                        .city(resultSet.getString("city"))
                        .stateProvince(resultSet.getString("state_province"))
                        .country(Country.builder()
                                .name(resultSet.getString("c.name"))
                                .region(Region.builder()
                                        .name(resultSet.getString("r.name"))
                                        .build())
                                .build())
                        .build();
                locations.add(location);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
        return locations;
    }

    public void getLocationByID(Integer id) {
        String query =
                "SELECT l.id, l.street_address, l.postal_code, l.city, l.state_province, c.name, r.name " +
                "FROM location l " +
                "LEFT JOIN country c ON c.id = l.country_id " +
                "LEFT JOIN region r ON r.id = c.region_id WHERE l.id = ?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Location location = Location.builder()
                        .id(resultSet.getInt("id"))
                        .streetAddress(resultSet.getString("street_address"))
                        .postalCode(resultSet.getString("postal_code"))
                        .city(resultSet.getString("city"))
                        .stateProvince(resultSet.getString("state_province"))
                        .country(Country.builder()
                                .name(resultSet.getString("c.name"))
                                .region(Region.builder()
                                        .name(resultSet.getString("r.name"))
                                        .build())
                                .build())
                        .build();
                System.out.println(
                        "ID: " + location.getId() + ",  Street Address: " + location.getStreetAddress() +
                        ",  \nPostal Code: " + location.getPostalCode() + ",  City: " + location.getCity() +
                        ",  State Province: " + location.getStateProvince() + ",  \nCountry: " + location.getCountry()
                        .getName() + ",  Region: " + location.getCountry().getRegion().getName());
            } else {
                System.out.println("Location with ID: " + id + " not found!");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public List<Location> searchingByCity(String city) {
        List<Location> locations = new ArrayList<>();
        String query =
                "SELECT l.id, l.street_address, l.postal_code, l.city, l.state_province, c.name, r.name " +
                "FROM location l " +
                "LEFT JOIN country c ON c.id = l.country_id " +
                "LEFT JOIN region r ON r.id = c.region_id WHERE l.city LIKE ?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + city + "%");

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Location location = Location.builder()
                        .id(resultSet.getInt("id"))
                        .streetAddress(resultSet.getString("street_address"))
                        .postalCode(resultSet.getString("postal_code"))
                        .city(resultSet.getString("city"))
                        .stateProvince(resultSet.getString("state_province"))
                        .country(Country.builder()
                                .name(resultSet.getString("c.name"))
                                .region(Region.builder()
                                        .name(resultSet.getString("r.name"))
                                        .build())
                                .build())
                        .build();
                locations.add(location);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
        return locations;
    }

    public List<Location> searchingLocationByCountry(String name) {
        List<Location> locations = new ArrayList<>();
        String query =
                "SELECT l.id, l.street_address, l.postal_code, l.city, l.state_province, c.name, r.name " +
                "FROM location l " +
                "LEFT JOIN country c ON c.id = l.country_id " +
                "LEFT JOIN region r ON r.id = c.region_id WHERE c.name LIKE ?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + name + "%");

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Location location = Location.builder()
                        .id(resultSet.getInt("id"))
                        .streetAddress(resultSet.getString("street_address"))
                        .postalCode(resultSet.getString("postal_code"))
                        .city(resultSet.getString("city"))
                        .stateProvince(resultSet.getString("state_province"))
                        .country(Country.builder()
                                .name(resultSet.getString("c.name"))
                                .region(Region.builder()
                                        .name(resultSet.getString("r.name"))
                                        .build())
                                .build())
                        .build();
                locations.add(location);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
        return locations;
    }

    public boolean existsByLocationId(Integer id) {
        String query = "SELECT * FROM location l WHERE l.id = ?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public void createLocation(CreateLocationRequest request) {
        String query =
                "INSERT INTO location(id, street_address, postal_code, city, state_province, country_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        if (countryDAO.existsByCountryId(request.getCountryID())) {
            if (!existsByLocationId(request.getId())) {
                try {
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, request.getId());
                    preparedStatement.setString(2, request.getStreetAddress());
                    preparedStatement.setString(3, request.getPostalCode());
                    preparedStatement.setString(4, request.getCity());
                    preparedStatement.setString(5, request.getStateProvince());
                    preparedStatement.setString(6, request.getCountryID());

                    int rows = preparedStatement.executeUpdate();
                    if (rows > 0) {
                        System.out.println("Inserting location successfully, new street address: " + request.getStreetAddress());
                        System.out.println("Updated Rows: " + rows);
                    }
                } catch (Exception e) {
                    throw new RuntimeException("Error: " + e.getMessage());
                }
            } else {
                System.out.println("Location with ID: " + request.getId() + " already exist!");
            }
        } else {
            System.out.println("Country with ID: " + request.getCountryID() + " not found!");
        }
    }

    public void updateLocation(UpdateLocationRequest request) {
        String query =
                "UPDATE location l SET l.street_address = ?, l.postal_code = ?, " +
                "l.city = ?, l.state_province = ? WHERE l.id = ?";

        if (existsByLocationId(request.getId())) {
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, request.getStreetAddress());
                preparedStatement.setString(2, request.getPostalCode());
                preparedStatement.setString(3, request.getCity());
                preparedStatement.setString(4, request.getStateProvince());
                preparedStatement.setInt(5, request.getId());

                int rows = preparedStatement.executeUpdate();
                if (rows > 0) {
                    System.out.println("Updating successful!");
                    System.out.println("Updated Rows: " + rows);
                }
            } catch (Exception e) {
                throw new RuntimeException("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Location with ID: " + request.getId() + " not found!");
        }
    }

    public void deleteLocationByID(Integer id) {
        String query = "DELETE FROM location l WHERE l.id = ?";

        if (existsByLocationId(id)) {
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, id);

                int rows = preparedStatement.executeUpdate();
                if (rows > 0) {
                    System.out.println("Deleting location with ID: " + id + " successful!");
                    System.out.println("Updated Rows: " + rows);
                }
            } catch (Exception e) {
                throw new RuntimeException("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Location with ID: " + id + " not found!");
        }
    }
}