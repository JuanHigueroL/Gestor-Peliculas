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

//COnfiguration le dice a Spring que lea este archivo nada más arrancar, porque aquí hay configuraciones críticas del sistema
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
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(form -> form
                        .loginPage("/login").permitAll()
                        // Redirige a /peliculas al loguearse con éxito
                        .defaultSuccessUrl("/peliculas", true))
                .authorizeHttpRequests((authz) -> authz
                        // Las rutas públicas ahora son  /peliculas y /actores
                        .requestMatchers("/js/**", "/css/**", "/img/**", "/login", "/registro","/registrado","sidebarActores","sidebarPeliculas", "/peliculas", "/actores").permitAll()
                        // LO DEMÁS requiere LOGIN
                        .anyRequest().authenticated()
                );
        return http.build();
    }

}