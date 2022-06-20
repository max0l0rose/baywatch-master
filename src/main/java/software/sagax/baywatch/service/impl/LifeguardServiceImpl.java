package software.sagax.baywatch.service.impl;

import org.springframework.stereotype.Service;
import software.sagax.baywatch.dto.SpeedNameDTO;
import software.sagax.baywatch.model.Beach;
import software.sagax.baywatch.model.Lifeguard;
import software.sagax.baywatch.repository.LifeguardRepository;
import software.sagax.baywatch.service.LifeguardService;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Service
public class LifeguardServiceImpl implements LifeguardService {

    private final LifeguardRepository lifeguardRepository;

    public LifeguardServiceImpl(LifeguardRepository lifeguardRepository) {
        this.lifeguardRepository = lifeguardRepository;
    }

    @Override
    public List<Lifeguard> findAll() {
        return lifeguardRepository.findAll();
    }

    @Override
    public Lifeguard addLifeguard(String name, Integer speed, Beach beach, Lifeguard boss) {

        Lifeguard lifeguard = new Lifeguard();
        lifeguard.setName(name);
        lifeguard.setSpeed(speed);
        if(Objects.nonNull(beach)) {
            lifeguard.setBeach(beach);
        }
        if(Objects.nonNull(boss)) {
            lifeguard.setBoss(boss);
        }
        return this.addLifeguard(lifeguard);
    }

    @Override
    public Lifeguard addLifeguard(Lifeguard lifeguard) {
        return lifeguardRepository.save(lifeguard);
    }

    @Override
    public List<String> findWhenSpeedGreaterThanBoss() {
        return lifeguardRepository.findWhenSpeedGreaterThanBoss();
    }

    @Override
    public List<String> findFastestLifeguards() {
        return lifeguardRepository.findFastestLifeguards();
    }

    @Override
    public List<String> findBosses() {
        return lifeguardRepository.findBosses();
    }

    @Override
    public void fireLifeguard(String name) {

        //TODO
        //Implement deleting lifeguard by name using Java Stream API
        lifeguardRepository.findAll().stream()
                .filter(l -> Objects.equals(l.getName(), name))
                .forEach(l -> lifeguardRepository.delete(l));

        // Way 2. No Streams. JPA Derived Query Methods.
//        lifeguardRepository.deleteAllByNameIs(name);
    }

    @Override
    public void changeSpeed(String name, Integer speed) {

        //TODO
        //Implement updating lifeguard as you see fit.

        lifeguardRepository.findAll().stream()
                .filter(l -> Objects.equals(l.getName(), name))
                .forEach(l -> {
                    l.setSpeed(speed);
                    lifeguardRepository.save(l);
                });

        // Way 2. No Streams. JPA Derived Query Methods.
        //lifeguardRepository.updateSpeed(name, speed);
    }

    @Override
    public Map<Integer, Long> groupAndCountBySpeed() {

        //TODO
        //Implement grouping and count lifeguards by speed.

//        Map<Integer, Long> groupedBySpeed = lifeguardRepository.findAll().stream()
//                .collect(Collectors.groupingBy(
//                            Lifeguard::getSpeed,
//                            Collectors.counting()
//                            //Collectors.mapping(Lifeguard::getName, Collectors.toSet())) // names only
//                        )
//                );

        // Way 2. No Streams.
//        List<int[]> list = lifeguardRepository.groupAndCountBySpeed();
//        Map<Integer, Long> groupedBySpeed = list.stream().collect(Collectors.toMap(e -> e[0], e -> Long.valueOf(e[1])));

        List<int[]> list = lifeguardRepository.groupAndCountBySpeed();
        Map<Integer, Long> groupedBySpeed = list.stream().collect(Collectors.toMap(e -> e[0], e -> Long.valueOf(e[1])));

        return groupedBySpeed;
    }


    //@Override
    public Map<Integer, String> namesAndSpeeds() {
        List<SpeedNameDTO> list = lifeguardRepository.namesAndSpeeds();
        Map<Integer, List<String>> map = list.stream()
                .collect(Collectors.toMap(e -> e.getSpeed(),
                                        e -> List.of(e.getName()),
                                        (e1, e2) -> {
                                            ArrayList<String> list2 = new ArrayList(e1);
                                            list2.addAll(e2);
                                            return list2;
                                        }));
        return null;

//        return lifeguards.stream()
//                .collect(Collectors.toMap(
//                        Lifeguard::getSpeed,
//                        v -> 1L,
//                        (existing, replacement) -> existing + 1));

    }

//    SELECT DISTINCT l.name " +
//                                   "from lifeguard l " +
//                                   "right join lifeguard r ON l.id = r.boss_id " +
//                                   "where l.name is not null

}
