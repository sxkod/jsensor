/*
process the incoming string from the sensor and generate a gxitem object with the data
 */
package gxlaunch;

/**
 *
 * @author skodela
 */
public class gxProc {
    
    gxProc(){};
    
    public gxItem process(String str){
        gxItem gxi=new gxItem();
        if (str==null){gxi.ecode=1; return gxi;}
        
        try{
            if (!str.contains("ax")){ gxi.ecode=2; return gxi;}
            if (!str.contains("&")){ gxi.ecode=2; return gxi;}
            
            String[] tokens=str.split("&");
            for (String s: tokens) {
                if (s.contains("=")){

                String[] subtok=s.split("=");
                switch (subtok[0]){
                    case "ax":
                            try { gxi.ax=Integer.parseInt(subtok[1]); } catch (Exception e){gxi.ax=0; }
                            continue;
                    case "ay":
                            try { gxi.ay=Integer.parseInt(subtok[1]);} catch (Exception e){gxi.ay=0; }
                            continue;
                    case "az":
                            try { gxi.az=Integer.parseInt(subtok[1]);} catch (Exception e){gxi.az=0; }
                            continue;

                    case "yy":
                            try { gxi.yy=Integer.parseInt(subtok[1]);} catch (Exception e){gxi.yy=0; }
                            continue;
                    case "pp":
                            try { gxi.pp=Integer.parseInt(subtok[1]);} catch (Exception e){gxi.pp=0;  }
                            continue;
                    case "rr":
                            try { gxi.rr=Integer.parseInt(subtok[1]);} catch (Exception e){gxi.rr=0;  }
                            continue;

                    case "m1":
                            try { gxi.m1=Integer.parseInt(subtok[1]);} catch (Exception e){gxi.m1=0; }
                            continue;
                    case "m2":
                            try { gxi.m2=Integer.parseInt(subtok[1]);} catch (Exception e){gxi.m2=0;  }
                            continue;
                    case "m3":
                            try { gxi.m3=Integer.parseInt(subtok[1]);} catch (Exception e){gxi.m3=0;  }
                            continue;
                }}
            }
        }catch(Exception e){
            e.printStackTrace();
            gxi.ecode=3;
        }
        return gxi;
    }
    
}
