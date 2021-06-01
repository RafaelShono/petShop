/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visao_controle;

import com.toedter.calendar.JDateChooser;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Animal;
import modelo.ItemVenda;
import modelo.Pessoa;
import modelo.ProdutoServico;
import modelo.dao.Conexao;
import modelo.dao.PessoaAnimalDAO;
import modelo.dao.ProdutoServDAO;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.swing.JTable;
import modelo.Utilitario;
import modelo.Venda;
import modelo.dao.VendaDAO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import visao_controle.AlterarVenda;

/**
 *
 * @author rafael
 */
public class Vendas extends javax.swing.JFrame {

    private Pessoa pessoaLocal = new Pessoa();
    private ArrayList<Pessoa> listaClientes;
    private ArrayList<Animal> nomeAnimais;
    private ArrayList<ProdutoServico> prodServicosLista;
    private int valorAgregar = 0;

    public Venda vendaCompleta = new Venda();

    int idCliente;

    private float totalSoma = 0;

    /**
     * Creates new form Vendas
     */
    public Vendas() throws Exception {
        initComponents();

        this.vendaCompleta = new Venda();
        jTotalCompra.setEditable(false);
        carregarComboClientes();
        carregarComboProdutoServicos();

    }

    public void setVendaEmEdicao(Venda vendaEmEdicao) throws Exception {
        this.vendaCompleta = vendaEmEdicao;

        preencherDadosTela();

    }

    private void preencherDadosTela() throws Exception {

        Date dataDia = vendaCompleta.getDataHora();
        jDataAtendimento.setDate(dataDia);
        atualizarListaProdutos();
        for (int i = 0; i < this.listaClientes.size(); i++) {
            if (this.listaClientes.get(i).getId() == this.vendaCompleta.getCliente().getId()) {
                jcbNomeCliente.setSelectedIndex(i);
            }
        }

    }

    private void carregarComboClientes() {
        PessoaAnimalDAO dao = new PessoaAnimalDAO();
        try {
            this.listaClientes = dao.listar();
            jcbNomeCliente.removeAllItems();
            for (Pessoa cliente : this.listaClientes) {
                jcbNomeCliente.addItem(cliente.getNome());
                System.out.println(cliente.getId());
            }
        } catch (Exception ex) {
            Logger.getLogger(Vendas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void carregarComboProdutoServicos() throws Exception {
        ProdutoServDAO dao = new ProdutoServDAO();
        try {
            this.prodServicosLista = dao.carregarProdutosOuServicos();
            jcbNomeProdutos.removeAllItems();
            for (ProdutoServico produtoOuServico : this.prodServicosLista) {
                jcbNomeProdutos.addItem(produtoOuServico.getNome());
            }

        } catch (Exception ex) {
            Logger.getLogger(Vendas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private  void gerarRelatorio() throws JRException, Exception{
          java.util.Date dataEsc = jDataAtendimento.getDate();
         //   vendaCompleta.setDataHora(dataEsc);
        
        JasperReport report = JasperCompileManager.compileReport("Relatorios/pedido.jrxml");
        Map<String, Object> parametros  =  new  HashMap<>();
      //  parametros.put("pessoaB", this.vendaCompleta.getCliente().getId());
         parametros.put("vendaID", this.vendaCompleta.getId());
     //   parametros.put("data", dataEsc);
        
         
        JasperPrint  jasperPrint = JasperFillManager.fillReport(report, parametros, Conexao.getConexao());
       JasperViewer viewer = new JasperViewer(jasperPrint);
       viewer.setTitle("Pedido Detalhado");
       viewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
       viewer.setVisible(true);
       
       // JasperViewer viewer = new JasperViewer(jasperPrint, false, false);
        ////Coloca em maximizado
       // viewer.setTitle("Pedido Detalhado");//Coloca um título no relatório
       // viewer.setVisible(true);
    
    
        
       
    
    
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jcbNomeCliente = new javax.swing.JComboBox<>();
        jDataAtendimento = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jQntProduto = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jcbNomeProdutos = new javax.swing.JComboBox<>();
        jBtnAdd = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jcbNomeAnimal = new javax.swing.JComboBox<>();
        jAumentarProd = new javax.swing.JButton();
        jDiminuirProd = new javax.swing.JButton();
        jValorUnidade = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableLista = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jTotalCompra = new javax.swing.JFormattedTextField();
        jButton2 = new javax.swing.JButton();
        pesquisarVenda = new javax.swing.JButton();
        JbtnDeletarCompra = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/shop2.png"))); // NOI18N
        jLabel2.setText("jLabel2");

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Vendas");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel1))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nome do cliente:");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Data do Atendimento");

        jcbNomeCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbNomeClienteActionPerformed(evt);
            }
        });
        jcbNomeCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jcbNomeClienteKeyReleased(evt);
            }
        });

        jDataAtendimento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jDataAtendimentoMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jDataAtendimentoMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jDataAtendimento, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(27, 27, 27)
                        .addComponent(jcbNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(250, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jcbNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDataAtendimento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(129, 129, 129))
        );

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));
        jPanel3.setForeground(new java.awt.Color(204, 204, 204));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nome do produto");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Quantidade");

        jQntProduto.setText("1");
        jQntProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jQntProdutoActionPerformed(evt);
            }
        });
        jQntProduto.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jQntProdutoPropertyChange(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Valor Unidade");

        jcbNomeProdutos.setToolTipText("");
        jcbNomeProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jcbNomeProdutosMouseClicked(evt);
            }
        });
        jcbNomeProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbNomeProdutosActionPerformed(evt);
            }
        });

        jBtnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/btnAdd.png"))); // NOI18N
        jBtnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnAddActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Nome do Animal:");

        jcbNomeAnimal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jcbNomeAnimalMouseReleased(evt);
            }
        });
        jcbNomeAnimal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbNomeAnimalActionPerformed(evt);
            }
        });

        jAumentarProd.setText("+");
        jAumentarProd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jAumentarProdMouseClicked(evt);
            }
        });
        jAumentarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAumentarProdActionPerformed(evt);
            }
        });

        jDiminuirProd.setText("-");
        jDiminuirProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDiminuirProdActionPerformed(evt);
            }
        });
        jDiminuirProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jDiminuirProdKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(40, 40, 40)
                                .addComponent(jcbNomeAnimal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel4))
                                .addGap(36, 36, 36)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jcbNomeProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jQntProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jDiminuirProd)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jAumentarProd))
                                    .addComponent(jValorUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 249, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBtnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbNomeAnimal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(17, 17, 17)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jcbNomeProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jQntProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jAumentarProd)
                    .addComponent(jDiminuirProd))
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jValorUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jBtnAdd)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(0, 51, 51));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 690, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 65, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(0, 51, 51));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
        );

        jPanel6.setBackground(new java.awt.Color(0, 51, 51));

        jTableLista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Animal", " Produto/Serviço", "Quantidade", "Preço Unitário", "Total Item"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableLista.setAutoscrolls(false);
        jTableLista.setShowVerticalLines(false);
        jTableLista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableListaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableLista);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(0, 51, 51));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Total:");

        jTotalCompra.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("¤#,##0"))));
        jTotalCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTotalCompraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jTotalCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTotalCompra, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/save.png"))); // NOI18N
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jButton2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton2KeyPressed(evt);
            }
        });

        pesquisarVenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/pesquisar.png"))); // NOI18N
        pesquisarVenda.setText("Pesquisar/Alterar");
        pesquisarVenda.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pesquisarVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisarVendaActionPerformed(evt);
            }
        });

        JbtnDeletarCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/delete.png"))); // NOI18N
        JbtnDeletarCompra.setText("Deletar");
        JbtnDeletarCompra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JbtnDeletarCompraMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JbtnDeletarCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(pesquisarVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 217, Short.MAX_VALUE)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(60, 60, 60))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(JbtnDeletarCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pesquisarVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 27, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jTableListaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableListaMouseClicked
        // TODO add your handling code here:

        DefaultTableModel model = (DefaultTableModel) jTableLista.getModel();
        int linhaSelecionada = jTableLista.getSelectedRow();
        model.removeRow(linhaSelecionada);
        vendaCompleta.getItens().remove(linhaSelecionada);
        //  atualizarListaProdutos();


    }//GEN-LAST:event_jTableListaMouseClicked

    private void jcbNomeProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbNomeProdutosActionPerformed
        // TODO add your handling code here:

        if (jcbNomeProdutos.getSelectedItem() != null) {
            ProdutoServico produto = prodServicosLista.get(jcbNomeProdutos.getSelectedIndex());
            jValorUnidade.setText(Utilitario.formatarMoeda(produto.getValor()));

        }

    }//GEN-LAST:event_jcbNomeProdutosActionPerformed

    private void jcbNomeProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcbNomeProdutosMouseClicked
        // TODO add your handling code here:


    }//GEN-LAST:event_jcbNomeProdutosMouseClicked

    private void jQntProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jQntProdutoActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_jQntProdutoActionPerformed

    private void jcbNomeClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jcbNomeClienteKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbNomeClienteKeyReleased

    private void jcbNomeClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbNomeClienteActionPerformed
        // TODO add your handling code here:
        if (jcbNomeCliente.getSelectedItem() != null) {
            PessoaAnimalDAO dao = new PessoaAnimalDAO();
            Pessoa nomeclienteSelecionado = this.listaClientes.get(jcbNomeCliente.getSelectedIndex());

            idCliente = nomeclienteSelecionado.getId();

            try {
                this.nomeAnimais = dao.carregarAnimais(nomeclienteSelecionado);
                jcbNomeAnimal.removeAllItems();
                for (Animal animal : this.nomeAnimais) {
                    jcbNomeAnimal.addItem(animal.getNome());
                }
            } catch (Exception ex) {
                Logger.getLogger(Vendas.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }//GEN-LAST:event_jcbNomeClienteActionPerformed

    private void jcbNomeAnimalMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcbNomeAnimalMouseReleased


    }//GEN-LAST:event_jcbNomeAnimalMouseReleased

    private void jAumentarProdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jAumentarProdMouseClicked
        // TODO add your handling code here:


    }//GEN-LAST:event_jAumentarProdMouseClicked

    private void jQntProdutoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jQntProdutoPropertyChange
        // TODO add your handling code here:


    }//GEN-LAST:event_jQntProdutoPropertyChange

    private void jDataAtendimentoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDataAtendimentoMouseReleased

    }//GEN-LAST:event_jDataAtendimentoMouseReleased

    private void jButton2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton2KeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton2KeyPressed

    private void jBtnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnAddActionPerformed
        // TODO add your handling code here:
        ItemVenda item = new ItemVenda();
        if (jcbNomeAnimal.getSelectedIndex() == -1) {
            String mensagem = "Preencha todos os campos";
            JOptionPane.showMessageDialog(null, mensagem);

        } else {
            Animal animal = this.nomeAnimais.get(jcbNomeAnimal.getSelectedIndex());
            item.setAnimal(animal);
         
            String qnt = jQntProduto.getText().replaceAll(",", ".");
            String val = jValorUnidade.getText().replaceAll(",", ".");
            if (qnt == null || "".equals(qnt) || Float.parseFloat(qnt) == 0) {
                String mensagem = "Preencha todos os campos";
                JOptionPane.showMessageDialog(null, mensagem);
            } else if (val == null || "".equals(val)) {
                String mensagem = "Preencha todos os campos";
                JOptionPane.showMessageDialog(null, mensagem);
            } else {
                ProdutoServico produto = this.prodServicosLista.get(jcbNomeProdutos.getSelectedIndex());
                item.setProdutoServico(produto);

                val = val.replaceAll("R\\$ ", "");
                item.setQuantidade(Float.parseFloat(qnt));
                item.setValorUnitario(Float.parseFloat(val));
                float total = Float.parseFloat(qnt) * Float.parseFloat(val);
                //    totalSoma += total;

                //  jTotalCompra.setText(Utilitario.formatarMoeda(totalSoma));
                this.vendaCompleta.getItens().add(item);

                atualizarListaProdutos();

            }
        }

        // clear the entries.
       
    }//GEN-LAST:event_jBtnAddActionPerformed

    private void limparTabela() {

        //DefaultTableModel model = (DefaultTableModel) jTableLista.getModel();
        //model.setRowCount(0);
        jTotalCompra.setText("");
        JDateChooser dateChooser = new JDateChooser();
        jDataAtendimento.setDate(new Date());

    }
     

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        try {
            // TODO add your handling code here:

            pessoaLocal.setId(idCliente);

            java.util.Date dataEsc = jDataAtendimento.getDate();
            vendaCompleta.setDataHora(dataEsc);
            vendaCompleta.setCliente(this.pessoaLocal);
            VendaDAO venda = new VendaDAO();
            String mensagem = "Você precisa preencher todos os campos";
            if (dataEsc == null || jcbNomeProdutos.getSelectedItem() == null || jValorUnidade.getText() == null) {
                JOptionPane.showMessageDialog(null, mensagem);
            } else {
                try {

                    venda.gravar(this.vendaCompleta);
                    gerarRelatorio();
                    limparTabela();
                    
                } catch (Exception ex) {
                    Logger.getLogger(Vendas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(Vendas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton2MouseClicked

    private void jcbNomeAnimalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbNomeAnimalActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_jcbNomeAnimalActionPerformed

    private void jDataAtendimentoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDataAtendimentoMouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_jDataAtendimentoMouseExited

    private void jTotalCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTotalCompraActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_jTotalCompraActionPerformed

    private void pesquisarVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesquisarVendaActionPerformed
        try {
            // TODO add your handling code here:

            AlterarVenda telaAlterarVenda = new AlterarVenda();
            telaAlterarVenda.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(Vendas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_pesquisarVendaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void JbtnDeletarCompraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JbtnDeletarCompraMouseClicked
        // TODO add your handling code here:
        VendaDAO dao = new VendaDAO();

        this.vendaCompleta.setAtivo(false);
        try {
            dao.gravar(vendaCompleta);
        } catch (Exception ex) {
            Logger.getLogger(Vendas.class.getName()).log(Level.SEVERE, null, ex);
        }

        // this.vendaCompleta.isAtivo();
        limparTabela();
        


    }//GEN-LAST:event_JbtnDeletarCompraMouseClicked

    private void jDiminuirProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDiminuirProdActionPerformed
        // TODO add your handling code here:
        valorAgregar --;
        if(valorAgregar<0){
        JOptionPane.showMessageDialog(null, "Insira valor maior que 1"); 
        }
        String.valueOf(valorAgregar);
        jQntProduto.setText(String.valueOf(valorAgregar));
        
    }//GEN-LAST:event_jDiminuirProdActionPerformed

    private void jDiminuirProdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDiminuirProdKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jDiminuirProdKeyPressed

    private void jAumentarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAumentarProdActionPerformed
        // TODO add your handling code here:
          valorAgregar ++;
           if(valorAgregar<0){
        JOptionPane.showMessageDialog(null, "Insira valor maior que 1"); 
        }
        String.valueOf(valorAgregar);
        jQntProduto.setText(String.valueOf(valorAgregar));
    }//GEN-LAST:event_jAumentarProdActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Vendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vendas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Vendas().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(Vendas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JbtnDeletarCompra;
    private javax.swing.JButton jAumentarProd;
    private javax.swing.JButton jBtnAdd;
    private javax.swing.JButton jButton2;
    private com.toedter.calendar.JDateChooser jDataAtendimento;
    private javax.swing.JButton jDiminuirProd;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JTextField jQntProduto;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableLista;
    private javax.swing.JFormattedTextField jTotalCompra;
    private javax.swing.JTextField jValorUnidade;
    private javax.swing.JComboBox<String> jcbNomeAnimal;
    private javax.swing.JComboBox<String> jcbNomeCliente;
    private javax.swing.JComboBox<String> jcbNomeProdutos;
    private javax.swing.JButton pesquisarVenda;
    // End of variables declaration//GEN-END:variables

    private void atualizarListaProdutos() {

        DefaultTableModel model = (DefaultTableModel) jTableLista.getModel();
        model.setNumRows(0);
        float somador = 0;
        for (ItemVenda item : vendaCompleta.getItens()) {

            Object[] row = {
                item.getAnimal().getNome(),
                item.getProdutoServico().getNome(),
                item.getQuantidade(), Utilitario.formatarMoeda(item.getValorUnitario()),
                Utilitario.formatarMoeda(item.getQuantidade() * item.getValorUnitario())};
            somador += item.getQuantidade() * item.getValorUnitario();
            System.out.println(somador);
            model.addRow(row);

        }

        jTotalCompra.setText(String.valueOf(somador));

    }
}
