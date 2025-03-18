
package model;


public class PessoaJuridica extends Pessoa{
    private String cnpj;
    
    public PessoaJuridica() {}
    
    public PessoaJuridica(int id, String nome, String cnpj) {
        super(id, nome);
        this.cnpj = cnpj;
    }
    
    public String getCnpj() {
        return cnpj;
    }
    
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
      
    public void exibri() {
        super.exibir();
        System.out.println("CNPJ: " + cnpj);
    }
}
