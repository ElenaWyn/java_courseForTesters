package rest.Tests;

import model.Issue;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;

public class RestTests extends TestBase {

    @Test
    public  void testCreateIssue() throws IOException {
        Set<Issue> oldIssues  = getIssues();
        Issue newIssue = new Issue().withSubject("test issue").withDescription("test description");
        int issueId = createIssue(newIssue);
        Set<Issue> newIssues  = getIssues();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(oldIssues.size(), newIssues.size());


    }

    @Test
    public void testGetIssue (int issueId) throws IOException {
        skipIfNotFixed(issueId);
        Issue issue = getIssueById(issueId);
        //then do smth with issue

    }




}
