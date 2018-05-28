import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.Thread;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;


public class response extends JFrame{

	private static final int DEFFAULT_PORT=0;
	private JButton one;
	public static JButton two;
	public static JButton three;
	public  static JTextArea centerTextArea;
	public static JTextPane centerTextPane;
	public static JOptionPane makesure;
	public JMenuBar menubar;   //菜单条
	public JMenu menuFile; //菜单
	public JMenu menuHelp;
	public JMenu menuNetCard;//网卡
	public JMenuItem itemOpen, itemSave,itemExit,itemSelect,itemSwitch,itemDoc,itemAbout;   //菜单项
	private PopupMenu popup;
	private MenuItem showItem;
	private MenuItem exitItem;
	private TrayIcon trayIcon;
	public static Image img;//图标


	private void setup(){
		this.setTitle("ARP攻击检测");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(647,400);


		popup = new PopupMenu();
		showItem = new MenuItem("显示");
		exitItem = new MenuItem("退出");

		popup.add(showItem);
		popup.add(exitItem);

		//图标更换
		String path = "m1.ico";
		img = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(path));
		this.setIconImage(img);
			trayIcon = new TrayIcon(new ImageIcon(path).getImage(),"ARPDefender", popup);
			trayIcon.setImageAutoSize(true);












		centerTextArea=new JTextArea();
		centerTextArea.setEditable(false);
		centerTextArea.setBackground(new Color(211,211,211));
		//显示
		one=new JButton("手动检测");
		two=new JButton("绑定");
		three=new JButton("解绑");
		//four=new JButton("选择网卡");
		/* two three */


		JScrollPane scrollPane=new JScrollPane();




		JPanel panel=new JPanel();
		panel.add(one);
		panel.add(two);
		panel.add(three);
		//panel.add(four);
		two.setEnabled(false);
		three.setEnabled(false);
		//JMenu menuF=new JMenu();
		menubar = new JMenuBar();
		menuFile = new JMenu("文件(F)");
		menuNetCard=new JMenu("网卡(N)");
		menuHelp=new JMenu("帮助(H)");
		menuFile.setMnemonic('F');  //设置菜单的键盘操作方式是Alt + F键
		menuNetCard.setMnemonic('N');
		menuHelp.setMnemonic('H');
		itemOpen = new JMenuItem("打开(O)");
		itemSave = new JMenuItem("保存(S)");
		itemExit=new JMenuItem("退出(X)");
		itemSelect=new JMenuItem("选择网卡(E)");
		itemSwitch=new JMenuItem("打开/关闭网卡(W)");
		itemDoc=new JMenuItem("文档(D)");
		itemAbout=new JMenuItem("关于(A)");
		//设置菜单项的键盘操作方式是Ctrl+O和Ctrl+S键
		KeyStroke Ctrl_cutKey = KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK);
		itemOpen.setAccelerator(Ctrl_cutKey);
		Ctrl_cutKey = KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK);
		itemSave.setAccelerator(Ctrl_cutKey);
		Ctrl_cutKey = KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK);
		itemExit.setAccelerator(Ctrl_cutKey);
		Ctrl_cutKey = KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK);
		itemSelect.setAccelerator(Ctrl_cutKey);
		Ctrl_cutKey = KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_DOWN_MASK);
		itemSwitch.setAccelerator(Ctrl_cutKey);
		Ctrl_cutKey = KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK);
		itemDoc.setAccelerator(Ctrl_cutKey);
		Ctrl_cutKey = KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK);
		itemAbout.setAccelerator(Ctrl_cutKey);

		menuFile.add(itemOpen);
		//menuFile.addSeparator();
		menuFile.add(itemSave);
		menuFile.add(itemExit);
		menuNetCard.add(itemSelect);
		menuNetCard.add(itemSwitch);
		menuHelp.add(itemDoc);
		menuHelp.add(itemAbout);
		menubar.add(menuFile);  //将菜单添加到菜单条上
		menubar.add(menuNetCard);
		menubar.add(menuHelp);
		setJMenuBar(menubar);





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
				A.attackrecord.add("\n");
//				Scanthread scanning=new Scanthread();
//						scanning.start();

				Date date=new Date();
				  DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				  final String time=format.format(date);

				  String commandStr = "arp -a";
				  A.exeCmd(commandStr);
				  if(A.whetherunderattack.equals("您正在遭受ARP攻击")){
         		centerTextArea.append("网关: "+A.ip+" 攻击者MAC: "+A.mac+"\n");}
				centerTextArea.append(A.whetherunderattack+"  "+time+"\n");
			}
		});

		//开始检测


		two.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				  A.exeCmd("arp -a");
				  //A.exeCm("netsh i i show in");


				String sentence ="netsh -c i i add neighbors "+A.idx+" "+A.ip+" "+A.mac;
				//System.out.println(sentence);

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


				String sentence ="netsh -c i i delete neighbors "+A.idx+" "+A.ip;//+" "+A.mac;
				//System.out.println(sentence);
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
		itemSelect.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					new selectnet();
				} catch (Exception e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		});

		//打开网卡选择窗口
	itemDoc.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){


			try {
				//String url = "http://www.baidu.com";
				String url = "https://raw.githubusercontent.com/Nexus01/ARPDefender/master/README.md";
				java.net.URI uri = java.net.URI.create(url);
				java.awt.Desktop dp = java.awt.Desktop.getDesktop();
				if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) {
					dp.browse(uri);// 获取系统默认浏览器打开链接
				}
			} catch (java.lang.NullPointerException e1) {
				// uri为空时抛出异常
				e1.printStackTrace();
			} catch (java.io.IOException e2) {
				// 无法获取系统默认浏览器
				e2.printStackTrace();
			}
		}
	});
		itemAbout.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){


				try {
					//String url = "http://www.baidu.com";
					String url = "https://github.com/Nexus01/ARPDefender";
					java.net.URI uri = java.net.URI.create(url);
					java.awt.Desktop dp = java.awt.Desktop.getDesktop();
					if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) {
						dp.browse(uri);// 获取系统默认浏览器打开链接
					}
				} catch (java.lang.NullPointerException e1) {
					// uri为空时抛出异常
					e1.printStackTrace();
				} catch (java.io.IOException e2) {
					// 无法获取系统默认浏览器
					e2.printStackTrace();
				}
			}
		});
		itemExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){


				try {
					System.exit(0);
				} catch (Exception e3) {
					// 此为uri为空时抛出异常
					e3.printStackTrace();
				}

			}
		});
		itemSwitch.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					//A.netswitch="enabled";
					String commandStr="netsh interface set interface "+A.netselected+" "+A.netswitch[A.otherchoice];
					A.execute(commandStr);
				} catch (Exception e3) {
					e3.printStackTrace();
				}

			}
		});

		showItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(true);
				SystemTray.getSystemTray().remove(trayIcon);   //移除托盘图标
			}
		});
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	this.addWindowListener(new WindowAdapter() {
		@Override
		public void windowClosing(WindowEvent e) {
			setVisible(false);
			SystemTray tray = SystemTray.getSystemTray();//实例化系统托盘
			try {
				tray.add(trayIcon); //添加系统托盘图标
			} catch (AWTException ex) {
				ex.printStackTrace();
			}
		}
	});
	trayIcon.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			SystemTray tray = SystemTray.getSystemTray();//实例化系统托盘
			if (e.getClickCount() == 2) { // 鼠标双击
				tray.remove(trayIcon); // 移除托盘图标
				setVisible(true);
			}
		}
	});

	}














	response(){
		setup();
		setListener();
//		int n = JOptionPane.showConfirmDialog(null, "你会了吗?", "标题",JOptionPane.YES_NO_OPTION); //返回值为0或1
//		System.out.println(n);
	}
}
