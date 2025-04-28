import pika, os

# Acesso a CLODUAMQP_URL and variables
url = os.environ.get('CLOUDAMQP_URL', 'amqps://tkxuljpc:7Irana2gy1_NfRSHCRP2mH1zuIljm1Ih@jaragua.lmq.cloudamqp.com/tkxuljpc')
params = pika.URLParameters(url)
connection = pika.BlockingConnection(params)
channel = connection.channel()
channel.queue_declare(queue='hello')
channel.basic_publish(exchange='',
                      routing_key='hello',
                      body='Hello CloudAMQP!')

print(" [x] Sent 'Hello World!'")
connection.close()