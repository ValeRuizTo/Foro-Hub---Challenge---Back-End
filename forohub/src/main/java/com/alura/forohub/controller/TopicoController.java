package com.alura.forohub.controller;


import com.alura.forohub.dto.DatosRegistroTopico;
import com.alura.forohub.model.Topico;
import com.alura.forohub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.alura.forohub.dto.DatosListadoTopico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import com.alura.forohub.dto.DatosDetalleTopico;


@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoService service;

    public TopicoController(TopicoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Topico> registrar(@RequestBody @Valid DatosRegistroTopico datos) {

        Topico topico = service.registrar(datos);

        return ResponseEntity.ok(topico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listar(
            @PageableDefault(size = 10, sort = "fechaCreacion") Pageable paginacion) {

        Page<DatosListadoTopico> page = service.listar(paginacion);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleTopico> detallar(@PathVariable Long id) {

        DatosDetalleTopico datos = service.detallar(id);

        return ResponseEntity.ok(datos);
    }
}