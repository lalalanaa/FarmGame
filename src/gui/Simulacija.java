package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout.Alignment;

public class Simulacija extends Frame{
	
	private Njiva njiva = new Njiva(5, 5, this);
	private int cena = 2000;
	
	public void populateWindow()
	{		
		Panel leftp = new Panel();
		leftp.setLayout(new GridLayout(2, 1));
		
		Label l = new Label("Novac: " + cena);
		l.setFont(new Font("Serif", Font.BOLD, 14));
		l.setAlignment(Label.CENTER);
		leftp.add(l);
		
		Button b = new Button("Kokoska (300)");
		b.setFont(new Font("Serif", Font.BOLD, 14));
		b.setForeground(Color.RED);
		b.addActionListener((ae) -> {
			njiva.requestFocus();
			if(cena - 600 < 0) b.setEnabled(false); 
			
			Akter a = new Kokoska(1000, 2000);
			if(!njiva.postaviAktera(a)) return;
			
			cena -= 300;
			l.setText("Novac: " + cena);
		});
		leftp.add(b);
		
		add(njiva, BorderLayout.CENTER);
		add(leftp, BorderLayout.EAST);
		njiva.setFocusable(true);
	}
	
	
	public Simulacija()
	{
		setBounds(500, 150, 600, 500);
		setResizable(false);
		
		setTitle("Simulacija Farme");
		populateWindow();
		
		njiva.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_UP) 
					njiva.pomeriSelektovano(Smer.GORE);
				
				else if(e.getKeyCode() == KeyEvent.VK_DOWN)
					njiva.pomeriSelektovano(Smer.DOLE);
				
				else if(e.getKeyCode() == KeyEvent.VK_LEFT)
					njiva.pomeriSelektovano(Smer.LEVO);
				
				else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
					{
						njiva.pomeriSelektovano(Smer.DESNO);
					}
			}
		});
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				njiva.zaustaviAktere();
				dispose();
			}
		});
		
		setVisible(true);
	}
	
	
	public static void main(String[] args) 
	{
		new Simulacija();
	}

}
