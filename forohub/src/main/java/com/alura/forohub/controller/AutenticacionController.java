package com.alura.forohub.controller;


import com.alura.forohub.model.Usuario;
import com.alura.forohub.security.TokenService;
import org.springframework.http.ResponseEntity;
import com.alura.forohub.dto.DatosAutenticacion;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid DatosAutenticacion datos) {

        var authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        datos.login(),
                        datos.password()
                );

        var authentication = manager.authenticate(authenticationToken);

        var usuario = (Usuario) authentication.getPrincipal();

        var jwtToken = tokenService.generarToken(usuario);

        return ResponseEntity.ok(jwtToken);
    }

    @Autowired
    private TokenService tokenService;
}
