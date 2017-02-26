package io.github.frc2090.scouting.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Created by dchotzen-hartzell19 on 2/17/17.
 */
public class DBHandler {

    public Connection dbc = null;
    public Statement stmt = null;

    public DBHandler() {
        try {
            Class.forName("org.sqlite.JDBC");
            dbc = DriverManager.getConnection("jdbc:sqlite:test.db");
            setupTables();
        } catch (Exception e) {
            System.out.println("Something bad happeneded");
            System.exit(1);
        }
    }

    public void setupTables() {

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            try {

                stmt = dbc.createStatement();
                String sql = sc.nextLine();
                System.out.println("Gimme a statement bud");
                stmt.executeUpdate(sql);
                stmt.close();
            } catch (Exception e) {
                System.out.println("oops");
            }
        }


    }

}
