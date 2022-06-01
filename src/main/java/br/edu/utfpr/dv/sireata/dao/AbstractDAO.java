package br.edu.utfpr.dv.sireata.dao;

import java.sql.SQLException;

public interface  AbstractDAO<E> {
    E buscarPorId(int id) throws SQLException;

     int salvar(E obj) throws SQLException;
}