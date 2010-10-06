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
import java.util.ArrayList;
import java.util.List;


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
                int alturaArvore;
                int constante = 40; // QUANTO QUE VAI "ANDAR" NO Y A CADA ALTURA DA ARVORE
                double tamanhoOcupado;
                int altRet;

                tamanhoRetangulo = maxChaves  * 20; // o tamanho do retangulo é dado pelo numero maximo de chaves * 23(pixels) para os numeros ficarem bem espacados
                altRet = 20;
                
                alturaArvore = arvore.getAlturaArvore(arvore.getRaiz(), 0);
                tamanhoOcupado = calculaTamanhoOcupado(arvore.getRaiz(), alturaArvore,tamanhoRetangulo,maxChaves);
                System.out.println("altura = " + alturaArvore);
                System.out.println("tamanho Ocupado  = " + tamanhoOcupado);
                if(alturaArvore < 2 ){
                    n.setX(300); // nao precisa centralizar a raiz, pq os nos filhos cabem na tela
                }else{
                    n.setX((int)tamanhoOcupado/2 + 100); // senao centraliza a raiz
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

                int alturaAtual=0;
                desenhaFilhos(n,g2);
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


        public void desenhaFilhos(No n, Graphics2D g2){
           //alturaAtual++;
           int i;
           int anda = 0;
           int tamanhoRetangulo;
           int alturaArvore;
           int constante = 40; // QUANTO QUE VAI "ANDAR" NO Y A CADA ALTURA DA ARVORE
           int altRet = 20; // padrao adotado para altura do retangulo
           int j = 0;
           int armazenaCoordenadaX = 0 ; //essa variavel armazena o valor da coordenada X do aux, util para depois setar o proximo x do novo aux
           int espacoEntreLinhas=0; // espaco para tracar as linhas
           int espacoEntreRetangulos = 10; // k = espaco entre os retangulos
           alturaArvore = arvore.getAlturaArvore(arvore.getRaiz(), 0);
           tamanhoRetangulo = arvore.getMaximoChaves()  * 20; // o tamanho do retangulo é dado pelo numero maximo de chaves * 20(pixels) para os numeros ficarem bem espacados

            //System.out.println("coordenada n.x" + n.getX());
            //System.out.println("coordenada n.y" + n.getY());
           List<No> filhos = new ArrayList<No>(); //lista de filhos do no passado por parametro
           //No aux;
            if(!n.folha()){ // mostrar os filhos da Raiz - OK

                while ( j < n.numFilhos() ){
                    filhos.add(j,n.getListFilhos().get(j)); // setando a posicao da lista, com a lista de filhos
                    filhos.get(j).setY(n.getY() + constante);

                    if(j == 0){ // seta a primeira coordenada com a condicao valida para altura = 1
                        filhos.get(j).setX(n.getX()- tamanhoRetangulo - espacoEntreRetangulos); // essa condicao eh valida para h = 1
                    }
                    if(j != 0 ){
                        filhos.get(j).setX(armazenaCoordenadaX + tamanhoRetangulo + espacoEntreRetangulos);
                    }


                    g2.setColor(Color.LIGHT_GRAY);
                    g2.fill( new Rectangle2D.Double(filhos.get(j).getX() ,filhos.get(j).getY(),tamanhoRetangulo,altRet));

                    No pai = arvore.buscaPaiDoNo(filhos.get(j),arvore.getRaiz());  // pegar o pai do filho (aux) para desenahr a linha

                    g2.setPaint(Color.BLACK);
                    for(i=0;i<filhos.get(j).numChaves();i++){
                        g2.drawString(Integer.toString(filhos.get(j).getChave(i)), filhos.get(j).getX() + anda, filhos.get(j).getY() + 12);
                        anda += 16;
                    }
                     anda = 0;
                     
                    // desenhar a linha
                    g2.draw(new Line2D.Double(filhos.get(j).getX(), filhos.get(j).getY(),pai.getX()+espacoEntreLinhas,pai.getY()+altRet - 3));
                    espacoEntreLinhas +=18;


                   armazenaCoordenadaX = filhos.get(j).getX(); // essa variavel armazena o valor da coordenada X
                   // isto eh util, para depois setar o proximo x do novo aux
                   
                    j = j+ 1;

                }
                
           }
        }
}
/*
 * o metodo desenhaFlhos, eu posso passar um no auxiliar (pai)
 * aih eu verifico a altura
 *
 *
 */

