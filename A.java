import javax.sound.sampled.AudioFormat;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
//import java.awt.desktop.ScreenSleepEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.BreakIterator;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.lang.Thread;

public class A {
    protected static boolean underattacked=false;//未遭受攻击
    protected static int choice;//用户选择 是0，否1
    protected static int otherchoice;//与choice相反
  protected static String whetherunderattack;
	protected static String ip;//ip地址
	protected static String mac;//mac地址
	protected static String staticinfo;
	protected static String dynamicinfo;
	protected static String idx;//IDX编码
    protected static ArrayList<String> buffer=new ArrayList<String>(100);//存放网卡缓存区
    protected static ArrayList<String> attackrecord=new ArrayList<String>(100);//存放被攻击记录
    protected static int buffercount=0;
    protected static int attacktimes=0;//被攻击次数
    protected static String netselected;//选中的网卡
    //protected static ArrayList<String> netcardattacked=new ArrayList<>(100);//受攻击网卡列表
    protected static Map<String,String> netcardattacked=new Map<String, String>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean containsKey(Object key) {
            return false;
        }

        @Override
        public boolean containsValue(Object value) {
            return false;
        }

        @Override
        public String get(Object key) {
            return null;
        }

        @Override
        public String put(String key, String value) {
            return null;
        }

        @Override
        public String remove(Object key) {
            return null;
        }

        @Override
        public void putAll(Map<? extends String, ? extends String> m) {

        }

        @Override
        public void clear() {

        }

        @Override
        public Set<String> keySet() {
            return null;
        }

        @Override
        public Collection<String> values() {
            return null;
        }

        @Override
        public Set<Entry<String, String>> entrySet() {
            return null;
        }
    };
    protected static String[] netswitch={"disabled","enabled"};//网卡开关
    protected static int uselessline=4;//无用行
    protected static boolean isinterrupt;

	public static void exeCm(String commandStr) {
        BufferedReader br = null;
        int linenum=0;
        try {

            Process p = Runtime.getRuntime().exec(commandStr);
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            StringBuilder sb = new StringBuilder();


            while ((line = br.readLine())!=null) {
                sb.append(line + "\n");
                //buffer[a]=line;
                linenum++;

            }
           //b=a-1;//有b行，第b行第一个是要找的数据
            //System.out.println(linenum-1+"\n");
            buffercount=linenum-1+uselessline-1;
            String s=sb.toString();
            String[] ss=s.split("\n");
            try
            {
                for (int i=uselessline;i<=linenum-1;i++)
                    buffer.add(i-uselessline, ss[i-1]);

            }catch(NullPointerException e)
            {
                System.out.println("发生异常的原因为 :"+e.getMessage());
            }


        }
            //判断主体

         catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            if (br != null)
            {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


    }






	public static void exeCmd(String commandStr) {
        BufferedReader br = null;
        int a=0,b;
        try {

            Process p = Runtime.getRuntime().exec(commandStr);
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            StringBuilder sb = new StringBuilder();


            while ((line = br.readLine())!=null) {
                sb.append(line + "\n");
                a++;
            }
            b=a-3;
            //System.out.println(b);
            //判断行数，提取命令行结束
            String s=sb.toString();
            String[] ss=s.split("     ");
            String staticinfo="静态",dynamicinfo="动态",s4="Static",s42="Dynamic";

            int first=0;
            //staticinfo为标准，s2为比较

            for(a=0;a<3*b;a++)
            {
                //System.out.println(ss[a].trim());
            	if(ss[a].trim().equals(dynamicinfo)||ss[a].trim().equals(s42))
            	{
            		break;
            	}
            }
    		first=a-1;
    		ip=ss[3].substring(8).trim();
    		//System.out.println(ip);
            mac=ss[first].trim();
    		//路由器或者攻击者mac


            whetherunderattack="您的电脑未遭受ARP攻击";


            for(a=(first+2);a<3*b;a++)
            {
            	if(ss[a+1].trim().equals(staticinfo)||ss[a].trim().equals(s4))
            		break;
            	if(ss[a].trim().equals(dynamicinfo)||ss[a].trim().equals(s42))
            	  if(ss[a-1].trim().equals(ss[first].trim()))
            	  {
            		  whetherunderattack="您正在遭受ARP攻击";//break;
            	  }
            }
        }
            //判断主体

         catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            if (br != null)
            {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


    }

    public static void execute(String commandStr){
        try{
            Process p = Runtime.getRuntime().exec(commandStr);
        }
        catch (Exception ee){
            System.out.println(ee.getMessage());
        }
    }
    public static void main(String[] args) {


        //A.exeCm("netsh i i show in");

        new response();
        new selectnet();
        //Scanthread scanbackstage=new Scanthread();
        //while (true) {
            //System.out.println(selectnet.haveselected);
//            if(selectnet.haveselected) {
//                try {
//                    scanbackstage.start();
//                }
//                catch (IllegalThreadStateException ie){
//                    System.out.println("线程异常 "+ie.getMessage());
//                }
//            }
        //}
    }





}
