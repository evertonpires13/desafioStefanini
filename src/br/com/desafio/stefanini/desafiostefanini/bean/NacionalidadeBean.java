/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.desafio.stefanini.desafiostefanini.bean;

import br.com.desafio.stefanini.desafiostefanini.model.Nacionalidade;
import br.com.desafio.stefanini.desafiostefanini.repository.NacionalidadeRepository;
import br.com.desafio.stefanini.desafiostefanini.util.Utilitario;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author everton
 */
@Component
@ViewScoped
@ManagedBean(name = "nacionalidadeBean")
public class NacionalidadeBean extends GenericBean<NacionalidadeRepository, Nacionalidade> {

    /*------------------------------------------------------------------------*/
    @Autowired
    public NacionalidadeBean(NacionalidadeRepository modeloRepository) {

        super(modeloRepository);

    }

    /*------------------------------------------------------------------------*/
    @Override
    public void onFind() {

        if (!pesquisa.getDescricao().isBlank() || !pesquisa.getSigla().isEmpty()) {

            String sigla = getPesquisa().getSigla().isEmpty() ? "%" : "%" + getPesquisa().getSigla().replaceAll(" ", "%") + "%";
            String descricao = getPesquisa().getDescricao().isEmpty() ? "%" : "%" + getPesquisa().getDescricao().replaceAll(" ", "%") + "%";

            modeloList = modeloRepository.findByParametros(sigla, descricao);

            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('addObj').hide();");

        }
    }

    /*------------------------------------------------------------------------*/

    @Override
    public boolean validate() {

        boolean retorno = true;

        if (modelo.getSigla().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Utilitario.NOME_SISTEMA, "A <strong> sigla <strong>  precisa ser preenchida."));
            retorno = false;
        } else {
            if (modelo.getSigla().length() > 5) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Utilitario.NOME_SISTEMA, "A <strong> sigla <strong>  não pode conter mais que 05 (cinco) caracteres."));
                retorno = false;
            }
        }

        if (modelo.getDescricao().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Utilitario.NOME_SISTEMA, "A <strong> descrição <strong>  nprecisa ser preenchido."));
            retorno = false;
        }

        return retorno;
    }
     /*------------------------------------------------------------------------*/
}
