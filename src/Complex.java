
import java.text.DecimalFormat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nenab
 */
public class Complex {
    double real;
    double imag;
    public Complex(double my_real, double my_imag){
       this.real = my_real;
       this.imag = my_imag;
    }

    public Complex conjugate()
    {
        this.imag = - this.imag;
        return this;
    }
    public Complex times(Complex b) {
         Complex a = this;
         double real = a.real * b.real - a.imag * b.imag;
         double imag = a.real * b.imag + a.imag * b.real;
         return new Complex(real, imag);
     }
    public Complex plus(Complex b) {
         Complex a = this;             // invoking object
         double real = a.real + b.real;
         double imag = a.imag + b.imag;
         return new Complex(real, imag);
     }

     // return a new Complex object whose value is (this - b)
     public Complex minus(Complex b) {
         Complex a = this;
         double real = a.real - b.real;
         double imag = a.imag - b.imag;
         return new Complex(real, imag);
     }
     public String toString() {
         DecimalFormat df = new DecimalFormat("#.##");
         if ((imag > -0.05 && imag < 0.05) && (real > -0.05 && real < 0.05)) return "";
         if ((imag > -0.05 && imag < 0.05) && (real > 0.05 || real < 0)  ) return df.format(real) + "";
         if ((real > -0.05 && real < 0.05) && (imag > 0.05 || imag < 0)) return df.format(imag) + "i";
         if (imag <  0) return df.format(real) + " - " + df.format(-imag) + "i";

         return "("+df.format(real) + " + " + df.format(imag) + "i" + ")";
     }
     public Complex scale(double alpha) {
         return new Complex(alpha * real, alpha * imag);
     }
     public Complex divides(Complex b) {
         Complex a = this;
         return a.times(b.reciprocal());
     }
      public Complex reciprocal() {
         double scale = real*real + imag*imag;
         return new Complex(real / scale, -imag / scale);
     }
     
    
}
