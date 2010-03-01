/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.home.ui.components;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author jeffrey
 */
public class AutocompletionTextField extends JTextField
{
    private static final int MAX_LENGTH = 100;
    private static final int DOUBLE_CLICK_MS = 300;

    long timeMouseDown = 0;
    int lastX = 0, lastY = 0;
    private LimitedDocument doc;

    private class LimitedDocument extends PlainDocument
    {
        private int max;

        public LimitedDocument( int length )
        {
            if ( length > 0 )
            {
                this.max = length;
            }
            else
            {
                this.max = 0;
            }
        }

        public void setLength( int length )
        {
            this.max = length;
        }

        @Override
        public void insertString( int i, String string, AttributeSet as ) throws BadLocationException
        {
            if ( getLength() + string.length() > max )
            {
                java.awt.Toolkit.getDefaultToolkit().beep();
            }
            else
            {
                super.insertString( i, string, as );
            }
        }
    }

    private void supportFocus()
    {
        addFocusListener( new FocusAdapter()
        {
            @Override
            public void focusGained( FocusEvent fe )
            {
                selectAll();
            }
        } );
    }

    private void supportMouse()
    {
        addMouseListener( new MouseAdapter()
        {
            @Override
            public void mouseClicked( MouseEvent me )
            {
                long currentTime = me.getWhen();
                if ( (lastX == me.getX()) && (lastY == me.getY())
                        && ((currentTime - timeMouseDown) < DOUBLE_CLICK_MS) )
                {
                    // Select all text
                    selectAll();
                }
                else
                {
                    // Single click here
                    timeMouseDown = me.getWhen();
                    lastX = me.getX();
                    lastY = me.getY();
                }
            }
        } );
    }

    public AutocompletionTextField( int i )
    {
        super( i );
        this.setDocument( new LimitedDocument( MAX_LENGTH ) );

        // Add focus listener
        supportFocus();
        supportMouse();
    }

    public AutocompletionTextField( String string )
    {
        super( string );
        this.setDocument( new LimitedDocument( MAX_LENGTH ) );
        this.setText( string );

        supportFocus();
        supportMouse();
    }

    public AutocompletionTextField()
    {
        this.setDocument( new LimitedDocument( MAX_LENGTH ) );
        supportFocus();
        supportMouse();
    }
}
