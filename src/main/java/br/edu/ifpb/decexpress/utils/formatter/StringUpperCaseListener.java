package br.edu.ifpb.decexpress.utils.formatter;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StringUpperCaseListener {
    private Logger logger = Logger.getLogger(StringUpperCaseListener.class.getName());

    @PrePersist
    @PreUpdate
    /**
     * Deixa todas as strings fornecidas pelo usuário em maiusculas
     * @param object
     */
    public void formatStrings(Object object) {

        try {
            List<Field> declaredFields = new ArrayList<Field>();
            declaredFields.addAll(Arrays.asList(object.getClass().getDeclaredFields()));

            for (Field f : declaredFields) {
                if (f.getType().equals(String.class)) {
                    f.setAccessible(true);
                    if (f.get(object) != null) {
                        f.set(object, f.get(object).toString().toUpperCase());
                    }
                }
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Erro ao deixar String Maiuscula " + ex.getMessage());
        }
    }
}