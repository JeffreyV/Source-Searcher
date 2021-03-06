package be.home.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import be.home.ui.components.AutocompletionTextField;
import be.home.ui.components.FileOverviewPanel;
import be.home.ui.components.SourceSearcherPopupMenu;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * 
 * @author Jeffrey Vandenborne
 * @since February 2010
 * @version 1.0
 */
public class SourceSearcher extends JFrame
{
    // TODO Constants
    private static final int             FRAME_WIDTH = 500;
    private static final int             FRAME_HEIGHT = 500;

    private JPanel                       pnl_top;
    private FileOverviewPanel            pnl_file_list;
    private AutocompletionTextField      txt_find;
    private JButton                      btn_find, btn_menu;
    private SourceSearcherPopupMenu      mnu_main_menu;
    public static SourceSearcher         source_searcher;

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

        this.setPreferredSize( new Dimension(FRAME_WIDTH,FRAME_HEIGHT));

        // Make the top panel
        pnl_top = new JPanel( new GridBagLayout() );
        GridBagConstraints c = new GridBagConstraints();

        // Add buttons, textfield
        txt_find = new AutocompletionTextField( "Search..." );
        txt_find.setPreferredSize( new Dimension( 100, 30 ) );
        txt_find.setMargin( new Insets( 2, 10, 2, 2 ) );
        btn_find = new JButton( "Find" );

        btn_menu = new JButton( "Menu >" );
        btn_menu.addActionListener( new ActionListener() {

            public void actionPerformed( ActionEvent e )
            {
                mnu_main_menu.show(source_searcher, btn_menu.getX() + btn_menu.getWidth() + 5, btn_menu.getY() + btn_menu.getHeight());
            }
        });

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.80;
        c.insets = new Insets( 10, 10, 10, 10 );
        pnl_top.add( txt_find, c );

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.10;
        c.insets = new Insets( 10, 10, 10, 10 );
        pnl_top.add( btn_find, c );

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 0;
        c.weightx = 0.10;
        c.insets = new Insets( 10, 10, 10, 10 );
        pnl_top.add( btn_menu, c);

        pnl_file_list = new FileOverviewPanel();

        mnu_main_menu = new SourceSearcherPopupMenu();

        add( pnl_file_list, BorderLayout.WEST );
        add( pnl_top, BorderLayout.NORTH );

        pack();
    }

    public static void main( String[] args )
    {
        try
        {
            UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
        }
        catch ( Exception e )
        {
            /*
             * Unexpected error
             */
            System.err.println( "Theming error: " + e.getMessage() );
        }

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
