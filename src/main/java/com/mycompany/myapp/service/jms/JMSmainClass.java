/*
package com.mycompany.myapp.service.jms;

import com.mycompany.myapp.domain.Logs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

 interface LambdaTest {
    public static void show() {
    }

    public int test(String a);
}

public class JMSmainClass {
    private final static Logger logger = LoggerFactory.getLogger(JMSmainClass.class);

    //Queue test
*/
/*    public static void main(String[] args) throws JMSException, InterruptedException {
        ServiceJMS jmsTemplate = new ServiceJMS();
        try {
            Session session = jmsTemplate.initJmsTemplate();
            //Queue send and receive asynchronously
            while (session != null) {
                jmsTemplate.sendMessage(session, "message to send: " + Thread.currentThread().getName());
                logger.debug("Message sent successfully");
                Thread.sleep(5000);
                jmsTemplate.receiveMsgSynchronously(session);
            }
        } catch (JMSException e) {
            logger.error("Exception while sending/receiving message via JMS in class: {}", e.getClass());
            logger.error("Message: {}", e.getMessage());
            e.printStackTrace();
        } finally {
            jmsTemplate.close();
        }
    }*//*


*/
/*    public static void main(String[] args) throws JMSException, InterruptedException {
        //Topic test
        ServiceJmsPubSub serviceJmsPubSub = new ServiceJmsPubSub();
        try {
            TopicSession session = serviceJmsPubSub.initJmsTemplate();
            serviceJmsPubSub.subscribe(session);
            serviceJmsPubSub.publish(session);
        } catch (JMSException e) {
            logger.error("Exception while sending/receiving message via JMS in class: {}", e.getClass());
            logger.error("Message: {}", e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
            logger.error("InterruptedException while sending/receiving message via JMS in class: {}", e.getClass());
            logger.error("Message: {}", e.getMessage());
        } finally {
            serviceJmsPubSub.close();
        }
    }*//*


    public static void main(String[] args) {
        LambdaTest ceva = (String a) -> {
            int result = 0;
            try {
                result = Integer.parseInt(a);
            } catch (NumberFormatException e) {
                logger.error("NumberFormatException e: {}", e);
            }
            logger.debug("Result: {}", result);
            return result;
        };
        ceva.test("123456");
        Runnable thread1 = () -> {
            String test = "sgdsg3453532";
            if (test != null && test.length() > 0) {
                for (int i = 0; i < test.length(); i++) {
                    logger.debug("Character: {}", test.getBytes());
                }
            }
        };
        Thread t = new Thread(thread1);
        t.start();
        logger.debug("========================================================================");
        //String user_id, String logger, String level, String message, ZonedDateTime date
        List<Logs> lista = Arrays.asList(new Logs("ionutb", "Logs", "DEBUG", "mesage"),
            new Logs("ionutb", "Logs", "ERROR", "mesage2"), new Logs("ionutb", "Logs", "WARN", "mesage3"));
        Collections.sort(lista, (Logs o1, Logs o2) -> o1.getMessage().compareTo(o2.getMessage()));

        lista.forEach(System.out::println);
        lista.stream()
            .filter(p -> p.getMessage().startsWith("m")).findFirst().orElse(new Logs());
        //.forEach(p->System.out.println(p.getMessage()));
        lista.parallelStream().map(p -> p.getUser_id().toUpperCase()).reduce((a, b) -> a + b);
        lista.forEach(a -> logger.debug("{}", a));

        List<Logs> filtered = lista
            .stream()
            .filter(p -> p.getMessage().startsWith("P")).sorted()
            .collect(Collectors.toList());

        Supplier<Stream<String>> streamSupplier =
            () -> Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> s.startsWith("a"));

        streamSupplier.get().anyMatch(s -> true);   // ok
        streamSupplier.get().noneMatch(s -> true);  // ok
        streamSupplier.get().count();
        */
/*Optional<String> reduced =
            lista
                .stream()
                .sorted()
                .reduce((s1, s2) -> s1.getMessage()+s2.getMessage());*//*


        */
/*reduced.ifPresent(System.out::println);*//*


        Optional<String> optional = Optional.of("bam");

        optional.isPresent();           // true
        optional.get();                 // "bam"
        optional.orElse("fallback");    // "bam"

        optional.ifPresent((s) -> System.out.println(s.charAt(1)));     // "b"
        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        System.out.println(commonPool.getParallelism());    // 3
        Arrays.asList("a1", "a2", "b1", "c2", "c1")
            .parallelStream()
            .filter(s -> {
                System.out.format("filter: %s [%s]\n",
                    s, Thread.currentThread().getName());
                return true;
            })
            .map(s -> {
                System.out.format("map: %s [%s]\n",
                    s, Thread.currentThread().getName());
                return s.toUpperCase();
            })
            .forEach(s -> System.out.format("forEach: %s [%s]\n",
                s, Thread.currentThread().getName()));


        String Str = "(level=1.0),(Type=switch),(data-protocols=ethernet),(serial-number=Y019CM3CM004),(Uuid=00000000000010008000a897dc8bf100),(mac-address=A8:97:DC:8B:F1:00),(sysoid=1.3.6.1.4.1.19046.1.7.31),(mtm=7159BRX),(ipv4-enabled=TRUE),(ipv4-address=10.241.6.102),(ipv6-enabled=TRUE),(ipv6-addresses=fe80:0:0:0:aa97:dcff:fe8b:f100),(deviceName=G8332)";
        System.out.print("Found Index=====###");
        System.out.print(Str.substring(Str.indexOf("Uuid")+5,Str.indexOf("Uuid")+37));
        System.out.println(Str.indexOf("@@@@@@@@@@@@@@@@@@@@@\n"));
        String[] values = Str.split(",");
        for (int i = 0; i < values.length; i++) {
            if (values[i].contains("Uuid")) {
                String[] subValue = values[i].split("=");
                System.out.println(subValue[1].replace(")",""));
            }
        }

    }

    public Logs showUserId(List<Logs> lista) {
        for (Logs log : lista) {
            if (log.getUser_id().startsWith("i")) {
                return log;
            }
        }
        return null;
    }

    public String setAsd(Logs g) {
        logger.error("");
        return "";
    }

}
*/
