package model;

import java.io.*;
import java.util.*;

public class Lab04Historico {
    private int numAge;
    private int numConta;
    private Vector<String> vetOperacoes = new Vector<>();

    public Lab04Historico(int numAge, int numConta) {
        this.numAge = numAge;
        this.numConta = numConta;
    }

    public boolean gravar(int codHist, double valor) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(numAge + "." + numConta + ".hist", true))) {
            Date hoje = new Date();
            Calendar cal = new GregorianCalendar();
            cal.setTime(hoje);

            int dia = cal.get(Calendar.DAY_OF_MONTH);
            int mes = cal.get(Calendar.MONTH) + 1; // Mês começa em 0
            int ano = cal.get(Calendar.YEAR);
            int hora = cal.get(Calendar.HOUR_OF_DAY);
            int min = cal.get(Calendar.MINUTE);
            int seg = cal.get(Calendar.SECOND);

            pw.printf("%04d %07d %02d %02d %04d %02d %02d %02d %s %.2f%n",
                    numAge, numConta, dia, mes, ano, hora, min, seg,
                    codHist == 1 ? "Saque" : "Deposito", valor);

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<String> recuperarHistorico() {
        ArrayList<String> historico = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(numAge + "." + numConta + ".hist"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                vetOperacoes.add(linha);
                historico.add(linha);
            }
        } catch (FileNotFoundException e) {
            System.out.println("\nConta sem movimento\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return historico;
    }

    public void imprimir() {
        ArrayList<String> historico = recuperarHistorico();
        if (historico.isEmpty()) {
            System.out.println("\nConta sem movimento\n");
            return;
        }

        System.out.printf("Agência: %04d%n", numAge);
        System.out.printf("Conta: %07d%n", numConta);
        System.out.printf("%-10s %-8s %-10s %-10s%n", "Data", "Hora", "Operação", "Valor (R$)");

        for (String operacao : historico) {
            String[] partes = operacao.split(" ");
            System.out.printf("%02d/%02d/%04d %02d:%02d:%02d %-10s R$ %.2f%n",
                    Integer.parseInt(partes[2]), Integer.parseInt(partes[3]), Integer.parseInt(partes[4]),
                    Integer.parseInt(partes[5]), Integer.parseInt(partes[6]), Integer.parseInt(partes[7]),
                    partes[8], Double.parseDouble(partes[9].replace(",", ".")));
        }
    }
}