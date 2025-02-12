package br.edu.utfpr.dv.sireata.dao;

import br.edu.utfpr.dv.sireata.model.Anexo;
import br.edu.utfpr.dv.sireata.util.ConnectionUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnexoDAO implements AbstractDAO<Anexo> {
    private final DefaultDAO dao = new DefaultDAO();

    public Anexo buscarPorId(int id) throws SQLException {
        ResultSet rs = dao.buscarPorId(id, "SELECT anexos.* FROM anexos " +
                "WHERE idAnexo = ?");
        return this.carregarObjeto(rs);
    }


    public List<Anexo> listarPorAta(int idAta) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionDAO.getInstance().getConnection();
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT anexos.* FROM anexos " +
                    "WHERE idAta=" + idAta + " ORDER BY anexos.ordem");

            List<Anexo> list = new ArrayList<Anexo>();

            while (rs.next()) {
                list.add(this.carregarObjeto(rs));
            }

            return list;
        } finally {
            ConnectionUtils.closeConnections(rs, stmt, conn);
        }
    }

    public int salvar(Anexo anexo) throws SQLException {
        boolean insert = (anexo.getIdAnexo() == 0);
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionDAO.getInstance().getConnection();

            if (insert) {
                stmt = conn.prepareStatement("INSERT INTO anexos(idAta, ordem, descricao, arquivo) VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            } else {
                stmt = conn.prepareStatement("UPDATE anexos SET idAta=?, ordem=?, descricao=?, arquivo=? WHERE idAnexo=?");
            }

            stmt.setInt(1, anexo.getAta().getIdAta());
            stmt.setInt(2, anexo.getOrdem());
            stmt.setString(3, anexo.getDescricao());
            stmt.setBytes(4, anexo.getArquivo());

            if (!insert) {
                stmt.setInt(5, anexo.getIdAnexo());
            }

            stmt.execute();

            if (insert) {
                rs = stmt.getGeneratedKeys();

                if (rs.next()) {
                    anexo.setIdAnexo(rs.getInt(1));
                }
            }

            return anexo.getIdAnexo();
        } finally {
            ConnectionUtils.closeConnections(rs, stmt, conn);
        }
    }
    
    public void excluir(int id) throws SQLException {
        dao.excluir(id, "DELETE FROM anexos WHERE idAnexo =");
    }

    private Anexo carregarObjeto(ResultSet rs) throws SQLException {
        Anexo anexo = new Anexo();

        anexo.setIdAnexo(rs.getInt("idAnexo"));
        anexo.getAta().setIdAta(rs.getInt("idAta"));
        anexo.setDescricao(rs.getString("descricao"));
        anexo.setOrdem(rs.getInt("ordem"));
        anexo.setArquivo(rs.getBytes("arquivo"));

        return anexo;
    }

}
