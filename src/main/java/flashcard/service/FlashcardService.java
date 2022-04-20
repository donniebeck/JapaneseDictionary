package flashcard.service;

import java.io.File;
import java.util.ArrayList;

import flashcard.dao.FlashcardDAO;
import flashcard.entity.Deck;
import flashcard.entity.Flashcard;

public class FlashcardService
{
	FlashcardDAO dao;
	
	public FlashcardService()
	{
		dao = new FlashcardDAO();
	}
	public void close()
	{
		dao.closeDAO();
	}
	public ArrayList<Flashcard> fetchAllCardsFromDeck(int deckID)
	{
		return dao.fetchAllFromDeck(deckID);
	}
	public ArrayList<Deck> getAllDecks()
	{
		return dao.fetchAllDecks();
	}
	public void insertCardIntoDeck(Flashcard card)
	{
		dao.insertCardIntoDeck(card);
	}
	public ArrayList<Flashcard> fetchAllDueFromDeck(int deckID)
	{
		return dao.fetchAllDueFromDeck(deckID);
	}
	public int createDeck(String deckName)
	{
		return dao.createDeck(deckName);
	}
	public void updateCard(Flashcard card)
	{
		dao.updateCard(card);
	}
	public void exportDeck(Deck selectedDeck, File exportDir)
	{
		dao.exportDeck(selectedDeck, exportDir);
	}
	public void importDeck(File file)
	{
		dao.importDeck(file);
		
	}
	public void deleteDeck(Deck deck)
	{
		dao.deleteDeck(deck);
	}
	public void deleteCard(Flashcard flashcard)
	{
		dao.deleteCard(flashcard);
		
	}
}
