package arvore_b;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.lang.Math;
import java.util.Vector;
import java.lang.Throwable;
import java.lang.Exception;

/**
 *
 * @author Valter Henrique, Arthur Mazer, Vitor Villela
 */
public class Arvore {

    private No raiz;
    private final int iNumMaxFilhos;
    private final int iNumMinChaves;
    private int iNumMaxChaves;

    /**
     * Toda árvore precisa saber o número máximo de filhos que terá antes de começar a inserir elementos na mesma
     * @param aNumMaxFilhos
     * @author Valter Henrique
     */
    public Arvore(int aNumMaxFilhos) {
        iNumMaxFilhos = aNumMaxFilhos;
        // Calculando o mínimo de chaves que um nó nesta árvore poderá ter
        iNumMinChaves = (int) Math.floor(((double) (iNumMaxFilhos - 1)) / ((double) 2));
        iNumMaxChaves = iNumMaxFilhos - 1;
        raiz = new No();;
    }

    /**
     * Verifica se a árvore esta vazia
     * @return true se árvore estiver vazia, false se tiver ao menos uma chave
     * @author Valter Henrique
     */
    public boolean vazia() {
        if (raiz.numChaves() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Inserindo chave passada na árvore
     * @param aChave
     * @return true se conseguiu inserir chave na árvore, false senão conseguiu
     * @author Valter Henrique
     */
    public boolean insere(No aRaiz, int aChave) {

        if (buscaChave(raiz, aChave) != null)
            return false;

        // se houver espaço a ser inserido na raiz
            if(aRaiz.numChaves() == iNumMaxChaves){
                if (aRaiz.numFilhos() == iNumMaxFilhos)
                    insereNoNaoCheio(aRaiz, aChave);
                else{

                No noNovo = new No();

                // adicionando o nó raiz como filho do novo nó
                noNovo.addFilho(aRaiz);

                // dividindo a nó cheio que agora é o filho (que antes era a raiz)
                this.divideNo(noNovo, 0, noNovo.getFilho(0));

                raiz  = noNovo;

                // como dividimos o nó agora temos um nó com espaço a ser inserido
                insereNoNaoCheio(noNovo.getFilho(1), aChave);
                }
            }else
                insereNoNaoCheio(aRaiz, aChave);

        return true;
    }

    public void insereNoNaoCheio(No aNo, int aChave) {

        if (aNo.folha()) {
            aNo.addChave(aChave);
            ordenarNo(aNo);
        } else {
            int i = this.procurarFilho(aNo, aChave);
            int iNumFilhos = aNo.numFilhos();

            if (aNo.getFilho(i).numChaves() == iNumMaxChaves) {
                divideNo(aNo, i, aNo.getFilho(i));
                i++;
            }

            if (aNo.numFilhos() >= i) {
                insereNoNaoCheio(aNo.getFilho(i), aChave);
            } else {
                insere(raiz, aChave);
            }

        }
    }


    /**
     * Dividindo um nó
     * @param aPai O nó pai que terá como filho o nó a ser divido
     * @param aFilho O nó a ser dividido
     * @param i O índice de onde esta o nó a ser dividido
     * @author Valter Henrique
     */
    public void divideNo(No aPai, int i, No aFilho) {

        System.out.println("DIVINDO O NÓ !!");

        // iMeio terá a posição da chave do meio que subirá para o nó pai
        int iMeio = this.calcularMeio(aFilho.numChaves());

        System.out.println("CHAVE DO MEIO SERÁ >> " + iMeio);

        No noNovo = new No();

        // passando as chaves do filho para o novo nó
        while (aFilho.numChaves() > (iMeio + 1)) {
            noNovo.addChave(aFilho.getChave(iMeio + 1));
            aFilho.removeChave(iMeio + 1);
        }

        if (!aFilho.folha()) {
            //passando os filhos (do meio da direita) do nó filho para o novo nó
            while (aFilho.numFilhos() > (iMeio + 1)) {
                noNovo.addFilho(aFilho.getFilho(iMeio + 1));
                aFilho.removeFilho(iMeio + 1);
            }
        }

        // adicionando a chave no nó pai
        aPai.addChaveNoIndice(i, aFilho.getChave(iMeio));
        aFilho.removeChave(iMeio);

        // adicionando novo nó ao pai
        aPai.addFilhosNoIndice(i + 1, noNovo);

        if (aPai.numChaves() > iNumMaxChaves) {
            No noRaiz = new No();
            No noDireita = new No();

            iMeio = this.calcularMeio(aPai.numChaves());

            while(aPai.numChaves() > iMeio+1){
                noDireita.addChave(aPai.getChave(iMeio+1));
                aPai.removeChave(iMeio+1);
            }

            while(aPai.numFilhos() > iMeio+1){
                noDireita.addFilho(aPai.getFilho(iMeio+1));
                aPai.removeFilho(iMeio+1);
            }

            // subindo a chave para  a nova raiz
            noRaiz.addChave(aPai.getChave(iMeio));
            aPai.removeChave(iMeio);
            
            // adicionando os filhos a nova raiz
            // aPai será o nó da esquerda
            noRaiz.addFilho(aPai);
            // noDireita será o nó da direita da nova raiz
            noRaiz.addFilho(noDireita);

            raiz = noRaiz;
            //noRaiz.addFilho(aPai);
        } 
            
    }

    /**
     * Removendo chave da árvore
     * @param aChave A chave a ser removida
     * @return true se conseguiu remover a chave, false senão conseguiu remover
     * @throws Exception
     * @author Arthur Mazer
     */
    boolean remove(int aChave) throws Exception {
        if (vazia()) {
            throw new Exception("A Lista encontra-se vazia");
        }

        No node = new No();

        node = this.buscaChave(raiz, aChave);
        if (node == null) {
            throw new Exception("Elemento não encontrado");
        }


        /*Caso 1:
         *       O elemento procurado é folha         *
         */
        if ( node.folha() )  {
            node.getListChaves().remove(aChave);
            return true;
        }



        return true;
    }

    /**
     * Busca por uma chave na árvore recursivamente
     * @param aNo O nó aonde será procurada a chave
     * @param aChave A chave aer buscada na árvore
     * @return O nó aonde esta a chave, null senão encontrar a chave na árvore
     * @author Valter Henrique
     */
    public No buscaChave(No aNo, int aChave) {
        int i = 0;
        int iNumChaves = aNo.numChaves();

        // procurando em qual filho deve estar a chave
        while (i < iNumChaves && aChave > aNo.getChave(i)) {
            i++;
        }

        // se achou a chave, retorna o Nó
        if (i < iNumChaves && aChave == aNo.getChave(i)) {
            return aNo;
        }

        // se não tiver filhos, então é folha
        if (aNo.getListFilhos().isEmpty()) {
            return null;
        } else {
            // aNo recebe o resultado da busca que foi realizada, desempilhando a pilha que foi contruída
            // devido a recursão realizada 
            aNo = buscaChave(aNo.getListFilhos().get(i), aChave);
        }

        return aNo;

    }

    /**
     * Ordena as chaves ativas dentro de um nó
     * @param aNo O nó a ser ordenado
     * @author Valter Henrique
     */
    private void ordenarNo(No aNo) {
        Collections.sort(aNo.getListChaves());
    }

    /**
     * Exibe as chaves da árvore
     * @author Valter Henrique
     */
    public void exibir() {

        int iNumFilhos = raiz.numFilhos();

        System.out.println("Numero de filhos da raiz  >> " + iNumFilhos);

        // quando houve valores somente na raiz
        if (iNumFilhos == 0) {
            exibirNo(raiz);
        } // quando houver filhos com valores
        else {
            // indice para indicar qual filho esta
            int i = 0;
            Iterator it = raiz.getListChaves().iterator();
            while (i < iNumFilhos) {

                System.out.print("\n Filho (" + i + ") >> ");
                exibirNo(raiz.getListFilhos().get(i));

                if (it.hasNext()) {
                    System.out.print("\n Raiz >> ");
                    System.out.print(it.next());
                }
                i++;
            }
        }
    }

    public void exibir(No aNo) {
        System.out.println("NUMERO DE FILHOS É >> " + aNo.numFilhos());


        if (aNo.folha()) {
            exibirNo(aNo);
        } else {
            int i = 0;
            int k = 0;

            while (i < aNo.numFilhos()) {
                if (k < aNo.numChaves()) {
                    System.out.println("***********");
                    System.out.print("Nó Pai >> " + aNo.getChave(k));
                    System.out.println("\n***********");
                    k++;
                }
                    exibir(aNo.getFilho(i));
                    i++;

                
            }


        }

    }

    /**
     * Exibir chaves do nó passado como parâmetro
     * @param aNo O nó a ter as chaves exibidas
     * @author Valter Henrique
     */
    public void exibirNo(No aNo) {
        Iterator it = aNo.getIteratorChaves();

        System.out.println("------------");
        while (it.hasNext()) {
            System.out.print(it.next() + ", ");
        }

        System.out.println("\n------------");
    }

    /**
     * Calcula-se qual será a chave do meio do nó a subir
     * @param aNumChaves Numero de chaves do nó a em questão
     * @return O valor do índice do meio do nó
     * @author Valter Henrique
     */
    public int calcularMeio(int aNumChaves) {

       if (aNumChaves % 2 == 0) {
            return (int) Math.ceil((double) (aNumChaves) / (double) 2);
        } else {
           // -1 no final pois os indices começam com 0(zero)
            return (int) Math.ceil((double) aNumChaves / (double) 2) - 1;

        }

    }

    /**
     * Retorna a raiz da árvore, necessário para poder inicializar a busca de uma determinada chave
     * @return Nó raiz da árvore
     * @author Valter Henrique
     */
    public No getRaiz() {
        return raiz;
    }

    /**
     * Busca e retorna o índice de qual filho terá a chave a ser inserida
     * @param aNo Nó pai
     * @param aChave A chave a ser inserida
     * @return O índice de qual filho receberá a chave
     * @author Valter Henrique
     */
    public int procurarFilho(No aNo, int aChave) {
        int i = 0;
        int iTamanho = aNo.getListChaves().size();

        while (i < iTamanho && aChave > aNo.getChave(i)) {
            i++;
        }

        return i;

    }


    /*alterei a partir daqui, vou colocar os metodos get e set Ordem
     tive que tirar do atributo iNumMaximoChaves o identificador final
     porque pode alterar a ordem */

    /**
     * este metodo altera a ordem da arvore
     * @param x sera um valor, do tipo inteiro, passado para alterar o numero maximo de filhos que uma arvore pode ter
     */
    public void setMaximoChaves(int x){
        iNumMaxChaves = x;
    }

    /**
     * este metodo retorna o numero maximo de filhos
     * @return do tipo inteiro
     */
    public int getMaximoChaves(){
        return iNumMaxChaves;
    }

    /**
     * Retorna o número minimo de chaves a serem armazenadas em um nó
     * @return INT
     */
    public int getMinimoChaves(){
        return iNumMinChaves;
    }

    /**
     * Método que permuta aChave e seu antecessor de nó
     * @param node
     * @param aChave
     */
    public void trocaAntecessor(No node,int aChave){
        No node_aux = node.getAntecessor(aChave);
        int K;

        K = node_aux.getListChaves().get(node_aux.getListChaves().size()-1);

        node_aux.removeChave(node_aux.getIndexChave(K));
        node_aux.addChave(aChave);

        node.removeChave(node.getIndexChave(aChave));
        node.addChave(K);
    }



}
