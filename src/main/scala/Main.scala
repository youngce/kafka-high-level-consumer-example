/**
  * Created by mark on 12/6/15.
  */
object Main extends App{
  //Set your configurations
  val props = new java.util.Properties();
  props.put("zookeeper.connect", "localhost:2181");
  props.put("group.id", "high-level");
  props.put("zookeeper.session.timeout.ms", "400");
  props.put("zookeeper.sync.time.ms", "200");
  props.put("auto.commit.interval.ms", "1000");
  val config= new kafka.consumer.ConsumerConfig(props);

  //Creating consumer client
  val consumer=kafka.consumer.Consumer.create(config)
  //Creating the thread pool
  val topic="topic1"
  val numThread=1
  val topicCounts=Map(topic->numThread)
  val consumerMap=consumer.createMessageStreams(topicCounts)

  val consumerIterator=consumerMap.get(topic).get.head.iterator()
  consumerIterator.map(_.message()).foreach(msg=>println(msg))
}
