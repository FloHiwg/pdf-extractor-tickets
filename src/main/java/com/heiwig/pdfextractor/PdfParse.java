package com.heiwig.pdfextractor;

import java.io.*;

import org.apache.tika.exception.TikaException;

import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.xml.parsers.ParserConfigurationException;

public class PdfParse {

    public static void main(final String[] args) throws IOException, TikaException, SAXException, ParserConfigurationException {

        File selectedFile = null;

        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        int returnValue = jfc.showOpenDialog(null);
        // int returnValue = jfc.showSaveDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            selectedFile = jfc.getSelectedFile();

            System.out.println(TrainTicketParser.getBookingNumberAndAmount(selectedFile));
        }

    }
}




