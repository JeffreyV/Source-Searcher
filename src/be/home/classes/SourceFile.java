package be.home.classes;

import java.awt.Image;
import java.io.File;
import java.net.URI;

public class SourceFile extends File
{
	private boolean valid_source;
	private String	source_language;
	private Image	icon;

	public SourceFile( File parent, String child )
	{
		super( parent, child );
	}

	public SourceFile( String parent, String child )
	{
		super( parent, child );
	}

	public SourceFile( String pathname )
	{
		super( pathname );
		valid_source = false;
		check_source(pathname);
	}

	public SourceFile( URI uri )
	{
		super( uri );
		valid_source = false;
		check_source(uri.toString());
	}
	
	private void check_source( String filename )
	{
		// TODO Create check for file sources
	}

	public Image getIcon()
	{
		return this.icon;
	}
}
