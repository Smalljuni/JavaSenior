package kcsj;
/*
   ���ѧ���ĶԻ���
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StuAddDiag extends JDialog implements ActionListener {
    // ������Ҫ��swing���
    JLabel jl1, jl2, jl3, jl4, jl5, jl6;
    JTextField jf1, jf2, jf3, jf4, jf5, jf6;
    JPanel jp1, jp2, jp3;
    JButton jb1, jb2;

    // owner��������,title�Ǵ��ڵ�����,modalָ����ģʽ����(true)���߷�ģʽ����(false)
    //ģʽ����:�û���Ҫ�ȵ�������Ի������ܺ��������ڼ�������
    //��ģʽ���ڣ������û��ڴ���Ի����ͬʱ�������Ի�����н���
    public StuAddDiag(Frame owner, String title, boolean modal) {

        super(owner, title, modal);

        jl1 = new JLabel("ѧ��");
        jl2 = new JLabel("����");
        jl3 = new JLabel("�Ա�");
        jl4 = new JLabel("����");
        jl5 = new JLabel("רҵ");
        jl6 = new JLabel("Ժϵ");

        jf1 = new JTextField(10);
        jf2 = new JTextField(10);
        jf3 = new JTextField(10);
        jf4 = new JTextField(10);
        jf5 = new JTextField(10);
        jf6 = new JTextField(10);

        jb1 = new JButton("���");
        jb1.addActionListener(this);
        jb2 = new JButton("ȡ��");
        jb2.addActionListener(this);

        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();

        // ���ò���
        jp1.setLayout(new GridLayout(6, 1));
        jp2.setLayout(new GridLayout(6, 1));

        jp3.add(jb1);
        jp3.add(jb2);

        jp1.add(jl1);
        jp1.add(jl2);
        jp1.add(jl3);
        jp1.add(jl4);
        jp1.add(jl5);
        jp1.add(jl6);

        jp2.add(jf1);
        jp2.add(jf2);
        jp2.add(jf3);
        jp2.add(jf4);
        jp2.add(jf5);
        jp2.add(jf6);

        this.add(jp1, BorderLayout.WEST);
        this.add(jp2, BorderLayout.CENTER);
        this.add(jp3, BorderLayout.SOUTH);
        this.setBounds(700, 450, 300, 200);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == jb1) {
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try {
                // 1.��������
                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("���سɹ�");
                // 2.�������ݿ�
                String url = "jdbc:mysql://localhost:3306/test?allowPublicKeyRetrieval=true&" +
                        "useUnicode=true&characterEncoding=UTF-8&useSSL=false&" +
                        "serverTimezone=Asia/Shanghai&zeroDateTimeBehavior=CONVERT_TO_NULL";
                String user = "root";
                String passwd = "1234";
                conn = DriverManager.getConnection(url, user, passwd);

                // �������
                String sql = "insert into stu values(?,?,?,?,?,?)";
                pstmt = conn.prepareStatement(sql);

                // ������ֵ
                pstmt.setString(1, jf1.getText());
                pstmt.setString(2, jf2.getText());
                pstmt.setString(3, jf3.getText());
                pstmt.setString(4, jf4.getText());
                pstmt.setString(5, jf5.getText());
                pstmt.setString(6, jf6.getText());

                pstmt.executeUpdate();

                this.dispose();// �ر�ѧ���Ի���

            } catch (Exception arg1) {
                arg1.printStackTrace();
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                        rs = null;
                    }
                    if (pstmt != null) {
                        pstmt.close();
                        pstmt = null;
                    }
                    if (conn != null) {
                        conn.close();
                        conn = null;
                    }
                } catch (Exception arg2) {
                    arg2.printStackTrace();
                }
            }

        } else if (e.getSource() == jb2) {
            this.dispose();
        }
    }

}

