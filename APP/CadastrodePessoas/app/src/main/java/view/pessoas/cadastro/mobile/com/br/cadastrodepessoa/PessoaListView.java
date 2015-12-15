package view.pessoas.cadastro.mobile.com.br.cadastrodepessoa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import view.pessoas.cadastro.mobile.com.br.cadastrodepessoa.adapter.PessoaListAdapter;
import view.pessoas.cadastro.mobile.com.br.cadastrodepessoa.entity.Pessoa;
import view.pessoas.cadastro.mobile.com.br.cadastrodepessoa.repository.PessoaRepository;

public class PessoaListView extends AppCompatActivity {

    private ListView pessoaListView;
    private PessoaListAdapter pessoaListAdapter;
    private List<Pessoa> listaPessoa;
    private String palavraChave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa_list_view);

        try {
            // recupera os dados
            listaPessoa = (ArrayList<Pessoa>) PessoaRepository.getInstancia(getApplicationContext()).getlistaPessoa();

            // cria o adapter
            pessoaListAdapter = new PessoaListAdapter(this,listaPessoa);

            // conectando os objetos da view
            pessoaListView = (ListView) findViewById(R.id.pessoaList);
            pessoaListView.setVisibility(View.VISIBLE);
            pessoaListView.setAdapter(pessoaListAdapter);


            // pesquisa pessoa
            ((ImageButton) findViewById(R.id.btnPesquisar)).setOnClickListener(new OnClickListener() {

                public void onClick(View arg0) {
                    palavraChave = "%" + ((EditText) findViewById(R.id.edtPesquisar)).getText().toString() + "%";
                    pessoaListView.invalidateViews();

                    // recupera os dado
                    listaPessoa = (ArrayList<Pessoa>)PessoaRepository.getInstancia(getApplicationContext()).getlistaPessoaByName(palavraChave);

                    // cria o adapter
                    pessoaListAdapter = new PessoaListAdapter(getApplicationContext(), listaPessoa);

                    pessoaListView.setVisibility(View.VISIBLE);
                    pessoaListView.setAdapter(pessoaListAdapter);
                }
            });

            // clique no pessoa
            pessoaListView.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                        long arg3) {
                    Intent pessoaListView;
                    pessoaListView = new Intent(PessoaListView.this, PessoaDetail.class);
                    pessoaListView.putExtra("pessoa_id", listaPessoa.get(position).getId());
                    startActivity(pessoaListView);

                }
            });

            // volta pagina inicial
            ((ImageButton) findViewById(R.id.imgHome)).setOnClickListener(new OnClickListener() {
                public void onClick(View arg0) {
                    Intent principal = new Intent(PessoaListView.this, PrincipalActivity.class);
                    startActivity(principal);
                    finish();
                }
            });

            // volta para pagina inicial
            ((ImageButton) findViewById(R.id.imgAnterior)).setOnClickListener(new OnClickListener() {
                public void onClick(View arg0) {
                    Intent pessoaListView;
                    if (getIntent().getIntExtra("pessoa_id",0) != 0){
                        pessoaListView = new Intent(PessoaListView.this, PessoaDetail.class);
                        pessoaListView.putExtra("pessoa_id", getIntent().getIntExtra("pessoa_id",0));
                    }
                    else
                        pessoaListView = new Intent(PessoaListView.this, PrincipalActivity.class);
                    startActivity(pessoaListView);
                    finish();
                }
            });

            // adicionar pessoa
            ((ImageButton) findViewById(R.id.imgAddPessoa)).setOnClickListener(new OnClickListener() {
                public void onClick(View arg0) {
                    Intent pessoaDetail = new Intent(PessoaListView.this, PessoaDetail.class);
                    pessoaDetail.putExtra("lista_pessoa", "1");
                    startActivity(pessoaDetail);
                }
            });

        } catch (Exception e) {
            Log.e(PessoaListView.class.getName(), e.getMessage());
            Intent principal = new Intent(PessoaListView.this, Principal.class);
            startActivity(principal);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }


}
