package project;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;


public class WordStart {
	public ArrayList<String> arr = new ArrayList<>(); 
	public void create() {
		try {
			Scanner inputStream=new Scanner(new File("word.txt"));
			while(inputStream.hasNextLine())
				this.arr.add(inputStream.nextLine());
		}catch(FileNotFoundException e) {
			System.out.println("더 이상 없음");
		}
	}
	public void shuffle() {
		Collections.shuffle(this.arr);
	}
}

