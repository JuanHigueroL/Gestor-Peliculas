package es.uah.clienteActoresPeliculas.config;

import es.uah.clienteActoresPeliculas.model.User;
import es.uah.clienteActoresPeliculas.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//AuthenticationProvider es una interfaz de Spring Security que permite implementar un proveedor de autenticación personalizado.
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private IUserService userService;

    public CustomAuthenticationProvider() {
        super();
    }

    //authenticate se ejecuta automáticamente en el momento exacto en que el usuario intenta autenticarse
    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {

        final String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        // Llama al microservicio a través del Cliente/Servicio
        User usuarioLogueado = userService.buscarPorUsernameAndPassword(username, password);

        if (usuarioLogueado != null) {
            // Crear una lista de autoridades (roles) para el usuario autenticado
            final List<GrantedAuthority> grantedAuths = new ArrayList<>();

            // Como el usuario solo tiene un ROL, se añade directamente (sin bucle for)
            grantedAuths.add(new SimpleGrantedAuthority(usuarioLogueado.getRol().getAuthority()));

            // Creamos el usuario de Spring Security
            final UserDetails principal = new org.springframework.security.core.userdetails.User(username, password, grantedAuths);
            return new UsernamePasswordAuthenticationToken(principal, password, grantedAuths);
        }
        return null;
    }

    //supports indica si este proveedor de autenticación puede manejar el tipo de autenticación proporcionado.
    //En este caso, verifica si la clase de autenticación es UsernamePasswordAuthenticationToken.
    //SupressWarnings
    @SuppressWarnings("rawtypes")
    @Override
    public boolean supports(final Class authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}