import java.awt.geom.Ellipse2D;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.Thread;

public class Scanthread extends Thread{

        public Scanthread() {
        }
        @Override
        public void run() {
            try {
                while (true) {
                    Date date = new Date();
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    final String time = format.format(date);

                    String commandStr = "arp -a";
                    A.exeCmd(commandStr);
                    if (A.whetherunderattack.equals("您正在遭受ARP攻击")) {
                        A.attackrecord.add(A.whetherunderattack +"于"+ time + "\n"+"攻击者MAC地址为"+A.mac );
                        A.attacktimes++;
                        response.centerTextArea.append(A.attackrecord.get(A.attacktimes));
                        A.netswitch="disabled";
                        commandStr="netsh interface set interface "+A.netcardunderattack+" "+A.netswitch;
                        System.out.println(commandStr);
                        A.execute(commandStr);
                        break;
                    }
                    //attackrecord.add(A.whetherunderattack+time+"\n");
                }
            }
            catch(Exception e2)
                {
                    System.out.println("扫描线程中异常为"+ e2.getMessage());
                }


        }
}
