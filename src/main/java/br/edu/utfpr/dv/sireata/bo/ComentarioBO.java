package br.edu.utfpr.dv.sireata.bo;

import br.edu.utfpr.dv.sireata.dao.ComentarioDAO;
import br.edu.utfpr.dv.sireata.model.Comentario;
import br.edu.utfpr.dv.sireata.model.Comentario.SituacaoComentario;
import br.edu.utfpr.dv.sireata.util.LoggerUtils;

import java.util.List;

public class ComentarioBO {

    public Comentario buscarPorId(int id) throws Exception {
        try {
            ComentarioDAO dao = new ComentarioDAO();

            return dao.buscarPorId(id);
        } catch (Exception e) {
            LoggerUtils.LogAndExcept(e);
            return null;
        }
    }

    public Comentario buscarPorUsuario(int idUsuario, int idPauta) throws Exception {
        try {
            ComentarioDAO dao = new ComentarioDAO();

            return dao.buscarPorUsuario(idUsuario, idPauta);
        } catch (Exception e) {
            LoggerUtils.LogAndExcept(e);
            return null;
        }
    }

    public List<Comentario> listarPorPauta(int idPauta) throws Exception {
        try {
            ComentarioDAO dao = new ComentarioDAO();

            return dao.listarPorPauta(idPauta);
        } catch (Exception e) {
            LoggerUtils.LogAndExcept(e);
            return null;
        }
    }

    private boolean isEmptyComentario(Comentario comentario) {
        return comentario.getSituacao() == SituacaoComentario.RECUSADO && comentario.getComentarios().trim().isEmpty();
    }

    private boolean isInvalidComentario(Comentario comentario) {
        return comentario.getSituacaoComentarios() == SituacaoComentario.RECUSADO && comentario.getMotivo().trim().isEmpty();
    }


    public void validarDados(Comentario comentario) throws Exception {
        if (isEmptyComentario(comentario)) {
            throw new Exception("Informe o seu comentário.");
        }
        if (isInvalidComentario(comentario)) {
            throw new Exception("Informe o motivo da recusa.");
        }
    }


    public boolean isEmptyPauta(Comentario comentario) {
        return comentario.getPauta() == null || comentario.getPauta().getIdPauta() == 0;
    }

    public int salvar(Comentario comentario) throws Exception {
        try {
            if (isEmptyPauta(comentario)) {
                throw new Exception("Informe a pauta.");
            }

            this.validarDados(comentario);

            ComentarioDAO dao = new ComentarioDAO();

            return dao.salvar(comentario);
        } catch (Exception e) {
            LoggerUtils.LogAndExcept(e);
            return 0;
        }
    }

}
