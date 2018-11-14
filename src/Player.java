public class Player {

    private Hand hand;

    private String type;

    private String name;

    public String getName()
    {
        return name;
    }

    public String getType()
    {
        return type;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public Player(String name, String type)
    {
        setName(name);
        setType(type);
    }
}
