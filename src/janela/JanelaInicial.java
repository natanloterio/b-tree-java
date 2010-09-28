package janela;
import arvore_b.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/*eu mudei so o tratamento de eventos para o frame ao inves do panel*/


public class JanelaInicial extends JFrame
	implements ActionListener{
        private JLabel lTitulo;
	private JButton bInsere;
	private JButton bRemove;
	private JButton bInsereN;
        private JButton bBusca;
	private JButton bAlteraOrdem;
	private JButton bSair;
	private Arvore mytree;
	private JTextField tCampo;
	//private PainelInicial pbinTree; // pq declarar ??
	private PainelDesenhaArvore pBinTree;

	//private JTextField jcampo;
	public JanelaInicial(){
		super();
		this.setTitle("ARVORE-B");

		// metodo do professor para abrir a janela num tamanho, de acordo
		// com as coordeanadads do monitor, e centralizar a janela
		//______________________________
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int alturaTela = d.height;
		int comprimentoTela = d.width;
		this.setSize(comprimentoTela/2, alturaTela/2);
		setLocation((comprimentoTela)/4, (alturaTela)/4);
		this.setLayout(new BorderLayout());
		//________________________________

		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		configureMenu();
                mytree = new Arvore(5);
                lTitulo = new JLabel("Ordem = " + Integer.toString(mytree.getMaximoChaves())); // "ordem =," mytree.getNumMaximoFilhos
                lTitulo.setForeground(Color.red);
		tCampo = new JTextField(7);
		bInsere = new JButton("INSERIR");
                bBusca = new JButton("BUSCAR");
		bRemove = new JButton("REMOVER");
		bInsereN = new JButton("INSERIR N");
		bAlteraOrdem = new JButton("ALTERAR ORDEM");
		bSair = new JButton("SAIR");
                // insercao, busca, remocao, limpeza, inserir n , alterar ordem
		
		pBinTree = new PainelDesenhaArvore(mytree);



		bInsere.addActionListener(this);
		bRemove.addActionListener(this);
                bBusca.addActionListener(this);
		bInsereN.addActionListener(this);
		bAlteraOrdem.addActionListener(this);
		bSair.addActionListener(this);

		add(new PainelInicial(lTitulo,tCampo,bInsere,bRemove,bInsereN,bBusca,bAlteraOrdem,bSair),BorderLayout.NORTH);
		//this.repaint();
		this.setVisible(true);

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
                if(e.getSource() == bInsere){
                    s = tCampo.getText();
                    tCampo.setText("");
                    int k;
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


		}
                 if(e.getSource() == bInsereN){
                     // vou chamar o metodo mostrar aqui, por enquanto
                     //mytree.exibir();
                 }

                if(e.getSource() == bAlteraOrdem){
                    String sA;
                    sA = tCampo.getText();
                    tCampo.setText("");
                    int k;
                    try{
                        k = Integer.parseInt(sA);
                        mytree.setMaximoChaves(k);
                        lTitulo.setText("Ordem = " + Integer.toString(mytree.getMaximoChaves()));
                        mytree = new Arvore(mytree.getMaximoChaves());
                        JOptionPane.showMessageDialog(this, "nova ordem = " + mytree.getMaximoChaves(),"Ordem Alterada",JOptionPane.INFORMATION_MESSAGE);
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



		if(e.getSource() == bSair){
                        System.exit(0);

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
					"Esta aplicação corresponde ao metodos de inserção e remoção\n" +
					"uma árvore-B" ,
					"Como utilizar este programa.",
					JOptionPane.INFORMATION_MESSAGE);
		}

	}

}
