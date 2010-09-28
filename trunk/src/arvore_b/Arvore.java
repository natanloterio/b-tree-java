package arvore_b;

import java.util.Collections;
import java.util.Iterator;


/**
 * Classe árvore que irá gerenciar as inserções e remoções que uma árvore B terá
 * @author Valter Henrique, Arthur Mazer, Vitor Villela
 */
public class Arvore {

    // nó raiz da árvore
    private No raiz;
    // número máximo de filhos que a árvore terá
    private final int iNumMaxFilhos;
    // número mínimo de chaves que os nós terão
    private final int iNumMinChaves;
    // número mínimo de chaves que os nós terão
    private int iNumMaxChaves;

    /**
     * Toda árvore precisa saber o número máximo de filhos que terá antes de começar a inserir elementos na mesma
     * @param aNumMaxFilhos
     * @author Valter Henrique
     */
    public Arvore(int aNumMaxFilhos) {
        // valor máximo de filhos que a árvore terá, informado pelo usuário
        iNumMaxFilhos = aNumMaxFilhos;
        // Calculando o mínimo de chaves que um nó nesta árvore poderá ter
        iNumMinChaves = (int) Math.floor(((double) (iNumMaxFilhos - 1)) / ((double) 2));
        // Calculando o máximo de chaves que os nós terão nessa árvore
        iNumMaxChaves = iNumMaxFilhos - 1;
        // inicializando a raiz da árvore
        raiz = new No();
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

        // buscando chave na árvore, caso retorne != null, quer dizer que encontrou um nó que possui a chave
        if (buscaChave(raiz, aChave) != null) {
            return false;

        } // senão não achar a chave na árvore, então inserir a chave na árvore
        else {
            // se houver espaço na raiz para ser inserido o valor
            if (raiz.numChaves() < iNumMaxChaves) {

                // quando não há filhos
                if (raiz.folha()) {
                    raiz.addChave(aChave);
                    ordenarNo(raiz);
                } else {
                    insereNoNaoCheio(raiz, aChave);
                }

            } // se não houver mais espaço na raiz
            else {
                if (raiz.numChaves() == iNumMaxChaves) {
                    if (raiz.folha()) {
                        No noFilho = raiz;
                        int i = procurarFilho(noFilho, aChave);

                        raiz = new No();

                        noFilho.addChave(aChave);
                        ordenarNo(noFilho);

                        divideNo(raiz, noFilho, i);

                    } else {
                        insereNoNaoCheio(raiz, aChave);
                    }

                }
            }

        }
        return true;
    }

    /**
     * Inserindo uma chave em um nó que não esta cheio
     * @param aNo O nó que não esta cheio
     * @param aChave A chave a ser inserida neste nó passado juntamente
     * @author Valter Henrique
     */
    public void insereNoNaoCheio(No aNo, int aChave) {

        // se o nó for folha, acabou a busca pela nó correto e insere a chave neste nó
        if (aNo.folha()) {
            aNo.addChave(aChave);
            ordenarNo(aNo);
        }
        // se o nó não for folha, quer dizer que tem filhos e então iremos procurar em qual filho é o melhor para receber a chave recursivamente
        else {
            // procurando o índice de qual filho irá receber a chave
            int i = this.procurarFilho(aNo, aChave);

            // se houver espaço no nó que vai receber a chave
            if (aNo.getFilho(i).numChaves() < iNumMaxChaves) {
                insereNoNaoCheio(aNo.getFilho(i), aChave);
            } else {
                // senão houver mais espaço no nó
                if (aNo.getFilho(i).numChaves() == iNumMaxChaves) {
                    // se nó a receber chave for folha e estiver cheio
                    if (aNo.getFilho(i).folha()) {
                        aNo.getFilho(i).addChave(aChave);
                        ordenarNo(aNo.getFilho(i));
                        
                        divideNo(aNo, aNo.getFilho(i), i);
                    } else {
                        insereNoNaoCheio(aNo.getFilho(i), aChave);
                    }
                }
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
    public void divideNo(No aPai, No aFilho, int iIndice) {

        // adicionando a chave no nó pai
        aPai.addChave(aFilho.getChave(iNumMinChaves));
        ordenarNo(aPai);

        aFilho.removeChavePeloIndice(iNumMinChaves);

        No noNovo = new No();

        // passando as chaves do filho para o novo nó
        while (aFilho.numChaves() > iNumMinChaves) {
            noNovo.addChave(aFilho.getChave(iNumMinChaves));
            aFilho.removeChavePeloIndice(iNumMinChaves);
        }

        // se aFilho não for folha, então temos que passar seus filhos para o novo nó
        if (!aFilho.folha()) {
            //passando os filhos (do meio da direita) do nó filho para o novo nó
            while (aFilho.numFilhos() > (iNumMinChaves + 1)) {
                noNovo.addFilho(aFilho.getFilho(iNumMinChaves + 1));
                aFilho.removeFilho(iNumMinChaves + 1);
            }
        }

        // se aPai for folha, quer dizer que é o novo nível acima da árvore
        if (aPai.folha()) {
            aPai.addFilho(aFilho);
            aPai.addFilhosNoIndice(aPai.numFilhos(), noNovo);
        } else {
            aPai.addFilhosNoIndice(iIndice + 1, noNovo);
        }

        // se ao passar a chave do meio para o pai e ele tiver mais chaves que o permitido, ele também precisará ser dividido
        if (aPai.numChaves() > iNumMaxChaves) {
            // pegando a maior chave do pai para procurarmos quem é seu antecessor ou seja, se aPai é filho de um nó, queremos saber quem é seu pai
            int iMaiorChavePai = this.getMaiorChave(aPai);
            // buscando o pai de aPai
            No noPai = buscaPai(raiz, iMaiorChavePai);

            // se não achar o pai de aPai, quer dizer que ele é a raiz
            if (noPai == null) {
                No novaRaiz = new No();
                raiz = novaRaiz;

                divideNo(novaRaiz, aPai, iIndice);

            } else {
                // caso aPai tenha um pai, então aPai será dividido e o pai de aPai irá receber a sua chave do meio recursivamente
                iIndice = this.procurarFilho(noPai, iMaiorChavePai);
                divideNo(noPai, aPai, iIndice);

            }

        }
    }

    /**
     * Buscando o pai de um terminado nó passando somente a maior chave do nó o qual eu quero saber quem é seu pai, mande sempre a raiz como parâmetro que o método recursivamente irá encontrar o pai, caso não tenha nenhum pai, é por que ele já é a raiz
     * @param aNo Nó que queremos procurar se ele contém um filho que contém a maior chave do nó
     * @param aChave A maior chave de um nó o qual queremos saber quem é seu pai
     * @return O nó pai que contém o nó que contém a maior chave passada como parâmetro
     * @author Valter Henrique
     */
    public No buscaPai(No aNo, int aChave) {

        // procurando o índice de qual filho é o melhor para procurarmos abaixo
        int i = this.procurarFilho(aNo, aChave);

        // se aNo for folha, quer dizer que não tem mais folhas, então não há sentido em procurarmos por filhos que possuam a maior chave, já que eles não existem
        if (aNo.folha())
            return null;

        // se aNo tiver um filho que contém a maior chave, então ele é o nó pai
        if (aNo.getFilho(i).contemChave(aChave)){
            return aNo;
        }
        // se ele não for o pai, então iremos procurar recursivamente
        else{
            aNo = buscaPai(aNo.getFilho(i), aChave);
        }

        return aNo;
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
        if (node.folha()) {
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
    public void exibir(No aNo) {
        if (aNo.folha()) {
            exibirNo(aNo);
        } else {
            int i = 0;
            int k = 0;

            while (i < aNo.numFilhos()) {
                if (k < aNo.numChaves()) {
                    System.out.println("***********");
                    System.out.print("Nó Pai >> " + aNo.getChave(k));
                    System.out.print("\nNumero de filhos >> " + aNo.numFilhos());
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

    /**
     * Retorna a chave de maior valor dentro de um nó passado como parâmetro
     * @param aNo O nó o qual queremos saber qual sua maior chave
     * @return Retorna a chave de maior valor neste nó
     * @author Valter Henrique
     */
    public int getMaiorChave(No aNo) {
        int i = 0;
        Iterator it = aNo.getIteratorChaves();

        while (it.hasNext()) {
            i++;
            it.next();
        }


        return aNo.getChave(i - 1);
    }


    /**************** SHERMAN ***********************************/

    /*alterei a partir daqui, vou colocar os metodos get e set Ordem
    tive que tirar do atributo iNumMaximoChaves o identificador final
    porque pode alterar a ordem */
    /**
     * este metodo altera a ordem da arvore
     * @param x sera um valor, do tipo inteiro, passado para alterar o numero maximo de filhos que uma arvore pode ter
     */
    // VALTER : Sherman, mas aqui o número de chaves tem que ser passado como parâmetro para a árvore ser criada, ele já faz isso no construtor
    public void setMaximoChaves(int x) {
        iNumMaxChaves = x;
    }

    /**
     * este metodo retorna o numero maximo de filhos
     * @return do tipo inteiro
     */
    public int getMaximoChaves() {
        return iNumMaxChaves;
    }

    /**
     * Retorna o número minimo de chaves a serem armazenadas em um nó
     * @return INT
     */
    public int getMinimoChaves() {
        return iNumMinChaves;
    }

    /**
     * Método que permuta aChave e seu antecessor de nó
     * @param node
     * @param aChave
     */
    public void trocaAntecessor(No node, int aChave) {
        No node_aux = node.getAntecessor(aChave);
        int K;

        K = node_aux.getListChaves().get(node_aux.getListChaves().size() - 1);

        node_aux.removeChavePeloIndice(node_aux.getIndexChave(K));
        node_aux.addChave(aChave);

        node.removeChavePeloIndice(node.getIndexChave(aChave));
        node.addChave(K);
    }

}

