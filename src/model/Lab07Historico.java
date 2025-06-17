package model;

import java.util.ArrayList;

public class Lab07Historico extends Lab04Historico {

    public Lab07Historico(int numAge, int numConta) {
        super(numAge, numConta); // Chama o construtor da superclasse
    }

    @Override
    public void imprimir() {
        ArrayList<String> historico = recuperarHistorico(); // Recupera o histórico
        if (historico.isEmpty()) {
            System.out.println("Nenhum movimento encontrado no histórico.");
            return; // Finaliza o método sem retornar nada
        }

        System.out.println("== Histórico de Movimentos ==");

        for (String registro : historico) {
            String[] detalhes = registro.split(";"); // Separar os detalhes por ';'
            try {
                if (detalhes.length < 2) { // Verifica se há ao menos código e valor
                    System.out.println("Registro incompleto: " + registro);
                    continue; // Ignora o registro inválido e segue para o próximo
                }

                int codigo = Integer.parseInt(detalhes[0]); // Parse do código do movimento
                switch (codigo) {
                    case 1 -> System.out.printf("Movimento: Saque - Valor: R$ %.2f%n", Double.parseDouble(detalhes[1]));
                    case 2 -> System.out.printf("Movimento: Depósito - Valor: R$ %.2f%n", Double.parseDouble(detalhes[1]));
                    case 3 -> System.out.printf("Movimento: Atualização do Saldo - Valor: R$ %.2f%n", Double.parseDouble(detalhes[1]));
                    default -> System.out.println("Movimento desconhecido - Código: " + codigo);
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Erro ao processar registro: " + registro);
                e.printStackTrace(); // Imprime detalhes do erro para depuração
            }
        }
    }
}