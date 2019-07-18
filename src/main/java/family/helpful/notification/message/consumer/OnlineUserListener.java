package family.helpful.notification.message.consumer;


import family.helpful.notification.actions.EnumActionStatus;
import family.helpful.notification.message.model.Channel;
import family.helpful.notification.message.model.ChannelContent;
import family.helpful.notification.message.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.ArrayList;
import java.util.List;


@EnableBinding({Sink.class})
public class OnlineUserListener
{
    private static final Logger logger = LoggerFactory.getLogger(ChannelListener.class);
    @Autowired
    private SimpMessagingTemplate template;

    private static List<User> onlineUserList= new ArrayList<>();
    private static long id=3;

    @StreamListener(target = Sink.INPUT,  condition = "headers['action'] == 'userLoggedIn'")
    public void userLoggedIn(Message<User> message) {

        logger.info(" received userLoggedIn message ["+message.toString()+"] ");

        User user= message.getPayload();

        user.setId(++id);

        onlineUserList.add(user);

        Message resultMessage= MessageBuilder.withPayload(onlineUserList)
                .setHeader("userLoggedIn"
                        , EnumActionStatus.SUCCESS.name()).build();

        this.template.convertAndSend("/topic/pushNotificationUserLoggedIn", resultMessage);


    }
}
