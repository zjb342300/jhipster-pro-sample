package com.mycompany.myapp.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

// jhipster-needle-add-import - JHipster will add getters and setters here, do not remove

/**

 * {@link com.mycompany.myapp.domain.CommonExtData}的DTO。
 */
@ApiModel(description = "通用扩展数据")
public class CommonExtDataDTO implements Serializable {

    private Long id;

    /**
     * 属性名
     */
    @NotNull
    @Size(max = 100)
    @ApiModelProperty(value = "属性名", required = true)
    private String fieldName;

    /**
     * 通用字段类型
     */
    @ApiModelProperty(value = "通用字段类型")
    private String commonFieldType;

    /**
     * 通用数据Id
     */
    @ApiModelProperty(value = "通用数据Id")
    private Long commonFieldId;

    /**
     * 宿主实体名称
     */
    @ApiModelProperty(value = "宿主实体名称")
    private String ownerType;

    /**
     * 宿主实体Id
     */
    @ApiModelProperty(value = "宿主实体Id")
    private Long ownerId;

    // jhipster-needle-dto-add-field - JHipster will add fields here, do not remove

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getCommonFieldType() {
        return commonFieldType;
    }

    public void setCommonFieldType(String commonFieldType) {
        this.commonFieldType = commonFieldType;
    }

    public Long getCommonFieldId() {
        return commonFieldId;
    }

    public void setCommonFieldId(Long commonFieldId) {
        this.commonFieldId = commonFieldId;
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    // jhipster-needle-dto-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommonExtDataDTO)) {
            return false;
        }

        CommonExtDataDTO commonExtDataDTO = (CommonExtDataDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, commonExtDataDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CommonExtDataDTO{" +
            "id=" + getId() +
            ", fieldName='" + getFieldName() + "'" +
            ", commonFieldType='" + getCommonFieldType() + "'" +
            ", commonFieldId=" + getCommonFieldId() +
            ", ownerType='" + getOwnerType() + "'" +
            ", ownerId=" + getOwnerId() +
            "}";
    }
}
