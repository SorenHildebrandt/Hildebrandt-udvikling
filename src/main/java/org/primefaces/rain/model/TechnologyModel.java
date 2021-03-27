package org.primefaces.rain.model;

import com.google.gson.Gson;
import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;
import org.primefaces.rain.converter.TechnologyConverter;
import org.primefaces.rain.entity.Stock;
import org.primefaces.rain.entity.Technology;
import org.bson.BsonDocument;
import org.bson.BsonRegularExpression;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
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

    public void create(Technology technology) {
        MongoCollection<Document> collection = mongoClient.getDatabase(db_hildebra_db1).getCollection(col_technology);
        //collectionCount_integer = Math.toIntExact(collection.countDocuments());
        //System.out.println(collection.countDocuments());
        richText1 = technology.getRichText1();
        richText2 = technology.getRichText2();
        id_integer = technology.getId_integer();
        selectedTechnologies = technology.getSelectedTechnologies();
        System.out.println("selectedTechnologies: " + selectedTechnologies + " id_integer: " + id_integer  );
        selectedExperienceYear = technology.getSelectedExperienceYear();
        selectedCompetenceLevel = technology.getSelectedCompetenceLevel();
        menutop = technology.getMenuTop();
        menuSecondLevel = technology.getMenuSecondLevel();

        //Dokument skal opdateres
        if  (id_integer != 0) {
            System.out.println("id_integer er forskellig fra nul og et dokument skal opdaterets");
            //JsonWriterSettings prettyPrint = JsonWriterSettings.builder().indent(true).build();
            // update one document
            Bson filter = eq("id_integer", id_integer);
            System.out.println("Filter " + filter);
            //Bson updateOperation = combine(set("id_integer", id_integer), set("richText1", richText1), set("richText2", richText2), set("selectedTechnologies", selectedTechnologies), set("competenceLevel", competenceLevel), set("experinceYear", experinceYear));
            Bson updateOperation = combine(set("id_integer", id_integer), set("richText1", richText1), set("richText2", richText2), set("selectedTechnologies", selectedTechnologies), set("selectedExperienceYear", selectedExperienceYear), set("selectedCompetenceLevel",selectedCompetenceLevel), set("menuTop",menutop), set("menuSecondLevel",menuSecondLevel));
            System.out.println("updateOperation " + updateOperation);
            UpdateResult updateResult = collection.updateOne(filter, updateOperation);
            //mongoClient.close();
            }

        if (id_integer == 0) {
            System.out.println("id_integer er 0 vi gemmer et nyt dokument");
            //MongoCollection<Document> collectionCount = mongoClient.getDatabase(db_hildebra_db1).getCollection(col_technology);
            collectionCount_integer = Math.toIntExact(collection.countDocuments());
            System.out.println("collection count" + collection.countDocuments());
            id_integer =  collectionCount_integer + 1;
            Document d = new Document().append("id_integer", id_integer)
                    .append("selectedTechnologies", technology.getSelectedTechnologies())
                    .append("richText1", technology.getRichText1())
                    .append("richText2", technology.getRichText2())
                    .append("selectedExperienceYear", technology.getSelectedExperienceYear())
                    .append("selectedCompetenceLevel", technology.getSelectedCompetenceLevel())
                    .append("menuTop", technology.getMenuTop())
                    .append("menuSecondLevel", technology.getMenuSecondLevel());

            collection.insertOne(d);
            //mongoClient.close();
        }
    }

    public List<Stock> findStockList(String filter) {
        System.out.println("TechnologyModel findStockLiat" + filter);
        List<Stock> stocks = new ArrayList<>();
        MongoCollection<Document> collection = mongoClient.getDatabase(db_hildebra_db1).getCollection(col_stocks);

        FindIterable<Document> iter;
        //Läses
        if (filter == null || filter.trim().length() == 0) {
            System.out.println("TechnologyModel findStockList (collection.find)");
            iter = collection.find();
        } else {
            //läses ikke
            System.out.println("TechnologyModel findStockList (else)");
            BsonRegularExpression bsonRegex = new BsonRegularExpression(filter);
            BsonDocument bsonDoc = new BsonDocument();
            bsonDoc.put("richText1", bsonRegex);
            iter = collection.find(bsonDoc);
        }
        //läses
        iter.forEach(new Block<Document>() {
            @Override
            public void apply(Document doc) {
                stocks.add(new Gson().fromJson(doc.toJson(), Stock.class));
                System.out.println("TechnologyModel findStockList: from Json to java object ");
            }
        });
        //läses
        System.out.println("TechnologyModel find (return aktie Liste)  " + stocks);
        return stocks;
    }

    public List<Technology> find(String filter) {
        System.out.println("TechnologyModel find" + filter);
        List<Technology> list = new ArrayList<>();

        MongoCollection<Document> collection = mongoClient.getDatabase(db_hildebra_db1).getCollection(col_technology);

        FindIterable<Document> iter;
        //Läses
        if (filter == null || filter.trim().length() == 0) {
            System.out.println("TechnologyModel find (collection.find)");
            iter = collection.find();
        } else {
            //läses ikke
            System.out.println("TechnologyModel find (else)");
            BsonRegularExpression bsonRegex = new BsonRegularExpression(filter);
            BsonDocument bsonDoc = new BsonDocument();
            bsonDoc.put("richText1", bsonRegex);
            iter = collection.find(bsonDoc);
        }
        //läses
        iter.forEach(new Block<Document>() {
            @Override
            public void apply(Document doc) {
                list.add(new Gson().fromJson(doc.toJson(), Technology.class));
                System.out.println("TechnologyModel find: from Json to java object ");
            }
        });
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
    public void saveStock(Stock stock) {
        MongoCollection<Document> collection = mongoClient.getDatabase(db_hildebra_db1).getCollection(col_stocks);
        richText1 = stock.getRichText1();
        richText2 = stock.getRichText2();
        id_integer = stock.getId_integer();
        selectedTechnologies = stock.getSelectedTechnologies();
        selectedExperienceYear = stock.getSelectedExperienceYear();
        selectedCompetenceLevel = stock.getSelectedCompetenceLevel();
        menutop = stock.getMenuTop();
        menuSecondLevel = stock.getMenuSecondLevel();
        //Felter til aktier
        stockName = stock.getStockName();
        tickerCode = stock.getTickerCode();
        //System.out.println("selectedTechnologies: " + selectedTechnologies + " id_integer: " + id_integer  );

        //Dokument skal opdateres
        if  (id_integer != 0) {
            System.out.println("id_integer er forskellig fra nul og et dokument skal opdaterets");
            Bson filter = eq("id_integer", id_integer);
            System.out.println("Filter " + filter);
            Bson updateOperation = combine(set("id_integer", id_integer), set("richText1", richText1), set("richText2", richText2),
                    set("selectedTechnologies", selectedTechnologies), set("selectedExperienceYear", selectedExperienceYear),
                    set("selectedCompetenceLevel",selectedCompetenceLevel), set("menuTop",menutop), set("menuSecondLevel",menuSecondLevel),
                    set("stockName",stockName), set("tickerCode",tickerCode));
            System.out.println("updateOperation " + updateOperation);
            UpdateResult updateResult = collection.updateOne(filter, updateOperation);
            System.out.println("updateResult " + updateResult);
        }

        //Nyt dokument oprettes
        if (id_integer == 0) {
            System.out.println("id_integer er 0 vi gemmer et nyt dokument");
            collectionCount_integer = Math.toIntExact(collection.countDocuments());
            System.out.println("collection count" + collection.countDocuments());
            id_integer =  collectionCount_integer + 1;
            Document d = new Document().append("id_integer", id_integer)
                    .append("selectedTechnologies", stock.getSelectedTechnologies())
                    .append("richText1", stock.getRichText1())
                    .append("richText2", stock.getRichText2())
                    .append("selectedExperienceYear", stock.getSelectedExperienceYear())
                    .append("selectedCompetenceLevel", stock.getSelectedCompetenceLevel())
                    .append("menuTop", stock.getMenuTop())
                    .append("menuSecondLevel", stock.getMenuSecondLevel())
                    .append("stockName", stock.getStockName())
                    .append("tickerCode", stock.getTickerCode());
            collection.insertOne(d);
        }
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
}




