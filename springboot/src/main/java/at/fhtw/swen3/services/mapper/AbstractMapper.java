package at.fhtw.swen3.services.mapper;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractMapper<S, T> implements Mapper<S, T>{

    public List<T> mapToTarget(Collection<S> sources) {
        List<T> targets = new ArrayList<>();
        sources.forEach(o -> targets.add(mapToTarget(o)));
        return targets;
    }

    public List<S> mapToSource(Collection<T> targets) {
        List<S> sources = new ArrayList<>();
        targets.forEach(o -> sources.add(mapToSource(o)));
        return sources;
    }
}
