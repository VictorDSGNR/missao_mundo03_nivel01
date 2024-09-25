/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package model;

/**
 *
 * @author victoracosta
 */

import java.util.Scanner;
import java.io.IOException;
import java.util.List;
import java.io.File;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;

public class CadastroPOOtext {

    private static Scanner scanner = new Scanner(System.in);
    private static PessoaFisicaRepo repoFisica = new PessoaFisicaRepo();
    private static PessoaJuridicaRepo repoJuridica = new PessoaJuridicaRepo();
    
    public static void main(String[] args) {
        try {
            repoFisica.recuperar("pessoasFisicas.dat");
        } catch (IOException | ClassNotFoundException e) {
            
        }
        
        try {
            repoJuridica.recuperar("pessoasJuridicas.dat");
        } catch (IOException | ClassNotFoundException e) {
           
        }
        
        int opcao;

        do {
            System.out.println("==================================");
            System.out.println("1 - Incluir Pessoa");
            System.out.println("2 - Alterar Pessoa");
            System.out.println("3 - Excluir Pessoa");
            System.out.println("4 - Buscar pelo Id");
            System.out.println("5 - Exibir Todos");
            System.out.println("6 - Persistir Dados");
            System.out.println("7 - Recuperar Dados");
            System.out.println("0 - Finalizar Programa");
            System.out.println("==================================");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    incluirPessoa();
                    break;
                case 2:
                    alterarPessoa();
                    break;
                case 3:
                    excluirPessoa();
                    break;
                case 4:
                    buscarPeloId();
                    break;
                case 5:
                    exibirTodos();
                    break;
                case 6:
                    persistirDados();
                    break;
                case 7:
                    recuperarDados();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao!= 0);
    }
    
    private static void incluirPessoa() {
        String tipo;        
        int id;
        String nome; 
        String cpf; 
        int idade;
        String cnpj; 
         
        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
        
        tipo = scanner.next().toUpperCase();
        
        if (!tipo.equals("J") &&!tipo.equals("F")) {
            System.out.println("Opção inválida!");
            return;
        }
        
        System.out.println("Digite o id da Pessoa:");
        
        id = scanner.nextInt();
        
        System.out.println("Insira os dados...");
        System.out.println("Nome:");
        
        nome = scanner.next();
        
        if (tipo.equals("J")) {
            System.out.println("CNPJ:");
            cnpj = scanner.next();
            
            PessoaJuridica pessoaJ = new PessoaJuridica(id, nome, cnpj);
            repoJuridica.inserir(pessoaJ);
            
            saveData("J");
        }
        
        if (tipo.equals("F")) {
            System.out.println("CPF:");
            cpf = scanner.next();
            
            System.out.println("Idade:");
            idade = scanner.nextInt();
            
            PessoaFisica pessoaF = new PessoaFisica(id, nome, cpf, idade);
            repoFisica.inserir(pessoaF);
            
            saveData("F");
        }
    }
    
    private static void saveData(String tipo) {
        if (tipo.equals("J")) {
            try {
              repoJuridica.persistir("pessoasJuridicas.dat");
            } catch (IOException e) {
               
            }
        }
        
        if (tipo.equals("F")) {
            try {
              repoFisica.persistir("pessoasFisicas.dat");
            } catch (IOException e) {
               
            }
        }
    }
    
    private static void exibirTodos() {
        System.out.println("Dados de Pessoas Fisicas:");
        List<PessoaFisica> pessoasFisicas = repoFisica.obterTodos();
        if (!pessoasFisicas.isEmpty()) {
            for (PessoaFisica pf : pessoasFisicas) {
                pf.exibir();
                System.out.println("");
            }
        } else {
            System.out.println("Nenhuma Pessoa Fisica");
        }
        
        System.out.println("-----------------------------");
        
        System.out.println("Dados de Pessoas Juridicas:");
        
        List<PessoaJuridica> pessoaJuridicas = repoJuridica.obterTodos();
        if (!pessoaJuridicas.isEmpty()) {
            for (PessoaJuridica pf : pessoaJuridicas) {
                pf.exibir();
                System.out.println("");
            }
        } else {
            System.out.println("Nenhuma Pessoa Juridica");
        }
    }
    
    private static void buscarPeloId() {
        String tipo;  
        int id;  
        
        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
        tipo = scanner.next().toUpperCase();
        
        if (!tipo.equals("J") &&!tipo.equals("F")) {
            System.out.println("Opção inválida!");
            return;
        }
                
        System.out.println("Digite o id da Pessoa:");
        id = scanner.nextInt();
        
        if (tipo.equals("J")) {
            PessoaJuridica pessoa = repoJuridica.obter(id);
            if (pessoa == null) {
                System.out.println("Pessoa não encontrada");
            } else {
                System.out.println("Pessoa Juridica:");
                pessoa.exibir();
            }
        }
        
        if (tipo.equals("F")) {
            PessoaFisica pessoa = repoFisica.obter(id);
            if (pessoa == null) {
                System.out.println("Pessoa não encontrada");
            } else {
                System.out.println("Pessoa Fisica:");
                pessoa.exibir();
            }
        }
    } 
    
    private static void excluirPessoa() {
        String tipo;  
        int id;  
        
        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
        tipo = scanner.next().toUpperCase();
        
        if (!tipo.equals("J") &&!tipo.equals("F")) {
            System.out.println("Opção inválida!");
            return;
        }
                
        System.out.println("Digite o id da Pessoa:");
        id = scanner.nextInt();
        
        if (tipo.equals("J")) {
            PessoaJuridica pessoa = repoJuridica.obter(id);
            if (pessoa == null) {
                System.out.println("Pessoa não encontrada");
            } else {
                repoJuridica.excluir(id);
                System.out.printf("A pessoa com a id:%d e o nome: %s foi excluída%n", pessoa.getId(), pessoa.getNome());
                saveData("J");
            }
        }
        
        if (tipo.equals("F")) {
            PessoaFisica pessoa = repoFisica.obter(id);
            if (pessoa == null) {
                System.out.println("Pessoa não encontrada");
            } else {
                repoFisica.excluir(id);
                System.out.printf("A pessoa com a id:%d e o nome: %s foi excluída%n", pessoa.getId(), pessoa.getNome());
                saveData("F");
            }
        }
    }
    
    private static void alterarPessoa() {
        String tipo;        
        int id;
        String nome; 
        String cpf; 
        int idade;
        String cnpj; 
        PessoaJuridica pessoaJuridica = null;
        PessoaFisica pessoaFisica = null;
         
        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
        tipo = scanner.next().toUpperCase();
        
        if (!tipo.equals("J") &&!tipo.equals("F")) {
            System.out.println("Opção inválida!");
            return;
        }
        
        System.out.println("Digite o id da Pessoa:");
        id = scanner.nextInt();
        
        if (tipo.equals("J")) {
            pessoaJuridica = repoJuridica.obter(id);
            if (pessoaJuridica == null) {
                System.out.println("Pessoa não encontrada");
                return;
            } 
        }
        
        if (tipo.equals("F")) {
            pessoaFisica = repoFisica.obter(id);
            if (pessoaFisica == null) {
                System.out.println("Pessoa não encontrada");
                return;
            }
        }
        
        System.out.println("Insira os dados...");
        if (tipo.equals("J")) {
            System.out.printf("Nome (%s):", pessoaJuridica.getNome());
            System.out.println("");
        }
        
        if (tipo.equals("F")) {
            System.out.printf("Nome (%s):", pessoaFisica.getNome());
            System.out.println("");
        }
        
        nome = scanner.next();
        
        if (tipo.equals("J")) {
            System.out.printf("CNPJ (%s):", pessoaJuridica.getCnpj());
            System.out.println("");
            cnpj = scanner.next();
            
            PessoaJuridica pessoaJ = new PessoaJuridica(id, nome, cnpj);
            repoJuridica.alterar(id, pessoaJ);
            
            saveData("J");
        }
        
        if (tipo.equals("F")) {
            System.out.printf("CPF (%s):", pessoaFisica.getCpf());
            System.out.println("");
            cpf = scanner.next();
            
            System.out.printf("Idade (%d):", pessoaFisica.getIdade());
            System.out.println("");
            idade = scanner.nextInt();
            
            PessoaFisica pessoaF = new PessoaFisica(id, nome, cpf, idade);
            repoFisica.alterar(id, pessoaF);
            
            saveData("F");
        }
    }
    
    private static void persistirDados() {
        String tipo;
        String prefixo;
        File file;
        
        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
        tipo = scanner.next().toUpperCase();
        
        if (!tipo.equals("J") &&!tipo.equals("F")) {
            System.out.println("Opção inválida!");
            return;
        }
        
        System.out.println("Insira o prefixo do arquivo:");
        prefixo = scanner.next();
        
        if (tipo.equals("J")) {
            file = new File(prefixo + ".juridica.bin");
        } else {
            file = new File(prefixo + ".fisica.bin");
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            if (tipo.equals("J")) {
                List<PessoaJuridica> pessoas = repoJuridica.obterTodos();
                oos.writeObject(pessoas);
            } else {
                List<PessoaFisica> pessoas = repoFisica.obterTodos();
                oos.writeObject(pessoas);
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados: " + e.getMessage());
            return;
        }
        
        System.out.println("Dados salvos com êxito");
    }
    
    private static void recuperarDados() {
        String tipo;
        String prefixo;
        File file;
        ObjectInputStream ois;
        List<PessoaFisica> pessoasFisicas = null;
        List<PessoaJuridica> pessoasJuridicas = null;

        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
        tipo = scanner.next().toUpperCase();

        if (!tipo.equals("J") &&!tipo.equals("F")) {
            System.out.println("Opção inválida!");
            return;
        }

        System.out.println("Insira o prefixo do arquivo:");
        prefixo = scanner.next();

        try {
            if (tipo.equals("J")) {
                file = new File(prefixo + ".juridica.bin");
                ois = new ObjectInputStream(new FileInputStream(file));
                pessoasJuridicas = (List<PessoaJuridica>) ois.readObject();
                ois.close();
                repoJuridica.novosDados(pessoasJuridicas);
                saveData("J");
            }
            if (tipo.equals("F")) {
                file = new File(prefixo + ".fisica.bin");
                ois = new ObjectInputStream(new FileInputStream(file));
                pessoasFisicas = (List<PessoaFisica>) ois.readObject();
                ois.close();
                repoFisica.novosDados(pessoasFisicas);
                saveData("F");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao recuperar dados: " + e.getMessage());
            return;
        }
        
        System.out.println("Dados recuperados com êxito");
    }
}