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
import br.edu.ifpb.mt.dac.dao.CompraDAO;
import br.edu.ifpb.mt.dac.dao.PersistenciaDacException;
import br.edu.ifpb.mt.dac.entities.Cliente;
import br.edu.ifpb.mt.dac.entities.Compra;

public class RemoverCompra extends JanelaPadrao{

	public RemoverCompra() {
		super("Remover Compra");
		setSize(400,330);
		conf();
		tab();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private DefaultTableModel modelo;
	private JTable tabela;
	private CompraDAO compraDao;
	private String id;
	private List<Compra> compras;
	private int linhaSelecionada;
	
	
	public void conf() {
		Color orchid = new Color(221,160,221);
		Color magnetta = new Color(139,0,139);
		
		JLabel textoInicial = new JLabel("Remover Compra");
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
				compras = compraDao.getCompras();	    	   
	      	    Compra cE = compraDao.obterID(Long.parseLong(id));
	    	    boolean achou = false;
	    	    
	    	    for(Compra cp: compras) {
	    	    	if(cp.equals(cE)) {
	    	    		achou = true;
	    	    		break;
	    	    	}else
	    	    		System.out.println("Compra não encontrada!");
	    	    }if(achou == true) {	    	    	   
	    	    	   compraDao.remove(cE);
	    	    	   modelo.removeRow(linhaSelecionada);
	    	    	   tabela.repaint();
	    	    	   JOptionPane.showMessageDialog(null,"Compra removida com sucesso!");
	    	       }	    	       
    	    } catch(PersistenciaDacException erro) {
    	    	erro.printStackTrace();
		    }
		}}); 
        add(botaoConfirmar);
        
        JButton botaoCancelar = new JButton("Cancelar");
        botaoCancelar.setBounds(210, 235, 110, 40);
        botaoCancelar.setForeground(magnetta);
        botaoCancelar.setBackground(orchid);
        botaoCancelar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == botaoCancelar) {
					dispose();
					new CadastroDeCompraGUI();
				}
			}
        	
        });
        add(botaoCancelar);
	}
	
	public void tab() {
		
	      //colunas da lista 
	      modelo  = new DefaultTableModel();
	      modelo.addColumn("ID_COMPRA");
	      modelo.addColumn("DATA");
          modelo.addColumn("VALOR_TOTAL");
          modelo.addColumn("ID_CLIENTE");
          modelo.addColumn("ID_FUNCIONARIO");
          
          compraDao = new CompraDAO();
  
		try {
			compras = compraDao.getCompras();
			if(compras.size() > 0){
              
          	for(Compra comp : compras){
                 
          		  Object[] linha = new Object[5];
                  
                  linha[0] = comp.getId();
                  linha[1] = comp.getDate();
                  linha[2] = comp.getValorTotal();
                  linha[3] = comp.getCliente().getId();
                  linha[4] = comp.getFuncionario().getId();

                  
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
