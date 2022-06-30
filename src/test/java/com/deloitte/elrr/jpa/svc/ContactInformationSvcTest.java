/**
 *
 */
package com.deloitte.elrr.jpa.svc;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.util.ReflectionTestUtils;

import com.deloitte.elrr.entity.ContactInformation;
import com.deloitte.elrr.repository.ContactInformationRepository;

/**
 * @author mnelakurti
 *
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ContactInformationSvcTest {


        /**
        *
        */
        @Mock
        private ContactInformationRepository contactInformationRepository;

        /**
         *
         *
         */
        @Test
        void test() {
            ContactInformationSvc contactInformationSvc =
                    new ContactInformationSvc(
                    contactInformationRepository);
            ContactInformation contactInformation = new ContactInformation();
            contactInformation.setContactinformationid(1L);
            List<ContactInformation> contactInformationList = new ArrayList<>();
            contactInformationList.add(contactInformation);
            ReflectionTestUtils.setField(contactInformationSvc,
                    "contactInformationRepository",
                    contactInformationRepository);
            Mockito.doReturn(contactInformation)
            .when(contactInformationRepository)
                    .save(contactInformation);
            Mockito.doReturn(true).when(contactInformationRepository)
            .existsById(1L);
            Mockito.doReturn(contactInformation)
            .when(contactInformationRepository)
            .findIdByElectronicmailaddress(Mockito.any());
            Mockito.doNothing().when(contactInformationRepository)
            .deleteById(1L);

            contactInformationSvc.getI(contactInformation);
            contactInformationSvc.findAll();
            contactInformationSvc.get(1L);
            contactInformationSvc.save(contactInformation);
            contactInformationSvc.deleteAll();
            contactInformationSvc.delete(1L);
            contactInformationSvc.update(contactInformation);
            contactInformationSvc.saveAll(contactInformationList);
            contactInformationSvc
            .getContactInformationByElectronicmailaddress("");

        }
}
