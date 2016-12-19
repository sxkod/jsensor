/*
class to hold the incoming events
 */
package gxlaunch;

/**
 *
 * @author skodela
 */
public class gxItem {
    // gx, gy, gz are y/p/r essentially
    public int ax,ay,az,yy,pp,rr,m1,m2,m3,ecode;
    
    gxItem(){ ecode=0;}
    public void printStr(){
        System.out.println("ax="+ax+" ay="+ay+" az="+az+" yy="+yy+" pp="+pp+" rr="+rr);
    }

}
