/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.java.br.com.desafio.stefanini.desafiostefanini.bean;

import br.com.desafio.stefanini.desafiostefanini.util.Utilitario;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author everton
 */
@RunWith(MockitoJUnitRunner.class)
public class UtilitarioTest {

    @Test
    public void adicionarMascaraNula() {
        assertEquals(null, Utilitario.adicionarMascaraCPF(null));
    }

    @Test
    public void adicionarMascaraComValor() {
        assertEquals("606.479.510-70", Utilitario.adicionarMascaraCPF("60647951070"));

    }

    @Test
    public void removerMascaraNula() {
        assertEquals(null, Utilitario.removerMascaraCPF(null));
    }

    @Test
    public void removerMascaraComValor() {
        assertEquals("60647951070", Utilitario.removerMascaraCPF("606.479.510-70"));
    }

    @Test
    public void estados() {
        assertEquals(27, Utilitario.estados().size());
    }

    @Test
    public void validarCPFSucesso() {
        assertEquals(true, Utilitario.validCPF("60647951070"));
    }

    @Test
    public void validarCPFFracasso() {
        assertEquals(false, Utilitario.validCPF("60647951071"));
    }

    @Test
    public void validarEmailFracasso() {
        assertEquals(false, Utilitario.validarEmail("testetestando.com"));
    }

    @Test
    public void validarEmailSucesso() {
        assertEquals(true, Utilitario.validarEmail("teste@testando.com"));
    }

}
