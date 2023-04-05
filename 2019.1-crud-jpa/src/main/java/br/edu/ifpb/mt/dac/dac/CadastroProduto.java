package br.edu.ifpb.mt.dac.dac;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.edu.ifpb.mt.dac.entities.Produto;
import br.edu.ifpb.mt.dac.dao.PersistenciaDacException;
import br.edu.ifpb.mt.dac.dao.ProdutoDAO;

public class CadastroProduto  extends JanelaPadrao{
	
	private JLabel labelProduto, labelCor,labelTamanho,labelPreco;
	private JComboBox<String> comboBoxProduto, comboBoxTamanho;
	private JTextField textFieldCor,textFieldPreco;
	private JPanel painel;
	private JButton botaoSalvar, botaoCancelar;
	
	public CadastroProduto() {
		super("Cadastro de Produto");
		conf();
		setSize(300,330);
	}
	 
	public void conf() {
		Color orchid = new Color(221,160,221);
		Color magnetta = new Color(139,0,139); 
	    Color rosa = new Color(216,191,216); // Cria uma cor rosa
		
		JLabel textoInicial = new JLabel("Cadastrar Produto");
		textoInicial.setForeground(magnetta);
		textoInicial.setBounds(15,7,490,60);
		textoInicial.setFont(new Font("Arial",Font.BOLD, 30));
		add(textoInicial);

	    getContentPane().setBackground(Color.white);
		
	    painel = new JPanel();
	    painel.setLayout(null);
	    
	    labelProduto = new JLabel("Produto:");
	    labelProduto.setForeground(magnetta);
	    labelProduto.setBounds(15, 90, 120, 20);
	    add(labelProduto);
	    
	    comboBoxProduto = new JComboBox<>();
	    comboBoxProduto.setBackground(orchid);
	    comboBoxProduto.setForeground(magnetta);
	    comboBoxProduto.setModel(new DefaultComboBoxModel<>(new String[] { "CAMISA","TERNO","VESTIDO","SAIA","SHORT","CALCA","MACACAO","MEIAS","LUVAS","GRAVATA","ROUPA_INTIMA" }));
	    comboBoxProduto.setBounds(140, 90, 120, 20);
	    add(comboBoxProduto);
	    
	    labelCor = new JLabel("Cor:");
	    labelCor.setForeground(magnetta);
	    labelCor.setBounds(15, 120, 120, 20);
	    add(labelCor);
	    
	    textFieldCor = new JTextField();
	    textFieldCor.setForeground(magnetta);
	    textFieldCor.setBounds(140, 120, 120, 20);
	    add(textFieldCor);
	    
	    labelTamanho = new JLabel("Tamanho:");
	    labelTamanho.setForeground(magnetta);
	    labelTamanho.setBounds(15, 150, 120, 20);
	    add(labelTamanho);
	    
	    comboBoxTamanho = new JComboBox<>();;
	    comboBoxTamanho.setBackground(orchid);
	    comboBoxTamanho.setForeground(magnetta);
	    comboBoxTamanho.setModel(new DefaultComboBoxModel<>(new String[] { "P","PP","M","G","GG"}));
	    comboBoxTamanho.setBounds(140, 150, 120, 20);
	    add(comboBoxTamanho);
	    
	    labelPreco = new JLabel("Preço:");
	    labelPreco.setForeground(magnetta);
	    labelPreco.setBounds(15, 180, 120, 20);
	    add(labelPreco);
	    
	    textFieldPreco = new JTextField();
	    textFieldPreco.setForeground(magnetta);
	    textFieldPreco.setBounds(140, 180, 120, 20);
	    add(textFieldPreco);
	    
	     botaoSalvar = new JButton("Salvar");
	     botaoSalvar.setForeground(magnetta);
	     botaoSalvar.setBounds(15, 220, 120, 30);
	     botaoSalvar.setBackground(orchid);
	     botaoSalvar.addActionListener(new ActionListener() {
	        	
	        	@Override
	            public void actionPerformed(ActionEvent e) {
	        		
	        		ProdutoDAO prodDao = new ProdutoDAO();
	              
	        	          try {
	        	        	  	if(e.getSource() == botaoSalvar) {
	        	        	  		
	        	        	  		String produto = comboBoxProduto.getSelectedItem().toString();
	        	        	  		String cor = textFieldCor.getText();
	        	        	  		String tamanho = comboBoxTamanho.getSelectedItem().toString();
	        	        	  		String preco = textFieldPreco.getText();
		        	          
	        	        	  		Produto prod = new Produto(cor, tamanho, Double.parseDouble(preco), produto);
	        	        	  		
	        	        	  		prodDao.salvar(prod);
	        	        	  		
	        	        	  		JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso!");
	        	        	  	}
	        	        	  		
						} catch (PersistenciaDacException e1) {
							e1.printStackTrace();
						}
	        			
	        		}
	        });
	     
	     add(botaoSalvar);
	        
	     botaoCancelar = new JButton("Cancelar");
	     botaoCancelar.setForeground(magnetta);
	     botaoCancelar.setBounds(140, 220, 120, 30);
	     botaoCancelar.setBackground(orchid);
	     botaoCancelar.addActionListener(new ActionListener() {
	        	
	        	@Override
	            public void actionPerformed(ActionEvent e) {
	               
	        		if(e.getSource() == botaoCancelar) {
	        			dispose();
	        			new CadastroDeCompraGUI();   			
	        		}
	        	}
	              
	        });
	     add(botaoCancelar);
	      
	     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     setLocationRelativeTo(null);
	     setResizable(false);
	     
	     setVisible(true);
	
	}
}
