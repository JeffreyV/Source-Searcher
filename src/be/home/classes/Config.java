package be.home.classes;

import java.io.File;
import java.io.IOException;

public class Config
{
	private static String config_base = System.getProperty( "user.dir" ) + "/config/";
	
	public static File get(String name)
	{
		try
		{
			File file = new File( config_base + name );
			if ( file.exists() && file.isFile() && file.canRead() )
			{
				return file;
			}
			else
			{
				throw new IOException("File is not valid.");
			}
		} catch ( Exception e )
		{
			System.err.println("IOError: " + e.getMessage());
			return null;
		}
	}
}
