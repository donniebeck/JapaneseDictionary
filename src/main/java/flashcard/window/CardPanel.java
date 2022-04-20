package flashcard.window;

import javax.swing.JPanel;

import dictionary.controller.DictionaryWindow;
import flashcard.entity.Deck;
import flashcard.entity.Flashcard;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JEditorPane;

public class CardPanel extends JPanel
{
	/**
	 * generated serial ID
	 */
	private static final long serialVersionUID = 6845619490608157401L;
	private boolean frontSideShowing = false;
	private JEditorPane displayCardText;
	private JButton greatButton;
	private JButton goodButton;
	private JButton missButton;
	private JButton flipButton;
	private ArrayList<Flashcard> cards;
	/**
	 * Create the panel.
	 * @param deck 
	 */
	public CardPanel(Deck deck)
	{
		cards = DictionaryWindow.flashcards.fetchAllDueFromDeck(deck.getDeckID());
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{20, 250, 0, 250, 20, 0};
		gridBagLayout.rowHeights = new int[]{20, 200, 40, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		displayCardText = new JEditorPane();
		GridBagConstraints gbc_editorPane = new GridBagConstraints();
		gbc_editorPane.gridwidth = 3;
		gbc_editorPane.insets = new Insets(0, 0, 5, 5);
		gbc_editorPane.fill = GridBagConstraints.BOTH;
		gbc_editorPane.gridx = 1;
		gbc_editorPane.gridy = 1;
		add(displayCardText, gbc_editorPane);
		displayCardText.setEditable(false);
		displayCardText.setContentType("text/html");
		displayCardText.setFont(new Font("Meiryo", Font.PLAIN, 15));
		displayCardText.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, true);
		
		
		goodButton = new JButton("Good");
		goodButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextCard("good");
			}
		});
		GridBagConstraints gbc_goodButton = new GridBagConstraints();
		gbc_goodButton.insets = new Insets(0, 0, 5, 5);
		gbc_goodButton.gridx = 2;
		gbc_goodButton.gridy = 2;
		add(goodButton, gbc_goodButton);
		goodButton.setVisible(frontSideShowing);
		
		missButton = new JButton("Missed");
		missButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextCard("missed");
			}
		});
		GridBagConstraints gbc_missButton = new GridBagConstraints();
		gbc_missButton.anchor = GridBagConstraints.EAST;
		gbc_missButton.insets = new Insets(0, 0, 5, 5);
		gbc_missButton.gridx = 1;
		gbc_missButton.gridy = 2;
		add(missButton, gbc_missButton);
		missButton.setVisible(frontSideShowing);

		
		greatButton = new JButton("Great");
		greatButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nextCard("great");
			}
		});
		GridBagConstraints gbc_greatButton = new GridBagConstraints();
		gbc_greatButton.anchor = GridBagConstraints.WEST;
		gbc_greatButton.insets = new Insets(0, 0, 5, 5);
		gbc_greatButton.gridx = 3;
		gbc_greatButton.gridy = 2;
		add(greatButton, gbc_greatButton);
		greatButton.setVisible(frontSideShowing);
		
		
		GridBagConstraints gbc_flipButton = new GridBagConstraints();
		gbc_flipButton.insets = new Insets(0, 0, 5, 5);
		gbc_flipButton.gridx = 2;
		gbc_flipButton.gridy = 4;
		flipButton = new JButton("flip");
		flipButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toggleButtons();
				if(frontSideShowing)
				{
					displayCardText.setText("<html><center>" + cards.get(0).getBack());
				}
				else
				{
					displayCardText.setText("<html><center>" + cards.get(0).getFront());
				}
				
			}
		});
		add(flipButton, gbc_flipButton);
		
		
		setFirstCard();
	}
	
	private void setFirstCard()
	{
		if(cards.isEmpty())
		{
			flipButton.setVisible(false);
			displayCardText.setFont(new Font("Meiryo", Font.PLAIN, 40));
			displayCardText.setText("<html><center><b>No more cards to review</b></center></html>");
		}
		else
		{
			displayCardText.setText("<html><center>" + cards.get(0).getFront());
			displayCardText.setFont(new Font("Meiryo", Font.PLAIN, 32));
		}
	}
	private void toggleButtons()
	{
		frontSideShowing = !frontSideShowing;
		missButton.setVisible(frontSideShowing);
		goodButton.setVisible(frontSideShowing);
		greatButton.setVisible(frontSideShowing);
	}
	private void nextCard(String userInput)
	{
		if (userInput.equals("missed"))
		{
			toggleButtons();
			cards.get(0).miss();
			DictionaryWindow.flashcards.updateCard(cards.get(0));
			cards.add(cards.get(0));
			cards.remove(0);
			displayCardText.setText("<html><center>" + cards.get(0).getFront());
		} else if (userInput.equals("good"))
		{
			toggleButtons();
			cards.get(0).good();
			DictionaryWindow.flashcards.updateCard(cards.get(0));
			cards.remove(0);
			setFirstCard();
		} else if (userInput.equals("great"))
		{
			toggleButtons();
			cards.get(0).great();
			DictionaryWindow.flashcards.updateCard(cards.get(0));
			cards.remove(0);
			setFirstCard();
		}
	}
	
	

}
