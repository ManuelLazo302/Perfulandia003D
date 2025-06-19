package com.perfulandia.usuarios;

import com.perfulandia.usuarios.model.Usuario;
import org.mockito.Mockito.*;
import org.junit.jupiter.api.Assertions;

import com.perfulandia.usuarios.repository.UsuarioRepository;
import com.perfulandia.usuarios.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService;

    @MockitoBean
    private UsuarioRepository usuarioRepository;

    @Test
    public void testFindAll() {
        when(usuarioRepository.findAll()).thenReturn(List.of(new Usuario(1, "18.371.795-K", "Hugo", "Colina", "HugoColina95@gmail.com", "Recoleta # 1395", "Cliente")));

        List<Usuario> usuarios = usuarioService.findAll();
        assertNotNull(usuarios);
        assertEquals(1, usuarios.size());
    }

    @Test
    public void testFindByCodigo() {
        Integer id = 1;
        Usuario usuario = new Usuario(id, "18.371.795-K", "Hugo", "Colina", "HugoColina95@gmail.com", "Recoleta # 1395", "Cliente");

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));
        Usuario found = usuarioService.findById(id);
        assertNotNull(found);
        assertEquals(id, found.getId());
    }

    @Test
    public void testSave() {
        Usuario usuario = new Usuario(1, "18.371.795-K", "Hugo", "Colina", "HugoColina95@gmail.com", "Recoleta # 1395", "Cliente");
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario saved = usuarioService.save(usuario);
        assertNotNull(saved);
        assertEquals("Hugo", saved.getNombre());
    }

    @Test
    public void testDelete() {
        Integer id = 1;
        doNothing().when(usuarioRepository).deleteById(id);

        usuarioService.delete(id);
        verify(usuarioRepository, times(1)).deleteById(id);
    }
}
