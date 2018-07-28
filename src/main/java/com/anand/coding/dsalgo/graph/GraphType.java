package com.anand.coding.dsalgo.graph;

import java.util.HashMap;
import java.util.Map;

/**
 * GraphType Enumeration: DIRECTED/UNDIRECTED
 */
public enum GraphType {
    DIRECTED("Directed"),
    UNDIRECTED("Undirected");

    private final String graphType;

    /**
     *
     * @param graphType
     */
    private GraphType(final String graphType) {
        this.graphType = graphType;
    }

    private static final Map<String, GraphType> GRAPH_TYPE_MAP = new HashMap<>();
    static {
        for (final GraphType type : GraphType.values()) {
            GRAPH_TYPE_MAP.put(type.graphType, type);
        }
    }

    /**
     * String value to graph type.
     *
     * @param value the graph type value to specify the type.
     * @return GraphType
     */
    public static GraphType fromValue(final String value) {
        if (!GRAPH_TYPE_MAP.containsKey(value)) {
            throw new IllegalArgumentException(value);
        }
        return GRAPH_TYPE_MAP.get(value);
    }

    /**
     *
     * @return
     */
    public String getValue() {
        return graphType;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return graphType;
    }
}
