/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esa.ccsds.mo.blobbugtest;

import org.ccsds.moims.mo.mal.MALContext;
import org.ccsds.moims.mo.mal.MALContextFactory;
import org.ccsds.moims.mo.mal.MALException;
import org.ccsds.moims.mo.mal.MALHelper;
import org.ccsds.moims.mo.mal.MALInteractionException;
import org.ccsds.moims.mo.mal.consumer.MALConsumer;
import org.ccsds.moims.mo.mal.consumer.MALConsumerManager;
import org.ccsds.moims.mo.mal.structures.Blob;
import org.ccsds.moims.mo.mal.structures.Identifier;
import org.ccsds.moims.mo.mal.structures.IdentifierList;
import org.ccsds.moims.mo.mal.structures.QoSLevel;
import org.ccsds.moims.mo.mal.structures.SessionType;
import org.ccsds.moims.mo.mal.structures.UInteger;
import org.ccsds.moims.mo.mal.structures.URI;
import org.ccsds.moims.mo.test.TestHelper;
import org.ccsds.moims.mo.test.testservice.TestServiceHelper;
import org.ccsds.moims.mo.test.testservice.consumer.TestServiceStub;

/**
 *
 * @author Maxime Garnier
 */
public class BlobBugConsumer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MALException, MALInteractionException {
        //General consumer property
        PropertiesHelper.loadProperties(
                "appDemoConsumer.properties");

        //URI of the testService Provider
        PropertiesHelper.loadProperties(
                "appDemoProviderURI.properties");
        //MAL
        MALContextFactory malFactory = MALContextFactory.newFactory();
        MALContext mal = malFactory.createMALContext(System.getProperties());

        MALConsumerManager consumerMgr = mal.createConsumerManager();

        MALHelper.init(MALContextFactory.getElementFactoryRegistry());

        URI uriTo = new URI(System.getProperty("appDemoProviderURI"));

        IdentifierList domain = new IdentifierList();
        Identifier network = new Identifier("GROUND");
        SessionType session = SessionType.LIVE;
        Identifier sessionName = new Identifier("LIVE");
        UInteger priority = new UInteger(0);

        TestHelper.init(MALContextFactory.getElementFactoryRegistry());
        TestServiceHelper.init(MALContextFactory.getElementFactoryRegistry());
        MALConsumer consumer = consumerMgr.createConsumer("TestServiceConsumer", uriTo, null,
                TestServiceHelper.TESTSERVICE_SERVICE, null, domain,
                network, session, sessionName,
                QoSLevel.QUEUED, System.getProperties(), priority);
        
        TestServiceStub testServiceStub = new TestServiceStub(consumer);
        
        Blob response = testServiceStub.testRequest(new Blob());

    }

}
