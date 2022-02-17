
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nenab
 */
public final class Polinomi {
    public static int id = new Random().nextInt(1000);
    public Complex[] prvi_polinom;
    public Complex[] drugi_polinom;
    public int dimenzija;
    public int dva_n;
    
    public Polinomi(int stupanj){
        id++;
        dimenzija = stupanj;
        prvi_polinom = napuni();
        drugi_polinom = napuni();
    }
    
    static boolean potencijaOdDva(int n)
    {
        return (int)(Math.ceil((Math.log(n) / Math.log(2))))
            == (int)(Math.floor(((Math.log(n) / Math.log(2)))));
    }

    Polinomi() {
        
    }
    public Complex[] napuni()
    {
        Complex[] niz;
        int s = dimenzija;
        if(potencijaOdDva(dimenzija))
            niz = new Complex[s * 2];
        else{
            while(!potencijaOdDva(s))
            {
                s++;
            }
            niz = new Complex[s * 2];
        }
        
        Random ra = new Random();
        Complex nula = new Complex(0.0,0.0);
        for(int i = 0; i < dimenzija; i++){
            niz[i] = new Complex((double)ra.nextInt(100), (double)ra.nextInt(100));
        }
        for(int i = dimenzija; i < s * 2; i++){
            niz[i] = nula;
        }
        dva_n = s * 2; // Zapamnti do proširenja
        return niz;
    }
    public String ispisPolinoma(Complex[] niz)
    {
        String s1 = "";
        for(int i = 0; i < dimenzija; i++)
        {
            if(i == 0)
            {
                s1 += niz[i].toString() + " + ";

            }
            else
            {
               String kon1 = niz[i].toString() + "x^" + i +" + ";
               s1 += kon1;

            }
        }
        String s = "";
        for( int i = 0; i < s1.length()-2; i++)
                s += s1.charAt(i);
        return s;
    }
    
    public Complex[] mnoziIterativniFFT(){
        Complex[] rezultat = new Complex[dva_n];
        Complex[] a = prvi_polinom.clone();
        Complex[] b = drugi_polinom.clone();
        a = IterativnaVerzija.iterativniFFT(a, dva_n);
        b = IterativnaVerzija.iterativniFFT(b, dva_n);
        rezultat = IterativnaVerzija.mnozi(a, b);
        rezultat = IterativnaVerzija.inverz(rezultat);
        return rezultat;
    }
    public Complex[] mnoziStandardno(){
        Complex[] rezultat = new Complex[dimenzija * 2];
        Complex nula = new Complex(0.0, 0.0);
        for(int i = 0; i < dimenzija * 2; i++)
            rezultat[i] = nula;

        Complex[] a = prvi_polinom.clone();
        Complex[] b = drugi_polinom.clone();
        
        for(int i = 0; i < dimenzija; i++){
            for(int j = 0; j < dimenzija; j++){
                Complex t = a[i].times(b[j]);
                rezultat[i + j] = rezultat[i + j].plus(t);
            }
        }
        return rezultat;
    }
    public Complex[] mnoziFFT(){
        Complex[] rezultat = new Complex[dva_n];
        Complex[] a = prvi_polinom.clone();
        Complex[] b = drugi_polinom.clone();
        a = racunajFFT.fft(a);
        b = racunajFFT.fft(b);
        rezultat = racunajFFT.mult(a,b);
        rezultat = racunajFFT.invfft(rezultat);
        
        return rezultat;
    }
}
