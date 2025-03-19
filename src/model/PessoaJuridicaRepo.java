
package model;

import java.io.*;
import java.util.ArrayList;

public class PessoaJuridicaRepo {
    private ArrayList<PessoaJuridica> lista = new ArrayList<>();
    
    public void inserir(PessoaJuridica pj) {
        lista.add(pj);
    }
    
    public void excluir(int id) {
        lista.removeIf(pj -> pj.getId() == id);
    }
    
    public void exibirTodos() {
        for (PessoaJuridica pj : lista) {
            pj.exibir();
            System.out.println("----------------");
        }
    }
    
    public void persistir(String arquivo) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            out.writeObject(lista);
        }
    }
    
    @SuppressWarnings("unchecked")            
    public void recuperar(String arquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo))) {
            lista = (ArrayList<PessoaJuridica>) in.readObject();
        }
    }
}
