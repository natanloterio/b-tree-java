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
 * @author Valter
 */
public class Arvore {

    private No raiz = new No();
    private int iNumMaxFilhos = -1;

    public Arvore() {
    }

    public boolean vazia() {
        if (raiz.getListChaves().isEmpty()) {
            return true;
        }

        return false;
    }

    public boolean insere(int aChave) {

        int iNumChaves = raiz.getListChaves().size();

        // tentando inserir a chave na folha apropriada, se houver filhos
        if (raiz.getListFilhos().size() > 0) {
            inserirChaveFolha(aChave);
        } else {

            // se não houver filhos ainda, inserir a chave na raiz
            if (iNumChaves < (iNumMaxFilhos - 1)) {
                raiz.addChave(aChave);
                ordenarNo(raiz);
            } // dividindo a raiz quando estiver cheia
            else {
                dividirNo(raiz, aChave);
            }
        }

        return true;
    }

    boolean remove(int aChave) throws Exception {
        if (vazia()) {
            throw new Exception("A Lista encontra-se vazia");
        }

        No node = new No();

        node = this.buscarNo(aChave);
        if (node == null) {
            throw new Exception("Elemento não encontrado");
        }

        if (node.folha() == true && node.getListChaves().size() > 1) {
            node.getListChaves().remove(aChave);
            return true;
        }

        if (node.folha() == true && node.getListChaves().size() == 1){
            ///...........
        }


        return true;
    }

    // # SEM TERMINAR, ALGUÉM POR FAVOR TERMINE ESTE MÉTODO
    // deve buscar pelo nó na árvore e retornar TRUE ou FALSE
    
    //Sherman: true ou false ? não faz mais sentido retornar o nó ???
    public No buscarNo(int aChave) {
        int count;
        for (count = 0; count < this.getRaiz().getListChaves().size(); count++) {

            if (aChave == this.getRaiz().getListChaves().get(count)) {
                return this.getRaiz();
            }


            if (aChave < this.getRaiz().getListChaves().get(count)) {
                return this.buscarNo(aChave, this.getRaiz().getFilho(count));
            }


        }

        //O numero buscado eh maior que todos da lista da raiz, entao busca no
        //ultimo filho mais a direita
        return this.buscarNo(aChave, this.getRaiz().getFilho(count + 1));
    }

    public No buscarNo(int aChave, No aux) {
        int count;

        if (aux == null) {
            return null;
        }

        for (count = 0; count < aux.getListChaves().size(); count++) {
            if (aChave == aux.getListChaves().get(count)) {
                return aux;
            }

            if (aChave < aux.getListChaves().get(count)) {
                return this.buscarNo(aChave, aux.getFilho(count));
            }
        }

        return this.buscarNo(aChave, aux.getFilho(count + 1));
    }

    // busca por uma chave na árvore
    public boolean buscaChave(No aNo, int aChave) {
        int i = 0;
        int iNumChaves = aNo.getListChaves().size();


        if (aNo.getListChaves().contains(aChave)) {
            return true;
        } else {

            while (i < iNumChaves && aChave > aNo.getListChaves().get(i)) {
                i++;
            }


            // se não fora encontrado a chave
            if (i >= iNumChaves) {
                return false;
            }

            /*
            if (aChave == aNo.getListChaves().get(i)) {
            return true;
            } else {
             */
            buscaChave(aNo.getListFilhos().get(i), aChave);
        }
        return false;
    }

    private void ordenarNo(No aNo) {
        Collections.sort(aNo.getListChaves());
    }

    public void setNumMaxFilhos(int aNumMaxFilhos) {
        this.iNumMaxFilhos = aNumMaxFilhos;
    }

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

    public void exibirNo(No aNo) {
        Iterator it = aNo.getIterator();

        while (it.hasNext()) {
            System.out.print(it.next() + ", ");
        }
    }

    public void removerChaves(No aNo, Vector aRemover) {
        Iterator it = aRemover.iterator();

        while (it.hasNext()) {
            System.out.println("Removendo >> " + it.next());
            aNo.getListChaves().remove(it.next());
        }
        // ao final da remoção ordenar as chaves
        Collections.sort(aNo.getListChaves());

        System.out.println("No raiz >>> ");
        exibirNo(aNo);

    }

    // inserindo a chave na folha apropriada
    public boolean inserirChaveFolha(int aChave) {

        int i = 0;
        int iTamanho = raiz.getListChaves().size();

        // verificando a qual nó filho a chave pertencerá
        while (i < iTamanho && aChave > raiz.getListChaves().get(i)) {
            i++;
        }

        // se o nó que foi encontrado para receber a chave tiver espaço para receber a chave
        // ela será inserida
        if (raiz.getListFilhos().get(i).getListChaves().size() < (iNumMaxFilhos - 1)) {

            raiz.getListFilhos().get(i).addChave(aChave);
            ordenarNo(raiz.getListFilhos().get(i));
        } // caso não haja espaço no nó, é necessário fazer a divisão do nó.
        else {
            dividirNo(raiz.getListFilhos().get(i), aChave);
        }

        return true;
    }

    public void dividirNo(No aNo, int aChave) {

        System.out.println("nó cheio");

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

    // sabendo qual posição que esta o nó que irá subir
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

    public No getRaiz() {
        return raiz;
    }
}
