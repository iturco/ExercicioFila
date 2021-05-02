import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Reserva> lista = new ArrayList<>();
        Fila<Reserva> espera = new Fila();

        int opcao;
        int mesa = 0;
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
                    if (lista.size() < 4) {
                        mesa++;
                        lista.add(ReservarMesa(lista, mesa));

                    } else {
                        System.out.println("Sua reserva será colocada na lista de espera");
                        mesa++;
                        espera.enqueue(ReservarMesa(espera, mesa));

                    }
                    break;
                case 2:
                    Pesquisar(lista);
                    break;
                case 3:
                    System.out.println(lista);
                    break;
                case 4:
                    System.out.println(espera);
                    break;
                case 5:
                    mesa--;
                    CancelarReserva(lista, espera);
                    break;

            }
        } while (opcao != 6);
        System.out.println("Finalizado");

    }

    // RESERVA DA MESA
    public static Reserva ReservarMesa(List<Reserva> lista, int mesa) {
        Scanner sc = new Scanner(System.in);
        Cliente cliente = null;
        boolean avista = true;

        System.out.println("\n Cadastrar clientes da mesa:");
        System.out.println("1. Pessoa juridica");
        System.out.println("2. Pessoa fisica");
        int tipoPessoa = sc.nextInt();

        switch (tipoPessoa) {
            case 1:
                cliente = CadastrarJuridica();
                break;

            case 2:
                cliente = CadastrarFisica();
                break;
        }

        System.out.println("\n Formas de pagamentos: ");
        System.out.println("1. a vista");
        System.out.println("2. parcelado");
        int pagamento = sc.nextInt();
        switch (pagamento) {
            case 1:
                avista = true;
                break;

            case 2:
                avista = false;
                break;
        }

        Reserva reserva = new Reserva(cliente, avista);
        System.out.println();

        return reserva;
    }

    // CADASTRO PESSOA JURIDICA
    public static Cliente CadastrarJuridica() {
        Scanner sc = new Scanner(System.in);

        System.out.print("CNPJ: ");
        String cnpj = sc.next();
        System.out.print("Nome: ");
        String nome = sc.next();
        PessoaJuridica pJur = new PessoaJuridica(cnpj, nome);
        return pJur;
    }

    // CADASTRO PESSOA FISICA
    public static Cliente CadastrarFisica() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        PessoaFisica pFis = new PessoaFisica(cpf, nome);
        return pFis;
    }

    // PESQUISAR
    public static void Pesquisar(List<Reserva> lista) {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n Pesquisar por CPF ou CNPJ?  ");
        String opcao2 = sc.nextLine().toLowerCase();

        switch (opcao2) {
            case "cpf":
                System.out.print("\n Qual CPF deseja pesquisar?  ");
                String cpf = sc.nextLine();

                for (int i = 0; i < lista.size(); i++) {
                    Cliente cli = lista.get(i).getCliente();
                    PessoaFisica pf = (PessoaFisica) cli;
                    if (pf.getCpf().equals(cpf)) {
                        System.out.print("Ja tem reserva");
                    } else {
                        System.out.println("Ainda não tem reserva");
                    }
                }
                break;

            case "cnpj":
                for (int i = 0; i < lista.size(); i++) {
                    System.out.print("\n Qual CNPJ deseja pesquisar?  ");
                    String cnpj = sc.nextLine();
                    Cliente cli = lista.get(i).getCliente();
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

    // CANCELAR RESERVA
    public static void CancelarReserva(List<Reserva> lista, Fila<Reserva> espera) {
        Scanner sc = new Scanner(System.in);

        System.out.print("\n Cancelar por CPF ou CNPJ?  ");
        String opcao3 = sc.nextLine().toLowerCase();

        switch (opcao3) {
            case "cpf":
                System.out.print("\n Qual CPF deseja cancelar a reserva?  ");
                String cpfapagar = sc.nextLine();

                for (int i = 0; i < lista.size(); i++) {
                    Reserva apagar = lista.get(i);
                    Cliente cli = apagar.getCliente();
                    if (cli instanceof PessoaFisica) {
                        PessoaFisica pf = (PessoaFisica) cli;

                        if (cpfapagar.equals(pf.getCpf())) {
                            System.out.println();
                            lista.remove(i);
                            if (lista.size() > 5) {
                                Reserva inverter = espera;
                                lista.add(inverter);
                                espera.dequeue;
                            }

                        } else {
                            System.out.println("Cpf não encontrado");
                        }
                    }
                }
                break;

            case "cnpj":
                System.out.print("\n Qual CNPJ deseja cancelar a reserva?  ");
                String cnpjapagar = sc.nextLine();

                for (int i = 0; i < lista.size(); i++) {
                    Reserva apagar = lista.get(i);
                    Cliente cli = apagar.getCliente();
                    if (cli instanceof PessoaJuridica) {

                        PessoaJuridica pj = (PessoaJuridica) cli;

                        if (cnpjapagar.equals(pj.getCnpj())) {
                            System.out.println();
                            lista.remove(i);
                            if (lista.size() > 5) {
                                Reserva inverter = espera.get(0);
                                lista.add(inverter);
                                espera.remove(espera.get(0));
                            }

                        } else {
                            System.out.println("Cnpj não encontrado");
                        }
                        break;
                    }
                }

        }
    }
}
