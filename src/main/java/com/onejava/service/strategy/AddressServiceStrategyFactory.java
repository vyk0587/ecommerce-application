package com.onejava.service.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class AddressServiceStrategyFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddressServiceStrategyFactory.class);
    private final Map<String, AddressServiceStrategy> strategyMap;

    /*
        * The AddressServiceStrategyFactory constructor takes a list of AddressServiceStrategy beans and
        * creates a map of strategy names to strategy instances.
        * The key is the simple class name of the strategy in lower case.
     */
    @Autowired
    public AddressServiceStrategyFactory(List<AddressServiceStrategy> strategies) {
        if (strategies.isEmpty()) {
            LOGGER.warn("No AddressServiceStrategy beans found. Check your Spring configuration.");
        } else {
            LOGGER.info("Found {} AddressServiceStrategy beans.", strategies.size());
        }
        this.strategyMap = strategies.stream()
                .collect(Collectors.toMap(strategy -> strategy.getClass().getSimpleName().toLowerCase(), Function.identity()));
    }

    /*
        * The getStrategy method takes a strategy name and returns the corresponding strategy instance.
        * If the strategy is not found, it throws an IllegalArgumentException.
     */
    public AddressServiceStrategy getStrategy(String strategyName) {
        AddressServiceStrategy strategy = strategyMap.get(strategyName.toLowerCase());
        if (strategy == null) {
            LOGGER.error("Strategy not found for name: {}", strategyName);
            throw new IllegalArgumentException("Strategy not found for name: " + strategyName);
        }
        LOGGER.info("Selected strategy: {}", strategy.getClass().getSimpleName());
        return strategy;
    }
}