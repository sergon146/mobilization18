package com.sergon146.core.mappers;

/**
 * @author Sergon146 (sergon146@gmail.com).
 * @since 21.04.2018
 */

public interface PairMapper<S, D> {
    S to(D destination);

    D from(S source);
}
