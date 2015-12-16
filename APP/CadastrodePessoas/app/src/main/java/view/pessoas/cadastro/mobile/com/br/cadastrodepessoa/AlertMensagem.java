package view.pessoas.cadastro.mobile.com.br.cadastrodepessoa;

import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

public class AlertMensagem {

	@SuppressWarnings("deprecation")
	public static void mensagemAlerta(Context ctx, String titulo,
			String mensagem) {
		AlertDialog alertDialog = new AlertDialog.Builder(ctx).create();

		// Setting Dialog Title
		alertDialog.setTitle(titulo);

		// Setting Dialog Message
		alertDialog.setMessage(mensagem);

		// Setting OK Button
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});

		// Showing Alert Message
		alertDialog.show();
	}

	@SuppressWarnings("deprecation")
	public static void mensagemAlertaFinish(Context ctx, String titulo,
			String mensagem, final Activity view,
			final Map<String, Object> paramsIntent,
			final Map<String, Object> params) {
		AlertDialog alertDialog = new AlertDialog.Builder(ctx).create();

		// Setting Dialog Title
		alertDialog.setTitle(titulo);

		// Setting Dialog Message
		alertDialog.setMessage(mensagem);

		// Setting OK Button
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Context view_origem = null;
				Class view_destino = null;
				Intent intent = null;
				dialog.cancel();
				if (!(view instanceof PrincipalActivity))
					view.finish();
				for (Map.Entry<String, Object> entry : paramsIntent.entrySet()) {
					if (((String) entry.getKey()).equals("intent1"))
						view_origem = (Context) entry.getValue();
					else if (((String) entry.getKey()).equals("intent2"))
						view_destino = (Class) entry.getValue();
				}

				if (view_origem != null && view_destino != null) {
					intent = new Intent(view_origem, view_destino);
				}

				for (Map.Entry<String, Object> entry : params.entrySet()) {
					if (intent != null) {
						intent.putExtra(((String) entry.getKey()),
								((Integer) entry.getValue()));
					}
				}

				if (intent != null) {
					view.startActivity(intent);
				}

			}
		});

		// Showing Alert Message
		alertDialog.show();
	}
}
