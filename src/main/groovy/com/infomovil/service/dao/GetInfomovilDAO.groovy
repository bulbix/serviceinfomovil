package com.infomovil.service.dao

import com.infomovil.service.util.InfomovilDataException
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Types
import java.text.SimpleDateFormat
import java.util.Map

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.SqlOutParameter
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.SqlParameterSource
import org.springframework.stereotype.Repository
import oracle.jdbc.OracleTypes
import com.infomovil.service.model.*
import static com.infomovil.service.util.Util.*


@Repository
class GetInfomovilDAO {

	@Autowired
	protected JdbcTemplate jdbcTemplate

	Map<String,Object> getLoginConProductos(String email, String password) throws InfomovilDataException {

		SqlParameterSource inparams = new MapSqlParameterSource()
				.addValue("pa_UserName", email)
				.addValue("pa_PasswdUser", password)

		final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")

		SqlOutParameter cursor = new SqlOutParameter("cur_Cursor",
				OracleTypes.CURSOR, new RowMapper<ProductoUsuario>() {
					@Override
					public ProductoUsuario mapRow(ResultSet rs, int rowNum)
					throws SQLException {

						ProductoUsuario productoUsuario = new ProductoUsuario(
								email : rs.getString("EMAIL"),
								userId : rs.getLong("USERACCOUNT_ID"),
								productoId : rs.getLong("PRODUCTOID"),
								claveComercial : rs.getString("CLAVECOMERCIAL"),
								descripcion : rs.getString("DESCRIPCION"),
								abc : rs.getString("ABC"),
								fechaInicio : sdf.format(rs.getDate("FECHA_INICIO")),
								fechaFin : sdf.format(rs.getDate("FECHA_FIN")),
								referencia : rs.getString("REFERENCIA")
								)

						return productoUsuario
					}
				})

		def resultMap = callStore(TipoErrorBD.PA_MJECODERROR,
				jdbcTemplate, "PA_INFOGET_PG6",  "SP_GETRESPLOGIN", inparams, cursor,
				new SqlOutParameter("pa_DomainId", Types.BIGINT),
				new SqlOutParameter("pa_Campania", Types.VARCHAR),
				new SqlOutParameter("pa_Patrocinador", Types.VARCHAR),
				new SqlOutParameter("pa_Version", Types.VARCHAR))

		return resultMap
	}

}
