import pika, os

# Acesso a CLODUAMQP_URL and variables
url = os.environ.get('CLOUDAMQP_URL', '')
params = pika.URLParameters(url)
connection = pika.BlockingConnection(params)
channel = connection.channel()
channel.queue_declare(queue='hello')
def callback(ch, method, properties, body):
  print(" [x] Received " + str(body))
  
channel.basic_consume('hello',
                      callback,
                      auto_ack=True)

print(' [*] Waiting for messages')
channel.start_consuming()
connection.close()