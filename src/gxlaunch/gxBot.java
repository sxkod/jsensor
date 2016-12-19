/*
the robot class
 */
package gxlaunch;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

/**
 *
 * @author skodela
 */
public class gxBot {

  public Robot grobot = new Robot();

  
  public gxBot() throws AWTException
  {
      try{
    grobot.setAutoDelay(40);
    grobot.setAutoWaitForIdle(true);
      }catch(Exception e){e.printStackTrace();}
  }
  
  public void ltClick()
  {
    grobot.mousePress(InputEvent.BUTTON1_MASK);
    grobot.delay(100);
    grobot.mouseRelease(InputEvent.BUTTON1_MASK);
    grobot.delay(100);
  }
  
  public void mdClick()
  {
    grobot.mousePress(InputEvent.BUTTON2_MASK);
    grobot.delay(100);
    grobot.mouseRelease(InputEvent.BUTTON2_MASK);
    grobot.delay(100);
  }
  
   public void rtClick()
  {
    grobot.mousePress(InputEvent.BUTTON3_MASK);
    grobot.delay(100);
    grobot.mouseRelease(InputEvent.BUTTON3_MASK);
    grobot.delay(100);
  }
    
  public void mmove(int x, int y){
      grobot.mouseMove(x, y);
  }
    
  public void mscroll(int x){
      grobot.mouseWheel(x);
  }
  
  public void typer(int n)
  {
    grobot.delay(40);
    grobot.keyPress(n);
    grobot.keyRelease(n);
  }

  public void typer(String str)
  {
    byte[] bytes = str.getBytes();
    
    if (str.length()<1){return;}
    
    for (byte b : bytes)
    {
      int code = b;
      if (code > 96 && code < 123) code = code - 32;
      grobot.delay(40);
      grobot.keyPress(code);
      grobot.keyRelease(code);
    }
  }
  
  public void sleeper(int n){
    grobot.delay(n);
  }
}
