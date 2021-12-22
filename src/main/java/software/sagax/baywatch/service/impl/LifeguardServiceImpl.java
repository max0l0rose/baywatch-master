package software.sagax.baywatch.service.impl;

import org.springframework.stereotype.Service;
import software.sagax.baywatch.model.Beach;
import software.sagax.baywatch.model.Lifeguard;
import software.sagax.baywatch.repository.LifeguardRepository;
import software.sagax.baywatch.service.LifeguardService;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    }

    @Override
    public void changeSpeed(String name, Integer speed) {

        //TODO
        //Implement updating lifeguard as you see fit.

    }

    @Override
    public Map<Integer, Long> groupAndCountBySpeed() {

        //TODO
        //Implement grouping and count lifeguards by speed.
        return Collections.emptyMap();
    }

}
