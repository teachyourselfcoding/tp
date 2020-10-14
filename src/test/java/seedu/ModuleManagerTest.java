package seedu;

import org.junit.jupiter.api.Test;
import seedu.exception.ModuleAlreadyExistsException;

import static org.junit.jupiter.api.Assertions.*;

class ModuleManagerTest {

    @Test
    void checkIfModuleExist_module_trueIfExist() throws ModuleAlreadyExistsException {
        ModuleManager moduleManager = new ModuleManager();
        Module module1 = new Module("CS2113");
        Module module2 = new Module("MA4270");
        moduleManager.addModule(module1);
        assertEquals(moduleManager.checkIfModuleExist(module1), true);
        assertEquals(moduleManager.checkIfModuleExist(module2), false);
    }

    @Test
    void getTotalNumberOfModules_modules_numberOfModulesInModuleManager() throws ModuleAlreadyExistsException {
        ModuleManager moduleManager = new ModuleManager();
        Module module1 = new Module("CS2113");
        Module module2 = new Module("MA4270");
        Module module3 = new Module("CS2102");
        assertEquals(moduleManager.getTotalNumberOfModules(), 0);
        moduleManager.addModule(module1);
        assertEquals(moduleManager.getTotalNumberOfModules(), 1);
        moduleManager.addModule(module1);
        assertEquals(moduleManager.getTotalNumberOfModules(), 1);
        moduleManager.addModule(module2);
        assertEquals(moduleManager.getTotalNumberOfModules(), 2);
        moduleManager.addModule(module2);
        assertEquals(moduleManager.getTotalNumberOfModules(), 2);
        moduleManager.addModule(module1);
        assertEquals(moduleManager.getTotalNumberOfModules(), 2);
        moduleManager.addModule(module3);
        assertEquals(moduleManager.getTotalNumberOfModules(), 3);
        moduleManager.addModule(module3);
        assertEquals(moduleManager.getTotalNumberOfModules(), 3);
        moduleManager.addModule(module2);
        assertEquals(moduleManager.getTotalNumberOfModules(), 3);
        moduleManager.addModule(module1);
        assertEquals(moduleManager.getTotalNumberOfModules(), 3);
    }
}
