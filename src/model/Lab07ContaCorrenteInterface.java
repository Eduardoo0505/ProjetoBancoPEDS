package model;

// Interface para a conta corrente
public interface Lab07ContaCorrenteInterface {
    void depositar(double valor);
    int sacar(double valor);
    void calcularJuros();
}
