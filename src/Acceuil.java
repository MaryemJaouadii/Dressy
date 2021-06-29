import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class Acceuil extends JFrame implements ItemListener,ActionListener,WindowListener{

	JLabel lbl_acc;
	JPanel content;
	JLabel mariage;
	JLabel fian;
	JLabel soiree;
	JLabel mar;
	JLabel fi;
	JLabel soi;
	JPanel filter;
	JComboBox<String> combobox1;
	JComboBox<String> combobox2;
	JComboBox<String> combobox3;
	JPanel im;
	public JPanel pim;
	JButton vp1,vp2,vp3;
	JPanel pis;
	JPanel pif;
	JPanel robes_mariages;
	JButton retour;
	JPanel robes_fian;
	JPanel robes_soirees;

	JButton btn_filter;
	private JButton reservations;
	private JButton ajouter;
	private JComboBox<String> comboboxvoilee;






	Acceuil() {

		Manager mn=new Manager();


		this.setTitle("Accueil");
		this.setSize(1000,830);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setBackground(new Color(0xFFf9f3f3));
		this.setLayout(new BorderLayout());
		ImageIcon icone = new ImageIcon(new ImageIcon("accueil.png").getImage().getScaledInstance(1000, 200, Image.SCALE_DEFAULT));
		//ImageIcon icone= new ImageIcon("accueil.png");
		lbl_acc=new JLabel(icone);
		this.add(lbl_acc,BorderLayout.NORTH);


		content= new JPanel();
		this.add(content ,BorderLayout.CENTER);
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		mariage = new JLabel("Robes de Mariages :");
		mariage.setPreferredSize(new Dimension(900,40));
		mariage.setForeground(new Color(0xFFca8a8b));
		mariage.setFont(new Font("Pacifico", Font.BOLD, 20));
		//content.add(mariage);


		pim = new JPanel();

		//pim.setPreferredSize(new Dimension(900,200));

		pim.setLayout(new FlowLayout());
		pim.setBackground(new Color(0xFFf9f3f3));

		pim.add(mariage);

		mn.selectionImages("select image,id from robes where type='mariage' and prixParJour=1200", pim);
		mn.selectionImages("select image,id from robes where type='mariage' and prixParJour=1300", pim);
		//mn.selectionImages("select image,id,type,couleur,description,prixParJour from robes where type='mariage' and prixParJour=1700", pim);




		// voir plus
		vp1 = new JButton("voir plus");
		vp1.setOpaque(true);
		vp1.setBackground(new Color(0xFFe2bcb7));
		vp1.setFont(new Font("Arial", Font.BOLD, 10));
		pim.add(vp1);

		vp1.addActionListener(this);



		content.add(pim);



		pis= new JPanel();
		pis.setBackground(new Color(0xFFf9f3f3));

		soiree = new JLabel("Robes Soirées :");
		//soiree.setPreferredSize(new Dimension(900,40));
		soiree.setForeground(new Color(0xFFca8a8b));
		soiree.setFont(new Font("Pacifico", Font.BOLD, 20));
		soiree.setPreferredSize(new Dimension(900,20));
		//content.add(soiree);

		pis.setLayout(new FlowLayout());

		pis.add(soiree);

		//mn.selectionImages("select image,id from robes where type='soirée' and couleur='bleu ciel' and prixParJour=70", pis);
		mn.selectionImages("select image,id from robes where type='soirée' and couleur='violet' ", pis);
		mn.selectionImages("select image,id from robes where type='soirée' and couleur='bleu marine' ", pis);



		vp2 = new JButton("voir plus");
		vp2.setOpaque(true);
		vp2.setBackground(new Color(0xFFe2bcb7));
		vp2.setFont(new Font("Arial", Font.BOLD, 10));


		pis.add(vp2);

		vp2.addActionListener(this);

		content.add(pis);



		//mar = new JLabel("Robes des Mar");
		//content.add(mar);
		fian = new JLabel("Robes de Fiançailles :");
		fian.setPreferredSize(new Dimension(900,40));
		fian.setForeground(new Color(0xFFca8a8b));
		fian.setFont(new Font("Pacifico", Font.BOLD, 20));
		//content.add(fian);

		pif = new JPanel();
		pif.setBackground(new Color(0xFFf9f3f3));
		pif.setLayout(new FlowLayout());

		pif.add(fian);

		mn.selectionImages("select image,id from robes where type='fiançailles' and couleur='rose clair' and prixParJour=90", pif);
		mn.selectionImages("select image,id from robes where type='fiançailles' and couleur='rose caramel'", pif);




		vp3 = new JButton("voir plus");
		vp3.setOpaque(true);
		vp3.setBackground(new Color(0xFFe2bcb7));
		//vp3.setForeground(new Color(0xFFca8a8b));
		vp3.setFont(new Font("Arial", Font.BOLD, 10));

		pif.add(vp3);
		vp3.addActionListener(this) ;






		content.add(pif);



		//soi = new JLabel("Robes Soi");
		//content.add(soi);
		content.setBackground(new Color(0xFFf9f3f3));








		// bas de page :

		filter = new JPanel();
		filter.setLayout(new FlowLayout());
		JLabel help= new JLabel("Filtrer selon : ");
		filter.add(help);

		//selon type
		JLabel type= new JLabel("TYPE ");
		filter.add(type);

		combobox1 = new JComboBox<String>();
		combobox1.addItem("all");

		combobox1.setSelectedItem("all");
		filter.add(combobox1);
		combobox1.addItemListener(this);
		
		
		
		






		//selon couleur
		JLabel couleur= new JLabel("COULEUR ");
		filter.add(couleur);

		combobox2 = new JComboBox<String>();
		combobox2.addItem("all");
		combobox2.setSelectedItem("all");
		filter.add(combobox2);
		combobox2.addItemListener(this);
		this.addWindowListener(this);



		//selon prix
		JLabel prix= new JLabel("PRIX ");
		filter.add(prix);
		String [] fprix = new String[] {"<100","<200","<300","<500","<1000","<1500","<2000"};
		combobox3 = new JComboBox<String>(fprix);
		combobox3.addItem("all");
		combobox3.setSelectedItem("all");
		filter.add(combobox3);
		combobox3.addItemListener(this);


		btn_filter=new JButton("Filtrer");
		btn_filter.setOpaque(true);
		btn_filter.setBackground(new Color(0xFFbdd2b6));
		btn_filter.addActionListener(this);
		filter.add(btn_filter);
		
		
	/*	JLabel sep=new JLabel("                                  ");
		filter.add(sep);*/
		
		reservations= new JButton("Voir toutes les reservations");
		reservations.setOpaque(true);
		reservations.setBackground(new Color(0xFFfff8d9));
		filter.add(reservations);
		reservations.addActionListener(this);

		
		
		
		ajouter= new JButton("Ajouter des Robes");
		ajouter.setOpaque(true);
		ajouter.setBackground(new Color(0xFFfff8d9));
		filter.add(ajouter);
		ajouter.addActionListener(this);

		this.add(filter,BorderLayout.SOUTH);
		filter.setBackground(new Color(0xFFe2bcb7));





		this.setVisible(true);
	} // fin constructeur



	public static void main(String[] args) {

		new Acceuil();


	}



	@Override
	public void itemStateChanged(ItemEvent e) {


		/*	if (combobox1.getSelectedItem().toString().equals("mariage")) {
			this.dispose();
			new RobesMariages();


		}*/




	} 

	//}



	@Override
	public void actionPerformed(ActionEvent e) {




		if (e.getSource()==vp1) {
			
			
			String req="select image,id from robes where type='mariage'";

			new Filtrer(req);
			
			

		}


		if (e.getSource()==vp2) {

			String req="select image,id from robes where type='soirée'";

			new Filtrer(req);
			

		}
		if (e.getSource()==vp3) {
			String req="select image,id from robes where type='fiançailles'";

			new Filtrer(req);
			

		}

		if (e.getSource()==btn_filter) {
			if(combobox1.getSelectedItem().toString().equals("all") && (combobox2.getSelectedItem().toString().equals("all")) && (combobox3.getSelectedItem().toString().equals("all")) ) 
			{
				JOptionPane.showMessageDialog(null, "Veuillez choisir au mois une condition pour bien filtrer. Merci!");
			}
			
			


			// ken couleur w prix kahaw selectionnés
			else if(combobox1.getSelectedItem().toString().equals("all") && !(combobox2.getSelectedItem().toString().equals("all")) && !(combobox3.getSelectedItem().toString().equals("all")) ) 
			{
				String couleur=combobox2.getSelectedItem().toString();
				String prix=combobox3.getSelectedItem().toString();
				String req="select image,id from robes where couleur like '"+couleur+'%'+"' and prixParJour"+prix+"";

				new Filtrer(req);
			}


			//type et couleur
			else if(!(combobox1.getSelectedItem().toString().equals("all")) && !(combobox2.getSelectedItem().toString().equals("all")) && combobox3.getSelectedItem().toString().equals("all") ) 
			{
				String type=combobox1.getSelectedItem().toString();
				String couleur=combobox2.getSelectedItem().toString();

				String req="select image,id from robes where type='"+type+"' and couleur like '"+couleur+'%'+"'";

				new Filtrer(req);
			}

			//type et prix
			else if(!(combobox1.getSelectedItem().toString().equals("all")) && combobox2.getSelectedItem().toString().equals("all") && !(combobox3.getSelectedItem().toString().equals("all")) ) 
			{
				String type=combobox1.getSelectedItem().toString();
				String prix=combobox3.getSelectedItem().toString();

				String req="select image,id from robes where type='"+type+"' and prixParJour"+prix+"";

				new Filtrer(req);
			}


			//couleur kahaw
			else if(combobox1.getSelectedItem().toString().equals("all") && !(combobox2.getSelectedItem().toString().equals("all")) && combobox3.getSelectedItem().toString().equals("all") ) 
			{
				String couleur=combobox2.getSelectedItem().toString();

				String req="select image,id from robes where couleur like '"+couleur+'%'+"'";

				new Filtrer(req);
			}

			//prix
			else if(combobox1.getSelectedItem().toString().equals("all") && combobox2.getSelectedItem().toString().equals("all") && !(combobox3.getSelectedItem().toString().equals("all") ) )
			{
				String prix=combobox3.getSelectedItem().toString();

				String req="select image,id from robes where prixParJour"+prix+"";

				new Filtrer(req);
			}
			
			
			//type
			else if(!(combobox1.getSelectedItem().toString().equals("all")) && combobox2.getSelectedItem().toString().equals("all") && combobox3.getSelectedItem().toString().equals("all")  )
			{
				String type=combobox1.getSelectedItem().toString();

				String req="select image,id from robes where type='"+type+"'";

				new Filtrer(req);
			}
			
			
		

			else 
			{
				String type=combobox1.getSelectedItem().toString();
				String couleur=combobox2.getSelectedItem().toString();
				String prix=combobox3.getSelectedItem().toString();
				
		
				String req="select image,id from robes where type='"+type+"' and couleur like '"+couleur+'%'+"' and prixParJour"+prix+"";
				new Filtrer(req);
				
			}



		}
		
		
		if(e.getSource()==reservations) {
			this.dispose();
			ThreadAff t=new ThreadAff();
			Thread c=new Thread(t);
			c.start();
			//new Reservations();
		}
		
		if(e.getSource()==ajouter) {
			this.dispose();
			new AjouterRobe();
		}
		





	}



	@Override
	public void windowOpened(WindowEvent e) {
		Manager mn= new Manager();
		ResultSet rs=mn.selection("select distinct (couleur) from robes");
		try {
			while(rs.next()) {

				combobox2.addItem(rs.getString(1));
			}
		} catch (SQLException e1) {

			e1.printStackTrace();
		}


		ResultSet rs2=mn.selection("select distinct (type) from robes");
		try {
			while(rs2.next()) {

				combobox1.addItem(rs2.getString(1));
			}
		} catch (SQLException e1) {

			e1.printStackTrace();
		}

	}



	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub

	}



	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}



	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}



	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}



	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}



	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}


}