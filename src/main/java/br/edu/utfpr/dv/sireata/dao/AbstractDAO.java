package br.edu.utfpr.dv.sireata.dao;

import java.sql.SQLException;

public abstract class AbstractDAO<E> {
    public abstract E buscarPorId(int id) throws SQLException;

    public abstract int salvar(E obj) throws SQLException;
}