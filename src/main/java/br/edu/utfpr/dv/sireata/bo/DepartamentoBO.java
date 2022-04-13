package br.edu.utfpr.dv.sireata.bo;

import br.edu.utfpr.dv.sireata.dao.DepartamentoDAO;
import br.edu.utfpr.dv.sireata.model.Departamento;
import br.edu.utfpr.dv.sireata.util.LoggerUtils;

import java.util.List;

public class DepartamentoBO {

    public Departamento buscarPorId(int id) throws Exception {
        try {
            DepartamentoDAO dao = new DepartamentoDAO();

            return dao.buscarPorId(id);
        } catch (Exception e) {
            LoggerUtils.LogAndExcept(e);
            return null;
        }
    }

    public Departamento buscarPorOrgao(int idOrgao) throws Exception {
        try {
            DepartamentoDAO dao = new DepartamentoDAO();

            return dao.buscarPorOrgao(idOrgao);
        } catch (Exception e) {
            LoggerUtils.LogAndExcept(e);
            return null;
        }
    }

    public List<Departamento> listarTodos(boolean apenasAtivos) throws Exception {
        try {
            DepartamentoDAO dao = new DepartamentoDAO();

            return dao.listarTodos(apenasAtivos);
        } catch (Exception e) {
            LoggerUtils.LogAndExcept(e);
            return null;
        }
    }

    public List<Departamento> listarPorCampus(int idCampus, boolean apenasAtivos) throws Exception {
        try {
            DepartamentoDAO dao = new DepartamentoDAO();

            return dao.listarPorCampus(idCampus, apenasAtivos);
        } catch (Exception e) {
            LoggerUtils.LogAndExcept(e);
            return null;
        }
    }

    public List<Departamento> listarParaCriacaoAta(int idCampus, int idUsuario) throws Exception {
        try {
            DepartamentoDAO dao = new DepartamentoDAO();

            return dao.listarParaCriacaoAta(idCampus, idUsuario);
        } catch (Exception e) {
            LoggerUtils.LogAndExcept(e);
            return null;
        }
    }

    public List<Departamento> listarParaConsultaAtas(int idCampus, int idUsuario) throws Exception {
        try {
            DepartamentoDAO dao = new DepartamentoDAO();

            return dao.listarParaConsultaAtas(idCampus, idUsuario);
        } catch (Exception e) {
            LoggerUtils.LogAndExcept(e);
            return null;
        }
    }

    public boolean isValidDepartamento(Departamento departamento) {
        return departamento.getCampus() != null || departamento.getCampus().getIdCampus() != 0;
    }

    public int salvar(Departamento departamento) throws Exception {
        if (!isValidDepartamento(departamento)) {
            throw new Exception("Informe o câmpus do departamento.");
        }
        if (departamento.getNome().isEmpty()) {
            throw new Exception("Informe o nome do departamento.");
        }

        try {
            DepartamentoDAO dao = new DepartamentoDAO();

            return dao.salvar(departamento);
        } catch (Exception e) {
            LoggerUtils.LogAndExcept(e);
            return 0;
        }
    }

}
