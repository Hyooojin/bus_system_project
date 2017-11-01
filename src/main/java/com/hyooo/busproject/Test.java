package com.hyooo.busproject;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class Test {

   /**
    * @param args
    * @throws IOException 
    * @throws SAXException 
    * @throws ParserConfigurationException 
    */
   public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
         StringBuilder urlBuilder = new StringBuilder("http://openapi.gbis.go.kr/ws/rest/busrouteservice");
         urlBuilder.append("?serviceKey=%2FAZmph59t7wQ0JpCGVHGnBdeFKDfQ74%2BQQ9RBmL4Vcw0AlMjHKt%2Fsssr6Qhx%2F0O8l6OmR2JddolqyifPaHAfmA%3D%3D");
         urlBuilder.append("&keyword=M7426");

      Document doc = builder.parse(urlBuilder.toString());

      NodeList list = doc.getElementsByTagName("routeId");
      
      int i=0;
      Element element;
      String contents;
      
      while(list.item(i)!=null){
         element = (Element) list.item(i);
         contents = element.getTextContent();
         System.out.println(contents);
         i++;
      }
   }
   }
