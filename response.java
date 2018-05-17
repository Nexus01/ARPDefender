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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.*;

public class response extends JFrame{

	private static final int DEFFAULT_PORT=0;
	private JButton one;
	private JButton two;
	private JButton three;
	private JTextArea centerTextArea;



	private void setup(){
		this.setTitle("ARP攻击检测");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,400);





		//图标更换
		String path = "m1.ico";
		try{
			Image img = ImageIO.read(this.getClass().getResource(path));
			this.setIconImage(img);

		}catch (IOException e){
			e.printStackTrace();
		}







		centerTextArea=new JTextArea();
		centerTextArea.setEditable(false);
		centerTextArea.setBackground(new Color(211,211,211));
		//显示
		one=new JButton("检测");
		two=new JButton("绑定");
		three=new JButton("解绑");
		/* two three */


		JScrollPane scrollPane=new JScrollPane();




		JPanel panel=new JPanel();
		panel.add(one);
		panel.add(two);
		panel.add(three);




		scrollPane.setViewportView(centerTextArea);



		add(scrollPane,BorderLayout.CENTER);
		add(panel,BorderLayout.SOUTH);
		setVisible(true);



		Font font=new Font("",Font.BOLD,15);
		centerTextArea.setFont(font);


		centerTextArea.append("检测是否正在遭受ARP攻击"+"\n"+"若遭受ARP攻击时IP与mac绑定请先断网"+"\n"+"平时无需断网"+"\n"+"绑定非永久，关机即重置"+"\n");
	}




	private void setListener(){

		one.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Date date=new Date();
				  DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				  final String time=format.format(date);

				  String commandStr = "arp -a";
				  A.exeCmd(commandStr);
				  if(A.s7.equals("您正在遭受ARP攻击"))
         centerTextArea.append(A.s6+A.s5+"\n");
				centerTextArea.append(A.s7+time+"\n");
			}
		});

		//检测


		two.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				  A.exeCmd("arp -a");
				  A.exeCm("netsh i i show in");


				String sentence ="netsh -c i i add neighbors "+A.s8+" "+A.s6+" "+A.s5;
				System.out.println(sentence);
				try {
					Process p = Runtime.getRuntime().exec(sentence);
		            new BufferedReader(new InputStreamReader(p.getInputStream()));
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		});

		//绑定



		three.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				  A.exeCmd("arp -a");
				  A.exeCm("netsh i i show in");


				String sentence ="netsh -c i i delete neighbors "+A.s8+" "+A.s6+" "+A.s5;
				System.out.println(sentence);
				try {
					Process p = Runtime.getRuntime().exec(sentence);
		            new BufferedReader(new InputStreamReader(p.getInputStream()));
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		});

		//解绑



	}














	response(){
		setup();
		setListener();
	}
}
