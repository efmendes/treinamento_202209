package com.indracompany.treinamento.model.repository;

import com.indracompany.treinamento.model.entity.Usuario;

public interface UsuarioRepository extends GenericCrudRepository<Usuario, Long> {

	Usuario findByNoLoginIgnoreCase(String user);



}
