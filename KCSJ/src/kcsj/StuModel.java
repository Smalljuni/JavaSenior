package kcsj;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;


public class StuModel extends AbstractTableModel {
    // �̳�TableModel�����࣬ʵ��ѧ��ģ��
    // ��rowData�д�������ݣ���columnNames�������
    Vector rowData, columnNames;

    // �����������ݿ�ı���
    // ���г�ʼ������Ϊ��
    Statement stat = null;
    Connection conn = null;
    ResultSet rs = null;

    // ��ʼ��
    public void init(String sql) {
        if (sql.equals("")) {
            // ��ʼ��ʱ��ʾ���еĴ�������ݿ��е�����
            sql = "select * from stu";
        }
        // �м䲿��
        // ��������
        columnNames = new Vector();//��������
        columnNames.add("ѧ��");
        columnNames.add("����");
        columnNames.add("�Ա�");
        columnNames.add("����");
        columnNames.add("רҵ");
        columnNames.add("Ժϵ");

        // rowData��Ŷ���
        rowData = new Vector();

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
            stat = conn.createStatement();
            rs = stat.executeQuery(sql);

            if (rs.next() == false) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "�Ҳ�����ѧ����", "����", JOptionPane.INFORMATION_MESSAGE);
            }
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                Vector hang = new Vector();
                hang.add(rs.getString(1));
                hang.add(rs.getString(2));
                hang.add(rs.getString(3));
                hang.add(rs.getInt(4));
                hang.add(rs.getString(5));
                hang.add(rs.getString(6));
                // ���뵽rowData��
                rowData.add(hang);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                if (stat != null) {
                    stat.close();
                    stat = null;
                }
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    // ���캯�������ݵ�sql����ȡ����ģ��
    public StuModel(String sql) {
        this.init(sql);
    }

    // ����ѧ��ģ�ͣ������г�ʼ��
    public StuModel() {
        this.init("");
    }

    // ��ȡ����
    public int getRowCount() {
        // TODO Auto-generated method stub
        return this.rowData.size();
    }

    // ��ȡ����
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return this.columnNames.size();
    }

    // ��ȡĿ���м�Ŀ���е�����
    public Object getValueAt(int row, int column) {
        // TODO Auto-generated method stub
        return ((Vector) (this.rowData.get(row))).get(column);
    }

    // ��ȡ�����ֶ�����
    public String getColumnName(int column) {
        // TODO Auto-generated method stub
        return (String) this.columnNames.get(column);
    }
}

