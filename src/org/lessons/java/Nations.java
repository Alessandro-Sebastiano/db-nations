//      query sql
//        select c.country_id, c.name, r.name, c2.name
//        from countries c
//        join regions r on c.region_id = r.region_id
//        join continents c2  on r.continent_id = c2.continent_id
//        order by c.name asc;

package org.lessons.java;

import java.sql.*;
import java.util.Scanner;

public class Nations {

    private static final String URL = "jdbc:mysql://localhost:3306/nations_db";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("Search regions:");
        String userString = input.nextLine();

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            String query = """
                        
                        select c.country_id, c.name, r.name, c2.name 
                        from countries c
                        join regions r on c.region_id = r.region_id
                        join continents c2  on r.continent_id = c2.continent_id
                        where c.name like "%"?"%"
                        order by c.name asc;
                        
                    """;

            try(PreparedStatement ps = conn.prepareStatement(query)){

                ps.setString(1, String.valueOf(userString));

                try(ResultSet rs = ps.executeQuery()) {
                    while (rs.next()){
                        String cName = rs.getString("c.name");
                        int id = rs.getInt("c.country_id");
                        String conName = rs.getString("c2.name");
                        String rName = rs.getString("r.name");
                        System.out.println(id + " - " + cName + " - " + rName + " - " + conName);
                    }
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        System.out.println("Select an region id:");
        int inputId = Integer.parseInt(input.nextLine());

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)){

            String query = """
                    
                    """;

            try(PreparedStatement ps = conn.prepareStatement(query)) {

            }

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

}
