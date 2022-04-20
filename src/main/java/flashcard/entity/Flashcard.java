package flashcard.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Flashcard
{
	private static final double FREQUENCY_MODIFIER = 2.0;
	int cardID;
	int deckID;
	String front;
	String back;
	double frequencyMultiplier;
	LocalDateTime due;
	int streak;
	public Flashcard(int cardID, int deckID, String front, String back, double frequencyMultiplier, LocalDateTime due,
			int streak)
	{
		super();
		this.cardID = cardID;
		this.deckID = deckID;
		this.front = front;
		this.back = back;
		this.frequencyMultiplier = frequencyMultiplier;
		this.due = due;
		this.streak = streak;
	}
	public Flashcard()
	{
		this.frequencyMultiplier = 1.0;
		this.streak = 0;
		setDueNow();
	}
	public int getCardID()
	{
		return cardID;
	}
	public String getFront()
	{
		return front;
	}
	public String getBack()
	{
		return back;
	}
	public double getFrequencyMultiplier()
	{
		return frequencyMultiplier;
	}
	public LocalDateTime getDue()
	{
		return due;
	}
	public String getDueString()
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		return formatter.format(due);
	}
	public int getStreak()
	{
		return streak;
	}
	public int getDeckID()
	{
		return deckID;
	}
	public void setCardID(int cardID)
	{
		this.cardID = cardID;
	}
	public void setFront(String front)
	{
		this.front = front;
	}
	public void setBack(String back)
	{
		this.back = back;
	}
	public void setFrequencyMultiplier(double frequencyMultiplier)
	{
		if(frequencyMultiplier <= 0)
		{
			this.frequencyMultiplier = 0;
		}
		else
		{
			this.frequencyMultiplier = frequencyMultiplier;
		}
	}
	public void setDue(LocalDateTime due)
	{
		this.due = due;
	}
	public void setDue(String due)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		LocalDateTime date = LocalDateTime.parse(due, formatter);
		this.due = date;
	}
	public void setStreak(int streak)
	{
		this.streak = streak;
	}
	public void setDeckID(int deckID)
	{
		this.deckID = deckID;
	}
	@Override
	public String toString()
	{
		return front + back;
	}
	public void setDueNow()
	{
		this.due =  LocalDateTime.now();
	}
	public boolean isDue()
	{
		LocalDateTime now = LocalDateTime.now();		
		return due.isBefore(now) || due.isEqual(now);
	}
	public void miss()
	{
		setStreak(0);
		setFrequencyMultiplier(this.frequencyMultiplier/(2*FREQUENCY_MODIFIER));
		calculateDueDate();
	}
	public void good()
	{
		setStreak(getStreak()+1);
		setFrequencyMultiplier(this.frequencyMultiplier*FREQUENCY_MODIFIER);
		calculateDueDate();
		
	}
	public void great()
	{
		setStreak(getStreak()+1);
		setFrequencyMultiplier(this.frequencyMultiplier*(2*FREQUENCY_MODIFIER));
		calculateDueDate();
	}
	public void calculateDueDate()
	{
		double hoursToAdd = getStreak() * getFrequencyMultiplier();
		setDue(LocalDateTime.now().plusHours((long) hoursToAdd));
	}
	
}
