package br.edu.utfpr.dv.sireata.bo;

import br.edu.utfpr.dv.sireata.dao.AbstractDAO;
import br.edu.utfpr.dv.sireata.dao.OrgaoDAO;
import br.edu.utfpr.dv.sireata.model.Orgao;
import br.edu.utfpr.dv.sireata.model.OrgaoMembro;
import br.edu.utfpr.dv.sireata.model.Usuario;
import br.edu.utfpr.dv.sireata.util.LoggerUtils;

import java.util.List;

public class OrgaoBO extends BOFactory {

    public Orgao buscarPorId(int id) throws Exception {
        try {
            return getCreatorDAO().buscarPorId(id);
        } catch (Exception e) {
            LoggerUtils.LogAndExcept(e);
            return null;
        }
    }

    public List<Orgao> listarTodos(boolean apenasAtivos) throws Exception {
        try {
            OrgaoDAO dao = new OrgaoDAO();

            return dao.listarTodos(apenasAtivos);
        } catch (Exception e) {
            LoggerUtils.LogAndExcept(e);
            return null;
        }
    }

    public List<Orgao> listarPorDepartamento(int idDepartamento) throws Exception {
        try {
            OrgaoDAO dao = new OrgaoDAO();

            return dao.listarPorDepartamento(idDepartamento);
        } catch (Exception e) {
            LoggerUtils.LogAndExcept(e);
            return null;
        }
    }

    public List<Orgao> listarPorCampus(int idCampus) throws Exception {
        try {
            OrgaoDAO dao = new OrgaoDAO();

            return dao.listarPorCampus(idCampus);
        } catch (Exception e) {
            LoggerUtils.LogAndExcept(e);
            return null;
        }
    }

    public List<Orgao> listarParaCriacaoAta(int idDepartamento, int idUsuario) throws Exception {
        try {
            OrgaoDAO dao = new OrgaoDAO();

            return dao.listarParaCriacaoAta(idDepartamento, idUsuario);
        } catch (Exception e) {
            LoggerUtils.LogAndExcept(e);
            return null;
        }
    }

    public List<Orgao> listarParaConsultaAtas(int idDepartamento, int idUsuario) throws Exception {
        try {
            OrgaoDAO dao = new OrgaoDAO();

            return dao.listarParaConsultaAtas(idDepartamento, idUsuario);
        } catch (Exception e) {
            LoggerUtils.LogAndExcept(e);
            return null;
        }
    }

    public List<Orgao> listar(int idCampus, int idDepartamento) throws Exception {
        if (idDepartamento > 0) {
            return this.listarPorDepartamento(idDepartamento);
        }
        if (idCampus > 0) {
            return this.listarPorCampus(idCampus);
        }

        return this.listarTodos(true);

    }

    public Usuario buscarPresidente(int idOrgao) throws Exception {
        try {
            OrgaoDAO dao = new OrgaoDAO();

            return dao.buscarPresidente(idOrgao);
        } catch (Exception e) {
            LoggerUtils.LogAndExcept(e);
            return null;
        }
    }

    public Usuario buscarSecretario(int idOrgao) throws Exception {
        try {
            OrgaoDAO dao = new OrgaoDAO();

            return dao.buscarSecretario(idOrgao);
        } catch (Exception e) {
            LoggerUtils.LogAndExcept(e);
            return null;
        }
    }

    public boolean isMembro(int idOrgao, int idUsuario) throws Exception {
        try {
            OrgaoDAO dao = new OrgaoDAO();

            return dao.isMembro(idOrgao, idUsuario);
        } catch (Exception e) {
            LoggerUtils.LogAndExcept(e);
            return false;
        }
    }

    public boolean isValidDepartamento(Orgao orgao) {
        return orgao.getDepartamento() != null || orgao.getDepartamento().getIdDepartamento() != 0;
    }

    public boolean isValidPresidente(Orgao orgao) {
        return orgao.getPresidente() != null || orgao.getPresidente().getIdUsuario() != 0;
    }

    public boolean isValidSecretario(Orgao orgao) {
        return orgao.getSecretario() != null || orgao.getSecretario().getIdUsuario() != 0;
    }


    public int salvar(Orgao orgao) throws Exception {
        if (orgao.getNome().isEmpty()) {
            throw new Exception("Informe o nome do câmpus.");
        }
        if (!isValidDepartamento(orgao)) {
            throw new Exception("Selecione o departamento.");
        }
        if (!isValidPresidente(orgao)) {
            throw new Exception("Selecione o presidente.");
        }
        if (!isValidSecretario(orgao)) {
            throw new Exception("Selecione o secretário");
        }

        boolean encontrou = false;

        for (OrgaoMembro u : orgao.getMembros()) {
            if (u.getUsuario().getIdUsuario() == orgao.getPresidente().getIdUsuario()) {
                encontrou = true;
                break;
            }
        }
        if (!encontrou) {
            OrgaoMembro membro = new OrgaoMembro();
            membro.setUsuario(orgao.getPresidente());
            membro.setDesignacao("coordenador");
            orgao.getMembros().add(membro);
        }

        try {
            return getCreatorDAO().salvar(orgao);
        } catch (Exception e) {
            LoggerUtils.LogAndExcept(e);
            return 0;
        }
    }

    @Override
    public AbstractDAO<Orgao> getCreatorDAO() {
        return new OrgaoDAO();
    }
}
