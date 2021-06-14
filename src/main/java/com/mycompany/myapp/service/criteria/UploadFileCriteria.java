package com.mycompany.myapp.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.FloatFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;
import tech.jhipster.service.filter.ZonedDateTimeFilter;

/**
 * Criteria class for the {@link com.mycompany.myapp.domain.UploadFile} entity. This class is used
 * in {@link com.mycompany.myapp.web.rest.UploadFileResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /upload-files?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class UploadFileCriteria implements Serializable, Criteria {

    private String jhiCommonSearchKeywords;

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter fullName;

    private StringFilter name;

    private StringFilter ext;

    private StringFilter type;

    private StringFilter url;

    private StringFilter path;

    private StringFilter folder;

    private StringFilter entityName;

    private ZonedDateTimeFilter createAt;

    private LongFilter fileSize;

    private LongFilter referenceCount;

    private LongFilter userId;

    private StringFilter userLogin;

    private LongFilter categoryId;

    private StringFilter categoryTitle;

    public UploadFileCriteria() {}

    public UploadFileCriteria(UploadFileCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.fullName = other.fullName == null ? null : other.fullName.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.ext = other.ext == null ? null : other.ext.copy();
        this.type = other.type == null ? null : other.type.copy();
        this.url = other.url == null ? null : other.url.copy();
        this.path = other.path == null ? null : other.path.copy();
        this.folder = other.folder == null ? null : other.folder.copy();
        this.entityName = other.entityName == null ? null : other.entityName.copy();
        this.createAt = other.createAt == null ? null : other.createAt.copy();
        this.fileSize = other.fileSize == null ? null : other.fileSize.copy();
        this.referenceCount = other.referenceCount == null ? null : other.referenceCount.copy();
        this.userId = other.userId == null ? null : other.userId.copy();
        this.userLogin = other.userLogin == null ? null : other.userLogin.copy();
        this.categoryId = other.categoryId == null ? null : other.categoryId.copy();
        this.categoryTitle = other.categoryTitle == null ? null : other.categoryTitle.copy();
    }

    @Override
    public UploadFileCriteria copy() {
        return new UploadFileCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getFullName() {
        return fullName;
    }

    public StringFilter fullName() {
        if (fullName == null) {
            fullName = new StringFilter();
        }
        return fullName;
    }

    public void setFullName(StringFilter fullName) {
        this.fullName = fullName;
    }

    public StringFilter getName() {
        return name;
    }

    public StringFilter name() {
        if (name == null) {
            name = new StringFilter();
        }
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public StringFilter getExt() {
        return ext;
    }

    public StringFilter ext() {
        if (ext == null) {
            ext = new StringFilter();
        }
        return ext;
    }

    public void setExt(StringFilter ext) {
        this.ext = ext;
    }

    public StringFilter getType() {
        return type;
    }

    public StringFilter type() {
        if (type == null) {
            type = new StringFilter();
        }
        return type;
    }

    public void setType(StringFilter type) {
        this.type = type;
    }

    public StringFilter getUrl() {
        return url;
    }

    public StringFilter url() {
        if (url == null) {
            url = new StringFilter();
        }
        return url;
    }

    public void setUrl(StringFilter url) {
        this.url = url;
    }

    public StringFilter getPath() {
        return path;
    }

    public StringFilter path() {
        if (path == null) {
            path = new StringFilter();
        }
        return path;
    }

    public void setPath(StringFilter path) {
        this.path = path;
    }

    public StringFilter getFolder() {
        return folder;
    }

    public StringFilter folder() {
        if (folder == null) {
            folder = new StringFilter();
        }
        return folder;
    }

    public void setFolder(StringFilter folder) {
        this.folder = folder;
    }

    public StringFilter getEntityName() {
        return entityName;
    }

    public StringFilter entityName() {
        if (entityName == null) {
            entityName = new StringFilter();
        }
        return entityName;
    }

    public void setEntityName(StringFilter entityName) {
        this.entityName = entityName;
    }

    public ZonedDateTimeFilter getCreateAt() {
        return createAt;
    }

    public ZonedDateTimeFilter createAt() {
        if (createAt == null) {
            createAt = new ZonedDateTimeFilter();
        }
        return createAt;
    }

    public void setCreateAt(ZonedDateTimeFilter createAt) {
        this.createAt = createAt;
    }

    public LongFilter getFileSize() {
        return fileSize;
    }

    public LongFilter fileSize() {
        if (fileSize == null) {
            fileSize = new LongFilter();
        }
        return fileSize;
    }

    public void setFileSize(LongFilter fileSize) {
        this.fileSize = fileSize;
    }

    public LongFilter getReferenceCount() {
        return referenceCount;
    }

    public LongFilter referenceCount() {
        if (referenceCount == null) {
            referenceCount = new LongFilter();
        }
        return referenceCount;
    }

    public void setReferenceCount(LongFilter referenceCount) {
        this.referenceCount = referenceCount;
    }

    public LongFilter getUserId() {
        return userId;
    }

    public LongFilter userId() {
        if (userId == null) {
            userId = new LongFilter();
        }
        return userId;
    }

    public void setUserId(LongFilter userId) {
        this.userId = userId;
    }

    public StringFilter getUserLogin() {
        return userLogin;
    }

    public StringFilter userLogin() {
        if (userLogin == null) {
            userLogin = new StringFilter();
        }
        return userLogin;
    }

    public void setUserLogin(StringFilter userLogin) {
        this.userLogin = userLogin;
    }

    public LongFilter getCategoryId() {
        return categoryId;
    }

    public LongFilter categoryId() {
        if (categoryId == null) {
            categoryId = new LongFilter();
        }
        return categoryId;
    }

    public void setCategoryId(LongFilter categoryId) {
        this.categoryId = categoryId;
    }

    public StringFilter getCategoryTitle() {
        return categoryTitle;
    }

    public StringFilter categoryTitle() {
        if (categoryTitle == null) {
            categoryTitle = new StringFilter();
        }
        return categoryTitle;
    }

    public void setCategoryTitle(StringFilter categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getJhiCommonSearchKeywords() {
        return jhiCommonSearchKeywords;
    }

    public void setJhiCommonSearchKeywords(String jhiCommonSearchKeywords) {
        this.jhiCommonSearchKeywords = jhiCommonSearchKeywords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final UploadFileCriteria that = (UploadFileCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(fullName, that.fullName) &&
            Objects.equals(name, that.name) &&
            Objects.equals(ext, that.ext) &&
            Objects.equals(type, that.type) &&
            Objects.equals(url, that.url) &&
            Objects.equals(path, that.path) &&
            Objects.equals(folder, that.folder) &&
            Objects.equals(entityName, that.entityName) &&
            Objects.equals(createAt, that.createAt) &&
            Objects.equals(fileSize, that.fileSize) &&
            Objects.equals(referenceCount, that.referenceCount) &&
            Objects.equals(userId, that.userId) &&
            Objects.equals(userLogin, that.userLogin) &&
            Objects.equals(categoryId, that.categoryId) &&
            Objects.equals(categoryTitle, that.categoryTitle)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            fullName,
            name,
            ext,
            type,
            url,
            path,
            folder,
            entityName,
            createAt,
            fileSize,
            referenceCount,
            userId,
            userLogin,
            categoryId,
            categoryTitle
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UploadFileCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (fullName != null ? "fullName=" + fullName + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (ext != null ? "ext=" + ext + ", " : "") +
                (type != null ? "type=" + type + ", " : "") +
                (url != null ? "url=" + url + ", " : "") +
                (path != null ? "path=" + path + ", " : "") +
                (folder != null ? "folder=" + folder + ", " : "") +
                (entityName != null ? "entityName=" + entityName + ", " : "") +
                (createAt != null ? "createAt=" + createAt + ", " : "") +
                (fileSize != null ? "fileSize=" + fileSize + ", " : "") +
                (referenceCount != null ? "referenceCount=" + referenceCount + ", " : "") +
                (userId != null ? "userId=" + userId + ", " : "") +
                (userLogin != null ? "userLogin=" + userLogin + ", " : "") +
                (categoryId != null ? "categoryId=" + categoryId + ", " : "") +
                (categoryTitle != null ? "categoryTitle=" + categoryTitle + ", " : "") +
            "}";
    }
}
