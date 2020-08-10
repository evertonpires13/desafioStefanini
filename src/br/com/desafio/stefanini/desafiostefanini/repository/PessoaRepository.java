/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.desafio.stefanini.desafiostefanini.repository;


import br.com.desafio.stefanini.desafiostefanini.model.Pessoa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author everton
 */
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    
     @Query(value = "SELECT n "
            + "FROM Pessoa n "
            + "WHERE "
            + "	1=1 "
            + "	and (n.nome like :nome  ) "
            + "	and (n.email like :email  ) "
            + "ORDER BY n.nome")
    public List<Pessoa> findByParametros(
            @Param("nome") String nome,
            @Param("email") String email
    );
    
}
