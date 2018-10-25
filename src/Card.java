import javax.swing.*;

public class Card {

    private String suit;
    private String value;

    public String getSuit()
    {
        return suit;
    }

    public String getValue()
    {
        return value;
    }

    public void setSuit(String suit)
    {
        this.suit = suit;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public Card(int suit, int value)
    {
        switch (suit)
        {
            case 0:
                setSuit("Clubs");
                break;
            case 1:
                setSuit("Spades");
                break;
            case 2:
                setSuit("Hearts");
                break;
            case 3:
                setSuit("Diamonds");
                break;
        }

        switch (value)
        {
            case 0:
                setValue("Ace");
                break;
            case 1:
                setValue("Two");
                break;
            case 2:
                setValue("Three");
                break;
            case 3:
                setValue("Four");
                break;
            case 4:
                setValue("Five");
                break;
            case 5:
                setValue("Six");
                break;
            case 6:
                setValue("Seven");
                break;
            case 7:
                setValue("Eight");
                break;
            case 8:
                setValue("Nine");
                break;
            case 9:
                setValue("Ten");
                break;
            case 10:
                setValue("Jack");
                break;
            case 11:
                setValue("Queen");
                break;
            case 12:
                setValue("King");
                break;

        }
    }
}
