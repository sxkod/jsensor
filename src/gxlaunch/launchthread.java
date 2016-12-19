package gxlaunch;


import java.awt.AWTException;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Event.*;
import java.awt.AWTKeyStroke;
import static java.lang.Math.abs;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author skodela
 */
public class launchthread extends Thread{
    int runflag=1;
    private static String cursens, gxurl;
    private static gxBot gbot;
    private static gxProc myproc;
    private static gxItem gxi=null,pgxi=null;
    private static gxConf myconf;
    private static gxnMapping mymap;
    private static HashMap<String, ArrayList<String>> cmap;
    private static keyparser mykpx;
    private static parsedKey pxtmp;
    private static HashMap<String,parsedKey> pxkeys;
    private static List<String> evts = Arrays.asList("ax+", "ax-","", "ay+", "ay-","", "az+", "az-","", "yy+", "yy-", "","pp+", "pp-","", "rr+", "rr-","", "m1", "m2", "m3");
    private static List<String> btns = Arrays.asList("m1", "m2", "m3");
    private static gxStack xstack ;

    public static void main(String args[]){
        (new launchthread()).start();
        }

    
    public  void run() {
        myconf = new gxConf();
        myconf.confproc();  
        myconf.printall(); 
        
        xstack = new gxStack(myconf.astack);
        xstack.setsdiff(myconf.sdiff);
        
        //load config and mapping
        gxurl=myconf.gxurl+"?xpin="+myconf.gxpin;
        myproc = new gxProc();         
        mymap = new gxnMapping();
        mykpx=new keyparser();
        pxtmp=new parsedKey();
        pxkeys=new HashMap<String,parsedKey>();
        
        String curmapsel=gxlaunch.laucherUI.lui.getselmap();
        if (curmapsel==null){gxlaunch.laucherUI.lui.setlog("\nNo map selected."); return;}
        mymap.procmap(curmapsel); 
        cmap=mymap.getmapping();
        
        if (cmap.keySet().isEmpty()){gxlaunch.laucherUI.lui.setlog("\nNo mappings found."); return;}
        else{gxlaunch.laucherUI.lui.setlog("\nLoaded "+curmapsel);}
        
        //load mapping to keypress dictionary
        for (String sxt: cmap.keySet()){
            gxlaunch.laucherUI.lui.setlog("\nMapping : "+sxt+" to "+cmap.get(sxt).get(0));
            pxtmp=mykpx.parse(cmap.get(sxt).get(0));
            pxkeys.put(sxt, pxtmp);
        }

        //find the sensor
        if (gxurl==null){ gxlaunch.laucherUI.lui.setlog("\nCan't find URL for sensor!"); return; }
        if (gxurl.indexOf("http://")!=0){ gxurl="http://"+gxurl;  }
        gxlaunch.laucherUI.lui.setlog("\nSensor at : "+gxurl);
        gxGet conx=new gxGet(gxurl);
        gxlaunch.laucherUI.lui.setlog("\nConnected to sensor.");
        
        //create robot
        try{gbot=new gxBot();}
        catch(Exception e){
            //e.printStackTrace();
            gxlaunch.laucherUI.lui.setlog("\nSomething went horribly wrong while launching input robot!");
            return;
        }
        gxlaunch.laucherUI.lui.setlog("\nSensor service running.");
        
        
        //lets read the sensor twice and populate gxi and pgxi
        try{cursens=conx.getsensor();}
        catch (Exception e){ e.printStackTrace();}

        gxi=myproc.process(cursens);
        if (gxi!=null) {pgxi=gxi;}

        try{cursens=conx.getsensor();}
        catch (Exception e){ e.printStackTrace();}
        gxi=myproc.process(cursens);
        
        //this is where we spend the rest of our time
        while (true) {
            
            //get current reading
            try{cursens=conx.getsensor();}
            catch (Exception e){ 
                System.out.println("lT>xx>");
            }
            
            //create gxitem from current readings
            gxi=myproc.process(cursens);
            String kstr="";        
            //process new style asc key mode using gxnmapping
            kstr=makeasckey(gxi);

            if (!kstr.isEmpty()){
                //gxi.printStr(); // DEBUG
                doasckey(kstr);
            }
            
            
            
            try{ Thread.sleep(myconf.polltm); } catch(Exception e){ return;}
            //store older 
            if (gxi!=null) {pgxi=gxi;}
    }
}
 
    private String makebinkey(gxItem gxi){
        String kstr="",mstr="";
            try{ 
            if (gxi.ax>pgxi.ax){ if ((gxi.ax-pgxi.ax)>myconf.sensax){kstr+="10 ";} else {kstr+="00 ";}}
            else if (pgxi.ax>gxi.ax){if ((pgxi.ax-gxi.ax)>myconf.sensax){kstr+="01 ";} else {kstr+="00 ";} }
            else {kstr+="00 ";}

            if (gxi.ay>pgxi.ay){ if ((gxi.ay-pgxi.ay)>myconf.sensay){kstr+="10 ";}  else {kstr+="00 ";}}
            else if (pgxi.ay>gxi.ay){if ((pgxi.ay-gxi.ay)>myconf.sensay){kstr+="01 ";}  else {kstr+="00 ";}}
            else {kstr+="00 ";}

            if (gxi.az>pgxi.az){ if ((gxi.az-pgxi.az)>myconf.sensaz){kstr+="10 ";}  else {kstr+="00 ";}}
            else if (pgxi.az>gxi.az){if ((pgxi.az-gxi.az)>myconf.sensaz){kstr+="01 ";}  else {kstr+="00 ";}}
            else {kstr+="00 ";}                

            if (gxi.yy>pgxi.yy){ if ((gxi.yy-pgxi.yy)>myconf.sensyy){kstr+="10 ";} else {kstr+="00 ";}}
            else if (pgxi.yy>gxi.yy){if ((pgxi.yy-gxi.yy)>myconf.sensyy){kstr+="01 ";} else {kstr+="00 ";} }
            else {kstr+="00 ";}

            if (gxi.pp>pgxi.pp){ if ((gxi.pp-pgxi.pp)>myconf.senspp){kstr+="10 ";}  else {kstr+="00 ";}}
            else if (pgxi.pp>gxi.pp){if ((pgxi.pp-gxi.pp)>myconf.senspp){kstr+="01 ";}  else {kstr+="00 ";}}
            else {kstr+="00 ";}

            if (gxi.rr>pgxi.rr){ if ((gxi.rr-pgxi.rr)>myconf.sensrr){kstr+="10 ";}  else {kstr+="00 ";}}
            else if (pgxi.rr>gxi.rr){if ((pgxi.rr-gxi.rr)>myconf.sensrr){kstr+="01 ";}  else {kstr+="00 ";}}
            else {kstr+="00 ";} 

            if (gxi.m1==1) {kstr+="1"; mstr+="1";} else {kstr+="0"; mstr+="0";}
            if (gxi.m2==1) {kstr+="1"; mstr+="1";} else {kstr+="0"; mstr+="0";}
            if (gxi.m3==1) {kstr+="1"; mstr+="1";} else {kstr+="0"; mstr+="0";}

        }catch(Exception e){
            //e.printStackTrace();
            System.out.println("lT>xy>");
        }
    return kstr;
    }

    private void dobinkey(String kstr){
        ArrayList cmd=cmap.get(kstr);
        if (cmd!=null) { //here get the actions for the key
            parsedKey pxlocal=pxkeys.get(kstr);
            System.out.print("Got ");
            System.out.println(cmd);
            for (int n=0;n<pxlocal.vals.size();n++){
                switch (pxlocal.modes.get(n)){
                    case '+':
                        switch (pxlocal.vals.get(n)){
                            case -1001:
                                System.out.println("Will press btn 1");
                                break;
                            case -2001:
                                System.out.println("Will press btn 2");
                                break;
                            case -3001:
                                System.out.println("Will press btn 3");
                                break;
                            default:                                    
                                System.out.println("Will press key ");
                                System.out.println(pxlocal.vals.get(n));
                        }
                        break;
                    case '-':
                        switch (pxlocal.vals.get(n)){
                            case -1001:
                                System.out.println("Will release btn 1");
                                break;
                            case -2001:
                                System.out.println("Will release btn 2");
                                break;
                            case -3001:
                                System.out.println("Will release btn 3");
                                break;
                            default:                                    
                                System.out.println("Will release key ");
                                System.out.println(pxlocal.vals.get(n));
                        }
                        break;
                    case '/':
                        switch (pxlocal.vals.get(n)){
                            case -1001:
                                System.out.println("Will press & releasebtn 1");
                                break;
                            case -2001:
                                System.out.println("Will press & releasebtn 2");
                                break;
                            case -3001:
                                System.out.println("Will press & release btn 3");
                                break;
                            default:                                    
                                System.out.println("Will press & release key ");
                                gbot.typer(pxlocal.vals.get(n));
                                System.out.println(pxlocal.vals.get(n));
                        }
                        break;
                }
            }
        }
    
    }
    
    private String makeasckey(gxItem gxi){
        String bstr="",tmpstr="";
        float tmpnum;
        
        //see if we have buttons clicked/released etc
        if (gxi.m1==1){bstr="m1+";} else if (gxi.m1==2){ bstr="m1-";}
        if (bstr.length()>0){bstr+=",";}
        if (gxi.m2==1){bstr+="m2+";} else if (gxi.m2==2){ bstr="m2-";}
        if (bstr.length()>0){bstr+=",";}
        if (gxi.m3==1){bstr+="m3+";} else if (gxi.m3==2){ bstr="m3-";}
        if (bstr.length()>0){bstr+=",";}
        
        //findout the abs change in each axis and factor it in proportion to the sensitivity
        float chax=0,chay=0,chaz=0,chyy=0,chpp=0,chrr=0;        
        chax = abs(gxi.ax-pgxi.ax)/myconf.sensax;
        chay = abs(gxi.ay-pgxi.ay)/myconf.sensay;
        chaz = abs(gxi.az-pgxi.az)/myconf.sensaz;
        chyy = abs(gxi.yy-pgxi.yy)/myconf.sensyy;
        chpp = abs(gxi.pp-pgxi.pp)/myconf.senspp;
        chrr = abs(gxi.rr-pgxi.rr)/myconf.sensrr;
        
        
        //find the one with biggest change
        tmpnum=0;
        if (chax>tmpnum){tmpnum=chax; tmpstr="ax";}
        else if (chay>tmpnum) {tmpnum=chay; tmpstr="ay";}
        else if (chaz>tmpnum) {tmpnum=chaz; tmpstr="az";}
        else if (chyy>tmpnum) {tmpnum=chyy; tmpstr="yy";}
        else if (chpp>tmpnum) {tmpnum=chpp; tmpstr="pp";}
        else if (chrr>tmpnum) {tmpnum=chrr; tmpstr="rr";}
        if (tmpstr.length()>0) {System.out.println("Biggest change in -> "+tmpstr);}
        //for the biggest one, see if we need + / ++ / - / -- and return with bstr+new
        switch(tmpstr){
            case "ax":
                if (gxi.ax>pgxi.ax) { //here find-repl "return bstr+" to xstack.push( TODO
                    if(chax>myconf.axmul){ return bstr+"ax++";} else {return bstr+"ax+";}
                }
                else {
                    if(chax>myconf.axmul){ return bstr+"ax--";} else {return bstr+"ax-";}
                }
            case "ay":
                if (gxi.ay>pgxi.ay) {
                    if(chax>myconf.aymul){ return bstr+"ay++";} else {return bstr+"ay+";}
                }
                else {
                    if(chax>myconf.aymul){ return bstr+"ay--";} else {return bstr+"ay-";}
                }
            case "az":
                if (gxi.az>pgxi.az) {
                    if(chaz>myconf.azmul){ return bstr+"az++";} else {return bstr+"az+";}
                }
                else {
                    if(chaz>myconf.azmul){ return bstr+"az--";} else {return bstr+"az-";}
                }
            case "yy":
                if (gxi.yy>pgxi.yy) {
                    if(chyy>myconf.yymul){ return bstr+"yy++";} else {return bstr+"yy+";}
                }
                else {
                    if(chyy>myconf.yymul){ return bstr+"yy--";} else {return bstr+"yy-";}
                }
            case "pp":
                if (gxi.pp>pgxi.pp) {
                    if(chpp>myconf.ppmul){ return bstr+"pp++";} else {return bstr+"pp+";}
                }
                else {
                    if(chpp>myconf.ppmul){ return bstr+"pp--";} else {return bstr+"pp-";}
                }
            case "rr":
                if (gxi.rr>pgxi.rr) {
                    if(chrr>myconf.rrmul){ return bstr+"rr++";} else {return bstr+"rr+";}
                }
                else {
                    if(chrr>myconf.rrmul){ return bstr+"rr--";} else {return bstr+"rr-";}
                }
            default:
                return bstr;
        }

    }
    
    private void doasckey(String kstr){
        //System.out.println("Got : "+kstr);
        xstack.push(kstr);
        
        String tmpx="";
        try{
            tmpx=cmap.get(kstr).get(0);
        }
        catch(Exception e){}
        
        if (tmpx.length()>0){
            //System.out.println(cmap.get(kstr).get(0)); 
        }
        System.out.println(xstack.commonest());
    }

}