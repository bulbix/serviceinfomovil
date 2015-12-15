package com.infomovil.service.dao.editorVolante

import java.sql.ResultSet
import java.sql.SQLException
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.SqlOutParameter
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.SqlParameterSource
import org.springframework.stereotype.Repository;

import com.infomovil.service.model.editorVolante.VContacto
import com.infomovil.service.model.editorVolante.VUbicacion
import static com.infomovil.service.util.Util.*
import oracle.jdbc.OracleTypes

@Repository
class GetEditorVolanteDAO {
	
	@Autowired
	protected JdbcTemplate jdbcTemplate
	
	def getContacto(Long offerId){
		SqlParameterSource inparams = new MapSqlParameterSource()
		.addValue("pa_OfferID", offerId)
		
		SqlOutParameter  cursor = new SqlOutParameter("cur_Cursor",
				OracleTypes.CURSOR,new RowMapper<VContacto>(){
					@Override
					public VContacto mapRow(ResultSet rs, int rowNum) throws SQLException {
						VContacto contacto = new VContacto();
						contacto.contactoId = rs.getLong("CONTACTO_ID")
						contacto.offerId = rs.getLong("OFFER_ID")
						contacto.descripcion = rs.getString("DESCRIPCION")
						contacto.orderNaptr = rs.getInt("ORDER_NAPTR")
						contacto.preference = rs.getInt("PREFERENCE")
						contacto.contenido = rs.getString("CONTENIDO")
						contacto.codigoPais = rs.getString("CODIGO_PAIS")
						contacto.services = rs.getString("SERVICES")
						contacto.tipoContacto = rs.getString("TIPO_CONTACTO")
						contacto.activo = rs.getInt("ACTIVO")
						return contacto;
					}});
		
		Map<String,Object> out =  callStore(TipoErrorBD.PA_CODEERROR, jdbcTemplate,"PA_INFO_VOLANTE_PG2","SP_GET_CONTACTO", inparams, cursor)
		
		[codeError:0,contacto:(List<VContacto>)out.get("cur_Cursor")]
		
	}
	
	def getUbicacion(Long offerId){
		SqlParameterSource inparams = new MapSqlParameterSource()
		.addValue("pa_OfferID", offerId)
		
		SqlOutParameter  cursor = new SqlOutParameter("cur_Cursor",
				OracleTypes.CURSOR,new RowMapper<VUbicacion>(){
					@Override
					public VUbicacion mapRow(ResultSet rs, int rowNum) throws SQLException {
						VUbicacion ubicacion = new VUbicacion();
						ubicacion.locId = rs.getLong("LOC_ID")
						ubicacion.offerId = rs.getLong("OFFER_ID")
						ubicacion.latitude = rs.getFloat("LATITUDE")
						ubicacion.longitude = rs.getFloat("LONGITUDE")
						ubicacion.direccion = rs.getString("DIRECCION")
						return ubicacion;
					}});
		
		Map<String,Object> out =  callStore(TipoErrorBD.PA_CODEERROR, jdbcTemplate,"PA_INFO_VOLANTE_PG2","SP_GET_UBICACION", inparams, cursor)
		
		[codeError:0, ubicacion:(List<VUbicacion>)out.get("cur_Cursor")]
	}

}
