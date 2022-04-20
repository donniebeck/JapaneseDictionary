package flashcard.window;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import flashcard.entity.Flashcard;

 
/**
 * Custom renderer to display a country's flag alongside its name
 *
 * @author wwww.codejava.net
 */
public class CardListRenderer extends JLabel implements ListCellRenderer<Flashcard> {
 
    /**
	 * generated serial ID
	 */
	private static final long serialVersionUID = 3943485454688000685L;

	public Component getListCellRendererComponent(JList<? extends Flashcard> list, Flashcard flashcard, int index,
        boolean isSelected, boolean cellHasFocus) {
    	setOpaque(true);
		setText("<html>" + flashcard.getFront() + "<br/>" + flashcard.getBack() + "</html>");
        setFont(new Font("Meiryo", Font.PLAIN, 15));
        if (isSelected) 
        {
        	setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground()); 
        }
        else
        {
        	setBackground(list.getBackground());
            setForeground(list.getForeground()); 
        }
        
        return this;
    }
     
}