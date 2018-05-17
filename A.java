import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class A {
  protected static String s7;
	protected static String s6;//ip地址
	protected static String s5;//mac地址
	protected static String s1;
	protected static String s3;
	protected static String s8;//IDX编码



	public static void exeCm(String commandStr) {
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
           b=a-1;//有b行，第b行第一个是要找的数据
           String s=sb.toString();
           String[] ss=s.split("\n");
           s8=ss[b-1].substring(0,4).trim();//IDX编码

/*
           System.out.println(b);
           System.out.println(sb);
           System.out.println(ss.length);
           System.out.println(ss[6]);
           System.out.println(s8);
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
            //判断行数，提取命令行结束
            String s=sb.toString();
            String[] ss=s.split("     ");
            String s1="静态",s3="动态",s4="Static",s42="Dynamic";

            int first=0;
            //s1为标准，s2为比较

            for(a=0;a<3*b;a++)
            {
            	if(ss[a].trim().equals(s3)||ss[a].trim().equals(s42))
            	{
            		break;
            	}
            }
    		first=a-1;
    		s6=ss[3].substring(8).trim();
    		s5=ss[first].trim();
    		//路由器mac


            s7="您的电脑未遭受ARP攻击";


            for(a=(first+2);a<3*b;a++)
            {
            	if(ss[a+1].trim().equals(s1)||ss[a].trim().equals(s4))
            		break;
            	if(ss[a].trim().equals(s3)||ss[a].trim().equals(s42))
            	  if(ss[a-1].trim().equals(ss[first].trim()))
            	  {

            		  s7="您正在遭受ARP攻击";break;
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



        new response();


    }





}
