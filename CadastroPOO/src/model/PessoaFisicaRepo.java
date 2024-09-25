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
 public class PessoaFisicaRepo {
 private List<PessoaFisica> pessoasFisicas = new ArrayList<>();
 public void inserir(PessoaFisica pessoaFisica) {
 pessoasFisicas.add(pessoaFisica);
 }
 public void alterar(PessoaFisica pessoaFisica) {
 }
 public void excluir(int id) {
 }
 public PessoaFisica obter(int id) {
 return null;
 }
 public List<PessoaFisica> obterTodos() {
 return new ArrayList<>(pessoasFisicas);
 }
 public void persistir(String fileName) throws IOException {
 try (ObjectOutputStream oos = new ObjectOutputStream(new
 FileOutputStream(fileName))) {
 oos.writeObject(pessoasFisicas);
 }
 }
public void recuperar(String fileName) throws IOException, ClassNotFoundException {
 try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
 pessoasFisicas = (List<PessoaFisica>) ois.readObject();
 }
 }
 }