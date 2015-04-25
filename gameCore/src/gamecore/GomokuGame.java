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
        lastMove = null;
    }

    public int MakeMove(GameFieldCoordinates coordinates, UUID playerID)
    {
        if(isFirstMovesNow && playerID.equals(firstPlayer.getID()))
        {
            isFirstMovesNow = false;
            lastMove = coordinates;
            return field.SetFieldElement(coordinates, FieldElemType.Black);
        }
        else if(!isFirstMovesNow && playerID.equals(secondPlayer.getID()))
        {
            isFirstMovesNow = true;
            lastMove = coordinates;
            return field.SetFieldElement(coordinates, FieldElemType.White);
        }
        return -1;
    }

    public GameStatus CheckGameStatus()
    {
        if(field.CheckForWin(lastMove))
            if(isFirstMovesNow)
                return GameStatus.firstWins;
            else
                return GameStatus.secondWins;
        else
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
        if(firstPlayer.getID().equals(playerID) ||
            secondPlayer.getID().equals(playerID))
            return true;
        else
            return false;
    }

    boolean isFirstMovesNow;
    GameFieldCoordinates lastMove;
    private GameField field;
    private Player firstPlayer;
    private Player secondPlayer;
}
