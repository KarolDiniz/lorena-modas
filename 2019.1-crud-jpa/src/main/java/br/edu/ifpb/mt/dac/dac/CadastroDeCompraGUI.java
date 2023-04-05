package br.edu.ifpb.mt.dac.dac;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.text.SimpleDateFormat;

import br.edu.ifpb.mt.dac.dao.ClienteDAO;
import br.edu.ifpb.mt.dac.dao.CompraDAO;
import br.edu.ifpb.mt.dac.dao.FuncionarioDAO;
import br.edu.ifpb.mt.dac.dao.PersistenciaDacException;
import br.edu.ifpb.mt.dac.dao.ProdutoDAO;
import br.edu.ifpb.mt.dac.entities.Cliente;
import br.edu.ifpb.mt.dac.entities.Compra;
import br.edu.ifpb.mt.dac.entities.Funcionario;
import br.edu.ifpb.mt.dac.entities.Produto;

public class CadastroDeCompraGUI extends JFrame implements ActionListener {
    
    private JPanel painel;
    private JLabel labelFuncionario, labelCliente, labelData, labelProduto, labelValorTotal;
    private JTextField textFieldValorTotal;
    private JButton botaoSalvar,  botaoRemover, botaoAdicionarProd, botaoRemoveProd,botaoNovoProd;
    private JFormattedTextField textFieldData;
    MaskFormatter cepMask;
    JComboBox<Funcionario> comboBoxFunc;
    JComboBox<Cliente> comboBoxCliente; 
    
    public CadastroDeCompraGUI() {
    	
    	Color orchid = new Color(221,160,221);
		Color magnetta = new Color(139,0,139); 
        Color rosa = new Color(216,191,216); // Cria uma cor rosa
        getContentPane().setBackground(rosa);
    	
        painel = new JPanel();
        painel.setLayout(null);
        
        labelFuncionario = new JLabel("ID do funcionário:");
        labelFuncionario.setForeground(magnetta);
        labelFuncionario.setBounds(10, 40, 120, 20);
        painel.add(labelFuncionario);
       
        
        try {
        	
            comboBoxFunc = new JComboBox<Funcionario>();
            
            funcDao =new FuncionarioDAO();
            
        	funcionarios = funcDao.getFuncionarios();
        	
        	for(Funcionario p: funcionarios) {
        		comboBoxFunc.addItem(p);
        	 }
        	 comboBoxFunc.setForeground(magnetta);
             comboBoxFunc.setBounds(140, 40, 120, 20);
             painel.add(comboBoxFunc);
			
		} catch (PersistenciaDacException e) {
			e.printStackTrace();
		}
      
        labelCliente = new JLabel("ID do cliente:");
        labelCliente.setForeground(magnetta);
        labelCliente.setBounds(10, 70, 120, 20);
        painel.add(labelCliente);
        
      try {
        	
    	  comboBoxCliente = new JComboBox<Cliente>();
            
            clienteDao = new ClienteDAO();
            
        	clientes = clienteDao.getClientes();
        	
        	for(Cliente c: clientes) {
        		comboBoxCliente.addItem(c);
        	 }
        	 comboBoxCliente.setForeground(magnetta);
        	 comboBoxCliente.setBounds(140, 70, 120, 20);
             painel.add(comboBoxCliente);
             
		} catch (PersistenciaDacException e) {
			e.printStackTrace();
		}
      
        labelData = new JLabel("Data:");
        labelData.setForeground(magnetta);
        labelData.setBounds(10, 100, 120, 20);
        painel.add(labelData);
        
        try {
			  textFieldData = new JFormattedTextField(new MaskFormatter("##/##/####"));
			  textFieldData.setForeground(magnetta);
			  textFieldData.setBounds(140, 100, 120, 20);
			  textFieldData.setFont(new Font("Arial", Font.PLAIN, 12));
			  painel.add(textFieldData);
        
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        labelProduto = new JLabel("Produtos:");
        labelProduto.setForeground(magnetta);
        labelProduto.setBounds(10, 130, 120, 20);
        painel.add(labelProduto);
        
        labelValorTotal = new JLabel("Valor Total:");
        labelValorTotal.setForeground(magnetta);
        labelValorTotal.setBounds(10, 360, 120, 20);
        painel.add(labelValorTotal);
        
        textFieldValorTotal = new JTextField(Double.toString(valorTotal));
        textFieldValorTotal.setForeground(magnetta);
        textFieldValorTotal.setBounds(140, 360, 120, 20);
        painel.add(textFieldValorTotal);
        
        botaoNovoProd = new JButton("+");
        botaoNovoProd.setForeground(magnetta);
        botaoNovoProd.setBounds(30, 310, 50, 20);
        botaoNovoProd.setBackground(orchid);
        botaoNovoProd.addActionListener(new ActionListener() {
        	
        	@Override
            public void actionPerformed(ActionEvent e) {
               
        		if(e.getSource() == botaoNovoProd) {
        			dispose();
        			new CadastroProduto();   			
        		}
        	}
              
        });
        
        painel.add(botaoNovoProd);
        
        botaoRemoveProd = new JButton("-");
        botaoRemoveProd.setForeground(magnetta);
        botaoRemoveProd.setBounds(90, 310, 50, 20);
        botaoRemoveProd.setBackground(orchid);
        botaoRemoveProd.addActionListener(new ActionListener() {
        	
        	@Override
            public void actionPerformed(ActionEvent e) {
               
        		if(e.getSource() == botaoRemoveProd) {
        			
        			try {
        				Produto pE = obterProduto();

						produtoDao.remove(pE);
						
						textFieldValorTotal.setText(Double.toString(0.0));
						
						modelo.removeRow(linhaSelecionada);
		    	    	tabela.repaint();
						
						JOptionPane.showMessageDialog(null, "Produto removido com sucesso!");
        				
        			}catch (PersistenciaDacException e1) {
        				e1.printStackTrace();
        			}
        		}
        	}  
        });
        painel.add(botaoRemoveProd);
        
        botaoAdicionarProd = new JButton("Carrinho");
        botaoAdicionarProd.setForeground(magnetta);
        botaoAdicionarProd.setBounds(150, 310, 90, 20);
        botaoAdicionarProd.setBackground(orchid);
        botaoAdicionarProd.addActionListener(new ActionListener() {
        	
        	@Override
            public void actionPerformed(ActionEvent e) {
               
        		if(e.getSource() == botaoAdicionarProd) {
        			
        			Produto pE = obterProduto();
        			
        			valorTotal += pE.getValor();
        			
        			textFieldValorTotal.setText(Double.toString(valorTotal));
        	
           			produtos.add(pE);
           			
           			JOptionPane.showMessageDialog(null, "Produto adicioando no carrinho");
        		}
        	}
              
        });
        painel.add(botaoAdicionarProd);
        
        botaoSalvar = new JButton("Salvar");
        botaoSalvar.setForeground(magnetta);
        botaoSalvar.setBounds(10, 390, 90, 30);
        botaoSalvar.setBackground(orchid);
        botaoSalvar.addActionListener(this);
        painel.add(botaoSalvar);
        
        botaoRemover = new JButton("Remover");
        botaoRemover.setForeground(magnetta);
        botaoRemover.setBounds(110, 390, 90, 30);
        botaoRemover.setBackground(orchid);
        botaoRemover.addActionListener(this);
        painel.add(botaoRemover);
        
        JButton voltarButton = new JButton("<-");
        voltarButton.setBounds(210, 390, 50, 30);
        voltarButton.setBackground(orchid);
        voltarButton.setForeground(magnetta);
        voltarButton.addActionListener(new ActionListener() {
        	
        	@Override
            public void actionPerformed(ActionEvent e) {
               
        		if(e.getSource() == voltarButton) {
        			dispose();
        			new Painelnicial();   			
        		}
        	}
              
        });
        
        painel.add(voltarButton);
        painel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(magnetta), "Cadastro de Compra"));
        painel.setPreferredSize(new Dimension(270, 430));
       
        tab();
        add(painel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
    
    @SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
    	
    	Cliente cliente = new Cliente();
        Compra compra = new Compra();
        Funcionario funcionario = new Funcionario(); 
        CompraDAO compradao = new CompraDAO();

        if (e.getSource() == botaoSalvar) {
        	     	
        	try {
        		
	            String idFuncionario = comboBoxFunc.getSelectedItem().toString();
	            String idCliente = comboBoxCliente.getSelectedItem().toString();
	            String data = textFieldData.getText();
	            
	            Date date = new Date(data);
	            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	            String dataFormatada = sdf.format(date);
	        
		           
	    //      	Setagem dos identificadores:
		
		    		funcionario.setId(Long.parseLong(idFuncionario));
		        	cliente.setId(Long.parseLong(idCliente));
		
		//        	Setagem de produto:
		        		
		        	compra.setValorTotal(valorTotal);
		        	compra.setDate(new Date(dataFormatada));
		        	compra.setFuncionario(funcionario);
		        	compra.setCliente(cliente);
		        
					compra.setProdutos(produtos);
					
					compradao.salvar(compra);
					
					JOptionPane.showMessageDialog(null, "Compra adicionada com sucesso!");
		
	          
	          
        	} catch (PersistenciaDacException e1) {
				e1.printStackTrace();
			}
        } else if (e.getSource() == botaoRemover) {
        	dispose();
        	new RemoverCompra();
        }
    }
    
    	private DefaultTableModel modelo;
		private JTable tabela;
		private String id;
		private List<Produto> produtos1;
		private List<Funcionario> funcionarios;
		private List<Cliente> clientes;
		private ProdutoDAO produtoDao;
		private FuncionarioDAO funcDao;
		private ClienteDAO clienteDao;
		private int linhaSelecionada;
		private List<Produto> produtos = new ArrayList<>();
		private Double valorTotal = (double) 0;
		
     public void tab() {
    	
	    //colunas da lista 
	    modelo  = new DefaultTableModel();
	    modelo.addColumn("ID");
	    modelo.addColumn("Nome");
        modelo.addColumn("Cor");
        modelo.addColumn("Tamanho");
        modelo.addColumn("Preço");

        produtoDao = new ProdutoDAO();
        
        try {
      	produtos1 = produtoDao.getProdutos();    	
      	if(produtos1.size() > 0){
            
        	for(Produto produto : produtos1){             
        		Object[] linha = new Object[5];
                
                linha[0] = produto.getId();
                linha[1] = produto.getTipoProduto().name();
                linha[2] = produto.getTipoTamanho().name();
                linha[3] = produto.getCor();
                linha[4] = produto.getValor();
                
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
					}else {
						JOptionPane.showMessageDialog(null,"Selecione um produto");
					}
		            tabela.repaint();
				}
			});
	        
        JScrollPane painelTabela = new JScrollPane(tabela);
	    painelTabela.setBounds(10, 150, 250, 153);
	    add(painelTabela);  
	}
     
     public Produto obterProduto() {
		
    	 try {
				
    		 Produto pE = produtoDao.obterID(Long.parseLong(id));
			 
    		 produtos1 = produtoDao.getProdutos();
			
				boolean achou = false;
			 
				if(produtos1.size() > 0){
           
					for(Produto p : produtos1){
					 
						if(p.equals(pE)){
							achou = true;
							break;
						}
					}
					if(achou) {
						return pE;
					}
				}
			} catch (PersistenciaDacException e) {
				e.printStackTrace();
			}
			 return null;
	}
 
}
	

