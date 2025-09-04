package org.project.frogs.frogs_project_backoffice.model;

import java.util.Set;

import org.hibernate.validator.constraints.URL;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "frogs")
public class Frog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "common name cannot be empty")
    @Size(min = 3, message = "common name must be at least 3 chars long")
    private String commonName;

    @NotBlank(message = "scientific name cannot be empty")
    @Size(min = 3, message = "scientific name must be at least 3 chars long")
    private String scientificName;

    @NotBlank(message = "color cannot be empty")
    @Size(min = 3, max = 50, message = "color must be min 3 and max 50 characters long")
    private String color;

    @NotNull(message = "average weight cannot be empty")
    @Positive(message = "average weight must be greater than 0")
    private Integer averageWeight;

    @Lob
    private String description;

    private Boolean toxicity;

    @URL(message = "must be a valid URL")
    @Size(max = 500, message = "image URL link too long")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "conservationStatus_id", nullable = false)
    @NotNull(message = "a frog must have a conservation status")
    private ConservationStatus conservationStatus;

    @ManyToMany
    @JoinTable(name = "frog_habitat", joinColumns = @JoinColumn(name = "frog_id"), inverseJoinColumns = @JoinColumn(name = "habitat_id"))
    @NotEmpty(message = "a frog must have at least one habitat")
    private Set<Habitat> habitats;

    public Frog() {
    }

    public Frog(Integer id, String commonName, String scientificName, String color, Integer averageWeight,
            String description, ConservationStatus conservationStatus, Set<Habitat> habitats) {
        this.id = id;
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.color = color;
        this.averageWeight = averageWeight;
        this.description = description;
        this.conservationStatus = conservationStatus;
        this.habitats = habitats;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommonName() {
        return this.commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getScientificName() {
        return this.scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getAverageWeight() {
        return this.averageWeight;
    }

    public void setAverageWeight(Integer averageWeight) {
        this.averageWeight = averageWeight;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ConservationStatus getConservationStatus() {
        return this.conservationStatus;
    }

    public void setConservationStatus(ConservationStatus conservationStatus) {
        this.conservationStatus = conservationStatus;
    }

    public Set<Habitat> getHabitats() {
        return this.habitats;
    }

    public void setHabitats(Set<Habitat> habitats) {
        this.habitats = habitats;
    }

    public Boolean isToxicity() {
        return this.toxicity;
    }

    public Boolean getToxicity() {
        return this.toxicity;
    }

    public void setToxicity(Boolean toxicity) {
        this.toxicity = toxicity;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
