/*
get data from the sensor
 */
package gxlaunch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author skodela
 */
public class gxGet {
    
    private final String USER_AGENT = "gxCon 0.1";
    private String url;
    private URL obj=null;
    gxGet(String xurl){
        url=xurl;
        try{
            obj = new URL(url);
        }
        catch (Exception e){}
    }

    public String getsensor() throws Exception {
        try{
            HttpURLConnection conx = (HttpURLConnection) obj.openConnection();
            conx.setRequestMethod("GET");
            conx.setRequestProperty("User-Agent", USER_AGENT);

            int res = conx.getResponseCode();

            BufferedReader buf = new BufferedReader(new InputStreamReader(conx.getInputStream()));
            String istr;
            StringBuffer response = new StringBuffer();

            while ((istr = buf.readLine()) != null) {
                    response.append(istr);
            }
            buf.close();

            return response.toString();
        } catch (Exception e) { 
            gxlaunch.laucherUI.lui.setlog("Unable to get data from sensor!");
            //e.printStackTrace(); 
            return "FAIL";
        }
    }

}
