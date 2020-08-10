/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.desafio.stefanini.desafiostefanini.bean;

import br.com.desafio.stefanini.desafiostefanini.model.Nacionalidade;
import br.com.desafio.stefanini.desafiostefanini.repository.NacionalidadeRepository;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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
}
