package model;
import java.io.*;
import java.util.ArrayList;

public class PessoaFisicaRepo {
    private ArrayList<PessoaFisica> lista = new ArrayList<>();
    
    public void inserir(PessoaFisica pf) {
        lista.add(pf);
    }
    
    public void alterar(int id, PessoaFisica novaPessoa) {
        for (PessoaFisica pf : lista) {
            if (pf.getId() == id) {
                pf.setNome(novaPessoa.getNome());
                pf.setCpf(novaPessoa.getCpf());
                pf.setIdade(novaPessoa.getIdade);
                return;
            }
        }
    }
    
    public void excluir(int id) {
        lista.removeIf(pf -> pf.getId() == id); 
    }
    
    public PessoaFisica obter(int id) {
        for (PessoaFisica pf : lista) {
            if (pf.getId() == id) {
                return pf;
            }
        }
        return null;
    }
    
    public void exibirTodos() {
        for(PessoaFisica pf : lista) {
            pf.exibir();
            System.out.println("-----------------");
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
                lista = (ArrayList<PessoaFisica>) in.readObject();
        }
    }

    public PessoaFisica buscarPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
