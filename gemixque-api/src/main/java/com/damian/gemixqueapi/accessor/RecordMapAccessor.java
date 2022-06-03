package com.damian.gemixqueapi.accessor;

import org.neo4j.driver.Record;
import org.neo4j.driver.Value;
import org.neo4j.driver.types.MapAccessor;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RecordMapAccessor implements MapAccessor {

    private final Record delegate;

    public RecordMapAccessor(Record delegate) {
        this.delegate = delegate;
    }

    @Override
    public Iterable<String> keys() {
        return this.delegate.keys();
    }

    @Override
    public boolean containsKey(String key) {
        return this.delegate.containsKey(key);
    }

    @Override
    public Value get(String key) {
        return this.delegate.get(key);
    }

    @Override
    public int size() {
        return this.delegate.size();
    }

    @Override
    public Iterable<Value> values() {
        return this.delegate.values();
    }

    @Override
    public <T> Iterable<T> values(Function<Value, T> mapFunction) {
        return this.delegate.values().stream().map(mapFunction).collect(Collectors.toList());
    }

    @Override
    public Map<String, Object> asMap() {
        return this.delegate.asMap();
    }

    @Override
    public <T> Map<String, T> asMap(Function<Value, T> mapFunction) {
        return this.delegate.asMap(mapFunction);
    }

    @Override
    public String toString() {
        return this.delegate.toString();
    }
}
