package view.pessoas.cadastro.mobile.com.br.cadastrodepessoa.helper;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import view.pessoas.cadastro.mobile.com.br.cadastrodepessoa.entity.Pessoa;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper{

	//Nome da base de dados
	private static final String DATABASE_NAME = "cadastroPessoa.db";

	//versao da base de dados
	private static final int VERSAO = 1;
	
	//INSTANCIA UNICA A SER RETORNADA
	private static DatabaseHelper instancia = null;
	
	//objeto Dao da tabela 
	private Dao<Pessoa, Integer> pessoaDAO = null;

	//construtor da classe
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, VERSAO);
	}

	//evendo de criacao da classe implementa a criacao das tabelas
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connS) {
		try {
			TableUtils.createTable(connS, Pessoa.class);
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), e.getMessage());
		}
	}

	//Metodo de atualizacao da tabela, executado quando muda a versao do BD
	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connS, int oldV, int newV) {
		try {
			TableUtils.dropTable(connS, Pessoa.class, true);
			onCreate(db, connS);
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), e.getMessage());
		}
	}	
	
	//devolvendo uma unica instancia da classe
	public static DatabaseHelper getInstancia(Context context){
		if (instancia == null){
			return instancia = new DatabaseHelper(context.getApplicationContext());
			}		
		return instancia;
				
	}
	
	public Dao<Pessoa,Integer> getPessoaDAO() {
		if (pessoaDAO == null){
			try {
				return pessoaDAO = getDao(Pessoa.class);
			} catch (SQLException e) {
				Log.e(DatabaseHelper.class.getName(), e.getMessage());
			}
		}
		return pessoaDAO;
	}

}
