package com.ttn.blog;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

@SlingServlet(
        paths = "/bin/servlet/downloadBlogs",
        methods = "GET")
public class BlogToPdfServlet extends SlingSafeMethodsServlet {
    @Reference
    BlogService blogService;

    public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/blogs.list.html").forward(request, response);
        response.setContentLength(0);
        response.getOutputStream().close();
    }
}