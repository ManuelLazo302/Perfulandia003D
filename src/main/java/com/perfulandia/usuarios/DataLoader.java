package com.perfulandia.usuarios;

import com.perfulandia.usuarios.model.*;
import com.perfulandia.usuarios.repository.*;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();

        int numCliente = 20;
        int numEmpleado = 6;
        int numGerente = 3;
        int numAdmin = 1;

        for (int i = 0; i < numCliente; i++) {
            Usuario usuario = new Usuario();
            usuario.setRun(faker.idNumber().valid());
            usuario.setNombre(faker.name().firstName());
            usuario.setApellido(faker.name().lastName());
            usuario.setCorreoElectronico(faker.internet().emailAddress());
            usuario.setDireccionEnvio(faker.address().fullAddress());
            usuario.setRol("Cliente");
            usuarioRepository.save(usuario);
        }

        for (int i = 0; i < numEmpleado; i++) {
            Usuario usuario = new Usuario();
            usuario.setRun(faker.idNumber().valid());
            usuario.setNombre(faker.name().firstName());
            usuario.setApellido(faker.name().lastName());
            usuario.setCorreoElectronico(faker.internet().emailAddress());
            usuario.setRol("Empleado de Ventas");
            usuarioRepository.save(usuario);
        }

        for (int i = 0; i < numGerente; i++) {
            Usuario usuario = new Usuario();
            usuario.setRun(faker.idNumber().valid());
            usuario.setNombre(faker.name().firstName());
            usuario.setApellido(faker.name().lastName());
            usuario.setCorreoElectronico(faker.internet().emailAddress());
            usuario.setRol("Gerente Sucursal");
            usuarioRepository.save(usuario);
        }

        for (int i = 0; i < numAdmin; i++) {
            Usuario usuario = new Usuario();
            usuario.setRun(faker.idNumber().valid());
            usuario.setNombre(faker.name().firstName());
            usuario.setApellido(faker.name().lastName());
            usuario.setCorreoElectronico(faker.internet().emailAddress());
            usuario.setRol("Administrador");
            usuarioRepository.save(usuario);
        }
    }
}
