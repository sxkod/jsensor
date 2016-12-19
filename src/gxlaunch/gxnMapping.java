package gxlaunch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class gxnMapping {
   
    private static String MAPFILE;
    private static String dxin;
    private static BufferedReader br=null;
    private static StringBuilder sb;
    private static HashMap<String, ArrayList<String>> mapdata = new HashMap<String,ArrayList<String>>();
    

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

    private void procall(String s){
        String[] tmp=s.split("==");   
        //process left of ==
        String tmpgrp=null;
        if ( (tmp[0].contains("[")) && (tmp[0].contains("]"))) {
            tmpgrp=tmp[0].substring(1,tmp[0].length()-1);}

 
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
        mapdata.put(tmpgrp,litem); 
        return ;
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
            procall(s);
        }
    }
    
    public HashMap<String, ArrayList<String>> getmapping(){
        return mapdata;
    }

}


