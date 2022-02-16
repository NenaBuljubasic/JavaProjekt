
import java.util.Random;
import java.util.Vector;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nenab
 */
public class Polinomi {
    public Vector<Complex> vektor_prvi;
    public Vector<Complex> vektor_drugi;
    public int stupanj_prvi;
    public int stupanj_drugi;
    
    
    public Polinomi(int prvi, int drugi){
        stupanj_prvi = prvi;
        stupanj_drugi = drugi;
        vektor_prvi = new Vector<>();
        vektor_drugi = new Vector<>();
        napuniVektor(stupanj_prvi, vektor_prvi);
        napuniVektor(stupanj_drugi, vektor_drugi);
    }
    
    static boolean potencijaOdDva(int n)
    {
        return (int)(Math.ceil((Math.log(n) / Math.log(2))))
            == (int)(Math.floor(((Math.log(n) / Math.log(2)))));
    }

    Polinomi() {
        
    }
    public void napuniVektor(int s, Vector<Complex> v){
        Random r = new Random();
        double re;
        double im;
        Complex broj;
            for(int i = 0; i < s; i++){
            
                re = r.nextDouble();
                im = r.nextDouble();
                broj = new Complex(re, im);
                v.add(broj);
            }
            while(!potencijaOdDva(s)){
                re = 0;
                im = 0;
                broj = new Complex(re, im);
                v.add(broj);
                s++;
            }
    }
    
    // Standardno množenje polinoma
    public Vector<Complex> pomnozi(){
        Vector<Complex> rezultat = new Vector<>();
        for(int i = 0; i < vektor_prvi.size() + vektor_drugi.size() - 1; i++){
            rezultat.add(new Complex(0,0));
        }
        for(int i = 0; i < vektor_prvi.size(); i++){
            for(int j = 0; j < vektor_drugi.size(); j++){
               Complex temp = rezultat.elementAt(i+j);
               Complex e = (vektor_prvi.get(i)).times(vektor_drugi.get(j));
               rezultat.setElementAt(temp.plus(e), i+j);
            }
        }
        return rezultat;
    }
    public Complex[] napuni(int dimenzija, int dimenzijaProsirena, int dimenzijaKonacna)
    {
        Complex[] niz;
        Random ra = new Random();
        String s1 = "";
        if(dimenzijaProsirena > dimenzija)
        {
            dimenzijaKonacna = dimenzijaProsirena;
            niz = new Complex[dimenzijaProsirena*2];
            for(int i = 0; i < dimenzija; i++)
            {
                niz[i] = new Complex((double)ra.nextInt(100), (double)ra.nextInt(100));
                
                System.out.println(niz[i]);
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
            Complex nula = new Complex(0.0,0.0);
            for(int i = dimenzija ; i < dimenzijaProsirena*2; i++)
            {
                niz[i] = nula;
                s1 += "";

            }
        }
        else
        {
            dimenzijaKonacna = dimenzija;
            niz = new Complex[dimenzija*2];
            for(int i = 0; i < dimenzija; i++)
            {
                niz[i] = new Complex((double)ra.nextInt(100), (double)ra.nextInt(100));
                
                System.out.println(niz[i]);
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
            Complex nula = new Complex(0.0,0.0);
            for(int i = dimenzija ; i < dimenzija*2; i++)
            {
                niz[i] = nula;
            }

            

    }
        return niz;
    }
    public String ispisPolinoma(Complex[] niz, int dimenzija, int dimenzijaKonacna)
    {
        String s1 = "";
        Random ra = new Random();
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
        
        for(int i = dimenzija ; i < dimenzijaKonacna*2; i++)
        {           
            String kon1 = niz[i].toString() + "x^" + i +" + ";
            s1 += "";
        }
        String s = "";
        for( int i = 0; i < s1.length()-2; i++)
                s += s1.charAt(i);
        return s;
    }
    
   
}
