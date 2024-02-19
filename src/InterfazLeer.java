import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class InterfazLeer {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazLeer window = new InterfazLeer();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfazLeer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(168, 25, 244, 212);
		frame.getContentPane().add(textArea);
		
		JButton btnRead = new JButton("Read file");
		btnRead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
				fc.addChoosableFileFilter(filter);
				fc.setAcceptAllFileFilterUsed(false);
				int result = fc.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					BufferedReader buffer;
					try {
						File file = fc.getSelectedFile();
						buffer = new BufferedReader(new FileReader(file));
						String line;
						String texto = "";
						while ((line = buffer.readLine()) != null) {
							texto += line + "\n";
						}
						textArea.setText(texto);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnRead.setBounds(40, 39, 85, 35);
		frame.getContentPane().add(btnRead);
		
		JButton btnWrite = new JButton("Write file");
		btnWrite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
				fc.addChoosableFileFilter(filter);
				fc.setAcceptAllFileFilterUsed(false);
				int result = fc.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					try {
						File file = fc.getSelectedFile();
						FileWriter fw = new FileWriter(file, false);
						fw.write(textArea.getText());
						fw.close();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		btnWrite.setBounds(40, 126, 85, 35);
		frame.getContentPane().add(btnWrite);
	}
}
