/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arvore_b;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Valter
 */
public class Arquivo {

    private List<Integer> list = new ArrayList<Integer>();

    public Arquivo() {
    }

    /**
     * Lendo arquivo que contem informações para carregar uma determinada árvore automaticamente
     * @param sArqLer
     * @return Retorna uma lista de inteiros sendo o primeiro termo da lista o número máximo de chaves e os demais as chaves a serem inseridas na árvore
     * @author Valter Henrique
     */
    public List<Integer> lerArquivo(String sArqLer) {

        //lendo sLinhas
        try {
            // ler o arquivo
            FileReader i = new FileReader(sArqLer);
            // bufferizando o que foi lido
            BufferedReader in = new BufferedReader(i);
            // o que foi lido será atribuido a uma string
            String sLinha;
            // que esta string por sua vez, será convertida em um valor do tipo inteiro
            int iValor;

            while ((sLinha = in.readLine()) != null) {
                if (sLinha.length() > 0) {
                    System.out.print(sLinha + ", ");

                    // convertendo de string para inteiro
                    iValor = Integer.parseInt(sLinha);

                    // adicionando este valores aos valores que deverão ser adicionados á arvore
                    list.add(iValor);
                }

            }
            // fechando os arquivos
            i.close();
        } catch (Exception x) {
            System.out.println(x.getMessage());
        }

        return list;
    }
}
