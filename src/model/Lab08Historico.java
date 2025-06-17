package model;

import util.MyClassException;

import java.io.*;
import java.util.ArrayList;

public class Lab08Historico {
    private int numAge;          // Número da agência
    private int numConta;        // Número da conta
    private ArrayList<String> historico; // Lista de operações

    // Construtor
    public Lab08Historico(int numAge, int numConta) {
        this.numAge = numAge;
        this.numConta = numConta;
        this.historico = new ArrayList<>();
    }

    // Método para gravar operações no histórico
    public void gravar(int codigo, double valor) throws MyClassException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(numAge + "." + numConta + ".hist", true))) {
            writer.printf("%d;%.2f%n", codigo, valor); // Código e valor separados por ;
            System.out.println("Operação registrada no histórico.");
        } catch (IOException e) {
            throw new MyClassException(
                "Erro ao gravar a operação no histórico.",
                this.getClass().getName(),
                "gravar"
            );
        }
    }

    // Método para recuperar o histórico do arquivo
    public void recuperar() throws MyClassException {
        try (BufferedReader reader = new BufferedReader(new FileReader(numAge + "." + numConta + ".hist"))) {
            String linha;
            historico.clear(); // Limpa o histórico antes de carregar
            while ((linha = reader.readLine()) != null) {
                historico.add(linha);
            }
            System.out.println("Histórico recuperado com sucesso.");
        } catch (FileNotFoundException e) {
            throw new MyClassException(
                "Arquivo de histórico não encontrado.",
                this.getClass().getName(),
                "recuperar"
            );
        } catch (IOException e) {
            throw new MyClassException(
                "Erro ao recuperar o histórico do arquivo.",
                this.getClass().getName(),
                "recuperar"
            );
        }
    }

    // Método para imprimir o histórico no console
    public void imprimir() throws MyClassException {
        try {
            recuperar(); // Recupera os dados antes de imprimir
            if (historico.isEmpty()) {
                System.out.println("Nenhuma operação encontrada no histórico.");
                return;
            }

            System.out.println("== Histórico de Operações ==");
            for (String registro : historico) {
                String[] detalhes = registro.split(";");
                try {
                    int codigo = Integer.parseInt(detalhes[0]);
                    double valor = Double.parseDouble(detalhes[1]);
                    switch (codigo) {
                        case 1 -> System.out.printf("Operação: Saque - Valor: R$ %.2f%n", valor);
                        case 2 -> System.out.printf("Operação: Depósito - Valor: R$ %.2f%n", valor);
                        case 3 -> System.out.printf("Operação: Juros Aplicados - Valor: R$ %.2f%n", valor);
                        default -> System.out.println("Operação desconhecida. Código: " + codigo);
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Registro inválido no histórico: " + registro);
                }
            }
        } catch (MyClassException e) {
            throw new MyClassException(
                "Erro ao imprimir o histórico.",
                this.getClass().getName(),
                "imprimir"
            );
        }
    }

    // Getters para agência e conta (opcionalmente úteis)
    public int getNumAge() {
        return numAge;
    }

    public int getNumConta() {
        return numConta;
    }
}