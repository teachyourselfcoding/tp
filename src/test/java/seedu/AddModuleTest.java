package seedu;

import org.junit.jupiter.api.Test;
import seedu.command.AddModuleCommand;
import seedu.exception.InvalidArgumentsException;
import seedu.exception.ModuleAlreadyExistsException;
import seedu.module.Module;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;



public class AddModuleTest {
    @Test
    void AddModuleConstructor_emptyArgument_throwsInvalidArgumentsException() {
        assertThrows(InvalidArgumentsException.class, () -> {
            String input = "module";
            String[] words = input.split(" ");
            new AddModuleCommand(Arrays.copyOfRange(words, 1, input.length()));
        });
    }

    @Test
    void AddModuleConstructor_invalidModuleCode_throwsInvalidArgumentsException() {
        assertThrows(InvalidArgumentsException.class, () -> {
            String input = "module c/[a,b]";
            String[] words = input.split(" ");
            new AddModuleCommand(Arrays.copyOfRange(words, 1, input.length()));
        });
        assertThrows(InvalidArgumentsException.class, () -> {
            String input = "module c/";
            String[] words = input.split(" ");
            new AddModuleCommand(Arrays.copyOfRange(words, 1, input.length()));
        });
        assertThrows(InvalidArgumentsException.class, () -> {
            String input = "module c/STAAAAA";
            String[] words = input.split(" ");
            new AddModuleCommand(Arrays.copyOfRange(words, 1, input.length()));
        });
    }

    @Test
    void AddModuleExecute_caseInsensitive_throwsModuleAlreadyExistsException() {
        assertThrows(ModuleAlreadyExistsException.class, () -> {
            ModuleManager moduleManager = new ModuleManager();
            Module a = new Module("ST2132");
            Module b = new Module("st2132");
            moduleManager.addModule(a);
            moduleManager.addModule(b);
        });
    }

    @Test
    void AddModuleConstructor_invalidAu_throwsInvalidArgumentsException() {
        assertThrows(InvalidArgumentsException.class, () -> {
            String input = "module c/ST2115 a/-0.3";
            String[] words = input.split(" ");
            new AddModuleCommand(Arrays.copyOfRange(words, 1, input.length()));
        });

        assertThrows(InvalidArgumentsException.class, () -> {
            String input = "module c/ST2115 a/";
            String[] words = input.split(" ");
            new AddModuleCommand(Arrays.copyOfRange(words, 1, input.length()));
        });

        assertThrows(InvalidArgumentsException.class, () -> {
            String input = "module c/ST2115 a/+4.9";
            String[] words = input.split(" ");
            new AddModuleCommand(Arrays.copyOfRange(words, 1, input.length()));
        });

        assertThrows(InvalidArgumentsException.class, () -> {
            String input = "module c/ST2115 a/+4Yes";
            String[] words = input.split(" ");
            new AddModuleCommand(Arrays.copyOfRange(words, 1, input.length()));
        });
    }
}
