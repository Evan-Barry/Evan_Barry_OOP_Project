public class Card {

    private Suit suit;
    private Value value;

    public Suit getSuit()
    {
        return suit;
    }

    public Value getValue()
    {
        return value;
    }

    public void setSuit(Suit suit)
    {
        this.suit = suit;
    }

    public void setValue(Value value)
    {
        this.value = value;
    }

    public Card(int suit, int value)
    {
        switch (suit)
        {
            case 0:
                setSuit(Suit.Clubs);
                break;
            case 1:
                setSuit(Suit.Spades);
                break;
            case 2:
                setSuit(Suit.Hearts);
                break;
            case 3:
                setSuit(Suit.Diamonds);
                break;
        }

        switch (value)
        {
            case 0:
                setValue(Value.Ace);
                break;
            case 1:
                setValue(Value.Two);
                break;
            case 2:
                setValue(Value.Three);
                break;
            case 3:
                setValue(Value.Four);
                break;
            case 4:
                setValue(Value.Five);
                break;
            case 5:
                setValue(Value.Six);
                break;
            case 6:
                setValue(Value.Seven);
                break;
            case 7:
                setValue(Value.Eight);
                break;
            case 8:
                setValue(Value.Nine);
                break;
            case 9:
                setValue(Value.Ten);
                break;
            case 10:
                setValue(Value.Jack);
                break;
            case 11:
                setValue(Value.Queen);
                break;
            case 12:
                setValue(Value.King);
                break;

        }
    }
}
