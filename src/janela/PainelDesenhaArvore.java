package janela;
import arvore_b.*;
import javax.swing.*;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;


public class PainelDesenhaArvore extends JPanel {

	Arvore arvore;

	public PainelDesenhaArvore(Arvore bt){
		super();

		arvore = bt;
	}
}
	/*
public void paint(Graphics g){

		Graphics2D g2 = (Graphics2D)g;

		paintComponent(g2);

	//	desenhaNo (arvore.getRaiz(), g2);

	}
	public void updateCanvas(){


		Dimension d1 = getParent().getSize();
		Dimension d = new Dimension();
		/*
		if (!arvore.vazia()){
			d.setSize(arvore.screenSpace()+ 80, (arvore.height() + 1) * 40 + 80);
			setPreferredSize(d);
			revalidate();
			if (d1.getWidth() > arvore.screenSpace()){
				arvore.getRaiz().setX(arvore.getRaiz().screenSpace(arvore.getRaiz().getLeft()) + 15
						              + (int) (d1.getWidth() - arvore.screenSpace())/2);
			}
			else{
				arvore.getRaiz().setX(arvore.getRaiz().screenSpace(arvore.getRaiz().getLeft()) + 15);
			}

		}
		arvore.updateCoordinates();

		return;
	}

	private void desenhaNo (No n, Graphics2D g2){

		if (n != null){
			if (n.getLeft() != null){
				g2.draw(new Line2D.Double(n.getX(), n.getY(), n.getLeft().getX(), n.getLeft().getY()));
				drawNode (n.getLeft(), g2);
			}

			if (n.getRight() != null){
				g2.draw(new Line2D.Double(n.getX(), n.getY(), n.getRight().getX(), n.getRight().getY()));
				drawNode (n.getRight(), g2);
			}

			g2.setPaint(Color.LIGHT_GRAY);
			g2.fill(new Ellipse2D.Double(n.getX()-15, n.getY()-15, 30, 30));

			g2.setPaint(Color.BLACK);
			g2.drawString(Integer.toString(n.getKey()), n.getX() - 5, n.getY() + 5);
		}

		return;
	}

} */

