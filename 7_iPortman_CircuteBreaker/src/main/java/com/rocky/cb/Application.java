package com.rocky.cb;

import java.time.Duration;
import java.util.function.Consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
	SpringApplication.run(Application.class, args);
	/* 
	 * Level 1
	 * ApplicationContext ctx=SpringApplication.run(Application.class, args);
	 * CircuteBreakerService cbs=ctx.getBean(CircuteBreakerService.class);
	 * List<IgmDTO> igmList=cbs.findAllIgms();
	 * igmList.stream().forEach(System.out::println); System.out.println(
	 * "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"
	 * ); List<VoyageRegDTO> voyagesList=cbs.findAllVoyages();
	 * voyagesList.stream().forEach(System.out::println);
	 */
	
	
		
		
	}
	
	@Bean
    public Customizer<Resilience4JCircuitBreakerFactory> slowCircuteBreakerCustomizer(){
		return (tocustomize)->{
           tocustomize.configure((builder)->{
				builder.circuitBreakerConfig(CircuitBreakerConfig.custom().failureRateThreshold(5)
						.waitDurationInOpenState(Duration.ofSeconds(2)).slidingWindowSize(2).build())
						.timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(3)).build());
	           }, "voyage");
		};
		/*
		 * return new Customizer<Resilience4JCircuitBreakerFactory>() { public void
		 * customize(Resilience4JCircuitBreakerFactory tocustomize) {
		 * tocustomize.configure(new Consumer<Resilience4JConfigBuilder>() {
		 * 
		 * @Override public void accept(Resilience4JConfigBuilder t) {
		 * CircuitBreakerConfig
		 * cfg=CircuitBreakerConfig.custom().failureRateThreshold(5).
		 * waitDurationInOpenState(Duration.ofSeconds(2)).slidingWindowSize(2).build();
		 * TimeLimiterConfig
		 * tlc=TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(3)).build()
		 * ; t.circuitBreakerConfig(cfg).timeLimiterConfig(tlc); } }, "voayge"); } };
		 */
    }

	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
