/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arvore_b;
import janela.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.String;
import java.lang.Throwable;
import java.util.Random;

/**
 *
 * @author Valter Henrique, Arthur Mazer, Vitor Villela
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
        JanelaInicial janela = new JanelaInicial();
        janela.setVisible(true);

    }
    
/*

        int iOpcao = 1;
        // Valter : Por favor não removam essa linha, apenas comentem, é mais prático,rsrs.
        String sArquivo = "C:\\Users\\Valter\\Documents\\NetBeansProjects\\Arvore_B\\src\\arvore_b\\Dados.txt";

        // Valter : Por favor não removam essa linha, apenas comentem, é mais prático,rsrs.
        String sArquivo = "C:\\Users\\Valter\\Documents\\NetBeansProjects\\Arvore B\\trunk\\src\\arvore_b\\Dados.txt";
        //String sArquivo = "C:\\Documents and Settings\\317306\\Meus documentos\\NetBeansProjects\\Arvore B\\trunk\\src\\arvore_b\\Dados.txt";


        // int iNumMaxFilhos = numMaxFilhos();
        int iNumMaxFilhos = 3;

        Scanner scanner = new Scanner(System.in);
        Arvore arvore = new Arvore(iNumMaxFilhos);
        List<Integer> list = new ArrayList<Integer>();
        Random random = new Random();

        while (iOpcao != 0) {
            System.out.println(" -- Arvore Binária -- ");
            System.out.println(" 1 - Inserir");
            System.out.println(" 2 - Remover");
            System.out.println(" 3 - Exibir");
            System.out.println(" 4 - Número máximo de filhos ");
            System.out.println(" 5 - Carregar arquivo");
            System.out.println(" 6 - Limpar árvore");
            System.out.println(" 7 - Busca chave");
            System.out.println(" 9 - Gerar arquivo aleatório");
            System.out.println(" 0 - Sair");
            System.out.print(">> ");

            iOpcao = scanner.nextInt();

            // se ao selecionar uma destas opções e a árvore estiver vazia
            // exibir mensagem de erro que estará em case 8
            if (iOpcao == 2 || iOpcao == 3 || iOpcao == 7) {
                if (arvore.vazia()) {
                    iOpcao = 8;
                }
            }


            System.out.println("-------------------------------");

            switch (iOpcao) {
                case 0:
                    System.out.println("Até logo !");
                    break;

                case 1:
                    int iInserir;
                    System.out.println("Inserir >> ");
                    iInserir = scanner.nextInt();

                    if (arvore.insere(iInserir)) {
                        System.out.println("Chave inserida com sucesso!");
                    } else {
                        System.out.println("Não foi possível inserir a chave!\nChave JÁ EXISTENTE!!");
                    }
                    break;

                case 2:
                    int iRemover;
                    int iOpcaoRemover;
                    System.out.println("(1) Informar valor ");
                    System.out.println("(2) Remover valor aleatóriamente");
                    System.out.print("\n>> ");
                    iOpcaoRemover = scanner.nextInt();
                    
                    if (iOpcaoRemover == 1) {
                        System.out.println("Remover >> ");
                        iRemover = scanner.nextInt();
                        if (arvore.remove(iRemover)) {
                            System.out.println("Chave removida com sucesso !");
                        } else {
                            System.out.println("Chave não existe na árvore");
                        }
                    }else{
                        int i = random.nextInt(list.size());
                        iRemover = list.get(i);
                        list.remove(i);
                        
                        System.out.println("Remover (aleatóriamente) >> " + iRemover);
                        //list.remove(iRemover);
                        if (arvore.remove(iRemover)) {
                            System.out.println("Chave removida com sucesso !");
                        } else {
                            System.out.println("Chave não existe na árvore");
                        }
                    }

                    break;

                case 3:
                    arvore.exibir(arvore.getRaiz());
                    break;

                case 4:
                    arvore = new Arvore(numMaxFilhos());
                    break;

                // os valores que serão inseridos na árvore podem ser carregados atráves de um arquivo, chamando 'Dados'
                case 5:
                    Arquivo arq = new Arquivo();

                    System.out.println("-- Valors carregados do arquivo -- ");

                    // mudar este caminho para o local aonde esta o arquivo 'Dados' no seu computador
                    list = arq.lerArquivo(sArquivo);

                    // o primeiro valor lido do arquivo será o número máximo de filhos que aquela árvore poderá ter
                    arvore = new Arvore(list.get(0));

                    int i = 1;
                    // inserindo os valores restantes na arvore
                    while (i < list.size()) {
                        arvore.insere(list.get(i));
                        //arvore.insere(arvore.getRaiz(), list.get(i));
                        i++;
                    }
                    break;

                case 6:
                    arvore = new Arvore(numMaxFilhos());
                    break;

                case 7:
                    int iChave;
                    System.out.print("Informe a chave >> ");
                    iChave = scanner.nextInt();

                    if (arvore.buscaChave(arvore.getRaiz(), iChave) != null) {
                        System.out.println("Chave já existe na arvore !!!");
                    } else {
                        System.out.println("Não foi encontrada a chave informada");
                    }
                    break;

                case 8:
                    System.out.println("Arvore vazia !\nOperação não possível !");
                    break;

                case 9:
                    Arquivo arqRandomico = new Arquivo();

                    int iValorMaxChave;
                    int iQtdChaves;

                    iNumMaxFilhos = numMaxFilhos();

                    System.out.print("Informe o valor máximo que uma chave poderá ter >> ");
                    iValorMaxChave = scanner.nextInt();

                    System.out.print("Quantas chaves aleatórias serão criadas >> ");
                    iQtdChaves = scanner.nextInt();

                    arqRandomico.gerarDadosAleatório(sArquivo, iNumMaxFilhos, iValorMaxChave, iQtdChaves);
                    break;

                default:
                    System.out.println("Esta não é uma opção válida!");
            }

            System.out.println("\n-------------------------------");

        }
    }

    // retornando o número máximo de filhos que terá a árvore
    static int numMaxFilhos() {
        int iNumMaxFilhos = 0;
        Scanner scanner = new Scanner(System.in);

        // 3 é o mínimo de chaves em um nó pode ter em uma árvore B
        while (iNumMaxFilhos < 3) {
            System.out.println("Numero máximo de filhos dos nós da árvore >> ");
            System.out.println(">> ");
            iNumMaxFilhos = scanner.nextInt();
        }
        return iNumMaxFilhos;
    }

    */
}