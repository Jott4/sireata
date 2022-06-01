package br.edu.utfpr.dv.sireata.bo;

import br.edu.utfpr.dv.sireata.dao.*;

public abstract class BOFactory {


    public static AbstractDAO<? extends Object> from(DataRetriever dataRetriever) {
        switch (dataRetriever) {
            case Anexo:
                return new AnexoDAO();
            case Ata:
                return new AtaDAO();
            case AtaParticipante:
                return new AtaParticipanteDAO();
            case Campus:
                return new CampusDAO();
            case Comentario:
                return new ComentarioDAO();
            case Departamento:
                return new DepartamentoDAO();
            case Orgao:
                return new OrgaoDAO();
            case Pauta:
                return new PautaDAO();
            case Usuario:
                return new UsuarioDAO();

        }

        return null;
    }

    public abstract AbstractDAO getCreatorDAO();

    public enum DataRetriever {
        Anexo,
        Ata,
        AtaParticipante,
        Campus,
        Comentario,
        Departamento,
        Orgao,
        Pauta,
        Usuario
    }

}
