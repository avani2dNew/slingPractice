package com.ttn.blog;

import java.util.List;

public interface BlogService {

    public List<Blog> fetchBlogs(String resourcePath);

    public List<Blog> fetchBlogs(String resourcePath, String sortOrder);
}
