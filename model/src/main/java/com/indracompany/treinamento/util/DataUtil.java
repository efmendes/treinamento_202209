package com.indracompany.treinamento.util;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DataUtil {
	private static final String FORMATO_PADRAO = "MM/dd/yyyy HH:mm";

	public String dataFormatada(Date data) {
		Format formatter = new SimpleDateFormat(FORMATO_PADRAO);
		return formatter.format(data);
	}
}