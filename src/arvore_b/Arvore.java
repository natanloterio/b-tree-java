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

    private No raiz = new No();
    private final int iNumMaxFilhos;
    private final int iNumMinChaves;

    /**
     * Toda árvore precisa saber o número máximo de filhos que terá antes de começar a inserir elementos na mesma
     * @param aNumMaxFilhos
     * @author Valter Henrique
     */
    public Arvore(int aNumMaxFilhos) {
        iNumMaxFilhos = aNumMaxFilhos;
        // Calculando o mínimo de chaves que um nó nesta árvore poderá ter
        iNumMinChaves = (int) Math.floor(((double) (iNumMaxFilhos - 1)) / ((double) 2));
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
    public boolean insere(int aChave) {

        // se ao buscar a chave e retorna null quer dizer que não existe tal chave na árvore
        // podendo assim inserir a chave na árvore
        if (this.buscaChave(raiz, aChave) == null) {

            // tentando inserir a chave na folha apropriada, se houver filhos
            if (raiz.numFilhos() > 0) {
                inserirChaveFolha(raiz, aChave);
            } else {
                // se não houver filhos ainda, inserir a chave na raiz
                if (raiz.numChaves() < (iNumMaxFilhos - 1)) {
                    raiz.addChave(aChave);
                    ordenarNo(raiz);
                } // dividindo a raiz quando estiver cheia
                else {
                    inserirChaveFolha(raiz, aChave);
                    //dividirNo(raiz, aChave);
                }
            }
        } else {
            return false;
        }

        return true;
    }

    /** Inserindo a chave na folha apropriada
     *
     * @param aNo Em qual nó será inserido esta chave
     * @param aChave Qual chave a ser inserida
     * @return true se conseguiu inserir a chave, false senão conseguiu inserir
     * @author Valter Henrique
     */
    public boolean inserirChaveFolha(No aNo, int aChave) {

        // 'i' receberá a qual filho pertencerá a nova chave
        int i = procurarFilho(aNo, aChave);

        // se for inserir mas o nó passado é raiz, sem filhos
        if (aNo.getListFilhos().isEmpty()) {
            this.dividirNoRaiz(aNo, aChave);
        } else {

            // se o nó que foi encontrado para receber a chave tiver espaço para receber a chave
            // ela será inserida
            if (aNo.getListFilhos().get(i).getListChaves().size() < (iNumMaxFilhos - 1)) {
                aNo.getListFilhos().get(i).addChave(aChave);
                ordenarNo(aNo.getListFilhos().get(i));
            } // caso não haja espaço no nó, é necessário fazer a divisão do nó.
            else {
                //dividirNo(raiz.getListFilhos().get(i), aChave);
                divideNo(aNo, aNo.getListFilhos().get(i), i);
                insere(aChave);
            }
        }

        return true;
    }

    /**
     * Dividindo um nó
     * @param aPai O nó pai que terá como filho o nó a ser divido
     * @param aFilho O nó a ser dividido
     * @param i O índice de onde esta o nó a ser dividido
     * @author Valter Henrique
     */
    public void divideNo(No aPai, No aFilho, int i) {

        System.out.println("DIVINDO O NÓ !!");

        // iMeio terá a posição da chave do meio que subirá para o nó pai
        int iMeio = this.calcularMeio(aFilho.getListChaves().size());

        System.out.println("CHAVE DO MEIO SERÁ >> " + iMeio);
        System.out.println("MINIMO SERÁ >> " + this.iNumMinChaves);
        boolean bFolha;

        No noNovo = new No();

        int k = iMeio + 1;

        // se o número de chaves que exitir no nó for maior que a metade + 1
        while (aFilho.numChaves() > k) {
            System.out.println("aquiiiiiiiiiiiiiiii");
            noNovo.getListChaves().add(aFilho.getListChaves().get(k));
            aFilho.getListChaves().remove(k);
            //aFilho.removeChave(aFilho, k);
            k++;
        }

        k = iMeio + 1;
        // se o nó filho (o qual será dividido) não for folha
        // então passar os seus filhos (da metade +1) para o novo nó
        if (!aFilho.folha()) {
            while (k <= aFilho.getListFilhos().size()) {
                noNovo.addFilho(aFilho.getFilho(k));
                aFilho.removeChave(k);
                k++;
            }
        }

        // adicionando a chave no nó pai
        aPai.getListChaves().add(i, aFilho.getListChaves().get(iMeio));
        aFilho.getListChaves().remove(iMeio);

        aPai.getListFilhos().add(i + 1, noNovo);

        System.out.print("\n Noh Pai >> ");
        this.exibirNo(aPai);

        System.out.print("\n Noh Filho >> ");
        this.exibirNo(aFilho);

        System.out.print("\n Noh Novo >> ");
        this.exibirNo(noNovo);

    }

    /**
     * Dividindo o nó raiz quando estiver cheia
     * @param aNo No a ser dividido
     * @param aChave A chave a ser inserida neste nó
     * @author Valter Henrique
     */
    public void dividirNoRaiz(No aNo, int aChave) {

        No noEsq = new No();
        No noDir = new No();

        int iNumChaves = raiz.getListChaves().size();
        // sabendo quem é o indice do meio, qual chave irá subir na divisão do nó
        int iMeio = calcularMeio(iNumChaves);


        aNo.getListFilhos().add(noEsq);
        aNo.getListFilhos().add(noDir);


        // é (i+1) pois ultrapassaria o indice o qual deverá ser pego para o próximo nó
        while (aNo.getListChaves().get(0) < aNo.getListChaves().get(iMeio)) {
            // primeiro tenho que adicionar os valores ao novo nó primeiro para depois adicionar este nó a lista de filhos da raiz
            noEsq.addChave(raiz.getListChaves().get(0));

            // removendo a chave que foi para outro nó
            raiz.getListChaves().remove(0);

            // ao remover uma chave, o indice precisa ser atualizado
            iMeio--;
        }

        // enquanto houver duas chaves, pelo menos, na raiz, preciso completar o outro nó
        while (raiz.getListChaves().size() > 1) {
            noDir.addChave(raiz.getListChaves().get(1));

            // removendo a chave que foi para outro nó
            raiz.getListChaves().remove(1);
        }

        // adicionando a chave passada ao nó correto
        if (aChave < aNo.getListChaves().get(0)) {
            noEsq.addChave(aChave);
        } else {
            noDir.addChave(aChave);
        }

        ordenarNo(noEsq);
        ordenarNo(noDir);
        ordenarNo(aNo);

        exibirNo(aNo);
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

        if (node.folha() == true && node.getListChaves().size() > 1) {
            node.getListChaves().remove(aChave);
            return true;
        }

        if (node.folha() == true && node.getListChaves().size() == 1) {
            ///...........
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
        int iNumChaves = aNo.getListChaves().size();

        // procurando em qual filho deve estar a chave
        while (i < iNumChaves && aChave > aNo.getListChaves().get(i)) {
            i++;
        }

        // se achou a chave, retorna o Nó
        if (i < iNumChaves && aChave == aNo.getListChaves().get(i)) {
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

        int iNumFilhos = raiz.getListFilhos().size();

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

    /**
     * Exibir chaves do nó passado como parâmetro
     * @param aNo O nó a ter as chaves exibidas
     * @author Valter Henrique
     */
    public void exibirNo(No aNo) {
        Iterator it = aNo.getIteratorChaves();

        while (it.hasNext()) {
            System.out.print(it.next() + ", ");
        }
    }

    /**
     * Calcula-se qual será a chave do meio do nó a subir
     * @param aNumChaves Numero de chaves do nó a em questão
     * @return O valor do índice do meio do nó
     * @author Valter Henrique
     */
    public int calcularMeio(int aNumChaves) {

        int iMeio;

        if (aNumChaves % 3 == 0) {
            iMeio = (int) Math.ceil((double) aNumChaves / (double) 2);
        } else {
            iMeio = (int) Math.ceil((double) (aNumChaves + 1) / (double) 2);
        }

        iMeio--;

        return iMeio;
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

        while (i < iTamanho && aChave > aNo.getListChaves().get(i)) {
            i++;
        }

        return i;

    }
}
