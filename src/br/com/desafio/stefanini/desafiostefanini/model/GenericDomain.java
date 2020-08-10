/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.desafio.stefanini.desafiostefanini.model;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author everton
 */
@MappedSuperclass
public abstract class GenericDomain implements Serializable {
   
    @Column(name = "cadastroData")
    private Calendar cadastroData;
 
    @Column(name = "alteracaoData")
    private Calendar alteracaoData;
  
    /**
     * @return the id
     */
    public abstract Long getId();

   
    public abstract void setId(Long id);
 
    /**
     * @return the cadastroData
     */
    public Calendar getCadastroData() {
        return cadastroData;
    }

    /**
     * @param cadastroData the cadastroData to set
     */
    public void setCadastroData(Calendar cadastroData) {
        this.cadastroData = cadastroData;
    }

    /**
     * @return the alteracaoData
     */
    public Calendar getAlteracaoData() {
        return alteracaoData;
    }

    /**
     * @param alteracaoData the alteracaoData to set
     */
    public void setAlteracaoData(Calendar alteracaoData) {
        this.alteracaoData = alteracaoData;
    }
    
}
