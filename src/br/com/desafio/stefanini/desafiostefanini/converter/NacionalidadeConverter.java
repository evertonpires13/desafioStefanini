/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.desafio.stefanini.desafiostefanini.converter;

import br.com.desafio.stefanini.desafiostefanini.repository.NacionalidadeRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author everton
 */
@Service
@Component
@Scope("request")
public class NacionalidadeConverter extends SpringRepositoryConverter<NacionalidadeRepository> {
    
}
