/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.desafio.stefanini.desafiostefanini.converter;

import br.com.desafio.stefanini.desafiostefanini.model.Nacionalidade;
import br.com.desafio.stefanini.desafiostefanini.repository.NacionalidadeRepository;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author everton
 */
@FacesConverter(value = "nacionalidadeConvert", forClass = Nacionalidade.class)
@ManagedBean(name = "nacionalidadeConverterBean")
public class NacionalidadeConvert implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {

        try {
            
            if (value != null && !value.isEmpty()) {
                return (Nacionalidade) uiComponent.getAttributes().get(value);
            }
            
        } catch (Exception e) {
            System.out.print("Erro : " + e.toString());
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {

        try {

            if (value instanceof Nacionalidade) {
                Nacionalidade entity = (Nacionalidade) value;
                if (entity != null && entity instanceof Nacionalidade && entity.getId() != null) {
                    //uiComponent.getAttributes().put(entity.getId().toString(), entity);
                    // return entity.getId().toString();
                    return entity.getId().toString();
                }
            }

        } catch (Exception e) {
            System.out.print("Erro : " + e.toString());
        }

        return "";
    }

}
