package com.infomovil.service.util;

import java.security.Key;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.infomovil.service.dao.GetInfomovilDAO;

import java.security.*;


public class Util {

	private static final Logger log = Logger.getLogger(Util.class);

	public enum TipoErrorBD{ 
		
		PA_CODEERROR("pa_CodeError","pa_DescError"), PACODEERROR("paCodeError","paDescError"), 
		PA_MJECODERROR("pa_MjeCodeError","pa_MjeDescError"), PAMJECODERROR("paMjeCodeError","paMjeDescError");
	
		String code;
		String msg;
		
		private TipoErrorBD(String code, String msg){
			this.code = code;
			this.msg = msg;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}
	
	}
	

	public static Map<String,Object> callStore(TipoErrorBD tipoError, JdbcTemplate jdbcTemplate, String packageName, 
			String procedureName, SqlParameterSource in, SqlParameter... signOut ) throws InfomovilDataException {

		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate);        

		jdbcCall.withSchemaName("INFOMOVIL");
		jdbcCall.withCatalogName(packageName);
		jdbcCall.withProcedureName(procedureName);		
		jdbcCall.addDeclaredParameter(new SqlOutParameter(tipoError.getCode(), Types.INTEGER));
		jdbcCall.addDeclaredParameter(new SqlOutParameter(tipoError.getMsg(), Types.VARCHAR));				

		for(SqlParameter outParam : signOut){
			jdbcCall.addDeclaredParameter(outParam);
		}

		long startTime = System.currentTimeMillis();
		Map<String,Object> outParams =  jdbcCall.execute(in);		

		long endTime = System.currentTimeMillis();
		String tiempo = (endTime - startTime) + " MiliSegundos";

		log.info(String.format("LOGID-[%s]|Query ejecutado:{%s} %s",new Date().getTime(),jdbcCall.getCallString(), tiempo));		

		int codeError =  (Integer)outParams.get(tipoError.getCode());
		String msgError = (String)outParams.get(tipoError.getMsg());

		if(codeError != 0){		
			throw new InfomovilDataException(codeError, msgError);
		}

		return outParams;		
	}
	
	private static final String ALGO = "AES";
	private static final byte[] keyValue = new byte[] { 'S', '3', 'c', 'r',
			'E', 't', 'P', 'I', 'N', 'F', '0', 'M', 'I', 'V', '1', 'l' };

	public static String encrypt(String Data) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = c.doFinal(Data.getBytes());
		String encryptedValue = new String(new Base64().encode(encVal));
		return encryptedValue;
	}
	
	public static String decrypt(String encryptedData) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.DECRYPT_MODE, key);
		byte[] decordedValue = new Base64().decodeBase64(encryptedData);
		byte[] decValue = c.doFinal(decordedValue);
		String decryptedValue = new String(decValue);
		return decryptedValue;
	}
	
	private static Key generateKey() throws Exception {
		Key key = new SecretKeySpec(keyValue, ALGO);
		return key;
	}
	
	/***
	 * Credenciales desencriptadas
	 * @param hashUser
	 * @return
	 */
	public static String[] getCredenciales(String hashUser){
		try{
			String decode = decrypt(hashUser);
			String[] credentials = decode.split(";");
			return new String[]{credentials[0], encrypt(credentials[1])};
		}
		catch(Exception e){
			return null;
		}
	}
	
	public static Map<String,Object> okHashUser(String hashUser, GetInfomovilDAO getInfomovilDAO){
		try{
			String[] credentials = getCredenciales(hashUser);
			return getInfomovilDAO.getLoginConProductos(credentials[0], credentials[1]);
		}
		catch(Exception e){
			Map<String,Object> result = new HashMap<String,Object>();
			result.put("pa_DomainId", 0);
			return result;
		}
	}

}
