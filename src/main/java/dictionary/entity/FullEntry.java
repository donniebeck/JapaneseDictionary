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
					taForm = verb.substring(0,verb.length()-1) + "た";
					teForm = verb.substring(0,verb.length()-1) + "て";
					ebaForm = verb.substring(0,verb.length()-1) + "れば";
					potentialForm = verb.substring(0,verb.length()-1) + "られ";
					passiveForm = verb.substring(0,verb.length()-1) + "られ";
					causativeForm = verb.substring(0,verb.length()-1) + "させ";
					imperativeForm = verb.substring(0,verb.length()-1) + "ろ";
					volitionalForm = verb.substring(0,verb.length()-1) + "よう";
					negativeStem = verb.substring(0,verb.length()-1);
					break;
				case 31: //bu
					masuStem = verb.substring(0,verb.length()-1) + "び";
					taForm = verb.substring(0,verb.length()-1) + "んだ";
					teForm = verb.substring(0,verb.length()-1) + "んで";
					ebaForm = verb.substring(0,verb.length()-1) + "べば";
					potentialForm = verb.substring(0,verb.length()-1) + "べ";
					passiveForm = verb.substring(0,verb.length()-1) + "ばれ";
					causativeForm = verb.substring(0,verb.length()-1) + "ばせ";
					imperativeForm = verb.substring(0,verb.length()-1) + "べ";
					volitionalForm = verb.substring(0,verb.length()-1) + "ぼう";
					negativeStem = verb.substring(0,verb.length()-1) + "ば";
					break;
				case 32: //gu
					masuStem = verb.substring(0,verb.length()-1) + "ぎ";
					taForm = verb.substring(0,verb.length()-1) + "いで";
					teForm = verb.substring(0,verb.length()-1) + "いだ";
					ebaForm = verb.substring(0,verb.length()-1) + "げば";
					potentialForm = verb.substring(0,verb.length()-1) + "げ";
					passiveForm = verb.substring(0,verb.length()-1) + "がれ";
					causativeForm = verb.substring(0,verb.length()-1) + "がせ";
					imperativeForm = verb.substring(0,verb.length()-1) + "げ";
					volitionalForm = verb.substring(0,verb.length()-1) + "ごう";
					negativeStem = verb.substring(0,verb.length()-1) + "が";
					break;
				case 33: // ku
					masuStem = verb.substring(0,verb.length()-1) + "き";
					taForm = verb.substring(0,verb.length()-1) + "いた";
					teForm = verb.substring(0,verb.length()-1) + "いて";
					ebaForm = verb.substring(0,verb.length()-1) + "けば";
					potentialForm = verb.substring(0,verb.length()-1) + "け";
					passiveForm = verb.substring(0,verb.length()-1) + "かれ";
					causativeForm = verb.substring(0,verb.length()-1) + "かせ";
					imperativeForm = verb.substring(0,verb.length()-1) + "け";
					volitionalForm = verb.substring(0,verb.length()-1) + "こう";
					negativeStem = verb.substring(0,verb.length()-1) + "か";
					break;
				case 34: //iku
					masuStem = verb.substring(0,verb.length()-1) + "き";
					taForm = verb.substring(0,verb.length()-1) + "った";
					teForm = verb.substring(0,verb.length()-1) + "って";
					ebaForm = verb.substring(0,verb.length()-1) + "けば";
					potentialForm = verb.substring(0,verb.length()-1) + "け";
					passiveForm = verb.substring(0,verb.length()-1) + "かれ";
					causativeForm = verb.substring(0,verb.length()-1) + "かせ";
					imperativeForm = verb.substring(0,verb.length()-1) + "け";
					volitionalForm = verb.substring(0,verb.length()-1) + "こう";
					negativeStem = verb.substring(0,verb.length()-1) + "か";
					break;
				case 35: //mu
					masuStem = verb.substring(0,verb.length()-1) + "み";
					taForm = verb.substring(0,verb.length()-1) + "んだ";
					teForm = verb.substring(0,verb.length()-1) + "んで";
					ebaForm = verb.substring(0,verb.length()-1) + "めば";
					potentialForm = verb.substring(0,verb.length()-1) + "め";
					passiveForm = verb.substring(0,verb.length()-1) + "まれ";
					causativeForm = verb.substring(0,verb.length()-1) + "ませ";
					imperativeForm = verb.substring(0,verb.length()-1) + "め";
					volitionalForm = verb.substring(0,verb.length()-1) + "もう";
					negativeStem = verb.substring(0,verb.length()-1) + "ま";
					break;
				case 36: //nu
					masuStem = verb.substring(0,verb.length()-1) + "に";
					taForm = verb.substring(0,verb.length()-1) + "んだ";
					teForm = verb.substring(0,verb.length()-1) + "んで";
					ebaForm = verb.substring(0,verb.length()-1) + "ねば";
					potentialForm = verb.substring(0,verb.length()-1) + "ね";
					passiveForm = verb.substring(0,verb.length()-1) + "なれ";
					causativeForm = verb.substring(0,verb.length()-1) + "なせ";
					imperativeForm = verb.substring(0,verb.length()-1) + "ね";
					volitionalForm = verb.substring(0,verb.length()-1) + "のう";
					negativeStem = verb.substring(0,verb.length()-1) + "な";
					break;
				case 37: //ru
					masuStem = verb.substring(0,verb.length()-1) + "り";
					taForm = verb.substring(0,verb.length()-1) + "った";
					teForm = verb.substring(0,verb.length()-1) + "って";
					ebaForm = verb.substring(0,verb.length()-1) + "れば";
					potentialForm = verb.substring(0,verb.length()-1) + "れ";
					passiveForm = verb.substring(0,verb.length()-1) + "られ";
					causativeForm = verb.substring(0,verb.length()-1) + "らせ";
					imperativeForm = verb.substring(0,verb.length()-1) + "れ";
					volitionalForm = verb.substring(0,verb.length()-1) + "ろう";
					negativeStem = verb.substring(0,verb.length()-1) + "ら";
					break;
				case 39: //su
					masuStem = verb.substring(0,verb.length()-1) + "し";
					taForm = verb.substring(0,verb.length()-1) + "した";
					teForm = verb.substring(0,verb.length()-1) + "して";
					ebaForm = verb.substring(0,verb.length()-1) + "せば";
					potentialForm = verb.substring(0,verb.length()-1) + "せ";
					passiveForm = verb.substring(0,verb.length()-1) + "され";
					causativeForm = verb.substring(0,verb.length()-1) + "させ";
					imperativeForm = verb.substring(0,verb.length()-1) + "せ";
					volitionalForm = verb.substring(0,verb.length()-1) + "そう";
					negativeStem = verb.substring(0,verb.length()-1) + "さ";
					break;
				case 40: //tsu
					masuStem = verb.substring(0,verb.length()-1) + "ち";
					taForm = verb.substring(0,verb.length()-1) + "った";
					teForm = verb.substring(0,verb.length()-1) + "って";
					ebaForm = verb.substring(0,verb.length()-1) + "てば";
					potentialForm = verb.substring(0,verb.length()-1) + "て";
					passiveForm = verb.substring(0,verb.length()-1) + "たれ";
					causativeForm = verb.substring(0,verb.length()-1) + "たせ";
					imperativeForm = verb.substring(0,verb.length()-1) + "て";
					volitionalForm = verb.substring(0,verb.length()-1) + "とう";
					negativeStem = verb.substring(0,verb.length()-1) + "た";
					break;
				case 41: //u
					masuStem = verb.substring(0,verb.length()-1) + "い";
					taForm = verb.substring(0,verb.length()-1) + "った";
					teForm = verb.substring(0,verb.length()-1) + "って";
					ebaForm = verb.substring(0,verb.length()-1) + "えば";
					potentialForm = verb.substring(0,verb.length()-1) + "え";
					passiveForm = verb.substring(0,verb.length()-1) + "われ";
					causativeForm = verb.substring(0,verb.length()-1) + "わせ";
					imperativeForm = verb.substring(0,verb.length()-1) + "え";
					volitionalForm = verb.substring(0,verb.length()-1) + "おう";
					negativeStem = verb.substring(0,verb.length()-1) + "わ";
					break;
				case 45: //kuru
					masuStem = verb.substring(0,verb.length()-2) + "き";
					taForm = verb.substring(0,verb.length()-2) + "きた";
					teForm = verb.substring(0,verb.length()-2) + "きて";
					ebaForm = verb.substring(0,verb.length()-1) + "れば";
					potentialForm = verb.substring(0,verb.length()-2) + "こられ";
					passiveForm = verb.substring(0,verb.length()-2) + "こられ";
					causativeForm = verb.substring(0,verb.length()-2) + "こさせ";
					imperativeForm = verb.substring(0,verb.length()-2) + "こい";
					volitionalForm = verb.substring(0,verb.length()-2) + "こよう";
					negativeStem = verb.substring(0,verb.length()-2) + "こ";
					break;
				case 46: //する verb without する
					masuStem = verb + "し";
					taForm = verb + "した";
					teForm = verb + "して";
					ebaForm = verb + "すれば";
					potentialForm = verb + "でき";
					passiveForm = verb + "され";
					causativeForm = verb + "させ";
					imperativeForm = verb + "しろ";
					volitionalForm = verb + "しよう";
					negativeStem = verb + "し";
					break;
				case 47: //including suru
				case 48:
					masuStem = verb.substring(0,verb.length()-2) + "し";
					taForm = verb.substring(0,verb.length()-2) + "した";
					teForm = verb.substring(0,verb.length()-2) + "して";
					ebaForm = verb.substring(0,verb.length()-1) + "れば";
					potentialForm = verb.substring(0,verb.length()-2) + "でき";
					passiveForm = verb.substring(0,verb.length()-2) + "され";
					causativeForm = verb.substring(0,verb.length()-2) + "させ";
					imperativeForm = verb.substring(0,verb.length()-2) + "しろ";
					volitionalForm = verb.substring(0,verb.length()-2) + "しよう";
					negativeStem = verb.substring(0,verb.length()-2) + "し";
					break;
				}
				conjugationList+=
						"<h><b>Positive</b></h><table>"
						+ "<tr><td>Present: </td><td>"  + verb  + "</td></tr>"
						+ "<tr><td>Past: </td><td>"  + taForm  + "</td></tr>"
						+ "<tr><td>te Form: </td><td>"  + teForm  + "</td></tr>"
						+ "<tr><td>eba Conditional: </td><td>"  + ebaForm  + "</td></tr>"
						+ "<tr><td>tara Conditional: </td><td>"  + taForm  + "ら</td></tr>"
						+ "<tr><td>Potential: </td><td>"  + potentialForm + "る</td></tr>"
						+ "<tr><td>Passive: </td><td>"  + passiveForm  + "る</td></tr>"
						+ "<tr><td>Causative: </td><td>"  + causativeForm  + "る</td></tr>"
						+ "<tr><td>Imperative: </td><td>"  + imperativeForm  + "</td></tr>"
						+ "<tr><td>Volitional: </td><td>"  + volitionalForm  + "</td></tr>"
						+"</table>"
						
						+ "<h><b>Negative</b></h><table>"
						+ "<tr><td>Present: </td><td>"  + negativeStem  + "ない</td></tr>"
						+ "<tr><td>Past: </td><td>"  +  negativeStem + "なかった</td></tr>"
						+ "<tr><td>eba Conditional: </td><td>"  + negativeStem  + "なければ</td></tr>"
						+ "<tr><td>tara Conditional: </td><td>"  +  negativeStem + "なかったら</td></tr>"
						+ "<tr><td>Potential: </td><td>"  + potentialForm + "ない</td></tr>"
						+ "<tr><td>Passive: </td><td>"  +  passiveForm + "ない</td></tr>"
						+ "<tr><td>Causative: </td><td>"  +  causativeForm + "ない</td></tr>"
						+ "<tr><td>Imperative: </td><td>"  +  verb + "な</td></tr>"
						+"</table>"
						
						+ "<h><b>Masu</b></h><table>"
						+ "<tr><td>Present: </td><td>"  + masuStem  + "ます</td></tr>"
						+ "<tr><td>Past: </td><td>"  +  masuStem + "ました</td></tr>"
						+ "<tr><td>eba Conditional: </td><td>"  + masuStem  + "ませば</td></tr>"
						+ "<tr><td>tara Conditional: </td><td>"  +  masuStem + "ましたら</td></tr>"
						+ "<tr><td>Potential: </td><td>"  + potentialForm + "ます</td></tr>"
						+ "<tr><td>Passive: </td><td>"  +  passiveForm + "ます</td></tr>"
						+ "<tr><td>Causative: </td><td>"  +  causativeForm + "ます</td></tr>"
						+ "<tr><td>Volitional: </td><td>"  + masuStem  + "ましょう</td></tr>"
						+"</table>"
						
						+ "<h><b>Masu Negative</b></h><table>"
						+ "<tr><td>Present: </td><td>"  + masuStem  + "ません</td></tr>"
						+ "<tr><td>Past: </td><td>"  +  masuStem + "ませんでした</td></tr>"
						+ "<tr><td>eba Conditional: </td><td>"  + masuStem  + "ませんなら</td></tr>"
						+ "<tr><td>tara Conditional: </td><td>"  +  masuStem + "ませんでしたら</td></tr>"
						+ "<tr><td>Potential: </td><td>"  + potentialForm + "ません</td></tr>"
						+ "<tr><td>Passive: </td><td>"  +  passiveForm + "ません</td></tr>"
						+ "<tr><td>Causative: </td><td>"  +  causativeForm + "ません</td></tr>"
						+"</table>";		
				break;	
			}
		}
		
	}
	
	
}
