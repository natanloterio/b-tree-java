/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arvore_b;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Valter
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int iOpcao = 1;
        int iNumMaxFilhos = 0;
        
        Scanner scanner = new Scanner(System.in);
        Arvore arvore = new Arvore();

        while (iOpcao != 0) {
            System.out.println(" -- Arvore Binária -- ");
            System.out.println(" 1 - Inserir");
            System.out.println(" 2 - Remover");
            System.out.println(" 3 - Exibir");
            System.out.println(" 4 - Número máximo de filhos ");
            System.out.println(" 5 - Carregar arquivo");
            System.out.println(" 6 - Limpar árvore");
            System.out.println(" 7 - Busca chave");
            System.out.println(" 0 - Sair");
            System.out.println(">>");

            iOpcao = scanner.nextInt();

            System.out.println("-------------------------------");

            switch (iOpcao) {
                case 0:
                    System.out.println("Até logo !");
                    break;

                case 1:
                    if (iNumMaxFilhos == 0)
                        System.out.println("Primeiro informe o número máximo de filhos dos nós da árvore!");
                    else{
                        int iInserir;
                        System.out.println("Inserir >> ");
                        iInserir = scanner.nextInt();

                        if (arvore.insere(iInserir))
                            System.out.println("Chave inserida com sucesso!");
                        else
                            System.out.println("Não foi possível inserir a chave!");
                    }
                        

                    break;

                case 2:
                    int iRemover;
                    System.out.println("Remover >> ");
                    iRemover = scanner.nextInt();
                    if ( arvore.remove(iRemover))
                        System.out.println("Chave removida com sucesso!");
                    else
                        System.out.println("Árvore vazia!");
                    break;

                case 3:
                    if (arvore.vazia())
                        System.out.println("Árvore vazia!");
                    else
                        arvore.exibir();
                    break;

                case 4:
                    // 3 é o mínimo de chaves em um nó pode ter em uma árvore B
                    while (iNumMaxFilhos < 3){
                        System.out.println("Numero máximo de filhos dos nós da árvore >> ");
                        System.out.println(">> ");
                        iNumMaxFilhos = scanner.nextInt();
                    }

                    // dizendo a arvore o numero máximo de filhos que um nó poderá ter
                    arvore.setNumMaxFilhos(iNumMaxFilhos);
                    break;

                // oa valores que serão inseridos na árvore podem ser carregados atráves de um arquivo, chamando 'Dados'
                case 5:
                    Arquivo arq = new Arquivo();
                    List<Integer> list = new ArrayList<Integer>();

                    System.out.println("-- Valors carregados do arquivo -- ");

                    // mudar este caminho para o local aonde esta o arquivo 'Dados' no seu computador
                    list = arq.lerArquivo("C:\\Users\\Valter\\Documents\\NetBeansProjects\\Arvore_B\\src\\arvore_b\\Dados.txt");

                    // o primeiro valor lido do arquivo será o número máximo de filhos que aquela árvore poderá ter
                    iNumMaxFilhos = list.get(0);
                    arvore.setNumMaxFilhos(iNumMaxFilhos);
                    
                    int i = 1;
                    // inserindo os valores restantes na arvore
                    while (i < list.size()){
                        arvore.insere(list.get(i));
                        i++;
                    }
                    break;

                case 6:
                    arvore = new Arvore();
                    iNumMaxFilhos = 0;
                    System.out.println("Árvore limpa !!");
                break;

                case 7:
                    int iChave;

                    if (iNumMaxFilhos > 2) {
                        System.out.print("Informe a chave >> ");
                        iChave = scanner.nextInt();

                        if (arvore.buscaChave(arvore.getRaiz(), iChave)) {
                            System.out.println("Chave já existe na arvore !!!");
                        } else {
                            System.out.println("Não foi encontrada a chave informada");
                        }
                    }else
                        System.out.println("Primeiro informe o número máximo de filhos dos nós da árvore!");
                    
                    break;

                default:
                    System.out.println("Esta não é uma opção válida!");
            }

            System.out.print("\n-------------------------------\n");

        }


    }
}
