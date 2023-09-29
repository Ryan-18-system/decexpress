package br.edu.ifpb.decexpress.utils.formatter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FormatarCampo {
    String mascara() default ""; // Atributo para especificar a máscara (e.g., "cpf" ou "telefone")
}
