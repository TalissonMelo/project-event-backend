package com.talissonmelo.projectevent.repositories.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.talissonmelo.projectevent.domain.Event;
import com.talissonmelo.projectevent.dto.filter.EventFilter;

public class EventSpec {

	public static Specification<Event> usingFilter(EventFilter filter){
		return (root, query,builder) -> {
		
			List<Predicate> predicates = new ArrayList<>();
			
			if(filter.getName() != null) {
				predicates.add(builder.like(root.get("name"), "%" + filter.getName() + "%"));
			}
			
			if(filter.getDescription() != null) {
				predicates.add(builder.like(root.get("description"), "%" + filter.getDescription() + "%"));
			}
			
			if(filter.getInitialData() != null) {
				predicates.add(builder.greaterThanOrEqualTo(root.get("initialData"), filter.getInitialData()));				
			}
			
			if(filter.getFinalData() != null) {
				predicates.add(builder.lessThanOrEqualTo(root.get("finalData"), filter.getFinalData()));
			}

			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
