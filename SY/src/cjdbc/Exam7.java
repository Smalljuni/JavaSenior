package cjdbc;

import java.sql.*;
import java.util.Calendar;

public class Exam7 {
    public static void main(String args[]) {
        Connection con;
        PreparedStatement pre;
        ResultSet rs;
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, 0, 28); // ע��0����һ��
        Date date = new Date(calendar.getTimeInMillis());
        String url = "jdbc:mysql://localhost:3306/test?user=root&password=lwj08200533&allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai&zeroDateTimeBehavior=CONVERT_TO_NULL";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("" + e);
        }
        try {
            con = DriverManager.getConnection(url);
            String insertCondition = "INSERT INTO student VALUES (?,?,?,?,?)";
            pre = con.prepareStatement(insertCondition);
            pre.setInt(1, 199);            // ���õĵ�1��?��ֵ���ַ���"008"
            pre.setString(2, "��Ϊ��");        // ���õĵ�2��?��ֵ���ַ���"��Ϊ��"
            pre.setDate(3, date);            // ���õĵ�3��?��ֵ�����ڶ���date
            pre.setDouble(4, 1.99);            // ���õĵ�4��?��ֵ����ֵ1.99
            pre.setString(5, "��");            // ���õĵ�4��?��ֵ����ֵ1.99

            int m = pre.executeUpdate();
            if (m != 0)
                System.out.println("�Ա��в���" + m + "����¼�ɹ�");
            pre = con.prepareStatement("SELECT * FROM student");
            rs = pre.executeQuery();
            while (rs.next()) {
                String number = rs.getString(1);
                String name = rs.getString(2);
                Date birth = rs.getDate(3);
                double height = rs.getDouble(4);
                System.out.println(
                        number + "," + name + "," + birth + "," + height);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
