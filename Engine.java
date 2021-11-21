package project;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


public class Engine extends JFrame{
	public static int speed=1000;
	public static JLabel[] arrjlabel=new JLabel[9];
	public static JLabel[] heart=new JLabel[3]; //목숨 레이블
	public static JTextField inputText;
	
	public  WordStart word_start=new WordStart();
	public  Random random=new Random();
	public Rain data_rain=new Rain();
	
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
		setVisible(true);
	}
	public void start(GrassPanel Name) {
		word_start.create();
		word_start.shuffle();
		for(int i=0;i<arrjlabel.length;i++) {
			arrjlabel[i]=new JLabel(word_start.arr.get(i));
			arrjlabel[i].setFont(new Font("굴림", Font.BOLD, 20));
			arrjlabel[i].setSize(60,60);
			arrjlabel[i].setLocation(1300,random.nextInt(650)+10);
			Name.add(arrjlabel[i]);
		}
		data_rain.start();
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


