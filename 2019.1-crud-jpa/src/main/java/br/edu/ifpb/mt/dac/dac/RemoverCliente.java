package br.edu.ifpb.mt.dac.dac;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.edu.ifpb.mt.dac.dao.ClienteDAO;
import br.edu.ifpb.mt.dac.dao.PersistenciaDacException;
import br.edu.ifpb.mt.dac.entities.Cliente;

public class RemoverCliente extends JanelaPadrao{

	public RemoverCliente() {
		super("Remover Cliente");
		setSize(400,330);
		conf();
		tab();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private DefaultTableModel modelo;
	private JTable tabela;
	private ClienteDAO clienteDao;
	private String id;
	private List<Cliente> clientes;
	private int linhaSelecionada;
	
	
	public void conf() {
		Color orchid = new Color(221,160,221);
		Color magnetta = new Color(139,0,139);
		
		JLabel textoInicial = new JLabel("Remover Cliente");
		textoInicial.setForeground(magnetta);
		textoInicial.setBounds(60,7,490,60);
		textoInicial.setFont(new Font("Arial",Font.BOLD, 30));
		add(textoInicial);
        
        JButton botaoConfirmar = new JButton("Confirmar");
        botaoConfirmar.setBounds(70, 235, 110, 40);
        botaoConfirmar.setForeground(magnetta);
        botaoConfirmar.setBackground(orchid);
        botaoConfirmar.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e) {
		
			try {
	    		
				clientes = clienteDao.getClientes();
	    	   
	      	    Cliente cE = clienteDao.getObterID(Long.parseLong(id));
	    	   
	    	    boolean achou = false;
	    	    
	    	    for(Cliente cli: clientes) {
	    	    	if(cli.equals(cE)) {
	    	    		achou = true;
	    	    		break;
	    	    	}else
	    	    		System.out.println("Cliente não encontrado!!");
	    	    }
	    	       if(achou == true) {
	    	    	   
	    	    	   clienteDao.remove(cE);
	    	    	   modelo.removeRow(linhaSelecionada);
	    	    	   tabela.repaint();
	    	    	   JOptionPane.showMessageDialog(null,"Cliente removido com sucesso!");
	    	       }
	    	       
    	    } catch(PersistenciaDacException erro) {
    	    	erro.printStackTrace();
		    }
		}
    	
        }); 
        add(botaoConfirmar);
        
        JButton botaoCancelar = new JButton("Cancelar");
        botaoCancelar.setBounds(210, 235, 110, 40);
        botaoCancelar.setForeground(magnetta);
        botaoCancelar.setBackground(orchid);
        botaoCancelar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == botaoCancelar) {
					dispose();
					new CadastroClienteGUI();
				}
			}
        	
        });
        add(botaoCancelar);
	}
	
	public void tab() {
		
	      //colunas da lista 
	      modelo  = new DefaultTableModel();
	      modelo.addColumn("ID");
	      modelo.addColumn("Nome");
          modelo.addColumn("CEP");
          modelo.addColumn("Cidade");
          modelo.addColumn("Bairro");
          modelo.addColumn("Rua");
          modelo.addColumn("Numero");
          
          clienteDao = new ClienteDAO();
  
		try {
			clientes= clienteDao.getClientes();
			if(clientes.size() > 0){
              
          	for(Cliente cliente : clientes){
                 
          		  Object[] linha = new Object[7];
                  
                  linha[0] = cliente.getId();
                  linha[1] = cliente.getNome();
                  linha[2] = cliente.getEndereco().getCep();
                  linha[3] = cliente.getEndereco().getCidade();
                  linha[4] = cliente.getEndereco().getBairro();
                  linha[5] = cliente.getEndereco().getRua();
                  linha[6] = cliente.getEndereco().getNumero();
                  
                  modelo.addRow(linha);
              }
          }    
	        
		} catch (PersistenciaDacException e) {
			e.printStackTrace();
		}
          
          tabela = new JTable(modelo);
          
          tabela.addMouseListener(new MouseListener() {
			
				public void mouseReleased(MouseEvent e) {}
				public void mousePressed(MouseEvent e) {}
				public void mouseExited(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {}
				
				public void mouseClicked(MouseEvent e) {
				
					linhaSelecionada = tabela.getSelectedRow();
					
					if(linhaSelecionada != -1) {
						id = tabela.getValueAt(linhaSelecionada, 0).toString();
					}
		            tabela.repaint();
				}
			});
	        JScrollPane painelTabela = new JScrollPane(tabela);
	        painelTabela.setBounds(15, 75, 357, 153);
	        add(painelTabela);  
	}

}