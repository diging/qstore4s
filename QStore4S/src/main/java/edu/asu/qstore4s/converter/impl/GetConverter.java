package edu.asu.qstore4s.converter.impl;

import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.springframework.stereotype.Service;

import edu.asu.qstore4s.converter.IGetConverter;
import edu.asu.qstore4s.converter.IXmlElements;
import edu.asu.qstore4s.exception.InvalidDataException;
import edu.asu.qstore4s.exception.ParserException;

/**
 * 
 * @author Bhargav Desai
 *
 */
@Service
public class GetConverter implements IGetConverter {

	/**
	 * {@inheritDoc}}
	 */
	
	@Override
	public List<String> parseXML(String xml) throws ParserException,
			IOException, URISyntaxException, ParseException,
			InvalidDataException {

		SAXBuilder XMLbuilder = new SAXBuilder();
		Document XMLDocument;

		try {
			String formattedXML = Format.compact(xml);
			XMLDocument = (Document) XMLbuilder.build((new StringReader(
					formattedXML)));

		} catch (JDOMException e) {
			throw new ParserException(e.toString());
		}

	
		List<String> idList = new ArrayList<String>();
		
		Element rootElement = XMLDocument.getRootElement();
		Namespace nameSpace = rootElement.getNamespace();
		List<Element> Children = rootElement.getChildren(IXmlElements.ID,nameSpace);
		Iterator<Element> childrenIterator = Children.iterator();

		do {
			Element childNode = childrenIterator.next();
			String id = childNode.getText();
			if(id!=null && !id.equals("")){
			idList.add(id);	
			}
			
			
		} while (childrenIterator.hasNext());

		
		if(idList.size()==0){
			throw new InvalidDataException("Please provide atleast one id value");
		}
		
		return idList;
	}

}
