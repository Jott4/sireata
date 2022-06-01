package br.edu.utfpr.dv.sireata.bo;

public abstract class BOFactory {


    public static AbstractDataRetriever from(DataRetriever dataRetriever) {
        

    }

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
