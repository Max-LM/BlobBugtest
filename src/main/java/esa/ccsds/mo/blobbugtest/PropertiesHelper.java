/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esa.ccsds.mo.blobbugtest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maxime Garnier
 */
public class PropertiesHelper {

    public static void printProperties(String destination, String property) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(destination));
        writer.write(property);
        writer.close();
    }

    public static void loadProperties(String Filename) {
        final java.util.Properties sysProps = System.getProperties();

        File fileProperties = new File(Filename);

        if (fileProperties.exists()) {
            try {
                sysProps.putAll(StructureHelper.loadProperties(
                        fileProperties.toURI().toURL(), ""));

            } catch (MalformedURLException ex) {
                Logger.getLogger(PropertiesHelper.class.getName()).log(
                        Level.SEVERE,
                        null, ex);
            }
        }
        System.setProperties(sysProps);
    }

}
