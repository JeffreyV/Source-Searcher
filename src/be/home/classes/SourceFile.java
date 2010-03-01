package be.home.classes;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class SourceFile extends File
{
	private File	db_valid_extensions;
	private boolean	valid_source;
	private String	source_language;
	private Image	icon;

	public SourceFile( String pathname, String extension_config )
	{
		super( pathname );
		valid_source = false;
		db_valid_extensions = new File( extension_config );
		check_source();
	}

	public SourceFile( URI uri, String extension_config  )
	{
		super( uri );
		valid_source = false;
		db_valid_extensions = new File( extension_config );
		check_source( );
	}
	
	public boolean isValidSource()
	{
		return valid_source;
	}
	
	public String getLanguage()
	{
		return this.source_language;
	}

	private void check_source()
	{
		try
		{
			if ( db_valid_extensions.isFile() && db_valid_extensions.canRead() && this.isFile() && this.canRead() )
			{
				String extension = SimpleRegex.getFileExtension( this.getName() );				
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document doc = null;
				
				// Parse the document
				doc = builder.parse( db_valid_extensions );
				
				// Get all nodes
				NodeList ext_list = doc.getElementsByTagName( "language" );
				for ( int i = 0; i < ext_list.getLength(); ++i )
				{
					Element e = (Element) ext_list.item( i );
					NodeList nodes = e.getElementsByTagName( "name" );
					NodeList extensions = e.getElementsByTagName( "extension" );
					
					for (int j = 0; j < extensions.getLength(); j++ )
					{						
						if ( extensions.item( j ).getFirstChild().getNodeValue().equals( extension ) )
						{
							this.source_language = nodes.item( 0 ).getFirstChild().getNodeValue();
							this.valid_source = true;
							System.out.println( System.getProperty( "user.dir"  ) + "/" +e.getElementsByTagName( "icon" ).item( 0 ).getFirstChild().getNodeValue() );
							Image im = ImageIO.read( new File( System.getProperty( "user.dir"  ) + "/" + 
									e.getElementsByTagName( "icon" ).item( 0 ).getFirstChild().getNodeValue() ) );
							
							this.icon = im.getScaledInstance( 30, 30, 0 );
							return;
						}
					}
				}
				
				this.source_language = null;
				this.icon = null;
				this.valid_source = false;
			}
			else
			{
				this.source_language = null;
				this.icon = null;
				this.valid_source = false;
				
				// Throw exception
				throw new IOException( "Not a valid extension file or not readable. \nCheck permissions." );
			}
		} catch ( IOException e )
		{
			System.err.println("IOException: " + e.getMessage());
		}
		catch ( Exception e )
		{
			System.err.println("Other exception: " + e.getMessage());
		}
	}

	public Image getIcon()
	{
		return this.icon;
	}
}
