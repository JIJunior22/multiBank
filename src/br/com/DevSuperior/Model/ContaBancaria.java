package br.com.DevSuperior.Model;

import br.com.DevSuperior.Interfaces.AcoesBancarias;
import br.com.DevSuperior.Interfaces.ExtratoBancario;
import br.com.DevSuperior.Interfaces.ValidarAcoes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class ContaBancaria extends Bank implements AcoesBancarias, ExtratoBancario, ValidarAcoes {
    //atributos


    //Construtor


    public ContaBancaria() {

    }

    public void cadastrar() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\u001B[33m== DADOS CADASTRAIS ==\u001B[m");
        System.out.print("\u001B[33mNome do titular: \u001B[m");
        setTitular(sc.nextLine());
        System.out.print("\u001B[33mCPF: \u001B[m");
        setCpf(sc.next());


        System.out.println("\u001B[33m== DADOS DO CLIENTE ==\u001B[m");
        System.out.println("\u001B[33mTitular: \u001B[m" + getTitular().toUpperCase());
        System.out.println("\u001B[33mNumero: \u001B[m" + getNumero());
        System.out.println("\u001B[33mCPF: \u001B[m" + getCpf());
        System.out.println("\u001B[33mSaldo R$: \u001B[m" + getSaldo());
        System.out.println("\u001B[33mLimite de saque diário R$: \u001B[m" + getLimiteSaque());
        System.out.println("\u001B[34mConta criada com sucesso!\u001B[m");
        System.out.println("\u001B[33m-\u001B[m".repeat(20));
    }

    @Override
    public void depositar() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\u001B[33mQuanto deseja depositar? R$:\u001B[m");
        double quantia = sc.nextDouble();
        setSaldo(getSaldo() + quantia);
        System.out.println("\u001B[33mDepósito de R$:\u001B[m" + quantia);
        System.out.println("\u001B[33mSeu saldo atual é R$:\u001B[m" + getSaldo());
        System.out.println("\u001B[33m-\u001B[m".repeat(20));
    }

    @Override
    public void sacar() {
        //Tenta executar essa instrução
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("\u001B[33mQuanto deseja sacar? R$:\u001B[m");
            double sacarValor = sc.nextDouble();

            //metodo da interface,"validar"
            validarSaque(sacarValor);

            setSaldo((getSaldo() - sacarValor));
            System.out.println("\u001B[33mSaque de R$: \u001B[m" + sacarValor);
            System.out.printf("\u001B[36mSeu saldo atual é R$ %.2f%n: \u001B[m" , getSaldo());
            System.out.println("\u001B[33m-\u001B[m".repeat(20));
        } catch (RuntimeException e) {
            //Aqui ele exibe o que foi armazenado na variavel "e",que foi definido no cath
            System.out.println(e.getMessage());
        }

    }


    @Override
    public void extrato() {
        //Mostrar a data atual
        LocalDate dataAtual = LocalDate.now();
        //Exibe a data no formato brasileiro
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = dataAtual.format(formatter);

        //horario
        LocalTime horaAtual = LocalTime.now();
        DateTimeFormatter horarioOPeracao = DateTimeFormatter.ofPattern("HH:mm:ss");
        String horaFormatada = horaAtual.format(horarioOPeracao);

        System.out.println("\u001B[33m== EXTRATO BANCARIO ==\u001B[m");
        System.out.println("\u001B[33mData da operação: \u001B[m" + dataFormatada);
        System.out.println("\u001B[33mHorário da operação: \u001B[m" + horaFormatada);

        System.out.println("\u001B[33mCliente: \u001B[m" + getTitular().toUpperCase());
        System.out.println("\u001B[33mNumero: \u001B[m" + getNumero());
        System.out.println("\u001B[33mCPF: \u001B[m" + getCpf());
        System.out.println("\u001B[33mSaldo R$: \u001B[m" + getSaldo());
        System.out.println("\u001B[33mLimite de saque diário R$: \u001B[m" + getLimiteSaque());

        System.out.println("-".repeat(20));

    }


    @Override
    public void validarSaque(double valor) {

        if (valor > getSaldo()) {
            throw new RuntimeException("\u001B[31mErro de Saque:Saldo insuficiente!\u001B[m");

        }
        if (valor > getLimiteSaque()) {
            throw new RuntimeException("\u001B[31mErro de Saque:Valor acima do limite permitido!\u001B[m");

        }


    }
}
