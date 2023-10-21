package br.com.DevSuperior.Model;

import br.com.DevSuperior.Interfaces.AcoesBancarias;
import br.com.DevSuperior.Interfaces.ExtratoBancario;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class ContaBancaria extends Bank implements AcoesBancarias, ExtratoBancario {
    //atributos


  //Construtor


    public ContaBancaria() {

    }
    public void cadastrar(){
        Scanner sc=new Scanner(System.in);

        System.out.println("== DADOS CADASTRAIS ==");
        System.out.print("Nome do titular: ");
        setTitular(sc.nextLine());
        System.out.print("CPF: ");
        setCpf(sc.next());


        System.out.println("== DADOS DO CLIENTE ==");
        System.out.println("Titular: "+getTitular().toUpperCase());
        System.out.println("Numero: "+getNumero());
        System.out.println("CPF: "+getCpf());
        System.out.println("Saldo R$: "+getSaldo());
        System.out.println("Limite de saque diário R$: "+getLimiteSaque());
        System.out.println("Conta criada com sucesso!");
        System.out.println("-".repeat(20));
    }

    @Override
    public void depositar() {
        Scanner sc=new Scanner(System.in);
        System.out.print("Quanto deseja depositar? R$:");
        double quantia=sc.nextDouble();
        setSaldo(getSaldo()+quantia);
        System.out.println("Depósito de R$:"+quantia);
        System.out.println("Seu saldo atual é R$:"+getSaldo());
        System.out.println("-".repeat(20));
    }

    @Override
    public void sacar() {
        Scanner sc=new Scanner(System.in);
        System.out.print("Quanto deseja sacar? R$:");
        double sacarValor=sc.nextDouble();


        if(sacarValor<=getLimiteSaque()&&sacarValor<=getLimiteSaque()){
            setSaldo((getSaldo()-sacarValor));
            System.out.println("Saque de R$: "+sacarValor);
            System.out.println("Seu saldo atual é R$: "+getSaldo());
            System.out.println("-".repeat(20));
        } else if (getSaldo()<sacarValor) {
            System.out.println("Erro de Saque:Saldo insuficiente!");
        } else{
            System.out.println("Erro de Saque:Valor acima do limite permitido!");

        }

    }

    @Override
    public void extrato() {
        //Mostrar a data atual
        LocalDate dataAtual= LocalDate.now();
        //Exibe a data no formato brasileiro
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = dataAtual.format(formatter);

        //horario
        LocalTime horaAtual = LocalTime.now();
        DateTimeFormatter horarioOPeracao = DateTimeFormatter.ofPattern("HH:mm:ss");
        String horaFormatada = horaAtual.format(horarioOPeracao);

        System.out.println("== EXTRATO BANCARIO ==");
        System.out.println("Data da operação: "+dataFormatada);
        System.out.println("Horário da operação: " + horaFormatada);

        System.out.println("Cliente: "+getTitular().toUpperCase());
        System.out.println("Numero: "+getNumero());
        System.out.println("CPF: "+getCpf());
        System.out.println("Saldo R$: "+getSaldo());
        System.out.println("Limite de saque diário R$: "+getLimiteSaque());

        System.out.println("-".repeat(20));

    }
}
