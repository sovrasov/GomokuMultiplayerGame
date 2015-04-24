package gamecore;

import junit.framework.TestCase;

public class GameFieldTest extends TestCase {
    
    public GameFieldTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCheckForWin() {
        System.out.println("CheckForWin");
        GameFieldCoordinates coordinates = new GameFieldCoordinates(0,0);
        GameField instance = new GameField();
        for(int i=0; i<5; i++)
        {
            coordinates.setX(i);
            instance.SetFieldElement(coordinates, FieldElemType.Black);
        }
        
        boolean expResult = instance.CheckForWin(coordinates);
        assertEquals(expResult, true);
        
        instance = new GameField();
        coordinates.setY(5);
        for(int i=0; i<5; i++)
        {
            coordinates.setX(i);
            instance.SetFieldElement(coordinates, FieldElemType.Black);
        }
        expResult = instance.CheckForWin(coordinates);
        assertEquals(expResult, true);
        
        instance = new GameField();
        for(int i=0; i<5; i++)
        {
            coordinates.setX(i);
            coordinates.setY(i);
            instance.SetFieldElement(coordinates, FieldElemType.Black);
        }
        expResult = instance.CheckForWin(coordinates);
        assertEquals(expResult, true);
    }

    public void testGetFieldElement() {
        
        System.out.println("Set/GetFieldElement");
        GameFieldCoordinates coordinates = new GameFieldCoordinates(0,0);
        GameField instance = new GameField();
        FieldElemType expResult = FieldElemType.Empty;
        FieldElemType result = instance.GetFieldElement(coordinates);
        assertEquals(expResult, result);
        
        coordinates = new GameFieldCoordinates(5,5);
        instance.SetFieldElement(coordinates, FieldElemType.Black);
        result = instance.GetFieldElement(coordinates);
        expResult = FieldElemType.Black;
        assertEquals(expResult, result);
        
    }
    
}
