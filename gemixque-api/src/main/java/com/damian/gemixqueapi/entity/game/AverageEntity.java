package com.damian.gemixqueapi.entity.game;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node("Average")
public class AverageEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Property("aggregated_rating")
    private final String aggregatedRating;

    @Property("nr_reviews")
    private final Integer nrReviews;

    @Property
    private final Double value;

    public AverageEntity(String aggregatedRating, Integer nrReviews, Double value) {
        this.aggregatedRating = aggregatedRating;
        this.nrReviews = nrReviews;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public String getAggregatedRating() {
        return aggregatedRating;
    }

    public Integer getNrReviews() {
        return nrReviews;
    }

    public Double getValue() {
        return value;
    }
}
