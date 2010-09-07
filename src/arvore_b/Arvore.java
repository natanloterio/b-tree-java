package arvore_b;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.lang.Math;
import java.util.Vector;

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

        No noEsq = new No();
        No noDir = new No();

        int iNumChaves = raiz.getListChaves().size();

        // inserindo a chave na raiz
        if (iNumChaves < (iNumMaxFilhos - 1)) {
            raiz.addChave(aChave);
            ordenarNo(raiz);

        }
        // quando o nó raiz esta cheio
        else {
            int iMeio;

            // somente depois que eu adiciono o nó criado a lista de nós filhos da raiz
            raiz.getListFilhos().add(noEsq);
            raiz.getListFilhos().add(noDir);
            
            // sabendo qual posição que esta o nó que irá subir
            if (iNumChaves % 3 == 0)
                iMeio = (int) Math.ceil( (double)iNumChaves / (double) 2);
            else
                iMeio = (int) Math.ceil( (double) (iNumChaves + 1) / (double) 2);

            iMeio--;

            // # 
            System.out.println("A chave do meio será >> " + iMeio);
            
            // indice para andar no nó raiz
            

            // é (i+1) pois ultrapassaria o indice o qual deverá ser pego para o próximo nó
            while(raiz.getListChaves().get(0) < raiz.getListChaves().get(iMeio)){
                // primeiro tenho que adicionar os valores ao novo nó primeiro para depois adicionar este nó a lista de filhos da raiz
                noEsq.addChave(raiz.getListChaves().get(0));
                
                

                // removendo a chave que foi para outro nó
                raiz.getListChaves().remove(0);

                // indo para o próximo indice
                //i++;

                iMeio--;
            }

            
            //quando 'i' sair do while ele estará em cima da chave do meio do nó, por isso pegamos os seus próximos
            //int i = 1;

            while(raiz.getListChaves().size() > 1){
                noDir.addChave(raiz.getListChaves().get(1));
            

                // adicionando a chave passada ao nó
                if (raiz.getListChaves().size() == 2)
                    noDir.addChave(aChave);

                // removendo a chave que foi para outro nó
                raiz.getListChaves().remove(1);
            }

            System.out.println("No Raiz >> ");
            exibirNo(raiz);
        }

        return true;
    }

    public boolean remove(int aChave) {
        if (vazia()) {
            return false;
        }

        return true;
    }

    // deve buscar pelo nó na árvore e retorná-lo
    public void buscarNo(int aChave) {
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
        /*
        // quando houve valores somente na raiz
        if (iNumFilhos == 0){
            exibirNo(raiz);
        }
        // quando houver filhos com valores 
        else{
            // indice para indicar qual filho esta
            int i = 0;

            while (i < iNumFilhos){
                if (i > 0){
                    // indice para andar nas chaves da raiz
                    int j = 0;

                    // para cada vez que andar nas chaves, mostrar o nó filho seguinte
                    while(j < raiz.getListChaves().size()){
                        System.out.println(raiz.getListChaves().get(j));

                        // recebendo o nó filho abaixo a chave da raiz, respectiva posição J
                        No noAtual = raiz.getListFilhos().get(i);
                        exibirNo(noAtual);

                        // indo para o próximo nó folha
                        i++;

                        // indo para próxima chave da raiz
                        j++;
                    }
                }
                else{
                    // exibindo o primeiro nó filho
                    No noAtual = raiz.getListFilhos().get(i);
                    exibirNo(noAtual);
                    i++;
                }
            }
        }
        */
    }

    public void exibirNo(No aNo){
        Iterator it = aNo.getIterator();

        while (it.hasNext()) {
            System.out.println(it.next() + ", ");
        }
    }

    public void removerChaves(No aNo, Vector aRemover){
           Iterator it = aRemover.iterator();

            while(it.hasNext()){
                System.out.println("Removendo >> " + it.next());
                aNo.getListChaves().remove(it.next());
        }
           // ao final da remoção ordenar as chaves
           Collections.sort(aNo.getListChaves());

           System.out.println("No raiz >>> ");
           exibirNo(aNo);

    }
}
