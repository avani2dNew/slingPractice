package com.ttn.blog;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;

@SlingServlet(
        paths = "/bin/servlet/downloadBlogs",
        methods = "POST")
public class BlogToPdfServlet extends SlingAllMethodsServlet {

    @Reference
    BlogService blogService;

    protected void doPost(SlingHttpServletRequest requestObj, SlingHttpServletResponse responseObj) throws ServletException, IOException {

        java.util.List<Blog> blogList = blogService.fetchBlogs("/content/blogs", "asc");

        responseObj.setContentType("application/pdf");
        responseObj.setHeader("Content-Disposition", "attachment; filename=\"" + "blogPdf.pdf\"");
        responseObj.setHeader("Cache-Control", "no-cache");
        OutputStream os = responseObj.getOutputStream();
        try {
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            PdfWriter writer = PdfWriter.getInstance(document, os);
            document.open();

            Anchor anchorTarget = new Anchor("Blogs:");
            anchorTarget.setName("BackToTop");

            Paragraph paragraph1 = new Paragraph();
            paragraph1.setSpacingBefore(50);
            paragraph1.add(anchorTarget);
            document.add(paragraph1);

            PdfPTable table = new PdfPTable(5);
            table.setSpacingBefore(25);
            table.setSpacingAfter(25);
            PdfPCell c1 = new PdfPCell(new Phrase("Title"));
            table.addCell(c1);
            PdfPCell c2 = new PdfPCell(new Phrase("Description"));
            table.addCell(c2);
            PdfPCell c3 = new PdfPCell(new Phrase("Author"));
            table.addCell(c3);
            PdfPCell c4 = new PdfPCell(new Phrase("Published On"));
            table.addCell(c4);
            PdfPCell c5 = new PdfPCell(new Phrase("Tags"));
            table.addCell(c5);

            for (Blog blog : blogList) {
                table.addCell(blog.getTitle());
                table.addCell(blog.getDescription());
                table.addCell(blog.getAuthor());
                table.addCell(blog.getPublishDate().toString());
                table.addCell(blog.getTags());
            }
            document.add(table);
            document.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            os.flush();
            os.close();
        }
    }
}