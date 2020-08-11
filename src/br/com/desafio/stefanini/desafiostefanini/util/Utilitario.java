/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.desafio.stefanini.desafiostefanini.util;

import br.com.caelum.stella.validation.CPFValidator;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author everton
 */
public class Utilitario {

    public static final String NOME_SISTEMA = " Desafio Stefanini";

    /*------------------------------------------------------------------------*/
    public static String removerMascaraCPF(String cpf) {

        if (cpf != null) {

            if (!cpf.isEmpty()) {

                return cpf.replaceAll("\\.", "").replaceAll("\\-", "");

            } else {
                return cpf;
            }

        } else {
            return null;
        }

    }

    /*------------------------------------------------------------------------*/
    public static String adicionarMascaraCPF(String cpf) {

        if (cpf != null) {

            if (cpf.length() == 11) {

                String s = cpf.substring(0, 3) + "."
                        + cpf.substring(3, 6) + "."
                        + cpf.substring(6, 9) + "-"
                        + cpf.substring(9, 11);

                return s;

            } else {
                return cpf;
            }

        } else {
            return null;
        }

    }

    /*------------------------------------------------------------------------*/

    public static final List<Select> estados() {

        List<Select> retorno = new ArrayList<>();

        retorno.add(new Select("AC", "Acre"));
        retorno.add(new Select("AL", "Alagoas"));
        retorno.add(new Select("AP", "Amapá"));
        retorno.add(new Select("AM", "Amazonas"));
        retorno.add(new Select("BA", "Bahia"));
        retorno.add(new Select("CE", "Ceará"));
        retorno.add(new Select("DF", "Distrito Federal"));
        retorno.add(new Select("ES", "Espírito Santo"));
        retorno.add(new Select("GO", "Goiás"));
        retorno.add(new Select("MA", "Maranhão"));
        retorno.add(new Select("MT", "Mato Grosso"));
        retorno.add(new Select("MS", "Mato Grosso do Sul"));
        retorno.add(new Select("MG", "Minas Gerais"));
        retorno.add(new Select("PA", "Pará"));
        retorno.add(new Select("PB", "Paraíba"));
        retorno.add(new Select("PR", "Paraná"));
        retorno.add(new Select("PE", "Pernambuco"));
        retorno.add(new Select("PI", "Piauí"));
        retorno.add(new Select("RJ", "Rio de Janeiro"));
        retorno.add(new Select("RN", "Rio Grande do Norte"));
        retorno.add(new Select("RS", "Rio Grande do Sul"));
        retorno.add(new Select("RO", "Rondônia"));
        retorno.add(new Select("RR", "Roraima"));
        retorno.add(new Select("SC", "Santa Catarina"));
        retorno.add(new Select("SP", "São Paulo"));
        retorno.add(new Select("SE", "Sergipe"));
        retorno.add(new Select("TO", "Tocantins"));

        return retorno;

    }

    /*------------------------------------------------------------------------*/
    public static final boolean validCPF(String cpf) {

        if (cpf == null) {
            return false;
        } else {

            CPFValidator cpfValidator = new CPFValidator();
            try {
                cpfValidator.assertValid(Utilitario.removerMascaraCPF(cpf));
                return true;
            } catch (Exception e) {

                return false;
            }
        }
        
    }

    /*------------------------------------------------------------------------*/
    public static final boolean validarEmail(String email) {
        
        if (email == null) {
            return false;
        } else {
            try {
                InternetAddress emailAddr = new InternetAddress(email);
                emailAddr.validate();
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        
    }
    /*------------------------------------------------------------------------*/

}
