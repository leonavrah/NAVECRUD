package br.edu.ceub.navcrud.pesistencia;

import java.util.List;

import br.edu.ceub.navcrud.model.Contato;

public interface IcontatoDAO {
    public boolean salvar(Contato contato);
    public boolean atualizar(Contato contato);
    public boolean deletar(Contato contato);
    public List<Contato> listar();

}
