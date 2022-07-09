//�����

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * ���ݿ�����
 */
public class Conn {
    public static void main(String[] args) {
        Connection con;
        //jdbc����
        String driver = "com.mysql.cj.jdbc.Driver";
        //�����ҵ����ݿ���cxxt
        String url = "jdbc:mysql://localhost:3306/test?&useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "1234";
        try {
            //ע��JDBC��������
            Class.forName(driver);
            //��������
            con = DriverManager.getConnection(url, user, password);
            if (!con.isClosed()) {
                System.out.println("���ݿ����ӳɹ�");
            }
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println("���ݿ�����û�а�װ");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("���ݿ�����ʧ��");
        }
    }
}
