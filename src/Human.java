public class Human extends Player {

    private String name;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void surrender()
    {

    }

    public Human(String name)
    {
        setName(name);
        setType("human");
    }

    public Human()
    {
        setName("Player 1");
        setType("human");
    }

}
