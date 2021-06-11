package org.generation.blogPessoal.service;

import java.util.List;

import org.generation.blogPessoal.model.Postagem;
import org.generation.blogPessoal.repository.PostagemRepository;
import org.generation.blogPessoal.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PostagemService {

	private @Autowired PostagemRepository repository;

	private @Autowired TemaRepository temaRepository;

	/**
	 * Metodo para buscar todas as postagens
	 * 
	 * @return ResponseEntity com o status HTTP e uma lista com todas as postagens
	 * 
	 * @author Luciano
	 */
	public ResponseEntity<List<Postagem>> findAll() {
		List<Postagem> listaDePostagem = repository.findAll();
		if (listaDePostagem.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listaDePostagem);
		}
	}

	/**
	 * Metodo para buscar uma postagem por ID
	 * 
	 * @param id
	 * @return ResponseEntity com o status HTTP e a postagem
	 */
	public ResponseEntity<Postagem> findById(Long id) {
		return repository.findById(id).map(resp -> ResponseEntity.status(200).body(resp))
				.orElse(ResponseEntity.status(404).build());
	}

	/**
	 * Metodo para buscar postagens pelo titulo
	 * 
	 * @param titulo
	 * @return ResponseEntity com o status HTTP e as postagens
	 */
	public ResponseEntity<List<Postagem>> findByTitulo(String titulo) {
		List<Postagem> listaPorTitulo = repository.findAllByTituloContainingIgnoreCase(titulo);
		if (listaPorTitulo.isEmpty()) {
			return ResponseEntity.status(404).build();
		} else {
			return ResponseEntity.status(200).body(listaPorTitulo);
		}
	}

	/**
	 * Metodo para salvar uma postagem
	 * 
	 * @param novaPostagem
	 * @return ResponseEntity com o status HTTP e a nova postagem
	 */
	public ResponseEntity<Postagem> savePostagem(Postagem novaPostagem) {
		if (temaRepository.existsById(novaPostagem.getTema().getId())) {
			return ResponseEntity.status(201).body(repository.save(novaPostagem));
		} else {
			return ResponseEntity.status(400).build();
		}
	}

	/**
	 * Metodo para alterar uma postagem
	 * 
	 * @param alterPostagem
	 * @return ResponseEntity com o status HTTP e a postagem alterada
	 */
	public ResponseEntity<Postagem> updatePostagem(Postagem alterPostagem) {
		if (repository.existsById(alterPostagem.getId())
				&& temaRepository.existsById(alterPostagem.getTema().getId())) {
			return ResponseEntity.status(200).body(repository.save(alterPostagem));
		} else {
			return ResponseEntity.status(404).build();
		}
	}

	/**
	 * Metodo para apagar uma postagem
	 * 
	 * @param id
	 * @return ResponseEntity com o status HTTP
	 */
	public ResponseEntity<Postagem> deletePostagem(Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
		} else {
			return ResponseEntity.status(404).build();
		}
		return null;
	}
}
