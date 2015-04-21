package gamecore;

public class GomokuGame {

    public GomokuGame(Player _firstPlayer, Player _secondPlayer)
    {
        firstPlayer = _firstPlayer;
        secondPlayer = _secondPlayer;
        isfirstMovesNow = true;
        field = new GameField();
    }
    public GomokuGame()
    {
        firstPlayer = null;
        secondPlayer = null;
        field = null;
    }
    
    public void MakeMove(GameFieldCoordinates coordinates)
    {
        
    }
    
    public GameStatus CheckGameStatus()
    {
        return GameStatus.inProcess;
    }
    
    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }
    
    boolean isfirstMovesNow;
    private GameField field;
    private Player firstPlayer;
    private Player secondPlayer;
}
