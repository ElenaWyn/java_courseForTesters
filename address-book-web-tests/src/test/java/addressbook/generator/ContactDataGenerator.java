package addressbook.generator;

import addressbook.model.Contact;
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
import java.util.UUID;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Group count")
    public int count;
    @Parameter(names = "-f", description = "File to write data")
    public String file;
    @Parameter(names = "-d", description = "Format of File to write data")
    public String format;


    public static void main (String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jc = new JCommander(generator);
        try {
            jc.parse(args);
        } catch (ParameterException ex){
            jc.usage();
            return;
        }
        generator.runContactGeneration();
    }

    public void runContactGeneration() throws IOException {
        List<Contact> contacts = generateContacts(count);
        if (format.equals("csv")) {
            saveAsCSV(contacts, new File(file));
        } else if (format.equals("xml"))
        {
            saveAsXML(contacts, new File(file));
        } else if (format.equals("json")) {
            saveAsGson(contacts, new File(file));

        } else {
            System.out.println("Unrecognized format " + format);
        }
    }

    private void saveAsXML(List<Contact> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(Contact.class);
        String xml = xstream.toXML(contacts);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    private void saveAsGson(List<Contact> contacts, File file) throws IOException {
            Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
            String json = gson.toJson(contacts);
            Writer writer = new FileWriter(file);
            writer.write(json);
            writer.close();
    }

    private void saveAsCSV(List<Contact> groups, File file) {
    }

    private List<Contact> generateContacts(int count) {
        List<Contact> contacts = new ArrayList<Contact>();
        for (int i = 0; i < count; i++) {
            contacts.add(new Contact().withFirstname(String.format(UUID.randomUUID().toString())).withLastname(UUID.randomUUID().toString()).withAddress(UUID.randomUUID().toString())
            .withTel_home(String.valueOf(100000000 + Math.random()*899999999)).withTel_work(String.valueOf(100000000 + Math.random()*899999999))
                    .withTel_mobile(String.valueOf(100000000 + Math.random()*899999999)).withEmail(UUID.randomUUID().toString()));

        }
        return contacts;


    }




}
