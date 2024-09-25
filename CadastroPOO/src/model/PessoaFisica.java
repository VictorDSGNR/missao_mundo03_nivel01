/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
 package model;
 /**
 *
 * @author victoracosta
 */
 import java.io.Serializable;
 // PessoaFisica class
 public class PessoaFisica extends Pessoa implements Serializable {
 private String cpf;
 private int idade;
 public PessoaFisica() {
 super();
 }
 public PessoaFisica(int id, String nome, String cpf, int idade) {
 super(id, nome);
 this.cpf = cpf;
 this.idade = idade;
 }
 @Override
 public void exibir() {
 super.exibir();
 System.out.println("CPF: " + cpf);
 System.out.println("Idade: " + idade);
 }
}