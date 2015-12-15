package view.pessoas.cadastro.mobile.com.br.cadastrodepessoa.entity;

import java.io.Serializable;
import java.util.Date;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

public abstract class AppBaseEntity implements Serializable {

	@DatabaseField(columnName = "data_ult_sincronizacao", dataType=DataType.DATE)
	private Date dataUltSincronizacao;

	@DatabaseField(columnName = "tipo_sincronizacao", dataType=DataType.ENUM_STRING , width = 3)	
	private TipoSincronizacao tipoSincronizacao;
	
	@DatabaseField(columnName = "id_alternativo", dataType=DataType.STRING, width= 100)
	private String idAlternativo;

	public Date getDataUltSincronizacao() {
		return dataUltSincronizacao;
	}

	public void setDataUltSincronizacao(Date dataUltSincronizacao) {
		this.dataUltSincronizacao = dataUltSincronizacao;
	}

	public String getIdAlternativo() {
		return idAlternativo;
	}

	public void setIdAlternativo(String idAlternativo) {
		this.idAlternativo = idAlternativo;
	}

	public TipoSincronizacao getTipoSincronizacao() {
		return tipoSincronizacao;
	}

	public void setTipoSincronizacao(TipoSincronizacao tipoSincronizacao) {
		this.tipoSincronizacao = tipoSincronizacao;
	}
	
	
}
