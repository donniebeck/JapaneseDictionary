package flashcard.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import flashcard.entity.Deck;
import flashcard.entity.Flashcard;

public class FlashcardDAO
{
	private static final String FLASHCARDFILE = System.getProperty("user.dir") + "\\flashcardcollection\\flashcard_collection.db";
	private static final String JDBCURL = "jdbc:sqlite:/"+FLASHCARDFILE;
	static Connection connection;

	
	public FlashcardDAO()
	{
		try
		{
			connection = DriverManager.getConnection(JDBCURL);
		} catch (SQLException e)
		{
			System.out.println("There was an error creating the data access object.");
			e.printStackTrace();
		}
	}
	
	public void closeDAO()
	{
		try
		{
			connection.close();
		} catch (SQLException e)
		{
			System.out.println("There was an error closing the database connection."); 
			e.printStackTrace();
		}
	}

	public ArrayList<Flashcard> fetchAllFromDeck(int deckID)
	{
		ArrayList<Flashcard> allCards = new ArrayList<Flashcard>();
		try
		{
			String query = "SELECT * FROM cards WHERE deckID = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, deckID);
			ResultSet result = statement.executeQuery();
			while(result.next())
			{
				Flashcard temp = new Flashcard();
				temp.setCardID(result.getInt("cardID"));
				temp.setDeckID(result.getInt("deckID"));
				temp.setFront(result.getString("front"));
				temp.setBack(result.getString("back"));
				temp.setDue(result.getString("due"));
				temp.setFrequencyMultiplier(result.getDouble("freq_mult"));
				temp.setStreak(result.getInt("streak"));
				allCards.add(temp);
			}
			
		} catch (SQLException e)
		{
			System.out.println("There was an error selecting all cards");
			e.printStackTrace();
		}
		return allCards;
	}
	public ArrayList<Flashcard> fetchAllDueFromDeck(int deckID)
	{
		ArrayList<Flashcard> dueCards = new ArrayList<Flashcard>();
		try
		{
			String query = "SELECT * FROM cards WHERE deckID = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, deckID);
			ResultSet result = statement.executeQuery();
			while(result.next())
			{
				Flashcard temp = new Flashcard();
				temp.setCardID(result.getInt("cardID"));
				temp.setDeckID(result.getInt("deckID"));
				temp.setFront(result.getString("front"));
				temp.setBack(result.getString("back"));
				temp.setDue(result.getString("due"));
				temp.setFrequencyMultiplier(result.getDouble("freq_mult"));
				temp.setStreak(result.getInt("streak"));
				if(temp.isDue())
				{
					dueCards.add(temp);
				}
			}
			
		} catch (SQLException e)
		{
			System.out.println("There was an error fetching all the cards due for the deck");
			e.printStackTrace();
		}
		return dueCards;
	}
	public int getAmountDueFromDeck(int deckID)
	{
		ArrayList<Flashcard> dueCards = new ArrayList<Flashcard>();
		try
		{
			String query = "SELECT * FROM cards WHERE deckID = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, deckID);
			ResultSet result = statement.executeQuery();
			while(result.next())
			{
				Flashcard temp = new Flashcard();
				temp.setCardID(result.getInt("cardID"));
				temp.setDeckID(result.getInt("deckID"));
				temp.setFront(result.getString("front"));
				temp.setBack(result.getString("back"));
				temp.setDue(result.getString("due"));
				temp.setFrequencyMultiplier(result.getDouble("freq_mult"));
				temp.setStreak(result.getInt("streak"));
				if(temp.isDue())
				{
					dueCards.add(temp);
				}
			}
			
		} catch (SQLException e)
		{
			System.out.println("There was an error fetching all the cards due for the deck");
			e.printStackTrace();
		}
		return dueCards.size();
	}

	public ArrayList<Deck> fetchAllDecks()
	{
		ArrayList<Deck> allDecks = new ArrayList<Deck>();
		try
		{
			String query = "SELECT * FROM deck";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while(result.next())
			{
				Deck temp = new Deck();
				temp.setDeckID(result.getInt("deckID"));
				temp.setDeckName(result.getString("deckName"));
				temp.setAmountDue(getAmountDueFromDeck(result.getInt("deckID")));
				allDecks.add(temp);
			}
			
		} catch (SQLException e)
		{
			System.out.println("There was an error fetching all the decks");
			e.printStackTrace();
		}
		return allDecks;
	}
	public void insertCardIntoDeck(Flashcard card)
	{
		try
		{
			String query = "INSERT INTO cards (\"front\",\"back\", \"freq_mult\", \"streak\", \"due\", \"deckID\") VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, card.getFront());
			statement.setString(2, card.getBack());
			statement.setDouble(3, card.getFrequencyMultiplier());
			statement.setInt(4, card.getStreak());
			statement.setString(5, card.getDueString());
			statement.setInt(6, card.getDeckID());
			statement.execute();			
		} catch (SQLException e)
		{
			System.out.println("There was an error inserting the card into the deck");
			e.printStackTrace();
		}
	}
	public Flashcard fetchSingleCard(int cardID)
	{
		return null;
	}

	public int createDeck(String deckName)
	{
		int deckID=0;
		try
		{
			String query = "INSERT INTO deck (\"deckName\") VALUES (?)";
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, deckName);
			statement.execute();			
			deckID = statement.getGeneratedKeys().getInt(1);
			statement.close();
			
		} catch (SQLException e)
		{
			System.out.println("There was an error creating the deck");
			e.printStackTrace();
		}
		return deckID;
	}

	public void updateCard(Flashcard card)
	{
		try
		{
			String query = "UPDATE cards SET front = ?, back = ?, streak = ?, freq_mult = ?, due = ? WHERE cardID = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, card.getFront());
			statement.setString(2, card.getBack());
			statement.setInt(3, card.getStreak());
			statement.setDouble(4, card.getFrequencyMultiplier());
			statement.setString(5, card.getDueString());
			statement.setInt(6, card.getCardID());
			statement.execute();			
		} catch (SQLException e)
		{
			System.out.println("There was an error updating the card ID: " + card.getCardID());
			e.printStackTrace();
		}
	}
	public void exportDeck(Deck selectedDeck, File exportDir)
	{
		try
		{
			if (exportDir.createNewFile()) 
			{
				System.out.println("File created: " + exportDir.getName());
			}
			else 
			{
				JFrame frame = new JFrame();
				if (JOptionPane.showConfirmDialog(frame, "That file already exists. Overwrite it?", "Yes", JOptionPane.YES_NO_OPTION)==0)
				{
					exportDir.delete();
					exportDir.createNewFile();
				}
			}
			char record_separator = 0x1e;
			char unit_separator = 0x1f;
			String query = "SELECT * FROM cards WHERE deckID = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, selectedDeck.getDeckID());
			ResultSet result = statement.executeQuery();
			Writer myWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(exportDir), "UTF-8"));
			while(result.next())
			{
				myWriter.write(result.getString("front"));
				myWriter.write(unit_separator);
				myWriter.write(result.getString("back"));
				myWriter.write(record_separator);
			}
			result.close();
			myWriter.close();
			
		} catch (SQLException e)
		{
			System.out.println("There was an error selecting all cards");
			e.printStackTrace();
		} catch (IOException e)
		{
			System.out.println("There was an error creating the deck file");
			e.printStackTrace();
		}
	}

	public void importDeck(File importedFile)
	{
		try
		{
			String query = "INSERT INTO deck (\"deckName\") VALUES (?)";
			PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, importedFile.getName().substring(0, importedFile.getName().length()-5));
			statement.execute();
			int deckID = statement.getGeneratedKeys().getInt(1);
			statement.close();
			char record_separator = 0x1e;
			char unit_separator = 0x1f;

			FileInputStream is = new FileInputStream(importedFile);
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader reader = new BufferedReader(isr);
			Scanner read = new Scanner(reader);
		    read.useDelimiter(""+record_separator+"");
		    while(read.hasNext())
		    {
		    	String cardString = read.next();
		    	String frontAndBack[] = cardString.split(""+ unit_separator+"");
		    	Flashcard flashcard = new Flashcard();
		    	flashcard.setDeckID(deckID);
		    	flashcard.setFront(frontAndBack[0]);
		    	flashcard.setBack(frontAndBack[1]);
		    	insertCardIntoDeck(flashcard);
		    }
		    read.close();
		} catch (SQLException e)
		{
			System.out.println("There was an error inserting all the cards");
			e.printStackTrace();
		} catch (IOException e)
		{
			System.out.println("There was an error importing the deck file");
			e.printStackTrace();
		}
		
	}

	public void deleteDeck(Deck deck)
	{
		try
		{
			String query = "DELETE FROM cards WHERE deckID = ?";
			PreparedStatement statement1 = connection.prepareStatement(query);
			statement1.setInt(1, deck.getDeckID());
			statement1.execute();
			statement1.close();
			
			query = "DELETE FROM deck WHERE deckID = ?";
			PreparedStatement statement2 = connection.prepareStatement(query);
			statement2.setInt(1, deck.getDeckID());
			statement2.execute();
			statement2.close();
			
		}catch (SQLException e)
		{
			System.out.println("There was an error deleting the deck");
			e.printStackTrace();
		}
		
	}

	public void deleteCard(Flashcard flashcard)
	{
		try
		{
			String query = "DELETE FROM cards WHERE cardID = ?";
			PreparedStatement statement1 = connection.prepareStatement(query);
			statement1.setInt(1, flashcard.getCardID());
			statement1.execute();
			statement1.close();
			
		}catch (SQLException e)
		{
			System.out.println("There was an error deleting the card");
			e.printStackTrace();
		}
		
	}
}
