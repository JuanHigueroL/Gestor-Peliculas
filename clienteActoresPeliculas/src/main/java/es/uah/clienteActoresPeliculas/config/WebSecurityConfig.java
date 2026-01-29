package es.uah.clienteActoresPeliculas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//Configuration le dice a Spring que lea este archivo nada más arrancar, porque aquí hay configuraciones críticas del sistema
//@EnableWebSecurity habilita la seguridad web en la aplicación
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private CustomAuthenticationProvider authProvider;

    //Esta función define el AuthenticationManager que hará uso de nuestro CustomAuthenticationProvider para la autenticación
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authProvider);
        return authenticationManagerBuilder.build();
    }

    //Configuración de las reglas de seguridad HTTP
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desactivar CSRF para evitar problemas en formularios
                .authorizeHttpRequests(auth -> auth

                        // -----------------------------------------------------------------
                        // 1. ZONA ADMIN (Restricciones Específicas)
                        // -----------------------------------------------------------------

                        // Acciones de Actores (Admin)
                        .requestMatchers(
                                "/actores/nuevo",
                                "/actores/guardar",
                                "/actores/editar/**",
                                "/actores/borrar/**",
                                "/actores/unirPelicula/**",
                                "/actores/unirActorPelicula"
                        ).hasAuthority("ROLE_ADMIN")

                        // Acciones de Películas (Admin)
                        .requestMatchers(
                                "/peliculas/nuevo",
                                "/peliculas/guardar",
                                "/peliculas/editar/**",
                                "/peliculas/borrar/**",
                                "/peliculas/unirActor/**",
                                "/peliculas/unirPeliculaActor"
                        ).hasAuthority("ROLE_ADMIN")

                        // -----------------------------------------------------------------
                        // ZONA PÚBLICA / CUALQUIER USUARIO
                        // -----------------------------------------------------------------
                        .requestMatchers(
                                "/js/**", "/css/**", "/img/**",
                                "/login",
                                "/registro",
                                "/registrado",
                                "/sidebarActores",
                                "/sidebarPeliculas",
                                "/peliculas",
                                "/actores",
                                "/actores/uploads/**",
                                "/peliculas/uploads/**",
                                "/peliculas",
                                "/peliculas/",
                                "/peliculas/listado",
                                "/peliculas/id/**",
                                "/peliculas/{id}",
                                "/peliculas/actor/**",
                                "/peliculas/genero/**",
                                "/peliculas/titulo/**",

                                // --- ACTORES: Vistas públicas y Filtros ---
                                "/actores",
                                "/actores/",
                                "/actores/listado",
                                "/actores/id/**",
                                "/actores/{id}",
                                "/actores/pais/**",
                                "/actores/nombre/**"
                        ).permitAll()

                        // -----------------------------------------------------------------
                        // EL RESTO
                        // -----------------------------------------------------------------
                        // Cualquier otra ruta requiere login por defecto.
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login").permitAll()
                        .defaultSuccessUrl("/peliculas", true) // Redirige aquí al entrar
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout").permitAll()
                );

        return http.build();
    }


}