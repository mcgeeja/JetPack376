package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class LevelReader {


	private Graphics2D g;
	private char[][] ch;
	private int levelNum;
	public Player player;
	protected ArrayList <Platform> platforms;
	protected ArrayList <Fuel> fuels;
	protected ArrayList<Rocket> rocketPieces = new ArrayList<>();
	
	protected FileReader f;
	protected int rocketPiece = 1;
	protected int on;

	    public LevelReader(int num) {
	    	this.on = 1;
	    	this.ch = new char[10][20];
	    	this.platforms = new ArrayList<Platform>() ;
	    	this.fuels = new ArrayList<Fuel>();
	    	if(num == 1) {
	    	try {
				this.f = new FileReader("levels/levelOne.txt");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	    	}
	    	if(num == 2) {
		    	try {
					this.f = new FileReader("levels/levelTwo.txt");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
		    }
	    	if(num == 3) {
		    	try {
					this.f = new FileReader("levels/levelThree.txt");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
		    	}
	    	int count = 0;
	        Scanner scanner;
	        scanner = new Scanner(this.f);
	        
	    	this.ch = new char[10][20];
	    	this.platforms = new ArrayList<Platform>() ;
        	
        	
	        while(scanner.hasNextLine()) {
	        	
	        	String line = scanner.nextLine();
	        	for(int i = 0;i < line.length(); i++){
	            	char c = line.charAt(i);	  
	            	this.ch[count][i] = c;
	            	if(c == '1') {
	            		this.on = 1;
	            	}
	            	if(c == '2') {
	            		this.on = 1;
	            	}
	            	if(c == '_') {
	            		Platform p = new Platform(i*96, count*108, 100, 40);
	            		
	            		platforms.add(p);
	            		
	            	}
	            	
	            	if(c == 'f') {
	            		Platform p = new Platform(i*96, count*108, 100, 40);
	            		Fuel fuel = new Fuel((i*96)-40, (count*108)- 40);
	            		fuels.add(fuel);
	            		platforms.add(p);
	            		
	            	}
	            	if(c == '-') {
	            		Platform p = new Platform(i*96, count*108, 100, 70);
	            		
	            		platforms.add(p);
	            	}
	            	if(c == 'R') {
	            		Rocket r = new Rocket(i*96, count*108, this.rocketPiece);
	            		rocketPieces.add(r);
	            		rocketPiece += 1;
	            	}
	            		
	            }
	            count += 1;
	        	
	        }
	        scanner.close();
	        rocketPiece = 1;
	    }
	 

	    public void drawFile(Graphics2D g) {

	        
        	
        	for(int i = 0; i < this.platforms.size(); i++) {
        		this.platforms.get(i).drawOn(g);
        	}
        	for(int i = 0; i < this.fuels.size(); i++) {
        		this.fuels.get(i).drawOn(g);
        	}
        	for(int i = 0; i < this.rocketPieces.size(); i++) {
        		this.rocketPieces.get(i).draw(g);
        	}

	        
	    }
	   
	    
}
