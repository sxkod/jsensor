/*
reads game.map and maps incoming events to a keyboard/mouse event
*/
package gxlaunch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author skodela
 */
public class gxMapping {
    private static String MAPFILE;
    private static String dxin;
    private static BufferedReader br=null;
    private static StringBuilder sb;
    private static HashMap<String, ArrayList<String>> mapdata = new HashMap<String,ArrayList<String>>();
    private static List<String> evts = Arrays.asList("ax+", "ax-","", "ay+", "ay-","", "az+", "az-","", "yy+", "yy-", "","pp+", "pp-","", "rr+", "rr-","", "m1", "m2", "m3");
    private static List<String> btns = Arrays.asList("m1", "m2", "m3");
    private static List<String> nonbtns = Arrays.asList("ax+", "ax-","", "ay+", "ay-","", "az+", "az-","", "yy+", "yy-", "","pp+", "pp-","", "rr+", "rr-");

  
    
    gxMapping(){}


    public String loadmap(){
        try {
            br = new BufferedReader(new FileReader("mapping/"+MAPFILE));                   
        } catch (Exception ex) {
                System.out.println("Map file doesnot exist!");
                return "FAIL 1";
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
                System.out.println("Could not read Map file!");
                return "FAIL 2";
        } finally {
            try {
                br.close();
            } catch (Exception ex) {
                System.out.println("Did not close map file!");
                return "FAIL 3";
            }
        }        
    if (dxin!=null) {return dxin;} else {return "FAIL 4";}
    } 

    private void procbtns(String s){
        String[] tmp=s.split("==");   
        //process left of ==
        String tmpgrp=null;
        if ( (tmp[0].contains("[")) && (tmp[0].contains("]"))) {
            tmpgrp=tmp[0].substring(1,tmp[0].length()-1);}
        for (String ex: nonbtns){ //if we see anything other than buttons, getout, it is a combo!
            if ((tmpgrp.contains(ex) && (ex.length()>0))){return;} 
        }

        String kstr="";            
        for (String x: btns){
            if(x.length()==0) {kstr+=" "; continue;} 
            if (tmpgrp.contains(x)) { kstr+="1";  }
            else { kstr+="0";}

        }        
        //process right of ==
        String asubkey="",asubdur="",asubmul="";
        if(tmp[1].contains(",")) {
            String[] tmpact;
            int round=0;
            tmpact=tmp[1].split(",");
            for (String asub : tmpact) {
                String tmpasub=asub.replace("(", "");
                tmpasub=tmpasub.replace(")", "");

                switch (round) {
                    case 0:
                        asubkey=tmpasub;
                        round=round+1;
                        continue;
                    case 1:
                        asubdur=tmpasub;
                        round=round+1;
                        continue;
                    case 2:
                        asubmul=tmpasub;
                        round=round+1;
                        continue;         
                }
            }
        }
                
        gxlaunch.laucherUI.lui.setlog("gxMapping: Mapping "+s+" to "+asubkey);

        ArrayList<String> litem=new ArrayList<String>();
        litem.add(asubkey);
        litem.add(asubdur);
        litem.add(asubmul);
        mapdata.put(kstr,litem); 
        return ;
    }
    
    private void procevts(String s){
            String[] tmp=s.split("==");   
            //process left of ==
            String tmpgrp=null;
            if ( (tmp[0].contains("[")) && (tmp[0].contains("]"))) {
                tmpgrp=tmp[0].substring(1,tmp[0].length()-1);}
        
            int hasnonbtns=0;
            for (String ex: nonbtns){ //if we see only buttons, getout, they are done!
            if ((tmpgrp.contains(ex)) && (ex.length()>0)){hasnonbtns=1; } 
            }
            if (hasnonbtns==0){return;}

            gxlaunch.laucherUI.lui.setlog("gxMapping: Mapping "+s);
        
            String kstr="";            
            for (String x: evts){
                if(x.length()==0) {kstr+=" "; continue;} 
                if (tmpgrp.contains(x)) { kstr+="1";  }
                else { kstr+="0";}
               
            }
            //process right of ==
            String asubkey="",asubdur="",asubmul="";
            if(tmp[1].contains(",")) {
                String[] tmpact;
                int round=0;
                tmpact=tmp[1].split(",");
                for (String asub : tmpact) {
                    String tmpasub=asub.replace("(", "");
                    tmpasub=tmpasub.replace(")", "");
                    
                    switch (round) {
                        case 0:
                            asubkey=tmpasub;
                            round=round+1;
                            continue;
                        case 1:
                            asubdur=tmpasub;
                            round=round+1;
                            continue;
                        case 2:
                            asubmul=tmpasub;
                            round=round+1;
                            continue;         
                    }
                }
            }
            
                
        gxlaunch.laucherUI.lui.setlog("gxMapping: Mapping "+s+" to "+asubkey);

        ArrayList<String> litem=new ArrayList<String>();
        litem.add(asubkey);
        litem.add(asubdur);
        litem.add(asubmul);
        mapdata.put(kstr,litem);       
    }
    
    public void procmap(String fname){
        MAPFILE=fname;
        loadmap();
        if (dxin==null) {gxlaunch.laucherUI.lui.setlog("\nUnable to load the specified map."); return; }
        for (String s: dxin.split(System.lineSeparator())){
                        
            s=s.replaceAll("^/+", "");
            s=s.replaceAll("/+$", "");
            if (s.length()==0) {continue;}
            if (!s.contains("==")) {continue; }
            if (s.charAt(0)=='#') {continue;}
            procevts(s);
            procbtns(s);
        }
    }
    
    public HashMap<String, ArrayList<String>> getmapping(){
        return mapdata;
    }

}