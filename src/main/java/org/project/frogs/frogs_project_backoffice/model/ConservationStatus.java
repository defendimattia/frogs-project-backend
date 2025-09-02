package org.project.frogs.frogs_project_backoffice.model;

import java.util.Set;

import org.hibernate.validator.constraints.URL;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "conservationStatuses")
public class ConservationStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @NotBlank
    @Size(min = 5, max = 100, message = "description must be min 5 and max 100 characters long")
    private String description;

    @NotBlank
    @Pattern(regexp = "[A-Z]{2}")
    @Size(min = 2, max = 2, message = "IUCN code must be exactly 2 letters")
    private String iucnCode;

    @NotNull
    @Min(value = 0, message = "risk level must be at least 0")
    @Max(value = 5, message = "risk level cannot be greater than 5")
    private Integer riskLevel;

    @OneToMany(mappedBy = "conservationStatus")
    private Set<Frog> frogs;

    public ConservationStatus() {

    }

    public ConservationStatus(Integer id, String name, String description, String iucnCode, Integer riskLevel) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.iucnCode = iucnCode;
        this.riskLevel = riskLevel;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIucnCode() {
        return this.iucnCode;
    }

    public void setIucnCode(String iucnCode) {
        this.iucnCode = iucnCode;
    }

    public Integer getRiskLevel() {
        return this.riskLevel;
    }

    public void setRiskLevel(Integer riskLevel) {
        this.riskLevel = riskLevel;
    }

}
