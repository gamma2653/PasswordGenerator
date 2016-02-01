package com.gamsion.chris.passwordGen;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import com.gamsion.chris.utilities.passwordGen.generator.Generator;
import com.gamsion.chris.utilities.passwordGen.generator.PasswordRequirements;
import com.gamsion.chris.utilities.passwordGen.utility.ReferenceVars;

public class GUIFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private static Dimension screensize = Toolkit.getDefaultToolkit()
			.getScreenSize();
	private File save;
	private JButton generate = new JButton("Generate");
	private JLabel text1Label = new JLabel("Number of Passwords");
	private JTextField text1 = new JTextField("0", screensize.width / 23);
	private JLabel text2Label = new JLabel("Number of Uppercase Letters");
	private JTextField text2 = new JTextField("0", screensize.width / 23);
	private JLabel text3Label = new JLabel("Number of Lowercase Letters");
	private JTextField text3 = new JTextField("0", screensize.width / 23);
	private JLabel text4Label = new JLabel("Number of Numbers");
	private JTextField text4 = new JTextField("0", screensize.width / 23);
	private JButton readme = new JButton("Open Readme");
	private Image logo;
	private generatedPasswordFrame passwordresult;
	private InputStream inputStream = getClass().getClassLoader()
			.getResourceAsStream("com/gamsion/chris/passwordGen/docs/Readme.txt");
	private Scanner s = new Scanner(inputStream).useDelimiter("\\A");
	private String message =  s.hasNext() ? s.next() : "";

	public GUIFrame(String name, Image i) {
		if (i == null) {
			try {
				logo = ImageIO.read(this.getClass().getResource(
						"/" + ReferenceVars.IconPath));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			logo = i;
		}
		this.setName(name);
		this.setPreferredSize(new Dimension(screensize.width / 2,
				screensize.height / 2));
		this.setResizable(true);
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle(name);
		this.setIconImage(logo);
		this.pack();
		this.setLocationRelativeTo(null);

		text1.setToolTipText("This is the number of passwords you want generated. Must include only numbers.");
		text2.setToolTipText("This is the number of uppercase letters you want included in the generation. Must include only numbers.");
		text3.setToolTipText("This is the number of lowercase letters you want included in the generation. Must include only numbers.");
		text4.setToolTipText("This is the number of numbers you want included in generation. Must include only numbers.");
		this.add(text1Label);
		this.add(text1);
		this.add(text2Label);
		this.add(text2);
		this.add(text3Label);
		this.add(text3);
		this.add(text4Label);
		this.add(text4);
		generate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String strnumbofpass = text1.getText();
				String strupper = text2.getText();
				String strlower = text3.getText();
				String strnumbs = text4.getText();
				int numbofpass;
				int upper;
				int lower;
				int numbs;
				try {
					numbofpass = Integer.valueOf(strnumbofpass);
					upper = Integer.valueOf(strupper);
					lower = Integer.valueOf(strlower);
					numbs = Integer.valueOf(strnumbs);
				} catch (NumberFormatException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,
							"Please do not enter anything but numbers!");
					return;
				}
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < numbofpass; i++) {
					sb.append(Generator.generate(new PasswordRequirements(
							upper, lower, numbs)));
					sb.append("\n");
				}

				if (passwordresult == null) {
					passwordresult = new generatedPasswordFrame(
							"Generated Passwords", sb.toString());
				} else {
					passwordresult.setVisible(true);
					passwordresult.setPasswords(sb.toString());
				}
			}

		});
		this.add(generate);
		readme.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(GUIFrame.this, message);
				
			}
			
		});
		this.add(readme);
		this.pack();
		this.validate();
		this.setVisible(true);

	}

	public class generatedPasswordFrame extends JFrame {
		private static final long serialVersionUID = 1L;
		JTextPane messageBox = new JTextPane();

		public generatedPasswordFrame(String name, String passwords) {
			this.setName(name);
			this.setPreferredSize(new Dimension(screensize.width / 4,
					screensize.height * 2 / 3));
			this.setResizable(true);
			this.setLayout(new FlowLayout());
			this.setDefaultCloseOperation(HIDE_ON_CLOSE);
			this.setTitle(name);
			this.setIconImage(logo);
			this.pack();
			this.setLocationRelativeTo(null);
			this.setVisible(true);
			messageBox.setSize(this.getSize());
			messageBox.setEditable(false);
			setPasswords(passwords);
			savePasswords(passwords);
			this.add(messageBox);
		}

		public void setPasswords(String passwords) {
			StringBuilder message = new StringBuilder(
					"All passwords are saved under the directory:\n\"PasswordGenerator/[year.day.month_hour.minutes.seconds]\"\n\nThe passwords generated are:\n\n");
			message.append(passwords);
			messageBox.setText(message.toString());
		}

	}

	public static void main(String[] args) {
		new GUIFrame("Gamsion's Password Generator", null);

	}
	public void savePasswords(String passwords){
		try {
			SimpleDateFormat format = new SimpleDateFormat("/yy.dd.MM_HH.mm.ss");
			String filename = format.format(new Date()) + ".txt";
			save = new File(new File(".").getAbsolutePath() + "/PasswordGenerator/");
			save.mkdir();
			save = new File(save.getAbsoluteFile()+filename);
			save.createNewFile();
			FileWriter fw = new FileWriter(save);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(passwords);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
