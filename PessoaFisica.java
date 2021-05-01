public class PessoaFisica extends Cliente {
    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public PessoaFisica(String cpf, String nome) {
        super(nome);
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Pessoa fisica --> cpf: " + cpf + "  || Nome: " + getNome();
    }
}
