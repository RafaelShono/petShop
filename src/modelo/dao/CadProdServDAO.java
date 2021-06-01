/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.ProdutoServico;

/**
 *
 * @author suporte
 */
public class CadProdServDAO {
     public void gravar(ProdutoServico produto) throws Exception {
        if (produto.getId() == 0) {
            inserir(produto);
        } else {
            alterar(produto);
        }
    }
     private void inserir(ProdutoServico produto) throws Exception {
        String sql = "insert into produtos_servicos (nome,valor,servico,frequencia_aplicacao_dias) values (?,?,?,?) ";
        PreparedStatement consulta = Conexao.getConexao().prepareStatement(sql);
        consulta.setString(1, produto.getNome());
        consulta.setDouble(2,  produto.getValor());
        consulta.setBoolean(3, produto.isServico());
        consulta.setInt(4,produto.getFrequenciaAplicacao());
        consulta.executeUpdate();
        
      
     }
     
     private void alterar(ProdutoServico produto) throws Exception {
        String sql = "update produtos_servicos set "
                + "nome = ?, servico = ?, "
                + "valor = ?, frequencia_aplicacao_dias = ?, registro_ativo = ?"
                + " where id = ? ";

        PreparedStatement consulta = Conexao.getConexao().prepareStatement(sql);
        consulta.setString(1,produto.getNome());
        consulta.setBoolean(2, produto.isServico());
        consulta.setFloat(3, produto.getValor());
        consulta.setInt(4, produto.getFrequenciaAplicacao());
        consulta.setBoolean(5, produto.isAtivo());
        consulta.setInt(6, produto.getId());
        consulta.executeUpdate();//Roda o insert no BD
     }
public ArrayList<ProdutoServico> pesquisar(String filtro) throws Exception {
        ArrayList<ProdutoServico> produto = new ArrayList();
        String sql = "SELECT * from produtos_servicos  WHERE registro_ativo AND lower(nome) like ?";

        PreparedStatement consulta = Conexao.getConexao().prepareStatement(sql);
        consulta.setString(1, "%" + filtro.toLowerCase() + "%");//nome
        //consulta.setString(2, "%" + filtro.toLowerCase() + "%");//valor
        ResultSet resultadoConsulta = consulta.executeQuery();
        
        //Faz a varredura na tabelinha de resultado da consulta
        while (resultadoConsulta.next()) {
            ProdutoServico proServ = new ProdutoServico();
           proServ.setNome(resultadoConsulta.getString("nome"));
           proServ.setValor(resultadoConsulta.getFloat("valor"));
           proServ.setAtivo(resultadoConsulta.getBoolean("registro_ativo"));
           proServ.setFrequenciaAplicacao(resultadoConsulta.getInt("frequencia_aplicacao_dias"));
           proServ.setId(resultadoConsulta.getInt("id"));
            proServ.setServico(resultadoConsulta.getBoolean("servico"));
            
            produto.add(proServ);
         //   prod.setId(resultadoConsulta.getInt("id"));
           
           
            
           // prod.setCidade(cidade);

            //pessoas.add(pessoa);
        }

        return produto;
    }


}