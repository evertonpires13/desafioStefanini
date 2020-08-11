/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.desafio.stefanini.desafiostefanini.bean;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.desafio.stefanini.desafiostefanini.util.Utilitario;
import br.com.desafio.stefanini.desafiostefanini.model.Nacionalidade;
import br.com.desafio.stefanini.desafiostefanini.model.Pessoa;
import br.com.desafio.stefanini.desafiostefanini.repository.NacionalidadeRepository;
import br.com.desafio.stefanini.desafiostefanini.repository.PessoaRepository;
import java.util.Calendar;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.mail.internet.InternetAddress;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author everton
 */
@Component
@ManagedBean(name = "pessoaBean")
@Service
public class PessoaBean extends GenericBean<PessoaRepository, Pessoa> {

    private List<Nacionalidade> nacionalidades;
    private NacionalidadeRepository nacionalidadeRepository;

    /*------------------------------------------------------------------------*/
    @Autowired
    public PessoaBean(PessoaRepository modeloRepository, NacionalidadeRepository nacionalidadeRepository) {

        super(modeloRepository);

        this.nacionalidadeRepository = nacionalidadeRepository;

        nacionalidades = nacionalidadeRepository.findAll();

    }

    /*------------------------------------------------------------------------*/
    @Override
    public void onFind() {

        if (!pesquisa.getNome().isEmpty() || !pesquisa.getEmail().isEmpty()) {

            String nome = getPesquisa().getNome().isEmpty() ? "%" : "%" + getPesquisa().getNome().replaceAll(" ", "%") + "%";
            String email = getPesquisa().getEmail().isEmpty() ? "%" : "%" + getPesquisa().getEmail().replaceAll(" ", "%") + "%";

            modeloList = modeloRepository.findByParametros(nome, email);

            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('addObj').hide();");

        }

    }

    /*------------------------------------------------------------------------*/
    public void combo() {
        nacionalidades = nacionalidadeRepository.findAll();
    }

    /*------------------------------------------------------------------------*/
    @Override
    public boolean validate() {

        Boolean retorno = true;

        // Nome é obrigatorio
        if (modelo.getNome().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Utilitario.NOME_SISTEMA, "Nome é obrigatório."));
            retorno = false;
        }

        // Email só é validado se for preenchido
        if (!modelo.getEmail().isEmpty()) {
            try {
                InternetAddress emailAddr = new InternetAddress(modelo.getEmail());
                emailAddr.validate();
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Utilitario.NOME_SISTEMA, "E-Mail inválido."));
                retorno = false;
            }
        }

        // Nascimento Obrigatorio
        if (modelo.getNascimento() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Utilitario.NOME_SISTEMA, "Data de nascimento é obrigatório."));
            retorno = false;
        } else {
            Calendar agora = Calendar.getInstance();
            if (modelo.getNascimento().after(agora.getTime())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Utilitario.NOME_SISTEMA, "Esta criança ainda não nasceu."));
                retorno = false;

            }
        }

        // CPF Obrigatório
        if (modelo.getCpf().isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Utilitario.NOME_SISTEMA, "CPF Obrigatório."));
            retorno = false;
        } else {

            System.out.println(modelo.getCpf());
             System.out.println(modelo.getCpf());
              System.out.println(modelo.getCpf());
               System.out.println(modelo.getCpf());
                System.out.println(modelo.getCpf());
                 System.out.println(modelo.getCpf());
                 
                 
                  System.out.println(Utilitario.removerMascaraCPF(modelo.getCpf()));
                   System.out.println(Utilitario.removerMascaraCPF(modelo.getCpf()));
                    System.out.println(Utilitario.removerMascaraCPF(modelo.getCpf()));
                     System.out.println(Utilitario.removerMascaraCPF(modelo.getCpf())); System.out.println(Utilitario.removerMascaraCPF(modelo.getCpf()));
                      System.out.println(Utilitario.removerMascaraCPF(modelo.getCpf()));
                     
                 
                 
            CPFValidator cpfValidator = new CPFValidator();
            try {
                cpfValidator.assertValid(Utilitario.removerMascaraCPF(modelo.getCpf()));
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Utilitario.NOME_SISTEMA, "CPF inválido."));
                retorno = false;
            }
        }

        return retorno;

    }

    /*------------------------------------------------------------------------*/
    /**
     * @return the nacionalidades
     */
    public List<Nacionalidade> getNacionalidades() {
        return nacionalidades;
    }

    /*------------------------------------------------------------------------*/
    /**
     * @param nacionalidades the nacionalidades to set
     */
    public void setNacionalidades(List<Nacionalidade> nacionalidades) {
        this.nacionalidades = nacionalidades;
    }
    /*------------------------------------------------------------------------*/
}
