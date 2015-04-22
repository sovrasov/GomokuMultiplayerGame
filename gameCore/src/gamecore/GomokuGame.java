package gamecore;

import java.util.UUID;

public class GomokuGame {

    public GomokuGame(Player _firstPlayer, Player _secondPlayer)
    {
        firstPlayer = _firstPlayer;
        secondPlayer = _secondPlayer;
        isFirstMovesNow = true;
        field = new GameField();
    }
    public GomokuGame()
    {
        firstPlayer = null;
        secondPlayer = null;
        field = null;
    }
    
    public int MakeMove(GameFieldCoordinates coordinates, UUID playerID)
    {
        if(isFirstMovesNow == true && playerID == firstPlayer.getID())
        {
            isFirstMovesNow = false;
            return field.SetFieldElement(coordinates, FieldElemType.Black);
        }
        else if(isFirstMovesNow != true && playerID == secondPlayer.getID())
        {
            isFirstMovesNow = true;
            return field.SetFieldElement(coordinates, FieldElemType.White);
        }
        return -1;
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
    
    public boolean hasPlayer(UUID playerID)
    {
        if(firstPlayer.getID() == playerID || secondPlayer.getID() == playerID)
            return true;
        else
            return false;
    }
    
    boolean isFirstMovesNow;
    private GameField field;
    private Player firstPlayer;
    private Player secondPlayer;
}
