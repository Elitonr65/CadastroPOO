import model.PessoaFisica
import model.PessoaFisicaRepo;
import model.PessoaJuridica;
import model.PessoaJuridicaRepo;
import java.util.Scanner;

import java.io.IOException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PessoaFisicaRepo repoFisica = new PessoaFisicaRepo();
        PessoaJuridicaRepo repoJuridica = new PessoaJuridicaRepo();

        int opcao;
        do {
            System.out.println("\nrun:");
            System.out.println("=================================");
            System.out.println("1 - Incluir Pessoa");
            System.out.println("2 - Alterar Pessoa");
            System.out.println("3 - Excluir Pessoa");
            System.out.println("4 - Buscar pelo Id");
            System.out.println("5 - Exibir Todos");
            System.out.println("6 - Persistir Dados");
            System.out.println("7 - Recuperar Dados");
            System.out.println("0 - Finalizar Programa");
            System.out.println("=================================");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    incluirPessoa(scanner, repoFisica, repoJuridica);
                    break;
                case 2:
                    alterarPessoa(scanner, repoFisica, repoJuridica);
                    break;
                case 3:
                    excluirPessoa(scanner, repoFisica, repoJuridica);
                    break;
                case 4:
                    buscarPessoa(scanner, repoFisica, repoJuridica);
                    break;
                case 5:
                    repoFisica.exibirTodos();
                    repoJuridica.exibirTodos();
                    break;
                case 6:
                    persistirDados(scanner, repoFisica, repoJuridica);
                    break;
                case 7:
                    recuperarDados(scanner, repoFisica, repoJuridica);
                    break;
                case 0:
                    System.out.println("Finalizando programa...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void incluirPessoa(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.println("F - Pessoa Física | J - Pessoa Jurídica");
        char tipo = scanner.next().toUpperCase().charAt(0);
        System.out.print("Digite o id da pessoa: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        System.out.println("Insira os dados...");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        if (tipo == 'F') {
            System.out.print("CPF: ");
            String cpf = scanner.nextLine();
            System.out.print("Idade: ");
            int idade = scanner.nextInt();
            repoFisica.inserir(new PessoaFisica(id, nome, cpf, idade));
        } else {
            System.out.print("CNPJ: ");
            String cnpj = scanner.nextLine();
            repoJuridica.inserir(new PessoaJuridica(id, nome, cnpj));
        }
    }

    private static void alterarPessoa(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.println("F - Pessoa Física | J - Pessoa Jurídica");
        char tipo = scanner.next().toUpperCase().charAt(0);
        System.out.print("Digite o id da pessoa a ser alterada: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        if (tipo == 'F') {
            PessoaFisica pessoa = repoFisica.buscarPorId(id);
            if (pessoa != null) {
                System.out.println("Dados atuais: " + pessoa);
                System.out.print("Novo nome: ");
                String nome = scanner.nextLine();
                System.out.print("Novo CPF: ");
                String cpf = scanner.nextLine();
                System.out.print("Nova Idade: ");
                int idade = scanner.nextInt();
                pessoa.setNome(nome);
                pessoa.setCpf(cpf);
                pessoa.setIdade(idade);
                repoFisica.atualizar(pessoa);
            } else {
                System.out.println("Pessoa Física não encontrada.");
            }
        } else {
            PessoaJuridica pessoa = repoJuridica.buscarPorId(id);
            if (pessoa != null) {
                System.out.println("Dados atuais: " + pessoa);
                System.out.print("Novo nome: ");
                String nome = scanner.nextLine();
                System.out.print("Novo CNPJ: ");
                String cnpj = scanner.nextLine();
                pessoa.setNome(nome);
                pessoa.setCnpj(cnpj);
                repoJuridica.atualizar(pessoa);
            } else {
                System.out.println("Pessoa Jurídica não encontrada.");
            }
        }
    }

    private static void excluirPessoa(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.println("F - Pessoa Física | J - Pessoa Jurídica");
        char tipo = scanner.next().toUpperCase().charAt(0);
        System.out.print("Digite o id da pessoa a ser excluída: ");
        int id = scanner.nextInt();

        if (tipo == 'F') {
            repoFisica.remover(id);
        } else {
            repoJuridica.remover(id);
        }
    }

    private static void buscarPessoa(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.println("F - Pessoa Física | J - Pessoa Jurídica");
        char tipo = scanner.next().toUpperCase().charAt(0);
        System.out.print("Digite o id da pessoa a ser buscada: ");
        int id = scanner.nextInt();

        if (tipo == 'F') {
            PessoaFisica pessoa = repoFisica.buscarPorId(id);
            System.out.println(pessoa != null ? pessoa : "Pessoa Física não encontrada.");
        } else {
            PessoaJuridica pessoa = repoJuridica.buscarPorId(id);
            System.out.println(pessoa != null ? pessoa : "Pessoa Jurídica não encontrada.");
        }
    }

    private static void persistirDados(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.print("Digite o prefixo do arquivo: ");
        String prefixo = scanner.next();
        try {
            repoFisica.salvar(prefixo + ".fisica.bin");
            repoJuridica.salvar(prefixo + ".juridica.bin");
            System.out.println("Dados salvos com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    private static void recuperarDados(Scanner scanner, PessoaFisicaRepo repoFisica, PessoaJuridicaRepo repoJuridica) {
        System.out.print("Digite o prefixo do arquivo: ");
        String prefixo = scanner.next();
        try {
            repoFisica.recuperar(prefixo + ".fisica.bin");
            repoJuridica.recuperar(prefixo + ".juridica.bin");
            System.out.println("Dados recuperados com sucesso.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao recuperar os dados: " + e.getMessage());
        }
    }
}