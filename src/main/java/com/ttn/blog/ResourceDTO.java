package com.ttn.blog;

public class ResourceDTO {

    String name;
    String path;
    String resourceType;
    String resourceSuperType;

    public ResourceDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourceSuperType() {
        return resourceSuperType;
    }

    public void setResourceSuperType(String resourceSuperType) {
        this.resourceSuperType = resourceSuperType;
    }
}
