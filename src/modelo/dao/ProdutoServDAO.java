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
 * @author rafael
 */
public class ProdutoServDAO {
     public ArrayList<ProdutoServico> carregarProdutosOuServicos() throws Exception {
        ArrayList<ProdutoServico> listaProdServ = new ArrayList();
        String sql = "select * from produtos_servicos where registro_ativo";
       
        PreparedStatement consulta = Conexao.getConexao().prepareStatement(sql);
      
        ResultSet resultadoConsulta = consulta.executeQuery();

        //Faz a varredura na tabelinha de resultado da consulta
        while (resultadoConsulta.next()) {
            ProdutoServico  prodServDados = new ProdutoServico();
            prodServDados.setId(resultadoConsulta.getInt("id"));
            prodServDados.setNome(resultadoConsulta.getString("nome"));
            prodServDados.setValor(resultadoConsulta.getFloat("valor"));
            prodServDados.setAtivo(resultadoConsulta.getBoolean("registro_ativo"));
            listaProdServ.add(prodServDados);
        }
         System.out.println(listaProdServ);

        return listaProdServ;
    }
    
    
    
}
