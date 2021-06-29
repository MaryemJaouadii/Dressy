import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ThreadAff extends JFrame implements Runnable,ActionListener{

	JTable jt;


	JButton retour;


	JLabel lbl_acc;


	ResModel model;


	private JPanel pr;
	@Override
	public void run() {








		while(true) {



			this.setTitle("Tous les Reservations");
			this.setSize(1000,830);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.getContentPane().setBackground(new Color(0xFFf9f3f3));
			this.setLayout(new BorderLayout());
			ImageIcon icone= new ImageIcon("accueil.png");
			lbl_acc=new JLabel(icone);
			this.add(lbl_acc,BorderLayout.NORTH);
			pr = new JPanel();
			retour = new JButton("Retour à la page d'accueil");
			retour.setOpaque(true);
			retour.setBackground(new Color(0xFFfff8d9));
			retour.addActionListener(this);
			pr.add(retour);
			this.add(pr,BorderLayout.SOUTH);
			


			Manager mn=new Manager();



			String req="select ro.image,r.id,r.idRobe,r.idClient,r.date,c.nom,c.prenom,r.acompte,r.reste from reservation r,client c,robes ro where r.idRobe=ro.id and r.idClient=c.id and ro.image=(select image from robes where id=r.idRobe)";

			ResultSet rs=mn.selection(req);


			model= new ResModel(rs); // el suppr wl modification wkol chy bch ysir fl objet model, el model kima dapter fl android

			jt=new JTable(model);// elle juste permet d'afficher les données, objet graphique, el suppr wl modification wkol chy bch ysir fl objet model
			this.add(new JScrollPane(jt),BorderLayout.CENTER);
			jt.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getButton()==e.BUTTON3)
					{
						JPopupMenu pop =new JPopupMenu();
						JMenuItem sup= new JMenuItem("supprimer");
						JMenuItem suptt= new JMenuItem("supprimer tous");
						pop.add(sup);
						pop.add(suptt);
						pop.show(jt,e.getX(),e.getY());
						sup.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								if (jt.getSelectedRow()>=0)
									model.supprimerligne(jt.getSelectedRow());

							}
						});
						
						suptt.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								if (jt.getSelectedRow()>=0)
									model.supprimertous();

							}
						});
						
					}
				}

			});












		
			

			this.setVisible(true);	
			
			
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}






		}
	}






	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==retour) {
			this.dispose();
			new Acceuil();

		}






























	}

}
