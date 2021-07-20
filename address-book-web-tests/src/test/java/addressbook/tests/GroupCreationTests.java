package addressbook.tests;

import addressbook.model.Group;
import addressbook.model.GroupSet;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.openqa.selenium.json.TypeToken;
import org.testng.annotations.*;


import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class GroupCreationTests extends TestBase {

  @DataProvider //from csv
  public Iterator<Object[]> validGroupsCSV() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups1.csv")));
    String line  = reader.readLine();
    while (line != null)
    {
      String[] split = line.split(";");
      list.add(new Object[] {new Group().withGroupName(split[0]).withGroupHeader(split[1]).withGroupFooter(split[2])});
      line  = reader.readLine();
    }
    return list.iterator();

  }

  @DataProvider
  public Iterator<Object[]> validGroupsJSON() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups1.json")));
    String json = "";
    String line  = reader.readLine();
    while (line != null)
    {
      json += line;
      line  = reader.readLine();
    }
    Gson gson = new Gson();
    List<Group> groups = gson.fromJson(json, new TypeToken<List<Group>>(){}.getType());
    return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();

  }


  @DataProvider
  public Iterator<Object[]> validGroupsXML() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups1.xml")));
    String xml = "";
    String line  = reader.readLine();
    while (line != null)
    {
      xml += line;
      line  = reader.readLine();
    }
    XStream xstream = new XStream();
    xstream.processAnnotations(Group.class);
    List <Group> groups = (List <Group>) xstream.fromXML(xml);
    return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();

  }




  @Test(dataProvider = "validGroupsJSON")
  public void testGroupCreation(Group group) throws Exception {

    app.goTo().GroupPage();
    GroupSet before = app.db().groups();
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size() +1));
    GroupSet after = app.db().groups();
    assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }

  @Test
  public void testBadGroupCreation() throws Exception {

    app.goTo().GroupPage();
    GroupSet before = app.db().groups();
    Group group = new Group().withGroupName("Grupa'").withGroupHeader("TestGroupHeader").withGroupFooter("TestGroupFooter");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size()));
    GroupSet after = app.db().groups();
    assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }



}
