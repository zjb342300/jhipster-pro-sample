package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mycompany.myapp.domain.enumeration.CommonFieldType;
import com.mycompany.myapp.domain.enumeration.EndUsedType;
import com.mycompany.myapp.domain.enumeration.FixedType;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 模型字段
 */

@Entity
@Table(name = "common_table_field")
@DynamicUpdate
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CommonTableField implements Serializable, Owner {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 标题
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "title", length = 100, nullable = false)
    private String title;

    /**
     * 属性名称
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "entity_field_name", length = 100, nullable = false)
    private String entityFieldName;

    /**
     * 数据类型
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private CommonFieldType type;

    /**
     * 字段名称
     */
    @NotNull
    @Size(max = 100)
    @Column(name = "table_column_name", length = 100, nullable = false)
    private String tableColumnName;

    /**
     * 列宽
     */
    @Min(value = 0)
    @Max(value = 1200)
    @Column(name = "column_width")
    private Integer columnWidth;

    /**
     * 显示顺序
     */
    @Column(name = "jhi_order")
    private Integer order;

    /**
     * 行内编辑
     */
    @Column(name = "edit_in_list")
    private Boolean editInList;

    /**
     * 列表隐藏
     */
    @Column(name = "hide_in_list")
    private Boolean hideInList;

    /**
     * 表单隐藏
     */
    @Column(name = "hide_in_form")
    private Boolean hideInForm;

    /**
     * 可过滤
     */
    @Column(name = "enable_filter")
    private Boolean enableFilter;

    /**
     * 验证规则
     */
    @Size(max = 800)
    @Column(name = "validate_rules", length = 800)
    private String validateRules;

    /**
     * 显示在过滤树
     */
    @Column(name = "show_in_filter_tree")
    private Boolean showInFilterTree;

    /**
     * 列固定
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "fixed")
    private FixedType fixed;

    /**
     * 可排序
     */
    @Column(name = "sortable")
    private Boolean sortable;

    /**
     * 树形标识
     */
    @Column(name = "tree_indicator")
    private Boolean treeIndicator;

    /**
     * 前端只读
     */
    @Column(name = "client_read_only")
    private Boolean clientReadOnly;

    /**
     * 值范围
     */
    @Size(max = 2000)
    @Column(name = "field_values", length = 2000)
    private String fieldValues;

    /**
     * 必填
     */
    @Column(name = "not_null")
    private Boolean notNull;

    /**
     * 系统字段
     */
    @Column(name = "jhi_system")
    private Boolean system;

    /**
     * 字段说明
     */
    @Size(max = 200)
    @Column(name = "help", length = 200)
    private String help;

    /**
     * 字体颜色
     */
    @Size(max = 80)
    @Column(name = "font_color", length = 80)
    private String fontColor;

    /**
     * 列背景色
     */
    @Size(max = 80)
    @Column(name = "background_color", length = 80)
    private String backgroundColor;

    /**
     * 空值隐藏
     */
    @Column(name = "null_hide_in_form")
    private Boolean nullHideInForm;

    /**
     * 前端用法
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "end_used")
    private EndUsedType endUsed;

    /**
     * 关系配置项
     */
    @Column(name = "options")
    private String options;

    /**
     * 元模型
     */
    @ManyToOne
    @JsonIgnoreProperties(value = { "commonTableFields", "relationships", "metaModel", "creator", "businessType" }, allowSetters = true)
    private CommonTable metaModel;

    /**
     * 所属表
     */
    @ManyToOne
    @JsonIgnoreProperties(value = { "commonTableFields", "relationships", "metaModel", "creator", "businessType" }, allowSetters = true)
    private CommonTable commonTable;

    @Transient
    private Map<String, Object> extData = new HashMap<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CommonTableField id(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return this.title;
    }

    public CommonTableField title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEntityFieldName() {
        return this.entityFieldName;
    }

    public CommonTableField entityFieldName(String entityFieldName) {
        this.entityFieldName = entityFieldName;
        return this;
    }

    public void setEntityFieldName(String entityFieldName) {
        this.entityFieldName = entityFieldName;
    }

    public CommonFieldType getType() {
        return this.type;
    }

    public CommonTableField type(CommonFieldType type) {
        this.type = type;
        return this;
    }

    public void setType(CommonFieldType type) {
        this.type = type;
    }

    public String getTableColumnName() {
        return this.tableColumnName;
    }

    public CommonTableField tableColumnName(String tableColumnName) {
        this.tableColumnName = tableColumnName;
        return this;
    }

    public void setTableColumnName(String tableColumnName) {
        this.tableColumnName = tableColumnName;
    }

    public Integer getColumnWidth() {
        return this.columnWidth;
    }

    public CommonTableField columnWidth(Integer columnWidth) {
        this.columnWidth = columnWidth;
        return this;
    }

    public void setColumnWidth(Integer columnWidth) {
        this.columnWidth = columnWidth;
    }

    public Integer getOrder() {
        return this.order;
    }

    public CommonTableField order(Integer order) {
        this.order = order;
        return this;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Boolean getEditInList() {
        return this.editInList;
    }

    public CommonTableField editInList(Boolean editInList) {
        this.editInList = editInList;
        return this;
    }

    public void setEditInList(Boolean editInList) {
        this.editInList = editInList;
    }

    public Boolean getHideInList() {
        return this.hideInList;
    }

    public CommonTableField hideInList(Boolean hideInList) {
        this.hideInList = hideInList;
        return this;
    }

    public void setHideInList(Boolean hideInList) {
        this.hideInList = hideInList;
    }

    public Boolean getHideInForm() {
        return this.hideInForm;
    }

    public CommonTableField hideInForm(Boolean hideInForm) {
        this.hideInForm = hideInForm;
        return this;
    }

    public void setHideInForm(Boolean hideInForm) {
        this.hideInForm = hideInForm;
    }

    public Boolean getEnableFilter() {
        return this.enableFilter;
    }

    public CommonTableField enableFilter(Boolean enableFilter) {
        this.enableFilter = enableFilter;
        return this;
    }

    public void setEnableFilter(Boolean enableFilter) {
        this.enableFilter = enableFilter;
    }

    public String getValidateRules() {
        return this.validateRules;
    }

    public CommonTableField validateRules(String validateRules) {
        this.validateRules = validateRules;
        return this;
    }

    public void setValidateRules(String validateRules) {
        this.validateRules = validateRules;
    }

    public Boolean getShowInFilterTree() {
        return this.showInFilterTree;
    }

    public CommonTableField showInFilterTree(Boolean showInFilterTree) {
        this.showInFilterTree = showInFilterTree;
        return this;
    }

    public void setShowInFilterTree(Boolean showInFilterTree) {
        this.showInFilterTree = showInFilterTree;
    }

    public FixedType getFixed() {
        return this.fixed;
    }

    public CommonTableField fixed(FixedType fixed) {
        this.fixed = fixed;
        return this;
    }

    public void setFixed(FixedType fixed) {
        this.fixed = fixed;
    }

    public Boolean getSortable() {
        return this.sortable;
    }

    public CommonTableField sortable(Boolean sortable) {
        this.sortable = sortable;
        return this;
    }

    public void setSortable(Boolean sortable) {
        this.sortable = sortable;
    }

    public Boolean getTreeIndicator() {
        return this.treeIndicator;
    }

    public CommonTableField treeIndicator(Boolean treeIndicator) {
        this.treeIndicator = treeIndicator;
        return this;
    }

    public void setTreeIndicator(Boolean treeIndicator) {
        this.treeIndicator = treeIndicator;
    }

    public Boolean getClientReadOnly() {
        return this.clientReadOnly;
    }

    public CommonTableField clientReadOnly(Boolean clientReadOnly) {
        this.clientReadOnly = clientReadOnly;
        return this;
    }

    public void setClientReadOnly(Boolean clientReadOnly) {
        this.clientReadOnly = clientReadOnly;
    }

    public String getFieldValues() {
        return this.fieldValues;
    }

    public CommonTableField fieldValues(String fieldValues) {
        this.fieldValues = fieldValues;
        return this;
    }

    public void setFieldValues(String fieldValues) {
        this.fieldValues = fieldValues;
    }

    public Boolean getNotNull() {
        return this.notNull;
    }

    public CommonTableField notNull(Boolean notNull) {
        this.notNull = notNull;
        return this;
    }

    public void setNotNull(Boolean notNull) {
        this.notNull = notNull;
    }

    public Boolean getSystem() {
        return this.system;
    }

    public CommonTableField system(Boolean system) {
        this.system = system;
        return this;
    }

    public void setSystem(Boolean system) {
        this.system = system;
    }

    public String getHelp() {
        return this.help;
    }

    public CommonTableField help(String help) {
        this.help = help;
        return this;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public String getFontColor() {
        return this.fontColor;
    }

    public CommonTableField fontColor(String fontColor) {
        this.fontColor = fontColor;
        return this;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public String getBackgroundColor() {
        return this.backgroundColor;
    }

    public CommonTableField backgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Boolean getNullHideInForm() {
        return this.nullHideInForm;
    }

    public CommonTableField nullHideInForm(Boolean nullHideInForm) {
        this.nullHideInForm = nullHideInForm;
        return this;
    }

    public void setNullHideInForm(Boolean nullHideInForm) {
        this.nullHideInForm = nullHideInForm;
    }

    public EndUsedType getEndUsed() {
        return this.endUsed;
    }

    public CommonTableField endUsed(EndUsedType endUsed) {
        this.endUsed = endUsed;
        return this;
    }

    public void setEndUsed(EndUsedType endUsed) {
        this.endUsed = endUsed;
    }

    public String getOptions() {
        return this.options;
    }

    public CommonTableField options(String options) {
        this.options = options;
        return this;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public CommonTable getMetaModel() {
        return this.metaModel;
    }

    public CommonTableField metaModel(CommonTable commonTable) {
        this.setMetaModel(commonTable);
        return this;
    }

    public void setMetaModel(CommonTable commonTable) {
        this.metaModel = commonTable;
    }

    public CommonTable getCommonTable() {
        return this.commonTable;
    }

    public CommonTableField commonTable(CommonTable commonTable) {
        this.setCommonTable(commonTable);
        return this;
    }

    public void setCommonTable(CommonTable commonTable) {
        this.commonTable = commonTable;
    }

    public void setExtData(Map<String, Object> extData) {
        this.extData = extData;
    }

    public CommonTableField extData(Map<String, Object> extData) {
        this.extData = extData;
        return this;
    }

    public Map<String, Object> getExtData() {
        return extData;
    }

    @Override
    public String getOwnerEntityName() {
        return this.getClass().getSimpleName();
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommonTableField)) {
            return false;
        }
        return id != null && id.equals(((CommonTableField) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CommonTableField{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", entityFieldName='" + getEntityFieldName() + "'" +
            ", type='" + getType() + "'" +
            ", tableColumnName='" + getTableColumnName() + "'" +
            ", columnWidth=" + getColumnWidth() +
            ", order=" + getOrder() +
            ", editInList='" + getEditInList() + "'" +
            ", hideInList='" + getHideInList() + "'" +
            ", hideInForm='" + getHideInForm() + "'" +
            ", enableFilter='" + getEnableFilter() + "'" +
            ", validateRules='" + getValidateRules() + "'" +
            ", showInFilterTree='" + getShowInFilterTree() + "'" +
            ", fixed='" + getFixed() + "'" +
            ", sortable='" + getSortable() + "'" +
            ", treeIndicator='" + getTreeIndicator() + "'" +
            ", clientReadOnly='" + getClientReadOnly() + "'" +
            ", fieldValues='" + getFieldValues() + "'" +
            ", notNull='" + getNotNull() + "'" +
            ", system='" + getSystem() + "'" +
            ", help='" + getHelp() + "'" +
            ", fontColor='" + getFontColor() + "'" +
            ", backgroundColor='" + getBackgroundColor() + "'" +
            ", nullHideInForm='" + getNullHideInForm() + "'" +
            ", endUsed='" + getEndUsed() + "'" +
            ", options='" + getOptions() + "'" +
            "}";
    }
}
