package addressbook.generator;

import addressbook.model.Group;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupDataGenerator {

    @Parameter(names = "-c", description = "Group count")
    public int count;
    @Parameter(names = "-f", description = "File to write data")
    public String file;
    @Parameter(names = "-d", description = "Format of File to write data")
    public String format;

    public static void main (String[] args) throws IOException {
        GroupDataGenerator generator = new GroupDataGenerator();
        JCommander jc = new JCommander(generator);
        try {
            jc.parse(args);
        } catch (ParameterException ex){
            jc.usage();
            return;
        }
        generator.run();
    }

    /*public static void main (String[] args) throws IOException {
        List<Group> groups = generateGroups(Integer.parseInt(args[0]));
        save(groups, new File(args[1]));
    }*/

    private void run() throws IOException {
        List<Group> groups = generateGroups(count);
        if (format.equals("csv")) {
            saveAsCSV(groups, new File(file));
        } else if (format.equals("xml"))
        {
            saveAsXML(groups, new File(file));
        } else if (format.equals("json")) {
            saveAsGson(groups, new File(file));

        } else {
                System.out.println("Unrecognized format " + format);
            }
    }

    private void saveAsGson(List<Group> groups, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(groups);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }



    private void saveAsXML(List<Group> groups, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(Group.class);
        String xml = xstream.toXML(groups);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    private static void saveAsCSV(List<Group> groups, File file) throws IOException {
        System.out.println(new File(".").getAbsolutePath());
        Writer writer = new FileWriter(file);
        for (Group group : groups) {
            writer.write(String.format("%s;&s;&s\n", group.getGroupName(), group.getGroupHeader(), group.getGroupFooter()));
        }
        writer.close();
    }

    private static List<Group> generateGroups(int count) {
        List<Group> groups = new ArrayList<Group>();
        for (int i = 0; i < count; i++) {
            groups.add(new Group().withGroupName(String.format("Test %s", i)).withGroupHeader(String.format("Header %s", i))
                    .withGroupFooter(String.format("Footer %s", i)));

        }
        return groups;
    }
}
