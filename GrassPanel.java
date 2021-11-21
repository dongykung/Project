package project;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class GrassPanel extends JPanel{
	private ImageIcon background=new ImageIcon("image/homeback.jpg");
	private Image backimg=background.getImage();
	private ImageIcon warrior2=new ImageIcon("image/warrior2.png");
	private Image warrior2img=warrior2.getImage();
	
	
	private Engine win=null;
	public GrassPanel(Engine win){
		this.win=win;
		setLayout(null);
		
		Engine.inputText=new JTextField(2);
		Engine.inputText.setBounds(20, 550, 200, 50);
		Engine.inputText.setOpaque(true);
		Engine.inputText.setBackground(new Color(254,204,153));
		Engine.inputText.setFont(new Font("Comic Sans MS",Font.ITALIC,25));
		Engine.inputText.setFocusable(true);
		Engine.inputText.requestFocus();
		add(Engine.inputText); 
		win.start(this);
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backimg,0,0,getWidth(),getHeight(),this);
		g.drawImage(warrior2img,20,230,170,300,this);
	}
}

