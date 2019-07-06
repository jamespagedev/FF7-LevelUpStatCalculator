package ClassFiles;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QuickTest {
    public static void main(String[] args) {
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date dateobj = new Date();
        System.out.println(df.format(dateobj));
        try {
            Thread.sleep(4000);
        } catch(InterruptedException e) {
            System.out.println("got interrupted!");
        }
        dateobj = new Date();
        System.out.println(df.format(dateobj));
    }
}