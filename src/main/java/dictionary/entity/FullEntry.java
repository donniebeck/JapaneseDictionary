package dictionary.entity;

import java.util.HashSet;
import java.util.Set;

public class FullEntry
{
	int entry;
	
	Set<String> kanjiList;
	Set<String> readingList;
	Set<String> definitionList;
	Set<Integer> partOfSpeechTagsID;
	Set<String> partOfSpeechTags;
	String conjugationList;
	public FullEntry(int id)
	{
		this.entry = id;
		kanjiList = new HashSet<String>();
		readingList = new HashSet<String>();
		definitionList = new HashSet<String>();
		partOfSpeechTags = new HashSet<String>();
		conjugationList = "";
		partOfSpeechTagsID = new HashSet<Integer>();
	}
	public FullEntry()
	{
		// TODO Auto-generated constructor stub
	}
	public int getEntry()
	{
		return entry;
	}
	public Set<String> getKanjiList()
	{
		return kanjiList;
	}
	public Set<String> getReadingList()
	{
		return readingList;
	}
	public Set<String> getDefinitionList()
	{
		return definitionList;
	}
	public Set<String> getPartOfSpeechTags()
	{
		return partOfSpeechTags;
	}
	public Set<Integer> getPartOfSpeechTagsID()
	{
		return partOfSpeechTagsID;
	}
	public String getConjugationList()
	{
		return conjugationList;
	}
	public void setEntry(int entry)
	{
		this.entry = entry;
	}
	public void setKanjiList(Set<String> kanjiList)
	{
		this.kanjiList = kanjiList;
	}
	public void setReadingList(Set<String> readingList)
	{
		this.readingList = readingList;
	}
	public void setDefinitionList(Set<String> definitionList)
	{
		this.definitionList = definitionList;
	}
	public void setPartOfSpeechTags(Set<String> partOfSpeechTags)
	{
		this.partOfSpeechTags = partOfSpeechTags;
	}
	public void setPartOfSpeechTagsID(Set<Integer> partOfSpeechTagsID)
	{
		this.partOfSpeechTagsID = partOfSpeechTagsID;
	}
	public void setConjugationList(String conjugationList)
	{
		this.conjugationList = conjugationList;
	}
	
	public void addKanji(String kanji)
	{
		if (kanji != null)
		{
			this.kanjiList.add(kanji);
		}
	}
	public void addReading(String reading)
	{
		if (!reading.isEmpty())
		{
			this.readingList.add(reading);
		}
	}
	public void addDefinition(String definition)
	{
		if (!definition.isEmpty())
		{
			this.definitionList.add(definition);
		}
	}
	public void addPartOfSpeechTag(String pos)
	{
		if (!pos.isEmpty())
		{
			this.partOfSpeechTags.add(pos);
		}
	}
	public void addPartOfSpeechTagID(Integer posID)
	{
		if (posID != null)
		{
			this.partOfSpeechTagsID.add(posID);
		}
	}
	public String generateFullEntryHTML()
	{
		createConjugationList();
		String fullEntry = "<html><p><b>";
		Boolean hangingComma = false;
		for (String kanji : kanjiList)
		{
			fullEntry+=kanji +  ", ";
			hangingComma=true;
		}
		if(hangingComma)
		{
			fullEntry = fullEntry.substring(0, fullEntry.length()-2);
			hangingComma=false;
		}
		fullEntry+="</b></p><p>";
		for (String reading : readingList)
		{
			fullEntry+=reading +   ", ";
			hangingComma=true;
		}
		if(hangingComma)
		{
			fullEntry = fullEntry.substring(0, fullEntry.length()-2);
			hangingComma=false;
		}
		fullEntry+="</p><p>";
		for (String partOfSpeechTag : partOfSpeechTags)
		{
			fullEntry+=partOfSpeechTag  +  ", ";
			hangingComma=true;
		}
		if(hangingComma)
		{
			fullEntry = fullEntry.substring(0, fullEntry.length()-2);
			hangingComma=false;
		}
		fullEntry+="</p><ol style=\"list-style-type:lower-alpha\">";
		for (String definition : definitionList)
		{
			fullEntry+="<li>" + definition + "</li>" ;
		}
		fullEntry+="</ol></p>";
		if(!conjugationList.isEmpty())
		{
			fullEntry+=conjugationList;
		}
		fullEntry+="</html>";
		
		return fullEntry;
	}
	private void createConjugationList()
	{
		
		for (int keywordID : partOfSpeechTagsID)
		{
			Keyword keyword = new Keyword(keywordID);
			if(keyword.isVerb && conjugationList.isEmpty())
			{
				String verb;
				if(!kanjiList.isEmpty() && keywordID != 45)
				{
					verb = kanjiList.iterator().next();
				}
				else
				{
					verb = readingList.iterator().next();
				}
				
				String masuStem = verb;
				String taForm = verb;
				String teForm = verb;
				String ebaForm = verb;
				String potentialForm = verb;
				String passiveForm = verb;
				String causativeForm = verb;
				String imperativeForm = verb;
				String volitionalForm = verb;
				String negativeStem = verb;
				
				
				
				
				switch(keyword.id)
				{
				case 28: //Ichidan
					masuStem = verb.substring(0,verb.length()-1);
					taForm = verb.substring(0,verb.length()-1) + "???";
					teForm = verb.substring(0,verb.length()-1) + "???";
					ebaForm = verb.substring(0,verb.length()-1) + "??????";
					potentialForm = verb.substring(0,verb.length()-1) + "??????";
					passiveForm = verb.substring(0,verb.length()-1) + "??????";
					causativeForm = verb.substring(0,verb.length()-1) + "??????";
					imperativeForm = verb.substring(0,verb.length()-1) + "???";
					volitionalForm = verb.substring(0,verb.length()-1) + "??????";
					negativeStem = verb.substring(0,verb.length()-1);
					break;
				case 31: //bu
					masuStem = verb.substring(0,verb.length()-1) + "???";
					taForm = verb.substring(0,verb.length()-1) + "??????";
					teForm = verb.substring(0,verb.length()-1) + "??????";
					ebaForm = verb.substring(0,verb.length()-1) + "??????";
					potentialForm = verb.substring(0,verb.length()-1) + "???";
					passiveForm = verb.substring(0,verb.length()-1) + "??????";
					causativeForm = verb.substring(0,verb.length()-1) + "??????";
					imperativeForm = verb.substring(0,verb.length()-1) + "???";
					volitionalForm = verb.substring(0,verb.length()-1) + "??????";
					negativeStem = verb.substring(0,verb.length()-1) + "???";
					break;
				case 32: //gu
					masuStem = verb.substring(0,verb.length()-1) + "???";
					taForm = verb.substring(0,verb.length()-1) + "??????";
					teForm = verb.substring(0,verb.length()-1) + "??????";
					ebaForm = verb.substring(0,verb.length()-1) + "??????";
					potentialForm = verb.substring(0,verb.length()-1) + "???";
					passiveForm = verb.substring(0,verb.length()-1) + "??????";
					causativeForm = verb.substring(0,verb.length()-1) + "??????";
					imperativeForm = verb.substring(0,verb.length()-1) + "???";
					volitionalForm = verb.substring(0,verb.length()-1) + "??????";
					negativeStem = verb.substring(0,verb.length()-1) + "???";
					break;
				case 33: // ku
					masuStem = verb.substring(0,verb.length()-1) + "???";
					taForm = verb.substring(0,verb.length()-1) + "??????";
					teForm = verb.substring(0,verb.length()-1) + "??????";
					ebaForm = verb.substring(0,verb.length()-1) + "??????";
					potentialForm = verb.substring(0,verb.length()-1) + "???";
					passiveForm = verb.substring(0,verb.length()-1) + "??????";
					causativeForm = verb.substring(0,verb.length()-1) + "??????";
					imperativeForm = verb.substring(0,verb.length()-1) + "???";
					volitionalForm = verb.substring(0,verb.length()-1) + "??????";
					negativeStem = verb.substring(0,verb.length()-1) + "???";
					break;
				case 34: //iku
					masuStem = verb.substring(0,verb.length()-1) + "???";
					taForm = verb.substring(0,verb.length()-1) + "??????";
					teForm = verb.substring(0,verb.length()-1) + "??????";
					ebaForm = verb.substring(0,verb.length()-1) + "??????";
					potentialForm = verb.substring(0,verb.length()-1) + "???";
					passiveForm = verb.substring(0,verb.length()-1) + "??????";
					causativeForm = verb.substring(0,verb.length()-1) + "??????";
					imperativeForm = verb.substring(0,verb.length()-1) + "???";
					volitionalForm = verb.substring(0,verb.length()-1) + "??????";
					negativeStem = verb.substring(0,verb.length()-1) + "???";
					break;
				case 35: //mu
					masuStem = verb.substring(0,verb.length()-1) + "???";
					taForm = verb.substring(0,verb.length()-1) + "??????";
					teForm = verb.substring(0,verb.length()-1) + "??????";
					ebaForm = verb.substring(0,verb.length()-1) + "??????";
					potentialForm = verb.substring(0,verb.length()-1) + "???";
					passiveForm = verb.substring(0,verb.length()-1) + "??????";
					causativeForm = verb.substring(0,verb.length()-1) + "??????";
					imperativeForm = verb.substring(0,verb.length()-1) + "???";
					volitionalForm = verb.substring(0,verb.length()-1) + "??????";
					negativeStem = verb.substring(0,verb.length()-1) + "???";
					break;
				case 36: //nu
					masuStem = verb.substring(0,verb.length()-1) + "???";
					taForm = verb.substring(0,verb.length()-1) + "??????";
					teForm = verb.substring(0,verb.length()-1) + "??????";
					ebaForm = verb.substring(0,verb.length()-1) + "??????";
					potentialForm = verb.substring(0,verb.length()-1) + "???";
					passiveForm = verb.substring(0,verb.length()-1) + "??????";
					causativeForm = verb.substring(0,verb.length()-1) + "??????";
					imperativeForm = verb.substring(0,verb.length()-1) + "???";
					volitionalForm = verb.substring(0,verb.length()-1) + "??????";
					negativeStem = verb.substring(0,verb.length()-1) + "???";
					break;
				case 37: //ru
					masuStem = verb.substring(0,verb.length()-1) + "???";
					taForm = verb.substring(0,verb.length()-1) + "??????";
					teForm = verb.substring(0,verb.length()-1) + "??????";
					ebaForm = verb.substring(0,verb.length()-1) + "??????";
					potentialForm = verb.substring(0,verb.length()-1) + "???";
					passiveForm = verb.substring(0,verb.length()-1) + "??????";
					causativeForm = verb.substring(0,verb.length()-1) + "??????";
					imperativeForm = verb.substring(0,verb.length()-1) + "???";
					volitionalForm = verb.substring(0,verb.length()-1) + "??????";
					negativeStem = verb.substring(0,verb.length()-1) + "???";
					break;
				case 39: //su
					masuStem = verb.substring(0,verb.length()-1) + "???";
					taForm = verb.substring(0,verb.length()-1) + "??????";
					teForm = verb.substring(0,verb.length()-1) + "??????";
					ebaForm = verb.substring(0,verb.length()-1) + "??????";
					potentialForm = verb.substring(0,verb.length()-1) + "???";
					passiveForm = verb.substring(0,verb.length()-1) + "??????";
					causativeForm = verb.substring(0,verb.length()-1) + "??????";
					imperativeForm = verb.substring(0,verb.length()-1) + "???";
					volitionalForm = verb.substring(0,verb.length()-1) + "??????";
					negativeStem = verb.substring(0,verb.length()-1) + "???";
					break;
				case 40: //tsu
					masuStem = verb.substring(0,verb.length()-1) + "???";
					taForm = verb.substring(0,verb.length()-1) + "??????";
					teForm = verb.substring(0,verb.length()-1) + "??????";
					ebaForm = verb.substring(0,verb.length()-1) + "??????";
					potentialForm = verb.substring(0,verb.length()-1) + "???";
					passiveForm = verb.substring(0,verb.length()-1) + "??????";
					causativeForm = verb.substring(0,verb.length()-1) + "??????";
					imperativeForm = verb.substring(0,verb.length()-1) + "???";
					volitionalForm = verb.substring(0,verb.length()-1) + "??????";
					negativeStem = verb.substring(0,verb.length()-1) + "???";
					break;
				case 41: //u
					masuStem = verb.substring(0,verb.length()-1) + "???";
					taForm = verb.substring(0,verb.length()-1) + "??????";
					teForm = verb.substring(0,verb.length()-1) + "??????";
					ebaForm = verb.substring(0,verb.length()-1) + "??????";
					potentialForm = verb.substring(0,verb.length()-1) + "???";
					passiveForm = verb.substring(0,verb.length()-1) + "??????";
					causativeForm = verb.substring(0,verb.length()-1) + "??????";
					imperativeForm = verb.substring(0,verb.length()-1) + "???";
					volitionalForm = verb.substring(0,verb.length()-1) + "??????";
					negativeStem = verb.substring(0,verb.length()-1) + "???";
					break;
				case 45: //kuru
					masuStem = verb.substring(0,verb.length()-2) + "???";
					taForm = verb.substring(0,verb.length()-2) + "??????";
					teForm = verb.substring(0,verb.length()-2) + "??????";
					ebaForm = verb.substring(0,verb.length()-1) + "??????";
					potentialForm = verb.substring(0,verb.length()-2) + "?????????";
					passiveForm = verb.substring(0,verb.length()-2) + "?????????";
					causativeForm = verb.substring(0,verb.length()-2) + "?????????";
					imperativeForm = verb.substring(0,verb.length()-2) + "??????";
					volitionalForm = verb.substring(0,verb.length()-2) + "?????????";
					negativeStem = verb.substring(0,verb.length()-2) + "???";
					break;
				case 46: //?????? verb without ??????
					masuStem = verb + "???";
					taForm = verb + "??????";
					teForm = verb + "??????";
					ebaForm = verb + "?????????";
					potentialForm = verb + "??????";
					passiveForm = verb + "??????";
					causativeForm = verb + "??????";
					imperativeForm = verb + "??????";
					volitionalForm = verb + "?????????";
					negativeStem = verb + "???";
					break;
				case 47: //including suru
				case 48:
					masuStem = verb.substring(0,verb.length()-2) + "???";
					taForm = verb.substring(0,verb.length()-2) + "??????";
					teForm = verb.substring(0,verb.length()-2) + "??????";
					ebaForm = verb.substring(0,verb.length()-1) + "??????";
					potentialForm = verb.substring(0,verb.length()-2) + "??????";
					passiveForm = verb.substring(0,verb.length()-2) + "??????";
					causativeForm = verb.substring(0,verb.length()-2) + "??????";
					imperativeForm = verb.substring(0,verb.length()-2) + "??????";
					volitionalForm = verb.substring(0,verb.length()-2) + "?????????";
					negativeStem = verb.substring(0,verb.length()-2) + "???";
					break;
				}
				conjugationList+=
						"<h><b>Positive</b></h><table>"
						+ "<tr><td>Present: </td><td>"  + verb  + "</td></tr>"
						+ "<tr><td>Past: </td><td>"  + taForm  + "</td></tr>"
						+ "<tr><td>te Form: </td><td>"  + teForm  + "</td></tr>"
						+ "<tr><td>eba Conditional: </td><td>"  + ebaForm  + "</td></tr>"
						+ "<tr><td>tara Conditional: </td><td>"  + taForm  + "???</td></tr>"
						+ "<tr><td>Potential: </td><td>"  + potentialForm + "???</td></tr>"
						+ "<tr><td>Passive: </td><td>"  + passiveForm  + "???</td></tr>"
						+ "<tr><td>Causative: </td><td>"  + causativeForm  + "???</td></tr>"
						+ "<tr><td>Imperative: </td><td>"  + imperativeForm  + "</td></tr>"
						+ "<tr><td>Volitional: </td><td>"  + volitionalForm  + "</td></tr>"
						+"</table>"
						
						+ "<h><b>Negative</b></h><table>"
						+ "<tr><td>Present: </td><td>"  + negativeStem  + "??????</td></tr>"
						+ "<tr><td>Past: </td><td>"  +  negativeStem + "????????????</td></tr>"
						+ "<tr><td>eba Conditional: </td><td>"  + negativeStem  + "????????????</td></tr>"
						+ "<tr><td>tara Conditional: </td><td>"  +  negativeStem + "???????????????</td></tr>"
						+ "<tr><td>Potential: </td><td>"  + potentialForm + "??????</td></tr>"
						+ "<tr><td>Passive: </td><td>"  +  passiveForm + "??????</td></tr>"
						+ "<tr><td>Causative: </td><td>"  +  causativeForm + "??????</td></tr>"
						+ "<tr><td>Imperative: </td><td>"  +  verb + "???</td></tr>"
						+"</table>"
						
						+ "<h><b>Masu</b></h><table>"
						+ "<tr><td>Present: </td><td>"  + masuStem  + "??????</td></tr>"
						+ "<tr><td>Past: </td><td>"  +  masuStem + "?????????</td></tr>"
						+ "<tr><td>eba Conditional: </td><td>"  + masuStem  + "?????????</td></tr>"
						+ "<tr><td>tara Conditional: </td><td>"  +  masuStem + "????????????</td></tr>"
						+ "<tr><td>Potential: </td><td>"  + potentialForm + "??????</td></tr>"
						+ "<tr><td>Passive: </td><td>"  +  passiveForm + "??????</td></tr>"
						+ "<tr><td>Causative: </td><td>"  +  causativeForm + "??????</td></tr>"
						+ "<tr><td>Volitional: </td><td>"  + masuStem  + "????????????</td></tr>"
						+"</table>"
						
						+ "<h><b>Masu Negative</b></h><table>"
						+ "<tr><td>Present: </td><td>"  + masuStem  + "?????????</td></tr>"
						+ "<tr><td>Past: </td><td>"  +  masuStem + "??????????????????</td></tr>"
						+ "<tr><td>eba Conditional: </td><td>"  + masuStem  + "???????????????</td></tr>"
						+ "<tr><td>tara Conditional: </td><td>"  +  masuStem + "?????????????????????</td></tr>"
						+ "<tr><td>Potential: </td><td>"  + potentialForm + "?????????</td></tr>"
						+ "<tr><td>Passive: </td><td>"  +  passiveForm + "?????????</td></tr>"
						+ "<tr><td>Causative: </td><td>"  +  causativeForm + "?????????</td></tr>"
						+"</table>";		
				break;	
			}
		}
		
	}
	
	
}
