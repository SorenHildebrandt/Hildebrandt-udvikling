package org.primefaces.rain.model;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;
//import com.sun.org.apache.bcel.internal.generic.DADD;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.primefaces.rain.converter.TechnologyConverter;
import org.primefaces.rain.entity.Stock;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

public class StockModel extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(TechnologyModel.class.getName());
    private final static String HOST = "localhost";
    private final static int PORT = 27017;
    private DBCollection col;
    private String richText1;
    private String richText2;
    private Integer id_integer;
    private List<String> selectedTechnologies;
    private ObjectId id;
    private Integer collectionCount_integer;
    private String selectedExperienceYear;
    private String selectedCompetenceLevel;
    private String menutop;
    private String menuSecondLevel;
    private String stockName;
    private String tickerCode;
    private String stockRichText1;
    //private GsonCompatibleDate buyStockDate;
    private Date buyStockDate;
    private Integer stockPrice;
    private String returnInvest;
    private Integer numberOfStocks;
    private List <String> selectedBusiness;
    private String selectedValuta;

    @Inject
    private transient MongoClient mongoClient;

    @Inject
    private transient TechnologyConverter technologyConverter;
    private Object Stock;
    private String json;
    String db_hildebra_db1 = "hildebra_db1";

    String col_stocks = "stocks";

    public void saveStock(Stock stock, String newDocument, Integer collectionCount_integer) {
        MongoCollection<Document> collection = mongoClient.getDatabase(db_hildebra_db1).getCollection(col_stocks);

        richText1 = stock.getRichText1();
        System.out.println("stockRichText1" +stockRichText1);
        richText2 = stock.getRichText2();
        id_integer = stock.getId_integer();
        selectedTechnologies = stock.getSelectedTechnologies();
        selectedBusiness = stock.getSelectedBusiness();
        selectedExperienceYear = stock.getSelectedExperienceYear();
        selectedCompetenceLevel = stock.getSelectedCompetenceLevel();
        menutop = stock.getMenuTop();
        menuSecondLevel = stock.getMenuSecondLevel();
        //Felter til aktier
        stockName = stock.getStockName();
        tickerCode = stock.getTickerCode();
        buyStockDate = stock.getBuyStockDate();
        stockPrice = stock.getStockPrice();
        returnInvest = stock.getReturnInvest();
        numberOfStocks = stock.getNumberOfStocks();
        selectedValuta = stock.getSelectedValuta();

        System.out.println("collectionCount_integer " + collectionCount_integer);
        System.out.println("newDocument " + newDocument);

        //Dokument skal opdateres
        if  (newDocument=="false" && collectionCount_integer == 0) {
            System.out.println("newDocument==\"false\" && collectionCount_integer == 0");
            Bson filter = eq("id_integer", id_integer);
            System.out.println("Filter " + filter);
            Bson updateOperation = combine(set("id_integer", id_integer), set("richText1", richText1), set("richText2", richText2),
                    set("selectedTechnologies", selectedTechnologies), set("selectedExperienceYear", selectedExperienceYear),
                    set("selectedCompetenceLevel",selectedCompetenceLevel), set("menuTop",menutop), set("menuSecondLevel",menuSecondLevel),
                    set("stockName",stockName), set("tickerCode",tickerCode), set("buyStockDate", buyStockDate),
                    set("stockPrice", stockPrice), set("returnInvest", returnInvest), set("numberOfStocks", numberOfStocks),
                    set("selectedBusiness", selectedBusiness), set("selectedValuta", selectedValuta));
            System.out.println("updateOperation " + updateOperation);
            UpdateResult updateResult = collection.updateOne(filter, updateOperation);
            System.out.println("updateResult " + updateResult);
        } else {
            Bson filter = eq("id_integer", collectionCount_integer);
            System.out.println("else Filter " + filter + " " + collectionCount_integer);
            Bson updateOperation = combine(set("id_integer", collectionCount_integer), set("richText1", richText1), set("richText2", richText2),
                    set("selectedTechnologies", selectedTechnologies), set("selectedExperienceYear", selectedExperienceYear),
                    set("selectedCompetenceLevel",selectedCompetenceLevel), set("menuTop",menutop), set("menuSecondLevel",menuSecondLevel),
                    set("stockName",stockName), set("tickerCode",tickerCode), set("buyStockDate", buyStockDate),
                    set("stockPrice", stockPrice), set("returnInvest", returnInvest), set("numberOfStocks", numberOfStocks),
                    set("selectedBusiness", selectedBusiness), set("selectedValuta", selectedValuta));
            System.out.println("updateOperation " + updateOperation);
            UpdateResult updateResult = collection.updateOne(filter, updateOperation);
            System.out.println("updateResult " + updateResult);
        }
        //set("buyStockDate",buyStockDate)

        //Nyt dokument oprettes
        if (newDocument=="true") {
            System.out.println("id_integer er 0 vi gemmer et nyt dokument");
            this.collectionCount_integer = Math.toIntExact(collection.countDocuments());
            System.out.println("collection count" + collection.countDocuments());
            id_integer =  this.collectionCount_integer + 1;
             Document d = new Document().append("id_integer", id_integer)
                    .append("selectedTechnologies", stock.getSelectedTechnologies())
                    .append("richText1", stock.getRichText1())
                    .append("richText2", stock.getRichText2())
                    .append("selectedExperienceYear", stock.getSelectedExperienceYear())
                    .append("selectedCompetenceLevel", stock.getSelectedCompetenceLevel())
                    .append("menuTop", stock.getMenuTop())
                    .append("menuSecondLevel", stock.getMenuSecondLevel())
                    .append("stockName", stock.getStockName())
                    .append("tickerCode", stock.getTickerCode())
                    .append("buyStockDate", stock.getBuyStockDate())
                     .append("stockPrice", stock.getStockPrice())
                     .append("returnInvest", stock.getReturnInvest())
                     .append("numberOfStocks", stock.getNumberOfStocks())
                     .append("selectedBusiness", stock.getSelectedBusiness())
                     .append("selectedValuta", stock.getSelectedValuta());
            collection.insertOne(d);
        }
    }

    public List<Stock> findStockList(String filter ) {
        System.out.println("StockModel findStockLiat" + filter);
        List<Stock> list = new ArrayList<>();
        MongoCollection<Document> collection = mongoClient.getDatabase(db_hildebra_db1).getCollection(col_stocks);
        //buyStockDate = stock.getBuyStockDate();
        FindIterable<Document> iter;
        //Läses
        for (Document docs : collection.find()) {
            // do something
            System.out.println("DBObject " + docs);
            //System.out.println("DBObject " + docs.get("id_integer",id_integer);
            Stock stock = new Stock();

            stock.setBuyStockDate((Date) docs.get("buyStockDate"));
            stock.setMenuTop((String) docs.get("menuTop"));
            stock.setSelectedTechnologies((List<String>) docs.get("selectedTechnologies"));
            stock.setMenuSecondLevel((String) docs.get("menuSecondLevel"));
            stock.setSelectedExperienceYear((String) docs.get("selectedExperienceYear"));
            stock.setSelectedCompetenceLevel((String) docs.get("selectedCompetenceLevel"));
            stock.setStockName((String) docs.get("stockName"));
            stock.setTickerCode((String) docs.get("tickerCode"));
            ObjectId id = (ObjectId) docs.get("_id");
            stock.setRichText1((String) docs.get("richText1"));
            stock.setId(id.toString());
            stock.setId_integer((Integer) docs.get("id_integer"));
            stock.setStockPrice((Integer) docs.get("stockPrice"));
            stock.setReturnInvest((String) docs.get("returnInvest"));
            stock.setNumberOfStocks((Integer) docs.get("numberOfStocks"));
            stock.setSelectedBusiness((List<String>) docs.get("selectedBusiness"));
            List<String> selectedBusiness = stock.getSelectedBusiness();
            String selectedBusiness_string = selectedBusiness.toString().replace("[", "").replace("]", "");
            System.out.println("selectedTech_string " + selectedBusiness_string);
            stock.setSelectedBusiness_string(selectedBusiness_string);
            stock.setSelectedValuta((String) docs.get("selectedValuta"));
            list.add(stock);

            //list.add(new Gson().fromJson(docs.toJson(), Technology.class));
        }
        //läses
        //läses
        System.out.println("TechnologyModel find (return Liste)  " + list);
        return list;
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
