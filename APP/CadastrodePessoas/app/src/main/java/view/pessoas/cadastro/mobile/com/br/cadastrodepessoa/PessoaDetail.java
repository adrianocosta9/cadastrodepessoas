package view.pessoas.cadastro.mobile.com.br.cadastrodepessoa;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import view.pessoas.cadastro.mobile.com.br.cadastrodepessoa.entity.Pessoa;
import view.pessoas.cadastro.mobile.com.br.cadastrodepessoa.entity.Sexo;
import view.pessoas.cadastro.mobile.com.br.cadastrodepessoa.repository.PessoaRepository;
import view.pessoas.cadastro.mobile.com.br.cadastrodepessoa.util.AppConstants;
import view.pessoas.cadastro.mobile.com.br.cadastrodepessoa.util.DateUtil;
import view.pessoas.cadastro.mobile.com.br.cadastrodepessoa.util.Utils;

public class PessoaDetail extends AppCompatActivity {

    private Pessoa pessoa;
    private Sexo sexo = null;
    private TextWatcher cpfMask;
    private TextWatcher dataMask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa_detail);

        cpfMask = Utils.insertMascara("###.###.###-##", ((EditText) findViewById(R.id.edtCpfPessoa)));
        ((EditText) findViewById(R.id.edtCpfPessoa)).addTextChangedListener(cpfMask);

        dataMask = Utils.insertMascara("##/##/####", ((EditText) findViewById(R.id.edtDataNascimentoPessoa)));
        ((EditText) findViewById(R.id.edtDataNascimentoPessoa)).addTextChangedListener(dataMask);

        try {
            pessoa = (Pessoa) PessoaRepository.getInstancia(getApplicationContext()).buscaPorId(getIntent().getIntExtra("pessoa_id",0));

            // Recupera pessoa se o pessoa tiver sido passado por uma activity anterior (editar pessoa).
            if (pessoa != null){

                ((EditText) findViewById(R.id.edtNomePessoa)).setText(pessoa.getNome());
                ((EditText) findViewById(R.id.edtCpfPessoa)).setText(pessoa.getCpf());
                ((EditText) findViewById(R.id.edtRgPessoa)).setText(pessoa.getRg());
                ((EditText) findViewById(R.id.edtDataNascimentoPessoa)).setText(pessoa.getDataNascimento() == null ? null : DateUtil.formataData(pessoa.getDataNascimento(), AppConstants.formatoBR));
                ((RadioButton) findViewById(R.id.rbMasculinoPessoa)).setChecked(pessoa.getSexo() == Sexo.M ? true : false);
                ((RadioButton) findViewById(R.id.rbFemininoPessoa)).setChecked(pessoa.getSexo() == Sexo.F ? true : false);
                ((EditText) findViewById(R.id.edtTelefonePessoa)).setText(pessoa.getContatoTelefone());
                ((EditText) findViewById(R.id.edtTelefoneCelularPessoa)).setText(pessoa.getContatoTelefoneCelular());
                ((EditText) findViewById(R.id.edtEmailPessoa)).setText(pessoa.getContatoEmail());
                ((EditText) findViewById(R.id.edtObservacaoPessoa)).setText(pessoa.getObservacao());
                ((EditText) findViewById(R.id.edtUfPessoa)).setText(pessoa.getUf());
                ((EditText) findViewById(R.id.edtMunicipioPessoa)).setText(pessoa.getCidade());
                ((EditText) findViewById(R.id.edtBairroPessoa)).setText(pessoa.getBairro());
                ((EditText) findViewById(R.id.edtLogradouroPessoa)).setText(pessoa.getLogradouro());
                ((EditText) findViewById(R.id.edtNumeroPessoa)).setText(pessoa.getNumero() != null ? Integer.toString(pessoa.getNumero()) : null);
                ((EditText) findViewById(R.id.edtComplementoPessoa)).setText(pessoa.getComplemento());
                ((EditText) findViewById(R.id.edtCepPessoa)).setText(pessoa.getCep());
                ((ImageButton) findViewById(R.id.imgRemover)).setVisibility(View.VISIBLE);
                sexo = pessoa.getSexo() == Sexo.M ? Sexo.M : Sexo.F;
            }else{
                ((ImageButton) findViewById(R.id.imgRemover)).setVisibility(View.INVISIBLE);
            }

            // volta para pagina anterior
            ((ImageButton) findViewById(R.id.imgAnterior)).setOnClickListener(new OnClickListener() {
                public void onClick(View arg0) {
                    Intent pessoaDetail;
                    if (getIntent().getIntExtra("pessoa_id",0) != 0 || getIntent().getStringExtra("lista_pessoa") != null){
                        pessoaDetail = new Intent(PessoaDetail.this, PessoaListView.class);
                        pessoaDetail.putExtra("pessoa_id", getIntent().getIntExtra("pessoa_id",0));
                    } else {
                        pessoaDetail = new Intent(PessoaDetail.this, PrincipalActivity.class);
                    }
                    startActivity(pessoaDetail);
                    finish();
                }
            });

            // salva a pessoa
            ((ImageButton) findViewById(R.id.imgSalvar)).setOnClickListener(new OnClickListener() {

                public void onClick(View arg0) {
                    salvaPessoa();
                }
            });

            // remove a pessoa
            ((ImageButton) findViewById(R.id.imgRemover)).setOnClickListener(new OnClickListener() {

                public void onClick(View arg0) {
                    removerPessoa();
                }
            });

        } catch (Exception e) {
            Log.e(PessoaDetail.class.getName(), e.getMessage());
            Intent principal = new Intent(PessoaDetail.this, Principal.class);
            startActivity(principal);
            finish();
        }
    }

    /**
     * Metodo responsavel por setar o valor do atributo pessoa.setSexo().
     * Toda vez que o RadioButton e selecionado e chamado este metodo neste atualizado
     * o valor do objeto sexo.
     * @param view
     */
    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.rbMasculinoPessoa:
                if (checked)
                    this.sexo = Sexo.M;
                break;
            case R.id.rbFemininoPessoa:
                if (checked)
                    this.sexo = Sexo.F;
                break;
        }
    }

    /**
     * Metodo responsavel pro remover uma pessoa do banco local do celular.
     * E feito um pergunta se realmente o usuario deseja remover a pessoa selecionada.
     */
    private void removerPessoa(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Atenção");
        builder.setMessage(getString(R.string.remover_pessoa_dialog));

        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                //Presiste a operacao no banco de dados local do celular e apos o usuario o direcionado  tela listar pessoas.
                PessoaRepository.getInstancia(PessoaDetail.this).delete(pessoa);
                Intent pessoaDetail = new Intent(PessoaDetail.this, PessoaListView.class);
                startActivity(pessoaDetail);
                finish();
                dialog.dismiss();
            }

        });

        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            //Continua a mesma tela sem fazer nada com a pessoa.
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }
    /*
    * Metodo responsavel por salvar ou alterar um pessoa no banco
    * local do celular, se o objeto pessoa estiver setado e feito um
    * update caso nao (for nulo) inserido um novo registro.
    */
    private void salvaPessoa(){

        Boolean alteraPessoa = pessoa == null ? false : true;
        Integer id = pessoa == null ? null : pessoa.getId();
        String idAlternativo = pessoa == null ? null : pessoa.getIdAlternativo();

        if (!Utils.checkCPF(Utils.unmask(((EditText) findViewById(R.id.edtCpfPessoa)).getText().toString()))){
            AlertMensagem.mensagemAlerta(PessoaDetail.this, "Atenção", getString(R.string.cpf_invalido));
            ((EditText) findViewById(R.id.edtCpfPessoa)).setFocusable(true);
            ((EditText) findViewById(R.id.edtCpfPessoa)).setText("");
        }
        else  if (PessoaRepository.getInstancia(this).naoDeveExistirPessoasIguais(
                    ((EditText) findViewById(R.id.edtNomePessoa)).getText().toString(),
                        Utils.unmask(((EditText) findViewById(R.id.edtCpfPessoa)).getText().toString())).size() == 0
                            || alteraPessoa){

            //Seta o objeto pessoa com os dados informados na activity.
            pessoa = new Pessoa();
            pessoa.setId(id);
            pessoa.setIdAlternativo(idAlternativo);
            pessoa.setNome(((EditText) findViewById(R.id.edtNomePessoa)).getText().toString());
            pessoa.setCpf(Utils.unmask(((EditText) findViewById(R.id.edtCpfPessoa)).getText().toString()));
            pessoa.setRg(((EditText) findViewById(R.id.edtRgPessoa)).getText().toString());
            pessoa.setDataNascimento(((EditText) findViewById(R.id.edtDataNascimentoPessoa)).getText().toString().equals("") ? null : DateUtil.retornaDate(((EditText) findViewById(R.id.edtDataNascimentoPessoa)).getText().toString(), AppConstants.formatoBR));
            pessoa.setSexo(sexo);
            pessoa.setContatoTelefone(((EditText) findViewById(R.id.edtTelefonePessoa)).getText().toString());
            pessoa.setContatoTelefoneCelular(((EditText) findViewById(R.id.edtTelefoneCelularPessoa)).getText().toString());
            pessoa.setContatoEmail(((EditText) findViewById(R.id.edtEmailPessoa)).getText().toString());
            pessoa.setObservacao(((EditText) findViewById(R.id.edtObservacaoPessoa)).getText().toString());
            pessoa.setUf(((EditText) findViewById(R.id.edtUfPessoa)).getText().toString());
            pessoa.setCidade(((EditText) findViewById(R.id.edtMunicipioPessoa)).getText().toString());
            pessoa.setBairro(((EditText) findViewById(R.id.edtBairroPessoa)).getText().toString());
            pessoa.setLogradouro(((EditText) findViewById(R.id.edtLogradouroPessoa)).getText().toString());
            pessoa.setNumero(((EditText) findViewById(R.id.edtNumeroPessoa)).getText().toString().equals("") ? null : Integer.parseInt(((EditText) findViewById(R.id.edtNumeroPessoa)).getText().toString()));
            pessoa.setComplemento(((EditText) findViewById(R.id.edtComplementoPessoa)).getText().toString());
            pessoa.setCep(((EditText) findViewById(R.id.edtCepPessoa)).getText().toString());
            pessoa.setInativo(false);

            //Presiste a operacao no banco de dados local do celular e apos o usuario o direcionado  tela listar pessoa.
            PessoaRepository.getInstancia(this).inserir(pessoa);

            Map<String,Object> paramsIntent = new HashMap<String,Object>();
            Map<String,Object> params = new HashMap<String,Object>();
            paramsIntent.put("intent1", PessoaDetail.this);
            paramsIntent.put("intent2", PessoaListView.class);
            AlertMensagem.mensagemAlertaFinish(PessoaDetail.this, "Atenção", alteraPessoa ? getString(R.string.atualizar_pessoa_sucess) : getString(R.string.adicionar_pessoa_sucess),PessoaDetail.this,paramsIntent,params);

        } else {
            AlertMensagem.mensagemAlerta(PessoaDetail.this, "Atenção", getString(R.string.adiconar_pessoa_erro));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onConfigurationChanged(final Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

}
