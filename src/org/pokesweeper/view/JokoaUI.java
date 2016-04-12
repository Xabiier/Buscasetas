package org.pokesweeper.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javafx.util.Duration;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import org.pokesweeper.model.Audioa;
import org.pokesweeper.model.Helbideak;
import org.pokesweeper.model.Pika;
import org.pokesweeper.model.Ranking;
import org.pokesweeper.model.Tableroa;

public class JokoaUI extends JFrame implements ActionListener{
	
	//Atributoak
	private static JokoaUI nirejokoa;
	private static final long serialVersionUID = 1L;
	private static JPanel behekoPanela;
	public static boolean bukatuta = false;
	public static KontadoreaUI minaKontadorea;
	public static KontadoreaUI denboraKontadorea;
	public static String erabiltzailea;
	
	// Main metodoa
	public static void main(String[] args) {
		Helbideak.denakKargatu();
		new Splash();
		JDialog.setDefaultLookAndFeelDecorated(true);
	}
	
	//Eraikitzailea
	private JokoaUI(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("PokéSweeper");
		this.setIconImage(Helbideak.ikonoa);
		this.setCursor(Helbideak.kursorea);
		UIManager.put("Menu.font", Helbideak.iturria);
		UIManager.put("MenuItem.font", Helbideak.iturria);
		
		
		goikoPanelaEraiki();
		
		behekoPanela = new JPanel();
		behekoPanela.setBackground(new Color(112, 200, 160));
		behekoPanela.setLayout(new BorderLayout(0, 0));
		getContentPane().add(behekoPanela, BorderLayout.CENTER);
		
		panelakEraiki();
		
		Tableroa.getNireTableroa().tableroaEraiki(7, 10, 0, true);
		TableroaUI.getNireTableroaUI().tableroaEraiki();
		behekoPanela.add(TableroaUI.getNireTableroaUI());
		
		new LoginUI(this).setVisible(true);

		this.setVisible(true);
		this.pack();
		
		Audioa intro = new Audioa(Helbideak.intro);
		intro.play();
		intro.getPlayer().setOnEndOfMedia(new Runnable(){
			@Override
			public void run(){
				final Audioa bucle = new Audioa(Helbideak.bucle);
				bucle.play();
				bucle.getPlayer().setOnEndOfMedia(new Runnable(){
					@Override
					public void run(){
						bucle.getPlayer().seek(Duration.ZERO);
					}
				});
			}
		});
	}
	
	public static JokoaUI getNireJokoa(){
		if (nirejokoa == null){
			nirejokoa = new JokoaUI();
		}
		return nirejokoa;
	}
	
	//Beste metodoak
	private void panelakEraiki() {
		
		JPanel panelIpar = new JPanel();
		panelIpar.setBackground(new Color(112, 200, 160));
		behekoPanela.add(panelIpar, BorderLayout.NORTH);
		
		JPanel panelMendebal = new JPanel();
		panelMendebal.setBackground(new Color(112, 200, 160));
		behekoPanela.add(panelMendebal, BorderLayout.WEST);
		
		JPanel panelEkial = new JPanel();
		panelEkial.setBackground(new Color(112, 200, 160));
		behekoPanela.add(panelEkial, BorderLayout.EAST);
		
		JPanel panelHegoal = new JPanel();
		panelHegoal.setBackground(new Color(112, 200, 160));
		behekoPanela.add(panelHegoal, BorderLayout.SOUTH);
		
	}
	
	private void goikoPanelaEraiki() {
		JPanel goikoPanela = new JPanel(new FlowLayout());
		goikoPanela.setBackground(new Color(112, 200, 160));
		getContentPane().add(goikoPanela, BorderLayout.NORTH);
		
		JPanel goikoPanelaGoian = new JPanel();
		goikoPanelaGoian.setBackground(new Color(112, 200, 160));
		goikoPanela.add(goikoPanelaGoian, BorderLayout.NORTH);
		
		goikoPanela.add(this.getLorea());
		goikoPanela.add(JokoaUI.minaKontadorea = new KontadoreaUI());
		goikoPanela.add(this.getLorea());
		JPanel goian = new JPanel();
		goian.setBackground(new Color(112, 200, 160));
		goikoPanela.add(goian);
		goikoPanela.add(PikaUI.getNirePika());
		JPanel behean = new JPanel();
		behean.setBackground(new Color(112, 200, 160));
		goikoPanela.add(behean);
		goikoPanela.add(this.getLorea());
		goikoPanela.add(JokoaUI.denboraKontadorea = new KontadoreaUI());
		goikoPanela.add(this.getLorea());
	}
	
	private JLabel getLorea(){
		JLabel lorea = new JLabel(Helbideak.lorea);
		return lorea;
	}
	
	public static void galdu(){
		JokoaUI.bukatuta = true;
		Tableroa.getNireTableroa().denboraKontadorea.denboraGelditu();
		Pika.getNirePika().setPikaEgoera("galdu");
	}
	
	public static void irabazi(){
		JokoaUI.bukatuta = true;
		Tableroa.getNireTableroa().denboraKontadorea.denboraGelditu();
		Pika.getNirePika().setPikaEgoera("irabazi");
		try {
			Ranking.getNireRanking().idatzi(JokoaUI.erabiltzailea, Tableroa.getNireTableroa().denboraKontadorea.getKont());
		} catch (IOException e) {e.printStackTrace();}
	}
	
	public void erreseteatu(int pErrenkada, int pZutabe, int pMinaKop, boolean pBichilloak) {
		JokoaUI.behekoPanela.remove(TableroaUI.getNireTableroaUI());
		Tableroa.getNireTableroa().tableroaEraiki(pErrenkada, pZutabe, pMinaKop, pBichilloak);
		TableroaUI.getNireTableroaUI().tableroaEraiki();
		JokoaUI.bukatuta = false;
		JokoaUI.behekoPanela.add(TableroaUI.getNireTableroaUI());
		Pika.getNirePika().setPikaEgoera("normal");
		Tableroa.getNireTableroa().denboraKontadorea.denboraErreseteatu();
		pack();
		setLocationRelativeTo(null);
	}
	
   public String getUserName(){
    	String username = erabiltzailea;
    	if (username.length()<=10){
    		username = username.substring(0, username.length());
    	}
    	else {
    		username = username.substring(0, 10);
    	}
    	return username;
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}

}
