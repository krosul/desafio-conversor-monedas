package Menu;

import Client.ClientAPI;
import Models.CurrencyModel;

import java.util.Currency;
import java.util.Scanner;

public class MenuMain {

    static Scanner teclado = new Scanner(System.in);
    static String breakLine = "*************************";
    static String dolarSiglas = "USD";
    static String realBrasileñoSiglas = "BRL";
    static String pesoColombianoSiglas = "COP";
    static String pesoArgentinoSiglas = "ARS";
    static ClientAPI cliente = new ClientAPI();
    static boolean flag=true;

    public static void main(String[] args) {
        while (flag) {
            handleMenu();

        }
    }

    public static void handleMenu() {
        System.out.println(breakLine);
        System.out.println("Sea bienvenido al conversor de monedas :)");
        System.out.println("Elije alguna de estas opciones:");
        System.out.println("1)Dolar => Peso argentino");
        System.out.println("2)Peso Argentino =>Dolar");
        System.out.println("3)Dolar=>Real Brasileño");
        System.out.println("4)Real Brasileño =>Dolar");
        System.out.println("5)Dolar =>Peso colombiano");
        System.out.println("6)Peso colombiano =>Dolar");
        System.out.println("7)Salir");
        int option = teclado.nextInt();
        if (option==7){
            flag=false;
            return;
        }

        System.out.println("Ingresa la cantidad que deseas convertir:");
        double quantity = teclado.nextDouble();
        switch (option) {
            case 1: {
                CurrencyModel res = cliente.getCurrency(dolarSiglas);
                double arg = res.conversion_rates.get(pesoArgentinoSiglas);
                double result = quantity * arg;
                System.out.println(quantity + dolarSiglas + "son:" + result + pesoArgentinoSiglas);
                return;
            }
            case 2: {
                CurrencyModel res = cliente.getCurrency(pesoArgentinoSiglas);
                double arg = res.conversion_rates.get(dolarSiglas);
                double result = quantity * arg;
                System.out.println(quantity + pesoArgentinoSiglas + " son: " + result + dolarSiglas);
                return;
            }
            case 3:{
                CurrencyModel res = cliente.getCurrency(dolarSiglas);
                double brl= res.conversion_rates.get(realBrasileñoSiglas);
                double result= quantity*brl;
                System.out.println(quantity + dolarSiglas + " son: " + result + realBrasileñoSiglas);
                return;
            }
            case 4:{
                CurrencyModel res= cliente.getCurrency(realBrasileñoSiglas);
                double dollars= res.conversion_rates.get(dolarSiglas);
                double result= quantity*dollars;
                System.out.println(quantity + realBrasileñoSiglas + " son: " + result + dolarSiglas);
                return;
            }
            case 5: {
                CurrencyModel res = cliente.getCurrency(dolarSiglas);
                double pesos = res.conversion_rates.get(pesoColombianoSiglas);
                double result = quantity * pesos;
                System.out.println(quantity + dolarSiglas + " son: " + result + pesoColombianoSiglas);
                return;
            }
            case 6: {
                CurrencyModel res = cliente.getCurrency(pesoColombianoSiglas);
                double dollars = res.conversion_rates.get(dolarSiglas);
                double result = quantity * dollars;
                System.out.println(quantity + pesoColombianoSiglas + " son: " + result + dolarSiglas);
                return;
            }

        }

    }
}
