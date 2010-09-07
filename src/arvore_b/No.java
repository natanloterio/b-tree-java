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

    public No() {
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

    public void addChave(int aChave){
        listChaves.add(aChave);
    }

    public Iterator getIterator(){
        return listChaves.iterator();
    }

}
