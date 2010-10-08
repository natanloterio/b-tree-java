package janela;
import arvore_b.*;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class PainelInicial extends JPanel
{
        private JLabel lOrdem;
	private JButton bInsere;
	private JButton bRemove;
	private JButton bInsereN;
        private JButton bBusca;
	private JButton bAlteraOrdem;
        private JButton bLimpeza;
	private JTextField tCampo;
	private Arvore mytree;
	//private JTextField tf;
	public PainelInicial(JLabel ordem,JTextField texto,JButton bI,JButton bR,JButton bIN,JButton bB,JButton bAO,JButton bL){
		lOrdem = ordem;
                tCampo = texto;
		bInsere = bI;
		bRemove = bR;
		bInsereN = bIN;
                bBusca = bB;
		bAlteraOrdem  =bAO;
                bLimpeza = bL;
		mytree = new Arvore(5);
                this.add(lOrdem);
		this.add(tCampo);
		this.add(bInsere);
		this.add(bRemove);
		this.add(bInsereN);
                this.add(bBusca);
                this.add(bLimpeza);
		this.add(bAlteraOrdem);

	}


}
