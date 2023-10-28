package br.com.DevSuperior.Model;

import br.com.DevSuperior.ExcecaoPersonalizada.MinhaExcecao;
import br.com.DevSuperior.ExcecaoPersonalizada.SenhaInvalidaException;
import br.com.DevSuperior.ExcecaoPersonalizada.UsuarioInvalidoException;
import br.com.DevSuperior.Interfaces.AcoesBancarias;
import br.com.DevSuperior.Interfaces.ExtratoBancario;
import br.com.DevSuperior.Interfaces.Menu;
import br.com.DevSuperior.Interfaces.Validacao;
import br.com.DevSuperior.Interfaces.ValidarAcoes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;


public class ContaBancaria extends Bank implements AcoesBancarias, ExtratoBancario, ValidarAcoes, Validacao, Menu {
    //atributos


    //Construtor


    public ContaBancaria() {

    }

    public void criarConta() {

        Scanner sc = new Scanner(System.in);
        System.out.println("\u001B[33m== DADOS CADASTRAIS ==\u001B[m");
        System.out.print("\u001B[33mNome do titular: \u001B[m");
        setTitular(sc.nextLine());
        System.out.print("Escolha uma senha: ");
        setSenha(sc.next());
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
        if (getLogado()) {
            try {
                System.out.print("\u001B[33mQuanto deseja depositar? R$:\u001B[m");
                double quantia = sc.nextDouble();
                if (quantia <= 0) {
                    throw new MinhaExcecao("\u001B[31mDEPÓSITO INVÁLIDO! SOMENTE VALORES MAIORES QUE ZERO\u001B[m");

                }
                setSaldo(getSaldo() + quantia);
                System.out.println("\u001B[33mDepósito de R$:\u001B[m" + quantia);
                System.out.println("\u001B[33mSeu saldo atual é R$:\u001B[m" + getSaldo());
                System.out.println("\u001B[33m-\u001B[m".repeat(20));

                //exceção exibida quando um dado não pode ser convertido em número
            } catch (InputMismatchException e) {
                System.out.println("\u001B[31mENTRADA INVÁLIDA. INSIRA UM VALOR NUMÉRICO VÁLIDO.\u001B[m");

                //Exceção personalizada
            } catch (MinhaExcecao ex) {
                System.out.println(ex.getMessage());
            } finally {
                System.out.println("\u001B[35mO PRIME BANK AGRADECE SUA CONFIANÇA!\u001B[m");
            }
        }

    }

    @Override
    public void sacar() {
        if (getLogado()) {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print("\u001B[33mQuanto deseja sacar? R$:\u001B[m");
                double sacarValor = sc.nextDouble();

                //metodo da interface,"validar"
                validarSaque(sacarValor);

                setSaldo((getSaldo() - sacarValor));
                System.out.println("\u001B[33mSaque de R$: \u001B[m" + sacarValor);
                System.out.printf("\u001B[36mSeu saldo atual é R$ %.2f%n\u001B[m", getSaldo());
                System.out.println("\u001B[33m-\u001B[m".repeat(20));
            } catch (RuntimeException e) {
                //Aqui ele exibe o que foi armazenado na variavel "e",que foi definido no cath
                System.out.println(e.getMessage());
            } finally {
                System.out.println("\u001B[35mPRECISA DE CRÉDITO EXTRA? O PRIME BANK TEM A SOLUÇÃO!\u001B[m");
            }

        }
        //Tenta executar essa instrução
    }

    @Override
    public void extrato() {
        if (getLogado()) {
            //Mostrar a data atual
            LocalDate dataAtual = LocalDate.now();
            //Exibe a data no formato brasileiro
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dataFormatada = dataAtual.format(formatter);

            //horario
            LocalTime horaAtual = LocalTime.now();
            //Formato brasileiro
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
            System.out.println("\u001B[33mNR.AUTENTICACAO: \u001B[m" + chaveAutenticacao);

            System.out.println("-".repeat(20));

        }

    }

    @Override
    public void validarSaque(double valor) {

        if (valor > getSaldo()) {
            throw new RuntimeException("\u001B[31mERRO DE SAQUE: SALDO INSUFICIENTE!\u001B[m");

        }
        if (valor > getLimiteSaque()) {
            throw new RuntimeException("\u001B[31mERRO DE SAQUE: VALOR ACIMA DO LIMITE PERMITIDO!\u001B[m");

        }
        if (valor <= 0) {
            throw new RuntimeException("\u001B[31mERRO DE SAQUE: VALOR NÃO PERMITIDO!\u001B[m") {

            };

        }

    }

    //Estas interfaces ainda não estão em uso!!!!!

    @Override
    public boolean verificarTitular(String titular) {
        boolean user = false;
        if (getTitular().equals(titular)) {
            try {
                if (getTitular().length() <= 5) {
                    throw new UsuarioInvalidoException();
                }
                user = true;
            } catch (UsuarioInvalidoException e) {
                System.err.println(e);
            }

        } else {
            System.out.println("\u001B[31mUSUÁRIO INVÁLIDO!\u001B[m");
        }

        return user;
    }

    @Override
    public boolean verificarSenha(String senha) {
        boolean password = false;
        if (getSenha().equals(senha)) {
            try {
                if (getSenha().length() < 6) {
                    throw new SenhaInvalidaException();
                }
                password = true;
            } catch (SenhaInvalidaException e) {
                System.err.println(e);
            }
        } else {
            System.out.println("\u001B[31mSENHA INVÁLIDA\u001B[m");
        }

        return password;
    }

    @Override
    public void autenticar(String titularNome, String titularSenha) {
        if (getLogado()) {
            try {
                if (getTitular().equals(titularNome) && getSenha().equals(titularSenha)) {
                    System.out.println("\u001B[34mCLIENTE AUTENTICADO NO INTERNET BANKING\u001B[m");


                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }

    }


    @Override
    public boolean logar() {
        if (getLogado())
            System.out.println("== FAZER LOGIN ==");
        Scanner sc = new Scanner(System.in);
        System.out.print("Usuário: ");
        String user = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.next();

        if (this.verificarTitular(user) && this.verificarSenha(senha)) {
            setTitular(user);
            setSenha(senha);
            setLogado(true);
            System.out.println("\u001B[34mCLIENTE LOGADO NO INTERNET BANKING\u001B[m");
        }
        return getLogado();
    }

    @Override
    public void menu() {
        int opcao;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("= MENU - INTERNET BANKING =");
            System.out.println("1. Criar conta");
            System.out.println("2. Fazer Login");
            System.out.println("3. Depositar");
            System.out.println("4. Sacar");
            System.out.println("5. Exibir extrato");
            System.out.println("6. Encerrar");
            System.out.print("Insira a opção desejada: ");

            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    if (!getLogado()) {
                        criarConta();
                    }
                    break;

                case 2:
                    logar();
                    break;

                case 3:
                    if (getLogado()) {
                        depositar();
                    } else {
                        System.out.println("\u001B[31mFAÇA LOGIN ANTES DE FAZER UM DEPÓSITO.\u001B[m");
                    }
                    break;
                case 4:
                    if (getLogado()) {
                        sacar();
                    } else {
                        System.out.println("\u001B[31mFAÇA LOGIN ANTES DE FAZER UM SAQUE.\u001B[m");
                    }
                    break;
                case 5:
                    if (getLogado()) {
                        extrato();
                    } else {
                        System.out.println("\u001B[31mFAÇA LOGIN ANTES DE EXIBIR O EXTRATO.\u001B[m");
                    }
                    break;
                case 6:
                    System.out.println("\u001B[33mENCERRANDO SISTEMA...\u001B[m");
                    break;
                default:
                    System.out.println("\u001B[31mOpção Inválida!\u001B[m");
            }
        } while (opcao != 6);
    }
}