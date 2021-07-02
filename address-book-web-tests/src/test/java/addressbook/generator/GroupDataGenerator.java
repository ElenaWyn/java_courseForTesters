package addressbook.generator;

import addressbook.model.Group;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

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

    public static void main (String[] args) throws IOException {
        GroupDataGenerator generator = new GroupDataGenerator();
        JCommander jc = new JCommander(generator);
        try {
            JCommander.parse (args);
        } catcb (ParameterException ex){
            JCommander.usage();
            return;
        }
        generator.run();

        


    }

    private void run() throws IOException {
        List<Group> groups = generateGroups(count);
        save(groups, new File(file));
    }

    private static void save(List<Group> groups, File file) throws IOException {
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
