package br.edu.utfpr.dv.sireata.dao;

import br.edu.utfpr.dv.sireata.model.Pauta;
import br.edu.utfpr.dv.sireata.util.ConnectionUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PautaDAO implements AbstractDAO<Pauta> {
    private final DefaultDAO dao = new DefaultDAO();


    public Pauta buscarPorId(int id) throws SQLException {
        ResultSet rs = dao.buscarPorId(id, "SELECT * FROM pautas WHERE idPauta = ?");
        return this.carregarObjeto(rs);
    }

    public List<Pauta> listarPorAta(int idAta) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionDAO.getInstance().getConnection();
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM pautas WHERE idAta=" + idAta + " ORDER BY ordem");

            List<Pauta> list = new ArrayList<Pauta>();

            while (rs.next()) {
                list.add(this.carregarObjeto(rs));
            }

            return list;
        } finally {
            ConnectionUtils.closeConnections(rs, stmt, conn);
        }
    }


    public int salvar(Pauta pauta) throws SQLException {
        boolean insert = (pauta.getIdPauta() == 0);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionDAO.getInstance().getConnection();

            if (insert) {
                stmt = conn.prepareStatement("INSERT INTO pautas(idAta, ordem, titulo, descricao) VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            } else {
                stmt = conn.prepareStatement("UPDATE pautas SET idAta=?, ordem=?, titulo=?, descricao=? WHERE idPauta=?");
            }

            stmt.setInt(1, pauta.getAta().getIdAta());
            stmt.setInt(2, pauta.getOrdem());
            stmt.setString(3, pauta.getTitulo());
            stmt.setString(4, pauta.getDescricao());

            if (!insert) {
                stmt.setInt(5, pauta.getIdPauta());
            }

            stmt.execute();

            if (insert) {
                rs = stmt.getGeneratedKeys();

                if (rs.next()) {
                    pauta.setIdPauta(rs.getInt(1));
                }
            }

            return pauta.getIdPauta();
        } finally {
            if ((rs != null) && !rs.isClosed())
                rs.close();
            if ((stmt != null) && !stmt.isClosed())
                stmt.close();
            if ((conn != null) && !conn.isClosed())
                conn.close();
        }
    }

    public void excluir(int id) throws SQLException {
        dao.excluir(id, "DELETE FROM pautas WHERE idPauta=");
    }

    private Pauta carregarObjeto(ResultSet rs) throws SQLException {
        Pauta pauta = new Pauta();

        pauta.setIdPauta(rs.getInt("idPauta"));
        pauta.getAta().setIdAta(rs.getInt("idAta"));
        pauta.setOrdem(rs.getInt("ordem"));
        pauta.setTitulo(rs.getString("titulo"));
        pauta.setDescricao(rs.getString("descricao"));

        return pauta;
    }

}
