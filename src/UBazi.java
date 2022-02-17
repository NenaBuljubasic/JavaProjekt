/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author gabrijela
 */
public class UBazi {
    public int ID;
    public int dimenzija;
    public int standardni;
    public int rekFFT;
    public int iterFFT;
    public int standJNI;
    
    public UBazi(int a, int b, int c, int d, int e, int f){
        ID = a;
        dimenzija = b;
        standardni = c;
        rekFFT = d;
        iterFFT = e;
        standJNI = f;
    }
    
    @Override
    public String toString(){
        return "ID: " + ID + ", dimenzija: " + dimenzija + ", stand: " + standardni +
                ", rek: " + rekFFT + ", iter: " + iterFFT + ", standJNI: " + standJNI;
    }
}
