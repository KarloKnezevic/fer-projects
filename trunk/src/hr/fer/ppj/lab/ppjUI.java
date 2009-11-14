package hr.fer.ppj.lab;

import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * 
 * @author Don
 */
public class ppjUI extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;

	/** Creates new form ppjUI */
	public ppjUI() {
		initComponents();
		// Get the native look and feel class name
		String nativeLF = UIManager.getSystemLookAndFeelClassName();

		// Install the look and feel
		try {
			UIManager.setLookAndFeel(nativeLF);
		} catch (InstantiationException e) {
		} catch (ClassNotFoundException e) {
		} catch (UnsupportedLookAndFeelException e) {
		} catch (IllegalAccessException e) {
		}

	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
		Table = new javax.swing.JTable();
		jMenuBar1 = new javax.swing.JMenuBar();
		menuFile = new javax.swing.JMenu();
		jMenuItem1 = new javax.swing.JMenuItem();
		jMenuItem2 = new javax.swing.JMenuItem();

		
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		jScrollPane1.setViewportView(Table);

		menuFile.setText("File");
		menuFile.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				menuFileMouseEntered(evt);
			}
		});

		jMenuItem1.setText("Open");
		jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					jMenuItem1ActionPerformed(evt);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		menuFile.add(jMenuItem1);

		jMenuItem2.setText("Exit");
		jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem2ActionPerformed(evt);
			}
		});
		menuFile.add(jMenuItem2);

		jMenuBar1.add(menuFile);

		setJMenuBar(jMenuBar1);
		
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 620,
				javax.swing.GroupLayout.PREFERRED_SIZE));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 220,
				Short.MAX_VALUE));
		
		pack();
	}// </editor-fold>

	private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) throws Exception {
		javax.swing.JFileChooser jfc = new javax.swing.JFileChooser();
		jfc.setCurrentDirectory(new java.io.File("C:\\"));
		if (jfc.showOpenDialog(this) == javax.swing.JFileChooser.APPROVE_OPTION) {
			java.io.File inFile = jfc.getSelectedFile();
			try {
				this.Table.setModel(Compiler.getTableModel(inFile));
			} catch (IOException e) {
				// greška
			}
		}
	}

	private void menuFileMouseEntered(java.awt.event.MouseEvent evt) {
		// TODO add your handling code here:
	}

	private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {
		System.exit(0); // TODO add your handling code here:
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ppjUI().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify
	public javax.swing.JTable Table;
	private javax.swing.JMenuBar jMenuBar1;
	private javax.swing.JMenuItem jMenuItem1;
	private javax.swing.JMenuItem jMenuItem2;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JMenu menuFile;
	// End of variables declaration

}