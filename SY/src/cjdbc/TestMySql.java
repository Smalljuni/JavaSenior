package cjdbc;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

public class TestMySql {

    public static void main(String[] args) throws Exception {
        Connection conn = null;
        String sql;

        String url = "jdbc:mysql://192.168.254.100:3306/dbtest1?user=lwj&password=abc123&" +
                "allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=UTF-8&" +
                "useSSL=false&serverTimezone=Asia/Shanghai&zeroDateTimeBehavior=CONVERT_TO_NULL";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(url);

            Statement stmt = conn.createStatement();

            stmt.execute("drop table if exists students");
            sql = "create table students(NO char(20),name varchar(20),primary key(NO))";

            int result = stmt.executeUpdate(sql);

            if (result != -1) {
                sql = "insert into students(NO,name) values('2013001','qaz')";
                result = stmt.executeUpdate(sql);
                sql = "insert into students(NO,name) values('2013002','wsx')";
                result = stmt.executeUpdate(sql);
                sql = "select * from students";
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    System.out.println(rs.getString(1) + "\t" + rs.getString(2));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }
}