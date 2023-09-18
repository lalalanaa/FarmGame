package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ptica extends Akter {

	public Ptica(int minv, int maxv, Color b)
	{
		super(minv, maxv, b);
	}

	@Override
	public void run(){
		try {
			while(!Thread.interrupted()) 
			{
				synchronized (t) { while(pauzirano) t.wait(); }
				polje.repaint();
				
				Thread.sleep(new Random().nextInt(maxVreme - minVreme) + minVreme );
				ArrayList<Polje> susedi = polje.njiva.dohvatiSlobodneSusede(polje);
				
				if(susedi.size() != 0)
				{
					polje.ukloniAktera();
					polje.repaint();
					polje = susedi.get(new Random().nextInt(susedi.size()));
					postaviAktera(polje);
					polje.repaint();
				}
			}
		}catch (InterruptedException e) {}
	}

	@Override
	public void iscrtaj(Graphics g) 
	{		
		g.setColor(boja);
		g.fillOval(polje.getWidth()/4, polje.getHeight()/4, polje.getWidth() / 2, polje.getHeight()/2);
		g.setColor(Color.RED);
		g.fillOval(3*polje.getWidth()/8, 3*polje.getHeight()/8, polje.getWidth() / 4, polje.getHeight()/4);
	}

}
