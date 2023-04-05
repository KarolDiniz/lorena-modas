package br.edu.ifpb.mt.dac.dac;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

public class Painelnicial extends JanelaPadrao {
	
	private Color orchid = new Color(221,160,221);
	private Color magnetta = new Color(139,0,139);
	
	public Painelnicial() {
		super("Painel Inicial");
		super.setSize(700,350); 		
		conf();
		setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void conf() {	
		
		addLabel("╔════════ ♥ღ❤ღ♥ ════════╗", "Serif", 390,20,490,60,20);
		addLabel("♥ Lorena ♥","Serif", 400,80,490,60,50 );
		addLabel("MODAS","Monospaced", 475,120,490,60,50);
		addLabel("╚════════ ♥♥♥♥♥ ════════╝","Serif", 390,180,490,60, 20);
		addLabel("Painel Inicial", "Arial", 40,7,490,60,50);
		addLabel("Seja bem vindo(a), a Lorena Modas.", "Arial", 70,80,250,20,14);

		addButton("Cadastro de Funcionario", 40,115,300,30);
		addButton("Cadastro de Cliente", 40,160,300, 30);
		addButton("Cadastro de Compra", 40,205,300, 30);
		addButton("Sair", 40,250,300, 30);	
	}
	
	public class OuvinteInternoMenu implements ActionListener{		
		public void actionPerformed(ActionEvent e) {			
			String botao = e.getActionCommand();			
			switch (botao) { 			
				case "Cadastro de Cliente":
					dispose();
					new CadastroClienteGUI();
					break;
				case "Cadastro de Funcionario":
					dispose();
					new CadastroFuncionarioGUI();
					break;
				case "Cadastro de Compra":
					dispose();
					new CadastroDeCompraGUI();
					break;
				case "Sair":
					dispose();
			}		
		}		
	}

	private JLabel addLabel(String nome, String fonte, int x, int y, int a, int l, int tamanho) {
		JLabel LorenaLabel = new JLabel(nome);
		LorenaLabel.setForeground(magnetta);
		LorenaLabel.setBounds(x, y, a,l);
		LorenaLabel.setFont(new Font(fonte, Font.PLAIN, tamanho));
		add(LorenaLabel );
		return LorenaLabel;
	}
	private JButton addButton (String nome, int x, int y, int a, int l) {
		JButton b1 = new JButton(nome);
		b1.setForeground(magnetta);
		b1.setBounds(x, y, a,l);
		b1.setBackground(orchid);
		add(b1);
		b1.addActionListener(new OuvinteInternoMenu());
		return b1;
	}
}