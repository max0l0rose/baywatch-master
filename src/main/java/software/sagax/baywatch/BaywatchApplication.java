package software.sagax.baywatch;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import software.sagax.baywatch.repository.BeachRepository;
import software.sagax.baywatch.repository.LifeguardRepository;
import software.sagax.baywatch.service.BeachService;
import software.sagax.baywatch.service.LifeguardService;
import software.sagax.baywatch.service.impl.BeachServiceImpl;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class BaywatchApplication {

    private final BeachService beachService;
    private final LifeguardService lifeguardService;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println();

        List<String> list = beachService.findAllBeachesWithLifeguardsLessThan(4);
        list.stream().forEach(System.out::println);

        System.out.println();

        list = beachService.findAllByMaxTotalSpeed();
        list.stream().forEach(System.out::println);

        System.out.println();

        list = lifeguardService.findWhenSpeedGreaterThanBoss();
        list.stream().forEach(System.out::println);

        System.out.println();

        list = lifeguardService.findFastestLifeguards();
        list.stream().forEach(System.out::println);

        System.out.println();

        list = lifeguardService.findBosses();
        list.stream().forEach(System.out::println);

        System.out.println();
    }

    public static void main(String[] args) {
        SpringApplication.run(BaywatchApplication.class, args);
    }

}
