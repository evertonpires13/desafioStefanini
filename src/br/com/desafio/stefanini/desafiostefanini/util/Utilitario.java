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

}
