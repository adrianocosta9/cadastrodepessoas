package view.pessoas.cadastro.mobile.com.br.cadastrodepessoa.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.util.Log;

@SuppressLint("SimpleDateFormat")
public class DateUtil {

	/**
     * Metodo utilizado para converte um objeto qualquer passado para Date
     * - Implementado (retorna Date a partir de um objeto String passado (data)) 
     *
     * @param data Object data, aceitando assim qualquer tipo de objeto
     * @param formato Formato a ser formatada a data
     * @return Object Date, data convertida em Date
     */
	public static Date retornaDate(Object data, String formato) {
		SimpleDateFormat sfd = new SimpleDateFormat(formato);

		try {
			if (data instanceof String) {
				return data == null ? null : sfd.parse((String) data);
			} else if (data instanceof Date){
				return (data == null ? null : sfd.parse(formataData(data, formato)));
			}else{
				Throwable e = new Throwable(AppConstants.ERRO_TIPO_OBJETO_NAO_IMPLEMENTADO);
				throw new Exception("Util : retornaDate ", e);
			}
		} catch (Exception ex) {
			Log.e(DateUtil.class.getName(), ex.getMessage());
			return null;
		}
		
	}
	
	 /**
     * Metodo utilizado para formata a data para um padrao qualquer escolhido
     * - Implementado (retorna String a partir de um object Date passado (data))
     * 
     * @param data Object data, aceitando assim qualquer tipo de objeto
     * @param formato Formato a ser formatada a data
     * @return Object String, data formatada
     */
	public static String formataData(Object data, String formato) {
		SimpleDateFormat sfd = new SimpleDateFormat(formato);
		try {
			if (data instanceof Date) {
				return data == null ? null : sfd.format(data);
			} else {
				Throwable e = new Throwable(AppConstants.ERRO_TIPO_OBJETO_NAO_IMPLEMENTADO);
				throw new Exception("Util : formataData ", e);
			}
		} catch (Exception ex) {
			Log.e(DateUtil.class.getName(), ex.getMessage());
			return null;
		}
	}
}
