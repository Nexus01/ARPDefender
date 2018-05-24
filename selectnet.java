import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import  java.awt.FlowLayout;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;


public class selectnet extends JFrame {
    private JTextField t = new JTextField(50);
    private JComboBox c = new JComboBox();
    private JButton b = new JButton("选择网卡");
    private JButton b2=new JButton("扫描网卡");
    private int count = 0;
    public selectnet() {
        // TODO Auto-generated constructor stub
        this.setTitle("选择网卡");
        setLayout(new FlowLayout());
        setSize(600,475);
        setVisible(true);
        //A.exeCm("netsh i i show in");

        t.setEditable(false);
        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

                try {
                    //if (c.getSelectedItem() != null) {
                        t.setText("您选择了 " + c.getSelectedItem());
                    //}
                    A.idx = c.getSelectedItem().toString().substring(0, 4).trim();
                    String[] temp= c.getSelectedItem().toString().split("\\s+\\p{Zs}");
                    A.netcardunderattack=temp[temp.length-1];
                    System.out.println("netcard is "+A.netcardunderattack+"\n");
                } catch (NullPointerException e1) {
                    t.setText("请先点击扫描网卡");
                }
            }
        });

        b2.addActionListener(new ActionListener() {


        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            if (c.getItemCount() > 0)
                c.removeAllItems();
            A.exeCm("netsh i i show in");
            while(A.buffercount>0&&!A.buffer.isEmpty()&&A.buffercount>count) {
                c.addItem(A.buffer.get(count++));
                //System.out.println(A.buffer.size());
                A.buffercount--;
            }
            count=0;
            A.buffer.clear();
            }

        });
        c.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

            }
        });
        c.setEditable(false);

        add(t);
        add(c);
        add(b);
        add(b2);

    }

    /**
     * @param args
     */
    /*public static void main(String[] args) {
        // TODO Auto-generated method stub
        new selectnet();
    }*/

}

