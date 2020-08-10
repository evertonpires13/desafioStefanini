package br.unip.dsd.bean.validator;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Rule;
import org.junit.Test;

import static org.easymock.EasyMock.*;

import org.junit.rules.ExpectedException;

import br.unip.dsd.bean.validador.ValidadorDeSenha;
import br.unip.dsd.factory.MessageFactory;



public class ValidadorDeSenhaTest extends EasyMockSupport{
	
	private MessageFactory msg = createNiceMock(MessageFactory.class);

	@TestSubject
	private final ValidadorDeSenha validador = new ValidadorDeSenha(msg);

    @Rule
    public ExpectedException thrown= ExpectedException.none();
    
	@Mock
	private FacesContext context;
	@Mock
	private UIComponent uiComponent;

	@Test()
	public void testSenhaErrada() {
		String mensagem = "Mensgem";
		thrown.expect(ValidatorException.class);
		thrown.expectMessage(mensagem);
		String valor = "1234A";		
		expect(msg.getMessage("senhaInvalida")).andReturn(mensagem);
		replay(msg);
		validador.validate(context, uiComponent, valor);
	}
	
	@Test
	public void testSenhaCorreta() {
		String valor = "1234Aa";		
		validador.validate(context, uiComponent, valor);
	}
}