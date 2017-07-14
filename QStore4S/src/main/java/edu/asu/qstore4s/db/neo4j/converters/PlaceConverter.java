package edu.asu.qstore4s.db.neo4j.converters;

import org.neo4j.ogm.typeconversion.AttributeConverter;

import edu.asu.qstore4s.domain.elements.impl.Place;

public class PlaceConverter implements AttributeConverter<Place, String> {

    @Override
    public Place toEntityAttribute(String arg0) {
        Place place = new Place();
        place.setSourceURI(arg0 == null ? "" : arg0);
        return place;
    }

    @Override
    public String toGraphProperty(Place place) {
        if (place == null) {
            return "";
        }
        return place.getSourceURI();
    }

}
