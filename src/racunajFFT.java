/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nenab
 */
public class racunajFFT {
    public static Complex[] fft(Complex[] a)
    {
        int n = a.length;
        if( n == 1)
            return a;
        
        Complex[] parni = new Complex[n/2];
        Complex[] neparni = new Complex[n/2];

        for (int i = 0; i < n / 2; i++)
        {
           parni[i] = a[i*2];
           neparni[i] = a[i*2+1];
        }
        Complex[] y0 = fft(parni);
        Complex[] y1 = fft(neparni);
        
        Complex[] y = new Complex[n];
        
        for (int k = 0; k < n / 2; k++)
        {
            double alpha = 2 * Math.PI * k / n;
            Complex c = new Complex(Math.cos(alpha), Math.sin(alpha));
            y[k] = y0[k].plus(c.times(y1[k]));
            y[k+n/2] = y0[k].minus(c.times(y1[k]));
        }
        return y;       
        
        
        
    }
    
    public static Complex[] ifft(Complex[] a)
    {
        int n = a.length;
        if( n == 1)
            return a;
        
        Complex[] parni = new Complex[n/2];
        Complex[] neparni = new Complex[n/2];


        for (int i = 0; i < n / 2; i++)
        {
            parni[i] = a[i*2];
            neparni[i] = a[i*2+1];
        }
        Complex[] y0 = ifft(parni);
        Complex[] y1 = ifft(neparni);
        
        Complex[] y = new Complex[n]; // duljine n
        
        for (int k = 0; k < n / 2; k++)
        {
            double alpha = 2 * Math.PI * k / n;
            Complex c = new Complex(Math.cos(alpha), Math.sin(alpha));
            y[k] = y0[k].plus(y1[k].divides(c));
            y[k+n/2] = y0[k].minus(y1[k].divides(c));
        }
        return y;
    }
    public static Complex[] invfft(Complex[] a)
    {
        a = ifft(a);
        int n = a.length;
        for(int i=0;i<n;i++)
        {
            Complex c = new Complex((double)n,0.0);
            a[i] = a[i].divides(c);
        }
        return a;
    }

    public static Complex[] mult(Complex[] a,Complex[] b)
    {
        int n = Math.max(a.length,b.length);
        Complex[] c = new Complex[n];//duljine n
        for (int i=0;i<n;i++)
        {
            c[i] = a[i].times(b[i]);
        }
        return c;
    }
    
}
