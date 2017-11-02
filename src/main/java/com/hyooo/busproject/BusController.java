package com.hyooo.busproject;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@Controller
public class BusController {
	
	
@RequestMapping(value = "/", method = RequestMethod.GET)
	public String bus(Locale locale, Model model) {
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );

		return "bus"; 
	}
	
	
@RequestMapping(value="getkeyword", method=RequestMethod.GET)		
	public void inputKeyword(@RequestParam("busname") String busNo) {
		// Scanner sc = new Scanner(System.in);
		// System.out.print("버스번호: ");
		// String bus_keyword = sc.nextLine();
		System.out.println(busNo);
		String routeId = null;
		try {
			routeId = getId(busNo);
			System.out.println(routeId);
			//getBusStations(routeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	
	public static String getId(String busNo) throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		StringBuilder urlBuilder = new StringBuilder("http://openapi.gbis.go.kr/ws/rest/busrouteservice");
		urlBuilder.append(
				"?serviceKey=%2FAZmph59t7wQ0JpCGVHGnBdeFKDfQ74%2BQQ9RBmL4Vcw0AlMjHKt%2Fsssr6Qhx%2F0O8l6OmR2JddolqyifPaHAfmA%3D%3D");
		urlBuilder.append("&keyword=" + busNo);
		System.out.println(busNo);

		Document doc = builder.parse(urlBuilder.toString());

		NodeList list = doc.getElementsByTagName("routeId");

		int i = 0;
		Element element;
		String contents = null;

		while (list.item(i) != null) {
			element = (Element) list.item(i);
			contents = element.getTextContent();
			//System.out.println(contents);
			
			i++; 
		}
		return contents;
	}
	
	
/*	public static void getBusStation(String routeId) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		StringBuilder urlBuilder = new StringBuilder("http://openapi.gbis.go.kr/ws/rest/busrouteservice/station");
		urlBuilder.append(
				"?serviceKey=%2FAZmph59t7wQ0JpCGVHGnBdeFKDfQ74%2BQQ9RBmL4Vcw0AlMjHKt%2Fsssr6Qhx%2F0O8l6OmR2JddolqyifPaHAfmA%3D%3D");
		urlBuilder.append("&routeId=" +routeId);
		Document doc = builder.parse(urlBuilder.toString());
		
		int i = 0;
		String contents = null;
		NodeList list = doc.getElementsByTagName("busRouteStationList");
		
		for(i = 0; i < list.getLength(); i++){		
			Node nNode = list.item(i);
			if(nNode.getNodeType() == Node.ELEMENT_NODE){
								
				Element eElement = (Element) nNode;
				System.out.println("######################");
				//System.out.println(eElement.getTextContent());
				System.out.println("금융사  : " + getTagValue("centerYn", eElement));
				System.out.println("상품 코드  : " + getTagValue("stationName", eElement));
				System.out.println("상품명 : " + getTagValue("fin_prdt_nm", eElement));
				System.out.println("연평균 수익률  : " + getTagValue("avg_prft_rate", eElement));
				System.out.println("공시 이율  : " + getTagValue("dcls_rate", eElement));
			}	// for end
		}	// if end	
		
//	}
		
		
	}*/
	
	
}
