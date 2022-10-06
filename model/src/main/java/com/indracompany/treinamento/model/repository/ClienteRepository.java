package com.indracompany.treinamento.model.repository;


import com.indracompany.treinamento.model.entity.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends GenericCrudRepository<Cliente, Long>{


	public Optional<Cliente> findByCpf(String cpf);

	public List<Cliente> findByNomeContainingIgnoreCaseAndAtivoTrue(String nome);

	@Query("select c from Cliente c where upper(c.nome) like upper(:name) and ativo=true ")
	public List<Cliente> findByName(@Param("name") String name);


	@Query(value = "select * from clientes " +
			"where (upper(nome) like upper(:nome) ) and ativo=1",nativeQuery = true)
	public List<Cliente> findByNomeSqlNative(@Param("nome")String nome);


	/**
	 * ! O SQL e a linguagem nativa do do JPA, entao caso seja necessario podemos fazer uma query nativa usando:
	 * @Query("ALGUMA QUERY :parametro", nativeQuery = true)
	 *
	 * ! Ou podemos usar o JPQL que e um padrao aceito pelo JPA
	 * * A principal diferença e que ao fazer o select e referenciar alguma propriedade da entidade, temos que usar
	 * * O nome da Entidade e o nome da propriedade ou inves dos nomes disponiveis no banco
	 */

	/**
	 * A anotacoa @Transactional e usada para que caso em algum ponto do metodo alguma exception seja lançada
	 * A anotaçao vai automaticamente desfazer tudo que foi feito, isso e feito em operaçoes que precisam de atomicidade
	 * por exemplo uma transferencia entre duas contas bancarias, onde caso uma das partes de problema, e essencial
	 * que tudo seja desfeito para evitar incoerencias no banco.
	 */

}
