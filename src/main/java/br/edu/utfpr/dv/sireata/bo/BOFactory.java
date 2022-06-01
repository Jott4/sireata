package br.edu.utfpr.dv.sireata.bo;

import br.edu.utfpr.dv.sireata.dao.*;

public abstract class BOFactory {


    public static AbstractDAO<? extends Object> from(DataRetriever dataRetriever) {
        switch (dataRetriever) {
            case AnexoDAO:
                return new AnexoDAO();
            case AtaDAO:
                return new AtaDAO();
            case AtaParticipanteDAO:
                return new AtaParticipanteDAO();
            case CampusDAO:
                return new CampusDAO();
            case ComentarioDAO:
                return new ComentarioDAO();
            case DepartamentoDAO:
                return new DepartamentoDAO();
            case OrgaoDAO:
                return new OrgaoDAO();
            case PautaDAO:
                return new PautaDAO();
            case UsuarioDAO:
                return new UsuarioDAO();

        }

        return null;
    }

    public abstract AbstractDAO getCreatorDAO();

    public enum DataRetriever {
        AnexoDAO,
        AtaDAO,
        AtaParticipanteDAO,
        CampusDAO,
        ComentarioDAO,
        DepartamentoDAO,
        OrgaoDAO,
        PautaDAO,
        UsuarioDAO
    }

}
