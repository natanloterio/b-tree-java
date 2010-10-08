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

//deficiencias do programa :
// nao foi feita a centralizacao da raiz, de acordo com a insercao dos nos , a partir da altura =2
// existe sobreposicao dos nos em alguns valores inseridos, quando altura >1
// na busca o valor digitado, nao fica em outra cor, para dar destaque.

public class PainelDesenhaArvore extends JPanel {

	Arvore arvore;

	public PainelDesenhaArvore(Arvore bt){
		super();

		arvore = bt;
	}
        /**
         * Este metodo eh chamado toda vez que ocorre alguma alteracao no painel
         * @param g
         * @author Vitor Villela
         */

        public void paint(Graphics g){
            Graphics2D g2 = (Graphics2D)g;
            paintComponent(g2);
            drawNo(arvore.getRaiz(),arvore.getMaximoChaves(),g2);
	}

        /**
         * Este metodo desenhha o no n, passado por parametro
         * atraves da biblioteca Graphics
         * @param n = no
         * @param maxChaves = maximo de chaves que a arvore pode pssuir
         * @param g2, utilizado para desenhar os nos no painel
         * @author Vitor Villela
         */
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
                tamanhoOcupado = calculaTamanhoOcupado(n,alturaArvore,tamanhoRetangulo,arvore.getMaximoChaves());
                n.setX(400); // nao coseguimos centralizar a raiz,para casos onde a altura eh maior que 1
                
                                
                n.setY(30);
                

                // desenhando a raiz!!
                g2.setColor(Color.LIGHT_GRAY);
                g2.fill( new Rectangle2D.Double(n.getX() ,n.getY(),tamanhoRetangulo,altRet)); // a altura do retangulo serah sempre 20
		g2.setPaint(Color.BLACK);
                for(i=0;i<n.numChaves();i++){
                        g2.drawString(Integer.toString(n.getChave(i)), n.getX()  + anda, n.getY() + 12);
                        anda += 20;
		}
                anda = 0;
                int indice =0;
                desenhaFilhos(n,indice,g2);
                
		return;
	}


        /**
         * Este metodo desenha os filhos de um no n, passado por parametro
         * @param n
         * @param indice
         * @param g2
         * @return
         * @author Vitor Villela
         */
        public boolean desenhaFilhos(No n,int indice, Graphics2D g2){
           int i;
           int anda = 0;
           int j=0;
           int tamanhoRetangulo;
           int alturaArvore;
           int constante = 40; // QUANTO QUE VAI "ANDAR" NO Y A CADA ALTURA DA ARVORE
           int altRet = 20; // padrao adotado para altura do retangulo
           int armazenaCoordenadaX = 0 ; //essa variavel armazena o valor da coordenada X do aux, util para depois setar o proximo x do novo aux
           int espacoEntreLinhas=0; // espaco para tracar as linhas
           int espacoEntreRetangulos = 10; // espaco entre os retangulos
           int cont=0;
           tamanhoRetangulo = arvore.getMaximoChaves()  * 20; // o tamanho do retangulo é dado pelo numero maximo de chaves * 20(pixels) para os numeros ficarem bem espacados

           List<No> filhos = new ArrayList<No>(); //lista de filhos do no passado por parametro
           
            if(!n.folha()){ 

                while ( j < n.numFilhos() ){
                    filhos.add(j,n.getListFilhos().get(j)); // setando a posicao da lista, com a lista de filhos
                    filhos.get(j).setY(n.getY() + constante);

                    if(indice == 0){ 
                        if(j == 0){  
                            filhos.get(j).setX(n.getX()- 2 * (tamanhoRetangulo + espacoEntreRetangulos));
                        }else{
                            filhos.get(j).setX(armazenaCoordenadaX + tamanhoRetangulo + espacoEntreRetangulos);
                        }
                    }else{
                        if(j == 0){
                            filhos.get(j).setX(indice*n.numFilhos() * (tamanhoRetangulo + espacoEntreRetangulos + 35) );

                        }else{
                            filhos.get(j).setX(armazenaCoordenadaX + tamanhoRetangulo + espacoEntreRetangulos);
                        }

                    }

                    g2.setColor(Color.LIGHT_GRAY);
                    g2.fill( new Rectangle2D.Double(filhos.get(j).getX() ,filhos.get(j).getY(),tamanhoRetangulo,altRet));

                    g2.setPaint(Color.BLACK);
                    for(i=0;i<filhos.get(j).numChaves();i++){
                        g2.drawString(Integer.toString(filhos.get(j).getChave(i)), filhos.get(j).getX() + anda, filhos.get(j).getY() + 12);
                        anda += 16;
                    }
                     anda = 0;
                     
                    // desenhando a linha
                    No pai = arvore.buscaPaiDoNo(filhos.get(j),arvore.getRaiz());  // pegar o pai do filho (aux) para desenhar a linha
                    g2.draw(new Line2D.Double(filhos.get(j).getX(), filhos.get(j).getY(),pai.getX()+espacoEntreLinhas,pai.getY()+altRet - 3));
                    espacoEntreLinhas +=18;


                   armazenaCoordenadaX = filhos.get(j).getX(); // essa variavel armazena o valor da coordenada X                  
                   //desenhaFilhos(filhos.get(j),j,alturaAtual,g2);
                   j = j+ 1;

                }
                //desenhando os filhos dos filhos agora
                for(i=0;i<n.numFilhos();i++){
                    desenhaFilhos(filhos.get(i),i,g2);
                }
                
           }
           return true;
        }
        /**
         * metodo que calcula o espaco ocupado pelos retangulos no eixo X
         * @param n
         * @param altura
         * @param tamRetangulo
         * @param maxChaves
         * @author Vitor Villela
         * @return
         */
         double calculaTamanhoOcupado(No n, int altura,int tamRetangulo,int maxChaves){
            int k = 10; // espaco entre os retangulos
            if(altura == 0){
                return 0;
            }else{
                return ( ((Math.pow(maxChaves,altura) -1) * k) + ( Math.pow(maxChaves,altura) * tamRetangulo) );
            }
        }



}
