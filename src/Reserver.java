import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;




public class Reserver extends JFrame implements ActionListener {

	JLabel lbl_acc;

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
	private JPanel ancien;
	private JPanel robe;
	private JPanel content;
	private JButton reserver;
	private JPanel footer;

	private JPanel infos;

	private JPanel first;

	private JButton choisir;

	String final_date;

	JComboBox<Integer> annee;
	JComboBox<String> jour, mois;
	String tab[];
	JPanel center;
	
	int reste;
	int prix;



	//type,couleur,description,prixParJour
	//public Reserver(String id,String typee,String Couleuur, String description,String prixx) 
	static int idd;

	private JTextField tf_nom;

	private JTextField tf_prenom;

	private JTextField tf_adresse;

	private JTextField tf_tel;

	private JTextField tf_cin;

	private JTextField tf_lacompte;

	private JPanel center2;

	private JPanel footer2;

	private JButton voirTous;

	private JButton btn_resev;

	private JPanel premier;

	private JButton annuler;

	private JButton retourner;

	private JButton ajouter;
	public Reserver(int id) 
	{
		this.idd=id;

		this.setTitle("Accueil");
		this.setSize(1000,830);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setBackground(new Color(0xFFf9f3f3));
		this.setLayout(new BorderLayout());


		//tete de page:

		ImageIcon icone = new ImageIcon(new ImageIcon("accueil.png").getImage().getScaledInstance(1000, 200, Image.SCALE_DEFAULT));
		//ImageIcon icone= new ImageIcon("accueil.png");
		lbl_acc=new JLabel(icone);
		this.add(lbl_acc,BorderLayout.NORTH);




		Manager mn=new Manager();



		// contenu milieu de page:

		center = new JPanel();
		center.setBackground(new Color(0xFFf9f3f3));
		this.add(center,BorderLayout.CENTER);
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));



		JLabel lchoisir=new JLabel("Veuillez choisir une date de reservation");
		lchoisir.setFont(new Font("pacifico", Font.BOLD, 25));
		lchoisir.setForeground(new Color(0xFFca8a8b));
		center.add(lchoisir);

		JPanel pnl_date = new JPanel();
		pnl_date.setBackground(new Color(0xFFf9f3f3));
		pnl_date.setLayout(new FlowLayout());


		//jour
		JLabel ljour = new JLabel("Jour : ");
		ljour.setFont(new Font("pacifico", Font.BOLD, 15));
		pnl_date.add(ljour);
		String []jours= new String[31];
		jours[0]="01";
		jours[1]="02";
		jours[2]="03";
		jours[3]="04";
		jours[4]="05";
		jours[5]="06";
		jours[6]="07";
		jours[7]="08";
		jours[8]="09";
		jours[9]="10";
		jours[10]="11";
		jours[11]="12";
		jours[12]="13";
		jours[13]="14";
		jours[14]="15";
		jours[15]="16";
		jours[16]="17";
		jours[17]="18";
		jours[18]="19";
		jours[19]="20";
		jours[20]="21";
		jours[21]="22";
		jours[22]="23";
		jours[23]="24";
		jours[24]="25";
		jours[25]="26";
		jours[26]="27";
		jours[27]="28";
		jours[28]="29";
		jours[29]="30";
		jours[30]="31";
		
		
		
		jour =new JComboBox(jours);
		pnl_date.add(jour);


		//mois
		JLabel lmois = new JLabel("Mois : ");
		lmois.setFont(new Font("pacifico", Font.BOLD, 15));
		pnl_date.add(lmois);
		String []month= new String[12];
		
		month[0]="01";
		month[1]="02";
		month[2]="03";
		month[3]="04";
		month[4]="05";
		month[5]="06";
		month[6]="07";
		month[7]="08";
		month[8]="09";
		month[9]="10";
		month[10]="11";
		month[11]="12";
		
		mois =new JComboBox(month);
		pnl_date.add(mois);


		//année
		JLabel lannee = new JLabel("Année : ");
		lannee.setFont(new Font("pacifico", Font.BOLD, 15));
		pnl_date.add(lannee);
		Integer []annees= new Integer[4];
		int j=0;
		for (int i =2021;i<2025;i++)
		{annees[j]=i;
		j++;}

		annee =new JComboBox(annees);
		pnl_date.add(annee);

		JLabel sep = new JLabel("     ");
		pnl_date.add(sep);



		choisir = new JButton("choisir");
		choisir.setOpaque(true);
		choisir.setBackground(new Color(0xFFbdd2b6));
		pnl_date.add(choisir);

		choisir.addActionListener(this);


		center.add(pnl_date);
		
		
		
		
		
		//content:
		content = new JPanel();
		content.setBackground(new Color(0xFFf9f3f3));
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		
		JLabel remp = new JLabel("Veuillez remplir ces champs");
		remp.setFont(new Font("pacifico", Font.BOLD, 25));
		remp.setForeground(new Color(0xFFca8a8b));
		content.add(remp);
		
		
		
		JLabel lnom = new JLabel("Nom : ");
		lnom.setFont(new Font("pacifico", Font.BOLD, 15));
		tf_nom=new JTextField(10);
		
		JLabel lprenom = new JLabel("Prenom : ");
		lprenom.setFont(new Font("pacifico", Font.BOLD, 15));
		tf_prenom=new JTextField(15);
		
		JLabel ltel = new JLabel("Telephone : ");
		ltel.setFont(new Font("pacifico", Font.BOLD, 15));
		tf_tel=new JTextField(8);
		
		JLabel ladresse = new JLabel("Adresse : ");
		ladresse.setFont(new Font("pacifico", Font.BOLD, 15));
		tf_adresse=new JTextField(15);
		
		JLabel lcin = new JLabel("CIN : ");
		lcin.setFont(new Font("pacifico", Font.BOLD, 15));
		tf_cin=new JTextField(8);
		
		JPanel pacompte = new JPanel();
		pacompte.setBackground(new Color(0xFFf9f3f3));
		pacompte.setLayout(new FlowLayout());
		
		JLabel lacompte = new JLabel("L'Acompte : ");
		lacompte.setFont(new Font("pacifico", Font.BOLD, 15));
		tf_lacompte=new JTextField(8);
	
		
		JPanel p=new JPanel();
		p.setLayout(new FlowLayout());
		p.setBackground(new Color(0xFFf9f3f3));
		p.add(lnom);
		p.add(tf_nom);
		p.add(lprenom);
		p.add(tf_prenom);
		p.add(ltel);
		p.add(tf_tel);
		p.add(ladresse);
		p.add(tf_adresse);
		p.add(lcin);
		p.add(tf_cin);
		
		content.add(p);
		pacompte.add(lacompte);
		pacompte.add(tf_lacompte);
		content.add(pacompte);
		
		
	
	
		


		
		
		
		//center 2 :
		center2 = new JPanel();
		center2.setBackground(new Color(0xFFf9f3f3));
		JLabel bye= new JLabel("Reservation effectuée avec succés !");
		bye.setFont(new Font("pacifico", Font.BOLD, 25));
		bye.setForeground(new Color(0xFFca8a8b));
	
		
	//	btn_resev.addActionListener(this);
		
		center2.add(bye);
		
		
		//footer2:
		
		footer2 = new JPanel();
		footer2.setLayout(new FlowLayout());
		footer2.setBackground(new Color(0xFFf9f3f3));
		voirTous = new JButton("Voir toutes les reservations");
		voirTous.setOpaque(true);
		voirTous.setBackground(new Color(0xFFbdd2b6));
		voirTous.addActionListener(this);
		
		ajouter= new JButton("Ajouter des Robes");
		ajouter.setOpaque(true);
		ajouter.setBackground(new Color(0xFFfff8d9));
		ajouter.addActionListener(this);
		footer2.add(ajouter);
		
		
		
		
		retourner = new JButton("Retour à la page d'Accueil");
		retourner.setOpaque(true);
		retourner.setBackground(new Color(0xFFfff8d9));
		retourner.addActionListener(this);
		
		
		footer2.add(voirTous);
		footer2.add(retourner);















		// pieds de page

		footer = new JPanel();
		footer.setBackground(new Color(0xFFf9f3f3));
		//this.add(footer,BorderLayout.SOUTH);



		reserver = new JButton("Effectuer la reservation");
		//reserver.setForeground(new Color(0xFFf9f3f3));
		reserver.setOpaque(true);
		reserver.setBackground(new Color(0xFFbdd2b6));

		annuler = new JButton("Annuler la reservation");
		annuler.setOpaque(true);
		annuler.setBackground(new Color(0xFFe2bcb7));


		footer.add(reserver);
		footer.add(annuler);
		annuler.addActionListener(this);
		reserver.addActionListener(this);


		//panel au premier:
		premier=new JPanel();
		premier.setBackground(new Color(0xFFf9f3f3));
		premier.add(retourner);
		this.add(premier,BorderLayout.SOUTH);




		this.setVisible(true);	
	}//fin constructeur






	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==annuler) {
			this.dispose();
			new Acceuil();
		}
		
		if(e.getSource()==retourner) {
			this.dispose();
			new Acceuil();
		}
		
		if (e.getSource()==ajouter) {
			this.dispose();
			new AjouterRobe();
		}

		


		if (e.getSource()==choisir) {



			String fjour=jour.getSelectedItem().toString();
			String fmois=mois.getSelectedItem().toString();
			String fannee=annee.getSelectedItem().toString();

			final_date=fannee+"-"+fmois+"-"+fjour;
			System.out.println("el final date "+final_date);
			this.add(premier,BorderLayout.SOUTH);
			
			if (exist(final_date)) {
				JOptionPane.showMessageDialog(null, "Désolé cette date est reservé, veuillez choisir une autre date");
				this.dispose();
				new Reserver(idd);
				
			}
			else {this.remove(premier);
				center.add(content);
				this.add(footer,BorderLayout.SOUTH);
				SwingUtilities.updateComponentTreeUI(this);
				
			}
		
			
		}
		if (e.getSource()==reserver) {
			
			String nom=tf_nom.getText();
			String prenom=tf_prenom.getText();
			int tel=Integer.parseInt(tf_tel.getText());
			String adresse=tf_adresse.getText();
			int cin=Integer.parseInt(tf_cin.getText());
			int acompte=Integer.parseInt(tf_lacompte.getText());
			


			Manager mn = new Manager();
			
			
			ResultSet rs2=mn.selection("select prixParJour from robes where id='"+idd+"'");
			
			try {
				while(rs2.next()) {

					prix=Integer.parseInt(rs2.getString(1));
					reste=prix-acompte;
					System.out.println("le reste est "+reste);
				}
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
			
			
			
			
			
			
			mn.insererClient(nom,prenom ,tel, adresse, cin, idd,final_date,reste, acompte);
			//mn.insererReservation(idd, acompte);
			
			this.remove(center);
			this.remove(footer);
			this.add(center2,BorderLayout.CENTER);
			this.add(footer2,BorderLayout.SOUTH);
			
			
			SwingUtilities.updateComponentTreeUI(this);
			
			
		}
		
		if(e.getSource()==voirTous) {
			this.dispose();
			ThreadAff t=new ThreadAff();
			Thread c=new Thread(t);
			c.start();
		}


		




	}
	
	boolean exist(String date) {
		Manager pm = new Manager();
		ResultSet rs = pm.selection("select date from reservation where idRobe='"+idd+"'");
		try {
			while(rs.next()) {
				System.out.println(rs.getString(1));
				if(date.equals(rs.getString(1))) {
					return true;
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return false;
}

	
	





public static void main(String[] args) {
	new Reserver(idd);
}



}
