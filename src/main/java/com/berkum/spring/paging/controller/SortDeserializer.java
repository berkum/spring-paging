package com.berkum.spring.paging.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

@JsonComponent
public class SortDeserializer extends JsonDeserializer<Sort> {

	@Override
	public Sort deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		ObjectCodec oc = jp.getCodec();
		ArrayNode node = oc.readTree(jp);

		List<Order> orders = new ArrayList<>();
		for (JsonNode aNode : node) {
			Direction d = Direction.fromString(aNode.get("direction").asText());
			String property = aNode.get("property").asText();
			Boolean ignoreCase = aNode.get("ignoreCase").asBoolean();
			Order o = null;
			if (ignoreCase) {
				o = new Order(d, property).ignoreCase();
			} else {
				o = new Order(d, property).ignoreCase();
			}

			orders.add(o);
		}

		Sort s = new Sort(orders);

		return s;
	}

	@Override
	public Class<Sort> handledType() {
		return Sort.class;
	}
}
