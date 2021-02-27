package org.primefaces.rain.dao;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import javax.ejb.Singleton;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ApplicationScoped;
import java.rmi.UnknownHostException;


@Singleton
public class MongoProducer {

    MongoClient mongoClient;

    @Produces
    public MongoClient create() throws UnknownHostException {
        System.out.println("public MongoClient create");

        if (mongoClient == null) {
            System.out.println("mongo client er null");
            //MongoClientURI uri = new MongoClientURI("mongodb://hildebra_dbu1:xriGM5nCaU7U@uc4.nodecluster.net/hildebra_db1");
            //MongoClient mongoClient = new MongoClient(uri);
            mongoClient = new MongoClient();
            return mongoClient;
        }
        else {
            System.out.println("mongo client er forskellig fra null");
            return mongoClient;
        }
    }
}


