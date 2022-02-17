/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author gabrijela
 */
public class Dretva implements Runnable{

    public Polinomi p;
    public int metoda;
    public Complex[] rjesenje;
    public long vrijeme;
    
    public Dretva(Polinomi pol, int m){
        p = pol;
        metoda = m;
    } 
    @Override
    public void run(){
        try{
            System.out.println("OVO je metoda: " + metoda);
            if(metoda == 1){
            // Izabrali ste standardno množenje polinoma
            long start = System.currentTimeMillis();
            rjesenje = p.mnoziStandardno();
            long end = System.currentTimeMillis();
            vrijeme = end - start;
            }
            else if(metoda == 2){
                // rekurzivna varijanta Fourierove transformacije
                long start = System.currentTimeMillis();
                rjesenje  = p.mnoziFFT();
                long end = System.currentTimeMillis();
                vrijeme = end - start;
            }
            else if(metoda == 3){
                // Iterativna metoda množenja
                long start = System.currentTimeMillis();
                rjesenje = p.mnoziIterativniFFT();
                long end = System.currentTimeMillis();
                vrijeme = end - start;
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
        } 
    }
    
    public Complex[] vratiRjesenje(){
        return rjesenje;
    }
    public long vratiVrijeme(){
        return vrijeme;
    }
}
