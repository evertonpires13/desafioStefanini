/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.desafio.stefanini.desafiostefanini.bean;

import br.com.desafio.stefanini.desafiostefanini.model.GenericDomain;
import br.com.desafio.stefanini.desafiostefanini.util.Utilitario;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.MappedSuperclass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author everton
 */
@MappedSuperclass
public abstract class GenericBean<T1 extends JpaRepository, T2> implements Serializable {

    //  @Autowired
    protected T1 modeloRepository;

    protected T2 modelo;

    protected List<T2> modeloList;

    protected T2 pesquisa;

    /*------------------------------------------------------------------------*/
    @Autowired
    public GenericBean(T1 modeloRepository) {

        this.modeloRepository = modeloRepository;
        modelo = getInstanceOfbean();

        pesquisa = getInstanceOfbean();
        modeloList = modeloRepository.findAll();

    }

    /*------------------------------------------------------------------------*/
    private T2 getInstanceOfbean() {

        Type type = getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) type;
        try {
            return ((Class<T2>) paramType.getActualTypeArguments()[1]).newInstance();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }

    /*------------------------------------------------------------------------*/
    @Transactional(readOnly = false)
    public synchronized void onSave() {

        if (validate()) {
            Boolean registroNovo = false;
            if (((GenericDomain) modelo).getId() == null) {
                registroNovo = true;
                ((GenericDomain) modelo).setCadastroData(Calendar.getInstance());
            }
            ((GenericDomain) modelo).setAlteracaoData(Calendar.getInstance());

            try {

                T2 modeloSalvo = (T2) modeloRepository.saveAndFlush(modelo);
                if (modeloSalvo != null) {

                    if (registroNovo) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Utilitario.NOME_SISTEMA, "Registro adicionado com sucesso."));
                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, Utilitario.NOME_SISTEMA, "Registro atualizado com sucesso."));
                    }

                    modelo = getInstanceOfbean();
                    modeloList = modeloRepository.findAll();

                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Utilitario.NOME_SISTEMA, "Erro ao salvar <strong>Registro</strong>."));
                }

            } catch (org.hibernate.exception.ConstraintViolationException e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Utilitario.NOME_SISTEMA, "Sr(a) Usuário, este CPF já foi encontrado no sistema."));
            } catch (Exception e) {
                e.printStackTrace();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Utilitario.NOME_SISTEMA, "Sr(a) Usuário, encontramos um erro fatal de sistema. Não conseguimos processar sua solicitação. (<strong>" + e.toString() + "</strong>)."));
            }

            System.gc();
        }

    }

    /*------------------------------------------------------------------------*/
    public abstract void onFind();

    /*------------------------------------------------------------------------*/
    public abstract boolean validate();

    /*------------------------------------------------------------------------*/
    public void onNew() {

        try {

            modelo = getInstanceOfbean();

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Utilitario.NOME_SISTEMA, "Erro inesperado : <strong>" + e.toString() + "</strong> Inexistente."));
        }

    }

    /*------------------------------------------------------------------------*/
    /**
     * Metodo de exclusao
     *
     * @param id
     */
    public void onRemove() {

        try {
            if (modelo != null) {
                GenericDomain c = (GenericDomain) (T2) (getModeloRepository()).findOne(((GenericDomain) modelo).getId());
                if (c != null && c.getId() != null) {
                    ((JpaRepository) getModeloRepository()).delete(c);

                    onFind();

                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Utilitario.NOME_SISTEMA, "<strong>Registro Removido</strong>."));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Utilitario.NOME_SISTEMA, "<strong>Registro</strong> Inexistente."));
                }

            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Utilitario.NOME_SISTEMA, "Erro inesperado : <strong>" + e.toString() + "</strong> Inexistente."));
        }

    }

    /*------------------------------------------------------------------------*/
    public void onEdit(Long id) {

        if (id != null) {
            modelo = (T2) modeloRepository.findOne(id);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Utilitario.NOME_SISTEMA, "Identificador inexistente."));
        }

    }

    /*------------------------------------------------------------------------*/
    public void onDelete(Long id) {

        if (id != null) {
            T2 delete = (T2) modeloRepository.findOne(id);
            if (delete != null) {
                modeloRepository.delete(modeloRepository.findOne(id));
                modeloList = modeloRepository.findAll();
            }

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Utilitario.NOME_SISTEMA, "Identificador inexistente."));
        }

    }

    /*------------------------------------------------------------------------*/
    /**
     * @return the modeloRepository
     */
    public T1 getModeloRepository() {
        return modeloRepository;
    }

    /*------------------------------------------------------------------------*/
    /**
     * @param modeloRepository the modeloRepository to set
     */
    public void setModeloRepository(T1 modeloRepository) {
        this.modeloRepository = modeloRepository;
    }

    /*------------------------------------------------------------------------*/
    /**
     * @return the modelo
     */
    public T2 getModelo() {
        return modelo;
    }

    /*------------------------------------------------------------------------*/
    /**
     * @param modelo the modelo to set
     */
    public void setModelo(T2 modelo) {
        this.modelo = modelo;
    }

    /*------------------------------------------------------------------------*/
    /**
     * @return the modeloList
     */
    public List<T2> getModeloList() {
        return modeloList;
    }

    /*------------------------------------------------------------------------*/
    /**
     * @param modeloList the modeloList to set
     */
    public void setModeloList(List<T2> modeloList) {
        this.modeloList = modeloList;
    }

    /*------------------------------------------------------------------------*/
    /**
     * @return the pesquisa
     */
    public T2 getPesquisa() {
        return pesquisa;
    }

    /*------------------------------------------------------------------------*/
    /**
     * @param pesquisa the pesquisa to set
     */
    public void setPesquisa(T2 pesquisa) {
        this.pesquisa = pesquisa;
    }
    /*------------------------------------------------------------------------*/
}
