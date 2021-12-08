
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class Engine extends JFrame{
	public static int money=0;

	public StartPanel sPanel=null;
	public HelpPanel hPanel=null;
	public HomePanel homePanel=null;
	public GrassPanel grassPanel=null;
	public Engine() {
		this.setTitle("영타자 게임");
		sPanel=new StartPanel(this);
		hPanel=new HelpPanel(this);
		homePanel=new HomePanel(this);
		grassPanel=new GrassPanel(this);
		add(this.sPanel);
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(0,0);
		setSize(1500,700);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	
	public void change(String panelName) {
		if(panelName.equals("HelpPanel")) {
			getContentPane().removeAll();
			getContentPane().add(hPanel);
			revalidate();
			repaint();
		}
		else if(panelName.equals("StartPanel")) {
			getContentPane().removeAll();
			getContentPane().add(sPanel);
			revalidate();
			repaint();
		}
		else if(panelName.equals("HomePanel")) {
			getContentPane().removeAll();
			getContentPane().add(homePanel);
			revalidate();
			repaint();
		}
		else if(panelName.equals("GrassPanel")) {
			getContentPane().removeAll();
			getContentPane().add(grassPanel);
			revalidate();
			repaint();
		}
	}
	public static void main(String[] args) {
		new Engine();
	}

}
