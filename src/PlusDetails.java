import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class PlusDetails extends JFrame implements ActionListener {

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


	//type,couleur,description,prixParJour
	//public PlusDetails(String id,String typee,String Couleuur, String description,String prixx) 
	static int idd;
	public PlusDetails(int id) 
	{
		this.idd=id;

		this.setTitle("Accueil");
		this.setSize(1000,830);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setBackground(new Color(0xFFf9f3f3));
		this.setLayout(new BorderLayout());
		
		
		//tete de page:
		
		ImageIcon icone= new ImageIcon("accueil.png");
		lbl_acc=new JLabel(icone);
		this.add(lbl_acc,BorderLayout.NORTH);


		Manager mn=new Manager();



		// contenu milieu de page:

		content= new JPanel();
		content.setLayout(new FlowLayout());
		content.setBackground(new Color(0xFFf9f3f3));

		
		
		
		
		
		mn.selectionImage("select image from robes where id='"+id+"'", content);
		
		
		
	
		
		this.add(content,BorderLayout.CENTER);
		
		
		infos = new JPanel();
		infos.setBackground(new Color(0xFFf9f3f3));
		infos.setLayout(new BoxLayout(infos, BoxLayout.Y_AXIS));
		
		
	
		ResultSet rs2=mn.selection("select type,couleur,description,prixParJour from robes where id='"+id+"'");
		
		try {
			ResultSetMetaData rsmd=rs2.getMetaData();
			int i=0;
			while(rs2.next()) {
				
				JLabel ltype=new JLabel(rsmd.getColumnName(1));
				ltype.setFont(new Font("pacifico", Font.BOLD, 25));
				ltype.setForeground(new Color(0xFFca8a8b));
				infos.add(ltype);
				JLabel lltype =new JLabel(rs2.getString(1));
				lltype.setFont(new Font("pacifico", Font.BOLD, 20));
				infos.add(lltype);
				
				
				
				JLabel lcouleur=new JLabel(rsmd.getColumnName(2));
				lcouleur.setFont(new Font("pacifico", Font.BOLD, 25));
				lcouleur.setForeground(new Color(0xFFca8a8b));
				infos.add(lcouleur);
				JLabel llcouleur =new JLabel(rs2.getString(2));
				llcouleur.setFont(new Font("pacifico", Font.BOLD, 20));
				infos.add(llcouleur);
				
				
				
				JLabel ldescription=new JLabel(rsmd.getColumnName(3));
				infos.add(ldescription);
				ldescription.setFont(new Font("pacifico", Font.BOLD, 25));
				ldescription.setForeground(new Color(0xFFca8a8b));
				JLabel lldescription =new JLabel(rs2.getString(3));
				lldescription.setFont(new Font("pacifico", Font.BOLD, 20));
				infos.add(lldescription);
				
				
				
				JLabel lprix=new JLabel(rsmd.getColumnName(4));
				infos.add(lprix);
				lprix.setFont(new Font("pacifico", Font.BOLD, 25));
				lprix.setForeground(new Color(0xFFca8a8b));
				JLabel llprix =new JLabel(rs2.getString(4));
				llprix.setFont(new Font("pacifico", Font.BOLD, 20));
				infos.add(llprix);
			}
			
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		
		
		
		
		
		content.add(infos);
		
		
		
		
		
		
		// pieds de page
		
		footer = new JPanel();
		footer.setBackground(new Color(0xFFf9f3f3));
		this.add(footer,BorderLayout.SOUTH);
		
		
		
		reserver = new JButton("Reserver cette robe");
		//reserver.setForeground(new Color(0xFFf9f3f3));
		reserver.setOpaque(true);
		reserver.setBackground(new Color(0xFFbdd2b6));
		
		retour = new JButton("Retour à la page d'Accueil");
		retour.setOpaque(true);
		retour.setBackground(new Color(0xFFe2bcb7));
		
		
		footer.add(reserver);
		footer.add(retour);
		retour.addActionListener(this);
		reserver.addActionListener(this);
		
		
		
		
		
		//System.out.println("ena tawa fi plus details"+id);
		

		/*JLabel ttype= new JLabel();
		ttype.setText(typee);
		ttype.setPreferredSize(new Dimension(800,20));



		JLabel lcouleur= new JLabel("Type :");
		lcouleur.setPreferredSize(new Dimension(800,20));

		JLabel tcouleur= new JLabel();
		tcouleur.setText(Couleuur);
		tcouleur.setPreferredSize(new Dimension(800,20));



		JLabel ldes= new JLabel("Type :");
		ldes.setPreferredSize(new Dimension(800,20));

		JLabel tdes= new JLabel();
		tdes.setText(description);
		tdes.setPreferredSize(new Dimension(800,20));


		JLabel lprix= new JLabel("Type :");
		lprix.setPreferredSize(new Dimension(800,20));

		JLabel tprix= new JLabel();
		tprix.setText(prixx);
		tprix.setPreferredSize(new Dimension(800,20));*/


		this.setVisible(true);	
	}//fin constructeur






	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==retour) {
			this.dispose();
			new Acceuil();
			
		}
		
		if(e.getSource()==reserver) {
			this.dispose();
			new Reserver(idd);
			
		}
		
		
	
		
		

	}


	public static void main(String[] args) {
		//new PlusDetails(idd);
		//System.out.println("idd = "+idd);
	}

}
