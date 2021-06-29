import java.awt.Component;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

public class ResModel extends AbstractTableModel{


	ResultSetMetaData rsmd;
	Reservations resev;

	ArrayList<Object[]> data=new ArrayList<Object[]>(); //data howa ensble de ligne wkol lignef eha des colonnes
	Manager mn=new Manager();

	private BufferedInputStream is2;

	ResModel(ResultSet rs) {
		//this.resev=resev;
		byte[] imagee = null;

		try {
			rsmd=rs.getMetaData();// feha qq methodes kima columcount wcolumname...
			while(rs.next()) {//rs.next tparcouri les lignes
				
				
				
				
				
				
				
				imagee = rs.getBytes("image");

				//créer l'image 
				Image img = Toolkit.getDefaultToolkit().createImage(imagee);
				ImageIcon icone = new ImageIcon(img.getScaledInstance(100, 100, Image.SCALE_DEFAULT));
				JLabel l = new JLabel();
				//	l.setPreferredSize(new Dimension(300,200));
				l.setIcon(icone);
				System.out.println("icone ="+icone);
				
				
				
				
			    /*    Blob blob = rs.getBlob(1);
			        is2 = new BufferedInputStream(blob.getBinaryStream());
			        Image raw = null;
					try {
						raw = ImageIO.read(is2);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        ImageIcon icon  =new ImageIcon(raw);*/

				
		/*		 // Get image from database
	              InputStream GetImageFromDB = rs.getBinaryStream("Image");
	             // Create JLabel Without text
	               JLabel image = new JLabel("");
	           // Set label size
	               image.setBounds(5, 91, 440, 41);
	             // Convert InputStream to BufferedImage
	               BufferedImage im = null;
				try {
					im = ImageIO.read(GetImageFromDB);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
	               // Scale the image
	               Image scaledImage = im.getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);
	              //Convert scaled image to ImageIcon
	               ImageIcon icon = new ImageIcon(scaledImage);
	       // Set label icon
	               image.setIcon(icon);
	    // Revalidate to label
	               image.revalidate();*/
				
				
				

				Object[]ligne=new Object[rsmd.getColumnCount()];
				for (int i=2 ; i<=rsmd.getColumnCount() ; i++) {
					ligne[0]=icone;
					ligne[i-1]=rs.getObject(i);
				/*	
					MyRenderer renderer = new MyRenderer();

					renderer.getTableCellRendererComponent(resev.jt, icone);

					resev.jt.getColumnModel().getColumn(0).setCellRenderer(renderer);*/
				
				/*Object[]ligne=new Object[rsmd.getColumnCount()];
				for (int i=1 ; i<=rsmd.getColumnCount() ; i++) {
					ligne[i-1]=rs.getObject(i);*/
				
					
					
					
					 
					
				}


				
				data.add(ligne);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		
		
	} // fin constructeur

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
	
	
	@Override
	public boolean isCellEditable(int l, int c) {
		
		if (getColumnName(c).equals("date") || getColumnName(c).equals("nom") || getColumnName(c).equals("prenom") || getColumnName(c).equals("acompte")) return true; 
		else return false;
	}
	
	
	
	
	@Override
	public void setValueAt(Object val, int l, int c) {
		data.get(l)[c]=val;
		//mise a jour de la base de donnée
		Object[] p=data.get(l);
		mn.modifier_date( p[nomToIndice("date")]+"",Integer.parseInt(p[nomToIndice("id")]+""));
		mn.modifier_nom( p[nomToIndice("nom")]+"",Integer.parseInt(p[nomToIndice("idClient")]+""));
		mn.modifier_prenom( p[nomToIndice("prenom")]+"",Integer.parseInt(p[nomToIndice("idClient")]+""));
		mn.modifier_acompte(Integer.parseInt(p[nomToIndice("acompte")]+""),Integer.parseInt(p[nomToIndice("id")]+""),Integer.parseInt(p[nomToIndice("idRobe")]+""));
		//mn.modifier_reste( p[nomToIndice("date")]+"",Integer.parseInt(p[nomToIndice("id")]+""));
		//resev.jt.repaint();
	}
	
	
	
	
/*	@Override
	public void setValueAt(Object val, int l, int c) {
	
		data.get(l)[c]=val;
		int a=mn.modifierRes(
				Integer.parseInt(""+data.get(l)[nomToIndice("id")]),
				""+data.get(l)[nomToIndice("allahou a3lem")],
				""+data.get(l)[nomToIndice("Prenom")],
				Double.parseDouble(""+data.get(l)[nomToIndice("Moyenne")]));
				if (a>0)
				{
					JOptionPane.showMessageDialog(null, "Modification avec succes!");
				}
				else {
					JOptionPane.showMessageDialog(null, "Modification non effectuée");
				}
			
	}*/



	public void supprimerligne(int selectedRow) {
		mn.supprimerRes(data.get(selectedRow)[nomToIndice("id")]);
		data.remove(selectedRow);
		
	}
	
	
	public void supprimertous() {
		mn.supprimerTous();
		data.removeAll(data);
	}
	
	
	/*public class IconCellRenderer extends DefaultTableCellRenderer {
	    @Override
	    public Component getTableCellRendererComponent( JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column ) {
	        Component c = super.getTableCellRendererComponent( table, value, isSelected, hasFocus, row, column );
	        ((JLabel) c).setIcon( (ImageIcon) value );
	        ((JLabel) c).setText( "" );
	        return c;
	    }
	}
*/
	
	
	




}