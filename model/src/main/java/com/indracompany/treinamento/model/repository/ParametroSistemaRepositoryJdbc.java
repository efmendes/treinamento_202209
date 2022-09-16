package com.indracompany.treinamento.model.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.indracompany.treinamento.model.entity.ParametroSistema;

@Repository
public class ParametroSistemaRepositoryJdbc {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Cacheable(value="paramSistemaCache", key="{#nomeParametro}")
	public ParametroSistema carregarPorNomeParametroJdbcTemplate(String nomeParametro) {
		StringBuilder query = new StringBuilder()
				.append("select * from parametro_sistema WHERE upper(nome_parametro) = upper(?) ");
		return jdbcTemplate.queryForObject(query.toString(), new ParametroSistemaRowMapper(), nomeParametro);
	}

	private class ParametroSistemaRowMapper implements RowMapper<ParametroSistema> {

		@Override
		public ParametroSistema mapRow(ResultSet rs, int rowNum) throws SQLException {

			ParametroSistema param = new ParametroSistema();
			param.setId(rs.getLong("id"));
			param.setNomeParametro(rs.getString("NOME_PARAMETRO"));
			param.setValorParametro(rs.getString("VALOR_PARAMETRO"));

			return param;

		}
	}

}
