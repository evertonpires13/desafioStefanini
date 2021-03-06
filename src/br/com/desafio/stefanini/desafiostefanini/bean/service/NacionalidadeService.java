/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.desafio.stefanini.desafiostefanini.bean.service;

import br.com.desafio.stefanini.desafiostefanini.model.Nacionalidade;
import br.com.desafio.stefanini.desafiostefanini.repository.NacionalidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author everton
 */
@Service
public class NacionalidadeService {

    @Autowired
    private NacionalidadeRepository nacionalidadeRepository;

    public void save(Nacionalidade nacionalidade) {
        nacionalidadeRepository.save(nacionalidade);
    }

}
