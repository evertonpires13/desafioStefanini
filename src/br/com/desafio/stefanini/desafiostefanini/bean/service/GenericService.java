/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.desafio.stefanini.desafiostefanini.bean.service;

import br.com.desafio.stefanini.desafiostefanini.model.GenericDomain;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author everton
 * @param <T>
 * @param <T2>
 */
public class GenericService<T extends JpaRepository, T2 extends GenericDomain> {

    @Autowired
    private T modeloRepository;

    public void save(T2 modelo) {
        modeloRepository.save(modelo);
    }

    public void delete(T2 modelo) {
        modeloRepository.delete(modelo);
    }

    public List<T2> list() {
        return modeloRepository.findAll();
    }

    public T2 findById(Long id) {
        System.out.println( "testando");
         System.out.println( "testandowww" + modeloRepository);
         
        T2 tt =  (T2) modeloRepository.findOne(id);
        System.out.println( "testando" + tt);
        return tt;
    }
}
