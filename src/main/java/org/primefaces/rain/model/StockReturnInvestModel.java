package org.primefaces.rain.model;

import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.primefaces.rain.converter.TechnologyConverter;
import org.primefaces.rain.entity.Stock;
import org.primefaces.rain.entity.StockReturnInvest;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;


public class StockReturnInvestModel {
    private static final Logger LOGGER = Logger.getLogger(TechnologyModel.class.getName());
    private DBCollection col;
    private Integer id_integer;

    private ObjectId id;
    private Integer collectionCount_integer;

    private String selectedStock;
    private double returnInvestPercent;
    private Date dateReturnInvest;

    @Inject
    private transient MongoClient mongoClient;

    @Inject
    private transient TechnologyConverter technologyConverter;
    private Object Stock;
    private String json;
    String db_hildebra_db1 = "hildebra_db1";

    String col_stocks = "stockReturnInvest";

    public void saveStockReturnInvest(StockReturnInvest stockReturnInvest, String newDocument, Integer collectionCount_integer) {
        MongoCollection<Document> collection = mongoClient.getDatabase(db_hildebra_db1).getCollection(col_stocks);
        selectedStock = stockReturnInvest.getSelectedStock();
        returnInvestPercent = stockReturnInvest.getReturnInvestPercent();
        dateReturnInvest = stockReturnInvest.getDateReturnInvest();

        System.out.println("collectionCount_integer " + collectionCount_integer);
        System.out.println("newDocument " + newDocument);

        //Dokument skal opdateres
        if (newDocument == "false" && collectionCount_integer == 0) {
            System.out.println("newDocument==\"false\" && collectionCount_integer == 0");
            Bson filter = eq("id_integer", id_integer);
            System.out.println("Filter " + filter);
            Bson updateOperation = combine(set("id_integer", id_integer), set("dateReturnInvest", dateReturnInvest), set("selectedStock", selectedStock), set("returnInvestPercent", returnInvestPercent));
            System.out.println("updateOperation " + updateOperation);
            UpdateResult updateResult = collection.updateOne(filter, updateOperation);
            System.out.println("updateResult " + updateResult);
        } else {
            Bson filter = eq("id_integer", collectionCount_integer);
            System.out.println("else Filter " + filter + " " + collectionCount_integer);
            Bson updateOperation = combine(set("id_integer", id_integer), set("dateReturnInvest", dateReturnInvest), set("selectedStock", selectedStock), set("returnInvestPercent", returnInvestPercent));
            System.out.println("updateOperation " + updateOperation);
            UpdateResult updateResult = collection.updateOne(filter, updateOperation);
            System.out.println("updateResult " + updateResult);
        }
        //set("buyStockDate",buyStockDate)

        //Nyt dokument oprettes
        if (newDocument == "true") {
            System.out.println("id_integer er 0 vi gemmer et nyt dokument");
            this.collectionCount_integer = Math.toIntExact(collection.countDocuments());
            System.out.println("collection count" + collection.countDocuments());
            id_integer = this.collectionCount_integer + 1;
            Document d = new Document().append("id_integer", id_integer)
                    .append("dateReturnInvest", stockReturnInvest.getDateReturnInvest())
                    .append("selectedStock", stockReturnInvest.getSelectedStock())
                    .append("returnInvestPercent", stockReturnInvest.getReturnInvestPercent());
            collection.insertOne(d);
        }

    }

    public List<StockReturnInvest> findStockList(String filter) {
        System.out.println("StockReturnInvestModel findStockLiat" + filter);
        List<StockReturnInvest> list = new ArrayList<>();
        MongoCollection<Document> collection = mongoClient.getDatabase(db_hildebra_db1).getCollection(col_stocks);
        //buyStockDate = stock.getBuyStockDate();
        FindIterable<Document> iter;

        for (Document docs : collection.find()) {
            System.out.println("DBObject " + docs);
            //System.out.println("DBObject " + docs.get("id_integer",id_integer);
            StockReturnInvest stockReturnInvest = new StockReturnInvest();

            stockReturnInvest.setSelectedStock((String) docs.get("selectedStock"));
            stockReturnInvest.setReturnInvestPercent((Double) docs.get("returnInvestPercent"));
            stockReturnInvest.setDateReturnInvest((Date) docs.get("dateReturninvest"));

            ObjectId id = (ObjectId) docs.get("_id");
            stockReturnInvest.setId(id.toString());
            stockReturnInvest.setId_integer((Integer) docs.get("id_integer"));

            list.add(stockReturnInvest);

        }




        return list;
    }

    public void deleteStock(StockReturnInvest stockReturnInvest) {

        System.out.println("model delete stock");
        id_integer = stockReturnInvest.getId_integer();
        System.out.println("id_integer model " + id_integer);
        MongoCollection<Document> collection = mongoClient.getDatabase(db_hildebra_db1).getCollection(col_stocks);
        Bson filter = eq("id_integer", id_integer);
        System.out.println("Filter " + filter);
        collection.deleteOne(filter);

    }
}

