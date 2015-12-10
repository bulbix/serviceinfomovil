package com.infomovil.service.dao


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.infomovil.service.ServiceInfomovilApplication

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration
import static com.infomovil.service.util.Util.*

@RunWith(SpringJUnit4ClassRunner)
@SpringApplicationConfiguration(classes = ServiceInfomovilApplication)
@WebAppConfiguration
class GetInfomovilDAOTest {

	@Autowired
	GetInfomovilDAO getInfomovilDAO
	
	@Test
	void testGetLoginConProductos(){
		println(getInfomovilDAO.getLoginConProductos("docker6@mail.com", encrypt("garbage1")))
	}
}
