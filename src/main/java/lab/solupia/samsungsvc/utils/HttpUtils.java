package lab.solupia.samsungsvc.utils;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jason, Moon (jason@solupia.co.kr)
 * @since 2018-12-11
 */
@Slf4j
public class HttpUtils {

//  private static String HOST = "http://192.168.1.30:8080";
	private static String HOST = "https://openapi.samsung.net";

    private static HttpHeaders getHeaders(String token) {
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_UTF8_VALUE);
        httpHeaders.set(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);

        return httpHeaders;
    }

    public static <T> ResponseEntity<T> get(String url, Class<T> clazz, String token) {
        
        RestTemplate restTemplate = new RestTemplate();
        
        List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();
        converters.removeIf(httpMessageConverter -> httpMessageConverter instanceof StringHttpMessageConverter);
        converters.add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));

        return restTemplate.exchange(HOST + url, HttpMethod.GET, new HttpEntity(getHeaders(token)), clazz);
    }

    public static <T> ResponseEntity<T> post(String url, Class<T> clazz, String token) throws HttpClientErrorException, HttpServerErrorException {
        try {
            log.info("HttpUtils POST :: URL :: ", url);
            
            RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
            
            List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();
            converters.removeIf(httpMessageConverter -> httpMessageConverter instanceof StringHttpMessageConverter);
            converters.add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
            
            return restTemplate.exchange(HOST + url, HttpMethod.POST, new HttpEntity<>(getHeaders(token)), clazz);
        } catch (HttpClientErrorException e) {
            log.error("***********************************************************************************************************************************************");
            log.error("HttpUtils POST :: ERROR :: ", e);
            log.error("HttpUtils POST :: e.getResponseBodyAsString() :: ", e.getResponseBodyAsString());
            log.error("***********************************************************************************************************************************************");

            throw new HttpClientErrorException(e.getStatusCode());
        } catch (HttpServerErrorException e) {
            log.error("***********************************************************************************************************************************************");
            log.error("HttpUtils POST :: ERROR :: ", e);
            log.error("HttpUtils POST :: e.getResponseBodyAsString() :: ", e.getResponseBodyAsString());
            log.error("***********************************************************************************************************************************************");

            throw new HttpServerErrorException(e.getStatusCode());
        }
    }

    public static <S, T> ResponseEntity<T> post(String url, S request, Class<T> clazz, String token) {
        
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        
        List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();
        converters.removeIf(httpMessageConverter -> httpMessageConverter instanceof StringHttpMessageConverter);
        converters.add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));

        return restTemplate.exchange(HOST + url, HttpMethod.POST, new HttpEntity<>(request, getHeaders(token)), clazz);
    }
}
