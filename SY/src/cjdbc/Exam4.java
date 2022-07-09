package cjdbc;

import java.sql.*;
import java.util.*;

public class Exam4 {
    public static void main(String args[]) {
        Connection con;
        Statement sql;
        ResultSet rs;
        Calendar calendar = Calendar.getInstance();
        calendar.set(1999, 9, 18); //ע��9����ʮ��
        java.sql.Date date = new java.sql.Date(calendar.getTimeInMillis());
        String url = "jdbc:mysql://localhost:3306/test?user=root&password=1234&allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai&zeroDateTimeBehavior=CONVERT_TO_NULL";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("" + e);
        }
        try {
            con = DriverManager.getConnection(url);
            sql = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = sql.executeQuery("SELECT * FROM student");
            rs.absolute(3);
            rs.updateString(2, "������");
            rs.updateRow();    //��message���3�м�¼��name��ֵ����Ϊ����������
            rs.moveToInsertRow();
            rs.updateString(1, "005");
            rs.updateString(2, "�´���");
            rs.updateDate(3, date);
            rs.updateDouble(4, 1.79);
            rs.insertRow();     //��message�����һ�м�¼
            rs = sql.executeQuery("SELECT * FROM student");
            while (rs.next()) {
                String number = rs.getString(1);
                String name = rs.getString(2);
                java.sql.Date birth = rs.getDate(3);
                double height = rs.getDouble(4);
                System.out.println(number + "," + name + "," + birth + "," + height);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
