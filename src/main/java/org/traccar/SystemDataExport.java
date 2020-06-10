package org.traccar;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.nio.charset.Charset;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * A class that creates a xml file in order
 * to export Sytem log info. 
 * @author siachos_petros (siachospetros@gmail.com)
 */

public class SystemDataExport {
	
	public static final String xmlFilePath = "C:\\Users\\Desktop\\files\\xmlfile.xml";
	
	/**
	 * Receives an Integer number and creates a xml file in order
	 * to export Sytem log info. 
	 * @param number the integer input number
	 * @return 
	 * A creation of a xml file
	 * @exception IllegalArgumentException when input is NOT 0 or NOT 1
	 */	
    public static void xmlcreator(int ok) {
    	if (ok ==1) {
        try {
        	OperatingSystemMXBean operatingSystemBean = ManagementFactory.getOperatingSystemMXBean();
        	
        	RuntimeMXBean runtimeBean = ManagementFactory.getRuntimeMXBean();
        	
        	MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
            
        	DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
 
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
 
            Document document = documentBuilder.newDocument();
 
            // root element
            Element root = document.createElement("traccar");
            document.appendChild(root);
 
            // info element
            Element info = document.createElement("info");
 
            root.appendChild(info);
 
             
            // Operating system's name element
            Element Name = document.createElement("Operating system's name");
            Name.appendChild(document.createTextNode(operatingSystemBean.getName()));
            info.appendChild(Name);
 
            // Operating system's version element
            Element version = document.createElement("Operating system's version");
            version.appendChild(document.createTextNode(operatingSystemBean.getVersion()));
            info.appendChild(version);
 
            // Operating system's architecture element
            Element arhit = document.createElement("Operating system's architecture");
            arhit.appendChild(document.createTextNode(operatingSystemBean.getArch()));
            info.appendChild(arhit);
 
            // Java runtime's name elements
            Element name2 = document.createElement("Java runtime's name");
            name2.appendChild(document.createTextNode(runtimeBean.getVmName()));
            info.appendChild(name2);
            
            // Java runtime's vendor elements
            Element vendor = document.createElement("Java runtime's vendor");
            vendor.appendChild(document.createTextNode(runtimeBean.getVmVendor()));
            info.appendChild(vendor);
            
            // Java runtime's version elements
            Element version2 = document.createElement("Java runtime's version");
            version2.appendChild(document.createTextNode(runtimeBean.getVmVersion()));
            info.appendChild(version2);
            
            // Memory limit's heap elements
            Element heap = document.createElement("Memory limit's heap");
            heap.appendChild(document.createTextNode(memoryBean.getHeapMemoryUsage().getMax() / (1024 * 1024) + "mb"));
            info.appendChild(heap);
            
            // Memory limit's heap elements
            Element non_heap = document.createElement("Memory limit's heap");
            non_heap.appendChild(document.createTextNode(memoryBean.getNonHeapMemoryUsage().getMax() / (1024 * 1024) + "mb"));
            info.appendChild(non_heap);
            
            // Character encoding elements
            Element Character_encoding = document.createElement("Character encoding");
            Character_encoding.appendChild(document.createTextNode(System.getProperty("file.encoding") + " charset: " + Charset.defaultCharset()));
            info.appendChild(Character_encoding);
                       
            // create the xml file
            //transform the DOM Object to an XML File
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(xmlFilePath));
 
            // If you use
            // StreamResult result = new StreamResult(System.out);
            // the output will be pushed to the standard output ...
            // You can use that for debugging 
 
            transformer.transform(domSource, streamResult);
 
            System.out.println("Done creating XML File");
 
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
    if (ok==0) {
    	try {
    	        
    	DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

        Document document = documentBuilder.newDocument();

        // root element
        Element root = document.createElement("traccar");
        document.appendChild(root);

        // employee element
        Element info = document.createElement("info");

        root.appendChild(info);

         
        // Operating system's name element
        Element warn = document.createElement("warn");
        warn.appendChild(document.createTextNode("Failed to get system info"));
        info.appendChild(warn);

                           
        // create the xml file
        //transform the DOM Object to an XML File
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File(xmlFilePath));

        // If you use
        // StreamResult result = new StreamResult(System.out);
        // the output will be pushed to the standard output ...
        // You can use that for debugging 

        transformer.transform(domSource, streamResult);

        
    } catch (ParserConfigurationException pce) {
        pce.printStackTrace();
    } catch (TransformerException tfe) {
        tfe.printStackTrace();
    }
    }else {
    	throw new IllegalArgumentException("Number had no sign");
    }
}
}
