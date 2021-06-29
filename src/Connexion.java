

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



public class Connexion extends JFrame implements ActionListener{

	private JTextField pseudo;
	private JTextField mdp;
	private JButton submit;
	private JButton reset;
	

	public Connexion() {

		




		this.getContentPane().setBackground(new Color(0xFFf9f3f3));
		this.setSize(new Dimension (700,150));
		this.setLocation(300,300);
		this.getContentPane().setBackground(new Color(0xFFf9f3f3));
		this.setTitle("Connexion");
		this.setLayout(new BorderLayout());
		//this.setResizable(false);
		JLabel msg = new JLabel("Bienvenue, veuillez entrer votre Mot de Passe!");
		msg.setFont(new Font("Pacifico", Font.BOLD, 20));
		msg.setForeground(new Color(0xFFca8a8b));
		msg.setHorizontalAlignment(JLabel.CENTER);
		msg.setVerticalAlignment(JLabel.CENTER);
		msg.setPreferredSize(new Dimension(420,40));
		this.add(msg,BorderLayout.NORTH);

		//panel pour les champs:
		JPanel champs = new JPanel();
		champs.setOpaque(true);
		champs.setBackground(new Color(0xFFf9f3f3));
		champs.setLayout(new FlowLayout());
	

		//password
		JLabel lbl_mdp=new JLabel("Mot de passe");
		lbl_mdp.setFont(new Font("Pacifico", Font.BOLD, 15));
		lbl_mdp.setForeground(new Color(0xFFbdd2b6));
		mdp=new JPasswordField(20);
		champs.add(lbl_mdp);
		champs.add(mdp);
		this.add(champs,BorderLayout.CENTER);

		//les boutons:
		JPanel boutons = new JPanel();
		boutons.setOpaque(true);
		boutons.setBackground(new Color(0xFFf9f3f3));
		boutons.setLayout(new FlowLayout());
	
		//bouton connexion
		submit= new JButton("Connexion");
		submit.setOpaque(true);
		submit.setBackground(new Color(0xFFbdd2b6));
		submit.addActionListener(this);
		//bouton annuler:
		reset= new JButton("Annuler");
		reset.addActionListener(this);
		reset.setOpaque(true);
		reset.setBackground(new Color(0xFFfff8d9));
		boutons.add(submit);
		boutons.add(reset);
		this.add(boutons,BorderLayout.SOUTH);


		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);








	}



	public static void main(String[] args) {
		new Connexion();
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==submit) {


			if(!(mdp.getText().equals("admin"))) {
				JOptionPane.showMessageDialog(null, "mot de passe incorrecte !");

			}
			else {
				Acceuil ac= new Acceuil();

			}

		}

		else if (e.getSource()==reset) 
		{
			pseudo.setText("");
			mdp.setText("");
		}

	}







}
