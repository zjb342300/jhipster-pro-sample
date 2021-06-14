package com.mycompany.myapp.service;

import cn.hutool.core.bean.BeanUtil;
import com.mycompany.myapp.config.ApplicationProperties;
import com.mycompany.myapp.config.Constants;
import com.mycompany.myapp.domain.UploadFile;
import com.mycompany.myapp.oss.builder.OssBuilder;
import com.mycompany.myapp.oss.model.BladeFile;
import com.mycompany.myapp.repository.UploadFileRepository;
import com.mycompany.myapp.repository.UserRepository;
import com.mycompany.myapp.security.SecurityUtils;
import com.mycompany.myapp.service.dto.UploadFileDTO;
import com.mycompany.myapp.service.mapper.UploadFileMapper;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// jhipster-needle-add-import - JHipster will add getters and setters here, do not remove

/**
 * Service Implementation for managing {@link UploadFile}.
 */
@Service
@Transactional
public class UploadFileService {

    private final Logger log = LoggerFactory.getLogger(UploadFileService.class);
    private final List<String> relationCacheNames = Arrays.asList(com.mycompany.myapp.domain.User.class.getName() + ".uploadFile");

    private final UploadFileRepository uploadFileRepository;

    private final CacheManager cacheManager;

    private final UploadFileMapper uploadFileMapper;

    private final ApplicationProperties applicationProperties;

    private final UserRepository userRepository;

    private final OssBuilder ossBuilder;

    public UploadFileService(
        UploadFileRepository uploadFileRepository,
        CacheManager cacheManager,
        UploadFileMapper uploadFileMapper,
        ApplicationProperties applicationProperties,
        UserRepository userRepository,
        OssBuilder ossBuilder
    ) {
        this.uploadFileRepository = uploadFileRepository;
        this.cacheManager = cacheManager;
        this.uploadFileMapper = uploadFileMapper;
        this.applicationProperties = applicationProperties;
        this.userRepository = userRepository;
        this.ossBuilder = ossBuilder;
    }

    /**
     * Save a uploadFile.
     *
     * @param uploadFileDTO the entity to save
     * @return the persisted entity
     */
    public UploadFileDTO save(UploadFileDTO uploadFileDTO) throws Exception {
        log.debug("Request to save UploadFile : {}", uploadFileDTO);
        if (!uploadFileDTO.getFile().isEmpty()) {
            final String extName = FilenameUtils.getExtension(uploadFileDTO.getFile().getOriginalFilename());
            final String randomNameNew = UUID.randomUUID().toString().replaceAll("\\-", "");
            final String yearAndMonth = ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM"));
            final String savePathNew =
                Constants.DATA_PATH +
                File.separator +
                applicationProperties.getUpload().getRootPath() +
                File.separator +
                yearAndMonth +
                File.separator;
            final String saveFileName = savePathNew + randomNameNew + "." + extName;
            String fileUrl =
                applicationProperties.getUpload().getDomainUrl() +
                applicationProperties.getUpload().getRootPath() +
                "/" +
                yearAndMonth +
                "/" +
                randomNameNew +
                "." +
                extName;
            final long fileSize = uploadFileDTO.getFile().getSize();
            BladeFile bladeFile = ossBuilder
                .template()
                .putFile(uploadFileDTO.getFile().getOriginalFilename(), uploadFileDTO.getFile().getInputStream());
            uploadFileDTO.setCreateAt(ZonedDateTime.now());
            uploadFileDTO.setExt(extName);
            uploadFileDTO.setFullName(uploadFileDTO.getFile().getOriginalFilename());
            uploadFileDTO.setName(uploadFileDTO.getFile().getName());
            uploadFileDTO.setFolder(savePathNew);
            uploadFileDTO.setUrl(fileUrl);
            uploadFileDTO.setFileSize(fileSize);
        } else {
            throw new BadRequestAlertException("Invalid file", "UploadFile", "imagesnull");
        }
        UploadFile uploadFile = uploadFileMapper.toEntity(uploadFileDTO);
        SecurityUtils.getCurrentUserLogin().flatMap(userRepository::findOneByLogin).ifPresent(uploadFile::setUser);
        uploadFile = uploadFileRepository.save(uploadFile);
        return uploadFileMapper.toDto(uploadFile);
    }

    /**
     * Get all the uploadFiles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<UploadFileDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UploadFiles");
        return uploadFileRepository.findAll(pageable).map(uploadFileMapper::toDto);
    }

    /**
     * Get one uploadFile by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<UploadFileDTO> findOne(Long id) {
        log.debug("Request to get UploadFile : {}", id);
        return uploadFileRepository.findById(id).map(uploadFileMapper::toDto);
    }

    /**
     * Delete the uploadFile by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete UploadFile : {}", id);
        uploadFileRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<UploadFileDTO> findByUserIsCurrentUser() {
        return uploadFileMapper.toDto(uploadFileRepository.findByUserIsCurrentUser());
    }

    /**
     * Update ignore specified fields by uploadFile
     */
    public UploadFileDTO updateByIgnoreSpecifiedFields(UploadFileDTO changeUploadFileDTO, Set<String> unchangedFields) throws Exception {
        UploadFileDTO uploadFileDTO = findOne(changeUploadFileDTO.getId()).get();
        BeanUtil.copyProperties(changeUploadFileDTO, uploadFileDTO, unchangedFields.toArray(new String[0]));
        uploadFileDTO = save(uploadFileDTO);
        return uploadFileDTO;
    }

    /**
     * Update specified fields by uploadFile
     */
    public UploadFileDTO updateBySpecifiedFields(UploadFileDTO changeUploadFileDTO, Set<String> fieldNames) throws Exception {
        UploadFileDTO uploadFileDTO = findOne(changeUploadFileDTO.getId()).get();
        UploadFileDTO finalUploadFileDTO = uploadFileDTO;
        fieldNames.forEach(
            fieldName -> {
                BeanUtil.setFieldValue(finalUploadFileDTO, fieldName, BeanUtil.getFieldValue(changeUploadFileDTO, fieldName));
            }
        );
        uploadFileDTO = save(finalUploadFileDTO);
        return uploadFileDTO;
    }

    /**
     * Update specified field by uploadFile
     */
    public UploadFileDTO updateBySpecifiedField(UploadFileDTO changeUploadFileDTO, String fieldName) throws Exception {
        UploadFileDTO uploadFileDTO = findOne(changeUploadFileDTO.getId()).get();
        BeanUtil.setFieldValue(uploadFileDTO, fieldName, BeanUtil.getFieldValue(changeUploadFileDTO, fieldName));
        uploadFileDTO = save(uploadFileDTO);
        return uploadFileDTO;
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
