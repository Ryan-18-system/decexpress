package br.edu.ifpb.decexpress.utils.formatter;

import java.lang.reflect.Field;
import java.text.ParseException;

public class FormatadorUtil {
    public static void formatarCampos(Object objeto) throws IllegalAccessException, ParseException {
        Class<?> classe = objeto.getClass();
        Field[] campos = classe.getDeclaredFields();

        for (Field campo : campos) {
            if (campo.isAnnotationPresent(FormatarCampo.class)) {
                FormatarCampo anotacao = campo.getAnnotation(FormatarCampo.class);
                campo.setAccessible(true);

                Object valorCampo = campo.get(objeto);
                if (valorCampo != null) {
                    String valorFormatado = formatarCampo(valorCampo, anotacao.mascara());
                    campo.set(objeto, valorFormatado);
                }
            }
        }
    }

    private static String formatarCampo(Object valor, String mascara) {
        if (valor == null || mascara == null || mascara.isEmpty()) {
            return valor.toString(); // Se não houver valor ou máscara, retorne o valor original
        }

        String valorFormatado = valor.toString();

        if ("cpf".equalsIgnoreCase(mascara)) {
            valorFormatado = formatarCPF(valorFormatado);
        } else if ("telefone".equalsIgnoreCase(mascara)) {
            valorFormatado = formatarTelefone(valorFormatado);
        } else if ("cep".equalsIgnoreCase(mascara)) {
            valorFormatado = formatarCEP(valorFormatado);
        } else if ("celular".equalsIgnoreCase(mascara)) {
            valorFormatado = formatarCelular(valorFormatado);
        }

        return valorFormatado;
    }

    public static String formatarCEP(String cep) {
        if (cep.length() == 8) {
            return cep.substring(0, 5) + "-" + cep.substring(5);
        }
        return cep;
    }

    public static String formatarCelular(String celular) {
        if (celular.length() == 11) {
            return "(" + celular.substring(0, 2) + ") " + celular.substring(2, 7) + "-" + celular.substring(7);
        } else if (celular.length() == 10) {
            return "(" + celular.substring(0, 2) + ") " + celular.substring(2, 6) + "-" + celular.substring(6);
        }
        return celular;
    }


    private static String formatarCPF(String cpf) {
        if (cpf.length() == 11) {
            return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9);
        }
        return cpf;
    }

    private static String formatarTelefone(String telefone) {
        if (telefone.length() == 11) {
            return "(" + telefone.substring(0, 2) + ") " + telefone.substring(2, 7) + "-" + telefone.substring(7);
        } else if (telefone.length() == 10) {
            return "(" + telefone.substring(0, 2) + ") " + telefone.substring(2, 6) + "-" + telefone.substring(6);
        }
        return telefone;
    }
}