
package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaJuridicaRepo {
    private List<PessoaJuridica> empresas = new ArrayList<>();
    
    public void inserir(PessoaJuridica empresa) {
        empresas.add(empresa);
    }
    
    public void excluir(int id) {
        empresas.removeIf(e -> e.getId() == id);
    }
    
    public PessoaJuridica obter(int id) {
        return empresas.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }
    
    public List<PessoaJuridica> obterTodos() {
        return empresas;
    }
    
    public void persistir(String arquivo) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            out.writeObject(empresas);
        }
    }
    public void recuperar(String arquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo))) {
            empresas = (List<PessoaJuridica>) in.readObject();
        }
    }
}
