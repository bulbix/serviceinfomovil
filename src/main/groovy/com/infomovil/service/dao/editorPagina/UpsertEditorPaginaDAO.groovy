package com.infomovil.service.dao.editorPagina

import com.infomovil.service.model.editorPagina.Contacto
import com.infomovil.service.model.editorPagina.KeywordData;
import com.infomovil.service.model.editorPagina.Ubicacion
import com.infomovil.service.model.editorVolante.VUbicacion
import java.sql.ResultSet
import java.sql.SQLException
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.SqlOutParameter
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.SqlParameterSource
import org.springframework.stereotype.Repository;
import static com.infomovil.service.util.Util.*
import oracle.jdbc.OracleTypes

@Repository
class UpsertEditorPaginaDAO {
	
	@Autowired
	protected JdbcTemplate jdbcTemplate
	
	def upsertUbicacion(Ubicacion ubicacion){
		SqlParameterSource inparams = new MapSqlParameterSource()
		.addValue("paDomainId", ubicacion.idDominio)
		.addValue("paLatitudeLoc", ubicacion.latitude)
		.addValue("paLongitudeLoc", ubicacion.longitude)
		
		callStore(TipoErrorBD.PACODEERROR, jdbcTemplate,"PQ_INFOSTORE_PG2","SP_STORELOCRECORD", inparams)
		[codeError:0]
	}
	
	def insertKeywordData(KeywordData keywordData){
		SqlParameterSource inparams = new MapSqlParameterSource()
		.addValue("paDomainId", keywordData.idDominio)
		.addValue("paKeywordField", keywordData.keywordField)
		.addValue("paKeywordValue", keywordData.keywordValue)
		.addValue("paKeywordPos", keywordData.keywordPos)
		
		callStore(TipoErrorBD.PACODEERROR, jdbcTemplate,"PQ_INFOSTORE_PG1","SP_STOREKEYWORDDATA", inparams)
		[codeError:0]
	}
	
	def updateKeywordData(KeywordData keywordData){
		SqlParameterSource inparams = new MapSqlParameterSource()
		.addValue("paKeywordId", keywordData.idKeyword)
		.addValue("paDomainId", keywordData.idDominio)
		.addValue("paKeywordField", keywordData.keywordField)
		.addValue("paKeywordValue", keywordData.keywordValue)
		.addValue("paKeywordPos", keywordData.keywordPos)
		
		callStore(TipoErrorBD.PACODEERROR, jdbcTemplate,"PQ_INFOUPDATE_PG1","SP_UPDATEKEYWORDDATA", inparams)
		[codeError:0]
	}
	
	def insertContacto(Contacto contacto){
		SqlParameterSource inparams = new MapSqlParameterSource()
		.addValue("paDomainId", contacto.idDominio)
		.addValue("paLongLabelNaptr", contacto.longLabel)
		.addValue("paRegExp", contacto.regExp)
		.addValue("paServicesNaptr", contacto.services)
		.addValue("paSubCategory", contacto.subCategory)
		.addValue("paVisible", contacto.activo)
		
		callStore(TipoErrorBD.PACODEERROR, jdbcTemplate,"PQ_INFOSTORE_PG2","SP_STORERECORDNAPTR", inparams)
		[codeError:0]
	}
	
	def updateContacto(Contacto contacto){
		SqlParameterSource inparams = new MapSqlParameterSource()
		.addValue("pa_Dominio", contacto.idDominio)
		.addValue("pa_IDContacto", contacto.contactoId)
		.addValue("pa_Descripcion", contacto.longLabel)
		.addValue("pa_Preferencia", contacto.preference)
		.addValue("pa_Contenido", contacto.regExp)
		.addValue("pa_Servicios", contacto.services)
		.addValue("pa_SubCategoria", contacto.subCategory)
		.addValue("pa_Activo", contacto.activo)
		
		callStore(TipoErrorBD.PA_CODEERROR, jdbcTemplate,"PA_INFO_IMAGENES","SP_ACTUALIZA_CONTACTO", inparams)
		[codeError:0]
	}
	
	def cambiaSubdominio(String email, String subdominio){
		SqlParameterSource inparams = new MapSqlParameterSource()
		.addValue("pa_UserEmail", email)
		.addValue("pa_NewSD", subdominio)
		
		callStore(TipoErrorBD.PA_CODEERROR, jdbcTemplate,"PA_INFO_UTIL","SP_CAMBIA_SD", inparams)
		[codeError:0]
		
	}
	

}
