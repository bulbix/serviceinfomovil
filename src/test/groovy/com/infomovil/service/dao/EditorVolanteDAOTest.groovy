package com.infomovil.service.dao

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration

import com.infomovil.service.ServiceInfomovilApplication;

import static org.junit.Assert.*;
import org.junit.Test;
import static com.infomovil.service.util.Util.*

@RunWith(SpringJUnit4ClassRunner)
@SpringApplicationConfiguration(classes = ServiceInfomovilApplication)
@WebAppConfiguration
class EditorVolanteDAOTest {
	

	@Autowired
	EditorVolanteDAO editorVolanteDAO
	
	@Test
	void testGetPaises(){
		println(editorVolanteDAO.getPaises("%"))
	}
	
	@Test
	void testCredenciales(){
		println(encrypt("docker6@mail.com;garbage1"))
	}
	
	
}
