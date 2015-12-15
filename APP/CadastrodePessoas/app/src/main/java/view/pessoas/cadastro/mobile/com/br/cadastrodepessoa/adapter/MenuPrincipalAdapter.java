package view.pessoas.cadastro.mobile.com.br.cadastrodepessoa.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import view.pessoas.cadastro.mobile.com.br.cadastrodepessoa.R;

public class MenuPrincipalAdapter extends BaseAdapter {

	private Activity activity;

	String[] Main_menu;

	public MenuPrincipalAdapter(Activity act) {
		this.activity = act;
		Main_menu = act.getResources().getStringArray(R.array.menu_principal);
	}

	public int getCount() {
		return Main_menu.length;
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.menu_principal_item, null);
			holder = new ViewHolder();

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.txtText = (TextView) convertView.findViewById(R.id.txtText);

		holder.txtText.setText(Main_menu[position]);

		return convertView;
	}

	static class ViewHolder {
		TextView txtText;
	}

}