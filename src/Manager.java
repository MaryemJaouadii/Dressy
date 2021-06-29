import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.table.AbstractTableModel;

public class Manager extends AbstractTableModel  {
	Connection con=null;
	Statement st=null;
	JLabel label;
	Acceuil ac;
	private JMenuItem vpd;
	Object[]ligne;
	JLabel l;

	ResultSetMetaData rsmd;

	ArrayList<Object[]> data=new ArrayList<Object[]>();
	static int idd;



	public Manager() {


		/** etape 1 : chargement driver */


		try {
			Class.forName("com.mysql.jdbc.Driver");
			//System.out.println("driver chargé...");
		} catch (ClassNotFoundException e) {
			System.out.println("erreur de chargement driver "+ e.getMessage()); //bch nchoufou l erreur mnin jena bedhabt
		}

		/** etape 2 : connexion et statement */


		String url="jdbc:mysql://127.0.0.1/location"; // mabase hya esm base , mysql hya type l base
		String user="root";
		String mp="";




		try {
			con=DriverManager.getConnection(url, user, mp);

			//System.out.println("connection etablie ...");


			if (con!=null) {
				st=con.createStatement();//hedhy jamais ykoun feha error
			}
		} catch (SQLException e) {

			System.out.println("erreur de cnx: " +e.getMessage());
		}


	} // fin constructeur



	int insererClient(String nom , String prenom , int tel , String adresse, int cin,int iddd,String date,int rest, int acompte) 
	{

		int a=-1;
		/** etape 3 : Requete MAJ */
		try {
			if (st!=null)
			{
				a =st.executeUpdate("insert into client(nom,Prenom,tel,adresse,cin) values ('"+nom+"','"+prenom+"',"+tel+",'"+adresse+"',"+cin+")");
				//System.out.println("ba3d requete l ajout");
				ResultSet resultat = st.getGeneratedKeys();
				if (resultat.next()) {
					int idCl =resultat.getInt(1);
					insererReservation(date, idCl, iddd,rest , acompte);
					//System.out.println("le id du client est "+resultat.getInt(1));



				}

			}
		}
		catch (SQLException e) {
			System.out.println("ma3maltech el ajout");

			System.out.println("erreur d'ajout: " + e.getMessage());
		}
		return a;

	}


	int insererReservation( String datee,int idclt, int idrb,int rest, int acompte) 
	{

		int a=-1;
		/** etape 3 : Requete MAJ */
		try {
			if (st!=null)
			{
				a =st.executeUpdate("insert into reservation (date,idClient,idRobe,reste,acompte) values ('"+datee+"',"+idclt+","+idrb+","+rest+","+acompte+")");

			}
		}
		catch (SQLException e) {

			System.out.println("erreur d'ajout: " + e.getMessage());
		}
		return a;

	}
	
	
	int ajouterRobe( String type,String couleur, String description , boolean voilee, int prix ,FileInputStream input,File file ) 
	{

		int a=-1;
		
		
		try {
			if (st!=null)
			{
				a =st.executeUpdate("insert into robes(type,couleur,description,voilee,prixParJour,image) values"
						+ " ('"+type+"',"+couleur+","+description+","+voilee+","+prix+")");

			}
		}
		catch (SQLException e) {

			System.out.println("erreur d'ajout: " + e.getMessage());
		}
		return a;

	}
	
	
	public int AjouterRobee(String type,String couleur, String description , int prix ,FileInputStream input,File file  ) {
		int b=-1;
		String req="insert into robes(type,couleur,description,prixParJour,image,icone) values(?,?,?,?,?,?)";


		try {
			PreparedStatement ps = con.prepareStatement(req);
			ps.setString(1,type);
			ps.setString(2,couleur);
			ps.setString(3,description);
			ps.setInt(4, prix);
			 ps.setBinaryStream(5,(InputStream)input,(int)file.length());
			 ps.setString(6, "hghgg");
			b=ps.executeUpdate();
			//System.out.println("sart");

		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println("masartch");
		}


		return b;

	}






	ResultSet selection(String req)
	{
		ResultSet rs= null;
		try {
			rs= st.executeQuery(req); 
			//System.out.println("selection effectuée...");

		} catch (SQLException e) {

			System.out.println("erreur de selection "+e.getMessage());
		}

		return rs;
	}
	
	
	
	
/*	ResultSet selectionTous(String req)
	{
		ResultSet rs= null;
		try {
			rs= st.executeQuery(req); 
		
			byte[] imagee = null;

			while (rs.next()) {




				imagee = rs.getBytes("image");

				//créer l'image 
				Image img = Toolkit.getDefaultToolkit().createImage(imagee);
				ImageIcon icone = new ImageIcon(img.getScaledInstance(300, 320, Image.SCALE_DEFAULT));
				JLabel l = new JLabel();
				//	l.setPreferredSize(new Dimension(300,200));
				l.setIcon(icone);

				add(l);

			}

			

		} catch (SQLException e) {

			System.out.println("erreur de selection "+e.getMessage());
		}

		return rs;
	}*/



	ResultSet selectionDate(String req)
	{
		ResultSet rs= null;
		try {
			rs= st.executeQuery(req); 


		} catch (SQLException e) {

			System.out.println("erreur de selection "+e.getMessage());
		}

		return rs;
	}

	int modifiericone(String nouveauicone, int id) {
		int a=-1;
		String req = "UPDATE robes SET icone = '"+nouveauicone+"' WHERE id = "+id+"";
		try {
			PreparedStatement ps = con.prepareStatement(req);
			a = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return a;
	}
	
	
	int modifier_date(String date, int id) {
		int a=-1;
		String req = "UPDATE reservation SET date = '"+date+"' WHERE id = "+id+"";
		try {
			PreparedStatement ps = con.prepareStatement(req);
			a = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return a;
	}
	int modifier_nom(String nom, int idClient) {
		int a=-1;
		String req = "UPDATE client SET nom = '"+nom+"' WHERE id = "+idClient+"";
		try {
			PreparedStatement ps = con.prepareStatement(req);
			a = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return a;
	}
	int modifier_prenom(String prenom, int idClient) {
		int a=-1;
		String req = "UPDATE client SET prenom = '"+prenom+"' WHERE id = "+idClient+"";
		try {
			PreparedStatement ps = con.prepareStatement(req);
			a = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return a;
	}
	
	/*int prixRobe (int id) {
		int prix=0;
		ResultSet rs2=selection("SELECT prixParJour FROM `robes` WHERE id='"+id+"'");
		try {
			while(rs2.next()) {

				prix=Integer.parseInt(rs2.getString(1));
			}
		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		
		return prix;
		
	}*/
	
	
	
	
	
	
	
	int modifier_acompte(int acompte, int id, int idRobe) {
		int a=-1;
		String req = "UPDATE reservation SET acompte = "+acompte+" WHERE id = "+id+"";
		String r3="SELECT prixParJour FROM `robes` WHERE id='"+idRobe+"'";
		try {
			PreparedStatement ps = con.prepareStatement(req);
			a = ps.executeUpdate();
			
			ResultSet rs2=selection("SELECT prixParJour FROM `robes` WHERE id='"+idRobe+"'");
				if(rs2.next()) {

					 int prix = Integer.parseInt(rs2.getString(1));
					 System.out.println("le id du robe est "+idRobe);
					 System.out.println("le prix1 ="+prix);
					int reste=prix-acompte;
					System.out.println("le reste est= "+reste);
					modifier_reste(reste,id);
				}
		//	System.out.println("le prix de la robe est"+prixRobe(idRobe));
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("fama catch");
		}
		
		return a;
		}
		
		/*ResultSet rs=selection("select prixParJour from robes where id='"+idRobe+"'");
		try {
			while (rs.next()) {
				prix=rs.getInt(1);
				System.out.println("le prix1 ="+prix);
				reste=prix-acompte;
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		System.out.println("le prix2 ="+prix);*/
		
		
		
		
	/*	ResultSet rs= null;
		try {
			rs= st.executeQuery("select prixParJour from robes where id='"+idRobe+"'"); 
			
			while (rs.next()) {
				prix=rs.getInt(1);
				System.out.println("le prix1 ="+prix);
				reste=prix-acompte;
			}
		}
			catch (SQLException e) {
				
				e.printStackTrace();
			}*/
		
		
		
		
			
		
		
		

		
		


	
	
	
	
	int modifier_reste(int reste, int id) {
		int a=-1;
		String req = "UPDATE reservation SET reste = "+reste+" WHERE id = "+id+"";
		try {
			PreparedStatement ps = con.prepareStatement(req);
			a = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return a;
	}
	
	
	
	
	/*int modifier_reste(String date, int id) {
		int a=-1;
		String req = "UPDATE reservation SET date = '"+date+"' WHERE id = "+id+"";
		try {
			PreparedStatement ps = con.prepareStatement(req);
			a = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}


		return a;
	}*/
	





	ResultSet selectionImage(String req,JPanel pn)
	{ResultSet rs= null;



	try {
		rs= st.executeQuery(req); 

		byte[] imagee = null;

		while (rs.next()) {




			imagee = rs.getBytes("image");

			//créer l'image 
			Image img = Toolkit.getDefaultToolkit().createImage(imagee);
			ImageIcon icone = new ImageIcon(img.getScaledInstance(300, 320, Image.SCALE_DEFAULT));
			JLabel l = new JLabel();
			//	l.setPreferredSize(new Dimension(300,200));
			l.setIcon(icone);

			pn.add(l);

		}


	} catch (SQLException e) {

		System.out.println("erreur de selection "+e.getMessage());
	}

	return rs;
	}
	
	
	public void supprimerRes(Object id) {
		try {
			st.executeUpdate("delete from reservation where id="+id);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	public void supprimerTous() {
		try {
			st.executeUpdate("delete from reservation ");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	
	
/*	
	public int modifierAc( int acompte) {
		int a=-1;
		String req="update reservation set acompte=?";


		try {
			PreparedStatement ps = con.prepareStatement(req);
			ps.setString(1,nom);
			ps.setString(2,prenom);
			ps.setDouble(3,moyenne);
			ps.setInt(4,numero);
			a=ps.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		}


		return a;

	}*/










	ResultSet selectionImages(String req,JPanel pn)
	{ResultSet rs= null;

	try {
		rs= st.executeQuery(req); 


		byte[] imagee = null;

		while (rs.next()) {


			imagee = rs.getBytes("image");


			//créer l'image 
			Image img = Toolkit.getDefaultToolkit().createImage(imagee);

			ImageIcon icone = new ImageIcon(img.getScaledInstance(120, 150, Image.SCALE_DEFAULT));
			//System.out.println(icone.toString());

			modifiericone(icone.toString(),Integer.parseInt(rs.getString(2)));

			l = new JLabel();
			l.setIcon(icone);
			pn.add(l);




			l.addMouseListener(new MouseListener() {


				@Override
				public void mouseClicked(MouseEvent e) {

					Manager mn= new Manager();

					//System.out.println(((JLabel) e.getSource()).getIcon().toString());
					ResultSet rs3=mn.selection("select id from robes where icone='"+((JLabel) e.getSource()).getIcon().toString()+"'");

					try {
						while(rs3.next()) {

							int idddd = Integer.parseInt(rs3.getString(1));
							new PlusDetails(idddd);
						}
					} catch (SQLException e1) {

						e1.printStackTrace();
					}


				}

				@Override
				public void mousePressed(MouseEvent e) {


				}

				@Override
				public void mouseReleased(MouseEvent e) {


				}

				@Override
				public void mouseEntered(MouseEvent e) {


				}

				@Override
				public void mouseExited(MouseEvent e) {


				}
			});

		}
		//System.out.println("fin boucle while");
		if(imagee==null) {
			JOptionPane.showMessageDialog(null, "Nous Sommes Désolés ! il n'existe pas des robes avec ces conditions");
		}


	} catch (SQLException e) {

		System.out.println("erreur de selection "+e.getMessage());
	}

	return rs;
	}





	@Override
	public int getColumnCount() {

		try {
			return rsmd.getColumnCount();
		} catch (SQLException e) {

			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int getRowCount() {

		return data.size();
	}


	@Override
	public Object getValueAt(int l, int c) {

		return (data.get(l))[c];


	}

	@Override
	public String getColumnName(int c) {

		//return super.getColumnName(column);//talaani abcd 
		try {
			return rsmd.getColumnName(c+1);
		} catch (SQLException e) {
			return null;
		}
	}


	int nomToIndice(String ch) {
		int a=-1;
		for(int i=0; i<getColumnCount();i++) {
			if (getColumnName(i).equals(ch)) return i;
		}
		return a;
	}



	



}












