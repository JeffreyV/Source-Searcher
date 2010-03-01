package be.home.classes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleRegex
{
	public static String getFileExtension( String filename )
	{
		Pattern p = Pattern.compile( "^.*[.]" );
		Matcher m = p.matcher( filename );
		
		return m.replaceFirst( "" );
	}
}
