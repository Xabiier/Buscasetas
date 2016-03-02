package org.pokesweeper.model;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Helbideak {

	public static Font iturria;
	public static Font unownIturria;
	public static Image ikonoa;
	public static Cursor kursorea;
	
	public static Icon pikaNormal;
	public static Icon pikaIrabazi;
	public static Icon pikaGaldu;
	public static Icon pikaKlik;
	
	public static Icon voltorb;
	public static Icon voltorb_s;
	
	public static Icon[] lurra = new Icon[9];
	public static Icon[] belar_normal = new Icon[9];
	public static Icon[] belar_mugimendu = new Icon[9];
	public static Icon[] zenbaki = new Icon[8];
	
	public static void denakKargatu(){
		Helbideak.besteakKargatu();
		Helbideak.belarraKargatu();
		Helbideak.lurraKargatu();
		//Helbideak.mugimenduaKargatu();
		Helbideak.pikaKargatu();
		Helbideak.zenbakiakKargatu();
	}
	
	private static void besteakKargatu(){
		try{
			iturria = (Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("src/skin/font/normala.ttf"))).deriveFont(12F);
			unownIturria = (Font.createFont( Font.TRUETYPE_FONT, new FileInputStream("src/skin/font/unown.ttf"))).deriveFont(12F);
			ikonoa = ImageIO.read(new File("src/skin/ikonoa.png"));
			kursorea = Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage("src/skin/kursorea.png") , new Point(3, 30), "img");
			voltorb = new ImageIcon(ImageIO.read(Helbideak.class.getResource("/skin/voltorb/voltorb.png")));
			voltorb_s = new ImageIcon(ImageIO.read(Helbideak.class.getResource("/skin/voltorb/voltorb_s.png")));
		}catch (IOException | FontFormatException e){
			 e.printStackTrace();
		}
	}
		
	private static void pikaKargatu(){
		try {
		pikaNormal = new ImageIcon(ImageIO.read(Helbideak.class.getResource("/skin/pika/normal.png")));
		pikaIrabazi = new ImageIcon(ImageIO.read(Helbideak.class.getResource("/skin/pika/irabazi.png")));
		pikaGaldu = new ImageIcon(ImageIO.read(Helbideak.class.getResource("/skin/pika/galdu.png")));
		pikaKlik = new ImageIcon(ImageIO.read(Helbideak.class.getResource("/skin/pika/klik.png")));
		} catch (IOException e) {}
	}
		
	private static void lurraKargatu(){
		try{
			for(int i = 0; i < lurra.length; i++)
				lurra[i] = new ImageIcon(ImageIO.read(Helbideak.class.getResource("/skin/lurra/"+(i+1)+".png")));
		} catch (IOException e) {}
		
	}
	
	private static void belarraKargatu(){
		try{
			for(int i = 0; i < belar_normal.length; i++)
				belar_normal[i] = new ImageIcon(ImageIO.read(Helbideak.class.getResource("/skin/belarra/normala/"+(i+1)+".png")));
		} catch (IOException e) {}
	}
	
	/*private static void mugimenduaKargatu(){
		try{
			//belar_mugimendu_1 = new ImageIcon(ImageIO.read(Helbideak.class.getResource("/skin/belarra/mugimendua/1/1.gif")));
			//GIF BAKARRA EGONGO DA? (SETROLLOVERICON?)
		} catch (IOException e) {}
	}*/
	
	private static void zenbakiakKargatu(){
		try{
			for(int i = 0; i < zenbaki.length; i++)
				zenbaki[i] = new ImageIcon(ImageIO.read(Helbideak.class.getResource("/skin/zenbakiak/"+(i+1)+".png")));
		} catch (IOException e) {}
	}
}
