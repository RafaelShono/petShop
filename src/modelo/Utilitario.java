/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author rafael
 */
public class Utilitario {
    public static String formatarMoeda(float valor){
      Locale locale = new Locale("pt", "BR");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
       return (currencyFormatter.format(valor));
    
    }
    
}
