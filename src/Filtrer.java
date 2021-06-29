import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Filtrer extends JFrame implements ItemListener,ActionListener,WindowListener {

	JLabel lbl_acc;
	JPanel content;
	JLabel mariage;
	JLabel fian;
	JLabel soiree;
	JLabel mar;
	JLabel fi;
	JLabel soi;
	JPanel Filtrer;
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
	private JPanel rbs;
	private JButton btn_filter;



	public Filtrer(String req) {
	

		this.setTitle("Accueil");
		this.setSize(1000,830);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setBackground(new Color(0xFFf9f3f3));
		this.setLayout(new BorderLayout());
		ImageIcon icone= new ImageIcon("accueil.png");
		lbl_acc=new JLabel(icone);
		this.add(lbl_acc,BorderLayout.NORTH);


		Manager mn=new Manager();


		// tous les robes de mariages

		// tous les robes soirées

		// tous les robes de fiançailles

		
		
		rbs = new JPanel();
		rbs.setLayout(new FlowLayout());
		rbs.setBackground(new Color(0xFFf9f3f3));
		mn.selectionImages(req, rbs);
		retour = new JButton("Retour");
		rbs.add(retour);
		retour.addActionListener(this);


		this.add(rbs,BorderLayout.CENTER);






		//bas de page
		Filtrer = new JPanel();
		Filtrer.setLayout(new FlowLayout());
		JLabel help= new JLabel("Ou vous Pouvez filtrer selon : ");
		Filtrer.add(help);

		//selon type
		JLabel type= new JLabel("Type ");
		Filtrer.add(type);

		combobox1 = new JComboBox<String>();
		combobox1.addItem("all");
		combobox1.setSelectedItem("all");
	//	combobox1.setSelectedItem(typee);
		Filtrer.add(combobox1);
		combobox1.addItemListener(this);
		//combobox1.setSelectedItem(new Color(0xFFe2bcb7));





		//selon couleur
		JLabel couleur= new JLabel("Couleur ");
		Filtrer.add(couleur);

		combobox2 = new JComboBox<String>();
		Filtrer.add(combobox2);
		combobox2.addItemListener(this);
		this.addWindowListener(this);



		//selon prix
		JLabel prix= new JLabel("Prix ");
		Filtrer.add(prix);
		String [] fprix = new String[] {"<100","<200","<300","<500","<1000","<1500","<2000"};
		combobox3 = new JComboBox<String>(fprix);
		Filtrer.add(combobox3);
		combobox3.addItemListener(this);


		
		
		btn_filter=new JButton("filtrer");
		btn_filter.addActionListener(this);
		Filtrer.add(btn_filter);



		
		Filtrer.setBackground(new Color(0xFFe2bcb7));

		this.add(Filtrer,BorderLayout.SOUTH);
		












		this.setVisible(true);	
	}//fin  constructeur
	













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
		combobox2.addItem("all");


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
		

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==retour) {
			this.dispose();
			new Acceuil();


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
		
		

	}


	public static void main(String[] args) {
		
	}

}
