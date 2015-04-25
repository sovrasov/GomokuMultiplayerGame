package gamecore;

enum FieldElemType
{
    Empty, White, Black
}

public class GameField {
    public GameField()
    {
        field = new char[GlobalConstants.fieldSize][GlobalConstants.fieldSize];
    }

    public int SetFieldElement(GameFieldCoordinates coordinates, FieldElemType value)
    {
        if(field[coordinates.getX()][coordinates.getY()] == 0)
        {
            if(value == FieldElemType.Black)
                field[coordinates.getX()][coordinates.getY()] = 1;
            else if(value==FieldElemType.White)
                field[coordinates.getX()][coordinates.getY()] = 2;
            else
                field[coordinates.getX()][coordinates.getY()] = 0;
            return 0;
        }
        else
            return -1;
    }

    public boolean CheckForWin(GameFieldCoordinates coordinates)
    {
        int iLowerBound = CoreUtils.imax(0, coordinates.getX() - 2);
        int iUpperBound = CoreUtils.imin(14, coordinates.getX() + 2);

        int jLowerBound = CoreUtils.imax(0, coordinates.getY() - 2);
        int jUpperBound = CoreUtils.imin(14, coordinates.getY() + 2);

        for(int i = iLowerBound; i <= iUpperBound; i++)
            for(int j = jLowerBound; j <= jUpperBound; j++)
                if(CheckDiagonals(i, j) || CheckHorozintal(i, j)
                        || CheckVertical(i, j))
                    return true;
        return false;
    }

    FieldElemType GetFieldElement(GameFieldCoordinates coordinates)
    {
        char fieldElemValue = field[coordinates.getX()][coordinates.getY()];
        FieldElemType elemType;
        switch(fieldElemValue)
        {
            case 0:
                elemType = FieldElemType.Empty;
                break;
            case 1:
                elemType = FieldElemType.Black;
                break;
            case 2:
                elemType = FieldElemType.White;
                break;
            default:
                elemType = FieldElemType.Empty;
        }
        return elemType;
    }

    private boolean CheckHorozintal(int i, int j)
    {
        if( i > 1 && i < 13 && field[i][j] != 0)
        {
            char refValue = field[i][j];
            if(field[i-2][j] == refValue && field[i-1][j] == refValue
                    && field[i+1][j] == refValue && field[i+2][j] == refValue)
                return true;
            else
                return false;
        }
        else
            return false;
    }
    private boolean CheckVertical(int i, int j)
    {
        if( j > 1 && j < 13 && field[i][j] != 0)
        {
            char refValue = field[i][j];
            if(field[i][j-2] == refValue && field[i][j-1] == refValue
                    && field[i][j+1] == refValue && field[i][j+2] == refValue)
                return true;
            else
                return false;
        }
        else
            return false;
    }
    private boolean CheckDiagonals(int i, int j)
    {
        if( i > 1 && i < 13 && j > 1 && j < 13 && field[i][j] != 0)
        {
            char refValue = field[i][j];
            if(field[i-2][j-2] == refValue && field[i-1][j-1] == refValue
                    && field[i+1][j+1] == refValue && field[i+2][j+2] == refValue)
                return true;
            else if(field[i-2][j+2] == refValue && field[i-1][j+1] == refValue
                    && field[i+1][j-1] == refValue && field[i+2][j-2] == refValue)
                return true;
            else
                return false;
        }
        else
            return false;
    }

    private final char field[][];
}