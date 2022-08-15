package my.study.license.service;

import lombok.extern.slf4j.Slf4j;
import my.study.license.config.ServiceConfig;
import my.study.license.model.License;
import my.study.license.model.Organization;
import my.study.license.repository.LicenseRepository;
import my.study.license.service.client.OrganizationDiscoveryClient;
import my.study.license.service.client.OrganizationFeignClient;
import my.study.license.service.client.OrganizationRestTemplateClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
@Slf4j
public class LicenseService {

    @Autowired
    private MessageSource messages;

    @Autowired
    private LicenseRepository licenseRepository;

    @Autowired
    private ServiceConfig config;

    @Autowired
    OrganizationFeignClient organizationFeignClient;

    @Autowired
    OrganizationRestTemplateClient organizationRestClient;

    @Autowired
    OrganizationDiscoveryClient organizationDiscoveryClient;

    public License getLicense(String licenseId, String organizationId) {
        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);

        if (license == null) {
            String message = messages.getMessage("license.search.error.message", null, null);
            throw new IllegalArgumentException(String.format(message, licenseId, organizationId));
        }
        return license.withComment(config.getProperty());
    }

    public License getLicense(String licenseId, String organizationId, String clientType) {
        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);

        if (license == null) {
            throw new IllegalArgumentException(String.format(
                    messages.getMessage("license.search.error.message", null, null),
                    licenseId, organizationId
            ));
        }

        Organization organization = retrieveOrganizationInfo(organizationId, clientType);
        if (organization != null) {
            license.setOrganizationName(organization.getName());
            license.setContactName(organization.getContactName());
            license.setContactEmail(organization.getContactEmail());
            license.setContactPhone(organization.getContactPhone());
        }

        return license.withComment(config.getProperty());
    }

    private Organization retrieveOrganizationInfo(String organizationId, String clientType) {
        switch (clientType) {
            case "feign":
                log.warn("I am using the feign client");
                return organizationFeignClient.getOrganization(organizationId);
            case "rest":
                log.warn("I am using the rest client");
                return organizationRestClient.getOrganization(organizationId);
            case "discovery":
                log.warn("I am using the discovery client");
                return organizationDiscoveryClient.getOrganization(organizationId);
            default:
                return organizationRestClient.getOrganization(organizationId);
        }
    }

    @Transactional
    public License createLicense(License license) {
        license.setLicenseId(UUID.randomUUID().toString());
        licenseRepository.save(license);
        return license.withComment(config.getProperty());
    }

    @Transactional
    public License updateLicense(License license) {
        licenseRepository.save(license);
        return license.withComment(config.getProperty());
    }

    @Transactional
    public String deleteLicense(String licenseId) {
        License license = new License();
        license.setLicenseId(licenseId);
        licenseRepository.delete(license);
        String message = messages.getMessage("license.delete.message", null, null);
        return String.format(message, licenseId);
    }

}
