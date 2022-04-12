package br.edu.utfpr.dv.sireata.dao;

import br.edu.utfpr.dv.sireata.util.ConnectionUtils;

import java.sql.*;

public class DefaultDAO {

    public ResultSet buscarPorId(int id, String sql ) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            conn = ConnectionDAO.getInstance().getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);

            rs = stmt.executeQuery();

            if(rs.next()){
                return rs;
            }else{
                return null;
            }
        }finally{
            ConnectionUtils.closeConnections(rs, stmt, conn);
        }
    }


    public void excluir(int id, String sql) throws SQLException{
        Connection conn = null;
        Statement stmt = null;

        try{
            conn = ConnectionDAO.getInstance().getConnection();
            stmt = conn.createStatement();

            stmt.execute(sql + String.valueOf(id));
        }finally{
            if((stmt != null) && !stmt.isClosed())
                stmt.close();
            if((conn != null) && !conn.isClosed())
                conn.close();
        }
    }
}
