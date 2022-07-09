package cjdbc;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.Statement;

public class TestStatement {
    public static void main(String[] args) throws Exception {
        Connection conn = null;
        String sql;
        Statement ps;
        ResultSet rs;
        // MySQL��JDBC URL��д��ʽ��jdbc:mysql://�������ƣ����Ӷ˿�/���ݿ������?����=ֵ
        // ������������Ҫָ��useUnicode��characterEncoding

        String url = "jdbc:mysql://localhost:3306/test?"
                + "user=root&password=1234&useUnicode=true"
                + "&characterEncoding=UTF8&useSSL=false"
                + "&serverTimezone=Asia/Shanghai&zeroDateTimeBehavior=CONVERT_TO_NULL"
                + "&allowPublicKeyRetrieval=true";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");// ��̬����mysql����
            System.out.println("�ɹ�����MySQL��������");
            conn = DriverManager.getConnection(url);

            long time1 = System.currentTimeMillis();
            ps = conn.createStatement();
            for (int i = 0; i < 10000; i++) {
                rs = ps.executeQuery("select * from student where sid= " + i);
                rs.close();
            }
            System.out.println("111:" + (System.currentTimeMillis() - time1));

            long time2 = System.currentTimeMillis();
            for (int i = 0; i < 10000; i++) {
                PreparedStatement pss = conn.prepareStatement(
                        "select * from student where sid = " + i);
                rs = pss.executeQuery();
                rs.close();
            }
            System.out.println("222:" + (System.currentTimeMillis() - time2));

            long time3 = System.currentTimeMillis();
            PreparedStatement pss = conn
                    .prepareStatement("select * from student where sid = ?");
            for (int i = 0; i < 10000; i++) {
                pss.setInt(1, i);
                rs = pss.executeQuery();
                rs.close();
            }
            System.out.println("333:" + (System.currentTimeMillis() - time3));

        } catch (SQLException e) {
            System.out.println("MySQL��������");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }
}