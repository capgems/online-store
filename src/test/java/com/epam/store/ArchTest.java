package com.epam.store;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.epam.store");

        noClasses()
            .that()
                .resideInAnyPackage("com.epam.store.service..")
            .or()
                .resideInAnyPackage("com.epam.store.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.epam.store.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
