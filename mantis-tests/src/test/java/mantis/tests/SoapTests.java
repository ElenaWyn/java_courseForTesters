package mantis.tests;

import mantis.model.DefaultTrustManager;
import mantis.model.Issue;
import mantis.model.Project;
import org.testng.annotations.Test;

import javax.net.ssl.*;
import javax.xml.rpc.ServiceException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SoapTests extends TestBase {

    @Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProjects();
        System.out.println("We have " + projects.size() + " projects");
        for (Project project : projects) {
            System.out.println(project.getName());
        }
    }

    @Test
    public void testCreateIssue() throws MalformedURLException, ServiceException, RemoteException {
        Set<Project> projects = app.soap().getProjects();
        Issue issue = new Issue().withSummary("dsfhldsigf").withDescription("sdhfsh").withProject(projects.iterator().next());
        Issue created = app.soap().addIssue(issue);
        assertEquals(issue.getSummary(), created.getSummary());


    }

}
