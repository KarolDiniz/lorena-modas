package br.edu.ifpb.mt.dac.dac;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.edu.ifpb.mt.dac.dao.FuncionarioDAO;
import br.edu.ifpb.mt.dac.dao.PersistenciaDacException;
import br.edu.ifpb.mt.dac.entities.Funcionario;
import br.edu.ifpb.mt.dac.entities.TipoFuncionario;

public class CadastroFuncionarioGUI {
	
    JPanel panel = new JPanel(new GridLayout(0, 2, 10, 10));

    private JFrame frame;
    private JTextField nomeField;
    private JComboBox<TipoFuncionario> tipoFuncionarioComboBox;
    
    Color magnetta = new Color(139,0,139);
	Color orchid = new Color(221,160,221);
	Color rosa = new Color(216,191,216);

    public CadastroFuncionarioGUI() {
        initComponents();
    }

    public JLabel addLabel(String nome) {
    	JLabel nomeLabel = new JLabel(nome);
        nomeLabel.setForeground(magnetta);
        panel.add(nomeLabel);
		return nomeLabel;
    }
    private void initComponents() {  	
        frame = new JFrame("Cadastro de Funcionário");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(rosa);

        addLabel("Nome:");
        nomeField = new JTextField(20);
        nomeField.setForeground(magnetta);
        panel.add(nomeField);

        addLabel("Tipo de Funcionário:");
        tipoFuncionarioComboBox = new JComboBox<>(TipoFuncionario.values());
        tipoFuncionarioComboBox.setForeground(magnetta);
        panel.add(tipoFuncionarioComboBox);

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setBackground(orchid);
        cadastrarButton.setForeground(magnetta);
        cadastrarButton.addActionListener(new ActionListener() {
        	
        	@Override
            public void actionPerformed(ActionEvent e) {       		
        	String nome = nomeField.getText();
            String tipo = tipoFuncionarioComboBox.getSelectedItem().toString();           
        	FuncionarioDAO funcDao = new FuncionarioDAO(); 
        	
        			if(e.getSource() == cadastrarButton) {	
        				try {	
        					Funcionario func = new Funcionario();
        					func.setNome(nome);	
	        				if(tipo.equals("ATENDENTE")) {
	        					func.setTipo(TipoFuncionario.ATENDENTE);
	        				}else if (tipo.equals("GERENTE")){
	        					func.setTipo(TipoFuncionario.GERENTE);
	        				}else {
	        					func.setTipo(TipoFuncionario.ESTOQUISTA);
	        				}
	        				JOptionPane.showMessageDialog(null,"Funcionário adicionado com sucesso");
	        				funcDao.salvar(func);
	        			
        				}catch(PersistenciaDacException erro) {
        	    	    	erro.printStackTrace();
        	    	    }
        			}}});
  
        JButton voltarButton = new JButton("<-");
        voltarButton.setBackground(orchid);
        voltarButton.setForeground(magnetta);
        voltarButton.addActionListener(new ActionListener() {
        	
        	@Override
            public void actionPerformed(ActionEvent e) {
               
        		if(e.getSource() == voltarButton) {
        			frame.dispose();
        			new Painelnicial();   			
        		}
        	}});
        
        JButton removeButton = new JButton("Remover");
        removeButton.setBackground(orchid);
        removeButton.setForeground(magnetta);
        removeButton.addActionListener(new ActionListener() {
        	
            public void actionPerformed(ActionEvent e) {
            	frame.dispose();
            	new RemoverFuncionario(); 
            }});
        
        panel.add(cadastrarButton);
        panel.add(removeButton);
        panel.add(voltarButton);
        
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}