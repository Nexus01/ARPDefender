import javax.sound.sampled.AudioFormat;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.desktop.ScreenSleepEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class A {
  protected static String whetherunderattack;
	protected static String ip;//ip地址
	protected static String mac;//mac地址
	protected static String staticinfo;
	protected static String dynamicinfo;
	protected static String idx;//IDX编码
    protected static ArrayList<String> buffer=new ArrayList(2000);//存放网卡缓存区
    protected static int buffercount=0;

	public static void exeCm(String commandStr) {
        BufferedReader br = null;
        int a=0,b;
        //String[] buffer={};
        try {

            Process p = Runtime.getRuntime().exec(commandStr);
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            StringBuilder sb = new StringBuilder();


            while ((line = br.readLine())!=null) {
                sb.append(line + "\n");
                //buffer[a]=line;
                a++;

            }
           b=a-1;//有b行，第b行第一个是要找的数据
            buffercount=b+3;
           String s=sb.toString();
           String[] ss=s.split("\n");
            try
            {
                for (int i=3;i<b;i++)
                    buffer.add(i-3, ss[i]);

            }catch(NullPointerException e)
            {
                System.out.println("发生异常的原因为 :"+e.getMessage());
            }

           //idx=ss[b-1].substring(0,4).trim();//IDX编码

/*
           System.out.println(b);
           System.out.println(sb);
           System.out.println(ss.length);
           System.out.println(ss[6]);
           System.out.println(idx);
  */


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
            mac=ss[first].trim();
            //System.out.println(A.mac);
            //System.out.println(first);
    		//路由器mac


            whetherunderattack="您的电脑未遭受ARP攻击";


            for(a=(first+2);a<3*b;a++)
            {
            	if(ss[a+1].trim().equals(staticinfo)||ss[a].trim().equals(s4))
            		break;
            	if(ss[a].trim().equals(dynamicinfo)||ss[a].trim().equals(s42))
            	  if(ss[a-1].trim().equals(ss[first].trim()))
            	  {

            		  whetherunderattack="您正在遭受ARP攻击";break;
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

    public static void main(String[] args) {


        //A.exeCm("netsh i i show in");
        new response();
        new selectnet();

    }





}
