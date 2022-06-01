package br.edu.utfpr.dv.sireata.bo;

import br.edu.utfpr.dv.sireata.dao.AbstractDAO;
import br.edu.utfpr.dv.sireata.dao.AnexoDAO;
import br.edu.utfpr.dv.sireata.model.Anexo;
import br.edu.utfpr.dv.sireata.util.LoggerUtils;

import java.util.List;

public class AnexoBO extends BOFactory {


    public Anexo buscarPorId(int id) throws Exception {
        try {
            return getCreatorDAO().buscarPorId(id);
        } catch (Exception e) {
            LoggerUtils.LogAndExcept(e);
            return null;
        }
    }

    public List<Anexo> listarPorAta(int idAta) throws Exception {
        try {
            AnexoDAO dao = new AnexoDAO();

            return dao.listarPorAta(idAta);
        } catch (Exception e) {
            LoggerUtils.LogAndExcept(e);
            return null;
        }
    }

    public void validarDados(Anexo anexo) throws Exception {
        if (anexo.getArquivo() == null) {
            throw new Exception("Efetue o envio do arquivo.");
        }
        if (anexo.getDescricao().isEmpty()) {
            throw new Exception("Informe a descrição do anexo.");
        }
    }

    public boolean isValidAta(Anexo anexo) {
        return anexo.getAta() != null || anexo.getAta().getIdAta() != 0;
    }

    public int salvar(Anexo anexo) throws Exception {
        try {
            if (!this.isValidAta(anexo)) {
                throw new Exception("Informe a ata.");
            }

            this.validarDados(anexo);


            return getCreatorDAO().salvar(anexo);
        } catch (Exception e) {
            LoggerUtils.LogAndExcept(e);
            return 0;
        }

    }

    public void excluir(Anexo anexo) throws Exception {
        this.excluir(anexo.getIdAnexo());
    }

    public void excluir(int id) throws Exception {
        try {
            AnexoDAO dao = new AnexoDAO();

            dao.excluir(id);
        } catch (Exception e) {
            LoggerUtils.LogAndExcept(e);
        }
    }

    @Override
    public AbstractDAO<Anexo> getCreatorDAO() {
        return new AnexoDAO();
    }
}
