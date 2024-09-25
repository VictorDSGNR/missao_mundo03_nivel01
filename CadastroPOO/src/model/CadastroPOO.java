/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package model;
import java.io.IOException;
 /**
 *
 * @author victoracosta
 */
public class CadastroPOO {
public static void main(String[] args) {
PessoaFisicaRepo repo1 = new PessoaFisicaRepo();
 PessoaFisica pessoa1 = new PessoaFisica(01, "Victor", "123453175-98", 37);
 PessoaFisica pessoa2 = new PessoaFisica(02, "Yumi", "125443175-68", 32);
 repo1.inserir(pessoa1);
 repo1.inserir(pessoa2);
 System.out.println("Dados de Pessoa Fisica Armazenados.");
 try {
 repo1.persistir("pessoasFisicas.dat");
 } catch (IOException e) {
 e.printStackTrace();
 }
 PessoaFisicaRepo repo2 = new PessoaFisicaRepo();
 try {
 repo2.recuperar("pessoasFisicas.dat");
 System.out.println("Dados de Pessoa Fisica Recuperados.");
 } catch (IOException | ClassNotFoundException e) {
 e.printStackTrace();
 }
 for (PessoaFisica pf : repo2.obterTodos()) {
 pf.exibir();
 }
 PessoaJuridicaRepo repo3 = new PessoaJuridicaRepo();
 PessoaJuridica pessoa3 = new PessoaJuridica(3, "CREATEE BRASIL", " 12.345.678/0001-10");
 PessoaJuridica pessoa4 = new PessoaJuridica(4, "CREATEE STORE", "12.346.978/0001-11");
 repo3.inserir(pessoa3);
 repo3.inserir(pessoa4);
 System.out.println("Dados de Pessoa Juridica Armazenados.");
 try {
 repo3.persistir("pessoasJuridicas.dat");
 } catch (IOException e) {
 e.printStackTrace();
 }
 PessoaJuridicaRepo repo4 = new PessoaJuridicaRepo();
 try {
repo4.recuperar("pessoasJuridicas.dat");
 System.out.println("Dados de Pessoa Juridica Recuperados.");
 } catch (IOException | ClassNotFoundException e) {
 e.printStackTrace();
 }
 for (PessoaJuridica pf : repo4.obterTodos()) {
 pf.exibir();
 }
 }
 }