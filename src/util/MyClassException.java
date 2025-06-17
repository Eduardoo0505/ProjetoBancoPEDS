package util;

public class MyClassException extends Exception {
    private String classe;      // Classe onde o erro ocorreu
    private String mensagem;    // Mensagem de negócio
    private String metodo;      // Método onde o erro ocorreu

    // Construtor padrão
    public MyClassException() {
        super();
    }

    // Construtor com mensagem
    public MyClassException(String mensagem) {
        super(mensagem);
        this.mensagem = mensagem;
    }

    // Construtor com mensagem e causa
    public MyClassException(String mensagem, Throwable causa) {
        super(mensagem, causa);
        this.mensagem = mensagem;
    }

    // Construtor com mensagem, classe e método
    public MyClassException(String mensagem, String classe, String metodo) {
        super(mensagem);
        this.mensagem = mensagem;
        this.classe = classe;
        this.metodo = metodo;
    }

    // Get para os atributos à mais
    public String getClasse() {
        return classe;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getMetodo() {
        return metodo;
    }

    // Sobrescrevendo o método toString para exibir informações detalhadas
    @Override
    public String toString() {
        return String.format("Erro na Classe: %s | Método: %s | Mensagem: %s", classe, metodo, mensagem);
    }
}