package dictionary.service;

import java.util.ArrayList;
import dictionary.dao.DataAccessObject;
import dictionary.entity.FullEntry;
import dictionary.entity.SearchEntry;

public class DictionaryService
{
	DataAccessObject dao;
	
	public DictionaryService()
	{
		//creating our data access object
		dao = new DataAccessObject();
	}
	
	public void close()
	{
		dao.closeDAO();
	}
	
	public ArrayList<SearchEntry> search(String text)
	{
		char queryType = text.charAt(0);
		ArrayList<SearchEntry> arrayListSearchEntry;
		
		if(queryType < 0x3040) //English upper bound
		{
			arrayListSearchEntry = dao.searchByEnglish(text);
		} else if (queryType < 0x4e00) //Kana upper bound
		{
			arrayListSearchEntry = dao.searchByKana(text);
		} else //Anything higher is assumed to be kanji
		{
			arrayListSearchEntry = dao.searchByKanji(text);
		}

		return arrayListSearchEntry;
	}
	
	public String fetchFullHTMLByID(int id)
	{
		return dao.fetchByEntryID(id).generateFullEntryHTML();
	}
	
	public FullEntry fetchFullByID(int id)
	{
		return dao.fetchByEntryID(id);
	}
	
	public void insertEntry(FullEntry newEntry)
	{
		dao.insert(newEntry);
	}
	
	public int getNewID()
	{
		return dao.findNewEntryID();
	}
	
	public void deleteEntry(FullEntry newEntry)
	{
		dao.delete(newEntry);
	}
}
