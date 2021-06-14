package com.mycompany.myapp.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ReflectUtil;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.domain.ResourceCategory;
import com.mycompany.myapp.domain.enumeration.RelationshipType;
import com.mycompany.myapp.repository.CommonTableRepository;
import com.mycompany.myapp.repository.ResourceCategoryRepository;
import com.mycompany.myapp.service.criteria.ResourceCategoryCriteria;
import com.mycompany.myapp.service.dto.ResourceCategoryDTO;
import com.mycompany.myapp.service.mapper.ResourceCategoryMapper;
import com.mycompany.myapp.util.JpaUtil;
import java.util.*;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TupleElement;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.hibernate.SQLQuery;
import org.hibernate.query.criteria.internal.path.PluralAttributePath;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * 用于对数据库中的{@link ResourceCategory}实体执行复杂查询的Service。
 * 主要输入是一个{@link ResourceCategoryCriteria}，它被转换为{@link Specification}，
 * 所有字段过滤器都将应用到表达式中。
 * 它返回满足条件的{@link ResourceCategoryDTO}列表{@link List} 或 {@link ResourceCategoryDTO} 的分页列表 {@link Page}。
 */
@Service
@Transactional(readOnly = true)
public class ResourceCategoryQueryService extends QueryService<ResourceCategory> {

    private final Logger log = LoggerFactory.getLogger(ResourceCategoryQueryService.class);

    private final ResourceCategoryRepository resourceCategoryRepository;

    private final EntityManager em;

    private final CommonTableRepository commonTableRepository;

    private final ResourceCategoryMapper resourceCategoryMapper;

    public ResourceCategoryQueryService(
        ResourceCategoryRepository resourceCategoryRepository,
        EntityManager em,
        CommonTableRepository commonTableRepository,
        ResourceCategoryMapper resourceCategoryMapper
    ) {
        this.resourceCategoryRepository = resourceCategoryRepository;
        this.em = em;
        this.commonTableRepository = commonTableRepository;
        this.resourceCategoryMapper = resourceCategoryMapper;
    }

    /**
     * Return a {@link List} of {@link ResourceCategoryDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ResourceCategoryDTO> findByCriteria(ResourceCategoryCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ResourceCategory> specification = createSpecification(criteria);
        return resourceCategoryMapper.toDto(resourceCategoryRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ResourceCategoryDTO} which matches the criteria from the database.
     * @param specification The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ResourceCategoryDTO> findBySpecification(Specification<ResourceCategory> specification, Pageable page) {
        log.debug("find by criteria : {}, page: {}", specification, page);
        return resourceCategoryRepository.findAll(specification, page).map(resourceCategoryMapper::toDto);
    }

    /**
     * Return a {@link Page} of {@link ResourceCategoryDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ResourceCategoryDTO> findByCriteria(ResourceCategoryCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ResourceCategory> specification = createSpecification(criteria);
        return resourceCategoryRepository.findAll(specification, page).map(resourceCategoryMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ResourceCategoryCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ResourceCategory> specification = createSpecification(criteria);
        return resourceCategoryRepository.count(specification);
    }

    /**
     * Return the number of matching entities in the database.
     * @param specification The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countBySpecification(Specification specification) {
        log.debug("count by specification : {}", specification);
        return resourceCategoryRepository.count(specification);
    }

    /**
     * Function to convert {@link ResourceCategoryCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<ResourceCategory> createSpecification(ResourceCategoryCriteria criteria) {
        Specification<ResourceCategory> specification = Specification.where(null);
        if (criteria != null) {
            if (StringUtils.isNotEmpty(criteria.getJhiCommonSearchKeywords())) {
                if (StringUtils.isNumeric(criteria.getJhiCommonSearchKeywords())) {
                    specification =
                        specification.or(
                            buildSpecification(
                                new LongFilter().setEquals(Long.valueOf(criteria.getJhiCommonSearchKeywords())),
                                ResourceCategory_.id
                            )
                        );
                    specification =
                        specification.or(
                            buildRangeSpecification(
                                (LongFilter) new LongFilter().setEquals(Long.valueOf(criteria.getJhiCommonSearchKeywords())),
                                ResourceCategory_.id
                            )
                        );
                    specification =
                        specification.or(
                            buildRangeSpecification(
                                (IntegerFilter) new IntegerFilter().setEquals(Integer.valueOf(criteria.getJhiCommonSearchKeywords())),
                                ResourceCategory_.sort
                            )
                        );
                } else {
                    specification =
                        specification.or(
                            buildStringSpecification(
                                new StringFilter().setContains(criteria.getJhiCommonSearchKeywords()),
                                ResourceCategory_.title
                            )
                        );
                    specification =
                        specification.or(
                            buildStringSpecification(
                                new StringFilter().setContains(criteria.getJhiCommonSearchKeywords()),
                                ResourceCategory_.code
                            )
                        );
                }
            } else {
                if (criteria.getId() != null) {
                    specification = specification.and(buildRangeSpecification(criteria.getId(), ResourceCategory_.id));
                }
                if (criteria.getTitle() != null) {
                    specification = specification.and(buildStringSpecification(criteria.getTitle(), ResourceCategory_.title));
                }
                if (criteria.getCode() != null) {
                    specification = specification.and(buildStringSpecification(criteria.getCode(), ResourceCategory_.code));
                }
                if (criteria.getSort() != null) {
                    specification = specification.and(buildRangeSpecification(criteria.getSort(), ResourceCategory_.sort));
                }
                if (criteria.getFilesId() != null) {
                    specification =
                        specification.and(
                            buildSpecification(
                                criteria.getFilesId(),
                                root -> root.join(ResourceCategory_.files, JoinType.LEFT).get(UploadFile_.id)
                            )
                        );
                }
                if (criteria.getFilesUrl() != null) {
                    specification =
                        specification.and(
                            buildSpecification(
                                criteria.getFilesUrl(),
                                root -> root.join(ResourceCategory_.files, JoinType.LEFT).get(UploadFile_.url)
                            )
                        );
                }
                if (criteria.getChildrenId() != null) {
                    specification =
                        specification.and(
                            buildSpecification(
                                criteria.getChildrenId(),
                                root -> root.join(ResourceCategory_.children, JoinType.LEFT).get(ResourceCategory_.id)
                            )
                        );
                }
                if (criteria.getChildrenTitle() != null) {
                    specification =
                        specification.and(
                            buildSpecification(
                                criteria.getChildrenTitle(),
                                root -> root.join(ResourceCategory_.children, JoinType.LEFT).get(ResourceCategory_.title)
                            )
                        );
                }
                if (criteria.getImagesId() != null) {
                    specification =
                        specification.and(
                            buildSpecification(
                                criteria.getImagesId(),
                                root -> root.join(ResourceCategory_.images, JoinType.LEFT).get(UploadImage_.id)
                            )
                        );
                }
                if (criteria.getImagesUrl() != null) {
                    specification =
                        specification.and(
                            buildSpecification(
                                criteria.getImagesUrl(),
                                root -> root.join(ResourceCategory_.images, JoinType.LEFT).get(UploadImage_.url)
                            )
                        );
                }
                if (criteria.getParentId() != null) {
                    specification =
                        specification.and(
                            buildSpecification(
                                criteria.getParentId(),
                                root -> root.join(ResourceCategory_.parent, JoinType.LEFT).get(ResourceCategory_.id)
                            )
                        );
                }
                if (criteria.getParentTitle() != null) {
                    specification =
                        specification.and(
                            buildSpecification(
                                criteria.getParentTitle(),
                                root -> root.join(ResourceCategory_.parent, JoinType.LEFT).get(ResourceCategory_.title)
                            )
                        );
                }
            }
        }
        return specification;
    }

    @Transactional
    public boolean updateBySpecifield(String fieldName, Object value, ResourceCategoryCriteria criteria) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaUpdate<ResourceCategory> q = cb.createCriteriaUpdate(ResourceCategory.class);
        CriteriaQuery<ResourceCategory> sq = cb.createQuery(ResourceCategory.class);
        Root<ResourceCategory> root = q.from(ResourceCategory.class);
        q.set(root.get(fieldName), value).where(createSpecification(criteria).toPredicate(root, sq, cb));
        int result = em.createQuery(q).executeUpdate();
        em.flush();
        return result > 0;
    }

    /**
     * 直接转换为dto。maytoone的，直接查询结果。one-to-many和many-to-many后续加载
     * @param entityName 模型名称
     * @param criteria 条件表达式
     * @param predicate 条件
     * @param pageable 分页
     * @return Page<ResourceCategoryDTO>
     */
    @Transactional(readOnly = true)
    public Page<ResourceCategoryDTO> selectByCustomEntity(
        String entityName,
        ResourceCategoryCriteria criteria,
        Predicate predicate,
        Specification specification,
        Pageable pageable
    ) {
        List<ResourceCategory> dataList = new ArrayList<>();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> q = cb.createTupleQuery();
        Root<ResourceCategory> root = q.from(ResourceCategory.class);
        if (StringUtils.isEmpty(entityName)) {
            entityName = "ResourceCategory";
        }
        Optional<CommonTable> oneByEntityName = commonTableRepository.findOneByEntityName(entityName);
        List<String> fields = new ArrayList<>();
        List<CommonTableRelationship> toManyRelationships = new ArrayList<>();
        List<CommonTableRelationship> manyToOneRelationships = new ArrayList<>();
        List<Selection<?>> s = new ArrayList<>();
        if (oneByEntityName.isPresent()) {
            List<CommonTableField> tableFields = oneByEntityName
                .get()
                .getCommonTableFields()
                .stream()
                .filter(commonTableField -> !commonTableField.getHideInList())
                .collect(Collectors.toList());
            tableFields.forEach(commonTableField -> fields.add(commonTableField.getEntityFieldName()));
            List<CommonTableRelationship> tableRelationships = oneByEntityName
                .get()
                .getRelationships()
                .stream()
                .filter(commonTableRelationship -> !commonTableRelationship.getHideInList())
                .collect(Collectors.toList());
            tableRelationships.forEach(
                commonTableRelationship -> {
                    if (
                        commonTableRelationship.getRelationshipType().equals(RelationshipType.ONE_TO_MANY) ||
                        commonTableRelationship.getRelationshipType().equals(RelationshipType.MANY_TO_MANY)
                    ) {
                        toManyRelationships.add(commonTableRelationship);
                    } else {
                        try {
                            String clazzName =
                                ResourceCategory.class.getDeclaredField(commonTableRelationship.getRelationshipName()).getType().getName();
                            manyToOneRelationships.add(commonTableRelationship);
                            root.join(commonTableRelationship.getRelationshipName(), JoinType.LEFT);
                            String idFieldName = JpaUtil.getPkColumn(clazzName);
                            String alaisId = commonTableRelationship.getRelationshipName() + "." + idFieldName;
                            s.add(root.get(commonTableRelationship.getRelationshipName()).get(idFieldName).alias(alaisId));
                            String relationPredicateAlias =
                                commonTableRelationship.getRelationshipName() + "." + commonTableRelationship.getOtherEntityField();
                            if (s.stream().noneMatch(predicateRelation -> predicateRelation.getAlias().equals(relationPredicateAlias))) {
                                s.add(
                                    root
                                        .get(commonTableRelationship.getRelationshipName())
                                        .get(commonTableRelationship.getOtherEntityField())
                                        .alias(relationPredicateAlias)
                                );
                            }
                        } catch (NoSuchFieldException e) {
                            log.error(e.getMessage());
                        }
                    }
                }
            );
        }
        s.addAll(fields.stream().map(fieldName -> root.get(fieldName).alias(fieldName)).collect(Collectors.toList()));
        q.multiselect(s);
        Predicate criteriaPredicate = createSpecification(criteria).toPredicate(root, q, cb);
        Predicate specificationPredicate = specification == null ? null : specification.toPredicate(root, q, cb);
        if (criteriaPredicate != null) {
            q.where(criteriaPredicate);
        } else if (predicate != null) {
            q.where(predicate);
        } else if (specificationPredicate != null) {
            q.where(specificationPredicate);
        }
        q.distinct(true);
        long totalItems;
        if (specificationPredicate != null) {
            totalItems = countBySpecification(specification);
        } else {
            totalItems = countByCriteria(criteria);
        }
        if (totalItems > 0) {
            if (pageable != null) {
                List<Order> orders = new ArrayList<>();
                pageable
                    .getSort()
                    .forEach(
                        order ->
                            orders.add(order.isAscending() ? cb.asc(root.get(order.getProperty())) : cb.desc(root.get(order.getProperty())))
                    );
                q.orderBy(orders);
            }
            TypedQuery<Tuple> query = em.createQuery(q);
            if (pageable != null) {
                pageable.getSort().toList();
                query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
                query.setMaxResults(pageable.getPageSize());
            }
            List<Tuple> list = query.getResultList();
            for (Tuple tu : list) {
                Map<String, Object> itemmap = new HashMap<>();
                for (TupleElement<?> element : tu.getElements()) {
                    itemmap.put(element.getAlias(), tu.get(element.getAlias()));
                }
                ResourceCategory resourceCategory = BeanUtil.mapToBean(itemmap, ResourceCategory.class, true);
                // 组装manytoone的内容
                manyToOneRelationships.forEach(
                    relationship -> {
                        try {
                            String clazzName =
                                ResourceCategory.class.getDeclaredField(relationship.getRelationshipName()).getType().getName();
                            String idFieldName = JpaUtil.getPkColumn(clazzName);
                            String alaisId = relationship.getRelationshipName() + "." + idFieldName;
                            String alaisField = relationship.getRelationshipName() + "." + relationship.getOtherEntityField();
                            Object relationObject = ReflectUtil.newInstance(clazzName);
                            if (itemmap.get(alaisId) != null) {
                                BeanUtil.setFieldValue(relationObject, idFieldName, itemmap.get(alaisId));
                                if (!alaisField.equals(alaisId)) {
                                    BeanUtil.setFieldValue(relationObject, relationship.getOtherEntityField(), itemmap.get(alaisField));
                                }
                                BeanUtil.setFieldValue(resourceCategory, relationship.getRelationshipName(), relationObject);
                            }
                        } catch (NoSuchFieldException e) {
                            log.error(e.getMessage());
                        }
                    }
                );
                // 组装manytomany等的内容
                toManyRelationships
                    .stream()
                    .filter(
                        relationship ->
                            ObjectUtils.isNotEmpty(FieldUtils.getField(ResourceCategory.class, relationship.getRelationshipName()))
                    )
                    .forEach(
                        relationshipTemp -> {
                            CriteriaQuery<Tuple> subQuery = cb.createTupleQuery();
                            Class<?> clazz =
                                ((PluralAttributePath<?>) root.get(relationshipTemp.getRelationshipName())).getAttribute()
                                    .getElementType()
                                    .getJavaType();
                            Root<?> manyRoot = subQuery.from(clazz);
                            if (clazz.getSimpleName().equals("ResourceCategory")) {
                                BeanUtil.setFieldValue(
                                    resourceCategory,
                                    relationshipTemp.getRelationshipName(),
                                    this.selectByCustomEntity(
                                            "ResourceCategory",
                                            null,
                                            cb.equal(
                                                manyRoot.get(relationshipTemp.getOtherEntityRelationshipName()).get("id"),
                                                itemmap.get("id")
                                            ),
                                            null,
                                            null
                                        )
                                );
                            } else {
                                Selection<Object> idAlias = manyRoot.get("id").alias("id");
                                if (relationshipTemp.getOtherEntityField() != null) {
                                    subQuery.multiselect(
                                        idAlias,
                                        manyRoot.get(relationshipTemp.getOtherEntityField()).alias(relationshipTemp.getOtherEntityField())
                                    );
                                    subQuery.where(
                                        cb.equal(
                                            manyRoot.get(relationshipTemp.getOtherEntityRelationshipName()).get("id"),
                                            itemmap.get("id")
                                        )
                                    );
                                    Set<Object> subdataList = new LinkedHashSet<>();
                                    List<Tuple> sublist = em.createQuery(subQuery).getResultList();
                                    for (Tuple stu : sublist) {
                                        Map<String, Object> subitemmap = new HashMap<>();
                                        for (TupleElement<?> element : stu.getElements()) {
                                            subitemmap.put(element.getAlias(), stu.get(element.getAlias()));
                                        }
                                        subdataList.add(BeanUtil.mapToBean(subitemmap, clazz, true));
                                    }
                                    BeanUtil.setFieldValue(resourceCategory, relationshipTemp.getRelationshipName(), subdataList);
                                } else {
                                    // todo 暂时不予处理getOtherEntityField为null的情况。
                                    log.error("ResourceCategory." + relationshipTemp.getRelationshipName() + "otherEntityField为空。");
                                }
                            }
                        }
                    );
                dataList.add(resourceCategory);
            }
        }
        return new PageImpl<>(resourceCategoryMapper.toDto(dataList), pageable == null ? Pageable.unpaged() : pageable, totalItems);
    }

    public List<Map<String, Object>> findAllByJpql(String jqpl) {
        Query query = em.createQuery(jqpl);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.getResultList();
    }

    public Map<String, Object> getByJpql(String jqpl) {
        Query query = em.createQuery(jqpl);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return (Map<String, Object>) query.getSingleResult();
    }

    public String toUpperFirstChar2(String string) {
        char[] chars = string.toCharArray();
        if (chars[0] >= 'a' && chars[0] <= 'z') {
            chars[0] -= 32;
            return String.valueOf(chars);
        }
        return string;
    }
}
