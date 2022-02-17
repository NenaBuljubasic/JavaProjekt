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
    static { System.loadLibrary("standardnoJNI"); } 
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
            else if(metoda == 4){
                int[] a_real;
                int[] a_imag;
                int[] b_real;
                int[] b_imag;
                int[] c_real;
                int[] c_imag;
                a_real = new int[p.dimenzija];
                a_imag = new int[p.dimenzija];
                b_real = new int[p.dimenzija];
                b_imag = new int[p.dimenzija];
                c_real = new int[p.dimenzija*2];
                c_imag = new int[p.dimenzija*2];
                for(int i = 0; i < p.dimenzija; i++)
                {
                    a_real[i] = (int)p.prvi_polinom[i].real;
                    a_imag[i] = (int)p.prvi_polinom[i].imag;

                    b_real[i] = (int)p.drugi_polinom[i].real;
                    b_imag[i] = (int)p.drugi_polinom[i].imag;
                }
                for(int j = 0; j < p.dimenzija *2; j++ )
                {
                    c_real[j] = 0;
                    c_imag[j] = 0;
                }
                // Standardna JNI metoda množenja 
                long start = System.currentTimeMillis();
                p.mnoziStandJNI(a_real, a_imag, b_real, b_imag, c_real, c_imag, p.dimenzija);
                long end = System.currentTimeMillis();
                vrijeme = end - start;
                System.out.println("JNI vrijeme:"+vrijeme);
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
