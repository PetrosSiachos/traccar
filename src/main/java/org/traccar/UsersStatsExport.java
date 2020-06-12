package org.traccar;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * A class that creates a xml file in order
 * to export Sytem user's info.
 * @author siachos_petros (siachospetros@gmail.com)
 */
public class UsersStatsExport {


		public static final String xmlFilePath = "C:\\Users\\Desktop\\files\\xmlfile.xml";

	    public static void xml_user_creator(String[][] usersStats) {
	    	if (usersStats != null) {
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


	            // version name element
	            Element version = document.createElement(usersStats[0][0]);
	            version.appendChild(document.createTextNode(usersStats[0][1]));
	            info.appendChild(version);

	            // captureTime element
	            Element captureTime = document.createElement(usersStats[1][0]);
	            captureTime.appendChild(document.createTextNode(usersStats[1][1]));
	            info.appendChild(captureTime);

	            // activeUsers element
	            Element activeUsers = document.createElement(usersStats[2][0]);
	            activeUsers.appendChild(document.createTextNode(usersStats[2][1]));
	            info.appendChild(activeUsers);

	            // activeDevices elements
	            Element activeDevices = document.createElement(usersStats[3][0]);
	            activeDevices.appendChild(document.createTextNode(usersStats[3][1]));
	            info.appendChild(activeDevices);

	            // requests elements
	            Element requests = document.createElement(usersStats[4][0]);
	            requests.appendChild(document.createTextNode(usersStats[4][1]));
	            info.appendChild(requests);

	            // messagesReceived elements
	            Element messagesReceived = document.createElement(usersStats[5][0]);
	            messagesReceived.appendChild(document.createTextNode(usersStats[5][1]));
	            info.appendChild(messagesReceived);

	            // messagesStored elements
	            Element messagesStored = document.createElement(usersStats[6][0]);
	            messagesStored.appendChild(document.createTextNode(usersStats[6][1]));
	            info.appendChild(messagesStored);

	            // mailSent elements
	            Element mailSent = document.createElement(usersStats[7][0]);
	            mailSent.appendChild(document.createTextNode(usersStats[7][1]));
	            info.appendChild(mailSent);

	            // Character encoding elements
	            Element smsSent = document.createElement(usersStats[8][0]);
	            smsSent.appendChild(document.createTextNode(usersStats[8][1]));
	            info.appendChild(smsSent);

	            // geocoderRequests elements
	            Element geocoderRequests = document.createElement(usersStats[9][0]);
	            geocoderRequests.appendChild(document.createTextNode(usersStats[9][1]));
	            info.appendChild(geocoderRequests);

	            // geocoderRequests elements
	            Element geolocationRequests = document.createElement(usersStats[10][0]);
	            geolocationRequests.appendChild(document.createTextNode(usersStats[10][1]));
	            info.appendChild(geolocationRequests);

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
	    }
	    if (usersStats==null) {
	    	throw new IllegalArgumentException("null table");
	}
	}
}


