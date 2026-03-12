package com.contactmanager.contact_manager.service;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import com.contactmanager.contact_manager.entities.Resume_info.Educaion_info;
import com.contactmanager.contact_manager.entities.Resume_info.Personal_info;
import com.contactmanager.contact_manager.entities.Resume_info.Programing_Language_info;
import com.contactmanager.contact_manager.entities.Resume_info.Project_info;
import com.contactmanager.contact_manager.entities.Resume_info.Resume;
import com.contactmanager.contact_manager.entities.Resume_info.Speaking_Languages_info;
import com.lowagie.text.Anchor;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class DatabasePDFService {

        public static ByteArrayInputStream employeePDFReport(Resume resume) {
                Document document = new Document(PageSize.A4, 10, 10, 20, 20);
                ByteArrayOutputStream out = new ByteArrayOutputStream();

                try {
                        PdfWriter.getInstance(document, out);
                        document.open();

                        List<Personal_info> personal_infos = resume.getPersonal_infos();
                        List<Educaion_info> educaion_infos = resume.getEducaion_infos();
                        List<Project_info> project_infos = resume.getProject_infos();
                        List<Programing_Language_info> programing_Language_infos = resume
                                        .getPrograming_Language_infos();
                        List<Speaking_Languages_info> speaking_Languages_infos = resume.getSpeaking_Languages_infos();

                        Personal_info pInfo = new Personal_info();
                        // Educaion_info educaion_info = new Educaion_info();
                        // Project_info project_info = new Project_info();
                        Programing_Language_info programing_Language_info = new Programing_Language_info();
                        // Speaking_Languages_info speaking_Languages_info = new
                        // Speaking_Languages_info();

                        for (Personal_info iterable_object : resume.getPersonal_infos()) {
                                pInfo.setFull_Name(iterable_object.getFull_Name());
                                pInfo.setE_mail(iterable_object.getE_mail());
                                pInfo.setPhone_Number(iterable_object.getPhone_Number());
                                pInfo.setCity(iterable_object.getCity());
                        }

                        for (Programing_Language_info programing_Language_informatio : resume
                                        .getPrograming_Language_infos()) {
                                programing_Language_info.setProgramming_Languages(
                                                programing_Language_informatio.getProgramming_Languages());
                                programing_Language_info.setProgramming_Technology(
                                                programing_Language_informatio.getProgramming_Technology());
                                programing_Language_info.setProgramming_Frameworks(
                                                programing_Language_informatio.getProgramming_Frameworks());
                                programing_Language_info.setProgramming_Database(
                                                programing_Language_informatio.getProgramming_Database());
                        }

                        // Add Content to PDF file ->
                        Font fontHeader = FontFactory.getFont(FontFactory.TIMES_ROMAN, 30);
                        // Font fonttext = FontFactory.getFont(FontFactory.HELVETICA,1,Font.UNDERLINE);
                        // Paragraph para = new Paragraph("Chirag Parmar",
                        // FontFactory.getFont(FontFactory.TIMES_ROMAN, 30));
                        // para.setExtraParagraphSpace(2f);
                        // para.setAlignment(Element.ALIGN_CENTER);
                        // para.setSpacingBefore(10f);
                        // para.setSpacingAfter(2.5f);
                        // document.add(para);
                        Paragraph spacing = new Paragraph();
                        spacing.setSpacingAfter(10f); // Adjust spacing as needed (in points)
                        document.add(spacing);
                        PdfPTable NameHeader = new PdfPTable(1);

                        PdfPCell NameHeader1 = new PdfPCell(new Phrase(pInfo.getFull_Name(), fontHeader));
                        NameHeader1.setPaddingLeft(4);
                        NameHeader1.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        NameHeader1.setHorizontalAlignment(Element.ALIGN_CENTER);
                        NameHeader1.setBorderWidth(0);
                        NameHeader.addCell(NameHeader1);
                        document.add(NameHeader);

                        Anchor anchor = new Anchor(pInfo.getE_mail(),
                                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.UNDERLINE));
                        anchor.setReference("mailto:" + pInfo.getE_mail());

                        Image mailImage = null;
                        Image callImage = null;
                        Image homeImage = null;
                        try {
                                mailImage = Image.getInstance("src\\main\\resources\\static\\image\\email.png");
                                mailImage.scaleAbsolute(16, 16);
                                callImage = Image.getInstance("src\\main\\resources\\static\\image\\call.png");
                                callImage.scaleAbsolute(11, 11);
                                homeImage = Image.getInstance("src\\main\\resources\\static\\image\\home.png");
                                homeImage.scaleAbsolute(11, 11);
                        } catch (Exception e) {

                        }

                        // Add the link to a paragraph
                        Paragraph paragraph = new Paragraph();
                        // paragraph.add(anchor);

                        paragraph.add(new Chunk(mailImage, -5, -5, true));
                        // Chunk eChunk = new
                        // Chunk("chiragparmar19950@gmail.com",FontFactory.getFont(FontFactory.TIMES_ROMAN,
                        // 10, Font.UNDERLINE));
                        paragraph.add(anchor);
                        Chunk separator = new Chunk("  |   ",
                                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD));
                        paragraph.add(separator);
                        paragraph.add(new Chunk(callImage, -4, -3, true));
                        Chunk pChunk = new Chunk("(+91) " + pInfo.getPhone_Number(),
                                        FontFactory.getFont(FontFactory.TIMES_ROMAN, 10));
                        paragraph.add(pChunk);
                        // paragraph.add();
                        paragraph.add(separator);
                        paragraph.add(new Chunk(homeImage, -4, -1, true));
                        Chunk cChunk = new Chunk(pInfo.getCity(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 10));
                        paragraph.add(cChunk);
                        paragraph.setAlignment(Element.ALIGN_CENTER);
                        paragraph.setSpacingAfter(10f);
                        document.add(paragraph);

                        // Paragraph Education = new Paragraph();
                        // Chunk educationcChunk = new Chunk("Education", FontFactory.getFont("Calibri",
                        // 14, new Color(115, 115, 115)));
                        // Education.setIndentationLeft(25f);
                        // Education.add(educationcChunk);
                        // document.add(Education);
                        Paragraph education = new Paragraph("Education",
                                        FontFactory.getFont("Calibri", 14, new Color(115, 115, 115)));
                        education.setIndentationLeft(28f); // Set left padding in points
                        education.setSpacingBefore(5f);
                        education.setSpacingAfter(5f);
                        // Create a table for the horizontal line
                        PdfPTable Line = new PdfPTable(1);
                        Line.setWidthPercentage(90);
                        PdfPCell cell = new PdfPCell();
                        cell.setFixedHeight(0.5f); // Adjust the height of the line
                        cell.setBorderWidth(1);
                        cell.setBorderColor(new Color(217, 217, 217));
                        // cell.setBorder(Rectangle.NO_BORDER);
                        Line.setSpacingAfter(3f);
                        Line.addCell(cell);
                        // Add the paragraph and table to the document
                        document.add(education);
                        document.add(Line);

                        PdfPTable tableeducatioPTable = new PdfPTable(2);
                        tableeducatioPTable.setWidths(new float[] { 3f, 1 });
                        tableeducatioPTable.setWidthPercentage(87);
                        tableeducatioPTable.setHorizontalAlignment(1);
                        // for (int j = 1; j <= 2; j++) {
                        // PdfPCell cellinfo = new PdfPCell();
                        // cellinfo.setBorderWidth(0);
                        // tableeducatioPTable.addCell(cellinfo);
                        // }

                        // for (int i = 1; i <= 10; i++) {
                        // String iString = Integer.toString(i);
                        // PdfPCell cellinfo = new PdfPCell();
                        // // if (i % 2 != 0) {
                        // // cellinfo.setFixedHeight(5f);
                        // // cellinfo.setPaddingLeft(0f);
                        // // cellinfo.addElement(new Phrase(iString));
                        // // cellinfo.setBorderWidth(0);
                        // // }
                        // cellinfo.setFixedHeight(18f);
                        // cellinfo.setPaddingLeft(1f);
                        // cellinfo.setPaddingTop(1f);
                        // // cellinfo.setTop(1);
                        // cellinfo.addElement(new Phrase(iString,
                        // FontFactory.getFont(FontFactory.TIMES_ROMAN, 10)));

                        // cellinfo.setBorderWidth(PdfPCell.NO_BORDER);
                        // // cellinfo.setBorderWidth(1);
                        // cellinfo.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        // cellinfo.setHorizontalAlignment(Element.ALIGN_CENTER);
                        // tableeducatioPTable.addCell(cellinfo);
                        // }

                        for (Educaion_info Educaion_info : resume.getEducaion_infos()) {

                                PdfPCell Cource = new PdfPCell(new Paragraph(Educaion_info.getCource_Name(),
                                                FontFactory.getFont(FontFactory.TIMES_ROMAN, 11))); // Cource_Name
                                // Cource.setFixedHeight(18f);
                                // Cource.setPadding(0);
                                // Cource.setPaddingBottom(-f);
                                Cource.setBorder(PdfPCell.NO_BORDER);
                                tableeducatioPTable.addCell(Cource);

                                PdfPCell City = new PdfPCell(new Paragraph(Educaion_info.getEducaion_City(),
                                                FontFactory.getFont(FontFactory.TIMES_ROMAN,
                                                                11,
                                                                new Color(
                                                                                115, 115, 115)))); // City Name
                                // City.setFixedHeight(18f);
                                City.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                // City.setPadding(-5f);
                                // City.setPaddingBottom(-5f);
                                City.setBorder(PdfPCell.NO_BORDER);
                                tableeducatioPTable.addCell(City);

                                PdfPCell Institute = new PdfPCell(new Paragraph(Educaion_info.getInstitute_Name(),
                                                FontFactory.getFont(FontFactory.TIMES_ROMAN,
                                                                11))); // City Name
                                // Institute.setFixedHeight(18f);
                                // Institute.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                // Institute.setPadding(0);
                                // Institute.setPaddingTop(-1f);
                                // Institute.setPaddingBottom(-5f);
                                Institute.setBorder(PdfPCell.NO_BORDER);
                                tableeducatioPTable.addCell(Institute);

                                PdfPCell Duration = new PdfPCell(new Paragraph(Educaion_info.getEducaion_Time(),
                                                FontFactory.getFont(FontFactory.TIMES_ROMAN,
                                                                11,
                                                                new Color(
                                                                                115, 115, 115)))); // City Name
                                // Duration.setFixedHeight(18f);
                                Duration.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                // Duration.setPadding(0);
                                // Duration.setPaddingTop(-1f);
                                // Duration.setPaddingBottom(-5f);
                                Duration.setBorder(PdfPCell.NO_BORDER);
                                tableeducatioPTable.addCell(Duration);

                                PdfPCell marks = new PdfPCell(new Paragraph(
                                                "CGPI : " + Educaion_info.getCGPA() + ", " + "Percentage : "
                                                                + Educaion_info.getPercentage(),
                                                FontFactory.getFont(FontFactory.TIMES_ROMAN, 11, Font.BOLD))); // City
                                                                                                               // Name
                                // marks.setFixedHeight(18f);
                                // Institute.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                // marks.setPadding(0);
                                // marks.setPaddingTop(-5f);
                                marks.setPaddingBottom(10f);
                                marks.setBorder(PdfPCell.NO_BORDER);
                                tableeducatioPTable.addCell(marks);

                                PdfPCell blanckCell = new PdfPCell(new Paragraph("",
                                                FontFactory.getFont(FontFactory.TIMES_ROMAN, 11))); // City Name
                                // blanckCell.setFixedHeight(18f);
                                blanckCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                // blanckCell.setPadding(0);
                                // blanckCell.setPaddingTop(-5f);
                                blanckCell.setPaddingBottom(10f);
                                blanckCell.setBorder(PdfPCell.NO_BORDER);

                                tableeducatioPTable.addCell(blanckCell);

                        }

                        document.add(tableeducatioPTable);

                        // PdfPCell cellinfo = new PdfPCell(new Phrase("1",
                        // FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
                        // PdfPCell cellinfo1 = new PdfPCell(new Phrase("1",
                        // FontFactory.getFont("Calibri", 11, new Color(115, 115, 115))));
                        // cellinfo.addElement(new Phrase("1",
                        // FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
                        // cellinfo.addElement(new Phrase("2",
                        // FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
                        // cellinfo1.addElement(new Phrase("2",
                        // FontFactory.getFont(FontFactory.TIMES_ROMAN, 11)));
                        // cellinfo.setFixedHeight(25f);
                        // cellinfo1.setFixedHeight(25f);
                        // cellinfo.setVerticalAlignment(Element.ALIGN_TOP);
                        // cellinfo.setHorizontalAlignment(Element.ALIGN_CENTER);
                        // cellinfo1.setVerticalAlignment(Element.ALIGN_TOP);
                        // cellinfo1.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        // tableeducatioPTable.addCell(cellinfo);
                        // tableeducatioPTable.addCell(cellinfo1);

                        // document.add(tableeducatioPTable);

                        // PdfPTable table = new PdfPTable(5);
                        // // Add PDF Table Header ->
                        // Stream.of("Id","First Name", "Last Name", "Department", "Phone
                        // Number").forEach(headerTitle -> {
                        // PdfPCell header = new PdfPCell();
                        // Font headFont = FontFactory.getFont(FontFactory.TIMES_BOLD);
                        // header.setBackgroundColor(Color.CYAN);
                        // header.setHorizontalAlignment(Element.ALIGN_CENTER);
                        // header.setBorderWidth(1);
                        // header.setPhrase(new Phrase(headerTitle, headFont));
                        // table.addCell(header);
                        // });

                        Paragraph projects = new Paragraph("Projects",
                                        FontFactory.getFont("Calibri", 14, new Color(115, 115, 115)));
                        projects.setIndentationLeft(28f); // Set left padding in points
                        projects.setSpacingBefore(5f);
                        projects.setSpacingAfter(5f);
                        // Create a table for the horizontal line
                        // PdfPTable Line = new PdfPTable(1);
                        // Line.setWidthPercentage(90);
                        // PdfPCell cell1 = new PdfPCell();
                        // cell1.setFixedHeight(0.5f); // Adjust the height of the line
                        // cell1.setBorderWidth(1);
                        // cell1.setBorderColor(new Color(217, 217, 217));
                        // cell.setBorder(Rectangle.NO_BORDER);
                        // Line.setSpacingAfter(3f);
                        // Line.addCell(cell1);
                        // Add the paragraph and table to the document
                        document.add(projects);
                        document.add(Line);

                        // for (Employee employee : employees) {
                        // int id = (int) employee.getId();
                        // String idString = Integer.toString(id);
                        // PdfPCell idCell = new PdfPCell(new Phrase(idString));
                        // idCell.setPaddingLeft(4);
                        // idCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        // idCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        // table.addCell(idCell);

                        PdfPTable Projects_table = new PdfPTable(2);
                        Projects_table.setWidths(new float[] { 3f, 1 });
                        Projects_table.setWidthPercentage(87);
                        Projects_table.setHorizontalAlignment(1);

                        // PdfPCell firstNameCell = new PdfPCell(new Phrase(employee.getFirstName()));
                        // firstNameCell.setPaddingLeft(4);
                        // firstNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        // firstNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        // table.addCell(firstNameCell);
                        for (Project_info project_info : resume.getProject_infos()) {

                                PdfPCell Project_Name = new PdfPCell(new Paragraph(project_info.getProject_Name(),
                                                FontFactory.getFont("Calibri", 11, Font.BOLD))); // Cource_Name
                                Project_Name.setFixedHeight(18f);
                                // Cource.setPadding(0);
                                Project_Name.setPaddingBottom(-5f);
                                Project_Name.setBorder(PdfPCell.NO_BORDER);
                                Projects_table.addCell(Project_Name);

                                PdfPCell Technology = new PdfPCell(new Paragraph(project_info.getProject_Technology(),
                                                FontFactory.getFont(FontFactory.TIMES_ROMAN,
                                                                10,
                                                                new Color(
                                                                                115, 115, 115)))); // City Name
                                // Technology.setFixedHeight(18f);
                                Technology.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                // City.setPadding(-5f);
                                Technology.setPaddingBottom(-5f);
                                Technology.setBorder(PdfPCell.NO_BORDER);
                                Projects_table.addCell(Technology);

                                PdfPCell Project_Info = new PdfPCell(
                                                new Paragraph(project_info.getProject_Discription(),
                                                                FontFactory.getFont(FontFactory.TIMES_ROMAN, 11))); // Cource_Name
                                // Project_Info.setFixedHeight(18f);
                                // Cource.setPadding(0);
                                Project_Info.setPaddingBottom(10f);
                                // Project_Info.setPaddingTop(-5f);
                                Project_Info.setBorder(PdfPCell.NO_BORDER);
                                Projects_table.addCell(Project_Info);

                                PdfPCell Database = new PdfPCell(new Paragraph(project_info.getProject_DataBase(),
                                                FontFactory.getFont(FontFactory.TIMES_ROMAN,
                                                                10,
                                                                new Color(
                                                                                115, 115, 115)))); // City Name
                                // Database.setFixedHeight(18f);
                                Database.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                // City.setPadding(-5f);
                                Database.setPaddingBottom(10f);
                                Database.setBorder(PdfPCell.NO_BORDER);
                                Projects_table.addCell(Database);
                        }

                        document.add(Projects_table);
                        // PdfPCell lastNameCell = new PdfPCell(new
                        // Phrase(String.valueOf(employee.getLastName())));
                        // lastNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        // firstNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        // lastNameCell.setPaddingRight(4);
                        // table.addCell(lastNameCell);

                        Paragraph Programing_Language = new Paragraph("Programing Language",
                                        FontFactory.getFont("Calibri", 14, new Color(115, 115, 115)));
                        Programing_Language.setIndentationLeft(28f); // Set left padding in points
                        Programing_Language.setSpacingBefore(5f);
                        Programing_Language.setSpacingAfter(5f);
                        document.add(Programing_Language);
                        document.add(Line);

                        PdfPTable Programing_Language_info_table = new PdfPTable(3);
                        Programing_Language_info_table.setWidths(new float[] { 0.6f, 0.2f, 4f });
                        Programing_Language_info_table.setWidthPercentage(87);
                        // Programing_Language_info_table.setHorizontalAlignment(1);

                        // Programing_Language_info_table.setHorizontalAlignment(-1);

                        for (Programing_Language_info iterable_element : resume.getPrograming_Language_infos()) {

                        PdfPCell pro_Lan_name = new PdfPCell(
                                        new Paragraph("Languages",
                                                        FontFactory.getFont(FontFactory.TIMES_ROMAN,
                                                                        10))); // City Name
                        // City.setFixedHeight(18f);
                        // pro_Lan_name.setHorizontalAlignment(Element.ALIGN_CENTER);
                        // City.setPadding(-5f);
                        // City.setPaddingBottom(-5f);
                        // pro_Lan_name.setPaddingLeft(-100f);
                        pro_Lan_name.setBorder(PdfPCell.NO_BORDER);
                        Programing_Language_info_table.addCell(pro_Lan_name);

                        PdfPCell dots = new PdfPCell(
                                        new Paragraph(":-",
                                                        FontFactory.getFont(FontFactory.TIMES_ROMAN,
                                                                        10))); // City Name
                        // City.setFixedHeight(18f);
                        pro_Lan_name.setHorizontalAlignment(Element.ALIGN_CENTER);
                        // City.setPadding(-5f);
                        // City.setPaddingBottom(-5f);
                        dots.setBorder(PdfPCell.NO_BORDER);
                        Programing_Language_info_table.addCell(dots);

                        PdfPCell pro_Lan = new PdfPCell(
                                        new Paragraph(iterable_element.getProgramming_Languages(),
                                                        FontFactory.getFont(FontFactory.TIMES_ROMAN,
                                                                        10))); // City Name
                        // City.setFixedHeight(18f);
                        // pro_Lan.setHorizontalAlignment(Element.ALIGN_CENTER);
                        // City.setPadding(-5f);
                        // City.setPaddingBottom(-5f);
                        pro_Lan.setBorder(PdfPCell.NO_BORDER);
                        Programing_Language_info_table.addCell(pro_Lan);

                        PdfPCell pro_Tech_name = new PdfPCell(
                                        new Paragraph("Technology",
                                                        FontFactory.getFont(FontFactory.TIMES_ROMAN,
                                                                        10))); // City Name
                        // City.setFixedHeight(18f);
                        // pro_Lan_name.setHorizontalAlignment(Element.ALIGN_CENTER);
                        // City.setPadding(-5f);
                        // City.setPaddingBottom(-5f);
                        pro_Tech_name.setBorder(PdfPCell.NO_BORDER);
                        Programing_Language_info_table.addCell(pro_Tech_name);

                        Programing_Language_info_table.addCell(dots);

                        PdfPCell pro_Tech = new PdfPCell(
                                        new Paragraph(iterable_element.getProgramming_Technology(),
                                                        FontFactory.getFont(FontFactory.TIMES_ROMAN,
                                                                        10))); // City Name
                        // City.setFixedHeight(18f);
                        // pro_Tech.setHorizontalAlignment(Element.ALIGN_CENTER);
                        // City.setPadding(-5f);
                        // City.setPaddingBottom(-5f);
                        pro_Tech.setBorder(PdfPCell.NO_BORDER);
                        Programing_Language_info_table.addCell(pro_Tech);

                        PdfPCell pro_Frameworks_name = new PdfPCell(
                                        new Paragraph("Frameworks ",
                                                        FontFactory.getFont(FontFactory.TIMES_ROMAN,
                                                                        10))); // City Name
                        // City.setFixedHeight(18f);
                        // pro_Lan_name.setHorizontalAlignment(Element.ALIGN_CENTER);
                        // City.setPadding(-5f);
                        // City.setPaddingBottom(-5f);
                        pro_Frameworks_name.setBorder(PdfPCell.NO_BORDER);
                        Programing_Language_info_table.addCell(pro_Frameworks_name);

                        Programing_Language_info_table.addCell(dots);

                        PdfPCell pro_Frameworks = new PdfPCell(
                                        new Paragraph(iterable_element.getProgramming_Frameworks(),
                                                        FontFactory.getFont(FontFactory.TIMES_ROMAN,
                                                                        10))); // City Name
                        // City.setFixedHeight(18f);
                        // pro_Frameworks.setHorizontalAlignment(Element.ALIGN_CENTER);
                        // City.setPadding(-5f);
                        // City.setPaddingBottom(-5f);
                        pro_Frameworks.setBorder(PdfPCell.NO_BORDER);
                        Programing_Language_info_table.addCell(pro_Frameworks);

                        PdfPCell pro_Database_name = new PdfPCell(
                                        new Paragraph("Database ",
                                                        FontFactory.getFont(FontFactory.TIMES_ROMAN,
                                                                        10))); // City Name
                        // City.setFixedHeight(18f);
                        // pro_Lan_name.setHorizontalAlignment(Element.ALIGN_CENTER);
                        // City.setPadding(-5f);
                        // City.setPaddingBottom(-5f);
                        pro_Database_name.setBorder(PdfPCell.NO_BORDER);
                        Programing_Language_info_table.addCell(pro_Database_name);

                        Programing_Language_info_table.addCell(dots);

                        PdfPCell pro_Database = new PdfPCell(
                                        new Paragraph(iterable_element.getProgramming_Database(),
                                                        FontFactory.getFont(FontFactory.TIMES_ROMAN,
                                                                        10))); // City Name
                        // City.setFixedHeight(18f);
                        // pro_Database.setHorizontalAlignment(Element.ALIGN_CENTER);
                        // City.setPadding(-5f);
                        // City.setPaddingBottom(-5f);
                        pro_Database.setBorder(PdfPCell.NO_BORDER);
                        Programing_Language_info_table.addCell(pro_Database);
                        }

                        document.add(Programing_Language_info_table);


                        

                        Paragraph Speaking_Language = new Paragraph("Speaking Language",
                                        FontFactory.getFont("Calibri", 14, new Color(115, 115, 115)));
                        Speaking_Language.setIndentationLeft(28f); // Set left padding in points
                        Speaking_Language.setSpacingBefore(5f);
                        Speaking_Language.setSpacingAfter(5f);
                        document.add(Speaking_Language);
                        document.add(Line);

                        PdfPTable Speaking_Language_info_table = new PdfPTable(3);
                        Speaking_Language_info_table.setWidths(new float[] { 0.6f, 0.1f, 4f });
                        Speaking_Language_info_table.setWidthPercentage(87);
                        // PdfPCell lastNameCell = new PdfPCell(new
                        // Phrase(String.valueOf(employee.getLastName())));
                        // lastNameCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        // firstNameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        // lastNameCell.setPaddingRight(4);
                        // table.addCell(lastNameCell);

                        for (Speaking_Languages_info sp : resume.getSpeaking_Languages_infos()) {
                                PdfPCell spe_lan = new PdfPCell(
                                                new Paragraph(sp.getSpeaking_Language(),
                                                                FontFactory.getFont(FontFactory.TIMES_ROMAN,
                                                                                10))); // City Name
                                // City.setFixedHeight(18f);
                                // pro_Lan_name.setHorizontalAlignment(Element.ALIGN_CENTER);
                                // City.setPadding(-5f);
                                // City.setPaddingBottom(-5f);
                                // pro_Lan_name.setPaddingLeft(-100f);
                                spe_lan.setBorder(PdfPCell.NO_BORDER);
                                Speaking_Language_info_table.addCell(spe_lan);

                                PdfPCell dash = new PdfPCell(
                                                new Paragraph(":-",
                                                                FontFactory.getFont(FontFactory.TIMES_ROMAN,
                                                                                10))); // City Name
                                // City.setFixedHeight(18f);
                                dash.setHorizontalAlignment(Element.ALIGN_CENTER);
                                // City.setPadding(-5f);
                                // City.setPaddingBottom(-5f);
                                dash.setBorder(PdfPCell.NO_BORDER);
                                Speaking_Language_info_table.addCell(dash);

                                PdfPCell Spea_level = new PdfPCell(
                                                new Paragraph(sp.getSpeaking_Language_Level(),
                                                                FontFactory.getFont(FontFactory.TIMES_ROMAN,
                                                                                10))); // City Name
                                // City.setFixedHeight(18f);
                                // pro_Lan.setHorizontalAlignment(Element.ALIGN_CENTER);
                                // City.setPadding(-5f);
                                // City.setPaddingBottom(-5f);
                                Spea_level.setBorder(PdfPCell.NO_BORDER);
                                Speaking_Language_info_table.addCell(Spea_level);

                        }
                        document.add(Speaking_Language_info_table);
                        // PdfPCell deptCell = new PdfPCell(new
                        // Phrase(String.valueOf(employee.getDepartment())));
                        // deptCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        // deptCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        // deptCell.setPaddingRight(4);
                        // table.addCell(deptCell);

                        // PdfPCell phoneNumCell = new PdfPCell(new
                        // Phrase(String.valueOf(employee.getPhoneNumber())));
                        // phoneNumCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        // phoneNumCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        // phoneNumCell.setPaddingRight(4);
                        // table.addCell(phoneNumCell);
                        // }
                        // document.add(table);

                        document.close();
                } catch (DocumentException e) {
                        e.printStackTrace();
                }       

                return new ByteArrayInputStream(out.toByteArray());
        }
}