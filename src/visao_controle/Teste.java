/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao_controle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import modelo.Cidade;
import modelo.Estado;
import modelo.Pessoa;
import modelo.dao.Conexao;
import modelo.dao.PessoaAnimalDAO;

/**
 *
 * @author Rafael
 */
public class Teste {
    public static void main(String[] args) throws Exception {
    
         ArrayList<Pessoa> cliente = new ArrayList<>();
        String sql = "select id, nome from pessoa venda";
        PreparedStatement comandoSql = Conexao.getConexao().prepareStatement(sql);
        ResultSet retornoConsulta = comandoSql.executeQuery();
        while (retornoConsulta.next()) {
            Pessoa e = new Pessoa();
            e.setId(retornoConsulta.getInt("id"));
            e.setNome(retornoConsulta.getString("nome"));
            cliente.add(e);
        
         System.out.println(e.getNome());
        }
    }
}
