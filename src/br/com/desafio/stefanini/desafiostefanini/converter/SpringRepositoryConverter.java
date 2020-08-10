/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.desafio.stefanini.desafiostefanini.converter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
 
/**
 *
 * @author everton
 */
public class SpringRepositoryConverter <R extends JpaRepository<?, Long>> implements Converter {

    @Autowired
    private R repository;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
 
        if (value == null || value.isEmpty()) {
            return null;
        }

        try {

            if (repository != null) {
                Long id = Long.parseLong(value);
                //System.out.println("ID : " + value);
                return repository.findOne(id);
            } else {
                //System.out.println("R nulo");
                return null;
            }

        } catch (NumberFormatException e) {
            //System.out.println("Erro na conversão : getAsObject : " + e.toString());
            return null;
        }

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        try {
              
            if (value != null) {

                if (!(value instanceof String)) {

                    Method getId = value.getClass().getMethod("getId");
                    Long val = (Long) getId.invoke(value);
                    return val.toString();

                    //return getId.invoke(value).toString();
                    //return val;
                } else {
                    return null;
                }

            } else {
                return null;
            }

        } catch (NullPointerException
                | NoSuchMethodException
                | SecurityException
                | IllegalArgumentException
                | IllegalAccessException
                | InvocationTargetException e) {
//            Logger.getLogger(SpringRepositoryConverter.class.getName()).log(Level.SEVERE, null, ex);
            //System.out.println("Erro na conversão : getAsString : " + e.toString());
            return null;

        }

    }
    
}
