//      query sql
//        select r.name, r.region_id, c.name
//        from regions r
//        join continents c
//        on c.continent_id = r.continent_id
//        order by r.name asc;

package org.lessons.java;

import java.sql.*;

public class Nations {

    public static void main(String[] args) {

        String URL = "jdbc:mysql://localhost:3306/nations_db";
        String USER = "root";
        String PASSWORD = "root";

        try(Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            String query = """
                        
                        select r.name, r.region_id, c.name
                        from regions r
                        join continents c
                        on c.continent_id = r.continent_id
                        order by r.name asc;
                        
                    """;

            try(PreparedStatement ps = conn.prepareStatement(query)){
                try(ResultSet rs = ps.executeQuery()) {
                    while (rs.next()){
                        String name = rs.getString("r.name");
                        int id = rs.getInt("r.region_id");
                        String cName = rs.getString("c.name");
                        System.out.println(name + " - " + id + " - " + cName );
                    }
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

}
