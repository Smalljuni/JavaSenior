package kcsj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Main extends JFrame implements ActionListener {
    // ����һЩ�ؼ�

    JPanel jp1, jp2;
    JLabel jl1, jl2;
    JButton jb1, jb2, jb3, jb4;
    JTable jt;
    JScrollPane jsp;//�������
    JTextField jtf;
    StuModel sm;
    // �����������ݿ�ı���
    Statement stat = null;
    PreparedStatement ps;
    Connection conn = null;
    ResultSet rs = null;

    public static void main(String[] args) {
        Main start = new Main();
    }

    // ���캯��
    public Main() {
        jp1 = new JPanel();
        jtf = new JTextField(10);
        jb1 = new JButton("��ѯ");
        jb1.addActionListener(this);
        jl1 = new JLabel("���������֣�");

        jp1.add(jl1);
        jp1.add(jtf);
        jp1.add(jb1);

        jb2 = new JButton("���");
        jb2.addActionListener(this);
        jb3 = new JButton("�޸�");
        jb3.addActionListener(this);
        jb4 = new JButton("ɾ��");
        jb4.addActionListener(this);

        jp2 = new JPanel();
        jp2.add(jb2);
        jp2.add(jb3);
        jp2.add(jb4);

        // ����ģ�Ͷ���
        sm = new StuModel();

        // ��ʼ��
        jt = new JTable(sm);

        jsp = new JScrollPane(jt);

        // ��jsp���뵽jframe��
        this.add(jsp);
        this.add(jp1, "North");
        this.add(jp2, "South");

        int windowWidth = this.getWidth(); //��ô����
        int windowHeight = this.getHeight(); //��ô����
        Toolkit kit = Toolkit.getDefaultToolkit(); //���幤�߰�
        Dimension screenSize = kit.getScreenSize(); //��ȡ��Ļ�ĳߴ�
        int screenWidth = screenSize.width; //��ȡ��Ļ�Ŀ�
        int screenHeight = screenSize.height; //��ȡ��Ļ�ĸ�
        this.setSize(600, 400);
        this.setLocation(screenWidth / 3 - windowWidth / 3, screenHeight / 3 - windowHeight / 3);//���ô���λ��


        this.setTitle("ѧ������ϵͳ");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    public void actionPerformed(ActionEvent arg0) {
        // �ж����ĸ���ť�����
        if (arg0.getSource() == jb1) {
            System.out.println("�û�ϣ������ѯ");
            // ��Ϊ�ѱ�����ݷ�װ��StuModel�У����ԱȽϼ򵥵���ɲ�ѯ
            String name = this.jtf.getText().trim();
            // дһ��sql���
            String sql = "select * from stu where stuName = '" + name + "' ";
            // ����һ������ģ���࣬������
            sm = new StuModel(sql);
            // ����jtable
            jt.setModel(sm);

        }
        // һ��������ӽ���
        else if (arg0.getSource() == jb2) {
            System.out.println("���");
            StuAddDiag sa = new StuAddDiag(this, "���ѧ��", true);

            // �����ٻ���µ�����ģ��,
            sm = new StuModel();
            jt.setModel(sm);
        } else if (arg0.getSource() == jb4) {
            // ����ɾ����¼
            // 1.�õ�ѧ����ID
            int rowNum = this.jt.getSelectedRow();// getSelectedRow�᷵�ظ��û����е���
            // ������û�һ�ж�û��ѡ���ͷ���-1
            if (rowNum == -1) {
                // ��ʾ���
                JOptionPane.showMessageDialog(this, "��ѡ��һ��");
                return;
            }
            // �õ�ѧ��ID
            String stuId = (String) sm.getValueAt(rowNum, 0);
            System.out.println("Id�� " + stuId);

            // �������ݿ�,���ɾ������
            try {
                // 1.��������
                Class.forName("com.mysql.cj.jdbc.Driver");
                // 2.�������ݿ�
                String url = "jdbc:mysql://localhost:3306/test?allowPublicKeyRetrieval=true&" +
                        "useUnicode=true&characterEncoding=UTF-8&useSSL=false&" +
                        "serverTimezone=Asia/Shanghai&zeroDateTimeBehavior=CONVERT_TO_NULL";
                String user = "root";
                String passwd = "1234";

                conn = DriverManager.getConnection(url, user, passwd);
                System.out.println("���ӳɹ�");
                ps = conn.prepareStatement("delete from stu where stuId = ?");
                ps.setString(1, stuId);
                ps.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                        rs = null;

                    }
                    if (ps != null) {
                        ps.close();
                        ps = null;
                    }
                    if (conn != null) {
                        conn.close();
                        conn = null;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            sm = new StuModel();
            // ����jtable
            jt.setModel(sm);
        } else if (arg0.getSource() == jb3) {
            System.out.println("�û�ϣ���޸�");
            // �����û�ϣ���޸�
            int rowNum = this.jt.getSelectedRow();  //�õ�ѡ�е��к�
            if (rowNum == -1) {
                // ��ʾ
                JOptionPane.showMessageDialog(this, "��ѡ��һ��");
                return;
            }
            // ��ʾ�Ի���
            StuUpDiag su = new StuUpDiag(this, "�޸�ѧ��", true, sm, rowNum);
            sm = new StuModel();
            jt.setModel(sm);
        }
    }
}

