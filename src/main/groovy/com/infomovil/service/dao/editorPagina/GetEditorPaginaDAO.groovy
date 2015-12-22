package com.infomovil.service.dao.editorPagina

import com.infomovil.service.model.editorPagina.Contacto
import com.infomovil.service.model.editorPagina.KeywordData;
import com.infomovil.service.model.editorPagina.Ubicacion
import com.infomovil.service.model.editorVolante.VUbicacion
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Types;
import java.util.Map;
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
class GetEditorPaginaDAO {
	
	@Autowired
	protected JdbcTemplate jdbcTemplate
	
	def getUbicacion(String email){
		SqlParameterSource inparams = new MapSqlParameterSource()
		.addValue("paUsuario", email)
		
		SqlOutParameter  cursor = new SqlOutParameter("paCursor",
				OracleTypes.CURSOR,new RowMapper<Ubicacion>(){
					@Override
					public Ubicacion mapRow(ResultSet rs, int rowNum) throws SQLException {
						Ubicacion ubicacion = new Ubicacion();
						ubicacion.latitude = rs.getFloat("LATITUDE")
						ubicacion.longitude = rs.getFloat("LONGITUDE")
						return ubicacion;
					}});
		
		Map<String,Object> out =  callStore(TipoErrorBD.PACODEERROR, jdbcTemplate,"PQ_INFO_LOGIN","SP_GET_TEXTOS", inparams, cursor)
		
		[codeError:0, ubicacion:(List<Ubicacion>)out.get("paCursor")]
	}
	
	def getKeywordData(String email){
			SqlParameterSource inparams = new MapSqlParameterSource()
		.addValue("paUsuario", email)
		
		SqlOutParameter  cursor = new SqlOutParameter("paCursor",
				OracleTypes.CURSOR,new RowMapper<KeywordData>(){
					@Override
					public KeywordData mapRow(ResultSet rs, int rowNum) throws SQLException {
						KeywordData keywordData = new KeywordData();
						keywordData.idKeyword = rs.getLong("KEYDATA_ID")
						keywordData.keywordField = rs.getString("KEYWORD_FIELD")
						keywordData.keywordValue = rs.getString("KEYWORD_VALUE")
						keywordData.keywordPos = rs.getInt("KEYWORD_POS")
						keywordData.keywordType = rs.getString("TYPE_KEYWORD")
						return keywordData;
					}});
		
		Map<String,Object> out =  callStore(TipoErrorBD.PACODEERROR, jdbcTemplate,"PQ_INFO_LOGIN","SP_GET_KEYWORD", inparams, cursor)
		
		[codeError:0, keywordData:(List<KeywordData>)out.get("paCursor")]
	}
	
	Integer getPriord(Long idDominio) {
		
		SqlParameterSource inparams = new MapSqlParameterSource()
		.addValue("paDomainId", idDominio);

		Map<String,Object> out =
		callStore(TipoErrorBD.PACODEERROR, jdbcTemplate,"PQ_INFOCHECK_PG1","SP_CHECK_POSKWDATA", inparams, 
			new SqlOutParameter("paKeyWPos", Types.INTEGER));
		
		
		Integer result = (Integer)out.get("paKeyWPos");
		
		return result;
	}
	
	def getContacto(Long idDominio){
		SqlParameterSource inparams = new MapSqlParameterSource()
		.addValue("paDomainId", idDominio)
	
		SqlOutParameter  cursor = new SqlOutParameter("curRecorNaptr",
				OracleTypes.CURSOR,new RowMapper<Contacto>(){
					@Override
					public Contacto mapRow(ResultSet rs, int rowNum) throws SQLException {
						Contacto contacto = new Contacto(
							contactoId:rs.getLong("id_contacto"),
							longLabel:rs.getString("long_label"),
							regExp:rs.getString("reg_exp"),
							services:rs.getString("services"),
							subCategory:rs.getString("sub_category"),
							preference:rs.getInt("preference"),
							activo:rs.getInt("visible")
							
						)
						return contacto;
					}});
	
	Map<String,Object> out =  callStore(TipoErrorBD.PAMJECODERROR, jdbcTemplate,"PQ_INFOGET_PG2","SP_GETRECORNAPTR", inparams, cursor)
	
	[codeError:0, contacto:(List<Contacto>)out.get("curRecorNaptr")]
}
	
	

}
