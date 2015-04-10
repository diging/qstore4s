package edu.asu.qstore4s.search.elements.factory.impl;

import org.springframework.stereotype.Service;

import edu.asu.qstore4s.search.elements.factory.IPropertyObjectFactory;
import edu.asu.qstore4s.search.elements.impl.PropertyObject;

@Service
public class PropertyObjectFactory implements IPropertyObjectFactory {
	@Override
	public PropertyObject createPropertyObject(){
		return new PropertyObject();
	}
}
