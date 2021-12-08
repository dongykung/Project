import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class HomePanel extends JPanel{
	private ImageIcon background=new ImageIcon("image/homeback.jpg");
	private Image backimg=background.getImage();
	private ImageIcon warrior=new ImageIcon("image/warrior.png");
	private Image warriorimg=warrior.getImage();
	private ImageIcon grassimg=new ImageIcon("image/grass.png");
	private ImageIcon oceanimg=new ImageIcon("image/ocean.png");
	private ImageIcon hellimg=new ImageIcon("image/hell.png");
	//이미지
	private JButton grass,ocean,hell;
	private JLabel money=null;
	private WordStart word_create=new WordStart(); //단어 어레이리스트
	
	private Engine win=null;
	
	public HomePanel(Engine win) {
		this.win=win;
		setLayout(null);
		
		money=new JLabel("");
		String getmoney=String.valueOf(Engine.money);
		money.setText(getmoney);
		money.setLocation(1400,0);
		money.setSize(200,50);
		money.setForeground(new Color(102,000,153));
		money.setFont(new Font("Comic Sans MS",Font.ITALIC,20));
		add(money);
		
		grass=new JButton(grassimg);
		grass.setSize(185,110);
		grass.setLocation(1200,100);
		grass.setBorderPainted(false);
		add(grass);
		grass.addActionListener(new MyActionListener());
		
		ocean=new JButton(oceanimg);
		ocean.setSize(185,110);
		ocean.setLocation(1200,300);
		ocean.setBorderPainted(false);
		add(ocean);
		grass.addActionListener(new MyActionListener());
		
		hell=new JButton(hellimg);
		hell.setSize(180,110);
		hell.setLocation(1200,500);
		hell.setBorderPainted(false);
		add(hell);
		hell.addActionListener(new MyActionListener());
		
	}
	

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backimg,0,0,getWidth(),getHeight(),this);
		g.drawImage(warriorimg,50,300,170,300,this);
		g.setColor(new Color(000,153,051));
		g.setFont(new Font("Comic Sans MS",Font.BOLD,30));
		g.drawString("GRASS",1090,165);
		g.setColor(new Color(000,051,204));
		g.setFont(new Font("Comic Sans MS",Font.BOLD,30));
		g.drawString("OCEAN",1080,365);
		g.setColor(new Color(153,000,000));
		g.setFont(new Font("Comic Sans MS",Font.ITALIC,30));
		g.drawString("HELL",1120,565);
		g.setColor(new Color(204,153,051));
		g.setFont(new Font("Comic Sans MS",Font.ITALIC,70));
		g.drawString("Choose a place to explore!",180,70);
	
	}
	
	class MyActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==grass) {
				win.change("GrassPanel");
			}
		}
	}

}
