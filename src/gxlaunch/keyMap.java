package gxlaunch;

import com.sun.glass.events.KeyEvent;
import java.awt.event.InputEvent;
import java.util.HashMap;
import javafx.event.EventType;

/**
 *
 * @author skodela
 */
public class keyMap {
    public HashMap<String,Integer> keys;
    keyMap(){
        keys=new HashMap<String,Integer>();
        keys.put("RA", KeyEvent.VK_RIGHT);
        keys.put("LA", KeyEvent.VK_LEFT);
        keys.put("UA", KeyEvent.VK_UP);
        keys.put("DA", KeyEvent.VK_DOWN);
        
        keys.put("A", KeyEvent.VK_A);
        keys.put("B", KeyEvent.VK_B);
        keys.put("C", KeyEvent.VK_C);
        keys.put("D", KeyEvent.VK_D);
        keys.put("E", KeyEvent.VK_E);
        keys.put("F", KeyEvent.VK_F);
        keys.put("G", KeyEvent.VK_G);
        keys.put("H", KeyEvent.VK_H);
        keys.put("I", KeyEvent.VK_I);
        keys.put("J", KeyEvent.VK_J);
        keys.put("K", KeyEvent.VK_K);
        keys.put("L", KeyEvent.VK_L);
        keys.put("M", KeyEvent.VK_M);
        keys.put("N", KeyEvent.VK_N);
        keys.put("O", KeyEvent.VK_O);
        keys.put("P", KeyEvent.VK_P);
        keys.put("Q", KeyEvent.VK_Q);
        keys.put("R", KeyEvent.VK_R);
        keys.put("S", KeyEvent.VK_S);
        keys.put("T", KeyEvent.VK_T);
        keys.put("U", KeyEvent.VK_U);
        keys.put("V", KeyEvent.VK_V);
        keys.put("W", KeyEvent.VK_W);
        keys.put("X", KeyEvent.VK_X);
        keys.put("Y", KeyEvent.VK_Y);
        keys.put("Z", KeyEvent.VK_Z);

        keys.put("0", KeyEvent.VK_0);
        keys.put("1", KeyEvent.VK_1);
        keys.put("2", KeyEvent.VK_2);
        keys.put("3", KeyEvent.VK_3);
        keys.put("4", KeyEvent.VK_4);
        keys.put("5", KeyEvent.VK_5);
        keys.put("6", KeyEvent.VK_6);
        keys.put("7", KeyEvent.VK_7);
        keys.put("8", KeyEvent.VK_8);
        keys.put("9", KeyEvent.VK_9);
        
        keys.put("_0", KeyEvent.VK_NUMPAD0);
        keys.put("_1", KeyEvent.VK_NUMPAD1);
        keys.put("_2", KeyEvent.VK_NUMPAD2);
        keys.put("_3", KeyEvent.VK_NUMPAD3);
        keys.put("_4", KeyEvent.VK_NUMPAD4);
        keys.put("_5", KeyEvent.VK_NUMPAD5);
        keys.put("_6", KeyEvent.VK_NUMPAD6);
        keys.put("_7", KeyEvent.VK_NUMPAD7);
        keys.put("_8", KeyEvent.VK_NUMPAD8);
        keys.put("_9", KeyEvent.VK_NUMPAD9);
     
        keys.put("F1", KeyEvent.VK_F1);
        keys.put("F2", KeyEvent.VK_F2);
        keys.put("F3", KeyEvent.VK_F3);
        keys.put("F4", KeyEvent.VK_F4);
        keys.put("F5", KeyEvent.VK_F5);
        keys.put("F6", KeyEvent.VK_F6);
        keys.put("F7", KeyEvent.VK_F7);
        keys.put("F8", KeyEvent.VK_F8);
        keys.put("F9", KeyEvent.VK_F9);
        keys.put("F10", KeyEvent.VK_F10);
        keys.put("F11", KeyEvent.VK_F11);
        keys.put("F12", KeyEvent.VK_F12);
        keys.put("F13", KeyEvent.VK_F13);

        keys.put("!", KeyEvent.VK_EXCLAMATION);
        keys.put("@", KeyEvent.VK_AT);
        keys.put("#", KeyEvent.VK_NUMBER_SIGN);
        keys.put("$", KeyEvent.VK_DOLLAR);
        keys.put("%", KeyEvent.VK_DIVIDE);
        keys.put("^", KeyEvent.VK_CIRCUMFLEX);
        keys.put("&", KeyEvent.VK_AMPERSAND);
        keys.put("*", KeyEvent.VK_ASTERISK);
        keys.put("(", KeyEvent.VK_LEFT_PARENTHESIS);
        keys.put(")", KeyEvent.VK_RIGHT_PARENTHESIS);
        keys.put("-", KeyEvent.VK_MINUS);
        keys.put("=", KeyEvent.VK_EQUALS);
        keys.put("RT", KeyEvent.VK_ENTER);
        keys.put("\\",KeyEvent.VK_BACK_SLASH);
        keys.put("BS", KeyEvent.VK_BACKSPACE);
        keys.put("[", KeyEvent.VK_OPEN_BRACKET);
        keys.put("]", KeyEvent.VK_CLOSE_BRACKET);
        keys.put(";", KeyEvent.VK_SEMICOLON);
        keys.put(":", KeyEvent.VK_COLON);
        keys.put("<", KeyEvent.VK_LESS);
        keys.put(">", KeyEvent.VK_GREATER);
        keys.put(",", KeyEvent.VK_COMMA);
        keys.put(".", KeyEvent.VK_PERIOD);
        keys.put("/", KeyEvent.VK_SLASH);
        //keys.put("?", KeyEvent.VK_);
        
        keys.put("ESC",KeyEvent.VK_ESCAPE);
        keys.put("TAB",KeyEvent.VK_TAB);
        keys.put("DEL",KeyEvent.VK_DELETE);
        keys.put("WIN",KeyEvent.VK_WINDOWS);
        keys.put("CMD",KeyEvent.VK_COMMAND);
        
        keys.put("HOM",KeyEvent.VK_HOME);
        keys.put("END",KeyEvent.VK_END);
        keys.put("PGU",KeyEvent.VK_PAGE_UP);
        keys.put("PGD",KeyEvent.VK_PAGE_DOWN);
        
        keys.put("X1",-1001);
        keys.put("X2",-2001);
        keys.put("X3",-3001);
        
        keys.put("CTL",KeyEvent.VK_CONTROL);
        keys.put("SHF",KeyEvent.VK_SHIFT);
        keys.put("ALT",KeyEvent.VK_ALT);
        keys.put("SP", KeyEvent.VK_SPACE);
    }
}