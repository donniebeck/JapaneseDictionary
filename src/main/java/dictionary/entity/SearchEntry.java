package dictionary.entity;

public class SearchEntry
{
	final int MAX_DEF_LENGTH = 50;
	int entry;
	String kanji;
	String reading;
	String definition;
	
	public SearchEntry()
	{
		this.entry = 0;
		this.kanji = "No results";
		this.reading = "";
		this.definition = "";
	}
	public SearchEntry(int entry, String kanji, String reading, String definition)
	{
		super();
		this.entry = entry;
		this.kanji = kanji;
		this.reading = reading;
		this.definition = definition;
	}
	public int getEntry()
	{
		return entry;
	}
	public void setEntry(int entry)
	{
		this.entry = entry;
	}
	public String getKanji()
	{
		return kanji;
	}
	public String getReading()
	{
		return reading;
	}
	public String getDefinition()
	{
		return definition;
	}
	public void setKanji(String kanji)
	{
		this.kanji = kanji;
	}
	public void setReading(String reading)
	{
		this.reading = reading;
	}
	public void setDefinition(String definition)
	{
		this.definition = definition;
		if (this.definition.length() >= MAX_DEF_LENGTH)
		{
			this.definition = this.definition.substring(0, MAX_DEF_LENGTH);
			this.definition += ", ...";
		}
	}
	@Override
	public String toString()
	{
		if (this.kanji != null)
		{
			return "<html><b> " + kanji +  "</b><br/> " + reading+  "<br/> " + definition + "</html>";
		}
		return "<html><b> " + reading +  "</b><br/> " + definition + "</html>";
	}
	public void appendKanji(String kanji)
	{
		if(kanji != null && !this.kanji.contains(kanji))
		{
			this.kanji += ", " + kanji;
		}
	}
	public void appendReading(String reading)
	{
		if(reading != null && !this.reading.contains(reading))
		{
			this.reading += ", " + reading;
		}
	
	}
	public void appendDefinition(String definition)
	{
		if (this.definition.length() <= MAX_DEF_LENGTH)
		{
			if (definition != null && !this.definition.contains(definition))
			{
				this.definition += ", " + definition;
			}
			if (this.definition.length() >= MAX_DEF_LENGTH)
			{
				this.definition = this.definition.substring(0, MAX_DEF_LENGTH);
				this.definition += ", ...";
			}
		}
	}
	
	
}
