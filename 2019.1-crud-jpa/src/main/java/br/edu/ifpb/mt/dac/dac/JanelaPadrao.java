package br.edu.ifpb.mt.dac.dac;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class JanelaPadrao extends JFrame {

	public JanelaPadrao(String nome) {	
	    setTitle(nome);
        setSize(400,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        Color rosa = new Color(216,191,216); // Cria uma cor rosa
        getContentPane().setBackground(rosa);
    
	}
}
