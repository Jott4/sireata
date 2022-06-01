package br.edu.utfpr.dv.sireata.bo;

import br.edu.utfpr.dv.sireata.dao.AbstractDAO;
import br.edu.utfpr.dv.sireata.dao.CampusDAO;
import br.edu.utfpr.dv.sireata.model.Campus;
import br.edu.utfpr.dv.sireata.util.LoggerUtils;

import java.util.List;

public class CampusBO extends BOFactory {

    public Campus buscarPorId(int id) throws Exception {
        try {
            return getCreatorDAO().buscarPorId(id);
        } catch (Exception e) {
            LoggerUtils.LogAndExcept(e);
            return null;
        }
    }

    public Campus buscarPorDepartamento(int idDepartamento) throws Exception {
        try {
            CampusDAO dao = new CampusDAO();

            return dao.buscarPorDepartamento(idDepartamento);
        } catch (Exception e) {
            LoggerUtils.LogAndExcept(e);
            return null;
        }
    }

    public List<Campus> listarTodos(boolean apenasAtivos) throws Exception {
        try {
            CampusDAO dao = new CampusDAO();

            return dao.listarTodos(apenasAtivos);
        } catch (Exception e) {
            LoggerUtils.LogAndExcept(e);
            return null;
        }
    }

    public List<Campus> listarParaCriacaoAta(int idUsuario) throws Exception {
        try {
            CampusDAO dao = new CampusDAO();

            return dao.listarParaCriacaoAta(idUsuario);
        } catch (Exception e) {
            LoggerUtils.LogAndExcept(e);
            return null;
        }
    }

    public List<Campus> listarParaConsultaAtas(int idUsuario) throws Exception {
        try {
            CampusDAO dao = new CampusDAO();

            return dao.listarParaConsultaAtas(idUsuario);
        } catch (Exception e) {
            LoggerUtils.LogAndExcept(e);
            return null;
        }
    }

    public int salvar(Campus campus) throws Exception {
        if (campus.getNome().isEmpty()) {
            throw new Exception("Informe o nome do câmpus.");
        }

        try {


            return getCreatorDAO().salvar(campus);
        } catch (Exception e) {
            LoggerUtils.LogAndExcept(e);
            return 0;
        }
    }

    @Override
    public AbstractDAO<Campus> getCreatorDAO() {
        return new CampusDAO();
    }
}
