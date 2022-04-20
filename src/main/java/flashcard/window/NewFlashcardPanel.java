package flashcard.window;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JTextArea;

import dictionary.controller.DictionaryWindow;
import dictionary.entity.FullEntry;
import flashcard.entity.Deck;
import flashcard.entity.Flashcard;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewFlashcardPanel extends JPanel
{
	/**
	 * generated serial ID
	 */
	private static final long serialVersionUID = 1269960842978632830L;
	private JTextArea frontSideTextArea;
	private JTextArea backSideTextArea;
	private Flashcard editedFlashcard;
	private JComboBox<Deck> deckSelectionComboBox;
	/**
	 * Create the panel.
	 * @param fullEntry 
	 * @param flashcards 
	 */
	/**
	 * @wbp.parser.constructor
	 */
	public NewFlashcardPanel(FullEntry fullEntry)
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{20, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel deckSelectionLabel = new JLabel("Deck: ");
		GridBagConstraints gbc_deckSelectionLabel = new GridBagConstraints();
		gbc_deckSelectionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_deckSelectionLabel.anchor = GridBagConstraints.EAST;
		gbc_deckSelectionLabel.gridx = 1;
		gbc_deckSelectionLabel.gridy = 1;
		add(deckSelectionLabel, gbc_deckSelectionLabel);
		deckSelectionLabel.setFont(new Font("Meiryo", Font.PLAIN, 15));
		
		deckSelectionComboBox = new JComboBox<Deck>();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 2;
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 1;
		add(deckSelectionComboBox, gbc_comboBox);
		deckSelectionComboBox.setFont(new Font("Meiryo", Font.PLAIN, 15));
		ArrayList<Deck> decks = DictionaryWindow.flashcards.getAllDecks();
		for (Deck deck : decks)
		{
			deckSelectionComboBox.addItem(deck);
		}
		Deck newDeck = new Deck();
		newDeck.setDeckName("<new deck>");
		deckSelectionComboBox.addItem(newDeck);
		
		JLabel frontSideLabel = new JLabel("Front");
		GridBagConstraints gbc_frontSideLabel = new GridBagConstraints();
		gbc_frontSideLabel.insets = new Insets(0, 0, 5, 5);
		gbc_frontSideLabel.gridx = 1;
		gbc_frontSideLabel.gridy = 2;
		add(frontSideLabel, gbc_frontSideLabel);
		frontSideLabel.setFont(new Font("Meiryo", Font.PLAIN, 15));
		
		frontSideTextArea = new JTextArea(fullEntry.getKanjiList().toString().substring(1, fullEntry.getKanjiList().toString().length()-1));
		GridBagConstraints gbc_frontSideTextArea = new GridBagConstraints();
		gbc_frontSideTextArea.gridwidth = 2;
		gbc_frontSideTextArea.insets = new Insets(0, 0, 5, 5);
		gbc_frontSideTextArea.fill = GridBagConstraints.BOTH;
		gbc_frontSideTextArea.gridx = 2;
		gbc_frontSideTextArea.gridy = 2;
		add(frontSideTextArea, gbc_frontSideTextArea);
		frontSideTextArea.setFont(new Font("Meiryo", Font.PLAIN, 24));
		frontSideTextArea.setLineWrap(true);
		
		JLabel backSideLabel = new JLabel("Back");
		GridBagConstraints gbc_backSideLabel = new GridBagConstraints();
		gbc_backSideLabel.insets = new Insets(0, 0, 5, 5);
		gbc_backSideLabel.gridx = 1;
		gbc_backSideLabel.gridy = 3;
		add(backSideLabel, gbc_backSideLabel);
		backSideLabel.setFont(new Font("Meiryo", Font.PLAIN, 15));
		
		backSideTextArea = new JTextArea(fullEntry.getDefinitionList().toString().substring(1, fullEntry.getDefinitionList().toString().length()-1));
		GridBagConstraints gbc_backSideTextArea = new GridBagConstraints();
		gbc_backSideTextArea.gridwidth = 2;
		gbc_backSideTextArea.insets = new Insets(0, 0, 5, 5);
		gbc_backSideTextArea.fill = GridBagConstraints.BOTH;
		gbc_backSideTextArea.gridx = 2;
		gbc_backSideTextArea.gridy = 3;
		add(backSideTextArea, gbc_backSideTextArea);
		backSideTextArea.setFont(new Font("Meiryo", Font.PLAIN, 24));
		backSideTextArea.setLineWrap(true);
		
		JButton createFlashcardBtn = new JButton("Create");
		createFlashcardBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (((Deck) deckSelectionComboBox.getSelectedItem()).getDeckName().equals("<new deck>"))
				{
					JFrame frame = new JFrame();
				    String message = "Deck Name";
				    String deckName = JOptionPane.showInputDialog(frame, message);
				    if (deckName != null)
				    {
				    	int deckID = DictionaryWindow.flashcards.createDeck(deckName);
				    	Deck newlyCreatedDeck = new Deck();
				    	newlyCreatedDeck.setDeckID(deckID);
				    	newlyCreatedDeck.setDeckName(deckName);
				    	deckSelectionComboBox.addItem(newlyCreatedDeck);
				    	deckSelectionComboBox.setSelectedItem(newlyCreatedDeck);
				    }
				}
				else
				{
					Flashcard newCard = new Flashcard();
					newCard.setDeckID(((Deck) deckSelectionComboBox.getSelectedItem()).getDeckID());
					newCard.setFront(frontSideTextArea.getText());
					newCard.setBack(backSideTextArea.getText());
					DictionaryWindow.flashcards.insertCardIntoDeck(newCard);
					DictionaryWindow.closeNewFlashcardFrame();
				}
			}
		});
		GridBagConstraints gbc_createFlashcardBtn = new GridBagConstraints();
		gbc_createFlashcardBtn.insets = new Insets(0, 0, 5, 5);
		gbc_createFlashcardBtn.gridx = 2;
		gbc_createFlashcardBtn.gridy = 4;
		add(createFlashcardBtn, gbc_createFlashcardBtn);
		createFlashcardBtn.setFont(new Font("Meiryo", Font.PLAIN, 15));
	}
	public NewFlashcardPanel(Flashcard flashcard)
	{
		editedFlashcard = flashcard;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{20, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel frontSideLabel = new JLabel("Front");
		GridBagConstraints gbc_frontSideLabel = new GridBagConstraints();
		gbc_frontSideLabel.insets = new Insets(0, 0, 5, 5);
		gbc_frontSideLabel.gridx = 1;
		gbc_frontSideLabel.gridy = 1;
		add(frontSideLabel, gbc_frontSideLabel);
		frontSideLabel.setFont(new Font("Meiryo", Font.PLAIN, 15));
		
		frontSideTextArea = new JTextArea(flashcard.getFront());
		GridBagConstraints gbc_frontSideTextArea = new GridBagConstraints();
		gbc_frontSideTextArea.gridwidth = 2;
		gbc_frontSideTextArea.insets = new Insets(0, 0, 5, 5);
		gbc_frontSideTextArea.fill = GridBagConstraints.BOTH;
		gbc_frontSideTextArea.gridx = 2;
		gbc_frontSideTextArea.gridy = 1;
		add(frontSideTextArea, gbc_frontSideTextArea);
		frontSideTextArea.setFont(new Font("Meiryo", Font.PLAIN, 24));
		frontSideTextArea.setLineWrap(true);
		
		JLabel backSideLabel = new JLabel("Back");
		GridBagConstraints gbc_backSideLabel = new GridBagConstraints();
		gbc_backSideLabel.insets = new Insets(0, 0, 5, 5);
		gbc_backSideLabel.gridx = 1;
		gbc_backSideLabel.gridy = 2;
		add(backSideLabel, gbc_backSideLabel);
		backSideLabel.setFont(new Font("Meiryo", Font.PLAIN, 15));
		
		backSideTextArea = new JTextArea(flashcard.getBack());
		GridBagConstraints gbc_backSideTextArea = new GridBagConstraints();
		gbc_backSideTextArea.gridwidth = 2;
		gbc_backSideTextArea.insets = new Insets(0, 0, 5, 5);
		gbc_backSideTextArea.fill = GridBagConstraints.BOTH;
		gbc_backSideTextArea.gridx = 2;
		gbc_backSideTextArea.gridy = 2;
		add(backSideTextArea, gbc_backSideTextArea);
		backSideTextArea.setFont(new Font("Meiryo", Font.PLAIN, 24));
		backSideTextArea.setLineWrap(true);
		
		JButton createFlashcardBtn = new JButton("Update");
		createFlashcardBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				editedFlashcard.setFront(frontSideTextArea.getText());
				editedFlashcard.setBack(backSideTextArea.getText());
				DictionaryWindow.flashcards.updateCard(editedFlashcard);
				EditDeckPanel.closeUpdateWindow();
			}
		});
		GridBagConstraints gbc_createFlashcardBtn = new GridBagConstraints();
		gbc_createFlashcardBtn.insets = new Insets(0, 0, 5, 5);
		gbc_createFlashcardBtn.gridx = 2;
		gbc_createFlashcardBtn.gridy = 3;
		add(createFlashcardBtn, gbc_createFlashcardBtn);
		createFlashcardBtn.setFont(new Font("Meiryo", Font.PLAIN, 15));
	}

}
