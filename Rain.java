package project;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Rain extends Thread{

	public void run() {
		for(int i=0;i<100;i++) {
			try {
				for(int j=0;j<Engine.arrjlabel.length;j++) {
					int x=Engine.arrjlabel[j].getX();
					int y=Engine.arrjlabel[j].getY();
					x-=10;
					Engine.arrjlabel[j].setLocation(x,y);
					if(Engine.arrjlabel[j].isVisible()&&Engine.arrjlabel[j].getX()<20) {
						Engine.arrjlabel[j].setVisible(false);
					}
				}
				Thread.sleep(Engine.speed);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
}

