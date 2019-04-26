package family.helpful.notification.message.consumer;


import family.helpful.notification.actions.EnumActionStatus;


import family.helpful.notification.message.model.ProblemContent;
import family.helpful.notification.message.model.SolutionContent;
import family.helpful.notification.message.model.Transaction;
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
    @StreamListener(target = Sink.INPUT,  condition = "headers['action'] == 'publishProblemContent'")
    public void publishProblemContent(Message<ProblemContent> message) {

       logger.info(" received publishProblemContent ["+message.toString()+"] ");



       Message resultMessage= MessageBuilder.withPayload(message.getPayload())
                                       .setHeader("publishProblemContent"
                                                     , EnumActionStatus.SUCCESS.name()).build();

        this.template.convertAndSend("/topic/pushNotificationProblemContent", resultMessage);


    }
    @StreamListener(target = Sink.INPUT,  condition = "headers['action'] == 'publishSolutionContent'")
    public void publishSolutionContent(Message<SolutionContent> message) {

        logger.info(" received publishSolutionContent ["+message.toString()+"] ");



        Message resultMessage= MessageBuilder.withPayload(message.getPayload())
                .setHeader("publishSolutionContent"
                        , EnumActionStatus.SUCCESS.name()).build();

        this.template.convertAndSend("/topic/pushNotificationSolutionContent", resultMessage);


    }
    @StreamListener(target = Sink.INPUT,  condition = "headers['action'] == 'sendThankCoin'")
    public void sendThankCoin(Message<Transaction> message) {

        logger.info(" received sendThankCoin ["+message.toString()+"] ");



        Message resultMessage= MessageBuilder.withPayload(message.getPayload())
                .setHeader("sendThankCoin"
                        , EnumActionStatus.SUCCESS.name()).build();

        this.template.convertAndSend("/topic/sendThankCoin", resultMessage);


    }
}
