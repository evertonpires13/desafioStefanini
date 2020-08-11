/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.java.br.com.desafio.stefanini.desafiostefanini.bean;

import br.com.desafio.stefanini.desafiostefanini.bean.NacionalidadeBean;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author everton
 */
@RunWith(MockitoJUnitRunner.class)
public class NacionalidadeBeanTest {

    @Mock
    private NacionalidadeBean nacionalidadeBean;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    
     @Test
    public void validarCamposSucesso() {

       

    }

    @Test
    public void validarCamposFracasso() {

        
    }

}
