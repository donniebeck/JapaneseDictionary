package dictionary.entity;

public class Keyword
{

	String keyword;
	boolean isVerb = false;
	int id;
	
	public Keyword(int id)
	{
		this.id = id;
		switch (id) 
		{
			case 1: keyword = "Adjective い"; isVerb=false; break;
			case 2: keyword = "Adjective な"; isVerb=false; break;
			case 3: keyword = "Adjective の"; isVerb=false; break;
			case 6: keyword = "Adverb"; isVerb=false; break;
			case 7: keyword = "Adjective いい"; isVerb=false; break;
			case 13: keyword = "Expression"; isVerb=false; break;
			case 14: keyword = "Interjection"; isVerb=false; break;
			case 17: keyword = "Noun"; isVerb=false; break;
			case 28: keyword = "Ichidan verb る";isVerb=true; break;
			case 31: keyword = "Godan verb ぶ"; isVerb=true; break;
			case 32: keyword = "Godan verb ぐ"; isVerb=true; break;
			case 33: keyword = "Godan verb く"; isVerb=true; break;
			case 34: keyword = "Irr verb いく"; isVerb=true; break;
			case 35: keyword = "Godan verb む"; isVerb=true; break;
			case 36: keyword = "Godan verb ぬ"; isVerb=true; break;
			case 37: keyword = "Godan verb る"; isVerb=true; break;
			case 39: keyword = "Godan verb す"; isVerb=true; break;
			case 40: keyword = "Godan verb つ"; isVerb=true; break;
			case 41: keyword = "Godan verb う"; isVerb=true; break;
			case 45: keyword = "Irr verb くる"; isVerb=true; break;
			case 46: keyword = "Irr verb する"; isVerb=true; break;
			case 47: keyword = "Irr verb する"; isVerb=true; break;
			case 48: keyword = "Irr verb する"; isVerb=true; break;
			case 67: keyword = "Proper noun"; isVerb=false; break;
			case 68: keyword = "Verb"; isVerb=true; break;
			case 98: keyword = "Unclassified"; isVerb=false; break;
		}
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public boolean isVerb()
	{
		return isVerb;
	}
	public void setVerb(boolean isVerb)
	{
		this.isVerb = isVerb;
	}
	@Override
	public String toString()
	{
		return keyword;
	}
	
	

}
