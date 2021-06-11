package org.generation.blogPessoal.repository;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long>{

	public Optional<Postagem> findByTituloLike(String titulo);
	public List<Postagem> findAllByTexto(String texto);
	public List<Postagem> findAllByTituloContainingIgnoreCase (String titulo);

}