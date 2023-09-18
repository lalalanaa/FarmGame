package gui;

import java.awt.GridLayout;
import java.awt.Panel;
import java.util.ArrayList;

enum Smer {GORE, DESNO, DOLE, LEVO};

public class Njiva extends Panel{
	
	private int n, m;
	private Simulacija sim;
	private Polje selektovano = null;
	private ArrayList<Polje> polja = new ArrayList<>();
	private ArrayList<Akter> akteri = new ArrayList<>();
	
	public Njiva(int nn, int mm, Simulacija s)
	{
		n = nn;
		m = mm;
		sim = s;
		this.setLayout(new GridLayout(n, m, 2, 2));
		for(int i = 0; i < n*m; i++)
		{
			Polje p = new ZemljanoPolje(this);
			polja.add(p);
			this.add(p);
		}
		selektovano = polja.get(0);
	}
	
	public void kliknuto(Polje p)
	{
		int ind = polja.indexOf(p);
		
		Polje pp = new TravnataPovrs(this);
		polja.set(ind, pp);
		this.remove(p);
		this.add(pp, ind);
		revalidate();
		pp.repaint();
		sim.requestFocus();
	}
	
	public boolean postaviAktera(Akter a)
	{
		a.postaviAktera(selektovano);
		try {
			if(selektovano.getAkter() != a) return false;
		} catch (GAkter e) {}
		akteri.add(a);
		a.pokreni();
		return true;
	}
	
	public void zaustaviAktere()
	{
		for(Akter a : akteri)
			a.zaustavi();
	}
	
	public void pauzirajAktere()
	{
		for(Akter a : akteri)
			a.pauziraj();
	}
	
	public void odpauzirajAkrere()
	{
		for(Akter a : akteri)
			a.odpauziraj();
	}
	
	public Polje getSelektovanoPolje() { return selektovano; }
	
	
	public void pomeriSelektovano(Smer s)
	{
		Polje old = selektovano;
		ArrayList<Polje> lista = dohvatiSusede(selektovano);
		
		if(lista.get(s.ordinal()) != null) selektovano = lista.get(s.ordinal());
		
		old.repaint();
		selektovano.repaint();
	}
	
	
	public ArrayList<Polje> dohvatiSusede(Polje p)
	{
		int ind = polja.indexOf(p);
		ArrayList<Polje> lista = new ArrayList<>();
		
		if(ind - n >= 0) lista.add(polja.get(ind - n));
		else lista.add(null);
		if(ind + 1 < m*n && ind%m + 1 < m) lista.add(polja.get(ind+1));
		else lista.add(null);
		if(ind + n < m*n) lista.add(polja.get(ind + n));
		else lista.add(null);
		if(ind - 1 >= 0 && ind%m - 1 >= 0) lista.add(polja.get(ind - 1));
		else lista.add(null);
		
		return lista;
	}
	
	public ArrayList<Polje> dohvatiSlobodneSusede(Polje p)
	{
		ArrayList<Polje> susedi = new ArrayList<>();
		ArrayList<Polje> lista = dohvatiSusede(p);
		
		for(Polje pp : lista)
			if(pp != null && !pp.zauzeto()) susedi.add(pp);
		
		return susedi;
	}
		

}
