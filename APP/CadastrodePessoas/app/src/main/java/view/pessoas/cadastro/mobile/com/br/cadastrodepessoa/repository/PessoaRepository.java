package view.pessoas.cadastro.mobile.com.br.cadastrodepessoa.repository;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import android.util.Log;
import view.pessoas.cadastro.mobile.com.br.cadastrodepessoa.entity.Pessoa;
import view.pessoas.cadastro.mobile.com.br.cadastrodepessoa.helper.DatabaseHelper;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;

public class PessoaRepository {
	
	private Context ctx;
	private Dao<Pessoa,Integer> pessoaDAO;
	
	private static PessoaRepository instancia =null;
	
	public PessoaRepository(Context ctx) {
		super();
		this.ctx = ctx;
		pessoaDAO = DatabaseHelper.getInstancia(ctx).getPessoaDAO();
	}

	public List<Pessoa> naoDeveExistirPessoasIguais (String nome, String cpf){
		try {
			PreparedQuery<Pessoa> preparedQuery = pessoaDAO.queryBuilder()
					.orderBy("nome", true)
					.where().not().eq("inativo", true)
					.and().like("nome", nome)
					.or().like("cpf", cpf)
					.prepare();
			return pessoaDAO.query(preparedQuery);
		} catch (SQLException e) {
			Log.e(PessoaRepository.class.getName(), e.getMessage());
		}
		return null;
	}
	public int inserir(Pessoa pessoa){
		try {
			return pessoaDAO.createOrUpdate(pessoa).getNumLinesChanged();
		} catch (SQLException e) {
			Log.e(PessoaRepository.class.getName(), e.getMessage());
		}
		return 0;
	}
	
	public int delete(Pessoa pessoa) {
		pessoa.setInativo(true);
		return inserir(pessoa);
	}

	public List<Pessoa> getlistaPessoa(){
		try {
			PreparedQuery<Pessoa> preparedQuery = pessoaDAO.queryBuilder()
					.orderBy("nome", true)
					.where().not().eq("inativo",true)
					.prepare();
			
			return pessoaDAO.query(preparedQuery);
		} catch (SQLException e) {
			Log.e(PessoaRepository.class.getName(), e.getMessage());
		}
		return null;
	}
	
	public Pessoa buscaPorId(int id){
		try {
			return pessoaDAO.queryForId(id);
		} catch (SQLException e) {
			Log.e(PessoaRepository.class.getName(), e.getMessage());
		}		
		return null;
	}

	public List<Pessoa> getlistaPessoaByName(String nome){
		try {
			PreparedQuery<Pessoa> preparedQuery = pessoaDAO.queryBuilder()
					.orderBy("nome", true)
					.where().not().eq("inativo",true)
					.and().like("nome",nome)
					.prepare();

			return pessoaDAO.query(preparedQuery);
		} catch (SQLException e) {
			Log.e(PessoaRepository.class.getName(), e.getMessage());
		}
		return null;
	}

	public static PessoaRepository getInstancia(Context ctx){
		if (instancia == null) {
			return instancia = new PessoaRepository(ctx);
		}
		return instancia;			
	}

}
