package br.edu.utfpr.dv.sireata.bo;

import br.edu.utfpr.dv.sireata.dao.PautaDAO;
import br.edu.utfpr.dv.sireata.model.Pauta;
import br.edu.utfpr.dv.sireata.util.LoggerUtils;

import java.util.List;

public class PautaBO {

	public Pauta buscarPorId(int id) throws Exception {
		try {
			PautaDAO dao = new PautaDAO();

			return dao.buscarPorId(id);
		} catch (Exception e) {
			LoggerUtils.LogAndExcept(e);
			return null;
		}
	}

	public List<Pauta> listarPorAta(int idAta) throws Exception {
		try {
			PautaDAO dao = new PautaDAO();

			return dao.listarPorAta(idAta);
		} catch (Exception e) {
			LoggerUtils.LogAndExcept(e);
			return null;
		}
	}

	public void validarDados(Pauta pauta) throws Exception {
		if (pauta.getTitulo().isEmpty()) {
			throw new Exception("Informe o título da pauta.");
		}
	}

	private boolean isValidAta(Pauta pauta) {
		return pauta.getAta() == null || pauta.getAta().getIdAta() == 0;
	}

	public int salvar(Pauta pauta) throws Exception {
		try {
			if (!isValidAta(pauta)) {
				throw new Exception("Informe a ata.");
			}

			this.validarDados(pauta);

			PautaDAO dao = new PautaDAO();

			return dao.salvar(pauta);
		} catch (Exception e) {
			LoggerUtils.LogAndExcept(e);
			return 0;
		}
	}

	public void excluir(Pauta pauta) throws Exception {
		this.excluir(pauta.getIdPauta());
	}

	public void excluir(int id) throws Exception {
		try {
			PautaDAO dao = new PautaDAO();

			dao.excluir(id);
		} catch (Exception e) {
			LoggerUtils.LogAndExcept(e);

		}
	}

}
