package cjdbc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginFrame {
    protected static String dbClassName = "com.mysql.cj.jdbc.Driver";
    protected static String dbUrl = "jdbc:mysql://localhost:3306/test?user=root&password=lwj08200533&allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai&zeroDateTimeBehavior=CONVERT_TO_NULL";
    protected static String dbUser = "";
    protected static String dbPwd = "";

    // ��½�����GUI���
    private JFrame jf = new JFrame("��½");

    private JTextField userField = new JTextField(20);

    private JTextField passField = new JTextField(20);

    private JButton loginButton = new JButton("��½");

    // ִ��JDBC�����Ķ���
    private Connection conn;

    private Statement stmt;

    private PreparedStatement pstmt;

    private ResultSet rs;

    public void init() throws Exception {
        // Ϊ��½��ť����¼�������
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // ��½�ɹ�����ʾ"��½�ɹ�"
                if (validate(userField.getText(), passField.getText())) {
                    JOptionPane.showMessageDialog(jf, "??????");
                }
                // ������ʾ"��½ʧ��"
                else {
                    JOptionPane.showMessageDialog(jf, "??????");
                }
            }
        });
        jf.add(userField, BorderLayout.NORTH);
        jf.add(passField);
        jf.add(loginButton, BorderLayout.SOUTH);
        jf.pack();
        jf.setVisible(true);
    }


    private boolean validate(String userName, String userPass) {
        try {
            Class.forName(dbClassName);
            conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
            pstmt = conn.prepareStatement("select * from user "
                    + "where name=? and psword=?");
            pstmt.setString(1, userName);
            pstmt.setString(2, userPass);
            // �����ѯ��ResultSet���г���һ���ļ�¼�����½�ɹ�
            if (pstmt.executeQuery().next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        new LoginFrame().init();
    }
}
