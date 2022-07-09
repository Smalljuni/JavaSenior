package sy6;

import javax.swing.*;
import java.awt.*;

public class Main01 {
    public static void main(String args[]) {
        Example win = new Example();
        win.setBounds(100, 100, 360, 260);
    }
}

class Example extends JFrame {
    JTextField text;

    JButton button1, button2;

    JRadioButton radio1, radio2;

    ButtonGroup group;

    JCheckBox checkBox1, checkBox2, checkBox3, checkBox4;

    JComboBox<String> comboBox;

    JFrame window1 = new JFrame();

    JTextArea area;

    public Example() {
        init();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void init() {
        setLayout(new FlowLayout());
        JLabel label1 = new JLabel("你的名字:");
        add(label1);
        text = new JTextField(20);
        add(text);
        group = new ButtonGroup();
        JLabel label2 = new JLabel("性别:");
        add(label2);
        radio1 = new JRadioButton("男");
        radio2 = new JRadioButton("女");
        group.add(radio1);
        group.add(radio2);
        add(radio1);
        add(radio2);
        JLabel label3 = new JLabel("你喜欢的水果:");
        add(label3);
        checkBox1 = new JCheckBox("苹果");
        checkBox2 = new JCheckBox("桔子");
        checkBox3 = new JCheckBox("香蕉");
        checkBox4 = new JCheckBox("桃子");
        add(checkBox1);
        add(checkBox2);
        add(checkBox3);
        add(checkBox4);
        JLabel label4 = new JLabel("你每次吃几个水果:");
        add(label4);
        comboBox = new JComboBox<String>();
        comboBox.addItem("少于一个");
        comboBox.addItem("多于一个");
        add(comboBox);
        JLabel label5 = new JLabel("你觉得吃水果有什么好处:");
        add(label5);
        area = new JTextArea(6, 12);
        add(new JScrollPane(area));
        button1 = new JButton("确定");
        add(button1);
        button2 = new JButton("重写");
        add(button2);
    }
}