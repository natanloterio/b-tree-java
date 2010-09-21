package arvore_b;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Valter Henrique, Arthur Mazer, Vitor Villela
 */
public class No {

    private List<Integer> listChaves = new ArrayList<Integer>();
    private List<No> listFilhos = new ArrayList<No>();

    /**
     * seta todos os filhos para NULL;
     * Valter : isso é mesmo necessário? Se não há filhos, o tamanho da listFilhos será 0.
     * @author : Arthur Mazer
    */
    public No() {
        int i;
        for (i = 0; i < this.listFilhos.size(); i++) {
            this.listFilhos.set(i, null);
        }
    }

    /**
     * Retorna a lista de chaves de inteiros
     * @author Valter Henrique
     * @return the listChaves
     */
    public List<Integer> getListChaves() {
        return listChaves;
    }

    /**
     * Retorna a lista de filhos
     * @return the listFilhos
     * @author Valter Henrique
     */
    public List<No> getListFilhos() {
        return listFilhos;
    }

    /**
     * Adiciona chave ao nó que chamou o método
     * @param aChave
     * @author Valter Henrique
     */
    public void addChave(int aChave) {
        listChaves.add(aChave);
    }

    /**
     * Inserindo a chave em uma posição da lista de forma que os valores posterioes a essa posição se desloquem para direita após essa inserção
     * @param index O indice aonde quer inserir a chave
     * @param aChave A chave a ser inserida
     * @author Valter Henrique
     */
    public void addChaveNoIndice(int index, int aChave){
        listChaves.add(index, aChave);
    }

    /**
     * Inserindo um nó em uma posição da lista de filhos de forma que os nós posteriores a essa posição se desloquem para direita após a inserção
     * @param index O índice de aonde deve ocorrer essa inserção
     * @param aNo O nó a ser inserido
     * @author Valter Henrique
     */
    public void addFilhosNoIndice(int index, No aNo){
        listFilhos.add(index, aNo);
    }

    /**
     * Retorna a chave no indíce informado
     * @param index O indice que contem a chave
     * @return O indice o qual contém a chave
     * @author Valter Henrique
     */
    public int getChave(int index){
        return listChaves.get(index);
    }

    /**
     *Adiciona filho ao nó que chamou o método
     * @param aNo
     * @author Valter Henrique
     */
    public void addFilho(No aNo){
        listFilhos.add(aNo);
    }

    /**
     * Removendo determinada chave do nó, passando o seu índice apenas lembrando que a 'list' já reordena a numeração dos índices após a remoção
     * @param aNo - o nó que terá a chave removida
     * @param index - o índice aonde será removido a chave
     * @author Valter Henrique
     */
    public void removeChave(int index){
        System.out.println("REMOVENDO CHAVE");
        System.out.println(listChaves.remove(index));
    }

    /**
     * Removendo determinado filho, passando o seu índice apenas lembrando que a 'list' já reordena a numeros dos ínidices após a remoção
     * @param index Indice de qual filho será removido
     * @author Valter Henrique
     */
    public void removeFilho(int index){
        listFilhos.remove(index);
    }

    /**
     * Retorna o número de chaves ativas que o nó possui
     * @param aNo
     * @return o número de chaves ativas que o nó possui atualmente
     * @author Valter Henrique
     */
    public int numChaves(){
        return listChaves.size();
    }

    /**
     * Retorna o número de filhos que o nó possui
     * @return O número de nós que o nó possui
     * @author Valter Henrique
     */
    public int numFilhos(){
        return listFilhos.size();
    }

    /**
     * Retorna o iterador da lista de Chaves
     * @return iterador para a lista de chaves
     * @author Valter Henrique
     */
    public Iterator getIteratorChaves() {
        return listChaves.iterator();
    }

    /** Retorna o filho desejado
     * @param index
     * @return nó no indíce informado
     * @author Arthur Mazer
     */
    public No getFilho(int index) {
        return this.listFilhos.get(index);
    }

    /** Caso o filho mais a esquerda do NÓ for null já prova que ele é folha!
     * @return true se nó for folha, false se nó não for folha
     * @author Arthur Mazer
     */
    public boolean folha() {
        if (this.listFilhos.isEmpty())
            return true;

        return false;

    }
}
