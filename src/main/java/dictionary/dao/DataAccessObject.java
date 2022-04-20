package dictionary.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dictionary.entity.FullEntry;
import dictionary.entity.SearchEntry;

public class DataAccessObject
{
	private static final String DICTFILE = System.getProperty("user.dir") + "\\dictionary\\jmdict.db";
	private static final String JDBCURL = "jdbc:sqlite:/"+DICTFILE;
	static Connection connection;
	
	public DataAccessObject()
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
	
	public ArrayList<SearchEntry> searchByEnglish(String searchString)
	{
		ArrayList<SearchEntry> searchResults = new ArrayList<SearchEntry>();
		try
		{
			String query = "SELECT DISTINCT g.entr as entry, k.txt as kanji, r.txt as reading, g.txt as definition, g2.txt as altdefinition "
					+ "FROM gloss g "
					+ "JOIN gloss g2 ON g.entr=g2.entr "
					+ "JOIN rdng r ON g.entr=r.entr "
					+ "LEFT JOIN kanj k ON g.entr=k.entr "
					+ "LEFT JOIN rdng_freq f ON g.entr=f.entr "
					+ "AND f.kw = 5 "
					+ "WHERE g.txt LIKE ? "
					+ "ORDER BY f.value IS NOT NULL DESC, f.value ASC";
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, searchString + "%");

			ResultSet result = statement.executeQuery();
		
			int last_entry = 0;
			
			while(result.next())
			{
				int current_entry = result.getInt("entry");
				if (current_entry != last_entry)
				{
					SearchEntry temp = new SearchEntry();
					temp.setEntry(current_entry);
					temp.setKanji(result.getString("kanji"));
					temp.setReading(result.getString("reading"));
					temp.setDefinition(result.getString("definition"));
					searchResults.add(temp);
					last_entry = current_entry;
				}
				else
				{
					searchResults.get(searchResults.size() - 1 ).appendKanji(result.getString("kanji"));
					searchResults.get(searchResults.size() - 1 ).appendReading(result.getString("reading"));
					searchResults.get(searchResults.size() - 1 ).appendDefinition(result.getString("altdefinition"));
				}
			}
		} catch (SQLException e)
		{
			System.out.println("There was an error searching by English");
			e.printStackTrace();
		}
		return searchResults;
	}
	
	public ArrayList<SearchEntry> searchByKanji(String searchString)
	{
		ArrayList<SearchEntry> searchResults = new ArrayList<SearchEntry>();
		try
		{
			String query = "SELECT DISTINCT g.entr as entry, k.txt as kanji, r.txt as reading, g.txt as definition, k2.txt as altkanji "
					+ "FROM rdng r "
					+ "JOIN gloss g ON k.entr=g.entr "
					+ "JOIN kanj k ON k.entr=r.entr "
					+ "JOIN kanj k2 ON k.entr=k2.entr "
					+ "LEFT JOIN kanj_freq f ON k.entr=f.entr "
					+ "AND f.kw = 5 "
					+ "WHERE k.txt LIKE ? "
					+ "ORDER BY f.value IS NOT NULL DESC, f.value ASC";
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, searchString + "%");
			ResultSet result = statement.executeQuery();
		
			int last_entry = 0;
			
			while(result.next())
			{
				int current_entry = result.getInt("entry");
				if (current_entry != last_entry)
				{
					SearchEntry temp = new SearchEntry();
					temp.setEntry(current_entry);
					temp.setKanji(result.getString("kanji"));
					temp.setReading(result.getString("reading"));
					temp.setDefinition(result.getString("definition"));
					last_entry = current_entry;
					searchResults.add(temp);
				}
				else
				{
					searchResults.get(searchResults.size() - 1 ).appendKanji(result.getString("altkanji"));
					searchResults.get(searchResults.size() - 1 ).appendReading(result.getString("reading"));
					searchResults.get(searchResults.size() - 1 ).appendDefinition(result.getString("definition"));
				}
			}
		} catch (SQLException e)
		{
			System.out.println("There was an error searching by kanji");
			e.printStackTrace();
		}
		return searchResults;
	}
	
	public ArrayList<SearchEntry> searchByKana(String searchString)
	{
		ArrayList<SearchEntry> searchResults = new ArrayList<SearchEntry>();
		try
		{
			String query = "SELECT DISTINCT g.entr as entry, k.txt as kanji, r.txt as reading, g.txt as definition, r2.txt as altreading "
					+ "FROM rdng r "
					+ "JOIN rdng r2 ON r.entr=r2.entr "
					+ "JOIN gloss g ON r.entr=g.entr "
					+ "LEFT JOIN kanj k ON r.entr=k.entr "
					+ "LEFT JOIN rdng_freq f ON r.entr=f.entr "
					+ "AND f.kw = 5 "
					+ "WHERE r.txt LIKE ? "
					+ "ORDER BY f.value IS NOT NULL DESC, f.value ASC";
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, searchString + "%");
			ResultSet result = statement.executeQuery();
		
			int last_entry = 0;
			
			while(result.next())
			{
				int current_entry = result.getInt("entry");
				if (current_entry != last_entry)
				{
					SearchEntry temp = new SearchEntry();
					temp.setEntry(current_entry);
					temp.setKanji(result.getString("kanji"));
					temp.setReading(result.getString("reading"));
					temp.setDefinition(result.getString("definition"));
					last_entry = current_entry;
					searchResults.add(temp);
				}
				else
				{
					searchResults.get(searchResults.size() - 1 ).appendKanji(result.getString("kanji"));
					searchResults.get(searchResults.size() - 1 ).appendReading(result.getString("altreading"));
					searchResults.get(searchResults.size() - 1 ).appendDefinition(result.getString("definition"));
				}
			}
		} catch (SQLException e)
		{
			System.out.println("There was an error searching by kana");
			e.printStackTrace();
		}
		return searchResults;
	}
	
	public FullEntry fetchByEntryID(int id)
	{
		FullEntry entry = new FullEntry(id);
		try
		{
			String query = "SELECT DISTINCT e.id as entry, k.txt as kanji, r.txt as reading, g.txt as definition, kw.descr as keyword, p.kw as posID "
					+ "FROM entr e "
					+ "JOIN pos p ON e.id=p.entr "
					+ "JOIN kwpos kw ON p.kw=kw.id "
					+ "JOIN rdng r ON e.id=r.entr "
					+ "JOIN gloss g ON e.id=g.entr "
					+ "LEFT JOIN kanj k ON e.id=k.entr "
					+ "WHERE e.id = ?";
			
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			while(result.next())
			{
				entry.addKanji(result.getString("kanji"));
				entry.addReading(result.getString("reading"));
				entry.addDefinition(result.getString("definition"));
				entry.addPartOfSpeechTag(result.getString("keyword"));
				entry.addPartOfSpeechTagID(result.getInt("posID"));
			}
		}catch (SQLException e)
		{
			System.out.println("There was an error fetching the full entry by id");
			e.printStackTrace();
		}
		return entry;
	}

	public void insert(FullEntry newEntry)
	{
		try {
			if (!newEntry.getKanjiList().isEmpty())
			{
				PreparedStatement statement1 = connection.prepareStatement("pragma foreign_keys = off;");
				statement1.execute();
				statement1.close();
				
				PreparedStatement statement2 = connection.prepareStatement("INSERT INTO entr (\"id\") VALUES (?)");
				statement2.setInt(1, newEntry.getEntry());
				statement2.execute();
				statement2.close();
				
				PreparedStatement statement3 = connection.prepareStatement("INSERT INTO kanj (\"entr\", \"txt\") VALUES  (?, ?);");
				statement3.setInt(1, newEntry.getEntry());
				statement3.setString(2, newEntry.getKanjiList().iterator().next());
				statement3.execute();
				statement3.close();
				
				PreparedStatement statement4 = connection.prepareStatement("INSERT INTO rdng (\"entr\", \"txt\") VALUES  (?, ?); ");
				statement4.setInt(1, newEntry.getEntry());
				statement4.setString(2, newEntry.getReadingList().iterator().next());
				statement4.execute();
				statement4.close();
				
				PreparedStatement statement5 = connection.prepareStatement("INSERT INTO gloss (\"entr\", \"txt\") VALUES  (?, ?); ");
				statement5.setInt(1, newEntry.getEntry());
				statement5.setString(2, newEntry.getDefinitionList().iterator().next());
				statement5.execute();
				statement5.close();
				
				PreparedStatement statement6 = connection.prepareStatement("INSERT INTO pos (\"entr\", \"kw\") VALUES  (?, ?); ");
				statement6.setInt(1, newEntry.getEntry());
				statement6.setInt(2, newEntry.getPartOfSpeechTagsID().iterator().next());
				statement6.execute();
				statement6.close();
				
				PreparedStatement statement7 = connection.prepareStatement("pragma foreign_keys = on;");
				statement7.execute();
				statement7.close();
			}

		
		}catch (SQLException e)
		{
			System.out.println("There was an error creating the new entry");
			e.printStackTrace();
		}
	}
	public void delete(FullEntry newEntry)
	{
		try {
			if (!newEntry.getKanjiList().isEmpty())
			{
				PreparedStatement statement1 = connection.prepareStatement("pragma foreign_keys = off;");
				statement1.execute();
				statement1.close();
				
				PreparedStatement statement2 = connection.prepareStatement("delete from entr where id = ?");
				statement2.setInt(1, newEntry.getEntry());
				statement2.execute();
				statement2.close();
				
				PreparedStatement statement3 = connection.prepareStatement("delete from kanj where entr = ?");
				statement3.setInt(1, newEntry.getEntry());
				statement3.execute();
				statement3.close();
				
				PreparedStatement statement4 = connection.prepareStatement("delete from rdng where entr = ?");
				statement4.setInt(1, newEntry.getEntry());
				statement4.execute();
				statement4.close();
				
				PreparedStatement statement5 = connection.prepareStatement("delete from gloss where entr = ?");
				statement5.setInt(1, newEntry.getEntry());
				statement5.execute();
				statement5.close();
				
				PreparedStatement statement6 = connection.prepareStatement("delete from pos where entr = ?");
				statement6.setInt(1, newEntry.getEntry());
				statement6.execute();
				statement6.close();
				
				PreparedStatement statement7 = connection.prepareStatement("pragma foreign_keys = on;");
				statement7.execute();
				statement7.close();
			}

		
		}catch (SQLException e)
		{
			System.out.println("There was an error creating the new entry");
			e.printStackTrace();
		}
	}
	public int findNewEntryID()
	{
		int newID=0;
		try 
		{
			PreparedStatement statement = connection.prepareStatement("SELECT MAX(id)+1 as newID FROM entr");
			ResultSet result = statement.executeQuery();
			while(result.next())
			{
				newID=result.getInt("newID");
			}
			statement.close();
		}catch (SQLException e)
		{
			System.out.println("There was an error finding a new ID");
			e.printStackTrace();
		}
		return newID;
	}
}
