package br.com.DevSuperior.Model;

import java.util.Random;

public abstract class Bank {
    private String titular;
    private String cpf;
    private double saldo=50.00;
    private double limiteSaque=300;
    //Deixando o Random aqui, evita que ele gere códigos diferentes para o mesmo objeto
    Random rd=new Random();
    private int numero=rd.nextInt(1000,9000);

    //Construtor vazio
    public Bank(){

    }
    //construtor com argumentos

    public Bank(int numero, String titular, double saldo, double limiteSaque) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = saldo;
        this.limiteSaque = limiteSaque;
    }
    //Metodos especiais

    public int getNumero() {


        return numero;
    }

    public void setNumero() {


    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        // Remove todos os caracteres não numéricos do CPF/CNPJ
        this.cpf = cpf.replaceAll("[^0-6]", "");


        // Verifica se o CNPJ tem 14 dígitos
        if (cpf.length() != 11) {
            throw new IllegalArgumentException("\u001B[31mCPF inválido, deve conter 11 dígitos.\u001B[m");
        }
        // Aplica a máscara
        String cpfMascarado = cpf.substring(0, 3)
                + "." + cpf.substring(3, 6)
                + "." + cpf.substring(6, 9)
                + "-" + cpf.substring(9, 11);

        this.cpf = cpfMascarado;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getLimiteSaque() {
        return limiteSaque;
    }

    public void setLimiteSaque(double limiteSaque) {
        this.limiteSaque = limiteSaque;
    }


    //Metodos


}
