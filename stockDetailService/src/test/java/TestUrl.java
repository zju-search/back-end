import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class TestUrl {

    public static void main(String[] args){

        String urlStr="http://hq.sinajs.cn/list=sz000001";
        URL url = null;
        //请求的输入流
        BufferedReader in = null;
        //输入流的缓冲
        StringBuffer sb = new StringBuffer();
        try{
            url = new URL(urlStr);
            in = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8") );
            String str = null;
            //一行一行进行读入
            while((str = in.readLine()) != null) {
                sb.append( str );
            }
        } catch (Exception ex) {

        } finally{
            try{
                if(in!=null) {
                    in.close(); //关闭流
                }
            }catch(IOException ex) {

            }
        }
        String result =sb.toString();
        System.out.println(result);
        String[] splitFirst = result.split("\"");
        System.out.println(splitFirst[1]);
        String[] splitSecond = splitFirst[1].split(",");
        System.out.println(splitSecond[3]);
        double current_price = Double.parseDouble(splitSecond[3]);
        System.out.println(current_price);
    }
}
