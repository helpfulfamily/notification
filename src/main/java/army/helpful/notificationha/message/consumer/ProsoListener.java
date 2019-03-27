package army.helpful.notificationha.message.consumer;


import army.helpful.notificationha.actions.EnumActionStatus;
import army.helpful.notificationha.actions.EnumActionTypes;
import army.helpful.notificationha.message.model.Content;

import army.helpful.notificationha.message.model.ContentMessage;
import army.helpful.notificationha.message.model.TitleMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.SimpMessagingTemplate;


@EnableBinding({Sink.class})
public class ProsoListener
{
    private static final Logger logger = LoggerFactory.getLogger(ProsoListener.class);
    @Autowired
    private SimpMessagingTemplate template;
    @StreamListener(target = Sink.INPUT,  condition = "headers['action'] == 'publishContent'")
    public void publishContent(Message<Content> message) {

       logger.info(" received publishContent ["+message.toString()+"] ");



       Message resultMessage= MessageBuilder.withPayload(message.getPayload())
                                       .setHeader(EnumActionTypes.publishContent.name()
                                                     , EnumActionStatus.SUCCESS.name()).build();

        this.template.convertAndSend("/topic/pushNotification", resultMessage);


    }
    @StreamListener(target = Sink.INPUT,  condition = "headers['action'] == 'getAllWithAmount'")
    public void getAllWithAmount(Message<TitleMessage> message) {

        logger.info(" received publishContent ["+message.toString()+"] ");



        Message resultMessage= MessageBuilder.withPayload(message.getPayload())
                .setHeader(EnumActionTypes.getAllWithAmount.name()
                        , EnumActionStatus.SUCCESS.name()).build();

     //   this.template.convertAndSend("/topic/pushNotification", resultMessage);

    }
    @StreamListener(target = Sink.INPUT,  condition = "headers['action'] == 'findByTitleWithAmount'")
    public void findByTitleWithAmount(Message<ContentMessage> message) {

        logger.info(" received publishContent ["+message.toString()+"] ");



        Message resultMessage= MessageBuilder.withPayload(message.getPayload())
                .setHeader(EnumActionTypes.findByTitleWithAmount.name()
                        , EnumActionStatus.SUCCESS.name()).build();

       // this.template.convertAndSend("/user/queue/towhom-userdeneme", resultMessage);


    }

}
