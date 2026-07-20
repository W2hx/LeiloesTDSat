/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) {

        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

        try {

            conn = new conectaDAO().connectDB();

            prep = conn.prepareStatement(sql);

            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());

            prep.executeUpdate();

            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");

            prep.close();
            conn.close();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto:\n" + e.getMessage());

        }

    }

public ArrayList<ProdutosDTO> listarProdutos() {

    String sql = "SELECT * FROM produtos";

    try {

        conn = new conectaDAO().connectDB();

        prep = conn.prepareStatement(sql);

        resultset = prep.executeQuery();

        listagem.clear();

        while (resultset.next()) {

            ProdutosDTO produto = new ProdutosDTO();

            produto.setId(resultset.getInt("id"));
            produto.setNome(resultset.getString("nome"));
            produto.setValor(resultset.getInt("valor"));
            produto.setStatus(resultset.getString("status"));

            listagem.add(produto);
        }

        conn.close();

    } catch (Exception e) {

        JOptionPane.showMessageDialog(null,
                "Erro ao listar produtos:\n" + e.getMessage());
    }

    return listagem;
}
public void venderProduto(int id) {

    String sql = "UPDATE produtos SET status=? WHERE id=?";

    try {

        conn = new conectaDAO().connectDB();

        prep = conn.prepareStatement(sql);

        prep.setString(1, "Vendido");
        prep.setInt(2, id);

        prep.executeUpdate();

        JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");

        prep.close();
        conn.close();

    } catch (Exception e) {

        JOptionPane.showMessageDialog(null,
                "Erro ao vender produto:\n" + e.getMessage());
    }

}
public ArrayList<ProdutosDTO> listarProdutosVendidos() {

    String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";

    ArrayList<ProdutosDTO> vendidos = new ArrayList<>();

    try {

        conn = new conectaDAO().connectDB();

        prep = conn.prepareStatement(sql);

        resultset = prep.executeQuery();

        while (resultset.next()) {

            ProdutosDTO produto = new ProdutosDTO();

            produto.setId(resultset.getInt("id"));
            produto.setNome(resultset.getString("nome"));
            produto.setValor(resultset.getInt("valor"));
            produto.setStatus(resultset.getString("status"));

            vendidos.add(produto);
        }

        conn.close();

    } catch (Exception e) {

        JOptionPane.showMessageDialog(null,
                "Erro ao listar produtos vendidos:\n" + e.getMessage());
    }

    return vendidos;
}
}
