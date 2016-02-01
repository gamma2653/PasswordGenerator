package com.gamsion.chris.passwordGen;

import javax.swing.JLabel;

public class TextBoxes extends JLabel{
	private static final long serialVersionUID = 1L;
	private String initialtext;
	public TextBoxes(String name, String initialText){
		this.setName(name);
		this.setText(initialText);
		this.initialtext = initialText;
	}
	public void resetToOriginalText(){
		this.setText(initialtext);
	}
	public void setOriginalText(String text){
		this.initialtext = text;
	}

}
