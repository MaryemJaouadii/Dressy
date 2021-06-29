import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.SwingUtilities;

public class RobesMariages extends JFrame implements ItemListener,ActionListener,WindowListener {

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
	
	 JPanel rms;



	public RobesMariages() {

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

		robes_mariages = new JPanel();
		robes_mariages.setLayout(new FlowLayout());
		robes_mariages.setBackground(new Color(0xFFf9f3f3));
		mn.selectionImages("select image,id from robes where type='mariage'", robes_mariages);
		retour = new JButton("Retour");
		robes_mariages.add(retour);
		retour.addActionListener(this);

		this.add(robes_mariages,BorderLayout.CENTER);






		//bas de page
		filter = new JPanel();
		filter.setLayout(new FlowLayout());
		JLabel help= new JLabel("Ou vous Pouvez filtrer selon : ");
		filter.add(help);

		//selon type
		JLabel type= new JLabel("Type ");
		filter.add(type);

		combobox1 = new JComboBox<String>();
		combobox1.addItem("all");
		combobox1.setSelectedItem("mariage");
		filter.add(combobox1);
		combobox1.addItemListener(this);
		//combobox1.setSelectedItem(new Color(0xFFe2bcb7));





		//selon couleur
		JLabel couleur= new JLabel("Couleur ");
		filter.add(couleur);

		combobox2 = new JComboBox<String>();
		
		combobox2.addItem("all");
		combobox2.setSelectedItem("all");
		filter.add(combobox2);
		combobox2.addItemListener(this);
		this.addWindowListener(this);



		//selon prix
		JLabel prix= new JLabel("Prix ");
		filter.add(prix);
		String [] fprix = new String[] {"<100","<200","<300","<500","<1000","<1500","<2000"};
		combobox3 = new JComboBox<String>(fprix);
		combobox3.addItem("all");
		combobox3.setSelectedItem("all");
		filter.add(combobox3);
		combobox3.addItemListener(this);



		this.add(filter,BorderLayout.SOUTH);
		filter.setBackground(new Color(0xFFe2bcb7));




		rms= new JPanel();
		rms.setBackground(new Color(0xFFf9f3f3));
		
		








		this.setVisible(true);	
	}//fin constructeur










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

	@Override
	public void itemStateChanged(ItemEvent e) {
		
		if((!combobox2.getSelectedItem().toString().equals("blanc")) && (!combobox2.getSelectedItem().toString().equals("all"))) {
			JOptionPane.showMessageDialog(null, "il n'existe que le couleur blanc !");
		}
		
		if((!combobox3.getSelectedItem().toString().equals("<2000"))&&(!combobox3.getSelectedItem().toString().equals("<1500")) && (!combobox3.getSelectedItem().toString().equals("all")) ) {
			JOptionPane.showMessageDialog(null, "il n'existe pas des robes moin cher que 1200 dt !");
		}
		
		
		else if(combobox3.getSelectedItem().toString().equals("<1500")) {
			remove(robes_mariages);
			Manager mn= new Manager();
			//mn.selectionImages("select image from robes where couleur="+combobox2.getSelectedItem().toString()+"and prixParJour"+combobox3.getSelectedItem().toString()+"", robes_mariages);
			mn.selectionImages("SELECT distinct (image) FROM `robes` WHERE type='mariage' and couleur='blanc' and prixParJour<1500", rms);
			this.add(rms,BorderLayout.CENTER);
			SwingUtilities.updateComponentTreeUI(this);
			
			//9a3da taffichi martiiinnnn
	
	}
		
		
		else if(combobox3.getSelectedItem().toString().equals("<2000")) {
				remove(robes_mariages);
				Manager mn= new Manager();
				//mn.selectionImages("select image from robes where couleur="+combobox2.getSelectedItem().toString()+"and prixParJour"+combobox3.getSelectedItem().toString()+"", robes_mariages);
				mn.selectionImages("SELECT distinct (image) FROM `robes` WHERE type='mariage' and couleur='blanc' and prixParJour<2000", rms);
				this.add(rms,BorderLayout.CENTER);
				SwingUtilities.updateComponentTreeUI(this);
				
				//9a3da taffichi martiiinnnn
		
		}
		
		
		
		
		
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==retour) {
			this.dispose();
			new Acceuil();


		}

	}


	public static void main(String[] args) {
		new RobesMariages();
	}
	
}
