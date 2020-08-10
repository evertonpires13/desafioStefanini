/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.desafio.stefanini.desafiostefanini.repository;

import br.com.desafio.stefanini.desafiostefanini.model.Nacionalidade;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author everton
 */
public interface NacionalidadeRepository extends JpaRepository<Nacionalidade, Long> {

    @Query(value = "SELECT n "
            + "FROM Nacionalidade n "
            + "WHERE "
            + "	1=1 "
            + "	and (n.sigla like :sigla  ) "
            + "	and (n.descricao like :descricao  ) "
            + "ORDER BY n.descricao")
    public List<Nacionalidade> findByParametros(
            @Param("sigla") String sigla,
            @Param("descricao") String descricao
    );

}
