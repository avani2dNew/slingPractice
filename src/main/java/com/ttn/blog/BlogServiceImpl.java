package com.ttn.blog;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ValueMap;

import java.text.SimpleDateFormat;
import java.util.*;

@Component(immediate = true)
@Service(BlogService.class)
public class BlogServiceImpl implements BlogService {
    @Reference
    ResourceResolverFactory resolverFactory;

    public List<Blog> fetchBlogs(String resourcePath) {
        try {
            ResourceResolver resolver = resolverFactory.getResourceResolver(fetchLoginCredentials());
            Resource resource = resolver.getResource(resourcePath);
            Iterator<Resource> childResources = resource.listChildren();
            System.out.println("List children");
            List<Blog> resources = new ArrayList<Blog>();
            while (childResources.hasNext()) {
                resources.add(bindResourceToBlog(childResources.next()));
            }
            return resources;
        } catch (Exception e) {
            System.out.println("Could not parse node.");
            return null;
        }
    }

    public List<Blog> fetchBlogs(String resourcePath, final String sortOrder) {
        try {
            List<Blog> blogs = fetchBlogs(resourcePath);
            Collections.sort(blogs, new Comparator<Blog>() {
                public int compare(Blog o1, Blog o2) {
                    if ("desc".equals(sortOrder)) {
                        return o2.getPublishDate().compareTo(o1.getPublishDate());
                    } else {
                        return o1.getPublishDate().compareTo(o2.getPublishDate());
                    }
                }
            });
            return blogs;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Blog>();
        }
    }

    Blog bindResourceToBlog(Resource resource) {
        ValueMap valueMap = resource.getValueMap();
        Blog blog = new Blog();
        blog.setName(resource.getName());
        blog.setPath(resource.getPath());
        blog.setResourceType(resource.getResourceType());
        blog.setResourceSuperType(resource.getResourceSuperType());
        blog.setTitle(resource.getName());
        blog.setTitle(valueMap.get("Title").toString());
        blog.setDescription(valueMap.get("Description").toString());
        blog.setAuthor(valueMap.get("Author").toString());
        blog.setPublishDate(fetchDate(valueMap.get("Published Date").toString()));
        blog.setTags(valueMap.get("Tags").toString());
        System.out.println("blog: " + blog);
        return blog;
    }

    Date fetchDate(String dateString) {
        try {
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-dd-MM");
            return format1.parse(dateString);
        } catch (Exception e) {
            return new Date();
        }
    }

    Map<String, Object> fetchLoginCredentials() {
        Map<String, Object> loginCred = new HashMap<String, Object>();
        loginCred.put(ResourceResolverFactory.USER, "admin");
        loginCred.put(ResourceResolverFactory.PASSWORD, "admin".toCharArray());
        return loginCred;
    }
}
