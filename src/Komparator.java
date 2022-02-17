
import java.util.Comparator;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author gabrijela
 */
public class Komparator implements Comparator<UBazi>{

    @Override
    public int compare(UBazi o1, UBazi o2) {
        if(o1.dimenzija > o2.dimenzija)
            return 1;
        else if(o1.dimenzija < o2.dimenzija)
            return -1;
        else
            return 0;
    }
    
}
