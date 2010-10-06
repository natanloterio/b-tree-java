package janela;
import arvore_b.*;
import javax.swing.*;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Line2D;
import java.math.*;


public class PainelDesenhaArvore extends JPanel {

	Arvore arvore;

	public PainelDesenhaArvore(Arvore bt){
		super();

		arvore = bt;
	}

        public void paint(Graphics g){
            Graphics2D g2 = (Graphics2D)g;
            paintComponent(g2);
            drawNo(arvore.getRaiz(),arvore.getMaximoChaves(),g2);
	}

	private void drawNo(No n,int maxChaves,Graphics2D g2){
		int i;
                int anda = 0;
                int tamanhoRetangulo;
                int altura;
                int constante = 40; // QUANTO QUE VAI "ANDAR" NO Y A CADA ALTURA DA ARVORE
                double tamanhoOcupado;
                int altRet;

                tamanhoRetangulo = maxChaves  * 20; // o tamanho do retangulo é dado pelo numero maximo de chaves * 23(pixels) para os numeros ficarem bem espacados
                altRet = 20;
                
                altura = arvore.getAlturaArvore(arvore.getRaiz(), 0);
                tamanhoOcupado = calculaTamanhoOcupado(arvore.getRaiz(), altura,tamanhoRetangulo,maxChaves);
                System.out.println("altura = " + altura);
                System.out.println("tamanho Ocupado  = " + tamanhoOcupado);
                if(altura < 2 ){
                    n.setX(300); // nao precisa centralizar a raiz, pq os nos filhos cabem na tela
                }else{
                    n.setX((int)tamanhoOcupado/2 + 100);
                }
                
                n.setY(30);
                
                //arvore.getRaiz().setX((int)tamanhoOcupado/2); // calculo o espaco ocupado pelos filhos no eixo x, para centralizar o noh pai

                // desenhando a raiz!!
                g2.setColor(Color.GRAY);
                g2.fill( new Rectangle2D.Double(n.getX() ,n.getY(),tamanhoRetangulo,altRet)); // a altura do retangulo serah sempre 20
		g2.setPaint(Color.BLACK);
                for(i=0;i<n.numChaves();i++){
                        g2.drawString(Integer.toString(n.getChave(i)), n.getX()  + anda, n.getY() + 12);
                        anda += 20;
		}
                anda = 0;
                //desenhando a linha

                
                desenhaFilhos(n,altura,constante,tamanhoRetangulo,altRet,g2);
		return;
	}

        double calculaTamanhoOcupado(No n, int altura,int tamRetangulo,int maxChaves){
            int k = 10; // espaco entre os retangulos
            if(altura == 0){
                return 0;
            }else{
                return ( ((Math.pow(maxChaves,altura) -1) * k) + ( Math.pow(maxChaves,altura) * tamRetangulo) );
            }
        }


        public void desenhaFilhos(No n, int altura,int constante, int tamanhoRetangulo,int altRet,Graphics2D g2){
            int anda = 0;
            int i=0;
            int j =0;
            int k=10; // k = espaco entre os retangulos
            n.setY(n.getY() + constante);
            n.setX(n.getX() - tamanhoRetangulo - (altura*altura)*constante);



            No aux;
            if(!n.folha()){ // mostrar os filhos da Raiz - OK
                while (j<n.numFilhos()){
                    aux = n.getListFilhos().get(j);
                    g2.setColor(Color.GRAY);
                    g2.fill( new Rectangle2D.Double(n.getX() ,n.getY(),tamanhoRetangulo,altRet));
                    g2.setPaint(Color.BLACK);
                    for(i=0;i<aux.numChaves();i++){
                        g2.drawString(Integer.toString(aux.getChave(i)), n.getX() + anda, n.getY() + 12);
                        anda += 16;
                    }
                    //g2.draw(new Line2D.Double(aux.getX(), aux.getY(),arvore.getPai(aux,arvore.getRaiz()).getX(), arvore.getPai(aux,arvore.getRaiz()).getY()));
                    
                    anda = 0;
                    n.setX(n.getX() + tamanhoRetangulo + k);
                    j = j+ 1;
                }
                
           }
                // mostrar os filhos dos filhos agora
//desenhaFilhos(aux,altura,constante,tamanhoRetangulo,altRet,g2);
        }
}


