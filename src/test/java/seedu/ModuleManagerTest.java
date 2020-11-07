package seedu;

import org.junit.jupiter.api.Test;
import seedu.exception.ModuleAlreadyExistsException;

import seedu.exception.ModuleNotExistsException;
import seedu.module.Module;

import java.time.LocalDate;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ModuleManagerTest {

    @Test
    void checkIfModuleExist_module_trueIfExist() throws ModuleAlreadyExistsException {
        ModuleManager moduleManager = new ModuleManager();
        Module module1 = new Module("CS2113");
        Module module2 = new Module("MA4270");
        try {
            moduleManager.addModule(module1);
            assertEquals(moduleManager.checkIfModuleExist(module1), true);
            assertEquals(moduleManager.checkIfModuleExist(module2), false);
        } catch (Exception e) {  // ignore exception because this is a valid case
        }
    }

    @Test
    void getTotalNumberOfModules_modules_numberOfModulesInModuleManager() throws ModuleAlreadyExistsException {
        ModuleManager moduleManager = new ModuleManager();
        Module module1 = new Module("CS2113");
        Module module2 = new Module("MA4270");
        Module module3 = new Module("CS2102");
        assertEquals(moduleManager.getNumberOfModules(), 0);
        try {
            moduleManager.addModule(module1);
            assertEquals(moduleManager.getNumberOfModules(), 1);
            moduleManager.addModule(module1);
            assertEquals(moduleManager.getNumberOfModules(), 1);
            moduleManager.addModule(module2);
            assertEquals(moduleManager.getNumberOfModules(), 2);
            moduleManager.addModule(module2);
            assertEquals(moduleManager.getNumberOfModules(), 2);
            moduleManager.addModule(module1);
            assertEquals(moduleManager.getNumberOfModules(), 2);
            moduleManager.addModule(module3);
            assertEquals(moduleManager.getNumberOfModules(), 3);
            moduleManager.addModule(module3);
            assertEquals(moduleManager.getNumberOfModules(), 3);
            moduleManager.addModule(module2);
            assertEquals(moduleManager.getNumberOfModules(), 3);
            moduleManager.addModule(module1);
            assertEquals(moduleManager.getNumberOfModules(), 3);
        } catch (Exception e) {
        }
    }

    @Test
    void display_displayNonExistentModule_expectException() {
        ModuleManager mm = new ModuleManager();
        assertThrows(ModuleNotExistsException.class,()->{mm.display("CS2113");});
    }

    @Test
    void display_displayNonExistentModuleWithDate_expectException() {
        ModuleManager mm = new ModuleManager();
        assertThrows(ModuleNotExistsException.class,()->{mm.display("CS2113", LocalDate.now());});
    }

}
