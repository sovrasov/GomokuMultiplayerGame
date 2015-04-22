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
    
    int SetFieldElement(GameFieldCoordinates coordinates, FieldElemType value)
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
    
    private final char field[][];
}