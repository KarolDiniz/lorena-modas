package br.edu.ifpb.mt.dac.dac;


import javax.swing.*;
import javax.swing.text.MaskFormatter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import br.edu.ifpb.mt.dac.entities.Cliente;
import br.edu.ifpb.mt.dac.entities.Endereco;
import br.edu.ifpb.mt.dac.entities.Telefone;
import br.edu.ifpb.mt.dac.dao.PersistenciaDacException;
import br.edu.ifpb.mt.dac.dao.ClienteDAO;
import java.util.List;
import java.util.ArrayList;

public class CadastroClienteGUI extends JFrame implements ActionListener {

	private JTextField nomeField;
    private JFormattedTextField telefoneField;
    private JFormattedTextField telefone2Field;
    private JFormattedTextField CEPField;
    private JTextField cidadeField;
    private JTextField bairroField;
    private JTextField ruaField;
    private JTextField numeroField;
    private JButton cadastrarButton;
    private JButton removerButton;
    private JButton voltarButton;
    private MaskFormatter phoneMask;
    
    Color magnetta = new Color(139,0,139); 
    Color orchid = new Color(221,160,221);
    Color rosa = new Color(216,191,216);
    
    public CadastroClienteGUI() {  	
    	conf();
        this.setVisible(true);
    }
    
    // Configurações da janela 	
    public void conf() {
        setTitle("Cadastro de Clientes");
        setSize(360, 510);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);      
        getContentPane().setBackground(rosa);
        
        addLabel("Nome:", 20, 20, 100, 30);    
        nomeField = new JTextField();
        nomeField.setForeground(magnetta);
        nomeField.setBounds(120, 20, 200, 30);
        add(nomeField);
        
        addLabel("Telefone (1):", 20, 70, 100, 30);
        
        try {
			
        	phoneMask = new MaskFormatter("(##) #####-####");
        	
        	telefoneField =  new JFormattedTextField(phoneMask);;
	        telefoneField.setForeground(magnetta);
	        telefoneField.setBounds(120, 70, 200, 30);
	        add(telefoneField);
	        
	        addLabel("Telefone (2):", 20, 120, 100, 30);        
	        telefone2Field =  new JFormattedTextField(phoneMask);;
	        telefone2Field.setForeground(magnetta);
	        telefone2Field.setBounds(120, 120, 200, 30);
	        add(telefone2Field);
		
        } catch (ParseException e) {
			e.printStackTrace();
		}
   
		try {
			 
			MaskFormatter cepMask = new MaskFormatter("#####-###");			
			addLabel("CEP:", 20, 170, 100, 30);
	        
			CEPField = new JFormattedTextField(cepMask);
	        CEPField.setForeground(magnetta);
	        CEPField.setBounds(120, 170, 200, 30);
	        add(CEPField);      
        
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        addLabel("Cidade:", 20, 220, 100, 30);     
        cidadeField = new JTextField();
        cidadeField.setForeground(magnetta);
        cidadeField.setBounds(120, 220, 200, 30);
        add(cidadeField);
        
        addLabel("Bairro:", 20, 270, 100, 30);        
        bairroField = new JTextField();
        bairroField.setForeground(magnetta);
        bairroField.setBounds(120, 270, 200, 30);
        add(bairroField);
        
        addLabel("Rua:", 20, 320, 100, 30);             
        ruaField = new JTextField();
        ruaField.setForeground(magnetta);
        ruaField.setBounds(120, 320, 200, 30);
        add(ruaField);
        
        addLabel("Numero:", 20, 370, 100, 30);       
        numeroField = new JTextField();
        numeroField.setForeground(magnetta);
        numeroField.setBounds(120, 370, 200, 30);
        add(numeroField);
        
        cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setForeground(magnetta);
        cadastrarButton.setBounds(20, 420, 115, 30);
        cadastrarButton.setBackground(orchid);
        cadastrarButton.addActionListener(this);
        add(cadastrarButton);
        
        removerButton = new JButton("Remover");
        removerButton.setBackground(orchid);
        removerButton.setForeground(magnetta);
        removerButton.setBounds(140, 420, 115, 30);
        removerButton.addActionListener(this);
        add(removerButton);        
        
        voltarButton = new JButton("<-");
        voltarButton.setForeground(magnetta);
        voltarButton.setBounds(260, 420, 60, 30);
        voltarButton.setBackground(orchid);
        voltarButton.addActionListener(new ActionListener() {
        	
        	@Override
            public void actionPerformed(ActionEvent e) {
               
        		if(e.getSource() == voltarButton) {
        			dispose();
        			new Painelnicial();   			
        		}
        	}           
        });
        add(voltarButton);
    }    
    public void actionPerformed(ActionEvent event) {       
    	ClienteDAO clienteDao = new ClienteDAO();
    	
    	if (event.getSource() == cadastrarButton) { // Ação do botão cadastrar        
            String nome = nomeField.getText();
            String telefone1 = (String)telefoneField.getValue();
            String telefone2 = (String)telefone2Field.getValue();
            String cep = (String)CEPField.getValue();
            String cidade = cidadeField.getText();
            String bairro = bairroField.getText();
            String rua = ruaField.getText();
            String numero = numeroField.getText();
   
    	    try {
    	    	// Setado as informações de um cliente	
        		Cliente cliente = new Cliente();
        		cliente.setNome(nome);
        		List<Telefone> telefones = new ArrayList<>();
        		
        		if(telefone2 == null) {
        			 Telefone t1 = new Telefone(telefone1 ,cliente);
        			 telefones.add(t1);
        		}else {
        			Telefone t1 = new Telefone(telefone1 ,cliente);
        			 telefones.add(t1);
        			 Telefone t2 = new Telefone(telefone2 ,cliente);
        			 telefones.add(t2);
        		}
        		cliente.setTelefones(telefones);
        	    Endereco endereco = new Endereco(rua, bairro, cep, numero, cidade);
        	    cliente.setEndereco(endereco);
        	    clienteDao.salvar(cliente);
        	    JOptionPane.showMessageDialog(null,"Cliente cadastrado com sucesso!");
				
			} catch (PersistenciaDacException e) {
				e.printStackTrace();
			}
        } 
    	else if (event.getSource() == removerButton) {
        	dispose();
        	new RemoverCliente();
        }
    }  
    
    public JLabel addLabel(String nome, int x, int y, int a, int l) {     
 	     JLabel nomeLabel = new JLabel(nome);
         nomeLabel.setForeground(magnetta);
         nomeLabel.setBounds(x, y, a, l);
         add(nomeLabel);        
 		 return nomeLabel;	
 	}
}