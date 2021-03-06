package com.mycompany.myapp.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ReflectUtil;
import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.domain.CommonTable;
import com.mycompany.myapp.domain.enumeration.RelationshipType;
import com.mycompany.myapp.repository.CommonTableRepository;
import com.mycompany.myapp.service.criteria.CommonTableCriteria;
import com.mycompany.myapp.service.dto.CommonTableDTO;
import com.mycompany.myapp.service.mapper.CommonTableMapper;
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
import tech.jhipster.service.filter.ZonedDateTimeFilter;

/**
 * 用于对数据库中的{@link CommonTable}实体执行复杂查询的Service。
 * 主要输入是一个{@link CommonTableCriteria}，它被转换为{@link Specification}，
 * 所有字段过滤器都将应用到表达式中。
 * 它返回满足条件的{@link CommonTableDTO}列表{@link List} 或 {@link CommonTableDTO} 的分页列表 {@link Page}。
 */
@Service
@Transactional(readOnly = true)
public class CommonTableQueryService extends QueryService<CommonTable> {

    private final Logger log = LoggerFactory.getLogger(CommonTableQueryService.class);

    private final CommonTableRepository commonTableRepository;

    private final EntityManager em;

    private final CommonTableMapper commonTableMapper;

    public CommonTableQueryService(CommonTableRepository commonTableRepository, EntityManager em, CommonTableMapper commonTableMapper) {
        this.commonTableRepository = commonTableRepository;
        this.em = em;
        this.commonTableMapper = commonTableMapper;
    }

    /**
     * Return a {@link List} of {@link CommonTableDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CommonTableDTO> findByCriteria(CommonTableCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CommonTable> specification = createSpecification(criteria);
        return commonTableMapper.toDto(commonTableRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CommonTableDTO} which matches the criteria from the database.
     * @param specification The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CommonTableDTO> findBySpecification(Specification<CommonTable> specification, Pageable page) {
        log.debug("find by criteria : {}, page: {}", specification, page);
        return commonTableRepository.findAll(specification, page).map(commonTableMapper::toDto);
    }

    /**
     * Return a {@link Page} of {@link CommonTableDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CommonTableDTO> findByCriteria(CommonTableCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CommonTable> specification = createSpecification(criteria);
        return commonTableRepository.findAll(specification, page).map(commonTableMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CommonTableCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CommonTable> specification = createSpecification(criteria);
        return commonTableRepository.count(specification);
    }

    /**
     * Return the number of matching entities in the database.
     * @param specification The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countBySpecification(Specification specification) {
        log.debug("count by specification : {}", specification);
        return commonTableRepository.count(specification);
    }

    /**
     * Function to convert {@link CommonTableCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CommonTable> createSpecification(CommonTableCriteria criteria) {
        Specification<CommonTable> specification = Specification.where(null);
        if (criteria != null) {
            if (StringUtils.isNotEmpty(criteria.getJhiCommonSearchKeywords())) {
                if (StringUtils.isNumeric(criteria.getJhiCommonSearchKeywords())) {
                    specification =
                        specification.or(
                            buildSpecification(
                                new LongFilter().setEquals(Long.valueOf(criteria.getJhiCommonSearchKeywords())),
                                CommonTable_.id
                            )
                        );
                    specification =
                        specification.or(
                            buildRangeSpecification(
                                (LongFilter) new LongFilter().setEquals(Long.valueOf(criteria.getJhiCommonSearchKeywords())),
                                CommonTable_.id
                            )
                        );
                    specification =
                        specification.or(
                            buildRangeSpecification(
                                (LongFilter) new LongFilter().setEquals(Long.valueOf(criteria.getJhiCommonSearchKeywords())),
                                CommonTable_.baseTableId
                            )
                        );
                    specification =
                        specification.or(
                            buildRangeSpecification(
                                (IntegerFilter) new IntegerFilter().setEquals(Integer.valueOf(criteria.getJhiCommonSearchKeywords())),
                                CommonTable_.recordActionWidth
                            )
                        );
                } else {
                    specification =
                        specification.or(
                            buildStringSpecification(
                                new StringFilter().setContains(criteria.getJhiCommonSearchKeywords()),
                                CommonTable_.name
                            )
                        );
                    specification =
                        specification.or(
                            buildStringSpecification(
                                new StringFilter().setContains(criteria.getJhiCommonSearchKeywords()),
                                CommonTable_.entityName
                            )
                        );
                    specification =
                        specification.or(
                            buildStringSpecification(
                                new StringFilter().setContains(criteria.getJhiCommonSearchKeywords()),
                                CommonTable_.tableName
                            )
                        );
                    specification =
                        specification.or(
                            buildStringSpecification(
                                new StringFilter().setContains(criteria.getJhiCommonSearchKeywords()),
                                CommonTable_.clazzName
                            )
                        );
                    specification =
                        specification.or(
                            buildStringSpecification(
                                new StringFilter().setContains(criteria.getJhiCommonSearchKeywords()),
                                CommonTable_.description
                            )
                        );
                }
            } else {
                if (criteria.getId() != null) {
                    specification = specification.and(buildRangeSpecification(criteria.getId(), CommonTable_.id));
                }
                if (criteria.getName() != null) {
                    specification = specification.and(buildStringSpecification(criteria.getName(), CommonTable_.name));
                }
                if (criteria.getEntityName() != null) {
                    specification = specification.and(buildStringSpecification(criteria.getEntityName(), CommonTable_.entityName));
                }
                if (criteria.getTableName() != null) {
                    specification = specification.and(buildStringSpecification(criteria.getTableName(), CommonTable_.tableName));
                }
                if (criteria.getSystem() != null) {
                    specification = specification.and(buildSpecification(criteria.getSystem(), CommonTable_.system));
                }
                if (criteria.getClazzName() != null) {
                    specification = specification.and(buildStringSpecification(criteria.getClazzName(), CommonTable_.clazzName));
                }
                if (criteria.getGenerated() != null) {
                    specification = specification.and(buildSpecification(criteria.getGenerated(), CommonTable_.generated));
                }
                if (criteria.getCreatAt() != null) {
                    specification = specification.and(buildRangeSpecification(criteria.getCreatAt(), CommonTable_.creatAt));
                }
                if (criteria.getGenerateAt() != null) {
                    specification = specification.and(buildRangeSpecification(criteria.getGenerateAt(), CommonTable_.generateAt));
                }
                if (criteria.getGenerateClassAt() != null) {
                    specification = specification.and(buildRangeSpecification(criteria.getGenerateClassAt(), CommonTable_.generateClassAt));
                }
                if (criteria.getDescription() != null) {
                    specification = specification.and(buildStringSpecification(criteria.getDescription(), CommonTable_.description));
                }
                if (criteria.getTreeTable() != null) {
                    specification = specification.and(buildSpecification(criteria.getTreeTable(), CommonTable_.treeTable));
                }
                if (criteria.getBaseTableId() != null) {
                    specification = specification.and(buildRangeSpecification(criteria.getBaseTableId(), CommonTable_.baseTableId));
                }
                if (criteria.getRecordActionWidth() != null) {
                    specification =
                        specification.and(buildRangeSpecification(criteria.getRecordActionWidth(), CommonTable_.recordActionWidth));
                }
                if (criteria.getEditInModal() != null) {
                    specification = specification.and(buildSpecification(criteria.getEditInModal(), CommonTable_.editInModal));
                }
                if (criteria.getCommonTableFieldsId() != null) {
                    specification =
                        specification.and(
                            buildSpecification(
                                criteria.getCommonTableFieldsId(),
                                root -> root.join(CommonTable_.commonTableFields, JoinType.LEFT).get(CommonTableField_.id)
                            )
                        );
                }
                if (criteria.getCommonTableFieldsTitle() != null) {
                    specification =
                        specification.and(
                            buildSpecification(
                                criteria.getCommonTableFieldsTitle(),
                                root -> root.join(CommonTable_.commonTableFields, JoinType.LEFT).get(CommonTableField_.title)
                            )
                        );
                }
                if (criteria.getRelationshipsId() != null) {
                    specification =
                        specification.and(
                            buildSpecification(
                                criteria.getRelationshipsId(),
                                root -> root.join(CommonTable_.relationships, JoinType.LEFT).get(CommonTableRelationship_.id)
                            )
                        );
                }
                if (criteria.getRelationshipsName() != null) {
                    specification =
                        specification.and(
                            buildSpecification(
                                criteria.getRelationshipsName(),
                                root -> root.join(CommonTable_.relationships, JoinType.LEFT).get(CommonTableRelationship_.name)
                            )
                        );
                }
                if (criteria.getMetaModelId() != null) {
                    specification =
                        specification.and(
                            buildSpecification(
                                criteria.getMetaModelId(),
                                root -> root.join(CommonTable_.metaModel, JoinType.LEFT).get(CommonTable_.id)
                            )
                        );
                }
                if (criteria.getMetaModelName() != null) {
                    specification =
                        specification.and(
                            buildSpecification(
                                criteria.getMetaModelName(),
                                root -> root.join(CommonTable_.metaModel, JoinType.LEFT).get(CommonTable_.name)
                            )
                        );
                }
                if (criteria.getCreatorId() != null) {
                    specification =
                        specification.and(
                            buildSpecification(
                                criteria.getCreatorId(),
                                root -> root.join(CommonTable_.creator, JoinType.LEFT).get(User_.id)
                            )
                        );
                }
                if (criteria.getCreatorLogin() != null) {
                    specification =
                        specification.and(
                            buildSpecification(
                                criteria.getCreatorLogin(),
                                root -> root.join(CommonTable_.creator, JoinType.LEFT).get(User_.login)
                            )
                        );
                }
                if (criteria.getBusinessTypeId() != null) {
                    specification =
                        specification.and(
                            buildSpecification(
                                criteria.getBusinessTypeId(),
                                root -> root.join(CommonTable_.businessType, JoinType.LEFT).get(BusinessType_.id)
                            )
                        );
                }
                if (criteria.getBusinessTypeName() != null) {
                    specification =
                        specification.and(
                            buildSpecification(
                                criteria.getBusinessTypeName(),
                                root -> root.join(CommonTable_.businessType, JoinType.LEFT).get(BusinessType_.name)
                            )
                        );
                }
            }
        }
        return specification;
    }

    @Transactional
    public boolean updateBySpecifield(String fieldName, Object value, CommonTableCriteria criteria) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaUpdate<CommonTable> q = cb.createCriteriaUpdate(CommonTable.class);
        CriteriaQuery<CommonTable> sq = cb.createQuery(CommonTable.class);
        Root<CommonTable> root = q.from(CommonTable.class);
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
     * @return Page<CommonTableDTO>
     */
    @Transactional(readOnly = true)
    public Page<CommonTableDTO> selectByCustomEntity(
        String entityName,
        CommonTableCriteria criteria,
        Predicate predicate,
        Specification specification,
        Pageable pageable
    ) {
        List<CommonTable> dataList = new ArrayList<>();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> q = cb.createTupleQuery();
        Root<CommonTable> root = q.from(CommonTable.class);
        if (StringUtils.isEmpty(entityName)) {
            entityName = "CommonTable";
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
                                CommonTable.class.getDeclaredField(commonTableRelationship.getRelationshipName()).getType().getName();
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
                CommonTable commonTable = BeanUtil.mapToBean(itemmap, CommonTable.class, true);
                // 组装manytoone的内容
                manyToOneRelationships.forEach(
                    relationship -> {
                        try {
                            String clazzName = CommonTable.class.getDeclaredField(relationship.getRelationshipName()).getType().getName();
                            String idFieldName = JpaUtil.getPkColumn(clazzName);
                            String alaisId = relationship.getRelationshipName() + "." + idFieldName;
                            String alaisField = relationship.getRelationshipName() + "." + relationship.getOtherEntityField();
                            Object relationObject = ReflectUtil.newInstance(clazzName);
                            if (itemmap.get(alaisId) != null) {
                                BeanUtil.setFieldValue(relationObject, idFieldName, itemmap.get(alaisId));
                                if (!alaisField.equals(alaisId)) {
                                    BeanUtil.setFieldValue(relationObject, relationship.getOtherEntityField(), itemmap.get(alaisField));
                                }
                                BeanUtil.setFieldValue(commonTable, relationship.getRelationshipName(), relationObject);
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
                        relationship -> ObjectUtils.isNotEmpty(FieldUtils.getField(CommonTable.class, relationship.getRelationshipName()))
                    )
                    .forEach(
                        relationshipTemp -> {
                            CriteriaQuery<Tuple> subQuery = cb.createTupleQuery();
                            Class<?> clazz =
                                ((PluralAttributePath<?>) root.get(relationshipTemp.getRelationshipName())).getAttribute()
                                    .getElementType()
                                    .getJavaType();
                            Root<?> manyRoot = subQuery.from(clazz);
                            if (clazz.getSimpleName().equals("CommonTable")) {
                                BeanUtil.setFieldValue(
                                    commonTable,
                                    relationshipTemp.getRelationshipName(),
                                    this.selectByCustomEntity(
                                            "CommonTable",
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
                                    BeanUtil.setFieldValue(commonTable, relationshipTemp.getRelationshipName(), subdataList);
                                } else {
                                    // todo 暂时不予处理getOtherEntityField为null的情况。
                                    log.error("CommonTable." + relationshipTemp.getRelationshipName() + "otherEntityField为空。");
                                }
                            }
                        }
                    );
                dataList.add(commonTable);
            }
        }
        return new PageImpl<>(commonTableMapper.toDto(dataList), pageable == null ? Pageable.unpaged() : pageable, totalItems);
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
