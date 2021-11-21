package project;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class StartPanel extends JPanel{
	private ImageIcon background=new ImageIcon("image/background.jpg");
	private Image backimg=background.getImage();
	private ImageIcon goat=new ImageIcon("image/goat.png");
	private Image goatimg=goat.getImage();
	
	private ImageIcon startimg=new ImageIcon("image/start.png");
	private ImageIcon helpimg=new ImageIcon("image/help.png");
	
	
	private JButton startbutton,help;
	private Engine win;
	
	
	
	public StartPanel(Engine win) {
		this.win=win;
		setLayout(null);
		
		startbutton=new JButton(startimg);
		startbutton.setSize(300,120);
		startbutton.setLocation(600,300);
		startbutton.setBorderPainted(false);
		add(startbutton);
		startbutton.addActionListener(new MyActionListener());
		
		help=new JButton(helpimg);
		help.setSize(300,120);
		help.setLocation(600,500);
		help.setBorderPainted(false);
		help.addActionListener(new MyActionListener());
		add(help);
		
	}
	class MyActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==help) {
				win.change("HelpPanel");
			}
			else if(e.getSource()==startbutton) {
				win.change("HomePanel");
			}
		}
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backimg,0,0,getWidth(),getHeight(),this);
		g.drawImage(goatimg,300,10,100,100,this);
		g.drawImage(goatimg,1100,10,100,100,this);
		g.setColor(new Color(153,000,204));
		g.setFont(new Font("Ink Free",Font.BOLD,60));
		g.drawString("English Typing Game",450,80);
	}
}

