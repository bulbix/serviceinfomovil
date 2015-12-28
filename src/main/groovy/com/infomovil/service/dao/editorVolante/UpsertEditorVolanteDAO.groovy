package com.infomovil.service.dao.editorVolante

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

import com.infomovil.service.model.editorVolante.VContacto
import com.infomovil.service.model.editorVolante.VUbicacion;

import static com.infomovil.service.util.Util.*

@Repository
class UpsertEditorVolanteDAO {
	
	@Autowired
	protected JdbcTemplate jdbcTemplate
	
	@Autowired
	protected GetEditorVolanteDAO getEditorVolanteDAO;	
	
	def upsertContacto(VContacto contacto){
		SqlParameterSource inparams = new MapSqlParameterSource()
		.addValue("pa_OfferID", contacto.offerId)
		.addValue("pa_ContactoID", contacto.contactoId)
		.addValue("pa_Descripcion", contacto.descripcion)
		.addValue("pa_Contenido", contacto.contenido)
		.addValue("pa_CodigoPais", contacto.codigoPais)
		.addValue("pa_Servicios", contacto.services)
		.addValue("pa_TipoContacto", contacto.tipoContacto)
		.addValue("pa_Activo", contacto.activo)
		callStore(TipoErrorBD.PA_CODEERROR, jdbcTemplate,"PA_INFO_VOLANTE","SP_UPSERT_CONTACTO", inparams)
		[codeError:0]
	}
	
	def upsertUbicacion(VUbicacion ubicacion){
		SqlParameterSource inparams = new MapSqlParameterSource()
		.addValue("pa_LocID", ubicacion.locId)
		.addValue("pa_OfferID", ubicacion.offerId)
		.addValue("pa_Latitude", ubicacion.latitude)
		.addValue("pa_Longitude", ubicacion.longitude)
		.addValue("pa_Direccion", ubicacion.direccion)
		callStore(TipoErrorBD.PA_CODEERROR, jdbcTemplate,"PA_INFO_VOLANTE","SP_UPSERT_UBICACION", inparams)
		def mapGetUbicacion = getEditorVolanteDAO.getUbicacion(ubicacion.offerId)
		
		
		[locId:mapGetUbicacion.ubicacion[0].locId, codeError:0]
	}
	
}
