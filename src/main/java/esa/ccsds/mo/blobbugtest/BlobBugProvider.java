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
import org.ccsds.moims.mo.mal.provider.MALInteraction;
import org.ccsds.moims.mo.mal.provider.MALProvider;
import org.ccsds.moims.mo.mal.provider.MALProviderManager;
import org.ccsds.moims.mo.mal.structures.Blob;
import org.ccsds.moims.mo.mal.structures.QoSLevel;
import org.ccsds.moims.mo.mal.structures.UInteger;
import org.ccsds.moims.mo.test.TestHelper;
import org.ccsds.moims.mo.test.testservice.TestServiceHelper;
import org.ccsds.moims.mo.test.testservice.provider.TestServiceInheritanceSkeleton;

/**
 *
 * @author Maxime Garnier
 */
public class BlobBugProvider extends TestServiceInheritanceSkeleton {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MALException {
        //General consumer property
        PropertiesHelper.loadProperties(
                "appDemoProvider.properties");

        MALContextFactory malFactory = MALContextFactory.newFactory();
        MALContext mal = malFactory.createMALContext(System.getProperties());
        MALHelper.init(MALContextFactory.getElementFactoryRegistry());
        MALProviderManager providerMgr = mal.createProviderManager();

        //Hardcoded attributes
        QoSLevel[] qosLevel = new QoSLevel[]{
            QoSLevel.ASSURED
        };
        UInteger priorityLevel = new UInteger(1);

        TestHelper.init(MALContextFactory.getElementFactoryRegistry());
        TestServiceHelper.init(MALContextFactory.getElementFactoryRegistry());
        
        BlobBugProvider handler = new BlobBugProvider();
        
        MALProvider provider = providerMgr.createProvider("TestService", null,
                TestServiceHelper.TESTSERVICE_SERVICE, new Blob(),
                handler, qosLevel,
                priorityLevel, System.getProperties(), Boolean.FALSE,
                null);
    }

    @Override
    public Blob testRequest(Blob _Blob0, MALInteraction interaction) throws MALInteractionException, MALException {
        return new Blob("ok".getBytes());
    }
}
