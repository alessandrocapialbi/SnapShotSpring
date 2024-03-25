package com.SWE_photoshoot_booking.mappers;

public interface Mapper<A, B> {
    B mapTo(A a);

    A mapFrom(B b);

}
