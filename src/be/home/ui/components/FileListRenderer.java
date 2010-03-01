package be.home.ui.components;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;

import be.home.classes.SourceFile;

public class FileListRenderer extends JLabel implements ListCellRenderer
{
	private static final Color CLR_BG_HIGHLIGHT = Color.GRAY;
	private static final Color CLR_FG_HIGHLIGHT = Color.WHITE;
	
	public FileListRenderer()
	{
		setOpaque( true );
		setIconTextGap( 12 );
	}
	
	@Override
	public Component getListCellRendererComponent( JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus )
	{
		// Workaround to create insets
		setBorder( BorderFactory.createEmptyBorder( 10,10,10,10) );
		
		if ( value instanceof SourceFile )
		{
			SourceFile file = (SourceFile) value;
			this.setText( file.getName() );
			this.setIcon( new ImageIcon(file.getIcon()) );
		}
		else
		{
			this.setText( "Invalid item." );
		}
		
		if (isSelected)
		{
			this.setBackground( (Color) UIManager.get("List.selectionBackground") );
			this.setForeground( CLR_FG_HIGHLIGHT );
		}
		else
		{
			this.setBackground( Color.WHITE );
			this.setForeground( Color.BLACK );
		}
		return this;
	}
}
