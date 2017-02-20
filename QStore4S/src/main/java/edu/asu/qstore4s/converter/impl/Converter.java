package edu.asu.qstore4s.converter.impl;

import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.stereotype.Service;

import edu.asu.qstore4s.converter.IConverter;
import edu.asu.qstore4s.converter.IXmlElements;
import edu.asu.qstore4s.domain.elements.impl.Relation;
import edu.asu.qstore4s.domain.elements.impl.Term;
import edu.asu.qstore4s.domain.elements.impl.TermPart;
import edu.asu.qstore4s.domain.elements.impl.TermParts;
import edu.asu.qstore4s.domain.events.impl.AppellationEvent;
import edu.asu.qstore4s.domain.events.impl.CreationEvent;
import edu.asu.qstore4s.domain.events.impl.RelationEvent;

/**
 * 
 * This class converts object list into XML or JSON string
 * 
 * @author Bhargav Desai
 */

@Service
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Converter implements IConverter {

    private List<String> idList;

    // TODO: delete dependency to neo4j template
    @Autowired
    @Qualifier("neo4jOperations")
    private Neo4jOperations template;

    public Converter() {
        idList = new ArrayList<String>();
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public String convertToJson(edu.asu.qstore4s.domain.elements.impl.Element element) throws JSONException {

        JSONObject jsonObj;

        String xml = convertToXML(element);

        jsonObj = XML.toJSONObject(xml);
        return (jsonObj.toString());

    }

    /**
     * {@inheritDoc}
     */

    @Override
    public String convertToJson(List<CreationEvent> creationEventList) throws JSONException {

        JSONObject jsonObj;

        String xml = convertToXML(creationEventList);

        jsonObj = XML.toJSONObject(xml);
        return (jsonObj.toString());

    }

    /**
     * {@inheritDoc}
     */

    @Override
    public String convertToJsonShallow(edu.asu.qstore4s.domain.elements.impl.Element element) throws JSONException {

        JSONObject jsonObj;

        String xml = convertToXMLShallow(element);

        jsonObj = XML.toJSONObject(xml);
        return (jsonObj.toString());

    }

    /**
     * This method convert the list of creation objects containing relational
     * events and appellation events to XML. .
     * 
     * @param creationEventList
     * 
     */

    private Document instanstiateXML() {
        idList.clear();
        Document xmldocument = new Document();
        Namespace namespace = Namespace.getNamespace(IXmlElements.NAMESPACE);
        Namespace xsi = Namespace.getNamespace("xsi", IXmlElements.XSI);

        Element parent = new Element("element_events", namespace);

        parent.addNamespaceDeclaration(xsi);

        parent.setAttribute("schemaLocation", IXmlElements.SCHEMA_LOCATION, xsi);

        xmldocument.setRootElement(parent);

        return xmldocument;
    }

    /**
     * {@inheritDoc}
     */

    @Override
    public String convertToXML(List<CreationEvent> creationEventList) {

        Document xmldocument = instanstiateXML();
        Namespace namespace = Namespace.getNamespace(IXmlElements.NAMESPACE);

        for (CreationEvent creationEvent : creationEventList) {
            idList.clear();
            addElementToXML(xmldocument, namespace, creationEvent, false);
        }

        XMLOutputter xmloutput = new XMLOutputter();

        xmloutput.setFormat(Format.getPrettyFormat());

        return xmloutput.outputString(xmldocument);

    }

    /**
     * {@inheritDoc}
     */

    @Override
    public String convertToXML(edu.asu.qstore4s.domain.elements.impl.Element element) {
        Document xmldocument = instanstiateXML();
        Namespace namespace = Namespace.getNamespace(IXmlElements.NAMESPACE);

        idList.clear();

        addElementToXML(xmldocument, namespace, element, false);
        XMLOutputter xmloutput = new XMLOutputter();

        xmloutput.setFormat(Format.getPrettyFormat());

        return xmloutput.outputString(xmldocument);

    }

    private void addElementToXML(Document document, Namespace namespace,
            edu.asu.qstore4s.domain.elements.impl.Element element, boolean shallow) {
        if (element instanceof AppellationEvent) {
            Element appellationEvent = addAppellationNode((AppellationEvent) element, namespace);
            document.getRootElement().addContent(appellationEvent);

        } else if (element instanceof RelationEvent) {
            Element relationEvent = addRelationEventNode((RelationEvent) element, namespace, shallow);
            document.getRootElement().addContent(relationEvent);

        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String convertToXMLShallow(edu.asu.qstore4s.domain.elements.impl.Element element) {
        Document xmldocument = instanstiateXML();
        Namespace namespace = Namespace.getNamespace(IXmlElements.NAMESPACE);

        idList.clear();

        addElementToXML(xmldocument, namespace, element, true);
        XMLOutputter xmloutput = new XMLOutputter();

        xmloutput.setFormat(Format.getPrettyFormat());

        return xmloutput.outputString(xmldocument);

    }

    /**
     * This method add RelationEvent node from creationEvent object.
     * 
     * @param creationEvent
     * @param namespace
     * @param isShallow
     * @return relationeventnode
     */
    private Element addRelationEventNode(RelationEvent creationEvent, Namespace namespace, boolean isShallow) {

        Element relationEvent = new Element(IXmlElements.RELATION_EVENT, namespace);
        {

            boolean idFlag = true;
            for (String idCompare : idList) {
                if (idCompare.equals(creationEvent.getId())) {
                    idFlag = false;
                    break;
                }
            }

            Element id = new Element(IXmlElements.ID, namespace);
            idList.add(creationEvent.getId());
            id.setText(creationEvent.getId());
            relationEvent.addContent(id);

            if (idFlag) {

                if (creationEvent.getCreator() != null) {
                    Element creater = new Element(IXmlElements.CREATOR, namespace);
                    creater.setText(creationEvent.getCreator().getSourceURI());
                    relationEvent.addContent(creater);
                }
                Element creation_date = new Element(IXmlElements.CREATION_DATE, namespace);
                SimpleDateFormat format = new SimpleDateFormat(IXmlElements.DATE_FORMAT);
                StringBuffer datestring = new StringBuffer();
                if (creationEvent.getCreationDate() != null) {
                    format.format(creationEvent.getCreationDate(), datestring, new FieldPosition(0));
                    creation_date.setText(datestring.toString());
                    relationEvent.addContent(creation_date);
                }

                if (creationEvent.getCreationPlace() != null) {

                    Element creation_Place = new Element(IXmlElements.CREATION_PLACE, namespace);
                    creation_Place.setText(creationEvent.getCreationPlace().getSourceURI());
                    relationEvent.addContent(creation_Place);
                }

                if (creationEvent.getSourceReference() != null) {
                    Element source_reference = new Element(IXmlElements.SOURCE_REFERENCE, namespace);
                    source_reference.setText(creationEvent.getSourceReference().getSourceURI());
                    relationEvent.addContent(source_reference);
                }

                if (creationEvent.getInterpretationCreator() != null) {
                    Element interpretation_creator = new Element(IXmlElements.INTERPRETATION_CREATOR, namespace);
                    interpretation_creator.setText(creationEvent.getInterpretationCreator().getSourceURI());
                    relationEvent.addContent(interpretation_creator);
                }

                if (creationEvent.getRelationCreator() != null) {
                    Element relation_creator = new Element(IXmlElements.RELATION_CREATOR, namespace);
                    relation_creator.setText(creationEvent.getRelationCreator().getSourceURI());
                    relationEvent.addContent(relation_creator);
                }

                if (creationEvent.getRefId() != null) {
                    Element ref_Id = new Element(IXmlElements.REFID, namespace);
                    ref_Id.setText(creationEvent.getRefId());
                    relationEvent.addContent(ref_Id);
                }

                // if (creationEvent.getExternal_refId() != null) {
                // Element external_Id = new Element(
                // IXmlElements.EXTERNAL_REFID, namespace);
                // external_Id.setText(creationEvent.getExternal_refId());
                // relationEvent.addContent(external_Id);
                // }
                //
                // if (creationEvent.getInternal_refId() != null) {
                // Element internal_Id = new Element(
                // IXmlElements.INTERNAL_REFID, namespace);
                // internal_Id.setText(creationEvent.getInternal_refId());
                // relationEvent.addContent(internal_Id);
                // }

                Relation relation = creationEvent.getRelation();
                if (relation != null) {
                    if (isShallow) {
                        Element relationNode = addRelationNode(relation, namespace, true);
                        relationEvent.addContent(relationNode);

                    } else {
                        Element relationNode = addRelationNode(relation, namespace, false);
                        relationEvent.addContent(relationNode);
                    }
                }
            }
        }

        return relationEvent;

    }

    /**
     * This method convert appellation node from the creation event object.
     * 
     * @param creationEvent
     * @param namespace
     * @return appellationevent node
     */

    private Element addAppellationNode(AppellationEvent creationEvent, Namespace namespace) {

        if (creationEvent.getTerm() == null) {
            creationEvent = template.load(AppellationEvent.class, creationEvent.getGraphId(), 2);
        }

        Element appellationevent = new Element(IXmlElements.APPELLATION_EVENT, namespace);

        Element id = new Element(IXmlElements.ID, namespace);
        id.setText(creationEvent.getId());
        idList.add(creationEvent.getId());
        appellationevent.addContent(id);

        if (creationEvent.getCreator() != null) {
            Element creater = new Element(IXmlElements.CREATOR, namespace);
            creater.setText(creationEvent.getCreator().getSourceURI());
            appellationevent.addContent(creater);
        }

        Element creation_date = new Element(IXmlElements.CREATION_DATE, namespace);
        SimpleDateFormat format = new SimpleDateFormat(IXmlElements.DATE_FORMAT);
        StringBuffer datestring = new StringBuffer();
        if (creationEvent.getCreationDate() != null) {
            format.format(creationEvent.getCreationDate(), datestring, new FieldPosition(0));
            creation_date.setText(datestring.toString());
            appellationevent.addContent(creation_date);
        }

        Element creation_Place = new Element(IXmlElements.CREATION_PLACE, namespace);

        if (creationEvent.getCreationPlace() != null) {
            creation_Place.setText(creationEvent.getCreationPlace().getSourceURI());
            appellationevent.addContent(creation_Place);
        }

        if (creationEvent.getSourceReference() != null) {
            Element source_reference = new Element(IXmlElements.SOURCE_REFERENCE, namespace);
            source_reference.setText(creationEvent.getSourceReference().getSourceURI());
            appellationevent.addContent(source_reference);
        }

        if (creationEvent.getRefId() != null) {
            Element ref_Id = new Element(IXmlElements.REFID, namespace);
            ref_Id.setText(creationEvent.getRefId());
            appellationevent.addContent(ref_Id);
        }

        if (creationEvent.getInterpretationCreator() != null) {
            Element interpretation_creator = new Element(IXmlElements.INTERPRETATION_CREATOR, namespace);
            interpretation_creator.setText(creationEvent.getInterpretationCreator().getSourceURI());
            appellationevent.addContent(interpretation_creator);
        }

        // if (creationEvent.getExternal_refId() != null) {
        // Element external_Id = new Element(
        // IXmlElements.EXTERNAL_REFID, namespace);
        // external_Id.setText(creationEvent.getExternal_refId());
        // appellationevent.addContent(external_Id);
        // }
        //
        // if (creationEvent.getInternal_refId() != null) {
        // Element internal_Id = new Element(
        // IXmlElements.INTERNAL_REFID, namespace);
        // internal_Id.setText(creationEvent.getInternal_refId());
        // appellationevent.addContent(internal_Id);
        // }

        Term term = creationEvent.getTerm();

        Element termnode = addTermNode(term, namespace);

        appellationevent.addContent(termnode);
        return appellationevent;

    }

    /**
     * This method convert relation node from relation object.
     * 
     * @param relation
     * @param namespace
     * @param isShallow
     * @return relation node
     */

    private Element addRelationNode(Relation relation, Namespace namespace, boolean isShallow) {

        if (relation.getPredicate() == null) {
            relation = template.load(Relation.class, relation.getGraphId(), 2);
        }

        Element relationNode = new Element(IXmlElements.RELATION, namespace);

        Element idRelation = new Element(IXmlElements.ID, namespace);
        idRelation.setText(relation.getId());
        relationNode.addContent(idRelation);
        idList.add(relation.getId());

        if (relation.getCreator() != null) {
            Element createrRelation = new Element(IXmlElements.CREATOR, namespace);
            createrRelation.setText(relation.getCreator().getSourceURI());
            relationNode.addContent(createrRelation);
        }
        Element creation_dateRelation = new Element(IXmlElements.CREATION_DATE, namespace);
        SimpleDateFormat format = new SimpleDateFormat(IXmlElements.DATE_FORMAT);
        StringBuffer datestring = new StringBuffer();
        if (relation.getCreationDate() != null) {
            format.format(relation.getCreationDate(), datestring, new FieldPosition(0));
            creation_dateRelation.setText(datestring.toString());

            relationNode.addContent(creation_dateRelation);
        }

        Element creation_PlaceRelation = new Element(IXmlElements.CREATION_PLACE, namespace);

        if (relation.getCreationPlace() != null) {
            creation_PlaceRelation.setText(relation.getCreationPlace().getSourceURI());
            relationNode.addContent(creation_PlaceRelation);
        }

        if (relation.getSourceReference() != null) {
            Element source_reference = new Element(IXmlElements.SOURCE_REFERENCE, namespace);
            source_reference.setText(relation.getSourceReference().getSourceURI());

            relationNode.addContent(source_reference);
        }

        if (relation.getExternal_refId() != null) {
            Element external_Id = new Element(IXmlElements.EXTERNAL_REFID, namespace);
            external_Id.setText(relation.getExternal_refId());
            relationNode.addContent(external_Id);
        }

        if (relation.getInternal_refId() != null) {
            Element internal_Id = new Element(IXmlElements.INTERNAL_REFID, namespace);
            internal_Id.setText(relation.getInternal_refId());
            relationNode.addContent(internal_Id);
        }

        CreationEvent subject = relation.getSubject();

        Element subjectnode = new Element(IXmlElements.SUBJECT, namespace);

        if (isShallow) {
            Element idNode = new Element(IXmlElements.ID, namespace);
            idNode.setText(subject.getId());
            subjectnode.addContent(idNode);

        } else {

            if (subject instanceof AppellationEvent) {

                Element appellationEvent = addAppellationNode((AppellationEvent) subject, namespace);
                subjectnode.addContent(appellationEvent);
            } else if (subject instanceof RelationEvent) {
                Element relationsubEvent = addRelationEventNode((RelationEvent) subject, namespace, false);
                subjectnode.addContent(relationsubEvent);
            }

        }
        relationNode.addContent(subjectnode);

        Element objectnode = new Element(IXmlElements.OBJECT, namespace);
        CreationEvent object = relation.getObject();

        if (isShallow) {
            Element idNode = new Element(IXmlElements.ID, namespace);
            idNode.setText(object.getId());
            objectnode.addContent(idNode);

        } else {

            if (object instanceof AppellationEvent) {
                // if (((AppellationEvent) object).getTerm() == null)
                // template.fetch(object);
                Element appellationEvent = addAppellationNode((AppellationEvent) object, namespace);
                objectnode.addContent(appellationEvent);
            } else if (object instanceof RelationEvent) {
                // if (((RelationEvent) object).getRelation() == null)
                // template.fetch(object);
                Element relationsubEvent = addRelationEventNode((RelationEvent) object, namespace, false);
                objectnode.addContent(relationsubEvent);
            }
        }
        relationNode.addContent(objectnode);

        Element predicatenode = new Element(IXmlElements.PREDICATE, namespace);
        AppellationEvent predicate = relation.getPredicate();

        if (isShallow) {
            Element idNode = new Element(IXmlElements.ID, namespace);
            idNode.setText(predicate.getId());
            predicatenode.addContent(idNode);

        } else {

            {
                Element appellationEvent = addAppellationNode((AppellationEvent) predicate, namespace);
                predicatenode.addContent(appellationEvent);
            }
        }
        relationNode.addContent(predicatenode);

        return relationNode;
    }

    /**
     * This method will convert Termnode from the term object.
     * 
     * @param term
     * @param namespace
     * @return TermNode
     */

    private Element addTermNode(Term term, Namespace namespace) {

        Element termnode = new Element(IXmlElements.TERM, namespace);
        {
            Element id = new Element(IXmlElements.ID, namespace);
            id.setText(term.getId());
            termnode.addContent(id);

            if (term.getCreator() != null) {
                Element creater = new Element(IXmlElements.CREATOR, namespace);
                creater.setText(term.getCreator().getSourceURI());
                termnode.addContent(creater);
            }
            Element creation_date = new Element(IXmlElements.CREATION_DATE, namespace);
            SimpleDateFormat format = new SimpleDateFormat(IXmlElements.DATE_FORMAT);
            StringBuffer datestring = new StringBuffer();
            if (term.getCreationDate() != null) {
                format.format(term.getCreationDate(), datestring, new FieldPosition(0));
                creation_date.setText(datestring.toString());
                termnode.addContent(creation_date);
            }

            if (term.getCreationPlace() != null) {
                Element creation_Place = new Element(IXmlElements.CREATION_PLACE, namespace);
                creation_Place.setText(term.getCreationPlace().getSourceURI());
                termnode.addContent(creation_Place);
            }

            if (term.getSourceReference() != null) {
                Element source_reference = new Element(IXmlElements.SOURCE_REFERENCE, namespace);
                source_reference.setText(term.getSourceReference().getSourceURI());
                termnode.addContent(source_reference);
            }

            // add interpretation
            if (term.getInterpretation() != null) {
                Element interpretation = new Element(IXmlElements.INTERPRETATION, namespace);
                interpretation.setText(term.getInterpretation().getSourceURI());

                // add datatype
                if (term.getDatatype() != null && !term.getDatatype().isEmpty()) {
                    interpretation.setAttribute(IXmlElements.INTERPRETATION_DATATYPE, term.getDatatype());
                }

                termnode.addContent(interpretation);
            }

            if (term.getNormalizedRepresentation() != null) {
                Element normalized_representation = new Element(IXmlElements.NORMALIZED_REPRESENTATION, namespace);
                normalized_representation.setText(term.getNormalizedRepresentation().getSourceURI());
                termnode.addContent(normalized_representation);
            }
        }

        TermParts printedrepresentation = term.getPrintedRepresentation();

        if (printedrepresentation != null) {
            Element printed_representation = addTermPartsNode(printedrepresentation, namespace);

            termnode.addContent(printed_representation);
        }

        Element certain = new Element(IXmlElements.CERTAIN, namespace);
        if (term.isCertain()) {
            certain.setText("true");
        } else if (!term.isCertain()) {
            certain.setText("false");
        }

        Element referenced_terms = new Element(IXmlElements.REFERENCED_TERMS, namespace);

        Set<Term> referencedTerms = term.getReferencedTerms();

        for (Term referenceTerm : referencedTerms) {

            Element subTerm = addTermNode(referenceTerm, namespace);
            referenced_terms.addContent(subTerm);

        }

        termnode.addContent(referenced_terms);

        termnode.addContent(certain);

        return termnode;

    }

    /**
     * This method will convert to TermParts node from printedrepresentaion
     * object.
     * 
     * @param printedrepresentation
     * @param namespace
     * @return TermPartsnode
     */

    private Element addTermPartsNode(TermParts printedrepresentation, Namespace namespace) {

        Element printed_representation = new Element(IXmlElements.PRINTED_REPRESENTATION, namespace);

        {
            Element id = new Element(IXmlElements.ID, namespace);
            id.setText(printedrepresentation.getId());
            printed_representation.addContent(id);

            if (printedrepresentation.getCreator() != null) {
                Element creater = new Element(IXmlElements.CREATOR, namespace);
                creater.setText(printedrepresentation.getCreator().getSourceURI());
                printed_representation.addContent(creater);
            }
            Element creation_date = new Element(IXmlElements.CREATION_DATE, namespace);
            SimpleDateFormat format = new SimpleDateFormat(IXmlElements.DATE_FORMAT);
            StringBuffer datestring = new StringBuffer();
            if (printedrepresentation.getCreationDate() != null)
                format.format(printedrepresentation.getCreationDate(), datestring, new FieldPosition(0));
            creation_date.setText(datestring.toString());
            printed_representation.addContent(creation_date);

            if (printedrepresentation.getCreationPlace() != null) {
                Element creation_Place = new Element(IXmlElements.CREATION_PLACE, namespace);
                creation_Place.setText(printedrepresentation.getCreationPlace().getSourceURI());
                printed_representation.addContent(creation_Place);
            }

            if (printedrepresentation.getReferencedSource() != null) {
                Element source_reference = new Element(IXmlElements.SOURCE_REFERENCE, namespace);
                source_reference.setText(printedrepresentation.getReferencedSource().getSourceURI());
                printed_representation.addContent(source_reference);

            }
        }

        Set<TermPart> termpartList = printedrepresentation.getTermParts();

        for (TermPart termpart : termpartList) {

            Element termpartnode = addTermPartNode(termpart, namespace);

            printed_representation.addContent(termpartnode);

        }

        return printed_representation;
    }

    /**
     * This method will convert to TermPartNode from Termpart object.
     * 
     * @param termpart
     * @param namespace
     * @return TermPartNode
     */

    private Element addTermPartNode(TermPart termpart, Namespace namespace) {

        Element termpartnode = new Element(IXmlElements.TERM_PART, namespace);

        Element id = new Element(IXmlElements.ID, namespace);
        id.setText(termpart.getId());
        termpartnode.addContent(id);

        if (termpart.getCreator() != null) {
            Element creater = new Element(IXmlElements.CREATOR, namespace);
            creater.setText(termpart.getCreator().getSourceURI());
            termpartnode.addContent(creater);
        }

        Element creation_date = new Element(IXmlElements.CREATION_DATE, namespace);
        SimpleDateFormat dateformat = new SimpleDateFormat(IXmlElements.DATE_FORMAT);
        StringBuffer datestring = new StringBuffer();

        if (termpart.getCreationDate() != null) {
            dateformat.format(termpart.getCreationDate(), datestring, new FieldPosition(0));
            creation_date.setText(datestring.toString());
            termpartnode.addContent(creation_date);
        }

        if (termpart.getCreationPlace() != null) {
            Element creation_Place = new Element(IXmlElements.CREATION_PLACE, namespace);
            creation_Place.setText(termpart.getCreationPlace().getSourceURI());
            termpartnode.addContent(creation_Place);
        }

        if (termpart.getSourceReference() != null) {
            Element source_reference = new Element(IXmlElements.SOURCE_REFERENCE, namespace);
            source_reference.setText(termpart.getSourceReference().getSourceURI());
            termpartnode.addContent(source_reference);
        }

        Element position = new Element(IXmlElements.POSITION, namespace);
        position.setText(termpart.getPosition() + "");
        termpartnode.addContent(position);

        Element expression = new Element(IXmlElements.EXPRESSION, namespace);
        expression.setText(termpart.getExpression());
        termpartnode.addContent(expression);

        if (termpart.getNormalization() != null) {
            Element normalization = new Element(IXmlElements.NORMALIZATION, namespace);
            normalization.setText(termpart.getNormalization().getSourceURI());
            termpartnode.addContent(normalization);
        }
        Element formatted_pointer = new Element(IXmlElements.FORMATTED_POINTER, namespace);
        formatted_pointer.setText(termpart.getFormattedPointer());
        termpartnode.addContent(formatted_pointer);

        Element format = new Element(IXmlElements.FORMAT, namespace);
        format.setText(termpart.getFormat());
        termpartnode.addContent(format);

        return termpartnode;

    }

}
