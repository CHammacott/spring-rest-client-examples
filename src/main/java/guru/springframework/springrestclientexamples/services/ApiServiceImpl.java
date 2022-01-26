package guru.springframework.springrestclientexamples.services;

import guru.springframework.api.domain.User;
import guru.springframework.api.domain.UserData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class ApiServiceImpl implements  ApiService {

    private RestTemplate restTemplate;

    private final String api_url;

    public ApiServiceImpl(RestTemplate restTemplate, @Value("${api.url}") String api_url) {
        this.restTemplate = restTemplate;
        this.api_url = api_url;
    }

    @Override
    public List<User> getUsers(Integer limit) {

        String fullApiUrl = api_url + "limit=" + limit;

        List<LinkedHashMap<String, Object>> apiData = restTemplate.getForObject(fullApiUrl, List.class);

        UserData userData = new UserData();

        userData.ingestApiUsers(apiData);

        return userData.getData();
    }

    //todo make it work? Nah BROKEN
    @Override
    public Flux<User> getUsers(Mono<Integer> limit) {
        return WebClient.create(api_url)
                .get()
                .uri(uriBuilder -> uriBuilder.queryParam("limit", String.valueOf(limit.block())).build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .flatMap(resp -> resp.bodyToMono(UserData.class))
                .flatMapIterable(UserData::getData);
    }
}
