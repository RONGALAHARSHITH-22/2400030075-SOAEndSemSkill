package com.example.lbconfig;

import java.util.List;

import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

// Keep this configuration outside the GatewayApplication component-scan package.
@Configuration
public class DriverConfig {

    @Bean
    ServiceInstanceListSupplier driverServiceInstanceListSupplier() {
        return new ServiceInstanceListSupplier() {

            @Override
            public String getServiceId() {
                return "driver-service";
            }

            @Override
            public Flux<List<ServiceInstance>> get() {
                return Flux.just(List.of(
                    new DefaultServiceInstance(
                        "driver-1", "driver-service", "localhost", 8081, false),
                    new DefaultServiceInstance(
                        "driver-2", "driver-service", "localhost", 8082, false),
                    new DefaultServiceInstance(
                        "driver-3", "driver-service", "localhost", 8083, false)
                ));
            }
        };
    }
}
