package br.com.piccash.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.piccash.api.entity.User;

@Repository("usuarioRepository")
public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value = "SELECT * FROM pic_user WHERE id = :id AND pic_usua_tipo = 'CLIENTE'", nativeQuery = true)
	Optional<User> findByClientId(@Param("id") Long id);

	@Query(value = "SELECT * FROM pic_user WHERE id = :id AND pic_usua_tipo = 'LOJISTA'", nativeQuery = true)
	Optional<User> findByShopkeeperId(@Param("id") Long id);

}
