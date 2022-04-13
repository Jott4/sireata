package br.edu.utfpr.dv.sireata.bo;

import br.edu.utfpr.dv.sireata.dao.AtaParticipanteDAO;
import br.edu.utfpr.dv.sireata.model.AtaParticipante;
import br.edu.utfpr.dv.sireata.util.LoggerUtils;

import java.util.List;

public class AtaParticipanteBO {

    public AtaParticipante buscarPorId(int id) throws Exception {
        try {
            AtaParticipanteDAO dao = new AtaParticipanteDAO();

            return dao.buscarPorId(id);
        } catch (Exception e) {
            LoggerUtils.LogAndExcept(e);
            return null;
        }
    }

    public List<AtaParticipante> listarPorAta(int idAta) throws Exception {
        try {
            AtaParticipanteDAO dao = new AtaParticipanteDAO();

            return dao.listarPorAta(idAta);
        } catch (Exception e) {
            LoggerUtils.LogAndExcept(e);
            return null;
        }
    }

    public boolean isValidUser(AtaParticipante participante) {
        return participante.getParticipante() != null || participante.getParticipante().getIdUsuario() != 0;
    }

    public boolean hasMotivoAusencia(AtaParticipante participante) {
        return !participante.isPresente() && participante.getMotivo().trim().isEmpty();
    }

    public void validarDados(AtaParticipante participante) throws Exception {
        if (this.isValidUser(participante)) {
            throw new Exception("Informe o usuário.");
        }
        if (this.hasMotivoAusencia(participante)) {
            throw new Exception("Informe o motivo da ausência.");
        }
        if (participante.isPresente()) {
            participante.setMotivo("");
        }
    }

    public boolean isValidAta(AtaParticipante participante) {
        return participante.getAta() != null || participante.getAta().getIdAta() != 0;
    }

    public int salvar(AtaParticipante participante) throws Exception {
        try {
            if (!isValidAta(participante)) {
                throw new Exception("Informe a ata.");
            }

            this.validarDados(participante);

            AtaParticipanteDAO dao = new AtaParticipanteDAO();

            return dao.salvar(participante);
        } catch (Exception e) {
            LoggerUtils.LogAndExcept(e);
            return 0;
        }
    }

    public void excluir(AtaParticipante participante) throws Exception {
        this.excluir(participante.getIdAtaParticipante());
    }

    public void excluir(int id) throws Exception {
        try {
            AtaParticipanteDAO dao = new AtaParticipanteDAO();

            dao.excluir(id);
        } catch (Exception e) {
            LoggerUtils.LogAndExcept(e);
        }
    }

}
