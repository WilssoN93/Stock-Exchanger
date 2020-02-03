package Utils;

public class Utils {

    public static void waitTime(long milliSeconds){

        try {
            Thread.sleep(milliSeconds);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
