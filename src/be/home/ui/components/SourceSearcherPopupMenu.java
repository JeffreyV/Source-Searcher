/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package be.home.ui.components;

import java.awt.Dimension;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author jeffrey
 */
public class SourceSearcherPopupMenu extends JPopupMenu
{
    private JMenu mnu_file, mnu_edit;

    // For the file menu
    private JMenuItem mnu_it_quit;

    public SourceSearcherPopupMenu()
    {
        createGUI();
    }

    private void createGUI()
    {
        mnu_file = new JMenu( "File" );
        mnu_edit = new JMenu( "Edit" );
        mnu_it_quit = new JMenuItem("Exit");

        setPreferredSize( new Dimension(100,100));
        add( mnu_file );
        add(mnu_edit);
        add( mnu_it_quit);
    }
}
