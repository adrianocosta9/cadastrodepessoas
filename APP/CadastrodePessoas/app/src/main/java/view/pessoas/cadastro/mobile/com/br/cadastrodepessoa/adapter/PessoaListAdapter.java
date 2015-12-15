package view.pessoas.cadastro.mobile.com.br.cadastrodepessoa.adapter;


import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import view.pessoas.cadastro.mobile.com.br.cadastrodepessoa.R;
import view.pessoas.cadastro.mobile.com.br.cadastrodepessoa.entity.Pessoa;


public class PessoaListAdapter extends BaseAdapter {


	private List<Pessoa> lista;
	private LayoutInflater inflater; 
	
	public PessoaListAdapter(Context ctx, List<Pessoa> lista) {
		inflater = LayoutInflater.from(ctx);
		this.lista = lista;
	}
		
	public int getCount(){
		return this.lista != null ? this.lista.size() : 0 ;					
	}
	
	public Object getItem(int position){
		return this.lista.get(position);		
	}
	
	public long getItemId(int position){
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) { 
			convertView = inflater.inflate (R.layout.pessoa_list_item, parent, false);
		}
		
		//Busca o item
		Pessoa cliente = lista.get(position);
		
		//Seta o nome da pessoa
		((TextView) convertView.findViewById(R.id.txtNomeCliente)).setText(cliente.getNome());
	
		return convertView;
		
	}
}	