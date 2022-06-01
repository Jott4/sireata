package br.edu.utfpr.dv.sireata.bo;

import br.edu.utfpr.dv.sireata.dao.*;

public abstract class BOFactory {

    public abstract AbstractDAO getCreatorDAO();

}
