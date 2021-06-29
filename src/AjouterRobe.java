import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;



public class AjouterRobe extends JFrame implements ActionListener {


	private JLabel lbl_acc;
	private JPanel content;
	private JTextField tf_type;
	private JRadioButton rb_oui;
	private JRadioButton rb_non;
	private JTextField tf_couleur;
	private JTextField tf_description;
	private JTextField tf_prix;
	private JButton btn_choisirImage;
	private JButton btn_ajouter;
	private ButtonGroup gr;
	JComboBox<String> comboboxdesc;
	JComboBox<String> comboboxtype;
	
	private JPanel center2;
	private JPanel center;
	private JPanel p;
	private JPanel pv;
	private JButton retourner;
	static File selectedFile;
	static FileInputStream input;

	public AjouterRobe() {
		this.setTitle("Accueil");

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1000,830);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setBackground(new Color(0xFFf9f3f3));
		this.setLayout(new BorderLayout());


		//tete de page:

		ImageIcon icone = new ImageIcon(new ImageIcon("accueil.png").getImage().getScaledInstance(1000, 250, Image.SCALE_DEFAULT));
		//ImageIcon icone= new ImageIcon("accueil.png");
		lbl_acc=new JLabel(icone);
		this.add(lbl_acc,BorderLayout.NORTH);



		//content:
		content = new JPanel();
		content.setBackground(new Color(0xFFf9f3f3));
		//content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		content.setLayout(new BorderLayout());

		JLabel remp = new JLabel("Ajouter une robe");
		remp.setFont(new Font("pacifico", Font.BOLD, 40));
		remp.setForeground(new Color(0xFFca8a8b));
		content.add(remp,BorderLayout.NORTH);
		
		
		



		JLabel ltype = new JLabel("Type : ");
		ltype.setFont(new Font("pacifico", Font.BOLD, 25));



		//tf_type=new JTextField(10);
		
		Manager mn= new Manager();
		comboboxtype = new JComboBox<String>();
		ResultSet rs=mn.selection("select distinct (type) from robes");
		try {
			while(rs.next()) {

				comboboxtype.addItem(rs.getString(1));
			}
		} catch (SQLException e1) {

			e1.printStackTrace();
		}


	



		JLabel lcouleur = new JLabel("Couleur : ");
		lcouleur.setFont(new Font("pacifico", Font.BOLD, 25));


		tf_couleur=new JTextField(10);


		JLabel ldescription = new JLabel("Description : ");
		ldescription.setFont(new Font("pacifico", Font.BOLD, 25));
		String [] desc = new String[] {"longue","courte"};
		comboboxdesc = new JComboBox<String>(desc);
		//tf_description=new JTextField(15);


		JLabel lprix = new JLabel("Prix Par Jour : ");
		lprix.setFont(new Font("pacifico", Font.BOLD, 25));
		tf_prix=new JTextField(8);


		/*JLabel limage = new JLabel("Ajouter une image : ");
		limage.setFont(new Font("pacifico", Font.BOLD, 15));
		tf_lacompte=new JTextField(8);*/

		btn_choisirImage=new JButton("Ajouter une image");
		btn_choisirImage.setOpaque(true);
		btn_choisirImage.setBackground(new Color(0xFFfff8d9));
		
		 pv=new JPanel();
		pv.setBackground(new Color(0xFFf9f3f3));
		
		btn_ajouter = new JButton("Valider");
		btn_ajouter.setOpaque(true);
		btn_ajouter.setBackground(new Color(0xFFbdd2b6));
		pv.add(btn_ajouter);
		btn_ajouter.addActionListener(this);



		 p=new JPanel();
		p.setLayout(new BoxLayout(p,BoxLayout.Y_AXIS));
		 //p.setLayout(new FlowLayout());
		p.setBackground(new Color(0xFFf9f3f3));
		

		/*//radio button
		rb_oui=new JRadioButton("Oui");
		rb_oui.setBackground(new Color(0xFFf9f3f3));
		rb_non=new JRadioButton("Non");
		rb_non.setBackground(new Color(0xFFf9f3f3));

		p.add(rb_oui);
		p.add(rb_non); // ama haka najm nselectionnehom e zouz donc lezm nhothom f grp
		 gr=new ButtonGroup();
		gr.add(rb_oui);
		gr.add(rb_non);*/
		
		
		
		

		
	
		
		p.add(ltype);
		p.add(comboboxtype);

		p.add(lcouleur);
		p.add(tf_couleur);

		p.add(ldescription);
		p.add(comboboxdesc);
		p.add(lprix);
		p.add(tf_prix);


		p.add(btn_choisirImage);
		btn_choisirImage.addActionListener(this);

			

		
	//	p.add(btn_ajouter);
		

		center = new JPanel();
		center.setBackground(new Color(0xFFf9f3f3));

		center.add(p);
		content.add(center,BorderLayout.CENTER);
		
		this.add(pv,BorderLayout.SOUTH);


		this.add(content,BorderLayout.CENTER);



		
		
		
		
		//center 2 :
				center2 = new JPanel();
				center2.setLayout(new FlowLayout());
				center2.setBackground(new Color(0xFFf9f3f3));
				JLabel bye= new JLabel("Ajout de la robe effectué avec succés !");
				bye.setFont(new Font("pacifico", Font.BOLD, 25));
				bye.setForeground(new Color(0xFFca8a8b));
				
				
				retourner = new JButton("Retour à la page d'Accueil");
				retourner.setOpaque(true);
				retourner.setBackground(new Color(0xFFfff8d9));
				retourner.addActionListener(this);
			
				
				
				center2.add(bye);
				center2.add(retourner);



		this.setVisible(true);

	} // fin constructeur




	public static void main(String[] args) {
		new AjouterRobe();
	}




	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==btn_choisirImage) {
			JFileChooser f=new JFileChooser();
			f.showOpenDialog(AjouterRobe.this);
			selectedFile = f.getSelectedFile();
			try {
				 input = new FileInputStream(selectedFile);
			} catch (FileNotFoundException e1) {
				
				e1.printStackTrace();
			}
		}
		
	//	String type,String couleur, String description , boolean voilee, int prix ,FileInputStream input,File file 
		
		if (e.getSource()==btn_ajouter) {
			Manager mn=new Manager();
			mn.AjouterRobee(comboboxtype.getSelectedItem().toString(), tf_couleur.getText().toString(), comboboxdesc.getSelectedItem().toString(), Integer.parseInt(tf_prix.getText().toString()), input,selectedFile);
			this.remove(content);
			this.add(center2);
			this.remove(pv);
			SwingUtilities.updateComponentTreeUI(this);
		}
		
		
		if(e.getSource()==retourner) {
			this.dispose();
			new Acceuil();
		}
		
	}


}
