package com.ttn.blog;

import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

import java.util.List;

@SlingServlet(
        resourceTypes = "blogs",
        selectors = "list",
        extensions = "html",
        methods = "GET")
public class BlogServlet extends SlingSafeMethodsServlet {
    @Reference
    BlogService blogService;

    public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {
        try {
            String sortOrder = request.getParameter("sortOrder");
            if (sortOrder == null) sortOrder = "asc";
            List<Blog> blogs = blogService.fetchBlogs("/content/blogs", sortOrder);
            request.setAttribute("blogList", blogs);

            if (sortOrder.equals("asc")) sortOrder = "desc";
            else sortOrder = "asc";
            request.setAttribute("sortOrder", sortOrder);
            request.getRequestDispatcher("/blogs.parse.html").forward(request, response);
        } catch (Exception e) {
        }
    }
}