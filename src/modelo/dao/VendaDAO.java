/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.Animal;
import modelo.ItemVenda;
import modelo.Pessoa;
import modelo.ProdutoServico;
import modelo.Venda;

/**
 *
 * @author suporte
 */
public class VendaDAO {

    public void gravar(Venda venda) throws Exception {
        if (venda.getId() == 0) {
            inserir(venda);
        } else {
            alterar(venda);
        }
    }

    private void inserir(Venda venda) throws Exception {
        PreparedStatement stmt = Conexao.getConexao().prepareStatement(
                "insert into venda (pessoa_id, data_hora) VALUES (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
        stmt.setInt(1, venda.getCliente().getId());
        stmt.setDate(2, new java.sql.Date(venda.getDataHora().getTime()));
        stmt.executeUpdate();
        //seta os parametros...
        //....

    
        ResultSet genKeys = stmt.getGeneratedKeys();
        if (genKeys.next()) {
            venda.setId(genKeys.getInt(1));
        }
        inserirOuAtualizarItensVenda(venda);
    }

    private void inserirOuAtualizarItensVenda(Venda venda) throws Exception {
        //Primeiro deleta todos os itens
        PreparedStatement consultaDeleteItens
                = Conexao.getConexao().prepareStatement("delete from itens_venda where venda_id = (?)");
        consultaDeleteItens.setInt(1, venda.getId());
        consultaDeleteItens.executeUpdate();

        //depois insere tudo novamente
        for (ItemVenda item : venda.getItens()) {
            PreparedStatement consultaReinsereItens
                    = Conexao.getConexao().prepareStatement(
                            "insert into itens_venda (venda_id, produtos_servicos_id, animal_id, quantidade, valor_unitario) values "
                            + "(?, ?, ?, ?, ?)");
            consultaReinsereItens.setInt(1, venda.getId());
            consultaReinsereItens.setInt(2, item.getProdutoServico().getId());
            consultaReinsereItens.setInt(3, item.getAnimal().getId());
            consultaReinsereItens.setFloat(4, item.getQuantidade());
            consultaReinsereItens.setFloat(5, item.getValorUnitario());
            
            consultaReinsereItens.executeUpdate();
        }
    }

    private void alterar(Venda venda) throws Exception {
        //Alterar na tabela venda...
        //.......
        inserirOuAtualizarItensVenda(venda);
    }

    public ArrayList<Venda> pesquisa(String filtro) throws Exception {

        ArrayList<Venda> venda = new ArrayList();

        String sql = "select  v.id, "
                + "v.data_hora, "
                + "p.nome as cliente "
                + "from venda v "
                + "join pessoa p on p.id = v.pessoa_id "
                + "where v.registro_ativo and "
                + "lower(to_char(v.data_hora, 'dd/MM/yyyy HH12:MI:SS')||p.nome) like ? ";

        PreparedStatement consulta = Conexao.getConexao().prepareStatement(sql);
        consulta.setString(1, "%" + filtro.toLowerCase() + "%");
        // consulta.setString(2, "%" + filtro.toLowerCase() + "%");
        ResultSet resultadoConsulta = consulta.executeQuery();

        //Faz a varredura na tabelinha de resultado da consulta
        while (resultadoConsulta.next()) {

            Venda vendasPesquisar = new Venda();
            Pessoa p = new Pessoa();
            vendasPesquisar.setId(resultadoConsulta.getInt("id"));
            vendasPesquisar.setDataHora(resultadoConsulta.getDate("data_hora"));
            vendasPesquisar.setCliente(p);
            p.setNome((resultadoConsulta.getString("cliente")));
            venda.add(vendasPesquisar);

        }

        return venda;

    }

    public Venda carregar(Venda venda) throws Exception {

        String sql = "select * ,v.id as idVenda, iv.id as idItem, p.id as idPessoa,ani.id as idAnimal, ani.nome as nomeAnimal, prodS. nome as nomeProduto"
                + "  from venda v  "
                + "  join pessoa p on p.id = v.pessoa_id  "
                + "  join itens_venda iv on iv.venda_id = v.id  "
                + "  join animal ani on ani.id = iv.animal_id"
                + "  join produtos_servicos prodS on prodS.id = iv.produtos_servicos_id "
                + "  where v.id = ? ";

        PreparedStatement consulta = Conexao.getConexao().prepareStatement(sql);
        consulta.setInt(1, venda.getId());

        ResultSet resultadoConsulta = consulta.executeQuery();

        //Faz a varredura na tabelinha de resultado da consulta
        Venda vendasPesquisar = new Venda();

        while (resultadoConsulta.next()) {
            Pessoa pessoa = new Pessoa();
           // Animal animal = new Animal();
            vendasPesquisar.setId(resultadoConsulta.getInt("idVenda"));
            vendasPesquisar.setCliente(pessoa);
            vendasPesquisar.getCliente().setId(resultadoConsulta.getInt("pessoa_id"));
            vendasPesquisar.getCliente().setNome(resultadoConsulta.getString("nome"));
            vendasPesquisar.setDataHora(resultadoConsulta.getDate("data_hora"));
            vendasPesquisar.setAtivo(resultadoConsulta.getBoolean("registro_ativo"));
            
            ItemVenda item = new ItemVenda();
            
            item.setAnimal(new Animal());
           
            item.setId(resultadoConsulta.getInt("idItem"));
            item.setQuantidade(resultadoConsulta.getFloat("quantidade"));
            item.setValorUnitario(resultadoConsulta.getFloat("valor_unitario"));
            item.getAnimal().setId(resultadoConsulta.getInt("idAnimal"));
           // item.getProdutoServico().setNome(resultadoConsulta.getString("nomeAnimal"));
            item.getAnimal().setNome(resultadoConsulta.getString("nomeAnimal"));
            
           ProdutoServico produto = new ProdutoServico();
           produto.setId(resultadoConsulta.getInt("produtos_servicos_id"));
           produto.setNome(resultadoConsulta.getString("nomeProduto"));
           item.setProdutoServico(produto);
            
            vendasPesquisar.getItens().add(item);

        }

        return vendasPesquisar;

    }

    public ArrayList<Venda> listar() throws Exception {
        ArrayList<Venda> vendas = new ArrayList();
        String sql = "select  v.id, "
                + "v.data_hora, "
                + "p.nome as cliente "
                + "from venda v "
                + "join pessoa p on p.id = v.pessoa_id "
                + "where v.registro_ativo and "
                + "lower(to_char(v.data_hora, 'dd/MM/yyyy HH12:MI:SS')||p.nome) where registro_ativo";

        PreparedStatement consulta = Conexao.getConexao().prepareStatement(sql);
        ResultSet resultadoConsulta = consulta.executeQuery();

        //Faz a varredura na tabelinha de resultado da consulta
        while (resultadoConsulta.next()) {
            Venda venda = new Venda();
            venda.setId(resultadoConsulta.getInt("id"));
            venda.getCliente().setNome(resultadoConsulta.getString("nome"));
            venda.setDataHora((resultadoConsulta.getDate("data_hora")));
            vendas.add(venda);
        }

        return vendas;
    }
}
