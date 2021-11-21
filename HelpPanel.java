package project;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class HelpPanel extends JPanel{
	private ImageIcon background=new ImageIcon("image/background.jpg");
	private Image backimg=background.getImage();
	private ImageIcon backbutton=new ImageIcon("image/back.png");
	private JButton back;
	private Engine win=null;
	
	
	public HelpPanel(Engine win) {
		this.win=win;
		setLayout(null);
		
		back=new JButton(backbutton);
		back.setSize(300,120);
		back.setLocation(1000,500);
		back.setBorderPainted(false);
		add(back);
		back.addActionListener(new MyActionListener());
	}
	class MyActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==back) {
				win.change("StartPanel");
			}
		}
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backimg,0,0,getWidth(),getHeight(),this);
		g.setColor(new Color(000,051,051));
		g.setFont(new Font("맑은 고딕",Font.BOLD,20));
		g.drawString("1.게임 시작 후 탐험지를 정해주세요 (초원,바다,지옥)",520,320);
		g.drawString("2.탐험지에 따라 난이도가 다르며 탐험을 통해 골드를 획득할 수 있습니다.",440,370);
		g.drawString("3.모은 골드를 통해 상점에서 아이템을 구매할 수 있습니다",500,420);
		g.drawString("4.악당(보스)를 물리쳐주세요!!",590,470);
	}
}

