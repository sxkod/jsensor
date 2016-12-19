/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gxlaunch;

//import java.util.ArrayList;

/**
 *
 * @author skodela
 */
public class keyparser {
    parsedKey px;
    keyMap xkeys=new keyMap();
    
    public parsedKey parse(String enc){
        px=new parsedKey();
        px.key=enc;
        if (!(enc.contains("##"))){ enc=enc+"##";}
        String[] segs=enc.split("##");

        for (String eachs: segs) {
            try{
                int knum=xkeys.keys.get(eachs.substring(0,eachs.length()-1));
                char mode=eachs.charAt(eachs.length()-1);
                px.vals.add(knum);
                px.modes.add(mode);
            }
            catch(Exception e){
                gxlaunch.laucherUI.lui.setlog("Unable to get key codes for "+enc);
            }
        }
        return px;
    }
}
