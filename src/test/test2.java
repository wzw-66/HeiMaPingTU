package test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class test2 extends JFrame implements ActionListener{
    JButton jb = new JButton("wzw");
    public test2(){
        //设置界面大小
        this.setSize(488,430);
        //设置标题
        this.setTitle("拼图 登录");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(3);
        setLayout(null);


        jb.setSize(100,100);
        jb.setLocation(0,0);

        jb.addActionListener(this);
        this.getContentPane().add(jb);











        //设置界面可视
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == jb){
            jb.setSize(500,500);
        }
    }
}
