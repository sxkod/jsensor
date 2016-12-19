package gxlaunch;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 *
 * @author skodela
 */
public class gxKeys {
    /*private String[] premods={"^","&","@"};
    private String[] postmods={"+","*","/"};
    private String[] alphabets={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
    private String[] Alphabets={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    private String[] nums={"0","1","2","3","4","5","6","7","8","9"}; */
    public ArrayList<ArrayList<Integer>> resx;
    
    
    gxKeys(){};
      
    ArrayList<ArrayList<Integer>> matchone(String enc){
      resx=new ArrayList<ArrayList<Integer>>();
      //check first char for ^&@ for modifiers
      //check last char for +*/ for click-press/release, click-press/hold, release
      //multiple events are seperated by a ##sign - THIS WILL BE DONE LATER :-)
      //leftover string between the modifiers and c-p/hold/release modifiers is the actual key

      String midstr=""; 
      char postmods='\0';
      ArrayList<Integer> axlocal=new ArrayList<Integer>();

      switch (enc.charAt(0)){ //do the first char
          case '^':
              axlocal.add(KeyEvent.CTRL_DOWN_MASK);
              axlocal.add(4);
              resx.add(axlocal);
              axlocal.clear();
              midstr+=enc.substring(1);
              break;
          case '&':
              axlocal.add(KeyEvent.SHIFT_DOWN_MASK);
              axlocal.add(4);
              resx.add(axlocal);
              axlocal.clear();
              midstr+=enc.substring(1);
              break;
          case '@':
              axlocal.add(KeyEvent.ALT_DOWN_MASK);
              axlocal.add(4);
              resx.add(axlocal);
              axlocal.clear();
              midstr+=enc.substring(1);
              break;

      }


      //do we have a hold modifier
      //default is +
    if ( (midstr.charAt(midstr.length()-1)=='+') || (midstr.charAt(midstr.length()-1)=='*') || (midstr.charAt(midstr.length()-1)=='/')){
        if (midstr.length()>1){
            postmods=midstr.charAt(midstr.length()-1);
            midstr=midstr.substring(0,midstr.length()-1);
        }
    }
    
    switch (midstr){ // do the rest
        case "LA":
            axlocal.add(KeyEvent.VK_RIGHT);
            switch (postmods){
                case '+':
                    axlocal.add(1);
                    break;
                case '*':
                    axlocal.add(2);
                    break;
                case '/':
                    axlocal.add(3);
                    break;
                default:                    
                    axlocal.add(1);
                    break;
            }
            
            break;
    }
    
    
    return resx;
    }
}

/*

RESULT is an ArrayList of ArrayList<Integre>
So it can be [
    KeyEvent, Type=
        press+release=1, 
        press+hold=2, 
        release=3
        release afternext key=4;
]

RA, LA, DA, UA for arrows
SP for space
RT for enter
*, ., ;, :, /, \, [,], - , symbols such as @,# for themselves

^E for CTRL-E
&E for SHIFT-E
@E for Alt-E

0-9 for numpad numbers
_0 to _9 for top line numbers

f1-f12 for themselves

X1+,X2+,X3+,X4+,X5+ for mouse click/release LEFT, RIGHT, MIDDLE, SCROLL UP AND SCROLL DOWN
X1*,X2*,X3*,X4*,X5* for mouse click&hold LEFT, RIGHT, MIDDLE, SCROLL UP AND SCROLL DOWN
X1/,X2/,X3/,X4/,X5/ for mouse release LEFT, RIGHT, MIDDLE, SCROLL UP AND SCROLL DOWN

^X1 .. CTRL CLICK 1 ..
&X1 .. SHIFT CLICK 1 ..
@X1 .. ALT CLICK 1 ..
*/
