package gui;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Akter implements Runnable
{
	protected int minVreme, maxVreme;
	protected boolean pauzirano = false;
	protected Color boja;
	protected Polje polje;
	protected Thread t;
	
	public Akter(int minv, int maxv, Color b) 
	{
		minVreme = minv;
		maxVreme = maxv;
		boja = b;
		t = new Thread(this);
	}
	
	public void postaviAktera(Polje p)
	{ 
		try {
			p.dodajAktera(this);
			polje = p;	
		} catch (GAkter e) { System.out.println("Polje je zauzeto!"); } 
	}	
	
	public void pokreni() { t.start(); }
 	
	public void zaustavi() { t.interrupt(); }
	
	public void pauziraj() { pauzirano = true; }
	
	public void odpauziraj() { pauzirano = false; synchronized (t) { t.notify(); } }
	
	public abstract void iscrtaj(Graphics g);

}
