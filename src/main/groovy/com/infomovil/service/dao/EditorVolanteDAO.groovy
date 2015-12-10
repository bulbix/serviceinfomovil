package com.infomovil.service.dao

import com.infomovil.service.model.Pais

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.SqlOutParameter
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.SqlParameterSource
import oracle.jdbc.OracleTypes
import java.sql.ResultSet
import java.sql.SQLException
import org.springframework.stereotype.Repository
import static com.infomovil.service.util.Util.*

@Repository
class EditorVolanteDAO {
	
	@Autowired
	protected JdbcTemplate jdbcTemplate
	
	List<Pais> getPaises(String busqueda){
		println("Invoking store getPaises...")
		
		SqlParameterSource inparams = new MapSqlParameterSource()
		.addValue("paBusqueda",  busqueda)
		
		SqlOutParameter  cursor = new SqlOutParameter("cur_Cursor",
			OracleTypes.CURSOR,new RowMapper<Pais>(){
				@Override
				public Pais mapRow(ResultSet rs, int rowNum) throws SQLException {
					Pais pais = new Pais();
					
					pais.paisid = rs.getString("PAISID")
					pais.paisnombre = rs.getString("PAISNOMBRE")
					pais.usuario = rs.getString("USUARIO")
					return pais;
				}});
	
		Map<String,Object> out = callStore(TipoErrorBD.PA_CODEERROR, jdbcTemplate,"PQ_INFO_PAIS","SP_CONSULTA_PAISES",
		inparams, cursor);
	
		return (List<Pais>)out.get("cur_Cursor")
	}

}
