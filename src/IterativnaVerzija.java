

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author gabrijela
 */
public class IterativnaVerzija {
    public static int bitReverse(int x, int log2n){
        int n = 0;
        for(int i = 0; i < log2n; i++){
            n <<= 1;
            n |= (x & 1);
            x >>= 1;
        }
        return n;
    }
    
    public static Complex[] iterativniFFT(Complex[] a, int n){
        
        Complex[] A = new Complex[n];
        int lgn = 0;
        int p = 1;
        while(p != n){
            p *= 2;
            lgn++;
        }
        
        // Napuni vektor A željenim poretkom
        for(int i = 0; i < n; i++){
            int rev = bitReverse(i, lgn);
            A[i] = a[rev];
        }
        
        for(int s = 1; s <= lgn; s++){
            int m = (int)Math.pow(2, s);
            double Re = Math.cos((2*Math.PI)/m);
            double Im = Math.sin(2 * Math.PI / m);
            Complex wm = new Complex(Re, Im);
            
            
            for(int k = 0; k < n; k+= m){
                
                Complex w = new Complex(1,0);
                
                for(int j = 0; j < m / 2; j++){
                    Complex t = w.times(A[k + j + (m/2)]);
                    Complex u = A[k + j];
                    
                    A[k + j] =  u.plus(t);
                    A[k + j + (m/2)] =  u.minus(t);
                    Complex temp = w.times(wm);
                    w = temp;
                }
            }
        }
        return A;
    }
    
    public static Complex[] inverz(Complex[] x){
        Complex[] y = new Complex[x.length];
        
        for(int i = 0; i < x.length; i++){
            y[i] = x[i].conjugate();
        }
        Complex[] temp = iterativniFFT(y, y.length);
        y = temp;
        
        for(int i = 0; i < y.length; i++){
            Complex t = y[i].conjugate();
            y[i] =  t;
        }
        for(int i = 0; i < y.length; i++){
            Complex t = y[i].scale(1.0 / (y.length));
            y[i] = t;
        }
        return y;
    }
    
    public static Complex[] mnozi(Complex[] a, Complex[] b){
        Complex[] rezultat = new Complex[a.length];
        for(int i = 0; i < a.length; i++){
            rezultat[i] = a[i].times(b[i]);
        }
        return rezultat;
    }
}
