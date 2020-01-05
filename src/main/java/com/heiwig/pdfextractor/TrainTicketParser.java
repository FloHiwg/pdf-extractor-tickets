package com.heiwig.pdfextractor;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TrainTicketParser {
    public static String getBookingNumberAndAmount(File file) throws IOException, TikaException, SAXException {
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream inputStream = new FileInputStream(file);
        ParseContext parseContext = new ParseContext();

        PDFParser pdfparser = new PDFParser();
        pdfparser.parse(inputStream, handler, metadata, parseContext);

        inputStream.close();

        String bookingNumber = getPatternValueAfterSpace(handler.toString(),
                Pattern.compile("Auftragsnummer:\\s[A-Z0-9]{6}"));
        String amount = getPatternValueAfterSpace(handler.toString(),
                Pattern.compile("Betrag\\s[0-9,\\s]+"));

        return bookingNumber + " - " + amount;
    }

    public static String getPatternValueAfterSpace(String text, Pattern ptn){
        String value = "";

        Matcher match = ptn.matcher(text);
        List<String> ips = new ArrayList<String>();
        while(match.find()){
            ips.add(match.group());
        }

        value = ips.get(0).trim().substring(ips.get(0).lastIndexOf(" ") + 1);

        return value;
    }
}
