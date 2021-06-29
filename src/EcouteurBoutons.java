import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class EcouteurBoutons implements ActionListener {
	
	Acceuil ac;
	
	public EcouteurBoutons(Acceuil ac) {
		this.ac=ac;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		

		if (e.getSource()==ac.vp1) {
			
			ac.remove(ac.content);
			ac.add(ac.robes_mariages,BorderLayout.CENTER);
			
			
					}
		else	if (e.getSource()==ac.vp2) {
				ac.add(ac.robes_soirees,BorderLayout.CENTER);
			
		}
		else	if (e.getSource()==ac.vp3) {
				ac.add(ac.robes_fian,BorderLayout.CENTER);
			
		}
			if(e.getSource()==ac.retour) {
				ac.add(ac.content,BorderLayout.CENTER);
			}
		
		

	}

}
