/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.desafio.stefanini.desafiostefanini.util;

/**
 *
 * @author everton
 */
public class Select {
    
    private String chave;
    private String descricao;
    
    public Select ( String chave,  String descricao){
        this.chave = chave;
        this.descricao=descricao;
    }

    /**
     * @return the chave
     */
    public String getChave() {
        return chave;
    }

    /**
     * @param chave the chave to set
     */
    public void setChave(String chave) {
        this.chave = chave;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}
