package flashcard.entity;

public class Deck
{
	int deckID;
	String deckName;
	int amountDue;
	public Deck(int deckID, String deckName, int amountDue)
	{
		super();
		this.deckID = deckID;
		this.deckName = deckName;
		this.amountDue = amountDue;
	}
	public Deck()
	{
		
	}
	public int getDeckID()
	{
		return deckID;
	}
	public String getDeckName()
	{
		return deckName;
	}
	public int getAmountDue()
	{
		return amountDue;
	}
	public void setDeckID(int deckID)
	{
		this.deckID = deckID;
	}
	public void setDeckName(String deckName)
	{
		this.deckName = deckName;
	}
	public void setAmountDue(int amountDue)
	{
		this.amountDue = amountDue;
	}
	@Override
	public String toString()
	{
		return deckName;
	}
	

}
