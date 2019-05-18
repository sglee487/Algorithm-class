package algohw09;

import java.util.ArrayList;
import java.util.HashSet;

//201300995
public class Prim_algorithm {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		vertex a = new vertex();
		vertex b = new vertex();
		vertex c = new vertex();
		vertex d = new vertex();
		vertex e = new vertex();
		vertex f = new vertex();
		vertex g = new vertex();
		vertex h = new vertex();
		vertex i = new vertex();
		
		a.setWay(b, 4);
		a.setWay(h, 8);
		b.setWay(a, 4);
		b.setWay(c, 8);
		b.setWay(h, 11);
		c.setWay(b, 8);
		c.setWay(d, 7);
		c.setWay(f, 4);
		c.setWay(i, 2);
		d.setWay(c, 7);
		d.setWay(e, 9);
		d.setWay(f, 14);
		e.setWay(d, 9);
		e.setWay(f, 10);
		f.setWay(c, 4);
		f.setWay(d, 14);
		f.setWay(e, 10);
		g.setWay(f, 2);
		g.setWay(h, 1);
		g.setWay(i, 6);
		h.setWay(a, 8);
		h.setWay(g, 1);
		h.setWay(i, 7);
		i.setWay(c, 2);
		i.setWay(g, 6);
		i.setWay(h, 7);
		
		
		ArrayList<vertex> Q = new ArrayList();
		Q.add(a);
		Q.add(b);
		Q.add(c);
		Q.add(d);
		Q.add(e);
		Q.add(f);
		Q.add(g);
		Q.add(h);
		Q.add(i);
		
		
	}

}
