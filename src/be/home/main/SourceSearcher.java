package be.home.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * @author Jeffrey Vandenborne
 * @since February 2010
 * @version 1.0
 */
public class SourceSearcher extends JFrame
{
	private JPanel					pnl_top;
	private JTextField				txt_find;
	private JButton					btn_find;
	private static SourceSearcher	source_searcher;

	public SourceSearcher() throws HeadlessException
	{
		super();
		createGUI();
	}

	public SourceSearcher( GraphicsConfiguration gc )
	{
		super( gc );
		createGUI();
	}

	public SourceSearcher( String title ) throws HeadlessException
	{
		super( title );
		createGUI();
	}

	/**
	 * Creates the layout for this frame
	 */
	private void createGUI()
	{
		this.setLayout( new BorderLayout() );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		if ( this.getTitle() == null )
		{
			this.setTitle( "Default Title" );
		}

		this.setSize( 500, 500 );

		// Make the top panel
		pnl_top = new JPanel( new GridBagLayout() );
		GridBagConstraints c = new GridBagConstraints();

		// Add buttons, textfield
		txt_find = new JTextField( "Search..." );
		txt_find.setPreferredSize( new Dimension(100,30) );
		txt_find.setMargin( new Insets( 2, 10, 2, 2 ) );
		btn_find = new JButton( "Find" );

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.80;		
		c.insets = new Insets( 10, 10, 10, 10 );
		pnl_top.add( txt_find, c );
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.20;
		c.insets = new Insets( 10, 10, 10, 10 );
		pnl_top.add( btn_find, c );
		
		add(pnl_top, BorderLayout.NORTH);
	}

	public static void main( String[] args )
	{
		javax.swing.SwingUtilities.invokeLater( new Runnable()
		{
			@Override
			public void run()
			{
				source_searcher = new SourceSearcher( "Source Searcher" );
				source_searcher.setVisible( true );
			}
		} );
	}
}
