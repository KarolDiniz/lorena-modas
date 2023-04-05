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

import br.edu.ifpb.mt.dac.dao.FuncionarioDAO;
import br.edu.ifpb.mt.dac.dao.PersistenciaDacException;
import br.edu.ifpb.mt.dac.entities.Funcionario;

public class RemoverFuncionario extends JanelaPadrao {

	public RemoverFuncionario(){
		super("Remover Funcionario");
		setSize(400,330);
		conf();
		tab(); 
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private DefaultTableModel modelo;
	private JTable tabela;
	private String id;
	private List<Funcionario> funcionarios;
	private FuncionarioDAO funcDao;
	private int linhaSelecionada;
	
	public void conf() {
		
		Color orchid = new Color(221,160,221);
		Color magnetta = new Color(139,0,139);
		
		JLabel textoInicial = new JLabel("Remover Funcionario");
		textoInicial.setForeground(magnetta);
		textoInicial.setBounds(40,7,490,60);
		textoInicial.setFont(new Font("Arial",Font.BOLD, 30));
		add(textoInicial);
        
        JButton botaoConfirmar = new JButton("Confirmar");
        botaoConfirmar.setBounds(70, 235, 110, 40);
        botaoConfirmar.setForeground(magnetta);
        botaoConfirmar.setBackground(orchid);
        botaoConfirmar.addActionListener(new ActionListener() {

        	public void actionPerformed(ActionEvent e) {
        	
        			try {
        				funcionarios = funcDao.getFuncionarios();
        				
        				Funcionario fE = funcDao.getObterID(Long.parseLong(id));
        				boolean achou = false;
        				
        				for(Funcionario funcionario : funcionarios) {
        					if(funcionario.equals(fE)) {
        						achou = true;
        						break;
        					}
        					System.out.println("Funcionario não encontrado");
        				}
        				if(achou) {
        					funcDao.remove(fE);
        					modelo.removeRow(linhaSelecionada);
        					tabela.repaint();
        					JOptionPane.showMessageDialog(null,"Funcionário removido com sucesso");
        				}
        		
	                }catch(PersistenciaDacException erro) {
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
					new CadastroFuncionarioGUI();
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
          modelo.addColumn("Tipo");
 
          funcDao = new FuncionarioDAO();
          
          try {
        	funcionarios = funcDao.getFuncionarios();
        	
        	if(funcionarios.size() > 0){
              
          	for(Funcionario funcionario : funcionarios){
                 
          		  Object[] linha = new Object[3];
                  
                  linha[0] = funcionario.getId();
                  linha[1] = funcionario.getNome();
                  linha[2] = funcionario.getTipo().name();
                  
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

