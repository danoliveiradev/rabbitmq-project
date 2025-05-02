package main.java.com.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Publisher {
    public static void main(String[] args) throws Exception {
        String uri = System.getenv("CLOUDAMQP_URL");
        if (uri == null)
            uri = "";

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(uri);
        factory.setConnectionTimeout(30000); // 30 segundos

        try (Connection connection = factory.newConnection();
                Channel channel = connection.createChannel()) {

            String queue = "hello";
            boolean durable = false;
            boolean exclusive = false;
            boolean autoDelete = false;

            channel.queueDeclare(queue, durable, exclusive, autoDelete, null);

            String message = "Hello CloudAMQP!";
            channel.basicPublish("", queue, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");

            // O try-with-resources garante que a conexão e o canal serão fechados
            // automaticamente quando o bloco terminar.

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}