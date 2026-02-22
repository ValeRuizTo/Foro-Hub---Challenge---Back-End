package com.alura.forohub.service;

import com.alura.forohub.dto.DatosListadoTopico;
import com.alura.forohub.dto.DatosRegistroTopico;
import com.alura.forohub.model.StatusTopico;
import com.alura.forohub.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.alura.forohub.repository.TopicoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alura.forohub.dto.DatosDetalleTopico;
import com.alura.forohub.dto.DatosActualizacionTopico;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TopicoService {

    private final TopicoRepository repository;

    public TopicoService(TopicoRepository repository) {
        this.repository = repository;
    }


    @Transactional
    public Topico registrar(DatosRegistroTopico datos) {

        if (repository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            throw new RuntimeException("Ya existe un tópico con el mismo título y mensaje.");
        }

        Topico topico = new Topico(
                null,
                datos.titulo(),
                datos.mensaje(),
                LocalDateTime.now(),
                StatusTopico.ABIERTO,
                datos.autor(),
                datos.curso()
        );

        return repository.save(topico);
    }
    public Page<DatosListadoTopico> listar(Pageable paginacion) {
        return repository.findAll(paginacion)
                .map(DatosListadoTopico::new);
    }

    public DatosDetalleTopico detallar(Long id) {

        Topico topico = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));

        return new DatosDetalleTopico(topico);
    }

    public DatosDetalleTopico actualizar(Long id, DatosActualizacionTopico datos) {

        Optional<Topico> optionalTopico = repository.findById(id);

        if (optionalTopico.isEmpty()) {
            throw new RuntimeException("Tópico no encontrado");
        }

        if (repository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            throw new RuntimeException("Ya existe un tópico con ese título y mensaje");
        }

        Topico topico = optionalTopico.get();

        topico.setTitulo(datos.titulo());
        topico.setMensaje(datos.mensaje());
        topico.setAutor(datos.autor());
        topico.setCurso(datos.curso());

        repository.save(topico);

        return new DatosDetalleTopico(topico);
    }
    public void eliminar(Long id) {

        Optional<Topico> optionalTopico = repository.findById(id);

        if (optionalTopico.isEmpty()) {
            throw new RuntimeException("Tópico no encontrado");
        }

        repository.deleteById(id);
    }
}
