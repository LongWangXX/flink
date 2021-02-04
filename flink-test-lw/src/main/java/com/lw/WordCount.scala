package com.lw

import java.util
import java.util.Properties

import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
import org.apache.kafka.clients.consumer.ConsumerConfig

import scala.collection.mutable

object WordCount {
  def main(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    val props = new Properties()
    props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.7.128:9092")
    props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "TEST")

    val consumer = new FlinkKafkaConsumer[String]("test", new SimpleStringSchema(), props)
    //生成一个DataStreamSource
    //创建一个LegacySourceTransformation
    env.addSource(consumer)
      //创建一个SingleOutputStreamOperator
      //生成OneInputTransformation
      //并且将Transformation添加到env里去
      .flatMap(_.split(" "))
      //创建一个SingleOutputStreamOperator
      //生成OneInputTransformation
      //并且将Transformation添加到env里去
      .map((_,1))
      //将map的transformation传入
      // 创建一个KeyedStream
      //创建一个PartitionerTransformation
      //将其返回
      .keyBy((x)=>{x._1})
      //SingleOutputStreamOperator
      //并且将Transformation添加到env里去
      .sum(1)
      .print("job")
    //执行任务
    env.execute("job")
  }
}
