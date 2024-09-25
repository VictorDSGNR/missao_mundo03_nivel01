/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
 package model;
 /**
*
 * @author victoracosta
 */
 import java.io.*;
 import java.util.ArrayList;
 import java.util.List;
 public class PessoaJuridicaRepo {
 private List<PessoaJuridica> pessoasJuridicas = new ArrayList<>();
 public void inserir(PessoaJuridica pessoaJuridica) {
 pessoasJuridicas.add(pessoaJuridica);
 }
 public void alterar(PessoaJuridica pessoaJuridica) {
 }
 public void excluir(int id) {
 }
 public PessoaJuridica obter(int id) {
 return null;
 }
 public List<PessoaJuridica> obterTodos() {
 return new ArrayList<>(pessoasJuridicas);
 }
 public void persistir(String fileName) throws IOException {
 try (ObjectOutputStream oos = new ObjectOutputStream(new
 FileOutputStream(fileName))) {
 oos.writeObject(pessoasJuridicas);
 }
 }
 public void recuperar(String fileName) throws IOException, ClassNotFoundException {
 try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
 pessoasJuridicas = (List<PessoaJuridica>) ois.readObject();
 }
 }
 }
