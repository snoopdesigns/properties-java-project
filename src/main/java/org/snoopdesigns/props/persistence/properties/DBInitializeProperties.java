package org.snoopdesigns.props.persistence.properties;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DBInitializeProperties {

    @Autowired
    private DataSource dataSource;

    @Value("${clearDb:#{false}}")
    private String clearDb;

    @PostConstruct
    public void initialize() {
        Boolean clearDbFlag = Boolean.parseBoolean(clearDb);
        if (clearDbFlag) {
            try {
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                statement.execute("DROP TABLE IF EXISTS APARTMENTS");
                statement.execute("DROP TABLE IF EXISTS COMPLEXES");
                statement.executeUpdate(
                        "CREATE TABLE APARTMENTS(" +
                                "ID INTEGER Primary key, " +
                                "CIAN_ID varchar(30), " +
                                "COMPLEX_ID INTEGER, " +
                                "URL varchar(64), " +
                                "FLOOR_NUMBER INTEGER, " +
                                "FLOOR_TOTAL INTEGER, " +
                                "HOUSE_TYPE varchar(30), " +
                                "SELL_TYPE REAL, " +
                                "TOTAL_AREA REAL, " +
                                "ROOMS_AREA REAL, " +
                                "LIVING_AREA REAL, " +
                                "WINDOW varchar(30), " +
                                "PRICE INTEGER, " +
                                "PHONE varchar(30), " +
                                "REPAIRS varchar(30), " +
                                "ADDRESS varchar(30), " +
                                "IS_FIRST_FLOOR varchar(30), " +
                                "IS_LAST_FLOOR varchar(30), " +
                                "DISTANCE_TO_CENTER double, " +
                                "DISTANCE_TO_METRO double, " +
                                "METRO_NAME varchar(40), " +
                                "METRO_LAT REAL, " +
                                "METRO_LNG REAL, " +
                                "HOUSE_TYPE_ENUM varchar(30), " +
                                "BUILDING_TYPE varchar(30))");
                statement.executeUpdate(
                        "CREATE TABLE COMPLEXES(" +
                                "ID INTEGER Primary key, " +
                                "CIAN_ID INTEGER, " +
                                "NAME varchar(40), " +
                                "ADDRESS varchar(50), " +
                                "LAT REAL, " +
                                "LNG REAL)");
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}