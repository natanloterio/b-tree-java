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


        	/*public void updateCanvas(){
                    Dimension d1 = getParent().getSize();
                    Dimension d = new Dimension();


                    if (!arvore.vazia()){
			d.setSize(tamanho, altura * 40 + 80);
			setPreferredSize(d);
			revalidate();

                    }
                    return;
                }*/
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
                g2.setColor(Color.LIGHT_GRAY);
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
            int i = 0;
            int j = 0;
            int armazena = 0 ;
            int linha=0;
            int k = 10; // k = espaco entre os retangulos
            //System.out.println("coordenada n.x" + n.getX());
            //System.out.println("coordenada n.y" + n.getY());



            No aux;
            if(!n.folha()){ // mostrar os filhos da Raiz - OK

                while ( j < n.numFilhos() ){
                    aux = n.getListFilhos().get(j);
                    aux.setY(n.getY() + constante);

                    if(j == 0){ // seta a primeira coordenada com a condicao valida para altura = 1
                        aux.setX(n.getX()- tamanhoRetangulo - k); // essa condicao eh valida para h = 1
                    }
                    if(j != 0 ){
                        aux.setX(armazena + tamanhoRetangulo + k);
                    }


                    g2.setColor(Color.LIGHT_GRAY);
                    g2.fill( new Rectangle2D.Double(aux.getX() ,aux.getY(),tamanhoRetangulo,altRet));

                    No pai = arvore.buscaPaiDoNo(aux,arvore.getRaiz());  // pegar o pai do filho (aux) para desenahr a linha

                    g2.setPaint(Color.BLACK);
                    for(i=0;i<aux.numChaves();i++){
                        g2.drawString(Integer.toString(aux.getChave(i)), aux.getX() + anda, aux.getY() + 12);
                        anda += 16;
                    }
                     anda = 0;
                     
                    // desenhar a linha
                    g2.draw(new Line2D.Double(aux.getX(), aux.getY(),pai.getX()+linha,pai.getY()+altRet - 3));
                    linha +=18;


                   armazena = aux.getX(); // essa variavel armazena o valor da coordenada X
                   // isto eh util, para depois setar o proximo x do novo aux
                   
                    j = j+ 1;

                    //desenhar os filhos dos aux ????
                }
                
           }
                // mostrar os filhos dos filhos agora
        }
}
/*
 * o metodo desenhaFlhos, eu posso passar um no auxiliar (pai)
 * aih eu verifico a altura
 *
 *
 */

