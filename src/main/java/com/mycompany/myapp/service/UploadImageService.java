package com.mycompany.myapp.service;

import cn.hutool.core.bean.BeanUtil;
import com.mycompany.myapp.config.ApplicationProperties;
import com.mycompany.myapp.config.Constants;
import com.mycompany.myapp.domain.UploadImage;
import com.mycompany.myapp.oss.OssTemplate;
import com.mycompany.myapp.oss.model.BladeFile;
import com.mycompany.myapp.repository.UploadImageRepository;
import com.mycompany.myapp.repository.UserRepository;
import com.mycompany.myapp.security.SecurityUtils;
import com.mycompany.myapp.service.dto.UploadImageDTO;
import com.mycompany.myapp.service.mapper.UploadImageMapper;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// jhipster-needle-add-import - JHipster will add getters and setters here, do not remove

/**
 * Service Implementation for managing {@link UploadImage}.
 */
@Service
@Transactional
public class UploadImageService {

    private final Logger log = LoggerFactory.getLogger(UploadImageService.class);
    private final List<String> relationCacheNames = Arrays.asList(com.mycompany.myapp.domain.User.class.getName() + ".uploadImage");

    private final UploadImageRepository uploadImageRepository;

    private final CacheManager cacheManager;

    private final UploadImageMapper uploadImageMapper;

    private final ApplicationProperties applicationProperties;

    private final UserRepository userRepository;

    private final OssTemplate ossTemplate;

    public UploadImageService(
        UploadImageRepository uploadImageRepository,
        CacheManager cacheManager,
        UploadImageMapper uploadImageMapper,
        ApplicationProperties applicationProperties,
        UserRepository userRepository,
        OssTemplate ossTemplate
    ) {
        this.uploadImageRepository = uploadImageRepository;
        this.cacheManager = cacheManager;
        this.uploadImageMapper = uploadImageMapper;
        this.applicationProperties = applicationProperties;
        this.userRepository = userRepository;
        this.ossTemplate = ossTemplate;
    }

    /**
     * Save a uploadImage.
     *
     * @param uploadImageDTO the entity to save
     * @return the persisted entity
     */
    public UploadImageDTO save(UploadImageDTO uploadImageDTO) throws Exception {
        log.debug("Request to save UploadImage : {}", uploadImageDTO);
        if (!uploadImageDTO.getImage().isEmpty()) {
            final String extName = FilenameUtils.getExtension(uploadImageDTO.getImage().getOriginalFilename());
            final String yearAndMonth = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM"));
            final String savePathNew =
                Constants.DATA_PATH +
                File.separator +
                applicationProperties.getUpload().getRootPath() +
                File.separator +
                yearAndMonth +
                File.separator;
            BladeFile bladeFile = ossTemplate.putFile(
                uploadImageDTO.getImage().getOriginalFilename(),
                uploadImageDTO.getImage().getInputStream()
            );

            uploadImageDTO.setCreateAt(ZonedDateTime.now());
            uploadImageDTO.setExt(extName);
            uploadImageDTO.setFullName(uploadImageDTO.getImage().getOriginalFilename());
            uploadImageDTO.setName(uploadImageDTO.getImage().getName());
            uploadImageDTO.setFolder(savePathNew);
            uploadImageDTO.setUrl(bladeFile.getLink());
            uploadImageDTO.setFileSize(uploadImageDTO.getImage().getSize());
        } else {
            throw new BadRequestAlertException("Invalid file", "UploadFile", "imagesnull");
        }
        UploadImage uploadImage = uploadImageMapper.toEntity(uploadImageDTO);
        SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneByLogin).ifPresent(uploadImage::setUser);
        uploadImage = uploadImageRepository.save(uploadImage);
        return uploadImageMapper.toDto(uploadImage);
    }

    /**
     * Get all the uploadImages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<UploadImageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UploadImages");
        return uploadImageRepository.findAll(pageable).map(uploadImageMapper::toDto);
    }

    /**
     * Get one uploadImage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<UploadImageDTO> findOne(Long id) {
        log.debug("Request to get UploadImage : {}", id);
        return uploadImageRepository.findById(id).map(uploadImageMapper::toDto);
    }

    /**
     * Delete the uploadImage by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete UploadImage : {}", id);
        uploadImageRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<UploadImageDTO> findByUserIsCurrentUser() {
        return uploadImageMapper.toDto(uploadImageRepository.findByUserIsCurrentUser());
    }

    /**
     * Update ignore specified fields by uploadImage
     */
    public UploadImageDTO updateByIgnoreSpecifiedFields(UploadImageDTO changeUploadImageDTO, Set<String> unchangedFields) throws Exception {
        UploadImageDTO uploadImageDTO = findOne(changeUploadImageDTO.getId()).get();
        BeanUtil.copyProperties(changeUploadImageDTO, uploadImageDTO, unchangedFields.toArray(new String[0]));
        uploadImageDTO = save(uploadImageDTO);
        return uploadImageDTO;
    }

    /**
     * Update specified fields by uploadImage
     */
    public UploadImageDTO updateBySpecifiedFields(UploadImageDTO changeUploadImageDTO, Set<String> fieldNames) throws Exception {
        UploadImageDTO uploadImageDTO = findOne(changeUploadImageDTO.getId()).get();
        UploadImageDTO finalUploadImageDTO = uploadImageDTO;
        fieldNames.forEach(
            fieldName -> {
                BeanUtil.setFieldValue(finalUploadImageDTO, fieldName, BeanUtil.getFieldValue(changeUploadImageDTO, fieldName));
            }
        );
        uploadImageDTO = save(finalUploadImageDTO);
        return uploadImageDTO;
    }

    /**
     * Update specified field by uploadImage
     */
    public UploadImageDTO updateBySpecifiedField(UploadImageDTO changeUploadImageDTO, String fieldName) throws Exception {
        UploadImageDTO uploadImageDTO = findOne(changeUploadImageDTO.getId()).get();
        BeanUtil.setFieldValue(uploadImageDTO, fieldName, BeanUtil.getFieldValue(changeUploadImageDTO, fieldName));
        uploadImageDTO = save(uploadImageDTO);
        return uploadImageDTO;
    }

    private void clearRelationsCache() {
        this.relationCacheNames.forEach(
                cacheName -> {
                    if (cacheManager.getCache(cacheName) != null) {
                        cacheManager.getCache(cacheName).clear();
                    }
                }
            );
    }
    // jhipster-needle-service-add-method - JHipster will add getters and setters here, do not remove

}
