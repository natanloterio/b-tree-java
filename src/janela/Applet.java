package janela;
import arvore_b.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import javax.swing.JScrollPane;


public class Applet extends JApplet
	implements ActionListener{
        private JLabel lTitulo;
	private JButton bInsere;
	private JButton bRemove;
	private JButton bInsereN;
        private JButton bBusca;
	private JButton bAlteraOrdem;
	private Arvore mytree;
        private JButton bLimpeza;
	private JTextField tCampo;
	//private PainelInicial pbinTree; // pq declarar ??
	private PainelDesenhaArvore pBinTree;

        public void init(){
            Applet();
        }
	//private JTextField jcampo;
	public void Applet (){
		
		this.setLayout(new BorderLayout());
		

		configureMenu();
                mytree = new Arvore(5);
                lTitulo = new JLabel("Ordem = " + Integer.toString(mytree.getMaximoChaves()+1));
                lTitulo.setForeground(Color.red);
		tCampo = new JTextField(7);
		bInsere = new JButton("INSERIR");
                bBusca = new JButton("BUSCAR");
		bRemove = new JButton("REMOVER");
		bInsereN = new JButton("INSERIR N");
		bAlteraOrdem = new JButton("ALTERAR ORDEM");
                bLimpeza = new JButton("LIMPAR TUDO");
                // insercao, busca, remocao, limpeza, inserir n , alterar ordem

		pBinTree = new PainelDesenhaArvore(mytree);



		bInsere.addActionListener(this);
		bRemove.addActionListener(this);
                bBusca.addActionListener(this);
		bInsereN.addActionListener(this);
		bAlteraOrdem.addActionListener(this);
                bLimpeza.addActionListener(this);

		//this.repaint();

                //this.getContentPane().add(pBinTree);

                //Cria uma barra de rolagem
		JScrollPane jsp = new JScrollPane ();

		//Adiciona a barra de rolagem na janela
                this.getContentPane().add(jsp);

                add(new PainelInicial(lTitulo,tCampo,bInsere,bRemove,bInsereN,bBusca,bAlteraOrdem,bLimpeza),BorderLayout.NORTH);
		jsp.setViewportView(pBinTree);

	}



	public void configureMenu (){
		JMenu menu;             //variavel para o armazenamento do menu
		JMenuBar menuBar;       //variavel para o armazenamento da barra de menus
		JMenuItem item;         //variavel para o armazenamento os itens de cada menu


		menuBar = new JMenuBar ();


		//Cria o menu 'Arquivo' e o adiciona à barra de menus
		menu = new JMenu ("Arquivo");
		menuBar.add(menu);

		//Para o menu 'Arquivo', cria o item 'Fechar'.
		item = new JMenuItem ("Fechar");

		//Indica o gerenciador de eventos para este menu e o inclui
		//  no menu 'Arquivo'.
		item.setActionCommand("Fechar");
		item.addActionListener(this);
		menu.add(item);

		//Cria o menu 'Ajuda' e o adiciona à barra de menus
		menu = new JMenu ("Ajuda");
		menuBar.add(menu);

		item = new JMenuItem ("Como usar...");
		item.setActionCommand("Como usar...");
		item.addActionListener(this);
		menu.add(item);

		item = new JMenuItem ("Sobre");
		item.setActionCommand("Sobre");
		item.addActionListener(this);
		menu.add(item);


		//Adiciona a barra de menus na janela principal da aplicação.
		this.setJMenuBar(menuBar);
	}

	public void actionPerformed(ActionEvent e) {
		String s;
                int k;
                if(e.getSource() == bInsere){
                    s = tCampo.getText();
                    tCampo.setText("");
                    try{
                        k = Integer.parseInt(s);

                        //mytree.insere(mytree.getRaiz(), k);
                        mytree.insere(k);

			//pBinTree.updateCanvas();
			pBinTree.repaint();
                    }
                    catch (NumberFormatException exception){

				//Se a caixa de texto estiver vazia...
				if (s.length() == 0){
					//Exiba mensagem apropriada por meio de uma caixa de diálogo.
					JOptionPane.showMessageDialog(this,
							"Você deve digitar algum número inteiro no campo de \n" +
							"texto e apertar o botão \"Inserir\" para adicioná-lo\n" +
							"à árvore binária.",
							"Erro na entrada de dados",
							JOptionPane.INFORMATION_MESSAGE);
				}
				//Estando preenchida, o valor não será numérico (se fosse, esta
				//  exceção não seria lançada). Neste caso, também exiba um aviso
				//  para o usuário, indicando que somente números inteiros são
				//  válidos para esta aplicação.
				else{
					JOptionPane.showMessageDialog(this,
						"Esta aplicação está restrita a números inteiros.",
						"Erro na entrada de dados",
						JOptionPane.ERROR_MESSAGE);
				}
			}

		}
		if(e.getSource() == bRemove){
                    s = tCampo.getText();
                    tCampo.setText("");
                    try{
                        k = Integer.parseInt(s);
                        mytree.remove(k);
                        pBinTree.repaint();
                    }
                    catch (NumberFormatException exception){

				//Se a caixa de texto estiver vazia...
				if (s.length() == 0){
					//Exiba mensagem apropriada por meio de uma caixa de diálogo.
					JOptionPane.showMessageDialog(this,
							"Você deve digitar algum número inteiro no campo de \n" +
							"texto e apertar o botão \"Inserir\" para adicioná-lo\n" +
							"à árvore binária.",
							"Erro na entrada de dados",
							JOptionPane.INFORMATION_MESSAGE);
				}
				//Estando preenchida, o valor não será numérico (se fosse, esta
				//  exceção não seria lançada). Neste caso, também exiba um aviso
				//  para o usuário, indicando que somente números inteiros são
				//  válidos para esta aplicação.
				else{
					JOptionPane.showMessageDialog(this,
						"Esta aplicação está restrita a números inteiros.",
						"Erro na entrada de dados",
						JOptionPane.ERROR_MESSAGE);
				}
			}

		}
                if(e.getSource() == bBusca){
                    s = tCampo.getText();
                    tCampo.setText("");
                    try{
                        k = Integer.parseInt(s);
                        if (mytree.buscaChave(mytree.getRaiz(), k) != null) {
                            JOptionPane.showMessageDialog(this,"Chave já existe na arvore !!!");
                        } else {
                            JOptionPane.showMessageDialog(this,"Não foi encontrada a chave informada");
                        }
                        pBinTree.repaint();
                    }
                    catch (NumberFormatException exception){

				//Se a caixa de texto estiver vazia...
				if (s.length() == 0){
					//Exiba mensagem apropriada por meio de uma caixa de diálogo.
					JOptionPane.showMessageDialog(this,
							"Você deve digitar algum número inteiro no campo de \n" +
							"texto e apertar o botão \"Inserir\" para adicioná-lo\n" +
							"à árvore binária.",
							"Erro na entrada de dados",
							JOptionPane.INFORMATION_MESSAGE);
				}
				//Estando preenchida, o valor não será numérico (se fosse, esta
				//  exceção não seria lançada). Neste caso, também exiba um aviso
				//  para o usuário, indicando que somente números inteiros são
				//  válidos para esta aplicação.
				else{
					JOptionPane.showMessageDialog(this,
						"Esta aplicação está restrita a números inteiros.",
						"Erro na entrada de dados",
						JOptionPane.ERROR_MESSAGE);
				}
			}
                }

                 if(e.getSource() == bInsereN){
                    s = tCampo.getText();
                    tCampo.setText("");
                    int valor;
                    int i;
                    /* METODO BASEADO EM ARQUIVO
                    Arquivo arq = new Arquivo();
                    String endereco = "C:\\Users\\Vitor\\Desktop\\Arvore_B\\src\\arvore_b\\Dados.txt";
                    */
                    try{
                        k = Integer.parseInt(s);
                        for(i=0;i<k;i++){
                            valor =(int)(Math.random()*100);
                            mytree.insere(valor);
                        }

                        /* METODO BASEADO EM ARQUIVO

                         arq.gerarDadosAleatório(endereco, mytree.getMaximoChaves() + 1,100, k);
                        List<Integer> list = new ArrayList<Integer>();

                        list = arq.lerArquivo(endereco);
                        int i;
                        //mytree.insere(mytree.getRaiz(), k);
                        for(i=1;i<list.size();i++){
                            mytree.insere(list.get(i));
                        }

                        */
			pBinTree.repaint();
                    }
                    catch (NumberFormatException exception){

				//Se a caixa de texto estiver vazia...
				if (s.length() == 0){
					//Exiba mensagem apropriada por meio de uma caixa de diálogo.
					JOptionPane.showMessageDialog(this,
							"Você deve digitar algum número inteiro no campo de \n" +
							"texto e apertar o botão \"Inserir\" para adicioná-lo\n" +
							"à árvore binária.",
							"Erro na entrada de dados",
							JOptionPane.INFORMATION_MESSAGE);
				}
				//Estando preenchida, o valor não será numérico (se fosse, esta
				//  exceção não seria lançada). Neste caso, também exiba um aviso
				//  para o usuário, indicando que somente números inteiros são
				//  válidos para esta aplicação.
				else{
					JOptionPane.showMessageDialog(this,
						"Esta aplicação está restrita a números inteiros.",
						"Erro na entrada de dados",
						JOptionPane.ERROR_MESSAGE);
				}
			}

                     // vou chamar o metodo mostrar aqui, por enquanto
                     //mytree.exibir();
                 }
                if(e.getSource() == bLimpeza){
                    mytree.limpaArvore();
                    pBinTree.repaint();
                }
                if(e.getSource() == bAlteraOrdem){ // PROBLEMA!
                    String sA;
                    sA = tCampo.getText();
                    tCampo.setText("");
                    try{
                        k = Integer.parseInt(sA);
                        mytree = new Arvore(k);
                        mytree.setMaximoChaves(k-1);
                        lTitulo.setText("Ordem = " + Integer.toString(mytree.getMaximoChaves()+1));

                        //pBinTree = new PainelDesenhaArvore(mytree);
                        pBinTree.repaint();

                    }
                    catch (NumberFormatException exception){

				//Se a caixa de texto estiver vazia...
				if (sA.length() == 0){
					//Exiba mensagem apropriada por meio de uma caixa de diálogo.
					JOptionPane.showMessageDialog(this,
							"Você deve digitar algum número inteiro no campo de \n" +
							"texto e apertar o botão \"Alterar Ordem\"  \n" + "para alterar o número máximo de filhos\n" +
							"à árvore binária.",
							"Erro na entrada de dados",
							JOptionPane.INFORMATION_MESSAGE);
				}
				//Estando preenchida, o valor não será numérico (se fosse, esta
				//  exceção não seria lançada). Neste caso, também exiba um aviso
				//  para o usuário, indicando que somente números inteiros são
				//  válidos para esta aplicação.
				else{
					JOptionPane.showMessageDialog(this,
						"Esta aplicação está restrita a números inteiros.",
						"Erro na entrada de dados",
						JOptionPane.ERROR_MESSAGE);
				}
			}

                }


		if (e.getActionCommand().equals("Fechar")){
			System.exit(0);
		}
		//Para os outros casos (abaixo), somente abra uma caixa de diálogo
		//  com instruções para o uso do sistema por parte do usuário.
		else if (e.getActionCommand().equals("Sobre")){
			JOptionPane.showMessageDialog(this,
					" Árvore B\n" +
					" Desenvolvedores:\n" +
					" Arthur Mazer\n" +
					" Valter Henrique \n" +
					" Vitor Souza Villela",
					"Sobre",
					JOptionPane.INFORMATION_MESSAGE);
		}
		else if (e.getActionCommand().equals("Como usar...")){
			JOptionPane.showMessageDialog(this,
					"Esta aplicação corresponde ao metodos de inserção ,remoção\n" +
                                        "e busca de uma árvore-B. \n" +
                                        "Para utilizar o programa, basta digitar um numero inteiro no campo\n" +
                                        " de texto correspondente e pressionar o botao desejado\n" ,
					"Como utilizar este programa.",
					JOptionPane.INFORMATION_MESSAGE);
		}

	}

}
