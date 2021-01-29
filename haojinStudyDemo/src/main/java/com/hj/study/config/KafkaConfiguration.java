package com.hj.study.config;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;


//@EnableKafka
//@Configuration
public class KafkaConfiguration {

   @Value("${spring.kafka.consumer.group-id}")
   String groupId;

   @Value("${spring.kafka.consumer.auto-offset-reset}")
   String autoOffsetReset;

   @Value("${spring.kafka.bootstrap-servers}")
   String bootstrapServers;

   @Value("${spring.kafka.consumer.max-poll-records}")
   String maxPollRecords;

   @Value("${spring.kafka.consumer.key-deserializer}")
   String keyDeserializer;

   @Value("${spring.kafka.consumer.value-deserializer}")
   String valDeserializer;



   @Value(("${spring.kafka.producer.retries}"))
   String producerRetries;

   @Value(("${spring.kafka.producer.batch-size}"))
   String producerBatchSize;

   @Value(("${spring.kafka.producer.buffer-memory}"))
   String producerBufferMemory;

   @Value(("${spring.kafka.producer.key-serializer}"))
   String producerKeySerializer;

   @Value(("${spring.kafka.producer.value-serializer}"))
   String producerValueSerializer;

   @Value(("${kafka.topic.num.partitions}"))
   Integer numPartitions;

   @Value(("${kafka.thread.core.pool.size}"))
   Integer corePoolSize;

   @Value(("${kafka.thread.maximum.pool.size}"))
   Integer maximumPoolSize;

   @Value(("${kafka.thread.queue.capacity}"))
   Integer queueCapacity;

   @Value(("${kafka.enabled}"))
   String kafkaEnabled;

//	@Value(("${file.separator}"))
//	String fileSeparator;
//
//	@Value(("${default.config.dir}"))
//	String defaultConfigDir;


   @Bean
   public KafkaListenerContainerFactory<?> batchFactory() {
      ConcurrentKafkaListenerContainerFactory<String, String> factory = new
              ConcurrentKafkaListenerContainerFactory<>();
      factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(consumerConfigs()));
      // 开启批量监听
      factory.setBatchListener(true);
      return factory;
   }

   @Bean
   public Map<String, Object> consumerConfigs() {
      Map<String, Object> props = new HashMap<>();
      props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
      props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
      props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
      //设置每次接收Message的数量
      props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, maxPollRecords);
      props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
      props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 120000);
      props.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, 180000);
      props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeserializer);
      props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valDeserializer);
      props.put("sasl.mechanism", "GSSAPI");
      return props;
   }

   /**
    *  创建TopicNameøøø为的Topic并设置分区数为6以及副本数为1
    * @return
    */
//   @Bean
//   public NewTopic initialTopic() {
//      return new NewTopic(KafkaSender.BATCH_ONLINE_RECOMMEND_TOPIC, 4, (short) 1);
//   }


//
//   @Bean
//   public AdminClient adminClient() {
//      return AdminClient.create(kafkaAdmin().getConfig());
//   }


   /**
    * 创建TopicNameøøø为的Topic并设置分区数为6以及副本数为1
    *
    * @return
    */
//   @Bean
//   public NewTopic initialTopic() {
//      return new NewTopic(KafkaSender.BATCH_ONLINE_RECOMMEND_TOPIC, 4, (short) 1);
//   }

//
//   @Bean
//   public AdminClient adminClient() {
//      return AdminClient.create(kafkaAdmin().getConfig());
//   }

   private Map<String, Object> producerConfigs() {
//		System.setProperty("java.security.auth.login.config", defaultConfigDir + fileSeparator + "jaas-cache.conf");
//		System.setProperty("java.security.krb5.conf", defaultConfigDir + fileSeparator + "krb5.conf");

      Map<String, Object> props = new HashMap<>(7);
      props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
      props.put(ProducerConfig.RETRIES_CONFIG, producerRetries);
      props.put(ProducerConfig.BATCH_SIZE_CONFIG, producerBatchSize);
      props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
      props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, producerBufferMemory);
      props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, producerKeySerializer);
      props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, producerValueSerializer);
      props.put("sasl.mechanism", "GSSAPI");
//		props.put("security.protocol", "SASL_PLAINTEXT");
      return props;
   }

   private ProducerFactory<String, String> producerFactory() {
      DefaultKafkaProducerFactory<String, String> producerFactory = new DefaultKafkaProducerFactory<>(
              producerConfigs());
      /*
       * producerFactory.transactionCapable();
       * producerFactory.setTransactionIdPrefix("hous-");
       */
      return producerFactory;
   }

   /*
    * @Bean public KafkaTransactionManager transactionManager() {
    * KafkaTransactionManager manager = new
    * KafkaTransactionManager(producerFactory()); return manager; }
    */

   @Bean("kafkaTemplate")
   public KafkaTemplate<String, String> kafkaTemplate() {
      //KafkaUtil.setKafkaTemplate(new KafkaTemplate<>(producerFactory()));
      return new KafkaTemplate<>(producerFactory());
   }

   @Bean("adminClient")
   public AdminClient adminClient() {
      if ("false".equalsIgnoreCase(kafkaEnabled)) {
         return null;
      }
      Map<String, Object> props = new HashMap<>();
      // 配置Kafka实例的连接地址
      // kafka的地址，不是zookeeper
      props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
      return AdminClient.create(props);
   }

   public Integer getNumPartitions() {
      return numPartitions;
   }

   public Integer getCorePoolSize() {
      return corePoolSize;
   }

   public Integer getMaximumPoolSize() {
      return maximumPoolSize;
   }

   public Integer getQueueCapacity() {
      return queueCapacity;
   }


}