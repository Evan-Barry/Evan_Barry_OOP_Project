public interface EndGameConditions {

    void bust(Player loser, Player winner);

    void winner(Player winner);

    void draw();

}
