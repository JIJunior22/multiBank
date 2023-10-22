package br.com.DevSuperior.Main;

import br.com.DevSuperior.Model.ContaBancaria;

public class Main {
    public static void main(String[] args) {
        ContaBancaria contaJunior = new ContaBancaria();
//        contaJunior.depositar(50);
//        contaJunior.depositar(10);
//        contaJunior.depositar(40);

        contaJunior.cadastrar();
        contaJunior.depositar();
        contaJunior.sacar();
        contaJunior.sacar();
        contaJunior.sacar();


    }
}