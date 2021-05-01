import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Fila<Reserva> fila = new Fila;
        Scanner sc = new Scanner(System.in);
        int opcao;
        do {
            System.out.println("\n Escolha a opção a seguir");
            System.out.println("1. Reservar mesa");
            System.out.println("2. Pesquisar reserva");
            System.out.println("3. Imprimir lista de reserva");
            System.out.println("4. Imprimir lista de espera");
            System.out.println("5. Cancelar reserva");
            System.out.println("6. Finalizar");

            opcao = sc.nextInt();

            switch (opcao) {
            case 1:
                    fila.add(ReservarMesa(fila));
                break;
            case 2:
                Pesquisar(fila);
                break;
            case 3:
                System.out.println(fila);
                break;
            case 4:
                
                if (tamanho > 6) {
                    for(int i = 0; i < tamanho; i++ ) {
                        System.out.println(fila.get(i));
                    }
                }
                break;
            case 5:
                CancelarReserva(fila);
                break;

            }
        } while (opcao != 6);
        System.out.println("Finalizado");

    }

    // Reservar mesa
    public static Reserva ReservarMesa(Fila<Reserva> fila) {
        Scanner sc = new Scanner(System.in);
        Cliente cliente = null;
        boolean pagamentoAVista = true;

        System.out.println("\n Cadastrar cliente");
        System.out.println("Digite 1 para cadastrar pessoa juridica");
        System.out.println("Digite 2 para cadastrar pessoa fisica");
        int tipoPessoa = sc.nextInt();

        switch (tipoPessoa) {
            case 1:
                cliente = CadastroJuridico();
                break;

            case 2:
                cliente = CadastroFisico();
                break;
        }

        System.out.println("\n Pagamento");
        System.out.println("1. A vista");
        System.out.println("2. Parcelado");
        int pagamento = sc.nextInt();

        switch (pagamento) {
            case 1:
                pagamentoAVista = true;
                break;

            case 2:
                pagamentoAVista = false;
                break;
        }
        Reserva reserva = new Reserva(cliente, pagamentoAVista);
        return reserva;

    }

    // Cadastrar Fisica
    public static Cliente CadastroFisico() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nome: ");
        String nome = sc.nextLine();
        System.out.println("CPF: ");
        String cpf = sc.nextLine();

        PessoaFisica pFisica = new PessoaFisica(cpf, nome);
        return pFisica;
    }

    // Cadastrar Juridica
    public static Cliente CadastroJuridico() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nome: ");
        String nome = sc.nextLine();
        System.out.println("CNPJ: ");
        String cnpj = sc.nextLine();

        PessoaJuridica pJuridica = new PessoaJuridica(cnpj, nome);
        return pJuridica;
    }

    // Pesquisar mesa
    public static void Pesquisar(Fila<Reserva> fila) {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n Pesquisar por CPF ou CNPJ?");
        String pesq = sc.nextLine().toLowerCase();

        switch (pesq) {
            case "cpf":
                System.out.print("\n Qual CPF deseja pesquisar?  ");
                String cpf = sc.nextLine();

                for (int i = 0; i < tamanho; i++) {
                    Cliente cli = fila.get(i).getCliente();
                    PessoaFisica pf = (PessoaFisica) cli;
                    if (pf.getCpf().equals(cpf)) {
                        System.out.print("Ja tem reserva");
                    } else {
                        System.out.println("Ainda não tem reserva");
                    }
                }
                break;

            case "cnpj":
                for (int i = 0; i < tamanho; i++) {
                    System.out.print("\n Qual CNPJ deseja pesquisar?  ");
                    String cnpj = sc.nextLine();
                    Cliente cli = fila.get(i).getCliente();
                    PessoaJuridica pj = (PessoaJuridica) cli;
                    if (pj.getCnpj().equals(cnpj)) {
                        System.out.print("Ja tem reserva");
                    } else {
                        System.out.println("Ainda não tem reserva");
                    }
                }
                break;
        }
    }


    // Cancelar
}
