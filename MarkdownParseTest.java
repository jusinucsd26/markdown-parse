import static org.junit.Assert.*;
import org.junit.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class MarkdownParseTest {
    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }
    @Test
    public void testFile1() throws IOException{
        String contents = Files.readString(Path.of("./test-file.md"));
        List<String> expect = List.of("https://something.com", "some-page.html");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }
    @Test
    public void testFile2() throws IOException{
        String contents = Files.readString(Path.of("./test-file2.md"));
        List<String> expect = List.of("thisisalink.com", "thisisanotherlink.org");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }
    @Test
    public void testFile3() throws IOException{
        String contents = Files.readString(Path.of("./test-file3.md"));
        List<String> expect = List.of("link[1].com", "link[].org");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }

    @Test
    public void testFile4() throws IOException{
        String contents = Files.readString(Path.of("./test-file4.md"));
        List<String> expect = List.of("link");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }

    @Test
    public void testSnippet1() throws IOException {
        List<String> expected = List.of("`google.com", "google.com", "ucsd.edu");
        String contents = Files.readString(Path.of("./snippet-1.md"));
        assertEquals(expected, MarkdownParse.getLinks(contents));    
    }

    @Test
    public void testSnippet2() throws IOException {
        List<String> expected = List.of("a.com", "a.com(())", "example.com");
        String contents = Files.readString(Path.of("./snippet-2.md"));
        assertEquals(expected, MarkdownParse.getLinks(contents));
    }

    @Test
    public void testSnippet3() throws IOException{
        List<String> expected = List.of("https://ucsd-cse15l-w22.github.io/");
        String contents = Files.readString(Path.of("./snippet-3.md"));
        assertEquals(expected, MarkdownParse.getLinks(contents));
    }
}