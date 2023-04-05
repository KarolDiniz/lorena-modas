package br.edu.ifpb.mt.dac.dac;

import javax.swing.JOptionPane;

public class DacException extends Exception {

	private static final long serialVersionUID = -7669751088704144947L;

	public DacException(String message) {
		super(message);
		JOptionPane.showMessageDialog(null, message);
	}

	public DacException(String message, Throwable cause) {
		super(message, cause);
		JOptionPane.showMessageDialog(null, message);
	}

}
