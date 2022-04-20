package dictionary.controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.LayoutStyle.ComponentPlacement;

import dictionary.entity.SearchEntry;
import dictionary.service.DictionaryService;
import flashcard.service.FlashcardService;
import flashcard.window.DeckSelectionPanel;
import flashcard.window.NewFlashcardPanel;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class DictionaryWindow
{

	private JFrame frame;
	private static JFrame newEntryWindow;
	private static JFrame deckSelectionWindow;
	private static JFrame newFlashcardWindow;
	private static JTextField searchField;
	private static JList<SearchEntry> list;
	private JTextPane entryPane;
	private JButton deleteEntryBtn;
	public static DictionaryService dictionary = new DictionaryService();
	public static FlashcardService flashcards = new FlashcardService();
	@SuppressWarnings("unused") //it is used
	private DefaultListModel<SearchEntry> listModel = new DefaultListModel<SearchEntry>();
	private JScrollPane searchSideScrollPane;
	private JScrollPane entrySideScrollPane;
	private JButton newEntryBtn;
	private JButton createFlashcardBtn;
	private JButton startFlashcardsBtn;
	private JMenuBar menuBar;
	private JMenu importMenu;
	private JMenu exportMenu;
	private JMenuItem selectDeckToExportMenuITem;
	private JMenuItem selectFileToImportFromMenu;
	private JMenu editDecksMenu;
	private JMenuItem selectDeckToEditMenuItem;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					DictionaryWindow window = new DictionaryWindow();
					window.frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DictionaryWindow()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new JFrame("Dictionary");
		frame.getContentPane().setFont(new Font("Meiryo", Font.PLAIN, 15));
		frame.setBounds(100, 100, 1000, 800);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		searchField = new JTextField();
		searchField.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
				if (!searchField.getText().isEmpty())
				{
					DefaultListModel<SearchEntry> listModel = new DefaultListModel<SearchEntry>();
					ArrayList<SearchEntry> arrayListSearchEntry = dictionary.search(searchField.getText());
					for (int i = 0 ; i < arrayListSearchEntry.size(); i++)
					{
						listModel.addElement(arrayListSearchEntry.get(i));
					}
					list.setModel(listModel);
				}
			}
			});
		searchField.setColumns(10);
		
		JButton searchBtn = new JButton("Search");
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!searchField.getText().isEmpty())
				{
					DefaultListModel<SearchEntry> listModel = new DefaultListModel<SearchEntry>();
					ArrayList<SearchEntry> arrayListSearchEntry = dictionary.search(searchField.getText());
					for (int i = 0 ; i < arrayListSearchEntry.size(); i++)
					{
						listModel.addElement(arrayListSearchEntry.get(i));
					}
					list.setModel(listModel);
				}
			}
		});
		
		searchSideScrollPane = new JScrollPane();
		
		entrySideScrollPane = new JScrollPane();
		
		deleteEntryBtn = new JButton("Delete Entry");
		deleteEntryBtn.setVisible(false);
		deleteEntryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(JOptionPane.showConfirmDialog(frame, "Are you sure?", "Delete", JOptionPane.YES_NO_OPTION)==0)
				{
					if(list.getSelectedValue()!=null)
					{
						dictionary.deleteEntry(dictionary.fetchFullByID(list.getSelectedValue().getEntry()));
						DefaultListModel<SearchEntry> listModel = new DefaultListModel<SearchEntry>();
						ArrayList<SearchEntry> arrayListSearchEntry = dictionary.search(searchField.getText());
						for (int i = 0 ; i < arrayListSearchEntry.size(); i++)
						{
							listModel.addElement(arrayListSearchEntry.get(i));
						}
						if (arrayListSearchEntry.size() == 0)
						{
							listModel.addElement(new SearchEntry());
						}
						list.setModel(listModel);
					}
				}
				
			}
		});
		
		newEntryBtn = new JButton("New entry");
		newEntryBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewEntryPanel newEntryPanel = new NewEntryPanel();
				newEntryWindow = new JFrame("New entry");
				newEntryWindow.setBounds(100, 100, 500, 500);
				newEntryWindow.setLocationRelativeTo(null);
				newEntryWindow.setVisible(true);
				newEntryWindow.getContentPane().add(newEntryPanel);
				newEntryWindow.setFont(new Font("Meiryo", Font.PLAIN, 15));
			}
		});
		
		createFlashcardBtn = new JButton("Turn into flashcard");
		createFlashcardBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewFlashcardPanel newFlashcardPanel = new NewFlashcardPanel(dictionary.fetchFullByID(list.getSelectedValue().getEntry()));
				newFlashcardWindow = new JFrame("Create flashcard");
				newFlashcardWindow.setBounds(100, 100, 800, 800);
				newFlashcardWindow.setLocationRelativeTo(null);
				newFlashcardWindow.setVisible(true);
				newFlashcardWindow.getContentPane().add(newFlashcardPanel);
			}
		});
		createFlashcardBtn.setVisible(false);
		
		startFlashcardsBtn = new JButton("Start studying");
		startFlashcardsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DeckSelectionPanel deckSelector = new DeckSelectionPanel();
				deckSelectionWindow = new JFrame("Deck Collection");
				deckSelectionWindow.setBounds(100, 100, 400, 400);
				deckSelectionWindow.setLocationRelativeTo(null);
				deckSelectionWindow.setVisible(true);
				deckSelectionWindow.getContentPane().add(deckSelector);
			}
		});
	
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(searchField)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(searchBtn, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
						.addComponent(searchSideScrollPane, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 449, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(entrySideScrollPane, GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(startFlashcardsBtn, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(createFlashcardBtn)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(newEntryBtn)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(deleteEntryBtn)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(searchField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(searchBtn)
						.addComponent(deleteEntryBtn)
						.addComponent(newEntryBtn)
						.addComponent(createFlashcardBtn)
						.addComponent(startFlashcardsBtn))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(entrySideScrollPane, GroupLayout.DEFAULT_SIZE, 711, Short.MAX_VALUE)
						.addComponent(searchSideScrollPane, GroupLayout.DEFAULT_SIZE, 711, Short.MAX_VALUE)))
		);
		
		entryPane = new JTextPane();
		entrySideScrollPane.setViewportView(entryPane);
		entryPane.setContentType("text/html");
		entryPane.setFont(new Font("Meiryo", Font.PLAIN, 15));
		entryPane.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, true);
		entryPane.setEditable(false);
		

		
		list = new JList<SearchEntry>();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if(!arg0.getValueIsAdjusting())
				{
					if(list.getSelectedValue()!=null)
					{
						entryPane.setText(dictionary.fetchFullHTMLByID(list.getSelectedValue().getEntry()));
						entryPane.setCaretPosition(0);
						deleteEntryBtn.setVisible(true);
						createFlashcardBtn.setVisible(true);
						newEntryBtn.setVisible(true);
					}
				}
			}
		});
		searchSideScrollPane.setViewportView(list);
		list.setFont(new Font("Meiryo", Font.PLAIN, 15));
		frame.getContentPane().setLayout(groupLayout);
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		importMenu = new JMenu("Import Deck");
		menuBar.add(importMenu);
		
		selectFileToImportFromMenu = new JMenuItem("Import .deck file");
		selectFileToImportFromMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				importDeck();
			}
		});
		importMenu.add(selectFileToImportFromMenu);
		
		exportMenu = new JMenu("Export Deck");
		menuBar.add(exportMenu);
		
		selectDeckToExportMenuITem = new JMenuItem("Select deck");
		selectDeckToExportMenuITem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeckSelectionPanel deckSelector = new DeckSelectionPanel("export");
				deckSelectionWindow = new JFrame("Deck Collection");
				deckSelectionWindow.setBounds(100, 100, 400, 400);
				deckSelectionWindow.setLocationRelativeTo(null);
				deckSelectionWindow.setVisible(true);
				deckSelectionWindow.getContentPane().add(deckSelector);
			}
		});
		exportMenu.add(selectDeckToExportMenuITem);
		
		editDecksMenu = new JMenu("Edit decks");
		menuBar.add(editDecksMenu);
		
		selectDeckToEditMenuItem = new JMenuItem("Select deck");
		selectDeckToEditMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DeckSelectionPanel deckSelector = new DeckSelectionPanel("edit");
				deckSelectionWindow = new JFrame("Deck Collection");
				deckSelectionWindow.setBounds(100, 100, 400, 400);
				deckSelectionWindow.setLocationRelativeTo(null);
				deckSelectionWindow.setVisible(true);
				deckSelectionWindow.getContentPane().add(deckSelector);
			}
		});
		editDecksMenu.add(selectDeckToEditMenuItem);
	}

	public static void closeNewEntryFrame()
	{
		newEntryWindow.setVisible(false);
		if (!searchField.getText().isEmpty())
		{
			DefaultListModel<SearchEntry> listModel = new DefaultListModel<SearchEntry>();
			ArrayList<SearchEntry> arrayListSearchEntry = dictionary.search(searchField.getText());
			for (int i = 0 ; i < arrayListSearchEntry.size(); i++)
			{
				listModel.addElement(arrayListSearchEntry.get(i));
			}
			list.setModel(listModel);
		}
	}
	
	public static void closeNewFlashcardFrame()
	{
		newFlashcardWindow.setVisible(false);
	}

	public static void closeDeckSelectionFrame()
	{
		deckSelectionWindow.setVisible(false);
	}
	protected void importDeck()
	{
		JFrame importWindow = new JFrame();
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Choose a deck to import");
		FileNameExtensionFilter deckFilter = new FileNameExtensionFilter("deck files (*.deck)", "deck");
		fileChooser.setFileFilter(deckFilter);
        int option = fileChooser.showOpenDialog(importWindow);
        if(option == JFileChooser.APPROVE_OPTION){
           File file = fileChooser.getSelectedFile();
           DictionaryWindow.flashcards.importDeck(file);
        }else{
           System.out.println("import cancelled");
        }
	}
}
