package com.org.example;

import com.github.javafaker.Faker;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class MessagesProvider {

    public final int MESSAGES_LIMIT = 11_000;

    MessagesProvider(List<User> users) throws IOException {
        Faker faker = new Faker();
        var messages = this.extractMessages();
        FileWriter out = new FileWriter("messages.csv");
        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader("id_sender", "id_receiver", "content", "time"))) {
            List<User> copyUsers = new LinkedList<>(users);
            for(int i = 0; i < users.size(); ++i) {
                int nrReceivers = ThreadLocalRandom.current().nextInt(0, 11);
                User currentUser = copyUsers.remove(i);
                List<User> receivers = RandomUtil.pickNRandomUsers(copyUsers, nrReceivers, ThreadLocalRandom.current());
                for(var receiver: receivers){
                    String extractedMessage = messages.remove(0);
                    printer.printRecord(currentUser.getId(), receiver.getId(), extractedMessage, faker.date().past(faker.random().nextInt(1, 1000), TimeUnit.DAYS).toString());
                }
                copyUsers.add(i, currentUser);
            }
        }

    }

    private List<String> extractMessages(){
        List<String> messages = new LinkedList<>();
        try {
            FileInputStream fileNameInput = new FileInputStream("topical_chat.csv");
            InputStreamReader input = new InputStreamReader(fileNameInput);
            CSVParser csvParser = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(input);
            int index = 0;
            for (CSVRecord record : csvParser) {
                String message = record.get("message");
                messages.add(message);
                index++;
                if (index == MESSAGES_LIMIT){
                    break;
                }
            }
            return messages;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
