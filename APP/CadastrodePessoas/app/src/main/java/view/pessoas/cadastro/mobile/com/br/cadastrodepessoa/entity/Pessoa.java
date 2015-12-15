package view.pessoas.cadastro.mobile.com.br.cadastrodepessoa.entity;

import java.util.Date;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "pessoa")
public class Pessoa extends AppBaseEntity  {

	@DatabaseField(columnName = "id" ,generatedId = true)
	private Integer id;
	
	@DatabaseField(columnName = "nome", dataType=DataType.STRING, width = 80)
	private String nome;	
	
	@DatabaseField(columnName = "endereco_logradouro", dataType=DataType.STRING, width = 80)
	private String logradouro;
	
	@DatabaseField(columnName = "endereco_numero", width = 5)
	private Integer numero;
	
	@DatabaseField(columnName = "endereco_complemento", dataType=DataType.STRING, width = 25)
	private String complemento;
	
	@DatabaseField(columnName = "endereco_bairro", dataType=DataType.STRING, width = 60)
	private String bairro;
	
	@DatabaseField(columnName = "endereco_cep", dataType=DataType.STRING, width = 9)
	private String cep;
	
	@DatabaseField(columnName = "endereco_cidade", dataType=DataType.STRING, width = 40)
	private String cidade;
	
	@DatabaseField(columnName = "endereco_uf", dataType=DataType.STRING, width = 40)
	private String uf;
	
	@DatabaseField(columnName = "contato_telefone", dataType=DataType.STRING, width = 40)
	private String contatoTelefone;
	
	@DatabaseField(columnName = "contato_telefone_celular", dataType=DataType.STRING, width = 40)
	private String contatoTelefoneCelular;
	
	@DatabaseField(columnName = "contato_email", dataType=DataType.STRING, width = 40)
	private String contatoEmail;
	
	@DatabaseField(columnName = "data_nascimento", dataType=DataType.DATE)
	private Date dataNascimento;
	
	@DatabaseField(columnName = "sexo", dataType=DataType.ENUM_STRING , width = 1)
	private Sexo sexo;	
	
	@DatabaseField(columnName = "cpf", dataType=DataType.STRING, width = 15)
	private String cpf;
	
	@DatabaseField(columnName = "rg", dataType=DataType.STRING, width = 15)
	private String rg;
	
	@DatabaseField(columnName = "obervacao", dataType=DataType.STRING, width = 500)
	private String observacao;

	@DatabaseField(columnName = "sincronizar", dataType=DataType.BOOLEAN, width = 5)
	private boolean sincronizar = true;
	
	@DatabaseField(columnName = "inativo", dataType=DataType.BOOLEAN, width = 5)
	private boolean inativo = false;
	
	public Pessoa() {
	}
	
	public boolean isInativo() {
		return inativo;
	}

	public void setInativo(boolean inativo) {
		this.inativo = inativo;
	}

	public boolean isSincronizar() {
		return sincronizar;
	}

	public void setSincronizar(boolean sincronizar) {
		this.sincronizar = sincronizar;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getContatoTelefone() {
		return contatoTelefone;
	}

	public void setContatoTelefone(String contatoTelefone) {
		this.contatoTelefone = contatoTelefone;
	}

	public String getContatoTelefoneCelular() {
		return contatoTelefoneCelular;
	}

	public void setContatoTelefoneCelular(String contatoTelefoneCelular) {
		this.contatoTelefoneCelular = contatoTelefoneCelular;
	}

	public String getContatoEmail() {
		return contatoEmail;
	}

	public void setContatoEmail(String contatoEmail) {
		this.contatoEmail = contatoEmail;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Override
	public String toString() {
		return getNome();
	}

}
