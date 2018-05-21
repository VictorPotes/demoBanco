package co.edu.icesi.demobanco.config;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import co.edu.icesi.demobanco.dao.IClientesDAO;
import co.edu.icesi.demobanco.dao.ITiposDocumentosDAO;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration("/applicationContext.xml")

public class TestConfig {

	@Autowired

	private IClientesDAO clientesDAO;

	@Autowired

	private ITiposDocumentosDAO tiposDocumentosDAO;

	@Test

	public void test() {

		assertNotNull(clientesDAO);

		assertNotNull(tiposDocumentosDAO);

	}

}