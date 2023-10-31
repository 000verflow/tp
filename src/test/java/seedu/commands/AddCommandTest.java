package seedu.commands;

import org.junit.jupiter.api.Test;
import seedu.data.Book;
import seedu.data.ResourceList;
import seedu.data.Status;
import seedu.data.SysLibException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddCommandTest {
    private final ResourceList resourcelist = new ResourceList();
    private final AddCommand addCommand = new AddCommand();

    @Test
    public void addCommandValidData() throws SysLibException {
        addCommand.execute("/id 123456789 /t The Minds of Billy Milligan /a Daniel Keyes /tag B /i 987654321 " +
                "/g Non-Fiction, Biography /s lost", resourcelist);

        Book newBook = (Book) resourcelist.getResourceList().get(0);

        assertEquals(newBook.getId(), 123456789);
        assertEquals(newBook.getTitle(), "The Minds of Billy Milligan");
        assertEquals(newBook.getAuthor(), "Daniel Keyes");
        assertEquals(newBook.getTag(), "B");
        assertEquals(newBook.getISBN(), "987654321");
        assertEquals(newBook.getGenreString(), "Non-Fiction, Biography");
        assertEquals(newBook.getStatus(), Status.LOST);
    }

    @Test
    public void addCommandOutput() throws SysLibException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        addCommand.execute("/id 123456789 /t The Minds of Billy Milligan /a Daniel Keyes /tag B /i 987654321 " +
                "/g Non-Fiction, Biography", resourcelist);

        String output = outputStream.toString();

        String expectedOutput = "This book is added: The Minds of Billy Milligan" + System.lineSeparator() +
                "____________________________________________________________" + System.lineSeparator();

        assertEquals(expectedOutput, output);
    }

    @Test
    public void addCommandInvalidId() {
        assertThrows(IllegalArgumentException.class, ()->addCommand.execute("/id abcd " +
                "/t The Minds of Billy Milligan /a Daniel Keyes /tag B /i 987654321", resourcelist));
    }

    @Test
    public void addCommandInsufficientData() {
        assertThrows(IllegalArgumentException.class, ()->addCommand.execute("/id ", resourcelist));
    }
}
