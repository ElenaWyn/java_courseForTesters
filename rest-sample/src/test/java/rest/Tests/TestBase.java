package rest.Tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import model.Issue;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.SkipException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;

public class TestBase {

    public Issue getIssueById(int issueID) throws IOException {
        String issue = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues/1.json")).returnContent().asString();
        JsonElement parsed = new JsonParser().parse(issue);
        Gson gson = new Gson();
        Issue issueByID = gson.fromJson(parsed, Issue.class);
        return issueByID;    //new Gson().fromJson(parsed, new TypeToken<Issue>(){}.getType());
    }


    public int createIssue(Issue newIssue) throws IOException {
        String json = getExecutor().execute(Request.Post("https://bugify.stqa.ru/api/issues.json")
                .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()), new BasicNameValuePair("description", newIssue.getDescription())))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        return parsed.getAsJsonObject().get("issue_id").getAsInt();
    }

    public Set<Issue> getIssues() throws IOException {
        String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues.json")).returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
    }



    public Executor getExecutor() {
        return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    public boolean isIssueOpen(int issueId) throws IOException {
        Issue issue = getIssueById(issueId);
        if (issue.getState_name() == "Closed") {
            return true;
        }
        return false;
    }



}
