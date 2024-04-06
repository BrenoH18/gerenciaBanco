package com.mycompany.gerenciabanco;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class GerenciaBanco {
    public static void main(String[] args) throws UnsupportedEncodingException {
        Scanner scanner = new Scanner(System.in, "UTF-8");
        PrintStream out = new PrintStream(System.out, true, "UTF-8");

        out.println("Bem-vindo ao Gerenciador Bancário!");

        out.println("Por favor, informe seu nome:");
        String nome = scanner.nextLine();

        out.println("Por favor, informe seu sobrenome:");
        String sobrenome = scanner.nextLine();

        out.println("Por favor, informe seu CPF:");
        String cpf = scanner.nextLine();

        ContaBancaria conta = new ContaBancaria(nome, sobrenome, cpf);

        do {
            exibirMenu(out);
            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    conta.consultarSaldo(out);
                    break;
                case 2:
                    out.println("Digite o valor a ser depositado:");
                    double valorDeposito = scanner.nextDouble();
                    conta.depositar(valorDeposito, out);
                    break;
                case 3:
                    out.println("Digite o valor a ser sacado:");
                    double valorSaque = scanner.nextDouble();
                    conta.sacar(valorSaque, out);
                    break;
                case 4:
                    out.println("Obrigado por utilizar nosso sistema. Até mais!");
                    return;
                default:
                    out.println("Opção inválida. Por favor, escolha uma opção válida.");
                    break;
            }
        } while (true);
    }

    public static void exibirMenu(PrintStream out) {
        out.println("\nEscolha uma opção:");
        out.println("1 - Consultar saldo");
        out.println("2 - Realizar depósito");
        out.println("3 - Realizar saque");
        out.println("4 - Encerrar");
    }
}

class ContaBancaria {
    private String nome;
    private String sobrenome;
    private String cpf;
    private double saldo;

    public ContaBancaria(String nome, String sobrenome, String cpf) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
    }

    public void depositar(double valor, PrintStream out) {
        if (valor <= 0) {
            out.println("Valor inválido. O valor do depósito deve ser positivo.");
            return;
        }
        saldo += valor;
        out.println("Depósito de R$" + valor + " realizado com sucesso. Novo saldo: R$" + saldo);
    }

    public void sacar(double valor, PrintStream out) {
        if (valor <= 0) {
            out.println("Valor inválido. O valor do saque deve ser positivo.");
            return;
        }
        if (valor > saldo) {
            out.println("Saldo insuficiente.");
        } else {
            saldo -= valor;
            out.println("Saque de R$" + valor + " realizado com sucesso. Novo saldo: R$" + saldo);
        }
    }

    public void consultarSaldo(PrintStream out) {
        out.println("Saldo atual: R$" + saldo);
    }
}
