package gxlaunch;

import java.util.HashMap;

/**
 *
 * @author skodela
 */
public class gxStack {
    private int sz;
    private String[] stack;
    private String[] xkeys={"ax-","ax--","ax+","ax++","ay-","ay--","ay+","ay++","az-","az--","az+","az++","yy-","yy--","yy+","yy++","pp-","pp--","pp+","pp++","rr-","rr--","rr+","rr++"};
    private HashMap<String,Integer> xkset;
    private int sdiff=1;
    
    gxStack(){
        sz=5;
        stack=new String[sz];
        xkset=new HashMap<String,Integer>();
        loadxkset();
    }
    gxStack(int n){
        sz=n;
        stack=new String[sz];
        xkset=new HashMap<String,Integer>();
        loadxkset();
    }
    
    public void setsdiff(int n){
        sdiff=n;
    }
    
    
    private void loadxkset(){
        for (int n=0;n<24;n++){ xkset.put(xkeys[n], 0);}
    }
    
    public void push(String itx){
        for (int n=sz-1;n>0;n--){
            stack[n]=stack[n-1];
        }
        stack[0]=itx;
    }
    
    public void print(){
        System.out.print("\nStack>> ");
        for (int n=0;n<sz;n++){ System.out.print(stack[n]+" : ");}          
        System.out.println("");
    }
    
    public void clear(){
        for (int n=0;n<sz;n++){ stack[n]=null;}      
    }
    public String commonest(){
        loadxkset();
        print();
        for (String itx: stack){
            if(itx==null){continue;}
            int cval=xkset.get(itx);
            cval++;
            xkset.put(itx, cval);
        }
        int maxval=0;
        String maxstr="";
        for (String tmp: xkset.keySet()){
            if (xkset.get(tmp)>maxval){
                maxval=xkset.get(tmp);
                maxstr=tmp;
            }        
        }
        if (xkset.get(maxstr)-xkset.get(stack[0])>sdiff){return maxstr;}
        else {return stack[0];}
    }
    
}
