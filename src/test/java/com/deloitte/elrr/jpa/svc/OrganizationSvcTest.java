/**
 *
 */
package com.deloitte.elrr.jpa.svc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.util.ReflectionTestUtils;

import com.deloitte.elrr.entity.Organization;
import com.deloitte.elrr.repository.OrganizationRepository;

/**
 * @author mnelakurti
 *
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class OrganizationSvcTest {

        /**
        *
        */
        @Mock
        private OrganizationRepository organizationRepository;

        /**
         *
         *
         */
        @Test
        void test() {
            OrganizationSvc organizationSvc = new OrganizationSvc(
                    organizationRepository);
            Organization organization = new Organization();
            UUID id = UUID.randomUUID();
            organization.setId(id);
            List<Organization> organizationList = new ArrayList<>();
            organizationList.add(organization);
            ReflectionTestUtils.setField(organizationSvc,
                    "organizationRepository", organizationRepository);
            Mockito.doReturn(organization)
                    .when(organizationRepository).save(organization);
            Mockito.doReturn(true).when(organizationRepository)
                    .existsById(id);
            Mockito.doNothing().when(organizationRepository).deleteById(id);

            organizationSvc.getId(organization);
            organizationSvc.findAll();
            organizationSvc.get(id);
            organizationSvc.save(organization);
            organizationSvc.deleteAll();
            organizationSvc.delete(id);
            organizationSvc.update(organization);
            organizationSvc.saveAll(organizationList);
        }
    }
