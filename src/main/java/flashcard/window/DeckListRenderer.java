package flashcard.window;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import flashcard.entity.Deck;

 
/**
 * Custom renderer to display a country's flag alongside its name
 *
 * @author wwww.codejava.net
 */
public class DeckListRenderer extends JLabel implements ListCellRenderer<Deck> {
 
    /**
	 * generated serial ID
	 */
	private static final long serialVersionUID = 3943485454688000685L;

	public Component getListCellRendererComponent(JList<? extends Deck> list, Deck deck, int index,
        boolean isSelected, boolean cellHasFocus) {
       
    	if(deck.getDeckName().equals("<new deck>"))
    	{
    		setText(deck.getDeckName());
    	}
    	else
    	{
    		 setText("<html><b>" + deck.getDeckName() + "</b> <p>Due: " + deck.getAmountDue() + "</p></html>");
    	}
        setFont(new Font("Meiryo", Font.PLAIN, 15));
        if (isSelected) 
        {
        	setOpaque(true);
        	setBackground(list.getSelectionBackground());
        }
        
        return this;
    }
     
}