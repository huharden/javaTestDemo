package com.hj.study.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
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

}