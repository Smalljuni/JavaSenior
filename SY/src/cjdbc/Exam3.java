package cjdbc;

import java.sql.*;

public class Exam3 {
    public static void main(String args[]) {
        Connection con;
        Statement sql;
        ResultSet rs;
        String url = "jdbc:mysql://localhost:3306/test?user=root&password=1234&allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai&zeroDateTimeBehavior=CONVERT_TO_NULL";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("" + e);
        }
        try {
            con = DriverManager.getConnection(url);
            sql = con.createStatement();

            rs = sql.executeQuery("SELECT * FROM student");
            while (rs.next()) {
                String number = rs.getString(1);
                String name = rs.getString(2);
                Date birth = rs.getDate(3);
                double height = rs.getDouble(4);
                System.out.println(number + "," + name + "," + birth + "," + height);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
