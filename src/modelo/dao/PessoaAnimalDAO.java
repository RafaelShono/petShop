/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.Animal;
import modelo.Cidade;
import modelo.Estado;
import modelo.Pessoa;
import visao_controle.AlterarVenda;

/**
 *
 * @author Rafael
 */
public class PessoaAnimalDAO {

    public void gravar(Pessoa pessoa) throws Exception {
        if (pessoa.getId() == 0) {
            inserir(pessoa);
        } else {
            alterar(pessoa);
        }
    }

    private void inserir(Pessoa pessoa) throws Exception {
        String sql = "insert into pessoa "
                + "(nome, data_nascimento, endereco, "
                + "numero, bairro, cidade_id, registro_ativo) "
                + "values "
                + "(?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement consulta = Conexao.getConexao().prepareStatement(sql);
        consulta.setString(1, pessoa.getNome());
        
        if (pessoa.getDataNascimento() != null){
        consulta.setDate(2,
                //Converte java.util.Date em java.sql.Date
                new java.sql.Date(pessoa.getDataNascimento().getTime()));
        }else{
            consulta.setDate(2, null);
        }
        
        consulta.setString(3, pessoa.getEndereco());
        consulta.setInt(4, pessoa.getNumero());
        consulta.setString(5, pessoa.getBairro());
        consulta.setInt(6, pessoa.getCidade().getId());
        consulta.setBoolean(7, pessoa.isAtivo());
        consulta.executeUpdate();//Roda o insert no BD
    }

    private void alterar(Pessoa pessoa) throws Exception {
        String sql = "update pessoa set "
                + "nome = ?, data_nascimento = ?, endereco = ?, "
                + "numero = ?, bairro = ?, cidade_id = ?, registro_ativo = ? "
                + "where id = ? ";

        PreparedStatement consulta = Conexao.getConexao().prepareStatement(sql);
        consulta.setString(1, pessoa.getNome());
        consulta.setDate(2,
                new java.sql.Date(pessoa.getDataNascimento().getTime())); //Converte java.util.Date em java.sql.Date
        consulta.setString(3, pessoa.getEndereco());
        consulta.setInt(4, pessoa.getNumero());
        consulta.setString(5, pessoa.getBairro());
        consulta.setInt(6, pessoa.getCidade().getId());
        consulta.setBoolean(7, pessoa.isAtivo());
        consulta.setInt(8, pessoa.getId());
        consulta.executeUpdate();//Roda o insert no BD
    }

    public ArrayList<Pessoa> pesquisar(String filtro) throws Exception {
        ArrayList<Pessoa> pessoas = new ArrayList();
        String sql = "select p.*, c.nome as cidade, e.sigla from pessoa p "
                + "left join cidade c on c.id = p.cidade_id "
                + "left join estado e on e.id = c.estado_id "
                + "where registro_ativo and "
                + "(lower(p.nome) like ? or lower(c.nome) like ?) ";

        PreparedStatement consulta = Conexao.getConexao().prepareStatement(sql);
        consulta.setString(1, "%" + filtro.toLowerCase() + "%");//nome
        consulta.setString(2, "%" + filtro.toLowerCase() + "%");//cidade
        ResultSet resultadoConsulta = consulta.executeQuery();
        
        //Faz a varredura na tabelinha de resultado da consulta
        while (resultadoConsulta.next()) {
            Pessoa pessoa = new Pessoa();
            pessoa.setId(resultadoConsulta.getInt("id"));
            pessoa.setNome(resultadoConsulta.getString("nome"));
            pessoa.setDataNascimento(resultadoConsulta.getDate("data_nascimento"));
            pessoa.setEndereco(resultadoConsulta.getString("endereco"));
            pessoa.setNumero(resultadoConsulta.getInt("numero"));
            pessoa.setBairro(resultadoConsulta.getString("bairro"));
            pessoa.setAtivo(resultadoConsulta.getBoolean("registro_ativo"));
            
            Cidade cidade = new Cidade();
            cidade.setId(resultadoConsulta.getInt("cidade_id"));
            cidade.setNome(resultadoConsulta.getString("cidade"));
            
            Estado estado = new Estado();
            estado.setSigla(resultadoConsulta.getString("sigla"));
            cidade.setEstado(estado);
            
            pessoa.setCidade(cidade);

            pessoas.add(pessoa);
        }

        return pessoas;
    }
     public ArrayList<Pessoa> listar() throws Exception {
        ArrayList<Pessoa> pessoas = new ArrayList();
        String sql = "select id, nome from pessoa "
                + "where registro_ativo";

        PreparedStatement consulta = Conexao.getConexao().prepareStatement(sql);
        ResultSet resultadoConsulta = consulta.executeQuery();

        //Faz a varredura na tabelinha de resultado da consulta
        while (resultadoConsulta.next()) {
            Pessoa pessoa = new Pessoa();
            pessoa.setId(resultadoConsulta.getInt("id"));
            pessoa.setNome(resultadoConsulta.getString("nome"));
            pessoas.add(pessoa);
        }

        return pessoas;
    }
     
      public ArrayList<Animal> carregarAnimais(Pessoa pessoa) throws Exception {
        ArrayList<Animal> animais = new ArrayList();
        String sql = "select * from animal where registro_ativo and pessoa_id = ?";

        PreparedStatement consulta = Conexao.getConexao().prepareStatement(sql);
        consulta.setInt(1, pessoa.getId());
        ResultSet resultadoConsulta = consulta.executeQuery();

        //Faz a varredura na tabelinha de resultado da consulta
        while (resultadoConsulta.next()) {
            Animal animal = new Animal();
            animal.setId(resultadoConsulta.getInt("id"));
            animal.setNome(resultadoConsulta.getString("nome"));
            animal.setDono(pessoa);
            animal.setAtivo(resultadoConsulta.getBoolean("registro_ativo"));
            animais.add(animal);
        }

        return animais;
    }

    //Não utiliza porque não exclui registros no BD, apenas inativa
//    private void excluir(Pessoa pessoa) throws Exception {
//        String sql = "delete from pessoa where id = ?";
//        PreparedStatement consulta = Conexao.getConexao().prepareStatement(sql);
//        consulta.setInt(1, pessoa.getId());
//        consulta.executeUpdate();//Roda o insert no BD
//    }
}
