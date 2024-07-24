package com.onejava.repository.specification;

import com.onejava.entity.Address;
import com.onejava.entity.User;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class AddressSpecification {

    public static Specification<Address> hasCity(String city) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("city"), city);
    }

    public static Specification<Address> hasCountry(String country) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("country"), country);
    }

    public static Specification<Address> hasPostalCode(String postalCode) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("postalCode"), postalCode);
    }

    public static Specification<Address> hasState(String state1, String state2) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.or(
                criteriaBuilder.equal(root.get("state"), state1),
                criteriaBuilder.equal(root.get("state"), state2)
        );
    }

    public static Specification<Address> hasUser(Integer userId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("user").get("id"), userId);
    }

    public static Specification<Address> hasUserName(String name) {
        return (root, query, criteriaBuilder) -> {
            Join<Address, User> userJoin = root.join("user");
            return criteriaBuilder.like(criteriaBuilder.lower(userJoin.get("name")), "%" + name.toLowerCase() + "%");
        };
    }

    public static Specification<Address> combinedSpecification(String city, String country, String postalCode, String state1, String state2, Integer userId, String userName) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (city != null && !city.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("city"), city));
            }
            if (country != null && !country.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("country"), country));
            }
            if (postalCode != null && !postalCode.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("postalCode"), postalCode));
            }
            if ((state1 != null && !state1.isEmpty()) || (state2 != null && !state2.isEmpty())) {
                Predicate statePredicate = criteriaBuilder.or(
                        criteriaBuilder.equal(root.get("state"), state1),
                        criteriaBuilder.equal(root.get("state"), state2)
                );
                predicates.add(statePredicate);
            }
            if (userId != null) {
                predicates.add(criteriaBuilder.equal(root.get("user").get("id"), userId));
            }
            if (userName != null && !userName.isEmpty()) {
                Join<Address, User> userJoin = root.join("user");
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(userJoin.get("userName")), "%" + userName.toLowerCase() + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }




}