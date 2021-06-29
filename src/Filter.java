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
import javax.swing.JPanel;

public class Filter extends JFrame implements ItemListener,ActionListener,WindowListener {

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
	private JPanel ancien;
	private JPanel rbs;



	public Filter(String typee,String couleuur,String prixx) {
	

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

		System.out.println(typee);
		System.out.println(couleuur);
		System.out.println(prixx);
		rbs = new JPanel();
		rbs.setLayout(new FlowLayout());
		rbs.setBackground(new Color(0xFFf9f3f3));
		mn.selectionImages("select image,id from robes where type='"+typee+"' and couleur='"+couleuur+"' and prixParJour"+prixx+"  ", rbs);
		retour = new JButton("Retour");
		rbs.add(retour);
		retour.addActionListener(this);


		this.add(rbs,BorderLayout.CENTER);






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
		combobox1.setSelectedItem("all");
		combobox1.setSelectedItem(typee);
		filter.add(combobox1);
		combobox1.addItemListener(this);
		//combobox1.setSelectedItem(new Color(0xFFe2bcb7));





		//selon couleur
		JLabel couleur= new JLabel("Couleur ");
		filter.add(couleur);

		combobox2 = new JComboBox<String>();
		filter.add(combobox2);
		combobox2.addItemListener(this);
		this.addWindowListener(this);



		//selon prix
		JLabel prix= new JLabel("Prix ");
		filter.add(prix);
		String [] fprix = new String[] {"<100","<200","<300","<500","<1000","<1500","<2000"};
		combobox3 = new JComboBox<String>(fprix);
		filter.add(combobox3);
		combobox3.addItemListener(this);



		this.add(filter,BorderLayout.SOUTH);
		filter.setBackground(new Color(0xFFe2bcb7));












		this.setVisible(true);	
	}//fin 1er constructeur lkol mouch all
	
public Filter(String couleuur,String prixx) {
	
}//ken couleur w prix

public Filter(String typee) {
	
} // ken 












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
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==retour) {
			this.dispose();
			new Acceuil();


		}

	}


	public static void main(String[] args) {
		
	}

}
