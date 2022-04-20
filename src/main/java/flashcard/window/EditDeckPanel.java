package flashcard.window;

import javax.swing.JPanel;

import dictionary.controller.DictionaryWindow;
import flashcard.entity.Deck;
import flashcard.entity.Flashcard;

import java.awt.GridBagLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JScrollPane;

public class EditDeckPanel extends JPanel
{

	private static JList<Flashcard> cardsJList;
	private JButton editCardButton;
	private JButton deleteCardButton;
	private static JFrame newFlashcardWindow;
	/**
	 * generated serial id
	 */
	private static final long serialVersionUID = 825108392101304567L;
	private static Deck editingDeck;
	private JScrollPane scrollPane;
	/**
	 * Create the panel.
	 * @param deck 
	 */
	public EditDeckPanel(Deck deck)
	{
		editingDeck = deck;
		
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 500, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JButton deleteDeck = new JButton("Delete deck");
		deleteDeck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame areYouSureFrame = new JFrame();
				if(JOptionPane.showConfirmDialog(areYouSureFrame, "Are you sure?", "Delete", JOptionPane.YES_NO_OPTION)==0)
				{
					deleteDeck();
				}
			}
		});
		GridBagConstraints gbc_DeleteDeckButton = new GridBagConstraints();
		gbc_DeleteDeckButton.anchor = GridBagConstraints.WEST;
		gbc_DeleteDeckButton.insets = new Insets(0, 0, 5, 5);
		gbc_DeleteDeckButton.gridx = 0;
		gbc_DeleteDeckButton.gridy = 0;
		add(deleteDeck, gbc_DeleteDeckButton);
		
		editCardButton = new JButton("Edit card");
		editCardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editCard();
			}
		});
		GridBagConstraints gbc_editCardButton = new GridBagConstraints();
		gbc_editCardButton.anchor = GridBagConstraints.EAST;
		gbc_editCardButton.insets = new Insets(0, 0, 5, 5);
		gbc_editCardButton.gridx = 1;
		gbc_editCardButton.gridy = 0;
		add(editCardButton, gbc_editCardButton);
		editCardButton.setVisible(false);
		
		deleteCardButton = new JButton("Delete card");
		deleteCardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteCard();
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 0;
		add(deleteCardButton, gbc_btnNewButton);
		deleteCardButton.setVisible(false);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		
		cardsJList = new JList<Flashcard>();
		scrollPane.setViewportView(cardsJList);
		cardsJList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if(!arg0.getValueIsAdjusting())
				{
					if(cardsJList.getSelectedValue()!=null)
					{
						editCardButton.setVisible(true);
						deleteCardButton.setVisible(true);
					}
				}
			}
		});
		cardsJList.setCellRenderer(new CardListRenderer());
		
		
		updateList();

	}
	protected void editCard()
	{
		if (cardsJList.getSelectedValue() != null)
		{
			NewFlashcardPanel newFlashcardPanel = new NewFlashcardPanel(cardsJList.getSelectedValue());
			newFlashcardWindow = new JFrame("Create flashcard");
			newFlashcardWindow.setBounds(100, 100, 800, 800);
			newFlashcardWindow.setLocationRelativeTo(null);
			newFlashcardWindow.setVisible(true);
			newFlashcardWindow.getContentPane().add(newFlashcardPanel);
		}
	}
	private void deleteDeck()
	{
		DeckSelectionPanel.closeDeckEditor();
		DictionaryWindow.flashcards.deleteDeck(editingDeck);
	}
	private void deleteCard()
	{
		if (cardsJList.getSelectedValue() != null)
		{
			DictionaryWindow.flashcards.deleteCard(cardsJList.getSelectedValue());
			updateList();
		}
	}
	static void updateList()
	{
		DefaultListModel<Flashcard> listModel = new DefaultListModel<Flashcard>();
		ArrayList<Flashcard> flashcardArrayList = DictionaryWindow.flashcards.fetchAllCardsFromDeck(editingDeck.getDeckID());
		for (int i = 0; i < flashcardArrayList.size(); i++)
		{
			listModel.addElement(flashcardArrayList.get(i));
		}
		cardsJList.setModel(listModel);
	}
	public static void closeUpdateWindow()
	{
		newFlashcardWindow.setVisible(false);
		updateList();
	}

}
