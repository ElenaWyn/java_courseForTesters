package rest.Tests;

import model.Issue;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;

public class RestTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() throws IOException {
        skipIfNotFixed(1286);
    }


    @Test
    public  void testCreateIssue() throws IOException {
        Set<Issue> oldIssues  = getIssues();
        Issue newIssue = new Issue().withSubject("test issue").withDescription("test description");
        int issueId = createIssue(newIssue);
        Set<Issue> newIssues  = getIssues();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(oldIssues.size(), newIssues.size());

    }







}
