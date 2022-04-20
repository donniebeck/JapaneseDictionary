package flashcard.window;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import dictionary.controller.DictionaryWindow;
import flashcard.entity.Deck;

import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class DeckSelectionPanel extends JPanel
{

	/**
	 * generated serial ID
	 */
	private static final long serialVersionUID = 4649236320117730167L;
	JList<Deck> deckList;
	ArrayList<String> deckListNames;
	DefaultListModel<Deck> listModel;
	JLabel selectDeckLabel;
	static JFrame editDeckWindow;
	/**
	 * Create the panel.
	 */
	public DeckSelectionPanel()
	{
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("default:grow"),}));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "1, 1, fill, fill");
		
		deckList = new JList<Deck>();
		deckList.addMouseListener(new MouseAdapter() {
			//This method checks if the mouse clicks happens on a list element or below; kind of annoying this is necessary but oh well
			private boolean processEvent(MouseEvent arg0) {
                int index = deckList.locationToIndex(arg0.getPoint());
                return index > -1 && deckList.getCellBounds(index, index).contains(arg0.getPoint());
            }
			public void mouseClicked(MouseEvent arg0) {
				if(processEvent(arg0))
				{
					//create a new deck
					if(deckList.getSelectedValue().toString().equals("<new deck>"))
					{
						JFrame frame = new JFrame();
					    String message = "Deck Name";
					    String deckName = JOptionPane.showInputDialog(frame, message);
					    if (deckName != null && !deckListNames.contains(deckName))
					    {
					    	DictionaryWindow.flashcards.createDeck(deckName);
					    	listModel = new DefaultListModel<Deck>();
							ArrayList<Deck> allDecks = DictionaryWindow.flashcards.getAllDecks();
							deckListNames = new ArrayList<String>();
							for (int i = 0 ; i < allDecks.size(); i++)
							{
								listModel.addElement(allDecks.get(i));
								deckListNames.add(allDecks.get(i).toString());
							}
							Deck newDeck = new Deck();
							newDeck.setDeckName("<new deck>");
							deckListNames.add("<new deck>");
							listModel.addElement(newDeck);
							deckList.setModel(listModel);
					    }
					}else
					{
						openDeck(deckList.getSelectedValue());
					}
					
				}
				
			}
		});
		scrollPane.setViewportView(deckList);
		deckList.setFont(new Font("Meiryo", Font.PLAIN, 15));
		listModel = new DefaultListModel<Deck>();
		ArrayList<Deck> allDecks = DictionaryWindow.flashcards.getAllDecks();
		deckListNames = new ArrayList<String>();
		for (int i = 0 ; i < allDecks.size(); i++)
		{
			listModel.addElement(allDecks.get(i));
			deckListNames.add(allDecks.get(i).toString());
		}
		Deck newDeck = new Deck();
		newDeck.setDeckName("<new deck>");
		deckListNames.add("<new deck>");
		listModel.addElement(newDeck);
		deckList.setModel(listModel);
		deckList.setCellRenderer(new DeckListRenderer());
		
		
		
		JLabel selectDeckLabel = new JLabel("Select a deck to study with", SwingConstants.CENTER);
		selectDeckLabel.setFont(new Font("Meiryo", Font.PLAIN, 25));
		scrollPane.setColumnHeaderView(selectDeckLabel);

	}
	public DeckSelectionPanel(String operationType)
	{
		setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				RowSpec.decode("default:grow"),}));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "1, 1, fill, fill");
		deckList = new JList<Deck>();
		
		if(operationType.equals("export"))
		{
			selectDeckLabel = new JLabel("Select a deck to export", SwingConstants.CENTER);
			deckList.addMouseListener(new MouseAdapter() {
				//This method checks if the mouse clicks happens on a list element or below; kind of annoying this is necessary but oh well
				private boolean processEvent(MouseEvent arg0) {
	                int index = deckList.locationToIndex(arg0.getPoint());
	                return index > -1 && deckList.getCellBounds(index, index).contains(arg0.getPoint());
	            }
				public void mouseClicked(MouseEvent arg0) {
					if(processEvent(arg0))
					{
						exportDeck(deckList.getSelectedValue());
					}
				}
			});
		}
		else if (operationType.equals("edit"))
		{
			selectDeckLabel = new JLabel("Select a deck to edit", SwingConstants.CENTER);
			deckList.addMouseListener(new MouseAdapter() {
				//This method checks if the mouse clicks happens on a list element or below; kind of annoying this is necessary but oh well
				private boolean processEvent(MouseEvent arg0) {
	                int index = deckList.locationToIndex(arg0.getPoint());
	                return index > -1 && deckList.getCellBounds(index, index).contains(arg0.getPoint());
	            }
				public void mouseClicked(MouseEvent arg0) {
					if(processEvent(arg0))
					{
						editDeck(deckList.getSelectedValue());
					}
				}
			});
		}
		
		
		scrollPane.setViewportView(deckList);
		deckList.setFont(new Font("Meiryo", Font.PLAIN, 15));
		listModel = new DefaultListModel<Deck>();
		ArrayList<Deck> allDecks = DictionaryWindow.flashcards.getAllDecks();
		deckListNames = new ArrayList<String>();
		for (int i = 0 ; i < allDecks.size(); i++)
		{
			listModel.addElement(allDecks.get(i));
		}
		deckList.setModel(listModel);
		
		
		
		
		selectDeckLabel.setFont(new Font("Meiryo", Font.PLAIN, 25));
		scrollPane.setColumnHeaderView(selectDeckLabel);

	}
	protected void editDeck(Deck deck)
	{
		JPanel editDeckPanel = new EditDeckPanel(deck);
		editDeckWindow = new JFrame();
		editDeckWindow.setBounds(100, 100, 800, 550);
		editDeckWindow.setLocationRelativeTo(null);
		editDeckWindow.setVisible(true);
		editDeckWindow.getContentPane().add(editDeckPanel);
		DictionaryWindow.closeDeckSelectionFrame();
	}
	protected void exportDeck(Deck selectedDeck)
	{
		JFrame exportWindow = new JFrame();
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Choose a location to export to");
		FileNameExtensionFilter deckFilter = new FileNameExtensionFilter("deck files (*.deck)", "deck");
		fileChooser.setFileFilter(deckFilter);
		fileChooser.setSelectedFile(new File(selectedDeck.getDeckName()+".deck"));
        int option = fileChooser.showOpenDialog(exportWindow);
        if(option == JFileChooser.APPROVE_OPTION){
           File file = fileChooser.getSelectedFile();
           DictionaryWindow.flashcards.exportDeck(selectedDeck, file);
        }else{
           System.out.println("export cancelled");
        }
		DictionaryWindow.closeDeckSelectionFrame();
	}
	protected void openDeck(Deck deck)
	{
		CardPanel cardPanel = new CardPanel(deck);
		JFrame cardWindow = new JFrame(deck.toString());
		cardWindow.setBounds(100, 100, 800, 550);
		cardWindow.setLocationRelativeTo(null);
		cardWindow.setVisible(true);
		cardWindow.getContentPane().add(cardPanel);
		DictionaryWindow.closeDeckSelectionFrame();
	}
	public static void closeDeckEditor()
	{
		editDeckWindow.setVisible(false);
	}

}
