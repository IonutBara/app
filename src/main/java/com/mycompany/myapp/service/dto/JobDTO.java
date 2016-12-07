package com.mycompany.myapp.service.dto;

/**
 * Created by ibara on 12/7/2016.
 */
public class JobDTO {

    private Long id;

    private String name;

    private String description;

    private String responsibilities;

    private String requirements;

    private String benefits;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public String getRequirements() {
        return requirements;
    }

    public String getBenefits() {
        return benefits;
    }

    public JobDTO(String name, String description, String responsibilities, String requirements, String benefits) {
        this.name = name;
        this.description = description;
        this.responsibilities = responsibilities;
        this.requirements = requirements;
        this.benefits = benefits;
    }

    public JobDTO() {
    }

    @Override
    public String toString() {
        return "JobDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", responsibilities='" + responsibilities + '\'' +
            ", requirements='" + requirements + '\'' +
            ", benefits='" + benefits + '\'' +
            '}';
    }
}
