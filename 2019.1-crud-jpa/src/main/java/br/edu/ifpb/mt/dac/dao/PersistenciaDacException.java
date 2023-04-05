package br.edu.ifpb.mt.dac.dao;

import javax.swing.JOptionPane;

import br.edu.ifpb.mt.dac.dac.DacException;

public class PersistenciaDacException extends DacException {

	private static final long serialVersionUID = 7159282553688713660L;

	public PersistenciaDacException(String message) {
		super(message);
	}

	public PersistenciaDacException(String message, Throwable cause) {
		super(message, cause);
		JOptionPane.showMessageDialog(null, message);
	}

}
