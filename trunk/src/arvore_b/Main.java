/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arvore_b;

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

                    arvore.setNumMaxFilhos(iNumMaxFilhos);
                    break;


                default:
                    System.out.println("Esta não é uma opção válida!");
            }

            System.out.println("-------------------------------");

        }


    }
}
