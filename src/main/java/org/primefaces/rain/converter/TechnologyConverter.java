package org.primefaces.rain.converter;

import com.mongodb.DBObject;
import org.primefaces.rain.entity.Technology;
import org.bson.types.ObjectId;

import java.util.List;

public class TechnologyConverter {

	// convert DBObject Object to Technology
	// take special note of converting ObjectId to String
	public static Technology toTechnology(DBObject doc) {
		System.out.println("toTechnology data mongo " + doc);
		Technology technology = new Technology();

		technology.setId_integer((Integer) doc.get("id_integer"));
		technology.setSelectedTechnologies((List<String>) doc.get("selectedTechnologies"));
		technology.setRichText1((String) doc.get("richText1"));
		technology.setCompetenceLevel((String) doc.get("competenceLevel"));
		technology.setExperinceYear((String) doc.get("experinceYear"));

		ObjectId id = (ObjectId) doc.get("_id");
		technology.setId(id.toString());
		String richText1 = technology.getRichText1();
		System.out.println("richtext " + richText1);
		String competenceLevel = technology.getCompetenceLevel();
		System.out.println("competenceLevel  " + competenceLevel );
		return technology;

	}
}
