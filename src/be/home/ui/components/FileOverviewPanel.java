package be.home.ui.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;

import be.home.classes.Config;
import be.home.classes.SourceFile;

public class FileOverviewPanel extends JPanel
{
	/*
	 * Members
	 */
	private JList				lst_files;
	private DefaultListModel	model_list_files;
	private FileListRenderer 	lst_renderer;

	public FileOverviewPanel()
	{
		super();
		createGUI();
	}

	public FileOverviewPanel( LayoutManager layout )
	{
		super( layout );
		createGUI();
	}

	private void createGUI()
	{
		// Set panel layout
		this.setLayout( new GridBagLayout() );
		GridBagConstraints constr = new GridBagConstraints();

		// Initialize components
		this.lst_files = new JList();
		this.lst_files.setPreferredSize( new Dimension(200,50) );
		this.model_list_files = new DefaultListModel();
		lst_files.setModel( model_list_files );
		lst_files.setCellRenderer( lst_renderer = new FileListRenderer() );
		
		// Add to panel
		constr.fill =  GridBagConstraints.BOTH;
		constr.gridx = 0;
		constr.gridy = 0;
		constr.weighty = 1.00;
		constr.insets = new Insets(10,10,10,10);
		add(lst_files, constr);
	}
}
