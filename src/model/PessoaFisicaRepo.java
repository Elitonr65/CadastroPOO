package model;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class PessoaFisicaRepo {
    private List<PessoaFisica> pessoas = new ArrayList<>();
    
    public void inserir(PessoaFisica pessoa) {
        pessoas.add(pessoa);
    }
    
    public void excluir(int id) {
        pessoas.removeIf(p -> p.getId() == id); 
    }
    
    public PessoaFisica obter(int id) {
        return pessoas.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }
    
    public List<PessoaFisica> obterTodos() {
        return pessoas;
    }
    
    public void persistir(String arquivo) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            out.writeObject(pessoas);
        }
    }
    public void recuperar(String arquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo))) {
                pessoas = (List<PessoaFisica>) in.readObject();
        }
    }
}
