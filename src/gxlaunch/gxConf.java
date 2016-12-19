package gxlaunch;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author skodela
 */
public class gxConf {

    private static final String CNFFILE="gxcontrol.conf";
    private static String dxin;
    private static BufferedReader br=null;
    private static StringBuilder sb;
    public String gxurl="",gxpin="";
    public  int sensax=0,sensay=0,sensaz=0,sensyy=0,senspp=0,sensrr=0,polltm=50,astack=7,sdiff=2;
    public int yymul=3,ppmul=3,rrmul=3;
    public int axmul=3,aymul=3,azmul=3;

    gxConf(){
        try {
            br = new BufferedReader(new FileReader(CNFFILE));                   
        } catch (Exception ex) {
                System.out.println("doesnot exist!");
                return;
        }
        try {
            sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            dxin = sb.toString();
        }   catch (Exception ex) {
                System.out.println("did not read!");
                return;
        } finally {
            try {
                br.close();
            } catch (Exception ex) {
                System.out.println("did not close!");
                return;
            }
        }        
} // end of constructor
    
    
public void printall(){
    System.out.println(">>"+gxurl);
    System.out.println(">>"+gxpin);
    System.out.println(">>"+sensax);
    System.out.println(">>"+sensay);
    System.out.println(">>"+sensaz);
    System.out.println(">>"+sensyy);
    System.out.println(">>"+senspp);
    System.out.println(">>"+sensrr);
}

public String getconf(){
    return dxin;
}

public void confproc(){
    if (dxin==null){
        gxlaunch.laucherUI.lui.setlog("Ouch - empty config");
        return ; }    
    else { 
        try{
            gxlaunch.laucherUI.lui.setconfta(dxin);
            for (String s: dxin.split(System.lineSeparator())){
                if (s.isEmpty()){continue;}
                if (s.charAt(0)=='#') {continue;}
                if (s.contains("==")){
                    String x[]= s.split("==") ;
                    switch (x[0]){
                        //any new config items go here
                        case "GXURL": {gxurl=x[1]; continue; }
                        case "GXPIN": {gxpin=x[1]; continue; }
                        case "SENSAX": {sensax=Integer.parseInt(x[1]); continue; }
                        case "SENSAY": {sensay=Integer.parseInt(x[1]); continue; }
                        case "SENSAZ": {sensaz=Integer.parseInt(x[1]); continue; }
                        case "SENSYY": {sensyy=Integer.parseInt(x[1]); continue; }
                        case "SENSPP": {senspp=Integer.parseInt(x[1]); continue; }
                        case "SENSRR": {sensrr=Integer.parseInt(x[1]); continue; }
                        case "POLLTM": {polltm=Integer.parseInt(x[1]); continue; }
                        case "ASTACK": {astack=Integer.parseInt(x[1]); continue; } 
                        case "AXMUL": {axmul=Integer.parseInt(x[1]); continue; }
                        case "AYMUL": {aymul=Integer.parseInt(x[1]); continue; }
                        case "AZMUL": {azmul=Integer.parseInt(x[1]); continue; }
                        case "YYMUL": {yymul=Integer.parseInt(x[1]); continue; }
                        case "PPMUL": {ppmul=Integer.parseInt(x[1]); continue; }
                        case "RRMUL": {rrmul=Integer.parseInt(x[1]); continue; }
                        case "SDIFF": {sdiff=Integer.parseInt(x[1]); continue; }                    }
                }
            }}
        catch(Exception ex) { 
            gxlaunch.laucherUI.lui.setlog("Couldn't parse the config file!"); 
            return;
        }

        if (gxpin.length()==0){gxlaunch.laucherUI.lui.setlog("No PIN for the sensor!");}
        if (gxurl.length()==0){gxlaunch.laucherUI.lui.setlog("No URL for the sensor!");}
        gxlaunch.laucherUI.lui.setlog("Read the configuration.");
    }

    }
}