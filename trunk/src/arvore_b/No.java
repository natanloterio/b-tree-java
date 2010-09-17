package arvore_b;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Valter
 */
public class No {

    private int iNumChaves;
    private List<Integer> listChaves = new ArrayList<Integer>();
    private List<No> listFilhos = new ArrayList<No>();

    //seta todos os filhos para NULL;
    public No() {
        int i;
        for (i = 0; i < this.listFilhos.size(); i++) {
            this.listFilhos.set(i, null);
        }
    }

    /**
     * @return the iNumChaves
     */
    public int getNumChaves() {
        return iNumChaves;
    }

    /**
     * @param iNumChaves the iNumChaves to set
     */
    public void setNumChaves(int iNumChaves) {
        this.iNumChaves = iNumChaves;
    }

    /**
     * @return the listChaves
     */
    public List<Integer> getListChaves() {
        return listChaves;
    }

    /**
     * @return the listFilhos
     */
    public List<No> getListFilhos() {
        return listFilhos;
    }

    public void addChave(int aChave) {
        listChaves.add(aChave);
    }

    public Iterator getIterator() {
        return listChaves.iterator();
    }

    //retorna o filho desejado
    public No getFilho(int index) {
        return this.listFilhos.get(index);
    }

    //caso o filho mais a esquerda do NÓ for null
    //ja prova que ele é folha!
    public boolean folha() {
        if (this.getFilho(0) == null) {
            return false;
        }
        return true;

    }
}
