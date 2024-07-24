package com.onejava.repository.specification;

import com.onejava.entity.QAddress;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public class AddressQueryDSL {
    private static final QAddress qAddress = QAddress.address;

    public static Predicate hasCity(String city) {
        return qAddress.city.eq(city);
    }

    public static Predicate hasCountry(String country) {
        return qAddress.country.eq(country);
    }

    public static Predicate hasPostalCode(String postalCode) {
        return qAddress.postalCode.eq(postalCode);
    }

    public static Predicate hasState(String state1, String state2) {
        return qAddress.state.eq(state1).or(qAddress.state.eq(state2));
    }

    public static Predicate hasUser(Integer userId) {
        return qAddress.user.id.eq(userId);
    }

    public static Predicate hasUserName(String userName) {
        return qAddress.user.userName.likeIgnoreCase("%" + userName + "%");
    }

    public static Predicate combinedSpecification(String city, String country, String postalCode, String state1, String state2, Integer userId, String userName) {
        BooleanBuilder builder = new BooleanBuilder();
        if (city != null && !city.isEmpty()) {
            builder.and(qAddress.city.eq(city));
        }
        if (country != null && !country.isEmpty()) {
            builder.and(qAddress.country.eq(country));
        }
        if (postalCode != null && !postalCode.isEmpty()) {
            builder.and(qAddress.postalCode.eq(postalCode));
        }
        if (state1 != null && !state1.isEmpty() || state2 != null && !state2.isEmpty()) {
            builder.and(qAddress.state.eq(state1).or(qAddress.state.eq(state2)));
        }
        if (userId != null) {
            builder.and(qAddress.user.id.eq(userId));
        }
        if (userName != null && !userName.isEmpty()) {
            builder.and(qAddress.user.userName.likeIgnoreCase("%" + userName + "%"));
        }
        return builder;
    }
}
