package project;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class GrassPanel extends JPanel{
	private int count;
	private ImageIcon background=new ImageIcon("image/homeback.jpg");
	private Image backimg=background.getImage();
	private ImageIcon warrior2=new ImageIcon("image/warrior2.png");
	private Image warrior2img=warrior2.getImage();
	private ImageIcon Boss=new ImageIcon("image/goblin.png");
	private Image Goblin=Boss.getImage();
	private ImageIcon click=new ImageIcon("image/grassstart.png");
	private ImageIcon back2=new ImageIcon("image/back2.png");
	private ImageIcon restart=new ImageIcon("image/restart.png");
	private Vector<JLabel>monster = new Vector<JLabel>();//날라오는 단어벡터

	private JTextField input = new JTextField(30);//입력창
	private JButton stb,restartbt,end;
	private JLabel countlabel=new JLabel("");
	private JLabel bossinfo;
	
	
	//스레드
	WordThread wordthread=null;
	DropWordThread dropthread=null;
	BottomWordThread bottomthread=null;
	//스레드
	
	private Engine win=null;
	

	public GrassPanel(Engine win){
		this.win=win;
		setLayout(null);
		
		createstb();
		createrestartbt();
		createend();
		
	}//생성자 종료
	public void createstb() {
		stb=new JButton(click);
		stb.setLocation(680,280);
		stb.setSize(220,80);
		add(stb);
		stb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==stb) {
					gamestart();
					stb.setVisible(false);
				}
			}
		});
	}
	public void createrestartbt() {
		restartbt=new JButton(restart);
		restartbt.setBounds(500,300,190,80);
		add(restartbt);
		restartbt.setVisible(false);
		restartbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==restartbt){
					gamestart();
				}
			}
		});
	}
	public void createend() {
		end=new JButton(back2);
		end.setBounds(720,300,190,80);
		add(end);
		end.setVisible(false);
		end.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==end){
					win.change("HomePanel");
					end.setVisible(false);
					restartbt.setVisible(false);
					createstb();
				}
			}
		});

	}
	public void createinput() {
		input.setLocation(45,580);
		input.setSize(150,50);
		input.setFont(new Font("Aharoni", Font.PLAIN, 20));
		add(input);//단어 입력창 추가
		input.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				synchronized(monster) {
					JTextField answer = (JTextField)(e.getSource());
					String inWord = answer.getText(); // 사용자가 입력한 단어
					for (int i=0; i < monster.size(); i++) {
						String text = monster.get(i).getText();
						if(text.equals(inWord)) { // 단어맞추기 성공
							if(count==0) {  //몬스터 목숨이 0이면 탐험 종료
								removeAll();
								createrestartbt();
								createend();
								drawcount();
								gameout();
								revalidate();
								repaint();
								break;
								}
							//System.out.println(inWord + " 맞춤"); // 콘솔확인용
					     	remove(monster.get(i)); // 패널에서 몬스터 제거
					     	monster.remove(i); //몬스터 벡터에서 몬스터 제거
					     	drawcount();
					     	repaint();
					     	answer.setText(null);
					     	count--;
							break; //맞췃으면 반복문 탈출
						}
						else
							answer.setText(null);
						answer.requestFocus(); // 엔터 친 후에도 textField에 focus유지
					} // end of for
					answer.setText(null);
				}
				
			} 
		});//이벤트리스너 종료
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backimg,0,0,getWidth(),getHeight(),this);
		g.drawImage(warrior2img,20,230,170,300,this);
		g.drawImage(Goblin,1250,100,250,500,this);
	}

	public void createthread() {
		 wordthread=new WordThread(monster);
		 dropthread=new DropWordThread(monster);
		 bottomthread=new BottomWordThread(monster);
	}

	public void gamestart() {
		count=12;
		drawbossinfo();
		drawcount();
		createinput();
		createthread();
		end.setVisible(false);
		restartbt.setVisible(false);
		dropthread.start();
		wordthread.start();
		bottomthread.start();
		
	}
	 public void gameout() {
		end.setVisible(true);
		restartbt.setVisible(true);
		dropthread.interrupt();
		wordthread.interrupt();
		bottomthread.interrupt();
		
		
	}
	 public void drawbossinfo() {
		 bossinfo=new JLabel("HP:");
		 bossinfo.setLocation(1350,550);
		 bossinfo.setSize(100,100);
		 bossinfo.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		 bossinfo.setForeground(new Color(255,051,051));
		 add(bossinfo);
	 }
	 public void drawcount() {
			String HP=String.valueOf(count);
			countlabel.setText(HP);
			countlabel.setSize(100,100);
			countlabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
			countlabel.setForeground(new Color(255,051,051));
			countlabel.setLocation(1400,550);
			countlabel.setVisible(true);
			add(countlabel);
	 }
	//단어생성스레드
	public class WordThread extends Thread{
		private WordStart word_create=new WordStart(); //단어 어레이리스트
		private Vector<JLabel>monster = null;
	
		synchronized void generateWord() {
		JLabel monsterLabel = new JLabel("");
		// 단어 한 개 선택
		String newWord = word_create.getRandomWord();
		monsterLabel.setText(newWord);
		monsterLabel.setSize(150,60);
		monsterLabel.setFont(new Font("Aharoni", Font.BOLD, 20));
		monsterLabel.setForeground(new Color(000,051,204));
		int startY = (int) (Math.random()*600+30);
		monsterLabel.setLocation(1250,startY);
		monster.addElement(monsterLabel);
		monsterLabel.setOpaque(false); // 배경 투명하게
		add(monsterLabel);
	}
		public WordThread(Vector<JLabel>monster) {
			word_create.create();
			this.monster=monster;
		}

	@Override
	public void run() {
		while(true) {
		
			generateWord();
			
			try {
				sleep(1500);
			} catch (InterruptedException e) {
				return;
			}
		} // end of while
	} // end of run()
	}
	//단어떨어뜨리기
public class DropWordThread extends Thread{
		
		private Vector<JLabel>monster = null;
		
		
		public DropWordThread(Vector<JLabel>monster) {
			this.monster = monster;
		}
		
		// y좌표 증가해 단어 밑으로 내림
		synchronized void dropWord() {
			for (int i=0; i<monster.size(); i++) {
				int x = monster.get(i).getX();
				int y =monster.get(i).getY();
				monster.get(i).setLocation(x-5, y);
				
			} // end of for
		}
		
		// targetVector에 들어있는 모든 JLabel들의 y좌표 증가
		@Override
		public void run() {
			 while (true){
				
				 dropWord();
				
				 try {
					 sleep(100);
					} catch (InterruptedException e) {
						return;
					}
			} // end of while
		} // end of run()
	} // end of DropWordThread
public class BottomWordThread extends Thread{
	private Vector<JLabel>monster = null;
	public BottomWordThread(Vector<JLabel>targetVector) {
		this.monster = targetVector;
	}
	@Override
	public void run() {
		while(true) {
			try {
				sleep(1);
				for(int i=0; i<monster.size(); i++) {
					// 바닥에 닿은 단어 구하기 위함
					int x = ((JLabel)monster.get(i)).getX();
					if (x< 100) {
						System.out.println(monster.get(i).getText() + " 떨어짐");	
						remove(monster.get(i)); // 패널에서 라벨 떼기
						monster.remove(i); // targetVector에서 삭제
					}
				}
			} catch (InterruptedException e) {
				return;
			}
		} // end of while
	} // end of run()
}

}
