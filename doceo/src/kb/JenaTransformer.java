package kb;

import java.util.ArrayList;

import model.Triple;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.vocabulary.RDF;

public class JenaTransformer {

	public void convertToJenaModel(ArrayList<Triple> tripleList) {

		Model model = ModelFactory.createDefaultModel();
		for (Triple triple : tripleList) {
			model.add(ResourceFactory.createStatement(
					ResourceFactory.createResource(triple.getSubject()),
					RDF.type,
					ResourceFactory.createResource(triple.getObject())));
		}

		model.write(System.out, "N-TRIPLE");
	}

	public void convertToJenaModel(String relation, ArrayList<Triple> tripleList) {

		Model model = ModelFactory.createDefaultModel();
		for (Triple triple : tripleList) {
			Property siegeRelation = ResourceFactory.createProperty("http://seagent.ege.edu.tr/siege/",
					triple.getPredicate());
			model.add(ResourceFactory.createStatement(
					ResourceFactory.createResource(triple.getSubject()),
					siegeRelation,
					ResourceFactory.createResource(triple.getObject())));
		}

		model.write(System.out, "N-TRIPLE");
	}

}
