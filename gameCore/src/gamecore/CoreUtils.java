package gamecore;

public class CoreUtils {
    
    public static int imax(int i, int j)
    {
        if(i>=j)
            return i;
        else
            return j;
    }
    
    public static int imin(int i, int j)
    {
        if(i>=j)
            return j;
        else
            return i;
    }
}