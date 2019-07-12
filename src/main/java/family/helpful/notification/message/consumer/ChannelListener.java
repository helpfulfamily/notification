package family.helpful.notification.message.consumer;


import family.helpful.notification.actions.EnumActionStatus;
import family.helpful.notification.message.model.*;
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
public class ChannelListener
{
    private static final Logger logger = LoggerFactory.getLogger(ChannelListener.class);
    @Autowired
    private SimpMessagingTemplate template;
    @StreamListener(target = Sink.INPUT,  condition = "headers['action'] == 'publishChannelContent'")
    public void publishProblemContent(Message<ChannelContent> message) {

       logger.info(" received publishChannelContent ["+message.toString()+"] ");



       Message resultMessage= MessageBuilder.withPayload(message.getPayload())
                                       .setHeader("publishChannelContent"
                                                     , EnumActionStatus.SUCCESS.name()).build();

        this.template.convertAndSend("/topic/pushNotificationChannelContent", resultMessage);


    }
    @StreamListener(target = Sink.INPUT,  condition = "headers['action'] == 'createChannel'")
    public void createFamily(Message<Channel> message) {

        logger.info(" received createFamily ["+message.toString()+"] ");



        Message resultMessage= MessageBuilder.withPayload(message.getPayload())
                .setHeader("createChannel"
                        , EnumActionStatus.SUCCESS.name()).build();

        this.template.convertAndSend("/topic/pushNotificationChannel", resultMessage);


    }
}
