/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gxlaunch;

/**
 *
 * @author skodela
 */
public class gxStrkey {
 
    public String makebinkey(gxConf myconf, gxItem[] gxitems){
        String kstr="",mstr="";
        gxItem gxi=gxitems[0];
        gxItem pgxi=gxitems[1];
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
    
    public String makeasckey(gxConf myconf, gxItem[] gxitems){
        String bstr="";
        gxItem gxi=gxitems[0];
        gxItem pgxi=gxitems[1];
        
        if (gxi.m1==1){bstr="m1";}
        if (gxi.m2==1){bstr+="m2";}
        if (gxi.m3==1){bstr+="m3";}
        if (!bstr.isEmpty()){return bstr;}
  
        if (gxi.ax>pgxi.ax+myconf.sensax) {return "ax+";}
        else if (gxi.ax+myconf.sensax<pgxi.ax) {return "ax-";}
        
        else if (gxi.ay>pgxi.ay+myconf.sensay) {return "ay+";}
        else if (gxi.ay+myconf.sensay<pgxi.ay) {return "ay-";}
        
        else if (gxi.az>pgxi.az+myconf.sensaz) {return "az+";}
        else if (gxi.az+myconf.sensaz<pgxi.az) {return "az-";}
        
        else if (gxi.yy>pgxi.yy+myconf.sensyy) {return "yy+";}
        else if (gxi.yy+myconf.sensyy<pgxi.yy) {return "yy-";}
        
        else if (gxi.pp>pgxi.pp+myconf.senspp) {return "pp+";}
        else if (gxi.pp+myconf.senspp<pgxi.pp) {return "pp-";}
        
        else if (gxi.rr>pgxi.rr+myconf.sensrr) {return "rr+";}
        else if (gxi.rr+myconf.sensrr<pgxi.rr) {return "rr-";}

        return "";
    }
        
}
