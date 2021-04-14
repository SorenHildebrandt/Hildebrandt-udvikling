package org.primefaces.rain.model;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;
import org.primefaces.rain.converter.TechnologyConverter;
import org.primefaces.rain.entity.Stock;
import org.primefaces.rain.entity.Technology;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

public class TechnologyModel extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(TechnologyModel.class.getName());
    private final static String HOST = "localhost";
    private final static int PORT = 27017;
    private DBCollection col;
    private String richText1;
    private String richText2;
    private Integer id_integer;
    private List <String> selectedTechnologies;
    private ObjectId id;
    private Integer collectionCount_integer;
    private String selectedExperienceYear;
    private String selectedCompetenceLevel;
    private String menutop;
    private String menuSecondLevel;
    private String stockName;
    private String tickerCode;
    private String stockRichText1;
    private Date buyStockDate;
    private Integer numberOfStocks;

    @Inject
    private transient MongoClient mongoClient;

    @Inject
    private transient TechnologyConverter technologyConverter;

    private Object Technology;
    private Object Stock;
    private String json;
    String db_hildebra_db1 = "hildebra_db1";
    String col_technology = "technology";
    String col_stocks = "stocks";

    public void saveTechnology(Technology technology, String newDocument, Integer collectionCount_integer) {
        MongoCollection<Document> collection = mongoClient.getDatabase(db_hildebra_db1).getCollection(col_technology);

        richText1 = technology.getRichText1();
        System.out.println("richText1" + richText1);
        richText2 = technology.getRichText2();
        id_integer = technology.getId_integer();
        selectedTechnologies = technology.getSelectedTechnologies();
        System.out.println("selectedTechnologies: " + selectedTechnologies + " id_integer: " + id_integer   );
        selectedExperienceYear = technology.getSelectedExperienceYear();
        selectedCompetenceLevel = technology.getSelectedCompetenceLevel();
        menutop = technology.getMenuTop();
        menuSecondLevel = technology.getMenuSecondLevel();
        buyStockDate = technology.getBuyStockDate();
        System.out.println("collectionCount_integer " + collectionCount_integer);

        System.out.println("newDocument " + newDocument);

        //Dokument skal opdateres
        if  (newDocument=="false" && collectionCount_integer == 0  ) {
            System.out.println("newDocument==\"false\" && collectionCount_integer == 0");
            Bson filter = eq("id_integer", id_integer);
            System.out.println("Filter " + filter + " " + id_integer);
            Bson updateOperation = combine(set("id_integer", id_integer),
                    set("richText1", richText1), set("richText2", richText2),
                    set("selectedTechnologies", selectedTechnologies),
                    set("selectedExperienceYear", selectedExperienceYear),
                    set("selectedCompetenceLevel",selectedCompetenceLevel),
                    set("menuTop",menutop), set("menuSecondLevel",menuSecondLevel),
                    set("buyStockDate",buyStockDate));
            System.out.println("updateOperation " + updateOperation);
            UpdateResult updateResult = collection.updateOne(filter, updateOperation);
            System.out.println("updateResult " + updateResult);
            } else {
            Bson filter = eq("id_integer", collectionCount_integer);
            System.out.println("else Filter " + filter + " " + collectionCount_integer);
            Bson updateOperation = combine(set("id_integer", collectionCount_integer),
                    set("richText1", richText1), set("richText2", richText2),
                    set("selectedTechnologies", selectedTechnologies),
                    set("selectedExperienceYear", selectedExperienceYear),
                    set("selectedCompetenceLevel",selectedCompetenceLevel),
                    set("menuTop",menutop), set("menuSecondLevel",menuSecondLevel),
                    set("buyStockDate",buyStockDate));
            System.out.println("updateOperation " + updateOperation);
            UpdateResult updateResult = collection.updateOne(filter, updateOperation);
            System.out.println("updateResult " + updateResult);

        }

        if (newDocument=="true") {
            System.out.println("id_integer er 0 vi gemmer et nyt dokument");
            this.collectionCount_integer = Math.toIntExact(collection.countDocuments());
            System.out.println("collection count" + collection.countDocuments());
            id_integer =  this.collectionCount_integer + 1;
            Document d = new Document().append("id_integer", id_integer)
                    .append("selectedTechnologies", technology.getSelectedTechnologies())
                    .append("richText1", technology.getRichText1())
                    .append("richText2", technology.getRichText2())
                    .append("selectedExperienceYear", technology.getSelectedExperienceYear())
                    .append("selectedCompetenceLevel", technology.getSelectedCompetenceLevel())
                    .append("menuTop", technology.getMenuTop())
                    .append("menuSecondLevel", technology.getMenuSecondLevel())
                    .append("buyStockDate", technology.getBuyStockDate());

            collection.insertOne(d);
            //mongoClient.close();
        }
    }

    public List<Technology> find(String filter) {
        System.out.println("TechnologyModel find" + filter);
        List<Technology> list = new ArrayList<>();

        MongoCollection<Document> collection = mongoClient.getDatabase(db_hildebra_db1).getCollection(col_technology);

        //FindIterable<Document> iter;
        //Läses
        for (Document docs : collection.find()) {
            // do something
            System.out.println("DBObject " + docs);
            //System.out.println("DBObject " + docs.get("id_integer",id_integer);
            Technology technology = new Technology();

            technology.setBuyStockDate((Date) docs.get("buyStockDate"));
            technology.setMenuTop((String) docs.get("menuTop"));
            technology.setSelectedTechnologies((List<String>) docs.get("selectedTechnologies"));
            technology.setMenuSecondLevel((String) docs.get("menuSecondLevel"));
            technology.setSelectedExperienceYear((String) docs.get("selectedExperienceYear"));
            technology.setSelectedCompetenceLevel((String) docs.get("selectedCompetenceLevel"));
            technology.setId_integer((Integer) docs.get("id_integer"));
            ObjectId id = (ObjectId) docs.get("_id");
            technology.setRichText1((String) docs.get("richText1"));
            technology.setId(id.toString());

            list.add(technology);

            //list.add(new Gson().fromJson(docs.toJson(), Technology.class));
        }
        //läses
        //läses
        System.out.println("TechnologyModel find (return Liste)  " + list);
        return list;
    }

    public Technology readTechnology(Technology technology) {
        System.out.println("readTechnology");

        //DB db = mongoClient.getDB("hildebrandt-udvikling");
        DB db = mongoClient.getDB(db_hildebra_db1);
        DBCollection coll = db.getCollection(col_technology);

        System.out.println("database " + db);
        System.out.println("coll " + coll);
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(technology.getId())).get();

        DBObject data = coll.findOne(query);
        //DBObject data = coll.
        System.out.println("readTechnology DBObject query " + query);
        System.out.println("readTechnology DBObject data " + data);
        System.out.println("mongo client close i metoden find readTechnology");
        //mongoClient.close();
        return TechnologyConverter.toTechnology(data);

        }

    public Technology findDocumentById(String id) {
        System.out.println("findDocumentById");
        //DB db = mongoClient.getDB("hildebrandt-udvikling");
        DB db = mongoClient.getDB(db_hildebra_db1);
        DBCollection coll = db.getCollection(col_technology);
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(id));

        DBObject dbObj = coll.findOne(query);
        System.out.println("mongo client close i metoden findDocumentById");
       // mongoClient.close();
        return TechnologyConverter.toTechnology(dbObj);
    }


    public void deleteStock(Stock stock) {
        System.out.println("model delete stock");
        id_integer = stock.getId_integer();
        System.out.println("id_integer model " + id_integer);
        MongoCollection<Document> collection = mongoClient.getDatabase(db_hildebra_db1).getCollection(col_stocks);
        Bson filter = eq("id_integer", id_integer);
        System.out.println("Filter " + filter);
        collection.deleteOne(filter);
    }

    public Integer collectionCount() {
        System.out.println("collectionCount" );
        MongoCollection<Document> collection = mongoClient.getDatabase(db_hildebra_db1).getCollection(col_technology);

        //System.out.println("collectionCount_integer" + collectionCount );
        collectionCount_integer = Math.toIntExact(collection.countDocuments());
        System.out.println("collection count" + collection.countDocuments());
        Integer collectionCount =  collectionCount_integer + 1;
        id_integer =  collectionCount_integer + 1;
        System.out.println("id_integer coll" + id_integer );
        return id_integer;
    }
}




