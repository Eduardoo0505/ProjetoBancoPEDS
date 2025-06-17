package model;

import util.MyClassException;

public class Lab08ContaCorrenteEspecial extends Lab08ContaCorrente {
    private double limite; // Limite de crédito adicional

    // Construtor completo
    public Lab08ContaCorrenteEspecial(int numAge, int numConta, String nome, double saldo, double limite) throws MyClassException {
        super(numAge, numConta, nome, saldo); // Chamada ao construtor da superclasse
        this.limite = limite;
    }

    // Construtor com apenas agência e conta
    public Lab08ContaCorrenteEspecial(int numAge, int numConta) throws MyClassException {
        super(numAge, numConta); // Chamada ao construtor da superclasse
    }

    // Getter e setter para limite
    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    // Método sobrescrito para saque com exceções
    @Override
    public void sacar(double valor) throws MyClassException {
        if (valor <= 0) {
            throw new MyClassException("O valor do saque deve ser positivo.", "Lab08ContaCorrenteEspecial", "sacar");
        }
        if (valor > getSaldo() + limite) {
            throw new MyClassException("Saldo insuficiente, considerando o limite de crédito.", "Lab08ContaCorrenteEspecial", "sacar");
        }
        setSaldo(getSaldo() - valor);
        System.out.printf("Saque realizado com sucesso. Novo saldo: R$ %.2f%n", getSaldo());
    }

    // Método sobrescrito para gravar os dados da conta especial
    @Override
    public boolean gravar() throws MyClassException {
        try {
            super.gravar(); // Chama o método da superclasse para gravar dados comuns
            java.io.FileWriter writer = new java.io.FileWriter(getNumAge() + "." + getNumConta() + ".limite");
            writer.write(String.valueOf(limite)); // Grava o limite em um arquivo separado
            writer.close();
            System.out.println("Limite de crédito gravado com sucesso.");
            return true;
        } catch (java.io.IOException e) {
            throw new MyClassException("Erro ao gravar o limite de crédito.", "Lab08ContaCorrenteEspecial", "gravar");
        }
    }

    // Método sobrescrito para recuperar os dados da conta especial
    @Override
    public void recuperar() throws MyClassException {
        super.recuperar(); // Recupera os dados comuns da superclasse
        try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(getNumAge() + "." + getNumConta() + ".limite"))) {
            limite = Double.parseDouble(reader.readLine()); // Recupera o limite do arquivo separado
            System.out.println("Limite de crédito recuperado com sucesso.");
        } catch (java.io.IOException e) {
            throw new MyClassException("Erro ao recuperar o limite de crédito.", "Lab08ContaCorrenteEspecial", "recuperar");
        } catch (NumberFormatException e) {
            throw new MyClassException("Formato inválido ao recuperar o limite de crédito.", "Lab08ContaCorrenteEspecial", "recuperar");
        }
    }

    // Método sobrescrito para remover os arquivos da conta especial
    @Override
    public boolean removerArquivo() throws MyClassException {
        boolean contaRemovida = super.removerArquivo(); // Remove o arquivo principal
        java.io.File arquivoLimite = new java.io.File(getNumAge() + "." + getNumConta() + ".limite");
        if (arquivoLimite.exists() && !arquivoLimite.delete()) {
            throw new MyClassException("Erro ao remover o arquivo de limite.", "Lab08ContaCorrenteEspecial", "removerArquivo");
        }
        System.out.println("Conta especial removida com sucesso.");
        return contaRemovida;
    }

    // Método para calcular juros, implementado exclusivamente em contas especiais
    public void calcularJuros() throws MyClassException {
        if (getSaldo() >= 0) {
            double juros = getSaldo() * 0.05; // 5% de juros
            setSaldo(getSaldo() + juros);
            System.out.printf("Juros de R$ %.2f aplicados. Novo saldo: R$ %.2f%n", juros, getSaldo());
        } else {
            throw new MyClassException("Não é possível calcular juros em saldos negativos.", "Lab08ContaCorrenteEspecial", "calcularJuros");
        }
    }
}