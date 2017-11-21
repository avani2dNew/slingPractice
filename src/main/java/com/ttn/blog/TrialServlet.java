package com.ttn.blog;

import com.google.gson.Gson;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

import java.util.List;

@SlingServlet(paths = "/bin/servlet/trial")
public class TrialServlet extends SlingSafeMethodsServlet {

    @Reference
    BlogService blogService;

    public void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {
        try {
            List<Blog> blogs = blogService.fetchBlogs("/content/blogs");
            response.getWriter().println(new Gson().toJson(blogs));
        } catch (Exception e) {

        }
    }
}
