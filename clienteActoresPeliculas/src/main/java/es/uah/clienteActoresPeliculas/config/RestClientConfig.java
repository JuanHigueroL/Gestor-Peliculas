package es.uah.clienteActoresPeliculas.config;

import es.uah.clienteActoresPeliculas.client.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

//Utilizada para definir que la clase puede registrar beans adicionales en el
//contexto o importar clases de configuración adicionales.
@Configuration
public class RestClientConfig {

    @Value("${peliculas.service.url}")
    private String serviceUrl;

    //Esta función (marcada como un @Bean) construye y configura el objeto RestClient estableciendo su URL base (http://localhost:8002)
    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .baseUrl(serviceUrl)
                .build();
    }

    //Sirve para hacer el cliente que se usa luego
    @Bean
    public PeliculaClient peliculaClient(RestClient restClient) {
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(PeliculaClient.class);
    }

    //Sirve para hacer el cliente que se usa luego
    @Bean
    public ActorClient actorClient(RestClient restClient) {
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(ActorClient.class);
    }

    @Bean
    public UserClient userClient(RestClient restClient) {
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(UserClient.class);
    }

    @Bean
    public OpinionClient opinionClient(RestClient restClient) {
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(OpinionClient.class);
    }

    @Bean
    public AuthorityClient authorityClient(RestClient restClient) {
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(AuthorityClient.class);
    }


}
