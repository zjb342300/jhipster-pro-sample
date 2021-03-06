package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 数据字典
 */

@Entity
@Table(name = "data_dictionary")
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DataDictionary implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 代码
     */
    @Column(name = "code")
    private String code;

    /**
     * 描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 字体颜色
     */
    @Column(name = "font_color")
    private String fontColor;

    /**
     * 背景颜色
     */
    @Column(name = "background_color")
    private String backgroundColor;

    /**
     * 子节点
     */
    @OneToMany(mappedBy = "parent")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "children", "parent" }, allowSetters = true)
    private Set<DataDictionary> children = new LinkedHashSet<>();

    /**
     * 上级节点
     */
    @ManyToOne
    @JsonIgnoreProperties(value = { "children", "parent" }, allowSetters = true)
    private DataDictionary parent;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DataDictionary id(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public DataDictionary name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public DataDictionary code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return this.description;
    }

    public DataDictionary description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFontColor() {
        return this.fontColor;
    }

    public DataDictionary fontColor(String fontColor) {
        this.fontColor = fontColor;
        return this;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public String getBackgroundColor() {
        return this.backgroundColor;
    }

    public DataDictionary backgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Set<DataDictionary> getChildren() {
        return this.children;
    }

    public DataDictionary children(Set<DataDictionary> dataDictionaries) {
        this.setChildren(dataDictionaries);
        return this;
    }

    public DataDictionary addChildren(DataDictionary dataDictionary) {
        this.children.add(dataDictionary);
        dataDictionary.setParent(this);
        return this;
    }

    public DataDictionary removeChildren(DataDictionary dataDictionary) {
        this.children.remove(dataDictionary);
        dataDictionary.setParent(null);
        return this;
    }

    public void setChildren(Set<DataDictionary> dataDictionaries) {
        if (this.children != null) {
            this.children.forEach(i -> i.setParent(null));
        }
        if (dataDictionaries != null) {
            dataDictionaries.forEach(i -> i.setParent(this));
        }
        this.children = dataDictionaries;
    }

    public DataDictionary getParent() {
        return this.parent;
    }

    public DataDictionary parent(DataDictionary dataDictionary) {
        this.setParent(dataDictionary);
        return this;
    }

    public void setParent(DataDictionary dataDictionary) {
        this.parent = dataDictionary;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DataDictionary)) {
            return false;
        }
        return id != null && id.equals(((DataDictionary) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DataDictionary{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", code='" + getCode() + "'" +
            ", description='" + getDescription() + "'" +
            ", fontColor='" + getFontColor() + "'" +
            ", backgroundColor='" + getBackgroundColor() + "'" +
            "}";
    }
}
