import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class GrassPanel extends JPanel{
	private ImageIcon background=new ImageIcon("image/homeback.jpg");
	private Image backimg=background.getImage();
	private ImageIcon warrior2=new ImageIcon("image/warrior2.png");
	private Image warrior2img=warrior2.getImage();
	
	private Vector<JLabel>targetVector = new Vector<JLabel>();
	private JTextField input = new JTextField(30);
	
	private WordThread dd=new WordThread(targetVector);
	private DropWordThread yy=new DropWordThread(targetVector);
	private Engine win=null;
	public GrassPanel(Engine win){
		this.win=win;
		setLayout(null);
		gamestart();
		
		input.setLocation(45,580);
		input.setSize(150,50);
		input.setFont(new Font("Aharoni", Font.PLAIN, 20));
		add(input);
		input.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				synchronized(targetVector) {
					JTextField t = (JTextField)(e.getSource());
					String inWord = t.getText(); // 사용자가 입력한 단어
					for (int i=0; i < targetVector.size(); i++) {
						String text = targetVector.get(i).getText();
						if(text.equals(inWord)) { // 단어맞추기 성공
						
							System.out.println(inWord + " 맞춤"); // 콘솔에서 확인 위함
					     	remove(targetVector.get(i)); // 패널에서 라벨 떼기
							targetVector.remove(i); // targetVector에서 삭제
							t.setText(null); // input 비우기
							break;
						}
						else
							t.setText(null);
						t.requestFocus(); // 엔터 친 후에도 textField에 focus유지
					} // end of for
				}
				
			} // end of actionPerformed()
		});
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backimg,0,0,getWidth(),getHeight(),this);
		g.drawImage(warrior2img,20,230,170,300,this);
	}
	public void gamestart() {
		dd.start();
		yy.start();
	}
	public class WordThread extends Thread{
		private WordStart word_create=new WordStart(); //단어 어레이리스트
		private Vector<JLabel>targetVector = null;
		
		synchronized void generateWord() {
		JLabel targetLabel = new JLabel("");
		// 단어 한 개 선택
		String newWord = word_create.getRandomWord();
		targetLabel.setText(newWord);
		targetLabel.setSize(60,60);
		targetLabel.setFont(new Font("Aharoni", Font.BOLD, 20));
		targetLabel.setBackground(new Color(219, 239, 255));
		int startY = (int) (Math.random()*690);
	
		
		targetLabel.setLocation(1200,startY);
		targetVector.addElement(targetLabel);
		targetLabel.setOpaque(false); // 배경 투명하게
		add(targetLabel);
	}
		public WordThread(Vector<JLabel>targetVector) {
			word_create.create();
			this.targetVector=targetVector;
		}

	@Override
	public void run() {
		while(true) {
		
			generateWord();
			repaint();
			try {
				sleep(1200);
			} catch (InterruptedException e) {
				return;
			}
		} // end of while
	} // end of run()
	}
	
public class DropWordThread extends Thread{
		
		private Vector<JLabel>targetVector = null;
		
		
		public DropWordThread(Vector<JLabel>targetVector) {
			this.targetVector = targetVector;
		}
		
		// y좌표 증가해 단어 밑으로 내림
		synchronized void dropWord() {
			for (int i=0; i<targetVector.size(); i++) {
				int x = targetVector.get(i).getX();
				int y = targetVector.get(i).getY();
				targetVector.get(i).setLocation(x-5, y);
				repaint();
			} // end of for
		}
		
		// targetVector에 들어있는 모든 JLabel들의 y좌표 증가
		@Override
		public void run() {
			 while (true){
				
				 dropWord();
				repaint();
				 try {
					 sleep(100);
					} catch (InterruptedException e) {
						return;
					}
			} // end of while
		} // end of run()
	} // end of DropWordThread

}
