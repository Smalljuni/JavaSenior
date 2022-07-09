package sy09;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class StuMa extends JFrame implements ActionListener {

    JTextField id, name, major, grade, birth;
    Connection connect = null;
    Statement stmt = null;
    boolean saveable = false;
    PreparedStatement prestmt = null;
    ResultSet result = null;
    JRadioButton male, female;
    JButton head, front, next, last, add, modify, delete, save;
    Container con;
    String query = "select * from student";

    public StuMa() {
        Toolkit toolKit = Toolkit.getDefaultToolkit();
        Dimension screen = toolKit.getScreenSize();
        setSize(400, 250);
        setLocation((screen.width - getSize().width) / 2, (screen.height - getSize().height) / 2);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        id = new JTextField(10);
        id.setEditable(false);
        name = new JTextField(10);
        name.setEditable(false);
        major = new JTextField(10);
        major.setEditable(false);
        grade = new JTextField(10);
        grade.setEditable(false);
        birth = new JTextField(8);
        birth.setEditable(false);
        ButtonGroup group = new ButtonGroup();
        male = new JRadioButton("��", true);
        female = new JRadioButton("Ů", false);
        group.add(male);
        group.add(female);
        JPanel sexChoice = new JPanel();
        sexChoice.add(male);
        sexChoice.add(female);
        head = new JButton("��ǰ");
        head.addActionListener(this);
        front = new JButton("��һ��");
        front.addActionListener(this);
        next = new JButton("��һ��");
        next.addActionListener(this);
        last = new JButton("���");
        last.addActionListener(this);
        add = new JButton("����");
        add.addActionListener(this);
        modify = new JButton("�޸�");
        modify.addActionListener(this);
        delete = new JButton("ɾ��");
        delete.addActionListener(this);
        save = new JButton("����");
        save.addActionListener(this);
        con = getContentPane();
        con.setLayout(null);
        JLabel title = new JLabel("ѧ������ϵͳ");
        title.setFont(new Font("����", Font.BOLD, 20));
        con.add(title);
        title.setBounds(130, 0, 200, 25);
        JLabel idLab = new JLabel("ѧ��:");
        con.add(idLab);
        idLab.setBounds(10, 30, 50, 20);
        con.add(id);
        id.setBounds(60, 30, 100, 20);
        JLabel nameLab = new JLabel("����:");
        con.add(nameLab);
        nameLab.setBounds(200, 30, 50, 20);
        con.add(name);
        name.setBounds(250, 30, 100, 20);
        JLabel majLab = new JLabel("רҵ:");
        con.add(majLab);
        majLab.setBounds(10, 60, 50, 20);
        con.add(major);
        major.setBounds(60, 60, 100, 20);
        JLabel graLab = new JLabel("�꼶:");
        con.add(graLab);
        graLab.setBounds(200, 60, 50, 20);
        con.add(grade);
        grade.setBounds(250, 60, 100, 20);
        JLabel birLab = new JLabel("��������:");
        con.add(birLab);
        birLab.setBounds(10, 90, 60, 20);
        con.add(birth);
        birth.setBounds(70, 90, 100, 20);
        JLabel sexLab = new JLabel("�Ա�:");
        con.add(sexLab);
        sexLab.setBounds(200, 90, 50, 20);
        con.add(male);
        male.setBounds(250, 90, 40, 20);
        con.add(female);
        female.setBounds(300, 90, 40, 20);
        con.add(head);
        head.setBounds(10, 130, 60, 30);
        con.add(front);
        front.setBounds(90, 130, 80, 30);
        con.add(next);
        next.setBounds(210, 130, 80, 30);
        con.add(last);
        last.setBounds(310, 130, 70, 30);
        con.add(add);
        add.setBounds(10, 180, 70, 30);
        con.add(modify);
        modify.setBounds(110, 180, 70, 30);
        con.add(delete);
        delete.setBounds(210, 180, 70, 30);
        con.add(save);
        save.setBounds(310, 180, 70, 30);
        setResizable(false);
        validate();
        init();

    }

    private void init() {
        try {
            connectAccess();
            stmt = connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            result = stmt.executeQuery(query);
            result.next();
            showData();

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    private void showData() {
        try {
            id.setText(result.getString(1));
            name.setText(result.getString(2));
            major.setText(result.getString(3));
            grade.setText(result.getString(4));
            birth.setText(result.getString(5));
            if (result.getString(6).equals("��")) {
                male.setSelected(true);
            } else {
                female.setSelected(true);
            }
        } catch (Exception e) {
        }

    }


    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/test?allowPublicKeyRetrieval=true&" +
            "useUnicode=true&characterEncoding=UTF-8&useSSL=false&" +
            "serverTimezone=Asia/Shanghai&zeroDateTimeBehavior=CONVERT_TO_NULL";
    static final String USER = "root";
    static final String PASS = "lwj08200533";

    public void connectAccess() {
        try {
            Class.forName(JDBC_DRIVER);
            connect = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println(" ʵ����Statement����...");
            stmt = connect.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent event) {
        JButton b = (JButton) event.getSource();
        id.setEditable(false);
        name.setEditable(false);
        major.setEditable(false);
        grade.setEditable(false);
        birth.setEditable(false);
        if (b == head) {
            try {
                result.first();
                showData();
            } catch (Exception e) {
            }


        } else if (b == front) {
            try {
                if (!result.isFirst()) {
                    result.previous();
                    showData();
                }
            } catch (Exception e) {
                System.out.println("front error!" + e.toString());
            }

        } else if (b == next) {
            try {
                if (!result.isLast()) {
                    result.next();
                    showData();
                }
            } catch (Exception e) {
                System.out.println("next error!" + e.toString());
            }

        } else if (b == last) {
            try {
                if (!result.isLast()) {
                    result.last();
                    showData();
                }

            } catch (Exception e) {
            }

        } else if (b == add) {
            try {
                result.last();
                id.setText(Integer.parseInt(result.getString(1)) + 1 + "");
                id.setEditable(true);
                name.setText("");
                name.setEditable(true);
                major.setText("");
                major.setEditable(true);
                grade.setText("");
                grade.setEditable(true);
                birth.setText("");
                birth.setEditable(true);
                male.setSelected(true);
                female.setSelected(false);
            } catch (Exception e) {
            }
            saveable = true;
        } else if (b == modify) {
            id.setEditable(false);
            name.setEditable(true);
            major.setEditable(true);
            grade.setEditable(true);
            birth.setEditable(true);
            try {
                prestmt = connect.prepareStatement("delete from student where id=?");
                prestmt.setString(1, id.getText());
                prestmt.execute();
                result = stmt.executeQuery(query);
            } catch (Exception e) {
                System.out.println("dele error!" + e.toString());
            }
            saveable = true;

        } else if (b == delete) {
            try {
                prestmt = connect.prepareStatement("delete from student where id=?");
                prestmt.setString(1, id.getText());
                prestmt.execute();
                result = stmt.executeQuery(query);
            } catch (Exception e) {
                System.out.println("dele error!" + e.toString());
            }

        } else if (b == save && saveable == true) {
            try {
                String s;
                prestmt = connect.prepareStatement("insert into student ("
                        + ") values(?,?,?,?,?,?)");
                prestmt.setString(1, id.getText());
                prestmt.setString(2, name.getText());
                prestmt.setString(3, major.getText());
                prestmt.setString(4, grade.getText());
                prestmt.setString(5, birth.getText());
                if (male.isSelected()) {
                    s = "��";
                } else {
                    s = "Ů";
                }
                prestmt.setString(6, s);
                prestmt.execute();
                result = stmt.executeQuery(query);
            } catch (Exception e) {
                System.out.println("save error!" + e.toString());
            }
            saveable = false;
        }

    }

    public static void main(String[] args) {
        new StuMa();
    }
}
