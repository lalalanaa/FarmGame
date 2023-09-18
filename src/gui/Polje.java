package gui;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Polje extends Canvas 
{
	private Color boja;
	protected Njiva njiva;
	private Akter akter = null;
	
	public Polje(Color b, Njiva n)
	{
		boja = b;
		njiva = n;
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				njiva.kliknuto(Polje.this);
			}
		});
	}
	
	
	public void dodajAktera(Akter a) throws GAkter
	{
		if(zauzeto()) throw new GAkter();
		akter = a;
	}
	
	
	public Akter getAkter() throws GAkter
	{
		if(!zauzeto()) throw new GAkter();
		return akter;
	}
	
	
	public void ukloniAktera() { akter = null; }
	
	
	public boolean zauzeto() { return akter != null; }
	
	
	public boolean selektovano() { return this == njiva.getSelektovanoPolje(); }
	
	
	@Override
	public void paint(Graphics g) 
	{
		setBackground(boja);
		
		if(selektovano())
		{
			Graphics2D g2 = (Graphics2D) g;
			Stroke oldStroke = g2.getStroke();
			g2.setStroke(new BasicStroke(5));
			g.setColor(Color.RED);
			g.drawRect(1, 1, this.getWidth()-2,  this.getHeight() - 2);
			g2.setStroke(oldStroke);
		}
		
		if(zauzeto()) akter.iscrtaj(g);
	}

}
