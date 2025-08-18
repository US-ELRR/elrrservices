package com.deloitte.elrr.services.aspect;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.deloitte.elrr.entity.Auditable;
import com.deloitte.elrr.entity.Entity;

@ExtendWith(MockitoExtension.class)
class ServiceAdviceTest {

    @InjectMocks
    private ServiceAdvice serviceAdvice;

    private MockedStatic<SecurityContextHolder> securityContextHolderMock;
    private SecurityContext securityContext;
    private Authentication authentication;
    private ProceedingJoinPoint proceedingJoinPoint;

    @BeforeEach
    void setUp() {
        securityContextHolderMock = Mockito.mockStatic(SecurityContextHolder.class);
        securityContext = mock(SecurityContext.class);
        authentication = mock(Authentication.class);
        proceedingJoinPoint = mock(ProceedingJoinPoint.class);

        securityContextHolderMock.when(SecurityContextHolder::getContext)
                .thenReturn(securityContext);
        // Use lenient stubbing for the authentication setup that may not be used in all tests
        Mockito.lenient().when(securityContext.getAuthentication()).thenReturn(authentication);
        Mockito.lenient().when(authentication.getPrincipal()).thenReturn("testuser");
    }

    @AfterEach
    void tearDown() {
        if (securityContextHolderMock != null) {
            securityContextHolderMock.close();
        }
    }

    @Test
    void aroundSave_ShouldSetUsernameAndLogEntity() throws Throwable {
        // Arrange
        TestAuditableEntity inputEntity = new TestAuditableEntity();
        inputEntity.setId(UUID.randomUUID());
        
        TestEntity outputEntity = new TestEntity();
        outputEntity.setId(UUID.randomUUID());

        when(proceedingJoinPoint.getArgs()).thenReturn(new Object[]{inputEntity});
        when(proceedingJoinPoint.proceed()).thenReturn(outputEntity);

        // Act
        Entity result = serviceAdvice.aroundSave(proceedingJoinPoint);

        // Assert
        assertNotNull(result);
        assertEquals(outputEntity, result);
        assertEquals("testuser", inputEntity.getUpdatedBy());
        verify(proceedingJoinPoint, times(1)).proceed();
    }

    @Test
    void aroundSaveAll_ShouldSetUsernameForAllEntitiesAndLogThem() throws Throwable {
        // Arrange
        TestAuditableEntity inputEntity1 = new TestAuditableEntity();
        inputEntity1.setId(UUID.randomUUID());
        TestAuditableEntity inputEntity2 = new TestAuditableEntity();
        inputEntity2.setId(UUID.randomUUID());
        Collection<Auditable<String>> inputEntities = List.of(inputEntity1, inputEntity2);

        TestEntity outputEntity1 = new TestEntity();
        outputEntity1.setId(UUID.randomUUID());
        TestEntity outputEntity2 = new TestEntity();
        outputEntity2.setId(UUID.randomUUID());
        Collection<Entity> outputEntities = List.of(outputEntity1, outputEntity2);

        when(proceedingJoinPoint.getArgs()).thenReturn(new Object[]{inputEntities});
        when(proceedingJoinPoint.proceed()).thenReturn(outputEntities);

        // Act
        Collection<Entity> result = serviceAdvice.aroundSaveAll(proceedingJoinPoint);

        // Assert
        assertNotNull(result);
        assertEquals(outputEntities, result);
        assertEquals("testuser", inputEntity1.getUpdatedBy());
        assertEquals("testuser", inputEntity2.getUpdatedBy());
        verify(proceedingJoinPoint, times(1)).proceed();
    }

    @Test
    void aroundSaveAll_ShouldHandleExceptionInLogging() throws Throwable {
        // Arrange
        TestAuditableEntity inputEntity = new TestAuditableEntity();
        inputEntity.setId(UUID.randomUUID());
        Collection<Auditable<String>> inputEntities = List.of(inputEntity);

        // Create a problematic entity that will cause logging to fail
        ProblematicEntity problematicEntity = new ProblematicEntity();
        problematicEntity.setId(UUID.randomUUID());
        Collection<Entity> outputEntities = List.of(problematicEntity);

        when(proceedingJoinPoint.getArgs()).thenReturn(new Object[]{inputEntities});
        when(proceedingJoinPoint.proceed()).thenReturn(outputEntities);

        // Act
        Collection<Entity> result = serviceAdvice.aroundSaveAll(proceedingJoinPoint);

        // Assert
        assertNotNull(result);
        assertEquals(outputEntities, result);
        assertEquals("testuser", inputEntity.getUpdatedBy());
        verify(proceedingJoinPoint, times(1)).proceed();
    }

    @Test
    void aroundSave_ShouldThrowExceptionWhenNoAuthentication() throws Throwable {
        // Arrange
        when(securityContext.getAuthentication()).thenReturn(null);
        
        TestAuditableEntity inputEntity = new TestAuditableEntity();
        when(proceedingJoinPoint.getArgs()).thenReturn(new Object[]{inputEntity});

        // Act & Assert
        IllegalStateException exception = assertThrows(IllegalStateException.class, 
                () -> serviceAdvice.aroundSave(proceedingJoinPoint));
        
        assertEquals("No authentication found for auditing.", exception.getMessage());
    }

    @Test
    void aroundSaveAll_ShouldThrowExceptionWhenNoAuthentication() throws Throwable {
        // Arrange
        when(securityContext.getAuthentication()).thenReturn(null);
        
        TestAuditableEntity inputEntity = new TestAuditableEntity();
        Collection<Auditable<String>> inputEntities = List.of(inputEntity);
        when(proceedingJoinPoint.getArgs()).thenReturn(new Object[]{inputEntities});

        // Act & Assert
        IllegalStateException exception = assertThrows(IllegalStateException.class, 
                () -> serviceAdvice.aroundSaveAll(proceedingJoinPoint));
        
        assertEquals("No authentication found for auditing.", exception.getMessage());
    }

    @Test
    void aroundSave_ShouldPropagateExceptionFromProceed() throws Throwable {
        // Arrange
        TestAuditableEntity inputEntity = new TestAuditableEntity();
        when(proceedingJoinPoint.getArgs()).thenReturn(new Object[]{inputEntity});
        
        RuntimeException expectedException = new RuntimeException("Test exception");
        when(proceedingJoinPoint.proceed()).thenThrow(expectedException);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, 
                () -> serviceAdvice.aroundSave(proceedingJoinPoint));
        
        assertEquals("Test exception", exception.getMessage());
        assertEquals("testuser", inputEntity.getUpdatedBy());
    }

    @Test
    void aroundSaveAll_ShouldPropagateExceptionFromProceed() throws Throwable {
        // Arrange
        TestAuditableEntity inputEntity = new TestAuditableEntity();
        Collection<Auditable<String>> inputEntities = List.of(inputEntity);
        when(proceedingJoinPoint.getArgs()).thenReturn(new Object[]{inputEntities});
        
        RuntimeException expectedException = new RuntimeException("Test exception");
        when(proceedingJoinPoint.proceed()).thenThrow(expectedException);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, 
                () -> serviceAdvice.aroundSaveAll(proceedingJoinPoint));
        
        assertEquals("Test exception", exception.getMessage());
        assertEquals("testuser", inputEntity.getUpdatedBy());
    }

    // Test helper classes
    private static class TestAuditableEntity extends Auditable<String> {
        // Test implementation of Auditable
    }

    private static class TestEntity extends Entity {
        // Test implementation of Entity
    }

    private static class ProblematicEntity extends Entity {
        // This entity will cause issues during JSON serialization
        @Override
        public String toString() {
            throw new RuntimeException("Serialization error");
        }
    }
}
